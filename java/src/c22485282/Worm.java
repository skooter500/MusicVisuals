package c22485282;

public class Worm {

        MyVisual mv;
        int xspacing = 8;   // Distance of each horizontal location 
        int w;              // Width of entire worm
        int maxworms = 2;   // Amount of worms to add together
    
        float theta = 0.0f; // Angle initialized for worm oscillation
        float[] amplitude = new float[maxworms];   // Stores height of worm
        float[] dx = new float[maxworms];          // Value for incrementing X
        float[] yvalues;                           // Stores height values of the worm 
    
        // Constructor for the Worm class, taking a MyVisual instance as input
        public Worm(MyVisual mv) {
            this.mv = mv;  //The passed MyVisual instance assigned to the local mv variable
            w = mv.width + 16; // Width of the worm visualization adjusted to the width of the sketch
            
            // // Initialize worm parameters
            for (int i = 0; i < maxworms; i++) {
                amplitude[i] = mv.random(100,120); // Random amplitudes set for each worm
                float period = mv.random(300,1000); // Random period set for each worm
                dx[i] = (mv.TWO_PI / period) * xspacing;
            } // End for
            yvalues = new float[w/xspacing];    // Num of vertical locations based on the width and xspacing
        } // End public worm()
        

    public void render() {
        // mv.hint(mv.DISABLE_DEPTH_TEST);
      
        // Calculate worm heights based on audio input
        calcWorm();

        // Render the worm visualization
        renderWorm();

        // mv.hint(mv.ENABLE_DEPTH_TEST);
    }
    
    void calcWorm() {
        
        theta += 0.02; // Increment theta for oscillation effect

        // Get the audio buffer from the MyVisual instance
        float[] audioBuffer = mv.getAudioBuffer().toArray();

        // Iterate over the yvalues array and update based on audio amplitude
        for (int i = 0; i < yvalues.length; i++) {
            float wormHeight = 0;
        
            // Calculate the worm height based on audio amplitude
            for (int j = 0; j < maxworms; j++) {
                // Use audio amplitude to modulate the worm's amplitude dynamically
                int index = (int) (mv.map(i, 0, yvalues.length - 1, 0, audioBuffer.length - 1)); // Map index to audioBuffer range
                float audioValue = audioBuffer[index];
            
               
                amplitude[j] = mv.map(audioValue, -1, 1, 20, 100); // Map audio amplitude to worm amplitude range

                if (j % 2 == 0) {
                    wormHeight += mv.sin(theta + (i * dx[j] / xspacing)) * amplitude[j];
                } else {
                    wormHeight += mv.cos(theta + (i * dx[j] / xspacing)) * amplitude[j];
                }
            } // End for
            yvalues[i] = wormHeight;
        }
    }
    

void renderWorm() {
    // Draw worm with an ellipse at each location
    mv.stroke(0);
    mv.strokeWeight(4);
    // Smoothly change colors
    float hue = 0;
    float hueIncrement = 255.0f / yvalues.length;
    for (int x = 0; x < yvalues.length; x++) {
        float y = mv.height / 2 + yvalues[x];
        float y2 = mv.height / 2 - yvalues[x];
        mv.fill(hue, 255, 255);
        mv.ellipse(x * xspacing, y, 100, 50);
        mv.fill((hue %255)-50, 255, 255);
        mv.ellipse(x * xspacing, y2, 65, 50); // Draw ellipse for second worm
        hue += hueIncrement;
    }
}
}


