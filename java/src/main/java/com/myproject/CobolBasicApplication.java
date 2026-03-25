package com.myproject;

import com.myproject.basics.VarCobolProgram;
import com.myproject.basics.SpecialNamesProgram;
import com.myproject.cics.CalcProgram;
import com.myproject.db2oper.Db2OperProgram;
import com.myproject.db2oper.Db2CursProgram;
import com.myproject.fileop.ReadPsProgram;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Mainframe COBOL Programs Java Porting
 * 
 * This application ports various COBOL programs from the myMainframe project to Java.
 * 
 * @author Phani (original COBOL)
 * @author Java Porting Team
 */
@SpringBootApplication
public class CobolBasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(CobolBasicApplication.class, args);
    }

    /**
     * Run all ported COBOL programs sequentially
     */
    @Bean
    public CommandLineRunner runAllPrograms(
            VarCobolProgram varCobolProgram,
            SpecialNamesProgram specialNamesProgram,
            CalcProgram calcProgram,
            Db2OperProgram db2OperProgram,
            Db2CursProgram db2CursProgram,
            ReadPsProgram readPsProgram) {
        
        return args -> {
            System.out.println("=".repeat(60));
            System.out.println("  MAINFRAME COBOL TO JAVA PORTING - PROGRAM EXECUTION");
            System.out.println("=".repeat(60));
            
            // 1. COBOL Basics - Variable Types
            System.out.println("\n[1] Running COBOL Variable Types Program (var.cbl)");
            System.out.println("-".repeat(60));
            varCobolProgram.execute();
            
            // 2. COBOL Basics - Special Names (Validation)
            System.out.println("\n[2] Running Special Names Validation Program (SpecialNames.cob)");
            System.out.println("-".repeat(60));
            specialNamesProgram.execute();
            
            // 3. CICS Calculator
            System.out.println("\n[3] Running CICS Calculator Program (calc.cbl)");
            System.out.println("-".repeat(60));
            calcProgram.execute();
            
            // 4. DB2 Operations
            System.out.println("\n[4] Running DB2 Operations Program (db2oper.cbl)");
            System.out.println("-".repeat(60));
            db2OperProgram.execute();
            
            // 5. DB2 Cursor
            System.out.println("\n[5] Running DB2 Cursor Program (db2curs.cbl)");
            System.out.println("-".repeat(60));
            db2CursProgram.execute();
            
            // 6. File Operations
            System.out.println("\n[6] Running File Read Program (READPS.cob)");
            System.out.println("-".repeat(60));
            readPsProgram.execute();
            
            System.out.println("\n" + "=".repeat(60));
            System.out.println("  ALL PROGRAMS COMPLETED");
            System.out.println("=".repeat(60));
        };
    }
}
