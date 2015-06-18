package com.codeproj.traininghandler.util;

public class Constants {
	public static final String GLOBAL_DEFAULT_LOCALE = "hu_HU";
	
	public static final String USER_TYPE_DATA_MASTER = "USER_TYPE_MASTER";
	public static final String USER_TYPE_DATA_PATIENT = "USER_TYPE_PATIENT";
	public static final String USER_TYPE_DATA_HEALER = "USER_TYPE_HEALER";
	public static final String USER_TYPE_DATA_TRAINEE = "USER_TYPE_TRAINEE";
	
	public static final Long USER_TYPE_DATA_MASTER_ID = 1L;
	public static final Long USER_TYPE_DATA_PATIENT_ID = 2L;
	public static final Long USER_TYPE_DATA_HEALER_ID = 3L;
	public static final Long USER_TYPE_DATA_TRAINEE_ID = 4L;
	
	public static final int EXCEL_TRAINING_START_ROW_INDEX = 3;
	public static final int EXCEL_TRAINING_START_COLUMN_INDEX = 2;
	public static final int EXCEL_TRAINING_NAME_COL_INDEX = 0;
	public static final int EXCEL_TRAINING_POST_CODE_COL_INDEX = 1;
	public static final int EXCEL_TRAINING_ADDRESS_COL_INDEX = 2;
	public static final int EXCEL_TRAINING_PHONE_NO_COL_INDEX = 3;
	public static final int EXCEL_TRAINING_EMAIL_COL_INDEX = 4;
	
	public static final int EXCEL_TRAINING_MAX_CHECK_COLUMN = 20;
	public static final int EXCEL_TRAINING_MAX_CHECK_ROW = 80;

	public static final int EXCEL_NUMBER_OF_DATA_ROWS = 5;
	
	public static final String EXCEL_TRAINING_MISSING_EMAIL_DOMAIN = "@nincs.com";
	public static final String EXCEL_TRAINING_MISSING_EMAIL = "none";
	
	
	public static final String VALIDATION_EXCEL_PROBLEM_DURING_OPENING_EXCEL = "Hiba történt az excel olvasása közben. Kérlek ellenőrízd, hogy nem sérült e a fájl. Előfordulhat, hogy nem excel fájlt próbáltál beimportálni.";
	public static final String VALIDATION_EXCEL_PROBLEM_DURING_READING_CONTENT = "Hiba történt az excel olvasása közben. Kérlek ellenőrízd, hogy a sor és oszlop megfelelő helyen kezdődik e. A helyes kezdési pont: Sor: " + (EXCEL_TRAINING_START_ROW_INDEX + 1) + ", Oszlop: " + (EXCEL_TRAINING_START_COLUMN_INDEX + 1) + ". A problémás sor: ";
	public static final String VALIDATION_EXCEL_IMPORT_INVALID_TRAINING_TYPE_ID = "Nem választottál ki tanfolyamot";
	public static final String VALIDATION_EXCEL_IMPORT_INVALID_YEAR = "Hibas ev! Minimum 1992";
	public static final String VALIDATION_EXCEL_IMPORT_INVALID_MONTH = "Hibas honap";
	public static final String VALIDATION_EXCEL_IMPORT_INVALID_DAY = "Hibas nap";
	public static final String VALIDATION_EXCEL_IMPORT_INVALID_FILE = "Nem talalom az excel fajlt";
	public static final String VALIDATION_EXCEL_IMPORT_IVALID_CONTENT = "A név kitöltése mindig kötelező";
	
	public static final String VALIDATION_EXCEL_EMPTY_LIST = "Ures lista";
	public static final String VALIDATION_EXCEL_SEPARATOR = ", ";
	
	public static final String PARAMETER_STRING_SIZE_MORE_THEN_10 = "Lorem ipsum";
	public static final String PARAMETER_STRING_SIZE_MORE_THEN_100 = "Lorem ipsum dolor sit amet, consectetur "
							+ "adipiscing elit. Proin quis sem eget erat pharetra mattis sed. ";
	public static final String PARAMETER_STRING_SIZE_MORE_THEN_300 = "Lorem ipsum dolor sit amet, consectetur "
							+ "adipiscing elit. Aliquam nec blandit dolor. Aenean at volutpat ipsum, quis dignissim nisi. Ut "
							+ "sed arcu elementum, dignissim nisl a, adipiscing tortor. Nullam sit amet risus faucibus, luctus turpis ut, rhoncus massa. Sed mollis justo dapibus faucibus turpis duis. ";
	
	public static final String VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST = "Hiba történt az adatok lekérése közben!";
	public static final String VALIDATION_ERR_MSG_MISSING_PREREQUISITE = "Ez a tanítvány még nem rendelkezik a megfelelő előfeltételekkel: ";
	public static final String VALIDATION_ERR_MSG_TRAINING_TYPE_DOESNT_EXIST = "A tanfolyam típus nem létezik";
	public static final String VALIDATION_ERR_MSG_TRAINING_TYPE_HAS_NOT_FOUND = "A tanfolyam típus nem létezik";
	public static final String VALIDATION_ERR_MSG_MANDATORY_PARAMETER = "Ezt a paramétert kötelező megadni: ";
	public static final String VALIDATION_ERR_MSG_PARAMETER_TOO_LONG = " túl hosszú. A maximum hosszúság: ";
	public static final String VALIDATION_ERR_MSG_EMAIL_INVALID = "Az email nem megfelelő formátumú";
	public static final String VALIDATION_DATE_NOT_VALID_1 = "A megadott idő: ";
	public static final String VALIDATION_DATE_NOT_VALID_2 = " nem lehet régebbi, mint ";
	public static final String VALIDATION_DATE_IN_THE_FUTURE_1 = "A megadott idő: ";
	public static final String VALIDATION_DATE_IN_THE_FUTURE_2 = " nem lehet a jövőben";
	public static final String VALIDATION_TRAINING_COMPLETION_DATE = "Tanfolyam elvégzési ideje";
	public static final String VALIDATION_ERR_MSG_REQUESTED_OBJECT_CANNOT_BE_FOUND = "A kért elem nem található";
		
}
