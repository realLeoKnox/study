// DOM 元素加载完成后执行
document.addEventListener('DOMContentLoaded', () => {
    const wrapper = document.getElementById('fullpage-wrapper');
    const pages = document.querySelectorAll('#fullpage-wrapper > div'); // 获取所有的整页容器 (container, container_2)
    const totalPages = pages.length;

    let currentPageIndex = 0;
    let isScrolling = false; // 防止滚动过快的节流锁

    // 核心函数：移动页面
    function moveToPage(index) {
        if (index < 0 || index >= totalPages) return;

        currentPageIndex = index;
        // 计算需要往上提的百分比。比如第0页就是0, 第1页就是 -100vh
        const translateY = -(currentPageIndex * 100);
        wrapper.style.transform = `translateY(${translateY}vh)`;

        // 锁定滚动一小段时间，等待 CSS 动画 (0.8s) 完成
        isScrolling = true;
        setTimeout(() => {
            isScrolling = false;
        }, 1000); // 稍微比 CSS 动画长一点，手感更好
    }

    // 监听鼠标滚轮事件 (电脑端)
    window.addEventListener('wheel', (event) => {
        if (isScrolling) return; // 如果正在动画中，忽略这次滚动

        // 判断当前页面内是否可以内部滚动（比如手机屏太小，第一页内容很多自己出现了滚动条）
        const currentContainer = pages[currentPageIndex];

        // 如果容器内容高度大于容器本身高度，说明单页内部有滚动条
        const isScrollable = currentContainer.scrollHeight > currentContainer.clientHeight;

        if (event.deltaY > 0) {
            // 向下滚
            // 如果单页内部能滚动，且还没滚到底部，就不翻页，让它内部滚
            if (isScrollable && currentContainer.scrollTop + currentContainer.clientHeight < currentContainer.scrollHeight - 1) {
                return;
            }
            moveToPage(currentPageIndex + 1);
        } else if (event.deltaY < 0) {
            // 向上滚
            // 如果单页内部能滚动，且还没滚到顶部，就不翻页，让它内部滚
            if (isScrollable && currentContainer.scrollTop > 0) {
                return;
            }
            moveToPage(currentPageIndex - 1);
        }
    }, { passive: false }); // 注意这里不需要 preventDefault，我们要保留单页内部滑动的能力

    // 监听移动端触摸滑动事件 (手机端)
    let startY = 0;
    window.addEventListener('touchstart', (event) => {
        startY = event.touches[0].clientY;
    }, { passive: true });

    window.addEventListener('touchend', (event) => {
        if (isScrolling) return;

        const endY = event.changedTouches[0].clientY;
        const deltaY = startY - endY;

        // 设置一个滑动阈值，防止误触 (滑动超过 50px 才算翻页)
        if (Math.abs(deltaY) < 50) return;

        const currentContainer = pages[currentPageIndex];
        const isScrollable = currentContainer.scrollHeight > currentContainer.clientHeight;

        if (deltaY > 0) {
            // 向上滑 (看下面的内容) -> 下一页
            if (isScrollable && Math.ceil(currentContainer.scrollTop + currentContainer.clientHeight) < currentContainer.scrollHeight) {
                return;
            }
            moveToPage(currentPageIndex + 1);
        } else if (deltaY < 0) {
            // 向下滑 (看上面的内容) -> 上一页
            if (isScrollable && currentContainer.scrollTop > 0) {
                return;
            }
            moveToPage(currentPageIndex - 1);
        }
    }, { passive: true });

    // === 鼠标跟随光效逻辑 ===
    const cursorGlow = document.getElementById('cursor-glow');

    // 只在支持精确指针(比如鼠标)的设备上开启这特效，纯触屏设备不开启
    if (window.matchMedia("(pointer: fine)").matches) {
        document.addEventListener('mousemove', (e) => {
            cursorGlow.style.opacity = '1'; // 鼠标进入页面后显形

            // 时刻更新光效的坐标
            cursorGlow.style.left = e.clientX + 'px';
            cursorGlow.style.top = e.clientY + 'px';
        });

        // 当鼠标离开浏览器可视区域时，让光晕熄灭
        document.documentElement.addEventListener('mouseleave', () => {
            cursorGlow.style.opacity = '0';
        });
    }

    // =========================================
    // === 酷炫的 Three.js 3D 粒子星空背景 ===
    // =========================================

    // 1. 初始化场景、相机和渲染器
    const canvas = document.getElementById('bg-canvas');
    const scene = new THREE.Scene();

    // 透视相机: (视野角度, 长宽比, 近裁剪面, 远裁剪面)
    const camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1000);
    camera.position.z = 30; // 相机往后退一点，才能看清全貌

    const renderer = new THREE.WebGLRenderer({
        canvas: canvas,
        alpha: true, // 允许透明背景
        antialias: true // 抗锯齿
    });
    renderer.setSize(window.innerWidth, window.innerHeight);
    renderer.setPixelRatio(Math.min(window.devicePixelRatio, 2)); // 适配高清屏

    // 2. 创建粒子系统
    const particlesGeometry = new THREE.BufferGeometry();
    const particlesCount = 1500; // 粒子数量

    // 每个粒子需要 x, y, z 三个维度的坐标
    const posArray = new Float32Array(particlesCount * 3);

    for (let i = 0; i < particlesCount * 3; i++) {
        // 在 -50 到 50 的空间内随机撒点
        posArray[i] = (Math.random() - 0.5) * 100;
    }

    // 把坐标数据塞进几何体
    particlesGeometry.setAttribute('position', new THREE.BufferAttribute(posArray, 3));

    // 使用基础材质给粒子上色
    const particlesMaterial = new THREE.PointsMaterial({
        size: 0.15,
        color: 0xffffff, // 纯白色粒子
        transparent: true,
        opacity: 0.6,
        blending: THREE.AdditiveBlending // 叠加混合，显得稍微亮一点
    });

    // 将几何体和材质组合成最终的粒子网格，并加入场景
    const particleMesh = new THREE.Points(particlesGeometry, particlesMaterial);
    scene.add(particleMesh);

    // 3. 监听鼠标移动，让整个星空有轻微的视差(Parallax)互动感
    let mouseX = 0;
    let mouseY = 0;
    let targetX = 0;
    let targetY = 0;
    const windowHalfX = window.innerWidth / 2;
    const windowHalfY = window.innerHeight / 2;

    document.addEventListener('mousemove', (event) => {
        // 将鼠标坐标转换成一个基于中心的相对值
        mouseX = (event.clientX - windowHalfX);
        mouseY = (event.clientY - windowHalfY);
    });

    // 4. 处理浏览器窗口调整大小
    window.addEventListener('resize', () => {
        camera.aspect = window.innerWidth / window.innerHeight;
        camera.updateProjectionMatrix();
        renderer.setSize(window.innerWidth, window.innerHeight);
    });

    // 5. 核心动画循环
    const clock = new THREE.Clock();

    // === 动态页面过渡特效参数 ===
    const pageColors = [
        new THREE.Color(0xffffff), // 第1页: 纯白星空
        new THREE.Color(0x00d2ff), // 第2页: 赛博蓝星空
        new THREE.Color(0x9d4edd)  // 第3页: 魅惑紫星空 (修改这里)
    ];
    const pageZPositions = [30, 15, 45]; // 每页相机的深浅距离不同

    function animate() {
        requestAnimationFrame(animate);

        const elapsedTime = clock.getElapsedTime();

        // 粒子自己缓慢自转
        particleMesh.rotation.y = elapsedTime * 0.05;
        particleMesh.rotation.x = elapsedTime * 0.02;

        // 根据鼠标位置，轻微且平滑地改变整体旋转角度 (缓动效果)
        targetX = mouseX * 0.001;
        targetY = mouseY * 0.001;

        particleMesh.rotation.y += 0.05 * (targetX - particleMesh.rotation.y);
        particleMesh.rotation.x += 0.05 * (targetY - particleMesh.rotation.x);

        // 【核心平滑过渡逻辑】：根据当前所在页数，逐渐改变颜色和相机的远近
        // lerp() 可以在每一帧平滑地将当前颜色靠拢到目标颜色
        particlesMaterial.color.lerp(pageColors[currentPageIndex], 0.03);
        // 平滑推拉相机镜头
        camera.position.z += (pageZPositions[currentPageIndex] - camera.position.z) * 0.03;

        renderer.render(scene, camera);
    }

    animate(); // 启动动画！
});


// =========================================
// === 基础的网页保护 / 防审查逻辑 ===
// =========================================

// 1. 彻底屏蔽鼠标右键菜单
document.addEventListener('contextmenu', (e) => {
    e.preventDefault();
});

// 2. 屏蔽键盘上一些常见的开发者调试按键组合
document.addEventListener('keydown', (e) => {
    // 屏蔽 F12 (开发者工具)
    if (e.key === 'F12' || e.keyCode === 123) {
        e.preventDefault();
    }
    // 屏蔽 Ctrl+Shift+I 或 Cmd+Option+I (直接打开开发者工具)
    if ((e.ctrlKey || e.metaKey) && e.shiftKey && (e.key === 'I' || e.key === 'i' || e.keyCode === 73)) {
        e.preventDefault();
    }
    // 屏蔽 Ctrl+U 或 Cmd+Option+U (查看源代码)
    if ((e.ctrlKey || e.metaKey) && (e.key === 'U' || e.key === 'u' || e.keyCode === 85)) {
        e.preventDefault();
    }
    // 屏蔽 Ctrl+S 或 Cmd+S (保存网页)
    if ((e.ctrlKey || e.metaKey) && (e.key === 'S' || e.key === 's' || e.keyCode === 83)) {
        e.preventDefault();
    }
});
