# Class 17：JavaScript 数据基础（手抄版）

## 一、变量与语法 ★

```js
let score = 98; score = 99; // 可重新赋值
const name = "张三";         // 不可重新绑定
```

- JS 区分大小写：`att` ≠ `Att`；`//` 是单行注释，`/* ... */` 是多行注释。
- `let`、`const` 是块级作用域；`var` 是旧写法。`const` 对象不能重新绑定，但内部属性仍可改。

## 二、七种原始类型 ★

| 类型 | 例子 |
| --- | --- |
| `string` | `"你好"` |
| `number` | `3`、`3.14`、`3.14e3` |
| `bigint` | `123n` |
| `boolean` | `true`、`false` |
| `undefined` | 声明后未赋值等情况 |
| `null` | 主动表示无值 |
| `symbol` | `Symbol("id")` |

★ 普通整数和小数都属 `number`；数组、函数和普通对象不是原始类型。

## 三、函数、数组、对象

```js
function greet(name) { return "你好，" + name; }
const list = ["张三", "李四"];
const person = { name: "张三", age: 18 };
```

函数用参数接收数据、`return` 返回结果；数组索引**从 0 开始**，`list[1]` 是“李四”；对象按属性名取值，`person.name` 是“张三”。

## 四、`typeof` 易错点 ★

```js
typeof 3;           // "number"
typeof "hi";        // "string"
typeof [];          // "object"
typeof null;        // "object"（历史遗留）
typeof function(){}; // "function"
```

判断数组用 `Array.isArray(value)`。`typeof null` 不能说明 `null` 是普通对象；空写 `typeof()` 是语法错误。

**两课串联：**Class 16 让按钮事件运行；Class 17 让事件函数能读写变量、数组和对象，再处理页面数据。
