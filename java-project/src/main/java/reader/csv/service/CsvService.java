package reader.csv.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import reader.csv.entites.Cell;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvService {
	@Autowired
	private ResourceLoader resourceLoader;

	public List<Cell> parseCsvFile(String filePath) {
		List<Cell> cells = new ArrayList<>();
		Resource resource = resourceLoader.getResource("classpath:" + filePath);
		try (Reader reader = Files.newBufferedReader(Paths.get(resource.getURI()));
				CSVParser csvParser = new CSVParser(reader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

			for (CSVRecord record : csvParser) {
				Cell cell = new Cell();
				cell.setOem(safeString(record.get("oem")));
				cell.setModel(safeString(record.get("model")));
				cell.setLaunchYear(parseYear(record.get("launch_announced")));
				cell.setLaunchStatus(safeString(record.get("Launch_status")));
				cell.setBodyDimensions(safeString(record.get("Body_dimensions")));
				cell.setBodyWeight(parseWeight(record.get("Body_weight")));
				cell.setBodySim(safeString(record.get("Body_sim")));
				cell.setDisplayType(safeString(record.get("Display_type")));
				cell.setDisplaySize(parseDisplaySize(record.get("display_size")));
				cell.setDisplayResolution(safeString(record.get("Display_resolution")));
				cell.setFeaturesSensors(safeString(record.get("Features_sensors")));
				cell.setPlatformOs(safeString(record.get("Platform_os")));

				cells.add(cell);
			}
		} catch (Exception e) {
			e.printStackTrace(); // Consider proper exception handling or logging
		}
		return cells;
	}

	private String safeString(String value) {
		return value == null || value.trim().isEmpty() || value.equals("-") ? null : value.trim();
	}

	private Integer parseYear(String value) {
		try {
			if (value.length() >= 4) {
				value = value.substring(0, 4);
			}
			return value == null || value.isEmpty() || value.equals("-") ? null : Integer.parseInt(value.trim());
		} catch (NumberFormatException e) {
			return null;
		}
	}

	private Float parseWeight(String value) {
		if (value != null && !value.isEmpty() && !value.equals("-")) {
			value = value.replaceAll("[^\\d.]", ""); // Remove all non-digits and non-dot characters
			try {
				return Float.parseFloat(value);
			} catch (NumberFormatException e) {
				return null;
			}
		}
		return null;
	}

	private Float parseDisplaySize(String value) {
		if (value != null && !value.isEmpty() && !value.equals("-")) {
			value = value.substring(0, value.indexOf(" ")); // Remove inches symbol and trim
			try {
				float valueFloat = Float.parseFloat(value);
				return valueFloat;
			} catch (NumberFormatException e) {
				return null;
			}
		}
		return null;
	}
}
