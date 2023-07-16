package gb.seminar2;

public class Main {
    public static void main(String[] args) {
        int[] nums = {4, 2, 11, 0, 0, 9, 12};
        int res;
        res = Funcs.cntEvens(nums);
        System.out.printf("Четных чисел: %d \n", res);

        res = Funcs.diffMaxMin(nums);
        System.out.printf("Разница между мин и макс значениями: %d \n", res);

        boolean resB = Funcs.adjacentZero(nums);
        System.out.printf("В массиве %s соседние нули\n", resB ? "присутствуют" : "отсутствуют");
    }

}
