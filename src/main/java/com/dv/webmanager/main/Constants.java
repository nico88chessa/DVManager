package com.dv.webmanager.main;

public class Constants {

    public enum LaserKind {

        NONE(-255, ""),
        CO2(0, "CO2"),
        UV(1, "UV"),
        YAGCW(2, "YAGCW"),
        YAGHP(3, "YAGHP"),
        IR(4, "IR"),
        YAGP(5, "YAGP"),
        YAGP2(6, "YAGP2"),
        MYAG(7, "MYAG");

        private final int code;
        private final String name;

        LaserKind(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public int getCode() { return code; }

        public String getName() { return name; }

        public static LaserKind valueFromInt(int value) {
            switch (value) {
            case 0: return CO2;
            case 1: return UV;
            case 2: return YAGCW;
            case 3: return YAGHP;
            case 4: return IR;
            case 5: return YAGP;
            case 6: return YAGP2;
            case 7: return MYAG;
            default: return NONE;
            }
        }

    };

    public enum PrintStatus {

        NONE(-255, ""),
        PRINTING(0, "IN STAMPA"),
        PRINTED(1, "COMPLETATO"),
        PRINT_ERROR(-1, "ERRORE STAMPA");

        private final int code;
        private final String name;

        PrintStatus(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public int getCode() { return code; }

        public String getName() { return name; }

        public static PrintStatus valueFromInt(int value) {
            switch (value) {
            case 0: return PRINTING;
            case 1: return PRINTED;
            case -1: return PRINT_ERROR;
            default: return NONE;
            }
        }


    };

}
