package editeur;

import java.awt.event.*;

public class JCanvasMouseAdapter extends JCanvasMouseListener implements MouseMotionListener{

    public JCanvasMouseAdapter(PanelGraph canvas) {
        super(canvas);
        canvas.addMouseMotionListener(this);

    }

    public void mouseDragged(MouseEvent e) {

    }


    public void mouseMoved(MouseEvent e) {

    }

}
