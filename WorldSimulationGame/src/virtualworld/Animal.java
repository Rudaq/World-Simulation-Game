package virtualworld;
import virtualworld.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Animal extends Organism{

    Random r = new Random();

    public Animal(int x, int y){
        super(x,y);
        this.sign = 'A';
        this.species="Animal";
        this.strength = r.nextInt(10)  + 1;
    }

    //Error
    @Override
    public void Action(World world) {

        Point next_position = world.Get_Neighbour_position(this);
        this.SetAge(this.getAge() + 1);

        if(world.getWorldMap().get(next_position.x).get(next_position.y) == null){

            this.prev_position = this.position; // this->prev_position = this->position;
            this.position = next_position;      // this->position = next_position;

            world.getWorldMap().get(this.prev_position.x).set(this.prev_position.y, null);
            world.getWorldMap().get(this.position.x).set(this.position.y, this);


        }
        else world.Handle_collision(this, world.getWorldMap().get(next_position.x).get(next_position.y));

    }

    @Override
    public boolean Collision(World world, Organism other) {
        if (this.getStrength() >= other.getStrength())
        {
            return true;
        }
        return false;
    }
}
