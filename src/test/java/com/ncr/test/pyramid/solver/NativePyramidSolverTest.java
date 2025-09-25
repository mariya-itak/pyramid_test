package com.ncr.test.pyramid.solver;

import com.ncr.test.pyramid.data.Pyramid;
import com.ncr.test.pyramid.data.PyramidGenerator;
import com.ncr.test.pyramid.data.impl.RandomPyramidGenerator;
import com.ncr.test.pyramid.solver.impl.NaivePyramidSolver;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NativePyramidSolverTest {

    protected PyramidSolver solver = new NaivePyramidSolver();

    @Test
    public void solverHandlesSingleRow() {
        int[][] data = { { 42 } };
        Pyramid pyramid = new Pyramid(data);
        assertEquals(42, solver.pyramidMaximumTotal(pyramid));
    }

    @Test
    public void solverFindsNonStraightPath() {
        int[][] data = {
                { 1, 2, 3 },
                { 4, 5, 0 },
                { 9, 0, 0 }
        };
        Pyramid pyramid = new Pyramid(data);
        assertEquals(17, solver.pyramidMaximumTotal(pyramid));
    }

    @Test
    public void solverHandlesNegativeValues() {
        int[][] data = {
                { -1, -5, -1 },
                { -2, -3,  0 },
                { -5,   0,  0 }
        };
        Pyramid pyramid = new Pyramid(data);
        assertEquals(-8, solver.pyramidMaximumTotal(pyramid));
    }

    @Test
    public void solverHandlesZeros() {
        int[][] data = {
                { 0, 0, 0 },
                { 0, 5, 0 },
                { 9, 0, 0 }
        };
        Pyramid pyramid = new Pyramid(data);
        assertEquals(14, solver.pyramidMaximumTotal(pyramid));
    }

    @Test
    public void solverHandlesRandomData() {
        RandomPyramidGenerator.setRandSeed(25321L);
        final PyramidGenerator generator = new RandomPyramidGenerator(5, 99);
        final Pyramid pyramid = generator.generatePyramid();

        assertEquals(398, this.solver.pyramidMaximumTotal(pyramid));
    }

    @Test
    public void solverSurvivesLargeData() {
        // recursive function using is not suitable for large datasets
        PyramidGenerator generator = new RandomPyramidGenerator(20, 1000);
        Pyramid pyramid = generator.generatePyramid();
        assertTrue(solver.pyramidMaximumTotal(pyramid) > 0L);
    }
}
