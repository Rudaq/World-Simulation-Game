package virtualworld.window;

import virtualworld.World;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class Menu extends JPanel{
    private final JPanel menu;
    private final JLabel direction;
    private final JSpinner spinnerX;
    private final JSpinner spinnerY;

     Menu(World world, Game game) {
        menu = new JPanel();
        menu.setBorder(BorderFactory.createTitledBorder("Menu"));
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));

        JLabel size = new JLabel("Size");
        size.setAlignmentX(CENTER_ALIGNMENT);

        //Spinner X
        SpinnerModel spinnerModelX = new SpinnerNumberModel(world.getSize_x(), 5, 20, 1);
        spinnerX = new JSpinner(spinnerModelX);

        spinnerX.addChangeListener(e -> {
            JSpinner s = (JSpinner) e.getSource();
            world.setSize_x((Integer) s.getValue());
            world.Resize();
            game.resize_Board(world);
            game.set_Board(world);
        });

        JLabel x = new JLabel("X");
        x.setAlignmentX(CENTER_ALIGNMENT);

        //Spinners
        SpinnerModel spinnerModelY = new SpinnerNumberModel(world.getSize_y(), 5, 20, 1);
        spinnerY = new JSpinner(spinnerModelY);
        Dimension spinnersize = new Dimension(50, 25);
        spinnerY.addChangeListener(e -> {
            JSpinner s = (JSpinner) e.getSource();
            world.setSize_y((Integer) s.getValue());
            world.Resize();
            game.resize_Board(world);
            game.set_Board(world);
        });
        spinnerX.setPreferredSize(spinnersize);
        spinnerX.setMinimumSize(spinnersize);
        spinnerX.setMaximumSize(spinnersize);
        spinnerY.setPreferredSize(spinnersize);
        spinnerY.setMinimumSize(spinnersize);
        spinnerY.setMaximumSize(spinnersize);

         //comments
         JScrollPane scrollPane = writeComments(world);

         JLabel directionlabel = new JLabel("Direction:");
         directionlabel.setAlignmentX(CENTER_ALIGNMENT);
         direction = new JLabel(world.getDirection());
         direction.setAlignmentX(CENTER_ALIGNMENT);

         menu.add(size);
         menu.add(spinnerX);
         menu.add(x);
         menu.add(spinnerY);
         menu.add(directionlabel);
         menu.add(direction);
         menu.add(scrollPane);
    }
        void setDirection(World world) {
            this.direction.setText(world.getDirection());
        }

        JPanel getPanel() {
            return menu;
        }

        void setSpinners(World world) {
            spinnerY.setValue(world.getSize_y());
            spinnerX.setValue(world.getSize_x());
        }

        private JScrollPane writeComments(World world) {
            StyleContext context = new StyleContext();
            StyledDocument document = new DefaultStyledDocument(context);

            Style style = context.getStyle(StyleContext.DEFAULT_STYLE);
            StyleConstants.setAlignment(style, StyleConstants.ALIGN_RIGHT);
            StyleConstants.setFontSize(style, 14);
            StyleConstants.setSpaceAbove(style, 4);
            StyleConstants.setSpaceBelow(style, 4);

            SimpleAttributeSet attributes = new SimpleAttributeSet();
            StyleConstants.setBold(attributes, true);
            StyleConstants.setItalic(attributes, true);
            if (!world.getComments().isEmpty()) {
                for (String comment : world.getComments()) {
                    try {
                        document.insertString(document.getLength(), comment + "\n", attributes);
                    } catch (BadLocationException e) {
                        e.printStackTrace();
                    }
                }
            }
            JTextPane textPane = new JTextPane(document);
            textPane.setEditable(false);
            return new JScrollPane(textPane);
        }


}





