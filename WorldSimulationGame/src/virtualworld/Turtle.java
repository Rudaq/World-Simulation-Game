package virtualworld;

import java.awt.*;

public class Turtle extends Animal{

    private boolean defending = true;

    public Turtle(int x, int y)
    {
        super(x,y);
        this.sign = 'T';
        this.species = "Turtle";
        this.strength = 2;
        this.initiative = 1;
    }

    @Override
    public void Action(World world) {
        this.SetAge(this.getAge() + 1);
        if(r.nextInt() % 5 == 4){
            defending = false;
            Point next_position = world.Get_Neighbour_position(this);

            if(world.getWorldMap().get(next_position.x).get(next_position.y) == null){
                this.prev_position = this.position;
                this.position = next_position;

                world.getWorldMap().get(this.prev_position.x).set(this.prev_position.y, null);
                world.getWorldMap().get(this.position.x).set(this.position.y, this);

            }
            else{
                world.Handle_collision(this, world.getWorldMap().get(next_position.x).get(next_position.y));
            }
            defending = true;
        }
    }
}
