package echecs;


import java.util.ArrayList;
import java.util.HashSet;

public class Tour extends Piece {
	
	private boolean dejaJoue;

	public Tour(Couleur couleurPiece) {
		super(couleurPiece);
		dejaJoue = false;
	}

	public boolean isDejaJoue() {
		return dejaJoue;
	}

	public void setDejaJoue(boolean dejaJoue) {
		this.dejaJoue = dejaJoue;
	}

	public boolean caseVide(Case[][] grille, Case caseDepart, Case caseArrivee){

		boolean bool = true;
		int departX = (caseDepart.getRangee() <= caseArrivee.getRangee()) ? caseDepart.getRangee() : caseArrivee.getRangee() ;
		int departY = (caseDepart.getColonne() <= caseArrivee.getColonne()) ? caseDepart.getColonne() : caseArrivee.getColonne() ;

		int diffX = caseDepart.getRangee() - caseArrivee.getRangee();
		int diffY = caseDepart.getColonne() - caseArrivee.getColonne();

		int taille  = (Math.abs(diffX) >= Math.abs(diffY)) ? Math.abs(diffX) : Math.abs(diffY);

		int incX = 0, incY = 0;
		incX = (diffX == 0) ? 0 : (incX+1);
		incY = (diffY == 0) ? 0 : (incY+1);

		for(int i=0; i<taille-1; i++) {
			departX += incX;
			departY += incY;
			if (!grille[departX][departY].estVide()) {
				bool = bool & false;
			}
		}
		return bool;
	}

	public boolean deplacerPiece(Case[][] grille, Case caseDepart, Case caseArrivee) {

		boolean bool = false;

		if ( ((caseDepart.getRangee() == caseArrivee.getRangee()) || (caseDepart.getColonne() == caseArrivee.getColonne())) 
				&& ( grille[caseArrivee.getRangee()][caseArrivee.getColonne()].estVide() || !grille[caseArrivee.getRangee()][caseArrivee.getColonne()].estDeMemeCouleur(this.getCouleur()))
				&& !(grille[caseArrivee.getRangee()][caseArrivee.getColonne()].getPiece() instanceof Roi)
				&& !caseIdentique(caseDepart, caseArrivee)
				&& caseVide(grille, caseDepart, caseArrivee)
				) {
			bool = true;
		}
		return bool;
	}
	
	public boolean deplacerPiecePourCaseAtteignable(Case[][] grille, Case caseDepart, Case caseArrivee) {

		boolean bool = false;

		if ( ((caseDepart.getRangee() == caseArrivee.getRangee()) || (caseDepart.getColonne() == caseArrivee.getColonne())) 
				&& ( grille[caseArrivee.getRangee()][caseArrivee.getColonne()].estVide() || !grille[caseArrivee.getRangee()][caseArrivee.getColonne()].estDeMemeCouleur(this.getCouleur()))

				&& !caseIdentique(caseDepart, caseArrivee)
				&& caseVide(grille, caseDepart, caseArrivee)
				) {
			bool = true;
		}
		return bool;
	}

	public String toString() {
		return "T";
	}

	@Override
	public HashSet<Case> obtenirCaseAtteignable(Case[][] grille, Case caseDepart) {

		HashSet<Case> listeCase = new HashSet<Case>();
		return this.obtenirCaseAtteignable(grille, caseDepart, caseDepart, listeCase, true, true, true, true);
	}

	public HashSet<Case> obtenirCaseAtteignable(Case[][] grille, Case caseDepart, Case caseSuivante, HashSet<Case> listeCase, boolean H, boolean B, boolean G, boolean D) {

//		System.out.println("caseDepart : "+caseDepart.getRangee()+","+caseDepart.getColonne());

		Case caseH = new Case(null, caseSuivante.getRangee()-1, caseSuivante.getColonne());
		Case caseB = new Case(null, caseSuivante.getRangee()+1, caseSuivante.getColonne());
		Case caseG = new Case(null, caseSuivante.getRangee(), caseSuivante.getColonne()-1);
		Case caseD = new Case(null, caseSuivante.getRangee(), caseSuivante.getColonne()+1);

		if (H && caseSuivante.getRangee() > 0 ) {
			if (this.deplacerPiecePourCaseAtteignable(grille, caseDepart, caseH)) {
				listeCase.add(new Case(null, caseH.getRangee(), caseH.getColonne()));
				this.obtenirCaseAtteignable(grille, caseDepart, caseH, listeCase, true, false, false, false);
			}
			else {
				H = false;
			}
			
		}
		if (B && caseSuivante.getRangee() < grille[0].length-1 ) {
			if (this.deplacerPiecePourCaseAtteignable(grille, caseDepart, caseB)) {
				listeCase.add(new Case(null, caseB.getRangee(), caseB.getColonne()));
				this.obtenirCaseAtteignable(grille, caseDepart, caseB, listeCase, false, true, false, false);
			}
			else {
				B = false;
			}
			
		}
		if (G && caseSuivante.getColonne() > 0 ) {
			if (this.deplacerPiecePourCaseAtteignable(grille, caseDepart, caseG)) {
				listeCase.add(new Case(null, caseG.getRangee(), caseG.getColonne()));
				this.obtenirCaseAtteignable(grille, caseDepart, caseG, listeCase, false, false, true, false);
			}
			else {
				G = false;
			}
			
		}
		if (D && caseSuivante.getColonne() < grille.length-1 ) {
			if (this.deplacerPiecePourCaseAtteignable(grille, caseDepart, caseD)) {
				listeCase.add(new Case(null, caseD.getRangee(), caseD.getColonne()));
				this.obtenirCaseAtteignable(grille, caseDepart, caseD, listeCase, false, false, false, true);
			}
			else {
				D = false;
			}
		}

		return listeCase;
	}

}