# Class 5：HTML 表格

上一课用 `<ul>`、`<ol>` 把同类项目列成清单；当信息同时具有**行与列的对应关系**时，就用表格呈现。例如“姓名、课程、成绩”需要按列对齐。

## 一、表格的基本结构

| 标签 | 含义 | 放置位置 |
| --- | --- | --- |
| `<table>` | 整张表格 | 最外层 |
| `<tr>` | 一行（table row） | 表格或行分组内 |
| `<th>` | 表头单元格（table header） | `<tr>` 内 |
| `<td>` | 数据单元格（table data） | `<tr>` 内 |
| `<caption>` | 表格标题 | `<table>` 的第一个子元素 |

```html
<table>
    <caption>学生成绩</caption>
    <tr>
        <th scope="col">姓名</th>
        <th scope="col">成绩</th>
    </tr>
    <tr>
        <td>小明</td>
        <td>90</td>
    </tr>
</table>
```

阅读顺序是：**表格 → 行 → 单元格**。`<th>` 说明这一列或这一行的含义；`scope="col"` 表示列标题，`scope="row"` 表示行标题。

### 注意

- `<caption>` 描述整张表，不是某一个单元格的标题。
- 表格适合展示数据关系；页面整体布局通常交给 CSS。
- 表头不只是“文字加粗”。有表头语义，读屏工具才更容易说明数据属于哪一列。

---

## 二、给表格加边框

HTML 决定表格的结构，CSS 决定边框、尺寸和对齐方式。

```css
table {
    border-collapse: collapse;
}
th, td {
    border: 1px solid #666;
    padding: 8px;
    text-align: center;
}
```

`border: 1px solid #666` 依次表示**宽度、线型、颜色**。原稿中的 `border: 1px` 没写线型，通常看不到预期的边框。`border-collapse: collapse` 让相邻单元格共用边框，避免间隔造成的双线效果。

---

## 三、合并单元格

| 属性 | 含义 | 写法 |
| --- | --- | --- |
| `colspan` | 横向跨越几列 | `<td colspan="2">…</td>` |
| `rowspan` | 纵向跨越几行 | `<td rowspan="2">…</td>` |

```html
<table>
    <tr><th>姓名</th><th>语文</th><th>数学</th></tr>
    <tr><td>小明</td><td>90</td><td>95</td></tr>
    <tr><th scope="row" colspan="2">两科总分</th><td>185</td></tr>
</table>
```

最后一行的第一个单元格已占两列，所以该行只需再写一个单元格。使用 `rowspan` 时，被跨到的下一行也要少写相应数量的单元格。

### 判断方法

画出表格网格，逐行数列数；被合并单元格覆盖的位置不能再额外放单元格。

---

## 四、按用途分组（了解）

较长的表格可以用 `<thead>`、`<tbody>`、`<tfoot>` 分别组织表头、数据和汇总行。

```html
<table>
    <caption>销售记录</caption>
    <thead><tr><th scope="col">商品</th><th scope="col">数量</th></tr></thead>
    <tbody><tr><td>笔记本</td><td>3</td></tr></tbody>
    <tfoot><tr><th scope="row">合计</th><td>3</td></tr></tfoot>
</table>
```

这些标签帮助表达结构；不要把 `<thead>` 与单个表头单元格 `<th>` 混为一谈。

---

## 五、本节小结

1. 列表适合一组项目；表格适合有行列关系的数据。
2. `<table>` 包含行 `<tr>`，每行包含 `<th>` 或 `<td>`。
3. `<caption>` 是整张表的标题；`scope` 可说明表头关联的行或列。
4. `colspan` 跨列，`rowspan` 跨行；合并后要调整其余单元格数量。
5. 表格结构用 HTML，边框与排版用 CSS。

## 与下一课相连

单元格里也可以放图片等内容。下一课先学习如何正确写 `<img>`，再认识音频和视频标签。

## 重点掌握

能从行列数据写出表格，并检查每行的单元格是否与合并关系一致。
