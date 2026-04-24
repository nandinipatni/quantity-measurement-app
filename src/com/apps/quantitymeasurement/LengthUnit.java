package com.apps.quantitymeasurement;

public enum LengthUnit {

    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    // convert THIS unit → base (feet)
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    // convert base (feet) → THIS unit
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }
}