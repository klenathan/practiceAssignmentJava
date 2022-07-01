import javax.lang.model.type.ArrayType;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Data {
    /**
     * countryArrayList is an array consist of Country object
     * A Country obj has 4 attributes: isoCode, Continent, Location
     * Country.countryData is a hashmap with key is the date and value is detailed data on the specific data
     * <p>{
     * date: [new_cases, new_deaths, people_vaxed, population]
     * }</p>
     * <p>hello</p>
     */
    ArrayList<Country> countryArrayList = new ArrayList<>();
    HashMap<String, ArrayList<DailyData>> countryDataArray = new HashMap<>();
    String path;

    public static void main(String[] args) {

        Data data = new Data("data/covid-data.csv");

//        System.out.println(data.countryDataArray.get("AFG").get(0).getDate());
//        ArrayList<DailyData> countryTest = data.countryDataArray.get("AFG");
//        System.out.println("Size: " + countryTest.size());
//        for (int i = 0; i < countryTest.size(); i++) {
//            System.out.printf("Data of AFG| date: %s | new_cases: %s | population: %s%n", countryTest.get(i).getDate(), countryTest.get(i).getNew_cases(), countryTest.get(i).getPopulation());
//        }
//        System.out.println(data.countryDataArray.get("AFG").get(0).getPopulation());
    }

    Data(String path) {
        this.path = path;
        createCountryObj();

    }

    private HashMap<String, String[]> formatData() {
        ArrayList<String[]> dataArray = this.readData();
        ArrayList<DailyData> tempDailyArray = new ArrayList<>();
        HashMap<String, String[]> countryInfo = new HashMap<>();
        for (String[] strings : dataArray) {
            if (!countryDataArray.containsKey(strings[0])) {
                tempDailyArray = new ArrayList<>();
                countryInfo.put(strings[0], new String[]{strings[1], strings[2]});
            }
            tempDailyArray.add(new DailyData(strings[3], strings[4], strings[5], strings[6], strings[7]));
            countryDataArray.put(strings[0], tempDailyArray);
        }
        return countryInfo;
    }

    private void createCountryObj() {
        HashMap<String, String[]> countryInfo = this.formatData();
        for (String s : countryDataArray.keySet()) {
            countryArrayList.add(new Country(s, countryInfo.get(s)[0], countryInfo.get(s)[1], countryDataArray.get(s)));
        }
    }

    private ArrayList<String[]> readData() {
        ArrayList<String[]> dataArray = new ArrayList<>();
        try {
            File inputFile = new File(this.path);
            Scanner reader = new Scanner(inputFile);
            String prevVaxNum = "";
            String prevCountry = "";
            String[] lineArray;
            System.out.println("start file reading: ");
//            ArrayList<DailyData> tempDailyArray;
            while (reader.hasNext()) {
                String line = reader.nextLine();
                lineArray = line.split(",", -1);

                // Fix blank or empty space to "0"
                // TODO: 01/07/2022 This thing changed the vaxed number to 0 :(
                // TODO: 01/07/2022 now the vax number is updated but first vax number doesnot reset to 0
                for (int i = 0; i < lineArray.length; i++) {
                    lineArray[i] = lineArray[i] != "" ? lineArray[i] : "0";
                }

                if (lineArray[6] == "0") {
                    lineArray[6] = prevVaxNum;
                } else {
                    prevVaxNum = lineArray[6];
                }

                if (!Objects.equals(lineArray[0], prevCountry)) {
                    System.out.println(prevCountry + " " + lineArray[0]);
                    lineArray[6] = "0";
                    prevVaxNum = "0";
                    prevCountry = lineArray[0];
                }
                dataArray.add(lineArray);
            }

        } catch (FileNotFoundException e) {
            System.out.println(this.path + "Does not exist");
        }
        dataArray.remove(0); // Remove the first csv line
        return dataArray;
    }
}