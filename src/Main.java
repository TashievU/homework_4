import javax.swing.*;
import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefence;
    public static int[] heroesHealth = {280, 270, 250, 400, 240, 260, 230,220};
    public static int[] heroesDamage = {10, 15, 20, 5, 10, 15, 10 , 0};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Golem", "Lucky", "Thor", "Berserk", "Medical"};
    public static int roundNumber = 0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            playRound();
        }
    }
    public static void medicHealthe(){
        for (int i = 0; i < heroesHealth.length; i++) {
            Random random = new Random();
            int randomIndex = random.nextInt(heroesAttackType.length);
            if( i == 3){
                continue;
            }
            if(heroesHealth[i] > 0 && heroesHealth[i] < 100){
                heroesHealth[i] += 50;
            }
            System.out.println("Medic hell: " + heroesAttackType[randomIndex]);
            break;
        }
    }
    public static void golem(){
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[4] > 0 && heroesHealth[i] > 0 && heroesHealth[4] != heroesHealth[i]) {
                heroesHealth[4] += bossDamage / 5;
                heroesHealth[i] -= bossDamage / 5;
            }
        }
    }
    public static void lucky(){
        Random random = new Random();
        int randomaiser = random.nextInt(5);
        switch (randomaiser){
            case 1:
                heroesHealth[4] = heroesHealth[4] + bossDamage;
                System.out.println("Lucky");
            case 2:

            case 3:

            case 4:

        }
    }
    public static void thor(){
        Random random = new Random();
        boolean randomaiser = random.nextBoolean();
        if (randomaiser){
            System.out.println("Босс оглушен");
            bossDamage = 0;
        }else{
            bossDamage = 50;
        }
    }

    public static void berserk(){
        Random random = new Random();
        int randomIndex = random.nextInt(3) + 1;
        if (heroesHealth[5] > 0 && bossHealth > 0) {
            heroesHealth[5] = bossDamage / 5;
            heroesDamage[5] = bossDamage * randomIndex;
        }
        System.out.println("Берсерк крит урон " + heroesDamage[randomIndex]);
    }


    public static void playRound() {
        roundNumber++;
        chooseBossDefence();
        thor();
        bossHits();
        heroesHit();
        printStatistics();
        medicHealthe();
        golem();
        lucky();
        berserk();
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length - 1); // 0,1,2
        bossDefence = heroesAttackType[randomIndex];

    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefence == heroesAttackType[i]) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2; // 2,3,4,5,6,7,8,9,10
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println("Critical damage: " + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int healthOfCurrentHero : heroesHealth) {
            if (healthOfCurrentHero > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void printStatistics() {
        if (roundNumber == 0) {
            System.out.println("BEFORE START -------------");
        } else {
            System.out.println("ROUND " + roundNumber + " -------------");
        }
        /*String value;
        if (bossDefence == null) {
            value = "No defence";
        } else {
            value = bossDefence;
        }*/
        System.out.println("Boss health: " + bossHealth + "; damage: "
                + bossDamage + "; defence: "
                + (bossDefence == null ? "No defence" : bossDefence));
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " +
                    heroesHealth[i] + "; damage: " + heroesDamage[i]);
        }
    }
}