package com.example.healingcontroller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AddHealerBTN;

    @FXML
    private TableColumn<Healer, Integer> CabinetHealerColumn;

    @FXML
    private TextField CabinetHealerField;

    @FXML
    private Button DeleteHealerBTN;

    @FXML
    private Button ExitBTN;

    @FXML
    private TableView<Healer> HealerTable;

    @FXML
    private TableColumn<Healer, Integer> IDHealerColumn;

    @FXML
    private TextField IdFieldRedact;

    @FXML
    private TableColumn<Healer, String> NameHealerColumn;

    @FXML
    private TextField NameHealerField;

    @FXML
    private Button RedactHealerBTN;

    @FXML
    private TableColumn<Healer, String> SpecHealerColumn;

    @FXML
    private TextField SpecHealerField;

    DataBaseHandler dbHandler = new DataBaseHandler();

    private final ObservableList<Healer> dthealer = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        HealerTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                Healer selectedHealer = HealerTable.getSelectionModel().getSelectedItem();
                if (selectedHealer != null) {

                    IdFieldRedact.setText(String.valueOf((selectedHealer.getId())));
                    NameHealerColumn.setText(selectedHealer.getHealerName());
                    SpecHealerColumn.setText(selectedHealer.getSpecHealer());
                    CabinetHealerColumn.setText(String.valueOf(selectedHealer.getCabinetHealer()));
                }
            }
        });

        AddHealerBTN.setOnAction(actionEvent -> {
            Healer healer = new Healer("",NameHealerField.getText(), SpecHealerField.getText(), CabinetHealerField.getText());
            dthealer.clear();
            try {
                dbHandler.insertHealer(healer);
            } catch (SQLException e) {
                throw new RuntimeException("Ошибка при добавлении данных в базу данных", e);
            } catch (Exception e) {
                throw new RuntimeException("Ошибка при добавлении данных в список", e);
            }
            UpdateHealerTableColumn();
        });

        RedactHealerBTN.setOnAction(actionEvent -> {
            Healer selectedHealer = HealerTable.getSelectionModel().getSelectedItem();
            if (selectedHealer != null) {
                // Получите обновленные значения из текстовых полей
                String id = IdFieldRedact.getText();
                String updatedName = NameHealerField.getText();
                String updatedSpec = SpecHealerField.getText();
                String updatedCabinet = CabinetHealerField.getText();

                // Обновить
                selectedHealer.setId(Integer.valueOf(id));
                selectedHealer.setHealerName(updatedName);
                selectedHealer.setSpecHealer(updatedSpec);
                selectedHealer.setCabinetHealer(Integer.valueOf(updatedCabinet));



                // Здесь вызовите метод для обновления
                try {
                    dbHandler.updateHealer(selectedHealer);
                } catch (SQLException e) {
                    throw new RuntimeException("Ошибка при обновлении данных в базе данных", e);
                }

                // Очистите текстовые поля после обновления
                dthealer.clear();
                IdFieldRedact.clear();
                NameHealerField.clear();
                SpecHealerField.clear();
                CabinetHealerField.clear();
                UpdateHealerTableColumn();
            } else {
                System.out.println("Врач не найден");
            }
        });

        DeleteHealerBTN.setOnAction(actionEvent -> {
            try {
                int healerIdToDelete = Integer.parseInt(IdFieldRedact.getText()); // Идентификатор игры, которую нужно удалить
                dbHandler.deleteHealer(healerIdToDelete);
                System.out.println("Запись успешно удалена");
            } catch (SQLException e) {
                throw new RuntimeException("Ошибка при удалении записи из базы данных", e);
            }
            dthealer.clear();
            IdFieldRedact.clear();
            NameHealerField.clear();
            SpecHealerField.clear();
            CabinetHealerField.clear();
            UpdateHealerTableColumn();
        });

        ExitBTN.setOnAction(actionEvent -> {
            AutoController lg = new AutoController();
            lg.openNewWindow((Stage) ExitBTN.getScene().getWindow(), "AutoPage.fxml");;
        });
    }



    private void UpdateHealerTableColumn(){
        try {
            addInfAboutHealer();
            IDHealerColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
            NameHealerColumn.setCellValueFactory(new PropertyValueFactory<>("HealerName"));
            SpecHealerColumn.setCellValueFactory(new PropertyValueFactory<>("SpecHealer"));
            CabinetHealerColumn.setCellValueFactory(new PropertyValueFactory<>("CabinetHealer"));
            HealerTable.setItems(dthealer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void addInfAboutHealer() throws  SQLException{
        ResultSet heal = dbHandler.getHealer();
        while (heal.next()){
            Healer healer = new Healer(
                    heal.getString(1),
                    heal.getString(2),
                    heal.getString(3),
                    heal.getString(4));
            dthealer.add(healer);
        }
    }
}
