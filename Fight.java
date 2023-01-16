import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Fight {
    Heroes heroes = Heroes.getHeroesInstance();
    Mobs mobs = Mobs.getMobsInstance();
    boolean gameOver = false;
    int heroChoice;
    int mobChoice;

    public int heroBattle(String hero, String mob){
        int hitAmount = 0;
        heroes.runRolls();
        mobs.runRolls();

        switch(hero){
            case "Mage":
                switch (mob){
                    case "Skele":
                        hitAmount = (heroes.mageAttackRoll - mobs.skeleDefenceRoll);
                        hitAmount = (hitAmount < 0) ? 0 : hitAmount;
                        mobs.skeleHitPoints -= hitAmount;
                        break;
                    case "Zombie":
                        hitAmount = (heroes.mageAttackRoll - mobs.zombieDefenceRoll);
                        hitAmount = (hitAmount < 0) ? 0 : hitAmount;
                        mobs.zombieHitPoints -= hitAmount;
                        break;
                    case "Vampire":
                        hitAmount = (heroes.mageAttackRoll - mobs.zombieDefenceRoll);
                        hitAmount = (hitAmount < 0) ? 0 : hitAmount;
                        mobs.vampireHitPoints -= hitAmount;
                        break;
                }
                break;
            case "Warrior":
                switch (mob){
                    case "Skele":
                        hitAmount = (heroes.warriorAttackRoll - mobs.skeleDefenceRoll);
                        hitAmount = (hitAmount < 0) ? 0 : hitAmount;
                        mobs.skeleHitPoints -= hitAmount;
                        break;
                    case "Zombie":
                        hitAmount = (heroes.warriorAttackRoll - mobs.zombieDefenceRoll);
                        hitAmount = (hitAmount < 0) ? 0 : hitAmount;
                        mobs.zombieHitPoints -= hitAmount;
                        break;
                    case "Vampire":
                        hitAmount = (heroes.warriorAttackRoll - mobs.zombieDefenceRoll);
                        hitAmount = (hitAmount < 0) ? 0 : hitAmount;
                        mobs.vampireHitPoints -= hitAmount;
                        break;
                }
                break;
            case "Archer":
                switch (mob){
                    case "Skele":
                        hitAmount = (heroes.archerAttackRoll - mobs.skeleDefenceRoll);
                        hitAmount = (hitAmount < 0) ? 0 : hitAmount;
                        mobs.skeleHitPoints -= hitAmount;
                        break;
                    case "Zombie":
                        hitAmount = (heroes.archerAttackRoll - mobs.zombieDefenceRoll);
                        hitAmount = (hitAmount < 0) ? 0 : hitAmount;
                        mobs.zombieHitPoints -= hitAmount;
                        break;
                    case "Vampire":
                        hitAmount = (heroes.archerAttackRoll - mobs.zombieDefenceRoll);
                        hitAmount = (hitAmount < 0) ? 0 : hitAmount;
                        mobs.vampireHitPoints -= hitAmount;
                        break;
                }
                break;
        }
        return hitAmount;
    }

    public List<FightInfo> mobBattle(){
        StillInCheck();

        if (!gameOver){

            boolean heroProceed = false;
            boolean mobProceed = false;

            while (!heroProceed){
                heroChoice = heroRandomiser();
                switch(heroChoice){
                    case 0:
                        if (!App.mageOut){
                            heroProceed = true;
                        }
                        break;
                    case 1:
                        if (!App.warriorOut){
                            heroProceed = true;
                        }
                        break;
                    case 2:
                        if (!App.archerOut){
                            heroProceed = true;
                        }
                }
            }

            while (!mobProceed){
                mobChoice = mobRandomiser();
                switch (mobChoice){
                    case 0:
                        if (!App.zombieOut){
                            mobProceed = true;
                        }
                        break;
                    case 1:
                        if (!App.skeleOut){
                            mobProceed = true;
                        }
                        break;
                    case 2:
                        if (!App.vampireOut){
                            mobProceed = true;
                        }
                        break;
                }
            }

            // int heroChoice = heroRandomiser();
            // int mobChoice = mobRandomiser();

            heroes.runRolls();
            mobs.runRolls();
            String[] heroArray = {"Mage", "Warrior", "Archer"};
            String[] mobArray = {"Skele", "Zombie", "Vampire"};
            int hitAmount = 0;

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
    
            hitAmount = (hitAmount < 1) ? 0 : hitAmount;
            List <FightInfo> fightInfoReturn = new ArrayList<>();
            fightInfoReturn.add(new FightInfo(mobArray[mobChoice], heroArray[heroChoice], Integer.valueOf(hitAmount)));
            
            return fightInfoReturn;
        } else {
            List <FightInfo> fightInfoReturn = new ArrayList<>();
            fightInfoReturn = null;
            return fightInfoReturn;
        }
    }

    void StillInCheck(){
        if (App.mageOut && App.warriorOut && App.archerOut){
            System.out.println("Game Over");
            gameOver = true;
        }

        if (App.zombieOut && App.skeleOut && App.vampireOut){
            System.out.println("Game Over, you win");
            gameOver = true;
        }
    }

    int mobRandomiser(){
        Random random = new Random();
        int mobChoice = random.nextInt(3);
        return mobChoice;
    }

    int heroRandomiser(){
        Random random = new Random();
        int heroChoice = random.nextInt(3);
        return heroChoice;
    }
}
