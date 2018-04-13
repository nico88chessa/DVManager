CREATE TABLE IF NOT EXISTS MACHINE (
ID INT NOT NULL AUTO_INCREMENT,
NAME VARCHAR(10) NOT NULL,
LAST_UPDATE DATETIME NULL DEFAULT NULL,
IP VARCHAR(15) NOT NULL,
TO_DELETE BOOLEAN DEFAULT FALSE,
PRIMARY KEY (ID)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS PRINT_TICKET (
ID_M INT NOT NULL,
ID_T INT NOT NULL,
QUEUE_ACTIVATED TINYINT(1) NOT NULL,
QUEUE_POSITION INT NOT NULL,
WORK_ID VARCHAR(255) DEFAULT NULL,
FILE_NAME VARCHAR(255) NOT NULL,
FILE_LENGTH DOUBLE NOT NULL,
FILE_HEIGHT DOUBLE NOT NULL,
FILE_PIXEL_X INT NOT NULL,
FILE_PIXEL_Y INT NOT NULL,
FILE_RESOLUTION_X DOUBLE NOT NULL,
FILE_RESOLUTION_Y DOUBLE NOT NULL,
FILE_TOTALPIXELX INT NOT NULL,
FILE_TOTALPIXELY INT NOT NULL,
FILE_TOTALSIZEX DOUBLE NOT NULL,
FILE_TOTALSIZEY DOUBLE NOT NULL,
CYLINDER_LENGTH DOUBLE NOT NULL,
CYLINDER_DIAMETER DOUBLE NOT NULL,
CYLINDER_START_AT DOUBLE NOT NULL,
CYLINDER_CROSS_AT DOUBLE NOT NULL,
LASER_KIND INT NOT NULL,
LASER_SOURCES INT NOT NULL,
LASER_MIN_POWER DOUBLE NOT NULL,
LASER_MAX_POWER DOUBLE NOT NULL,
LASER_SHOTS DOUBLE NOT NULL,
LASER_PERIOD DOUBLE NOT NULL,
SETUP_CALIBRATION TINYINT(1) NOT NULL,
SETUP_OFFSET_X DOUBLE NOT NULL,
SETUP_OFFSET_Y DOUBLE NOT NULL,
SETUP_Z_PRESENT TINYINT(1) NOT NULL,
SETUP_Z_POSITION DOUBLE NOT NULL,
SETUP_FOCAL_PRESENT TINYINT(1) NOT NULL,
SETUP_FOCAL_POSITION DOUBLE NOT NULL,
SETUP_SPEED INT NOT NULL,
SETUP_ENGRAVING_MODE VARCHAR(255) NOT NULL,
PRINT_START_AT DATETIME NOT NULL,
PRINT_STOP_AT DATETIME NOT NULL,
PRINT_STATUS INT NOT NULL,
PRINT_ERROR VARCHAR(255) DEFAULT NULL,
NOTES VARCHAR(255) DEFAULT NULL,
PRIMARY KEY (ID_M, ID_T),
FOREIGN KEY (ID_M) REFERENCES MACHINE(ID) ON DELETE CASCADE
) ENGINE = InnoDB;
