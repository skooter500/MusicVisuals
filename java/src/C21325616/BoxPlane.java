package C21325616;

import example.MyVisual;
import ie.tudublin.Heartbeat;

import java.util.*; 

import processing.core.PApplet;
import processing.core.PVector;

enum Side {
    PositiveX,
    PositiveY,
    PositiveZ,
    NegativeX,
    NegativeY,
    NegativeZ,
}

public class BoxPlane {

    Box[][] plane = new Box[20][20];
    Random rand = new Random();

    Side currentSide;

    PVector origin;

    public void init(Side side)
    {
        this.currentSide = side;

        for(int i = 0; i < 20; i++)
        {
            for(int j = 0; j < 20; j++)
            {
                plane[i][j] = new Box();
                plane[i][j].setDimension(new PVector(20, 20, 20));

                switch(side)
                {
                    case PositiveX:
                        plane[i][j].setPosition(new PVector(i * 20,j*20, 0));
                        break;
                        
                    case PositiveY:
                        plane[i][j].setPosition(new PVector(i * 20,0, j*20));
                        break;

                    case PositiveZ:
                        plane[i][j].setPosition(new PVector( 0 , i * 20, j*20));
                        break;

                    case NegativeX:
                        plane[i][j].setPosition(new PVector(i * 20,j*20, 400));
                        break;

                    case NegativeY:
                        plane[i][j].setPosition(new PVector(i * 20, 400, j*20));
                        break;

                    case NegativeZ:
                        plane[i][j].setPosition(new PVector(400,i * 20, j*20));
                        break;
                }
            }
        }
    }

    public void update(Heartbeat mv)
    {
        mv.getAudioBuffer().size();

        for(int i = 0; i < 20; i++)
        {
            for(int j = 0; j < 20; j++)
            {
                switch(currentSide)
                {
                    case PositiveX:
                        plane[i][j].setDimension(new PVector(20, 20,(mv.getAudioBuffer().get(i) * (rand.nextInt(500 - 150 + 1) + 150)) +  20));
                        break;
                        
                    case PositiveY:
                        plane[i][j].setDimension(new PVector(20, (mv.getAudioBuffer().get(i) * (rand.nextInt(500 - 150 + 1) + 150)) + 20, 20));
                        break;

                    case PositiveZ:
                        plane[i][j].setDimension(new PVector((mv.getAudioBuffer().get(i) * (rand.nextInt(500 - 150 + 1) + 150)) + 20, 20, 20));
                        break;

                    case NegativeX:
                        plane[i][j].setDimension(new PVector(20, 20, (mv.getAudioBuffer().get(i) * (rand.nextInt(500 - 150 + 1) + 150)) +  20));
                        break;

                    case NegativeY:
                        plane[i][j].setDimension(new PVector(20, (mv.getAudioBuffer().get(i) * (rand.nextInt(500 - 150 + 1) + 150)) + 20, 20));
                        break;

                    case NegativeZ:
                        plane[i][j].setDimension(new PVector((mv.getAudioBuffer().get(i) * (rand.nextInt(500 - 150 + 1) + 150)) + 20, 20, 20));
                        break;
                }

        
            }
        }
    }

    public void render(Heartbeat mv)
    {
        for(int i = 0; i < 20; i++)
        {
            for(int j = 0; j < 20; j++)
            {
                plane[i][j].render(mv);
            }
        }
    }

}
