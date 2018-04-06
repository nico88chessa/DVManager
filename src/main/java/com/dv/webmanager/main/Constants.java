package com.dv.webmanager.main;

public class Constants {

    public enum LaserKind {

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

    };

    public enum PrintStatus {
        PRINTING(0, "PRINTING"),
        PRINTED(1, "PRINTED"),
        PRINT_ERROR(-1, "PRINT_ERROR");

        private final int code;
        private final String name;

        PrintStatus(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public int getCode() { return code; }

        public String getName() { return name; }


    };

}
