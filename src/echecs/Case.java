package echecs;
import java.util.HashSet;

public class Case implements Comparable<Case>, Cloneable {

	private Piece piece;
	private int rangee,colonne;

	public Case(Piece piece, int rangee, int colonne) {

		this.piece = piece;
		this.rangee=rangee;
		this.colonne=colonne;
	}

	public String toString(){
		return ""+rangee+colonne;
	}
	/**
	 * Si la piece est à null une exception est levé.
	 * Cette méthode permet de limiter l'utilisation des try-catch dans le code.
	 */
	public boolean estVide(){

		try{
			if(piece==null)
				return true;
			else
				return false;
		}catch (Exception e){
			return true;
		}
	}

	public boolean estDeMemeCouleur(Couleur couleur){

		try{
			if(piece.couleurPiece == couleur)
				return true;
			else
				return false;
		}catch (Exception e){
			return true;
		}
	}


	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public Piece getPiece() {
		return piece;
	}

	public int getRangee(){
		return rangee;
	}

	public int getColonne(){
		return colonne;
	}

	/**
	 * Cette méthode permet de retourner la colonne ainsi que la rangée au format PGN.
	 * String[0]=piecePGN
	 * String[1]=colonnePGN
	 * String[2]=rangeePGN
	 * @see cf : http://www.iechecs.com/notation.htm
	 */
	public String[] toStringPGN() {
		String retour[]=new String[2];

		switch(rangee){
		case 0:
			retour[1]="8";
			break;
		case 1: 
			retour[1]="7";
			break;
		case 2: 
			retour[1]="6";
			break;
		case 3: 
			retour[1]="5";
			break;
		case 4: 
			retour[1]="4";
			break;
		case 5: 
			retour[1]="3";
			break;
		case 6: 
			retour[1]="2";
			break;
		case 7: 
			retour[1]="1";
			break;
		default:
			retour[1]="";
			break;
		}

		switch (colonne){
		case 7:
			retour[0]="h";
			break;
		case 6:
			retour[0]="g";
			break;
		case 5:
			retour[0]="f";
			break;
		case 4:
			retour[0]="e";
			break;
		case 3:
			retour[0]="d";
			break;
		case 2:
			retour[0]="c";
			break;
		case 1:
			retour[0]="b";
			break;
		case 0:
			retour[0]="a";
			break;

		default:
			retour[0]="";
			break;
		}

		return retour;
	}

	/**
	 * Cette méthode permet de connaître la couleur d'une case
	 * @return la couleur de la piece se trouvant sur la case
	 */
	public Couleur couleurCase() {
		return piece == null ? null : piece.couleurPiece;
	}


	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof Case){
			Case obj=(Case) arg0;
			if (this.rangee==obj.getRangee() && this.colonne==obj.getColonne() )
				return true;
			else
				return false;
		}
		return false;
	}

	//	public boolean equals(Case c) {
	//
	//		if (this.rangee==c.getRangee() && this.colonne==c.getColonne() ) {
	//			return true;
	//		}
	//		else {
	//			return false;
	//		}
	//	}



	public static void afficherCase(HashSet<Case> listeCase) {
		if (listeCase != null) {
			for (Case c : listeCase) {
				System.out.print("["+c.getRangee()+","+c.getColonne()+"] ");
			}
			System.out.println("");
		}
		else {
			System.out.println("La liste de case est null");
		}
	}


	@Override
	public int compareTo(Case c) {
		if (this.getRangee() < c.getRangee()) {
			return -1;
		}
		else if (this.getRangee() == c.getRangee() && this.getColonne() == c.getColonne()) {
			return 0;
		}
		else {
			return 1;
		}
	}

	public Object clone() throws CloneNotSupportedException {   
		return super.clone();
	}
}
