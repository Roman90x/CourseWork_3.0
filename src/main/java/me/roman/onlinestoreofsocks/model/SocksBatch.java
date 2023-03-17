package me.roman.onlinestoreofsocks.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocksBatch {
    private Socks socks;
    private int value;

}
