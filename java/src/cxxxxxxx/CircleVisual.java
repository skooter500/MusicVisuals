package cxxxxxxx;

public class CircleVisual
{
    MyVisual mv;
    float w;
    float cx, cy;

    CircleVisual(MyVisual mv)
    {
        this.mv = mv;
        w = mv.width / 2;
        cx = mv.width / 2;
        cy = mv.height / 2;
    }

    public void render()
    {
        mv.ellipse(cx, cy, mv.lerpedAmplitude, mv.lerpedAmplitude);
    }
}