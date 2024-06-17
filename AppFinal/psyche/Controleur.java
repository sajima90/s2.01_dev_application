package psyche;

/**
 * @author Groupe 4 SAE2.01 : Guelle Clément, Cauvin Pierre, Montagne Aubin, Delpech Nicolas
 * 							  Bouquet Jules, Rougeolle Henri, Yachir Yanis
 *
 */

import psyche.vue.FrameMenu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
		File file = new File("psyche/theme/Map.txt");

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}