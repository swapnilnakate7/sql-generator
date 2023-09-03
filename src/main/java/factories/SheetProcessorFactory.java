package factories;

import beans.SheetData;
import enums.Operation;
import processors.DeleteScriptProcessor;
import processors.InsertScriptProcessor;
import processors.SheetProcessor;
import processors.UpdateScriptProcessor;

import java.util.List;

public class SheetProcessorFactory {

    private SheetProcessorFactory(){
        super();
    }

    public static SheetProcessor getProcessor(Operation operation, List<SheetData> sheetDataList){
        if(operation == Operation.INSERT){
            return new InsertScriptProcessor(sheetDataList);
        }
        if(operation == Operation.DELETE){
            return new DeleteScriptProcessor(sheetDataList);
        }
        if(operation == Operation.UPDATE){
            return new UpdateScriptProcessor(sheetDataList);
        }
        return null;
    }
}
