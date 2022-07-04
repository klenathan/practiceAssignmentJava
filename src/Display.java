import java.util.ArrayList;
import java.util.Scanner;

public class Display {

    private Integer colWidth;
    Summary summary = new Summary();
    ArrayList<ArrayList<DailyData>> groupingResult = summary.groupDataByGroupNum("LAO", 15);
    ArrayList<Group> groupArray2 = summary.getTotalMetric();

    Display(int colWidth){
        this.colWidth = colWidth;


        route();

    }
    public void route(){
        while(true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("=".repeat(colWidth * 2 + 3));
            System.out.printf("Hello? Please choose: ");
            int userInput = scan.nextInt();
            if (userInput == 1) {
                drawTable(this.groupArray2, "case");
            } else if (userInput == 0) {
                break;
            }

        }
    }
    public void drawTable(ArrayList<Group> dividedGroup, String valueMetric){

        System.out.println("|Date" + " ".repeat(colWidth-4) + "|" +valueMetric+  " ".repeat(colWidth-valueMetric.length()) + "|");
        System.out.println("|" + "=".repeat(colWidth) + "|" + "=".repeat(colWidth) + "|");

        for (int i = 0; i < dividedGroup.size(); i++) {
            String date = dividedGroup.get(i).getDate();
            int value = 0;
            if (valueMetric == "vax"){
                value = dividedGroup.get(i).vax;
            } else if (valueMetric == "case") {
                value = dividedGroup.get(i).cases;
            } else if (valueMetric == "death") {
                value = dividedGroup.get(i).deaths;
            } else {
                System.out.println("Wrong metric");
            }

            System.out.println("|" + date + " ".repeat(colWidth-date.length())
                    + "|" + value + " ".repeat(colWidth-String.valueOf(value).length()) + "|");
        }
    }
}
