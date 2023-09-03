package cli;

import enums.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import processors.Processor;
import xlsx.Reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;


public class Main {
    static Reader reader;
    static Map<Integer,String> fileExtensions = new LinkedHashMap<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) throws IOException {
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

      LOGGER.info(Operation.INSERT.name());
      LOGGER.info("""
      Choose your file extension :
      1: xlsx 
      2: xls
      """);
      int option =Integer.parseInt(bufferedReader.readLine());
      LOGGER.info("Enter File Name");


      //Get File Name
      String fileName = bufferedReader.readLine();
      LOGGER.info("""
              Select Operation :
              1: Insert
              2: Update
              3: Delete
              """);
      int operationId = Integer.parseInt(bufferedReader.readLine());
      Operation selectedOperation = Operation.getOperation(operationId);
      reader = new Reader(fileName.split("\\.")[0]+fileExtensions.get(option));

      LOGGER.info(String.format("Number of sheets : %s",reader.getWorkbook().getNumberOfSheets()));


      //Process
        Processor processor = new Processor();
        processor.init(reader.getWorkbook());
        processor.setSelectedOperation(selectedOperation);
        processor.process();

    }

    static {
        fileExtensions.put(1,".xlsx");
        fileExtensions.put(2,".xls");
    }
}
