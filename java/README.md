# myMainframe Java Porting Project

메인프레임 COBOL 프로그램을 Java (Spring Boot) 로 포팅한 프로젝트입니다.

## 📋 프로젝트 개요

이 프로젝트는 레거시 메인프레임 COBOL 프로그램을 현대적인 Java 애플리케이션으로 포팅하는 것을 목표로 합니다.

### 원본 프로젝트
- **위치**: `/Cobol/`, `/JCL/`, `/DB2/`, `/PL1/`, `/REXX/`
- **기술**: COBOL, JCL, DB2, CICS, PL1, REXX

### 포팅 결과
- **위치**: `/java/`
- **기술**: Java 17, Spring Boot 3.x, Spring Data JPA, H2 Database

---

## 📁 프로젝트 구조

```
java/
├── src/main/java/com/myproject/
│   ├── CobolBasicApplication.java    # Spring Boot 메인 클래스
│   ├── basics/
│   │   ├── VarCobolProgram.java      # var.cbl 포팅
│   │   ├── AbcCopybook.java          # abc.cbl 카피북
│   │   └── SpecialNamesProgram.java  # SpecialNames.cob 포팅
│   ├── cics/
│   │   ├── CalcProgram.java          # calc.cbl 포팅
│   │   └── CalcMapDto.java           # BMS 맵 DTO
│   ├── db2oper/
│   │   ├── Db2OperProgram.java       # db2oper.cbl 포팅
│   │   └── Db2CursProgram.java       # db2curs.cbl 포팅
│   ├── entity/
│   │   └── Census.java               # CENSUS 테이블 엔티티
│   ├── repository/
│   │   └── CensusRepository.java     # Spring Data JPA
│   └── fileop/
│       └── ReadPsProgram.java        # READPS.cob 포팅
├── src/main/resources/
│   ├── application.yml               # Spring 설정
│   ├── data.sql                      # DB 샘플 데이터
│   └── data/
│       └── sample.txt                # 파일 입출력 샘플
└── pom.xml                           # Maven 설정
```

---

## 🔧 기술 스택

| 항목 | 기술 |
|------|------|
| **언어** | Java 17 |
| **프레임워크** | Spring Boot 3.2.0 |
| **ORM** | Spring Data JPA (Hibernate) |
| **데이터베이스** | H2 (임베디드) |
| **빌드 도구** | Maven |

---

## 🚀 실행 방법

### 1. 사전 요구사항 확인

```bash
# Java 버전 확인 (17 이상 필요)
java -version

# Maven 버전 확인
mvn -version
```

> **참고**: Java 17 이 설치되어 있지 않은 경우, Homebrew 로 설치 가능합니다.
> ```bash
> brew install openjdk@17
> ```

---

### 2. 프로젝트 빌드

```bash
# 프로젝트 디렉토리로 이동
cd /path/to/myMainframe/java

# Maven 으로 클린 빌드
mvn clean compile
```

빌드 성공 시 `BUILD SUCCESS` 메시지가 표시됩니다.

---

### 3. 애플리케이션 실행

#### 방법 A: Maven 으로 실행 (권장)

```bash
mvn spring-boot:run
```

#### 방법 B: JAR 파일로 실행

```bash
# 1. JAR 패키지 생성
mvn clean package -DskipTests

# 2. JAR 실행
java -jar target/myMainframe-java-1.0.0.jar
```

#### 방법 C: IDE 에서 실행

1. IntelliJ IDEA 또는 Eclipse 에서 프로젝트 열기
2. `CobolBasicApplication.java` 파일 찾기
3. 우클릭 → Run 실행

---

### 4. 실행 결과 확인

애플리케이션이 시작되면 모든 포팅된 COBOL 프로그램이 순차적으로 실행됩니다:

```
============================================================
  MAINFRAME COBOL TO JAVA PORTING - PROGRAM EXECUTION
============================================================

[1] Running COBOL Variable Types Program (var.cbl)
------------------------------------------------------------
* DIFFERENT VARIABLE TYPES IN COBOL *
INTEGER: 1
CHAR: AB
ALPHA: z1
DECIMAL: 2.14
...

[2] Running Special Names Validation Program (SpecialNames.cob)
------------------------------------------------------------
* SPECIAL-NAMES CLASS VALIDATION *
WS-INP >ABJKST01< IS VALID.
WS-INP >IM SPACE< IS NOT VALID.
...

[3] Running CICS Calculator Program (calc.cbl)
------------------------------------------------------------
* CICS CALCULATOR PROGRAM *
Input: NUM1 = 100, NUM2 = 25
Results:
  ADDITION:     125
  SUBTRACTION:  75
  MULTIPLY:     2500
  DIVISION:     4

[4] Running DB2 Operations Program (db2oper.cbl)
------------------------------------------------------------
* DB2 OPERATIONS PROGRAM *
MOST POP STATE IS: UTTAR PRADESH
RECORD INSERTED
RECORDS UPDATED: 10
...

[5] Running DB2 Cursor Program (db2curs.cbl)
------------------------------------------------------------
* DB2 CURSOR PROGRAM *
STATE MAHARASHTRA HAS 112374333
STATE UTTAR PRADESH HAS 199812341
...

[6] Running File Read Program (READPS.cob)
------------------------------------------------------------
* FILE READ PROGRAM (READPS) *
SAMPLE-DATA-001   This is sample data number one    Extra filler text
...

============================================================
  ALL PROGRAMS COMPLETED
============================================================
```

---

### 5. 단위 테스트 실행

포팅된 프로그램의 정확성을 검증하려면 단위 테스트를 실행하세요:

```bash
# 모든 테스트 실행
mvn test

# 특정 클래스만 테스트
mvn test -Dtest=CalcProgramTest
mvn test -Dtest=SpecialNamesProgramTest
```

테스트 결과:
- ✅ 18 개 테스트 모두 통과
- 테스트 커버리지: 기본 비즈니스 로직 중심

```
============================================================
  MAINFRAME COBOL TO JAVA PORTING - PROGRAM EXECUTION
============================================================

[1] Running COBOL Variable Types Program (var.cbl)
----------------------------------------
* DIFFERENT VARIABLE TYPES IN COBOL *
INTEGER: 1
CHAR: AB
...

[2] Running Special Names Validation Program (SpecialNames.cob)
----------------------------------------
* SPECIAL-NAMES CLASS VALIDATION *
WS-INP >ABJKST01< IS VALID.
...

[3] Running CICS Calculator Program (calc.cbl)
----------------------------------------
* CICS CALCULATOR PROGRAM *
Input: NUM1 = 100, NUM2 = 25
Results:
  ADDITION:     125
  SUBTRACTION:  75
  MULTIPLY:     2500
  DIVISION:     4

[4] Running DB2 Operations Program (db2oper.cbl)
----------------------------------------
* DB2 OPERATIONS PROGRAM *
MOST POP STATE IS: UTTAR PRADESH
...

[5] Running DB2 Cursor Program (db2curs.cbl)
----------------------------------------
* DB2 CURSOR PROGRAM *
STATE MAHARASHTRA HAS 112374333
...

[6] Running File Read Program (READPS.cob)
----------------------------------------
* FILE READ PROGRAM (READPS) *
IN ACTION PARA
SAMPLE-DATA-001   This is sample data number one    Extra filler text
...

============================================================
  ALL PROGRAMS COMPLETED
============================================================
```

---

## 🔍 문제 해결 (Troubleshooting)

### Java 17 을 찾을 수 없는 경우

**증상**: `error: release version 17 not supported`

**해결**:
```bash
# macOS - Homebrew 로 Java 17 설치
brew install openjdk@17

# Java 17 경로 확인
/usr/libexec/java_home -V

# JAVA_HOME 설정 후 실행
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
mvn spring-boot:run
```

### 포트 사용 중인 경우

**증상**: `Port 8080 is already in use`

**해결**:
```bash
# 8080 포트를 사용 중인 프로세스 확인
lsof -i :8080

# 프로세스 종료
kill -9 <PID>

# 또는 다른 포트 사용
mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8081"
```

### H2 콘솔에 접속할 수 없는 경우

**증상**: `H2 console not available`

**해결**:
1. 애플리케이션이 정상 실행 중인지 확인
2. 브라우저에서 `http://localhost:8080/h2-console` 접속
3. 설정 정보:
   - **JDBC URL**: `jdbc:h2:mem:mainframe`
   - **Username**: `sa`
   - **Password**: (비어 있음)

---

## 📟 디버깅 방법

### 로그 레벨 변경

`application.yml` 에서 로그 레벨을 조정하세요:

```yaml
logging:
  level:
    com.myproject: DEBUG      # 애플리케이션 로그
    org.hibernate.SQL: DEBUG  # SQL 쿼리 로그
    org.hibernate.type: TRACE # 바인딩 변수 로그
```

### 특정 프로그램만 실행

`CobolBasicApplication.java` 를 수정하여 원하는 프로그램만 실행할 수 있습니다:

```java
@Bean
public CommandLineRunner runAllPrograms(...) {
    return args -> {
        // 원하는 프로그램만 실행
        varCobolProgram.execute();
        calcProgram.execute();
    };
}
```

---

## 📊 COBOL → Java 매핑 테이블

### 데이터 타입 매핑

| COBOL | Java | 설명 |
|-------|------|------|
| `PIC 9(n)` | `int`, `long` | 정수 |
| `PIC S9(n)` | `int` | 부호 있는 정수 |
| `PIC X(n)` | `String` | 알파뉴메릭 |
| `PIC A(n)` | `String` | 알파벳 |
| `PIC 9V99` | `double` | 가정 소수점 |
| `PIC PPP999` | `double` | SCALE 가정소수점 |
| `COMP` | `short` | 16-bit integer |
| `COMP-1` | `float` | Single precision |
| `COMP-2` | `double` | Double precision |
| `COMP-3` | `BigDecimal` | Packed decimal |
| `OCCURS n` | `array[n]` | 배열 |
| `REDEFINES` | 동일 필드 재사용 | 메모리 공유 |
| `Level 88` | `boolean` 메서드 | 조건명 |

### 구문 매핑

| COBOL | Java |
|-------|------|
| `PERFORM paragraph` | `private void method()` |
| `IF condition THEN` | `if (condition) {` |
| `EVALUATE expression` | `switch (expression)` |
| `SEARCH table` | `Stream.filter()` |
| `INSPECT TALLYING` | `String.indexOf()` |
| `STRING ... INTO` | `StringBuilder.append()` |
| `UNSTRING ... INTO` | `String.split()` |
| `EXEC SQL SELECT` | `JpaRepository.findById()` |
| `EXEC SQL INSERT` | `JpaRepository.save()` |
| `EXEC SQL UPDATE` | `@Query UPDATE` |
| `EXEC SQL DELETE` | `JpaRepository.delete()` |
| `OPEN/CLOSE file` | `Files.newBufferedReader()` |
| `READ file` | `BufferedReader.readLine()` |

---

## 📝 프로그램별 상세 설명

### 1. VarCobolProgram (var.cbl)
- **기능**: COBOL 변수 타입 및 연산 데모
- **포팅 내용**:
  - 다양한 PIC 절 변수 타입
  - REDEFINES (메모리 재정의)
  - COMP 타입 (COMP, COMP-1, COMP-2, COMP-3)
  - OCCURS (배열/테이블)
  - Level 88 조건명
  - INSPECT, STRING, UNSTRING 문자열 연산

### 2. SpecialNamesProgram (SpecialNames.cob)
- **기능**: 사용자 정의 클래스를 통한 유효성 검사
- **포팅 내용**:
  - `SPECIAL-NAMES CLASS` 정의
  - 문자 유효성 검사 로직

### 3. CalcProgram (calc.cbl)
- **기능**: CICS 계산기 프로그램
- **포팅 내용**:
  - 사칙연산 비즈니스 로직
  - BMS 맵 DTO (입출력 구조체)
  - *참고: CICS 트랜잭션 처리 (SEND/RECEIVE MAP) 는 제거*

### 4. Db2OperProgram (db2oper.cbl)
- **기능**: DB2 CRUD 연산
- **포팅 내용**:
  - `SELECT ... FETCH FIRST ROW ONLY` → `Optional<Census>`
  - `INSERT INTO` → `JpaRepository.save()`
  - `UPDATE ... SET` → `@Query UPDATE`
  - `SQLCODE/SQLSTATE` → Java 예외 처리
  - NULL indicator → `null` 값

### 5. Db2CursProgram (db2curs.cbl)
- **기능**: DB2 CURSOR 처리
- **포팅 내용**:
  - `DECLARE CURSOR` → JPA `Stream<T>`
  - `OPEN/FETCH/CLOSE` → `try-with-resources`
  - 대량 데이터 스트리밍 처리

### 6. ReadPsProgram (READPS.cob)
- **기능**: 순차 파일 읽기
- **포팅 내용**:
  - `SELECT ASSIGN TO` → `Path`
  - `FILE STATUS` → 예외 처리
  - `OPEN/CLOSE` → `try-with-resources`
  - `READ AT END` → `readLine() == null`

---

## 🗂️ 데이터베이스

### CENSUS 테이블 스키마

```sql
CREATE TABLE CENSUS (
    STATE_UT    VARCHAR(30) PRIMARY KEY,
    POPULATION  INTEGER,
    GROWTH_PER  INTEGER,
    RURAL_POP   INTEGER,
    URBAN_POP   INTEGER,
    YEAR_OF_SUR INTEGER,
    RATIO       INTEGER
);
```

### H2 콘솔 접속

애플리케이션 실행 중 다음 URL 로 H2 콘솔에 접속할 수 있습니다:

- **URL**: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:mem:mainframe`
- **Username**: `sa`
- **Password**: (비어 있음)

---

## 🔤 JCL/PL1/REXX 포팅 현황

| 기술 | 포팅 여부 | 비고 |
|------|----------|------|
| **JCL** | ❌ | 배치 잡은 Spring Batch 로 확장 가능 |
| **PL1** | ❌ | 필요 시 별도 포팅 가능 |
| **REXX** | ❌ | 자동화 스크립트는 Shell/Python 으로 대체 권장 |

---

## 📚 참고 자료

### COBOL 학습
- [IBM COBOL Language Reference](https://www.ibm.com/docs/en/cobol-zos)
- [GnuCOBOL Programmer's Guide](https://gnucobol.sourceforge.io/)

### Spring Boot
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)

### 메인프레임 → Java 마이그레이션
- [Modernizing Mainframe Applications](https://www.ibm.com/garage/method/practices/modernize/)

---

## 📄 라이선스

이 프로젝트는 학습 및 참고 목적으로 작성되었습니다.

---

## 👥 기여자

- **Original COBOL**: Phani (2016-12-26)
- **Java Porting**: myMainframe Team
