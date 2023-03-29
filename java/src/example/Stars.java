package example;


import processing.core.*;



public class Stars 
{
    MyVisual mv;
    float cy = 0;

    public Stars(MyVisual mv)
    {
        this.mv = mv;
        cy = this.mv.height / 2;
    }



    public void render()
    {
        // for(int i = 0 ; i < mv.getAudioBuffer().size() ; i ++)
        // {
        //     mv.pushMatrix();
        //     mv.fill(255,255,255);
        //     mv.translate(mv.width,mv.height);
        //     mv.rotate(mv.frameCount);
        //     mv.rect(100, 100, 50, 70, 30); 
        //     mv.popMatrix();
        // }

        // float[] x = new float[100];
        // float[] y = new float[100];
        // float[] speed = new float[100];

        // mv.colorMode(PApplet.HSB);
        // for(int i = 0 ; i < mv.getAudioBuffer().size() ; i ++)
        // {
        //     while(i < 100) {  
        //       x[i] = mv.random(0, mv.width);
        //       y[i] = mv.random(0, mv.height);
        //       speed[i] = mv.random(1, 5);
        //       i = i + 1;
        // }


    }

}