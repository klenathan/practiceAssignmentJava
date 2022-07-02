import java.util.ArrayList;
import java.util.Objects;

public class Summary {

    ArrayList<Country> dataArray;

    public static void main(String[] args) {

    }
    Summary() {
        Data data = new Data("data/covid-data.csv");
        dataArray = data.countryArrayList;
    }
    public ArrayList<ArrayList<DailyData>> groupDataByGroupNo(String iso_code, int groupNo){
        Country country = null;
        ArrayList<ArrayList<DailyData>> dividedData = new ArrayList<>();

        for(Country x:dataArray) {

            if(Objects.equals(x.getIsoCode(), iso_code)) {
                country = x;
                dividedData = formatByGroupNum(country, groupNo);
                break;
            }
        }
        if (country == null){
            System.out.println("Country not found");
        }
        return dividedData;
    }

    private ArrayList<ArrayList<DailyData>> formatByGroupNum(Country x, int groupNo) {
        ArrayList<ArrayList<DailyData>> dividedData = new ArrayList<>();
        int dataSize = x.getCountryData().size();
        int eachGroup = dataSize / groupNo;
        int remain = dataSize % groupNo;
        int countThroughCountryDataIndex = 0;
        int remainEach = eachGroup + 1;

        System.out.printf("%s divided into %d group has %d or %d days each\n", x.getIsoCode(), groupNo, eachGroup, remainEach);

        for (int count=0; count<remain; count++) {
            ArrayList<DailyData> tempList = new ArrayList<>();
            for(int y = 0; y < remainEach; y++){
                DailyData tempData = x.getCountryData().get(countThroughCountryDataIndex);
                tempList.add(tempData);
                countThroughCountryDataIndex++;
            }
            dividedData.add(tempList);
        }
        for (int z = 0; z < groupNo - remain; z++) {
            ArrayList<DailyData> tempList = new ArrayList<>();
            for(int y = 0; y < eachGroup; y++){
                if (countThroughCountryDataIndex == dataSize) {break;}
                DailyData tempData = x.getCountryData().get(countThroughCountryDataIndex);
                tempList.add(tempData);
                countThroughCountryDataIndex++;
            }
            dividedData.add(tempList);
        }
        return dividedData;
    }

    // TODO: 01/07/2022 Fix this
    public ArrayList<ArrayList<DailyData>> groupDataByGroupSize(String iso_code, int groupSize){
        Country country;
        String dataIsoCode;
        ArrayList<ArrayList<DailyData>> dividedData = new ArrayList<>();

//        System.out.println(dataArray.size());
        for(Country x:dataArray) {
//            Objects.equals(x.getIsoCode(), iso_code)
            dataIsoCode = x.getIsoCode();
            if(Objects.equals(dataIsoCode, iso_code)) {
                country = x;
                dividedData = formatByGroupSize(country, groupSize);
                if (dividedData == null) {
                    System.out.println("Different iso code or group size");
                }
                break;
            }
        }
//        if (country == null){
//            System.out.println("Country not found");
//        }
        return dividedData;
    }
    private ArrayList<ArrayList<DailyData>> formatByGroupSize (Country country, int groupSize){
        ArrayList<ArrayList<DailyData>> dividedData = new ArrayList<>();
        int groupingIndex = 0;
        if (country.getCountryData().size() % groupSize != 0){
            dividedData = null;
            System.out.println(String.format("Cannot divide into group of %d days", groupSize));
        } else {
            for (int x = 0; x < country.getCountryData().size() / groupSize; x++) {
                ArrayList<DailyData> tempDailyData = new ArrayList<>();
                for (int i = 0; i < groupSize; i++) {
                    DailyData dailyData = country.getCountryData().get(groupingIndex);
                    tempDailyData.add(dailyData);
                    groupingIndex++;
                }
                dividedData.add(tempDailyData);
            }
        }
        return dividedData;
    }

    // TODO: 01/07/2022 Working on this 
    public ArrayList<Integer> getDifferenceMetric(ArrayList<DailyData> range) {
        int deaths = 0, cases = 0, vaxed = 0, population = 0;
        double rate = 0;
        ArrayList<Integer> differenceData = new ArrayList<>();

        for (DailyData dailyData : range) {
            deaths += Integer.parseInt(dailyData.getNew_death());
            cases += Integer.parseInt(dailyData.getNew_cases());
            population = Integer.parseInt(dailyData.getPopulation());
        }

        vaxed = Integer.parseInt(range.get(range.size()-1).getVaxed()) - Integer.parseInt(range.get(0).getVaxed());
        differenceData.add(deaths);
        differenceData.add(cases);
        differenceData.add(vaxed);
        differenceData.add(population);

        return differenceData;
    }

    public ArrayList<Integer> getTotalMetric(ArrayList<DailyData> range, ArrayList<ArrayList<DailyData>> countryData) {
        int deaths = 0;
        int cases = 0;
        int vaxed = 0;
        int population = 0;
        double rate = 0;
        ArrayList<Integer> toDateData = new ArrayList<>();

        for (int x = 0; x < countryData.size(); x++) {
            for (DailyData dailyData : range) {

                deaths += Integer.parseInt(dailyData.getNew_death());
                cases += Integer.parseInt(dailyData.getNew_cases());
                vaxed = Integer.parseInt(dailyData.getVaxed());
                population = Integer.parseInt(dailyData.getPopulation());
                rate = (vaxed * 1.0 / population) * 100;
            }
        }
        toDateData.add(deaths);
        toDateData.add(cases);
        toDateData.add(vaxed);
        toDateData.add(population);

//        System.out.printf("Deaths: %d | Cases: %d | Vaccinated: %d %.2f%%| Population: %d%n", deaths, cases, vaxed, rate, population);
        return toDateData;
    }
}


