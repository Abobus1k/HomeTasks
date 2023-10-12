package edu.hw2.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    @Test
    void testConstantEvaluate() {
        Constant constant = new Constant(1);

        double res = constant.evaluate();

        Assertions.assertEquals(1,res);
    }

    @Test
    void testNegateEvaluate() {
        Constant constant = new Constant(1);

        var negOne = new Negate(constant);
        double res = negOne.evaluate();


        Assertions.assertEquals(-1,res);
    }

    @Test
    void testAdditionEvaluate() {
        Constant firstConstant = new Constant(1);
        Constant secondConstant = new Constant(2);

        var sum = new Addition(firstConstant, secondConstant);
        double res = sum.evaluate();

        Assertions.assertEquals(3,res);
    }

    @Test
    void testMultiplicationEvaluate() {
        Constant firstConstant = new Constant(1);
        Constant secondConstant = new Constant(2);

        var multi = new Multiplication(firstConstant, secondConstant);
        double res = multi.evaluate();

        Assertions.assertEquals(2,res);
    }

    @Test
    void testExponentEvaluate() {
        Constant constant = new Constant(2);

        var exp = new Exponent(constant, 3);
        double res = exp.evaluate();

        Assertions.assertEquals(8,res);
    }
}
