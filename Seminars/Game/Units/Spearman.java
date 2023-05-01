// класс КОПЕЙЩИКОВ - потомок класса InfantryClass

package Seminars.Game.Units;

public class Spearman extends InfantryClass implements GameInterface{

    public Spearman(int team, String name, int x, int y) {
        super(50, 50, 4, 1, 3, 5, 4, team, name, x, y);
    }

    @Override
    public String getClassName(){
        return "Пехотинец";
    }

    @Override
    public String getCharName(){
        return "П";
    }
}