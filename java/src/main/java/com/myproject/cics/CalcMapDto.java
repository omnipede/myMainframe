package com.myproject.cics;

/**
 * COBOL cics/calc.cpybk 포팅 - CICS BMS 맵 데이터 구조
 * 
 * Original: Cobol/cics/calc.cpybk
 * 
 * CICS BMS (Basic Mapping Support) 맵의 입력/출력 구조체 정의
 * Java 에서는 DTO(Data Transfer Object) 로 변환
 * 
 * Original COBOL:
 * 01  MAPCALI.  (Input Map)
 *     02  FILLER PIC X(12).
 *     02  NUMONEL    COMP  PIC  S9(4).
 *     02  NUMONEF    PICTURE X.
 *     ...
 * 01  MAPCALO REDEFINES MAPCALI.  (Output Map)
 * 
 * @author Phani (original COBOL)
 */
public class CalcMapDto {

    // Input Map fields (MAPCALI)
    private short numOneL;      // NUMONEL COMP PIC S9(4)
    private char numOneF;       // NUMONEF PICTURE X
    private char numOneA;       // NUMONEA PICTURE X (REDEFINES)
    private int numOneI;        // NUMONEI PIC 9(5)
    
    private short numTwoL;      // NUMTWOL COMP PIC S9(4)
    private char numTwoF;       // NUMTWOF PICTURE X
    private char numTwoA;       // NUMTWOA PICTURE X (REDEFINES)
    private int numTwoI;        // NUMTWOI PIC 9(5)
    
    // Output Map fields (MAPCALO)
    private char numOneC;       // NUMONEC PICTURE X
    private int numOneO;        // NUMONEO PIC 9(5)
    
    private char numTwoC;       // NUMTWOC PICTURE X
    private int numTwoO;        // NUMTWOO PIC 9(5)
    
    private char addresC;       // ADDRESC PICTURE X
    private long addresO;       // ADDRESO PIC 9(6)
    
    private char subresC;       // SUBRESC PICTURE X
    private long subresO;       // SUBRESO PIC 9(6)
    
    private char mulresC;       // MULRESC PICTURE X
    private long mulresO;       // MULRESO PIC 9(10)
    
    private char divresC;       // DIVRESC PICTURE X
    private int divresO;        // DIVRESO PIC 9(5)
    
    private char msgiC;         // MSGIC PICTURE X
    private String msgiO;       // MSGIO PIC X(30)
    
    private char lev1C;         // LEV1C PICTURE X
    private String lev1O;       // LEV1O PIC X(8)

    // Default constructor
    public CalcMapDto() {
    }

    // Getters and Setters
    public short getNumOneL() {
        return numOneL;
    }

    public void setNumOneL(short numOneL) {
        this.numOneL = numOneL;
    }

    public char getNumOneF() {
        return numOneF;
    }

    public void setNumOneF(char numOneF) {
        this.numOneF = numOneF;
    }

    public char getNumOneA() {
        return numOneA;
    }

    public void setNumOneA(char numOneA) {
        this.numOneA = numOneA;
    }

    public int getNumOneI() {
        return numOneI;
    }

    public void setNumOneI(int numOneI) {
        this.numOneI = numOneI;
    }

    public short getNumTwoL() {
        return numTwoL;
    }

    public void setNumTwoL(short numTwoL) {
        this.numTwoL = numTwoL;
    }

    public char getNumTwoF() {
        return numTwoF;
    }

    public void setNumTwoF(char numTwoF) {
        this.numTwoF = numTwoF;
    }

    public char getNumTwoA() {
        return numTwoA;
    }

    public void setNumTwoA(char numTwoA) {
        this.numTwoA = numTwoA;
    }

    public int getNumTwoI() {
        return numTwoI;
    }

    public void setNumTwoI(int numTwoI) {
        this.numTwoI = numTwoI;
    }

    public char getNumOneC() {
        return numOneC;
    }

    public void setNumOneC(char numOneC) {
        this.numOneC = numOneC;
    }

    public int getNumOneO() {
        return numOneO;
    }

    public void setNumOneO(int numOneO) {
        this.numOneO = numOneO;
    }

    public char getNumTwoC() {
        return numTwoC;
    }

    public void setNumTwoC(char numTwoC) {
        this.numTwoC = numTwoC;
    }

    public int getNumTwoO() {
        return numTwoO;
    }

    public void setNumTwoO(int numTwoO) {
        this.numTwoO = numTwoO;
    }

    public char getAddresC() {
        return addresC;
    }

    public void setAddresC(char addresC) {
        this.addresC = addresC;
    }

    public long getAddresO() {
        return addresO;
    }

    public void setAddresO(long addresO) {
        this.addresO = addresO;
    }

    public char getSubresC() {
        return subresC;
    }

    public void setSubresC(char subresC) {
        this.subresC = subresC;
    }

    public long getSubresO() {
        return subresO;
    }

    public void setSubresO(long subresO) {
        this.subresO = subresO;
    }

    public char getMulresC() {
        return mulresC;
    }

    public void setMulresC(char mulresC) {
        this.mulresC = mulresC;
    }

    public long getMulresO() {
        return mulresO;
    }

    public void setMulresO(long mulresO) {
        this.mulresO = mulresO;
    }

    public char getDivresC() {
        return divresC;
    }

    public void setDivresC(char divresC) {
        this.divresC = divresC;
    }

    public int getDivresO() {
        return divresO;
    }

    public void setDivresO(int divresO) {
        this.divresO = divresO;
    }

    public char getMsgiC() {
        return msgiC;
    }

    public void setMsgiC(char msgiC) {
        this.msgiC = msgiC;
    }

    public String getMsgiO() {
        return msgiO;
    }

    public void setMsgiO(String msgiO) {
        this.msgiO = msgiO;
    }

    public char getLev1C() {
        return lev1C;
    }

    public void setLev1C(char lev1C) {
        this.lev1C = lev1C;
    }

    public String getLev1O() {
        return lev1O;
    }

    public void setLev1O(String lev1O) {
        this.lev1O = lev1O;
    }

    @Override
    public String toString() {
        return "CalcMapDto{" +
                "numOneI=" + numOneI +
                ", numTwoI=" + numTwoI +
                ", addresO=" + addresO +
                ", subresO=" + subresO +
                ", mulresO=" + mulresO +
                ", divresO=" + divresO +
                ", msgiO='" + msgiO + '\'' +
                '}';
    }
}
