package gb.seminar2;

public class Funcs {
    static int cntEvens(int[] inputNums) {      // Кол-во четных чисел
        int numsEven = 0;
        for (int n : inputNums) {
            if (n % 2 == 0) numsEven += 1;
        }
        return numsEven;
    }

    static int diffMaxMin(int[] inputNums) {     // Разница между макс. и мин. значениями
        int max = inputNums[0];
        int min = max;

        for (int i = 0; i < inputNums.length; i++) {
            if (inputNums[i] < min) min = inputNums[i];
            if (inputNums[i] > max) max = inputNums[i];
        }
        return max - min;
    }

    static boolean adjacentZero(int[] inputNums) {       // Проверка присутствия соседних нулей
        boolean res = false;
        int i = 1;
        do {
            if (inputNums[i - 1] == 0 && inputNums[i] == 0) res = true;
            i++;
        }
        while (i < inputNums.length && !res);
        return res;
    }
}
