package com.myproject.cics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CalcProgram 단위 테스트
 */
class CalcProgramTest {

    private CalcProgram program;

    @BeforeEach
    void setUp() {
        program = new CalcProgram();
    }

    @Test
    @DisplayName("덧셈 연산 테스트")
    void testAdd() {
        assertEquals(125, program.add(100, 25));
        assertEquals(0, program.add(0, 0));
        assertEquals(-50, program.add(-25, -25));
    }

    @Test
    @DisplayName("뺄셈 연산 테스트")
    void testSubtract() {
        assertEquals(75, program.subtract(100, 25));
        assertEquals(0, program.subtract(50, 50));
        assertEquals(-50, program.subtract(-25, 25));
    }

    @Test
    @DisplayName("곱셈 연산 테스트")
    void testMultiply() {
        assertEquals(2500, program.multiply(100, 25));
        assertEquals(0, program.multiply(0, 100));
        assertEquals(-500, program.multiply(-20, 25));
    }

    @Test
    @DisplayName("나눗셈 연산 테스트")
    void testDivide() {
        assertEquals(4, program.divide(100, 25));
        assertEquals(0, program.divide(0, 25));
        assertEquals(-4, program.divide(-100, 25));
    }

    @Test
    @DisplayName("나눗셈 0 으로 나누기 예외")
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> program.divide(100, 0));
    }

    @Test
    @DisplayName("계산 결과 DTO 테스트")
    void testCalcResult() {
        CalcProgram.CalcResult result = new CalcProgram.CalcResult(10, 5);
        
        assertEquals(15, result.addition);
        assertEquals(5, result.subtraction);
        assertEquals(50, result.multiplication);
        assertEquals(2, result.division);
    }

    @Test
    @DisplayName("프로그램 실행 테스트")
    void testExecute() {
        assertDoesNotThrow(() -> program.execute());
    }
}
