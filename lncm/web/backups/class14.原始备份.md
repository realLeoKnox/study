盒子模型
盒子边框
border : border-width || border-style || border color ||1px solid #f00

边框属性
none
solid
dashed
dotted
double

盒子边框写法
上边框
下边框
左边框
又边框
边框综合设置

---

圆角边框 CSS3
radius 半径（距离）
border-radius:左上角 右上角 右下角 左下角；

做椭圆，圆角半径？
椭圆的圆角半径

答案：border-radius: 50%; 就是椭圆（正方形则变成圆）。

原理是百分比的分母不一样：水平方向的半径按宽度的 50% 算，垂直方向的半径按高度的 50% 算。所以一个 200×100 的 div，水平半径 = 100px，垂直半径 = 50px，正好是椭圆。




| 属性 | 核心作用 | 使用要点 |
| --- | --- | --- |
| `border` | 设置元素边框 | 必须设置样式（如 `solid`），否则宽度和颜色无效；边框会增加元素尺寸，注意 `box-sizing` |
| `border-radius` | 设置元素圆角 | 四值顺序为左上、右上、右下、左下；用 `/` 可分别设置水平与垂直半径；`50%` 可做圆形或椭圆 |
| `border-collapse` | 控制表格单元格边框合并 | 仅适用于 `<table>`；`collapse` 合并边框会使 `border-spacing` 失效；默认 `separate` |


---

内边距 padding


外边距 margin




| 属性 | 核心作用 | 使用要点与易错点 |
| --- | --- | --- |
| `margin` | 设置元素外边距，控制盒子与盒子之间的距离 | 可取负值，能实现元素位移或重叠；相邻块级元素的垂直 margin 会发生合并（取较大值），父元素的 margin 也有可能和子元素发生塌陷；`margin: 0 auto;` 只能让**有固定宽度**的块级元素水平居中；行内元素（`inline`）的上下 margin 在布局中不生效 |
| `padding` | 设置元素内边距，控制边框与内容之间的距离 | 不能取负值；会增大盒子尺寸（标准盒模型），设置 `box-sizing: border-box` 后不再撑大总宽；背景色会延伸到 padding 区域（margin 不会）；行内元素的上下 padding 会绘制出来，但不影响行高和周边布局 |
| 简写规律 | `margin`、`padding` 都可用 1~4 个值 | 一个值：四边相同；两个值：上下 / 左右；三个值：上 / 左右 / 下；四个值：上 / 右 / 下 / 左（顺时针，从上方开始） |
| 百分比取值 | `margin`、`padding` 都可写百分比 | **水平和垂直方向都按包含块的宽度计算**，不是按高度，容易误判；垂直 margin 用百分比时尤其要注意 |
| 居中对比 | — | `padding` 无法实现元素居中；水平居中用 `margin: 0 auto`，内容居中用 `text-align: center` 或 Flex 布局 |
| 常见易错 | — | `margin` 合并只发生在普通流的垂直方向，浮动、绝对定位、Flex/Grid 子项中不会合并；父元素没有 `border`、`padding` 或 `overflow` 时，子元素的上 margin 可能“穿透”到父元素外面 |

---


footer部分
