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

    // WEIGHT TESTS
    Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
    Weight w2 = new Weight(1000.0, WeightUnit.GRAM);
    Weight w3 = new Weight(2.20462, WeightUnit.POUND);

    System.out.println("1kg == 1000g: " + w1.equals(w2)); // true
    System.out.println("1kg == ~2.20462lb: " + w1.equals(w3)); // true

    System.out.println("1kg to grams: " + w1.convertTo(WeightUnit.GRAM)); // 1000g
    System.out.println("1kg to pounds: " + w1.convertTo(WeightUnit.POUND)); // ~2.2lb

    System.out.println("1kg + 1000g (kg): " + w1.add(w2, WeightUnit.KILOGRAM)); // 2kg
    System.out.println("1kg + 1000g (g): " + w1.add(w2, WeightUnit.GRAM)); // 2000g
    }
}