package virtualworld;

import java.awt.*;

public abstract class Organism implements Comparable<Organism>{

    protected String species;
    protected char sign;
    protected int strength, initiative,age;

    protected int pos_x,pos_y;
    protected Point position = new Point();
    protected Point prev_position = new Point();
    protected World world;

//nwm czy dobrze
    public Organism(int x, int y, World world){
        this.SetSpecies(" ");
        this.SetSign(' ');
        this.SetStrength(0);
        this.SetInitiative(0);
        this.SetAge(0);
        this.setPos_x(x);
        this.setPos_y(y);
        this.setPosition(position);;
        this.setWorld(world);       //referencja do swiata, w którym znajduje się organizm
    }
    public Organism(int x, int y){
        this.pos_x = x;
        this.pos_y = y;
        this.position = new Point(x,y);
        this.prev_position = new Point(position);
    }

    public boolean greaterThan (final Organism other){
        if(initiative == other.initiative){
            return age > other.age;
        }
        return initiative > other.initiative;
    }
    public abstract void Action(World world);
    public abstract boolean Collision(World world, Organism other);

    public String GetSpecies() { return species; }
    public final void SetSpecies(String species) { this.species = species; }

    public char getSign() { return sign; }
    public final void SetSign(char sign) { this.sign = sign; }

    public int getStrength() { return strength; }
    public final void SetStrength (int strength) { this.strength = strength; }

    public int getInitiative() { return initiative;  }
    public final void SetInitiative(int initiative) { this.initiative = initiative; }

    public int getAge() { return age; }
    public final void SetAge(int age) { this.age = age; }

    public int getPos_x() {  return pos_x;   }
    public final void setPos_x(int pos_x) { this.pos_x = pos_x;  }

    public int getPos_y() { return pos_y; }
    public final void setPos_y(int pos_y) { this.pos_y = pos_y; }


    public Point getPosition() { return new Point(position); }
    public void setPosition(Point position) { this.position = position; }

    public World getWorld() { return world; }
    public final void setWorld(World world) { this.world = world; }


    /*
    bool Organism::operator > (const Organism& other)
    {
            if (initiative == other.initiative)
            {
                return age > other.age;
            }
        return initiative > other.initiative;
    }
    */

    @Override
    public int compareTo(Organism o)
    {
        if(this.getInitiative() == o.getInitiative())
        {
            return Integer.compare(this.getAge(), o.getAge());
        }
        return Integer.compare(this.getInitiative(), o.getInitiative());
    }
}