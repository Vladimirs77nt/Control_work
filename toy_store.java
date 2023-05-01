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

import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Random;

public class Toy_store {

    public static void main(String[] args) {
        System.out.println("Программа запущена");
        ArrayList<Product> prizes = new ArrayList<>();
        prizes.add(new Product(1, "Конструктор", 20));
        prizes.add(new Product(2, "Медвежонок", 30));
        prizes.add(new Product(3, "Самолетик", 40));
        prizes.add(new Product(4, "Кукла", 25));
        int probability = 0;
        for (Product i : prizes) {
            System.out.println(i.toString());
            probability += i.getProbability();
        }

        System.out.println(probability);


        PriorityQueue<Product> basket_of_prizes = new PriorityQueue<>();
    }
}

class Product{
    private int id;          // ID номер
    private String name;     // название игрушки
    private int probability; // частота выпадания, шанс
    
    public Product(int id, String name, int probability) {
        this.id = id;
        this.name = name;
        this.probability = probability;
    }

    @Override
    public String toString() {
        return "Приз {" + "id = " + id + ", " + name + ", вероятность: " + probability + "}";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProbability() {
        return probability;
    }

    public void setProbability(int probability) {
        this.probability = probability;
    }
  
}