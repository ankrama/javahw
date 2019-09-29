package org.itstep;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

@Slf4j
public class Application {

    /**
     * ---------
     * Задание 1
     * ---------
     * <p>
     * Напишите метод, с именем evenNumbers, который имя файла в ​​качестве входного параметра для
     * чтения данных из файла, содержащего ряд целых чисел, а также выводить различные статистические данные о
     * целых числах.
     * <p>
     * Предположить, что существует по крайней мере одно целое число в файле.
     * <p>
     * Вывести следующую статистику:
     * Общее количество чисел ,
     * сумма чисел
     * количество четных чисел и процент четных чисел.
     * <p>
     * Например, если входной файл numbers.txt содержит следующий текст:
     * <p>
     * 5 7 2 8 9 10 12 98 7 14 20 22
     * <p>
     * то вызов метода evenNumbers(input); должен приводить к следующему результату:
     * <p>
     * 12 чисел, сумма = 214
     * 8 четных (66.67%)
     *
     * @param fileName - имя файла
     */
    public static void eventNumbers(String fileName) {
        // TODO: пишите код здесь
        try {
            InputStream file = new FileInputStream(fileName);
            Scanner scanner = new Scanner(file);

            int count = 0;
            int summ = 0;
            int even = 0;


            while (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                count++;
                summ = number + summ;
                if (number % 2 == 0) {
                    even++;
                }

            }
            double par = (double) even / count;
            System.out.print("Чисел " + count + " " + "Сумма = " + summ + " " + "Четные: " + even + " ");
            System.out.printf("%.2g", par);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * ---------
     * Задание 2
     * ---------
     * <p>
     * Написать статический метод collapseSpaces, который принимает имя текстового файла, считывает содержимое
     * файла и возвращает его при этом если в строке есть несколько проблеов или табуляций, то
     * остаются только единичные пробелы между словами.
     * <p>
     * Например, если файл содержит следующий текст:
     * <p>
     * four      score   and
     * <p>
     * seven               years ago         our
     * <p>
     * fathers brought             forth
     * on this          continent
     * a         new
     * <p>
     * nation
     * <p>
     * то после вызова метод collapseSpaces(fileName); должен вывести на консоль следующий текст:
     * <p>
     * four score and
     * <p>
     * seven years ago our
     * <p>
     * fathers brought forth
     * on this continent
     * a new
     * <p>
     * nation
     * <p>
     * Каждое слово должно появляться на той же строке в выходе, как он появляется в файле.
     * Обратите внимание на то, что строки могут быть пустыми.
     *
     * @param fileName - имя текстового файла
     */
    public static String collapseSpaces(String fileName) {
        String text = readEntireFile(fileName);

        return text.replaceAll("\\s+", " ");
    }

    /**
     * ---------
     * Задание 3
     * ---------
     * <p>
     * Написать метода с именем readEntireFile, который принимает имя файла, в качестве параметра метода,
     * затем считывает этот файл и возвращает все текстовое содержимое
     * этого файла в виде строки.
     *
     * @param fileName
     */
    public static String readEntireFile(String fileName) {
        // TODO: пишите код здесь
        String content = "";

        try {
            InputStream file = new FileInputStream(fileName);
            Scanner scanner = new Scanner(file);
            String line = "";

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                content += line + "\n";
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return content;
    }


    /**
     * ---------
     * Задание 4
     * ---------
     * <p>
     * Напишите метод, с названием wordWrap, который принимает имя файла в качестве параметра метода и
     * возвращает массив строк - содержимое файла, при этом длинна каждой строки не должна превышать maxLength символов.
     * <p>
     * Например, если maxLength = 60 и файл содержит 112 символов, метод должен вернуть массив из двух строк,
     * одна из которых содержит первые 60 символов, а другая содержащая последние 52 символов.
     * <p>
     * Файл, содержащий 217 символов должен быть преобразован в четыре строки: три длиной 60 и
     * последняя строка длиной 37
     *
     * @param maxLength - максимальная длина строки
     */
    public static String[] wordWrap(String fileName, int maxLength) {
        String lines[] = null;
        // TODO: пишите код здесь

        String text = readEntireFile(fileName);
        // text.replaceAll("\\s+", " ");
        text = text.replaceAll("\n", " ");
        int fullParts = text.length() / maxLength; // 2
        int parts = fullParts + (text.length()%maxLength == 0 ? 0 : 1);
        lines = new String[parts];
        for (int i = 0; i < parts; i++) {
            lines[i] = text.substring(i*maxLength, i == parts - 1 ? text.length() : (i+1)*maxLength) ;
        }

        return lines;
    }

    /**
     * ---------
     * Задание 5
     * ---------
     * <p>
     * Напишите метод, с именем stripHtmlTags, который принимает имя файла, который содержит
     * веб страницу формата HTML в качестве параметра, а затем считывает этот файл и возвращает текст файла без HTML
     * тегов. Тег, как известно, представляет собой текст между символами < и >.
     * Например, рассмотрим следующий текст:
     *
     * <html>
     * <head>
     * <title>My web page</title>
     * </head>
     * <body>
     * <p>There are many pictures of my cat here,
     * as well as my <b>very cool</b> blog page,
     * which contains <font color="red">awesome
     * stuff about my trip to Vegas.</p>
     * <p>
     * Here's my cat now:<img src="cat.jpg">
     * </body>
     * </html>
     * <p>
     * Если файл содержит эти строки, ваша программа должна вывести следующий текст:
     * <p>
     * My web page
     * <p>
     * There are many pictures of my cat here,
     * as well as my very cool blog page,
     * which contains awesome
     * stuff about my trip to Vegas.
     * <p>
     * Here's my cat now:
     * <p>
     * Можно считать, что файл является правильным HTML документом, т.е. отстутвуют символы < или > внутри тегов.
     *
     * @param html - имя файла
     */
    public static String stripHtmlTags(String html) {
        String text = "";
        String textStart = readEntireFile(html);
        text = textStart.replaceAll("<.+>","");

        return text;
    }

    /**
     * Написать программу для подсчета наиболее встречающихся слов в неком тексте, например,
     * произведения Алиса в стране чудес. Словом считается последовательность букв длиной более 3-х
     * Файл с текстом прилагается (alice.txt)
     * <p>
     * При выводе результатов привести первые top наиболее встречающихся слова с указанием их количества.
     * <p>
     * Пример результата:
     * <p>
     * алиса: 406
     * сказала: 126
     * было: 105
     * сказал: 100
     * если: 87
     * только: 87
     * очень: 71
     * когда: 64
     * король: 61
     * подумала: 61
     * <p>
     * Подсчет слов не должен учитывать регистр и знаки препинания
     *
     * @param fileName - имя файла
     * @return словарь наиболее часто всречающихся слов
     */
    public static Map<String, Integer> statistics(String fileName, int top) {
        Map<String, Integer> words = null;

        // TODO: пишите код здесь

        return words;
    }

    public static void main(String[] args) {

        // TODO: реализовать и протестировать все статические методы этого класса
        eventNumbers("C:\\Users\\student\\numbers.txt");
        System.out.println();
        System.out.println(collapseSpaces("text.txt"));
        System.out.println(readEntireFile("text2.txt"));
        System.out.println(Arrays.toString(wordWrap("text2.txt", 10)));

        System.out.println(stripHtmlTags("text3.html"));
    }


}
