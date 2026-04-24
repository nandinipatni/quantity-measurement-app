package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public enum LengthUnit {
        FEET(1.0),
        INCHES(12.0),
        YARDS(3.0),
        CENTIMETERS(0.0328084);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double feetValue) {
            return feetValue / toFeetFactor;
        }
    }

    public static class Length {
        private final double value;
        private final LengthUnit unit;

        public Length(double value, LengthUnit unit) {
            this.value = value;
            this.unit = unit;
        }

        private double toFeet() {
            return unit.toFeet(value);
        }

        // 🔥 NEW METHOD (UC5 CORE)
        public Length convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double baseFeet = this.toFeet();
            double convertedValue = targetUnit.fromFeet(baseFeet);

            return new Length(convertedValue, targetUnit);
        }

        // 🔥 STATIC CONVERT METHOD (IMPORTANT FOR ASSIGNMENT)
        public static double convert(double value, LengthUnit from, LengthUnit to) {
            if (!Double.isFinite(value) || from == null || to == null) {
                throw new IllegalArgumentException("Invalid input");
            }

            double baseFeet = from.toFeet(value);
            return to.fromFeet(baseFeet);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Length other = (Length) obj;
            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    public static void main(String[] args) {

        // Demo conversions (as shown in your doc page 9)
        System.out.println(Length.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES)); // 12
        System.out.println(Length.convert(3.0, LengthUnit.YARDS, LengthUnit.FEET)); // 9
        System.out.println(Length.convert(36.0, LengthUnit.INCHES, LengthUnit.YARDS)); // 1
    }
}