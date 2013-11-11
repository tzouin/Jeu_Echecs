package echecs;

import java.awt.Point;



import java.util.ArrayList;
import java.util.HashSet;

public class Roi extends Piece {
	
	private boolean dejaJoue;

	public Roi(Couleur couleurPiece) {
		super(couleurPiece);
		dejaJoue = false;
	}

	public boolean isEchec(Case[][] grille, Case caseArrivee) {
		
//		System.out.println("***************is echec");
//		System.out.println("case arrive : ["+caseArrivee.getRangee()+";"+caseArrivee.getColonne()+"]");
		
		for(int i=0; i<grille.length; i++) {
			for(int j=0; j<grille[i].length; j++) {
				if (!grille[i][j].estVide() && this.couleurPiece != grille[i][j].couleurCase() ) {
					if (grille[i][j].getPiece() instanceof Cavalier
						|| grille[i][j].getPiece() instanceof Tour
						|| grille[i][j].getPiece() instanceof Fou
						|| grille[i][j].getPiece() instanceof Dame
						) {
						if (grille[i][j].getPiece().deplacerPiece(grille, grille[i][j], caseArrivee) ) {
							return true;
						}
					}
					else if (grille[i][j].getPiece() instanceof Roi) {
						int diffX = grille[i][j].getRangee() - caseArrivee.getRangee();
						int diffY = grille[i][j].getColonne() - caseArrivee.getColonne();
						if ( ((Math.abs(diffX) <= 1) && (Math.abs(diffY) <= 1)) 
								&& ( ( grille[caseArrivee.getRangee()][caseArrivee.getColonne()].estVide() ) || ( this.couleurPiece != grille[caseArrivee.getRangee()][caseArrivee.getColonne()].couleurCase()) )
								&& !caseIdentique(grille[i][j], caseArrivee)) {
							return true;
						}
					}
					else if (grille[i][j].getPiece() instanceof Pion) {
						int diffX = grille[i][j].getRangee() - caseArrivee.getRangee();
						int diffY = grille[i][j].getColonne() - caseArrivee.getColonne();
						if ((( grille[i][j].getPiece().getCouleur() == Couleur.NOIR && diffX == -1 && Math.abs(diffY) == 1 ) || ( grille[i][j].getPiece().getCouleur() == Couleur.BLANC && diffX == 1 && Math.abs(diffY) == 1 )) ) {
							return true;
						}
					}
					
					
					
				}
			}
		}
		return false;
	}

	public boolean deplacerPiece(Case[][] grille, Case caseDepart, Case caseArrivee) {
		boolean bool = false;

		int diffX = caseDepart.getRangee() - caseArrivee.getRangee();
		int diffY = caseDepart.getColonne() - caseArrivee.getColonne();

//		System.out.println("***************");
//		System.out.println("est echec : "+isEchec(grille, caseArrivee));
		
		if ( ((Math.abs(diffX) <= 1) && (Math.abs(diffY) <= 1)) 
				&& ( grille[caseArrivee.getRangee()][caseArrivee.getColonne()].estVide() || !grille[caseArrivee.getRangee()][caseArrivee.getColonne()].estDeMemeCouleur(this.getCouleur()))
				&& !(grille[caseArrivee.getRangee()][caseArrivee.getColonne()].getPiece() instanceof Roi)
				&& !caseIdentique(caseDepart, caseArrivee)
				/*&& !isEchec(grille, caseArrivee)*/
				) {
			bool = true;
		}

		//Verification que les deux rois ne se "collent" pas
		for (int i=-1;i==1;i++){
			for (int j=-1;j==1;j++){
				int rangeeTest=caseArrivee.getRangee()+i;
				int colonneTest=caseArrivee.getColonne()+j;

				if (rangeeTest <8 && colonneTest<8 && rangeeTest > 0 && colonneTest>0){
					if (!grille[rangeeTest][colonneTest].estVide()){
						if (grille[rangeeTest][colonneTest].getPiece() instanceof Roi
								&& grille[rangeeTest][colonneTest].getPiece().getCouleur() != couleurPiece) 
							bool=false;
					}
				}
			}
		}

		return bool;
	}
	
	public boolean deplacerPiecePourCaseAtteignable(Case[][] grille, Case caseDepart, Case caseArrivee) {
		boolean bool = false;

		int diffX = caseDepart.getRangee() - caseArrivee.getRangee();
		int diffY = caseDepart.getColonne() - caseArrivee.getColonne();

//		System.out.println("***************");
//		System.out.println("est echec : "+isEchec(grille, caseArrivee));
		
		if ( ((Math.abs(diffX) <= 1) && (Math.abs(diffY) <= 1)) 
				&& ( grille[caseArrivee.getRangee()][caseArrivee.getColonne()].estVide() || !grille[caseArrivee.getRangee()][caseArrivee.getColonne()].estDeMemeCouleur(this.getCouleur()))
				
				&& !caseIdentique(caseDepart, caseArrivee)
				/*&& !isEchec(grille, caseArrivee)*/
				) {
			bool = true;
		}

		//Verification que les deux rois ne se "collent" pas
		for (int i=-1;i==1;i++){
			for (int j=-1;j==1;j++){
				int rangeeTest=caseArrivee.getRangee()+i;
				int colonneTest=caseArrivee.getColonne()+j;

				if (rangeeTest <8 && colonneTest<8 && rangeeTest > 0 && colonneTest>0){
					if (!grille[rangeeTest][colonneTest].estVide()){
						if (grille[rangeeTest][colonneTest].getPiece() instanceof Roi
								&& grille[rangeeTest][colonneTest].getPiece().getCouleur() != couleurPiece) 
							bool=false;
					}
				}
			}
		}

		return bool;
	}

	public boolean isDejaJoue() {
		return dejaJoue;
	}

	public void setDejaJoue(boolean dejaJoue) {
		this.dejaJoue = dejaJoue;
	}

	public String toString() {
		return "R";
	}

	@Override
	public HashSet<Case> obtenirCaseAtteignable(Case[][] grille, Case caseDepart) {

		HashSet<Case> listeCase = new HashSet<Case>();

		Case case1 = new Case(null, caseDepart.getRangee()-1, caseDepart.getColonne()-1);
		Case case2 = new Case(null, caseDepart.getRangee()-1, caseDepart.getColonne());
		Case case3 = new Case(null, caseDepart.getRangee()-1, caseDepart.getColonne()+1);
		Case case4 = new Case(null, caseDepart.getRangee(), caseDepart.getColonne()+1);
		Case case5 = new Case(null, caseDepart.getRangee()+1, caseDepart.getColonne()+1);
		Case case6 = new Case(null, caseDepart.getRangee()+1, caseDepart.getColonne());
		Case case7 = new Case(null, caseDepart.getRangee()+1, caseDepart.getColonne()-1);
		Case case8 = new Case(null, caseDepart.getRangee(), caseDepart.getColonne()-1);

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
