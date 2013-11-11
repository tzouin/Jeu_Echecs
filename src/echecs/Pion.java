package echecs;

import java.awt.Point;


import java.util.ArrayList;
import java.util.HashSet;

public class Pion extends Piece {

	private boolean dejaDeplace;

	public boolean isDejaDeplace() {
		return dejaDeplace;
	}

	public void setDejaDeplace(boolean dejaDeplace) {
		this.dejaDeplace = dejaDeplace;
	}

	public Pion(Couleur couleurPiece) {
		super(couleurPiece);
		dejaDeplace = false;
	}

	public String toString() {
		return "";
	}

	public boolean deplacerPiece(Case[][] grille, Case caseDepart,	Case caseArrivee) {
		
//		System.out.println("***************Deplacement");
//		System.out.println("deja deplace : "+dejaDeplace);
//		System.out.println("Case depart : "+caseDepart.getRangee()+","+caseDepart.getColonne());
//		System.out.println("Case depart : "+caseArrivee.getRangee()+","+caseArrivee.getColonne());

		boolean bool = false;

		int diffX = caseDepart.getRangee() - caseArrivee.getRangee();
		int diffY = caseDepart.getColonne() - caseArrivee.getColonne();

		// prise
		if ((( this.couleurPiece == Couleur.NOIR && diffX == -1 && Math.abs(diffY) == 1 ) || ( this.couleurPiece == Couleur.BLANC && diffX == 1 && Math.abs(diffY) == 1 )) 
				&& ( !grille[caseArrivee.getRangee()][caseArrivee.getColonne()].estVide() )
				&& ( !grille[caseArrivee.getRangee()][caseArrivee.getColonne()].estDeMemeCouleur(this.getCouleur()) )
				&& !(grille[caseArrivee.getRangee()][caseArrivee.getColonne()].getPiece() instanceof Roi)
				&& !caseIdentique(caseDepart, caseArrivee)
				) 
		{

			bool = true;
			//dejaDeplace = true;
			
		}

		// sinon si la piece a deja ete déplacée
		else if ( dejaDeplace 
				&& (( this.couleurPiece == Couleur.NOIR && caseDepart.getRangee() == caseArrivee.getRangee() - 1 && caseDepart.getColonne() == caseArrivee.getColonne() ) 
						|| ( this.couleurPiece == Couleur.BLANC && caseDepart.getRangee() == caseArrivee.getRangee() + 1 && caseDepart.getColonne() == caseArrivee.getColonne() )) 
						&& ( ( grille[caseArrivee.getRangee()][caseArrivee.getColonne()].estVide() ) )
						&& !caseIdentique(caseDepart, caseArrivee)
				) 
		{
			bool = true;
			
		}
		// sinon si la piece n'a pas encore été déplacée
		else if ( (( this.couleurPiece == Couleur.NOIR && (caseDepart.getRangee() == caseArrivee.getRangee() - 1 ||  caseDepart.getRangee() == caseArrivee.getRangee() - 2) && caseDepart.getColonne() == caseArrivee.getColonne()) 
				|| ( this.couleurPiece == Couleur.BLANC && ( caseDepart.getRangee() == caseArrivee.getRangee() + 1 || caseDepart.getRangee() == caseArrivee.getRangee() + 2 ) && caseDepart.getColonne() == caseArrivee.getColonne() )) 
				&& !dejaDeplace
				&& ( ( grille[caseArrivee.getRangee()][caseArrivee.getColonne()].estVide() ) )
				&& !caseIdentique(caseDepart, caseArrivee)
				) 
		{
			bool = true;
			//dejaDeplace = true;
			
		}
		//prise en passant noir
		else if (diffX==-1 && (diffY==-1 || diffY==1) 
				&& couleurPiece==Couleur.NOIR 
				&& caseArrivee.estVide()
				) {

			if (caseArrivee.getRangee()==5 && caseDepart.getRangee()==4){
//				System.out.println("colonne pion adverse : "+(caseDepart.getColonne()-diffY));
				if (!grille[caseDepart.getRangee()][caseDepart.getColonne()-diffY].estVide()
						&& grille[caseDepart.getRangee()][caseDepart.getColonne()-diffY].getPiece() instanceof Pion
						&& grille[caseDepart.getRangee()][caseDepart.getColonne()-diffY].getPiece().getCouleur() != couleurPiece){
					return true;
				}
			}


			return false;
		}
		//prise en passant blanc
		else if (diffX==1 && (diffY==-1 || diffY==1) 
				&& couleurPiece==Couleur.BLANC
				&& caseArrivee.estVide()
				) {

			if (caseArrivee.getRangee()==2 && caseDepart.getRangee()==3){
				
				if (!grille[caseDepart.getRangee()][caseDepart.getColonne()-diffY].estVide()
						&& grille[caseDepart.getRangee()][caseDepart.getColonne()-diffY].getPiece() instanceof Pion
						&& grille[caseDepart.getRangee()][caseDepart.getColonne()-diffY].getPiece().getCouleur() != couleurPiece){
					return true;
				}
			}


			return false;
		}

		return bool;
	}

	@Override
	public HashSet<Case> obtenirCaseAtteignable(Case[][] grille, Case caseDepart) {
		
		HashSet<Case> listeCase = new HashSet<Case>();
		
		if (this.couleurPiece == Couleur.BLANC) {
			// en haut + 1
			Case case1 = new Case(null, caseDepart.getRangee()-1, caseDepart.getColonne());
			// en haut + 2
			Case case2 = new Case(null, caseDepart.getRangee()-2, caseDepart.getColonne());
			// a gauche prise
			Case case3 = new Case(null, caseDepart.getRangee()-1, caseDepart.getColonne()-1);
			// a droite prise
			Case case4 = new Case(null, caseDepart.getRangee()-1, caseDepart.getColonne()+1);
			 
//			if ( case2.getRangee() >= 0 
//					&& case2.getRangee() < grille[0].length 
//					&& case2.getColonne() >= 0 
//					&& case2.getColonne() < grille.length 
//					&& this.deplacerPiece(grille, caseDepart, case2) )
//			{
//				listeCase.add(new Case(null, case2.getRangee(), case2.getColonne()));
//			}
//			
//			if (case1.getRangee() >= 0 
//					&& case1.getRangee() < grille[0].length 
//					&& case1.getColonne() >= 0 
//					&& case1.getColonne() < grille.length 
//					&& this.deplacerPiece(grille, caseDepart, case1)) 
//			{
//				listeCase.add(new Case(null, case1.getRangee(), case1.getColonne()));
//			}
			

			if ( case3.getRangee() >= 0 
					&& case3.getRangee() < grille[0].length 
					&& case3.getColonne() >= 0 
					&& case3.getColonne() < grille.length 
					/*&& this.deplacerPiece(grille, caseDepart, case3)*/ )
			{
				listeCase.add(new Case(null, case3.getRangee(), case3.getColonne()));
			}

			if ( case4.getRangee() >= 0 
					&& case4.getRangee() < grille[0].length 
					&& case4.getColonne() >= 0 
					&& case4.getColonne() < grille.length 
					/*&& this.deplacerPiece(grille, caseDepart, case4)*/ )
			{
				listeCase.add(new Case(null, case4.getRangee(), case4.getColonne()));
			}
			
		}
		else if (this.couleurPiece == Couleur.NOIR) {
			
			// en bas + 1
			Case case1 = new Case(null, caseDepart.getRangee()+1, caseDepart.getColonne());
			// en bas + 2
			Case case2 = new Case(null, caseDepart.getRangee()+2, caseDepart.getColonne());
			// a droite prise
			Case case3 = new Case(null, caseDepart.getRangee()+1, caseDepart.getColonne()+1);
			// a gauche prise
			Case case4 = new Case(null, caseDepart.getRangee()+1, caseDepart.getColonne()-1);
			
//			System.out.println("case2 : "+case2.getRangee()+","+case2.getColonne());
//			
//			
//			System.out.println("deplacer1 : "+(case2.getRangee() >= 0 ));
//			System.out.println("deplacer2 : "+(case2.getRangee() < grille[0].length ));
//			System.out.println("deplacer3 : "+(case2.getColonne() >= 0  ));
//			System.out.println("deplacer4 : "+(case2.getColonne() < grille.length ));
			
//			if ( case2.getRangee() >= 0 
//					&& case2.getRangee() < grille[0].length 
//					&& case2.getColonne() >= 0 
//					&& case2.getColonne() < grille.length 
//					&& this.deplacerPiece(grille, caseDepart, case2) )
//			{
//				System.out.println("deplacer : bonjour");
//				listeCase.add(new Case(null, case2.getRangee(), case2.getColonne()));
//			}
//			
//			if (case1.getRangee() >= 0 
//					&& case1.getRangee() < grille[0].length 
//					&& case1.getColonne() >= 0 
//					&& case1.getColonne() < grille.length 
//					&& this.deplacerPiece(grille, caseDepart, case1)) 
//			{
//				listeCase.add(new Case(null, case1.getRangee(), case1.getColonne()));
//			}
			//System.out.println("deplacer5 : "+this.deplacerPiece(grille, caseDepart, case2));
			

			if ( case3.getRangee() >= 0 
					&& case3.getRangee() < grille[0].length 
					&& case3.getColonne() >= 0 
					&& case3.getColonne() < grille.length 
					/*&& this.deplacerPiece(grille, caseDepart, case3)*/ )
			{
				listeCase.add(new Case(null, case3.getRangee(), case3.getColonne()));
			}

			if ( case4.getRangee() >= 0 
					&& case4.getRangee() < grille[0].length 
					&& case4.getColonne() >= 0 
					&& case4.getColonne() < grille.length 
					/*&& this.deplacerPiece(grille, caseDepart, case4)*/ )
			{
				listeCase.add(new Case(null, case4.getRangee(), case4.getColonne()));
			}
			
		}
			
		return listeCase;
	}



}