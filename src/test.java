import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        Display program = new Display();
        Summary summary = new Summary();

        ArrayList<ArrayList<DailyData>> dividedGroup = summary.groupDataByGroupNo("VNM", 15);

//        for (int i = 0; i < dividedGroup.size(); i++) {
//            String firstDay = dividedGroup.get(i).get(0).getDate();
//            String lastDay = dividedGroup.get(i).get(dividedGroup.get(i).size()-1).getDate();
//            System.out.println(firstDay + " " + lastDay);
//            System.out.println(summary.getDifferenceMetric(dividedGroup.get(i)).get(2));
//        }

        program.drawTable(dividedGroup,
                summary,
                Display.method.total
                );

//        program.divideTable();
    }
}
