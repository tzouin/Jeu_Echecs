package echecsView;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import observable.Observer;

import echecs.Couleur;
import echecs.Piece;
import echecs.Plateau;
import echecsController.EchiquierController;

public class FenetreJeu extends JFrame implements Observer{

	int x,y,cote;
	private Echiquier echiquier;
	private EchiquierController controler;

	//Constructeur
	public FenetreJeu(EchiquierController controler){
		x = 20 ; y = 20 ; cote = 64;
		this.controler=controler;
		CreateAndShowGui();		
	}

	public void CreateAndShowGui(){
		this.setTitle("Jeu d'échecs");
		this.setPreferredSize(new Dimension(1000,1000));
		this.setMinimumSize(new Dimension(600,600));

		echiquier = new Echiquier(this);
		EcouteurSouris ecouteurSouris=new EcouteurSouris(controler, x, y ,cote);
		echiquier.addMouseListener(ecouteurSouris) ;

		this.setContentPane(echiquier);
		this.setVisible(true);
	}

	/**
	 * Permet de redessiner le plateau à chaque coup. 
	 * */
	public void redessineLePlateau (Graphics g) {
		int i, j; 		
		for (i = 0; i < 8; i = i + 1) {
			for (j = 0; j < 8; j = j + 1) {
				if ((i+j)%2==1){					
					g.setColor (Color.black) ;
					g.drawRect (x + i*cote, y+j*cote, cote, cote) ;
					g.drawImage(RessourcesPiece.CaseN, x + i*cote, y+j*cote, this);
				}else{
					g.setColor (Color.black) ;
					g.drawRect (x + i*cote, y+j*cote, cote, cote) ;
					g.drawImage(RessourcesPiece.CaseB, x + i*cote, y+j*cote, this);
				}
			}
		}

		/**
		 * Boucle qui parcours l'échiquier et qui dessine les pièces sur les cases. 
		 * Il est possible de changer les images en modifiant la classe : echecsView.RessourcesPiece.java
		 * */
		for (i = 0; i < 8; i++) {
			for (j = 0; j < 8; j++) {
				try{
					if (controler.getModel().grille[i][j] !=null ) {

						Piece pieceCase=controler.getModel().grille[i][j].getPiece();
						String nomPiece=pieceCase.toString();
						switch (nomPiece){

						case "T":
							g.drawImage(((pieceCase.getCouleur()==Couleur.BLANC) ? RessourcesPiece.TB : RessourcesPiece.TN), y+j*cote , x + i * cote , this);
							break;

						case "C":
							g.drawImage(((pieceCase.getCouleur()==Couleur.BLANC) ? RessourcesPiece.CB : RessourcesPiece.CN), y+j*cote, x + i * cote , this);
							break;

						case "F":
							g.drawImage(((pieceCase.getCouleur()==Couleur.BLANC) ? RessourcesPiece.FB : RessourcesPiece.FN), y+j*cote , x + i * cote , this);
							break;

						case "D":
							g.drawImage(((pieceCase.getCouleur()==Couleur.BLANC) ? RessourcesPiece.DB : RessourcesPiece.DN), y+j*cote , x + i * cote , this);
							break;

						case "R":
							g.drawImage(((pieceCase.getCouleur()==Couleur.BLANC) ? RessourcesPiece.RB : RessourcesPiece.RN), y+j*cote , x + i * cote , this);
							break;

						case "":
							g.drawImage(((pieceCase.getCouleur()==Couleur.BLANC) ? RessourcesPiece.PB : RessourcesPiece.PN), y+j*cote , x + i * cote , this);
							break;
						default:

						}
					}
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
			}
		}	    	
	}

	@Override
	public void MiseAJourVue(String str) {
		repaint();
	}
}
