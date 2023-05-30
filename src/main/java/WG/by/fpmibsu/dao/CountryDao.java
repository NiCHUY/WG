package WG.by.fpmibsu.dao;

import WG.by.fpmibsu.entity.Country;

import java.awt.*;
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
    public void CountryDAO() {}

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
