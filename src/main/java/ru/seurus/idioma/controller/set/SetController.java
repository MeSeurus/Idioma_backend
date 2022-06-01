package ru.seurus.idioma.controller.set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.seurus.idioma.entity.leftover.Leftovers;
import ru.seurus.idioma.entity.set.Set;
import ru.seurus.idioma.exception.ResourceNotFoundException;
import ru.seurus.idioma.repository.set.SetRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class SetController {

    @Autowired
    private SetRepository setRepository;

    @GetMapping("/set")
    public List<Set> getAllSet() {
        return setRepository.findAll();
    }

    @PostMapping("/set")
    public Set createSet(@RequestBody Set set) {
        return setRepository.save(set);
    }
    @GetMapping("/set/{id}")
    public ResponseEntity<Set> getSetById(@PathVariable Integer id) {
        Set set = setRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));
        return ResponseEntity.ok(set);
    }

    @PutMapping("/set/{id}")
    public ResponseEntity<Set> updateSet(@PathVariable Integer id, @RequestBody Set setUpdate) {
        Set set = setRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));

        set.setName(setUpdate.getName());
        set.setQuantity(setUpdate.getQuantity());
        set.setIsEmpty(setUpdate.getIsEmpty());

        Set updatedSet = setRepository.save(set);
        return ResponseEntity.ok(updatedSet);

    }

//    @DeleteMapping("/set/{id}")
//    public ResponseEntity<Map<String, Boolean>> deleteSet(@PathVariable Integer id) {
//        Set set = setRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));
//
//        setRepository.delete(set);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        return ResponseEntity.ok(response);
//    }
}
