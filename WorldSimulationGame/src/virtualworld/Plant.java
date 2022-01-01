package virtualworld;

import java.util.Random;

public class Plant extends Organism{

    Random r = new Random();


    public Plant(int x, int y){
        super(x,y);
        this.sign = 'P';
        this.species="Plant";
    }

    @Override
    public void Action(World world) {
        this.SetAge(this.getAge() + 1);
        if(r.nextInt(11) > 7){
            world.Create_child(this, -1, -1);
        }
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
