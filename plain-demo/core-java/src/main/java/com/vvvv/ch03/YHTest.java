//杨辉三角

package com.vvvv.ch03;

public class YHTest {
    public static void main(String[] args) {
        int[][] array = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0 || i == j)    //如果是第一列或者对角线
                {
                    array[i][j] = 1;
                } else {
                    array[i][j] = array[i - 1][j - 1] + array[i - 1][j];
                }
                System.out.print(array[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
