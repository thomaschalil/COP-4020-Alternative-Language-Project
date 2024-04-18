package reader.csv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reader.csv.entites.Cell;
import reader.csv.repository.CellRepository;
import reader.csv.service.CsvService;

@RestController
@RequestMapping("/api/cells")
public class CsvController {

    @Autowired
    private CellRepository cellRepository;

    @Autowired
    private CsvService csvService;

    @PostMapping("/upload")
    public List<Cell> uploadFile(@RequestParam("file") String filePath) {
        List<Cell> cells = csvService.parseCsvFile(filePath);
        return cellRepository.saveAll(cells);
    }

    @GetMapping
    public List<Cell> getAllCells() {
        return cellRepository.findAll();
    }
}