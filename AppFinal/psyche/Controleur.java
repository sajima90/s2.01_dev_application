package psyche;

/**
 * @author Groupe 4 SAE2.01 : Guelle Clément, Cauvin Pierre, Montagne Aubin, Delpech Nicolas
 * 							  Bouquet Jules, Rougeolle Henri, Yachir Yanis
 *
 */

import psyche.jeu.metier.Mine;
import psyche.vue.FrameMenu;
import psyche.scenario.ControleurScenario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Controleur
{

	/*--------------*/
	/*  Données     */
	/*--------------*/

	private final FrameMenu FrameMenu;
	private ControleurScenario ctrlScen;


	/*--------------*/
	/*  Méthodes    */
	/*--------------*/

	/**
	 * Constructeur de Controleur sans scénario
	 */
	public Controleur()
	{
		this.FrameMenu = new FrameMenu(this);
	}

	/**
	 * Constructeur de Controleur avec scénario
	 */
	public Controleur(String fichierScenario)
	{
		this.ctrlScen = new ControleurScenario(this, fichierScenario);
		this.FrameMenu = new FrameMenu(this);
	}


	public static void main(String[] args)
	{
		if (args.length > 0)
		{
			new Controleur(args[0]);
		}
		else
		{
			new Controleur();
		}

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

	/*--------------*/
	/*  Scénarios   */
	/*--------------*/

	public List<Mine> getMines()
	{
		return this.FrameMenu.getMines();
	}

	public void ouvrirFenetreModifier()
	{
		this.FrameMenu.ouvrirFenetreModifier();
	}

	public void ouvrirFenetreJouer()
	{
		this.FrameMenu.ouvrirFenetreJouer();
	}

	public void fermerFenetreJouer()
	{
		this.FrameMenu.fermerFenetreJouer();
	}
	public void fermerFenetreModifier()
	{
		this.FrameMenu.fermerFenetreModifier();
	}
}