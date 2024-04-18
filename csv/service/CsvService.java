package reader.csv.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import reader.csv.entites.Cell;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvService {

    public List<Cell> parseCsvFile(String filePath) {
        List<Cell> cells = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            for (CSVRecord record : csvParser) {
                Cell cell = new Cell();
                cell.setOem(record.get("oem-string"));
                cell.setModel(record.get("Model"));
                cell.setLaunchYear(Integer.parseInt(record.get("launch_announced")));
                cell.setLaunchStatus(record.get("Launch_status"));
                cell.setBodyDimensions(record.get("Body_dimensions"));
                cell.setBodyWeight(Float.parseFloat(record.get("Body_weight").replaceAll(" g", "").trim()));
                cell.setBodySim(record.get("Body_sim"));
                cell.setDisplayType(record.get("Display_type"));
                cell.setDisplaySize(Float.parseFloat(record.get("Display_size").replaceAll("\"", "").trim()));
                cell.setDisplayResolution(record.get("Display_resolution"));
                cell.setFeaturesSensors(record.get("Features_sensors"));
                cell.setPlatformOs(record.get("Platform_os"));
                
                cells.add(cell);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cells;
    }
}

