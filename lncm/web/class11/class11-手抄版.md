# Class 11：文章页面综合练习

## 一、页面拆解

标题 → 时间与来源 → 头图 → 声明框 → 正文 → 结尾强调

- 先问“这一块是什么内容”（HTML），再问“它要什么样式”（CSS）。

## 二、HTML 结构

```html
<article class="article">
    <header>
        <h1>标题</h1>
        <p class="meta">时间 · 来源</p>
    </header>
    <img class="cover" src="cover.gif" alt="头图内容">
    <p class="notice">转载声明</p>
    <p class="ending">结尾</p>
</article>
```

- `<header>` 放标题和元信息；`class` 只用来标记样式，不改变标签的含义

## 三、视觉层级

```css
.meta   { color: #666; font-size: 14px; }
.cover  { max-width: 100%; height: auto; }
.ending { font-weight: 700; }
```

- `font-weight: 700` 相当于 `bold`；内容本身重要时用 `<strong>`

## 四、盒模型 ★

由内到外：**内容 → padding 内边距 → border 边框 → margin 外边距**

```css
.notice { background-color: #eee; padding: 16px; margin: 20px 0; }
```

- `padding`：文字到背景边缘的距离；`margin`：框到周围元素的距离
- 默认情况下 `height` 只算内容区：`height: 100px` 加 `padding: 18px` 后，实际高度是 136px
- ✗ 一般不写固定 `height`，否则内容多了会溢出

## 五、检查清单

1. 每个 `<p>` 都闭合了
2. 中文页面用 `lang="zh-CN"`
3. 图片路径正确，`alt` 有内容
4. 窗口变窄时容器和图片能缩小

★ 写页面的顺序：确定内容 → 写 HTML 结构 → 写 CSS 样式 → 检查。
