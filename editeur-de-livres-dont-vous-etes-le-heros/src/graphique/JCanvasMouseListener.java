package graphique;

import java.awt.event.*;
import javax.swing.SwingUtilities;

public abstract class JCanvasMouseListener extends MouseAdapter {

    protected PanelGraph canvas;

    public JCanvasMouseListener(PanelGraph canvas) {
        super();
        this.canvas = canvas;
        canvas.addMouseListener(this);
    }

    public PanelGraph getCanvas() {
        return canvas;
    }

    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            leftClickAction(e);
        } else {
            rightClickAction(e);
        }
    }


    protected void rightClickAction(MouseEvent e) {

    }

    protected void leftClickAction(MouseEvent e) {

    }

}
