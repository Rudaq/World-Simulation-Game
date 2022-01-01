package virtualworld;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Antelope extends Animal {
    public Antelope(int x, int y) {
        super(x, y);
        this.sign = 'A';
        this.species = "Antelope";
        this.strength = 4;
        this.initiative = 4;
    }


    @Override
    public void Action(World world) {
        this.SetAge(this.getAge() + 1);
        boolean escape = new Random().nextBoolean();    //zakres miedzy true a false

        for(int i = 0; i < 2;  i++)
        {
            Point next_position = world.Get_Neighbour_position(this);

            if(world.getWorldMap().get(next_position.x).get(next_position.y) == null){

                this.prev_position = this.position; // this->prev_position = this->position;
                this.position = next_position;      // this->position = next_position;

                world.getWorldMap().get(this.prev_position.x).set(this.prev_position.y, null);
                world.getWorldMap().get(this.position.x).set(this.position.y, this);
            }
            else
            {
                if(escape)
                {
                    world.Handle_collision(this, world.getWorldMap().get(next_position.x).get(next_position.y));
                    break;
                }
                else
                {
                    Organism escaped_from = world.getWorldMap().get(next_position.x).get(next_position.y);
                    for(int j = 0; j < 9; j++)
                    {
                        next_position = world.Get_Neighbour_position(escaped_from);
                        if(world.getWorldMap().get(next_position.x).get(next_position.y) == null)
                        {
                            this.prev_position = this.position; // this->prev_position = this->position;
                            this.position = next_position;      // this->position = next_position;
                            world.getWorldMap().get(this.prev_position.x).set(this.prev_position.y, null);
                            world.getWorldMap().get(this.position.x).set(this.position.y, this);
                            break;
                        }
                    }
                    break;
                }
            }//if-else
        }//for
    }//action
}//class
