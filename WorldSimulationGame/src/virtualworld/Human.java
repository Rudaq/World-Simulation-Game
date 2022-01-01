package virtualworld;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.Vector;

public class Human extends Animal{
    private boolean ability_is_active = false;
    private int cooldown = 0;

    public Human(int x, int y)
    {
        super(x, y);
        this.sign = 'H';
        this.species = "Human";
        this.strength = 5;
        this.initiative = 4;
    }

    public boolean is_Ability_active(){ return ability_is_active; }

    public void Action(World world){
        Point next_position = this.getPosition();

        //obsluga klawiszy
        if(world.getDirection() == "Up")
            next_position.x--;
        if(world.getDirection() == "Down")
            next_position.x++;
        if(world.getDirection() == "Left")
            next_position.y--;
        if(world.getDirection() == "Right")
            next_position.y++;
        if(world.getDirection() == "Power")
            if (this.cooldown == 0)
            {
                ability_is_active = true;

                cooldown = 10;
            }

        if (cooldown == 5)
        {
            ability_is_active = false;
        }
        if (cooldown > 0)
        {
            cooldown--;
        }
        if (ability_is_active)
        {
            System.out.print("Human Ability is active!!");
            System.out.print("\n");

            String com =" Human Superpower is active!!";
            world.addComment(com);
        }

        this.SetAge(this.getAge() + 1);

        if ((next_position.x < 0 || next_position.x >= world.getSize_x()) || (next_position.y < 0 || next_position.y >= world.getSize_y()) || (next_position.x == this.position.x && next_position.y == this.position.y))
        {
            return;
        }

        if(world.getWorldMap().get(next_position.x).get(next_position.y) == null){

            this.prev_position = this.position; // this->prev_position = this->position;
            this.position = next_position;      // this->position = next_position;

            world.getWorldMap().get(this.prev_position.x).set(this.prev_position.y, null);
            world.getWorldMap().get(this.position.x).set(this.position.y, this);
        }
        else world.Handle_collision(this, world.getWorldMap().get(next_position.x).get(next_position.y));
    }

}
