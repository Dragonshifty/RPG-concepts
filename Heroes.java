import java.util.concurrent.ThreadLocalRandom;

public class Heroes {
    protected int mageHitPoints = 20;
    protected int mageAttackMin = 3;
    protected int MageAttackMax = 14;
    protected int mageDefenceMin = 3;
    protected int mageDefenceMax = 6;

    protected int warriorHitPoints = 40;
    protected int warriorAttackMin = 3;
    protected int warriorAttackMax = 9;
    protected int warriorDefenceMin = 3;
    protected int warriorDefenceMax = 6;

    protected int archerHitPoints = 30;
    protected int archerAttackMin = 4;
    protected int archerAttackMax = 12;
    protected int archerDefenceMin = 3;
    protected int archerDefenceMax = 6;

    protected int mageAttackRoll;
    protected int mageDefenceRoll;
    protected int warriorAttackRoll;
    protected int warriorDefenceRoll;
    protected int archerAttackRoll;
    protected int archerDefenceRoll;

    private Heroes() {}

    private static final Heroes heroesInstance = new Heroes();

    public static Heroes getHeroesInstance (){
        return heroesInstance;
    }

    public void runRolls(){
       mageAttackRoll = ThreadLocalRandom.current().nextInt(mageAttackMin, MageAttackMax + 1);
       mageDefenceRoll = ThreadLocalRandom.current().nextInt(mageDefenceMin, mageDefenceMax + 1);
       warriorAttackRoll = ThreadLocalRandom.current().nextInt(warriorAttackMin, warriorAttackMax + 1);
       warriorDefenceRoll = ThreadLocalRandom.current().nextInt(warriorDefenceMin, warriorDefenceMax + 1);
       archerAttackRoll = ThreadLocalRandom.current().nextInt(archerAttackMin, archerAttackMax + 1);
       archerDefenceRoll = ThreadLocalRandom.current().nextInt(archerDefenceMin, archerDefenceMax + 1);
    }
}
