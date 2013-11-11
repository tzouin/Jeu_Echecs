package echecs;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;


public abstract class Piece {
	
	protected  Couleur couleurPiece; 
	
	public Piece(Couleur couleurPiece) {
		this.couleurPiece = couleurPiece;
	}
	
	public boolean caseIdentique(Case caseDepart, Case caseArrivee) {
		//System.out.println("fff"+caseDepart.equals(caseArrivee));
		return caseDepart.equals(caseArrivee);
	}
	
	public boolean caseIdentique(Point source, Point destination) {
		return source.x == destination.x && source.y == destination.y;
	}
	
	public abstract HashSet<Case> obtenirCaseAtteignable(Case[][] grille, Case caseDepart);
	
	public abstract boolean deplacerPiece(Case[][] grille, Case caseDepart, Case caseArrivee);

	public abstract String toString();
	
	public Couleur getCouleur(){
		return couleurPiece;
	}
	
}
