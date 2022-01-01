package virtualworld.window;

import virtualworld.*;
import virtualworld.Organism.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;

public class Game extends JFrame implements KeyEventDispatcher {
    private Field_ board;
    private Menu menu;
    private World world;

    public Game(World world) {
        super("Paulina Puchalska - virtual world simulator");
        this.world = world;

        BorderLayout borderLayout = new BorderLayout(7, 0);
        setLayout(borderLayout);
        setFocusable(true);

        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(this);

        board = new Field_(world.getSize_x(), world.getSize_y(),world);

        JButton round = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Round();
            }
        });
        round.setText("Round");
        round.setFocusable(false);

        JButton save = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Save();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });
        save.setText("Save");
        save.setFocusable(false);

        JButton load = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Load();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });
        load.setText("Load saved file");
        load.setFocusable(false);

        JPanel south = new JPanel();
        south.add(round);
        south.add(save);
        south.add(load);

        //Human movement
        JButton H_Left = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Left();
            }
        });
        H_Left.setText("Left");
        H_Left.setFocusable(false);

        JButton H_Right = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Right();
            }
        });
        H_Right.setText("Right");
        H_Right.setFocusable(false);

        JButton H_Up = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Up();
            }
        });
        H_Up.setText("Up");
        H_Up.setFocusable(false);

        JButton H_Down = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Down();
            }
        });
        H_Down.setText("Down");
        H_Down.setFocusable(false);

        JButton H_Power = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Power();
            }
        });
        H_Power.setText("Power");
        H_Power.setFocusable(false);

        JPanel movement = new JPanel();
        movement.add(H_Left);
        movement.add(H_Up);
        movement.add(H_Down);
        movement.add(H_Right);
        movement.add(H_Power);



        menu = new Menu(world, this);
        Dimension menu_size = new Dimension(250, getHeight());
        menu.getPanel().setPreferredSize(menu_size);

        for (int i = 0; i < board.getButtons().length; i++) {
            for (int j = 0; j < board.getButtons()[i].length; j++) {
                int finalI = i;
                int finalJ = j;

                board.getButtons()[i][j].addActionListener(e -> {
                    if (board.getButtons()[finalI][finalJ].getText().equals("")) {
                        Object[] possibleValues = {"Heracleum", "Guarana", "SowThistle", "Grass", "Wolf", "Fox", "Sheep", "Turtle","Belladonna", "Antelope", "Cyber_Sheep"};

                        JOptionPane optionPane = new JOptionPane();
                        optionPane.setFocusable(false);

                        Object selectedValue = JOptionPane.showInputDialog(null, "Choose an organism", "Creating animals", JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);
                        Organism org;

                        if (selectedValue != null) {
                            switch (selectedValue.toString()) {
                                case "Fox": {
                                    org = new Fox(finalI, finalJ);

                                    break;
                                }
                                case "Heracleum": {
                                    org = new Heracleum(finalI, finalJ);
                                    break;
                                }
                                case "Wolf": {
                                    org = new Wolf(finalI, finalJ);
                                    break;
                                }
                                case "Sheep": {
                                    org = new Sheep(finalI, finalJ);
                                    break;
                                }
                                case "Turtle": {
                                    org = new Turtle(finalI, finalJ);
                                    break;
                                }
                                case "Belladonna": {
                                    org = new Belladonna(finalI, finalJ);
                                    break;
                                }
                                case "SowThistle": {
                                    org = new SowThistle(finalI, finalJ);
                                    break;
                                }
                                case "Antelope": {
                                    org = new Antelope(finalI, finalJ);
                                    break;
                                }
                                case "Cyber_Sheep": {
                                    org = new Cyber_Sheep(finalI, finalJ);
                                    break;
                                }
                                default: {
                                    org = new Grass(finalI, finalJ);
                                    break;
                                }
                            }

                            world.Create_child(org, finalI, finalJ);
                            set_Board(world);
                        }
                    }
                });
            }

        }

        add(menu.getPanel(), BorderLayout.EAST);
        add(board.getBoard(), BorderLayout.CENTER);
        add(south, BorderLayout.SOUTH);
        add(movement,BorderLayout.PAGE_START);

        board.Show_Board(world);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
        requestFocus();
    }

    public void resize_Board(World world)
    {
        remove( this.board.getBoard() );
        board = new Field_(world.getSize_x(), world.getSize_y(),world);
        add(board.getBoard(), BorderLayout.CENTER);

        for (int i = 0; i < board.getButtons().length; i++) {
            for (int j = 0; j < board.getButtons()[i].length; j++) {
                int finalI = i;
                int finalJ = j;

                board.getButtons()[i][j].addActionListener(e -> {
                    if (board.getButtons()[finalI][finalJ].getText().equals("")) {
                        Object[] possibleValues = {"Heracleum", "Guarana", "SowThistle", "Grass", "Wolf", "Fox", "Sheep", "Turtle","Belladonna", "Antelope", "Cyber_Sheep"};

                        JOptionPane optionPane = new JOptionPane();
                        optionPane.setFocusable(false);

                        Object selectedValue = JOptionPane.showInputDialog(null, "Choose an organism", "Creating animals", JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);
                        Organism org;

                        if (selectedValue != null) {
                            switch (selectedValue.toString()) {
                                case "Fox": {
                                    org = new Fox(finalI, finalJ);
                                    break;
                                }
                                case "Heracleum": {
                                    org = new Heracleum(finalI, finalJ);
                                    break;
                                }
                                case "Wolf": {
                                    org = new Wolf(finalI, finalJ);
                                    break;
                                }
                                case "Sheep": {
                                    org = new Sheep(finalI, finalJ);
                                    break;
                                }
                                case "Turtle": {
                                    org = new Turtle(finalI, finalJ);
                                    break;
                                }
                                case "Belladonna": {
                                    org = new Belladonna(finalI, finalJ);
                                    break;
                                }
                                case "SowThistle": {
                                    org = new SowThistle(finalI, finalJ);
                                    break;
                                }
                                case "Antelope": {
                                    org = new Antelope(finalI, finalJ);
                                    break;
                                }
                                case "Cyber_Sheep": {
                                    org = new Cyber_Sheep(finalI, finalJ);
                                    break;
                                }
                                default: {
                                    org = new Grass(finalI, finalJ);
                                    break;
                                }
                            }

                            world.Create_child(org, finalI, finalJ);
                            set_Board(world);
                        }
                    }
                });
            }
        }
    }

    public void set_Board(World world) {
        board.Show_Board(world);
        revalidate();
        repaint();
    }

    private void writeComments(World world) {
        remove(menu.getPanel());
        this.menu = new Menu(world, this);
        add(menu.getPanel(), BorderLayout.EAST);
        revalidate();
        repaint();
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        int direction = e.getKeyCode();
        switch (direction) {

            case KeyEvent.VK_SPACE: {
                Round();
                break;
            }
            default: {
                return false;
            }
        }
        return false;
    }

    private void Round() {
        world.setDirection("None");
        menu.setDirection(world);
        world.Make_turn();
        writeComments(world);
        set_Board(world);
    }

    private void Save() throws FileNotFoundException {
        world.Save_World();
    }

    private void Load() throws FileNotFoundException {
        try {
            world.Load_file();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        set_Board(world);
    }

    private void Left(){
        world.setDirection("Left");
        menu.setDirection(world);
        world.Make_turn();
        writeComments(world);
        set_Board(world);
    }
    private void Right(){
        world.setDirection("Right");
        menu.setDirection(world);
        world.Make_turn();
        writeComments(world);
        set_Board(world);
    }
    private void Up(){
        world.setDirection("Up");
        menu.setDirection(world);
        world.Make_turn();
        writeComments(world);
        set_Board(world);
    }
    private void Down() {
        world.setDirection("Down");
        menu.setDirection(world);
        world.Make_turn();
        writeComments(world);
        set_Board(world);
    }
    private void Power() {
        world.setDirection("Power");
        menu.setDirection(world);
        world.Make_turn();
        writeComments(world);
        set_Board(world);
    }
}
