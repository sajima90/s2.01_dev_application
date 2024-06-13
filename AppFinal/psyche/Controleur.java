package psyche;

import psyche.metier.map.Metier;
import psyche.metier.map.Mine;
import psyche.metier.map.Route;
import psyche.metier.minerai.Couleur;
import psyche.vue.map.*;

import java.util.List;

/**
 * @author Groupe 4 SAE2.01 : Guelle Clément, Cauvin Pierre, Montagne Aubin, Delpech Nicolas
 * 							  Bouquet Jules, Rougeolle Henri, Yachir Yanis
 *
 */

public class Controleur
{

	/*--------------*/
	/*  Données     */
	/*--------------*/

	private final Menu menu;


	/*--------------*/
	/*  Méthodes    */
	/*--------------*/

	/**
	 * Constructeur de Controleur
	 */

	public Controleur()
	{
		this.menu = new Menu(this);
	}

	public Menu getMenu(){return this.menu;}


	public static void main(String[] args)
	{
		new psyche.Controleur();
	}

}