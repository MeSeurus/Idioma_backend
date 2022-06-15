package ru.seurus.idioma.controller.set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.seurus.idioma.entity.set.SetOne;
import ru.seurus.idioma.exception.ResourceNotFoundException;
import ru.seurus.idioma.repository.set.SetOneRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class SetOneController {

    @Autowired
    private SetOneRepository setRepository;

    @GetMapping("/first_set")
    public List<SetOne> getAllFirstSet() {
        return setRepository.findAll();
    }

    @PostMapping("/first_set")
    public SetOne createSetOne(@RequestBody SetOne set) {
        return setRepository.save(set);
    }

    @GetMapping("/first_set/{id}")
    public ResponseEntity<SetOne> getSetOneById(@PathVariable Integer id) {
        SetOne set = setRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));
        return ResponseEntity.ok(set);
    }

    @PutMapping("/first_set/{id}")
    public ResponseEntity<SetOne> updateSetOne(@PathVariable Integer id, @RequestBody SetOne setUpdate) {
        SetOne set = setRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        set.setName(setUpdate.getName());
        set.setRecipeId(setUpdate.getRecipeId());
        set.setQuantity(setUpdate.getQuantity());
        set.setTableName(setUpdate.getTableName());

        SetOne updatedSet = setRepository.save(set);
        return ResponseEntity.ok(updatedSet);

    }

    @DeleteMapping("/first_set/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteSetOne(@PathVariable Integer id) {
        SetOne set = setRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        setRepository.delete(set);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}