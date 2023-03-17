package me.roman.onlinestoreofsocks.service.impl;

import lombok.AllArgsConstructor;
import me.roman.onlinestoreofsocks.exception.ValidationException;
import me.roman.onlinestoreofsocks.model.Color;
import me.roman.onlinestoreofsocks.model.Size;
import me.roman.onlinestoreofsocks.model.Socks;
import me.roman.onlinestoreofsocks.model.SocksBatch;
import me.roman.onlinestoreofsocks.repository.SocksRepository;
import me.roman.onlinestoreofsocks.service.StoreSocksService;
import me.roman.onlinestoreofsocks.service.ValidationService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class StoreSocksServiceImpl implements StoreSocksService {

    private final SocksRepository socksRepository;
    private final ValidationService validationService;

    @Override
    public void receipt(SocksBatch socksBatch) {
        checkSocksBatch(socksBatch);
        socksRepository.save(socksBatch);
    }

    @Override
    public int release(SocksBatch socksBatch) {
        checkSocksBatch(socksBatch);
        return socksRepository.remove(socksBatch);
    }

    @Override
    public int getCount(Color color, Size size, int cottonMin, int cottonMax) {
        if (!validationService.validate(color, size, cottonMin, cottonMax)) {
            throw new ValidationException();
        }
        Map<Socks, Integer> socksMap = socksRepository.getAll();

        for (Map.Entry<Socks, Integer> socksItem : socksMap.entrySet()) {
            Socks socks = socksItem.getKey();

            if (socks.getColor().equals(color) &&
                    socks.getSize().equals(size) &&
                    socks.getPercentOfCotton() >= cottonMin &&
                    socks.getPercentOfCotton() <= cottonMax) {
                return socksItem.getValue();
            }
        }
        return 0;
    }


    @Override
    public int cancellation(SocksBatch socksBatch) {
        checkSocksBatch(socksBatch);
        return socksRepository.remove(socksBatch);
    }

    private void checkSocksBatch(SocksBatch socksBatch) {
        if (!validationService.validate(socksBatch)) {
            throw new ValidationException();
        }
    }
}
