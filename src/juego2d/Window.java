package juego2d;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Window extends Canvas{

    private static final long serialVersionUID = 1L;
 
    public Window(int HEIGHT, int WIDTH, int SCALE, String NAME, Game game){
        JFrame f = new JFrame(NAME);
        f.setMinimumSize(new Dimension(WIDTH*SCALE+16, HEIGHT*SCALE+39));//640*480
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        f.setLocationRelativeTo(null);
        f.setResizable(true);
        f.setVisible(true);
        f.add(game); //game is a component porque extiende canvas
        f.pack();    
        /*Causes this Window to be sized to fit the preferred size and layouts of its subcomponents.
        The resulting width and height of the window are automatically enlarged if either of dimensions 
        is less than the minimum size as specified by the previous call to the setMinimumSize method.
        If the window and/or its owner are not displayable yet, both of them are made displayable before 
        calculating the preferred size. The Window is validated after its size is being calculated.
        */
    }
}