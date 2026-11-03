# Class 7：HTML 表单与常用控件

前两课分别学习了**展示数据的表格**和**展示媒体的标签**。本节转到用户操作：表单把文字、选项或文件收集起来，并在提交时发送给指定地址。

## 一、表单的基本结构

```html
<form action="/submit" method="post">
    <label for="username">姓名：</label>
    <input id="username" name="username" type="text">
    <button type="submit">提交</button>
</form>
```

| 部分 | 作用 | 记忆要点 |
| --- | --- | --- |
| `<form>` | 包住参与提交的控件 | 表单的整体容器 |
| `action` | 指定接收数据的地址 | `/submit` 只是示例，实际要有接收程序 |
| `method` | 指定提交方式 | 常见值是 `get`、`post` |
| `name` | 控件提交时的字段名 | 需要提交的控件通常要有 `name` |
| `value` | 控件的值或提交值 | 不同控件的用法略有不同 |
| `<button type="submit">` | 触发表单提交 | 明确写出按钮类型 |

`get` 通常把表单数据放在 URL 查询参数中，适合搜索等读取操作；`post` 通常把数据放在请求体中。**`post` 本身不等于加密**，传输敏感信息仍需 HTTPS。

### `label` 与 `placeholder`

`<label for="username">` 对应 `id="username"`：点击文字也能聚焦输入框，读屏工具也能知道控件的名称。`placeholder` 是框内的临时提示，输入后会消失，不能代替 `<label>`。

---

## 二、单行文本与密码框

| `type` | 用途 | 常用属性 |
| --- | --- | --- |
| `text` | 输入单行文字；省略 `type` 时的默认类型 | `name`、`value`、`placeholder`、`maxlength` |
| `password` | 输入密码，视觉上遮挡字符 | `name`、`required` 等 |

```html
<label for="account">账号：</label>
<input id="account" name="account" type="text" maxlength="20">

<label for="password">密码：</label>
<input id="password" name="password" type="password" required>
```

`type="password"` 只遮挡屏幕显示，不负责加密。`required` 表示提交前必须填写，但前端检查不能代替服务器检查。`readonly` 控件通常仍会提交；`disabled` 控件不能操作，通常也不会提交。

---

## 三、单选按钮与复选框

### 1. 单选：`radio`

同一组单选按钮使用相同的 `name`；每一项设置不同的 `value`，提交时才知道用户选了哪项。

```html
<label><input type="radio" name="gender" value="male">男</label>
<label><input type="radio" name="gender" value="female">女</label>
```

同组通常只能选中一项。`checked` 可以设置初始选中项；未选中的单选按钮不会提交。

### 2. 多选：`checkbox`

复选框彼此独立；同名复选框可以同时选中多个值。

```html
<label><input type="checkbox" name="hobby" value="music">音乐</label>
<label><input type="checkbox" name="hobby" value="art">美术</label>
```

未勾选的复选框通常不会提交。为每一项写不同的 `value`；若省略 `value`，选中后默认提交值是 `on`，难以区分选项。

| 对比项 | `radio` | `checkbox` |
| --- | --- | --- |
| 同组选择 | 通常最多一项 | 可以多项 |
| 分组方式 | 同名表示同一互斥组 | 同名便于接收多个值 |
| 典型场景 | 选择一个年级 | 勾选多个爱好 |

---

## 四、下拉选择与多行文本

### 1. 下拉列表：`select` 与 `option`

```html
<label for="city">城市：</label>
<select id="city" name="city">
    <option value="xingcheng">兴城</option>
    <option value="shenyang">沈阳</option>
</select>
```

`<select>` 是控件，`<option>` 是选项；`selected` 设置初始选中项。默认只能选一项，写上 `multiple` 后可以选多项。`value` 是提交值，标签之间的文字是用户看到的内容。

### 2. 多行文本：`textarea`

```html
<label for="intro">个人简介：</label>
<textarea id="intro" name="intro" rows="4" cols="30">默认内容</textarea>
```

`<textarea>` 的默认内容写在开始标签与结束标签之间，不能靠 `value` 属性设置。`rows`、`cols` 给出初始显示尺寸，也可用 CSS 调整。

---

## 五、文件上传

上传文件使用 `<input type="file">`，与 Class 6 用来**显示**图片的 `<img>` 作用不同。

```html
<form action="/upload" method="post" enctype="multipart/form-data">
    <label for="photo">选择照片：</label>
    <input id="photo" name="photo" type="file" accept="image/*">
    <button type="submit">上传</button>
</form>
```

| 属性 | 作用 |
| --- | --- |
| `accept="image/*"` | 提示文件选择器优先显示图片文件 |
| `multiple` | 允许选多个文件 |
| `enctype="multipart/form-data"` | 表单提交文件时需要的编码方式 |

`accept` 只是选择提示，服务器仍须检查收到的文件。普通网页上传文件通常使用 `method="post"`，并在 `<form>` 上设置 `enctype="multipart/form-data"`。

---

## 六、把三课串起来

| 需求 | 应选的结构 | 对应课程 |
| --- | --- | --- |
| 展示有行列关系的数据 | `<table>` | Class 5 |
| 在页面显示一张图片 | `<img>` | Class 6 |
| 播放音频或视频 | `<audio>`、`<video>` | Class 6 |
| 让用户输入、选择、上传并提交 | `<form>` 与表单控件 | Class 7 |

课堂示例把表单控件放在表格单元格里，是为了快速排成两列；表单的核心仍是 `<form>`、控件和提交按钮。一般表单排版可用 CSS，表格应主要用于真正的行列数据。

### 综合示例：课程报名页

```html
<h1>课程报名</h1>
<img src="course.jpg" alt="课程教室的照片">

<table>
    <tr><th scope="col">课程</th><th scope="col">名额</th></tr>
    <tr><td>HTML 入门</td><td>20</td></tr>
</table>

<form action="/signup" method="post">
    <label for="student">姓名：</label>
    <input id="student" name="student" type="text" required>
    <button type="submit">报名</button>
</form>
```

这段代码中，图片负责展示，表格负责呈现课程数据，表单负责收集报名信息；`/signup` 仍需由服务器提供实际处理程序。

## 本节小结

1. `<form>` 规定数据提交到哪里、用什么方式提交。
2. `name` 是提交字段名；`value` 是选项或控件的值。
3. `radio` 同名互斥，`checkbox` 可多选；两者都要给选项明确的 `value`。
4. `<label>` 说明控件用途；`placeholder` 不能代替它。
5. 文件上传用 `type="file"`，显示图片用 `<img>`。

## 重点掌握

能独立写出包含文字输入、单选、多选和提交按钮的表单，并解释每个控件提交时的 `name` 与 `value`。
