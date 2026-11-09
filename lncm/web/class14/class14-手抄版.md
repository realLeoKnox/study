# Class 14：盒子模型与页脚案例

## 一、盒子模型

- 从里到外：content → padding → border → margin
- ★ 标准盒模型：实际宽 = `width` + 左右 padding + 左右 border（高同理）
- 例：`width:220px; padding-left:15px; border-left/right:4px` → 实际宽 220+15+4+4 = **243px**

## 二、边框 border

- 综合写法：`border: 1px solid #f00;`（宽度 样式 颜色）
- ★ 样式必须写，默认 `none`，不写边框不显示
- 样式：`none` 无　`solid` 实线　`dashed` 虚线　`dotted` 点线　`double` 双线（≥3px 才看得出）
- 单边：`border-top` / `-bottom` / `-left` / `-right`

## 三、圆角 border-radius（CSS3）

- 顺序：**左上 右上 右下 左下**（顺时针）
- `/` 前水平半径，`/` 后垂直半径：`border-radius: 100px/50px;`
- ★ 椭圆：`border-radius: 50%;`（水平按宽的 50%，垂直按高的 50%；正方形 → 圆）
- `100px/50px` 用在 220×100 的盒子上，水平半径比宽的一半（110）小，上下会剩 20px 直线，不是标准椭圆

## 四、padding 与 margin

- padding：边框到内容的距离，背景色会延伸，**不能**取负值
- margin：盒子之间的距离，背景不延伸，**可以**取负值
- 简写：1 个值四边；2 个值 上下/左右；3 个值 上/左右/下；4 个值 上 右 下 左
- ★ 相邻块的上下 margin **合并**，取大的，不相加
- ★ 父子 margin 合并：`.d1{margin-top:30px}` 在 `body`（默认 8px）里，实际离顶部 30px，不是 38px
- 父元素有 border / padding / overflow 就不合并
- `margin: 0 auto` 只对**有固定宽度**的块级元素水平居中
- 行内元素上下 margin 不生效；百分比按**父元素宽度**算
- `border-collapse`：只对 `<table>` 有效

## 五、案例一：5 个 div

```css
div { width:220px; height:100px; background-color:#777;
      margin-bottom:10px; font-size:36px; }
.d1 { border-top:1px solid #f00; padding:15px; margin-top:30px; }
.d2 { border-left:4px dashed #f00; border-right:4px dashed #f00;
      padding-left:15px; margin-left:30px; }
.d3 { border:4px dotted #ff0000; }
.d4 { border:4px double #f00; border-radius:10px; }
.d5 { border-radius:100px/50px; }
```

- `.d1`：250 × 131（220+15+15，100+15+15+1）
- `.d2`：243 × 100；`margin-left:30px` 把整个盒子往右推
- `.d3`、`.d4`：228 × 108（边框增加尺寸）

## 六、案例二：页脚 footer

```
.footer（字号 14px，颜色 #9F582C）
├ .footer_top：黑条，高 35px，line-height 35px
│  └ .footer_top_cen：980px，margin:0 auto
│     ├ _left：float:left，8 个 li（首页|关于圣华|…）
│     └ _right：float:right，letter-spacing:15px（口号）
└ .footer_btm：深棕 #4A311B，高 170px
   └ .footer_btm_cen：980px，margin:0 auto
      └ _left：padding-top:10px，里面 3 个 div 横排
```

```css
.footer_top a { color:#9F582C; text-decoration:none; }
.footer_btm_cen_left div { float:left; }          /* 后代选择器 */
.footer_btm_cen_left_2 { padding-right:150px; padding-top:25px; }
```

- `line-height` = 高度 → 文字垂直居中
- `a` 的颜色不继承，要单独设置；`text-decoration:none` 去下划线
- `.footer_btm_cen_left div` 选中里面**所有** div → 全部浮动，横排
- 用 padding 把图片推到位置：`73 + 150 = 223px` 宽
- ★ 高度塌陷：`.footer_top_cen` 高 0（全是浮动）→ 外层 `.footer_top` / `.footer_btm` 必须写死高度
- 图片是行内元素，下面有 4px 缝隙；`img{display:block}` 可消除
- `../` = 上一级目录

## 七、易错点

- ✗ `border` 不写样式 → 看不见
- ✗ 盒子比 `width` 大：要加上 padding 和 border
- ✗ margin 是合并，不是相加
- ✗ 标准椭圆用 `50%`
- ✗ `li` 应放在 `ul` 里，课堂代码不规范
- 怎么清除浮动：老师还没讲
