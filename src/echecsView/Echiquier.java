package echecsView;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * <b>Echiquier est la classe permettant de dessiner la grille de jeu.</b>
 * <p>
 * Quand un clic est détecté, la demande est transmise au controleur.
 * </p>
 * 
 * @author Cyril MONSIEUX
 * @version 1.0
 */

public 	class Echiquier extends JPanel {
	private static final long serialVersionUID = 1L;
	private FenetreJeu fenetre;
	
	public Echiquier(FenetreJeu fenetre) {
		setPreferredSize(new Dimension(1000, 1000));
		this.fenetre=fenetre;
	}	
	
	/**Permet de dessiner le plateau*/
	public void paintComponent (Graphics g) {
		super.paintComponent (g) ;
		fenetre.redessineLePlateau (g) ;
	}
	
}