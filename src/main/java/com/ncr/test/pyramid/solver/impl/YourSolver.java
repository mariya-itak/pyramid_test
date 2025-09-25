package com.ncr.test.pyramid.solver.impl;

import com.ncr.test.pyramid.data.Pyramid;
import com.ncr.test.pyramid.solver.PyramidSolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * TASK: This is your 3rd task.
 * Please implement the class to satisfy the interface. *
 */
public class YourSolver implements PyramidSolver {

    @Override
    public long pyramidMaximumTotal(Pyramid pyramid) {
        int[][] items = pyramid.getData();
        int rowsCount = pyramid.getRows();
        // start from the top
        int[] pathSums = items[0];

        for (int i = 1; i < rowsCount; i++) {
            for (int j = 0; j < rowsCount-1; j++) {
                if (items[i][j] != 0 /*no need to count zero values*/) {
                    // move from left- and right- child to the parent, choose max from children, sum with the parent
                    // and update existing sum
                    pathSums[j] = items[i][j] + Math.max(pathSums[j], pathSums[j+1]);
                }
            }
        }

        return Arrays.stream(pathSums).max().getAsInt();
    }
    
}
