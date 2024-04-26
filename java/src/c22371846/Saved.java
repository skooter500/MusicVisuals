package c22371846;

import ie.tudublin.*;


public class Saved 
{
    Graph G;
    Animation A;
    Heartbeat HB;

    public Saved(Heartbeat HB) {
        this.HB = HB;
        //this.A = new Animation();
       // this.G = new Graph();
    }

    public void render() {
        HB.background(0);
        A.render();
        G.render();
    }
}

