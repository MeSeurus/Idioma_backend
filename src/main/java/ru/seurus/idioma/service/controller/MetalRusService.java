package ru.seurus.idioma.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.seurus.idioma.entity.set.SetOne;
import ru.seurus.idioma.entity.set.SetPositions;
import ru.seurus.idioma.entity.stock.StockMetalRus;
import ru.seurus.idioma.repository.stock.StockMetalRusRepository;

import java.util.HashMap;
import java.util.List;

@Service
public class MetalRusService {

    @Autowired
    private StockMetalRusRepository stockMetalRusRepository;

    public HashMap<String, String> cycleMetalRus(Integer quantity, SetPositions set) {
        HashMap<String, String> response = new HashMap<>();
        try {
            if (stockMetalRusRepository.getStockQuantity(set.getRecipeId()) < quantity*set.getQuantity()) {
                response.put("name", set.getName());
                response.put("current", stockMetalRusRepository.getStockQuantity(set.getRecipeId()).toString());
                response.put("needed", (set.getQuantity()).toString());
                response.put("result", "Недостаточно");
                response.put("id", set.getRecipeId().toString());
            } else {
                response.put("name", set.getName());
                response.put("current", stockMetalRusRepository.getStockQuantity(set.getRecipeId()).toString());
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

    public HashMap<String, String> cycleBuyMetalRus(Integer quantity, SetPositions set) {
        HashMap<String, String> response = new HashMap<>();
        double summary = quantity*set.getQuantity();
        try {
            while (summary != 0) {
                List<StockMetalRus> positions = stockMetalRusRepository.getOldestStock(set.getRecipeId());
                StockMetalRus position = positions.get(0);
                if (position.getQuantityCurrent() < summary) {
                    summary = summary - position.getQuantityCurrent();
                    stockMetalRusRepository.deleteById(position.getId());
                } else {
                    position.setQuantityCurrent(position.getQuantityCurrent() - summary);
                    stockMetalRusRepository.save(position);
                    if (position.getQuantityCurrent() < 0.01) {
                        stockMetalRusRepository.deleteById(position.getId());
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