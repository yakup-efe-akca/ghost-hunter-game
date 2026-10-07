
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

/**
 * Arif Erdem Taşgın 150124070
 * * Ripper Class:
 *  The Ripper class extends ParanormalEntity to create a 20-point enemy, constructing its visual representation by grouping a custom purple, 
    spiky polygon body with red circular eyes, placing this entity at the given starting coordinates, and applying a random initial velocity for its movement.
 */
public class Ripper extends ParanormalEntity {
	
	public Ripper(double startX, double startY) {
		this.scoreValue=20;
		
		Polygon spikyBody = new Polygon();
		
		spikyBody.getPoints().addAll(new Double[]{
	            0.0, -30.0,   
	            7.5, -7.5,    
	            30.0, 0.0,    
	            7.5, 7.5,     
	            0.0, 30.0,    
	            -7.5, 7.5,    
	            -30.0, 0.0,   
	            -7.5, -7.5    
	        });
		
		spikyBody.setFill(Color.PURPLE);
		this.body=spikyBody;
		
		Circle leftEye = new Circle(-4, -4, 3, Color.RED);
        Circle rightEye = new Circle(4, -4, 3, Color.RED);
        
        this.group=new Group();
        
        this.group.getChildren().addAll(this.body,leftEye,rightEye);
        
        this.group.setVisible(true);
        
        this.group.setTranslateX(startX);
        this.group.setTranslateY(startY);
        
        this.dx = (Math.random() * 2) - 1; 
        this.dy = (Math.random() * 2) - 1;
	}

}
