package echecs;

import java.awt.Point;


import java.util.ArrayList;
import java.util.HashSet;

public class Fou extends Piece {

	public Fou(Couleur couleurPiece) {
		super(couleurPiece);
	}

	public boolean caseVide(Case[][] grille, Case caseDepart, Case caseArrivee){
		
//		System.out.println("*****************************CASE VIDE FALSE*****************************************");
//		System.out.println("caseDepart : "+caseDepart.getRangee()+","+caseDepart.getColonne());
//		System.out.println("caseArrive : "+caseArrivee.getRangee()+","+caseArrivee.getColonne());
		
		boolean bool = true;
		int departX = (caseDepart.getRangee() <= caseArrivee.getRangee()) ? caseDepart.getRangee() : caseArrivee.getRangee() ;
		int departY = (caseDepart.getRangee() <= caseArrivee.getRangee()) ? caseDepart.getColonne() : caseArrivee.getColonne() ;
		
//		System.out.println("departX : "+departX);
//		System.out.println("departY : "+departY);
		
		int arriveX = (caseDepart.getRangee() <= caseArrivee.getRangee()) ? caseArrivee.getRangee() : caseDepart.getRangee() ;
		int arriveY = (caseDepart.getRangee() <= caseArrivee.getRangee()) ? caseArrivee.getColonne() : caseDepart.getColonne() ;
		
//		System.out.println("arriveX : "+arriveX);
//		System.out.println("arriveY : "+arriveY);
		
		int incX = 1;
		int incY = (departY <= arriveY) ? 1 : -1 ;
		
//		System.out.println("incX : "+incX);
//		System.out.println("incY : "+incY);
		
		int taille = arriveX - departX - 1;
		
//		System.out.println("taille : "+taille);
		
//		System.out.println("Case [5, 4] vide : "+!grille[5][4].estVide());
		
		for(int i=0; i<taille; i++) {
			
//			System.out.println("BOUCLE : ++++++++++++++++"+i);
//			System.out.println("departX+incX : "+(departX+incX));
//			System.out.println("departY+incY : "+(departY+incY));
//			System.out.println("est vide : "+(grille[departX+incX][departY+incY].estVide()));
			
			departX += incX;
			departY += incY;
			
			if (!grille[departX][departY].estVide()) {
//				System.out.println("************VIDE******************");
				bool = bool & false;
			}
		}
		return bool;
	}

	public boolean deplacerPiece(Case[][] grille, Case caseDepart, Case caseArrivee) {
		boolean bool = false;
		int diffX = caseDepart.getRangee() - caseArrivee.getRangee();
		int diffY = caseDepart.getColonne() - caseArrivee.getColonne();
		
//		System.out.println("**********************************************************************");
//		System.out.println("caseDepart : "+caseDepart.getRangee()+","+caseDepart.getColonne());
//		System.out.println("caseArrive : "+caseArrivee.getRangee()+","+caseArrivee.getColonne());
//		System.out.println("PIECE : "+grille[caseArrivee.getRangee()][caseArrivee.getColonne()].getPiece());
//		System.out.println("diffX : "+diffX);
//		System.out.println("diffY : "+diffY);
//		System.out.println("diffX == diffY : "+(diffX==diffY));
//		System.out.println("(diffX == ((-1) * diffY)) : "+(diffX == ((-1) * diffY)));
//		System.out.println("case arrive vide : "+grille[caseArrivee.getRangee()][caseArrivee.getColonne()].estVide());
//		System.out.println("case arrive de meme couleur : "+!grille[caseArrivee.getRangee()][caseArrivee.getColonne()].estDeMemeCouleur(this.getCouleur()));
		
//		System.out.println("caseArrivee.estVide() : "+caseArrivee.estVide());
//		System.out.println("caseArrivee.estVide() : "+caseArrivee.estVide());
		//System.out.println("caseArrivee.getPiece().getCouleur() != this.getCouleur() : "+(caseArrivee.getPiece().getCouleur() != this.getCouleur()));
		
		/*grille[caseArrivee.getRangee()][caseArrivee.getColonne()].getPiece().getCouleur() != this.getCouleur() ||*/
//		System.out.println("TEST : "+caseArrivee.getColonne() );
//		
//		System.out.println("caseDepart : "+caseDepart.getRangee()+","+caseDepart.getColonne());
//		System.out.println("caseArrive : "+caseArrivee.getRangee()+","+caseArrivee.getColonne());
//		
//		
//		
//		System.out.println("case vide : "+ grille[caseArrivee.getRangee()][caseArrivee.getColonne()].estVide() );
//		System.out.println("couleur case  : "+this.getCouleur());
//		System.out.println("couleur case arrive : "+grille[caseArrivee.getRangee()][caseArrivee.getColonne()].couleurCase());
//		
//		System.out.println("case meme couleur : "+ grille[caseArrivee.getRangee()][caseArrivee.getColonne()].estDeMemeCouleur(this.getCouleur()) );
//		
//		System.out.println("case instance roi : "+(grille[caseArrivee.getRangee()][caseArrivee.getColonne()].getPiece() instanceof Roi) );
//		
//		System.out.println("case identique depart arrive : "+caseIdentique(caseDepart, caseArrivee) );
//		System.out.println("case vide depart arrive : "+caseVide(grille, caseDepart, caseArrivee) );
		
		if ( ( (diffX == diffY) || (diffX == ((-1) * diffY)) ) 
				
				&& ( grille[caseArrivee.getRangee()][caseArrivee.getColonne()].estVide() || !grille[caseArrivee.getRangee()][caseArrivee.getColonne()].estDeMemeCouleur(this.getCouleur()))
				&& !(grille[caseArrivee.getRangee()][caseArrivee.getColonne()].getPiece() instanceof Roi)
				&& !caseIdentique(caseDepart, caseArrivee)
				&& caseVide(grille, caseDepart, caseArrivee)
			) {
//			System.out.println("on ajoute");
			bool = true;
		}
		
//		System.out.println("*******************************************************************************");
		return bool;
	}

	public boolean deplacerPiecePourCaseAtteignable(Case[][] grille, Case caseDepart, Case caseArrivee) {
		boolean bool = false;
		int diffX = caseDepart.getRangee() - caseArrivee.getRangee();
		int diffY = caseDepart.getColonne() - caseArrivee.getColonne();
		
		if ( ( (diffX == diffY) || (diffX == ((-1) * diffY)) ) 
				
				&& ( grille[caseArrivee.getRangee()][caseArrivee.getColonne()].estVide() || !grille[caseArrivee.getRangee()][caseArrivee.getColonne()].estDeMemeCouleur(this.getCouleur()))
				
				&& !caseIdentique(caseDepart, caseArrivee)
				&& caseVide(grille, caseDepart, caseArrivee)
			) {
			bool = true;
		}
		
		return bool;
	}

	public String toString() {
		return "F";
	}

	@Override
	public HashSet<Case> obtenirCaseAtteignable(Case[][] grille, Case caseDepart) {
		
		HashSet<Case> listeCase = new HashSet<Case>();
		return this.obtenirCaseAtteignable(grille, caseDepart, caseDepart, listeCase, true, true, true, true);
	}
	
	public HashSet<Case> obtenirCaseAtteignable(Case[][] grille, Case caseDepart, Case caseSuivante, HashSet<Case> listeCase, boolean HG, boolean HD, boolean BG, boolean BD) {

//		System.out.println("caseDepart : "+caseDepart.getRangee()+","+caseDepart.getColonne());
//		System.out.println("caseSuivante : "+caseSuivante.getRangee()+","+caseSuivante.getColonne());

		Case caseHG = new Case(null, caseSuivante.getRangee()-1, caseSuivante.getColonne()-1);
		Case caseHD = new Case(null, caseSuivante.getRangee()-1, caseSuivante.getColonne()+1);
		Case caseBG = new Case(null, caseSuivante.getRangee()+1, caseSuivante.getColonne()-1);
		Case caseBD = new Case(null, caseSuivante.getRangee()+1, caseSuivante.getColonne()+1);
		
		//System.out.println("caseHG : "+caseHG.getRangee()+","+caseHG.getColonne());
		
		

		if (HG && caseSuivante.getRangee() > 0 && caseSuivante.getColonne() > 0 ) {
			
//			System.out.println("PIECE : "+grille[caseHG.getRangee()][caseHG.getColonne()].getPiece());
//			System.out.println("caseDepart : "+caseDepart.getRangee()+","+caseDepart.getColonne());
//			System.out.println("caseHG : "+caseHG.getRangee()+","+caseHG.getColonne());
//			System.out.println("deplacerPiece************* : "+this.deplacerPiece(grille, caseDepart, caseHG));
			
			if (this.deplacerPiecePourCaseAtteignable(grille, caseDepart, caseHG)) {
				listeCase.add(new Case(null, caseHG.getRangee(), caseHG.getColonne()));
				this.obtenirCaseAtteignable(grille, caseDepart, caseHG, listeCase, true, false, false, false);
			}
			else {
				HG = false;
			}
			
		}
		if (HD && caseSuivante.getRangee() > 0 && caseSuivante.getColonne() < grille.length-1 ) {
			
//			System.out.println("HD PIECE : "+grille[caseHD.getRangee()][caseHD.getColonne()].getPiece());
//			System.out.println("HD caseDepart : "+caseDepart.getRangee()+","+caseDepart.getColonne());
//			System.out.println("HD caseHD : "+caseHD.getRangee()+","+caseHD.getColonne());
//			System.out.println("************HD deplacerPiece************* : "+this.deplacerPiece(grille, caseDepart, caseHD));
			
			if (this.deplacerPiecePourCaseAtteignable(grille, caseDepart, caseHD)) {
				listeCase.add(new Case(null, caseHD.getRangee(), caseHD.getColonne()));
				this.obtenirCaseAtteignable(grille, caseDepart, caseHD, listeCase, false, true, false, false);
			}
			else {
				HD = false;
			}
			
		}
		if (BG && caseSuivante.getRangee() < grille[0].length-1 && caseSuivante.getColonne() > 0 ) {
			if (this.deplacerPiecePourCaseAtteignable(grille, caseDepart, caseBG)) {
				listeCase.add(new Case(null, caseBG.getRangee(), caseBG.getColonne()));
				this.obtenirCaseAtteignable(grille, caseDepart, caseBG, listeCase, false, false, true, false);
			}
			else {
				BG = false;
			}
			
		}
		if (BD && caseSuivante.getRangee() < grille[0].length-1 && caseSuivante.getColonne() < grille.length-1 ) {
			if (this.deplacerPiecePourCaseAtteignable(grille, caseDepart, caseBD)) {
				listeCase.add(new Case(null, caseBD.getRangee(), caseBD.getColonne()));
				this.obtenirCaseAtteignable(grille, caseDepart, caseBD, listeCase, false, false, false, true);
			}
			else {
				BD = false;
			}
		}

		return listeCase;
	}


}
