import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class RobotAnimation extends JPanel implements ActionListener {
  private double[][] points = {
      {130, 70}, {130, 30}, {100, 20}, {130, 10}, {130, 30}, {170, 30}, {170, 10}, {200, 30}, {170, 30}, {170, 70},
      {220, 70}, {220, 115}, {200, 115}, {200, 170}, {210, 170}, {210, 180}, {170, 180}, {170, 140}, {140, 140},
      {140, 170}, {150, 170}, {150, 180}, {110, 180}, {110, 130}, {90, 130}, {90, 70}, {130, 70}
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

    RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    g2d.setRenderingHints(rh);

    g2d.setBackground(Color.WHITE);
    g2d.clearRect(0, 0, maxWidth, maxHeight);

    g2d.translate(maxWidth / 2, maxHeight / 2);
    // Перетворення для анімації руху.
    g2d.translate(this.tx, this.ty);
    // Створення зірки
    GeneralPath star = new GeneralPath();
    star.moveTo(this.points[0][0], this.points[0][1]);
    for (int i = 1; i < this.points.length; i++) star.lineTo(this.points[i][0], this.points[i][1]);
    star.closePath();
    // Перетворення для анімації повороту. Якщо не задати 2 та 3 параметри – поворот відбудеться відносно центру координат
    g2d.rotate(this.angle, star.getBounds2D().getCenterX(), star.getBounds2D().getCenterY());
    // Перетворення для анімації масштабу
    g2d.scale(this.scale, 0.99);
    // Перетворення для анімації зміни прозорості
    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) this.scale));
    // Далі йдуть всі ті методи, що необхідні для власне малювання малюнку
    g2d.fill(star);
  }
  // Цей метод буде викликано щоразу, як спрацює таймер
  public void actionPerformed(ActionEvent e) {
    if (this.scale < 0.01) this.delta = -this.delta;
    else if (this.scale > 0.99) this.delta = -this.delta;
    if (this.tx < -maxWidth / 3) this.dx = -this.dx;
    else if (this.tx > maxWidth / 3) this.dx = -this.dx;
    if (this.ty < -maxHeight / 3) this.dy = -this.dy;
    else if (this.ty > maxHeight / 3) this.dy = -this.dy;
    this.scale += this.delta;
    this.angle += 0.01;
    this.tx += this.dx;
    this.ty += this.dy;

    repaint();
  }
  public static void main(String[] args) {
    JFrame frame = new JFrame("Приклад анімації");
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
