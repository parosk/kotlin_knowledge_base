package foobar;

import java.util.Arrays;


public class Broadcast {
    public static int[] solution(int[] l, int t) {

        int sum = 0;
        int offset = 0;
        int[] sumList = new int[l.length];

        for (int i = 0; i < l.length; i++) {
            int value = l[i];
            sum += value;
            sumList[i] = sum;
        }

        for (int i = 0; i < sumList.length; i++) {
            for (int j = i; j < sumList.length; j++) {
                int total = sumList[j] - offset;
                if (total > t) {
                    break;
                }
                if (total == t) {
                    return new int[]{i, j};
                }
            }
            offset = sumList[i];
        }

        return new int[]{-1, -1};
    }

    public static void main(String[] args) {

        var input = new int[]{1, 2, 3, 4};
        var t = 15;
        System.out.print(Arrays.toString(solution(input, t)));
    }
}
