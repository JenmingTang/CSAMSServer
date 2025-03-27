package com.future.csamsserver.test;

import java.util.Scanner;

/**
 * @description
 * @dateTime 2025:03:21 02:00
 * @user Jenming
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n;
        n = Integer.parseInt(in.nextLine().trim());
        if (n < 4) System.out.println(n);
        if (n >= 4) System.out.println(2 + n - 1);
    }
}
