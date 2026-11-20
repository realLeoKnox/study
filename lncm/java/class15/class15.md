# Class 15：继承、成员访问与方法重写

> Class 14 学过组合（has-a）、包、对象数组和 `static`。本课学习继承（is-a）：当“学生是一种人”时，子类可以在父类已有能力上增加自己的成员。

## 本课目标

- 会写 `extends`，分清继承与组合。
- 理解父类构造器、`this`、`super` 的作用。
- 分清同名字段与重写方法的访问规则。
- 会用 `@Override` 检查重写，并读懂对象数组中的引用。

## 一、继承与组合

```java
class Person { }
class Student extends Person { } // Student 是一种 Person
class Phone { }
class Teacher extends Person {
    Phone phone;                 // Teacher 有一部 Phone
}
```

| 关系 | 英文记法 | 例子 |
| --- | --- | --- |
| 继承 | is-a | 学生是一种人 |
| 组合 | has-a | 学生有一部手机 |

Java 类只能直接继承一个类，但可以经过多层继承；`class C extends B`、`class B extends A` 时，C 也是 A 的子类型。构造方法不会像普通成员方法那样被继承，创建子类对象时仍须先完成父类部分的初始化。

---

## 二、构造器与 `this`、`super`

```java
class Person {
    protected String name;
    Person(String name) { this.name = name; }
}

class Student extends Person {
    private int score;
    Student(String name, int score) {
        super(name);        // 调用父类构造器
        this.score = score; // 当前对象的字段
    }
}
```

| 写法 | 指向什么 |
| --- | --- |
| `this.score` | 当前对象的成员 |
| `super.name` | 父类中可访问的成员 |
| `this(...)` | 本类的另一个构造器 |
| `super(...)` | 父类构造器 |

一个构造器不能同时使用 `this(...)` 与 `super(...)` 作为显式构造器调用。若没有显式写调用，编译器会尝试调用父类无参构造器 `super()`；父类只有带参构造器时，就要显式传入参数。

---

## 三、访问权限与 `import`

| 修饰符 | 主要可访问范围 |
| --- | --- |
| `public` | 任何可访问该类的代码 |
| `protected` | 同包代码；异包子类中也可按继承规则访问 |
| 不写（包访问） | 同一包内 |
| `private` | 声明它的类内部 |

`private` 字段不会因为创建了子类就变成子类可直接访问；通常由父类提供方法来操作。异包子类访问 `protected` 成员有额外限制，不能简单理解成“任意子类引用都能访问”。

`import` 只是让源代码能使用其他包中的类型名，不会复制类或创建对象。同包类型无需导入；`java.lang` 中的常见类型会自动可用；`import 包名.*;` 不会把子包也导入。这部分承接 Class 14 的包机制。

---

## 四、字段隐藏与方法重写

### 1. 同名字段：按引用的声明类型访问

```java
class Parent {
    int age = 1;
}
class Child extends Parent {
    int age = 8;
}

Parent p = new Child();
System.out.println(p.age);          // 1
System.out.println(((Child) p).age); // 8
```

子类声明同名字段时，父类字段并未消失。**字段访问由引用的编译时类型决定**，不会像实例方法一样动态分派。课堂 `person` 的 `age` 是 `private`，`Student` 又声明了 `age`；这两个字段仍是不同的存储位置，但子类不能直接访问父类的私有字段。

### 2. 重写实例方法：按实际对象执行

```java
class Animal {
    void cry() { System.out.println("动物叫"); }
}
class Dog extends Animal {
    @Override
    void cry() { System.out.println("汪汪"); }
}

Animal a = new Dog();
a.cry(); // 汪汪
```

**重写**要求子类实例方法与父类可重写方法的名称和参数列表匹配。调用时先检查引用类型是否有该方法；运行时再根据实际对象选择重写版本。`@Override` 能让编译器帮助发现误拼方法名或参数不匹配。

重写方法的可见性不能比父类更窄；返回类型可以相同，引用类型返回值可用符合规则的更具体类型；不能新增更宽的受检异常。`static` 方法同名属于**隐藏**，不是实例方法重写。

---

## 五、对象数组装的是引用

课堂代码先创建 `Student[] students = new Student[100]`，这只创建了**100 个引用位置**，每个位置起初为 `null`，不会自动创建 100 个学生对象。

```java
Student[] students = new Student[3];
for (int i = 0; i < students.length; i++) {
    students[i] = new Student("学生" + i, 80 + i);
}
```

若循环里一直写 `students[i] = student;`，所有位置都指向**同一个对象**。改动其中一个位置所指对象的字段，会从其他位置观察到同样的改动；要多个独立对象，循环中每次都要 `new`。

## 常见错误

1. 认为继承能让子类直接读取父类 `private` 字段。
2. 认为同名字段也会根据运行时对象动态选择。
3. 写出与父类参数不同的方法却以为完成了重写；应加 `@Override`。
4. 创建对象数组后直接访问 `students[0].name`，却没有先给该位置赋对象。
5. 把 `import` 当作“复制类文件”或“创建对象”。

## 本课小结

继承表达 is-a，组合表达 has-a；子类构造时要初始化父类部分。同名字段按引用类型访问，被重写的实例方法按实际对象执行。对象数组保存的是引用，不会自动生成对象。

## 与下一课相连

本课的 `Animal a = new Dog()` 已经是**向上转型**。下一课进一步学习父类引用能访问什么、何时可以安全向下转型，以及 `final` 的限制。

## 自测题

1. `Student[] a = new Student[5]` 创建了几个 `Student` 对象？
2. 父类字段与子类字段同名时，`Parent p = new Child(); p.age` 读哪一个？
3. `@Override` 帮助检查什么？
