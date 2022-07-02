package Display;

import java.util.ArrayList;

public class Table {
    int column, row, columnWidth;
    ArrayList<ArrayList<String>> tableArray = new ArrayList<>();

    public static void main(String[] args) {
        Table table = new Table(20);
        ArrayList<String> testArray = new ArrayList<>();
        testArray.add("abc");
        testArray.add("abc2");
        testArray.add("ab2c");
        ArrayList<String> tableArray = table.buildColumn(testArray, "test column");

        table.mergeColumn();

//        for (int i = 0; i < tableArray.size(); i++) {
//            System.out.println(tableArray.get(i));
//        }
    }

    public Table(int columnWidth){
        this.columnWidth = columnWidth;
    }

    private void mergeColumn(){
        ArrayList<String> testArray = new ArrayList<>();
        testArray.add("abc");
        testArray.add("abc2");
        testArray.add("ab2c");

        ArrayList<String> tableArray1 = buildColumn(testArray, "test column");

        ArrayList<String> tableArray2 = buildColumn(testArray, "Col 2");

        tableArray.add(tableArray1);
        tableArray.add(tableArray2);

        for (int i = 0; i < tableArray1.size(); i++) {
            String modified = tableArray1.get(i).replace("\n", "") + tableArray2.get(i);

            System.out.println(modified);
            tableArray1.set(i, modified);
        }

        System.out.println(tableArray2);
    }

    private ArrayList<String> buildColumn(ArrayList<String> content, String columnName){
        ArrayList<String> columnArray = new ArrayList<>();
        String columnHeader = columnName + " ".repeat(columnWidth-columnName.length()) + "|\n" + "-".repeat(columnWidth) + "|";
        columnArray.add(columnHeader);
        for (int i = 0; i < content.size(); i++) {
            String value = content.get(i);
            String currentRow = value + " ".repeat(columnWidth-value.length()) + "|";
            columnArray.add(currentRow);
        }
        return columnArray;
    }

}
