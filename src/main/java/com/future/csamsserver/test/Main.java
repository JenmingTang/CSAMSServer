package com.future.csamsserver.test;

/**
 * @description
 * @dateTime 2025:03:21 01:29
 * @user Jenming
 */

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Solution {

    /* Write Code Here */
    public long matrixSum(int n, int[][] operations, int operations_rows) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < operations_rows; i++) {
            int index = operations[i][1];
            int val = operations[i][2];
            if (operations[i][0] == 0) {
                for (int j = 0; j < n; j++) {
                    matrix[index][j] = val;
                }
            }
            if (operations[i][0] == 1) {

                for (int j = 0; j < n; j++) {
                    matrix[j][index] = val;
                }
            }

        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += matrix[i][j];
            }
        }
        return sum;

    }
}

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long res;

        int n;
        n = Integer.parseInt(in.nextLine().trim());

        int operations_rows = 0;
        int operations_cols = 0;
        operations_rows = in.nextInt();
        operations_cols = in.nextInt();

        int[][] operations = new int[operations_rows][operations_cols];
        for (int operations_i = 0; operations_i < operations_rows; operations_i++) {
            for (int operations_j = 0; operations_j < operations_cols; operations_j++) {
                operations[operations_i][operations_j] = in.nextInt();
            }
        }

        if (in.hasNextLine()) {
            in.nextLine();
        }


        res = new Solution().matrixSum(n, operations, operations_rows);
        System.out.println(String.valueOf(res));

    }
}
