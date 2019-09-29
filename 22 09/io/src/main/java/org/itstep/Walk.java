package org.itstep;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.function.BiPredicate;


public class Walk {

    // 1. Выводить содержимое каталога
    // 2. Создавать файл
    // 3. Отоброжать содержимео файла - FileStream, Scanner, BufferedReader || Files
    // 4. Создавать катологи


    public static void main(String[] args) throws IOException {

       int ig =2;
       for (; ig<1;ig++){
           System.out.println("hi");
       }

        // 1. File, Files
        File parentDir = new File("."); // . - относительный путь к текущей директории в которой запускается программа
        //  > java -cp target\classes org.itstep.Walk
        // System.out.println(parentDir.getName());
        //System.out.println(parentDir.getAbsolutePath());


        for (File f : parentDir.listFiles()) {
            if (f.isDirectory()) {
                System.out.printf("[DIR]          %-10s%n", f.getName());
            } else {
                System.out.printf("%-15d%-10s%n", f.length(), f.getName());
            }
        }
//        for (String file : parentDir.list()) {
//            File fileNew = new File(parentDir, file);
//            System.out.print(fileNew.getName());
//            if (fileNew.isDirectory()) {
//                System.out.print("[ DIR ] \n");
//            } else {
//
//                Long gg = fileNew.length();
//                System.out.println("[ " + gg + " ]");
//            }
//        }


//        try {
//            System.out.println(parendDir.createNewFile());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Files.list(Paths.get("."))
//                .forEach(path -> System.out.printf("%-15s %5s %s%n",path.getFileName(), path.toFile().isDirectory()? "[DIR]" : "", path.toFile().length() > 0 ? path.toFile().length() : ""));

//        final String filename = "test.txt";
//        Path wayFileName = Paths.get(filename);
//        Files.readAllLines(wayFileName);//#3
//
//        for (String s : Files.readAllLines(wayFileName)) {
//            System.out.println(s);
//        }

        //поиск файла/каталога
        System.out.println("All java and class files:\n");
        Files.find(Paths.get("."), 10, (path, basicFileAttributes) -> {
            //System.out.println(path);
            FileTime lastModifiedTime = basicFileAttributes.lastModifiedTime();
            FileTime past10mins = FileTime.from(System.currentTimeMillis() - 10 * 60 * 1000, TimeUnit.MILLISECONDS);
            //System.out.println(lastModifiedTime + " " + path.getFileName() + " " + past10mins);
            return lastModifiedTime.compareTo(past10mins) >= 0 && basicFileAttributes.isRegularFile() ;
//                String fileName = path.toString();
//                return fileName.endsWith(".class") || fileName.endsWith(".java");
        }).forEach(System.out::println);
        System.out.println(Files.exists(Paths.get("link.txt.lnk")));


    }


}
