
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class MenuManager {
private Stage stage;
private GameLoop gameloop;
	
	/** * Yavuz Selim Durdubaş 150124065
	 * Constructor for the MenuManager class.
	 * Injects the main application stage and the core GameLoop instance so that scenes can be transitioned and games can be started correctly.
	 */
	public MenuManager(Stage stage, GameLoop gameloop) {
        this.stage = stage;
        this.gameloop = gameloop;
    }
	
	/** * Yavuz Selim Durdubaş 150124065
     * Creates and configures the Main Menu scene.
     * Implements a background image bounding the screen dimensions and populates a VBox with Start, Select Level, and Exit buttons tied to application events.
     */
    public Scene createMainMenuScene() {
    	
    	StackPane root1 = new StackPane();
    	
        VBox menuLayout = new VBox(10); 
        menuLayout.setAlignment(Pos.CENTER);

        Image MainMenuImage = new Image(getClass().getResourceAsStream("MainMenuImage.jpeg"));
        ImageView imageView = new ImageView(MainMenuImage);
        imageView.setPreserveRatio(false);
        
        imageView.fitWidthProperty().bind(root1.widthProperty());
        imageView.fitHeightProperty().bind(root1.heightProperty());
        root1.getChildren().add(imageView);
        
        menuLayout.setBackground(null);
        
        MenuButton btnStart = new MenuButton("START GAME");
        btnStart.setOnMouseReleased(e -> {
            try {
                gameloop.startGame(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        btnStart.setPickOnBounds(false);
        
        
        MenuButton btnSelectLevel = new MenuButton("SELECT LEVEL");
        btnSelectLevel.setOnMouseReleased(e -> {
            stage.setScene(createLevelSelectScene());
            stage.setFullScreen(true);
        });
        btnSelectLevel.setPickOnBounds(false);
        
        
        MenuButton btnExit = new MenuButton("EXIT");
        btnExit.setOnMouseReleased(e -> System.exit(0));
        btnExit.setPickOnBounds(false);

        menuLayout.getChildren().addAll(btnStart, btnSelectLevel, btnExit);
     
        root1.getChildren().add(menuLayout);
        
        return new Scene(root1, 1080, 605); 
        }
    
    /** * Yavuz Selim Durdubaş 150124065
     * Creates and configures the Level Selection menu scene.
     * Uses a GridPane layout to display graphical buttons representing available levels (1, 2, 3, and Endless). Maps mouse events to update the GameLoop's current state and start the selected level.
     */
    public Scene createLevelSelectScene() {
      
    	StackPane root2 = new StackPane();
    	
    	GridPane levelGrid = new GridPane();

    	levelGrid.setAlignment(Pos.CENTER);

    	levelGrid.setHgap(40); 
    	levelGrid.setVgap(40);

    	levelGrid.setPadding(new Insets(20));

    	 Image MainMenuImage = new Image(getClass().getResourceAsStream("SelectMenuImage.jpeg"));
         ImageView imageView = new ImageView(MainMenuImage);
         imageView.setPreserveRatio(false);
         
         imageView.fitWidthProperty().bind(root2.widthProperty());
         imageView.fitHeightProperty().bind(root2.heightProperty());
         root2.getChildren().add(imageView);
         
         levelGrid.setBackground(null);
    	
    	
        	SelectLevelButton lvl1 = new SelectLevelButton("1", "Level1.jpeg");
        	lvl1.setOnMouseReleased(e -> {
	            stage.setScene(createLevelSelectScene());
	        });
	        SelectLevelButton lvl2 = new SelectLevelButton("2", "Level2.jpeg");
	        lvl2.setOnMouseReleased(e -> {
	            stage.setScene(createLevelSelectScene());
	        });
	        SelectLevelButton lvl3 = new SelectLevelButton("3", "Level3.jpeg");
	        lvl3.setOnMouseReleased(e -> {
	            stage.setScene(createLevelSelectScene());
	        });
	        SelectLevelButton lvlEndless = new SelectLevelButton("Endless", "Level4.jpeg");
	        lvlEndless.setOnMouseReleased(e -> {
	            stage.setScene(createLevelSelectScene());
	        });
	        lvl1.setOnMouseReleased(e -> {
	            GameLoop.currentLevel = 1;    
	            gameloop.startGame(stage);    
	        });
	        lvl2.setOnMouseReleased(e -> {
	            GameLoop.currentLevel = 2;      
	            gameloop.startGame(stage);      
	        });
	        lvl3.setOnMouseReleased(e -> {
	            GameLoop.currentLevel = 3;     
	            gameloop.startGame(stage);      
	        });
	        lvlEndless.setOnMouseReleased(e -> {
	            GameLoop.currentLevel = 4;      
	            gameloop.startGame(stage);      
	        });
	        
	        levelGrid.add(lvl1, 0, 0);
	        levelGrid.add(lvl2, 1, 0); 
	        levelGrid.add(lvl3, 0, 1); 
	        levelGrid.add(lvlEndless, 1, 1);


    	
    	VBox levelLayout = new VBox(30);
    	levelLayout.setAlignment(Pos.CENTER);
    	
    	levelLayout.getChildren().add(levelGrid);
    	
    	MenuButton btnBack = new MenuButton("BACK TO MENU");
    	levelLayout.getChildren().add(btnBack);
    	
    	btnBack.setPickOnBounds(false);
    	
    	btnBack.setOnMouseReleased(e -> {
            stage.setScene(createMainMenuScene());
            stage.setFullScreen(true);
        });

    	
    	 root2.getChildren().add(levelLayout);
    	 
    	return new Scene(root2, 1080, 605);
    }
}