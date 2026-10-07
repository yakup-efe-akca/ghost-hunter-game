import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class EndGamePanels {
	
	/** * Yavuz Selim Durdubaş 150124065
     * Creates and returns the Win Panel displayed when a level is cleared.
     * Constructs a transparent overlay with a styled dark background pane, displaying victory text and a button mapped to the next level's execution logic.
     */
    public static StackPane createWinPanel(double Width, double Height, Runnable nextLevel) {
        StackPane winPanel = new StackPane();
        winPanel.setPrefSize(Width, Height);

        Rectangle overlay = new Rectangle(Width, Height, Color.TRANSPARENT);

        VBox panelBox = new VBox(20);
        panelBox.setAlignment(Pos.CENTER);
        
        Rectangle panelBg = new Rectangle(450, 250); 
        panelBg.setFill(Color.rgb(0, 0, 0, 0.8));
        StackPane panelContainer = new StackPane(panelBg, panelBox);
        panelContainer.setMaxSize(450, 250);
        
        
        Text title = new Text("YOU WIN!");
        title.setFill(Color.WHITE);
        Font font = Font.loadFont(MenuButton.class.getResourceAsStream("Melted Monster.ttf"), 60);
        title.setFont(font);

        Text info = new Text("YOU'VE VACUUMED ALL THE GHOSTS!");
        info.setFill(Color.WHITE);
        Font font1 = Font.loadFont(MenuButton.class.getResourceAsStream("Melted Monster.ttf"), 25);
        info.setFont(font1);

       MenuButton nextLevelBtn = new MenuButton("NEXT LEVEL");
       nextLevelBtn.setOnMouseReleased(e -> nextLevel.run());
       nextLevelBtn.setPickOnBounds(false);

        panelBox.getChildren().addAll(title, info, nextLevelBtn);
        
        winPanel.getChildren().addAll(overlay, panelContainer);

        return winPanel;
    }
    
    /** * Yavuz Selim Durdubaş 150124065
     * Creates and returns the Lose Panel displayed when health is depleted or time runs out.
     * Sets up a semi-transparent screen overlay, shows a "GAME OVER" message, the final score, and injects interactive buttons to retry or return to the main menu.
     */
    public static StackPane createLosePanel(double Width, double Height, int Score, Runnable onRetry, Runnable onMainMenu) {
   
    	StackPane losePanel = new StackPane();
        
        Rectangle overlay = new Rectangle(Width, Height);
        overlay.setFill(Color.rgb(255, 255, 255, 0.5));
        overlay.widthProperty().bind(losePanel.widthProperty());
        overlay.heightProperty().bind(losePanel.heightProperty());
        
        VBox panelBox = new VBox(30);
        panelBox.setAlignment(Pos.CENTER);

        Text gameOverText = new Text("GAME OVER");
        gameOverText.setFill(Color.RED);
        Font font = Font.loadFont(MenuButton.class.getResourceAsStream("Melted Monster.ttf"), 120);
        gameOverText.setFont(font);

        Text scoreText = new Text("FINAL SCORE: " + Score);
        scoreText.setFill(Color.RED);
        Font font1 = Font.loadFont(MenuButton.class.getResourceAsStream("Melted Monster.ttf"),40);
        scoreText.setFont(font1);

        HBox buttons = new HBox(20);
        buttons.setAlignment(Pos.CENTER);
        
        LoseButton retryBtn = new LoseButton("RETRY");
        retryBtn.setOnMouseReleased(e -> onRetry.run());
        
        LoseButton mainMenuBtn = new LoseButton("MAIN MENU");
        mainMenuBtn.setOnMouseReleased(e -> onMainMenu.run());
        
        buttons.getChildren().addAll(retryBtn, mainMenuBtn);
        panelBox.getChildren().addAll(gameOverText, scoreText, buttons);       
        losePanel.getChildren().addAll(overlay, panelBox);

        return losePanel;
    }
}