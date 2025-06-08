package views;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image backgroundImage;

	public BackgroundPanel(String imagePath) {
		// Carrega a imagem
		backgroundImage = new ImageIcon(getClass().getResource(imagePath)).getImage();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (backgroundImage != null) {
			// Redimensiona a imagem para caber no painel
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
		}
	}
}
