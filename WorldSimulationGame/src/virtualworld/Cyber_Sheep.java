package virtualworld;

import java.awt.*;
import java.lang.Math;

public class Cyber_Sheep extends Animal {
    public Cyber_Sheep(int x, int y) {
        super(x, y);
        this.sign = 'C';
        this.species = "Cyber Sheep";
        this.strength = 11;
        this.initiative = 4;
    }

    private boolean is_Heracleum_Exist = true;
    private Point near_heracleum_position = new Point(-1,-1);

    @Override
    public void Action(World world) {
        this.SetAge(this.getAge() + 1);

        Find_nearest_heracleum(world);

        Point next_position;

        if(this.is_Heracleum_Exist)
        {
            next_position = position.getLocation();
            if(this.position.x > near_heracleum_position.x)      next_position.x--;
            else if(this.position.x < near_heracleum_position.x) next_position.x++;
            if(this.position.y > near_heracleum_position.y)      next_position.y--;
            else if(this.position.y < near_heracleum_position.y) next_position.y++;
        }
        else
            next_position = world.Get_Neighbour_position(this);


        if (world.getWorldMap().get(next_position.x).get(next_position.y) == null) {
            this.prev_position = this.position;
            this.position = next_position;

            world.getWorldMap().get(this.prev_position.x).set(this.prev_position.y, null);
            world.getWorldMap().get(this.position.x).set(this.position.y, this);

        } else {
            world.Handle_collision(this, world.getWorldMap().get(next_position.x).get(next_position.y));
        }
    }

    private void Find_nearest_heracleum(World world)
    {
        this.is_Heracleum_Exist = false;
        int distance = 999;

        for(int i = 0; i < world.getSize_x(); i++)
        {
            for(int j = 0; j < world.getSize_y(); j++)
            {
                if(world.getWorldMap().get(i).get(j) != null) {
                    if (world.getWorldMap().get(i).get(j).getSign() == 'X') {
                        this.is_Heracleum_Exist = true;
                        int temp = Math.abs(this.position.x - i) + Math.abs(this.position.y - j);
                        if (temp < distance) {
                            distance = temp;
                            near_heracleum_position = world.getWorldMap().get(i).get(j).getPosition();
                        }
                    }
                }
            }
        }

        if(this.is_Heracleum_Exist == false)
            this.near_heracleum_position.setLocation(-1, -1);

    }
}