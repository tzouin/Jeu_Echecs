package echecs;

/**
 * Cette classe représente un coup.
 * Elle permet de normaliser la partie, et de laisser la possibilité de : 
 * - stocker l'historique d'une partie
 * - normaliser au format PGN une partie.
 * */
public class Coup {
	private Case caseDepart;
	private Case caseArrivee;
	private Piece pieceDeplace; 
	private boolean promotion, echec, echecEtMat, Pat, priseEnPassant, petitRoque, grandRoque;

	public Coup(Case caseDepart, Case caseArrivee){
		this.caseDepart=caseDepart;
		this.caseArrivee=caseArrivee;

		pieceDeplace=caseDepart.getPiece();
		setPromotion(false);
		setEchec(false);
		setEchecEtMat(false);
		setPat(false);
		setPriseEnPassant(false);

	}
	
	public String decrisToi() {
		return caseDepart.getRangee()+";"+caseDepart.getColonne()+";"+caseArrivee.getRangee()+";"+caseArrivee.getColonne()+";";
	}
	
	public void setRoque(String oo){
		if (oo=="O-O")
			petitRoque=true;
		else
			grandRoque=true;
	}

	/**
	 * Formalisation au format PGN.<br/>
	 * <u>ex</u> :  e4  - e5
	 *      		Cf3 - Cc6
	 *      		Fb5 - a6
	 *      		...
	 **/
	public String toString(){
		String[] caseDepartPGN = caseDepart.toStringPGN();
		String[] caseArriveePGN= caseArrivee.toStringPGN();
		if (petitRoque) return "O-O"+ ((isEchec()) ? "+" : (isEchecEtMat()) ? "#" : (isPat()) ? "Pat" : "");
		if (grandRoque) return "O-O-O";
		return pieceDeplace.toString()+caseArriveePGN[0]+caseArriveePGN[1]+((isEchec()) ? "+" : (isEchecEtMat()) ? "#" : (isPat()) ? " Pat" : "");
	}


	public boolean isPromotion() {
		return promotion;
	}


	public void setPromotion(boolean promotion) {
		this.promotion = promotion;
	}


	public boolean isEchec() {
		return echec;
	}


	public void setEchec(boolean echec) {
		this.echec = echec;
	}


	public boolean isEchecEtMat() {
		return echecEtMat;
	}


	public void setEchecEtMat(boolean echecEtMat) {
		this.echecEtMat = echecEtMat;
	}


	public boolean isPat() {
		return Pat;
	}


	public void setPat(boolean pat) {
		Pat = pat;
	}


	public boolean isPriseEnPassant() {
		return priseEnPassant;
	}


	public void setPriseEnPassant(boolean priseEnPassant) {
		this.priseEnPassant = priseEnPassant;
	}

	public Case getCaseDepart() {
		return caseDepart;
	}

	public Case getCaseArrivee() {
		return caseArrivee;
	}

}