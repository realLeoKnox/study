# Class 10：文章页面的 CSS 排版

## 一、文章结构

```html
<article>
    <h1>标题</h1>
    <p class="meta">时间 · 来源</p>
    <figure>
        <img src="news.jpg" alt="比赛现场">
        <figcaption>图片说明</figcaption>
    </figure>
    <p>正文</p>
</article>
```

- `<article>` 表示可以独立阅读的文章；`<figure>` + `<figcaption>` 是图片加说明

## 二、容器居中

```css
.article { max-width: 600px; margin: 0 auto; padding: 0 16px; }
```

- `margin: 0 auto` — 上下 0，左右自动分配 → **容器**水平居中（需要设宽度）
- `max-width` 比 `width` 好：窗口变窄时容器能跟着缩小
- `padding` — 内容和边缘之间留白

★ `text-align: center` 让容器里的**文字**居中；`margin: 0 auto` 让**容器本身**居中。

## 三、图片

```css
figure img { max-width: 100%; height: auto; }
```

- `max-width: 100%` 图片不会超出父容器；`height: auto` 保持宽高比例
- `margin: 30px auto` — 上下 30px，左右居中
- 新闻图片要写有意义的 `alt`

## 四、正文与辅助文字

- 标题：较大的 `font-size`
- 时间、来源：较小的字号和较浅的颜色（如 `#666`）
- 正文：`line-height: 1.6`，`text-indent: 2em`
- `float: right` 可以让来源靠右（会影响后面文字的环绕）

✗ 每个 `<p>` 都要写 `</p>` 闭合；中文页面用 `<html lang="zh-CN">`。
