package ru.seurus.idioma.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.seurus.idioma.entity.set.SetOne;
import ru.seurus.idioma.entity.set.SetPositions;
import ru.seurus.idioma.entity.stock.StockMetalSaez;
import ru.seurus.idioma.repository.stock.StockMetalSaezRepository;

import java.util.HashMap;
import java.util.List;

@Service
public class MetalSaezService {

    private final String SUCCESS = "Достаточно";
    private final String UNSUCCESS = "Недостаточно";

    @Autowired
    private StockMetalSaezRepository stockMetalSaezRepository;

    public HashMap<String, Object> cycleMetalSaez(Integer quantity, SetPositions set) {
        HashMap<String, Object> response = new HashMap<>();
        try {
            if (stockMetalSaezRepository.getStockQuantity(set.getRecipeId()) < quantity*set.getQuantity()) {
                response.put("name", set.getName());
                response.put("current", stockMetalSaezRepository.getStockQuantity(set.getRecipeId()));
                response.put("needed", set.getQuantity());
                response.put("result", "Недостаточно");
                response.put("id", set.getRecipeId().toString());
            } else {
                response.put("name", set.getName());
                response.put("current", stockMetalSaezRepository.getStockQuantity(set.getRecipeId()));
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

    public HashMap<String, String> cycleBuyMetalSaez(Integer quantity, SetPositions set) {
        HashMap<String, String> response = new HashMap<>();
        double summary = quantity*set.getQuantity();
        try {
            while (summary != 0) {
                List<StockMetalSaez> positions = stockMetalSaezRepository.getOldestStock(set.getRecipeId());
                StockMetalSaez position = positions.get(0);
                if (position.getQuantityCurrent() < summary) {
                    summary = summary - position.getQuantityCurrent();
                    stockMetalSaezRepository.deleteById(position.getId());
                } else {
                    position.setQuantityCurrent(position.getQuantityCurrent() - summary);
                    stockMetalSaezRepository.save(position);
                    if (position.getQuantityCurrent() < 0.01) {
                        stockMetalSaezRepository.deleteById(position.getId());
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
