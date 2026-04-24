package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static class Length {
        private final double value;
        private final LengthUnit unit;

        public Length(double value, LengthUnit unit) {
            if (unit == null || !Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid input");
            }
            this.value = value;
            this.unit = unit;
        }

        private double toBase() {
            return unit.convertToBaseUnit(value);
        }

        public Length convertTo(LengthUnit targetUnit) {
            double base = this.toBase();
            return new Length(targetUnit.convertFromBaseUnit(base), targetUnit);
        }

        // UC6
        public Length add(Length other) {
            double sum = this.toBase() + other.toBase();
            return new Length(unit.convertFromBaseUnit(sum), unit);
        }

        // UC7
        public Length add(Length other, LengthUnit targetUnit) {
            double sum = this.toBase() + other.toBase();
            return new Length(targetUnit.convertFromBaseUnit(sum), targetUnit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Length other = (Length) obj;
            return Double.compare(this.toBase(), other.toBase()) == 0;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    public static void main(String[] args) {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        System.out.println(l1.add(l2, LengthUnit.FEET));   // 2 FEET
        System.out.println(l1.add(l2, LengthUnit.INCHES)); // 24 INCHES
        System.out.println(l1.add(l2, LengthUnit.YARDS));  // ~0.667 YARDS
    }
}