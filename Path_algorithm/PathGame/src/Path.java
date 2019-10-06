import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Path extends JPanel {
	private static final long serialVersionUID = 1L;
	private ArrayList<Point> pointList;

	public Path() {
		super();
		pointList = new ArrayList<Point>();
		setBackground(Color.GRAY.brighter());
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				pointList.add(new Point(e.getX(), e.getY()));
				paintComponent(Path.this.getGraphics());
			}
		});

	}

	public double Distanta(Point a, Point b) {
		double dist = Math
				.sqrt((a.getX() - b.getX()) * (a.getX() - b.getX()) + (a.getY() - b.getY()) * (a.getY() - b.getY()));
		return dist;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Point a = new Point();
		Point b = new Point();

		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		for (Point point : pointList) {
			g2.fillOval(point.x - 3, point.y - 3, 6, 6);
		}

		if (pointList.size() >= 2) {
			a = pointList.get(0);
			b = pointList.get(1);
			double d = Distanta(a, b);

			// System.out.println(a.getX() + " " + a.getY() + " " + " " +b.getX() + " " +
			// b.getY() );
			for (int i = 0; i < pointList.size(); i++) {
				for (int j = 0; j < pointList.size(); j++) {
					if (Distanta(pointList.get(i), pointList.get(j)) < d && i != j) {
						d = Distanta(pointList.get(i), pointList.get(j));
						// a = pointList.get(i);
						// b = pointList.get(j);
						a = new Point(pointList.get(i));
						b = new Point(pointList.get(j));
						// System.out.println(a.getX() + " " + a.getY() + " " + " " +b.getX() + " " +
						// b.getY() );
					}
				}
			}

			// System.out.println("Gasit pentru: ");
			// System.out.println("a(x)=" +a.getX() + " a(y)=" + a.getY() + " b(x)=" + " "
			// +b.getX() + " b(y)=" + b.getY() );
			g2.setColor(Color.RED);
			g2.fillOval(a.x - 3, a.y - 3, 6, 6);
			g2.fillOval(b.x - 3, b.y - 3, 6, 6);
			g2.drawLine((int) a.getX(), (int) a.getY(), (int) b.getX(), (int) b.getY());
			

		}

	}

}
