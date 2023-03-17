package me.roman.onlinestoreofsocks.service;

import me.roman.onlinestoreofsocks.model.Color;
import me.roman.onlinestoreofsocks.model.Size;
import me.roman.onlinestoreofsocks.model.SocksBatch;

public interface StoreSocksService {
    void receipt(SocksBatch socksBatch);

    int release(SocksBatch socksBatch);

    int getCount(Color color, Size size, int cottonMin, int cottonMax);

    int cancellation(SocksBatch socksBatch);
}
