# Class 9：CSS 入门、选择器与文字样式

前面用 HTML 写出了标题、图片、表格和表单。**HTML 说明内容是什么，CSS 决定内容如何显示。**本节先掌握最常用的 CSS 规则与选择器。

## 一、一条 CSS 规则由什么组成

```css
p {
    color: #333;
    font-size: 16px;
}
```

| 部分 | 示例 | 作用 |
| --- | --- | --- |
| 选择器 | `p` | 找到要设置样式的元素 |
| 属性 | `color` | 指定要改变什么 |
| 属性值 | `#333` | 指定改变成什么样 |
| 声明 | `color: #333;` | 一项“属性：值”的设置 |

CSS 声明写在花括号中，属性与值用冒号分开，声明末尾通常写分号。规则可以放在 HTML 的 `<style>` 中；页面较多时，也可用 `<link rel="stylesheet" href="style.css">` 引入独立样式表。

---

## 二、三种基础选择器

| 选择器 | CSS 写法 | 匹配的 HTML | 常见用途 |
| --- | --- | --- | --- |
| 标签选择器 | `p { ... }` | 所有 `<p>` | 同类元素的基础样式 |
| 类选择器 | `.price { ... }` | `class="price"` | 可重复使用的样式 |
| ID 选择器 | `#notice { ... }` | `id="notice"` | 页面内唯一元素 |

```html
<p class="price">¥32.9</p>
<p class="price" id="special-price">¥29.9</p>
```

```css
p { font-size: 16px; }
.price { color: #c00; }
#special-price { font-weight: bold; }
```

**记法：**HTML 属性写 `class="price"`、`id="special-price"`；CSS 选择器才在类名前加 `.`、在 ID 前加 `#`。同一个类可用于多个元素；同一个 `id` 在页面内应保持唯一。一个元素可以写多个类名，用空格隔开。

### 样式冲突时

同一元素的同一属性被多条规则设置时，浏览器会按层叠规则决定结果。在普通且同等条件的规则中，ID 选择器的优先级高于类选择器，类选择器高于标签选择器；同等优先级时，后写的规则通常覆盖先写的。实际结果还可能受样式来源、`!important` 等影响。

---

## 三、常用文字样式

| 属性 | 作用 | 例子 |
| --- | --- | --- |
| `color` | 文字颜色 | `color: #333;` |
| `font-size` | 文字大小 | `font-size: 16px;` |
| `font-family` | 字体与后备字体 | `font-family: "Microsoft YaHei", sans-serif;` |
| `font-weight` | 字重 | `font-weight: bold;` |
| `line-height` | 行高 | `line-height: 1.5;` |
| `text-indent` | 首行缩进 | `text-indent: 2em;` |
| `text-align` | 行内内容的水平对齐 | `text-align: center;` |

```css
.article-text {
    font-size: 16px;
    line-height: 1.6;
    text-indent: 2em;
    color: #333;
}
```

- `px` 是 CSS 像素；`rem` 相对于根元素的字体大小；`em` 在 `text-indent` 等属性中通常相对于该元素的字体大小。
- `line-height: 1.6` 是无单位的比例，行高随文字大小变化；不是固定 1.6 像素。
- `text-align: center` 控制容器中行内内容的对齐，不负责让整个块级容器居中。容器居中见 Class 10。
- 字体不一定安装在每台设备上，所以 `font-family` 要写后备字体。

---

## 四、结合课堂示例理解

课堂页面用 `.shou` 给多张商品图片设相同尺寸，用 `#color_red` 给一个价格标红。若多个价格都要红色，改用可复用的类更方便：

```html
<td class="price">¥32.9</td>
<td class="price">¥32.9</td>
```

```css
.price { color: #c00; }
```

商品图片传达商品信息时，`alt` 应简要说明内容，例如 `alt="基围虾菜品照片"`；只有纯装饰图片才适合空 `alt`。这与 Class 6 的图片规则一致。

---

## 五、编辑器速写（了解）

课堂提到的 `ul>li*3`、`.demo`、`#two` 等是 **Emmet 缩写**，用于在支持它的编辑器里快速生成代码，不是浏览器认识的 HTML 或 CSS 语法。是否按 Tab 展开，取决于编辑器设置。

| 缩写 | 常见生成结果 |
| --- | --- |
| `ul>li*3` | 一个 `<ul>` 中的三个 `<li>` |
| `div+p` | 相邻的 `<div>` 与 `<p>` |
| `.demo` | 带 `class="demo"` 的元素 |
| `#two` | 带 `id="two"` 的元素 |

## 本节小结

1. CSS 规则由选择器与声明组成。
2. 标签、类、ID 选择器分别适合基础样式、复用样式、唯一元素。
3. `font-size`、`line-height`、`text-indent`、`text-align` 控制不同方面的文字排版。
4. CSS 可以改变外观，但 HTML 标签的内容含义仍应选对。

## 与下一课相连

有了选择器和文字样式，下一课把标题、图片、来源和正文组合成一页文章，继续学习容器宽度、居中、留白与图片尺寸。

## 重点掌握

能把 HTML 的 `class`、`id` 与 CSS 的 `.类名`、`#ID名` 一一对应，并解释文字居中与容器居中的区别。
