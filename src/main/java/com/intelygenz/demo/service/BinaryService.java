package com.intelygenz.demo.service;

import com.intelygenz.demo.entity.Binary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class BinaryService implements IBinaryService {

    @Override
    public List<Integer> binarySorterLogic(List<Integer> numberList) {

        List<Binary> binaryList = listIntegerToListBinary(numberList);

        // Sort by number of '1s' and, if equal number, by smaller number
        Binary[] binaryArray = binaryList.toArray(new Binary[0]);
        mergeSort(binaryArray);

        return Arrays.stream(binaryArray).toList().stream().map(Binary::getActualNumber).toList();
    }

    private List<Binary> listIntegerToListBinary(List<Integer> numberList) {
        List<Binary> binaryList = new ArrayList<>();

        for (Integer number : numberList) {
            binaryList.add(new Binary(number));
        }

        return binaryList;
    }

    /**
     * Sorts the Binary Array by Binary's count of 1 from the binary number representation, if equal number, by smaller
     * number.
     * @param binaryArray Array of Binary elements
     */
    private static void mergeSort(Binary[] binaryArray) {
        int n = binaryArray.length;
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        Binary[] l = new Binary[mid];
        Binary[] r = new Binary[n - mid];

        System.arraycopy(binaryArray, 0, l, 0, mid);
        System.arraycopy(binaryArray, mid, r, 0, n - mid);

        mergeSort(l);
        mergeSort(r);

        merge(binaryArray, l, r, mid, n - mid);
    }

    public static void merge(Binary[] a, Binary[] l, Binary[] r, int left, int right) {

        Binary helpBinaryObject;
        int i = 0, j = 0, k = 0;

        // filter first by biggest count on '1s', if equal, by smaller number
        while (i < left && j < right) {
            if (l[i].getOnesCount() > r[j].getOnesCount()
                    || (l[i].getOnesCount().equals(r[j].getOnesCount())
                    && l[i].getActualNumber() < r[j].getActualNumber())) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }

}