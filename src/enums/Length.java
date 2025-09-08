package enums;

import java.util.Objects;

public class Length {
    private  long amount;
    private  LengthUnit unit;

    public Length(long amount, LengthUnit unit) {
        this.amount = amount;
        this.unit = Objects.requireNonNull(unit);
    }

    public long getAmount() { return amount; }
    public LengthUnit getUnit() { return unit; }

    long toMm() {
        return unit.toMm(amount);
    }

    public Length convert(LengthUnit target) {
        long mm = toMm();
        return new Length(target.fromMm(mm), target);
    }

    public Length plus(Length other) {
        long sumMm = this.toMm() + other.toMm();
        long resAmount = this.unit.fromMm(sumMm);
        return new Length(resAmount, this.unit);
    }

    public Length minus(Length other) {
        long diffMm = this.toMm() - other.toMm();
        long resAmount = this.unit.fromMm(diffMm);
        return new Length(resAmount, this.unit);
    }
    
    @Override
    public String toString() {
        return String.valueOf(amount) + unit.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Length)) return false;
        Length other = (Length) o;
        return this.toMm() == other.toMm();
    }

    @Override
    public int hashCode() {
        return Long.hashCode(this.toMm());
    }
}
