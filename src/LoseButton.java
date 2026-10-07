
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class LoseButton extends StackPane {
    private Rectangle background;
    private Text text;
    
    /** * Yavuz Selim Durdubaş 150124065
     * Constructor for the custom LoseButton component used in end-game screens.
     * Combines a background rectangle and styled text, mapping mouse press and release events to dynamically update the background color for user feedback.
     */
    public LoseButton(String buttonText) {
        background = new Rectangle(200, 40);
        background.setFill(Color.GRAY);

        text = new Text(buttonText);
        text.setFill(Color.WHITE);
        Font font = Font.loadFont(MenuButton.class.getResourceAsStream("Melted Monster.ttf"), 25);
        text.setFont(font);

        getChildren().addAll(background, text);

        setOnMousePressed(e -> {
            background.setFill(Color.RED); 
        });

        setOnMouseReleased(e -> {
            background.setFill(Color.GRAY);
        });
    }
}