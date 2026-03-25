package com.myproject.fileop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * COBOL fileop/READPS.cob 포팅 - 순차 파일 읽기
 * 
 * Original: Cobol/fileop/READPS.cob
 * 
 * Original COBOL:
 * SELECT SAMPLE ASSIGN TO FILEDD1
 * ORGANIZATION IS SEQUENTIAL
 * FILE STATUS WS-IN1
 * OPEN INPUT SAMPLE
 * READ SAMPLE AT END MOVE 'Y' TO WS-EOF
 * 
 * Java 에서는 NIO 파일 읽기로 구현
 * 
 * @author Phani (original COBOL)
 */
@Component
public class ReadPsProgram {

    private static final Logger log = LoggerFactory.getLogger(ReadPsProgram.class);

    @Value("${app.data-file:src/main/resources/data/sample.txt}")
    private String dataFilePath;

    /**
     * 파일 레코드 구조 (COBOL SAMPLE-FILE 대응)
     */
    public static class FileRecord {
        public String data1;  // PIC A(20)
        public String data2;  // PIC A(30)
        public String filler; // PIC X(30)

        public FileRecord(String data1, String data2, String filler) {
            this.data1 = data1;
            this.data2 = data2;
            this.filler = filler;
        }

        @Override
        public String toString() {
            return String.format("%-20s %-30s %-30s", data1, data2, filler);
        }
    }

    /**
     * 메인 실행 메서드
     */
    public void execute() {
        System.out.println("* FILE READ PROGRAM (READPS) *");
        System.out.println("(COBOL Sequential File → Java NIO)");
        System.out.println("-".repeat(40));

        Path filePath = Paths.get(dataFilePath);
        
        if (!Files.exists(filePath)) {
            System.out.println("FILE NOT FOUND: " + dataFilePath);
            System.out.println("Creating sample data file...");
            createSampleData(filePath);
        }

        readFile(filePath);

        System.out.println("\n* FILE READ PROGRAM COMPLETED *");
    }

    /**
     * 000-OPEN-PARA: 파일 오픈
     */
    private void readFile(Path filePath) {
        String fileStatus = "00";
        boolean eof = false;

        System.out.println("IN OPEN PARA");
        
        try (BufferedReader reader = Files.newBufferedReader(filePath, Charset.defaultCharset())) {
            fileStatus = "00";
            System.out.println(fileStatus + " FILE STATUS");

            System.out.println("IN READ PARA");
            
            String line;
            while ((line = reader.readLine()) != null) {
                eof = false;
                
                // 레코드 파싱
                FileRecord record = parseRecord(line);
                
                // 002-ACTION-PARA: 데이터 처리
                System.out.println("IN ACTION PARA");
                System.out.println(record);
            }
            
            // AT END 처리
            eof = true;
            System.out.println("END OF FILE REACHED");
            
        } catch (IOException e) {
            fileStatus = "04";  // 파일 오류
            System.out.println(fileStatus + " FILE STATUS - ERROR");
            log.error("File read error: {}", e.getMessage());
        }

        // 003-CLOSE-PARA: 파일 클로즈 (try-with-resources 로 자동 처리)
        System.out.println("FILE CLOSED");
    }

    /**
     * 레코드 파싱 (COBOL SAMPLE-FILE 구조에 맞춤)
     *
     * Original COBOL:
     * 01 SAMPLE-FILE.
     *    05 SAMPLE-DATA1 PIC A(20).
     *    05 SAMPLE-DATA2 PIC A(30).
     *    05 FILLER PIC X(30).
     *    
     * @param line 파싱할 라인
     * @return FileRecord 객체
     */
    public FileRecord parseRecord(String line) {
        String data1 = "";
        String data2 = "";
        String filler = "";

        if (line.length() >= 20) {
            data1 = line.substring(0, Math.min(20, line.length()));
        }
        if (line.length() >= 50) {
            data2 = line.substring(20, Math.min(50, line.length()));
        }
        if (line.length() >= 80) {
            filler = line.substring(50, Math.min(80, line.length()));
        } else if (line.length() > 50) {
            filler = line.substring(50);
        }

        return new FileRecord(data1, data2, filler);
    }

    /**
     * 샘플 데이터 파일 생성
     */
    private void createSampleData(Path filePath) {
        try {
            // 디렉토리 생성
            Files.createDirectories(filePath.getParent());

            // 샘플 데이터 작성 (COBOL 레코드 구조에 맞춤)
            String[] records = {
                formatRecord("SAMPLE-DATA-001", "This is sample data number one", "Extra filler text"),
                formatRecord("SAMPLE-DATA-002", "This is sample data number two", "More filler text"),
                formatRecord("SAMPLE-DATA-003", "This is sample data number three", "Additional filler"),
                formatRecord("SAMPLE-DATA-004", "This is sample data number four", "Even more filler"),
                formatRecord("SAMPLE-DATA-005", "This is sample data number five", "Last filler text")
            };

            Files.write(filePath, java.util.Arrays.asList(records), Charset.defaultCharset());
            System.out.println("Sample data file created: " + filePath);

        } catch (IOException e) {
            log.error("Failed to create sample data: {}", e.getMessage());
        }
    }

    /**
     * 레코드 포맷팅 (80 바이트 고정 길이)
     */
    private String formatRecord(String data1, String data2, String filler) {
        return String.format("%-20s %-30s %-30s", 
            padRight(data1, 20), 
            padRight(data2, 30), 
            padRight(filler, 30));
    }

    /**
     * 오른쪽 패딩
     */
    private String padRight(String s, int n) {
        if (s == null) s = "";
        return String.format("%-" + n + "s", s.substring(0, Math.min(s.length(), n)));
    }
}
