package com.intelygenz.demo.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BinaryServiceTest {

    @Autowired
    BinaryService binaryService;

//    64 32 16 8 4 2 1
//    1  0  0  0 0 0 1

    private static Stream<Arguments> provideTestArguments() {
        return Stream.of(
                Arguments.of(List.of(1, 15, 5, 7, 3), List.of(15, 7, 3, 5, 1)),
                Arguments.of(List.of(1, 33, 3, 4, 5), List.of(3, 5, 33, 1, 4)),
                Arguments.of(List.of(1, 33, 64, 3, 405), List.of(405, 3, 33, 1, 64)),
                Arguments.of(List.of(1, 3, 2, 64, 65), List.of(3, 65, 1, 2, 64)),
                Arguments.of(List.of(1, 1, 2, 2), List.of(1, 1, 2, 2)),
                Arguments.of(List.of(1, 405, 64, 3, 405), List.of(405, 405, 3, 1, 64)),
                Arguments.of(List.of(), List.of())
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestArguments")
    void shouldOrderInputList(List<Integer> inputNumberList, List<Integer> expectedResultList) {
        // given

        // when
        List<Integer> returnList = binaryService.binarySorterLogic(inputNumberList);

        // then
        assertNotNull(returnList);
        assertEquals(expectedResultList, returnList);
    }
}
