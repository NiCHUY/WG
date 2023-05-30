import WG.by.fpmibsu.dao.DaoException;
import WG.by.fpmibsu.dao.MapQuizDao;
import WG.by.fpmibsu.entity.Country;
import WG.by.fpmibsu.entity.MapQuiz;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static org.testng.Assert.assertEquals;

public class MapQuizTests {
    MapQuizDao mapQuizDao = new MapQuizDao();

    public MapQuizTests() throws DaoException {
    }
    public static class StaticDataProviderMapQuiz {
        @DataProvider(name = "mapQuizProviderArrayNormal")
        public static Object[][] createDataNormal() {
            return new Object[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}};
        }
        @DataProvider(name = "mapQuizProviderArrayNotNormal")
        public static Object[][] createDataNotNormal() {
            return new Object[][]{{3213}, {3213}, {123}, {3123}, {123123}, {123123},
                    {3213}, {-1}, {-12}, {-31}, {-3213}, {-123}, {-142}, {-312}, {-3}};
        }
        @DataProvider(name = "mapQuizProviderArrayCount")
        public static Object[][] createDaoCount() {
            return new Object[][]{{4}};
        }
    }
    @Test(groups = {"map-quiz"}, dataProvider = "mapQuizProviderArrayNormal", dataProviderClass = MapQuizTests.StaticDataProviderMapQuiz.class)
    public void testMapQuizDAONormal(int in, int expected) throws DaoException, SQLException {
        MapQuiz mapQuiz = mapQuizDao.read(in);
        int id = mapQuiz.getCountry().getID();
        assertEquals(id, expected,"WA");
    }
    @Test(groups = {"map-quiz"}, dataProvider = "mapQuizProviderArrayNotNormal", dataProviderClass = MapQuizTests.StaticDataProviderMapQuiz.class)
    public void testCountryDAONotFoundException(int in) throws DaoException {
        MapQuiz mapQuiz = mapQuizDao.read(in);
    }
    @Test(groups = {"map-quiz"}, dataProvider = "mapQuizProviderArrayCount", dataProviderClass = MapQuizTests.StaticDataProviderMapQuiz.class)
    public void testCountryDAOCount(int expected) throws DaoException {
        int count = mapQuizDao.returnCount();
        assertEquals(count, expected,"WA");
    }
}
