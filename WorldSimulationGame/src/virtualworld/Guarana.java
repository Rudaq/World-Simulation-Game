package virtualworld;

public class Guarana extends Plant{
    public Guarana(int x, int y)
    {
        super(x, y);
        this.sign = 'U';
        this.species = "Guarana";
    }

    @Override
    public boolean Collision(World world, Organism other) {
        other.SetStrength(other.getStrength() + 3);

        if(this.getStrength() >= other.getStrength()){
            return true;
        }
        return false;
    }
}
