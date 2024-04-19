package reader.csv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import reader.csv.entites.Cell;
import reader.csv.repository.CellRepository;
import reader.csv.service.CsvService;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
public class CsvController {

    @Autowired
    private CellRepository cellRepository;

    @Autowired
    private CsvService csvService;

    @PostConstruct
    public void loadCsvData() {
        List<Cell> cells = csvService.parseCsvFile("static/files/cells28529.csv"); // Specify the local path
        cellRepository.saveAll(cells);
    }

    @GetMapping("/api/cells")
    @ResponseBody
    public List<Cell> getAllCells() {
        return cellRepository.findAll();
    }
    
    @GetMapping("/showData")
    public String show(Model model) {
    	model.addAttribute("data", cellRepository.findAll());
    	return "show.html";
    }
}
