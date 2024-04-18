package com.rockpaperscissor;

public class Variables {
    private static Variables instance;
    public int winCount = 0;
    public int loseCount = 0;

    private Variables() {} // Private constructor to prevent instantiation outside of this class

    public static synchronized Variables getInstance() {
        if (instance == null) {
            instance = new Variables();
        }
        return instance;
    }
}
