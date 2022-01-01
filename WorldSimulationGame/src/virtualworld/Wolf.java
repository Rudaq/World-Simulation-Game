package virtualworld;

public class Wolf extends Animal{
    public Wolf(int x, int y){
        super(x,y);
        this.sign = 'W';
        this.species = "Wolf";
        this.strength = 9;
        this.initiative = 5;
    }
}
