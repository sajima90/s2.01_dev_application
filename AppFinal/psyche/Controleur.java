package psyche;

/**
 * @author Groupe 4 SAE2.01 : Guelle Clément, Cauvin Pierre, Montagne Aubin, Delpech Nicolas
 * 							  Bouquet Jules, Rougeolle Henri, Yachir Yanis
 *
 */

import psyche.vue.FrameMenu;

import java.util.List;

/**
 * @author Groupe 4 SAE2.01 : Guelle Clément, Cauvin Pierre, Montagne Aubin, Delpech Nicolas
 *   Bouquet Jules, Rougeolle Henri, Yachir Yanis
 *
 */

public class Controleur
{

	/*--------------*/
	/*  Données     */
	/*--------------*/

	private final FrameMenu FrameMenu;


	/*--------------*/
	/*  Méthodes    */
	/*--------------*/

	/**
	 * Constructeur de Controleur
	 */

	public Controleur()
	{
		this.FrameMenu = new FrameMenu(this);
	}


	public static void main(String[] args)
	{
		new Controleur();
	}

	public void setVisible()
	{
		this.FrameMenu.setVisible(true);
	}

}