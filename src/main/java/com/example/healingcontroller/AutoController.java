package com.example.healingcontroller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AutoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button LoginBTN;

    @FXML
    private TextField LoginField;

    @FXML
    private Button LoginHealerBTN;

    @FXML
    private PasswordField PasswordField;

    @FXML
    void initialize() {
        LoginBTN.setOnAction(actionEvent -> {
            String loginText = LoginField.getText().trim();
            String passText = PasswordField.getText().trim();

            if (!loginText.equals("") && !passText.equals("")) {
                if (loginText.equals("admin") && passText.equals("admin")) {
                    openNewWindow((Stage) LoginBTN.getScene().getWindow(), "AdminPage.fxml");
                } else {
                    System.out.println("Неверный логин или пароль");
                    }
            }
            else {
                System.out.println("Логин или пароль пусты!");
            }
        });

        LoginHealerBTN.setOnAction(actionEvent -> {
            openNewWindow((Stage) LoginBTN.getScene().getWindow(), "HealerPage.fxml");
        });
    }

    public void openNewWindow(Stage currentStage, String window) {
        try {
            // Загрузка файла FXML для нового окна
            FXMLLoader loader = new FXMLLoader(getClass().getResource(window));
            Parent root = loader.load();

            // Создание новой сцены и установка корневого узла
            Scene scene = new Scene(root);

            // Создание нового окна
            Stage stage = new Stage();
            stage.setTitle("");
            stage.setScene(scene);

            // Закрытие текущего окна
            currentStage.close();

            // Показать новое окно
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
