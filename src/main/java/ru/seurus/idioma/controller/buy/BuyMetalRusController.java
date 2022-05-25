package ru.seurus.idioma.controller.buy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.seurus.idioma.entity.buy.BuyElectrics;
import ru.seurus.idioma.entity.buy.BuyMetalImport;
import ru.seurus.idioma.entity.buy.BuyMetalRus;
import ru.seurus.idioma.exception.ResourceNotFoundException;
import ru.seurus.idioma.repository.buy.BuyMetalImportRepository;
import ru.seurus.idioma.repository.buy.BuyMetalRusRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class BuyMetalRusController {

    @Autowired
    private BuyMetalRusRepository buyMetalRusRepository;

    @GetMapping("/buy_metal_rus")
    public List<BuyMetalRus> getAllBuyMetalRus() {
        return buyMetalRusRepository.findAll();
    }

    @PostMapping("/buy_metal_rus")
    public BuyMetalRus createBuyMetalRus(@RequestBody BuyMetalRus buyMetalRus) {
        return buyMetalRusRepository.save(buyMetalRus);
    }
    @GetMapping("/buy_metal_rus/{id}")
    public ResponseEntity<BuyMetalRus> getMetalRusById(@PathVariable Integer id) {
        BuyMetalRus buyMetalRus = buyMetalRusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));
        return ResponseEntity.ok(buyMetalRus);
    }

    @PutMapping("/buy_metal_rus/{id}")
    public ResponseEntity<BuyMetalRus> updateBuyMetalRus(@PathVariable Integer id, @RequestBody BuyMetalRus buyMetalRusUpdate) {
        BuyMetalRus buyMetalRus = buyMetalRusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        buyMetalRus.setName(buyMetalRusUpdate.getName());
        buyMetalRus.setComment(buyMetalRusUpdate.getComment());
        buyMetalRus.setCoef(buyMetalRusUpdate.getCoef());
        buyMetalRus.setPricePURuble(buyMetalRusUpdate.getPricePURuble());
        buyMetalRus.setPriceRuble(buyMetalRusUpdate.getPriceRuble());

        BuyMetalRus updatedBuyMetalRus = buyMetalRusRepository.save(buyMetalRus);
        return ResponseEntity.ok(updatedBuyMetalRus);
    }

    @DeleteMapping("/buy_metal_rus/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteBuyMetalRus(@PathVariable Integer id) {
        BuyMetalRus buyMetalRus = buyMetalRusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        buyMetalRusRepository.delete(buyMetalRus);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
