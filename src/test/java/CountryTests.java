import WG.by.fpmibsu.dao.CountryDao;
import WG.by.fpmibsu.dao.DaoException;
import WG.by.fpmibsu.entity.Country;
import WG.by.fpmibsu.entity.User;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static org.testng.Assert.assertEquals;

public class CountryTests {
    CountryDao countryDao = new CountryDao();

    public CountryTests() throws DaoException {
    }
    public static class StaticDataProviderCountry {
        @DataProvider(name = "countryProviderArrayNormal")
        public static Object[][] createDataNormal() {
            return new Object[][]{{0, "Austria"}, {1, "Andorra"}, {2, "Albania"}, {3, "Belarus"}};
        }
        @DataProvider(name = "countryProviderArrayMinusException")
        public static Object[][] createDaoMinusException() {
            return new Object[][]{{-1}, {-1000}, {-12}, {-5}, {-6}, {-123}, {-324}};
        }
        @DataProvider(name = "countryProviderArrayNotFoundException")
        public static Object[][] createDaoNotFoundException() {
            return new Object[][]{{1000}, {20000}, {2133}, {123}, {31234}, {32454}, {654}, {4324}};
        }
        @DataProvider(name = "countryProviderArrayCount")
        public static Object[][] createDaoCount() {
            return new Object[][]{{4}};
        }
    }

    @Test(groups = {"country"}, dataProvider = "countryProviderArrayNormal", dataProviderClass = CountryTests.StaticDataProviderCountry.class)
    public void testCountryDAONormal(int in, String expected) throws DaoException, SQLException {
        Country country = countryDao.read(in);
        String name = country.getName().replace(" ", "");
        assertEquals(name, expected,"WA");
    }
    @Test(groups = {"country"}, dataProvider = "countryProviderArrayMinusException", dataProviderClass = CountryTests.StaticDataProviderCountry.class)
    public void testCountryDAONotFoundException(int in) throws DaoException, SQLException {
        Country country = countryDao.read(in);
    }
    @Test(groups = {"country"}, dataProvider = "countryProviderArrayNotFoundException", dataProviderClass = CountryTests.StaticDataProviderCountry.class)
    public void testCountryDAOMinusException(int in) throws DaoException, SQLException {
        Country country = countryDao.read(in);
    }
    @Test(groups = {"country"}, dataProvider = "countryProviderArrayCount", dataProviderClass = CountryTests.StaticDataProviderCountry.class)
    public void testCountryDAOCount(int expected) throws DaoException {
        int count = countryDao.returnCount();
        assertEquals(count, expected,"WA");
    }
}
