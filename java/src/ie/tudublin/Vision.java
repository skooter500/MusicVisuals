package ie.tudublin;

import ryan.RyansVisual;
import processing.core.*;

public abstract class Vision {
    RyansVisual rv;


    protected Vision(RyansVisual rv)
    {
        this.rv = rv;
    }

    public abstract void render();
}
