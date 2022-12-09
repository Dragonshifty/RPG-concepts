import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class App extends Application {

    Stage window;
    int heroesSelected;
    Fight fight = new Fight();
    Label mobLabel;
    Label heroLabel;
    Label hitAmountLabel;
    Label mageHP;
    Label warriorHP;
    Label archerHP;
    Button zombie;
    Button skele;
    Button vampire;

    public static void main(String[] args) {
        launch(args);
     }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("SA");

        // Heroes heroes = new Heroes();
        Heroes heroes = Heroes.getHeroesInstance();
        Mobs mobs = Mobs.getMobsInstance();
        

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(8);

        Label skeleHP = new Label("" + mobs.skeleHitPoints);
        GridPane.setConstraints(skeleHP, 0, 0);
        skele = new Button("Skele");
        GridPane.setConstraints(skele, 1, 0);

        Label zombieHP = new Label("" + mobs.zombieHitPoints);
        GridPane.setConstraints(zombieHP, 0, 1);
        zombie = new Button("Zombie");
        GridPane.setConstraints(zombie, 1, 1);

        Label vampireHP = new Label("" + mobs.vampireHitPoints);
        GridPane.setConstraints(vampireHP, 0, 2);
        vampire = new Button("Vampire");
        GridPane.setConstraints(vampire, 1, 2);

        mageHP = new Label("" + heroes.mageHitPoints);
        GridPane.setConstraints(mageHP, 3, 0);
        ToggleButton mage = new ToggleButton("Mage");
        GridPane.setConstraints(mage, 2, 0);
        mage.requestFocus();

        warriorHP = new Label("" + heroes.warriorHitPoints);
        GridPane.setConstraints(warriorHP, 3, 1);
        ToggleButton warrior = new ToggleButton("Warrior");
        GridPane.setConstraints(warrior, 2, 1);

        archerHP = new Label("" + heroes.archerHitPoints);
        GridPane.setConstraints(archerHP, 3, 2);
        ToggleButton archer = new ToggleButton("Archer");
        GridPane.setConstraints(archer, 2, 2);

        Label hitLabel = new Label("Hit Amount: ");
        GridPane.setConstraints(hitLabel, 1, 3);
        hitAmountLabel = new Label();
        GridPane.setConstraints(hitAmountLabel, 2, 3);

        mobLabel = new Label();
        GridPane.setConstraints(mobLabel, 1, 4);

        heroLabel = new Label();
        GridPane.setConstraints(heroLabel, 2, 4);

        Button nextRound = new Button("Next");
        GridPane.setConstraints(nextRound, 1, 5);

        grid.getChildren().addAll(skele, skeleHP, zombie, zombieHP, vampire, vampireHP, mage, mageHP, warrior, warriorHP, archer, archerHP, hitLabel, hitAmountLabel, mobLabel, heroLabel, nextRound);

        mage.setSelected(true);


        Scene scene = new Scene(grid, 400, 400);
        window.setScene(scene); 
        window.show();
        scene.getStylesheets().add("style.css");

        ResetMobButtons();

        mage.setOnAction(e -> {
            heroesSelected = 0;
            mage.setSelected(true);
            warrior.setSelected(false);
            archer.setSelected(false);
        });

        warrior.setOnAction(e -> {
            heroesSelected = 1;
            warrior.setSelected(true);
            mage.setSelected(false);
            archer.setSelected(false);
        });

        archer.setOnAction(e -> {
            heroesSelected = 2;
            archer.setSelected(true);
            mage.setSelected(false);
            warrior.setSelected(false);
        });

        skele.setOnAction(e -> {
            skele.setStyle("-fx-background-color: red; -fx-text-fill: white;");
            int hitAmount = 0;
            switch (heroesSelected){
                case 0:
                    hitAmount = fight.heroBattle(mage.getText(), skele.getText());
                    heroLabel.setText("Mage");
                    break;
                case 1:
                    hitAmount = fight.heroBattle(warrior.getText(), skele.getText());
                    heroLabel.setText("Warrior");
                    break;
                case 2:
                    hitAmount = fight.heroBattle(archer.getText(), skele.getText());
                    heroLabel.setText("Archer");
                    break;
            }
            mobLabel.setText("Skele");
            skeleHP.setText("" + mobs.skeleHitPoints);
            hitAmountLabel.setText("" + hitAmount);
            delay(1500, () -> mobFight());
        });

        zombie.setOnAction(e -> {
            int hitAmount = 0;
            zombie.setStyle("-fx-background-color: red; -fx-text-fill: white;"); 
            switch (heroesSelected){
                case 0:
                    hitAmount = fight.heroBattle(mage.getText(), zombie.getText());
                    heroLabel.setText("Mage");
                    break;
                case 1:
                    hitAmount = fight.heroBattle(warrior.getText(), zombie.getText());
                    heroLabel.setText("Warrior");
                    break;
                case 2:
                    hitAmount = fight.heroBattle(archer.getText(), zombie.getText());
                    heroLabel.setText("Archer");
                    break;
            }
            mobLabel.setText("Zombie");
            zombieHP.setText("" + mobs.zombieHitPoints);
            System.out.println(mobs.zombieHitPoints);
            hitAmountLabel.setText("" + hitAmount);
            delay(1500, () -> mobFight());
        });

        vampire.setOnAction(e -> {
            vampire.setStyle("-fx-background-color: red; -fx-text-fill: white;");
            int hitAmount = 0;
            switch (heroesSelected){
                case 0:
                    hitAmount = fight.heroBattle(mage.getText(), vampire.getText());
                    heroLabel.setText("Mage");
                    break;
                case 1:
                    hitAmount = fight.heroBattle(warrior.getText(), vampire.getText());
                    heroLabel.setText("Warrior");
                    break;
                case 2:
                    hitAmount = fight.heroBattle(archer.getText(), vampire.getText());
                    heroLabel.setText("Archer");
                    break;
            }
            mobLabel.setText("Vampire");
            vampireHP.setText("" + mobs.vampireHitPoints);
            hitAmountLabel.setText("" + hitAmount);
            delay(1500, () -> mobFight());
        });

        nextRound.setOnAction(e -> {
            // System.out.println("First");
            // try {
            //     TimeUnit.SECONDS.sleep(2);
            // } catch (InterruptedException e1) {
            //     e1.printStackTrace();
            // }
            // System.out.println("Second");
            mobFight();
        });
      
    }

    void mobFight(){
        // Object[] fightDetails = new Object[3];
        ResetMobButtons();
        List <FightInfo> fightDetails = new ArrayList<>();
        fightDetails = fight.mobBattle();

        String mobChoice = "";
        String heroChoice = "";
        String damageString = "Miss";
        int damage = 0;


        for (FightInfo info : fightDetails){
            mobChoice = info.getMobChoice();
            heroChoice = info.getHeroChoice();
            damage = info.getDamage();
        }

        Heroes heroes = Heroes.getHeroesInstance();
        mageHP.setText("" + heroes.mageHitPoints);
        warriorHP.setText("" + heroes.warriorHitPoints);
        archerHP.setText("" + heroes.archerHitPoints);
        mobLabel.setText("" + mobChoice);
        heroLabel.setText("" + heroChoice);
        if (damage < 1){
            hitAmountLabel.setText(damageString);
        } else {
        hitAmountLabel.setText("" + damage);
        }
    }


public static void delay(long millis, Runnable continuation) {
    Task<Void> sleeper = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            try { Thread.sleep(millis); }
            catch (InterruptedException e) { }
            return null;
        }
    };
    sleeper.setOnSucceeded(event -> continuation.run());
    new Thread(sleeper).start();
  }

  public void ResetMobButtons(){
    zombie.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
    skele.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
    vampire.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
  }
}
