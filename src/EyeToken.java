import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Arif Erdem Taşgın 150124070
 * * EyeToken Class:
 * The EyeToken class extends BaseToken to create a collectible item with a layered eye-like visual design, which grants the player a special vision ability for a specified duration when collected.
 */

public class EyeToken extends BaseToken {
	
	private double duration;

	public EyeToken(double startX, double startY,double duration) {
		super();
		this.duration=duration;
		this.group = new javafx.scene.Group(); 
		this.body = new Circle(15);
		this.body.setFill(Color.YELLOW);

		Circle innerCircle = new Circle(8);
		innerCircle.setFill(Color.WHITE);

		Circle eyeDot = new Circle(3);
		eyeDot.setFill(Color.BLACK);

		this.group.setTranslateX(startX);

		this.group.setTranslateY(startY);

		this.group.getChildren().addAll(this.body, innerCircle, eyeDot);

	}

	@Override
	public void applyBenefit(Hunter hunter) {
		hunter.activateEye(this.duration);

	}

}
