# Class 13：浮动 float 与导航栏案例

## 一、普通流 → 浮动

- 块级：独占一行，从上到下　行内：同一行，从左到右
- 想让块级元素**横着排** → 用 `float`

## 二、float ★

- `left` 靠左　`right` 靠右　`none` 不浮动（默认）
- 作用：图文环绕、多栏布局
- ★ **脱离文档流**：不占原位置，但**文字会环绕**它
- ★ **高度塌陷**：父元素没设高度，子元素全浮动 → 父元素高度为 0
- 一行放不下 → 浮动元素**掉到下一行**

## 三、案例一：三个红块

```css
div { float: left; width: 300px; height: 160px;
      margin: 10px; text-align: center;
      line-height: 160px; }        /* = height → 垂直居中 */
.r1 { float: right; }              /* 第 3 个靠右 */
```

- 前两个 div 并排靠左；`.r1` 靠右
- ★ 类选择器 > 标签选择器，所以 `.r1` 的 `float: right` 赢
- 每个红块占 `300 + 10 + 10 = 320px`；三个要 960px，窗口太窄第 3 个就掉下去
- `span` 没设 float → 仍是行内，文字会钻进红块中间的空地（环绕）

## 四、案例二：导航栏 header

```html
<div class="header">
  <div class="center">
    <li class="logo"></li>
    <li class="a1"></li> ... <li class="a8"></li>
  </div>
</div>
```

```css
.header { height: 79px; background-color: #000;
          border-bottom: 6px solid #644226; }
.center { width: 980px; height: 79px; margin: 0 auto; }
li      { float: left; list-style: none; }
.logo   { background-image: url(./images/logo.jpg);
          width: 190px; height: 79px; }
.a1     { background-image: url(./images/a1.jpg);
          width: 73px; height: 79px; }
.a1:hover { background-image: url(./images/b1.jpg); }
```

- `.header`：通栏黑底，高 79px，底部 6px 棕色边
- `.center`：版心，固定宽 980px + `margin: 0 auto` 居中
- `li`：`float: left` 横排，`list-style: none` 去圆点
- `.logo`、`.a1~.a8`：每项一张背景图，宽高 = 图片大小
- `:hover`：换成 `b1~b8.jpg` → 高亮
- ★ `margin: 0 auto` 居中的前提：**必须有固定宽度**
- 宽度合计：`190 + 73 + 7×100 = 963px ≤ 980px`，一行放得下；版心小于 963px，最后一个掉下去
- ★ 两层都写 `height: 79px` = 防止高度塌陷（删掉后黑条消失，只剩 6px 边）
- `.header` 总高 = 79 + 6 = 85px

## 五、易错点

- ✗ 截图写 `.center` 宽 970px，代码是 980px → 以代码为准
- ✗ `li` 应该放在 `ul` 里，课堂代码直接放在 `div` 里不规范
- `:hover` 里不用重复写宽高，只换 `background-image` 就行
- 怎么清除浮动：老师还没讲
