package com.myproject.db2oper;

import com.myproject.entity.Census;
import com.myproject.repository.CensusRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * COBOL db2curs.cbl 포팅 - DB2 CURSOR 처리
 * 
 * Original: Cobol/db2oper/db2curs.cbl
 * 
 * Original COBOL:
 * EXEC SQL
 *     DECLARE CURS_CE CURSOR FOR
 *     SELECT STATE_UT, POPULATION FROM CENSUS
 * END-EXEC
 * OPEN CURS_CE
 * FETCH CURS_CE INTO :STATE-UT, :POPULATION
 * CLOSE CURS_CE
 * 
 * Java 에서는 JPA Stream API 로 커서 처리 모방
 * 
 * @author Phani (original COBOL)
 */
@Component
public class Db2CursProgram {

    private final CensusRepository censusRepository;

    public Db2CursProgram(CensusRepository censusRepository) {
        this.censusRepository = censusRepository;
    }

    /**
     * 메인 실행 메서드
     */
    public void execute() {
        System.out.println("* DB2 CURSOR PROGRAM *");
        System.out.println("(DB2 CURSOR → JPA Stream porting)");
        System.out.println("-".repeat(40));

        // CURSOR 를 사용한 데이터 조회 및 출력
        fetchAndDisplayAll();

        System.out.println("\n* DB2 CURSOR PROGRAM COMPLETED *");
    }

    /**
     * SELECT ALL RECORDS USING CURSOR AND DISPLAY REPORT
     * 
     * Original COBOL:
     * EXEC SQL OPEN CURS_CE END-EXEC
     * PERFORM 000-FETCH-PARA UNTIL SQLCODE = 100
     * EXEC SQL FETCH CURS_CE INTO :STATE-UT, :POPULATION END-EXEC
     * DISPLAY 'STATE ', STATE-UT, ' HAS ', POPULATION
     * EXEC SQL CLOSE CURS_CE END-EXEC
     * 
     * Java 에서는 List 로 모든 데이터 조회 (트랜잭션 처리)
     */
    @Transactional(readOnly = true)
    private void fetchAndDisplayAll() {
        System.out.println("\n[CURSOR FETCH] STATE AND POPULATION REPORT");
        System.out.println("-".repeat(50));
        
        try {
            List<Census> censusList = censusRepository.findAll();
            for (Census census : censusList) {
                System.out.println("STATE " + census.getStateUt() + 
                                   " HAS " + census.getPopulation());
            }
        } catch (Exception e) {
            System.out.println("ERROR/CURSOR FETCH");
            System.out.println("SQL CODE: " + e.getMessage());
        }
        
        System.out.println("-".repeat(50));
    }
}
