# Class 16：JavaScript 入门（手抄版）

## 一、三种技术

| HTML | CSS | JavaScript |
| --- | --- | --- |
| 内容、结构 | 外观、布局 | 数据、交互 |

JavaScript（JS）与 Java 是不同语言。本课学习浏览器中的 JS：它可以找到页面元素、监听事件、修改页面。

## 二、引入脚本 ★

```html
<script>console.log("内嵌脚本");</script>
<script src="./main.js" defer></script>
```

- 内嵌：代码写在 `<script>` 中；`type="text/javascript"` 通常可省略。
- 外部：代码写在 `.js` 文件中，用 `src` 指定路径；路径相对当前 HTML 文件。
- ★ `defer`：外部普通脚本在 HTML 解析完成后执行，适合脚本要查找页面元素的情况。
- 课堂 `onclick="函数名()"` 可以运行；新代码可用事件监听，把行为写在 JS 中。

## 三、按钮点击全过程 ★

```html
<button id="hello" type="button">点我</button>
<script src="./main.js" defer></script>
```

```js
const button = document.querySelector("#hello");
button.addEventListener("click", () => {
    alert("欢迎");
});
```

**找到元素 → 监听 `click` → 点击时执行函数。**`#hello` 对应 HTML 的 `id="hello"`。选择器写错会得到 `null`。

## 四、调试与易错点

- `alert()` 弹窗，会打断操作；`console.log()` 在浏览器控制台输出，适合调试。
- JS 区分大小写；文件名、函数名要一致。
- 脚本查找元素前，该元素必须已被解析；`defer` 可帮助处理外部脚本的时机。
- 带 `src` 的 `<script>` 不应同时写内联代码；内联代码另写一个 `<script>`。
- 同期 `task2/hebing.html` 的悬停换图靠 CSS `:hover`，不是 JavaScript；`<li>` 应放在 `<ul>` 或 `<ol>` 中。

**下一课：**代码已能运行；Class 17 继续学习变量与数据类型，让代码能够处理值。
