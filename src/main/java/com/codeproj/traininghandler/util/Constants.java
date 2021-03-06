package com.codeproj.traininghandler.util;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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
	public static final String VALIDATION_EXCEL_IMPORT_INVALID_CONTENT = "Az importálni kívánt excel fájl tartalma nem megfelelő. Kérlek töltsd le a minta excel fájlt és ne hagyj ki sorokat, valamint csak a megfelelő oszlopokba írjál!";
	public static final String VALIDATION_EXCEL_IMPORT_USER_HAS_NO_PREREQUISITE = "A következő tanítványnak nincs meg a megfelelő előfeltétele a tanfolyamhoz: ";
	public static final String VALIDATION_EXCEL_IMPORT_USER_HAS_NO_PREREQUISITE_INFO = "\nAz előtte lévő tanítványokat sikeresen hozzáadtam. (Nem kell kitörölni azokat az ismételt próbálkozásnál, mert nem fogja a rendszer kétszer hozzáadni)";
	
	public static final String VALIDATION_EXCEL_EMPTY_LIST = "Üres lista";
	public static final String VALIDATION_EXCEL_IMPORT_NAME_EMPTY = "A név nem lehet üres";
	public static final String VALIDATION_EXCEL_IMPORT_NAME_TOO_LONG = "Túl hosszú a név. Maximum 100 karakter lehet";
	public static final String VALIDATION_EXCEL_IMPORT_POST_CODE_EMPTY = "Az iranyítószám nem lehet üres";
	public static final String VALIDATION_EXCEL_IMPORT_POST_CODE_TOO_LONG = "Túl hosszú az iranyítószám. Maximum 15 karakter lehet";
	public static final String VALIDATION_EXCEL_IMPORT_ADDRESS_EMPTY = "A cím nem lehet üres";
	public static final String VALIDATION_EXCEL_IMPORT_ADDRESS_TOO_LONG = "Túl hosszú a cím. Maximum 100 karakter lehet";
	public static final String VALIDATION_EXCEL_IMPORT_PHONE_NO_EMPTY = "A telefonszám nem lehet üres";
	public static final String VALIDATION_EXCEL_IMPORT_PHONE_NO_TOO_LONG = "Túl hosszú a telefonszam. Maximum 50 karakter lehet";
	public static final String VALIDATION_EXCEL_IMPORT_EMAIL_EMPTY = "Az email nem lehet üres";
	public static final String VALIDATION_EXCEL_IMPORT_EMAIL_TOO_LONG = "Túl hosszú az email. Maximum 80 karakter lehet";
	public static final String VALIDATION_EXCEL_IMPORT_EMAIL_INVALID = "Az email cim formatuma nem jo";
	public static final String VALIDATION_EXCEL_SEPARATOR = ", ";
	public static final String VALIDATION_EXCEL_IMPORT_EMPTY_TR_ATT_LIST = "A megadott excel fájl üres, nem tartalmaz érvényes sorokat!";
	
	public static final String PARAMETER_STRING_SIZE_MORE_THEN_10 = "Lorem ipsum";
	public static final String PARAMETER_STRING_SIZE_MORE_THEN_100 = "Lorem ipsum dolor sit amet, consectetur "
							+ "adipiscing elit. Proin quis sem eget erat pharetra mattis sed. ";
	public static final String PARAMETER_STRING_SIZE_MORE_THEN_300 = "Lorem ipsum dolor sit amet, consectetur "
							+ "adipiscing elit. Aliquam nec blandit dolor. Aenean at volutpat ipsum, quis dignissim nisi. Ut "
							+ "sed arcu elementum, dignissim nisl a, adipiscing tortor. Nullam sit amet risus faucibus, luctus turpis ut, rhoncus massa. Sed mollis justo dapibus faucibus turpis duis. ";
	
	public static final String VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST = "Hiba történt az adatok lekérése közben! Az adatok nem lettek elküldve, vagy a megadott azonosító nem érvényes.";
	public static final String VALIDATION_ERR_MSG_MISSING_PREREQUISITE = "Ez a tanítvány még nem rendelkezik a megfelelő előfeltételekkel: ";
	public static final String VALIDATION_ERR_MSG_TRAINING_TYPE_DOESNT_EXIST = "A tanfolyam típus nem létezik";
	public static final String VALIDATION_ERR_MSG_MANDATORY_PARAMETER = "Ezt a paramétert kötelező megadni: ";
	public static final String VALIDATION_ERR_MSG_PARAMETER_TOO_LONG = " túl hosszú. A maximum hosszúság: ";
	public static final String VALIDATION_ERR_MSG_EMAIL_INVALID = "Az email nem megfelelő formátumú";
	public static final String VALIDATION_DATE_NOT_VALID_1 = "A megadott idő: ";
	public static final String VALIDATION_DATE_NOT_VALID_2 = " nem lehet régebbi, mint ";
	public static final String VALIDATION_DATE_IN_THE_FUTURE_1 = "A megadott idő: ";
	public static final String VALIDATION_DATE_IN_THE_FUTURE_2 = " nem lehet a jövőben";
	public static final String VALIDATION_TRAINING_COMPLETION_DATE = "Tanfolyam elvégzési ideje";
	public static final String VALIDATION_ERR_MSG_REQUESTED_OBJECT_CANNOT_BE_FOUND = "A kért elem nem található";
	public static final String VALIDATION_ERR_MSG_TRAINING_COMPLETED_DATE_INVALID = "A megadott dátum érvénytelen";
	public static final String VALIDATION_ERR_MSG_GENERAL_ERROR = "A kért művelet közben szerver hiba történt!";
	public static final String VALIDATION_ERR_MSG_USER_TRAINING_COMPL_ALREADY_EXISTS = "A tanítvány már elvégezte ezt a tanfolyamot!";
	
	public static final String VALIDATION_PARAMETER_POSTCODE = "irányítószám";
	public static final String VALIDATION_PARAMETER_CITY = "város";
	public static final String VALIDATION_PARAMETER_STREET = "utca";
	public static final String VALIDATION_PARAMETER_HOUSE_NO = "házszám";
	public static final String VALIDATION_PARAMETER_COUNTRY = "ország";
	public static final String VALIDATION_PARAMETER_ADDRESS = "cím";
	public static final String VALIDATION_PARAMETER_USER_ID = "Felhasználó azonosító";
	public static final String VALIDATION_PARAMETER_TRAINING_TYPE_ID = "Tanfolyam típus azonosító";
	public static final String VALIDATION_PARAMETER_COMPLETED_DATE = "Tanfolyam elvégzési ideje";
	public static final String VALIDATION_PARAMETER_NAME = "Név";
	public static final String VALIDATION_PARAMETER_PHONE = "Telefonszám";
	public static final String VALIDATION_PARAMETER_EMAIL = "Email";
	public static final String VALIDATION_PARAMETER_ADDRESS_ID = "Cím azonosító";
	public static final String VALIDATION_PARAMETER_LEVEL_NO = "Fokozat";
	public static final String VALIDATION_PARAMETER_TRAINING_TYPE_DESC = "Tanfolyam típus leírás";
	public static final String VALIDATION_PARAMETER_LAST_NAME = "Családi név";
	public static final String VALIDATION_PARAMETER_FIRST_NAME = "Keresztnév";
	public static final String VALIDATION_PARAMETER_DISPLAY_NAME = "Becenév";
	public static final String VALIDATION_PARAMETER_DOB = "Születési idő";
	
	
	public static final DateTimeFormatter HUNGARIAN_DATE_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd");

	public static final int DB_USER_USER_TYPE_ID_TRAINEE = 4;
	
	public static final int TABLE_FIELD_SIZE_POSTCODE = 15; 
	public static final int TABLE_FIELD_SIZE_CITY = 50; 
	public static final int TABLE_FIELD_SIZE_STREET = 70; 
	public static final int TABLE_FIELD_SIZE_HOUSE_NO = 10; 
	public static final int TABLE_FIELD_SIZE_COUNTRY = 50; 
	public static final int TABLE_FIELD_SIZE_NAME = 100; 
	public static final int TABLE_FIELD_SIZE_ADDRESS = 100; 
	public static final int TABLE_FIELD_SIZE_PHONE = 50; 
	public static final int TABLE_FIELD_SIZE_EMAIL = 80; 
	public static final int TABLE_FIELD_SIZE_LEVEL_NO = 10; 
	public static final int TABLE_FIELD_SIZE_TRAINING_TYPE_DESC = 300; 
	public static final int TABLE_FIELD_SIZE_LAST_NAME = 50; 
	public static final int TABLE_FIELD_SIZE_FIRST_NAME = 50; 
	public static final int TABLE_FIELD_SIZE_DISPLAY_NAME = 50; 
	public static final int TABLE_FIELD_SIZE_FULL_NAME = 100; 
	public static final int TABLE_FIELD_SIZE_TT_NAME = 100; 
	public static final int TABLE_FIELD_SIZE_DOB = 100; 
	
	public static final int MIN_AGE_YEAR = 6; 
	
	public static final String IMPORT_EXCEL_REGEXP_PATTERN = "([0-9]{1,2}[\\w]{0,3}_[0-9]{4}-[0-9]{2}-[0-9]{2}.*)";
	
	public static final String MAV_PARAM_NAME_VALIDATION_MSG = "validationMsg";
	public static final String MAV_PARAM_NAME_IMPORT_SUCCESS = "isImportSuccess";
}
