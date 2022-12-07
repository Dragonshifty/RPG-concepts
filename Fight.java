import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Fight {
    Heroes heroes = Heroes.getHeroesInstance();
    Mobs mobs = Mobs.getMobsInstance();

    public int heroBattle(String hero, String mob){
        int hitAmount = 0;
        heroes.runRolls();
        mobs.runRolls();

        switch(hero){
            case "Mage":
                switch (mob){
                    case "Skele":
                        hitAmount = (heroes.mageAttackRoll - mobs.skeleDefenceRoll);
                        mobs.skeleHitPoints -= hitAmount;
                        break;
                    case "Zombie":
                        hitAmount = (heroes.mageAttackRoll - mobs.zombieDefenceRoll);
                        mobs.zombieHitPoints -= hitAmount;
                        break;
                    case "Vampire":
                        hitAmount = (heroes.mageAttackRoll - mobs.zombieDefenceRoll);
                        mobs.vampireHitPoints -= hitAmount;
                        break;
                }
                break;
            case "Warrior":
                switch (mob){
                    case "Skele":
                        hitAmount = (heroes.warriorAttackRoll - mobs.skeleDefenceRoll);
                        mobs.skeleHitPoints -= hitAmount;
                        break;
                    case "Zombie":
                        hitAmount = (heroes.warriorAttackRoll - mobs.zombieDefenceRoll);
                        mobs.zombieHitPoints -= hitAmount;
                        break;
                    case "Vampire":
                        hitAmount = (heroes.warriorAttackRoll - mobs.zombieDefenceRoll);
                        mobs.vampireHitPoints -= hitAmount;
                        break;
                }
                break;
            case "Archer":
                switch (mob){
                    case "Skele":
                        hitAmount = (heroes.archerAttackRoll - mobs.skeleDefenceRoll);
                        mobs.skeleHitPoints -= hitAmount;
                        break;
                    case "Zombie":
                        hitAmount = (heroes.archerAttackRoll - mobs.zombieDefenceRoll);
                        mobs.zombieHitPoints -= hitAmount;
                        break;
                    case "Vampire":
                        hitAmount = (heroes.archerAttackRoll - mobs.zombieDefenceRoll);
                        mobs.vampireHitPoints -= hitAmount;
                        break;
                }
                break;
        }
        return hitAmount;
    }

    public List<FightInfo> mobBattle(){
    // public Object[] mobBattle(){    
        Random random = new Random();
        int heroChoice = random.nextInt(3);
        int mobChoice = random.nextInt(3);
        heroes.runRolls();
        mobs.runRolls();
        String[] heroArray = {"Mage", "Warrior", "Archer"};
        String[] mobArray = {"Skele", "Zombie", "Vampire"};
        int hitAmount = 0;
        // Object[] returnInfo = new Object[3];
        // returnInfo[0] = mobArray[mobChoice];
        // returnInfo[1] = heroArray[heroChoice];
        


        switch(heroChoice){
            case 0:
                switch (mobArray[mobChoice]){
                    case "Skele":
                        hitAmount = (mobs.skeleAttackRoll - heroes.mageDefenceRoll);
                        break;
                    case "Zombie":
                        hitAmount = (mobs.zombieAttackRoll - heroes.mageDefenceRoll);
                        break;
                    case "Vampire":
                        hitAmount = (mobs.vampireAttackRoll - heroes.mageDefenceRoll);
                        break;
                }
                heroes.mageHitPoints -= hitAmount;
                break;
            case 1:
                switch (mobArray[mobChoice]){
                    case "Skele":
                        hitAmount = (mobs.skeleAttackRoll- heroes.warriorDefenceRoll);                       
                        break;
                    case "Zombie":
                        hitAmount = (mobs.zombieAttackRoll - heroes.warriorDefenceRoll);
                        break;
                    case "Vampire":
                        hitAmount = (mobs.vampireAttackRoll - heroes.warriorDefenceRoll);
                        break;
                }
                heroes.warriorHitPoints -= hitAmount;
                break;
            case 2:
                switch (mobArray[mobChoice]){
                    case "Skele":
                        hitAmount = (mobs.skeleAttackRoll - heroes.archerDefenceRoll);
                        break;
                    case "Zombie":
                        hitAmount = (mobs.zombieAttackRoll - heroes.archerDefenceRoll);
                        break;
                    case "Vampire":
                        hitAmount = (mobs.vampireAttackRoll - heroes.archerDefenceRoll);
                        break;
                }
                heroes.archerHitPoints -= hitAmount;
                break;
        }
        // returnInfo [2] = Integer.valueOf(hitAmount);
        hitAmount = (hitAmount < 1) ? 0 : hitAmount;
        List <FightInfo> fightInfoReturn = new ArrayList<>();
        fightInfoReturn.add(new FightInfo(mobArray[mobChoice], heroArray[heroChoice], Integer.valueOf(hitAmount)));
        
        return fightInfoReturn;
        // return returnInfo;
    }
}
