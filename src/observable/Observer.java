package observable;
/**
 * <b>Observer est l'interface qui devra être implémenté chez les observateurs.
 * Les objets qui sont ajoutés comme observateur devront implémenter cette interface.</b>
 * <p>
 * Cette interface permet :
 * <ul>
 * 		<li>de mettre à jour la vue</li>
 * </ul>
 * </p>
 * 
 */
public interface Observer {
	
	/** 
     * Cette méthode permet de mettre à jour les vues.
     *
     *@param str : le status de la partie ("Victoire joueur 1", "Match nul !", "Trait au joueur 1", ...)
     * @see #MiseAJourVue(String str) 
	 * @since     1.0                 
	 */
	public void MiseAJourVue(String str);
}