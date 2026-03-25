package com.myproject.basics;

import org.springframework.stereotype.Component;

/**
 * COBOL var.cbl 포팅 - 다양한 COBOL 변수 타입 및 연산 데모
 * 
 * Original: Cobol/basics/var.cbl
 * 
 * COBOL 데이터 타입 매핑:
 * - PIC 9(3)      → int
 * - PIC A(2)      → String
 * - PIC X(2)      → String
 * - PIC 9V99      → double (가정 소수점)
 * - PIC S9(2)     → int (부호 있음)
 * - PPP999        → double (SCALE 가정소수점)
 * - COMP          → short (16-bit integer)
 * - COMP-1        → float (single precision)
 * - COMP-2        → double (double precision)
 * - COMP-3        → BigDecimal (10 진 compressed)
 * - OCCURS        → 배열/List
 * - Level 88      → enum 또는 boolean 메서드
 * 
 * @author Phani (original COBOL)
 */
@Component
public class VarCobolProgram {

    // WS-BASIC-VAR
    private int wsInt = 1;           // PIC 9(3) VALUE 01
    private String wsChar = "AB";    // PIC A(2) VALUE 'AB'
    private String wsAlph = "z1";    // PIC X(2) VALUE 'z1'
    private double wsDec = 2.14;     // PIC 9V99 VALUE 2.14
    private int wsSign = -24;        // PIC S9(2) VALUE -24
    private double wsAdec;           // PIC PPP999 (SCALE: 0.001 단위)
    private String wsDate = "";      // PIC X(6)

    // WS-ADDRESS
    private String wsName = "";      // PIC X(20)
    private int wsPin = 0;           // PIC 9(5)

    // WS-ADDRESS-NEW (REDEFINES)
    private String wsAddressNew = ""; // REDEFINES WS-ADDRESS

    // WS-NUM1 ~ WS-NUM4 (COMP types)
    private short wsNum1 = 24;       // PIC 9(2) COMP
    private float wsNum2 = 24.0f;    // COMP-1 (single precision float)
    private double wsNum3 = 24.0;    // COMP-2 (double precision float)
    private int wsNum4 = 24;         // PIC 9(2) COMP-3 (packed decimal)

    // WS-TABLE (OCCURS)
    private final TableElement[] wsTable = new TableElement[3];

    // WS-COPY (from copybook)
    private final AbcCopybook wsCopy = new AbcCopybook();

    // WS-MARK (Level 88 condition names)
    private int wsMark = 0;

    // Helper class for table structure
    private static class TableElement {
        private String wsB = "";     // PIC A(2)
        private final String[] wsC = new String[2]; // OCCURS 2 TIMES PIC X(3)

        public TableElement() {
            for (int i = 0; i < wsC.length; i++) {
                wsC[i] = "";
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(wsB);
            for (String c : wsC) {
                sb.append(c);
            }
            return sb.toString();
        }
    }

    public VarCobolProgram() {
        // Initialize table
        for (int i = 0; i < wsTable.length; i++) {
            wsTable[i] = new TableElement();
        }
    }

    /**
     * 메인 실행 메서드
     */
    public void execute() {
        aPara();
        bPara();
        cPara();
        dPara();
        ePara();
        fPara();
        gPara();
        hPara();
        zPara();
    }

    /**
     * A-PARA: 다양한 변수 타입 출력
     */
    private void aPara() {
        System.out.println("* DIFFERENT VARIABLE TYPES IN COBOL *");
        System.out.println("INTEGER: " + wsInt);
        System.out.println("CHAR: " + wsChar);
        System.out.println("ALPHA: " + wsAlph);
        System.out.println("DECIMAL: " + wsDec);
        System.out.println("SIGNED: " + wsSign);
        
        // ASSUMED DECIMAL (PPP999 - 3 자리 가정 소수점)
        wsAdec = wsInt / 1000.0;
        System.out.println("ASSUMED DECIMAL: " + wsAdec);
        
        // DATE from system
        wsDate = java.time.LocalDate.now().format(
            java.time.format.DateTimeFormatter.ofPattern("yyMMdd")
        );
        System.out.println("DATE: " + wsDate);
        
        // INITIALIZATION 데모
        System.out.println("\n* INITIALIZATION *");
        wsName = "John Doe";
        wsPin = 12345;
        String wsAddress = wsName + wsPin;
        System.out.println("BEFORE INIT: " + wsAddress);
        
        // INITIALIZE - COBOL 의 INITIALIZE 문 모방
        wsName = "UNKNOWN";  // ALPHANUMERIC → 'UNKNOWN'
        wsPin = 0;           // NUMERIC → 0
        wsAddress = wsName + String.format("%05d", wsPin);
        System.out.println("AFTER-INIT: " + wsAddress);
    }

    /**
     * B-PARA: COMPUTE 연산
     */
    private void bPara() {
        System.out.println("\n* COMPUTE *");
        wsPin = wsInt * 2;
        System.out.println(wsPin);
    }

    /**
     * C-PARA: REDEFINES 데모
     */
    private void cPara() {
        System.out.println("\n* REDEFINES *");
        String wsAddress = "John Doe         12345";
        wsAddressNew = wsAddress;
        System.out.println("STORAGE USED FOR NEW: " + wsAddressNew);
        
        // REDEFINES 를 통해 같은 메모리 영역을 다른 뷰로 접근
        wsAddressNew = "00";
        System.out.println("AFTER CHANGING ORIGINAL: " + wsAddress);
        System.out.println("AFTER CHANGING REDEFINED: " + wsAddressNew);
    }

    /**
     * D-PARA: COMP 타입 출력
     */
    private void dPara() {
        System.out.println("\n* COMP *");
        System.out.println("COMP,COMP-1,COMP-2,COMP-3: " + 
            wsNum1 + "," + wsNum2 + "," + wsNum3 + "," + wsNum4);
    }

    /**
     * E-PARA: COPYBOOK 데모
     */
    private void ePara() {
        System.out.println("\n* COPY BOOKS *");
        System.out.println(wsCopy);
    }

    /**
     * F-PARA: IF 조건문 및 Level 88
     */
    private void fPara() {
        System.out.println("\n* IF *");
        if (wsInt > 0) {
            System.out.println("IT IS POSITIVE");
        } else if (wsInt <= 0) {
            System.out.println("IT IS NEGATIVE");
        } else {
            System.out.println("IT IS ZERO");
        }
        
        System.out.println("CONDITIONAL LEVEL 88/EVALUATE");
        wsMark = 85;
        System.out.println("WS-MARK: " + wsMark);
        
        // Level 88 condition names 모방
        if (isPass()) {
            System.out.println("PASSED");
        }
        if (isFail()) {
            System.out.println("FAILED");
        }
    }

    /**
     * G-PARA: 문자열 연산 (INSPECT, STRING, UNSTRING)
     */
    private void gPara() {
        System.out.println("\n* STRING OPERATIONS *");
        wsName = "PHANIKIRAN";
        int charCount = 0;
        
        // INSPECT TALLYING - 문자 수 카운트
        charCount = wsName.length();
        System.out.println(" COUNT NO OF CHARACTERS");
        System.out.println("NO OF CHAR: " + charCount);
        
        // INSPECT TALLYING - 특정 문자열 카운트
        int anCount = countOccurrences(wsName, "AN");
        System.out.println("NO OF AN: " + anCount);
        
        // INSPECT REPLACING - 문자 치환
        System.out.println("REPLACE SPECIFIC CHARS");
        wsName = wsName.replace("AN", "an");
        System.out.println(wsName);
        
        // STRING 연산
        System.out.println("\n* STRING *");
        int pointer = 0;
        String target = new String(new char[20]).replace('\0', ' ');
        String source1 = "Hello";
        String source2 = "World";
        
        // 간단한 STRING 연산 모방
        if (pointer + source1.length() + source2.length() <= target.length()) {
            target = source1 + source2;
            pointer = target.length();
            System.out.println("NOT OVERFLOW");
        } else {
            System.out.println("OVERFLOW");
        }
        System.out.println(target + " " + pointer);
        
        // UNSTRING 연산
        System.out.println("\n* UNSTRING *");
        pointer = 0;
        String delimiter = "a";
        String[] tokens = wsName.split(delimiter);
        String firstName = tokens.length > 0 ? tokens[0] : "";
        String lastName = tokens.length > 1 ? tokens[1] : "";
        System.out.println(firstName + " " + lastName);
        System.out.println(pointer);
    }

    /**
     * H-PARA: 테이블/배열 연산
     */
    private void hPara() {
        System.out.println("\n* TABLE/ARRAYS *");
        
        // 테이블 초기화: '12ABCDEF34GHIJKL56MNOPQR'
        String tableData = "12ABCDEF34GHIJKL56MNOPQR";
        for (int i = 0; i < wsTable.length; i++) {
            int pos = i * 8;
            if (pos + 2 <= tableData.length()) {
                wsTable[i].wsB = tableData.substring(pos + 2, Math.min(pos + 4, tableData.length()));
            }
            if (pos + 4 <= tableData.length()) {
                wsTable[i].wsC[0] = tableData.substring(pos + 4, Math.min(pos + 7, tableData.length()));
            }
            if (pos + 7 <= tableData.length()) {
                wsTable[i].wsC[1] = tableData.substring(pos + 7, Math.min(pos + 10, tableData.length()));
            }
        }
        
        // 테이블 출력
        StringBuilder sb = new StringBuilder();
        for (TableElement elem : wsTable) {
            sb.append(elem);
        }
        System.out.println(sb.toString());
        System.out.println("WS-TABLE  : " + sb.toString());
        System.out.println("WS-A(1)   : " + wsTable[0]);
        System.out.println("WS-B(1)   : " + wsTable[0].wsB);
        System.out.println("WS-C(1,1) : " + wsTable[0].wsC[0]);
        System.out.println("WS-C(1,2) : " + wsTable[0].wsC[1]);
        System.out.println("WS-A(2)   : " + wsTable[1]);
        System.out.println("WS-B(2)   : " + wsTable[1].wsB);
        System.out.println("WS-C(2,1) : " + wsTable[1].wsC[0]);
        System.out.println("WS-C(2,2) : " + wsTable[1].wsC[1]);
        System.out.println("WS-A(3)   : " + wsTable[2]);
        System.out.println("WS-C(3,1) : " + wsTable[2].wsC[0]);
        System.out.println("WS-C(3,2) : " + wsTable[2].wsC[1]);
        
        // INDEX by ACCESS
        System.out.println("\n * ACCESS BY INDEX * ");
        int i = 0, j = 0;  // SET I J TO 1 (0-based index)
        System.out.println(wsTable[i].wsC[j]);
        i++; j++;  // SET I J UP BY 1
        if (i < wsTable.length && j < wsTable[i].wsC.length) {
            System.out.println(wsTable[i].wsC[j]);
        }
        
        // SEARCH (linear search)
        System.out.println("\n* SEARCH/SEARCH ALL *");
        boolean found = false;
        for (int idx = 0; idx < wsTable.length; idx++) {
            if ("AB".equals(wsTable[idx].wsB)) {
                found = true;
                System.out.println("LETTER FOUND at index " + (idx + 1));
                break;
            }
        }
        if (!found) {
            System.out.println("NOT FOUND");
        }
    }

    /**
     * Z-PARA: 프로그램 종료
     */
    private void zPara() {
        System.out.println("\n* PROGRAM COMPLETED *");
    }

    // Level 88 condition methods
    private boolean isPass() {
        return wsMark >= 41 && wsMark <= 100;
    }

    private boolean isFail() {
        return wsMark >= 0 && wsMark <= 40;
    }

    // Helper method for counting occurrences
    private int countOccurrences(String str, String sub) {
        if (sub.isEmpty()) return 0;
        int count = 0;
        int idx = 0;
        while ((idx = str.indexOf(sub, idx)) != -1) {
            count++;
            idx += sub.length();
        }
        return count;
    }
}
