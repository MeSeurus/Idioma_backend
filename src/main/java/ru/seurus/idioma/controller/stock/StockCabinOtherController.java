package ru.seurus.idioma.controller.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.seurus.idioma.entity.buy.BuyCabinOther;
import ru.seurus.idioma.entity.buy.BuyElectrics;
import ru.seurus.idioma.entity.stock.StockCabinOther;
import ru.seurus.idioma.exception.ResourceNotFoundException;
import ru.seurus.idioma.repository.buy.BuyCabinOtherRepository;
import ru.seurus.idioma.repository.stock.StockCabinOtherRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class StockCabinOtherController {

    @Autowired
    private BuyCabinOtherRepository buyCabinOtherRepository;

    @Autowired
    private StockCabinOtherRepository stockCabinOtherRepository;

    @GetMapping("/stock_cabin_other")
    public List<StockCabinOther> getAllStockCabinOther() {
        return stockCabinOtherRepository.findAll();
    }

    @PostMapping("/stock_cabin_other")
    public StockCabinOther createStockCabinOther(@RequestBody StockCabinOther stockCabinOther) {
        return stockCabinOtherRepository.save(stockCabinOther);
    }

    @GetMapping("/stock_cabin_other/{id}")
    public ResponseEntity<StockCabinOther> getStockCabinOtherById(@PathVariable Integer id) {
        StockCabinOther stockCabinOther = stockCabinOtherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));
        return ResponseEntity.ok(stockCabinOther);
    }

    @DeleteMapping("/stock_cabin_other/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteStockCabinOther(@PathVariable Integer id) {
        StockCabinOther stockCabinOther = stockCabinOtherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        stockCabinOtherRepository.delete(stockCabinOther);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
