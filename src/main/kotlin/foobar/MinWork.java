package foobar;


import java.util.*;

public class MinWork {
    public static int[] solution(int[] data, int n) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : data) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        List<Integer> result = new ArrayList<>();
        for (int i : data) {
            if (map.get(i) <= n) {
                result.add(i);
            }
        }
        int[] arr = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            arr[i] = result.get(i);
        }
        return arr;
    }

    public static void main(String[] args) {
        System.out.print(Arrays.toString(solution(new int[]{1, 2, 2, 3, 3, 3, 4, 5, 5}, 1)));
    }


}