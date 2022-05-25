package ru.seurus.idioma.controller.buy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.seurus.idioma.entity.buy.BuyCabinOther;
import ru.seurus.idioma.entity.buy.BuyElectrics;
import ru.seurus.idioma.entity.buy.BuyImportParts;
import ru.seurus.idioma.exception.ResourceNotFoundException;
import ru.seurus.idioma.repository.buy.BuyCabinOtherRepository;
import ru.seurus.idioma.repository.buy.BuyImportPartsRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class BuyImportPartsController {

    @Autowired
    private BuyImportPartsRepository buyImportPartsRepository;

    @GetMapping("/buy_import_parts")
    public List<BuyImportParts> getAllBuyImportParts() {
        return buyImportPartsRepository.findAll();
    }

    @PostMapping("/buy_import_parts")
    public BuyImportParts createBuyImportParts(@RequestBody BuyImportParts buyImportParts) {
        return buyImportPartsRepository.save(buyImportParts);
    }
    @GetMapping("/buy_import_parts/{id}")
    public ResponseEntity<BuyImportParts> getImportPartsById(@PathVariable Integer id) {
        BuyImportParts buyImportParts = buyImportPartsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));
        return ResponseEntity.ok(buyImportParts);
    }

    @PutMapping("/buy_import_parts/{id}")
    public ResponseEntity<BuyImportParts> updateBuyImportParts(@PathVariable Integer id, @RequestBody BuyImportParts buyImportPartsUpdate) {
        BuyImportParts buyImportParts = buyImportPartsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        buyImportParts.setName(buyImportPartsUpdate.getName());
        buyImportParts.setComment(buyImportPartsUpdate.getComment());
        buyImportParts.setPrice(buyImportPartsUpdate.getPrice());

        BuyImportParts updatedBuyImportParts = buyImportPartsRepository.save(buyImportParts);
        return ResponseEntity.ok(updatedBuyImportParts);
    }

    @DeleteMapping("/buy_import_parts/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteBuyImportParts(@PathVariable Integer id) {
        BuyImportParts buyImportParts = buyImportPartsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        buyImportPartsRepository.delete(buyImportParts);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
