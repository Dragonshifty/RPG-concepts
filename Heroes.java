import java.util.concurrent.ThreadLocalRandom;

public class Heroes {
    public int mageHitPoints = 20;
    public int mageAttackMin = 3;
    public int MageAttackMax = 14;
    public int mageDefenceMin = 3;
    public int mageDefenceMax = 6;

    public int warriorHitPoints = 40;
    public int warriorAttackMin = 3;
    public int warriorAttackMax = 9;
    public int warriorDefenceMin = 3;
    public int warriorDefenceMax = 6;

    public int archerHitPoints = 30;
    public int archerAttackMin = 4;
    public int archerAttackMax = 12;
    public int archerDefenceMin = 3;
    public int archerDefenceMax = 6;

    public int mageAttackRoll;
    public int mageDefenceRoll;
    public int warriorAttackRoll;
    public int warriorDefenceRoll;
    public int archerAttackRoll;
    public int archerDefenceRoll;

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
