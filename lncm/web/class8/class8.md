# Class 8：表单补充与更多输入类型

Class 7 学会用表单收集信息。本节继续解决三个问题：**怎样给相关控件分组、怎样提示和检查输入、怎样选择合适的输入类型**。

## 一、回顾表单的提交关系

```html
<form action="/register" method="post">
    <label for="email">邮箱：</label>
    <input id="email" name="email" type="email" required>
    <button type="submit">注册</button>
</form>
```

| 写法 | 作用 |
| --- | --- |
| `action` | 指定处理提交数据的地址 |
| `method` | 指定提交方式，常见为 `get`、`post` |
| 控件的 `name` | 提交时使用的字段名 |
| `<label for="email">` 与 `id="email"` | 将说明文字与控件关联 |

`<form>` 的 `name` 是表单自身的可选名称，**不能代替控件的 `name`**。课堂示例中某些输入框没有 `name`，作为页面演示可以显示，但提交时通常不会以这些控件的值形成对应字段。

---

## 二、`fieldset` 与 `legend`：给控件分组

一组相关问题可以放在 `<fieldset>` 中，用 `<legend>` 给整组命名。

```html
<fieldset>
    <legend>联系方式</legend>
    <label for="phone">电话：</label>
    <input id="phone" name="phone" type="tel">
</fieldset>
```

`<fieldset>` 表示**一组表单控件**，`<legend>` 表示**这组控件的标题**。它们有结构和可访问性意义；浏览器默认画出的边框只是样式，可用 CSS 调整。

---

## 三、常用输入属性

| 属性 | 作用 | 易错点 |
| --- | --- | --- |
| `placeholder` | 给出输入格式提示 | 不能替代 `<label>` |
| `autofocus` | 页面加载后请求自动聚焦 | 同一页面通常只设一个，不应滥用 |
| `required` | 提交前要求填写或选择 | 只是浏览器端检查，服务器仍要验证 |
| `value` | 设置初始值或选项提交值 | 提示文字与实际值不是一回事 |

```html
<label for="mail">邮箱：</label>
<input id="mail" name="mail" type="email"
       placeholder="name@example.com" required>
```

`required`、`autofocus` 是布尔属性，写上就表示启用。`placeholder` 会在输入后消失，所以重要的字段名称要留在 `<label>` 中。

---

## 四、更多 `<input type>`

| 类型 | 用途 | 需要记住的限制 |
| --- | --- | --- |
| `email` | 邮箱地址 | 浏览器会检查基本格式，不保证邮箱真实存在 |
| `tel` | 电话号码 | 便于调出电话键盘，默认不验证各地号码格式 |
| `url` | 网址 | 浏览器会检查基本 URL 格式 |
| `number` | 数值 | 可配合 `min`、`max`、`step`；不适合电话、学号 |
| `date` | 日期 | 控件外观因浏览器而异，提交值通常为 `YYYY-MM-DD` |

```html
<label for="height">身高（厘米）：</label>
<input id="height" name="height" type="number"
       min="50" max="250" step="1">

<label for="birthday">出生日期：</label>
<input id="birthday" name="birthday" type="date">
```

选择输入类型要看**数据含义**，不能只看字符是否含数字。电话号码可能有前导零、`+` 和连字符，因此一般使用 `tel`，不使用 `number`。

---

## 五、从课堂示例学会检查表单

课堂页面展示了邮箱、密码、单选、多选、日期、数字、文件和文本域。实际编写时，逐项检查：

1. 每个控件是否有清楚的 `<label>`；一组单选按钮是否有组名。
2. 需要提交的控件是否有 `name`；同组单选是否同名、各选项是否有不同 `value`。
3. 文件上传是否使用 `method="post"` 和 `enctype="multipart/form-data"`（见 Class 7）。
4. `action` 指向的地址是否真的能处理数据；`action=""` 只适合课堂演示或特定场景。

`type="reset"` 会恢复表单初始值，并不一定把所有内容清空；正式页面中应谨慎放置，以免用户误点后丢失输入。

## 本节小结

表单负责收集和提交，`fieldset`/`legend` 负责分组，输入类型和属性帮助用户填写。浏览器端的提示与检查不能代替服务器端处理。

## 与下一课相连

到这里，HTML 已能组织内容并收集信息。下一课学习 CSS：用选择器找到 HTML 元素，再改变字体、颜色、间距等外观。

## 重点掌握

能写出带 `label`、`name`、`required` 的输入框，并解释 `email`、`tel`、`number`、`date` 各自适合什么数据。
