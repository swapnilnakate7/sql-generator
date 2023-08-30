package beans;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class RowData implements Serializable {
    private final List<String> cellData;

    public RowData(){
        cellData = new LinkedList<>();
    }

    public void addCellValue(String cellValue){
        cellData.add(cellValue);
    }

    public String printRow(){
        return String.join(SheetData.COMMA,this.cellData);
    }
}
