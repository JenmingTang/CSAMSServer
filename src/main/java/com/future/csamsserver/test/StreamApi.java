package com.future.csamsserver.test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description
 * @dateTime 2025:03:26 16:48
 * @user Jenming
 */
public class StreamApi {
    /*
    * 1. filter
作用：筛选符合条件的元素。
示例：stream.filter(x -> x > 10)

2. map
作用：将元素映射为其他形式的元素（如类型转换、提取字段）。
示例：stream.map(Person::getName)

3. flatMap
作用：将多个流合并为一个流（处理嵌套集合）。
示例：stream.flatMap(List::stream)

4. distinct
作用：去除重复元素（基于 equals 判定）。
示例：stream.distinct()

5. sorted
作用：对流进行排序（自然排序或自定义 Comparator）。
示例：stream.sorted()

6. limit
作用：限制流中元素的最大数量。
示例：stream.limit(10)

7. skip
作用：跳过前 N 个元素，返回剩余元素。
示例：stream.skip(5)

8. peek
作用：调试用，对元素执行操作但不改变流（如打印日志）。
示例：stream.peek(System.out::println)

9. forEach
作用：遍历流中的每个元素执行操作。
示例：stream.forEach(System.out::println)

10. collect
作用：将流收集到集合（如 List、Map）或其他容器。
示例：stream.collect(Collectors.toList())

11. reduce
作用：聚合操作（如求和、求积）。
示例：stream.reduce(0, Integer::sum)

12. anyMatch
作用：检查是否至少一个元素匹配条件。
示例：stream.anyMatch(x -> x > 5)

13. allMatch
作用：检查所有元素是否匹配条件。
示例：stream.allMatch(x -> x > 0)

14. noneMatch
作用：检查没有元素匹配条件。
示例：stream.noneMatch(x -> x < 0)

15. findFirst
作用：获取流中的第一个元素（非并行流保证首个出现的元素）。
示例：stream.findFirst()

16. findAny
作用：获取流中的任意元素（适用于并行流）。
示例：stream.findAny()

17. max/min
作用：获取流中的最大值或最小值。
示例：stream.max(Comparator.naturalOrder())

18. count
作用：统计流中元素的数量。
示例：stream.count()

这些操作可以组合使用，形成强大的数据处理流水线。例如：
    * */
    public static void main(String[] args) {
        final List<String> list = List.of("b", "a", "abcd", "bcda");
        List<String> result = list.stream()
                .filter(x -> x.length() > 3)
                .map(String::toUpperCase)
                .sorted().peek(System.out::println)
//                .collect(Collectors.toList());
                .toList();
        List<String> names = Arrays.asList("A", "B", "C");
        String result2 = names.stream()
                .reduce("", (acc, name) -> acc + name); // 初始值为 "", 累加每个元素
        System.out.println(result2); // 输出 "ABC"
        System.err.println("==============");
        flatMap();
        /*
        * [1, 2, 3, 4, 5, 6]
==============
* 异步的流，更快？
        * */
    }

    private static void flatMap() {
        List<List<Integer>> nestedList = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4),
                Arrays.asList(5, 6)
        );

        List<Integer> flatList = nestedList.stream()
                .flatMap(List::stream) // 将每个子列表转为流并合并
                .collect(Collectors.toList());
        System.out.println(flatList); // 输出 [1, 2, 3, 4, 5, 6]

    }
}
