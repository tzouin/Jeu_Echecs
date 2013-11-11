package echecs;

import java.awt.Point;

import java.util.ArrayList;
import java.util.HashSet;

public class Dame extends Piece {
	
	private Fou fou;
	private Tour tour;
	
	public Dame(Couleur couleurPiece) {
		super(couleurPiece);
		fou=new Fou(couleurPiece);
		tour=new Tour(couleurPiece);
	}
	
	public boolean deplacerPiece(Case[][] grille, Case caseDepart, Case caseArrivee) {
		return fou.deplacerPiece(grille, caseDepart, caseArrivee) || tour.deplacerPiece(grille, caseDepart, caseArrivee);
	}

	public String toString() {
		return "D";
	}

	@Override
	public HashSet<Case> obtenirCaseAtteignable(Case[][] grille, Case caseDepart) {
		
		HashSet<Case> listeCase = new HashSet<Case>();
		HashSet<Case> listeCase1 = new HashSet<Case>();
		
		listeCase = fou.obtenirCaseAtteignable(grille, caseDepart);
		listeCase1 = tour.obtenirCaseAtteignable(grille, caseDepart);
		
		for (Case c : listeCase1) {
			listeCase.add(c);
		}
		
		return listeCase;
	}
}
