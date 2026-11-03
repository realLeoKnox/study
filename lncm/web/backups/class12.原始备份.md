CSS
回顾
CSS外观属性
color
水平对齐
首航缩进
字间距 letter-spacing,默认normal
颜色半透明
文本和字体


字间距 letter-spacing,默认normal
```css
h1 { letter-spacing: 2px; }    /* 每个字之间加宽 2px */
p  { letter-spacing: 0.1em; }  /* 按字号的比例加宽，字号变了间距也跟着变 */
.tight { letter-spacing: -1px; }  /* 可以写负值，让字更紧 */
```

颜色半透明
color: rgba(r,g,b,a) a是alpha 透明的意思
`rgba()` 前三个值是**红、绿、蓝**，取值 0–255；第四个值 **a（alpha）是不透明度**，取值 0–1：0 是完全透明，1 是完全不透明。

```css
p   { color: rgba(0, 0, 0, 0.5); }               /* 50% 透明的黑字 */
div { background-color: rgba(255, 0, 0, 0.3); }  /* 淡红色半透明背景 */
```

- 记成“a 越小越透明”。
- ✗ **`rgba` 和 `opacity` 的区别（常考）：**
  - `rgba` 只让**这一个颜色**变透明，比如只有背景透明，里面的文字不受影响。
  - `opacity: 0.5` 让**整个元素连同里面的内容**一起变透明。
- 新的 CSS 里 `rgb(0 0 0 / 50%)` 也能表示透明，但考试按 `rgba` 写就行。

## 浮动初步
### 选择器补充
链接伪类选择器
- :link   未访问的链接
- :visited  已访问链接
- :hover   鼠标移动到连接上
- :active  鼠标在链接上按下时

  顺序不要颠倒，按照lvha的顺序

文本装饰
text-decoration 
none
underline
overline
line-through

标签显示模式 display
块转行
行转块
