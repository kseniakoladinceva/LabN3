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

