package example;

public abstract class poly {
    MyVisual mv;

    public poly(MyVisual mv){
        this.mv = mv;
    }

    public abstract void render();
}