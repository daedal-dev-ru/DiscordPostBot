package ru.daedal.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BotLogger {
    public static void logError(String msg) {
        System.out.println(ConsoleColor.ANSI_WHITE + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime())
                + ConsoleColor.ANSI_WHITE + " | "
                + ConsoleColor.ANSI_WHITE + "["
                + ConsoleColor.ANSI_YELLOW +"PostBot"
                + ConsoleColor.ANSI_PURPLE + " | "
                + ConsoleColor.ANSI_RED + "ERROR"
                + ConsoleColor.ANSI_WHITE + "] "
                        + ConsoleColor.ANSI_CYAN + msg);
    }

    public static void logInfo(String msg) {
        System.out.println(ConsoleColor.ANSI_WHITE + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime())
                + ConsoleColor.ANSI_WHITE + " | "
                + ConsoleColor.ANSI_WHITE + "["
                + ConsoleColor.ANSI_YELLOW +"PostBot"
                + ConsoleColor.ANSI_WHITE + "] "
                + ConsoleColor.ANSI_CYAN + msg);
    }
}
