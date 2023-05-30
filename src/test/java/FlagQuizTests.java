import WG.by.fpmibsu.dao.DaoException;
import WG.by.fpmibsu.dao.FactQuizDao;
import WG.by.fpmibsu.dao.FlagQuizDao;
import WG.by.fpmibsu.entity.FactQuiz;
import WG.by.fpmibsu.entity.FlagQuiz;
import WG.by.fpmibsu.entity.MapQuiz;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static org.testng.Assert.assertEquals;

public class FlagQuizTests {
    FlagQuizDao flagQuizDao = new FlagQuizDao();

    public FlagQuizTests() throws DaoException {}
    public static class StaticDataProviderFlagQuiz {
        @DataProvider(name = "flagQuizProviderArrayNormal")
        public static Object[][] createDataNormal() {
            return new Object[][]{{0, 0}, {1, 1}, {2,3}, {3, 2}, {4, 0}, {5, 2}};
        }
        @DataProvider(name = "flagQuizProviderArrayNotNormal")
        public static Object[][] createDataNotNormal() {
            return new Object[][]{{3213}, {3213}, {123}, {3123}, {123123}, {123123},
                    {3213}, {-1}, {-12}, {-31}, {-3213}, {-123}, {-142}, {-312}, {-3},
                    {133}, {-313}, {-43}, {-3124}, {-76}, {-456}, {-7}, {-5}};
        }
        @DataProvider(name = "flagQuizProviderArrayCount")
        public static Object[][] createDaoCount() {
            return new Object[][]{{6}};
        }
    }
    @Test(groups = {"flag-quiz"}, dataProvider = "flagQuizProviderArrayNormal", dataProviderClass = FlagQuizTests.StaticDataProviderFlagQuiz.class)
    public void testMapQuizDAONormal(int in, int expected) throws DaoException, SQLException {
        FlagQuiz flagQuiz = flagQuizDao.read(in);
        int id = flagQuiz.getAnswerCountry().getID();
        assertEquals(id, expected,"WA");
    }
    @Test(groups = {"flag-quiz"}, dataProvider = "flagQuizProviderArrayNotNormal", dataProviderClass = FlagQuizTests.StaticDataProviderFlagQuiz.class)
    public void testCountryDAONotFoundException(int in) throws DaoException {
        FlagQuiz flagQuiz = flagQuizDao.read(in);
    }
    @Test(groups = {"flag-quiz"}, dataProvider = "flagQuizProviderArrayCount", dataProviderClass = FlagQuizTests.StaticDataProviderFlagQuiz.class)
    public void testCountryDAOCount(int expected) throws DaoException {
        int count = flagQuizDao.returnCount();
        assertEquals(count, expected,"WA");
    }
}
