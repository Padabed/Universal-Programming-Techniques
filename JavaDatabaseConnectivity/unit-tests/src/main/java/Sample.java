import java.util.Date;
import java.util.Objects;

public class Sample {

    private String _property2;
    private Date _date1;
    private Integer _int1;
    private int _int2;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sample sample = (Sample) o;
        return _int2 == sample._int2 &&
                Objects.equals(_date1, sample._date1) &&
                Objects.equals(_int1, sample._int1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_date1, _int1, _int2);
    }
}
