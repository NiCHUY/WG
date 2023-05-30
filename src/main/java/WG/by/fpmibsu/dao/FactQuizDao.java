package WG.by.fpmibsu.dao;


import WG.by.fpmibsu.entity.Country;
import WG.by.fpmibsu.entity.FactQuiz;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FactQuizDao extends ConnectionInit implements ModeDao<Integer, FactQuiz> {
    private final String create = "INSERT INTO FactQuiz (FactQuiz_ID, FactText, Answer, Variant1," +
            " Variant2, Variant3, Variant4) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String read = "SELECT * FROM FactQuiz WHERE FactQuiz_ID = ?";
    private final String update = "UPDATE FactQuiz SET FactText = ?, Answer = ?, Variant1 = ?," +
            " Variant2 = ?, Variant3 = ?, Variant4 = ? WHERE FactQuiz_ID  = ?";
    private final String delete = "DELETE FROM FactQuiz WHERE FactQuiz_ID  = ?";
    private final String readAll = "SELECT * FROM FactQuiz";
    final private String count = "SELECT COUNT(*) AS quantity FROM FactQuiz;";


    Connection connection;

    public FactQuizDao() throws DaoException {
        super();
    }

    @Override
    public boolean create(FactQuiz factQuiz) throws DaoException {
        connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(create)) {
            statement.setInt(1, factQuiz.getFactQuizID());
            statement.setString(2, factQuiz.getFact());
            statement.setInt(3, factQuiz.getAnswer());
            statement.setInt(4, factQuiz.getFirstVariant().getID());
            statement.setInt(5, factQuiz.getSecondVariant().getID());
            statement.setInt(6, factQuiz.getThirdVariant().getID());
            statement.setInt(7, factQuiz.getFourthVariant().getID());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            throw new DaoException("Error creating FactQuiz", e);
        }
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

    @Override
    public FactQuiz read(Integer id) throws DaoException {
        connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(read)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int factQuizID = resultSet.getInt("FactQuiz_ID");
                String fact = resultSet.getString("FactText");
                int answer = resultSet.getInt("Answer");
                int variant1ID = resultSet.getInt("Variant1");
                int variant2ID = resultSet.getInt("Variant2");
                int variant3ID = resultSet.getInt("Variant3");
                int variant4ID = resultSet.getInt("Variant4");
                Country variant1 = new CountryDao().read(variant1ID);
                Country variant2 = new CountryDao().read(variant2ID);
                Country variant3 = new CountryDao().read(variant3ID);
                Country variant4 = new CountryDao().read(variant4ID);
                return new FactQuiz(factQuizID, fact, variant1, variant2, variant3, variant4, answer);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DaoException("Error reading FactQuiz", e);
        }
    }

    @Override
    public FactQuiz update(FactQuiz factQuiz) throws DaoException {
        connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(update)) {
            statement.setString(1, factQuiz.getFact());
            statement.setInt(2, factQuiz.getAnswer());
            statement.setInt(3, factQuiz.getFirstVariant().getID());
            statement.setInt(4, factQuiz.getSecondVariant().getID());
            statement.setInt(5, factQuiz.getThirdVariant().getID());
            statement.setInt(6, factQuiz.getFourthVariant().getID());
            statement.setInt(7, factQuiz.getFactQuizID());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                return factQuiz;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DaoException("Error updating FactQuiz", e);
        }
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(delete)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw new DaoException("Error deleting FactQuiz", e);
        }
    }

    @Override
    public List<FactQuiz> readAll() throws DaoException {
        connection = connectionPool.getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(readAll);
            List<FactQuiz> factQuizzes = new ArrayList<>();
            while (resultSet.next()) {
                int factQuizID = resultSet.getInt("FactQuiz_ID");
                String fact = resultSet.getString("FactText");
                int answer = resultSet.getInt("Answer");
                int variant1ID = resultSet.getInt("Variant1");
                int variant2ID = resultSet.getInt("Variant2");
                int variant3ID = resultSet.getInt("Variant3");
                int variant4ID = resultSet.getInt("Variant4");
                Country variant1 = new CountryDao().read(variant1ID);
                Country variant2 = new CountryDao().read(variant2ID);
                Country variant3 = new CountryDao().read(variant3ID);
                Country variant4 = new CountryDao().read(variant4ID);
                factQuizzes.add(new FactQuiz(factQuizID, fact, variant1, variant2, variant3, variant4, answer));
            }
            return factQuizzes;
        } catch (SQLException e) {
            throw new DaoException("Error reading FactQuizzes", e);
        }
    }
}