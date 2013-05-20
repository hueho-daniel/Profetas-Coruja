package datatype;

import com.google.common.base.Objects;
import org.joda.time.LocalDate;

import java.io.Serializable;

public final class SimpleDate implements Serializable {

    public final static int TRACKS_ONLY_YEAR = 1;
    public final static int TRACKS_YEAR_AND_MONTH = 2;
    public final static int TRACKS_FULL_DATE = 3;

    private final LocalDate delegate;

    public SimpleDate(int year, int month, int day) {
        this(year, month, day, TRACKS_FULL_DATE);
    }

    public SimpleDate(int year, int month) {
        this(year, month, NOT_DEFINED, TRACKS_YEAR_AND_MONTH);
    }

    public SimpleDate(int year) {
        this(year, NOT_DEFINED, NOT_DEFINED, TRACKS_ONLY_YEAR);
    }

    public String format() {
        switch (dateMode) {
            case TRACKS_FULL_DATE:
                return delegate.toString("yyyy-MM-dd");
            case TRACKS_YEAR_AND_MONTH:
                return delegate.toString("yyyy-MM");
            case TRACKS_ONLY_YEAR:
                return delegate.toString("yyyy");
            default:
                throw new RuntimeException("Bad-formed or corrupted instance of SimpleDate");
        }
    }

    public int tracks() {
        return dateMode;
    }

    public int getYear() {
        return delegate.getYear();
    }

    public int getMonth() {
        if (dateMode == TRACKS_ONLY_YEAR) {
            throw new IllegalAccessError("Instance doesn't track month");
        } else {
            return delegate.getMonthOfYear();
        }
    }

    public int getDay() {
        if (dateMode == TRACKS_ONLY_YEAR || dateMode == TRACKS_YEAR_AND_MONTH) {
            throw new IllegalAccessError("Instance doesn't track days");
        } else {
            return delegate.getDayOfMonth();
        }
    }

    public static SimpleDate parse(String strDate) throws IllegalArgumentException {
        return new SimpleDate(LocalDate.parse(strDate));
    }

    public static SimpleDate getToday() {
        return new SimpleDate(LocalDate.now());
    }

    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        } else if (anObject instanceof SimpleDate) {
            SimpleDate aDate = (SimpleDate) anObject;
            return this.tracks() == aDate.tracks() && this.delegate.equals(aDate.delegate);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.dateMode, this.delegate);
    }

    public String toString() {
        return format();
    }

    /*
     * Sessão privada
     */
    private static final long serialVersionUID = 8790883863690026543L;

    private final int dateMode;

    private final static short NOT_DEFINED = 1;

    private SimpleDate(int year, int month, int day, int dateMode) {
        this.delegate = new LocalDate(year, month, day);
        this.dateMode = dateMode;
    }

    private SimpleDate(LocalDate date) {
        this.dateMode = TRACKS_FULL_DATE;
        this.delegate = date;
    }

}
