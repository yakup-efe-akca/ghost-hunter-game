import javafx.scene.Group;
import javafx.scene.shape.Shape;

/**
 * Arif Erdem Taşgın 150124070
 * * BaseToken Class:
 * The BaseToken abstract class serves as a blueprint for all collectible items in the game, defining their shared visual properties and requiring them to implement a specific power-up effect for the player.
 */

public abstract class BaseToken {

	protected Group group;
	protected String type;
	protected Shape body;
	
	public abstract void applyBenefit (Hunter hunter);

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Shape getBody() {
		return body;
	}

	public void setBody(Shape body) {
		this.body = body;
	}
}