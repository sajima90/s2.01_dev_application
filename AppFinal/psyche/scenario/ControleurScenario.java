package psyche.scenario;

import psyche.Controleur;
import psyche.jeu.metier.Mine;
import psyche.jeu.metier.Route;
import psyche.jeu.metier.Couleur;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ControleurScenario
{
	/*
	Idée : avoir la liste des actions ligne par ligne dans scenario_1.run OU scenario_2.run
	Avoir aussi un int index pour savoir où on en est.
	Bouton suivant : Avancer l'index de 1 et effectuer l'action. / Recharger tout en ayant fait l'action suivante de notre index
	Bouton précédent : Reculer l'index de 1, recharger la carte de 0 et effectuer toutes les actions précedentes


	https://diw.iut.univ-lehavre.fr/pedago/info1/SAE_2_01/ressources/exercice_6/utilite_scenario.pdf
	 */

	private Controleur ctrl;
	private int etape;
	private String fichierScenario;
	private FrameScenario frameScenario;

	public ControleurScenario(Controleur ctrl, String fichierScenario)
	{
		this.ctrl = ctrl;
		this.frameScenario = new FrameScenario(this);
		this.fichierScenario = fichierScenario;
	}

	public void effectuerAction (int etape)
	{
		try {
			BufferedReader reader = new BufferedReader(new FileReader("../psyche/scenario/" + fichierScenario));
			String ligne;
			int numLigne = 0;
			while ((ligne = reader.readLine()) != null && numLigne < etape - 1 ) {
				numLigne++;
				traiterLigne(ligne);
			}
			reader.close();
		} catch (IOException e) {
			System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
		}
	}


	private void traiterLigne(String ligne) {
		// Suppression des espaces blancs au début et à la fin de la ligne
		ligne = ligne.trim();

		// Vérification si la ligne n'est pas vide
		if (!ligne.isEmpty()) {
			String[] parts = ligne.split("\\s+"); // Divise la ligne en mots en fonction des espaces
			if (parts.length > 0) {
				String command = parts[0].toLowerCase(); // Convertir en minuscules pour la comparaison
				switch (command) {
				case "ecrire":
					if (parts.length > 1)
					{
						int number = Integer.parseInt(parts[1]);
						System.out.println(number);
					}
					break;
				case "ouvrir":
					if (parts.length > 1)
					{
						String action = parts[1];
						switch (action) {
						case "modifier" :
							this.ctrl.ouvrirFenetreModifier();
							break;
						case "jouer" :
							this.ctrl.ouvrirFenetreJouer();
							break;
						}
					}
					break;
				case "fermer":
					if (parts.length > 1)
					{
						String action = parts[1];
						switch (action) {
						case "modifier" :
							this.ctrl.fermerFenetreModifier();
							break;
						case "jouer" :
							this.ctrl.fermerFenetreJouer();
							break;
						}
					}
					break;
				case "creer":
					if (parts[1].equals("mine"))
					{
						Mine.creerMine(Integer.parseInt(parts[2]), Integer.parseInt(parts[3]),
								Integer.parseInt(parts[4]), Couleur.valueOf(parts[5]));
					}
					else
					{
						Route.creerRoute(this.ctrl.getMines().get(Integer.parseInt(parts[1])),
								         this.ctrl.getMines().get(Integer.parseInt(parts[2])),
										 Integer.parseInt(parts[3]));
					}
					break;

					// Toutes les actions faisables par un joueur doivent pouvoir etre faites avec un scenario.run
				default:
					System.out.println("Commande non reconnue : " + command);
					break;
				}
			}
		}
	}

	public void setFichierScenario (String fichier) { this.fichierScenario = fichier;}

}
