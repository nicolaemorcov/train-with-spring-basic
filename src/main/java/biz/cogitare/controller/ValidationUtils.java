package biz.cogitare.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidationUtils {

	private static final Logger logger = LoggerFactory.getLogger(ValidationUtils.class);

	private static final String DATE_FORMAT_YYYY_MM_DD = "yyyy/MM/dd";

	private static final int NUMBER_OF_SECONDS_IN_A_DAY = 86400;

	private static final String ERROR_MESSAGE_MAX_VALUE = "maxValue=";

	private static final String ERROR_MESSAGE_MIN_VALUE = "minValue=";

	private static final String ERROR_MESSAGE_DELIMITER = ";";

	private static final String ERROR_MESSAGE_INPUT_VALUE = "InputValue=";

	private static final String ERROR_MESSAGE_PREFIX = "[";

	private static final String ERROR_MESSAGE_SUFFIX = "]";

	private static final String MIN_TIMESTAMP_STRING_FOR_VALIDATION = "2018/01/01";

	private static final long MIN_TIMESTAMP_FOR_VALIDATION;

	private static final DateTimeFormatter TIME_FORMATTER_HHMMSS;

	private static final DateTimeFormatter TIME_FORMATTER_HHMM;

	private ValidationUtils() {
	}

	public static void main(String[] args) {

	}

	static {
		try {
			MIN_TIMESTAMP_FOR_VALIDATION = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD).parse(MIN_TIMESTAMP_STRING_FOR_VALIDATION).getTime();
			TIME_FORMATTER_HHMMSS = DateTimeFormatter.ofPattern("HH:mm:ss");
			TIME_FORMATTER_HHMM = DateTimeFormatter.ofPattern("HH:mm");
		} catch (ParseException e) {
			throw new AssertionError(e);
		}
	}

	// object validation
	public static <T> T isNotNull(T obj, String message) {
		if (obj == null)
			throw new IllegalArgumentException(message);
		return obj;
	}

	// string validation
	public static String isNotNullAndNotEmpty(String value, String message) {
		if (value != null && !value.trim().isEmpty()) {
			return value;
		} else {
			throw new IllegalArgumentException(message);
		}
	}

	// Unix Time stamp validation less than current time
	public static long isValidTimestampInThePast(long timeStamp, String message) {
		if (timeStamp >= MIN_TIMESTAMP_FOR_VALIDATION && timeStamp <= System.currentTimeMillis()) {
			return timeStamp;
		} else {
			throw new IllegalArgumentException(new StringBuilder().append(message).append(ERROR_MESSAGE_PREFIX).append(ERROR_MESSAGE_INPUT_VALUE).append(timeStamp)
					.append(ERROR_MESSAGE_SUFFIX).toString());
		}
	}

	//Unix Time stamp validation with no future time validation
	public static long isValidTimestamp(long timeStampInMillSec, String message) {
		if (timeStampInMillSec >= MIN_TIMESTAMP_FOR_VALIDATION) {
			return timeStampInMillSec;
		} else {
			throw new IllegalArgumentException(new StringBuilder().append(message).append(ERROR_MESSAGE_PREFIX).append(ERROR_MESSAGE_INPUT_VALUE).append(timeStampInMillSec)
					.append(ERROR_MESSAGE_SUFFIX).toString());
		}
	}

	//Unix timestamp validation used with a threshold for future time validation
	public static long isValidTimestampInThePastAndFutureWithThreshold(long timeStamp, long timestampThresholdInMilliseconds, String message) {
		long currentTimeWithThreshold = System.currentTimeMillis() + timestampThresholdInMilliseconds;
		if (timeStamp >= MIN_TIMESTAMP_FOR_VALIDATION && timeStamp <= currentTimeWithThreshold) {
			return timeStamp;
		} else {
			throw new IllegalArgumentException(new StringBuilder().append(message).append(ERROR_MESSAGE_PREFIX).append(ERROR_MESSAGE_INPUT_VALUE).append(timeStamp)
					.append(ERROR_MESSAGE_SUFFIX).toString());
		}
	}


	//seconds validation
	public static int isValidSecondOfDay(int secondsOfDay, String message) {
		if (secondsOfDay>=0 && secondsOfDay<= NUMBER_OF_SECONDS_IN_A_DAY) {
			return secondsOfDay;
		} else {
			throw new IllegalArgumentException(new StringBuilder().append(message).append(ERROR_MESSAGE_PREFIX).append(ERROR_MESSAGE_INPUT_VALUE).append(secondsOfDay)
					.append(ERROR_MESSAGE_SUFFIX).toString());
		}
	}

	//Time Validations
	public static String isValidTimeInHHMMSS(String time, String message) {
		try {
			TIME_FORMATTER_HHMMSS.parse(time);
			return time;
		} catch(DateTimeException exe) {
			logger.error(" Invalid Time {}", time, exe);
			throw new IllegalArgumentException(new StringBuilder().append(message).append(ERROR_MESSAGE_PREFIX).append(ERROR_MESSAGE_INPUT_VALUE).append(time)
					.append(ERROR_MESSAGE_SUFFIX).toString());
		}
	}

	public static String isValidTimeInHHMM(String time, String message) {
		try {
			TIME_FORMATTER_HHMM.parse(time);
			return time;
		} catch(DateTimeException exe) {
			logger.error(" Invalid Time {}", time, exe);
			throw new IllegalArgumentException(new StringBuilder().append(message).append(ERROR_MESSAGE_PREFIX).append(ERROR_MESSAGE_INPUT_VALUE).append(time)
					.append(ERROR_MESSAGE_SUFFIX).toString());
		}
	}

	//Array Validations
	public static <T> T[] isNotNullAndNotEmpty(T[] array, String message) {
		if (array != null && array.length != 0) {
			return array;
		} else {
			throw new IllegalArgumentException(message);
		}
	}

	// Collection Object Validations
	public static <T> List<T> isNotNullAndNotEmpty(List<T> list, String message) {
		if (list != null && list.size() != 0) {
			return list;
		} else {
			throw new IllegalArgumentException(message);
		}
	}

	public static <K, V> Map<K, V> isNotNullAndNotEmpty(Map<K, V> map, String message) {
		if (map != null && map.size() != 0) {
			return map;
		} else {
			throw new IllegalArgumentException(message);
		}
	}

	public static <T> Set<T> isNotNullAndNotEmpty(Set<T> set, String message) {
		if (set != null && set.size() != 0) {
			return set;
		} else {
			throw new IllegalArgumentException(message);
		}
	}

	// int Validations
	public static int checkMinValue(int value, int min, String message) {
		if (value >= min) {
			return value;
		} else {
			throw new IllegalArgumentException(new StringBuilder().append(message).append(ERROR_MESSAGE_PREFIX).append(ERROR_MESSAGE_INPUT_VALUE).append(value)
					.append(ERROR_MESSAGE_DELIMITER).append(ERROR_MESSAGE_MIN_VALUE).append(min).append(ERROR_MESSAGE_SUFFIX).toString());
		}
	}

	public static int checkMaxValue(int value, int max, String message) {
		if (value <= max) {
			return value;
		} else {
			throw new IllegalArgumentException(new StringBuilder().append(message).append(ERROR_MESSAGE_PREFIX).append(ERROR_MESSAGE_INPUT_VALUE).append(value)
					.append(ERROR_MESSAGE_DELIMITER).append(ERROR_MESSAGE_MAX_VALUE).append(max).append(ERROR_MESSAGE_SUFFIX).toString());
		}
	}

	public static int isInRange(int value, int min, int max, String message) {
		if (value >= min && value <= max) {
			return value;
		} else {
			throw new IllegalArgumentException(new StringBuilder().append(message).append(ERROR_MESSAGE_PREFIX).append(ERROR_MESSAGE_INPUT_VALUE).append(value)
					.append(ERROR_MESSAGE_DELIMITER).append(ERROR_MESSAGE_MIN_VALUE).append(min).append(ERROR_MESSAGE_DELIMITER).
							append(ERROR_MESSAGE_MAX_VALUE).append(max).append(ERROR_MESSAGE_SUFFIX).toString());
		}
	}

	// long Validations
	public static long checkMinValue(long value, long min, String message) {
		if (value >= min) {
			return value;
		} else {
			throw new IllegalArgumentException(new StringBuilder().append(message).append(ERROR_MESSAGE_PREFIX).append(ERROR_MESSAGE_INPUT_VALUE).append(value)
					.append(ERROR_MESSAGE_DELIMITER).append(ERROR_MESSAGE_MIN_VALUE).append(min).append(ERROR_MESSAGE_SUFFIX).toString());
		}
	}

	public static long checkMaxValue(long value, long max, String message) {
		if (value <= max) {
			return value;
		} else {
			throw new IllegalArgumentException(new StringBuilder().append(message).append(ERROR_MESSAGE_PREFIX).append(ERROR_MESSAGE_INPUT_VALUE).append(value)
					.append(ERROR_MESSAGE_DELIMITER).append(ERROR_MESSAGE_MAX_VALUE).append(max).append(ERROR_MESSAGE_SUFFIX).toString());
		}
	}

	public static long isInRange(long value, long min, long max, String message) {
		if (value >= min && value <= max) {
			return value;
		} else {
			throw new IllegalArgumentException(new StringBuilder().append(message).append(ERROR_MESSAGE_PREFIX).append(ERROR_MESSAGE_INPUT_VALUE).append(value)
					.append(ERROR_MESSAGE_DELIMITER).append(ERROR_MESSAGE_MIN_VALUE).append(min).append(ERROR_MESSAGE_DELIMITER).
							append(ERROR_MESSAGE_MAX_VALUE).append(max).append(ERROR_MESSAGE_SUFFIX).toString());
		}
	}

	// float Validations
	public static float checkMinValue(float value, float min, String message) {
		if (value >= min) {
			return value;
		} else {
			throw new IllegalArgumentException(new StringBuilder().append(message).append(ERROR_MESSAGE_PREFIX).append(ERROR_MESSAGE_INPUT_VALUE).append(value)
					.append(ERROR_MESSAGE_DELIMITER).append(ERROR_MESSAGE_MIN_VALUE).append(min).append(ERROR_MESSAGE_SUFFIX).toString());
		}
	}

	public static float checkMaxValue(float value, float max, String message) {
		if (value <= max) {
			return value;
		} else {
			throw new IllegalArgumentException(new StringBuilder().append(message).append(ERROR_MESSAGE_PREFIX).append(ERROR_MESSAGE_INPUT_VALUE).append(value)
					.append(ERROR_MESSAGE_DELIMITER).append(ERROR_MESSAGE_MAX_VALUE).append(max).append(ERROR_MESSAGE_SUFFIX).toString());
		}
	}

	public static float isInRange(float value, float min, float max, String message) {
		if (value >= min && value <= max) {
			return value;
		} else {
			throw new IllegalArgumentException(new StringBuilder().append(message).append(ERROR_MESSAGE_PREFIX).append(ERROR_MESSAGE_INPUT_VALUE).append(value)
					.append(ERROR_MESSAGE_DELIMITER).append(ERROR_MESSAGE_MIN_VALUE).append(min).append(ERROR_MESSAGE_DELIMITER).
							append(ERROR_MESSAGE_MAX_VALUE).append(max).append(ERROR_MESSAGE_SUFFIX).toString());
		}
	}

	// double Validations
	public static double checkMinValue(double value, double min, String message) {
		if (value >= min) {
			return value;
		} else {
			throw new IllegalArgumentException(new StringBuilder().append(message).append(ERROR_MESSAGE_PREFIX).append(ERROR_MESSAGE_INPUT_VALUE).append(value)
					.append(ERROR_MESSAGE_DELIMITER).append(ERROR_MESSAGE_MIN_VALUE).append(min).append(ERROR_MESSAGE_SUFFIX).toString());
		}
	}

	public static double checkMaxValue(double value, double max, String message) {
		if (value <= max) {
			return value;
		} else {
			throw new IllegalArgumentException(new StringBuilder().append(message).append(ERROR_MESSAGE_PREFIX).append(ERROR_MESSAGE_INPUT_VALUE).append(value)
					.append(ERROR_MESSAGE_DELIMITER).append(ERROR_MESSAGE_MAX_VALUE).append(max).append(ERROR_MESSAGE_SUFFIX).toString());
		}
	}

	public static double isInRange(double value, double min, double max, String message) {
		if (value >= min && value <= max) {
			return value;
		} else {
			throw new IllegalArgumentException(new StringBuilder().append(message).append(ERROR_MESSAGE_PREFIX).append(ERROR_MESSAGE_INPUT_VALUE).append(value)
					.append(ERROR_MESSAGE_DELIMITER).append(ERROR_MESSAGE_MIN_VALUE).append(min).append(ERROR_MESSAGE_DELIMITER).
							append(ERROR_MESSAGE_MAX_VALUE).append(max).append(ERROR_MESSAGE_SUFFIX).toString());
		}
	}
}
