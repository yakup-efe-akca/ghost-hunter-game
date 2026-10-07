import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * Arif Erdem Taşgın 150124070
 * * ParanormalEntity Class:
 * This abstract class serves as the base template for all enemy types, defining their common properties and behaviors.
 */

public abstract class ParanormalEntity {
	
	double dx,dy;
	int scoreValue;
	protected Group group;
	protected Shape body;
	protected boolean visible;
	
	/**
	 * Arif Erdem Taşgın 150124070
	 * * move Method:
	 * This method updates the entity's coordinates on the screen based on its horizontal and vertical speed.
	 */
	void move(){
		group.setTranslateX(group.getTranslateX() + dx);

		group.setTranslateY(group.getTranslateY() + dy);
	}
	
	/**
	 * Arif Erdem Taşgın 150124070
	 * * applyVacuumEffect Method:
	 * This method visually shrinks the entity over time when caught by the vacuum and increases the player's score once it is completely captured.
	 */
	public boolean applyVacuumEffect(double second) {
		group.setVisible(true);
		body.setFill(Color.DARKRED);
		
		double currentScale = group.getScaleX();
		double shinkRate=0.5*second;
		
		group.setScaleX(currentScale-shinkRate);
		group.setScaleY(currentScale-shinkRate);
		
		if(group.getScaleX()<=0.1) {
			Hunter.setScore(this.scoreValue);
			return true;
		}
		return false;
	}
	
	/**
	 * Arif Erdem Taşgın 150124070
	 * * resetColor Method:
	 * This method restores the entity's original color depending on its specific subclass type, such as Ghost, Wisp, or Ripper.
	 */
	public void resetColor() {
	    if (this instanceof Ghost) {
	        body.setFill(Color.WHITE);
	    } else if (this instanceof Wisp) {
	        body.setFill(Color.DARKRED);
	    } else if (this instanceof Ripper) {
	        body.setFill(Color.PURPLE);
	    }
	}
	
	/**
	 * Arif Erdem Taşgın 150124070
	 * * checkIfVacuumed Method:
	 * This method detects if the entity's body intersects with the vacuum's scanner area and triggers the shrinking effect if a collision occurs.
	 */
	public void checkIfVacuumed(Shape scannerTriangle, double second) {
	   
	    Shape intersection = Shape.intersect(scannerTriangle, this.body);
	    
	    
	    if (intersection.getBoundsInLocal().getWidth() != -1) {
	       
	        applyVacuumEffect(second);
	    }
	}
	
	/**
	 * Arif Erdem Taşgın 150124070
	 * * checkBounds Method:
	 * This method prevents the entity from moving off-screen by reversing its movement direction whenever it hits the defined game boundaries
	 */
	public void checkBounds(double minX,double minY,double widht,double height) {
		
		double maxX = minX+widht;
		double maxY = minY+height;
		double currentX = group.getTranslateX();
		double currentY = group.getTranslateY();
		
		if(currentX<=minX || currentX>=maxX) {
			dx *=-1;
		}
		
		if(currentY <= minY || currentY>=maxY) {
			dy*=-1;
		}
		currentX+=dx;
		currentY+=dy;
		group.setTranslateX(currentX);
		group.setTranslateY(currentY);
	}
	public Group getGroup() {
		return group;
	}
	public Shape getBody() {
		return body;
	}
}
