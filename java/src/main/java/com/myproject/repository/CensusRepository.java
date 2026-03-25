package com.myproject.repository;

import com.myproject.entity.Census;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * CENSUS 테이블 Spring Data JPA Repository
 * 
 * Original: Cobol/db2oper/db2oper.cbl 의 DB2 연산
 * 
 * @author Phani (original COBOL)
 */
@Repository
public interface CensusRepository extends JpaRepository<Census, String> {

    /**
     * 인구순으로 정렬하여 첫 번째 지역 반환
     * 
     * Original SQL:
     * SELECT STATE_UT INTO :STATE-UT FROM CENSUS
     * ORDER BY POPULATION DESC
     * FETCH FIRST ROW ONLY
     */
    @Query("SELECT c FROM Census c ORDER BY c.population DESC")
    Census findTopByOrderByPopulationDesc();

    /**
     * 특정 연도의 데이터 검색
     */
    List<Census> findByYearOfSur(Integer yearOfSur);

    /**
     * 연도별 데이터 스트리밍 (커서 처리)
     * 
     * Original: DB2 CURSOR 처리
     */
    @Query("SELECT c FROM Census c")
    Stream<Census> findAllStream();

    /**
     * 주어진 연도 업데이트
     * 
     * Original SQL:
     * UPDATE CENSUS SET YEAR_OF_SUR = :newYear
     * WHERE YEAR_OF_SUR = :oldYear
     */
    @Modifying
    @Transactional
    @Query("UPDATE Census c SET c.yearOfSur = :newYear WHERE c.yearOfSur = :oldYear")
    int updateYearOfSur(@Param("oldYear") Integer oldYear, @Param("newYear") Integer newYear);

    /**
     * 주어진 지역의 연도 업데이트
     */
    @Modifying
    @Transactional
    @Query("UPDATE Census c SET c.yearOfSur = :year WHERE c.stateUt = :stateUt")
    int updateYearOfSurByState(@Param("stateUt") String stateUt, @Param("year") Integer year);
}
