package com.future.csamsserver.test;

import cn.hutool.core.util.RandomUtil;

import java.util.Random;
import java.util.Scanner;

/**
 * @description
 * @dateTime 2025:03:21 01:47
 * @user Jenming
 */
public class Main2 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n;
        n = Integer.parseInt(in.nextLine().trim());
        int[] bag = new int[n];
        for (int i = 0; i < n; i++) {
            bag[i] = in.nextInt();
        }
        final Random random = new Random();
        random.nextInt(1);
        for (int i = 0; i < n; i++) {
        }
        int x =0;
        int sum = 0;
        for (int i : bag) {
            sum += i;
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            int max = bag[0];
            for (int j = 0; j < n; j++) {
                if (bag[j] > max) {
                    max = bag[j];
                }
                if (n == bag.length - 1) {

                }
            }
        }
    }
}
