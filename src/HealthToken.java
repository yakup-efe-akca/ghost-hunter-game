
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * Arif Erdem Taşgın 150124070
 * * Ghost Class:
 * The HealthToken class extends BaseToken to create a collectible health item, visually represented as a green circle with a red cross, 
   which restores a specific amount of health to the player upon collection without exceeding the maximum health limit.
 */

public class HealthToken extends BaseToken {

	private double healthAmount;

	public HealthToken(double startX,double startY, double healthAmount) {
		this.healthAmount=healthAmount;
		super();
		this.group = new Group(); 
		this.body=new Circle(15);
		this.body.setFill(Color.GREEN);
		
		
		Rectangle rec1 = new Rectangle(16,4);
		rec1.setX(-8);
		rec1.setY(-2);
		rec1.setFill(Color.RED);
		
		Rectangle rec2 = new Rectangle(4,16);
		rec2.setX(-2);
		rec2.setY(-8);
		rec2.setFill(Color.RED);
		
		
		this.group.setTranslateX(startX);

		this.group.setTranslateY(startY);
		
		this.group.getChildren().addAll(this.body,rec1,rec2);	
	}

	@Override
	public void applyBenefit(Hunter hunter) {
		
		hunter.health+=healthAmount;
		
		if(hunter.health>hunter.maxHealth) {
			hunter.health=100;
		}
	}
}
