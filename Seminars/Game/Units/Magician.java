/** Magician - класс МАГОВ или КОЛДУНОВ (потомок MagClass) */

package Seminars.Game.Units;

import Seminars.Game.Units.GameInterface;

public class Magician extends MagicClass implements GameInterface{

    int magicPower;
    public Magician(int team, String name, int x, int y) {
        super(30, 30, 9, 5, 5, 12, 17, 1, team, name, x, y);
    }

    @Override
    public String getClassName(){
        return "Колдун";
    }

    @Override
    public String getCharName(){
        return String.format("\u16DD");
    }
}