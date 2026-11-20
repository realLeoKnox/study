# Class 17：JavaScript 变量、类型、函数与对象

> Class 16 让脚本运行起来，并用函数处理按钮点击。本课进一步认识脚本处理的数据：先把值存进变量，再用函数、数组和对象组织它们。

## 本课目标

- 会用 `let`、`const` 声明变量，认识 `var` 的旧写法。
- 区分七种原始类型与对象类型，正确理解 `typeof`。
- 会定义、调用函数，读懂数组索引和对象属性。
- 能识别课堂草稿里未写完的 JavaScript 语句。

## 一、基本语法与变量

JavaScript **区分大小写**：`att` 与 `Att` 是两个不同的名字。单行注释用 `//`，多行注释用 `/* ... */`。

```js
let score = 98;          // 以后可以重新赋值
score = 99;
const name = "张三";    // 这个变量名不能重新绑定到别的值
```

| 声明方式 | 能否重新赋值 | 作用域与使用建议 |
| --- | --- | --- |
| `let` | 能 | 块级作用域；值需要变化时使用 |
| `const` | 不能重新绑定 | 块级作用域；默认优先使用 |
| `var` | 能 | 函数作用域，旧代码常见；新练习通常不用 |

`const` 限制的是**变量绑定**，不代表对象内容不可修改。例如 `const person = { age: 18 }; person.age = 19;` 可以执行。变量命名和注释都属于代码语法，与 HTML 注释 `<!-- ... -->` 不同。

---

## 二、JavaScript 的数据类型

七种**原始类型**是：`string`、`number`、`bigint`、`boolean`、`undefined`、`symbol`、`null`。数组、函数和普通对象都属于**对象**一类，而不是原始类型。

| 类型 | 示例 | 记忆要点 |
| --- | --- | --- |
| 字符串 `string` | `"你好"`、`'hello'` | 表示文本 |
| 数字 `number` | `3`、`-0.6`、`3.14e3` | 普通整数与小数都属此类 |
| 大整数 `bigint` | `123n` | 用于需要精确表示的大整数 |
| 布尔 `boolean` | `true`、`false` | 表示真与假 |
| `undefined` | `let x;` 后的 `x` | 已声明但尚未赋值等情况 |
| `null` | `let x = null;` | 主动表示“当前没有值” |
| `symbol` | `Symbol("id")` | 可创建唯一标识符，入门阶段了解即可 |

```js
const height = 183;       // number
const pi = 3.14159267;    // number
const big = 9007199254740993n; // bigint
const passed = true;      // boolean
const username = "张三"; // string
```

`3.14e3` 表示 `3.14 × 10³`，值是 `3140`。`number` 还可能出现 `NaN`（不是有效数值的运算结果）与 `Infinity`；它们不是新类型，`typeof NaN` 仍是 `"number"`。

---

## 三、函数：把一段操作封装起来

函数是一段可重复调用的代码。Class 16 的点击处理函数就是“点击发生时执行的操作”。

```js
function greet(name) {
    return "你好，" + name;
}

console.log(greet("张三")); // 你好，张三
```

| 部分 | 作用 |
| --- | --- |
| `name` | 形参，接收调用时传入的数据 |
| `greet("张三")` | 调用函数，`"张三"` 是实参 |
| `return` | 结束函数并把结果交还给调用处 |

函数也可以作为值存入变量：

```js
const sayHello = function () {
    return "Hello";
};
console.log(sayHello());
```

原草稿的 `function (return "my name")` 不是合法函数语法；函数体必须写在 `{}` 中。`alert()` 用于弹窗，`console.log()` 用于控制台输出；`return` 本身不会自动显示结果。

---

## 四、数组与对象

### 1. 数组：按位置存多个值

```js
const students = ["张三", "李四", "王五"];
console.log(students[0]);     // 张三
console.log(students[1]);     // 李四
console.log(students.length); // 3
```

数组索引从 **0** 开始，最后一个元素的索引是 `length - 1`。索引越界时通常得到 `undefined`。数组可容纳多种类型，但同一用途的数据保持一致类型更容易理解。

### 2. 对象：按属性名保存相关信息

```js
const person = {
    id: "2025001",
    name: "张三",
    age: 18
};
console.log(person.name);    // 张三
console.log(person["age"]); // 18
```

对象字面量写在 `{}` 中，由“属性名: 值”组成。`person.name` 和 `person["name"]` 都可读取属性；属性不存在时通常得到 `undefined`。数组适合有顺序的一组项目，对象适合描述同一个事物的多个属性。

---

## 五、用 `typeof` 检查类型

`typeof` 是运算符，返回描述类型的字符串。它后面可以跟变量或表达式，括号可写可不写。

```js
typeof "Leo";       // "string"
typeof 11;          // "number"
typeof true;        // "boolean"
typeof undefined;   // "undefined"
typeof function () {}; // "function"
typeof [];          // "object"
typeof null;        // "object"，历史遗留现象
```

`typeof null` 的结果容易误导，不能据此认为 `null` 是普通对象；判断数组可用 `Array.isArray(value)`。没有给 `typeof` 提供操作数是语法错误，原草稿里空的 `typeof()` 不能直接运行。

### 补充：把结果写进页面

`document.body.innerText` 可把文字直接写进 `<body>`：`=` 会替换页面原有内容，`+=` 会追加；课堂文件中这两行是注释，认识即可。

```js
// document.body.innerText = height+score+pi+v+f1 +"\n"
// document.body.innerText += height+score+pi+v+f1
```

## 常见错误

1. 把 `number` 的整数与小数当成两种原始类型。
2. 把数组索引从 1 开始数，或把 `list[1]` 误认为第一个元素。
3. 认为 `const` 对象的属性永远不能修改。
4. 用 `typeof` 判断数组或 `null` 的具体身份。
5. 把 JavaScript 的对象字面量 `{ name: "张三" }` 直接当作 Java 的类实例；两种语言的对象模型不同。
6. 误以为给 `innerText` 直接赋值是在追加内容；实际会覆盖页面原有内容。

## 本课小结

变量保存值；原始类型描述基本值；函数组织可执行操作；数组按索引组织项目；对象按属性名组织信息。`typeof` 适合初步观察类型，但数组和 `null` 要单独判断。

## 与前一课相连

Class 16 的按钮事件处理函数可以读取输入、修改数组或对象，再更新页面。到这里，已经从“让代码运行”走到“让代码处理数据”。

## 自测题

1. `let x;` 与 `let x = null;` 表示的状态有何不同？
2. `const a = ["甲", "乙"];` 中 `a[1]` 是什么？还能执行 `a.push("丙")` 吗？
3. `typeof []` 与 `typeof null` 分别返回什么？如何判断一个值是数组？

## 重点掌握

能写出变量、函数、数组、对象各一个例子，并说清 `typeof` 的常见结果与两个例外。
