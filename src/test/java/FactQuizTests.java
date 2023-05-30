import WG.by.fpmibsu.dao.DaoException;
import WG.by.fpmibsu.dao.FactQuizDao;
import WG.by.fpmibsu.entity.FactQuiz;
import WG.by.fpmibsu.entity.FlagQuiz;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static org.testng.Assert.assertEquals;

public class FactQuizTests {
    FactQuizDao factQuizDao = new FactQuizDao();

    public FactQuizTests() throws DaoException {
    }

    public static class StaticDataProviderFactQuiz {
        @DataProvider(name = "factQuizProviderArrayNormal")
        public static Object[][] createDataNormal() {
            return new Object[][]{{0, 1}, {1, 3}};
        }
        @DataProvider(name = "factQuizProviderArrayNotNormal")
        public static Object[][] createDataNotNormal() {
            return new Object[][]{{3213}, {3213}, {123}, {3123}, {123123},
                    {3213}, {-1}, {-12}, {-31}, {-3213}, {-123}, {-142}, {-312}, {-3},
                    {133}, {-313}, {-43}, {-3124}, {-76}, {-456}, {-7}, {-5}};
        }
        @DataProvider(name = "factQuizProviderArrayCount")
        public static Object[][] createDaoCount() {
            return new Object[][]{{2}};
        }
    }
    @Test(groups = {"fact-quiz"}, dataProvider = "factQuizProviderArrayNormal", dataProviderClass = FactQuizTests.StaticDataProviderFactQuiz.class)
    public void testMapQuizDAONormal(int in, int expected) throws DaoException, SQLException {
        FactQuiz factQuiz = factQuizDao.read(in);
        int id = factQuiz.getAnswer();
        assertEquals(id, expected,"WA");
    }
    @Test(groups = {"fact-quiz"}, dataProvider = "factQuizProviderArrayNotNormal", dataProviderClass = FactQuizTests.StaticDataProviderFactQuiz.class)
    public void testCountryDAONotFoundException(int in) throws DaoException {
        FactQuiz factQuiz = factQuizDao.read(in);
    }
    @Test(groups = {"fact-quiz"}, dataProvider = "factQuizProviderArrayCount", dataProviderClass = FactQuizTests.StaticDataProviderFactQuiz.class)
    public void testCountryDAOCount(int expected) throws DaoException {
        int count = factQuizDao.returnCount();
        assertEquals(count, expected,"WA");
    }
}
