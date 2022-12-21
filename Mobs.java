import java.util.concurrent.ThreadLocalRandom;

public class Mobs {
    public int skeleHitPoints = 15;
    public int skeleAttackMin = 5;
    public int skeleAttackMax = 10;
    public int skeleDefenceMin = 3;
    public int skeleDefenceMax = 6;

    public int zombieHitPoints = 15;
    public int zombieAttackMin = 5;
    public int zombieAttackMax = 8;
    public int zombieDefenceMin = 3;
    public int zombieDefenceMax = 6;

    public int vampireHitPoints = 20;
    public int vampireAttackMin = 5;
    public int vampireAttackMax = 15;
    public int vampireDefenceMin = 3;
    public int vampireDefenceMax = 6;

    public int skeleAttackRoll;
    public int skeleDefenceRoll;
    public int zombieAttackRoll;
    public int zombieDefenceRoll;
    public int vampireAttackRoll;
    public int vampireDefenceRoll;

    private Mobs() {}

    private static final Mobs mobsInstance = new Mobs();

    public static Mobs getMobsInstance(){
        return mobsInstance;
    }

    public void runRolls(){
       skeleAttackRoll = ThreadLocalRandom.current().nextInt(skeleAttackMin, skeleAttackMax + 1);
       skeleDefenceRoll = ThreadLocalRandom.current().nextInt(skeleDefenceMin, skeleDefenceMax + 1);
       zombieAttackRoll = ThreadLocalRandom.current().nextInt(zombieAttackMin, zombieAttackMax + 1);
       zombieDefenceRoll = ThreadLocalRandom.current().nextInt(zombieDefenceMin, zombieDefenceMax + 1);
       vampireAttackRoll = ThreadLocalRandom.current().nextInt(vampireAttackMin, vampireAttackMax + 1);
       vampireDefenceRoll = ThreadLocalRandom.current().nextInt(vampireDefenceMin, vampireDefenceMax + 1);
    }
}