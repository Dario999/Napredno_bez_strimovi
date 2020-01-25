package Aud4;

public class AlienPack1 {

    private Alien1[] aliens;

    public AlienPack1(int numAliens){
        aliens = new Alien1[numAliens];
    }

    public void addAlien(Alien1 newAlien,int index){
        aliens[index] = newAlien;
    }

    public Alien1[] getAliens(){
        return aliens;
    }

    public int calculateDamage(){
        int damage = 0;
        for (int i = 0; i < aliens.length; i++) {
            if(aliens[i].type == Alien1.SNAKE_ALIEN){
                damage += 10;
            }else if(aliens[i].type == Alien1.OGRE_ALIEN){
                damage += 6;
            }else if(aliens[i].type == Alien1.MARSHMALLOW_MAN_ALIEN){
                damage += 1;
            }
        }
        return damage;
    }

}
