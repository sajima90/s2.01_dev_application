package psyche.map.metier;

import psyche.map.metier.Metier;

import javax.swing.*;
import java.io.*;

public class GestionFichier
{
	private String fichierCharger = "/home/saji/developpement/SAE 2.01/AppFinal/psyche/theme/Map.txt";

	private final Metier metier;

	public GestionFichier(Metier metier)
	{
		this.metier = metier;
	}

	public void enregistrer() {
		this.sauvegarderSommetsArretes(this.getFichierCharger());
	}

	public void enregistrerSous() {
		JFileChooser fileChooser = new JFileChooser();
		int returnValue = fileChooser.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			this.setNouveauFichier(selectedFile.getAbsolutePath());
		}
	}

	public String getFichierCharger() {
		return this.fichierCharger;
	}

	public void setFichierCharger(String path) {
		this.fichierCharger = path;
		this.chargerSommetsArretes(path);
	}

	private void setNouveauFichier(String absolutePath) {
		this.fichierCharger = absolutePath;
		this.sauvegarderSommetsArretes(absolutePath);
	}

	private void sauvegarderSommetsArretes(String path) {
		if (path == null || path.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Veuillez enregistrer dans votre fichier", "Erreur", JOptionPane.ERROR_MESSAGE);
			return;
		}

		try (PrintWriter writer = new PrintWriter(new FileOutputStream(path, false))) {
			StringBuilder sb = new StringBuilder();

			sb.append("[Sommets] :\n");
			for (Sommet sommet : this.metier.getSommets()) {
				String sommetLigne = String.format("%-20s, %-5d, %-5d, %-10s, %-5d\n", sommet.getNom(), sommet.getX(), sommet.getY(), sommet.getCouleur().name(), sommet.getPoint());				sb.append(sommetLigne);
			}

			sb.append("---------------------------------\n");
			sb.append("[Arretes] :\n");

			for (Arrete arrete : this.metier.getArretes()) {
				String arreteLigne = String.format("%-20s, %-20s, %-5d\n", arrete.getDepart().getNom(), arrete.getArrivee().getNom(), arrete.getTroncons());
				sb.append(arreteLigne);
			}

			writer.println(sb);
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	private void chargerSommetsArretes(String path) {
		if (path == null || path.isEmpty()) {
			return;
		}

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
			if (!this.metier.getArretes().isEmpty() && !this.metier.getSommets().isEmpty()) {
				this.metier.getSommets().clear();
				this.metier.getArretes().clear();
				this.metier.resetId();
			}


			String ligne;
			boolean lireSommets = false;
			boolean lireArretes = false;

			while ((ligne = reader.readLine()) != null) {
				if (ligne.equals("[Sommets] :")) {
					lireSommets = true;
					lireArretes = false;
					continue;
				} else if (ligne.equals("[Arretes] :")) {
					lireArretes = true;
					lireSommets = false;
					continue;
				}


				if (lireSommets) {
					if (ligne.equals("---------------------------------")) continue;
					String[] sommet = ligne.split(",");
					if (sommet.length == 5) {
						try {

							int x = Integer.parseInt(sommet[1].trim());
							int y = Integer.parseInt(sommet[2].trim());
							Couleur couleur = Couleur.valueOf(sommet[3].trim());
							int point = Integer.parseInt(sommet[4].trim());
							System.out.println( sommet[0].trim() + " " + x + " " + y + " " + couleur + " " + point);
							this.metier.ajouterSommet( x, y, point, couleur);
						} catch (NumberFormatException e) {
							System.err.println("Erreur de format pour les coordonnées : " + ligne);
						}
					}
				} else if (lireArretes) {
					if (ligne.equals("---------------------------------")) continue;
					String[] arrete = ligne.split(",");
					if (arrete.length == 3) {
						String arreteDepart = arrete[0].trim();
						String arreteArrivee = arrete[1].trim();
						try {
							int nombreTroncons = Integer.parseInt(arrete[2].trim());
							Sommet depart = this.metier.getSommet(arreteDepart);
							Sommet arrivee = this.metier.getSommet(arreteArrivee);
							this.metier.ajouterArrete(depart, arrivee, nombreTroncons);
						} catch (NumberFormatException e) {
							System.err.println("Erreur de format pour le nombre de tronçons : " + ligne);
						}
					}
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
