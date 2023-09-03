package beans;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class SheetData implements Serializable {
    private List<String> columns;
    private List<RowData> rows;

    private String sheetName;

    public static final String COMMA=",";

    public SheetData(String sheetName){
        this.columns = new LinkedList<>();
        this.rows = new LinkedList<>();
        this.sheetName = sheetName;
    }

    public void addColumn(String column){
        this.columns.add(column);
    }

    public void addRow(RowData rowData){
        this.rows.add(rowData);
    }

    public String printColumns(){
        return String.join(COMMA,this.columns);
    }

    public String printRows(){
        List<String> rowNames=this.rows.stream().map(RowData::printRow).toList();
        return String.join("|",rowNames);
    }

    public String getSheetName(){
        return this.sheetName;
    }

    public List<RowData> getRows() {
        return rows;
    }

    public List<String> getColumns() {
        return columns;
    }
}
