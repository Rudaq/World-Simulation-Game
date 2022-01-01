package virtualworld;

public class Sheep extends Animal{
    public Sheep(int x, int y)
    {
        super(x, y);
        this.sign = 'S';
        this.species = "Sheep";
        this.strength = 4;
        this.initiative = 4;
    }
}
