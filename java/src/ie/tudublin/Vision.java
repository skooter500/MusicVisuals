package ie.tudublin;

import c19444404.RyansVisual;
import processing.core.*;
// vision method to use polymorhpism to access all visuals in my RyansVisual
public abstract class Vision { 
    RyansVisual rv;


    protected Vision(RyansVisual rv)
    {
        this.rv = rv;
    }

    public abstract void render();
}
