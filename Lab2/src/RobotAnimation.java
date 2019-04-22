import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class RobotAnimation extends JPanel implements ActionListener {
  private double radius = 200;
  private double[][] points = {
      {0, 70}, {0, 30}, {-30, 20}, {0, 10}, {0, 30}, {40, 30}, {40, 10}, {70, 30}, {40, 30}, {30, 70},
      {90, 70}, {90, 115}, {70, 115}, {70, 170}, {80, 170}, {80, 180}, {40, 180}, {40, 140}, {10, 140},
      {10, 170}, {20, 170}, {20, 180}, {-20, 180}, {-20, 130}, {-40, 130}, {-40, 70}, {0, 70}
  };
  private Timer timer;

  private double angle = 0;

  private double scale = 1;
  private double delta = 0.01;

  private double dx = 1;
  private double tx = 0;
  private double dy = 1;

  private double ty = 6;
  private static int maxWidth;
  private static int maxHeight;

  RobotAnimation() {
    this.timer = new Timer(10, this);
    this.timer.start();
  }

  public void paint(Graphics g) {
    Graphics2D g2d = (Graphics2D)g;

    BasicStroke bs = new BasicStroke(16, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND);
    g2d.setStroke(bs);

    RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    g2d.setRenderingHints(rh);

    g2d.setBackground(Color.WHITE);
    g2d.clearRect(0, 0, maxWidth, maxHeight);

    g2d.translate(maxWidth / 2, maxHeight / 2);
    g2d.translate(this.tx, this.ty);

    GeneralPath figure = new GeneralPath();
    figure.moveTo(this.points[0][0], this.points[0][1]);

    for (int i = 1; i < this.points.length; i++)
      figure.lineTo(this.points[i][0], this.points[i][1]);

    figure.closePath();

    g2d.rotate(this.angle, figure.getBounds2D().getCenterX(), figure.getBounds2D().getCenterY());
    g2d.scale(this.scale, 0.99);
    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) this.scale));
    g2d.fill(figure);
  }
  public void actionPerformed(ActionEvent e) {
    double radiusInSquare = Math.pow(this.radius, 2);
    if (tx <= 0 && ty < 0){
      tx -= dx;
      ty = (-1) * Math.abs(Math.sqrt(radiusInSquare - Math.pow(tx, 2)));
    } else if (tx > 0 && ty <= 0){
      tx -= dx;
      ty = (-1) * Math.abs(Math.sqrt(radiusInSquare - Math.pow(tx, 2)));
    } else if (tx >= 0 && ty > 0){
      tx += dx;
      ty = Math.abs(Math.sqrt(radiusInSquare - Math.pow(tx, 2)));
    } else if (tx < 0 && ty >= 0){
      tx += dx;
      ty = Math.abs(Math.sqrt(radiusInSquare - Math.pow(tx, 2)));
    }

    angle += 0.01;

    repaint();
    /*if (this.scale < 0.01) this.delta = -this.delta;
    else if (this.scale > 0.99) this.delta = -this.delta;
    if (this.tx < -maxWidth / 3) this.dx = -this.dx;
    else if (this.tx > maxWidth / 3) this.dx = -this.dx;
    if (this.ty < -maxHeight / 3) this.dy = -this.dy;
    else if (this.ty > maxHeight / 3) this.dy = -this.dy;
    this.scale += this.delta;
    this.angle += 0.01;
    this.tx += this.dx;
    this.ty += this.dy;

    repaint();*/
  }
  public static void main(String[] args) {
    JFrame frame = new JFrame("Lab2");
    frame.add(new RobotAnimation());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(500, 500);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    Dimension size = frame.getSize();
    Insets insets = frame.getInsets();
    maxWidth = size.width - insets.left - insets.right - 1;
    maxHeight = size.height - insets.top - insets.bottom - 1;
  }
}
