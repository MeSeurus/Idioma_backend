package ru.seurus.idioma.controller.leftover;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.seurus.idioma.entity.leftover.Leftovers;
import ru.seurus.idioma.exception.ResourceNotFoundException;
import ru.seurus.idioma.repository.leftover.LeftoversRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class LeftoversController {

    @Autowired
    private LeftoversRepository leftoversRepository;

    @GetMapping("/leftovers")
    public List<Leftovers> getAllLeftovers() {
        return leftoversRepository.findAll();
    }

    @PostMapping("/leftovers")
    public Leftovers createLeftovers(@RequestBody Leftovers leftovers) {
        return leftoversRepository.save(leftovers);
    }
    @GetMapping("/leftovers/{id}")
    public ResponseEntity<Leftovers> getLeftoversById(@PathVariable Integer id) {
        Leftovers leftovers = leftoversRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));
        return ResponseEntity.ok(leftovers);
    }

    @PutMapping("/leftovers/{id}")
    public ResponseEntity<Leftovers> updateLeftovers(@PathVariable Integer id, @RequestBody Leftovers leftoversUpdate) {
        Leftovers leftovers = leftoversRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        leftovers.setName(leftoversUpdate.getName());
        leftovers.setComment(leftoversUpdate.getComment());
        leftovers.setDate(leftoversUpdate.getDate());
        leftovers.setQuantity(leftovers.getQuantity());

        Leftovers updatedLeftovers = leftoversRepository.save(leftovers);
        return ResponseEntity.ok(updatedLeftovers);

    }

    @DeleteMapping("/leftovers/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteLeftovers(@PathVariable Integer id) {
        Leftovers leftovers = leftoversRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        leftoversRepository.delete(leftovers);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
