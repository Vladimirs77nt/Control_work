// класс РАЗБОЙНИКОВ - потомок класса InfantryClass

package Seminars.Game.Units;

import Seminars.Game.Units.GameInterface;

public class Robber extends InfantryClass implements GameInterface{

    public Robber(int team, String name, int x, int y) {
        super(20, 20, 6, 2, 4, 3, 8, team, name, x, y);
    }

    @Override
    public String getClassName(){
        return "Разбойник";
    }

    @Override
    public String getCharName(){
        return "R";
    }
}