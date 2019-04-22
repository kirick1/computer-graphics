import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class RobotAnimation extends JPanel implements ActionListener {
  private double radius = 100;

  private double[][] points = {
      {0, -100}, {0, -140}, {-30, -150}, {0, -160}, {0, -140}, {40, -140}, {40, -160}, {70, -140}, {40, -140}, {40, -100},
      {90, -100}, {90, -55}, {70, -55}, {70, 0}, {80, 0}, {80, 10}, {40, 10}, {40, -30}, {10, -30},
      {10, 0}, {20, 0}, {20, 10}, {-20, 10}, {-20, -50}, {-40, -50}, {-40, -100}, {0, -100}
  };
  private Timer timer;

  private double angle = 0;
  private double delta = 0.01;
  private double scale = 1;

  private double dx = 1;
  private double tx = 0;

  private double ty = 6;
  private static int maxWidth;
  private static int maxHeight;

  private RobotAnimation() {
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
    if (this.scale < 0.01 ) this.delta = -this.delta;
    else if (scale > 0.99) this.delta = -this.delta;

    if (this.tx <= 0 && this.ty < 0) {
      this.tx -= this.dx;
      this.ty = (-1) * Math.abs(Math.sqrt(radiusInSquare - Math.pow(this.tx, 2)));
    } else if (this.tx > 0 && this.ty <= 0) {
      this.tx -= this.dx;
      this.ty = (-1) * Math.abs(Math.sqrt(radiusInSquare - Math.pow(this.tx, 2)));
    } else if (this.tx >= 0 && this.ty > 0) {
      this.tx += this.dx;
      this.ty = Math.abs(Math.sqrt(radiusInSquare - Math.pow(this.tx, 2)));
    } else if (this.tx < 0 && this.ty >= 0) {
      this.tx += this.dx;
      this.ty = Math.abs(Math.sqrt(radiusInSquare - Math.pow(this.tx, 2)));
    }
    this.scale += this.delta;
    this.angle += 0.01;
    repaint();
  }
  public static void main(String[] args) {
    JFrame frame = new JFrame("Lab2");
    frame.add(new RobotAnimation());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 600);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    Dimension size = frame.getSize();
    Insets insets = frame.getInsets();
    maxWidth = size.width - insets.left - insets.right - 1;
    maxHeight = size.height - insets.top - insets.bottom - 1;
  }
}
