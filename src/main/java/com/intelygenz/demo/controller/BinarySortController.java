package com.intelygenz.demo.controller;

import com.intelygenz.demo.service.IBinaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("intelygenz")
@RequiredArgsConstructor
@Slf4j
public class BinarySortController {

    final IBinaryService binaryService;

    @PostMapping(value = "/binary")
    public List<Integer> binarySorter(@RequestBody List<Integer> numberList) {
        log.info("List of numbers received to be sorted: {}", numberList);

        return binaryService.binarySorterLogic(numberList);
    }

}
