package echecsView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import echecsController.EchiquierController;

public class EcouteurSouris extends MouseAdapter {
	private EchiquierController controler;
	int x, y, cote;
	int xDepart, yDepart, xArrive, yArrive;
	boolean deuxiemeClic=false;
	public EcouteurSouris(EchiquierController controler, int x, int y, int cote){
		super();
		this.x=x;
		this.y=y;
		this.cote=cote;
		this.controler=controler;
	}
	
	public void mousePressed (MouseEvent e) {
		int xSouris = e.getX() ;
		int ySouris = e.getY() ;
		if (alinterieur(xSouris,ySouris)) {
			int i = numeroCaseColonne(xSouris) ;
			int j = numeroCaseLigne(ySouris) ;
			if (deuxiemeClic){
				controler.unNouveauCoup(xDepart, yDepart, j, i);
				deuxiemeClic=!deuxiemeClic;
			}else{
				xDepart=j;
				yDepart=i;
				deuxiemeClic=!deuxiemeClic;
			}
			
		}
	}
	
	/** 
     * Cette méthode vérifie que le clic à la position xPos, et yPos
     * est bien dans la zone de jeu.
     * 
     * @param xPos : la position X au moment du clic
     * @param yPos : la position Y au moment du clic
     * 
     * @return <b>true</b> si la position est correct, <b>false</b> sinon.
     * @see #alinterieur(int xPos, int yPos)
	 * @since     1.0                 
	 */
	private boolean alinterieur (int xPos, int yPos) {
		if ((x < xPos) && (xPos < x+8*cote)
				&& (y < yPos) && (yPos < y+8*cote)) {
			return true ;
		}
		else {
			return false ;
		}
	}
	
	/** 
     * Cette méthode renvoie la colonne par rapport à la position X du clic
     * 
     * @param xPos : la position X au moment du clic
     * 
     * @return Le numéro de la colonne
     * @see #numeroCaseColonne(int xPos)
	 * @since     1.0                 
	 */
	private int numeroCaseColonne(int Xpos) {
		return (Xpos-x)/cote ;
	}
	
	/** 
     * Cette méthode renvoie la rangée par rapport à la position Y du clic
     * 
     * @param yPos : la position Y au moment du clic
     * 
     * @return Le numéro de la rangée
     * @see #numeroCaseLigne(int YPos)
	 * @since     1.0                 
	 */
	private int numeroCaseLigne(int Ypos) {
		return (Ypos-y)/cote ;
	}
}
