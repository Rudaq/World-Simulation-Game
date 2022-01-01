package virtualworld;

public class Heracleum extends Plant{
    public Heracleum(int x, int y)
    {
        super(x, y);
        this.sign = 'X';
        this.species = "Heracleum";
        this.strength = 10;
    }

    @Override
    public void Action(World world) {
        this.SetAge(this.getAge() + 1);
        int x = this.position.x - 1;
        int y = this.position.y - 1;

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if( !(x<0 || x>=world.getSize_x())  && !(y<0 || y>=world.getSize_y())   ){
                    if(world.getWorldMap().get(x).get(y) != null){
                        if(world.getWorldMap().get(x).get(y).getInitiative() != 0 && world.getWorldMap().get(x).get(y).getSign() != 'C'){
                            world.getWorldMap().get(x).get(y).SetAge(-1);
                            System.out.print(this.species + " kill " + world.getWorldMap().get(x).get(y).GetSpecies() + "\n");
                            world.getWorldMap().get(x).set(y, null);
                            //world.getWorldMap().set(x,null).set(y,null);    //???? world.Get_world_map [x][y] = null; Odp Tak
                        }
                    }
                }
                y++;
            }
            y-=3;
            x++;
        }
        if(r.nextInt()%11 >7 )  world.Create_child(this, -1, -1);
    }
}
