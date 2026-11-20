# Class 16：向上转型、向下转型、`instanceof` 与 `final`

> Class 15 学会继承和重写。本课重点看**引用类型与实际对象类型不同时会发生什么**，再用 `final` 限制某些修改或继承行为。

## 本课目标

- 理解向上转型不会改变对象，只改变引用能直接使用的成员范围。
- 会先用 `instanceof` 检查，再进行安全的向下转型。
- 分清字段访问与重写方法的选择时机。
- 能解释 `final` 修饰变量、方法、类的不同含义。

## 一、对象赋值的兼容规则：向上转型

子类对象可以赋给父类类型的引用，无需强制转换。因为 Dog 是一种 Animal，需要 Animal 的地方可以接收 Dog。

```java
class Animal {
    int age = 0;
    void cry() { System.out.println("动物叫"); }
}
class Dog extends Animal {
    int age = 1;
    @Override
    void cry() { System.out.println("汪汪"); }
    void eat() { System.out.println("狗在吃饭"); }
}

Animal animal = new Dog(); // 向上转型；对象仍是 Dog
animal.cry();              // 汪汪：重写方法按实际对象选择
System.out.println(animal.age); // 0：字段按引用类型选择
// animal.eat();           // 编译错误：Animal 类型没有 eat()
```

**两步判断：**编译时看引用类型，决定能直接调用哪些成员；运行时对可调用的重写实例方法，看实际对象类型来选择方法体。向上转型没有复制、截断或变成一个新的父类对象。

---

## 二、向下转型：恢复子类视角

父类引用实际指向 Dog 对象时，才可以转回 Dog 类型：

```java
Animal animal = new Dog();
Dog dog = (Dog) animal;
dog.eat(); // 狗在吃饭
```

强制转换**不会改变对象本身**。若实际对象不是 Dog，编译可以通过，运行时却会抛 `ClassCastException`。

```java
Animal animal = new Animal();
// Dog dog = (Dog) animal; // 运行时错误
```

### 先检查再转换

```java
if (animal instanceof Dog) {
    Dog dog = (Dog) animal;
    dog.eat();
}
```

`instanceof` 判断左侧引用指向的对象是否可视为右侧类型，返回 `boolean`。引用为 `null` 时，普通 `instanceof` 判断为 `false`。在 JDK 25 中也可写 `if (animal instanceof Dog dog) { dog.eat(); }`，但先掌握课堂的两步写法即可。

课堂上转型/下转型对比照片保留在本目录：[查看原图](telegram-cloud-photo-size-1-5179304859457490206-w.jpg)。图中的“绝对安全”指向上转型本身无需运行时强制类型检查，不代表这段程序的所有操作都不会出错。

---

## 三、字段与方法为什么结果不同

课堂 `class16.java` 同时在 `Animal` 和 `Dog` 中声明了 `age`，又让 `Dog` 重写 `cry()`：

| 表达式 | 按什么决定 | 示例结果 |
| --- | --- | --- |
| `animal.age` | 引用类型 `Animal` | 父类的 `age`，默认值 0 |
| `dog.age` | 引用类型 `Dog` | 子类的 `age`，值 1 |
| `animal.cry()` | 实际对象 `Dog` | 执行 Dog 的版本 |
| `animal.eat()` | 编译时先看 `Animal` | 不可直接调用 |

这种差异是理解多态的关键。重写只适用于可重写的**实例方法**；字段不是动态分派的。除非有明确教学目的，同名字段容易令人混淆，实际代码尽量避免。

---

## 四、`final` 的三种用法

| 修饰位置 | 含义 | 示例 |
| --- | --- | --- |
| 变量/字段 | 赋值后不能再次赋值 | `final int limit = 10;` |
| 方法 | 子类不能重写 | `final void save() {}` |
| 类 | 不能被继承 | `final class Config {}` |

```java
final class StudentId {
    static final int MAX_LENGTH = 12;
}
```

`static final` 常用于类级常量，常量名通常用大写加下划线。**`final` 引用不能指向别的对象，但其对象内部状态未必不可变**：

```java
final StringBuilder text = new StringBuilder("A");
text.append("B"); // 可以修改对象内容
// text = new StringBuilder(); // 不可重新赋值
```

未在声明处赋值的 `final` 实例字段可以在每条构造路径中完成一次赋值。`final` 的作用是表达约束；不要把它简单记成“自动提升性能”或“对象完全不变”。

---

## 五、与课堂作业的连接

`class16/homework/MainClass.java` 把 `Rect` 与 `Circle` 放进 `Geometry[]`：

```java
Geometry[] shapes = { new Rect(3, 4), new Circle(2) };
for (Geometry shape : shapes) {
    System.out.println(shape.getArea());
}
```

数组元素的引用类型都是 Geometry，实际对象却可以是不同子类。调用 `getArea()` 时分别执行各自的实现。这已经引出 Class 17 的**运行时多态**与**抽象类**。

作业源码把 `totalArea` 保存为字段，并在 `computerTotalArea()` 中不断累加；若对同一个 `TotalArea` 对象调用两次，会把第一次的结果再加一遍。若希望每次调用都重新计算，应在方法内使用从 `0` 开始的局部 `sum`，或先重置字段。这是作业实现细节，不是多态规则本身。

## 常见错误

1. 以为 `Animal animal = new Dog()` 后，Dog 对象变成 Animal 对象。
2. 以为强制转换可以把真正的 Animal 变成 Dog。
3. 认为 `animal.age` 会像 `animal.cry()` 一样按实际对象动态选择。
4. 把 `final` 引用与不可变对象混为一谈。
5. 把“`super(...)` 与 `this(...)` 不能在同一构造器中都作为显式构造器调用”误写成“代码中不能同时出现 `super` 与 `this`”。

## 本课小结

父类引用可以指向子类对象；向上转型自动发生，向下转型要确认实际对象类型。`instanceof` 能做检查。字段看引用类型，重写实例方法看实际对象。`final` 分别限制重新赋值、重写和继承。

## 与下一课相连

不同子类对象放在同一个父类引用或数组中，调用相同方法却产生不同结果，这就是下一课要系统学习的多态。抽象类则用来规定子类共同要实现的行为。

## 自测题

1. `Animal a = new Dog();` 后，`a` 的声明类型和实际对象类型各是什么？
2. 为什么 `a.cry()` 可以运行 Dog 版本，而 `a.eat()` 不能直接编译？
3. `final StringBuilder x` 能否调用 `x.append()`？能否给 `x` 重新赋值？
