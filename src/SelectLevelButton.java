
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SelectLevelButton extends StackPane {

	private Rectangle background;
	
	/** * Yavuz Selim Durdubaş 150124065
     * Constructor for generating graphic-based level selection buttons.
     * Loads a preview image for the level, layers it with stylized text over a purple rectangle, and registers listeners for hover and click state transformations.
     */
	public SelectLevelButton(String levelNumber, String imagePath) {

		background = new Rectangle(240, 150);
		background.setFill(Color.PURPLE);

		ImageView imageView = new ImageView();
		Image img = new Image(getClass().getResourceAsStream(imagePath));
		imageView.setImage(img);
		imageView.setFitWidth(210);
		imageView.setFitHeight(120);

		Text text = new Text(levelNumber);
		text.setFont(Font.font("Chiller", 40));
		Font chillerFont = Font.loadFont(EndGamePanels.class.getResourceAsStream("chiller.ttf"), 50);
		text.setFont(chillerFont);
		text.setFill(Color.WHITE);
		text.setStroke(Color.BLACK);
		text.setStrokeWidth(1);

		getChildren().addAll(background, imageView, text);

		setOnMouseEntered(event -> {
			background.setFill(Color.MEDIUMPURPLE);
		});

		setOnMouseExited(event -> {
			background.setFill(Color.PURPLE);
		});

		setOnMousePressed(event -> {
			background.setFill(Color.WHITE);
		});

		setOnMouseReleased(event -> {
			background.setFill(Color.MEDIUMPURPLE);
		});
		background.setStroke(Color.rgb(200, 150, 255));
        background.setStrokeWidth(2);
        background.setStrokeType(StrokeType.INSIDE);
	}

}
