package WG.by.fpmibsu.dao;

import WG.by.fpmibsu.entity.Country;
import WG.by.fpmibsu.entity.FactQuiz;

import java.sql.*;
import java.util.ArrayList;

public class CountryDao extends ConnectionInit {
    Connection connection;
    public CountryDao() throws DaoException {
        super();
    }
    private final String update = "UPDATE Country SET Name = ?, Area = ?, Population = ?," +
            " Continent = ?, Fact = ?, Flag = ?, Territory = ? WHERE Country_ID = ?";
    private final String delete = "DELETE FROM Country WHERE Country_ID = ?";
    private final String readAll = "SELECT * FROM Country";
    private final String read = "SELECT * FROM Country WHERE Country_ID = ?";
    final private String count = "SELECT COUNT(*) AS quantity FROM Country;";
    private final String create = "INSERT INTO FactQuiz (FactText, Answer, Variant1," +
            " Variant2, Variant3, Variant4) VALUES (?, ?, ?, ?, ?, ?)";

    public void CountryDAO() {}

    public boolean create(FactQuiz factQuiz) throws DaoException {
        connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(create)) {
            statement.setString(1, factQuiz.getFact());
            statement.setInt(2, factQuiz.getAnswer());
            statement.setInt(3, factQuiz.getFirstVariant().getID());
            statement.setInt(4, factQuiz.getSecondVariant().getID());
            statement.setInt(5, factQuiz.getThirdVariant().getID());
            statement.setInt(6, factQuiz.getFourthVariant().getID());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            throw new DaoException("Error creating FactQuiz", e);
        }
    }

    public void update(Country country) throws SQLException, DaoException {
        connection = connectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(update);
        statement.setString(1, country.getName());
        statement.setFloat(2, country.getArea());
        statement.setInt(3, country.getPopulation());
        statement.setString(4, country.getContinent());
        statement.setString(5, country.getFact());
        statement.setString(6, country.getFlag());
        statement.setString(7, country.getTerritory());
        statement.setInt(8, country.getID());
        statement.executeUpdate();
        statement.close();
    }

    public void delete(Country country) throws SQLException, DaoException {
        connection = connectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(delete);
        statement.setInt(1, country.getID());
        statement.executeUpdate();
        statement.close();
    }

    public ArrayList<Country> readAll() throws SQLException, DaoException {
        ArrayList<Country> Country = new ArrayList<>();
        connection = connectionPool.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(readAll);
        while (resultSet.next()) {
            Country country = new Country(
                    resultSet.getInt("Country_ID"),
                    resultSet.getString("Name"),
                    resultSet.getFloat("Area"),
                    resultSet.getInt("Population"),
                    resultSet.getString("Continent"),
                    resultSet.getString("Fact"),
                    resultSet.getString("Flag"),
                    resultSet.getString("Territory")
            );
            Country.add(country);
        }
        resultSet.close();
        statement.close();
        return Country;
    }

    public int returnCount() throws DaoException {
        try {
            connection = connectionPool.getConnection();
            String query = count;
            int quantity = -1;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                String temp = resultSet.getString("quantity");
                quantity = Integer.parseInt(temp);

            }
            statement.close();
            connection.close();
            return quantity;
        } catch (SQLException e) {
            throw new DaoException("Error returning count", e);
        }
    }
    public Country read(int id) throws SQLException, DaoException {
        connection = connectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(read);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        Country country = null;
        if (resultSet.next()) {
            country = new Country(
                    resultSet.getInt("Country_ID"),
                    resultSet.getString("Name"),
                    resultSet.getFloat("Area"),
                    resultSet.getInt("Population"),
                    resultSet.getString("Continent"),
                    resultSet.getString("Fact"),
                    resultSet.getString("Flag"),
                    resultSet.getString("Territory")
            );
        }
        resultSet.close();
        statement.close();
        return country;
    }
}
