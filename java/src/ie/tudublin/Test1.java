package ie.tudublin;
import c19444404.*;


public class Test1 extends Vision{

    
    public Test1(RyansVisual rv) {
        super(rv);
    }

    @Override
    public void render() {
        rv.fill(255);
        rv.ellipse(rv.width / 2, rv.height / 2, 100, 100);        
    }    
}

