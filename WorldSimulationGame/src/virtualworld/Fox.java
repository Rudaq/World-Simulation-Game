package virtualworld;

import java.awt.*;

public class Fox extends Animal{
    public Fox(int x, int y)
    {
        super(x, y);
        this.sign = 'F';
        this.species = "Fox";
        this.strength = 3;
        this.initiative = 7;
    }

    @Override
    public void Action(World world) {
        this.SetAge(this.getAge() + 1);
        Point next_position = world.Get_Neighbour_position(this);

        if(world.getWorldMap().get(next_position.x).get(next_position.y) == null){
            this.prev_position = this.position;
            this.position = next_position;

            world.getWorldMap().get(this.prev_position.x).set(this.prev_position.y, null);
            world.getWorldMap().get(this.position.x).set(this.position.y, this);

        }
        else if(world.getWorldMap().get(next_position.x).get(next_position.y).getStrength() > this.getStrength()){/*do nothing*/  }
        else world.Handle_collision(this,world.getWorldMap().get(next_position.x).get(next_position.y));

    }
}
