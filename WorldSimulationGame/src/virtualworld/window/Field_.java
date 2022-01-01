package virtualworld.window;

import virtualworld.World;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import static java.awt.Color.*;

public class Field_ extends JPanel{
        private int x,y;
        private final JPanel board;
        private JButton[][] buttons;

        Field_(int x, int y, World world){
            this.x = x;
            this.y = y;
            board = setField_(world);

            entityColors.put('X', RED);
            entityColors.put('*', LIGHT_GRAY);
            entityColors.put('G', GREEN);
            entityColors.put('W', DARK_GRAY);
            entityColors.put('F', ORANGE);
            entityColors.put('S', WHITE);
            entityColors.put('T', YELLOW);
            entityColors.put('B', CYAN);
            entityColors.put('A', MAGENTA);
            entityColors.put('U', new Color(255,204,153));
            entityColors.put('C', new Color(255,20,255));
        }

        private Object[] possibleValues = {"Heracleum", "SowThistle", "Grass", "Wolf", "Fox", "Sheep", "Turtle","Belladonna"};
        private HashMap <Character, Color> entityColors = new HashMap<>();

        JPanel getBoard(){
            return board;
        }

        JButton[][] getButtons(){
            return buttons;
        }

        private JPanel setField_(World world) {
            this.x = world.getSize_x();
            this.y = world.getSize_y();

            JPanel board = new JPanel();
            board.setBorder(BorderFactory.createTitledBorder("Game board"));

            GridLayout gridLayout = new GridLayout(y,x);
            board.setLayout(gridLayout);

            buttons = new JButton[world.getSize_x()][world.getSize_y()];

            //tworzenie tablicy dwuwymiarowej wypełnionej przyciskami
            for(int i =0; i<world.getSize_x();i++){
                for(int j = 0;j<world.getSize_y();j++){
                    buttons[i][j] = new JButton();
                    buttons[i][j].setBackground(LIGHT_GRAY);
                    buttons[i][j].setMinimumSize(new Dimension(60, 60));
                    buttons[i][j].setPreferredSize(new Dimension(60, 60));
                    board.add(buttons[i][j]);
                }
            }
            return board;
        }

        void Show_Board(World world) {
            for (int i = 0; i < world.getSize_x(); i++) {
                for (int j = 0; j < world.getSize_y(); j++) {
                    if(world.Get_Organism(i,j) == null){
                        buttons[i][j].setText("");
                        buttons[i][j].setBackground(LIGHT_GRAY);
                    }
                    else{
                            buttons[i][j].setText(world.Get_Organism(i, j).GetSpecies()); //// ?
                            buttons[i][j].setBackground(entityColors.get(world.Get_Organism(i, j).getSign()));
                            buttons[i][j].setForeground(new Color(0 ,0,0));
                    }
                }
            }
        }
}
