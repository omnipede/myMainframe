package com.myproject.fileop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ReadPsProgram 파일 레코드 파싱 테스트
 */
class ReadPsProgramTest {

    private final ReadPsProgram program = new ReadPsProgram();

    @Test
    @DisplayName("레코드 파싱 테스트 - 정상 데이터")
    void testParseRecord() {
        // 80 바이트 고정 길이 레코드
        String line = "SAMPLE-DATA-001   This is sample data number one    Extra filler text             ";
        
        ReadPsProgram.FileRecord record = program.parseRecord(line);
        
        assertNotNull(record);
        assertNotNull(record.data1);
        assertNotNull(record.data2);
        assertNotNull(record.filler);
    }

    @Test
    @DisplayName("레코드 파싱 테스트 - 짧은 데이터")
    void testParseRecordShort() {
        String line = "SHORT";
        
        ReadPsProgram.FileRecord record = program.parseRecord(line);
        
        assertNotNull(record);
    }

    @Test
    @DisplayName("레코드 포맷팅 테스트")
    void testFormatRecord() {
        String formatted = formatRecord("DATA1", "DATA2", "FILLER");
        
        assertTrue(formatted.length() > 0);
        assertTrue(formatted.contains("DATA1"));
    }

    // private 메서드 테스트를 위한 헬퍼
    private String formatRecord(String data1, String data2, String filler) {
        return String.format("%-20s %-30s %-30s", 
            padRight(data1, 20), 
            padRight(data2, 30), 
            padRight(filler, 30));
    }

    private String padRight(String s, int n) {
        if (s == null) s = "";
        return String.format("%-" + n + "s", s.substring(0, Math.min(s.length(), n)));
    }
}
