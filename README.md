### GeekBrains_Course_JavaCoreForAndroid_HW_Lesson_12
---
### Домашняя работа к занятию №12
Студента GeekBrains ***Веремеенко Дмитрия***    
Факультет: ***Android-разработки***    
Курс: ***Java Core для Android***    
### Задание:
---
- [ ] ***Задача №1.***     
- a)Создать одномерный длинный массив: static final int SIZE = 10 000 000; static final int HALF = size / 2; float[] arr = new float[size].
- b) Заполнить этот массив единицами.
- с) Засечь время выполнения: long a = System.currentTimeMillis().
- d) Пройти по всему массиву и для каждой ячейки вычислить новое значение по формуле: arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
- e) Засечь время окончания метода System.currentTimeMillis().
- f) Вывести в консоль время работы программы по обработке массива: System.out.println(System.currentTimeMillis() - a).

                         
- [ ] ***Задача №2.***
- a)Создать одномерный длинный массив: static final int SIZE = 10 000 000; static final int HALF = size / 2; float[] arr = new float[size].  
- b) Заполнить этот массив единицами.
- с) Засечь время выполнения: long a = System.currentTimeMillis().
- d) Разбивает массив на два массива.
- e) Организовать и запустить 2 потока каждый из которых выполняет для своей части массива пунет (f).
- f) Пройти по всему массиву и для каждой ячейки вычислить новое значение по формуле: arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
- g) Соединить ранее разъединенные части массива в один;
- h) Засечь время окончания метода System.currentTimeMillis().
- i) Проверить массивы от первого и от воторго метода на идентичность.
- j) Вывести в консоль время работы программы по обработке второго массива: System.out.println(System.currentTimeMillis() - a).                         
                         
- [ ] ***Задача №3.***	*Сделать выводы. Попробовать оптимизировать и еще сократить время выполнения задачи.
   
     
### Отчет о выполнении:
---    
:clock2: ***Задача №1.***	 - выполняется -    
:clock2: ***Задача №2.***	 - выполняется -     
:clock2: ***Задача №3.***	 - выполняется -   
  
      
---   

*StDimensiy 15.02.2021*