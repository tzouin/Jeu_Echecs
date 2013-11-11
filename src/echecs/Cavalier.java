package echecs;
import java.util.ArrayList;
import java.util.HashSet;

public class Cavalier extends Piece {

	public Cavalier(Couleur couleurPiece) {
		super(couleurPiece);
	}

	/**
	 * Cette méthode permet de vérifier qu'il n'y a pas une pièce de sa couleur ou un roi sur la case de destination.
	 * Le cavalier peut sauter par dessus les autres pièces, on ne soucie donc pas de savoir si les cases sont vides.
	 * */
	/*public boolean caseVide(Case[][] grille, Case caseDepart, Case caseArrivee){
		boolean bool=false;
		//Verification de la case d'arrivée (différent de roi ou pièce de sa couleur
		try{
			if (caseArrivee.getPiece().getCouleur() == this.getCouleur() || caseArrivee.getPiece() instanceof Roi)
				return false;
		}catch(Exception e){}

		//System.out.println("arrivee rangee = "+caseArrivee.getRangee());
		//System.out.println("depart rangee = "+caseDepart.getRangee());
		//System.out.println("arrivee colonne = "+caseArrivee.getColonne());
		//System.out.println("depart colonne = "+caseDepart.getColonne());
		if (caseArrivee.getRangee() - caseDepart.getRangee()==-2 && caseArrivee.getColonne() - caseDepart.getColonne() ==  1) bool=true;
		if (caseArrivee.getRangee() - caseDepart.getRangee()==-2 && caseArrivee.getColonne() - caseDepart.getColonne() == -1) bool=true;
		if (caseArrivee.getRangee() - caseDepart.getRangee()==-1 && caseArrivee.getColonne() - caseDepart.getColonne() ==  2) bool=true;
		if (caseArrivee.getRangee() - caseDepart.getRangee()==-1 && caseArrivee.getColonne() - caseDepart.getColonne() == -2) bool=true;
		if (caseArrivee.getRangee() - caseDepart.getRangee()== 1 && caseArrivee.getColonne() - caseDepart.getColonne() == -2) bool=true;
		if (caseArrivee.getRangee() - caseDepart.getRangee()== 1 && caseArrivee.getColonne() - caseDepart.getColonne() ==  2) bool=true;
		if (caseArrivee.getRangee() - caseDepart.getRangee()== 2 && caseArrivee.getColonne() - caseDepart.getColonne() == -1) bool=true;
		if (caseArrivee.getRangee() - caseDepart.getRangee()== 2 && caseArrivee.getColonne() - caseDepart.getColonne() ==  1) bool=true;

		return bool;
	}*/

	/**
	 * Cette méthode permet de vérifier la validité du déplacement*/
	/*public boolean deplacerPiece(Case[][] grille, Case caseDepart, Case caseArrivee) {
		if (caseVide(grille, caseDepart, caseArrivee)){
			return true;
		}
		return false;
	}*/

	public boolean deplacerPiece(Case[][] grille, Case caseDepart, Case caseArrivee) {

		boolean bool = false;

		int diffX = caseDepart.getRangee() - caseArrivee.getRangee();
		int diffY = caseDepart.getColonne() - caseArrivee.getColonne();

		if ( ((Math.abs(diffX) == 2 && Math.abs(diffY) == 1) || (Math.abs(diffX) == 1 && Math.abs(diffY) == 2)) 
				&& ( grille[caseArrivee.getRangee()][caseArrivee.getColonne()].estVide() || !grille[caseArrivee.getRangee()][caseArrivee.getColonne()].estDeMemeCouleur(this.getCouleur()))
				&& !(grille[caseArrivee.getRangee()][caseArrivee.getColonne()].getPiece() instanceof Roi)
				&& !caseIdentique(caseDepart, caseArrivee)
				) {
			bool = true;
		}

		return bool;
	}
	
	public boolean deplacerPiecePourCaseAtteignable(Case[][] grille, Case caseDepart, Case caseArrivee) {

		boolean bool = false;

		int diffX = caseDepart.getRangee() - caseArrivee.getRangee();
		int diffY = caseDepart.getColonne() - caseArrivee.getColonne();

		if ( ((Math.abs(diffX) == 2 && Math.abs(diffY) == 1) || (Math.abs(diffX) == 1 && Math.abs(diffY) == 2)) 
				&& ( grille[caseArrivee.getRangee()][caseArrivee.getColonne()].estVide() || !grille[caseArrivee.getRangee()][caseArrivee.getColonne()].estDeMemeCouleur(this.getCouleur()))
				
				&& !caseIdentique(caseDepart, caseArrivee)
				) {
			bool = true;
		}

		return bool;
	}

	public String toString() {
		return "C";
	}

	@Override
	public HashSet<Case> obtenirCaseAtteignable(Case[][] grille, Case caseDepart) {

		HashSet<Case> listeCase = new HashSet<Case>();

		Case case1 = new Case(null, caseDepart.getRangee()-2, caseDepart.getColonne()-1);
		Case case2 = new Case(null, caseDepart.getRangee()-2, caseDepart.getColonne()+1);
		Case case3 = new Case(null, caseDepart.getRangee()-1, caseDepart.getColonne()+2);
		Case case4 = new Case(null, caseDepart.getRangee()+1, caseDepart.getColonne()+2);
		Case case5 = new Case(null, caseDepart.getRangee()+2, caseDepart.getColonne()+1);
		Case case6 = new Case(null, caseDepart.getRangee()+2, caseDepart.getColonne()-1);
		Case case7 = new Case(null, caseDepart.getRangee()+1, caseDepart.getColonne()-2);
		Case case8 = new Case(null, caseDepart.getRangee()-1, caseDepart.getColonne()-2);

		if ( case1.getRangee() >= 0 
				&& case1.getRangee() < grille[0].length 
				&& case1.getColonne() >= 0 
				&& case1.getColonne() < grille.length 
				&& this.deplacerPiecePourCaseAtteignable(grille, caseDepart, case1) )
		{
			listeCase.add(new Case(null, case1.getRangee(), case1.getColonne()));
		}

		if ( case2.getRangee() >= 0 
				&& case2.getRangee() < grille[0].length 
				&& case2.getColonne() >= 0 
				&& case2.getColonne() < grille.length 
				&& this.deplacerPiecePourCaseAtteignable(grille, caseDepart, case2) )
		{
			listeCase.add(new Case(null, case2.getRangee(), case2.getColonne()));
		}

		if ( case3.getRangee() >= 0 
				&& case3.getRangee() < grille[0].length 
				&& case3.getColonne() >= 0 
				&& case3.getColonne() < grille.length 
				&& this.deplacerPiecePourCaseAtteignable(grille, caseDepart, case3) )
		{
			listeCase.add(new Case(null, case3.getRangee(), case3.getColonne()));
		}

		if ( case4.getRangee() >= 0 
				&& case4.getRangee() < grille[0].length 
				&& case4.getColonne() >= 0 
				&& case4.getColonne() < grille.length 
				&& this.deplacerPiecePourCaseAtteignable(grille, caseDepart, case4) )
		{
			listeCase.add(new Case(null, case4.getRangee(), case4.getColonne()));
		}

		if ( case5.getRangee() >= 0 
				&& case5.getRangee() < grille[0].length 
				&& case5.getColonne() >= 0 
				&& case5.getColonne() < grille.length 
				&& this.deplacerPiecePourCaseAtteignable(grille, caseDepart, case5) )
		{
			listeCase.add(new Case(null, case5.getRangee(), case5.getColonne()));
		}

		if ( case6.getRangee() >= 0 
				&& case6.getRangee() < grille[0].length 
				&& case6.getColonne() >= 0 
				&& case6.getColonne() < grille.length 
				&& this.deplacerPiecePourCaseAtteignable(grille, caseDepart, case6) )
		{
			listeCase.add(new Case(null, case6.getRangee(), case6.getColonne()));
		}

		if ( case7.getRangee() >= 0 
				&& case7.getRangee() < grille[0].length 
				&& case7.getColonne() >= 0 
				&& case7.getColonne() < grille.length 
				&& this.deplacerPiecePourCaseAtteignable(grille, caseDepart, case7) )
		{
			listeCase.add(new Case(null, case7.getRangee(), case7.getColonne()));
		}

		if ( case8.getRangee() >= 0 
				&& case8.getRangee() < grille[0].length 
				&& case8.getColonne() >= 0 
				&& case8.getColonne() < grille.length 
				&& this.deplacerPiecePourCaseAtteignable(grille, caseDepart, case8) )
		{
			listeCase.add(new Case(null, case8.getRangee(), case8.getColonne()));
		}

		return listeCase;
	}

}
