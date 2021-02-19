package ru.geekbrains.JavaCoreForAndroid;

/**
 * Сourse: java core for android
 * Faculty of Geek University Android Development
 *
 * @Author Student Dmitry Veremeenko aka StDimensiy
 * Group 24.12.2020
 * <p>
 * HomeWork for lesson 12
 * Created 16.02.2021
 * v 1.0
 */
public class MyThread extends Thread{
    float[] arr;
    int adInd;
    String testNum;

    public MyThread(String testNum, float[] arr,  int adInd) {
        this.arr = arr;
        this.adInd = adInd;
        this.testNum = testNum;
    }

    @Override
    public void run() {
        Lesson12.startSingleThread(arr, testNum, adInd);
    }

}
