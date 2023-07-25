//Написать функцию, создающую резервную копию всех файлов
// в директории(без поддиректорий) во вновь созданную папку ./backup

package gb.seminar5.HW5;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class HW5 {

    public static void main(String[] args) {
        final String source = "d:/temp/";
        final String destination = "d:/backup/";

        copyDir(source, destination);
        System.out.println("Copied whole directory successfully.");
    }

    public static void copyDir(String src, String dst) {

        Path srcPath = Paths.get(src);
        Path dstPath = Paths.get(dst);

        if (!Files.isDirectory(dstPath)) {                 // Проверка существования директории назначения
            try {
                Files.createDirectories(dstPath);            // и её создание, в случае отсутствия
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (DirectoryStream<Path> files = Files.newDirectoryStream(srcPath)) {
            for (Path p : files) {
                if (Files.isRegularFile(p)) {
                    System.out.println(p);
                    Path dp = Paths.get(dst + p.getFileName());
                    Path bytes = Files.copy(p, dp, REPLACE_EXISTING); // Копирование файлов
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
