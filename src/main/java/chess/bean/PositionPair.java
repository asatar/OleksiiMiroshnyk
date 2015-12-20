package chess.bean;


/**
 * Class that represent a move - consist of two positions. Like e2 e4
 */
public class PositionPair {
    private Position start;
    private Position end;

    public PositionPair(Position start, Position end) {
        this.start = start;
        this.end = end;
    }

    public PositionPair(String b2, String c2) {
        this.start = new Position(b2);
        this.end = new Position(c2);
    }

    public Position getEnd() {
        return end;
    }

    public void setEnd(Position end) {
        this.end = end;
    }

    public Position getStart() {
        return start;
    }

    public void setStart(Position start) {
        this.start = start;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PositionPair that = (PositionPair) o;

        if (start != null ? !start.equals(that.start) : that.start != null) return false;
        return end != null ? end.equals(that.end) : that.end == null;

    }

    @Override
    public int hashCode() {
        int result = start != null ? start.hashCode() : 0;
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "["+start+","+end+"]";
    }
}
