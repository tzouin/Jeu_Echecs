package echecs;

public class Joueur {
	
	private String nom;
	private Couleur couleurJoueur;

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}
	
	public void setCouleurJoueur(Couleur couleurJoueur) {
		this.couleurJoueur = couleurJoueur;
	}

	public Couleur getCouleurJoueur() {
		return couleurJoueur;
	}
	
	public Joueur(String nom, Couleur couleurJoueur) {
		this.nom = nom;
		this.couleurJoueur = couleurJoueur;
	}
	
	public String toString() {
		return nom;
	}

	
}
