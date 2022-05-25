package ru.seurus.idioma.controller.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.seurus.idioma.entity.stock.StockHardware;
import ru.seurus.idioma.exception.ResourceNotFoundException;
import ru.seurus.idioma.repository.buy.BuyHardwareRepository;
import ru.seurus.idioma.repository.stock.StockHardwareRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class StockHardwareController {

    @Autowired
    private BuyHardwareRepository buyHardwareRepository;

    @Autowired
    private StockHardwareRepository stockHardwareRepository;

    @GetMapping("/stock_hardware")
    public List<StockHardware> getAllStockHardware() {
        return stockHardwareRepository.findAll();
    }

    @PostMapping("/stock_hardware")
    public StockHardware createStockHardware(@RequestBody StockHardware stockHardware) {
        return stockHardwareRepository.save(stockHardware);
    }

    @GetMapping("/stock_hardware/{id}")
    public ResponseEntity<StockHardware> getStockHardwareById(@PathVariable Integer id) {
        StockHardware stockHardware = stockHardwareRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));
        return ResponseEntity.ok(stockHardware);
    }

    @DeleteMapping("/stock_hardware/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteStockHardware(@PathVariable Integer id) {
        StockHardware stockHardware = stockHardwareRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        stockHardwareRepository.delete(stockHardware);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
