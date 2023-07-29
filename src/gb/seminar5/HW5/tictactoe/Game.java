package gb.seminar5.HW5.tictactoe;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Game {
    //private static final int WIN_COUNT = 3;
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'O';
    private static final char DOT_EMPTY = '.';
    private static final char VAL_EMPTY = '0';
    private static final char VAL_HUMAN = '1';
    private static final char VAL_AI = '2';
    private static final char VAL_RESERVE = '3';

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Random RANDOM = new Random();
    private static final int SIZE_X = 3;
    private static final int SIZE_Y = 3;
    private static char[][] field;
    private static String FILESTATE = "./gamestate";

    public static void main(String[] args) {
        initialize();
        printField();
        while (true) {
            queryState(); // Запрос сохранения/восстановления состояния игры

            humanTurn();
            printField();
            if (gameCheck(DOT_HUMAN, "Вы победили!"))
                break;
            aiTurn();
            printField();
            if (gameCheck(DOT_AI, "Победил компьютер!"))
                break;
        }
    }

    private static void initialize() {
        field = new char[SIZE_X][SIZE_Y];
        for (int x = 0; x < SIZE_X; x++) {
            for (int y = 0; y < SIZE_Y; y++) {
                field[x][y] = DOT_EMPTY;
            }
        }
    }

    private static void printField() {
        System.out.print("+");
        for (int i = 0; i < SIZE_X * 2 + 1; i++) {
            System.out.print((i % 2 == 0) ? "-" : i / 2 + 1);
        }
        System.out.println();

        for (int i = 0; i < SIZE_X; i++) {
            System.out.print(i + 1 + "|");

            for (int j = 0; j < SIZE_Y; j++)
                System.out.print(field[i][j] + "|");

            System.out.println();
        }

        for (int i = 0; i < SIZE_X * 2 + 2; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private static void humanTurn() {
        int x=0, y=0;
        do {
            System.out.println("Введите Х и Y  (1 to 3) через пробел: ");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!isCellValid(x, y) || !isCellEmpty(x, y));
        field[x][y] = DOT_HUMAN;
    }

    private static boolean isCellEmpty(int x, int y) {
        return field[x][y] == DOT_EMPTY;
    }

    private static boolean isCellValid(int x, int y) {
        return x >= 0 && x < SIZE_X
                && y >= 0 && y < SIZE_Y;
    }

    private static void aiTurn() {
        int x, y;
        do {
            x = RANDOM.nextInt(SIZE_X);
            y = RANDOM.nextInt(SIZE_Y);
        } while (!isCellEmpty(x, y));
        field[x][y] = DOT_AI;
    }

    private static boolean gameCheck(char symbol, String message) {
        if (checkWin(symbol)) {
            System.out.println(message);
            return true;
        }
        if (checkDraw()) {
            System.out.println("Draw");
            return true;
        }
        return false;
    }

    private static boolean checkDraw() {
        for (int x = 0; x < SIZE_X; x++) {
            for (int y = 0; y < SIZE_Y; y++) {
                if (isCellEmpty(x, y)) return false;
            }
        }

        return true;
    }

    private static boolean checkWin(char symbol) {
        // Проверка по трем горизонталям
        if (field[0][0] == symbol && field[0][1] == symbol && field[0][2] == symbol) return true;
        if (field[1][0] == symbol && field[1][1] == symbol && field[1][2] == symbol) return true;
        if (field[2][0] == symbol && field[2][1] == symbol && field[2][2] == symbol) return true;

        // Проверка по диагоналям
        if (field[0][0] == symbol && field[1][1] == symbol && field[2][2] == symbol) return true;
        if (field[0][2] == symbol && field[1][1] == symbol && field[2][0] == symbol) return true;

        // Проверка по трем вертикалям
        if (field[0][0] == symbol && field[1][0] == symbol && field[2][0] == symbol) return true;
        if (field[0][1] == symbol && field[1][1] == symbol && field[2][1] == symbol) return true;
        if (field[0][2] == symbol && field[1][2] == symbol && field[2][2] == symbol) return true;

        return false;
    }

    private static String fieldToStr() {
//        int intState = 0;
        String strState = null;
        char ch;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                ch = switch (field[i][j]) {
                    case DOT_EMPTY -> VAL_EMPTY;
                    case DOT_HUMAN -> VAL_HUMAN;
                    case DOT_AI -> VAL_AI;
                    default -> VAL_RESERVE;
                };
                if (i == 0 & j == 0) {
                    strState = String.valueOf(ch);
                } else strState += ch;
            }
        }
//int num = Integer.valueOf(strState);   // Нет смысла преобразовывать в число, всё равно в файл писать строку
//        System.out.println(num);
        return strState;
    }

    private static void writeState() {
        String res = fieldToStr();
        System.out.println(res);
        try (FileWriter fileWriter = new FileWriter(FILESTATE, false)) {
            fileWriter.write(res); // Записываем статус в файл
            fileWriter.flush(); // Очищаем буфер потока
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void strToField(String strState) {
        char ch;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                ch = switch (strState.charAt(i + j)) {
                    case VAL_EMPTY -> DOT_EMPTY;
                    case VAL_HUMAN -> DOT_HUMAN;
                    case VAL_AI -> DOT_AI;
                    default -> VAL_RESERVE;
                };
                field[i][j] = ch;
            }
        }
    }

    private static void readState() {
        String str = "";
        try (FileReader fileReader = new FileReader(FILESTATE)) {
            BufferedReader reader = new BufferedReader(fileReader);
            str = reader.readLine();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(str);
        strToField(str);
    }

    private static void queryState() {
        System.out.println("Введите s - для сохранения состояния игры, r - для восстановления сохраненного состояния игры, любое остальное - для продолжения: ");
        String str = SCANNER.next();
        if (str.equals("s")) {
            writeState();
        } else if (str.equals("r")) {
            readState();
            printField();
        }
    }
}
