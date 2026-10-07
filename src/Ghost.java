import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Arif Erdem Taşgın 150124070
 * * Ghost Class:
 * The Ghost class extends ParanormalEntity to create a 10-point enemy featuring a translucent head, red eyes, and a dynamically fading tail, 
   while overriding the movement method to apply a sinusoidal waving animation.
 */

public class Ghost extends ParanormalEntity {
	
	Circle[] tailParts;
	double time = 0;
	 
	public Ghost(double startX, double startY) {

		this.scoreValue=10;
		this.group = new Group();
		
		this.tailParts = new Circle[4];
		
		double tailY=15;
		double radius=16;
		double opacity=0.4;
		
		for(int i=0;i<4;i++) {
			Circle ghostsTail = new Circle(0, tailY, radius, Color.rgb(255, 255, 255, opacity));
			this.tailParts[i] = ghostsTail;
			this.group.getChildren().add(ghostsTail);
			tailY +=10;
			radius -=4.0;
			opacity -=0.08;
		}
		
		Circle ghostHead = new Circle (0, 0, 18, Color.rgb(255, 255, 255, 0.6));
		this.body=ghostHead;
		this.group.getChildren().add(ghostHead);
		
		Circle leftEyeBack = new Circle(-7, -5, 6, Color.rgb(50, 0, 0, 0.4));
        Circle rightEyeBack = new Circle(7, -5, 6, Color.rgb(50, 0, 0, 0.4));
        
        
        Circle leftEye = new Circle(-7, -5, 3, Color.RED);
        Circle rightEye = new Circle(7, -5, 3, Color.RED);
		
        this.group.getChildren().addAll(leftEyeBack, rightEyeBack, leftEye, rightEye);
		
        this.group.setVisible(true);
        
        this.group.setTranslateX(startX);
          
        this.group.setTranslateY(startY);
        
        this.dx = (Math.random() * 2) - 1;
		this.dy = (Math.random() * 2) - 1;
	}
	@Override
	void move() {
		
		super.move();
		
		time += 0.02;
		
		for (int i=0;i<tailParts.length;i++) {
			
			double wave = Math.sin(time-i)*(i*2.5);
			
			double curveX = -i*2;
			tailParts[i].setTranslateX(curveX + wave);
		}
		
	}
}