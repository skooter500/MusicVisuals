package C21325616;

import example.MyVisual;
import ie.tudublin.Heartbeat;

import java.util.*; 

import processing.core.PVector;

public class ParticleSystem {

    private PVector[] particles;
    int size = 0;
    Random rand = new Random();

    ParticleSystem(int size)
    {
        particles = new PVector[size * size];

        this.size = size;

        for(int i = 0; i < size * size; i++)
        {
            particles[i] = new PVector((rand.nextInt(1500 + 1500 + 1) - 1500), (rand.nextInt(1500  + 1500 + 1) - 1500), (rand.nextInt(1500  + 1500 + 1) - 1500));
        }
    }

    // center is 100, 100, 100
    public void update()
    {
        for(int i = 0; i < size * size; i++)
        {
            if(particles[i].x == 50 && particles[i].y == 50 && particles[i].z == 50)
            {
                particles[i] = new PVector((rand.nextInt(1500  + 1500 + 1) - 1500), (rand.nextInt(1500  + 1500 + 1) - 1500), (rand.nextInt(1500  + 1500 + 1) - 1500));
            } else {
                if(particles[i].x > 50)
                {
                    particles[i].x -= 1;
                } else { particles[i].x += 1; }
                
                if(particles[i].y > 50)
                {
                    particles[i].y -= 1;
                } else { particles[i].y += 1; }

                if(particles[i].z > 50)
                {
                    particles[i].z -= 1;
                } else { particles[i].z += 1; }
            }
        }
    }

    public void render(Heartbeat mv)
    {
        for(int i = 0; i < size * size; i++)
        {
            mv.stroke(255);
            mv.point(particles[i].x, particles[i].y, particles[i].z);
        }
    }
}
