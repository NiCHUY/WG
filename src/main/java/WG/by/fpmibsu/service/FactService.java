package WG.by.fpmibsu.service;

import WG.by.fpmibsu.dao.*;
import WG.by.fpmibsu.entity.FactQuiz;
import WG.by.fpmibsu.entity.FlagQuiz;
import WG.by.fpmibsu.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;

public class FactService {
    private static final Logger LOGGER = LogManager.getLogger(FlagService.class);
    public static int returnNum() throws SQLException, DaoException {
        Connection connection = ConnectionCreator.createConnection();
        FactQuizDao factQuizDao = new FactQuizDao(connection);
        int count = factQuizDao.returnCount(connection);
        return (int) (Math.random() * count);
    }
    public static void init(HttpServletRequest req) throws SQLException, DaoException {
        LOGGER.trace("Initialising FactQuiz question.");
        Connection connection = ConnectionCreator.createConnection();
        FactQuizDao factQuizDao = new FactQuizDao(connection);
        FactQuiz factQuiz = factQuizDao.read(returnNum(),connection);
        req.setAttribute("fact",(factQuiz.getFact()));
        req.setAttribute("o1", factQuiz.getFirstVariant().getName());
        req.setAttribute("o2", factQuiz.getSecondVariant().getName());
        req.setAttribute("o3", factQuiz.getThirdVariant().getName());
        req.setAttribute("o4", factQuiz.getFourthVariant().getName());

    }
    public static boolean answer(HttpServletRequest request) throws SQLException, DaoException {
        LOGGER.trace("Getting the answer to FactQuiz question.");
        Connection connection = ConnectionCreator.createConnection();
        FactQuizDao factQuizDao = new FactQuizDao(connection);
        FactQuiz factQuiz = factQuizDao.read(0,connection);
        HttpSession session = request.getSession();
        int ID = (int) session.getAttribute("ID");
        int answer = factQuiz.getAnswer();
        int var = Integer.parseInt(request.getParameter("option1"));
        Connection connection1 = ConnectionCreator.createConnection();
        UserDao userDao = new UserDao(connection1);
        User user = userDao.read(ID, connection1);
        connection1 = ConnectionCreator.createConnection();
        if (answer == var) {
            user.setFactQuizPassed(user.getFactQuizFailed()+1);
            userDao.update(ID, user, connection1);
            return true;
        } else{
            user.setFactQuizFailed(user.getFactQuizFailed()+1);
            userDao.update(ID, user, connection1);
            return false;
        }
    }
}

