package beans;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class RowData implements Serializable {
    private List<String> cellData;

    public RowData(){
        cellData = new LinkedList<>();
    }

    public void addCellValue(String cellValue){
        cellData.add(cellValue);
    }

    public String printRow(){
        StringBuilder rowDetails = new StringBuilder(64);
        this.cellData.forEach(cellValue->{
            rowDetails
                    .append(cellValue)
                    .append(SheetData.COMMA);
        });
        return rowDetails.toString();
    }
}
