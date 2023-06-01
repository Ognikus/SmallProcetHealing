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

public class HealerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AddPacientBTN;

    @FXML
    private TableColumn<Pacient, String> BolezPacientColumn;

    @FXML
    private TableColumn<Healer, Integer> CabinetHealerColumn;

    @FXML
    private Button DeletePacientBTN;

    @FXML
    private Button ExitBTN;

    @FXML
    private TableColumn<Pacient, String> HealerPacientColumn;

    @FXML
    private TableView<Healer> HealerTable;

    @FXML
    private TableColumn<Healer, Integer> IDHealerColumn;

    @FXML
    private TableColumn<Pacient, Integer> IDPacientColumn;

    @FXML
    private TextField IdFieldRedact;

    @FXML
    private TableColumn<Healer, String> NameHealerColumn;

    @FXML
    private TextField NameHealerField;

    @FXML
    private TableColumn<Pacient, String> NamePacientColumn;

    @FXML
    private TextField NamePacientField;

    @FXML
    private TextField NameZabolevanieField;

    @FXML
    private TableView<Pacient> PacientTable;

    @FXML
    private Button RedactPacientBTN;
    @FXML
    private Button UpdateHealer;

    @FXML
    private TableColumn<Healer, String> SpecHealerColumn;

    DataBaseHandler dbHandler = new DataBaseHandler();

    private final ObservableList<Healer> dthealer = FXCollections.observableArrayList();
    private final ObservableList<Pacient> dtpacient = FXCollections.observableArrayList();
    @FXML
    void initialize() {
        UpdateHealerTableColumn();
        UpdatePacientTableColumn();

        PacientTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                Pacient selectedPacient = PacientTable.getSelectionModel().getSelectedItem();
                if (selectedPacient != null) {

                    IdFieldRedact.setText(String.valueOf((selectedPacient.getId())));
                    NamePacientField.setText(selectedPacient.getPacientName());
                    NameZabolevanieField.setText(selectedPacient.getPacientDisease());
                    NameHealerField.setText(String.valueOf(selectedPacient.getPacientHealer()));
                }
            }
        });

        AddPacientBTN.setOnAction(actionEvent -> {
            Pacient pacient = new Pacient("", NamePacientField.getText(), NameZabolevanieField.getText(), NameHealerField.getText());
            dtpacient.clear();
            try {
                dbHandler.insertPacient(pacient);
            } catch (SQLException e) {
                throw new RuntimeException("Ошибка при добавлении данных в базу данных", e);
            } catch (Exception e) {
                throw new RuntimeException("Ошибка при добавлении данных в список", e);
            }
            UpdatePacientTableColumn();
        });

        RedactPacientBTN.setOnAction(actionEvent -> {
            Pacient selectedPacient = PacientTable.getSelectionModel().getSelectedItem();
            if (selectedPacient != null) {
                // Получите обновленные значения из текстовых полей
                String id = IdFieldRedact.getText();
                String updatedName = NamePacientField.getText();
                String updatedZabol = NameZabolevanieField.getText();
                String updatedHealer = NameHealerField.getText();

                // Обновить
                selectedPacient.setId(Integer.valueOf(id));
                selectedPacient.setPacientName(updatedName);
                selectedPacient.setPacientDisease(updatedZabol);
                selectedPacient.setPacientHealer(updatedHealer);



                // Здесь вызовите метод для обновления
                try {
                    dbHandler.updatePacient(selectedPacient);
                } catch (SQLException e) {
                    throw new RuntimeException("Ошибка при обновлении данных в базе данных", e);
                }

                // Очистите текстовые поля после обновления
                dtpacient.clear();
                IdFieldRedact.clear();
                NamePacientField.clear();
                NameZabolevanieField.clear();
                NameHealerField.clear();
                UpdatePacientTableColumn();
            } else {
                System.out.println("Пациент не найден");
            }
        });

        DeletePacientBTN.setOnAction(actionEvent -> {
            try {
                int pacientIdToDelete = Integer.parseInt(IdFieldRedact.getText()); // Идентификатор игры, которую нужно удалить
                dbHandler.deletePacient(pacientIdToDelete);
                System.out.println("Запись успешно удалена");
            } catch (SQLException e) {
                throw new RuntimeException("Ошибка при удалении записи из базы данных", e);
            }
            dtpacient.clear();
            IdFieldRedact.clear();
            NamePacientField.clear();
            NameZabolevanieField.clear();
            NameHealerField.clear();
            UpdatePacientTableColumn();
        });

        UpdateHealer.setOnAction(actionEvent -> {
            dthealer.clear();
            UpdateHealerTableColumn();
        });

        ExitBTN.setOnAction(actionEvent -> {
            AutoController lg = new AutoController();
            lg.openNewWindow((Stage) ExitBTN.getScene().getWindow(), "AutoPage.fxml");;
        });

    }

    private void UpdatePacientTableColumn(){
        try {
            addInfAboutPacient();
            IDPacientColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
            NamePacientColumn.setCellValueFactory(new PropertyValueFactory<>("PacientName"));
            BolezPacientColumn.setCellValueFactory(new PropertyValueFactory<>("PacientDisease"));
            HealerPacientColumn.setCellValueFactory(new PropertyValueFactory<>("PacientHealer"));
            PacientTable.setItems(dtpacient);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void addInfAboutPacient() throws  SQLException{
        ResultSet pacien = dbHandler.getPacient();
        while (pacien.next()){
            Pacient pacient = new Pacient(
                    pacien.getString(1),
                    pacien.getString(2),
                    pacien.getString(3),
                    pacien.getString(4));
            dtpacient.add(pacient);
        }
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
