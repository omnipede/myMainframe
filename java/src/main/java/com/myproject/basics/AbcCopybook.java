package com.myproject.basics;

/**
 * COBOL abc.cbl 카피북 포팅
 * 
 * Original: Cobol/basics/abc.cbl
 * 
 * COBOL COPYBOOK 은 Java 클래스로 변환하여 재사용
 * 
 * Original COBOL:
 * 01 WS-COPY.
 *  05 WS-COPY-1 PIC 9(2) VALUE 24.
 *  05 WS-COPY-2 PIC X(2) VALUE 'AB'.
 * 01 WS-NAMES.
 *  05 WS-FIRSTNAME PIC X(20) VALUE 'FIRST'.
 *  05 WS-LASTNAME PIC X(20) VALUE 'LAST'.
 * 
 * @author Phani (original COBOL)
 */
public class AbcCopybook {

    // WS-COPY 구조
    private int wsCopy1 = 24;        // PIC 9(2) VALUE 24
    private String wsCopy2 = "AB";   // PIC X(2) VALUE 'AB'

    // WS-NAMES 구조
    private String wsFirstname = "FIRST";  // PIC X(20) VALUE 'FIRST'
    private String wsLastname = "LAST";    // PIC X(20) VALUE 'LAST'

    // Default constructor
    public AbcCopybook() {
    }

    // Getters and Setters
    public int getWsCopy1() {
        return wsCopy1;
    }

    public void setWsCopy1(int wsCopy1) {
        this.wsCopy1 = wsCopy1;
    }

    public String getWsCopy2() {
        return wsCopy2;
    }

    public void setWsCopy2(String wsCopy2) {
        this.wsCopy2 = wsCopy2;
    }

    public String getWsFirstname() {
        return wsFirstname;
    }

    public void setWsFirstname(String wsFirstname) {
        this.wsFirstname = wsFirstname;
    }

    public String getWsLastname() {
        return wsLastname;
    }

    public void setWsLastname(String wsLastname) {
        this.wsLastname = wsLastname;
    }

    @Override
    public String toString() {
        return "WS-COPY-1: " + wsCopy1 + 
               ", WS-COPY-2: " + wsCopy2 +
               ", WS-FIRSTNAME: " + wsFirstname +
               ", WS-LASTNAME: " + wsLastname;
    }
}
