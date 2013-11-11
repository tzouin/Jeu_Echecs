package observable;

import java.util.ArrayList;

/**
 * <b>Observable est l'interface qui devra être implémenté sur le modèle.
 * Le pattern Observer permet de faire communiquer le modèle avec les autres objets.</b>
 * <p>
 * Cette interface permet :
 * <ul>
 * 		<li>d'ajouter un observateur</li>
 * 		<li>de supprimer un observateur</li>
 * 		<li>de notifier les observateurs</li>
 * </ul>
 * </p>
 * 
 */
public interface Observable {
	
	/** 
     * Cette méthode permet d'ajouter un observateur.
     *
     * @see #addObserver() Observer
	 * @since     1.0                 
	 */
	public void addObserver();
	
	/** 
     * Cette méthode permet de supprimer un observateur.
     * Si il n'y a plus d'observateur, l'application se ferme.
     * 
     * @see #removeObserver(Observer obs) Observer
	 * @since     1.0                 
	 */
	public void removeObserver(Observer obs);
	
	/** 
     * Cette méthode permet de notifier tout les observateurs.
     * Le status de la partie (Victoire joueur 1, Victoire joueur2, ...) sont transmises aux observateurs.
     * 
     * @see #notifyObserver() Observer
	 * @since     1.0                 
	 */
	public void notifyObserver(ArrayList<String> coups, boolean promotion, boolean echecEtMat, boolean pat);
}