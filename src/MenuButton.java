
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MenuButton extends StackPane{
	private Rectangle background;
    private Text text;
    
    /** * Yavuz Selim Durdubaş 150124065
     * Constructor for the primary interactive MenuButton component.
     * Assembles a custom-styled button with a purple background, a colored stroke, and a loaded custom font. Handles visual state changes when pressed or released.
     */
    public MenuButton(String buttonText) {
        background = new Rectangle(233, 55);
        background.setFill(Color.rgb(128, 0, 128)); 
        
        
        
        background.setStroke(Color.rgb(200, 150, 255));
        background.setStrokeWidth(2);
        background.setStrokeType(StrokeType.INSIDE);
        
        
        
        text = new Text(buttonText);
        text.setFill(Color.WHITE);
        Font font = Font.loadFont(MenuButton.class.getResourceAsStream("Melted Monster.ttf"), 25);
        text.setFont(font);
        
        
        getChildren().addAll(background, text);

        setOnMousePressed(event -> {
            background.setFill(Color.WHITE);
            text.setFill(Color.RED);
        });

        setOnMouseReleased(event -> {
            background.setFill(Color.rgb(128, 0, 128));
            text.setFill(Color.WHITE);
        });
    }
}