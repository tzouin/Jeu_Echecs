package echecsController;

import echecs.Plateau;

/**
 * <b>AbstractTicTacToeController est la classe abstraite représentant un controleur.</b>
 * <p>
 * Un controleur est caractérisé par les informations suivantes :
 * <ul>
 * <li>Un modèle</li>
 * </ul>
 * </p>
 * <p>
 * Lorsqu'une modification sera effectuée sur la vue, c'est le controleur qui sera averti. 
 * Puis le controleur informera le modèle des demandes de changements.
 * </p>
 */
public class EchiquierController {
	/**Le modèle*/
	protected Plateau Model;
	
	/**
	 * (Implicitement appelé par les sous classes)
	 * Construit un nouveau controleur.
	 *
	 *@param Model : le modèle 
	 * @see       #AbstractTicTacToeController(AbstractTicTacToeModel Model) 
	 * @since     1.0
	 */
	public EchiquierController(Plateau Model){
		this.Model=Model;
	}
		
    /** 
     * Effectue, si possible, un nouveau coup à la position (i,j)
     * Cette méthode appelle Model.unNouveauCoup(int i, int j).
     *
     * 
     * @see #unNouveauCoup(int, int)
	 * @since     1.0                 
	 */
	public void unNouveauCoup(int xDepart, int yDepart, int xArrive, int yArrive){
		Model.unNouveauCoup(xDepart, yDepart, xArrive, yArrive);
	}
	/** 
     * Cette méthode permet de vider toutes les cases. 
     * Cette méthode appelle Model.unNouveauCoup(int i, int j).
     *
     * @see #recommence()
	 * @since     1.0                 
	 */
	public void recommence(){
		
	}
    /** 
     * Effectue, si possible, la suppression du dernier coup.
     * Cette méthode appelle Model.annuleLeDernierCoup().
     * 
     * @see #annuleLeDernierCoup()
	 * @since     1.0                 
	 */
	public void annuleLeDernierCoup(){
		
	}
			
	
	/** 
     * Cette méthode permet de quitter le jeu.
     * Tout les observateurs sont supprimés.
     * Cette méthode appelle Model.fin().
     *
     * @see #fin()
	 * @since     1.0                 
	 */
	public void fin(){
		
	}
	
	/** 
     * Getter du modèle
     *
     * @return Retourne le modèle.
     * @see #getModel()
	 * @since     1.0                 
	 */
	public Plateau getModel(){
		return Model;
	}
}