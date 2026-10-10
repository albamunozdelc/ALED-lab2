package es.upm.aled.lab2.gui;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import es.upm.aled.lab2.kinematics.*;

/**
 * Draws on a window an exoskeleton represented as a tree of Nodes.
 * 
 * @author rgarciacarmona
 */
public class SkeletonPanel extends JPanel {

	private static final long serialVersionUID = 8724301078499171545L;
	private Segment root;

	/**
	 * Shows a window containing a 2D representation of an exoskeleton. Each joint
	 * in the kinematic chain is represented with a dot, and each segment with a
	 * line that connects two dots together. The end effector is also represented as
	 * a dot. It can be animated to show the exoskeleton's movement by calling the
	 * repaint() method after modifying the attributes of the kinematic chain's
	 * elements.
	 * 
	 * This class uses ForwardKinematics to compute the positions of every element
	 * in the kinematic chain.
	 * 
	 * @param root  The first Segment of the kinematic chain.
	 * @param sizeX The width of the window.
	 * @param sizeX The height of the window.
	 */
	public SkeletonPanel(Segment root, int sizeX, int sizeY) {
		this.root = root;
		// Setup Swing window: configuro la ventana donde se va a pintar
		//1. creo la ventana
		JFrame frame = new JFrame("Recursive Skeleton - Forward Kinematics");
		frame.add(this);
		//3. fijo el tamaño de la ventana
		frame.setSize(600, 600);
		//4. fijo por defecto como se cierra la ventana
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//5. queremos que la ventana sea visible
		frame.setVisible(true);
	}

	@Override //reprogramar un método heredado de tu padre, reutilizar porciones del código
	protected void paintComponent(Graphics g) {
		// Draws the window
		super.paintComponent(g);
		//genero las coordenadas de x e y por donde voy a empezar a pintar
		//punto central de la ventana
		float originX = getWidth() / 2;
		float originY = getHeight() / 2;

		// Computes the full tree of positions
		//convierte la info de segmento raiz en nodo raiz
		Node nodeRoot = ForwardKinematics.computePositions(root, originX, originY);

		// Draw the tree of Nodes recursively
		//invocas gráfico g, le dices donde empieza a pintar y cuál es el 
		//nodo raizz
		drawSkeleton(g, originX, originY, nodeRoot);
	}

	//método recursivo
	private void drawSkeleton(Graphics g, double parentX, double parentY, Node node) {
		//recibe un nodo
		//pinta círculo en pantalla en las coordenadas del nodo
		g.fillOval((int) node.getX() - 4, (int) node.getY() - 4, 8, 8);
		//pinta una línea que une ese nodo con el padre
		g.drawLine((int) parentX, (int) parentY, (int) node.getX(), (int) node.getY());
		//CASO BASE
		//si el nodo que me has pasado no tiene hijos (dim lista =0)
		if (node.getChildren().size() == 0) {
			return; //para ya
		}
		//PASO RECURSIVO
		//recorro todos los hijos del nodo que me habías pasado
		for (Node child : node.getChildren()) {
			//g: mismo controlador de gráficos para que se pinte en la misma pantalla
			//tengo que recordar las coordenadas de donde estaba el padre
			drawSkeleton(g, node.getX(), node.getY(), child);
		}
	}
}