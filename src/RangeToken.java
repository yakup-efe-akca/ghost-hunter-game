
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

/**
 * Arif Erdem Taşgın 150124070
 * * RangeToken Class:
 * The RangeToken class extends BaseToken to create a collectible power-up item visually represented by a light blue circle with dark blue triangles that increases the player's field of view when activated.
 */
public class RangeToken extends BaseToken {
	
	private double rangeAmount;


	public RangeToken(double startX,double startY, double rangeAmount) {
		super();
		this.group = new javafx.scene.Group();
		this.body=new Circle(15);
		this.body.setFill(Color.LIGHTBLUE);
		
		Polygon innerTriangle = new Polygon();
		
		innerTriangle.getPoints().addAll(new Double[]{ -5.0, -8.0,  -5.0, 8.0,  8.0, 0.0 });
		innerTriangle.setFill(Color.DARKBLUE);
		
		Polygon outerTriangle = new Polygon();
		
		outerTriangle.getPoints().addAll(new Double[]{ -10.0, -14.0,  -10.0, 14.0,  14.0, 0.0 });
		outerTriangle.setFill(Color.TRANSPARENT);
		outerTriangle.setStroke(Color.DARKBLUE);
		outerTriangle.setStrokeWidth(2);
		
		this.group.setTranslateX(startX);

		this.group.setTranslateY(startY);
		
		this.group.getChildren().addAll(this.body,innerTriangle,outerTriangle);
			
	}

	@Override
	public void applyBenefit(Hunter hunter) {	
		hunter.updateViewArea(this.rangeAmount);
	}
}