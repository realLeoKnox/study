# Class 12：文本外观、显示模式与链接伪类

Class 9–11 学会了选择器、文字样式和文章排版。本节继续补充 CSS 外观属性：**字间距、半透明颜色、文本装饰**；再学习**标签显示模式**和**链接伪类**，最后用背景图做一个竖向导航菜单。

## 一、回顾：CSS 外观属性

| 属性 | 作用 | 例子 |
| --- | --- | --- |
| `color` | 文字颜色 | `color: #333;` |
| `text-align` | 水平对齐 | `text-align: center;` |
| `text-indent` | 首行缩进 | `text-indent: 2em;` |
| `line-height` | 行高 | `line-height: 1.6;` |

---

## 二、字间距 `letter-spacing`

`letter-spacing` 设置**字符之间**的距离，默认值是 `normal`。

```css
h1 { letter-spacing: 2px; }      /* 每个字之间加宽 2px */
p  { letter-spacing: 0.1em; }    /* 按字号比例加宽 */
.tight { letter-spacing: -1px; } /* 可以写负值，让字更紧 */
```

- 单位可以用 `px`、`em`，也可以写负值。中文和英文都按单个字符加间距。
- 区分 `word-spacing`（词间距）：它只加宽**空格**，对英文单词之间有效，对中文基本没有效果。

---

## 三、颜色的写法与半透明

### 1. 十六进制与 `rgb()`

两者都由**红、绿、蓝**三个分量组成，只是进制不同：`rgb()` 用十进制（0–255），`#` 用十六进制（`00`–`FF`），每两位表示一个颜色。

```
rgb(255, 0, 0)  =  #FF0000  =  #F00（红色）
     R   G  B        RR GG BB
```

| 颜色 | `rgb()` | 十六进制 |
| --- | --- | --- |
| 黑 | `rgb(0, 0, 0)` | `#000000` / `#000` |
| 白 | `rgb(255, 255, 255)` | `#FFFFFF` / `#FFF` |
| 红 | `rgb(255, 0, 0)` | `#FF0000` / `#F00` |
| 灰 | `rgb(128, 128, 128)` | `#808080` |

每组两位相同时可以简写为三位，例如 `#333333` → `#333`。`#333`、`#666` 这类“三位相同”的值都是灰色，数字越小颜色越深。

### 2. 半透明 `rgba()`

`rgba(r, g, b, a)` 的第四个值 **a（alpha）表示不透明度**，取值 0–1：0 是完全透明，1 是完全不透明。

```css
p   { color: rgba(0, 0, 0, 0.5); }               /* 50% 透明的黑字 */
div { background-color: rgba(255, 0, 0, 0.3); }  /* 淡红色半透明背景 */
```

| 写法 | 透明的范围 |
| --- | --- |
| `rgba()` | 只有**这一个颜色**透明，例如背景透明而文字不受影响 |
| `opacity: 0.5` | **整个元素连同里面的内容**一起透明 |

十六进制也可以写透明度，例如 `#FF000080`；新语法 `rgb(0 0 0 / 50%)` 也行。课堂和考试按 `rgba()` 记即可。

---

## 四、文本装饰 `text-decoration`

| 值 | 效果 |
| --- | --- |
| `none` | 没有装饰线（**最常用：去掉链接下划线**） |
| `underline` | 下划线（链接的默认效果） |
| `overline` | 上划线 |
| `line-through` | 删除线 |

```css
a { text-decoration: none; }
```

删除线只是外观效果；如果内容表示“已删除”或“不再适用”，HTML 中应使用 Class 3 学过的 `<del>` 或 `<s>`。

---

## 五、标签显示模式 `display`

Class 1 学过：`<div>` 默认是块级元素，`<span>` 默认是行内元素。`display` 可以改变元素的显示模式。

| 模式 | 特点 | 代表标签 |
| --- | --- | --- |
| 块级 `block` | 独占一行，可以设置宽高 | `div`、`p`、`h1`～`h6`、`ul`、`li` |
| 行内 `inline` | 同一行排列，**宽高设置无效**，大小由内容决定 | `a`、`span`、`strong`、`em` |
| 行内块 `inline-block` | 同一行排列，又能设置宽高 | `img`、`input` |

```css
a    { display: block; }         /* 行转块：链接可以设宽高，整块都能点击 */
div  { display: inline; }        /* 块转行 */
span { display: inline-block; }  /* 转为行内块 */
```

**典型用途：**把 `<a>` 转为块级元素，再设置宽高，就能做成整块可点击的导航按钮（见第七部分）。

---

## 六、链接伪类选择器

| 伪类 | 含义 |
| --- | --- |
| `:link` | 未访问过的链接 |
| `:visited` | 已访问过的链接 |
| `:hover` | 鼠标移到链接上 |
| `:active` | 鼠标在链接上按下时 |

```css
a:link    { color: #333; }
a:visited { color: #999; }
a:hover   { color: red; }
a:active  { color: green; }
```

### 顺序必须是 LVHA

记忆口诀：**L**o**V**e **HA**te。四个伪类的优先级相同，同等优先级下**后写的规则覆盖先写的**。例如把 `:hover` 写在 `:visited` 前面，已访问的链接在鼠标经过时就不会变色。

### 使用要点

- `:link`、`:visited` 只对带 `href` 的链接有效；`:hover`、`:active` 可以用于任何元素，例如 `li:hover`。
- 出于隐私考虑，`:visited` 只能修改颜色类的属性（文字颜色、背景色、边框颜色等）。
- `:active` 只在按下的一瞬间生效，松开就没有了。
- `a:link` 的优先级比单独的 `a` 高，所以写了 `a:link` 之后，再用 `a { color: … }` 改颜色不会生效。
- 实际开发中最常用的是 `a { }` 加 `a:hover { }`。

---

## 七、课堂案例：用背景图做竖向导航菜单

课堂代码（`class12.html`）：

```html
<style>
  li {
    width: 100px;
    height: 35px;
    font-size: 16px;
    background-image: url("./0001.gif");
    margin-bottom: 6px;
    list-style-type: none;
    background-repeat: no-repeat;
    text-align: center;
    line-height: 35px;
  }
  li>a {
    color: #333;
    text-decoration: none;
  }
  li>a:hover {
    display: block;
    width: 100px;
    height: 35px;
    color: #973b1f;
    text-decoration: none;
    background-image: url("./悬停背景图");
  }
</style>

<div>
  <li><a href="">企业简介</a></li>
  <li><a href="">品牌历程</a></li>
  <li><a href="">资质荣誉</a></li>
  <li><a href="">服务指南</a></li>
</div>
```

### 属性解析（课堂表格）

| CSS 属性 | 功能 | 使用要点 |
| --- | --- | --- |
| `width` | 设置元素宽度 | 100px，`li` 和 hover 下的 `a` 都设为 100px，保证尺寸统一 |
| `height` | 设置元素高度 | 35px，和 `line-height` 配合让文字垂直居中 |
| `font-size` | 设置文字大小 | `li` 内文字 16px |
| `background-image` | 设置背景图片 | 引入图片作背景，hover 时换另一张图 |
| `margin-bottom` | 设置下外边距 | 6px，让每个 `li` 之间有间距 |
| `list-style-type` | 列表符号样式 | `none`，去掉 `li` 默认的小圆点 |
| `background-repeat` | 背景重复方式 | `no-repeat`，背景图只显示一次，不平铺 |
| `text-align` | 水平对齐 | `center`，文字水平居中 |
| `line-height` | 行高 | 与 `height` 相同（35px），单行文字垂直居中 |
| `color` | 文字颜色 | hover 时改变颜色，形成交互效果 |
| `text-decoration` | 文本装饰 | `none`，去掉 `a` 默认的下划线 |
| `display` | 元素显示类型 | hover 时设为 `block`，让 `a` 撑满父元素 `li`，整块区域可点击 |

### 本例的三个技巧

1. **`height` 与 `line-height` 相等**，单行文字垂直居中；再加 `text-align: center`，文字就在按钮正中间。
2. **背景图要配合 `background-repeat: no-repeat`**；盒子宽高与图片尺寸一致，图片正好铺满。
3. **子代选择器 `li>a`**：只选择 `li` 的直接子元素 `a`。`li a`（中间是空格）是后代选择器，会选中所有层级的 `a`。

### 背景图常用属性

| 属性 | 作用 | 常用值 |
| --- | --- | --- |
| `background-image` | 背景图片 | `url("路径")`，路径规则同 `<img>` 的 `src` |
| `background-repeat` | 是否平铺 | `repeat`（默认）、`no-repeat`、`repeat-x`、`repeat-y` |
| `background-position` | 图片位置 | `center`、`left top`、`10px 20px` |
| `background-size` | 图片大小 | `cover`、`contain`、`100% 100%` |

装饰性的图片放在 CSS 背景里；有内容、需要 `alt` 说明的图片用 `<img>`。

### 写新页面时可以改进的地方

- `<li>` 应放在 `<ul>` 中，不要直接放在 `<div>` 里（Class 4）。
- `display: block` 写在 `li>a` 的普通状态里更好：整个按钮都能点击，鼠标不必先碰到文字。
- `href=""` 点击后会刷新当前页面，占位时可以写 `href="#"`。
- 中文页面使用 `lang="zh-CN"`。
- 若同时写了 `margin-bottom` 和 `margin`，后写的 `margin` 会覆盖前面的 `margin-bottom`。

`margin` 的简写规则：

```
margin: 10px;                 四边都是 10
margin: 10px 20px;            上下 10，左右 20
margin: 10px 20px 30px;       上 10，左右 20，下 30
margin: 10px 20px 30px 40px;  上 右 下 左（顺时针）
```

---

## 八、普通流与浮动（预告）

**普通流（normal flow）**：网页中的元素默认按这样的方式排列——块级元素从上到下，行内元素从左到右。

**浮动（float）**本节只出了标题，下节课继续：浮动的元素会脱离普通流，靠向左侧或右侧，后面的文字环绕它排列。

## 本节小结

1. `letter-spacing` 调整字间距；`rgba()` 的 a 是不透明度，只影响这一个颜色。
2. `#` 写法就是十六进制的 `rgb()`，每两位表示一个颜色分量。
3. `text-decoration: none` 用来去掉链接下划线。
4. `display` 可以在块级、行内、行内块之间转换；`a` 转为 `block` 后可以设宽高。
5. 链接伪类按 LVHA 顺序书写。
6. `height` 等于 `line-height` 时，单行文字垂直居中。

## 重点掌握

能写出 LVHA 四个链接伪类并解释顺序的原因；能用 `display: block`、`line-height`、`background-image` 和 `:hover` 做出导航按钮。
