package me.roman.onlinestoreofsocks.service;

import me.roman.onlinestoreofsocks.model.Color;
import me.roman.onlinestoreofsocks.model.Size;
import me.roman.onlinestoreofsocks.model.SocksBatch;

public interface ValidationService {
    boolean validate(SocksBatch socksBatch);

    boolean validate(Color color, Size size, int cottonMin, int cottonMax);
}
