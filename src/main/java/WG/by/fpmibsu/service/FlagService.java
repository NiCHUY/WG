package WG.by.fpmibsu.service;

import WG.by.fpmibsu.dao.*;
import WG.by.fpmibsu.entity.FlagQuiz;
import WG.by.fpmibsu.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FlagService {
    private static final Logger LOGGER = LogManager.getLogger(FlagService.class);
    public static int returnNum() throws SQLException, DaoException {
        FlagQuizDao flagQuizDao = new FlagQuizDao();
        int count = flagQuizDao.returnCount();
        return (int) (Math.random() * count);
    }
    public static void init(HttpServletRequest req) throws SQLException, DaoException {
        LOGGER.trace("Initialising FlagQuiz question.");
        FlagQuizDao flagQuizDao = new FlagQuizDao();
        FlagQuiz flagQuiz = flagQuizDao.read(returnNum());
        req.setAttribute("img","image/flags/" + (flagQuiz.getAnswerCountry().getFlag().replace(" ", ""))  + ".png");
        req.setAttribute("o1", flagQuiz.getFirstVariant().getName());
        req.setAttribute("o2", flagQuiz.getSecondVariant().getName());
        req.setAttribute("o3", flagQuiz.getThirdVariant().getName());
        req.setAttribute("o4", flagQuiz.getFourthVariant().getName());

    }
    public static boolean answer(HttpServletRequest request) throws SQLException, DaoException {
        LOGGER.trace("Getting the answer to FlagQuiz question.");
        FlagQuizDao flagQuizDao = new FlagQuizDao();
        FlagQuiz flagQuiz = flagQuizDao.read(0);
        HttpSession session = request.getSession();
        int ID = (int) session.getAttribute("ID");
        int answer = flagQuiz.getAnswer();
        int var = Integer.parseInt(request.getParameter("option"));
        UserDao userDao = new UserDao();
        User user = userDao.read(ID);
        if (answer == var) {
            user.setFlagPassed(user.getFlagPassed()+1);
            userDao.update(ID, user);
            return true;
        } else{
            user.setFlagFailed(user.getFlagFailed()+1);
            userDao.update(ID, user);
            return false;
        }
    }
}
