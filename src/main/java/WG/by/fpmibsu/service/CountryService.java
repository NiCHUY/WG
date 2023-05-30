package WG.by.fpmibsu.service;

import WG.by.fpmibsu.dao.*;
import WG.by.fpmibsu.entity.FlagQuiz;
import WG.by.fpmibsu.entity.MapQuiz;
import WG.by.fpmibsu.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class CountryService {

    private static final Logger LOGGER = LogManager.getLogger(FlagService.class);
    public static int returnNum() throws SQLException, DaoException {
        Connection connection = ConnectionCreator.createConnection();
        MapQuizDao mapQuizDao = new MapQuizDao(connection);
        int count = mapQuizDao.returnCount(connection);
        return (int) (Math.random() * count);
    }
    public static void init(HttpServletRequest req) throws SQLException, DaoException {
        LOGGER.trace("Initialising FlagQuiz question.");
        Connection connection = ConnectionCreator.createConnection();
        MapQuizDao mapQuizDao = new MapQuizDao(connection);
        MapQuiz mapQuiz = mapQuizDao.read(returnNum(),connection);
        req.setAttribute("img","image/territory/" + (mapQuiz.getCountry().getTerritory().replace(" ", ""))  + ".png");
        req.setAttribute("fact", mapQuiz.getCountry().getFact());
        req.setAttribute("area", mapQuiz.getCountry().getArea());
        req.setAttribute("population", mapQuiz.getCountry().getPopulation());
        req.setAttribute("continent", mapQuiz.getCountry().getContinent());

    }
    public static boolean answer(HttpServletRequest request) throws SQLException, DaoException {
        LOGGER.trace("Getting the answer to FlagQuiz question.");
        Connection connection = ConnectionCreator.createConnection();
        MapQuizDao mapQuizDao = new MapQuizDao(connection);
        MapQuiz mapQuiz = mapQuizDao.read(returnNum(),connection);
        HttpSession session = request.getSession();
        int ID = (int) session.getAttribute("ID");
        String answer = mapQuiz.getCountry().getName();
        String var = request.getParameter("answer").replace(" ", "");
        Connection connection1 = ConnectionCreator.createConnection();
        UserDao userDao = new UserDao(connection1);
        User user = userDao.read(ID, connection1);
        connection = ConnectionCreator.createConnection();
        if (Objects.equals(answer, var)) {
            user.setFlagPassed(user.getFlagPassed()+1);
            userDao.update(ID, user, connection);
            return true;
        } else{
            user.setFlagFailed(user.getFlagFailed()+1);
            userDao.update(ID, user, connection);
            return false;
        }
    }
}