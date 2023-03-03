package beans;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class SheetData implements Serializable {
    List<String> columns;
    List<RowData> rows;

    public static final String COMMA=",";

    public SheetData(){
        this.columns = new LinkedList<>();
        this.rows = new LinkedList<>();
    }

    public void addColumn(String column){
        this.columns.add(column);
    }

    public void addRow(RowData rowData){
        this.rows.add(rowData);
    }

    public String printColumns(){
        StringBuilder columnNames = new StringBuilder(64);
        this.columns.forEach(columnName->{
            columnNames
                    .append(columnName)
                    .append(COMMA);
        });
        return columnNames.toString();
    }

    public String printRows(){
        StringBuilder rowDetails = new StringBuilder(64);
        this.rows.forEach(rowData -> {
            rowDetails.append(rowData.printRow())
                    .append("\n");
        });
        return rowDetails.toString();
    }


}