# Class 14：盒子模型（边框、圆角、内外边距）与页脚案例

Class 13 用浮动做了导航栏 header。本节课先把**盒子模型**讲完整：**边框 `border`、圆角 `border-radius`、内边距 `padding`、外边距 `margin`**，用一组 5 个灰色 `div` 练手；然后用浮动 + 盒子模型做圣华家具网站的**页脚 footer**。

下面所有“实测”数字，都是把课堂代码放进 Chromium 里渲染量出来的（`body` 默认外边距 8px）。

---

## 一、盒子模型：一个元素由四层组成

从里到外：**内容 content → 内边距 padding → 边框 border → 外边距 margin**。

```
┌──────────── margin（外边距，透明，管和别人的距离）
│ ┌────────── border（边框）
│ │ ┌──────── padding（内边距，管内容和边框的距离）
│ │ │ ┌────── content（内容，width / height 设的就是它）
```

课堂代码用的是**标准盒模型**（`box-sizing: content-box`，浏览器默认），所以：

```
盒子实际占的宽 = width + 左右 padding + 左右 border
盒子实际占的高 = height + 上下 padding + 上下 border
```

`width` / `height` 只管**内容区**，加了 padding 和 border 盒子就会变大。这一点在下面的案例里直接能量出来。

---

## 二、边框 `border`

### 1. 三要素与综合写法

```css
border: 1px solid #f00;     /* 宽度 样式 颜色，顺序可以换，但习惯这样写 */
```

| 部分 | 含义 |
| --- | --- |
| `border-width` | 粗细，如 `1px` |
| `border-style` | 样式，**必须写**，否则不显示 |
| `border-color` | 颜色，如 `#f00` |

### 2. 边框样式取值

| 值 | 效果 |
| --- | --- |
| `none` | 没有边框（默认值） |
| `solid` | 实线 |
| `dashed` | 虚线（短横线） |
| `dotted` | 点线 |
| `double` | 双线（需要宽度 ≥ 3px 才看得出是两条） |

★ `border-style` 的默认值是 `none`，所以**只写宽度和颜色、不写样式，边框不会出现**。

### 3. 四个边分别设置

| 写法 | 含义 |
| --- | --- |
| `border-top` | 上边框 |
| `border-bottom` | 下边框 |
| `border-left` | 左边框 |
| `border-right` | 右边框 |
| `border` | 四边一起设置（综合） |

---

## 三、案例一：`class14.html`（边框 + 内外边距 + 圆角）

公共样式：

```css
div{
  width: 220px;
  height: 100px;
  background-color: #777;
  margin-bottom: 10px;      /* 每个 div 下面留 10px */
  font-size: 36px;
}
```

五个 `div` 各自再加不同的样式。

### 1. `.d1`：上边框 + padding + margin-top

```css
.d1{
  border-top: 1px solid #f00;   /* 只有上边框 */
  padding: 15px;                /* 四边内边距 15px */
  margin-top: 30px;             /* 上外边距 30px */
}
```

实测：

| 项目 | 实测 | 怎么算 |
| --- | --- | --- |
| 实际宽 | 250px | 220 + 15 + 15（左右没有边框） |
| 实际高 | 131px | 100 + 15 + 15 + 1（上边框 1px） |
| 位置 y | 30 | **不是 8 + 30 = 38**，见下面 |

★ **为什么 y 是 30 而不是 38？** 浏览器给 `body` 默认 8px 外边距，`d1` 又设了 30px 的上外边距。因为 `body` 没有边框和内边距，**父元素和第一个子元素的上外边距会合并，取较大值 30px**（不是相加）。这就是 `class14.md` 里说的“父元素的 margin 也有可能和子元素发生塌陷（合并）”。

### 2. `.d2`：左右边框（虚线）+ padding-left + margin-left

```css
.d2{
  border-left: 4px dashed #f00;
  border-right: 4px dashed #f00;
  padding-left: 15px;
  margin-left: 30px;
}
```

实测：

| 项目 | 实测 | 怎么算 |
| --- | --- | --- |
| 实际宽 | 243px | 220 + 15（左内边距）+ 4 + 4（左右边框） |
| 实际高 | 100px | 上下都没设 padding 和 border |
| 位置 x | 38 | 8（body）+ 30（margin-left） |
| 位置 y | 171 | `.d1` 底部在 30 + 131 = 161，再加 `.d1` 的 `margin-bottom: 10px` |

这里 `margin-left` 能把盒子推到右边 30px，说明外边距是**盒子外面**的距离；`padding-left` 则让文字离左边框更远（看截图里 `div` 字往右缩进了）。

### 3. `.d3`：四边点线

```css
.d3{ border: 4px dotted #ff0000; }
```

实测 228 × 108：`220 + 4 + 4` 和 `100 + 4 + 4`。**边框会增加盒子尺寸**。

### 4. `.d4`：双线 + 圆角

```css
.d4{
  border: 4px double #f00;     /* 4px 才能看出两条线 */
  border-radius: 10px;         /* 四个角都是半径 10px 的圆角 */
}
```

同样 228 × 108。`double` 的 4px 被分成“线—空—线”三份。

### 5. `.d5`：用 `/` 分别设置水平、垂直半径

```css
.d5{ border-radius: 100px/50px; }
```

没有边框，所以尺寸就是 220 × 100。实测四个角的圆角都是**水平 100px、垂直 50px**。

---

## 四、圆角 `border-radius`（CSS3）

radius = 半径（距离）。

### 1. 取值规律

```css
border-radius: 左上角 右上角 右下角 左下角;   /* 顺时针，从左上开始 */
```

| 写几个值 | 含义 |
| --- | --- |
| 1 个 | 四个角都一样 |
| 2 个 | 左上和右下 / 右上和左下 |
| 4 个 | 左上、右上、右下、左下 |
| 用 `/` | `/` 前面是**水平半径**，后面是**垂直半径** |

### 2. 怎么做椭圆？

**答案：`border-radius: 50%;`**

原理：百分比的分母不一样——**水平方向的半径按宽度的 50% 算，垂直方向的半径按高度的 50% 算**。

- 一个 200 × 100 的 div：水平半径 = 100px，垂直半径 = 50px → 椭圆。
- 如果是**正方形**，两个半径相等 → 变成**圆**。

### 3. 和案例 `.d5` 对比

`.d5` 是 220 × 100，写的是 `100px/50px`：

- 垂直半径 50px = 高度的一半，上下刚好圆满；
- 水平半径 100px **比宽度的一半（110px）小 10px**，所以顶部和底部各剩一段 **20px 的直线**（看截图能发现不是标准椭圆，上下边缘是平的）。
- 要做成标准椭圆，应该写 `border-radius: 50%;`（等价于 `110px/50px`）。

---

## 五、内边距 `padding` 与外边距 `margin`

| | `padding`（内边距） | `margin`（外边距） |
| --- | --- | --- |
| 管什么 | 边框和内容之间的距离 | 盒子和盒子之间的距离 |
| 背景色 | **会延伸**到 padding 区域 | 不会（透明） |
| 负值 | **不能**取负值 | **可以**取负值（位移 / 重叠） |
| 对盒子尺寸 | 会增大盒子（标准盒模型） | 不增大盒子本身，但增加占位 |

### 1. 简写规律（`margin`、`padding` 都一样）

| 写几个值 | 含义 | 例子 |
| --- | --- | --- |
| 1 个 | 四边相同 | `padding: 15px;` |
| 2 个 | 上下 / 左右 | `margin: 0 auto;` |
| 3 个 | 上 / 左右 / 下 | `margin: 10px 20px 30px;` |
| 4 个 | 上 / 右 / 下 / 左（顺时针，从上开始） | `padding: 5px 10px 15px 20px;` |

### 2. 容易错的地方（整理自课堂表格）

- **`margin: 0 auto` 只能让有固定宽度的块级元素水平居中**（Class 13 的 `.center { width: 980px; margin: 0 auto; }`）。
- **相邻块级元素的上下 margin 会合并**，取较大值，不是相加。只发生在普通流的垂直方向；浮动元素之间不合并。
- **父元素的上 margin 会和第一个子元素的上 margin 合并**（案例 `.d1` 就是）。父元素有 `border`、`padding` 或 `overflow` 时就不会合并。
- **行内元素（`inline`）的上下 margin 不生效。**
- **百分比取值**：`margin`、`padding` 的百分比，水平和垂直方向**都按父元素（包含块）的宽度**计算，不是按高度。
- **`padding` 不能实现元素居中**：水平居中用 `margin: 0 auto`，文字居中用 `text-align: center`。
- **`box-sizing: border-box`**：设置后 `width` 把 padding 和 border 也算进去，加 padding 不再撑大盒子。课堂代码没有用到它，只作了解。
- **`border-collapse`**（表格）：只对 `<table>` 有效，`collapse` 是合并相邻单元格边框；默认 `separate`。

---

## 六、案例二：`footer.html`（圣华家具页脚）

页脚由**上下两条**组成：上面是 35px 高的黑色导航条，下面是 170px 高的深棕色版权区。

### 1. 结构

```
.footer                      字号 14px，文字颜色 #9F582C（整体继承）
├─ .footer_top               黑色条，高 35px
│  └─ .footer_top_cen        980px 版心，居中
│     ├─ .footer_top_cen_left    float:left，里面 8 个 li（首页|关于圣华|…）
│     └─ .footer_top_cen_right   float:right，口号“百年圣华 锻木成金”
└─ .footer_btm               深棕色条，高 170px
   └─ .footer_btm_cen        980px 版心，居中
      ├─ .footer_btm_cen_left  上内边距 10px，里面 3 个 div 横排
      │   ├─ _left_1   4 行版权/电话/地址/备案
      │   ├─ _left_2   放图片
      │   └─ _left_3   空 div（预留位置）
      └─ .footer_btm_cen_right 空 div（预留位置）
```

### 2. 逐条分析 CSS

```css
.footer { font-size: 14px; color: #9F582C; }        /* 整个页脚统一字号、文字色 */
li { float: left; list-style: none; }                /* 和 header 一样：横排、去圆点 */

.footer_top { height: 35px; background-color: #000; line-height: 35px; }
.footer_top a { color: #9F582C; text-decoration: none; }
.footer_top_cen { width: 980px; margin: 0 auto; }    /* 版心居中 */
.footer_top_cen_left  { float: left; }
.footer_top_cen_right { float: right; letter-spacing: 15px; }
```

- **`line-height: 35px`**：等于 `.footer_top` 的高度，文字上下居中（Class 12）。
- **`.footer_top a { color…; text-decoration: none; }`**：`<a>` 有浏览器自带的蓝色和下划线，**不会继承** `.footer` 的颜色，所以要单独设置。而 `|` 在 `<a>` 外面（写在 `<li>` 里），直接继承 `.footer` 的颜色。
- **`float: left` / `float: right`**：左边放导航文字，右边放口号，两头对齐。浮动盒子宽度**由内容撑开**（实测左 442.5px、右 250.5px）。
- **`letter-spacing: 15px`**：口号字与字之间拉开（Class 12）；两句之间的空隙是源码里换行折成的一个空格。

```css
.footer_btm { height: 170px; background-color: #4A311B; }
.footer_btm_cen { margin: 0 auto; width: 980px; }
.footer_btm_cen_left { padding-top: 10px; }
.footer_btm_cen_left div { float: left; }            /* 后代选择器：里面所有 div 横排 */
.footer_btm_cen_left_2 { padding-right: 150px; padding-top: 25px; }
```

- **`.footer_btm_cen_left div { float: left; }`**：空格是**后代选择器**，选中 `.footer_btm_cen_left` 里面的**所有 `div`**（3 个），让它们横向排开。
- **`.footer_btm_cen_left_2` 用 padding 调位置**：`padding-right: 150px` 在图片右边留出空位，`padding-top: 25px` 把图片往下压。实测这个 div 宽 223px = 73（图片宽）+ 150。
- 这里用 `padding` 推开位置（用 `margin` 也能做到同样的效果），老师选了 `padding`，所以盒子本身的宽度变成 223px，下一个浮动盒子 `_left_3` 就从它后面开始排。

### 3. 实测：高度塌陷再次出现

| 元素 | 实测高度 | 原因 |
| --- | --- | --- |
| `.footer_top`（写了 35px） | 35px | 固定高度 |
| `.footer_top_cen` | **0px** | 里面全是浮动，撑不起来 |
| `.footer_btm_cen` / `.footer_btm_cen_left` | **10px** | 只剩 `padding-top: 10px`，子元素全是浮动 |

把 `.footer_top` 和 `.footer_btm` 的 `height` 去掉再量：`.footer_top` 变成 **0px**（黑条消失），`.footer_btm` 只剩 **10px**。所以和 header 一样，**外层必须写死高度**（35px、170px），页脚才能撑出来。

### 4. 实测：图片下面多出 4px

`.footer_btm_cen_left_2` 实测高度 **108px**，而 `25 + 79 = 104`。多出的 4px 来自**图片默认是行内元素，下面要给文字基线留一点缝隙**。给图片加 `display: block` 后再量就是 **104px**。

> 解决图片底部缝隙的常见办法：`img { display: block; }`，或者 `vertical-align: bottom`。课上没讲，作为发现记录。

### 5. 页面里的几个细节

- 图片路径 `../class13/images/a1.jpg`：`../` 表示**回到上一级目录**，这里引用了上一节课的素材。
- 这张 `a1.jpg` 其实是导航栏里的“首页”菜单图（73×79），在页脚里应该只是**占位**；真实网站这里会换成二维码或者 logo。（这是我的判断，课上有没有说明未确认）
- 版权、电话、备案号都是示例内容，ICP 备案编号 `京ICP备1234567890号` 是假的。
- 和 header 一样，`li` 直接放在 `div` 里，**不符合规范**，标准写法是 `ul > li`。

---

## 七、易错点汇总

1. **`border` 不写样式就看不见**：`border-style` 默认 `none`，`border: 4px #f00;` 无效，必须写 `solid` / `dashed` 等。
2. **盒子比你设的 `width` 大**：标准盒模型下，实际宽 = `width` + padding + border（`.d2` 是 243px，不是 220px）。
3. **`margin` 不是相加，而是合并**：父子之间、相邻块之间的上下 margin 取较大值（`.d1` 的 y=30）。
4. **`border-radius: 50%` 才是标准椭圆/圆**：`100px/50px` 宽比 200 大时上下会剩一段直线。
5. **全是浮动的父元素高度为 0**：页脚、导航栏都要给外层写固定高度，清除浮动的方法老师还没讲。
6. **`<a>` 的颜色不继承**：想改链接颜色，要单独给 `a` 设置，并用 `text-decoration: none` 去掉下划线。
7. **`border-collapse` 只对表格有效**，不要用在 `div` 上。
8. **`padding` 不能为负数**，`margin` 可以。
