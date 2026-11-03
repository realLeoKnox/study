# Class 12：文本外观、显示模式与链接伪类

## 一、字间距

- `letter-spacing: 2px;` 字间距，默认 `normal`，可以写负值
- `word-spacing` 是词间距，只对空格有效，对中文基本无效

## 二、颜色写法

- `rgb(255,0,0)` = `#FF0000` = `#F00`（`#` 是十六进制，每两位一个颜色）
- `00`=0，`FF`=255；两位相同可简写成三位：`#333333` → `#333`
- `rgba(r,g,b,a)`：a 是不透明度，0 透明 → 1 不透明
- ✗ `rgba` 只让这个颜色透明；`opacity` 让整个元素连同内容一起透明

## 三、文本装饰 `text-decoration`

- `none` 无（去掉链接下划线）　`underline` 下划线
- `overline` 上划线　`line-through` 删除线

## 四、显示模式 `display` ★

- 块级 `block`：独占一行，可设宽高（div、p、h、li）
- 行内 `inline`：同一行排列，✗ 宽高设置无效（a、span、strong）
- 行内块 `inline-block`：同一行排列，又能设宽高（img、input）
- `a { display: block; }` 行转块 → 可以设宽高，整块都能点

## 五、链接伪类 ★

- `:link` 未访问　`:visited` 已访问
- `:hover` 鼠标经过　`:active` 按下时
- ★ 顺序必须是 **LVHA**（LoVe HAte）：优先级相同时后写的覆盖前面的

```css
a:link { color: #333; }
a:hover { color: red; }
```

## 六、导航菜单案例

```css
li {
  width: 100px; height: 35px;
  line-height: 35px;               /* 等于 height → 垂直居中 */
  text-align: center;
  list-style-type: none;           /* 去掉圆点 */
  background-image: url("./0001.gif");
  background-repeat: no-repeat;    /* 不平铺 */
  margin-bottom: 6px;
}
li>a { color: #333; text-decoration: none; }
li>a:hover {
  display: block;                  /* 撑满 li，整块可点 */
  width: 100px; height: 35px;
  color: #973b1f;
  background-image: url("./悬停图");
}
```

属性要点：

- width / height：100px × 35px，li 和 hover 的 a 尺寸统一
- font-size：16px
- background-image：背景图，hover 时换另一张
- background-repeat：no-repeat，只显示一次
- margin-bottom：6px，li 之间的间距
- list-style-type：none，去掉圆点
- text-align：center，水平居中
- line-height：= height，垂直居中
- color：hover 时变色
- text-decoration：none，去掉下划线
- display：hover 时设为 block，撑满 li

★ `li>a` 是子代选择器，只选直接子元素；`li a`（空格）是后代选择器，选所有层级。
✗ 先写 `margin-bottom` 再写 `margin`，后写的会覆盖前面的。

## 七、普通流

块级元素从上到下排，行内元素从左到右排 → 叫普通流。（浮动 float 下节课讲）
