import java.lang.reflect.Array;
import java.util.ArrayList;

public class Display {
    static enum method {total, difference};
    Display(){

    }
    public void route(){

    }

    public void divideTable(ArrayList<DailyData> data){
        for (int i = 0; i < data.size(); i++) {

        }
    }
    public void drawTable(ArrayList<ArrayList<DailyData>> dividedGroup,
                          Summary summary,
                          method method){
        int width = 25;
        for (int i = 0; i < dividedGroup.size(); i++) {
            String firstDay = dividedGroup.get(i).get(0).getDate();
            String lastDay = dividedGroup.get(i).get(dividedGroup.get(i).size()-1).getDate();
            System.out.printf(firstDay + " " + lastDay
                    + " ".repeat(width - firstDay.length() - lastDay.length() - 1)
                    + " | ");

            if(method == Display.method.difference) {
                System.out.println(summary.getDifferenceMetric(dividedGroup.get(i)).get(1) + " units");
            } else {
                System.out.println(summary.getTotalMetric(dividedGroup.get(i), dividedGroup).get(1));
            }

        }
    }
}
