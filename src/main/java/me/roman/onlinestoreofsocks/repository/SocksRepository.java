package me.roman.onlinestoreofsocks.repository;

import me.roman.onlinestoreofsocks.model.Socks;
import me.roman.onlinestoreofsocks.model.SocksBatch;

import java.util.Map;

public interface SocksRepository {
    void save(SocksBatch socksBatch);

    int remove(SocksBatch socksBatch);

    Map<Socks, Integer> getAll();
}
