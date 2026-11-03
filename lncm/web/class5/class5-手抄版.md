# Class 5：HTML 表格

## 一、基本结构

- `<table>` 表格 → `<tr>` 行 → `<th>` 表头单元格 / `<td>` 数据单元格
- `<caption>` — 整张表的标题，放在 `<table>` 里的第一个位置
- `scope="col"` 列标题，`scope="row"` 行标题

```html
<table>
    <caption>学生成绩</caption>
    <tr><th scope="col">姓名</th><th scope="col">成绩</th></tr>
    <tr><td>小明</td><td>90</td></tr>
</table>
```

## 二、边框（CSS）

```css
table  { border-collapse: collapse; }
th, td { border: 1px solid #666; padding: 8px; }
```

- `border` 三个值依次是：宽度、线型、颜色。✗ 只写 `1px` 看不到边框。
- `border-collapse: collapse` 让相邻单元格合并边框，不出现双线。

## 三、合并单元格

- `colspan="2"` — 横向跨 2 列
- `rowspan="2"` — 纵向跨 2 行
- ★ 合并之后，被占用的位置要**少写对应数量的单元格**（画网格、逐行数列）。

```html
<tr><th colspan="2">两科总分</th><td>185</td></tr>
```

## 四、分组（了解）

- `<thead>` 表头区　`<tbody>` 数据区　`<tfoot>` 汇总区
- ✗ `<thead>` 和 `<th>` 不是一回事。
