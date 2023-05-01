/** 1. MagicClacc - класс магических героев (потомок класса BaseHero)
это родительский класс для:
1.1. Priest - класс МОНАХОВ (потомок MagClass)
1.2. Magician - класс МАГОВ (потомок MagClass)
 */

 package Seminars.Game.Units;

import java.util.ArrayList;

public abstract class MagicClass extends BaseHero{
 
     protected int magicPower; // магическая сила персонажа
 
     public MagicClass(float hp, float maxHp, int speed, int damage, int damageMax, int defence, int attack, int magicPower, int team,
                        String name, int x, int y) {
        super(hp, maxHp, speed, damage, damageMax, defence, attack, team, name, x, y);
        this.magicPower = magicPower;
     }

     @Override
     public String getInfo(){
        return super.getInfo() + String.format("\t    ");
     }

     @Override
     public void step(ArrayList<BaseHero> teamArray) {
         if (hp<=0) hp = 0;
         else {
             System.out.printf("(%d) %s %s:\n    > готов помочь своей магической силой!\n", this.getTeam(), this.getClassName(), this.getName());
             int friendIndex = nearestIndexFriend(teamArray);
             if (friendIndex>=0) {
                 BaseHero friend = teamArray.get(friendIndex);
                 System.out.printf("    > Я выбрал кому помочь! -> %s %s\n", friend.getClassName(), friend.getName());
                 System.out.print("    > Вызвал заклинание исцеления !\n");
                 this.healing(friend);
             }
             else System.out.print("    > Мне исцелять некого...\n");
         }
     }
 }