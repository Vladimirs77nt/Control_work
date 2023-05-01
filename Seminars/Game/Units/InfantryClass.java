/** 2. InfantryClass - класс бойцов ближнего боя / пехотинцев (потомок класса BaseHero)
это родительский класс для:
2.1. Robber - класс РАЗБОЙНИКОВ (потомок класса InfantryClass)
2.2. Spearman - класс КОПЕЙЩИКОВ (потомок класса InfantryClass)
*/

package Seminars.Game.Units;

import java.util.ArrayList;

public abstract class InfantryClass extends BaseHero {

    public InfantryClass(float hp, float maxHp, int speed, int damage, int damageMax, int defence, int attack, int team,
                        String name, int x, int y) {
        super(hp, maxHp, speed, damage, damageMax, defence, attack, team, name, x, y);
    }

    @Override
    public String getInfo(){
        return super.getInfo() + String.format("\t    ");
    }

    @Override
    public void step(ArrayList<BaseHero> teamArray) {
        if (hp<=0) hp = 0;
        else {
            System.out.printf("(%d) %s %s:\n    > К бою готов!\n", this.getTeam(), this.getClassName(), this.getName());
            int opponentIndex = nearestIndexEnemy(teamArray);
            if (opponentIndex<0) this.setGameOver(team);
            else {
                BaseHero opponent = teamArray.get(opponentIndex);
                System.out.printf("    > Я выбрал цель! -> %s %s", opponent.getClassName(), opponent.getName());
                double len = position.getDistance(opponent);
                System.out.printf("  (расстояние до цели: %.1f)\n", len);
                if (len<=1.5){
                    System.out.print("    > Атакую !");
                    this.attack(opponent);
                } else {
                    this.move(opponent);
                }
            }
        }
    }
}