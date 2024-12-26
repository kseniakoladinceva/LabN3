## Отчет по лабораторной работе № 3

#### № группы: `ПМ-2401`

#### Выполнила: `Колядинцева Ксения Дмитриевна`

#### Вариант: `14`

### Cодержание:

- [Постановка задачи](#1-постановка-задачи)
- [Математическая модель](#2-математическая-модель)
- [Описание класса и методов](#3-описание-класса-и-методов)
- [Программа](#4-программа)

### 1. Постановка задачи

> Гит для поэта
Краткое описание
Разработать программу для реализации системы контроля версий, предназначенной
для работы со стихотворениями. Реализовать функционал добавления, удаления и изменения строк, сохранения состояний, отката к предыдущим версиям и сравнения изменений между ними.
Описание функционала
>1. Создание объекта гита
   Создание объекта гита по названию стихотворения, автору и максимальному количеству сохраняемых версий.
>2. Вывод стихотворения (переопределение toString)
   Отображение текущей версии стихотворения в формате: «НазваниеСтиха - ИмяПоэта» и строки построчно.
>3. Добавление строки
   Добавление новой строки в конец текущего стихотворения.
>4. Удаление строки по номеру
   Удаление строки с указанным номером (нумерация начинается с 1).
>5. Вставка строки по номеру
   Вставка новой строки в указанное место, сдвигая последующие строки вниз.
>6. Сохранение версии
   Сохранение текущей версии стихотворения. Если количество сохранений превышает максимум, самое старое сохранение удаляется. Обратите внимание: для корректного сохранения массив строк копируется, а не передаётся по ссылке.
>7. Просмотр сохранений
   Вывод сохранённой версии. Нумерация версий: 0 — текущая версия, 1 — последнее
   сохранение, и так далее.
>8. Удаление сохранения
   Удаление указанного сохранения. Более ранние сохранения, если были удалены,
   не восстанавливаются.
>9. Откат к сохранению
   Откат текущей версии к указанному сохранению. При этом текущая версия и все
   более поздние сохранения стираются.
>10. Сравнение версий
    Сравнение двух версий или текущей версии с одной из сохранённых:
    • Если передана одна версия, она сравнивается с текущей.
    • Если переданы две версии, они сравниваются между собой.
    24
>11. Общие строки между версиями
    Вывод строк, которые есть в обеих версиях, в порядке, в котором они встречаются
    в более новой версии.
>12. Различные строки между версиями
    Вывод строк, которые встречаются только в одной из версий:
    • Строки из старой версии, которых нет в новой, помечаются знаком «-» и
    выводятся в порядке их появления в старой версии.
    • Строки из новой версии, которых нет в старой, помечаются знаком «+» и
    выводятся в порядке их появления в новой версии.

### 2.Математическая модель
Для хранения версий стихотворения используется "неровный" двумерный массив: 1 параметр - номер столбца (версии), второй - количество строк в данной версии.
### 3. Описание класса и методов
1. Class Library - хранит всю необходимую информацию о данном стихотворении, имеет 5 полей: название стихотворения, имя автора, максимальное количество сохраняемых версий, массив версий (описан выше) и служебное поле - текущая версия стихотворения.
2. Конструктор Library - создает объект типа Library по трем параметрам: названию, автору и максимальному количеству сохранений.
3. Метод addString - получает на вход строку и добавляет ее в конец стихотворения
4. Метод deleteString - получает на вход номер строки и удаляет строку с таким номером, сдвигая остальные.
5. Метод insertString - получает на вход строку и позицию, на которую ее нужно вставить, вставляет эту строку в нужное место.
6. Метод savePoem - сохраняет текущую версию стихотворения и создает новую пустую версию.
7. Метод showVersion - получает на вход номер версии и возвращает данную версию.
8. Метод deleteVersion - получает на вход номер версии и удаляет ее, сдвигая остальные версии на освободившуюся позицию.
9. Метод returnVersion - получает на вход номер версии, которую делает текущей, удаляя имеющуюся текущую и все сохранения, которые были после указанной версии.
10. Метод compareVersions - может получать на вход как один, так и два номера. В первом случае сравнивает текущую версию с указанной, во втором - сравнивает две указанные между собой. Если текст версий совпадает, то возвращает true, иначе - false.
11. Метод sameStrings - получает на вход один или два номера. В первом случае выводит все совпадающие строки указанной версии и текущей в том порядке, в котором они идут в текущей. Во втором - совпадающие строки двух указанных версий в том порядке, в котором они идут в более новой версии.
12. Метод diffStrings - получает на вход один или два номера. В первом случае выводит сначала строки, которые есть в указанной версии и отсутствуют в текущей (помечены знаком -), а потом - строки, которые есть в текущей и отсутствуют в более старой (помечены знаком +). Во втором случае выводит сначала строки, присутствующие только в более старой версии (со знаком -), а потом - строки, присутствующие только в новой (со знаком +).

### 4. Программа
1. Описание класса Library
```java
public class Library{
    private String name;  //название стихотворения
    private String author;  //имя автора
    private int versions_max;  //макс.количество сохраняемых версий
    private String[][] text; //массив строк стихотворения
    private int currentver; //текущая версия стихотворения

    public Library(String name, String author, int versions_max){   //создаем запись о стихотворении
        this.name = name;
        this.author = author;
        this.versions_max = versions_max;
        this.text = new String[1][0];
        this.currentver = 0;
    }
    public void addString(String str){   //добавление строки
        currentver = 0;
        String []text1 = text[0];
        text[0] = new String[text1.length + 1];
        if (text1.length > 0) {
            for (int i = 0; i < text1.length; i++)
                text[0][i] = text1[i];
        }
        text[0][text[0].length - 1] = str;
    }

    public void deleteString(int n){   //удаление строки с определенным номером
        currentver = 0;
        if (n <= text[0].length) {
            String[] text1 = text[0];
            text[0] = new String[text1.length - 1];
            for (int i = 0; i < n - 1; i++)
                text[0][i] = text1[i];
            for (int i = n - 1; i < text[0].length; i++)
                text[0][i] = text1[i + 1];
        }
    }

    public void insertString(String str, int n){   //вставка строки в определенное место
        currentver = 0;
        String[] text1 = text[0];
        text[0] = new String[text1.length + 1];
        for (int i = 0; i < n - 1; i++)
            text[0][i] = text1[i];
        text[0][n - 1] = str;
        for (int i = n; i < text[0].length; i++)
            text[0][i] = text1[i - 1];
    }

    public void savePoem(){   //сохранение текущей версии стихотворения
        currentver = 0;
        String[][] text1 = text;
        if (text.length + 1 <= versions_max) {
            text = new String[text.length + 1][];
            text[0] = new String[0];
            for (int i = 0; i < text1.length; i++)
                text[i + 1] = new String[text1[i].length];
            for (int i = 0; i < text1.length; i++)
                for (int j = 0; j < text1[i].length; j++)
                    text[i + 1][j] = text1[i][j];
        }
        else {
            text = new String[text.length][];
            text[0] = new String[0];
            for (int i = 0; i < text1.length - 1; i++)
                text[i + 1] = new String[text1[i].length];
            for (int i = 0; i < text1.length - 1; i++)
                for (int j = 0; j < text1[i].length; j++)
                    text[i + 1][j] = text1[i][j];
        }
    }
    public Library showVersion(int n){   //показ желаемой версии
        if (n < text.length)
            currentver = n;
        return this;
    }

    public void deleteVersion(int n){   //удаление определенной версии
        currentver = 0;
        if (n < text.length) {
            String[][] text1 = text;
            text = new String[text.length - 1][];
            for (int i = 0; i < n; i++)
                text[i] = new String[text1[i].length];
            for (int i = n + 1; i < text1.length; i++)
                text[i - 1] = new String[text1[i].length];

            for (int i = 0; i < n; i++)
                for (int j = 0; j < text1[i].length; j++)
                    text[i][j] = text1[i][j];
            for (int i = n + 1; i < text1.length; i++)
                for (int j = 0; j < text1[i].length; j++)
                    text[i - 1][j] = text1[i][j];
        }
    }
    public void returnVersion(int n){  //сброс версий и возврат к определенной из сохраненных
        currentver = 0;
        int i = 0;
        while (i < n){
            this.deleteVersion(0);
            i++;
        }
    }

    public boolean compareVersions(int n){  //сравнение какой-то версии с текущей
        currentver = 0;
        if (text[0].length == text[n].length){
            for (int i = 0; i < text[0].length; i++)
                if (text[0][i].compareTo(text[n][i]) != 0)
                    return false;
            return true;
        }
        else
            return false;
    }

    public boolean compareVersions(int n1, int n2){  //сравнение двух версий
        if (text[n1].length == text[n2].length){
            for (int i = 0; i < text[n1].length; i++)
                if (text[n1][i].compareTo(text[n2][i]) != 0)
                    return false;
            return true;
        }
        else
            return false;
    }
    public String sameStrings(int n){   //вывод строк, совпадающих в текущей и запрашиваемой версиях
        String samestr = "";
        for (int i = 0; i < text[0].length; i++)
            for (int j = 0; j < text[n].length; j++){
                if (text[0][i].equals(text[n][j]))
                    samestr += text[0][i] + "\n";
            }
        return samestr;
    }

    public String sameStrings(int n1, int n2){  //вывод строк, совпадающих в двух любых версиях
        String samestr = "";
        if (n1 < n2) {
            for (int i = 0; i < text[n1].length; i++)
                for (int j = 0; j < text[n2].length; j++) {
                    if (text[n1][i].equals(text[n2][j]))
                        samestr += text[n1][i] + "\n";
                }
        }
        else
            for (int i = 0; i < text[n2].length; i++)
                for (int j = 0; j < text[n1].length; j++) {
                    if (text[n2][i].equals(text[n1][j]))
                        samestr += text[n2][i] + "\n";
                }
        return samestr;
    }

    public String diffStrings(int n){  //вывод строк, различающихся в текущей и указанной версиях
        String diffstr = "";
        int count = 0;
        for (int i = 0; i < text[n].length; i++) {
            for (int j = 0; j < text[0].length; j++) {
                if (text[n][i].compareTo(text[0][j]) == 0)
                    count++;
            }
            if (count == 0)
                diffstr += "- " + text[n][i] + "\n";
            count = 0;
        }
        for (int i = 0; i < text[0].length; i++) {
            for (int j = 0; j < text[n].length; j++) {
                if (text[0][i].compareTo(text[n][j]) == 0)
                    count++;
            }
            if (count == 0)
                diffstr += "+ " + text[0][i] + "\n";
            count = 0;
        }
        return diffstr;
    }

    public String diffStrings(int n1, int n2){  //вывод строк, различающихся в двух версиях
        String diffstr = "";
        int count = 0;
        if (n1 < n2){
            int temp = n1;
            n1 = n2;
            n2 = temp;
        }

        for (int i = 0; i < text[n1].length; i++) {
            for (int j = 0; j < text[n2].length; j++) {
                if (text[n1][i].compareTo(text[n2][j]) == 0)
                    count++;
            }
            if (count == 0)
                diffstr += "- " + text[n1][i] + "\n";
            count = 0;
        }
        for (int i = 0; i < text[n2].length; i++) {
            for (int j = 0; j < text[n1].length; j++) {
                if (text[n2][i].compareTo(text[n1][j]) == 0)
                    count++;
            }
            if (count == 0)
                diffstr += "+ " + text[n2][i] + "\n";
            count = 0;
        }
        return diffstr;
    }

    @Override  //переопределение toString
    public String toString(){
        if (text[currentver].length > 0){
            String t = "";
            for (int i = 0; i < text[currentver].length; i++)
                t += text[currentver][i] + "\n";
            return String.format("'%s - %s'\n%s", name, author, t);
        }
        else {
            return String.format("'%s - %s'", name, author);
        }
    }
}
```
2. Основная программа
```java
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static Scanner in = new Scanner(System.in);
    public static PrintStream out = System.out;

    public static void main(String[] args) {
        Library first = new Library("first", "me", 3); //создание новой записи о стихотворении
        first.addString("hi!"); //добавление строки к текущей версии
        first.addString("bye!"); //добавление строки к текущей версии
        first.addString("bye-bye!"); //добавление строки к текущей версии
        first.insertString("sleep", 3); //вставка строки в текущую версию
        first.deleteString(4); //удаление строки из текущей версии
        first.savePoem(); //сохранение версии
        out.println(first.showVersion(1)); //просмотр последней сохраненной версии
        first.addString("tired"); //добавление строки к текущей версии
        first.savePoem(); //сохранение версии
        out.println(first.showVersion(1)); //просмотр последней сохраненной версии
        first.addString("tired"); //добавление строки к текущей версии
        first.addString("happy"); //добавление строки к текущей версии
        out.println(first.compareVersions(1) + "\n"); //сравнение текущей версии с последней сохраненной
        out.println(first.compareVersions(1, 2) + "\n"); //сравнение последней и предпоследней сохраненных версий
        out.println(first.sameStrings(1)); //вывод совпадающих строк в текущей и последней сохраненной версиях
        out.println(first.sameStrings(1, 2)); //вывод совпадающих строк в предпоследней и последней сохраненной версиях
        out.println(first.diffStrings(1)); //вывод различающихся строк в текущей и последней сохраненной версиях
        out.println(first.diffStrings(1, 2));//вывод различающихся строк в предпоследней и последней сохраненной версиях
        first.deleteVersion(1); //удаление последней сохраненной версии
        first.returnVersion(1); //откат к последней сохраненной версии
    }
}
```
