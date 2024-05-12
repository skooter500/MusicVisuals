package c22485282;

public class Balls {
    MyVisual mv;
    int xspacing = 8;   // Distance of each horizontal location 
    int w;              // Width of entire balls
    int maxballs = 2;   // Amount of balls to add together

    float theta = 0.0f; // Angle initialized for ball oscillation
    float[] amplitude = new float[maxballs];   // Stores height of ball
    float[] dx = new float[maxballs];          // Value for incrementing X
    float[] yvalues;                           // Stores height values of the ball 

    
    // Constructor for the ball class, taking a MyVisual instance as input
    public Balls(MyVisual mv) {
        this.mv = mv;  //The passed MyVisual instance assigned to the local mv variable
         w = mv.width + 16; // Width of the ball visualization adjusted to the width of the sketch

           
        // // Initialize ball parameters
        for (int i = 0; i < maxballs; i++) {
            amplitude[i] = mv.random(50,80); // Random amplitudes set for each ball
            float period = mv.random(200,1000); // Random period set for each ball
            dx[i] = (mv.TWO_PI / period) * xspacing;
            } // End for
            yvalues = new float[w/xspacing];    // Num of vertical locations based on the width and xspacing
        } // End public Balls()

        public void render() {
            mv.hint(mv.DISABLE_DEPTH_TEST);
            calcBalls();
            renderBalls();
            mv.hint(mv.ENABLE_DEPTH_TEST);
        } // End render()

        void calcBalls() {
            theta += 0.02;
    
            for (int i = 0; i < yvalues.length; i++) {
                float x = theta + (i * dx[0] / xspacing);
                float ballHeight = 0;
                for (int j = 0; j < maxballs; j++) {
                    // Calculate the influence of audio input on the ball's movement
                    float audioValue = mv.getAudioBuffer().get(i); // Get audio value at index i
                    float mappedAmplitude = mv.map(audioValue, 0, 1, 50, 200); // Map audio value to desired amplitude range
                    
                    if (j % 2 == 0) {
                        ballHeight += mv.sin(x) * amplitude[j] * mappedAmplitude;
                    } else {
                        ballHeight += mv.cos(x) * amplitude[j] * mappedAmplitude;
                    }
                    x += dx[j];
                }
                yvalues[i] =ballHeight;
            }
        } // End calcBalls()

        void renderBalls() {
            // Draw ball with an ellipse at each location
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
                mv.ellipse(x * xspacing, y2, 65, 50); // Draw ellipse for second ball
                hue += hueIncrement;
            } // End for
        } // End renderBalls()
    

} // End Balls()
