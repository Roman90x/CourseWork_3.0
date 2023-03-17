package me.roman.onlinestoreofsocks.repository;

import me.roman.onlinestoreofsocks.model.Socks;
import me.roman.onlinestoreofsocks.model.SocksBatch;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
@Repository
public class SocksRepositoryImpl implements SocksRepository{

    public Map<Socks, Integer> socksMap = new HashMap<>();
    @Override
    public void save(SocksBatch socksBatch) {
        Socks socks = socksBatch.getSocks();

        if (socksMap.containsKey(socks)) {
            socksMap.replace(socks, socksMap.get(socks) + socksBatch.getValue());
        } else {
            socksMap.put(socks, socksBatch.getValue());
        }
    }

    @Override
    public int remove(SocksBatch socksBatch) {
        Socks socks = socksBatch.getSocks();

        if (socksMap.containsKey(socks)) {
            int value = socksMap.get(socks);

            if (value > socksBatch.getValue()) {
                socksMap.replace(socks, socksMap.get(socks) - socksBatch.getValue());
                return socksBatch.getValue();
            } else {
                socksMap.remove(socks);
                return value;
            }
        }
        return 0;
    }

    @Override
    public Map<Socks, Integer> getAll() {
        return socksMap;
    }
}
