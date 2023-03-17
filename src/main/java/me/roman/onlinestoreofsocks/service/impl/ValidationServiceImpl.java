package me.roman.onlinestoreofsocks.service.impl;

import me.roman.onlinestoreofsocks.model.Color;
import me.roman.onlinestoreofsocks.model.Size;
import me.roman.onlinestoreofsocks.model.SocksBatch;
import me.roman.onlinestoreofsocks.service.ValidationService;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl implements ValidationService {
    @Override
    public boolean validate(SocksBatch socksBatch) {
        return socksBatch.getSocks() != null
                && socksBatch.getSocks().getColor() != null
                && socksBatch.getSocks().getSize() != null
                && socksBatch.getValue() > 0
                && checkCotton(socksBatch.getSocks().getPercentOfCotton(), socksBatch.getSocks().getPercentOfCotton());
    }

    @Override
    public boolean validate(Color color, Size size, int cottonMin, int cottonMax) {
        return color != null && size != null && checkCotton(cottonMin, cottonMax);
    }

    private boolean checkCotton(int cottonMin, int cottonMax) {
        return cottonMin >= 0 && cottonMax <= 100;
    }
}