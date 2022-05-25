package ru.seurus.idioma.controller.buy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.seurus.idioma.entity.buy.BuyCabinOther;
import ru.seurus.idioma.entity.buy.BuyElectrics;
import ru.seurus.idioma.exception.ResourceNotFoundException;
import ru.seurus.idioma.repository.buy.BuyCabinOtherRepository;
import ru.seurus.idioma.repository.buy.BuyElectricsRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class BuyCabinOtherController {

    @Autowired
    private BuyCabinOtherRepository buyCabinOtherRepository;

    @GetMapping("/buy_cabin_other")
    public List<BuyCabinOther> getAllBuyElectrics() {
        return buyCabinOtherRepository.findAll();
    }

    @PostMapping("/buy_cabin_other")
    public BuyCabinOther createBuyCabinOther(@RequestBody BuyCabinOther buyCabinOther) {
        return buyCabinOtherRepository.save(buyCabinOther);
    }
    @GetMapping("/buy_cabin_other/{id}")
    public ResponseEntity<BuyCabinOther> getCabinOtherById(@PathVariable Integer id) {
        BuyCabinOther buyCabinOther = buyCabinOtherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));
        return ResponseEntity.ok(buyCabinOther);
    }

    @PutMapping("/buy_cabin_other/{id}")
    public ResponseEntity<BuyCabinOther> updateBuyCabinOther(@PathVariable Integer id, @RequestBody BuyCabinOther buyCabinOtherUpdate) {
        BuyCabinOther buyCabinOther = buyCabinOtherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        buyCabinOther.setName(buyCabinOtherUpdate.getName());
        buyCabinOther.setComment(buyCabinOtherUpdate.getComment());
        buyCabinOther.setPrice(buyCabinOtherUpdate.getPrice());

        BuyCabinOther updatedBuyCabinOther = buyCabinOtherRepository.save(buyCabinOther);
        return ResponseEntity.ok(updatedBuyCabinOther);
    }

    @DeleteMapping("/buy_cabin_other/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteBuyCabinOther(@PathVariable Integer id) {
        BuyCabinOther buyCabinOther = buyCabinOtherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        buyCabinOtherRepository.delete(buyCabinOther);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
