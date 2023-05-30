import WG.by.fpmibsu.dao.DaoException;
import WG.by.fpmibsu.dao.UserDao;
import WG.by.fpmibsu.entity.User;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class UserTests {
    UserDao userDao = new UserDao();

    public UserTests() throws DaoException {
    }

    public static class StaticDataProviderUser {
        @DataProvider(name = "userProviderArrayNormal")
        public static Object[][] createDaoNormal() {
            return new Object[][]{{0, "Admin"}, {1, "Aid"}, {3, "Neymar"}, {6, "1212"}};
        }
        @DataProvider(name = "userProviderArrayMinusException")
        public static Object[][] createDaoMinusException() {
            return new Object[][]{{-1}, {-1000}, {-12}, {-5}, {-6}, {-123}, {-324}};
        }
        @DataProvider(name = "userProviderArrayNotFoundException")
        public static Object[][] createDaoNotFoundException() {
            return new Object[][]{{1000}, {20000}, {2133}, {123}, {31234}, {32454}, {654}, {4324}};
        }

        @DataProvider(name = "userProviderArrayCount")
        public static Object[][] createDaoCount() {
            return new Object[][]{{7}};
        }
    }

    @Test(groups = {"user"}, dataProvider = "userProviderArrayNormal", dataProviderClass = StaticDataProviderUser.class)
    public void testUserDAONormal(int in, String expected) throws DaoException {
        User user = userDao.read(in);
        String name = user.getNickname().replace(" ", "");
        assertEquals(name, expected,"WA");
    }
    @Test(groups = {"user"}, dataProvider = "userProviderArrayCount", dataProviderClass = StaticDataProviderUser.class)
    public void testUserDAOCount(int expected) throws DaoException {
        int count = userDao.returnCount();
        assertEquals(count, expected,"WA");
    }
    @Test(groups = {"user"}, dataProvider = "userProviderArrayMinusException", dataProviderClass = StaticDataProviderUser.class,
            expectedExceptions = DaoException.class, expectedExceptionsMessageRegExp = "User not found with ID")
    public void testUserDAOMinusException(int in) throws DaoException {
        User user = userDao.read(in);
    }
    @Test(groups = {"user"}, dataProvider = "userProviderArrayNotFoundException", dataProviderClass = StaticDataProviderUser.class,
            expectedExceptions = DaoException.class, expectedExceptionsMessageRegExp = "User not found with ID")
    public void testUserDAONotFoundException(int in) throws DaoException {
        User user = userDao.read(in);
    }
}
