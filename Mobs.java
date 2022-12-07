import java.util.concurrent.ThreadLocalRandom;

public class Mobs {
    protected int skeleHitPoints = 15;
    protected int skeleAttackMin = 5;
    protected int skeleAttackMax = 10;
    protected int skeleDefenceMin = 3;
    protected int skeleDefenceMax = 6;

    protected int zombieHitPoints = 15;
    protected int zombieAttackMin = 5;
    protected int zombieAttackMax = 8;
    protected int zombieDefenceMin = 3;
    protected int zombieDefenceMax = 6;

    protected int vampireHitPoints = 20;
    protected int vampireAttackMin = 5;
    protected int vampireAttackMax = 15;
    protected int vampireDefenceMin = 3;
    protected int vampireDefenceMax = 6;

    protected int skeleAttackRoll;
    protected int skeleDefenceRoll;
    protected int zombieAttackRoll;
    protected int zombieDefenceRoll;
    protected int vampireAttackRoll;
    protected int vampireDefenceRoll;

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