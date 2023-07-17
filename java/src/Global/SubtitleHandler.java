package Global;
import processing.core.PApplet;
import java.util.ArrayList;


// SubtitleHandler.pde


public class SubtitleHandler {
    
    
    private PApplet p;
    private String filename;  

    public SubtitleHandler(PApplet p) {
        this.p = p;
        this.filename = filename;
    }


    public ArrayList<Subtitle> parseSrt(String filename) {
        ArrayList<Subtitle> subtitles = new ArrayList<Subtitle>();

        String[] lines = p.loadStrings(filename);
        int index = 1;
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].trim();
            if (line.matches("\\d+")) {
                index = Integer.parseInt(line);
            } else if (line.matches("\\d\\d:\\d\\d:\\d\\d,\\d\\d\\d --> \\d\\d:\\d\\d:\\d\\d,\\d\\d\\d")) {
                String[] times = line.split(" --> ");
                int start = parseTimecode(times[0]);
                int end = parseTimecode(times[1]);
                String text = "";
                i++;
                while (i < lines.length && !lines[i].trim().isEmpty()) {
                    text += lines[i] + "\n";
                    i++;
                }
                if (text.isEmpty()) {
                    i--;
                }
                subtitles.add(new Subtitle(index, start, end, text.trim()));
            }
        }

        return subtitles;
    }

    int parseTimecode(String timecode) {
        String[] parts = timecode.split(":|,");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);
        int milliseconds = Integer.parseInt(parts[3]);
        return hours * 3600000 + minutes * 60000 + seconds * 1000 + milliseconds;
    }

    String displaySubtitle(ArrayList<Subtitle> subtitles, int index) {
        Subtitle subtitle = subtitles.get(index);
        return subtitle.getIndex() + "\n" + subtitle.timestampToString() + "\n" + subtitle.getText();
    }
}
