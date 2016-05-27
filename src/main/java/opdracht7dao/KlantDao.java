// created especially for opdracht 7b. Workshop Part 2

package opdracht7dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pojo.*;
import service.*;

public class KlantDao {

    private Connection connection;

    public KlantDao() {
        connection = DbUtil.getConnection();
    }

    public void addKlant(Klant klant) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into klant(voornaam,achternaam,tussenvoegsel,email) values (?, ?, ?, ? )");
            // Parameters start with 1
            preparedStatement.setString(1, klant.getVoornaam());
            preparedStatement.setString(2, klant.getAchternaam());
            preparedStatement.setString(3, klant.getTussenvoegsel());
            preparedStatement.setString(4, klant.getEmail());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteKlant(int klantId) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from klant where klant_id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, klantId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateKlant(Klant klant) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update klant set voornaam=?, achternaam=?, tussenvoegsel=?, email=?" +
                            "where klant_id=?");
            // Parameters start with 1
            preparedStatement.setString(1, klant.getVoornaam());
            preparedStatement.setString(2, klant.getAchternaam());
            preparedStatement.setString(3, klant.getTussenvoegsel());
            preparedStatement.setString(4, klant.getEmail());
            preparedStatement.setLong(5, klant.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Klant> getAllKlanten() {
        List<Klant> klanten = new ArrayList<Klant>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from klant");
            while (rs.next()) {
                Klant klant = new Klant();
                klant.setId(rs.getLong("klant_id"));
                klant.setVoornaam(rs.getString("voornaam"));
                klant.setAchternaam(rs.getString("achternaam"));
                klant.setTussenvoegsel(rs.getString("tussenvoegsel"));
                klant.setEmail(rs.getString("email"));
                klanten.add(klant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return klanten;
    }

    public Klant getKlantById(int klantId) {
        Klant klant = new Klant();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from klant where klant_id=?");
            preparedStatement.setInt(1, klantId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	 klant.setId(rs.getLong("klant_id"));
                 klant.setVoornaam(rs.getString("voornaam"));
                 klant.setAchternaam(rs.getString("achternaam"));
                 klant.setTussenvoegsel(rs.getString("tussenvoegsel"));
                 klant.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return klant;
    }
}