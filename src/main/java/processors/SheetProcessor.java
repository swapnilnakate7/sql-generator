package processors;

import beans.SheetData;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class SheetProcessor implements Serializable {
    private final List<SheetData> sheetData;
    public SheetProcessor(List<SheetData> sheetData){
        this.sheetData = sheetData;
    }

    public void process(){
        this.sheetData.parallelStream().forEach(sheet->{
            System.out.println(sheet.printColumns());
            System.out.println(sheet.printRows());
        });
    }
}
