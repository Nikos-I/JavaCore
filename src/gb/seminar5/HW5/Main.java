package gb.seminar5.HW5;


import java.io.IOException;
import java.nio.file.Path;

import static java.nio.file.Files.createDirectory;

public class Main {
    public static void main(String[] args) {
        Path pathDest=Path.of("./backup");
        createDir(pathDest);

    }

    static void createDir(Path path) {
        try {
            createDirectory(path);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

