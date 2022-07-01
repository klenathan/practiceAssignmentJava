import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class Country {
    private String isoCode, continent, location;
    private ArrayList<DailyData> countryData;

    public Country(String isoCode, String continent, String location, ArrayList<DailyData> countryData) {
        this.isoCode = isoCode;
        this.continent = continent;
        this.location = location;
        this.countryData = countryData;
    }

    public Country(String isoCode, String continent, String location) {
        this.isoCode = isoCode;
        this.continent = continent;
        this.location = location;
    }

    public Country() {
    }

    public ArrayList<DailyData> getCountryData() {
        return countryData;
    }

    public void setCountryData(ArrayList<DailyData> countryData) {
        this.countryData = countryData;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

