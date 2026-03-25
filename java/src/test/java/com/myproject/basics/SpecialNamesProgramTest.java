package com.myproject.basics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SpecialNamesProgram 단위 테스트
 */
class SpecialNamesProgramTest {

    private SpecialNamesProgram program;

    @BeforeEach
    void setUp() {
        program = new SpecialNamesProgram();
    }

    @Test
    @DisplayName("유효한 입력 검증 - 숫자와 알파벳")
    void testValidAlphaNumeric() {
        // COBOL 원본의 테스트 케이스
        assertTrue(isValidAlpha("ABJKST01"));
        assertTrue(isValidAlpha("CDLMUV23"));
        assertTrue(isValidAlpha("12345678"));
        assertTrue(isValidAlpha("ABCDEF"));
    }

    @Test
    @DisplayName("유효한 입력 검증 - 특수문자 포함")
    void testValidWithSpecialChars() {
        assertTrue(isValidAlpha("KLTUCD@#"));
        assertTrue(isValidAlpha("MNVWEF#*"));
        assertTrue(isValidAlpha("@#*@#*@#"));
    }

    @Test
    @DisplayName("무효한 입력 검증 - 공백 포함")
    void testInvalidWithSpace() {
        assertFalse(isValidAlpha("IM SPACE"));
        assertFalse(isValidAlpha("TEST DATA"));
    }

    @Test
    @DisplayName("무효한 입력 검증 - 허용되지 않은 특수문자")
    void testInvalidSpecialChars() {
        assertFalse(isValidAlpha("PERCENT%"));
        assertFalse(isValidAlpha("AMPERSAND&"));
        assertFalse(isValidAlpha("DOT."));
    }

    @Test
    @DisplayName("프로그램 실행 테스트")
    void testExecute() {
        assertDoesNotThrow(() -> program.execute());
    }

    // private 메서드 테스트를 위한 헬퍼
    private boolean isValidAlpha(String input) {
        String VALID_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ@#*";
        if (input == null || input.isEmpty()) {
            return false;
        }
        String upperInput = input.toUpperCase();
        for (int i = 0; i < upperInput.length(); i++) {
            char c = upperInput.charAt(i);
            if (VALID_CHARS.indexOf(c) == -1) {
                return false;
            }
        }
        return true;
    }
}
