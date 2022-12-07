class FightInfo {
    private String heroChoice;
    private String mobChoice;
    private int damage;

    public FightInfo(String mobChoice, String heroChoice, int damage){
        if (heroChoice == null || mobChoice == null){
            throw new NullPointerException();
        }
        this.heroChoice = heroChoice;
        this.mobChoice = mobChoice;
        this.damage = damage;
    }

    public String getHeroChoice(){
        return heroChoice;
    }

    public String getMobChoice(){
        return mobChoice;
    }

    public int getDamage(){
        return damage;
    }
}
