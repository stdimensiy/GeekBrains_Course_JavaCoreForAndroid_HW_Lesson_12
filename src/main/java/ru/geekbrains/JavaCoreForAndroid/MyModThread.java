package ru.geekbrains.JavaCoreForAndroid;

import java.util.Date;

/**
 * Сourse: java core for android
 * Faculty of Geek University Android Development
 *
 * @Author Student Dmitry Veremeenko aka StDimensiy
 * Group 24.12.2020
 * <p>
 * HomeWork for lesson1
 * Created 04.02.2021
 * v2.0
 */
public class MyModThread extends Thread {
    float[] arr;
    int initialIndex;
    int offset;
    String testNum;

    public MyModThread(float[] arr, String testNum, int initialIndex, int offset) {
        this.arr = arr;
        this.initialIndex = initialIndex;
        this.offset = offset;
        this.testNum = testNum;
    }

    @Override
    public void run() {
        Lesson12.startModSingleThread(arr, testNum, initialIndex, offset);
    }
}
