
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * Arif Erdem Taşgın 150124070
 * * Wisp Class:
 *  The Wisp class creates a 30-point enemy characterized by a layered circular body, 
    blue eyes, and an animated ring of six rotating rectangles, moving at a constant diagonal speed.
 */
public class Wisp extends ParanormalEntity {

	private Group revolvingRectangles;

	public Wisp(double startX, double startY) {

		this.scoreValue = 30;

		Circle outerCircle = new Circle(20, Color.rgb(200, 0, 0, 0.3));

		Circle innerCircle = new Circle(12, Color.rgb(200, 0, 0, 0.8));

		this.body = outerCircle;

		revolvingRectangles = new Group();

		double distance = 32;
		double rectGenislik = 18;
		double rectKalinlik = 6;

		for (int i = 0; i < 6; i++) {

			double angle = i * 60;

			Rectangle rect = new Rectangle(-rectGenislik / 2, -rectKalinlik / 2, rectGenislik, rectKalinlik);
			rect.setFill(Color.RED);

			double rad = Math.toRadians(angle);
			rect.setTranslateX(distance * Math.cos(rad));
			rect.setTranslateY(distance * Math.sin(rad));

			rect.setRotate(angle);

			revolvingRectangles.getChildren().add(rect);
		}

		Circle leftEye = new Circle(-4, -4, 3, Color.BLUE);
		Circle rightEye = new Circle(4, -4, 3, Color.BLUE);
		this.group = new Group();
		this.group.getChildren().addAll(outerCircle, innerCircle, revolvingRectangles, leftEye, rightEye);
		this.group.setVisible(true);

		this.group.setTranslateX(startX);
		this.group.setTranslateY(startY);

		this.dx = 0.7;
		this.dy = 0.7;

	}

	@Override
	void move() {

		super.move();

		revolvingRectangles.setRotate(revolvingRectangles.getRotate() + 3);
	}
}