package com.myproject.entity;

import jakarta.persistence.*;

/**
 * CENSUS 테이블 JPA 엔티티
 * 
 * Original: Cobol/db2oper/db2oper.cbl 의 CENSUS 테이블
 * 
 * Original COBOL 구조:
 * 01  DCLCENSUS.
 *     10 STATE-UT             PIC X(30).
 *     10 POPULATION           PIC S9(9) USAGE COMP.
 *     10 GROWTH-PER           PIC S9(9) USAGE COMP.
 *     10 RURAL-POP            PIC S9(9) USAGE COMP.
 *     10 URBAN-POP            PIC S9(9) USAGE COMP.
 *     10 YEAR-OF-SUR          PIC S9(9) USAGE COMP.
 *     10 RATIO                PIC S9(9) USAGE COMP.
 * 
 * @author Phani (original COBOL)
 */
@Entity
@Table(name = "CENSUS")
public class Census {

    @Id
    @Column(name = "STATE_UT", length = 30, nullable = false)
    private String stateUt;

    @Column(name = "POPULATION")
    private Integer population;

    @Column(name = "GROWTH_PER")
    private Integer growthPer;

    @Column(name = "RURAL_POP")
    private Integer ruralPop;

    @Column(name = "URBAN_POP")
    private Integer urbanPop;

    @Column(name = "YEAR_OF_SUR")
    private Integer yearOfSur;

    @Column(name = "RATIO")
    private Integer ratio;

    // Default constructor
    public Census() {
    }

    // All-args constructor
    public Census(String stateUt, Integer population, Integer growthPer, 
                  Integer ruralPop, Integer urbanPop, Integer yearOfSur, Integer ratio) {
        this.stateUt = stateUt;
        this.population = population;
        this.growthPer = growthPer;
        this.ruralPop = ruralPop;
        this.urbanPop = urbanPop;
        this.yearOfSur = yearOfSur;
        this.ratio = ratio;
    }

    // Getters and Setters
    public String getStateUt() {
        return stateUt;
    }

    public void setStateUt(String stateUt) {
        this.stateUt = stateUt;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Integer getGrowthPer() {
        return growthPer;
    }

    public void setGrowthPer(Integer growthPer) {
        this.growthPer = growthPer;
    }

    public Integer getRuralPop() {
        return ruralPop;
    }

    public void setRuralPop(Integer ruralPop) {
        this.ruralPop = ruralPop;
    }

    public Integer getUrbanPop() {
        return urbanPop;
    }

    public void setUrbanPop(Integer urbanPop) {
        this.urbanPop = urbanPop;
    }

    public Integer getYearOfSur() {
        return yearOfSur;
    }

    public void setYearOfSur(Integer yearOfSur) {
        this.yearOfSur = yearOfSur;
    }

    public Integer getRatio() {
        return ratio;
    }

    public void setRatio(Integer ratio) {
        this.ratio = ratio;
    }

    @Override
    public String toString() {
        return "Census{" +
                "stateUt='" + stateUt + '\'' +
                ", population=" + population +
                ", growthPer=" + growthPer +
                ", ruralPop=" + ruralPop +
                ", urbanPop=" + urbanPop +
                ", yearOfSur=" + yearOfSur +
                ", ratio=" + ratio +
                '}';
    }
}
