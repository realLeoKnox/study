# Class 6：图片与多媒体

## 一、图片 `<img>`（空元素）

```html
<img src="./photo.jpg" alt="课堂板书" width="300" height="200">
```

- `src` — 图片路径（相对路径规则同 Class 3）
- `alt` — 替代文字，图片加载失败或读屏时使用；纯装饰图写 `alt=""`
- `width` / `height` — 显示尺寸（像素），最好保持原图比例
- `title` — 鼠标悬停提示，✗ 不能代替 `alt`

✗ 文件名、扩展名、路径必须和实际文件一致。

## 二、图片格式

- JPG：有损，不支持透明 → 照片
- PNG：无损，支持透明 → 截图
- GIF：颜色少，支持动画 → 小动图
- WebP / AVIF：压缩率高 → 网页图片
- SVG：矢量图，放大不模糊 → 图标、Logo

## 三、音频与视频（成对标签）

```html
<audio src="a.mp3" controls>不支持音频</audio>
<video src="v.mp4" controls width="400" poster="cover.jpg">不支持视频</video>
```

- `controls` 显示控件　`autoplay` 自动播放　`muted` 静音　`loop` 循环
- `poster` — 视频播放前显示的封面（只有视频有）
- 这些是**布尔属性**：写上就生效，不用写 `="true"`。
- ✗ 浏览器可能拦截有声音的自动播放。
- 多种格式可以用多个 `<source>` 备选。

★ 显示图片用 `<img>`；**上传**图片要用表单的 `<input type="file">`（Class 7）。
