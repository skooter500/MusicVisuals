package example.parts;

import example.MyVisual;

public class PixelDisintegrationVisual {

    MyVisual mv;
    int ballX;
    int ballY;

    public PixelDisintegrationVisual(MyVisual mv){
        this.mv = mv;
        ballX = mv.height/2;
        ballY = mv.width/2;
    }

    public void render(){
        float squareSize = 20;
            mv.noStroke();
            for(int i = 0 ; i < mv.getAudioBuffer().size() ; i ++) {
                for (int j = 0; j < 50; j++) {
                    float distX = ballX - i * squareSize;
                    float distY = ballY - j * squareSize;
                    float dist = mv.sqrt(distX * distX + distY * distY);
                    float noiseValue = mv.noise(dist / 50, mv.frameCount / 50.0f);
                    float alphaValue = mv.map(noiseValue, 0, 1, 0, 255);
                    mv.fill(0, 255, 255, alphaValue);
                    mv.rect(i * squareSize, j * squareSize, squareSize, squareSize);
                }
            }
    }
}
