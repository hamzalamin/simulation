package com.wora.smartbank2.helpers;


import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Utils {

    public static Double getDoubleParameter(HttpServletRequest request, String parameterName) {
        String paramValue = request.getParameter(parameterName);
        Double result = null;

        if (paramValue != null && !paramValue.isEmpty()) {
            try {
                result = Double.parseDouble(paramValue);
            } catch (NumberFormatException e) {
                System.out.println("Invalid format for parameter: " + parameterName + " with value: " + paramValue);
            }
        } else {
            System.out.println("Parameter '" + parameterName + "' is missing or empty.");
        }

        return result;
    }

    public static <T extends Enum<T>> T getEnumParameter(HttpServletRequest request, String parameterName, Class<T> enumClass) {
        String paramValue = request.getParameter(parameterName);

        if (paramValue != null && !paramValue.isEmpty()) {
            try {
                return Enum.valueOf(enumClass, paramValue.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid enum value for parameter: " + parameterName + " with value: " + paramValue);
            }
        } else {
            System.out.println("Parameter '" + parameterName + "' is missing or empty.");
        }

        return null;
    }

    public static LocalDate getDateParameter(HttpServletRequest request, String parameterName, String dateFormat) {
        String dateStr = request.getParameter(parameterName);

        if (dateStr != null && !dateStr.isEmpty()) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
                return LocalDate.parse(dateStr, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format for parameter: " + parameterName + " with value: " + dateStr);
            }
        } else {
            System.out.println("Parameter '" + parameterName + "' is missing or empty.");
        }

        return null;
    }

    public static Boolean getBooleanParameter(HttpServletRequest request, String parameterName) {
        String paramValue = request.getParameter(parameterName);

        if (paramValue != null && !paramValue.isEmpty()) {
            try {
                return Boolean.parseBoolean(paramValue);
            } catch (Exception e) {
                System.out.println("Invalid boolean value for parameter: " + parameterName + " with value: " + paramValue);
            }
        } else {
            System.out.println("Parameter '" + parameterName + "' is missing or empty.");
        }

        return null;
    }


}
