package ru.geekbrains.JavaCoreForAndroid;

import java.util.*;

/**
 * Сourse: java core for android
 * Faculty of Geek University Android Development
 *
 * @Author Student Dmitry Veremeenko aka StDimensiy
 * Group 24.12.2020
 * <p>
 * HomeWork for lesson 12
 * Created 16.02.2021
 * v1.0
 */
public class Lesson12 {
    static final int SIZE = 10_000_000;             // базовый размер тестируемого массива
    static float[] arrEtalon = new float[SIZE]; // инициализация Эталонного массива данных
    static int numberOfTests = 3;               // Базовое количество испытаний
    static int countTest;                       // количество принимаемых к учету тестов
    static int sumTimeTest;                     // суммарное время принятых к учету тестов
    static char charVal_f = '\u2714';           // код символа галочки согласно таблицы символов юникод
    static char charVal_t = '\u23f1';           // код символа начала замера времени
    static char charVal_ss = '\u26d4';          // код символа проблемной остановки
    static char charVal_rp = '\u23F5';          // код символа старта потока
    static char charVal_sp = '\u23F8';          // код символа останова потока
    static char charVal_gr = '\u23E9';          // код символа начала многопоточной операции
    static char charVal_gs = '\u23EF';          // код символа останова многопоточной операции

    public static void main(String[] args) throws InterruptedException {
        System.out.print("Подготовка (формирование эталонного массива): ...");
        Arrays.fill(arrEtalon, 1.0f);
        for (int i = 0; i < SIZE; i++)
            arrEtalon[i] *= Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2);
        System.out.print(" Готово! \n");
        //модуль тестирования
        System.out.println("Тест работы с массивом в режиме одного потока");
        countTest = 0;               // обнуляем количество принимаемых к учету тестов
        sumTimeTest = 0;             // обнуляем суммарное время принятых к учету тестов
        for (int i = 1; i <= numberOfTests; i++) {
            float[] arr = new float[SIZE];
            long currentTimeTest = startSingleThread(fill(arr), "№" + i, 0);
            validation(arr, currentTimeTest);
        }
        System.out.println("\nСводный результат тестирования режима \"один поток\"");
        System.out.println("    " + charVal_t + " Среднее время обработки массива " + SIZE + " элементов, \n" +
                "      заданным алгоритмом составляет: " + sumTimeTest / countTest + " mc.");
        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println("Тест работы с массивом в режиме двух потоков с разделением и склеиванием массива");
        countTest = 0;               // количество принимаемых к учету тестов
        sumTimeTest = 0;             // суммарное время принятых к учету тестов
        for (int i = 1; i <= numberOfTests; i++) {
            float[] arr = new float[SIZE];
            long currentTimeTest = startTwoThread(fill(arr), "№" + i);
            validation(arr, currentTimeTest);
        }
        System.out.println("Сводный результат тестирования режима \"Два потока\"");
        System.out.println("    " + charVal_t + " Среднее время обработки массива "
                + SIZE + " элементов, \n      заданным алгоритмом составляет: " + sumTimeTest / countTest + " mc.");
        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println("Тест работы с массивом в режиме двух потоков но с модифицированным алгоритмом");
        countTest = 0;               // количество принимаемых к учету тестов
        sumTimeTest = 0;             // суммарное время принятых к учету тестов
        for (int i = 1; i <= numberOfTests; i++) {
            float[] arr = new float[SIZE];
            long currentTimeTest = startTwoThreadV2(fill(arr), "№" + i);
            validation(arr, currentTimeTest);
        }
        System.out.println("Сводный результат тестирования режима \"Два потока\"");
        System.out.println("    " + charVal_t + " Среднее время обработки массива "
                + SIZE + " элементов, \n      заданным алгоритмом составляет: " + sumTimeTest / countTest + " mc.");

    }

    // сервисный метод, проверяет эквивалентны ли массивы и корректирует учитываемые результирующие данные
    private static void validation(float[] arr, long curentTimeTest) {
        if (Arrays.equals(arr, arrEtalon)) {
            countTest++;
            sumTimeTest += curentTimeTest;
            System.out.println("  Массивы эквивалентны. " + charVal_f);
        } else {
            System.out.println("  Массивы не эквивалентны!!! Время выполнения операции в расчет не принимается! "
                    + charVal_ss);
        }
    }

    //метод заполняет тестовый массив единицами
    public static float[] fill(float[] arr) {
        Arrays.fill(arr, 1.0f);
        return arr;
    }

    //метод выполняет задачу в режиме одного потока
    static long startSingleThread(float[] arr, String testNum, int adjustmentIndexForFormula) {
        System.out.println(charVal_rp + " Старт процесса... Испытание " + testNum + ".Поток:"
                + Thread.currentThread().getName() + " " + charVal_f);
        Date startTime = new Date();
        for (int i = 0; i < arr.length; i++)
            arr[i] *= Math.sin(0.2f + (i + adjustmentIndexForFormula) / 5)
                    * Math.cos(0.2f + (i + adjustmentIndexForFormula) / 5)
                    * Math.cos(0.4f + (i + adjustmentIndexForFormula) / 2);
        Date stopTime = new Date();
        long delta = stopTime.getTime() - startTime.getTime();
        System.out.println(charVal_sp + " Стоп  процесса... Испытание " + testNum + ".Поток:"
                + Thread.currentThread().getName() + " " + charVal_f + " / Время выполнения: " + delta + "мс.");
        return delta;
    }

    //модифицированный метод выполняет задачу в режиме одного потока
    static long startModSingleThread(float[] arr, String testNum, int initialIndex, int offset) {
        System.out.println(charVal_rp + " Старт процесса... Испытание " + testNum + ".Поток:"
                + Thread.currentThread().getName() + " " + charVal_f);
        Date startTime = new Date();
        int len = arr.length;
        for (int i = initialIndex; i < len; i += offset) {
            arr[i] *= Math.sin(0.2f + i / 5)
                    * Math.cos(0.2f + i / 5)
                    * Math.cos(0.4f + i / 2);
        }
        Date stopTime = new Date();
        long delta = stopTime.getTime() - startTime.getTime();
        System.out.println(charVal_sp + " Стоп  процесса... Испытание " + testNum + ".Поток:"
                + Thread.currentThread().getName() + " " + charVal_f + " / Время выполнения: " + delta + "мс.");
        return delta;
    }

    private static long startTwoThread(float[] arr, String testNum) throws InterruptedException {
        System.out.println(charVal_gr + " Старт многопоточного процесса " + testNum + ": " + charVal_f);
        Date startTime = new Date();
        float[] tarr1 = Arrays.copyOf(arr, SIZE * 2 / 3);
        float[] tarr2 = Arrays.copyOfRange(arr, tarr1.length, arr.length);
        //щупаем многопоток
        MyThread t1 = new MyThread(testNum, tarr1, 0);
        MyThread t2 = new MyThread(testNum, tarr2, tarr1.length);
        t1.start();
        t2.start();
        t1.join();
        t2.join(); // всех дождались сливаем результаты в массив
        // Процесс сборки массива обратно
        System.arraycopy(tarr1, 0, arr, 0, tarr1.length);
        System.arraycopy(tarr2, 0, arr, tarr1.length, tarr2.length);
        //System.out.println(Arrays.toString(arrEtalon));
        //System.out.println(Arrays.toString(arr));
        Date stopTime = new Date();
        long delta = stopTime.getTime() - startTime.getTime();
        System.out.println(charVal_gs + " Стоп многопоточного процесса " + testNum + ": " + charVal_f + "\n"
                + charVal_t + " Финальное время выполнения: " + delta + "мс.");
        return delta;
    }

    // Модифицированный метод обработки массива без разделения и склаивания массивов а с назначением области работы потока
    private static long startTwoThreadV2(float[] arr, String testNum) throws InterruptedException {
        System.out.println(charVal_gr + " Старт модифицированного многопоточного процесса " + testNum + ": " + charVal_f);
        Date startTime = new Date();
        MyModThread t1 = new MyModThread(arr, testNum, 0, 2);
        MyModThread t2 = new MyModThread(arr, testNum, 1, 2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        Date stopTime = new Date();
        long delta = stopTime.getTime() - startTime.getTime();
        System.out.println(charVal_gs + " Стоп многопоточного процесса " + testNum + ": " + charVal_f + "\n"
                + charVal_t + " Финальное время выполнения: " + delta + "мс.");
        return delta;
    }

}
