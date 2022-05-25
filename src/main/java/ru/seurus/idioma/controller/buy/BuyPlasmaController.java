package ru.seurus.idioma.controller.buy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.seurus.idioma.entity.buy.BuyElectrics;
import ru.seurus.idioma.entity.buy.BuyMetalSaez;
import ru.seurus.idioma.entity.buy.BuyPlasma;
import ru.seurus.idioma.exception.ResourceNotFoundException;
import ru.seurus.idioma.repository.buy.BuyMetalSaezRepository;
import ru.seurus.idioma.repository.buy.BuyPlasmaRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class BuyPlasmaController {

    @Autowired
    private BuyPlasmaRepository buyPlasmaRepository;

    @GetMapping("/buy_plasma")
    public List<BuyPlasma> getAllBuyPlasma() {
        return buyPlasmaRepository.findAll();
    }

    @PostMapping("/buy_plasma")
    public BuyPlasma createBuyPlasma(@RequestBody BuyPlasma buyPlasma) {
        return buyPlasmaRepository.save(buyPlasma);
    }
    @GetMapping("/buy_plasma/{id}")
    public ResponseEntity<BuyPlasma> getPlasmaById(@PathVariable Integer id) {
        BuyPlasma buyPlasma = buyPlasmaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));
        return ResponseEntity.ok(buyPlasma);
    }

    @PutMapping("/buy_plasma/{id}")
    public ResponseEntity<BuyPlasma> updateBuyPlasma(@PathVariable Integer id, @RequestBody BuyPlasma buyPlasmaUpdate) {
        BuyPlasma buyPlasma = buyPlasmaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        buyPlasma.setName(buyPlasmaUpdate.getName());
        buyPlasma.setComment(buyPlasmaUpdate.getComment());
        buyPlasma.setPriceGoods(buyPlasmaUpdate.getPriceGoods());
        buyPlasma.setPriceWork(buyPlasmaUpdate.getPriceWork());
        buyPlasma.setPriceResult(buyPlasmaUpdate.getPriceResult());

        BuyPlasma updatedBuyPlasma = buyPlasmaRepository.save(buyPlasma);
        return ResponseEntity.ok(updatedBuyPlasma);
    }

    @DeleteMapping("/buy_plasma/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteBuyPlasma(@PathVariable Integer id) {
        BuyPlasma buyPlasma = buyPlasmaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        buyPlasmaRepository.delete(buyPlasma);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
