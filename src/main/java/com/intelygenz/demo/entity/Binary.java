package com.intelygenz.demo.entity;

import lombok.Data;

@Data
public class Binary {

    Integer actualNumber;
    String binaryRepresentation;
    Integer onesCount = 0;

    public Binary(Integer number) {
        actualNumber = number;
        if (number != null) {
            binaryRepresentation = Integer.toBinaryString(number);
            for (char c : binaryRepresentation.toCharArray()) {
                if (c == '1') {
                    onesCount++;
                }
            }
        }
    }

}
