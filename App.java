import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App extends Application {

    Stage window;
    int heroesSelected;
    Fight fight = new Fight();
    Label attackerLabel;
    Label attackedLabel;
    Label hitAmountLabel;
    Label mageHP;
    Label warriorHP;
    Label archerHP;
    Button zombie;
    Button skele;
    Button vampire;
    Button mage;
    Button warrior;
    Button archer;

    boolean mageTurn;
    boolean warriorTurn;
    boolean archerTurn;
    
    static boolean mageOut;
    static boolean warriorOut;
    static boolean archerOut;
    static boolean skeleOut;
    static boolean zombieOut;
    static boolean vampireOut;

    Heroes heroes = Heroes.getHeroesInstance();
    Mobs mobs = Mobs.getMobsInstance();

    public static void main(String[] args) {
        launch(args);
     }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("SA");

        // Heroes heroes = new Heroes();
        // Heroes heroes = Heroes.getHeroesInstance();
        // Mobs mobs = Mobs.getMobsInstance();
        

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(8);

        Label skeleHP = new Label("" + mobs.skeleHitPoints);
        GridPane.setConstraints(skeleHP, 0, 0);
        skele = new Button("Skele");
        GridPane.setConstraints(skele, 1, 0);
        skele.setId("mobbutton");

        Label zombieHP = new Label("" + mobs.zombieHitPoints);
        GridPane.setConstraints(zombieHP, 0, 1);
        zombie = new Button("Zombie");
        GridPane.setConstraints(zombie, 1, 1);
        zombie.setId("mobbutton");

        Label vampireHP = new Label("" + mobs.vampireHitPoints);
        GridPane.setConstraints(vampireHP, 0, 2);
        vampire = new Button("Vampire");
        GridPane.setConstraints(vampire, 1, 2);
        vampire.setId("mobbutton");

        mageHP = new Label("" + heroes.mageHitPoints);
        GridPane.setConstraints(mageHP, 3, 0);
        mage = new Button("Mage");
        GridPane.setConstraints(mage, 2, 0);
        mage.setId("herobutton");

        warriorHP = new Label("" + heroes.warriorHitPoints);
        GridPane.setConstraints(warriorHP, 3, 1);
        warrior = new Button("Warrior");
        GridPane.setConstraints(warrior, 2, 1);
        warrior.setId("herobutton");

        archerHP = new Label("" + heroes.archerHitPoints);
        GridPane.setConstraints(archerHP, 3, 2);
        archer = new Button("Archer");
        GridPane.setConstraints(archer, 2, 2);
        archer.setId("herobutton");

        Label hitLabel = new Label("Hit Amount: ");
        GridPane.setConstraints(hitLabel, 1, 3);
        hitAmountLabel = new Label();
        GridPane.setConstraints(hitAmountLabel, 2, 3);

        attackerLabel = new Label();
        GridPane.setConstraints(attackerLabel, 1, 4);

        attackedLabel = new Label();
        GridPane.setConstraints(attackedLabel, 2, 4);

        Button nextRound = new Button("Next");
        GridPane.setConstraints(nextRound, 1, 5);

        grid.getChildren().addAll(skele, skeleHP, zombie, zombieHP, vampire, vampireHP, mage, mageHP, warrior, warriorHP, archer, archerHP, hitLabel, hitAmountLabel, attackerLabel, attackedLabel, nextRound);

        // mage.setSelected(true);


        Scene scene = new Scene(grid, 400, 400);
        window.setScene(scene); 
        window.show();
        scene.getStylesheets().add("style.css");

        mage.setOnAction(e -> {
            heroesSelected = 0;
            mage.setStyle("-fx-background-color: purple; -fx-text-fill: white;");
            attackerLabel.setText("Mage hits");
        });

        warrior.setOnAction(e -> {
            heroesSelected = 1;
            warrior.setStyle("-fx-background-color: purple; -fx-text-fill: white;");
            attackerLabel.setText("Warrior hits");

        });

        archer.setOnAction(e -> {
            heroesSelected = 2;
            archer.setStyle("-fx-background-color: purple; -fx-text-fill: white;");
            attackerLabel.setText("Archer hits");
        });

        skele.setOnAction(e -> {
            skele.setStyle("-fx-background-color: red; -fx-text-fill: white;");
            int hitAmount = 0;
            switch (heroesSelected){
                case 0:
                    hitAmount = fight.heroBattle(mage.getText(), skele.getText());
                    mageTurn = true;
                    break;
                case 1:
                    hitAmount = fight.heroBattle(warrior.getText(), skele.getText());
                    warriorTurn = true;
                    break;
                case 2:
                    hitAmount = fight.heroBattle(archer.getText(), skele.getText());
                    archerTurn = true;
                    break;
            }
            attackedLabel.setText("Skele");
            skeleHP.setText("" + mobs.skeleHitPoints);
            CheckForMiss(hitAmount);
            disableButtons(true);
            delay(1500, () -> mobFight());
        });

        zombie.setOnAction(e -> {
            int hitAmount = 0;
            zombie.setStyle("-fx-background-color: red; -fx-text-fill: white;"); 
            switch (heroesSelected){
                case 0:
                    hitAmount = fight.heroBattle(mage.getText(), zombie.getText());
                    mageTurn = true;
                    break;
                case 1:
                    hitAmount = fight.heroBattle(warrior.getText(), zombie.getText());
                    warriorTurn = true;
                    break;
                case 2:
                    hitAmount = fight.heroBattle(archer.getText(), zombie.getText());
                    archerTurn = true;
                    break;
            }
            attackedLabel.setText("Zombie");
            zombieHP.setText("" + mobs.zombieHitPoints);
            CheckForMiss(hitAmount);
            disableButtons(true);
            delay(1500, () -> mobFight());
        });

        vampire.setOnAction(e -> {
            vampire.setStyle("-fx-background-color: red; -fx-text-fill: white;");
            int hitAmount = 0;
            switch (heroesSelected){
                case 0:
                    hitAmount = fight.heroBattle(mage.getText(), vampire.getText());
                    mageTurn = true;
                    break;
                case 1:
                    hitAmount = fight.heroBattle(warrior.getText(), vampire.getText());
                    warriorTurn = true;
                    break;
                case 2:
                    hitAmount = fight.heroBattle(archer.getText(), vampire.getText());
                    archerTurn = true;
                    break;
            }
            attackedLabel.setText("Vampire");
            vampireHP.setText("" + mobs.vampireHitPoints);
            CheckForMiss(hitAmount);
            disableButtons(true);
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
        ResetButtons();
        HealthCheck();
        List <FightInfo> fightDetails = new ArrayList<>();
        fightDetails = fight.mobBattle();

        String mobChoice = "";
        String heroChoice = "";
        int damage = 0;

        if (fightDetails != null){
            for (FightInfo info : fightDetails){
                mobChoice = info.getMobChoice();
                heroChoice = info.getHeroChoice();
                damage = info.getDamage();
            }
    
            // List<FightInfo> finfo = Arrays.asList(fightDetails);
            // Stream<FightInfo> fightStream = Stream.of(fightDetails);
            // fightDetails.map(FightInfo::getMobChoice).forEach(System.out::println);
            // List<FightInfo> fino = fightDetails.stream().
            // List<FightInfo> fino = fightDetails.stream().filter(FightInfo::isTrue).collect(Collectors.toList());
    
            // fino.forEach(System.out::println);
    
            Heroes heroes = Heroes.getHeroesInstance();
            mageHP.setText("" + heroes.mageHitPoints);
            warriorHP.setText("" + heroes.warriorHitPoints);
            archerHP.setText("" + heroes.archerHitPoints);
            attackerLabel.setText("" + mobChoice + " hits");
            attackedLabel.setText("" + heroChoice);
    
            CheckForMiss(damage);
        } else {
            System.out.println("Game Over");
        }


        
    }

    void CheckForMiss(int hitAmount){
        if (hitAmount != 0){
            hitAmountLabel.setText("" + hitAmount);
            } else {
                hitAmountLabel.setText("Miss");
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

  public void ResetButtons(){
    zombie.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
    skele.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
    vampire.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
    mage.setStyle("-fx-background-color: green; -fx-text-fill: white;");
    warrior.setStyle("-fx-background-color: green; -fx-text-fill: white;");
    archer.setStyle("-fx-background-color: green; -fx-text-fill: white;");
    skele.setDisable(false);
    zombie.setDisable(false);
    vampire.setDisable(false);
    mage.setDisable(false);
    warrior.setDisable(false);
    archer.setDisable(false);
  }

  public void disableButtons(boolean check){
    if (!skeleOut) skele.setDisable(check);
    if (!zombieOut) zombie.setDisable(check);
    if (!vampireOut) vampire.setDisable(check);
    if (!mageOut) mage.setDisable(check);
    if (!warriorOut) warrior.setDisable(check);
    if (!archerOut) archer.setDisable(check);
  }

  public void TurnCheck(){
    if (mageTurn && warriorTurn && archerTurn){
        disableButtons(false);
        return;
    } 
    if (mageTurn){
        mage.setDisable(true);
    }  
    if (warriorTurn){
        warrior.setDisable(true);
    } 
    if (archerTurn){
        archer.setDisable(true);
    }
  }

  public void HealthCheck(){
    if (mobs.skeleHitPoints < 1){
        skeleOut = true;
        skele.setDisable(true);
    } 
    if (mobs.zombieHitPoints < 1){
        zombieOut = true;
        zombie.setDisable(true);
    } 
    if (mobs.vampireHitPoints < 1){
        vampireOut = true;
        vampire.setDisable(true);
    } 
    if (heroes.mageHitPoints < 1){
        mageOut = true;
        mage.setDisable(true);
    } 
    if (heroes.warriorHitPoints < 1){
        warriorOut = true;
        warrior.setDisable(warriorOut);
    } 
    if (heroes.archerHitPoints < 1){
        archerOut = true;
        archer.setDisable(archerOut);
    }
  }


}
