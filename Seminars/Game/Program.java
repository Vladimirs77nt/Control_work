package Seminars.Game;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

import Seminars.Game.Units.*;

public class Program {
    public static final int GANG_SIZE = 10;
    public static ArrayList<BaseHero> whiteSide;
    public static ArrayList<BaseHero> darkSide;
    public static ArrayList<BaseHero> allUnits;
    public static ArrayList<String> namesHero;
    public static Scanner iScanner = new Scanner(System.in, "cp866");
    static boolean GameOver = false;

    public static void main(String[] args) {

        init();                             // инициализация двух команд
        System.out.println(" -- НАЧАЛО БОЯ ---");

        ConsoleView.view();                 // вывести начальную граф/табличку состояния обеих команд

        do {
            System.out.println();
            System.out.println("Нажмите кнопку Enter... (0 - завершение игры)");
            String str = iScanner.nextLine();
            if (str.equals("0"))
                GameOver = true;            // если ввели 0 - то завершение игры
            makeStep();                     // сделать STEP для всех юнитов
            ConsoleView.view();             // вывести псевдографику
        } while (!GameOver);                // завершить цикл если GameOver

        iScanner.close();
    }

    
    // -----------------------------------------------------------------------------------
    // ИНИЦИАЛИЗАЦИЯ ДВУХ КОМАНД
    public static void init(){
        whiteSide = new ArrayList<>();
        darkSide = new ArrayList<>();
        allUnits = new ArrayList<>();

        // список имен для созданных героев (чтобы проверять на повторы)
        namesHero = new ArrayList<>();

        // создаем команду №1
        for (int i = 0; i < 10; i++) {
            switch (new Random().nextInt(4)) {
                case 0:
                    whiteSide.add(new Priest(1, createNames(), 0, i));
                    break;
                case 1:
                    whiteSide.add(new Robber(1, createNames(), 0, i));
                    break;
                case 2:
                    whiteSide.add(new Sniper(1, createNames(), 0, i));
                    break;
                case 3:
                    whiteSide.add(new Peasant(1, createNames(), 0, i));
                    break;
            }
        }
        // сортировка команды
        whiteSide.sort(new Comparator<BaseHero>() {
            @Override
            public int compare(BaseHero u1, BaseHero u2) {
                if (u2.getSpeed() - u1.getSpeed() == 0)
                    return (int)(u2.getHp() - (int)(u1.getHp()));
                return (u2.getSpeed() - u1.getSpeed());
            }
        });

        // создаем команду №2 и выводим список в терминал
        for (int i = 0; i < 10; i++) {
            switch (new Random().nextInt(4)) {
                case 0:
                    darkSide.add(new Magician(2, createNames(), 9, i));
                    break;
                case 1:
                    darkSide.add(new Spearman(2, createNames(), 9, i));
                    break;
                case 2:
                    darkSide.add(new Crossbowman(2, createNames(), 9, i));
                    break;
                case 3:
                    darkSide.add(new Peasant(2, createNames(), 9, i));
                    break;
            }
        }
        // сортировка команды
        darkSide.sort(new Comparator<BaseHero>() {
            @Override
            public int compare(BaseHero u1, BaseHero u2) {
                if (u2.getSpeed() - u1.getSpeed() == 0)
                    return (int)(u2.getHp() - (int)(u1.getHp()));
                return (u2.getSpeed() - u1.getSpeed());
            }
        });

        // создание ОБШЕЙ команды и сортировка ее по скорости героя
        allUnits.addAll(whiteSide);
        allUnits.addAll(darkSide);
        allUnits.sort(new Comparator<BaseHero>() {
            @Override
            public int compare(BaseHero u1, BaseHero u2) {
                if (u2.getSpeed() - u1.getSpeed() == 0)
                    return (int)(u2.getHp() - (int)(u1.getHp()));
                return (u2.getSpeed() - u1.getSpeed());
            }
        });
    }

    // метод STEP для обеих команд
    private static void makeStep(){
        BaseHero hero = whiteSide.get(0);
        for (int i = 0; i < GANG_SIZE; i++) {
            hero = whiteSide.get(i);
            hero.step(allUnits);
            if (hero.getGameOver() > 0) break;
            hero = darkSide.get(i);
            hero.step(allUnits);
            if (hero.getGameOver() > 0) break;
        }
        if (hero.getGameOver() > 0){
            GameOver = true;
            if (hero.getGameOver() == 1)
                System.out.println("\n  <<< Победила команда Green !!! >>>\n");
            else
                System.out.println("\n  <<< Победила команда Blue !!! >>>\n");
        }
    }

    /**
     * метод рандомного выбора имени из списка NamesFantazy.java
     * 
     * @return String name
     */
    private static String createNames() {
        String nameRandom;
        do {
            nameRandom = NamesFantazy.values()[new Random().nextInt(NamesFantazy.values().length)].toString();
        } while (namesHero.size() > 0 && namesHero.contains(nameRandom));
        namesHero.add(nameRandom);
        return nameRandom;
    }
}