package ru.seurus.idioma.controller.set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.seurus.idioma.entity.set.Set;
import ru.seurus.idioma.entity.set.SetOne;
import ru.seurus.idioma.entity.set.SetPositions;
import ru.seurus.idioma.exception.ResourceNotFoundException;
import ru.seurus.idioma.repository.set.SetOneRepository;
import ru.seurus.idioma.repository.set.SetPositionsRepository;
import ru.seurus.idioma.repository.set.SetRepository;
import ru.seurus.idioma.service.controller.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class SetController {

    @Autowired
    private SetRepository setRepository;

    @Autowired
    private SetPositionsRepository setPositionsRepository;

    @Autowired
    private MetalSaezService metalSaezService;

    @Autowired
    private PlasmaService plasmaService;

    @Autowired
    private MetalRusService metalRusService;

    @Autowired
    private MetalImportService metalImportService;

    @Autowired
    private CabinOtherService cabinOtherService;

    @Autowired
    private HardwareService hardwareService;

    @Autowired
    private ElectricsService electricsService;

    @Autowired
    private ConsumablesService consumablesService;

    @Autowired
    private ImportPartsService importPartsService;

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
        set.setPrice(setUpdate.getPrice());
        set.setQuantity(setUpdate.getQuantity());
        set.setIsEmpty(setUpdate.getIsEmpty());

        Set updatedSet = setRepository.save(set);
        return ResponseEntity.ok(updatedSet);

    }

    @GetMapping("/set/status/{id}")
    public String getSetStatus(@PathVariable Integer id, @RequestBody Integer quantity) {
        List<HashMap<String, Object>> response = new ArrayList<>();
//        if (id.equals(1) || id.equals("1")) {
        List<SetPositions> positions = setPositionsRepository.forCheck(id);
            for (SetPositions position : positions) {
                if (position.getTableName().equals("stock_metal_saez")) {
                    response.add(metalSaezService.cycleMetalSaez(quantity, position));
                }
                if (position.getTableName().equals("stock_metal_rus")) {
                    response.add(metalRusService.cycleMetalRus(quantity, position));
                }
                if (position.getTableName().equals("stock_metal_import")) {
                    response.add(metalImportService.cycleMetalImport(quantity, position));
                }
                if (position.getTableName().equals("stock_plasma")) {
                    response.add(plasmaService.cyclePlasma(quantity, position));
                }
                if (position.getTableName().equals("stock_consumables")) {
                    response.add(consumablesService.cycleConsumables(quantity, position));
                }
                if (position.getTableName().equals("stock_hardware")) {
                    response.add(hardwareService.cycleHardware(quantity, position));
                }
                if (position.getTableName().equals("stock_electrics")) {
                    response.add(electricsService.cycleElectrics(quantity, position));
                }
                if (position.getTableName().equals("stock_cabin_other")) {
                    response.add(cabinOtherService.cycleCabinOther(quantity, position));
                }
                if (position.getTableName().equals("stock_import_parts")) {
                    response.add(importPartsService.cycleImportParts(quantity, position));
                }
//            }
        }
        for (HashMap<String, Object> item : response) {
            if (item.containsValue("Недостаточно")) {
                return "Error";
            } else {
                continue;
            }
        }
        return "Success";
    }

    @GetMapping("/set/info/{id}")
    public ResponseEntity<List<HashMap<String, Object>>> getSetInfo(@PathVariable Integer id) {
        List<HashMap<String, Object>> response = new ArrayList<>();
//        if (id.equals(1) || id.equals("1")) {
            List<SetPositions> positions = setPositionsRepository.forCheck(id);
            for (SetPositions position : positions) {
                if (position.getTableName().equals("stock_metal_saez")) {
                    response.add(metalSaezService.cycleMetalSaez(1, position));
                }
                if (position.getTableName().equals("stock_metal_rus")) {
                    response.add(metalRusService.cycleMetalRus(1, position));
                }
                if (position.getTableName().equals("stock_metal_import")) {
                    response.add(metalImportService.cycleMetalImport(1, position));
                }
                if (position.getTableName().equals("stock_plasma")) {
                    response.add(plasmaService.cyclePlasma(1, position));
                }
                if (position.getTableName().equals("stock_consumables")) {
                    response.add(consumablesService.cycleConsumables(1, position));
                }
                if (position.getTableName().equals("stock_hardware")) {
                    response.add(hardwareService.cycleHardware(1, position));
                }
                if (position.getTableName().equals("stock_electrics")) {
                    response.add(electricsService.cycleElectrics(1, position));
                }
                if (position.getTableName().equals("stock_cabin_other")) {
                    response.add(cabinOtherService.cycleCabinOther(1, position));
                }
                if (position.getTableName().equals("stock_import_parts")) {
                    response.add(importPartsService.cycleImportParts(1, position));
                }
//            }
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/set/add/{id}")
    public ResponseEntity<String> buySet(@PathVariable Integer id) {
        Double lastBuy = 0.0;
        if (getSetStatus(id, 1).equals("Error")) {
            return ResponseEntity.ok("Сборка невозможна");
        } else {
//            if (id.equals(1) || id.equals("1")) {
            List<SetPositions> positions = setPositionsRepository.forCheck(id);
                for (SetPositions position : positions) {
                    if (position.getTableName().equals("stock_metal_saez")) {
                        metalSaezService.cycleBuyMetalSaez(1, position);
                    }
                    if (position.getTableName().equals("stock_metal_rus")) {
                        metalRusService.cycleBuyMetalRus(1, position);
                    }
                    if (position.getTableName().equals("stock_metal_import")) {
                        metalImportService.cycleBuyMetalImport(1, position);
                    }
                    if (position.getTableName().equals("stock_plasma")) {
                        plasmaService.cycleBuyPlasma(1, position);
                    }
                    if (position.getTableName().equals("stock_consumables")) {
                        consumablesService.cycleBuyConsumables(1, position);
                    }
                    if (position.getTableName().equals("stock_hardware")) {
                        hardwareService.cycleBuyHardware(1, position);
                    }
                    if (position.getTableName().equals("stock_electrics")) {
                        electricsService.cycleBuyElectrics(1, position);
                    }
                    if (position.getTableName().equals("stock_cabin_other")) {
                        cabinOtherService.cycleBuyCabinOther(1, position);
                    }
                    if (position.getTableName().equals("stock_import_parts")) {
                        importPartsService.cycleBuyImportParts(1, position);
                    }
//                }
            }
            try {
                Set current = setRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Message"));
                Double plus = current.getQuantity() + 1.0;
                current.setQuantity(plus);
                setRepository.save(current);
                return ResponseEntity.ok("Успешно");
            } catch (NullPointerException exception) {
                Set current = setRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Message"));
                Double plus = 1.0;
                current.setQuantity(plus);
                setRepository.save(current);
                return ResponseEntity.ok("Успешно");
            }
//            Set current = setRepository.findPosition(1);
//            current.setQuantity(setRepository.findPosition(1).getQuantity()+1.0);
//            setRepository.save(current);
//            return ResponseEntity.ok("Успешно");

        }
    }

    @DeleteMapping("/set/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteSet(@PathVariable Integer id) {
        Set set = setRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist with id: " + id));
        List<Integer> list = setPositionsRepository.forDelete(id);
        for (Integer i : list) {
            setPositionsRepository.deleteById(i);
        }
        setRepository.delete(set);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/set/")
    public ResponseEntity<Map<String, Boolean>> deleteChosenPositions(@RequestBody SetPositions deletion) {
        List<Integer> list = setPositionsRepository.forDelete(deletion.getSetId());
        for (Integer i : list) {
            setPositionsRepository.deleteById(i);
        }
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
