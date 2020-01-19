package frc.robot.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import edu.wpi.first.wpilibj.Timer;

/**
 * This class facilitates the logging of data to an external USB flash drive.
 * A new file, always called "latest.txt" is written to in the /logs/ folder of
 * the drive.
 * 
 * It is referenced statically in the program and is a singleton.
 * It supplies three levels of logging importance:
 *    INFO: For routine robot data.
 *    WARN: For exeeded "soft limits" of operation
 *    SEVERE: For exeeded "hard limits" of operation
 */
public class Logger{

    // Singleton instance
    private static Logger instance = new Logger();

    private FileWriter writer;
    private int id = 100000;
    private boolean empty = true;

    private Logger(){
        try{    
            // "/U" is the default directory for RoboRIO flash drives
            File logFile = new File("/U/logs/latest.txt");

            // If there is already a "latest" log file, rename it
            // to its id before creating a new one.
            if(logFile.exists()){
                Scanner getName = new Scanner(logFile);
                if(getName.hasNext()){
                    id = Integer.parseInt(getName.nextLine());
                    File saved = new File("/U/logs/" + id + ".txt");
                    logFile.renameTo(saved);
                }
                
                getName.close();
            }

            logFile.createNewFile();
            
            writer = new FileWriter(logFile);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Logs a message to the current log file.
     * @param tag Message importance tag
     * @param message Message to be logged
     */
    private void log(String tag, String message){
        try{
            // Write the log id to the first line of the file.
            if(empty){
                empty = false;
                writer.write(++id + "\r\n");
            }
            
            // Use StringBuilder for efficiency.
            StringBuilder msgBuilder = new StringBuilder();
            msgBuilder.append("[");
            msgBuilder.append(Timer.getFPGATimestamp());
            msgBuilder.append("] [");
            msgBuilder.append(tag);
            msgBuilder.append("]: ");
            msgBuilder.append(message);
            msgBuilder.append("\r\n");

            // flush() writes all data in the writer's buffer to the file.
            writer.write(msgBuilder.toString());
            writer.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Log message at the INFO importance level.
     */
    public static void info(String message){
        instance.log("INFO", message);
    }

    /**
     * Log a message at the WARN importance level.
     */
    public static void warn(String message){
        instance.log("WARN", message);
    }

    /**
     * Log a message at the SEVERE importance level.
     */
    public static void severe(String message){
        instance.log("SEVERE", message);
    }

}