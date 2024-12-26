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