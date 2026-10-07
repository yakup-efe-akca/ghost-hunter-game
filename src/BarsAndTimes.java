import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BarsAndTimes extends BorderPane {

    private Rectangle healthFill;
    private Rectangle vacuumFill;
    private Text scoreText;
    private Text timeText;
    private double vacuumHeight = 210;
    
    /** * Yavuz Selim Durdubaş 150124065
     * Updates the visual representation of the vacuum energy bar.
     * Calculates the ratio of the current energy to the maximum energy and adjusts the height of the fill rectangle to reflect the remaining capacity.
     */
    public void updateVacuumBar(double currentEnergy, double maxEnergy) {
        double maxBarHeight = 210.0;

        double ratio = currentEnergy / maxEnergy; 

        vacuumFill.setHeight(maxBarHeight * ratio);
    }
    
    /** * Yavuz Selim Durdubaş 150124065
     * Updates the visual representation of the player's health bar.
     * Calculates the ratio of the current health to the maximum health and adjusts the height of the fill rectangle to show remaining health.
     */
    public void updateHealthBar(double currentHealth, double maxHealth) {
        double maxBarHeight = 210.0;

        double ratio = currentHealth / maxHealth; 

        healthFill.setHeight(maxBarHeight * ratio);
    }
    public double getVacuumHeight() {
		return vacuumHeight;
	}

	public void setVacuumHeight(double vacuumHeight) {
		this.vacuumHeight = vacuumHeight;
	}

	public Rectangle getHealthFill() {
		return healthFill;
	}

	public Rectangle getVacuumFill() {
		return vacuumFill;
	}
	
	public void setHealthFill(Rectangle healthFill) {
		this.healthFill = healthFill;
	}

	public void setVacuumFill(Rectangle vacuumFill) {
		this.vacuumFill = vacuumFill;
	}

	public Text getScoreText() {
		return scoreText;
	}

	public Text getTimeText() {
		return timeText;
	}
	
	/** * Yavuz Selim Durdubaş 150124065
     * Constructor for the Heads-Up Display (HUD) showing bars and text.
     * Initializes the UI layouts for the vacuum and health bars, positions them on the left and right sides of the screen, and applies custom styling/fonts.
     */
	public BarsAndTimes(double screenW, double screenH) {
      	
    this.setPickOnBounds(false); 
    this.setPrefSize(screenW, screenH);
    this.setPadding(new Insets(10, 30, 0, 30)); 

    
        VBox vacuumBox = new VBox(7);
        vacuumBox.setAlignment(Pos.TOP_CENTER); 

        Text vacuumText = new Text("VACUUM");
        vacuumText.setFill(Color.WHITE);
        vacuumText.setFont(Font.font("Chiller", 25));

        StackPane vacumBarContainer = new StackPane();
        vacumBarContainer.setAlignment(Pos.TOP_CENTER); 
        
        Rectangle vacuumBorder = new Rectangle(45, 210, Color.BLACK); 
        vacuumFill = new Rectangle(35, vacuumHeight, Color.PURPLE); 
        
        vacumBarContainer.getChildren().addAll(vacuumBorder, vacuumFill);
        vacuumBox.getChildren().addAll(vacuumText, vacumBarContainer);
        
        VBox healthBox = new VBox(5);
        healthBox.setAlignment(Pos.TOP_CENTER); 

        	Text healthText = new Text("HEALTH");
        	healthText.setFill(Color.WHITE);
        	healthText.setFont(Font.font("Chiller", 25));

        	StackPane healthBarContainer = new StackPane();
        	healthBarContainer.setAlignment(Pos.TOP_CENTER); 
        
        	Rectangle healthBorder = new Rectangle(45, 210, Color.BLACK); 
        	healthFill = new Rectangle(35, 210, Color.RED); 
        
        	healthBarContainer.getChildren().addAll(healthBorder, healthFill);
        	healthBox.getChildren().addAll(healthText, healthBarContainer);
        	
        	Font chillerFont = Font.loadFont(EndGamePanels.class.getResourceAsStream("chiller.ttf"), 50);
        	healthText.setFont(chillerFont);	
        	vacuumText.setFont(chillerFont);

        this.setLeft(vacuumBox);
        
        this.setRight(healthBox);
        
        BorderPane.setAlignment(vacuumBox, Pos.TOP_LEFT);
        
        BorderPane.setAlignment(healthBox, Pos.TOP_RIGHT);
    }
}