package ru.seurus.idioma.controller.buy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.seurus.idioma.entity.buy.BuyCabinOther;
import ru.seurus.idioma.entity.buy.BuyConsumables;
import ru.seurus.idioma.entity.buy.BuyElectrics;
import ru.seurus.idioma.exception.ResourceNotFoundException;
import ru.seurus.idioma.repository.buy.BuyConsumablesRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class BuyConsumablesController {

    @Autowired
    private BuyConsumablesRepository buyConsumablesRepository;

    @GetMapping("/buy_consumables")
    public List<BuyConsumables> getAllBuyConsumables() {
        return buyConsumablesRepository.findAll();
    }

    @PostMapping("/buy_consumables")
    public BuyConsumables createBuyConsumables(@RequestBody BuyConsumables buyConsumables) {
        return buyConsumablesRepository.save(buyConsumables);
    }
    @GetMapping("/buy_consumables/{id}")
    public ResponseEntity<BuyConsumables> getConsumablesById(@PathVariable Integer id) {
        BuyConsumables buyConsumables = buyConsumablesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));
        return ResponseEntity.ok(buyConsumables);
    }

    @PutMapping("/buy_consumables/{id}")
    public ResponseEntity<BuyConsumables> updateBuyConsumables(@PathVariable Integer id, @RequestBody BuyConsumables buyConsumablesUpdate) {
        BuyConsumables buyConsumables = buyConsumablesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        buyConsumables.setName(buyConsumablesUpdate.getName());
        buyConsumables.setComment(buyConsumablesUpdate.getComment());
        buyConsumables.setPriceGoods(buyConsumablesUpdate.getPriceGoods());
        buyConsumables.setPriceWork(buyConsumablesUpdate.getPriceWork());
        buyConsumables.setPriceResult(buyConsumablesUpdate.getPriceResult());

        BuyConsumables updatedBuyConsumables = buyConsumablesRepository.save(buyConsumables);
        return ResponseEntity.ok(updatedBuyConsumables);
    }

    @DeleteMapping("/buy_consumabless/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteBuyConsumables(@PathVariable Integer id) {
        BuyConsumables buyConsumables = buyConsumablesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        buyConsumablesRepository.delete(buyConsumables);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
