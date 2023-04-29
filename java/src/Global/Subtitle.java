package Global;

public class Subtitle {
    private int index;
    private int start;
    private int end;
    private String text;

    Subtitle(int index, int start, int end, String text) {
        this.index = index;
        this.start = start;
        this.end = end;
        this.text = text;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    String timestampToString() {
        int startHours = start / 3600000;
        int startMinutes = (start / 60000) % 60;
        int startSeconds = (start / 1000) % 60;
        int startMilliseconds = start % 1000;
        int endHours = end / 3600000;
        int endMinutes = (end / 60000) % 60;
        int endSeconds = (end / 1000) % 60;
        int endMilliseconds = end % 1000;
        return String.format("%02d:%02d:%02d,%03d --> %02d:%02d:%02d,%03d", startHours, startMinutes, startSeconds,
                startMilliseconds, endHours, endMinutes, endSeconds, endMilliseconds);
    }
}
