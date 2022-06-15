package ru.seurus.idioma.controller.set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.seurus.idioma.entity.set.SetPositions;
import ru.seurus.idioma.exception.ResourceNotFoundException;
import ru.seurus.idioma.repository.set.SetPositionsRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class SetPositionsController {

    @Autowired
    private SetPositionsRepository setPositionsRepository;

    @GetMapping("/set_positions")
    public List<SetPositions> getAllFirstSet() {
        return setPositionsRepository.findAll();
    }

    @PostMapping("/set_positions")
    public SetPositions createSetPositions(@RequestBody SetPositions set) {
        return setPositionsRepository.save(set);
    }

    @GetMapping("/set_positions/{id}")
    public ResponseEntity<SetPositions> getSetPositionsById(@PathVariable Integer id) {
        SetPositions set = setPositionsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));
        return ResponseEntity.ok(set);
    }

    @PutMapping("/set_positions/{id}")
    public ResponseEntity<SetPositions> updateSetPositions(@PathVariable Integer id, @RequestBody SetPositions setUpdate) {
        SetPositions set = setPositionsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        set.setName(setUpdate.getName());
        set.setRecipeId(setUpdate.getRecipeId());
        set.setQuantity(setUpdate.getQuantity());
        set.setTableName(setUpdate.getTableName());
        set.setSetId(setUpdate.getSetId());

        SetPositions updatedSet = setPositionsRepository.save(set);
        return ResponseEntity.ok(updatedSet);

    }

    @DeleteMapping("/set_positions/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteSetPositions(@PathVariable Integer id) {
        SetPositions set = setPositionsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        setPositionsRepository.delete(set);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
