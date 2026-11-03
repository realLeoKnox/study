# Class 9：数组输入与命令行参数

> 前两课学习了数组的创建和遍历。本课关注“数据从哪里来”：可以在程序运行后通过 `Scanner` 逐项输入，也可以在启动程序时通过命令行参数一次传入。

## 本课目标

- 会用 `Scanner` 把多项输入保存到数组。
- 理解 `main(String[] args)` 中 `args` 的含义。
- 会编译、运行带命令行参数的 Java 程序。
- 能处理参数缺失、数字格式错误和输入方式混用问题。

## 4.16 数组知识回顾

- 数组保存同类型元素，创建后长度固定。
- 动态初始化使用 `new`，元素获得默认值。
- 静态初始化直接列出元素。
- 下标范围是 `0` 到 `length - 1`。
- 普通 `for` 可使用下标，增强 `for` 适合依次读取。
- 数组变量保存引用，数组对象在运行时创建。

## 4.17 使用 Scanner 输入数组

### 4.17.1 输入固定数量的字符串

```java
import java.util.Scanner;

public class StringArrayInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words = new String[5];

        for (int i = 0; i < words.length; i++) {
            System.out.print("请输入第 " + (i + 1) + " 个字符串：");
            words[i] = scanner.nextLine();
        }

        System.out.println("输入结果：");
        for (String word : words) {
            System.out.println(word);
        }

        scanner.close();
    }
}
```

数组下标从 0 开始，但面向用户的编号通常从 1 开始，所以提示中使用 `i + 1`。

### 4.17.2 输入数量由用户决定

```java
import java.util.Scanner;

public class ScoreInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入成绩数量：");
        int count = scanner.nextInt();

        if (count <= 0) {
            System.out.println("数量必须大于 0");
            scanner.close();
            return;
        }

        double[] scores = new double[count];
        double sum = 0;
        for (int i = 0; i < scores.length; i++) {
            System.out.print("请输入第 " + (i + 1) + " 个成绩：");
            scores[i] = scanner.nextDouble();
            sum += scores[i];
        }

        System.out.printf("平均分：%.2f%n", sum / scores.length);
        scanner.close();
    }
}
```

必须先确认数量有效，再创建数组和计算平均值。

## 4.18 nextLine 的换行问题

`nextInt()`、`nextDouble()` 等方法读取数值后，会把行尾换行符留在输入缓冲区。紧接着调用 `nextLine()` 时，它可能立即读取这个空行。

```java
int age = scanner.nextInt();
scanner.nextLine();          // 消耗数值后的换行符
String name = scanner.nextLine();
```

若全部数据都按整行读取，也可以先用 `nextLine()` 得到字符串，再使用 `Integer.parseInt` 或 `Double.parseDouble` 转换。

## 4.19 命令行参数

### 4.19.1 args 是什么

```java
public static void main(String[] args)
```

`args` 是一个 `String[]`。启动 Java 程序时，类名后面的每一段参数会依次成为数组元素。

```bash
javac CommandLineDemo.java
java CommandLineDemo apple banana cherry
```

参数映射：

| 命令内容 | 数组元素 |
|---|---|
| `apple` | `args[0]` |
| `banana` | `args[1]` |
| `cherry` | `args[2]` |

程序名和 `java` 命令本身不会放入 `args`。

### 4.19.2 遍历命令行参数

```java
public class CommandLineDemo {
    public static void main(String[] args) {
        System.out.println("参数个数：" + args.length);
        for (int i = 0; i < args.length; i++) {
            System.out.println("args[" + i + "] = " + args[i]);
        }
    }
}
```

如果没有传入参数，`args.length` 为 0。Java 启动器正常调用 `main` 时，`args` 通常是长度可能为 0 的数组，而不是 `null`。

### 4.19.3 包含空格的参数

命令行通常按空格分隔参数。要把带空格的内容作为一个参数，需要使用终端对应的引号规则。

```bash
java CommandLineDemo "hello world" Java
```

此时 `args[0]` 是 `hello world`，`args[1]` 是 `Java`。

## 4.20 把字符串参数转换为数字

命令行参数全部是字符串。参与数值计算前需要转换。

```java
int number = Integer.parseInt(args[0]);
double price = Double.parseDouble(args[1]);
```

转换失败会抛出 `NumberFormatException`，参数数量不足会导致 `ArrayIndexOutOfBoundsException`。应先检查长度，再捕获格式错误。

## 4.21 综合示例：命令行求和

```java
public class CommandLineSum {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("用法：java CommandLineSum 数字1 数字2 ...");
            return;
        }

        double sum = 0;
        try {
            for (String arg : args) {
                sum += Double.parseDouble(arg);
            }
            System.out.println("总和：" + sum);
        } catch (NumberFormatException exception) {
            System.out.println("所有参数都必须是有效数字");
        }
    }
}
```

示例运行：

```bash
javac CommandLineSum.java
java CommandLineSum 12.5 8 4.5
```

```text
总和：25.0
```

## 4.22 Scanner 与命令行参数对比

| 对比项 | `Scanner` | 命令行参数 |
|---|---|---|
| 输入时机 | 程序启动后交互输入 | 启动程序时一次给出 |
| 数据形式 | 可直接读取多种基本类型 | 全部先是 `String` |
| 适用场景 | 交互练习、菜单、逐项输入 | 脚本调用、配置、批处理 |
| 常见问题 | 换行残留、输入类型不匹配 | 参数不足、格式转换失败 |

## 常见错误

- 直接访问 `args[0]`，没有先判断 `args.length`。
- 认为命令行中的 `10` 自动就是 `int`。
- 忘记带空格的参数需要引号。
- `nextInt()` 后立即调用 `nextLine()`，读到空字符串。
- 用户输入的数组长度小于或等于 0，仍继续创建和计算。

## 本课小结

- `Scanner` 适合程序运行后的交互式输入。
- `main` 的 `args` 是字符串数组，保存启动程序时传入的参数。
- 命令行数字必须显式转换，并处理参数缺失与格式错误。
- 数组让一组输入得到统一保存和遍历；第十课将把重复计算封装成可调用的方法。

## 自测题

1. `java Demo a "b c"` 会得到几个命令行参数？
2. 为什么读取数字后再读整行文本时常要额外调用一次 `nextLine()`？
3. 编写程序：从命令行接收若干整数，输出最大值。
4. 把 `ScoreInput` 改为同时输出最高分和最低分。
