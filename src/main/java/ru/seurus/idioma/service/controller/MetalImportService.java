package ru.seurus.idioma.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.seurus.idioma.entity.set.SetOne;
import ru.seurus.idioma.entity.set.SetPositions;
import ru.seurus.idioma.entity.stock.StockMetalImport;
import ru.seurus.idioma.repository.stock.StockMetalImportRepository;

import java.util.HashMap;
import java.util.List;

@Service
public class MetalImportService {

    @Autowired
    private StockMetalImportRepository stockMetalImportRepository;

    public HashMap<String, Object> cycleMetalImport(Integer quantity, SetPositions set) {
        HashMap<String, Object> response = new HashMap<>();
        try {
            if (stockMetalImportRepository.getStockQuantity(set.getRecipeId()) < quantity*set.getQuantity()) {
                response.put("name", set.getName());
                response.put("current", stockMetalImportRepository.getStockQuantity(set.getRecipeId()));
                response.put("needed", set.getQuantity());
                response.put("result", "Недостаточно");
                response.put("id", set.getRecipeId().toString());
            } else {
                response.put("name", set.getName());
                response.put("current", stockMetalImportRepository.getStockQuantity(set.getRecipeId()));
                response.put("needed", set.getQuantity());
                response.put("result", "Достаточно");
                response.put("id", set.getRecipeId().toString());
            }
        } catch (NullPointerException exception) {
            response.put("name", set.getName());
            response.put("current", "-");
            response.put("needed", set.getQuantity());
            response.put("result", "Недостаточно");
            response.put("id", set.getRecipeId().toString());
        }
        return response;
    }

    public HashMap<String, String> cycleBuyMetalImport(Integer quantity, SetPositions set) {
        HashMap<String, String> response = new HashMap<>();
        double summary = quantity*set.getQuantity();
        try {
            while (summary != 0) {
                List<StockMetalImport> positions = stockMetalImportRepository.getOldestStock(set.getRecipeId());
                StockMetalImport position = positions.get(0);
                if (position.getQuantityCurrent() < summary) {
                    summary = summary - position.getQuantityCurrent();
                    stockMetalImportRepository.deleteById(position.getId());
                } else {
                    position.setQuantityCurrent(position.getQuantityCurrent() - summary);
                    stockMetalImportRepository.save(position);
                    if (position.getQuantityCurrent() < 0.01) {
                        stockMetalImportRepository.deleteById(position.getId());
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