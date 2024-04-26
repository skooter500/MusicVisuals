package C21325616;

import example.MyVisual;
import ie.tudublin.Heartbeat;

import java.util.*; 

import processing.core.PApplet;
import processing.core.PVector;

public class PointCube {

    private PVector[][] pointsOne;
    private PVector[][] pointsTwo;
    private PVector[][] pointsThree;
    private PVector[][] pointsFour;
    private PVector[][] pointsFive;
    private PVector[][] pointsSix;

    private int size = 0;
    Random rand = new Random();

    int[] Distortion = { 1, 2, 3, 4, 5, 5, 4, 3, 2, 1 };

    PointCube(int size)
    {
        pointsOne = new PVector[size][size];
        pointsTwo = new PVector[size][size];
        pointsThree = new PVector[size][size];
        pointsFour = new PVector[size][size];
        pointsFive = new PVector[size][size];
        pointsSix = new PVector[size][size];

        this.size = size;

        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                int distortion = Distortion[i] * Distortion[j];
                pointsOne[i][j] = new PVector(i * 10, -distortion, j * 10);
                pointsTwo[i][j] = new PVector(i * 10, size * 10 + distortion, j * 10);
                pointsThree[i][j] = new PVector(-distortion, i * 10, j * 10);
                pointsFour[i][j] = new PVector(size * 10 + distortion, i * 10, j * 10);
                pointsFive[i][j] = new PVector(i * 10, j * 10, -distortion);
                pointsSix[i][j] = new PVector(i * 10, j * 10, size * 10 + distortion);
            }
        }
    }

    public void update(Heartbeat mv)
    {
        mv.getAudioBuffer().size();

        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                int distortion = Distortion[i] * Distortion[j];
                pointsOne[i][j] = new PVector(i * 10, -(distortion + mv.constrain(mv.getAudioBuffer().get(i), 0.0f, 1.0f) * (rand.nextInt(100 - 10 + 1) + 10)), j * 10);
                pointsTwo[i][j] = new PVector(i * 10, size * 10 + (distortion + mv.constrain(mv.getAudioBuffer().get(i), 0.0f, 1.0f) * (rand.nextInt(100 - 10 + 1) + 10)), j * 10);
                pointsThree[i][j] = new PVector(-(distortion + mv.constrain(mv.getAudioBuffer().get(i), 0.0f, 1.0f) * (rand.nextInt(100 - 10 + 1) + 10)), i * 10, j * 10);
                pointsFour[i][j] = new PVector(size * 10 + (distortion + mv.constrain(mv.getAudioBuffer().get(i), 0.0f, 1.0f) * (rand.nextInt(100 - 10 + 1) + 10)), i * 10, j * 10);
                pointsFive[i][j] = new PVector(i * 10, j * 10, -(distortion + mv.constrain(mv.getAudioBuffer().get(i), 0.0f, 1.0f) * (rand.nextInt(100 - 10 + 1) + 10)));
                pointsSix[i][j] = new PVector(i * 10, j * 10, size * 10 + (distortion + mv.constrain(mv.getAudioBuffer().get(i), 0.0f, 1.0f) * (rand.nextInt(100 - 10 + 1) + 10)));
            }
        }
    }

    public void render(Heartbeat mv)
    {
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                mv.stroke(255);
                if(i - 1 > 0 && j - 1 > 0 && i + 1 < size && j + 1 < size)
                {
                    mv.line(pointsOne[i][j].x, pointsOne[i][j].y, pointsOne[i][j].z, pointsOne[i + 1][j].x, pointsOne[i + 1][j].y, pointsOne[i + 1][j].z);
                    mv.line(pointsOne[i][j].x, pointsOne[i][j].y, pointsOne[i][j].z, pointsOne[i - 1][j].x, pointsOne[i - 1][j].y, pointsOne[i - 1][j].z);
                    mv.line(pointsOne[i][j].x, pointsOne[i][j].y, pointsOne[i][j].z, pointsOne[i][j + 1].x, pointsOne[i][j + 1].y, pointsOne[i][j + 1].z);
                    mv.line(pointsOne[i][j].x, pointsOne[i][j].y, pointsOne[i][j].z, pointsOne[i][j - 1].x, pointsOne[i][j - 1].y, pointsOne[i][j - 1].z);

                    mv.line(pointsTwo[i][j].x, pointsTwo[i][j].y, pointsTwo[i][j].z, pointsTwo[i + 1][j].x, pointsTwo[i + 1][j].y, pointsTwo[i + 1][j].z);
                    mv.line(pointsTwo[i][j].x, pointsTwo[i][j].y, pointsTwo[i][j].z, pointsTwo[i - 1][j].x, pointsTwo[i - 1][j].y, pointsTwo[i - 1][j].z);
                    mv.line(pointsTwo[i][j].x, pointsTwo[i][j].y, pointsTwo[i][j].z, pointsTwo[i][j + 1].x, pointsTwo[i][j + 1].y, pointsTwo[i][j + 1].z);
                    mv.line(pointsTwo[i][j].x, pointsTwo[i][j].y, pointsTwo[i][j].z, pointsTwo[i][j - 1].x, pointsTwo[i][j - 1].y, pointsTwo[i][j - 1].z);

                    mv.line(pointsThree[i][j].x, pointsThree[i][j].y, pointsThree[i][j].z, pointsThree[i + 1][j].x, pointsThree[i + 1][j].y, pointsThree[i + 1][j].z);
                    mv.line(pointsThree[i][j].x, pointsThree[i][j].y, pointsThree[i][j].z, pointsThree[i - 1][j].x, pointsThree[i - 1][j].y, pointsThree[i - 1][j].z);
                    mv.line(pointsThree[i][j].x, pointsThree[i][j].y, pointsThree[i][j].z, pointsThree[i][j + 1].x, pointsThree[i][j + 1].y, pointsThree[i][j + 1].z);
                    mv.line(pointsThree[i][j].x, pointsThree[i][j].y, pointsThree[i][j].z, pointsThree[i][j - 1].x, pointsThree[i][j - 1].y, pointsThree[i][j - 1].z);

                    mv.line(pointsFour[i][j].x, pointsFour[i][j].y, pointsFour[i][j].z, pointsFour[i + 1][j].x, pointsFour[i + 1][j].y, pointsFour[i + 1][j].z);
                    mv.line(pointsFour[i][j].x, pointsFour[i][j].y, pointsFour[i][j].z, pointsFour[i - 1][j].x, pointsFour[i - 1][j].y, pointsFour[i - 1][j].z);
                    mv.line(pointsFour[i][j].x, pointsFour[i][j].y, pointsFour[i][j].z, pointsFour[i][j + 1].x, pointsFour[i][j + 1].y, pointsFour[i][j + 1].z);
                    mv.line(pointsFour[i][j].x, pointsFour[i][j].y, pointsFour[i][j].z, pointsFour[i][j - 1].x, pointsFour[i][j - 1].y, pointsFour[i][j - 1].z);

                    mv.line(pointsFive[i][j].x, pointsFive[i][j].y, pointsFive[i][j].z, pointsFive[i + 1][j].x, pointsFive[i + 1][j].y, pointsFive[i + 1][j].z);
                    mv.line(pointsFive[i][j].x, pointsFive[i][j].y, pointsFive[i][j].z, pointsFive[i - 1][j].x, pointsFive[i - 1][j].y, pointsFive[i - 1][j].z);
                    mv.line(pointsFive[i][j].x, pointsFive[i][j].y, pointsFive[i][j].z, pointsFive[i][j + 1].x, pointsFive[i][j + 1].y, pointsFive[i][j + 1].z);
                    mv.line(pointsFive[i][j].x, pointsFive[i][j].y, pointsFive[i][j].z, pointsFive[i][j - 1].x, pointsFive[i][j - 1].y, pointsFive[i][j - 1].z);

                    mv.line(pointsSix[i][j].x, pointsSix[i][j].y, pointsSix[i][j].z, pointsSix[i + 1][j].x, pointsSix[i + 1][j].y, pointsSix[i + 1][j].z);
                    mv.line(pointsSix[i][j].x, pointsSix[i][j].y, pointsSix[i][j].z, pointsSix[i - 1][j].x, pointsSix[i - 1][j].y, pointsSix[i - 1][j].z);
                    mv.line(pointsSix[i][j].x, pointsSix[i][j].y, pointsSix[i][j].z, pointsSix[i][j + 1].x, pointsSix[i][j + 1].y, pointsSix[i][j + 1].z);
                    mv.line(pointsSix[i][j].x, pointsSix[i][j].y, pointsSix[i][j].z, pointsSix[i][j - 1].x, pointsSix[i][j - 1].y, pointsSix[i][j - 1].z);
                }
                mv.point(pointsOne[i][j].x, pointsOne[i][j].y, pointsOne[i][j].z);
                mv.point(pointsTwo[i][j].x, pointsTwo[i][j].y, pointsTwo[i][j].z);
                mv.point(pointsThree[i][j].x, pointsThree[i][j].y, pointsThree[i][j].z);
                mv.point(pointsFour[i][j].x, pointsFour[i][j].y, pointsFour[i][j].z);
                mv.point(pointsFive[i][j].x, pointsFive[i][j].y, pointsFive[i][j].z);
                mv.point(pointsSix[i][j].x, pointsSix[i][j].y, pointsSix[i][j].z);
            }
        }
    }

}
