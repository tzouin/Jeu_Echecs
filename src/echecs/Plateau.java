package echecs;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;



import echecsController.EchiquierController;
import echecsView.FenetreJeu;


import observable.Observable;
import observable.Observer;

public class Plateau implements Observable{

	private Joueur joueur1;
	private Joueur joueur2;
	public Case[][] grille;
	private Couleur trait;
	private  ArrayList<Observer> listObserver;
	private ArrayList<String> coups;
	private boolean joueurBloque;
	
	public void setJoueur1(Joueur joueur1) {
		this.joueur1 = joueur1;
	}

	public Joueur getJoueur1() {
		return joueur1;
	}

	public void setJoueur2(Joueur joueur2) {
		this.joueur2 = joueur2;
	}

	public Joueur getJoueur2() {
		return joueur2;
	}

	public void setGrille(Case[][] grille) {
		this.grille = grille;
	}

	public Case[][] getGrille() {
		return grille;
	}

	public Plateau() {
		
		this.joueur1 = new Joueur("Bruno", Couleur.BLANC);
		this.joueur2 = new Joueur("Cyril", Couleur.NOIR);
		listObserver = new ArrayList<Observer>();
		coups        = new ArrayList<String>();
		trait   	 = Couleur.BLANC;
		this.grille  = new Case[8][8];

		for(int i=0; i<grille.length; i++) {
			for(int j=0; j<grille[i].length; j++) {
				grille[i][j] = new Case(null,i,j);
			}
		}

		grille[0][0].setPiece(new Tour(Couleur.NOIR));
		grille[0][1].setPiece(new Cavalier(Couleur.NOIR));
		grille[0][2].setPiece(new Fou(Couleur.NOIR));
		grille[0][3].setPiece(new Dame(Couleur.NOIR));
		grille[0][4].setPiece(new Roi(Couleur.NOIR));
		grille[0][5].setPiece(new Fou(Couleur.NOIR));
		grille[0][6].setPiece(new Cavalier(Couleur.NOIR));
		grille[0][7].setPiece(new Tour(Couleur.NOIR));
		grille[1][0].setPiece(new Pion(Couleur.NOIR));
		grille[1][1].setPiece(new Pion(Couleur.NOIR));
		grille[1][2].setPiece(new Pion(Couleur.NOIR));
		grille[1][3].setPiece(new Pion(Couleur.NOIR));
		grille[1][4].setPiece(new Pion(Couleur.NOIR));
		grille[1][5].setPiece(new Pion(Couleur.NOIR));
		grille[1][6].setPiece(new Pion(Couleur.NOIR));
		grille[1][7].setPiece(new Pion(Couleur.NOIR));

		grille[6][0].setPiece(new Pion(Couleur.BLANC));
		grille[6][1].setPiece(new Pion(Couleur.BLANC));
		grille[6][2].setPiece(new Pion(Couleur.BLANC));
		grille[6][3].setPiece(new Pion(Couleur.BLANC));
		grille[6][4].setPiece(new Pion(Couleur.BLANC));
		grille[6][5].setPiece(new Pion(Couleur.BLANC));
		grille[6][6].setPiece(new Pion(Couleur.BLANC));
		grille[6][7].setPiece(new Pion(Couleur.BLANC));
		grille[7][0].setPiece(new Tour(Couleur.BLANC));
		grille[7][1].setPiece(new Cavalier(Couleur.BLANC));
		grille[7][2].setPiece(new Fou(Couleur.BLANC));
		grille[7][3].setPiece(new Dame(Couleur.BLANC));
		grille[7][4].setPiece(new Roi(Couleur.BLANC));
		grille[7][5].setPiece(new Fou(Couleur.BLANC));
		grille[7][6].setPiece(new Cavalier(Couleur.BLANC));
		grille[7][7].setPiece(new Tour(Couleur.BLANC));
		
		joueurBloque = false;
		
		//aLeTrait=true;
		addObserver();

	}

	public boolean isJoueurBloque() {
		return joueurBloque;
	}

	@Override
	public void addObserver() {
		this.listObserver.add(new FenetreJeu(new EchiquierController(this)));
		notifyObserver(null,false,false,false);
	}

	@Override
	public void notifyObserver(ArrayList<String> coups, boolean promotion, boolean echecEtMat, boolean pat) {
		Iterator it = listObserver.iterator() ;
		while (it.hasNext()) {
			((FenetreJeu) it.next()).MiseAJourVue("coucou") ;
		}
	}

	@Override
	public void removeObserver(Observer obs) {
		listObserver.remove(obs);
	}

	
	
	public void envoyerCoupClient(Coup coupAEnvoyer){
		final Coup coup=coupAEnvoyer;
		notifyObserver(coups, coup.isPromotion(),coup.isEchecEtMat(), coup.isPat());

	}

	
	public void unNouveauCoup(int xDepart, int yDepart, int xArrive, int yArrive) {
		
		try{
			if(!grille[xDepart][yDepart].estVide()) {

				Coup coup=new Coup(grille[xDepart][yDepart], grille[xArrive][yArrive]);
				if (grille[xDepart][yDepart].getPiece().getCouleur()==trait){

					// on déplace une piece sans coup spécial
					if(grille[xDepart][yDepart].getPiece().deplacerPiece(grille, grille[xDepart][yDepart], grille[xArrive][yArrive])
							&& !checkEchec(new Case(null, xDepart, yDepart), new Case(null, xArrive, yArrive),grille, trait)
							){

						coup.setPriseEnPassant(checkPriseEnPassant(xDepart, yDepart, xArrive, yArrive));

						setDeplace(xDepart, yDepart);
						grille[xArrive][yArrive].setPiece(grille[xDepart][yDepart].getPiece());

						grille[xDepart][yDepart].setPiece(null);

						/*Vérification des "évènements" spéciaux (echec, promotion, etc...)*/
						coup.setPromotion(checkPromotion());
						coup.setEchec(checkFaitEchec());
						if (coupPossibleAdversaire().isEmpty()){
							if (coup.isEchec()){
								coup.setEchec(false);
								coup.setEchecEtMat(true);
							}else
								coup.setPat(true);
						}

						coups.add(coup.toString());
						notifyObserver(coups, coup.isPromotion(),coup.isEchecEtMat(), coup.isPat());
						switchTrait();
						
						//on envoie le coup joué
						if (!joueurBloque){
							joueurBloque = true;
							envoyerCoupClient(coup);
						}
						else{
							
							joueurBloque=false;
						}

						// sinon si roque
					}else if (checkRoque(xDepart, yDepart, xArrive, yArrive)) {
						setDeplace(xDepart,yDepart);
						//Effectue le roque
						coup.setRoque(effectuerRoque(xDepart,yDepart,xArrive,yArrive));						
						coup.setEchec(checkFaitEchec());
						if (coupPossibleAdversaire().isEmpty()){
							if (coup.isEchec()){
								coup.setEchec(false);
								coup.setEchecEtMat(true);
							}else
								coup.setPat(true);
						}

						coups.add(coup.toString());
						notifyObserver(coups, false, false, false);
						switchTrait();

					}else{
						System.out.println("Ce déplacement n'est pas autorisé !");
					}
				}else{
					System.out.println("Ce n'est pas votre tour");
				}
			}else{
				System.out.println("Vous avez selectionne une case de départ vide");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Cette méthode permet d'appeler la méthode Clone pour chaque case, 
	 * et ainsi créer un nouvel objet totalement indépendant de la grille.
	 * @see Cloneable
	 * */
	public Case[][] getClonedGrille(Case[][] grilleToConvert){
		Case[][] grilleCloned=new Case[8][8];
		for (int i=0;i<8;i++){
			for (int j=0;j<8;j++){
				try {
					grilleCloned[i][j]=(Case) grilleToConvert[i][j].clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
		}
		return grilleCloned;
	}

	/** 
	 * Cette méthode permet de vérifier si le roi est en échec
	 * @return vrai si le roi est en échec, faux sinon
	 */
	public boolean checkEchec(Case caseDepart, Case caseArrive, Case[][] grilleToTest, Couleur joueur) {
		Case caseDuRoi;
		Case[][] grilleApresCoup=getClonedGrille(grilleToTest);

		grilleApresCoup[caseArrive.getRangee()][caseArrive.getColonne()].setPiece(grilleApresCoup[caseDepart.getRangee()][caseDepart.getColonne()].getPiece());
		grilleApresCoup[caseDepart.getRangee()][caseDepart.getColonne()].setPiece(null);

		HashSet<Case> listeCaseJoueurAdverse = obtenirListeCaseAtteignableJoueurAdverse(grilleApresCoup, joueur);

		if (joueur==trait)
			caseDuRoi=obtenirCaseRoi(grilleApresCoup);
		else
			caseDuRoi=obtenirCaseRoiAdverse(grilleApresCoup);
		if (casePresenteDansListe(listeCaseJoueurAdverse, caseDuRoi)) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Cette méthode permet de trouver la liste de tous les coups possibles pour l'adversaire.
	 * @return ArrayList<Coup> représentant tout les coups possibles.
	 * */
	public ArrayList<Coup> coupPossibleAdversaire(){
		Case[][] grilleToTest=getClonedGrille(grille);
		ArrayList<Coup> coupsPossibles=new ArrayList<Coup>();
		Couleur joueurAdverse=trait;
		if (trait == Couleur.BLANC)
			joueurAdverse=Couleur.NOIR;
		else
			joueurAdverse=Couleur.BLANC;

		for (int i=0;i<8;i++){
			for (int j=0;j<8;j++){
				try{
					if (grilleToTest[i][j].getPiece().getCouleur() == joueurAdverse){
						Piece pieceEnCours=grilleToTest[i][j].getPiece();

						HashSet<Case> listeCase=pieceEnCours.obtenirCaseAtteignable(grilleToTest, grilleToTest[i][j]);
						for (Case c : listeCase) {
							if (!checkEchec(grilleToTest[i][j], c, grilleToTest, joueurAdverse))
								coupsPossibles.add(new Coup(grilleToTest[i][j], c));
						}
					}
				}catch(Exception e){
					//e.printStackTrace();
				}
			}
		}

		//System.out.println("coups Possibles : "+coupsPossibles+"\n");
		return coupsPossibles;
	}

	/**
	 * Cette méthode permet de vérifier si l'adversaire n'a plus aucun coup possible. 
	 * */
	public boolean checkFaitMat(){
		ArrayList<Coup> lstCoups=new ArrayList<Coup>();
		lstCoups=coupPossibleAdversaire();

		if (lstCoups.isEmpty()){
			return true;
		}else{
			return false;
		}
	}


	/**
	 * Cette méthode retourne vrai si le coup joué fait échec.
	 * */
	public boolean checkFaitEchec(){
		Case caseDuRoi=obtenirCaseRoiAdverse(grille);
		HashSet<Case> listeCaseJoueurAdverse = obtenirListeCaseAtteignableJoueurEnCours();

		if (casePresenteDansListe(listeCaseJoueurAdverse, caseDuRoi)) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Cette méthode permet d'obtenir la case ou se trouve le roi adverse
	 * @param Case[][] grilleToTest qui permet d'analyser une grille différente de celle en cours.
	 * @return la case du roi adverse
	 */
	public Case obtenirCaseRoiAdverse(Case[][] grilleToTest) {
		Couleur couleurAdverse = trait==Couleur.BLANC?Couleur.NOIR:Couleur.BLANC;
		for(int i=0; i<grilleToTest.length; i++) {
			for(int j=0; j<grilleToTest[i].length; j++) {
				if (!grilleToTest[i][j].estVide() && grilleToTest[i][j].getPiece().getCouleur()==couleurAdverse
						&& grilleToTest[i][j].getPiece() instanceof Roi) {
					return grilleToTest[i][j];
				}
			}
		}
		return null;
	}

	/**
	 * Cette méthode permet d'obtenir la liste des cases atteignables du joueur adverse
	 * @param grilleToTest : la grille de jeu à tester
	 * @param traitDemande : le trait du joueur
	 * @return la liste des cases atteignables du joueur adverse
	 */
	public HashSet<Case> obtenirListeCaseAtteignableJoueurAdverse(Case[][] grilleToTest, Couleur traitDemande) {

		HashSet<Case> listeCase = null;
		HashSet<Case> listeCaseTmp = null;

		Couleur couleurAdverse = (traitDemande==Couleur.BLANC) ? Couleur.NOIR : Couleur.BLANC;
		//		System.out.println("couleur adverse : "+couleurAdverse);

		for(int i=0; i<grilleToTest.length; i++) {
			for(int j=0; j<grilleToTest[i].length; j++) {
				if (!grilleToTest[i][j].estVide() && grilleToTest[i][j].getPiece().getCouleur() == couleurAdverse) {
					listeCaseTmp = grilleToTest[i][j].getPiece().obtenirCaseAtteignable(grilleToTest, grilleToTest[i][j]);
					listeCase = obtenirListeConcatene(listeCase, listeCaseTmp);
				}
			}
		}

		return listeCase;

	}

	/**
	 * Cette méthode permet d'obtenir la case ou se trouve le roi adverse
	 * @return la case du roi adverse
	 */
	public Case obtenirCaseRoi(Case[][] grilleToTest) {

		for(int i=0; i<grilleToTest.length; i++) {
			for(int j=0; j<grilleToTest[i].length; j++) {
				if (!grilleToTest[i][j].estVide() && grilleToTest[i][j].getPiece().getCouleur()==trait
						&& grilleToTest[i][j].getPiece() instanceof Roi) {
					return grilleToTest[i][j];
				}
			}
		}
		return null;
	}

	/**
	 * Cette méthode est appelée quand un roque est possible et permet d'alléger la méthode unNouveauCoup().
	 * */
	public String effectuerRoque(int xDepart, int yDepart, int xArrive, int yArrive){
		// le roi a été cliqué en premier
		if (grille[xDepart][yDepart].getPiece() instanceof Roi) {
			// petit roque
			if (yDepart < yArrive) {
				grille[xArrive][yArrive-2].setPiece(grille[xArrive][yArrive].getPiece());
				grille[xDepart][yDepart+2].setPiece(grille[xDepart][yDepart].getPiece());
				grille[xDepart][yDepart].setPiece(null);
				grille[xArrive][yArrive].setPiece(null);
				return "O-O";
			}
			// grand roque
			else {
				grille[xArrive][yArrive+3].setPiece(grille[xArrive][yArrive].getPiece());
				grille[xDepart][yDepart-2].setPiece(grille[xDepart][yDepart].getPiece());
				grille[xDepart][yDepart].setPiece(null);
				grille[xArrive][yArrive].setPiece(null);
				return "O-O-O";
			}
		}
		// la tour a été cliquée en premier
		else {
			// petit roque
			if (yArrive < yDepart) {
				grille[xArrive][yArrive+2].setPiece(grille[xArrive][yArrive].getPiece());
				grille[xDepart][yDepart-2].setPiece(grille[xDepart][yDepart].getPiece());
				grille[xDepart][yDepart].setPiece(null);
				grille[xArrive][yArrive].setPiece(null);
				return "O-O";
			}
			// grand roque
			else {
				grille[xArrive][yArrive-2].setPiece(grille[xArrive][yArrive].getPiece());
				grille[xDepart][yDepart+3].setPiece(grille[xDepart][yDepart].getPiece());
				grille[xDepart][yDepart].setPiece(null);
				grille[xArrive][yArrive].setPiece(null);
				return "O-O";
			}
		}
	}



	/**
	 * Cette méthode permet d'obtenir la liste des cases atteignables du joueur en cours
	 * @return la liste des cases atteignables du joueur en cours
	 */
	public HashSet<Case> obtenirListeCaseAtteignableJoueurEnCours() {

		HashSet<Case> listeCase = null;
		HashSet<Case> listeCaseTmp = null;

		for(int i=0; i<grille.length; i++) {
			for(int j=0; j<grille[i].length; j++) {
				if (!grille[i][j].estVide() && grille[i][j].getPiece().getCouleur() == trait) {
					listeCaseTmp = grille[i][j].getPiece().obtenirCaseAtteignable(grille, grille[i][j]);
					listeCase = obtenirListeConcatene(listeCase, listeCaseTmp);
				}
			}
		}

		return listeCase;

	}


	/**
	 * Cette méthode permet de vérifier s'il y a un roque
	 * @return vrai si le coup est un roque, faux sinon
	 */
	public boolean checkRoque(int xDepart, int yDepart, int xArrive, int yArrive) {

		boolean roque = false;

		Case caseTraverseRoi;
		Case caseArriveRoi;

		// si la case de depart est un roi du joueur en cours et la case d'arrivee est une tour du joueur en cours
		if ( (grille[xDepart][yDepart].getPiece() instanceof Roi && grille[xArrive][yArrive].getPiece() instanceof Tour
				&& grille[xDepart][yDepart].getPiece().getCouleur() == trait && grille[xArrive][yArrive].getPiece().getCouleur() == trait))
		{
			// petit roque
			if (yDepart < yArrive) {
				caseTraverseRoi = new Case(null, xDepart, yDepart+1);
				caseArriveRoi = new Case(null, xDepart, yDepart+2);
			}
			// grand roque
			else {
				caseTraverseRoi = new Case(null, xDepart, yDepart-1);
				caseArriveRoi = new Case(null, xDepart, yDepart-2);
			}

			//System.out.println("Case arrive roi : "+caseTraverseRoi.getRangee()+","+caseTraverseRoi.getColonne());
			Case.afficherCase(obtenirListeCaseAtteignableJoueurAdverse(grille, trait));
			//System.out.println(casePresenteDansListe(obtenirListeCaseAtteignableJoueurAdverse(), caseTraverseRoi));

			Roi roi = (Roi) grille[xDepart][yDepart].getPiece();
			Tour tour = (Tour) grille[xArrive][yArrive].getPiece();

			// si le roi et la tour n'ont pas deja joués et qu'il y a des cases vides entre le roi et la tour et que le roi n'est pas en échec
			if (!roi.isDejaJoue() && !tour.isDejaJoue() && !checkEchec(new Case(null, xDepart, yDepart), caseArriveRoi, grille, trait) && tour.caseVide(grille, grille[xDepart][yDepart], grille[xArrive][yArrive])
					&& !casePresenteDansListe(obtenirListeCaseAtteignableJoueurAdverse(grille, trait), caseTraverseRoi)) {
				//System.out.println("ROQUE");

				roque = true;
			}
			else {
				//System.out.println("PAS ROQUE");
			}

		}

		else if ( (grille[xDepart][yDepart].getPiece() instanceof Tour && grille[xArrive][yArrive].getPiece() instanceof Roi
				&& grille[xDepart][yDepart].getPiece().getCouleur() == trait && grille[xArrive][yArrive].getPiece().getCouleur() == trait))
		{

			// petit roque
			if (yArrive < yDepart) {
				caseTraverseRoi = new Case(null, xArrive, yArrive+1);
				caseArriveRoi = new Case(null, xArrive, yArrive+2);
			}
			// grand roque
			else {
				caseTraverseRoi = new Case(null, xArrive, yArrive-1);
				caseArriveRoi = new Case(null, xArrive, yArrive-2);
			}

			//System.out.println("Case arrive roi : "+caseTraverseRoi.getRangee()+","+caseTraverseRoi.getColonne());
			Case.afficherCase(obtenirListeCaseAtteignableJoueurAdverse(grille, trait));
			//System.out.println(casePresenteDansListe(obtenirListeCaseAtteignableJoueurAdverse(), caseTraverseRoi));

			Roi roi = (Roi) grille[xArrive][yArrive].getPiece();
			Tour tour = (Tour) grille[xDepart][yDepart].getPiece();

			// si le roi et la tour n'ont pas deja joués et qu'il y a des cases vides entre le roi et la tour et que le roi n'est pas en échec
			if (!roi.isDejaJoue() && !tour.isDejaJoue() && !checkEchec(new Case(null, xDepart, yDepart), caseArriveRoi, grille, trait) && tour.caseVide(grille, grille[xArrive][yArrive], grille[xDepart][yDepart])
					&& !casePresenteDansListe(obtenirListeCaseAtteignableJoueurAdverse(grille, trait), caseTraverseRoi)) {
				roque = true;
			}
			else {
				//System.out.println("PAS ROQUE");
			}

		}

		return roque;
	}




	/**
	 * Cette méthode permet d'obtenir la concaténation des deux liste données en paramètre
	 * @param listeCase1 : liste de case
	 * @param listeCase2 : liste de case
	 * @return la liste de cases concaténées
	 */
	public HashSet<Case> obtenirListeConcatene(HashSet<Case> listeCase1, HashSet<Case> listeCase2) {

		if (listeCase1 == null) {
			return listeCase2;
		}
		else if (listeCase2 == null) {
			return listeCase1;
		}
		else {
			for(Case c : listeCase2) {
				if (!casePresenteDansListe(listeCase1, c)) {
					listeCase1.add(c);
				}
			}
			return listeCase1;
		}
	}

	/**
	 * Cette méthode permet de savoir si une case donnée en paramètre est présente dans la liste
	 * @param listeCase : liste de cases atteignables
	 * @param caseDepart : case donnée en paramètre
	 * @return vrai si la caseDépart est présente dans la liste, faux sinon
	 */
	public boolean casePresenteDansListe(HashSet<Case> listeCase, Case caseDepart) {

		for(Case c : listeCase) {
			if (c.equals(caseDepart)) {
				return true;
			}
		}
		return false;
	}





	/**
	 * Cette méthode permet d'effectuer une promotion.
	 * Elle est appelé à partir de la fenetre de promotion (FenetrePromotion).
	 * */
	public void unePromotion(int i) {
		int rangeeTest=7;
		if (trait==Couleur.NOIR) rangeeTest=0;

		for (int j=0;j<8;j++){
			if (grille[rangeeTest][j].getPiece() instanceof Pion){
				Couleur couleur = grille[rangeeTest][j].getPiece().getCouleur();

				//La valeur de i correspond à la pièce qui a été seléctionné.
				switch (i){
				case 0:
					grille[rangeeTest][j].setPiece(new Tour(couleur));
					break;
				case 1:
					grille[rangeeTest][j].setPiece(new Cavalier(couleur));
					break;
				case 2:
					grille[rangeeTest][j].setPiece(new Fou(couleur));
					break;
				case 3: 
					grille[rangeeTest][j].setPiece(new Dame(couleur));
					break;
				}
			}
		}
		notifyObserver(coups, false, false, false);
	}


	public void switchTrait(){

		if (trait==Couleur.BLANC) 
			trait=Couleur.NOIR; 
		else trait=Couleur.BLANC;
	}

	public Couleur getTrait() {
		return trait;
	}

	/**
	 * Cette méthode permet de vérifier si une prise en passant à été effectuée.
	 * */
	public boolean checkPriseEnPassant(int xDepart, int yDepart, int xArrive, int yArrive){

		if (   grille[xDepart][yDepart].getPiece() instanceof Pion
				&& grille[xArrive][yArrive].estVide()
				&& grille[xDepart][yDepart].getColonne() != grille[xArrive][yArrive].getColonne()){
			grille[xDepart][yArrive].setPiece(null);
			return true;
		}
		return false;
	}

	/**
	 * Cette méthode permet de vérifier qu'il n'y a pas de promotion.
	 * @return boolean représentant le résultat de l'opération.
	 * */
	public boolean checkPromotion(){
		boolean promotion=false;
		for (int i=0;i<8;i++){
			if (grille[0][i].getPiece() instanceof Pion || grille[7][i].getPiece() instanceof Pion)
				promotion=true;
		}

		return promotion;
	}

	/**
	 * Cette méthode permet de définir si les pièces (Roi, Tour, Pion) ont déjà bougées.
	 * L'objectif est de réduire le code de unNouveauCoup.
	 * */
	public void setDeplace(int xDepart, int yDepart){
		//si la piece jouée est le roi
		if (grille[xDepart][yDepart].getPiece() instanceof Roi) {
			Roi roi = (Roi) grille[xDepart][yDepart].getPiece();
			roi.setDejaJoue(true);
		}

		//si la piece jouée est le tour
		if (grille[xDepart][yDepart].getPiece() instanceof Tour) {
			Tour tour = (Tour) grille[xDepart][yDepart].getPiece();
			tour.setDejaJoue(true);	
		}

		//si la piece jouée est le pion
		if (grille[xDepart][yDepart].getPiece() instanceof Pion) {
			Pion pion = (Pion) grille[xDepart][yDepart].getPiece();
			pion.setDejaDeplace(true);	
		}
	}



}
