package gb.seminar5;

import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static <fos> void main(String[] args) {
        int[] ar0={1, 2, 3, 4, 5, 6, 7, 8, 0, 8, 7, 6, 5, 4, 3};
        final int DIGIT_BOUND=48;
        try (FileOutputStream fos=new FileOutputStream("save.out")) {

            fos.write('[');
            for (int i=0; i < ar0.length; i++) {
                fos.write(DIGIT_BOUND + ar0[i]);
                if (i < ar0.length - 1) fos.write(',');
            }
            fos.write(']');
            fos.flush();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
