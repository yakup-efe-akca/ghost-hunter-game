import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

/**
 * Yakup Efe Akça 150124078
 * Arif Erdem Taşgın 150124070
 * * Hunter Class:
 * This class represents the main playable character (The Hunter) in the game.
 * It manages the player's visual components, movement mechanics, health system,
 * border collision detection, and the dynamic scanner/vacuum mechanics.
 */

public class Hunter {
	Config config1 = new Config();
	private Group group;
    private Circle body;
    private Polygon scannerTriangle;
     
    private boolean isHit=false;
    
    public static int score;
    double xCoordinate;
    double yCoordinate;

    private Rotate scannerRotation;
    private boolean isEyeActive = false;
	private double eyeTimer = 0;
	
	double minX, maxX, minY, maxY;
	
	double health;
	int vacuumPower;
	int speed;

	double energy;
	double maxEnergy;

	double maxHealth;
	long DamageTime = 0;

    public Hunter(double startX,double startY) {
    	speed = 3;
    	group=new Group();
    	
    	body=new Circle(20);
    	body.setFill(Color.ORANGE);
    	
    	scannerTriangle=new Polygon();
    	scannerTriangle.getPoints().addAll(new Double[]{0.0, 0.0, 150.0, -50.0, 150.0, 50.0});
    	scannerTriangle.setFill(Color.rgb(255, 0, 0, 0.4)); 
        scannerTriangle.setVisible(false);

        scannerRotation = new Rotate(0,0,0);
		scannerTriangle.getTransforms().add(scannerRotation);

        
		group.getChildren().addAll(scannerTriangle,body);
		group.setTranslateX(startX);
		group.setTranslateY(startY);
        	
		health = config1.map.get("maximum_health");
		vacuumPower = config1.map.get("maximum_vacuum");
		
		

		maxEnergy = config1.map.get("maximum_vacuum");
		energy = maxEnergy;

		maxHealth = config1.map.get("maximum_health");
		health = maxHealth;
		
		
		xCoordinate = startX;
		yCoordinate = startY;
    }
    
    /*Arif Erdem Taşgın 150124071
     * This method allows us to orient the vacuum field around the hunter using the mouse.
     */
    public void mouseCordinate ( double targetX,double targetY) {
		double dx = targetX-group.getTranslateX();
		double dy = targetY-group.getTranslateY();
		
		double angle = Math.toDegrees(Math.atan2(dy, dx));
		////////////
		double currentAngle = scannerRotation.getAngle();
		double angleDiff = angle - currentAngle;
        angleDiff = (angleDiff + 540) % 360 - 180;
        double rotarySpeed = 0.1;
        /////////////////
		scannerRotation.setAngle(currentAngle + (angleDiff * rotarySpeed));
		
	}
	
	public void activateEye(double duration) {
		setEyeActive(true);
		setEyeTimer(duration);
	}
	
	public Group getGroup() {
		return group;
	}

	public void activateScanner() {
		scannerTriangle.setVisible(true);
	}
	public void deactivateScanner() {
		scannerTriangle.setVisible(false);
	}

	public static int getScore() {
		return score;
	}

	public static void setScore(int score) {
		Hunter.score = score;
	}

	public boolean isEyeActive() {
		return isEyeActive;
	}
	public void setEyeActive(boolean isEyeActive) {
		this.isEyeActive = isEyeActive;
	}
	public double getEyeTimer() {
		return eyeTimer;
	}

	public void setEyeTimer(double eyeTimer) {
		this.eyeTimer = eyeTimer;
	}

	public Circle getBody() {
		return body;
	}
	
	/** Yakup Efe Akça 150124078
     * Reduces the Hunter's health when attacked by an entity.
     * Ensures health does not drop below zero.
     */
	
	public void  takeDamage(double damageAmount) {
		if(this.health-damageAmount>=0) {
			this.health-=damageAmount;
		}else {
			this.health=0;
		}
	}
	
	public void updateVisualState() {
		if(isHit) {
			this.body.setFill(Color.RED);
		}else {
			this.body.setFill(Color.ORANGE);
		}
	}

	public double getHealth() {
		return health;
	}

	public Polygon getScannerBody() {
		return scannerTriangle;
	}
	public void setHealth(double health) {
		this.health = health;
	}
	
	public void updateEyeTimer(double second) {
		
		if(isEyeActive) {
			eyeTimer-=second;
			if(eyeTimer<=0) {
				isEyeActive=false;
				eyeTimer=0;
			}
		}
	}
	
	public void updateViewArea(double rangeAmount) {
		double currentWidht1=-50.0;
		double currentWidht2=50.0;
		
		scannerTriangle.getPoints().clear();
		
		scannerTriangle.getPoints().addAll(new Double[]{0.0, 0.0, 150.0, currentWidht1-20.0, 150.0, currentWidht2+20.0});
	}
	
	/** Yakup Efe Akça 150124078
     * Moves the Hunter UP. Includes collision detection to prevent moving past the top border.
     * Moves the Hunter DOWN. Includes collision detection to prevent moving past the bottom border.
     * Moves the Hunter RIGHT. Includes collision detection to prevent moving past the right border.
     * Moves the Hunter LEFT. Includes collision detection to prevent moving past the left border.
     */
	
	public void moveUp() {
		if((yCoordinate - speed - 20) >= minY) {
			yCoordinate -= speed;
			group.setTranslateY(yCoordinate);
		}
	}
	public void moveDown() {
		if((yCoordinate + speed + 20) <= maxY) {
			yCoordinate += speed; 
			group.setTranslateY(yCoordinate);
		}
	}
	public void moveRight() {
		if((xCoordinate + speed + 20) <= maxX) {
			xCoordinate += speed;
			group.setTranslateX(xCoordinate);
		}
	}
	public void moveLeft() {
		if((xCoordinate - speed - 20) >= minX) {
			xCoordinate -= speed;
			group.setTranslateX(xCoordinate);
		}
	}
	
	/** Yakup Efe Akça 150124078
     * Sets the playable area boundaries for the current level.
     * This ensures the Hunter cannot walk off the screen.
     * minX The starting X-coordinate of the boundary.
     * minY The starting Y-coordinate of the boundary.
     * maxX The total width of the boundary.
     * maxY The total height of the boundary.
     */
	
	public void updateBorder(double minX, double minY, double maxX, double maxY) {
	    this.minX = minX;
	    this.minY = minY;
	    this.maxX = minX + maxX;
	    this.maxY = minY + maxY;
	}
}