# Class 18：泛型与通配符

> 上节课学了运行时多态与抽象类，解决了“不同子类对象用同一套方式调用”的问题。本课先用“多态三问”巩固上节脉络，再学习**泛型**——把数据类型本身也当作参数来传递，同时实现代码复用和编译期类型安全；最后用**参数限定**与**通配符**解决泛型不能直接向上转型的问题。

## 本课目标

- 能用自己的话答出“多态三问”，分清实现步骤与成立前提。
- 说清什么是泛型、什么是泛型类、泛型的本质是什么，理解它相比 `Object` 万能盒的优势。
- 会写泛型类、泛型接口和泛型方法，掌握基本类型必须用包装类的规则。
- 会用 `<T extends 上界>` 限定类型范围，并理解为什么 `Box<Integer>` 不是 `Box<Number>` 的子类。
- 会用无界、上界、下界三种通配符，并能靠 **PECS 口诀**快速判断读写场景。

---

## 课前回顾：多态三问

开课先复习 Class 17 的核心（2026-11-24 10:33 Asia/Shanghai），结合 `Animal → Dog / Cat` 例子把三个常考问题串清楚：

### 1. 什么是多态？

**同一句方法调用，作用在不同对象上，表现出不同的行为（多种形态）。** 落到 Java 运行时多态上，就是同一个**父类引用**，先后指向不同的**子类对象**，调用同一个被重写的方法时，执行的是各自子类的版本：

```java
Animal a = new Dog();
a.cry();   // 输出：汪汪
a = new Cat();
a.cry();   // 输出：喵喵
```

变的是引用 `a` 所指向的实际对象，而不是把同一只狗“变身”成了猫。

### 2. 通过几个步骤实现多态？（怎么写）

按写代码的先后顺序，分 **3 步**：

1. **建立继承或实现关系**：定义父类和多个子类（如 `Dog extends Animal`、`Cat extends Animal`）。
2. **子类重写父类方法**：各子类分别重写父类的方法（`@Override void cry()`）。
3. **向上转型并调用方法**：用父类引用指向子类对象（`Animal a = new Dog();`），再通过父类引用调用该方法（`a.cry()`）。

> 记住两步判断：**编译时看左边**（父类 `Animal` 里必须有 `cry()`），**运行时看右边**（实际 `new` 的是谁，就执行谁重写后的版本）。

### 3. 实现多态的前提条件？（缺啥不行）

必须同时满足 **3 个条件**：

1. 存在继承（`extends`）或接口实现（`implements`）关系；
2. 子类重写了父类可重写的实例方法（`private`、`static`、`final` 方法和成员变量不参与运行时多态）；
3. 父类引用指向子类对象（向上转型）。

> 区分第 2 题与第 3 题：第 2 题答的是**编码步骤**，第 3 题答的是**成立的必要条件**（少任何一个，运行时都不会发生动态绑定）。

---

## 一、什么是泛型（6.7.1）

### 1. 先看痛点：一个“盒子”的三代演进

假设我们要写一个“盒子”类，专门用来存一个数据、再取出来。

**第一代：每种类型单写一个类（`IntBox`、`StrBox`）**

```java
class IntBox {
    private int data;
    public void set(int data) { this.data = data; }
    public int get() { return data; }
}

class StrBox {
    private String data;
    public void set(String data) { this.data = data; }
    public String get() { return data; }
}
```

这两个类的代码结构一模一样，只差了字段和方法的类型名。如果以后还要存小数、存 `Student`、存 `Car`，难道要复制粘贴写十几个盒子类吗？显然太重复了。

**第二代：`Object` 万能盒（`ObjBox`）**

回想 Class 16 学过的向上转型：`Object` 是 Java 中所有类的总父类，任何引用类型对象都能直接放进 `Object` 变量里！于是我们写一个万能盒：

```java
class ObjBox {
    private Object data;
    public void set(Object data) { this.data = data; }
    public Object get() { return data; }
}

ObjBox box = new ObjBox();
box.set("苹果");                  // String 向上转型为 Object ✓
Integer n = (Integer) box.get(); // 编译不报错，运行时抛出 ClassCastException ✗
```

万能盒确实只写了一个类，但留下两个隐患：

- **取值麻烦**：`get()` 返回的一律是 `Object`，每次拿出来都要手动强制向下转型；
- **极易崩溃**：盒子里实际装的是 `"苹果"`，你误把它强转成 `Integer`，编译器完全看不出来，程序跑起来才突然报错崩溃。

**第三代：泛型盒（`Box<T>`）——给盒子贴上“专属标签”**

有没有一种办法，只写**一套**盒子代码，但在使用时给它贴个标签：贴上 `String` 标签就只准放字符串、拿出来直接就是 `String`；贴上 `Integer` 标签就只准放整数、拿出来直接就是 `Integer`？这就是**泛型**。

```java
class Box<T> {                   // T 是一个“类型占位符”，先空着，用时再填
    private T data;
    public void set(T data) { this.data = data; }
    public T get() { return data; }
}

Box<String> sBox = new Box<>();  // 贴上 String 标签：专门装字符串的盒子
sBox.set("苹果");                 // ✓
// sBox.set(123);                // ✗ 编译期直接红线报错，根本放不进去
String fruit = sBox.get();       // ✓ 直接就是 String，不需要强制转换
```

| 方案 | 代码复用 | 取值要强转吗 | 放错类型何时发现 |
| --- | --- | --- | --- |
| 写死类型（`IntBox`/`StrBox`） | 差，一类写一份 | 不需要 | 编译期 |
| `Object` 万能盒（`ObjBox`） | 好，一份通用 | 每次都要强转 | 运行期才崩溃（危险） |
| 泛型容器（`Box<T>`） | 好，一份通用 | 不需要 | 编译期直接拦截（安全） |

### 2. 三个核心定义（补齐课堂框架）

以前写方法 `add(int a, int b)`，是把具体的**数值**抽成参数，调用时再传 `3` 或 `5`；泛型则是更进一步，把**数据类型**本身抽成参数，使用时再传 `String` 或 `Integer`。

- **泛型的定义**：Java（JDK 1.5 引入）提供的一种**参数化类型**机制，允许在定义类、接口或方法时不写死具体的数据类型，而是用类型参数（如 `<T>`）占位，等到真正使用时再指定具体类型。
- **为什么说“泛型是比类更高一层的抽象表示”**：类是把多个具体对象的共同特征抽出来（比如从一辆辆具体的车抽出 `Car` 类）；泛型再往上抽一层，把多个“结构相同、只差数据类型”的类（`IntBox`、`StrBox`、`CarBox`）进一步抽成一个通用模板 `Box<T>`。
- **泛型类的定义**：在类声明后面带有一对尖括号和类型参数的类（如 `class Box<T>`）。
- **泛型的本质**：**把数据类型参数化**。编译器会在编译阶段根据传入的实际类型做严格检查，并悄悄替我们补上类型转换；到了运行期，JVM 会执行**类型擦除**（把 `T` 还原成 `Object` 或限定的父类），因此不会真的生成很多份不同的 `.class` 文件。

---

## 二、声明泛型类与创建对象（6.7.2）

### 1. 泛型类的定义形式与命名习惯

```java
class 类名<类型参数1, 类型参数2> {
    // 类体
}
```

尖括号里可以声明一个或多个类型参数（如 `class Pair<K, V>`）。字母本身只要是合法标识符都行，但业界约定用单个大写字母见名知意：

| 字母 | 英文全称 | 常见含义 |
| --- | --- | --- |
| `T` | Type | 最通用，代表任意一种类 |
| `E` | Element / Exception | 集合里的“元素”，或异常类型 |
| `K` | Key | 键值对里的“键” |
| `V` | Value | 键值对里的“值” |
| `S` / `U` | Subtype / 第二类型 | 子类型，或多个泛型参数时的辅助字母 |

### 2. 类型参数在泛型类里的三处位置

在泛型类 `Box<T>` 内部，`T` 可以像 `String`、`Car` 一样当作普通类型名来用，具体可以出现在 **三处非静态位置**（课件重点）：

1. **非静态成员属性的类型**（字段用来存什么类型的数据）
2. **非静态方法的形参类型**（包括构造方法和普通成员方法）
3. **非静态成员方法的返回值类型**

```java
public class Box<T> {
    private T data;              // 1. 非静态成员属性的类型

    public Box(T data) {         // 2. 构造方法（及非静态方法）的形参类型
        this.data = data;
    }

    public T getData() {         // 3. 非静态成员方法的返回值类型
        return data;
    }
}
```

> 为什么三处都强调“非静态”？回想 Class 14 的 `static`：`static` 成员属于**类本身**，在还没有 `new` 对象、还没有告诉它 `T` 是什么之前就已经加载好了，所以静态字段和普通静态方法里**不能**直接使用类上的 `T`。另外留意：课堂 PPT 该页把 `Box` 的构造器笔误写成了 `Car`、返回值大写成了 `Data`，自己写代码时构造器必须与类名完全一致，变量名也要区分大小写。

### 3. 创建泛型类对象（以及基础必补：包装类）

```java
// 完整写法
类名<实际类型> 对象名 = new 类名<实际类型>(实参);

// 菱形语法（JDK 7+ 推荐）：右边尖括号里可以省略，编译器看左边自动推断
类名<实际类型> 对象名 = new 类名<>(实参);
```

```java
Box<String> a = new Box<>("苹果");
Box<Integer> b = new Box<Integer>(180);
Box<?> c = new Box<>("任意盒子");
```

这里有 **三条必须记牢的规则**（也是初学者最容易懵的地方）：

**规则 1：尖括号里的实际类型不能是基本数据类型，必须是类或接口类型。**

写 `Box<int>` 会直接编译报错！因为 Java 泛型底层只支持引用类型（对象）。想装整数、小数、字符时，必须使用 Java 为 8 种基本类型准备好的**包装类**（它们都是真正的类，位于 `java.lang` 包，无需 `import`）：

| 基本类型 | byte | short | int | long | float | double | char | boolean |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 包装类 | `Byte` | `Short` | `Integer` | `Long` | `Float` | `Double` | `Character` | `Boolean` |

> 初学者常问：“既然不能用 `int`，为什么 `new Box<Integer>(180)` 里可以直接写整数 `180`？”因为 Java 支持**自动装箱/拆箱**：当你把 `180` 传给需要 `Integer` 的位置时，编译器会自动把它包装成 `Integer` 对象；取出后赋给 `int x = b.getData();` 时，又会自动拆回 `int`。所以只有**尖括号里**必须写包装类，传值取值照常写数字即可。

**规则 2：`<实际类型>` 也可以不写（退化为 `Object`）。**

如果写成 `Box b = new Box("苹果");`，编译器为了兼容老代码会放行，但此时 `Box` 里所有的 `T` 都当成 `Object` 对待——相当于退回了第二代的“万能盒”，失去了编译期类型检查，日常写代码尽量写清 `<实际类型>`。

**规则 3：通配符 `?` 只能写在声明类型里，不能用来 `new`。**

课件提到可以用 `?` 表示任意类，但按 Java 编译器规范，`new` 对象时必须明确造的是哪种具体盒子，不能写 `new Box<?>(...)`（会编译报错）；正确写法是把 `?` 写在左边变量或形参类型上，右边用具体类型或 `<>` 创建，如 `Box<?> c = new Box<>("ABC");`。

---

## 三、声明与实现泛型接口（6.7.3）

先一句话回顾基础：**接口（`interface`）**用来规定“应该具备哪些方法（能力清单）”，类用 **`implements`** 关键字去实现它并补全方法体。当接口里方法要处理的数据类型还不确定时，就可以把接口也做成泛型的。

### 1. 定义形式

```java
interface 接口名<类型参数表列> {
    // 抽象方法声明
}
```

### 2. 实现泛型接口的两种写法

就像接手一张“空白订单模板”，实现类有两种选择：**继续留白**，或者**当场定死具体类型**。

**方式一：实现类继续保留泛型（课件重点）**

实现类还不想决定具体类型，就在类名后面声明同样的 `<T>`，把决定权继续留给将来 `new` 对象的人：

```java
interface Chair<T> {                // 定义泛型接口：能获取一把 T 类型的椅子
    T getChair();
}

class Car<T> implements Chair<T> {  // 泛型类实现泛型接口：两边都带 <T>
    private T ch;

    public Car(T ch) {
        this.ch = ch;
    }

    @Override
    public T getChair() {
        return ch;
    }
}
```

使用时再指定类型（注意先分号结束 `new` 语句，再调用方法；课件右侧把 `a.getChair();` 误写进了花括号里）：

```java
Car<Integer> a = new Car<>(4);
System.out.println("座椅数量：" + a.getChair()); // 输出：座椅数量：4
```

**方式二：实现类当场指定具体类型（常用补充）**

如果实现类明确自己只处理 `String`，那就直接在 `implements` 后填好 `String`，实现类本身不再需要带 `<T>`：

```java
class LeatherChair implements Chair<String> {
    @Override
    public String getChair() {
        return "真皮座椅";
    }
}
// LeatherChair lc = new LeatherChair(); // new 时也不用再写尖括号
```

---

## 四、声明泛型方法（6.7.4）

有时候整个类本身不需要泛型，只是**某一个工具方法**（比如打印任意数据、交换数组里任意两个元素）想通用不同类型。这时候直接把该方法写成**泛型方法**即可。

### 1. 标准语法形式（补齐课堂缺记定义）

```java
[访问修饰符] [static] <类型参数表列> 返回值类型 方法名(形参列表) {
    // 方法体
}
```

最关键的特征：**`<T>` 必须紧贴在返回值类型的前面**。它的作用是提前向编译器“报备”：“后面参数或返回值里出现的 `T` 不是某个叫 `T` 的类，而是这个方法自己声明的类型参数。”

### 2. 普通方法 vs 泛型方法

```java
public class Printer {
    // 普通方法：写死了 int，传字符串或小数都会报错
    public void putInt(int s) {
        System.out.println(s);
    }

    // 泛型方法：在返回值 void 前声明 <T>，什么引用类型都能收
    public <T> void put(T s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        Printer a = new Printer();
        a.put(111);       // 自动装箱为 Integer，编译器自动推断 T = Integer
        a.put("红色");     // 编译器自动推断 T = String
        a.put(-123.123);  // 自动装箱为 Double，编译器自动推断 T = Double
    }
}
```

调用泛型方法时**不需要手写尖括号**，编译器会根据你传进去的实参自动猜出 `T` 是什么类型（课件右侧定义名是 `put`、调用处笔误写成了 `run`，上机时保持方法名一致即可）。

#### 必考辨析：泛型方法 vs 泛型类里的普通方法

很多同学看到 `Box<T>` 里的 `public T getData()` 里出现了 `T`，就以为它也是泛型方法——其实**不是**！

| 对比项 | 泛型类普通方法（Box<T> 的 getData） | 真正的泛型方法（<T> void put(T s)） |
| --- | --- | --- |
| 语法标志 | 返回值前**没有** `<T>` | 修饰符与返回值之间**单独声明** `<T>` |
| 类型来源 | 借用所属类上的 `T` | 方法自己独立声明，与类无关 |
| 写在普通类 | 不能（类没有 `<T>` 就报错） | **可以**，普通类和泛型类都能写 |
| 支持 static | 不能直接用类的 `T` | **可以**（如 `public static <T> void show(T x)`） |

---

## 五、泛型参数的限定（6.7.5）

前面写的 `Box<T>` 什么都能装——整数、字符串、甚至一只狗都能放进去。现在思考一个实际问题：如果我们要写一个计算器类 `Calculator<T>`，**只允许它处理数字（`Integer`、`Double` 等），不希望别人把 `String` 塞进来**，怎么办？

答案是给类型参数加一把“门槛锁”——用 **`extends` 限定上界**：

```java
// 语法形式：<类型形式参数 extends 父类>
class Calculator<T extends Number> {
    private T num;

    public Calculator(T num) {
        this.num = num;
    }

    public double toDouble() {
        // 因为锁死了 T 一定是 Number 或其子类，这里可以直接调用 Number 的方法
        return num.doubleValue();
    }
}
```

加了 `<T extends Number>` 之后，一举两得：

1. **拦住非法类型**：`Calculator<Integer>`、`Calculator<Double>` 都能通过，而 `Calculator<String>` 在编译期直接红线报错。
2. **解锁父类方法**：如果不加限定，编译器只把 `T` 当成 `Object`，你连 `doubleValue()` 都调不到；限定为 `Number` 后，类里面就能放心调用 `Number` 的所有公共方法。

> 语法提醒：（1）关键字必须拼成 **`extends`**（带 `s`；课件该页正文误漏成了 `extend`）。（2）在泛型限定里，**无论限定父类还是限定接口，都统一用 `extends`**，不能写 `implements`；同时限定多个边界时用 `&` 连接，且类必须放最前面，如 `<T extends Number & Comparable<T>>`。

---

## 六、泛型不变性与通配符（6.7.6）

### 1. 核心痛点：为什么 `Box<Integer>` 不是 `Box<Number>` 的子类？

在 Class 16 我们学过：`Integer` 是 `Number` 的子类，所以把一个 `Integer` 交给 `Number` 变量完全没问题（向上转型）。于是很多人自然会以为：`Box<Integer>` 应该也能传给 `Box<Number>` 吧？

```java
void show(Box<Number> b) {
    System.out.println(b.getData());
}

Box<Integer> bi = new Box<>(100);
// show(bi);  // ✗ 编译报错！Box<Integer> 不是 Box<Number> 的子类
```

**为什么 Java 要禁止这件事？用 4 步推演一眼看懂：**

1. 假设你手里有一个专门装整数的盒子 `Box<Integer> bi = new Box<>(100);`。
2. **假如** Java 允许 `Box<Number> bn = bi;`（两者指向堆里的**同一个盒子**）。
3. 接下来别人通过 `bn.setData(3.14);` 往里放一个小数——对 `Box<Number>` 来说这完全合法，因为 `Double` 也是 `Number`。
4. 最后你回到原来的 `bi`，执行 `Integer x = bi.getData();`——盒子里竟然躺着一个 `3.14`，类型安全彻底崩塌！

正因为装整数的盒子如果被当成“装任意数字的盒子”，就有可能被塞进小数，所以 Java 规定：**泛型不具备继承性（不变性）**——`Integer` 继承 `Number`，但 `Box<Integer>` 和 `Box<Number>` 是两个互不相干的类型。

### 2. 无界通配符 `<?>`：我什么盒子都接

那如果我只是想写一个 `show` 方法，能打印 `Box<Integer>`，也能打印 `Box<String>`、`Box<Double>`，怎么办？Java 引入了**通配符 `?`**，读作“**某种未知的类型**”：

```java
void show(Box<?> b) {
    Object data = b.getData(); // 不知道里面具体是什么类型，统一按 Object 接住
    System.out.println(data);
}
```

现在 `show(bi)`、`show(new Box<>("苹果"))` 都能传入了。但它也有局限：范围太宽（挡不住 `Box<String>`），也没法直接当作 `Number` 做数学计算。于是我们需要给 `?` 加上边界。

### 3. 一张图看懂上界与下界的范围

先把 `Object`、`Number`、`Integer`、`Double` 的继承关系画出来，后面两条规则瞬间就通了：

```text
          Object        ▲  <? super Integer>  下界通配符：往上找父类
            ↑           │  可接收：List<Integer> / List<Number> / List<Object>
          Number        │  ▼  <? extends Number> 上界通配符：往下找子类
         /      \       │  │  可接收：List<Number> / List<Integer> / List<Double>
     Integer   Double   ┴  ▼
```

### 4. 上界通配符 `C<? extends T>`——只出不进的“水果摊”

- **含义**：`?` 必须是 **`T` 本身、`T` 的子类，或实现了 `T` 接口的类**（只能写一个上界）。
- **直觉理解（水果摊比喻）**：摊位牌子上写着“只卖水果（`? extends Fruit`）”，实际里面可能专卖苹果，也可能专卖香蕉。
  - **往外拿（读安全）**：不管它是苹果摊还是香蕉摊，你伸手拿一个出来的**肯定都是水果**（可以放心用 `Fruit` 接住）。
  - **往里放（禁止写）**：你想往里塞一个苹果？不行！万一它其实是个“纯香蕉摊”，塞苹果进去就把摊位搞乱了。所以编译器直接禁止写入（`null` 除外）。
- **定位**：只出不进，适合**读取数据（生产者 Producer）**。

```java
import java.util.Arrays;
import java.util.List;

public class UpperBoundedWildcard {
    // 接收装 Number 或其子类（Integer、Double 等）的 List，用来求和
    public static double sumOfList(List<? extends Number> list) {
        double sum = 0.0;
        for (Number n : list) {
            sum += n.doubleValue(); // ✓ 安全读取：拿出来的一定是 Number
        }
        // list.add(10);            // ✗ 编译报错：不知实际是 Integer 还是 Double 列表
        return sum;
    }

    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 3);
        List<Double> doubleList = Arrays.asList(1.1, 2.2);
        System.out.println("整数和: " + sumOfList(intList));    // 输出：6.0
        System.out.println("小数和: " + sumOfList(doubleList)); // 实测：3.3000000000000003
    }
}
```

> 小贴士：课件注释把 `1.1 + 2.2` 简写为 `3.3`；实际运行输出 `3.3000000000000003` 是二进制浮点数精度问题，与泛型逻辑无关。

### 5. 下界通配符 `C<? super T>`——只进不出的“回收站”

- **含义**：`?` 必须是 **`T` 本身或 `T` 的超类/父类**（同样只能写一个下界）。
- **直觉理解（回收站比喻）**：门外放着一个箱子，标签至少能装整数（`? super Integer`）——它可能是 `Integer` 箱、`Number` 箱，甚至什么都收的 `Object` 回收箱。
  - **往里放（写安全）**：你往里扔一个 `Integer`（或它的子类），不管箱子是上述哪一种，**肯定都装得下**！
  - **往外拿（只能当 `Object` 读）**：你闭着眼伸手摸一个东西出来，万一那是 `Object` 回收箱，摸出来的可能根本不是数字，所以编译器只敢保证你摸出的是 `Object`。
- **定位**：只进不出，适合**写入数据（消费者 Consumer）**。

```java
import java.util.ArrayList;
import java.util.List;

public class LowerBoundedWildcard {
    // 接收装 Integer 或其父类（Number、Object）的 List，往里批量写入整数
    public static void addNumbers(List<? super Integer> list) {
        for (int i = 1; i <= 3; i++) {
            list.add(i);           // ✓ 安全写入：这几种父类容器都装得下 Integer
        }
        // Integer n = list.get(0);// ✗ 编译报错：上界未知，不能直接按 Integer 读
        Object first = list.get(0);// ✓ 读取只能放到 Object 变量里
    }

    public static void main(String[] args) {
        List<Integer> intList = new ArrayList<>();
        List<Number> numList = new ArrayList<>();
        List<Object> objList = new ArrayList<>();
        addNumbers(intList);       // ✓
        addNumbers(numList);       // ✓
        addNumbers(objList);       // ✓
        System.out.println("写入后的 intList: " + intList); // 输出：[1, 2, 3]
    }
}
```

### 6. 三种通配符速查与 PECS 终极口诀

| 写法 | 允许传入的容器 | 往里写（add / set） | 往外读（get） | 适用角色 |
| --- | --- | --- | --- | --- |
| `C<?>` | 任意泛型容器 | 禁止（除 `null`） | 只能当 `Object` | 只关心结构（如打印、求长度） |
| `C<? extends T>` | `T` 及其子类 / 实现类 | 禁止（除 `null`） | 直接当 `T` 读 | 生产者（Producer）：只读 |
| `C<? super T>` | `T` 及其父类 / 超类 | 可写 `T` 及子类 | 只能当 `Object` | 消费者（Consumer）：只写 |

#### 终极记忆口诀：PECS 原则（Producer Extends, Consumer Super）

- **Producer Extends（生产者用 `extends`）**：容器负责**向外提供数据（你只读）**，用 `<? extends T>`。
- **Consumer Super（消费者用 `super`）**：容器负责**接收你塞进去的数据（你只写）**，用 `<? super T>`。
- **既要读又要写**：别用通配符，直接使用具体的泛型 `<T>`（或具体类型如 `List<Integer>`）。

---

## 常见错误

1. **尖括号里写基本类型**：`Box<int>` 编译报错，必须改用包装类 `Box<Integer>`。
2. **用通配符直接创建对象**：`new Box<?>(...)` 编译报错，`?` 只能用在引用类型或方法形参声明里。
3. **在 `static` 成员里直接用类的 `T`**：静态成员不依赖对象存在；静态泛型方法必须在返回值前自己写 `<T>`。
4. **把泛型类里的普通方法当成泛型方法**：`Box<T>` 中的 `T getData()` 不是泛型方法；返回值前单独声明 `<T>` 的才是。
5. **误以为 `List<Integer>` 是 `List<Number>` 的子类**：泛型具有不变性；只读用 `List<? extends Number>`，只写用 `List<? super Integer>`。
6. **构造方法常见手误（衔接 Class 12–13）**：写带参构造时把形参名写错、写成自己赋给自己（如 `this.a = a;` 而形参叫 `anInt`），字段会一直是 `null`；定义了带参构造后，编译器不再赠送默认无参构造，`new ObjBox<>()` 会报错，需要传参或手补无参构造。

## 本课小结

泛型把数据类型抽成参数，用一套模板同时做到**代码复用**和**编译期类型安全**；泛型类、泛型接口、泛型方法分别在类、接口、方法层面参数化类型；`<T extends 上界>` 用来收窄允许的类型并解锁父类方法；面对泛型不变性，记住**水果摊（`? extends T` 只读）**、**回收站（`? super T` 只写）**和 **PECS 口诀**，就能准确选出合适的通配符。

## 自测题

1. 同样能装各种类型，为什么说 `Box<T>` 比 `Object` 万能盒更安全、更方便？
2. `Box<Integer> b = new Box<>(180);` 中，为什么尖括号不能写 `int`，而括号里能直接写 `180`？
3. 在泛型类 `Box<T>` 中，`T` 能用在哪些位置？为什么不能用来声明 `static` 字段？
4. 比较 `Box<T>` 中的 `public T getData()` 与普通类中的 `public static <T> void print(T x)`，哪一个是真正的泛型方法？
5. 为什么 `List<Integer>` 不能传给形参 `List<Number>`？如果你要写一个“只求和”的方法和一个“只添加整数”的方法，形参分别该怎么写？
