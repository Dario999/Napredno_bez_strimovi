package Aud4;

interface Alien{

    public int getDamage();

}

class SnakeAlien implements Alien{

    private String name;
    private int health;

    @Override
    public int getDamage() {
        return 10;
    }
}

class OgreAlien implements Alien{

    private String name;
    private int health;

    @Override
    public int getDamage() {
        return 6;
    }
}

class MarshmallowALien implements Alien{

    private String name;
    private int health;

    @Override
    public int getDamage() {
        return 1;
    }
}

public class AliensTest {

    private Alien[] aliens;

    public AliensTest(int num_Aliens){
        aliens = new Alien[num_Aliens];
    }

    public void addAlien(Alien alien,int index){
        if(index >= aliens.length){
            System.out.println("Index out of bound for adding Alien");
            return;
        }
        aliens[index] = alien;
    }

    public int calculateDamage(){
        int damage = 0;
        for (int i = 0; i < aliens.length; i++) {
            damage += aliens[i].getDamage();
        }
        return damage;
    }


    public static void main(String[] args) {

        AliensTest aliensTest = new AliensTest(4);

        SnakeAlien snakeAlien = new SnakeAlien();
        SnakeAlien snakeAlien1 = new SnakeAlien();
        OgreAlien ogreAlien = new OgreAlien();
        MarshmallowALien marshmallowALien = new MarshmallowALien();

        aliensTest.addAlien(snakeAlien,0);
        aliensTest.addAlien(ogreAlien,1);
        aliensTest.addAlien(marshmallowALien,2);
        aliensTest.addAlien(snakeAlien1,3);
        aliensTest.addAlien(snakeAlien1,4); // index out of bound test

        System.out.println(aliensTest.calculateDamage());




    }

}
