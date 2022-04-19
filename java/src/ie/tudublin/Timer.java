package ie.tudublin;

public class Timer {
    int start = 0;
    boolean running = false;
    SongVisuals sv;

    public Timer(SongVisuals sv){
        this.sv = sv;
    }

    public void start(){
        start = sv.millis();
        running = true;

    }

    public void stop(){
        running = false;
    }
    public int elapsedTime(){
        int elapsed = (sv.millis()-start);

        return elapsed;
    }

    public int seconds(){
        return elapsedTime() / 1000;
    }
}
