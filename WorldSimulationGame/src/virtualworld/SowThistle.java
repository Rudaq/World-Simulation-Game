package virtualworld;

public class SowThistle extends Plant{
    public SowThistle(int x, int y)
    {
        super(x, y);
        this.sign = '*';
        this.species = "SowThistle";
    }

    @Override
    public void Action(World world) {
        this.SetAge(this.getAge()+1);
        for(int i=0; i<3;i++){
            if(r.nextInt() % 11 > 7){
                world.Create_child(this, -1, -1);
            }
        }
    }
}
