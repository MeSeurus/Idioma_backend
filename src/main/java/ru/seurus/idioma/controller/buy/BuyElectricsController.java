package ru.seurus.idioma.controller.buy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.seurus.idioma.entity.buy.BuyElectrics;
import ru.seurus.idioma.exception.ResourceNotFoundException;
import ru.seurus.idioma.repository.buy.BuyElectricsRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class BuyElectricsController {

    @Autowired
    private BuyElectricsRepository buyElectricsRepository;

    @GetMapping("/buy_electrics")
    public List<BuyElectrics> getAllBuyElectrics() {
        return buyElectricsRepository.findAll();
    }

    @PostMapping("/buy_electrics")
    public BuyElectrics createBuyElectrics(@RequestBody BuyElectrics buyElectrics) {
        return buyElectricsRepository.save(buyElectrics);
    }
    @GetMapping("/buy_electrics/{id}")
    public ResponseEntity<BuyElectrics> getElectricsById(@PathVariable Integer id) {
        BuyElectrics buyElectrics = buyElectricsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));
        return ResponseEntity.ok(buyElectrics);
    }

    @PutMapping("/buy_electrics/{id}")
    public ResponseEntity<BuyElectrics> updateBuyElectrics(@PathVariable Integer id, @RequestBody BuyElectrics buyElectricsUpdate) {
        BuyElectrics buyElectrics = buyElectricsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        buyElectrics.setName(buyElectricsUpdate.getName());
        buyElectrics.setComment(buyElectricsUpdate.getComment());
        buyElectrics.setPrice(buyElectricsUpdate.getPrice());

        BuyElectrics updatedBuyElectrics = buyElectricsRepository.save(buyElectrics);
        return ResponseEntity.ok(updatedBuyElectrics);

    }

    @DeleteMapping("/buy_electrics/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteBuyElectrics(@PathVariable Integer id) {
        BuyElectrics buyElectrics = buyElectricsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        buyElectricsRepository.delete(buyElectrics);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
