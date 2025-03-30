package com.zeluciojr.enrollments.core.entities;

import java.util.regex.Pattern;

public class CPF extends LegalId{

    public static final String REGEX = "^(?!.*(\\d)\\1{10})(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11})$";

    public static CPF of(String value){
        var newCpf = new CPF();
        newCpf.setValue(value);
        return newCpf;
    }

    @Override
    protected boolean checkValidity() {
        var regexMatches = Pattern.matches(REGEX, this.getValue());
        var cleanValue = this.getValue()
                .replace(".", "")
                .replace("-", "");
        int firstCheckDigit = calculateDigit(cleanValue.substring(0, 9), 10);
        int secondCheckDigit = calculateDigit(cleanValue.substring(0, 9) + firstCheckDigit, 11);
        return regexMatches && cleanValue.endsWith("" + firstCheckDigit + secondCheckDigit);
    }

    private static int calculateDigit(String cpfSegment, int weight) {
        int sum = 0;
        for (char digitChar : cpfSegment.toCharArray()) {
            int digit = Character.getNumericValue(digitChar);
            sum += digit * weight;
            weight--;
        }
        int remainder = sum % 11;
        return (remainder < 2) ? 0 : 11 - remainder;
    }

}
