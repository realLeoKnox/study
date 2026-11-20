# Class 16：JavaScript 入门与引入方式

> Class 1–15 已用 HTML 组织内容、用 CSS 设置外观。从本课开始加入 JavaScript（JS），让页面能响应用户操作。JS 与 Java 是两种不同的语言。

## 本课目标

- 说清 HTML、CSS、JavaScript 各自负责什么。
- 会用内嵌脚本和外部 `.js` 文件给页面添加交互。
- 理解脚本执行时机，能给按钮绑定点击事件。
- 知道 `alert()` 与 `console.log()` 的用途。

## 一、三种技术怎样配合

| 技术 | 主要任务 | 例子 |
| --- | --- | --- |
| HTML | 表达内容和结构 | 标题、段落、按钮 |
| CSS | 控制外观和布局 | 颜色、宽度、间距 |
| JavaScript | 处理数据和交互 | 点击按钮后显示提示、更新文字 |

JavaScript 可在浏览器中运行，也可在其他运行环境中使用。本课只讨论**网页中的浏览器 JavaScript**。浏览器解析 HTML 后建立文档对象模型（DOM）；JS 可以读取和修改页面元素，并响应点击等事件。

---

## 二、把 JavaScript 写进页面

### 1. 内嵌脚本

使用 `<script>` 标签；在普通 HTML 文档里，JavaScript 是默认脚本语言，不必再写 `type="text/javascript"`。

```html
<body>
    <p>欢迎学习 JavaScript</p>
    <script>
        console.log("页面脚本已执行");
    </script>
</body>
```

浏览器解析到普通 `<script>` 时，会执行其中的代码。若脚本要操作某个元素，应确保该元素已经存在；把脚本放在相关 HTML 之后是初学时容易理解的写法。

### 2. 外部脚本

把代码存进 `.js` 文件，再用 `src` 引入。路径规则与 Class 3 的链接、Class 6 的图片路径相同。

```html
<script src="./main.js" defer></script>
```

```js
// main.js
console.log("外部脚本已执行");
```

`defer` 用于外部普通脚本：浏览器下载脚本时继续解析 HTML，等文档解析完成后再执行。课堂 `example/lesson.html` 在 `<head>` 中直接引入 `main.js`，其提示框可能先于页面正文出现；新代码中需要操作页面元素时，使用 `defer` 更稳妥。

### 3. 写在元素事件属性中（认识即可）

课堂示例用 `onclick="tishi()"` 让按钮调用函数。这种写法可以运行，但会把结构和行为混在 HTML 中；练习新代码时可用 `addEventListener()` 绑定事件。

---

## 三、一个完整的按钮示例

**index.html：**

```html
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>按钮交互</title>
    <script src="./main.js" defer></script>
</head>
<body>
    <button id="hello-button" type="button">点我</button>
</body>
</html>
```

**main.js：**

```js
const button = document.querySelector("#hello-button");
button.addEventListener("click", () => {
    alert("欢迎访问本站");
});
```

执行过程：① HTML 创建按钮；② `defer` 脚本在文档解析后运行；③ `querySelector()` 找到按钮；④ `addEventListener()` 注册点击处理函数；⑤ 点击时才执行 `alert()`。

`#hello-button` 是 CSS 式的 ID 选择器写法，与 Class 9 的 `#id名` 对应。若选择器写错，`querySelector()` 会得到 `null`，不能直接调用 `addEventListener()`。

---

## 四、常用观察方式

| 写法 | 效果 | 适合场景 |
| --- | --- | --- |
| `alert("提示")` | 弹出阻塞页面的提示框 | 初学演示、少量重要提示 |
| `console.log(值)` | 输出到浏览器开发者工具控制台 | 检查变量、调试执行过程 |

课堂页面加载时连续调用 `alert()`，能直观看见脚本执行，但提示框会打断操作。练习数据和变量时通常优先用 `console.log()`。

## 同期页面练习：CSS 悬停与 JavaScript 的区别

`task2/hebing.html` 是前几课 header、banner、footer 的综合页面，导航图片用 `.a1:hover` 等 CSS 规则在鼠标悬停时更换背景。这也是一种交互效果，但它由 **CSS 伪类 `:hover`** 完成，页面里没有为此编写 JavaScript。要处理更复杂的点击逻辑和数据，再使用本课的事件监听。

这份练习的若干 `<li>` 直接放在 `<div>` 中；新代码应把列表项放进 `<ul>` 或 `<ol>`。课堂练习文件保留原样，此处只记录规范写法。

## 常见错误

1. 把 JavaScript 当作 Java：两者语法有相似处，但类型和运行环境不同。
2. 外部脚本路径写错，或把 `<script src="…">` 后面的内联代码当成同一个脚本执行；内联代码应写在另一个 `<script>` 中。
3. 脚本过早查找尚未解析出的元素，得到 `null`。
4. 函数名、文件名大小写不一致；JavaScript 区分大小写。

## 本课小结

HTML 给页面内容，CSS 给页面样式，JavaScript 负责行为。脚本可写在 HTML 中，也可放在外部 `.js` 文件；按钮交互可以按“找到元素 → 监听事件 → 执行函数”的顺序理解。

## 与下一课相连

已经能让代码在浏览器中运行。下一课学习代码里处理的**值**：变量、基本数据类型、函数、数组和对象。

## 自测题

1. `<script src="./main.js" defer></script>` 中 `src` 和 `defer` 分别做什么？
2. 为什么在按钮尚未被解析时查询它可能得到 `null`？
3. `alert()` 与 `console.log()` 的用途有何不同？

## 重点掌握

能独立写出“按钮点击后显示提示”的 HTML 与 JS，并解释从页面元素到事件处理函数的连接过程。
