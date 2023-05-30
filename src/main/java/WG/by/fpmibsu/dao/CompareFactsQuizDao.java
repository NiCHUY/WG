package WG.by.fpmibsu.dao;
import WG.by.fpmibsu.entity.CompareFactsQuiz;
import WG.by.fpmibsu.entity.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompareFactsQuizDao implements ModeDao<Integer, CompareFactsQuiz> {
    private Connection connection;

    public CompareFactsQuizDao(Connection connection) {
        this.connection = connection;
    }
    private final String create = "INSERT INTO CompareFactQuiz (Question1, Question2, Question3, Question4," +
            " Variant1, Variant2, Variant3, Variant4, Answer1, Answer2, Answer3, Answer4)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String read = "SELECT * FROM CompareFactQuiz WHERE CompareFactQuiz_ID = ?";
    private final String update = "UPDATE CompareFactQuiz SET Question1 = ?, Question2 = ?, Question3 = ?, Question4 = ?," +
            " Variant1 = ?, Variant2 = ?, Variant3 = ?, Variant4 = ?, Answer1 = ?, Answer2 = ?, Answer3 = ?, Answer4 = ?" +
            " WHERE CompareFactQuiz_ID = ?";
    private final String delete = "DELETE FROM CompareFactQuiz WHERE CompareFactQuiz_ID = ?";
    private final String readALL = "SELECT * FROM CompareFactQuiz";

    public CompareFactsQuizDao() {
    }

    @Override
    public boolean create(CompareFactsQuiz compareFactsQuiz, Connection connection) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(create)) {
            statement.setString(1, compareFactsQuiz.getFirstQuestion());
            statement.setString(2, compareFactsQuiz.getSecondQuestion());
            statement.setString(3, compareFactsQuiz.getThirdQuestion());
            statement.setString(4, compareFactsQuiz.getFourthQuestion());
            statement.setInt(5, compareFactsQuiz.getFirstVariant().getID());
            statement.setInt(6, compareFactsQuiz.getSecondVariant().getID());
            statement.setInt(7, compareFactsQuiz.getThirdVariant().getID());
            statement.setInt(8, compareFactsQuiz.getFourthVariant().getID());
            statement.setInt(9, compareFactsQuiz.getFirstAnswer());
            statement.setInt(10, compareFactsQuiz.getSecondAnswer());
            statement.setInt(11, compareFactsQuiz.getThirdAnswer());
            statement.setInt(12, compareFactsQuiz.getFourthAnswer());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            throw new DaoException("Error creating CompareFactsQuiz", e);
        }
    }

    @Override
    public CompareFactsQuiz read(Integer id, Connection connection) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(read)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int compareFactsQuizID = resultSet.getInt("CompareFactQuiz_ID");
                String firstQuestion = resultSet.getString("Question1");
                String secondQuestion = resultSet.getString("Question2");
                String thirdQuestion = resultSet.getString("Question3");
                String fourthQuestion = resultSet.getString("Question4");
                int variant1ID = resultSet.getInt("Variant1");
                int variant2ID = resultSet.getInt("Variant2");
                int variant3ID = resultSet.getInt("Variant3");
                int variant4ID = resultSet.getInt("Variant4");
                int firstAnswer = resultSet.getInt("Answer1");
                int secondAnswer = resultSet.getInt("Answer2");
                int thirdAnswer = resultSet.getInt("Answer3");
                int fourthAnswer = resultSet.getInt("Answer4");
                connection = ConnectionCreator.createConnection();
                Country variant1 = new CountryDao(connection).read(variant1ID, connection);
                connection = ConnectionCreator.createConnection();
                Country variant2 = new CountryDao(connection).read(variant2ID, connection);
                connection = ConnectionCreator.createConnection();
                Country variant3 = new CountryDao(connection).read(variant3ID, connection);
                connection = ConnectionCreator.createConnection();
                Country variant4 = new CountryDao(connection).read(variant4ID, connection);
                return new CompareFactsQuiz(compareFactsQuizID, firstQuestion, secondQuestion, thirdQuestion, fourthQuestion, variant1, variant2, variant3, variant4, firstAnswer, secondAnswer, thirdAnswer, fourthAnswer);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DaoException("Error reading CompareFactsQuiz", e);
        }
    }

    @Override
    public CompareFactsQuiz update(CompareFactsQuiz compareFactsQuiz, Connection connection) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(update)) {
            statement.setString(1, compareFactsQuiz.getFirstQuestion());
            statement.setString(2, compareFactsQuiz.getSecondQuestion());
            statement.setString(3, compareFactsQuiz.getThirdQuestion());
            statement.setString(4, compareFactsQuiz.getFourthQuestion());
            statement.setInt(5, compareFactsQuiz.getFirstVariant().getID());
            statement.setInt(6, compareFactsQuiz.getSecondVariant().getID());
            statement.setInt(7, compareFactsQuiz.getThirdVariant().getID());
            statement.setInt(8, compareFactsQuiz.getFourthVariant().getID());
            statement.setInt(9, compareFactsQuiz.getFirstAnswer());
            statement.setInt(10, compareFactsQuiz.getSecondAnswer());
            statement.setInt(11, compareFactsQuiz.getThirdAnswer());
            statement.setInt(12, compareFactsQuiz.getFourthAnswer());
            statement.setInt(13, compareFactsQuiz.getCompareFactsQuizID());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                return compareFactsQuiz;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DaoException("Error updating CompareFactsQuiz", e);
        }
    }

    @Override
    public boolean delete(Integer id, Connection connection) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(delete)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw new DaoException("Error deleting CompareFactsQuiz", e);
        }
    }

    @Override
    public List<CompareFactsQuiz> readAll(Connection connection) throws DaoException {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(readALL);
            List<CompareFactsQuiz> compareFactsQuizzes = new ArrayList<>();
            while (resultSet.next()) {
                int compareFactsQuizID = resultSet.getInt("CompareFactQuiz_ID");
                String firstQuestion = resultSet.getString("Question1");
                String secondQuestion = resultSet.getString("Question2");
                String thirdQuestion = resultSet.getString("Question3");
                String fourthQuestion = resultSet.getString("Question4");
                int variant1ID = resultSet.getInt("Variant1");
                int variant2ID = resultSet.getInt("Variant2");
                int variant3ID = resultSet.getInt("Variant3");
                int variant4ID = resultSet.getInt("Variant4");
                int firstAnswer = resultSet.getInt("Answer1");
                int secondAnswer = resultSet.getInt("Answer2");
                int thirdAnswer = resultSet.getInt("Answer3");
                int fourthAnswer = resultSet.getInt("Answer4");
                connection = ConnectionCreator.createConnection();
                Country variant1 = new CountryDao(connection).read(variant1ID, connection);
                connection = ConnectionCreator.createConnection();
                Country variant2 = new CountryDao(connection).read(variant2ID, connection);
                connection = ConnectionCreator.createConnection();
                Country variant3 = new CountryDao(connection).read(variant3ID, connection);
                connection = ConnectionCreator.createConnection();
                Country variant4 = new CountryDao(connection).read(variant4ID, connection);
                CompareFactsQuiz compareFactsQuiz = new CompareFactsQuiz(compareFactsQuizID, firstQuestion, secondQuestion, thirdQuestion, fourthQuestion, variant1, variant2, variant3, variant4, firstAnswer, secondAnswer, thirdAnswer, fourthAnswer);
                compareFactsQuizzes.add(compareFactsQuiz);
            }
            return compareFactsQuizzes;
        } catch (SQLException e) {
            throw new DaoException("Error reading CompareFactsQuizzes", e);
        }
    }
}