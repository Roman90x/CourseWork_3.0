package me.roman.onlinestoreofsocks.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import me.roman.onlinestoreofsocks.controller.dto.ResponseDto;
import me.roman.onlinestoreofsocks.model.Color;
import me.roman.onlinestoreofsocks.model.Size;
import me.roman.onlinestoreofsocks.model.SocksBatch;
import me.roman.onlinestoreofsocks.service.StoreSocksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks")
@Tag(name = "API для учета носков", description = "Приход, расход, остаток")
@RequiredArgsConstructor
public class StoreSocksController {

    private final StoreSocksService storeSocksService;

    @PostMapping
    @Operation(summary = "Регистрирует приход товара на склад")
    @ApiResponse(responseCode = "200",description = "удалось добавить приход")
    @ApiResponse(responseCode = "400",description = "параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500",description = "произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<ResponseDto> receipt(@RequestBody SocksBatch socksBatch) {
        storeSocksService.receipt(socksBatch);
        return ResponseEntity.ok(new ResponseDto(""));
    }

    @PutMapping
    @Operation(summary = "Регистрирует отпуск носков со склада")
    @ApiResponse(responseCode = "200",description = "удалось произвести отпуск носков со склада")
    @ApiResponse(responseCode = "400",description = "товара нет на складе в нужном количестве или параметры запроса имеют некорректный формат")
    @ApiResponse(responseCode = "500",description = "произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<ResponseDto> release (@RequestBody SocksBatch socksBatch) {
        int socksCount = storeSocksService.release(socksBatch);
        return ResponseEntity.ok(new ResponseDto(""));
    }

    @GetMapping
    @Operation(summary = "Возвращает общее количество носков на складе")
    @ApiResponse(responseCode = "200",description = "запрос выполнен")
    @ApiResponse(responseCode = "400",description = "параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500",description = "произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<ResponseDto> getCount(@RequestParam Color color,
                                         @RequestParam Size size,
                                         @RequestParam int cottonMin,
                                         @RequestParam int cottonMax) {
        int socksCount = storeSocksService.getCount(color, size, cottonMin, cottonMax);
        return ResponseEntity.ok(new ResponseDto(""));
    }

    @DeleteMapping
    @Operation(summary = "Регистрирует списание испорченных (бракованных) носков")
    @ApiResponse(responseCode = "200",description = "запрос выполнен, товар списан со склада")
    @ApiResponse(responseCode = "400",description = "параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500",description = "произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<ResponseDto> cancellation(@RequestBody SocksBatch socksBatch) {
        int socksCount = storeSocksService.cancellation(socksBatch);
        return ResponseEntity.ok(new ResponseDto(""));
    }
}
