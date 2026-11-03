# Class 9：CSS 入门、选择器与文字样式

> HTML 决定内容**是什么**，CSS 决定内容**怎么显示**。

## 一、CSS 规则的组成

```css
p { color: #333; font-size: 16px; }
```

- `p` 选择器　`color` 属性　`#333` 属性值　`color: #333;` 一条声明
- CSS 写在 `<style>` 里，或者外部引入：`<link rel="stylesheet" href="style.css">`

## 二、三种选择器

- 标签选择器 `p {}` → 选中所有 `<p>`
- 类选择器 `.price {}` → 选中 `class="price"`（可重复使用，一个元素可以有多个类）
- ID 选择器 `#top {}` → 选中 `id="top"`（页面内唯一）

★ HTML 里写 `class="price"`，CSS 里写 `.price`；`.` 和 `#` 只出现在 CSS 里。

- 样式冲突时的优先级：**ID > 类 > 标签**；优先级相同时，后写的覆盖先写的。

## 三、文字样式

- `color` 颜色　`font-size` 字号　`font-weight: bold` 加粗
- `font-family: "Microsoft YaHei", sans-serif;` 字体，后面写备用字体
- `line-height: 1.5` 行高（不带单位，表示字号的倍数）
- `text-indent: 2em` 首行缩进 2 个字
- `text-align: center` 让**文字**居中（容器本身居中见 Class 10）

## 四、Emmet 缩写（编辑器功能）

- `ul>li*3` → 一个 ul 里有 3 个 li　`.demo` → `class="demo"`　`#two` → `id="two"`
- 这是编辑器的快捷写法，不是 HTML/CSS 语法。
