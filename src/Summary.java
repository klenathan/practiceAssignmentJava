import java.util.ArrayList;
import java.util.Objects;

public class Summary {

    private ArrayList<Country> dataArray;
    private ArrayList<ArrayList<DailyData>> groupingResult = new ArrayList<>();


    Summary() {
        Data data = new Data("data/covid-data.csv");
        dataArray = data.countryArrayList;
    }
    public ArrayList<ArrayList<DailyData>> groupDataByGroupNum(String iso_code, int groupNo){
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
        this.groupingResult = dividedData;
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
        this.groupingResult = dividedData;
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
    public ArrayList<Group> getDifferenceMetric() {
        ArrayList<Group> resultArray= new ArrayList<>();
        ArrayList<ArrayList<DailyData>> groupedData = this.groupingResult;

        int vaxDiff = 0, population = 0, prevVax = 0, currentVax = 0;

        // Each Group
        for (int i = 0; i < groupedData.size(); i++) {
            int cases = 0;
            int deaths = 0;

            String firstDay = groupedData.get(i).get(0).getDate();
            String lastDay = groupedData.get(i).get(groupedData.get(i).size()-1).getDate();
            String dateRange = firstDay + "-" + lastDay;
            // Each day
            for (int x = 0; x < groupedData.get(i).size(); x++) {
                DailyData tempDaily = groupedData.get(i).get(x);
                cases += Integer.parseInt(tempDaily.getNew_cases());
                deaths += Integer.parseInt(tempDaily.getNew_death());
                vaxDiff = Integer.parseInt(tempDaily.getVaxed()) - prevVax;
                currentVax = Integer.parseInt(tempDaily.getVaxed());
                population = Integer.parseInt(tempDaily.getPopulation());
            }
            resultArray.add(new Group(dateRange, cases, deaths, vaxDiff, population));
            prevVax = currentVax;
        }
        return resultArray;
    }

    public ArrayList<Group> getTotalMetric(){
        ArrayList<Group> resultArray= new ArrayList<>();
        ArrayList<ArrayList<DailyData>> groupedData = this.groupingResult;

        int cases = 0, deaths = 0, vax= 0, population = 0, prevVax = 0;

        // Each Group
        for (int i = 0; i < groupedData.size(); i++) {
            String firstDay = groupedData.get(i).get(0).getDate();
            String lastDay = groupedData.get(i).get(groupedData.get(i).size()-1).getDate();
            String dateRange = firstDay + "-" + lastDay;
            // Each day
            for (int x = 0; x < groupedData.get(i).size(); x++) {
                DailyData tempDaily = groupedData.get(i).get(x);
                cases += Integer.parseInt(tempDaily.getNew_cases());
                deaths += Integer.parseInt(tempDaily.getNew_death());
                vax = Integer.parseInt(tempDaily.getVaxed());
            }
            resultArray.add(new Group(dateRange, cases, deaths, vax, population));
        }
        return resultArray;
    }
}


