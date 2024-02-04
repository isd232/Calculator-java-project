package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExpressionEvaluatorTest {

    private final ExpressionEvaluator evaluator = new ExpressionEvaluator();

    @Test
    void testSimpleAddition() {
        assertEquals(7, evaluator.evaluate("3 + 4"), 0.001);
    }

    @Test
    void testComplexExpression() {
        assertEquals(14, evaluator.evaluate("(3 + 4) * 2"), 0.001);
    }

    @Test
    void testDivision() {
        assertEquals(2, evaluator.evaluate("8 / 4"), 0.001);
    }

    @Test
    void testSubtraction() {
        assertEquals(1, evaluator.evaluate("5 - 4"), 0.001);
    }

    @Test
    void testMultiplicationAndDivision() {
        assertEquals(3, evaluator.evaluate("6 / 2 * 1"), 0.001);
    }

    @Test
    void testPower() {
        assertEquals(16, evaluator.evaluate("2 ^ 4"), 0.001);
    }

}
