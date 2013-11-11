package echecsController;

import echecs.Plateau;

/**
 * <b>AbstractTicTacToeController est la classe abstraite repr�sentant un controleur.</b>
 * <p>
 * Un controleur est caract�ris� par les informations suivantes :
 * <ul>
 * <li>Un mod�le</li>
 * </ul>
 * </p>
 * <p>
 * Lorsqu'une modification sera effectu�e sur la vue, c'est le controleur qui sera averti. 
 * Puis le controleur informera le mod�le des demandes de changements.
 * </p>
 */
public class EchiquierController {
	/**Le mod�le*/
	protected Plateau Model;
	
	/**
	 * (Implicitement appel� par les sous classes)
	 * Construit un nouveau controleur.
	 *
	 *@param Model : le mod�le 
	 * @see       #AbstractTicTacToeController(AbstractTicTacToeModel Model) 
	 * @since     1.0
	 */
	public EchiquierController(Plateau Model){
		this.Model=Model;
	}
		
    /** 
     * Effectue, si possible, un nouveau coup � la position (i,j)
     * Cette m�thode appelle Model.unNouveauCoup(int i, int j).
     *
     * 
     * @see #unNouveauCoup(int, int)
	 * @since     1.0                 
	 */
	public void unNouveauCoup(int xDepart, int yDepart, int xArrive, int yArrive){
		Model.unNouveauCoup(xDepart, yDepart, xArrive, yArrive);
	}
	/** 
     * Cette m�thode permet de vider toutes les cases. 
     * Cette m�thode appelle Model.unNouveauCoup(int i, int j).
     *
     * @see #recommence()
	 * @since     1.0                 
	 */
	public void recommence(){
		
	}
    /** 
     * Effectue, si possible, la suppression du dernier coup.
     * Cette m�thode appelle Model.annuleLeDernierCoup().
     * 
     * @see #annuleLeDernierCoup()
	 * @since     1.0                 
	 */
	public void annuleLeDernierCoup(){
		
	}
			
	
	/** 
     * Cette m�thode permet de quitter le jeu.
     * Tout les observateurs sont supprim�s.
     * Cette m�thode appelle Model.fin().
     *
     * @see #fin()
	 * @since     1.0                 
	 */
	public void fin(){
		
	}
	
	/** 
     * Getter du mod�le
     *
     * @return Retourne le mod�le.
     * @see #getModel()
	 * @since     1.0                 
	 */
	public Plateau getModel(){
		return Model;
	}
}