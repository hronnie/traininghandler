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
	
	public static final String EXCEL_TRAINING_MISSING_EMAIL_DOMAIN = "@nincs.com";
	public static final String EXCEL_TRAINING_MISSING_EMAIL = "nincs";
	
	
	public static final String VALIDATION_EXCEL_IMPORT_INVALID_TRAINING_TYPE_ID = "Nem választottál ki tanfolyamot";
	public static final String VALIDATION_EXCEL_IMPORT_INVALID_YEAR = "Hibas ev! Minimum 1992";
	public static final String VALIDATION_EXCEL_IMPORT_INVALID_MONTH = "Hibas honap";
	public static final String VALIDATION_EXCEL_IMPORT_INVALID_DAY = "Hibas nap";
	public static final String VALIDATION_EXCEL_IMPORT_INVALID_FILE = "Nem talalom az excel fajlt";
	
	public static final String VALIDATION_EXCEL_EMPTY_LIST = "Ures lista";
	public static final String VALIDATION_EXCEL_IMPORT_NAME_EMPTY = "A nev nem lehet ures";
	public static final String VALIDATION_EXCEL_IMPORT_NAME_TOO_LONG = "Tul hosszu a nev. Maximum 100 karakter lehet";
	public static final String VALIDATION_EXCEL_IMPORT_POST_CODE_EMPTY = "Az iranyitoszam nem lehet ures";
	public static final String VALIDATION_EXCEL_IMPORT_POST_CODE_TOO_LONG = "Tul hosszu az iranyitoszam. Maximum 15 karakter lehet";
	public static final String VALIDATION_EXCEL_IMPORT_ADDRESS_EMPTY = "A cim nem lehet ures";
	public static final String VALIDATION_EXCEL_IMPORT_ADDRESS_TOO_LONG = "Tul hosszu a cim. Maximum 100 karakter lehet";
	public static final String VALIDATION_EXCEL_IMPORT_PHONE_NO_EMPTY = "A telefonszam nem lehet ures";
	public static final String VALIDATION_EXCEL_IMPORT_PHONE_NO_TOO_LONG = "Tul hosszu a telefonszam. Maximum 50 karakter lehet";
	public static final String VALIDATION_EXCEL_IMPORT_EMAIL_EMPTY = "Az email nem lehet ures";
	public static final String VALIDATION_EXCEL_IMPORT_EMAIL_TOO_LONG = "Tul hosszu az email. Maximum 80 karakter lehet";
	public static final String VALIDATION_EXCEL_SEPARATOR = ", ";
	
	public static final String PARAMETER_STRING_SIZE_MORE_THEN_10 = "Lorem ipsum";
	public static final String PARAMETER_STRING_SIZE_MORE_THEN_100 = "Lorem ipsum dolor sit amet, consectetur "
							+ "adipiscing elit. Proin quis sem eget erat pharetra mattis sed. ";
	public static final String PARAMETER_STRING_SIZE_MORE_THEN_300 = "Lorem ipsum dolor sit amet, consectetur "
							+ "adipiscing elit. Aliquam nec blandit dolor. Aenean at volutpat ipsum, quis dignissim nisi. Ut "
							+ "sed arcu elementum, dignissim nisl a, adipiscing tortor. Nullam sit amet risus faucibus, luctus turpis ut, rhoncus massa. Sed mollis justo dapibus faucibus turpis duis. ";
		
}
