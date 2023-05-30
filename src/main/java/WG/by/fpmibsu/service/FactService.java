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
        FactQuizDao factQuizDao = new FactQuizDao();
        int count = factQuizDao.returnCount();
        return (int) (Math.random() * count);
    }
    public static void init(HttpServletRequest req) throws SQLException, DaoException {
        LOGGER.trace("Initialising FactQuiz question.");
        FactQuizDao factQuizDao = new FactQuizDao();
        FactQuiz factQuiz = factQuizDao.read(returnNum());
        req.setAttribute("fact",(factQuiz.getFact()));
        req.setAttribute("o1", factQuiz.getFirstVariant().getName());
        req.setAttribute("o2", factQuiz.getSecondVariant().getName());
        req.setAttribute("o3", factQuiz.getThirdVariant().getName());
        req.setAttribute("o4", factQuiz.getFourthVariant().getName());

    }
    public static boolean answer(HttpServletRequest request) throws SQLException, DaoException {
        LOGGER.trace("Getting the answer to FactQuiz question.");
        FactQuizDao factQuizDao = new FactQuizDao();
        FactQuiz factQuiz = factQuizDao.read(0);
        HttpSession session = request.getSession();
        int ID = (int) session.getAttribute("ID");
        int answer = factQuiz.getAnswer();
        int var = Integer.parseInt(request.getParameter("option1"));
        UserDao userDao = new UserDao();
        User user = userDao.read(ID);
        if (answer == var) {
            user.setFactQuizPassed(user.getFactQuizFailed()+1);
            userDao.update(ID, user);
            return true;
        } else{
            user.setFactQuizFailed(user.getFactQuizFailed()+1);
            userDao.update(ID, user);
            return false;
        }
    }
}

