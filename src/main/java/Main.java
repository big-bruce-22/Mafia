import org.fusesource.jansi.AnsiConsole;

import io.github.palexdev.materialfx.theming.JavaFXThemes;
import io.github.palexdev.materialfx.theming.MaterialFXStylesheets;
import io.github.palexdev.materialfx.theming.UserAgentBuilder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import ui.controllers.HomePanelController;
import util.ResourcesLoader;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
		UserAgentBuilder.builder()
			.themes(JavaFXThemes.MODENA)
			.themes(MaterialFXStylesheets.forAssemble(true))
			.setDeploy(true)
			.setResolveAssets(true)
			.build()
			.setGlobal();

		FXMLLoader loader = new FXMLLoader(ResourcesLoader.loadURL("/fxml/HomePanel.fxml"));
		loader.setControllerFactory(_ -> new HomePanelController(stage));

		stage.setScene(new Scene(loader.load()));
		stage.setMaximized(true);
		stage.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
			if (e.getCode() == KeyCode.F11) {
				stage.setFullScreen(!stage.isFullScreen());
			}
		});

		stage.setTitle("Mafia");
		stage.show();
    }

    public static void main(String[] args) {
		AnsiConsole.systemInstall();
        launch(args);
    }
}