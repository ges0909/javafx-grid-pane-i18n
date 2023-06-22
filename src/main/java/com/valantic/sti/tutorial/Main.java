package com.valantic.sti.tutorial;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.Map;

public class Main extends Application {

    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_PASSWORD_PROMPT = "password_prompt";
    private static final String KEY_LOGIN = "login";
    private static final String KEY_TOOLTIP = "tooltip";

    private Stage window;

    public static void main(final String... args) {
        launch(args);
    }

    @Override
    public void start(final Stage stage) {
        window = stage;
        window.setTitle("Grid Pane with Localization");
        final LocalizationService localizationService = createLocalizationService();
        final Scene scene = new Scene(createContent(localizationService), 350, 150);
        window.setScene(scene);
        window.show();
    }

    private Parent createContent(final LocalizationService localizationService) {
        final GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8); // between cells
        grid.setHgap(10); // between cells

        final Label nameLabel = new Label();
        GridPane.setConstraints(nameLabel, 0, 0);
        nameLabel.textProperty().bind(localizationService.localizedStringBinding(KEY_USERNAME));

        final TextField nameInput = new TextField("valantic");
        GridPane.setConstraints(nameInput, 1, 0);

        final Label passwordLabel = new Label();
        GridPane.setConstraints(passwordLabel, 0, 1);
        passwordLabel.textProperty().bind(localizationService.localizedStringBinding(KEY_PASSWORD));

        final TextField passwordInput = new TextField();
        GridPane.setConstraints(passwordInput, 1, 1);
        passwordInput.setPromptText(null);
        passwordInput.promptTextProperty().bind(localizationService.localizedStringBinding(KEY_PASSWORD_PROMPT));

        final Button loginButton = new Button();
        GridPane.setConstraints(loginButton, 1, 2);
        final Tooltip buttonToolTip = new Tooltip();
        buttonToolTip.textProperty().bind(localizationService.localizedStringBinding(KEY_TOOLTIP));
        loginButton.setTooltip(buttonToolTip);
        loginButton.textProperty().bind(localizationService.localizedStringBinding(KEY_LOGIN));

        final ChoiceBox<Locale> localeChoiceBox = new ChoiceBox<>(
                FXCollections.observableArrayList(Locale.ENGLISH, Locale.GERMAN)
        );
        GridPane.setConstraints(localeChoiceBox, 0, 3);
        localeChoiceBox.getSelectionModel().selectFirst();
        localizationService.getSelectedLocale().bind(localeChoiceBox.valueProperty());

        grid.getChildren().addAll(
                nameLabel,
                nameInput,
                passwordLabel,
                passwordInput,
                loginButton,
                localeChoiceBox
        );

        return grid;
    }

    private LocalizationService createLocalizationService() {
        final LocalizationService localizationService = new LocalizationService(Locale.GERMAN);
        localizationService.addTranslations(
                Locale.ENGLISH,
                Map.of(
                        KEY_USERNAME, "Username",
                        KEY_PASSWORD, "Password",
                        KEY_PASSWORD_PROMPT, "password (8 char's)",
                        KEY_LOGIN, "Log In",
                        KEY_TOOLTIP, "This is a tooltip"
                ));
        localizationService.addTranslations(
                Locale.GERMAN,
                Map.of(
                        KEY_USERNAME, "Nutzer",
                        KEY_PASSWORD, "Passwort",
                        KEY_PASSWORD_PROMPT, "Geheimwort (8 Zeichen)",
                        KEY_LOGIN, "Anmelden",
                        KEY_TOOLTIP, "Hier gehts zur Anmeldung"
                ));
        return localizationService;
    }
}
