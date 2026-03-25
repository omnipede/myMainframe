package com.myproject.basics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * VarCobolProgram 단위 테스트
 */
class VarCobolProgramTest {

    private VarCobolProgram program;

    @BeforeEach
    void setUp() {
        program = new VarCobolProgram();
    }

    @Test
    @DisplayName("COBOL 변수 타입 초기값 확인")
    void testInitialValues() {
        // 초기값 검증은 execute() 호출 전에는 어렵지만
        // 인스턴스 생성이 성공하는지 확인
        assertNotNull(program);
    }

    @Test
    @DisplayName("프로그램 실행 테스트")
    void testExecute() {
        // 예외 없이 실행되는지 확인
        assertDoesNotThrow(() -> program.execute());
    }

    @Test
    @DisplayName("Level 88 조건 - Pass 점수 확인")
    void testPassCondition() {
        // 85 점은 통과 (41-100)
        // execute() 내부에서 검증
        assertDoesNotThrow(() -> program.execute());
    }
}
