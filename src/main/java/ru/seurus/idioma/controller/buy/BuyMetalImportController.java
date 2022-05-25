package ru.seurus.idioma.controller.buy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.seurus.idioma.entity.buy.BuyElectrics;
import ru.seurus.idioma.entity.buy.BuyImportParts;
import ru.seurus.idioma.entity.buy.BuyMetalImport;
import ru.seurus.idioma.exception.ResourceNotFoundException;
import ru.seurus.idioma.repository.buy.BuyImportPartsRepository;
import ru.seurus.idioma.repository.buy.BuyMetalImportRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class BuyMetalImportController {

    @Autowired
    private BuyMetalImportRepository buyMetalImportRepository;

    @GetMapping("/buy_metal_import")
    public List<BuyMetalImport> getAllBuyMetalImport() {
        return buyMetalImportRepository.findAll();
    }

    @PostMapping("/buy_metal_import")
    public BuyMetalImport createBuyMetalImport(@RequestBody BuyMetalImport buyMetalImport) {
        return buyMetalImportRepository.save(buyMetalImport);
    }
    @GetMapping("/buy_metal_import/{id}")
    public ResponseEntity<BuyMetalImport> getMetalImportById(@PathVariable Integer id) {
        BuyMetalImport buyMetalImport = buyMetalImportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));
        return ResponseEntity.ok(buyMetalImport);
    }

    @PutMapping("/buy_metal_import/{id}")
    public ResponseEntity<BuyMetalImport> updateBuyMetalImport(@PathVariable Integer id, @RequestBody BuyMetalImport buyMetalImportUpdate) {
        BuyMetalImport buyMetalImport = buyMetalImportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        buyMetalImport.setName(buyMetalImportUpdate.getName());
        buyMetalImport.setComment(buyMetalImportUpdate.getComment());
        buyMetalImport.setPriceEuro(buyMetalImportUpdate.getPriceEuro());
        buyMetalImport.setCoef(buyMetalImportUpdate.getCoef());
        buyMetalImport.setPricePURuble(buyMetalImportUpdate.getPricePURuble());
        buyMetalImport.setPriceRuble(buyMetalImportUpdate.getPriceRuble());

        BuyMetalImport updatedBuyMetalImport = buyMetalImportRepository.save(buyMetalImport);
        return ResponseEntity.ok(updatedBuyMetalImport);
    }

    @DeleteMapping("/buy_metal_import/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteBuyMetalImport(@PathVariable Integer id) {
        BuyMetalImport buyMetalImport = buyMetalImportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        buyMetalImportRepository.delete(buyMetalImport);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
