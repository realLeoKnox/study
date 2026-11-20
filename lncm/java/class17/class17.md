# Class 17：运行时多态与抽象类

> Class 15 学会继承和重写，Class 16 学会父类引用指向子类对象。本课把两者合起来：**同一父类类型的引用，面对不同子类对象，调用同名方法会表现出不同的行为**。

## 本课目标

- 会从课堂 `动物 → 狗/猫` 代码解释运行时多态。
- 知道调用方法时编译期与运行期各检查什么。
- 会声明抽象类、抽象方法并在具体子类中实现。
- 能区分抽象类与“还未写完的普通类”。

## 一、运行时多态的三个条件

1. 类型之间存在继承关系（或以后学习的接口实现关系）。
2. 子类重写父类可重写的实例方法。
3. 用父类类型的引用指向不同子类对象，并调用该方法。

```java
class Animal {
    void cry() { System.out.println("动物叫"); }
}
class Dog extends Animal {
    @Override void cry() { System.out.println("汪汪"); }
}
class Cat extends Animal {
    @Override void cry() { System.out.println("喵喵"); }
}

Animal animal = new Dog();
animal.cry(); // 汪汪
animal = new Cat();
animal.cry(); // 喵喵
```

这与课堂 `dongwu.java` 的过程相同：**引用变量 `animal` 的声明类型始终是 Animal，实际指向的对象先是 Dog，后是 Cat**。变的是引用指向的对象，不是让同一个对象突然变成另一种类。

---

## 二、方法调用分两步判断

```java
Animal animal = new Dog();
animal.cry();
```

| 阶段 | 根据什么判断 | 结果 |
| --- | --- | --- |
| 编译时 | 引用的声明类型 Animal | Animal 必须有可调用的 `cry()` |
| 运行时 | 实际对象类型 Dog | 执行 Dog 重写后的 `cry()` |

因此父类类型的参数也能接收不同子类：

```java
static void makeItCry(Animal animal) {
    animal.cry();
}
makeItCry(new Dog());
makeItCry(new Cat());
```

新增一种 Animal 子类并正确重写 `cry()` 后，`makeItCry()` 通常无需修改。**字段访问和 `static` 方法不按这套实例方法的运行时分派规则处理**，见 Class 15–16。

### 编译时与运行时多态（了解）

课上提到“编译时多态、运行时多态”。入门时可把**方法重载**理解为编译时根据参数选择方法签名，把**实例方法重写**理解为运行时根据实际对象选择方法实现。它们不是同一个机制，本课重点是后者。

---

## 三、为什么需要抽象类

如果“动物会叫”是共同要求，但一般的 Animal 没有统一的叫声，就可以只规定方法名称，把具体实现交给 Dog、Cat。

```java
abstract class Animal {
    abstract void cry(); // 只有方法声明，没有方法体

    void sleep() {       // 抽象类也能有普通方法
        System.out.println("睡觉");
    }
}
```

| 概念 | 规则 |
| --- | --- |
| 抽象类 | 用 `abstract class` 声明，不能直接 `new` |
| 抽象方法 | 用 `abstract` 声明，以分号结束，没有方法体 |
| 具体子类 | 必须实现继承到的抽象方法，或者自己也声明为抽象类 |
| 普通成员 | 抽象类仍可有字段、构造器和已实现的方法 |

**有抽象方法的类必须是抽象类；抽象类可以没有抽象方法。**抽象方法不能同时是 `private`、`static` 或 `final`，因为它必须留给子类实现。抽象类也不能同时声明为 `final`。

抽象类的构造器虽不能通过 `new Animal()` 直接调用，但创建具体子类对象时仍要执行父类构造器，完成父类部分的初始化。

---

## 四、完整示例：共同类型、不同实现

以下代码可保存为 `Demo.java`，编译运行：

```java
abstract class Animal {
    private final String name;
    Animal(String name) { this.name = name; }
    String getName() { return name; }
    abstract void cry();
}

class Dog extends Animal {
    Dog(String name) { super(name); }
    @Override void cry() { System.out.println(getName() + "：汪汪"); }
}

class Cat extends Animal {
    Cat(String name) { super(name); }
    @Override void cry() { System.out.println(getName() + "：喵喵"); }
}

public class Demo {
    public static void main(String[] args) {
        Animal[] animals = { new Dog("小黑"), new Cat("小白") };
        for (Animal animal : animals) {
            animal.cry();
        }
    }
}
```

运行结果：

```text
小黑：汪汪
小白：喵喵
```

数组里存的是 Animal 引用，实际对象各有自己的 `cry()` 实现。这也把 Class 14 的**对象数组**、Class 15 的**继承与重写**、Class 16 的**向上转型**串在了一起。

课堂抽象类示意照片保留在本目录：[查看原图](telegram-cloud-photo-size-1-5186252875296869737-w.jpg)。

---

## 五、对照课堂 `Boy.java` 与面积作业

`Boy.java` 中 `GirlFriend` 是抽象类，声明 `speak()`、`cooking()`；两个具体子类分别实现它们。`Man` 的 `friend` 字段只依赖抽象父类类型，因此可先指向 `ChinaGirlFriend`，再改为 `AmericanGirlFriend`，而 `showGirlFriend()` 的调用代码不用随每个子类修改。

`class16/homework/MainClass.java` 的 `Geometry[]` 同理：Rect、Circle 都实现 `getArea()`，循环逐个调用后得到面积总和。这个例子说明**面向共同抽象编程**，不必在每次调用前写一串 `instanceof` 判断。

课堂 `Boy.java` 若尚未设置 `friend` 就调用 `showGirlFriend()`，会因空引用发生 `NullPointerException`；使用前应保证字段已赋值。

## 常见错误

1. 把多态说成“同一个对象变成不同子类”。
2. 认为父类引用可以直接调用子类独有方法；编译时仍先看引用类型。
3. 把同名字段或 `static` 方法当作运行时重写。
4. 给抽象方法写方法体，或写 `new Animal()` 直接实例化抽象类。
5. 具体子类漏掉抽象方法实现，却没有把自己也声明为抽象类。

## 本课小结

继承提供共同类型，重写提供不同实现，父类引用提供统一调用方式。抽象类可以定义共同状态和行为规范，让具体子类补全尚未确定的行为。

## 自测题

1. `Animal a = new Dog(); a.cry();` 编译时和运行时分别看什么？
2. 为什么 `Animal[]` 可以同时保存 Dog 和 Cat？
3. 抽象类一定有抽象方法吗？有抽象方法的类一定是抽象类吗？
4. `final` 类与抽象类能否是同一个类？为什么？
