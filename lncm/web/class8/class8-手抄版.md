# Class 8：表单补充与更多输入类型

## 一、回顾

- `action` 提交地址，`method` 提交方式，控件的 `name` 是字段名
- ✗ `<form name>` 是表单自己的名字，不能代替控件的 `name`；控件没有 `name` 就不会提交

## 二、分组：`fieldset` + `legend`

```html
<fieldset>
    <legend>联系方式</legend>
    <label for="tel">电话：</label>
    <input id="tel" name="tel" type="tel">
</fieldset>
```

- `<fieldset>` 包住一组控件，`<legend>` 是这一组的标题

## 三、常用属性

- `placeholder` — 输入格式提示（✗ 不能代替 label）
- `autofocus` — 页面打开时自动聚焦（一个页面只设一个）
- `required` — 必填（浏览器检查，服务器仍要再检查）
- `value` — 初始值

## 四、更多 `type`

- `email` — 邮箱，检查基本格式
- `tel` — 电话，手机上弹出数字键盘，不检查格式
- `url` — 网址
- `number` — 数字，可加 `min` / `max` / `step`
- `date` — 日期，提交格式是 `YYYY-MM-DD`

✗ 电话号码、学号不要用 `number`（会丢掉前导 0、`+` 等字符），电话用 `tel`。

## 五、注意

- `type="reset"` 恢复成**初始值**，不一定是清空，谨慎使用。
- ★ 浏览器端的检查不能代替服务器端检查。
