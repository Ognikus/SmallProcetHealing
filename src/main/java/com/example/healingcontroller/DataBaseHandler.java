package com.example.healingcontroller;
import java.sql.*;

public class DataBaseHandler {

    private Connection connection;
    ResultSet resSet = null;

    public Connection getDBConnection() throws SQLException {
        String connectionStrings = "jdbc:postgresql://localhost:5432/HealerBD";
        connection = DriverManager.getConnection(connectionStrings, "postgres", "admin");
        return connection;
    }

    public boolean insertHealer(Healer healer) throws SQLException {
        String insertUser = "INSERT INTO healer (имя_врача, специальность, кабинет) VALUES (?, ?, ?)";

        try (PreparedStatement prSt = getDBConnection().prepareStatement(insertUser)) {
            prSt.setString(1, healer.getHealerName());
            prSt.setString(2, healer.getSpecHealer());
            prSt.setInt(3, healer.getCabinetHealer());
            int rowsAffected = prSt.executeUpdate();
            return rowsAffected > 0; // Возвращаем true, если были затронуты строки
        }
    }

    public ResultSet getHealer() throws SQLException {
        String getGames = "SELECT * FROM healer";
        PreparedStatement prST = getDBConnection().prepareStatement(getGames);
        resSet = prST.executeQuery();
        return resSet;
    }

    public void updateHealer(Healer healer) throws SQLException {
        String updateQuery = "UPDATE healer SET имя_врача = ?, специальность = ?, кабинет = ? WHERE id = ?";
        PreparedStatement prSt = getDBConnection().prepareStatement(updateQuery);
        prSt.setString(1, healer.getHealerName());
        prSt.setString(2, healer.getSpecHealer());
        prSt.setInt(3, healer.getCabinetHealer());
        prSt.setInt(4, healer.getId());
        prSt.executeUpdate();
    }

    public void deleteHealer(int healerId) throws SQLException {
        String deleteQuery = "DELETE FROM healer WHERE id = ?";
        PreparedStatement prSt = getDBConnection().prepareStatement(deleteQuery);
        prSt.setInt(1, healerId);
        prSt.executeUpdate();
    }

    public boolean insertPacient(Pacient pacient) throws SQLException {
        String insertUser = "INSERT INTO pacient (имя_пациента, заболевание, лечащий_врач) VALUES (?, ?, ?)";

        try (PreparedStatement prSt = getDBConnection().prepareStatement(insertUser)) {
            prSt.setString(1, pacient.getPacientName());
            prSt.setString(2, pacient.getPacientDisease());
            prSt.setString(3, pacient.getPacientHealer());
            int rowsAffected = prSt.executeUpdate();
            return rowsAffected > 0; // Возвращаем true, если были затронуты строки
        }
    }

    public ResultSet getPacient() throws SQLException {
        String getGames = "SELECT * FROM pacient";
        PreparedStatement prST = getDBConnection().prepareStatement(getGames);
        resSet = prST.executeQuery();
        return resSet;
    }

    public void updatePacient(Pacient pacient) throws SQLException {
        String updateQuery = "UPDATE pacient SET имя_пациента = ?, заболевание = ?, лечащий_врач = ? WHERE id = ?";
        PreparedStatement prSt = getDBConnection().prepareStatement(updateQuery);
        prSt.setString(1, pacient.getPacientName());
        prSt.setString(2, pacient.getPacientDisease());
        prSt.setString(3, pacient.getPacientHealer());
        prSt.setInt(4, pacient.getId());
        prSt.executeUpdate();
    }

    public void deletePacient(int pacientId) throws SQLException {
        String deleteQuery = "DELETE FROM pacient WHERE id = ?";
        PreparedStatement prSt = getDBConnection().prepareStatement(deleteQuery);
        prSt.setInt(1, pacientId);
        prSt.executeUpdate();
    }

}
