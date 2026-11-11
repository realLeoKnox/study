# Class 15：CSS 背景与页面合并

## 一、回顾

- 盒子边框、圆角边框、表格的细线边框
- `padding` 内边距、`margin` 外边距
- 外边距实现盒子居中：`margin: 0 auto`

## 二、CSS 背景 ★

| 属性 | 作用 |
| --- | --- |
| `background-color` | 背景颜色 |
| `background-image` | `url("路径")` |
| `background-repeat` | `repeat`（默认）/ `no-repeat` / `repeat-x` / `repeat-y` |
| `background-position` | 位置：水平 垂直 |

```css
div{
  width: 900px; height: 600px;
  border: 1px solid #f000;
  background-color: #f28;
  background-image: url("./2026-11-05 18.48.28.jpg");
  background-repeat: no-repeat;
  background-position: 50% 50%;     /* center center */
}
```

- `#f28` = `#ff2288`（3 位简写，每位重复）
- ★ `#f000`：4 位十六进制，最后一位是透明度，`0` = **全透明** → 边框看不见，但**仍占位**（盒子变成 902×602）
- 文件名有空格 → `url()` 里加引号
- `no-repeat`：不平铺，没盖住的地方露出背景色
- ★ 位置：第一个值**水平**，第二个值**垂直**
- `left`=0%，`center`=50%，`right`=100%；`top`=0%，`bottom`=100%
- ★ 百分比公式：偏移 = （盒子 − 图片）× 百分比
  例：盒子 900×600，图片 640×640，`50% 50%` → x = 130，y = −20（上下各裁 20px）

| 写法 | 图片左上角 |
| --- | --- |
| `50% 50%` | (130, −20) |
| `0 0` | (0, 0) |
| `100% 100%` | (260, −40) |
| `center top` | (130, 0) |
| `20px 30px` | (20, 30) |

- `repeat-x` 只横向铺，`repeat-y` 只纵向铺
- ★ 图片比盒子高时，`repeat-y` 看不出效果（纵向放不下第二张）

## 三、合并页：header + banner + footer

```html
<div class="header">…</div>
<div class="content">
  <div class="con_cen"><img src="../../sucai/images/kv2.jpg" alt=""></div>
</div>
<div class="footer">…</div>
```

```css
.con_cen { width: 980px; margin: 0 auto; }
.con_cen img { width: 980px; }
```

- 两份样式拼在同一个 `<style>`，类名不冲突；`li{float:left}` 重复无害
- `body{margin:0}`（来自 footer）→ header 也贴边
- ★ 只设宽度，高度自动按比例：1440×500 → 980×340.3（500×980÷1440）
- `<img>` 是行内元素，底部多 4px 缝隙：`.content` 高 344.3 = 340.3 + 4；`display:block` 可消除
- `../../sucai/images/`：`../` 往上一级目录

## 四、易错点

- ✗ `#f000` 是透明色，不是红色
- ✗ 透明边框也占位
- ✗ 百分比位置：（盒子 − 图片）× 百分比
- ✗ 图片比盒子大时，偏移量是负数，会被裁掉
- ✗ `repeat-y` 要图片比盒子矮才看得出来
- 二维码白边问题见 class14 笔记第八节
