package com.future.csamsserver.test;

import java.time.LocalDateTime;

/**
 * @description
 * @dateTime 2025:03:26 17:17
 * @user Jenming
 */
public class ThymeleafApi {
    public static void main(String[] args) {
        /*
        * <!-- 错误示例：不能直接写 Java 代码 -->
<div th:text="${System.out.println('Hello')}">...</div>

* 以下 ok
* <p>当前日期：[[${currentDate}]]</p>
<p>月份和星期：[[${monthAndDay}]]</p>
  <span th:text="'今天是：' + ${#dates.format(now, 'yyyy-MM-dd')}"></span>
    <div th:if="${variable} == '条件'">显示内容</div>
    <!-- 格式化日期 -->
  [[${#dates.format(now, 'yyyy年MM月dd日')}]]
  <!-- 直接拼接 -->
<p th:text="${fullDate + '#' + shortDate}">2025-03-26#03-26</p>

<!-- 或使用文本内插语法 -->
<p>[[${fullDate + '#' + shortDate}]]</p>

<p>asd[[${var}]]</p>
<span th:text="'前' + ${var} + '后'"></span>

                                    th:text="${#strings.abbreviate(item.fileName, 22)}"中文一个字符，会自动加上...

        * */
        /*


        1. 创建工具类
java
深色版本
public class StringUtils {
    public static String truncateWithEllipsis(String str, int maxLength) {
        if (str == == null || str.isEmpty()) {
            return "";
        }
        if (str.length() <= maxLength) {
            return str;
        }
        return str.substring(0, maxLength) + "...";
    }
}
2. 在 Thymeleaf 中使用
html
深色版本
<!-- 需要将工具类注册为 Thymeleaf 的方言 -->
<span
    th:text="${T(com.example.util.StringUtils).truncateWithEllipsis(item.title, 30)}"
></span>

        * */

        final String string = (LocalDateTime.now().getMonth() + "-" + LocalDateTime.now().getDayOfWeek());
        System.out.println("string = " + string);
//        string = MARCH-WEDNESDAY

    }
}
