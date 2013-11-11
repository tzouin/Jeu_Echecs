package echecs;

import java.awt.Point;

import echecsController.EchiquierController;


public class MainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Plateau plateau = new Plateau();
		EchiquierController controler=new EchiquierController(plateau);
	}
}
