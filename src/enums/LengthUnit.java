package enums;

public enum LengthUnit {
    MM(1), CM(10), M(1000);

    private final long mmPerUnit;

    LengthUnit(long mmPerUnit) {
        this.mmPerUnit = mmPerUnit;
    }

    long toMm(long amount) {
        return amount * mmPerUnit;
    }

    long fromMm(long mm) {
        return mm / mmPerUnit; 
    }

    public double between(Length from, Length to) {
        long diffMm = to.toMm() - from.toMm();
        return diffMm / (double) mmPerUnit;
    }
}
