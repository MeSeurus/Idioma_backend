package ru.seurus.idioma.controller.buy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.seurus.idioma.entity.buy.BuyElectrics;
import ru.seurus.idioma.entity.buy.BuyHardware;
import ru.seurus.idioma.exception.ResourceNotFoundException;
import ru.seurus.idioma.repository.buy.BuyHardwareRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class BuyHardwareController {

    @Autowired
    private BuyHardwareRepository buyHardwareRepository;

    @GetMapping("/buy_hardware")
    public List<BuyHardware> getAllBuyHardware() {
        return buyHardwareRepository.findAll();
    }

    @PostMapping("/buy_hardware")
    public BuyHardware createBuyHardware(@RequestBody BuyHardware buyHardware) {
        return buyHardwareRepository.save(buyHardware);
    }
    @GetMapping("/buy_hardware/{id}")
    public ResponseEntity<BuyHardware> getHardwareById(@PathVariable Integer id) {
        BuyHardware buyHardware = buyHardwareRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));
        return ResponseEntity.ok(buyHardware);
    }

    @PutMapping("/buy_hardware/{id}")
    public ResponseEntity<BuyHardware> updateBuyHardware(@PathVariable Integer id, @RequestBody BuyHardware buyHardwareUpdate) {
        BuyHardware buyHardware = buyHardwareRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        buyHardware.setName(buyHardwareUpdate.getName());
        buyHardware.setComment(buyHardwareUpdate.getComment());
        buyHardware.setPriceGoods(buyHardwareUpdate.getPriceGoods());
        buyHardware.setPriceWork(buyHardwareUpdate.getPriceWork());
        buyHardware.setPriceResult(buyHardwareUpdate.getPriceResult());

        BuyHardware updatedBuyHardware = buyHardwareRepository.save(buyHardware);
        return ResponseEntity.ok(updatedBuyHardware);
    }

    @DeleteMapping("/buy_hardware/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteBuyHardware(@PathVariable Integer id) {
        BuyHardware buyHardware = buyHardwareRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        buyHardwareRepository.delete(buyHardware);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
