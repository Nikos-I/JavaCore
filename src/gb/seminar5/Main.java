package gb.seminar5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    static void writer(int[] arr) {
        final int DIGIT_BOUND=48;
        try (FileOutputStream fos=new FileOutputStream("save.out")) {

            for (int i=0; i < arr.length; i++) {
                fos.write(DIGIT_BOUND + arr[i]);
                if (i < arr.length - 1) fos.write(',');
            }
            fos.flush();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static int[] reader(String fileName) {

        int[] ar10=new int[20];

        try (FileInputStream fis=new FileInputStream(fileName)) {
            int b;
            int i=0;
            while (true) {
                if (!((b=fis.read()) != -1)) break;
                if (b != ',') {
                    ar10[i++]=b;
                }


            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ar10;
    }

    public static void main(String[] args) {
        int[] ar0={1, 2, 3, 4, 5, 6, 7, 8, 0, 8, 7, 6, 5, 4, 3};
        writer(ar0);

        int[] ar2=reader("save.out");
        System.out.println(Arrays.toString(ar2));

    }


}
