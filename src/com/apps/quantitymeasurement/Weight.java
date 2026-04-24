package com.apps.quantitymeasurement;

public class Weight {

    private final double value;
    private final WeightUnit unit;

    public Weight(double value, WeightUnit unit) {
        if (unit == null || !Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid input");
        }
        this.value = value;
        this.unit = unit;
    }

    private double toBase() {
        return unit.convertToBaseUnit(value);
    }

    public Weight convertTo(WeightUnit targetUnit) {
        double base = this.toBase();
        return new Weight(targetUnit.convertFromBaseUnit(base), targetUnit);
    }

    public Weight add(Weight other) {
        double sum = this.toBase() + other.toBase();
        return new Weight(unit.convertFromBaseUnit(sum), unit);
    }

    public Weight add(Weight other, WeightUnit targetUnit) {
        double sum = this.toBase() + other.toBase();
        return new Weight(targetUnit.convertFromBaseUnit(sum), targetUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Weight other = (Weight) obj;
        return Double.compare(this.toBase(), other.toBase()) == 0;
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}