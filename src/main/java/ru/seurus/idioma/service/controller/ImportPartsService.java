package ru.seurus.idioma.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.seurus.idioma.entity.set.SetOne;
import ru.seurus.idioma.entity.set.SetPositions;
import ru.seurus.idioma.entity.stock.StockImportParts;
import ru.seurus.idioma.repository.stock.StockImportPartsRepository;

import java.util.HashMap;
import java.util.List;

@Service
public class ImportPartsService {

    @Autowired
    private StockImportPartsRepository stockImportPartsRepository;

    public HashMap<String, String> cycleImportParts(Integer quantity, SetPositions set) {
        HashMap<String, String> response = new HashMap<>();
        try {
            if (stockImportPartsRepository.getStockQuantity(set.getRecipeId()) < quantity*set.getQuantity()) {
                response.put("name", set.getName());
                response.put("current", stockImportPartsRepository.getStockQuantity(set.getRecipeId()).toString());
                response.put("needed", (set.getQuantity()).toString());
                response.put("result", "Недостаточно");
                response.put("id", set.getRecipeId().toString());
            } else {
                response.put("name", set.getName());
                response.put("current", stockImportPartsRepository.getStockQuantity(set.getRecipeId()).toString());
                response.put("needed", (set.getQuantity()).toString());
                response.put("result", "Достаточно");
                response.put("id", set.getRecipeId().toString());
            }
        } catch (NullPointerException exception) {
            response.put("name", set.getName());
            response.put("current", "-");
            response.put("needed", (set.getQuantity()).toString());
            response.put("result", "Недостаточно");
            response.put("id", set.getRecipeId().toString());
        }
        return response;
    }

    public HashMap<String, String> cycleBuyImportParts(Integer quantity, SetPositions set) {
        HashMap<String, String> response = new HashMap<>();
        double summary = quantity*set.getQuantity();
        try {
            while (summary != 0) {
                List<StockImportParts> positions = stockImportPartsRepository.getOldestStock(set.getRecipeId());
                StockImportParts position = positions.get(0);
                if (position.getQuantityCurrent() < summary) {
                    summary = summary - position.getQuantityCurrent();
                    stockImportPartsRepository.deleteById(position.getId());
                } else {
                    position.setQuantityCurrent(position.getQuantityCurrent() - summary);
                    stockImportPartsRepository.save(position);
                    if (position.getQuantityCurrent() < 0.01) {
                        stockImportPartsRepository.deleteById(position.getId());
                    }
                    summary = 0;
                }
            }
        } catch (Exception exception) {
            response.put(set.getName(), "Что-то пошло не так");
        }
        return response;
    }
}