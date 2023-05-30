import WG.by.fpmibsu.dao.DaoException;
import WG.by.fpmibsu.dao.UserDao;
import WG.by.fpmibsu.entity.User;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class test {
    UserDao userDao = new UserDao();

    public test() throws DaoException {
    }

    public static class StaticDataProvider {
        @DataProvider(name = "userProviderArrayNormal")
        public static Object[][] createDataNormal() {
            return new Object[][]{{0, "Admin"}, {1, "Aid"}, {3, "Neymar"}};
        }
        @DataProvider(name = "userProviderArrayMinusException")
        public static Object[][] createDataMinusException() {
            return new Object[][]{{-1}, {-1000}};
        }
        @DataProvider(name = "userProviderArrayNotFoundException")
        public static Object[][] createDataNotFoundException() {
            return new Object[][]{{1000}, {20000}};
        }
    }

    @Test(groups = {"user"}, dataProvider = "userProviderArrayNormal", dataProviderClass = StaticDataProvider.class)
    public void testUserDAONormal(int in, String expected) throws DaoException {
        User user = userDao.read(in);
        String name = user.getNickname().replace(" ", "");
        assertEquals(name, expected,"WA");
    }
    @Test(groups = {"user"}, dataProvider = "userProviderArrayMinusException", dataProviderClass = StaticDataProvider.class,
            expectedExceptions = DaoException.class, expectedExceptionsMessageRegExp = "User not found with ID")
    public void testUserDAOMinusException(int in) throws DaoException {
        User user = userDao.read(in);
    }
    @Test(groups = {"user"}, dataProvider = "userProviderArrayNotFoundException", dataProviderClass = StaticDataProvider.class,
            expectedExceptions = DaoException.class, expectedExceptionsMessageRegExp = "User not found with ID")
    public void testUserDAONotFoundException(int in) throws DaoException {
        User user = userDao.read(in);
    }
}
