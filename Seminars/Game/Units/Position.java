package Seminars.Game.Units;

import java.util.ArrayList;

public class Position{
    protected int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Метод вычисления расстояния до указанного героя
     * @param unit - указанный герой
     * @return - double - расстояние до героя
     */
    public double getDistance(BaseHero unit){
        return Math.sqrt(Math.pow(x - unit.position.x, 2) + Math.pow(y - unit.position.y, 2));
    }

    public boolean isEquals(Position position){
        return (position.x == x && position.y == y);
    }

    public int[] getDirection(BaseHero unit){
        int dx = Math.abs(unit.position.x - x);
        int dy = Math.abs(unit.position.y - y);
        if (dx>=dy){
            dx = (unit.position.x - x)/Math.abs(unit.position.x - x);
            dy = 0;
        } else {
            dy = (unit.position.y - y)/Math.abs(unit.position.y - y);
            dx = 0;
        }
        int [] dir = new int[] {dx, dy};
        return dir;
    }

    public boolean chekPosition(Position position, ArrayList<BaseHero> teamArray){
        for (BaseHero hero : teamArray) {
            if (hero.getPosition().isEquals(position) && hero.getHp()>0)
                return false;
        }
        return true;
    }
}