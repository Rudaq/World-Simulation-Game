package virtualworld;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.Arrays;
import java.util.Scanner;

public class World {

    protected int size_x, size_y;

    private ArrayList<Organism> Animals = new ArrayList<Organism>();            //lista wszystkich organizmow
    private ArrayList<ArrayList<Organism>>WorldMap = new ArrayList<ArrayList<Organism>>();
    private final Vector<String> comments = new Vector<>();     //lista komentarzu

    private final String save_file = new String("World_Save.txt");
    private Object Point;
    private String direction;

    public int getSize_x(){ return size_x; }
    public void setSize_x(int size_x) { this.size_x = size_x; }

    public int getSize_y() { return size_y;  }
    public void setSize_y(int size_y) { this.size_y = size_y; }

    public String getDirection() {
        return direction;
    }


























    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Vector<String> getComments() {
        return comments;
    }

    public void addComment(String comment) {
        this.comments.add(comment);
    }

    public  ArrayList<ArrayList<Organism>> getWorldMap(){
        return new ArrayList<ArrayList<Organism>> (WorldMap);
    }

    public World(int x, int y){
        this.size_x = x;
        this.size_y = y;

        this.direction = "right";

        //Vector resize
        for(int j=0;j<getSize_x();j++){
            ArrayList<Organism> organism = new ArrayList<Organism>();
            for(int k=0;k<getSize_y();k++){
                organism.add(null);
            }
            WorldMap.add(organism);
        }
    }

    public void Create_life(){
        Organism ptr_to_animal = null;

        for(int i =0; i<this.size_x;i++){
            int rand_x = new Random().nextInt(this.size_x );
            int rand_y = new Random().nextInt(this.size_y );
            int x = rand_x ;
            int y = rand_y ;

            if(this.WorldMap.get(x).get(y) == null){
                int choice = new Random().nextInt(11);

                if(choice == 0) ptr_to_animal = new Wolf(x,y);
                else if (choice == 1)  ptr_to_animal = new Grass(x, y);
                else if (choice == 2)  ptr_to_animal = new Sheep(x, y);
                else if (choice == 3) ptr_to_animal = new Fox(x, y);
                else if (choice == 4) ptr_to_animal = new SowThistle(x, y);
                else if (choice == 5) ptr_to_animal = new Turtle(x, y);
                else if (choice == 6) ptr_to_animal = new Antelope(x, y);
                else if (choice == 7) ptr_to_animal = new Guarana(x, y);
                else if (choice == 8) ptr_to_animal = new Belladonna(x, y);
                else if (choice == 9)  ptr_to_animal = new Heracleum(x, y);
                else if (choice == 10)  ptr_to_animal = new Cyber_Sheep(x, y);

                WorldMap.get(x).set(y,ptr_to_animal);
                Animals.add(ptr_to_animal);                //Animals.pushback(ptr)
            }
        }//for

        while(true){
            int rand_x = new Random().nextInt(this.size_x );
            int rand_y = new Random().nextInt(this.size_y );
            if(this.WorldMap.get(rand_x).get(rand_y) == null) {
                ptr_to_animal = new Human(rand_x, rand_y);
                WorldMap.get(rand_x).set(rand_y, ptr_to_animal);
                Animals.add(ptr_to_animal);                //Animals.pushback(ptr)
                break;
            }
        }//while
    }//create life

    public boolean Create_child(Organism organism, Integer x, Integer y){
        Point child_position = new Point(0, 0);
        if(x == -1 || y == -1)
        {
            child_position = this.Get_Neighbour_position(organism);
        }
        else    child_position = new Point(x, y);


        if (this.WorldMap.get(child_position.x).get(child_position.y) == null)
        {
            Organism ptr_to_animal = null;

            if (organism.getSign() == 'W') ptr_to_animal = new Wolf(child_position.x, child_position.y);
            if (organism.getSign() == 'G') ptr_to_animal = new Grass(child_position.x, child_position.y);
            if (organism.getSign() == 'S') ptr_to_animal = new Sheep(child_position.x, child_position.y);
            if (organism.getSign() == 'F') ptr_to_animal = new Fox(child_position.x, child_position.y);
            if (organism.getSign() == '*') ptr_to_animal = new SowThistle(child_position.x, child_position.y);
            if (organism.getSign() == 'T') ptr_to_animal = new Turtle(child_position.x, child_position.y);
            if (organism.getSign() == 'A') ptr_to_animal = new Antelope(child_position.x, child_position.y);
            if (organism.getSign() == 'U') ptr_to_animal = new Guarana(child_position.x, child_position.y);
            if (organism.getSign() == 'B') ptr_to_animal = new Belladonna(child_position.x, child_position.y);
            if (organism.getSign() == 'X') ptr_to_animal = new Heracleum(child_position.x, child_position.y);
            if (organism.getSign() == 'C') ptr_to_animal = new Cyber_Sheep(child_position.x, child_position.y);

            this.WorldMap.get(child_position.x).set(child_position.y, ptr_to_animal);
            this.Animals.add(ptr_to_animal);
            return true;
        }

        return false;
    }

    public void Make_turn(){
        comments.removeAllElements();

        //sortowanie
        Collections.sort(this.Animals, Collections.reverseOrder());

        ArrayList<Organism> temp = new ArrayList<>(Animals);

        for(Organism organism : temp) {
            if (organism.getAge() != -1) {
                //System.out.println(organism.species);
                organism.Action(this);
            }
        }

        for(int i=0; i<Animals.size();i++){
            if(this.Animals.get(i).getAge() == -1){
                Animals.remove(i);
                //Animals.erase(Animals.begin() + i);
                i--;
            }
        }
        this.Show_World();
    }//make turn

    public void Show_World(){
        for (ArrayList<Organism> row : WorldMap)
        {
            for (Organism collumn : row)
            {
                if (collumn == null) System.out.print('-');
                else System.out.print(collumn.getSign());
            }
            System.out.print("\n");
        }
    }

    public Organism Get_Organism(int x,int y){
        return this.WorldMap.get(x).get(y);
    }

    public void Handle_collision(Organism attacker, Organism defender) {
        System.out.println("Attacker : " + attacker.getSign());
        System.out.println("Defender : " + defender.getSign());
        String comm = "Attacker: " + attacker.getSign() + "  |  Defender: " + defender.getSign();
        this.addComment(comm);


        if (attacker.getSign() == defender.getSign()) {
            // Wyszukiwanie wolnego miejsca obok rodzicow
            Point child_position = this.Get_Neighbour_position(attacker);
            boolean child_created = false;

            //szukanie miejsca dla dziecka obok attacker
            //9 prob na znalezienie pustego miejsca dla dziecka, jak nie to go nie robi
            for (int i = 0; i < 9; i++) {
                if (!child_created) {
                    child_created = this.Create_child(attacker, -1, -1);
                } else break;
            }
            //szukanie miejsca dla dziecka obok defender
            if (!child_created) {
                for (int i = 0; i < 9; i++) {
                    if (!child_created) {
                        child_created = this.Create_child(defender, -1, -1);
                    } else break;

                }
            }
        }
        //FIGHT
        else {
            boolean attacker_is_alive = true;
            boolean defender_is_alive = true;

            if (defender.getSign() == 'H' || attacker.getSign() == 'H') {
                Human ptr = null;
                if (defender.getSign() == 'H')
                {
                    ptr = defender instanceof Human ? (Human)defender : null;
                }
                if (attacker.getSign() == 'H')
                {
                    ptr = attacker instanceof Human ? (Human)attacker : null;
                }


                if (ptr.is_Ability_active()) {

                    if (defender.getSign() == 'H') {
                        System.out.print(attacker.GetSpecies() + " get scared of " + defender.GetSpecies() + " and panic!" + "\n");
                        String com3 = attacker.GetSpecies() + " get scared of " + defender.GetSpecies() + " and panic!" + "\n";
                        this.addComment(com3);

                        attacker.Action(this);
                    }
                    if (attacker.getSign() == 'H') {
                        System.out.print(attacker.GetSpecies() + " get scared of " + defender.GetSpecies() + " and panic!" + "\n");

                        defender.Action(this);
                    }
                    return;
                }
            }
            attacker_is_alive = attacker.Collision(this, defender);
            defender_is_alive = defender.Collision(this, attacker);

            if (attacker_is_alive && defender_is_alive) {
                System.out.print(attacker.GetSpecies() + " fights with " + defender.GetSpecies() + " but nobody died!" + "\n");
                String comment = attacker.GetSpecies() + " fights with " + defender.GetSpecies() + " but nobody died!" + "\n";
                this.addComment(comment);
            }

            else if (attacker_is_alive && !defender_is_alive)
            {
                System.out.print(attacker.GetSpecies() + " kill " + defender.GetSpecies() + "!" + "\n");
                String comment1 = attacker.GetSpecies() + " kill " + defender.GetSpecies() + "!" + "\n";
                this.addComment(comment1);

                // atakujacy wchodzi na pozycje broniacego ktory umiera
                this.WorldMap.get(attacker.getPosition().x).set(attacker.getPosition().y, null);
                this.WorldMap.get(defender.getPosition().x).set(defender.getPosition().y, attacker);

                attacker.setPosition(defender.getPosition());
                defender.SetAge(-1);
            }
            else if (!attacker_is_alive && defender_is_alive) {
                System.out.print(defender.GetSpecies() + " kill " + attacker.GetSpecies() + "!" + "\n");
                String comment2 = defender.GetSpecies() + " kill " + attacker.GetSpecies() + "!" + "\n";
                this.addComment(comment2);

                // atakujacy umiera broniacy nie zmienia pozycji
                // this.WorldMap[attacker.getPosition().x][attacker.getPosition().y] = null;
                this.WorldMap.get(attacker.getPosition().x).set(attacker.getPosition().y, null);

                attacker.SetAge(-1);
            }

        }
    }

    public Point Get_Neighbour_position(Organism organism){
        int pos_x = organism.getPosition().x;
        int pos_y = organism.getPosition().y;
        int movement = new Random().nextInt(10)+1;
        int movement2 = new Random().nextInt(10)+1;

        //ruch w poziomie
        if(movement % 2 == 0){
            if((new Random().nextInt(10)+1) % 2 == 0){
                pos_x++;
            }
            else pos_x--;
        }
        if (pos_x < 0 || pos_x >= size_x)   pos_x = organism.getPosition().x;

        if(movement2 % 2 ==0){
            if((new Random().nextInt(10)+1) % 2 == 0){
                pos_y++;
            }
            else pos_y--;
        }
        if (pos_y < 0 || pos_y >= size_y) pos_y = organism.getPosition().y;

        //jesli pozycja ta sama co org to wybierz kolejna pozycje
        if (pos_y == organism.getPosition().y && pos_x == organism.getPosition().x)  return this.Get_Neighbour_position(organism);

        return new Point(pos_x, pos_y);
    }

    public void Save_World() throws FileNotFoundException{
        PrintWriter file = new PrintWriter(this.save_file);

            file.println(this.size_x);
            file.println(this.size_y);
            file.println(this.Animals.size());

            //zapisuje kazdy organizm, symbol, sile, wiek, pozycje
            for(Organism organism : this.Animals){
                if( organism != null)
                {
                    file.println(organism.getSign());
                    file.println(organism.getStrength());
                    file.println(organism.getAge());
                    file.println(organism.getPos_x());
                    file.println(organism.getPos_y());
                 //   file.print(organism.getPosition());

                }
            }//for
        file.close();
    }

    public  void Load_file() throws FileNotFoundException{
        Scanner save = new Scanner(new File(this.save_file)); //lub bez this "save_file.txt"
     //   Scanner point = new Scanner(System.in);
        this.Animals.clear();
        this.WorldMap.clear();

        this.size_x=save.nextInt();
        this.size_y=save.nextInt();

        for(int j=0;j<this.getSize_x();j++){
            ArrayList<Organism> organism = new ArrayList<Organism>();
            for(int k=0;k<this.getSize_y();k++){
                organism.add(null);
            }
            WorldMap.add(organism);
        }

        int size = save.nextInt();          //ilosc organizmow

        Character org_sign;
        Integer org_str,org_age,org_x,org_y;

        boolean human_sup;
        Organism org = null;

        for(int i=0;i<size;i++){
            org_sign = save.next().charAt(0);
            org_str = save.nextInt();
            org_age = save.nextInt();
            org_x = save.nextInt();
            org_y = save.nextInt();


            if(save != null){
                switch (org_sign){
                    case 'A':
                        org = new virtualworld.Antelope(org_x,org_y);
                        break;
                    case 'S':
                        org = new virtualworld.Sheep(org_x,org_y);
                        break;
                    case '*':
                        org = new virtualworld.SowThistle(org_x,org_y);
                        break;
                    case 'T':
                        org = new virtualworld.Turtle(org_x,org_y);
                        break;
                    case 'W':
                        org = new virtualworld.Wolf(org_x,org_y);
                        break;
                    case 'F':
                        org = new virtualworld.Fox(org_x,org_y);
                        break;
                    case 'G':
                        org = new virtualworld.Grass(org_x,org_y);
                        break;
                    case 'U':
                        org = new virtualworld.Guarana(org_x,org_y);
                        break;
                    case 'X':
                        org = new virtualworld.Heracleum(org_x,org_y);
                        break;
                    case 'H':
                        org = new virtualworld.Human(org_x,org_y);
                        human_sup = save.nextBoolean();
                        break;
                    case 'B':
                        org = new virtualworld.Belladonna(org_x,org_y);
                        break;
                    case 'C':
                        org = new virtualworld.Cyber_Sheep(org_x,org_y);
                        break;
                    default:    break;
                }//switch

                org.SetAge(org_age);
                org.SetStrength(org_str);
            }
            this.Animals.add(org);
            this.WorldMap.get(org_x).set(org_y, org);
        } //Create_life();
    }

    public void Resize() {
        ArrayList<Organism> new_animals = new ArrayList<>();

        this.Animals.forEach( (Organism o) -> {
            if( o.getPos_x() < this.getSize_x() && o.getPos_y() < this.getSize_y())
            {
                new_animals.add(o);
            }
        });

        Animals.clear();
        Animals = new_animals;

        this.WorldMap.clear();

        //Vector resize
        for(int j=0;j<getSize_x();j++){
            ArrayList<Organism> organism = new ArrayList<Organism>();
            for(int k=0;k<getSize_y();k++){
                organism.add(null);
            }
            WorldMap.add(organism);
        }

        // dla kazdego organizmu w arraylist -> postaw go na mapie
        Animals.forEach( (Organism o) -> this.WorldMap.get(o.getPos_x()).set(o.getPos_y(), o) );
    }
}
