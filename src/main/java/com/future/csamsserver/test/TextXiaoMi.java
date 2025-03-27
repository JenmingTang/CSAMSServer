package com.future.csamsserver.test;

import java.util.Scanner;
import java.util.Stack;

/**
 * @description
 * @dateTime 2025:03:21 01:24
 * @user Jenming
 */
public class TextXiaoMi {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {

            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        StringBuilder result = new StringBuilder();
        for (char c : stack) {
            result.append(c);
        }
        System.out.println(result);
    }
}
