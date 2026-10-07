import java.util.ArrayList;
import java.util.HashSet;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Yakup Efe Akça 150124078
 * * GameLoop Class:
 * This is the core application class that drives the entire game.
 * It manages the main animation loop, entity spawning, collision detection,
 * user inputs, level transitions, and updates to the Heads-Up Display (HUD).
 */

public class GameLoop extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	public ArrayList<ParanormalEntity> currentActiveEntities = new ArrayList<ParanormalEntity>();
	public ArrayList<BaseToken> currentActiveTokens = new ArrayList<BaseToken>();
	AnimationTimer animation;

	Config config1;
	Rectangle borderLine;
	static int currentLevel = 1;
	
	double mouseX = 0;
	double mouseY = 0;
	double numberOfGhost;
	double numberOfRippers;
	double numberOfWisps;
	double tokenTimer = 0;
	double spawnTimer = 0;
	boolean cheatActive = false;
	
	double remainingTime;
	long lastTime = 0;

	public double getRemainingTime() {
		return remainingTime;
	}

	public void setRemainingTime(double remainingTime) {
		this.remainingTime = remainingTime;
	}

	Ghost ghost1;
	Wisp wisp1;
	Ripper ripper1;

	HashSet<KeyCode> keys = new HashSet<KeyCode>();
	Hunter h1;
	BarsAndTimes hud;

	BaseToken newToken;
	double startXRandom;
	double startYRandom;
	Pane pane1 = new Pane();
	StackPane LostPane = new StackPane();
	
	/** Yakup Efe Akça 150124078
	 * 	Arif Erdem Taşgın 150124070
	 * The main entry point for the JavaFX application.
	 * It sets up the stage, the game icon, and opens the main menu.
	 */
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("GhostHunterINC");
		Image gameIcon = new Image(getClass().getResourceAsStream("ghost_hunter_icon.png"));
	    stage.getIcons().add(gameIcon);
		
		MenuManager menuManager = new MenuManager(stage, this);
        stage.setScene(menuManager.createMainMenuScene());
		stage.show();
		stage.setFullScreenExitHint("");
		stage.setFullScreen(true);
	}
	
	/**	Yakup Efe Akça 150124078
	 * Initializes and starts a new game session or a specific level.
	 * It sets up the environment, spawns entities, initializes the player, and starts the game loop.
	 */
	
	public void startGame(Stage stage) {
		spawnTimer = 0;
		lastTime = 0; 
	    tokenTimer = 0;
		LostPane.getChildren().clear();
	    pane1.getChildren().clear();
	    currentActiveEntities.clear();
	    currentActiveTokens.clear();
	    
		hud = new BarsAndTimes(1080, 605);
		StackPane stackPane = new StackPane();
		stackPane.getChildren().add(hud);

		config1 = new Config();

		numberOfGhost = config1.map.get("level_" + currentLevel + "_ghosts");
		numberOfRippers = config1.map.get("level_" + currentLevel + "_rippers");
		numberOfWisps = config1.map.get("level_" + currentLevel + "_wisps");
		
		if(numberOfGhost!=0) {
			for (int i = 0; i < numberOfGhost; i++) {

				 startXRandom = config1.map.get("level_" + currentLevel + "_playable_area_x")
						+ (Math.random() * config1.map.get("level_" + currentLevel + "_playable_area_width"));
				 startYRandom = config1.map.get("level_" + currentLevel + "_playable_area_y")
						+ (Math.random() * config1.map.get("level_" + currentLevel + "_playable_area_height"));

				ghost1 = new Ghost(startXRandom, startYRandom);

				currentActiveEntities.add(ghost1);
				pane1.getChildren().addAll(ghost1.getGroup());

			}
		}
			if(numberOfRippers!=0) {
				for (int i = 0; i < numberOfRippers; i++) {

					 startXRandom = config1.map.get("level_" + currentLevel + "_playable_area_x")
							+ (Math.random() * config1.map.get("level_" + currentLevel + "_playable_area_width"));
					 startYRandom = config1.map.get("level_" + currentLevel + "_playable_area_y")
							+ (Math.random() * config1.map.get("level_" + currentLevel + "_playable_area_height"));

					ripper1 = new Ripper(startXRandom,startYRandom);

					currentActiveEntities.add(ripper1);
					pane1.getChildren().addAll(ripper1.getGroup());

				}
			}
			if(numberOfWisps!=0) {
				for (int i = 0; i < numberOfWisps; i++) {

					 startXRandom = config1.map.get("level_" + currentLevel + "_playable_area_x")
							+ (Math.random() * config1.map.get("level_" + currentLevel + "_playable_area_width"));
					 startYRandom = config1.map.get("level_" + currentLevel + "_playable_area_y")
							+ (Math.random() * config1.map.get("level_" + currentLevel + "_playable_area_height"));

					wisp1 = new Wisp(startXRandom,startYRandom);

					currentActiveEntities.add(wisp1);
					pane1.getChildren().addAll(wisp1.getGroup());

				}
			}

		h1 = new Hunter(300, 300);
		h1.updateBorder(
			    config1.map.get("level_" + currentLevel + "_playable_area_x"),
			    config1.map.get("level_" + currentLevel + "_playable_area_y"),
			    config1.map.get("level_" + currentLevel + "_playable_area_width"),
			    config1.map.get("level_" + currentLevel + "_playable_area_height")
			);
		borderLine = new Rectangle();
		borderLine.setFill(Color.TRANSPARENT);
		borderLine.setStroke(Color.WHITE);
		borderLine.setStrokeWidth(2);
		borderLine.getStrokeDashArray().addAll(10.0,5.0);
		borderLine.setVisible(false);
		
		borderLine.setX(config1.map.get("level_" + currentLevel + "_playable_area_x"));
		borderLine.setY(config1.map.get("level_" + currentLevel + "_playable_area_y"));
		borderLine.setWidth(config1.map.get("level_" + currentLevel + "_playable_area_width"));
		borderLine.setHeight(config1.map.get("level_" + currentLevel + "_playable_area_height"));
		
		pane1.getChildren().add(borderLine);
		
		if (currentLevel == 4) {
		    remainingTime = 0;
		} else {
		    remainingTime = config1.map.get("level_" + currentLevel + "_time");
		}

		Text clock = new Text("" + remainingTime);
		clock.setFill(Color.WHITE);
		clock.setFont(Font.font("Chiller", 20));
		Text scoreLabel = new Text("Skor: 0");
		scoreLabel.setFill(Color.WHITE);
		scoreLabel.setFont(Font.font("Chiller", 20));
		Font chillerFont = Font.loadFont(EndGamePanels.class.getResourceAsStream("chiller.ttf"), 50);
		scoreLabel.setFont(chillerFont);
		clock.setFont(chillerFont);
		Timeline timeLine = new Timeline();
		KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), Event -> {
			
			int minute = (int) (remainingTime / 60);
			int second = (int) (remainingTime % 60);

			String time = String.format("%02d:%02d", minute, second);
			
			clock.setText(time);
			if (currentLevel != 4 && remainingTime <= 0) {
				timeLine.stop();
				animation.stop();
				StackPane losePanel = EndGamePanels.createLosePanel(1080, 800, Hunter.getScore(),
				        () -> { 
				            Hunter.setScore(0);
				            startGame(stage); 
				        }, 
				        () -> { 
				            Hunter.setScore(0);
				            MenuManager menuManager = new MenuManager(stage, GameLoop.this);
				            stage.setScene(menuManager.createMainMenuScene());
				            if (!stage.isFullScreen()) {
				            	stage.setFullScreenExitHint("");
				                stage.setFullScreen(true);
				            }
				        }
				    );
				
			    LostPane.getChildren().add(losePanel);
				
			}
		});
		timeLine.getKeyFrames().add(keyFrame);
		timeLine.setCycleCount((Timeline.INDEFINITE));
		timeLine.play();

		VBox centerBox = new VBox(clock, scoreLabel);
		centerBox.setAlignment(Pos.TOP_CENTER);
		
		
		animation = new AnimationTimer() {
			
		/** Yakup Efe Akça 150124078
		 * 	Arif Erdem Taşgın 150124070
		 THE GAME LOOP:
		Called automatically by the AnimationTimer approximately 60 times per second (60 FPS).
		All real-time game mechanics are processed within this method.
		Functions:
		1. Delta Time Calculation: Calculates the elapsed time between frames in seconds.
		2. Timer Updates: Updates the countdown timer, token spawn, and endless mode spawn timers.
		3. Movement Processing: Updates player movement via keyboard, scanner rotation via mouse, and random enemy movements.
		4. Collision & Damage: Detects intersections between the Hunter and entities/tokens, and applies damage or buffs.
		5. Vacuum (Scanner) Mechanic: Handles the shrinking logic for enemies caught in the scanner when the SPACE bar is pressed.
		6. Game State Checks: Triggers Win/Lose panels if the time runs out, health reaches zero, or all enemies are cleared.
		*/
			
			@Override
			public void handle(long arg) {
				double second;

				
				if (lastTime == 0) {
					lastTime = arg;
					return;
				} else {
					second = (arg - lastTime) / 1000000000.0;
					if (currentLevel == 4) {
				        remainingTime += second;
				    } else {
				        remainingTime -= second;
				    }
					
					lastTime = arg;
					tokenTimer += second;
				}
				if (currentLevel != 4 && remainingTime < 0) {
					remainingTime = 0;
					stop();
					
				}
				h1.updateEyeTimer(second);
				h1.mouseCordinate(mouseX, mouseY);
				
				if(tokenTimer>=5) {
					tokenTimer=0;
					if(currentActiveTokens.size()<2) {
						startXRandom = config1.map.get("level_" + currentLevel + "_playable_area_x")
								+ (Math.random() * config1.map.get("level_" + currentLevel + "_playable_area_width"));
						 startYRandom = config1.map.get("level_" + currentLevel + "_playable_area_y")
								+ (Math.random() * config1.map.get("level_" + currentLevel + "_playable_area_height"));
						int tokenType = (int)(Math.random()*3)+1;
						
						switch(tokenType) {
						case 1:
							newToken = new HealthToken(startXRandom,startYRandom,config1.map.get("health_token_increase"));
							currentActiveTokens.add(newToken);
							pane1.getChildren().add(newToken.getGroup());
							
							break;
						case 2:
							newToken= new RangeToken(startXRandom,startYRandom,config1.map.get("vacuum_token_increase"));
							currentActiveTokens.add(newToken);
							pane1.getChildren().add(newToken.getGroup());
							
							break;
						case 3:
							newToken= new EyeToken(startXRandom, startYRandom,config1.map.get("eye_token_duration"));
							currentActiveTokens.add(newToken);
							pane1.getChildren().add(newToken.getGroup());
							
							break;
						}
					}
				}
				for(int i = currentActiveTokens.size()-1;i>=0;i--) {
					
					BaseToken currentToken = currentActiveTokens.get(i);
					
					Shape intersection = Shape.intersect(h1.getBody(), currentToken.getBody());
					if ((intersection.getBoundsInLocal().getWidth() != -1)) {
						currentToken.applyBenefit(h1);
						if (h1.health > h1.maxHealth) {
					        h1.health = h1.maxHealth;
					    }
						pane1.getChildren().remove(currentToken.getGroup());
						currentActiveTokens.remove(i);
					}
				}

				if (currentLevel == 4) {
				    spawnTimer += second;
				   
				    if (spawnTimer >= 3) {
				        spawnTimer = 0;
				        
				        double sX = config1.map.get("level_4_playable_area_x") + (Math.random() * config1.map.get("level_4_playable_area_width"));
				        double sY = config1.map.get("level_4_playable_area_y") + (Math.random() * config1.map.get("level_4_playable_area_height"));
				        
				        ParanormalEntity yeniDusman;
				       
				        int rand = (int) (Math.random() * 3);
				        if (rand == 0) {
				            yeniDusman = new Ghost(sX, sY);
				        } else if (rand == 1) {
				            yeniDusman = new Wisp(sX, sY);
				        } else {
				            yeniDusman = new Ripper(sX, sY);
				        }
				      
				        currentActiveEntities.add(yeniDusman);
				        pane1.getChildren().add(yeniDusman.getGroup());
				    }
				}

				for (int i = currentActiveEntities.size() - 1; i >= 0; i--) {

					ParanormalEntity currentEnemy = currentActiveEntities.get(i);
					currentEnemy.move();
					currentEnemy.checkBounds(config1.map.get("level_" + currentLevel + "_playable_area_x"),
							config1.map.get("level_" + currentLevel + "_playable_area_y"),
							config1.map.get("level_" + currentLevel + "_playable_area_width"),
							config1.map.get("level_" + currentLevel + "_playable_area_height"));

					Shape intersection = Shape.intersect(h1.getBody(), currentEnemy.getBody());
					if ((intersection.getBoundsInLocal().getWidth() != -1)) {
						double enemyDamage = 0;

					    if (currentEnemy instanceof Ghost) {
					        enemyDamage = config1.map.get("ghost_damage"); 
					    } 
					    else if (currentEnemy instanceof Wisp) {
					        enemyDamage = config1.map.get("wisp_damage"); 
					    } 
					    else if (currentEnemy instanceof Ripper) {
					        enemyDamage = config1.map.get("ripper_damage"); 
					    }
					    h1.health -= enemyDamage * second;
					}

					hud.updateHealthBar(h1.health, h1.maxHealth);
					
					boolean isBeingVacuumed = false;
				    if (keys.contains(KeyCode.SPACE) && h1.energy > 0) {
				    	Bounds scannerBounds = h1.getScannerBody().localToScene(h1.getScannerBody().getBoundsInLocal());
				        Bounds enemyBounds = currentEnemy.getGroup().localToScene(currentEnemy.getGroup().getBoundsInLocal());
				        
				        if (scannerBounds.intersects(enemyBounds)) {
				            isBeingVacuumed = true;
				            
				            currentEnemy.applyVacuumEffect(second);
				            
				            
				            if(currentEnemy.getGroup().getScaleX()<=0.35) {
				            	pane1.getChildren().remove(currentEnemy.getGroup());
				            	currentActiveEntities.remove(i);
				            	int point = 0;
				            	if (currentEnemy instanceof Ghost) {
				            		point = config1.map.get("ghost_score"); 
				                } 
				                else if (currentEnemy instanceof Wisp) {
				                	point = config1.map.get("wisp_score"); 
				                } 
				                else if (currentEnemy instanceof Ripper) {
				                	point = config1.map.get("ripper_score"); 
				                }
				            	
				            	Hunter.setScore(Hunter.getScore() + point);
				            	scoreLabel.setText("Skor: " + Hunter.getScore());
				            	continue;
				            }
				        }
				    }
				    
					
				    if (h1.isEyeActive() || isBeingVacuumed || cheatActive) {
				        currentEnemy.getGroup().setVisible(true);
				    } else {
				        currentEnemy.getGroup().setVisible(false);
				    }
				    if (!isBeingVacuumed) {
				    	currentEnemy.resetColor();
				    }
				}
				if (currentLevel != 4 && currentActiveEntities.isEmpty()) {
			        
			        stop(); 
			        StackPane winPanel = EndGamePanels.createWinPanel(1080, 800, () -> {
			        	
			            nextLevel();
			            startGame(stage);
			        });
			        LostPane.getChildren().add(winPanel);
			    }

				if (keys.contains(KeyCode.SPACE) && h1.energy > 0) {
					h1.activateScanner();
					h1.energy -= (config1.map.get("vacuum_decrease") * second);
					for (int i = currentActiveEntities.size() - 1; i >= 0; i--) {

						ParanormalEntity currentEnemy = currentActiveEntities.get(i);
						currentEnemy.checkIfVacuumed(h1.getScannerBody(), second);
						
						if(currentEnemy.getGroup().getScaleX()<=0.1) {
							pane1.getChildren().remove(currentEnemy.getGroup());
							currentActiveEntities.remove(i);
						}
					}
				} else {
					h1.deactivateScanner();
				}

				
				if (!keys.contains(KeyCode.SPACE) && h1.energy < h1.maxEnergy)
					h1.energy += (config1.map.get("vacuum_increase") * second);
				if (h1.energy < 0) {
					h1.energy = 0;
				}
				if (h1.energy > h1.maxEnergy) {
					h1.energy = h1.maxEnergy;
				}

				hud.updateVacuumBar(h1.energy, h1.maxEnergy);

				if(keys.contains(KeyCode.G)) {
					cheatActive = !cheatActive;
					
					borderLine.setVisible(cheatActive);
					
					keys.remove(KeyCode.G);
				}
				

				if (h1.health <= 0) {
					h1.health = 0;
					stop();
					
					StackPane losePane = EndGamePanels.createLosePanel(1080, 800, Hunter.getScore(), 
					        () -> { 
					            Hunter.setScore(0);
					            startGame(stage); 
					        }, 
					        () -> { 
					            Hunter.setScore(0);
					            MenuManager menuManager = new MenuManager(stage, GameLoop.this);
					            stage.setScene(menuManager.createMainMenuScene());
					            if (!stage.isFullScreen()) {
					            	stage.setFullScreenExitHint("");
					                stage.setFullScreen(true);
					            }
					        }
					    );
					LostPane.getChildren().add(losePane);
				}
				if (h1.health > h1.maxHealth) {
					h1.health = h1.maxHealth;
				}


				if (keys.contains(KeyCode.W))
					h1.moveUp();
				if (keys.contains(KeyCode.S))
					h1.moveDown();
				if (keys.contains(KeyCode.D))
					h1.moveRight();
				if (keys.contains(KeyCode.A))
					h1.moveLeft();

				hud.updateHealthBar(h1.health, h1.maxHealth);
			}
		};

		animation.start();

		Pane pane = new Pane();
		pane.getChildren().addAll(h1.getGroup());
		
		StackPane MainPane = new StackPane();
		String FileName = "level" + currentLevel + ".jpeg"; 

		Image levelImage = new Image(getClass().getResourceAsStream(FileName));
		ImageView ImageView = new ImageView(levelImage);

		ImageView.setPreserveRatio(false);
		ImageView.fitWidthProperty().bind(MainPane.widthProperty());
		ImageView.fitHeightProperty().bind(MainPane.heightProperty());
		MainPane.getChildren().addAll(ImageView, pane, stackPane, centerBox, pane1, LostPane);
		
		

		Scene scene = new Scene(MainPane);
		scene.setFill(Color.AQUAMARINE);
		scene.setOnKeyPressed(e -> {
			keys.add(e.getCode());
		});
		scene.setOnKeyReleased(e -> {
			keys.remove(e.getCode());
		});
		scene.setOnMouseMoved(e -> {
		    mouseX = e.getX();
		    mouseY = e.getY();
		});
		scene.setOnMouseDragged(e -> {
		    mouseX = e.getX();
		    mouseY = e.getY();
		});
		
		
		stage.setScene(scene);
		stage.setFullScreenExitHint("");
		stage.setFullScreen(true);
	}
	
	/** Yakup Efe Akça 150124078
	 * Prepares the game state for the next consecutive level.
	 * Increments the level counter, updates boundary constraints, and resets the timer.
	 */
	
	public void nextLevel() {
		currentLevel++;
		
		h1.updateBorder(
			    config1.map.get("level_" + currentLevel + "_playable_area_x"),
			    config1.map.get("level_" + currentLevel + "_playable_area_y"),
			    config1.map.get("level_" + currentLevel + "_playable_area_width"),
			    config1.map.get("level_" + currentLevel + "_playable_area_height")
			);
		
		remainingTime = config1.map.get("level_" + currentLevel + "_time");
	}
}