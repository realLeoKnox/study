# Class 8：Java 二维数组

> 一维数组保存一列数据；二维数组可表示表格、矩阵或分组数据。在 Java 中，二维数组本质上是“数组的数组”，每一行都是独立的数组对象。

## 本课目标

- 掌握二维数组的声明、创建和静态初始化。
- 会用双层循环访问、修改和统计二维数组。
- 理解规则二维数组与不规则数组的区别。
- 能完成最大值、行平均值和条件替换等综合操作。

## 4.9 二维数组的结构

```java
int[][] matrix;
```

推荐把两组方括号都写在类型之后。下面写法也合法，但可读性较弱。

```java
int matrix2[][];
int[] matrix3[];
```

`int[][]` 表示“元素类型为 `int[]` 的数组”。因此，`matrix[i]` 是第 `i` 行数组，`matrix[i][j]` 才是具体的 `int` 元素。

## 4.10 创建与初始化

### 4.10.1 规则二维数组

```java
int[][] matrix = new int[3][4];
```

这会创建 3 行 4 列的结构，共 12 个 `int` 元素，每个元素默认值为 0。

```text
matrix
  ├── matrix[0] -> [0, 0, 0, 0]
  ├── matrix[1] -> [0, 0, 0, 0]
  └── matrix[2] -> [0, 0, 0, 0]
```

### 4.10.2 静态初始化

```java
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};
```

也可以写为：

```java
int[][] matrix = new int[][]{
    {1, 2},
    {3, 4}
};
```

### 4.10.3 只创建行数组

```java
int[][] data = new int[3][];
```

此时只创建了外层数组，`data[0]`、`data[1]`、`data[2]` 的初始值都是 `null`。访问 `data[0][0]` 前必须先创建第 0 行。

```java
data[0] = new int[2];
data[1] = new int[4];
data[2] = new int[3];
```

不能只指定列数而省略行数：

```java
int[][] wrong = new int[][3]; // 错误
```

## 4.11 不规则二维数组

Java 每一行都是独立数组，因此各行长度可以不同。

```java
int[][] ragged = {
    {1, 2},
    {3, 4, 5, 6},
    {7}
};
```

```text
ragged[0] -> [1, 2]
ragged[1] -> [3, 4, 5, 6]
ragged[2] -> [7]
```

外层长度是 `ragged.length`，第 `i` 行长度是 `ragged[i].length`。遍历时不能假设每行都与第一行等长。

## 4.12 元素访问和修改

```java
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6}
};

System.out.println(matrix.length);       // 2 行
System.out.println(matrix[0].length);    // 第 0 行有 3 列
System.out.println(matrix[1][2]);        // 6
matrix[0][1] = 20;
```

常见异常：

- 行下标或列下标越界：`ArrayIndexOutOfBoundsException`。
- 某一行仍为 `null` 就访问元素：`NullPointerException`。

## 4.13 遍历二维数组

### 4.13.1 使用下标遍历

```java
for (int i = 0; i < matrix.length; i++) {
    for (int j = 0; j < matrix[i].length; j++) {
        System.out.print(matrix[i][j] + " ");
    }
    System.out.println();
}
```

外层循环控制行，内层循环控制当前行的列。

### 4.13.2 使用增强 for

```java
for (int[] row : matrix) {
    for (int value : row) {
        System.out.print(value + " ");
    }
    System.out.println();
}
```

增强 `for` 更适合读取；需要修改某个位置、保存行列下标时，使用普通 `for`。

## 4.14 综合练习

### 4.14.1 求一维数组最大值

```java
int[] values = {3, 7, 1, 9, 4};
int max = values[0];
for (int i = 1; i < values.length; i++) {
    if (values[i] > max) {
        max = values[i];
    }
}
System.out.println("最大值：" + max);
```

最大值初始值应取数组中的元素，不能随意取 0，否则全为负数的数组会得到错误结果。

### 4.14.2 计算每行平均值并替换元素

```java
public class MatrixStatistics {
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        for (int i = 0; i < matrix.length; i++) {
            int sum = 0;
            for (int value : matrix[i]) {
                sum += value;
            }
            double average = (double) sum / matrix[i].length;
            System.out.printf("第 %d 行平均值：%.2f%n", i + 1, average);
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > 5) {
                    matrix[i][j] = 0;
                }
            }
        }

        System.out.println("修改后的矩阵：");
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
```

如果某一行长度可能为 0，计算平均值前要先判断 `matrix[i].length > 0`。

## 4.15 二维数组的引用关系

```java
int[][] first = {{1, 2}, {3, 4}};
int[][] second = first;
second[0][0] = 99;
System.out.println(first[0][0]); // 99
```

与一维数组相同，赋值复制的是引用。若只复制外层数组，内层行数组仍可能共享。初学阶段要明确“复制引用”和“复制所有元素”不是一回事。

## 常见错误

- 写 `new int[][]` 却没有指定长度或初始化器。
- `new int[3][]` 后直接访问 `array[0][0]`。
- 内层循环始终使用 `matrix[0].length`，导致不规则数组出错。
- 把最大值初始值固定为 0，忽略全为负数的情况。
- 计算平均值时使用整数除法，或没有处理空行。

## 本课小结

- 二维数组本质上是数组的数组，每一行是独立对象。
- `matrix.length` 是行数，`matrix[i].length` 是第 `i` 行列数。
- Java 支持各行长度不同的不规则二维数组。
- 双层循环可完成遍历、统计和修改。
- 下一课将把数组与键盘输入、命令行参数结合，学习程序如何接收一组外部数据。

## 自测题

1. `new int[3][]` 创建了哪些对象？
2. 为什么遍历不规则数组要使用 `matrix[i].length`？
3. 编写程序：求二维数组所有元素总和。
4. 编写程序：找出最大元素，并输出它的行下标和列下标。
