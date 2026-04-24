package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // ENUM for units (as shown in UC3 doc page 3 image)
    public enum LengthUnit {
        FEET(1.0),
        INCHES(1.0 / 12.0);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }
    }

    // Generic Length class (DRY principle)
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

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Length other = (Length) obj;
            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }
    }

    public static void main(String[] args) {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        System.out.println("Are equal: " + l1.equals(l2));
    }
}