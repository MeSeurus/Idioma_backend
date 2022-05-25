package ru.seurus.idioma.controller.buy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.seurus.idioma.entity.buy.BuyElectrics;
import ru.seurus.idioma.entity.buy.BuyMetalSaez;
import ru.seurus.idioma.exception.ResourceNotFoundException;
import ru.seurus.idioma.repository.buy.BuyMetalSaezRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class BuyMetalSaezController {

    @Autowired
    private BuyMetalSaezRepository buyMetalSaezRepository;

    @GetMapping("/buy_metal_saez")
    public List<BuyMetalSaez> getAllBuyMetalSaez() {
        return buyMetalSaezRepository.findAll();
    }

    @PostMapping("/buy_metal_saez")
    public BuyMetalSaez createBuyMetalSaez(@RequestBody BuyMetalSaez buyMetalSaez) {
        return buyMetalSaezRepository.save(buyMetalSaez);
    }
    @GetMapping("/buy_metal_saez/{id}")
    public ResponseEntity<BuyMetalSaez> getMetalSaezById(@PathVariable Integer id) {
        BuyMetalSaez buyMetalSaez = buyMetalSaezRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));
        return ResponseEntity.ok(buyMetalSaez);
    }

    @PutMapping("/buy_metal_saez/{id}")
    public ResponseEntity<BuyMetalSaez> updateBuyMetalSaez(@PathVariable Integer id, @RequestBody BuyMetalSaez buyMetalSaezUpdate) {
        BuyMetalSaez buyMetalSaez = buyMetalSaezRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        buyMetalSaez.setName(buyMetalSaezUpdate.getName());
        buyMetalSaez.setComment(buyMetalSaezUpdate.getComment());
        buyMetalSaez.setPriceEuro(buyMetalSaezUpdate.getPriceEuro());
        buyMetalSaez.setCoef(buyMetalSaezUpdate.getCoef());
        buyMetalSaez.setPriceRuble(buyMetalSaezUpdate.getPriceRuble());

        BuyMetalSaez updatedBuyMetalSaez = buyMetalSaezRepository.save(buyMetalSaez);
        return ResponseEntity.ok(updatedBuyMetalSaez);
    }

    @DeleteMapping("/buy_metal_saez/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteBuyMetalSaez(@PathVariable Integer id) {
        BuyMetalSaez buyMetalSaez = buyMetalSaezRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        buyMetalSaezRepository.delete(buyMetalSaez);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
