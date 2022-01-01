package virtualworld;
import virtualworld.window.Game;

import java.io.IOException;
import java.util.*;
import java.lang.*;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.awt.*;


public class Main {

    public static void main(String[] args) throws IOException {
        World virtual_life = new World(10,10 );
        virtual_life.Create_life();
        EventQueue.invokeLater(() -> new Game(virtual_life));
    }
}
