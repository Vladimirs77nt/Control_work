/* Задание 2. Магазин игрушек (Java)

    Информация о проекте.
Необходимо написать проект, для розыгрыша в магазине игрушек.
Функционал должен содержать добавление новых игрушек и задания веса для выпадения игрушек.

Программа, может использоваться в различных системах, поэтому необходимо разработать класс в виде конструктора

    Задание:
1) Напишите класс-конструктор у которого принимает минимум 3 строки,
содержащие три поля:
* id игрушки,
* текстовое название,
* частоту выпадения игрушки.
2) Из принятой строки id и частоты выпадения(веса) заполнить минимум три массива.
3) Используя API коллекцию: java.util.PriorityQueue добавить элементы в коллекцию
4) Организовать общую очередь
5) Вызвать Get 10 раз и записать результат в файл

    Подсказка:
В метод Put передаете последовательно несколько строк, пример:
1 2 конструктор;
2 2 робот;
3 6 кукла.

Метод Get должен случайно вернуть либо “2”, либо “3” и соответствии с весом.
В 20% случаях выходит единица
В 20% двойка
И в 60% тройка. */

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

public class Toy_store {
static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // формируем названия призов (пользовательский ввод)
        ArrayList <Toy> prizes = new ArrayList<>();
        System.out.print("Введите + если нужно ввести вручную список призов (иначе сформируется по умолчанию): ");
        
        String numStr = sc.nextLine();
        if (numStr.equals("+")) {                        // ручной ввод списка игрушек-призов
            prizes = getListPrize();
        }
        else {                                      // предварительно заданный список игрушек-призов
            prizes.add(new Toy(1, "Конструктор", 2));
            prizes.add(new Toy(2, "Робот", 2));
            prizes.add(new Toy(3, "Кукла", 6));
            System.out.println();
            for (int i = 0; i < prizes.size(); i++) {
                Toy toy = prizes.get(i);
                System.out.println(">>> Приз №" + toy.getId());
                System.out.println("> Название приза: " + toy.getName());
                System.out.println("> Шанс выпадания: " + toy.getProbability());
                System.out.println();
            }
        }

        // формируем список призов
        PriorityQueue <Toy> basket_of_prizes = new PriorityQueue<>();
        for (int j = 0; j < 10; j++) {
            Toy priz = getPrize(prizes);
            System.out.println("выпал " + priz.toString());
            basket_of_prizes.add(priz);
        }
    sc.close();
    }

// -------------------------------------------------------------------------------------------

    // функция создания списка призов (объекты класса-контсркутора Toy)
    public static ArrayList <Toy> getListPrize() {
        int num;
        String numStr;
        while (true) {
            System.out.print("Введите количество призов (от 3 и более): ");
            numStr = sc.nextLine();
            if (isNumeric(numStr) && Integer.parseInt (numStr)>=3) {
                num = Integer.parseInt (numStr);
                break;
            }
        }
        System.out.println();
        ArrayList <Toy> prizes = new ArrayList<>();
        int id = 1;
        for (int i = 0; i < num; i++) {
            System.out.println(">>> Приз №" + id);
            System.out.print("> Введите название приза: ");
            String name = sc.nextLine();
            int chance = 0;
            while (true) {
                System.out.print("> Введите шанс выпадания: ");
                numStr = sc.nextLine();
                if (isNumeric(numStr) && Integer.parseInt (numStr)>0) {
                    chance = Integer.parseInt (numStr);
                    break;
                }
            }
            
            System.out.println();
            prizes.add(new Toy(id, name, chance));
            id += 1;
        }
        return prizes;
    }

    /**
     * Проверка введенной строки на число
     * @param strNum
     * @return True / False
     */
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
    * Метод получения приза
    * @param prizes - на входе список призов ArrayList<Product>
     * @return 
    * @return объект приза <Product>
    */
    public static Toy getPrize(ArrayList<Toy> list) {
        int probability = 0;
        for (Toy i : list) probability += i.getProbability();
        int p = 0;
        int p2;
            int n = new Random().nextInt(probability);
            for (Toy i : list) {
                p2 = i.getProbability();
                if (n<(p+p2)) return i;
                p += p2;
            }
            return null;
    }
}
// -------------------------------------------------------------------------------------------
// класс-конструктор игрушки
class Toy implements Comparable<Toy>{
    private int id;          // ID номер игрушки
    private String name;     // название игрушки
    private int probability; // частота выпадания игрущки
    
    public Toy(int id, String name, int probability) {
        this.id = id;
        this.name = name;
        this.probability = probability;
    }

    @Override
    public String toString() {
        return "Игрушка {" + "id = " + id + ", " + name + ", вероятность: " + probability + "}";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getProbability() {
        return probability;
    }

    @Override
    public int compareTo(Toy o) {
        return 0;
    }
}