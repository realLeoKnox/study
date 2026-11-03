# Class 7：表单与常用控件

## 一、表单结构

```html
<form action="/submit" method="post">
    <label for="user">姓名：</label>
    <input id="user" name="user" type="text">
    <button type="submit">提交</button>
</form>
```

- `action` — 数据提交到哪个地址
- `method` — 提交方式：`get` 把数据放在 URL 里（适合搜索）；`post` 把数据放在请求体里
- `name` — 提交时的字段名（★ 要提交的控件必须有）
- `value` — 控件的值
- `<label for>` 对应控件的 `id`，点文字也能选中控件
- ✗ `placeholder` 只是提示，不能代替 `label`；✗ `post` 不等于加密。

## 二、文本框与密码框

- `type="text"` — 单行文本（不写 type 时的默认值），可加 `maxlength`
- `type="password"` — 密码框，只是遮住显示，**不加密**
- `required` 必填　`readonly` 只读（会提交）　`disabled` 禁用（不提交）

## 三、单选与多选

```html
<label><input type="radio" name="sex" value="m">男</label>
<label><input type="radio" name="sex" value="f">女</label>
<label><input type="checkbox" name="hobby" value="music">音乐</label>
```

- `radio`：**同名为一组**，只能选一项
- `checkbox`：可以选多项
- 每个选项写不同的 `value`；`checked` 设置默认选中；没选中的不提交

## 四、下拉框与多行文本

```html
<select name="city">
    <option value="sy" selected>沈阳</option>
</select>
<textarea name="intro" rows="4" cols="30">默认内容</textarea>
```

- `selected` 默认选中；`multiple` 允许多选
- `<textarea>` 的默认内容写在两个标签**之间**，✗ 不用 `value`

## 五、文件上传

```html
<form action="/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="photo" accept="image/*">
</form>
```

- ★ 上传文件必须用 `post` 加 `enctype="multipart/form-data"`
- `accept` 只是筛选提示，`multiple` 允许选多个文件
