package com.myproject.db2oper;

import com.myproject.entity.Census;
import com.myproject.repository.CensusRepository;
import org.springframework.stereotype.Component;

/**
 * COBOL db2oper.cbl 포팅 - DB2 CRUD 연산
 * 
 * Original: Cobol/db2oper/db2oper.cbl
 * 
 * 테스트 기능:
 * 1. SELECT - 인구순으로 정렬하여 첫 번째 지역 조회
 * 2. INSERT - WEST BENGAL 데이터 추가
 * 3. UPDATE - YEAR_OF_SUR 업데이트
 * 4. DELETE - 에러 처리 테스트 (NULL 값)
 * 
 * @author Phani (original COBOL)
 */
@Component
public class Db2OperProgram {

    private final CensusRepository censusRepository;

    public Db2OperProgram(CensusRepository censusRepository) {
        this.censusRepository = censusRepository;
    }

    /**
     * 메인 실행 메서드
     */
    public void execute() {
        System.out.println("* DB2 OPERATIONS PROGRAM *");
        System.out.println("(DB2 → JPA/Hibernate porting)");
        System.out.println("-".repeat(40));

        // 1. SELECT - Most populous state
        selectFirstByPopulation();

        // 2. INSERT - New record
        insertRecord();

        // 3. UPDATE - Update year
        updateRecord();

        // 4. UPDATE with NULL indicator - Error handling
        updateWithNullIndicator();

        System.out.println("\n* DB2 OPERATIONS COMPLETED *");
    }

    /**
     * SELECT FIRST ROW BY POPULATION
     * 
     * Original COBOL:
     * EXEC SQL
     *     SELECT STATE_UT INTO :STATE-UT FROM CENSUS
     *     ORDER BY POPULATION DESC
     *     FETCH FIRST ROW ONLY
     * END-EXEC
     */
    private void selectFirstByPopulation() {
        System.out.println("\n[1] SELECT - Most Populous State");
        try {
            Census census = censusRepository.findTopByOrderByPopulationDesc();
            
            if (census != null) {
                System.out.println("MOST POP STATE IS: " + census.getStateUt());
                System.out.println("  Population: " + census.getPopulation());
            } else {
                System.out.println("No records found");
            }
        } catch (Exception e) {
            System.out.println("ERROR/SELECT");
            System.out.println("SQL CODE: " + e.getMessage());
        }
    }

    /**
     * INSERT NEW RECORD
     * 
     * Original COBOL:
     * EXEC SQL
     *     INSERT INTO CENSUS VALUES(...)
     * END-EXEC
     */
    private void insertRecord() {
        System.out.println("\n[2] INSERT - New Record");
        try {
            Census census = new Census();
            census.setStateUt("WEST BENGAL");
            census.setPopulation(91347736);
            census.setGrowthPer(13);
            census.setRuralPop(62213676);
            census.setUrbanPop(2913460);
            census.setYearOfSur(2011);
            census.setRatio(947);

            censusRepository.save(census);
            System.out.println("RECORD INSERTED");
            System.out.println("  State: " + census.getStateUt());
        } catch (Exception e) {
            System.out.println("ERROR/INSERT");
            System.out.println("SQL CODE: " + e.getMessage());
            System.out.println("SQL STATE: " + e.getClass().getSimpleName());
        }
    }

    /**
     * UPDATE EXISTING RECORDS
     * 
     * Original COBOL:
     * EXEC SQL
     *     UPDATE CENSUS SET YEAR_OF_SUR = :YEAR-OF-SUR
     *     WHERE YEAR_OF_SUR = 2011
     * END-EXEC
     */
    private void updateRecord() {
        System.out.println("\n[3] UPDATE - Existing Records");
        try {
            int updated = censusRepository.updateYearOfSur(2011, 2016);
            System.out.println("RECORDS UPDATED: " + updated);
        } catch (Exception e) {
            System.out.println("ERROR/UPDATE");
            System.out.println("SQL CODE: " + e.getMessage());
            System.out.println("SQLSTATE: " + e.getClass().getSimpleName());
        }
    }

    /**
     * UPDATE with NULL indicator - Error handling
     * 
     * Original COBOL:
     * MOVE -1 TO NIND
     * MOVE LOW-VALUES TO STATE-UT
     * EXEC SQL
     *     UPDATE CENSUS SET STATE_UT = :STATE-UT:NIND
     *     WHERE YEAR_OF_SUR = 2016
     * END-EXEC
     */
    private void updateWithNullIndicator() {
        System.out.println("\n[4] UPDATE with NULL indicator");
        try {
            // COBOL 의 NIND = -1 (NULL indicator) 모방
            // Java 에서는 null 값으로 표현
            Census census = new Census();
            census.setStateUt(null);  // LOW-VALUES → null
            census.setYearOfSur(2016);

            // NOT NULL 제약조건 위반으로 에러 발생 예상
            censusRepository.save(census);
            System.out.println("RECORDS UPDATED");
        } catch (Exception e) {
            System.out.println("ERROR/UPDATE/NIND");
            System.out.println("SQL CODE: " + e.getMessage());
            System.out.println("SQLSTATE: " + e.getClass().getSimpleName());
        }
    }
}
