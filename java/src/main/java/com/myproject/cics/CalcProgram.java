package com.myproject.cics;

import org.springframework.stereotype.Component;

/**
 * COBOL cics/calc.cbl 포팅 - CICS 계산기 프로그램
 * 
 * Original: Cobol/cics/calc.cbl
 * 
 * 참고: CICS 트랜잭션 처리 (SEND MAP, RECEIVE MAP) 는 제거하고
 * 비즈니스 로직 (사칙연산 계산) 만 포팅
 * 
 * Original COBOL 은 CICS BMS 맵을 사용하여 화면 입출력 처리:
 * - EXEC CICS SEND MAP
 * - EXEC CICS RECEIVE MAP
 * - EXEC CICS RETURN
 * 
 * @author Phani (original COBOL)
 */
@Component
public class CalcProgram {

    /**
     * 계산 결과 DTO
     */
    public static class CalcResult {
        public long addition;       // 덧셈 결과
        public long subtraction;    // 뺄셈 결과
        public long multiplication; // 곱셈 결과
        public long division;       // 나눗셈 결과

        public CalcResult(long n1, long n2) {
            this.addition = n1 + n2;
            this.subtraction = n1 - n2;
            this.multiplication = n1 * n2;
            this.division = (n2 != 0) ? n1 / n2 : 0;
        }

        @Override
        public String toString() {
            return String.format(
                "Addition: %d, Subtraction: %d, Multiplication: %d, Division: %d",
                addition, subtraction, multiplication, division
            );
        }
    }

    /**
     * 메인 실행 메서드
     */
    public void execute() {
        System.out.println("* CICS CALCULATOR PROGRAM *");
        System.out.println("(CICS MAP handling removed - Business logic only)");
        System.out.println("-".repeat(40));

        // 테스트 데이터 (원본 COBOL 의 MAPCAL 입력 데이터 모방)
        long num1 = 100;
        long num2 = 25;

        System.out.println("Input: NUM1 = " + num1 + ", NUM2 = " + num2);

        // 계산 수행
        CalcResult result = calculate(num1, num2);

        // 결과 출력 (CICS SEND MAP 대신 콘솔 출력)
        System.out.println("Results:");
        System.out.println("  ADDITION:     " + result.addition);
        System.out.println("  SUBTRACTION:  " + result.subtraction);
        System.out.println("  MULTIPLY:     " + result.multiplication);
        System.out.println("  DIVISION:     " + result.division);
    }

    /**
     * 사칙연산 계산 수행
     * 
     * Original COBOL:
     * - ADD NUMONEI TO NUMTWOI GIVING WS-AD
     * - SUBTRACT NUMTWOI FROM NUMONEI GIVING WS-SU
     * - MULTIPLY NUMONEI BY NUMTWOI GIVING WS-MU
     * - DIVIDE NUMONEI INTO NUMTWOI GIVING WS-DI
     * 
     * @param num1 첫 번째 숫자
     * @param num2 두 번째 숫자
     * @return 계산 결과
     */
    public CalcResult calculate(long num1, long num2) {
        return new CalcResult(num1, num2);
    }

    /**
     * 덧셈 연산
     */
    public long add(long num1, long num2) {
        return num1 + num2;
    }

    /**
     * 뺄셈 연산
     */
    public long subtract(long num1, long num2) {
        return num1 - num2;
    }

    /**
     * 곱셈 연산
     */
    public long multiply(long num1, long num2) {
        return num1 * num2;
    }

    /**
     * 나눗셈 연산
     */
    public long divide(long num1, long num2) {
        if (num2 == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return num1 / num2;
    }
}
