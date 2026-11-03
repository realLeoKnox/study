# Class 10：文章页面的 CSS 排版

Class 9 学会“用选择器找到元素并设置文字样式”。本节把标题、来源、图片和正文组合成一篇文章，重点解决**内容宽度、居中、留白与图片尺寸**。

## 一、先看页面结构，再写样式

课堂示例的文章顺序是：位置提示 → 标题 → 发布时间与来源 → 图片及说明 → 正文。可以先用 HTML 表达结构，再用 CSS 调整外观。

```html
<article class="article">
    <h1>文章标题</h1>
    <p class="meta">发布时间与来源</p>
    <figure>
        <img src="news.jpg" alt="比赛现场照片">
        <figcaption>图片说明与来源</figcaption>
    </figure>
    <p class="body-text">正文第一段。</p>
</article>
```

`<article>` 表示可独立阅读的文章；`<figure>` 与 `<figcaption>` 把图片和说明关联起来。课堂代码用 `<div>` 也能排出外观，但写新页面时，有明确内容含义可选更合适的标签。

---

## 二、文章容器居中

课堂示例的 `.banxin { width: 600px; margin: 0 auto; }` 表示固定宽度 600px，左右外边距自动分配，因此容器水平居中。窄屏下固定 600px 可能产生横向滚动，可改为：

```css
.article {
    max-width: 600px;
    margin: 0 auto;
    padding: 0 16px;
    box-sizing: border-box;
}
```

| 写法 | 作用 |
| --- | --- |
| `max-width: 600px` | 容器最多宽 600px，窄屏可以缩小 |
| `margin: 0 auto` | 上下外边距为 0，左右自动分配以水平居中 |
| `padding: 0 16px` | 内容与左右边缘留出内边距 |
| `box-sizing: border-box` | 让宽度计算包含内边距和边框 |

**区分：**`text-align: center` 让容器内的文字等行内内容居中；`margin: 0 auto` 让有明确宽度或最大宽度的块级容器水平居中。

---

## 三、图片与图注

课堂示例把图片固定为 400px，并给外层图片区设置 `margin: 30px auto`。为避免图片在窄屏超出容器，可用：

```css
figure { max-width: 400px; margin: 30px auto; }
figure img { display: block; max-width: 100%; height: auto; }
figcaption { font-size: 12px; color: #666; }
```

`max-width: 100%` 限制图片不超过父容器；`height: auto` 保持原始宽高比。`margin: 30px auto` 表示上下各 30px、左右自动分配。

图片如果承载新闻信息，应写有意义的 `alt`，不能因为旁边已有图注就默认写空值；`alt` 描述图片，图注可以补充背景、时间或来源。

---

## 四、正文与辅助文字

```css
.article { font-family: sans-serif; line-height: 1.6; }
.meta, figcaption { color: #666; font-size: 12px; }
.body-text { text-indent: 2em; }
```

| 部分 | 常用样式 | 目的 |
| --- | --- | --- |
| 标题 | 较大的 `font-size`、适当 `margin` | 建立视觉层级 |
| 发布时间、来源 | 较小字号、较浅颜色 | 与正文区分 |
| 正文 | 合适的 `line-height` | 提高多行文字的可读性 |
| 首行缩进 | `text-indent: 2em` | 只缩进每段第一行 |

`line-height: 1.6` 是与字体大小相乘的无单位比例；相比写死 `25px`，字体大小改变时更容易保持合适行距。`font-weight: bold` 只改变外观；如果文字本身表示重要性，仍要考虑 Class 3 的 `<strong>`。

### 图注两侧排列（了解）

课堂示例用 `float: right` 把来源放到右侧。浮动会影响后续文字环绕；这类“左边标题、右边来源”的并排布局也可以用 Flexbox：

```css
.caption-row { display: flex; justify-content: space-between; gap: 12px; }
```

---

## 五、检查课堂 HTML 的结构

课堂代码里第一段正文的 `<p class="content">` 在第二个 `<p>` 开始前没有显式写 `</p>`。浏览器会尝试自动修复，但写笔记和新代码时应把每段明确闭合：

```html
<p class="body-text">正文第一段。</p>
<p class="body-text">正文第二段。</p>
```

页面内容是中文时，`<html lang="zh-CN">` 比 `lang="en"` 更合适。若图片是有信息的新闻照片，`alt` 应说明图片内容。课堂素材保持原样，以上是写新页面时要记住的检查点。

## 本节小结

1. 先用 HTML 组织文章，再用 CSS 处理宽度、居中、间距、字体和图片。
2. `margin: 0 auto` 用于容器水平居中；`text-align` 管容器内的行内内容。
3. `max-width` 与 `height: auto` 有助于窄屏显示和保持图片比例。
4. 正文的 `<p>` 要正确闭合，图片的 `alt` 要与图片用途相符。

## 与下一课相连

下一课继续用文章页面练习：把标题、元信息、头图、声明框和正文组合起来，检查 CSS 的复用与整体一致性。

## 重点掌握

能说明“元素本身居中”与“元素里的文字居中”的区别，并能写出不超出窄屏的文章容器和图片样式。
