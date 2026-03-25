package com.myproject.basics;

import org.springframework.stereotype.Component;

/**
 * COBOL SpecialNames.cob 포팅 - 사용자 정의 클래스를 통한 유효성 검사
 * 
 * Original: Cobol/SpecialNames.cob
 * 
 * COBOL SPECIAL-NAMES 절의 CLASS 정의를 Java 로 포팅
 * 
 * Original COBOL:
 * SPECIAL-NAMES.
 *     CLASS WS-VALID-ALPHA IS
 *         '0' THRU '9'
 *         'A' THRU 'Z'
 *         '@' '#' '*'
 * 
 * @author Phani (original COBOL)
 */
@Component
public class SpecialNamesProgram {

    /**
     * 유효한 문자 클래스 (COBOL CLASS 정의 모방)
     * - 숫자: '0' ~ '9'
     * - 알파벳: 'A' ~ 'Z'
     * - 특수문자: '@', '#', '*'
     */
    private static final String VALID_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ@#*";

    /**
     * 메인 실행 메서드
     */
    public void execute() {
        System.out.println("* SPECIAL-NAMES CLASS VALIDATION *");
        System.out.println("Valid characters: " + VALID_CHARS);
        System.out.println("-".repeat(40));

        // 테스트 데이터 (원본 COBOL 과 동일)
        String[] testData = {
            "ABJKST01",  // VALID
            "CDLMUV23",  // VALID
            "EFNOWX45",  // VALID
            "GHPQYZ67",  // VALID
            "IJRSAB89",  // VALID
            "KLTUCD@#",  // VALID
            "MNVWEF#*",  // VALID
            "@#*@#*@#",  // VALID
            "IM SPACE",  // INVALID (공백 포함)
            "PERCENT%",  // INVALID (% 포함)
        };

        for (String data : testData) {
            validateCusip(data);
        }
    }

    /**
     * CUSIP 유효성 검사 (원본 COBOL 의 VALIDATE-CUSIP 단락)
     * 
     * @param input 검사할 문자열
     */
    private void validateCusip(String input) {
        if (isValidAlpha(input)) {
            System.out.println("WS-INP >" + input + "< IS VALID.");
        } else {
            System.out.println("WS-INP >" + input + "< IS NOT VALID.");
        }
    }

    /**
     * 사용자 정의 클래스 WS-VALID-ALPHA 유효성 검사
     * 
     * @param input 검사할 문자열
     * @return 유효하면 true, 아니면 false
     */
    private boolean isValidAlpha(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }

        // 대문자로 변환 (COBOL 은 대소문자 구분 없음)
        String upperInput = input.toUpperCase();

        // 모든 문자가 유효한지 확인
        for (int i = 0; i < upperInput.length(); i++) {
            char c = upperInput.charAt(i);
            if (VALID_CHARS.indexOf(c) == -1) {
                return false;
            }
        }
        return true;
    }
}
