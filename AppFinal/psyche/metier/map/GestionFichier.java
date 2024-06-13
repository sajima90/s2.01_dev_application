package psyche.metier.map;

import psyche.metier.minerai.Couleur;
import psyche.metier.minerai.Minerai;

import javax.swing.*;
import java.io.*;

public class GestionFichier {

	private static final String MINES = "[Mines] :";
	private static final String ROUTES = "[Routes] :";
	private static final String SEPARATOR = "---------------------------------";

	private String fichierCharger = "/home/saji/Cours/IUT/TP/s2/s2.01_dev_application/AppFinal/psyche/theme/Map.txt";
	private final Metier metier;

	public GestionFichier(Metier metier) {
		this.metier = metier;
	}

	public String getFichierCharger() {
		return this.fichierCharger;
	}

	public void setFichierCharger(String path) {
		this.fichierCharger = path;
		this.chargerMinesRoutes(path);
	}

	private void setNouveauFichier(String absolutePath) {
		this.fichierCharger = absolutePath;
		this.sauvegarderMinesRoutes(absolutePath);
	}

	public void enregistrer() {
		this.sauvegarderMinesRoutes(this.getFichierCharger());
	}

	public void enregistrerSous() {
		JFileChooser fileChooser = new JFileChooser();
		int returnValue = fileChooser.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			this.setNouveauFichier(selectedFile.getAbsolutePath());
		}
	}

	private void sauvegarderMinesRoutes(String path) {
		if (path == null || path.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Veuillez enregistrer dans votre fichier", "Erreur", JOptionPane.ERROR_MESSAGE);
			return;
		}

		try (PrintWriter writer = new PrintWriter(new FileOutputStream(path, false))) {
			StringBuilder sb = new StringBuilder();

			sb.append(MINES).append("\n");
			for (Mine mine : this.metier.getMines()) {
				Couleur couleur = mine.getCouleur();
				Minerai minerai = mine.getMinerai() != null ? mine.getMinerai() : null;
				sb.append(String.format("%-5d, %-5d, %-20s, %-20s, %-20S %-20s \n", mine.getX(), mine.getY(), couleur, mine.getPoint(), minerai,  mine.getMinePrise() ? "Prise" : "Libre"));
			}

			sb.append(SEPARATOR).append("\n");
			sb.append(ROUTES).append("\n");

			for (Route route : this.metier.getRoutes()) {
				String symboleDepart = route.getDepart().getMinerai() != null ? route.getDepart().getMinerai().getSymbole() : "null";
				String symboleArrivee = route.getArrivee().getMinerai() != null ? route.getArrivee().getMinerai().getSymbole() : "null";
				sb.append(String.format("%-20s, %-20s, %-20s, %-20s, %-5d\n", symboleDepart, route.getDepart().getPoint(), symboleArrivee, route.getArrivee().getPoint(), route.getTroncons()));
			}

			writer.println(sb);
		} catch (IOException i) {
			i.printStackTrace();
		}
	}



	/**
	 * Charge les données de mines et de routes à partir du fichier spécifié.
	 *
	 * @param path Le chemin du fichier à partir duquel charger les données.
	 */
	private void chargerMinesRoutes(String path) {
		if (path == null || path.isEmpty())
			return;

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
			if (!this.metier.getRoutes().isEmpty() && !this.metier.getMines().isEmpty()) {
				this.metier.getMines().clear();
				this.metier.getRoutes().clear();
				this.metier.resetId();
			}

			String  ligne;
			boolean lireMines = false;
			boolean lireRoutes = false;

			while ((ligne = reader.readLine()) != null) {
				if (ligne.equals(MINES)) {
					lireMines = true;
					lireRoutes = false;
					continue;
				} else if (ligne.equals(ROUTES)) {
					lireRoutes = true;
					lireMines = false;
					continue;
				}

				if (lireMines) {
					if (ligne.equals(SEPARATOR)) continue;
					String[] Mine = ligne.split(",");
					if (Mine.length == 6) {
						int x = Integer.parseInt(Mine[0].trim());
						int y = Integer.parseInt(Mine[1].trim());
						Couleur couleur = Couleur.valueOf(Mine[2].trim());
						int point = Integer.parseInt(Mine[3].trim());
						Minerai minerai = Mine[4].equals("NULL") ? null : Minerai.valueOf(Mine[4].trim());
						boolean minePrise = "Prise".equals(Mine[5].trim());

						this.metier.ajouterMine(x, y, point, couleur);
					}
				}        else if (lireRoutes) {
					if (ligne.equals(SEPARATOR)) continue;

					String[] route = ligne.split(",");

					if (route.length == 5) {
						String symboleDepart = route[0].trim();
						String symboleArrivee = route[2].trim();

						if (!"null".equals(symboleDepart) && !"null".equals(symboleArrivee)) {
							int pointDepart = Integer.parseInt(route[1].trim());
							int pointArrivee = Integer.parseInt(route[3].trim());
							int troncons = Integer.parseInt(route[4].trim());

							Couleur couleurDepart = Couleur.valueOf(symboleDepart);
							Couleur couleurArrivee = Couleur.valueOf(symboleArrivee);

							Mine depart = this.metier.getMineParMineraiPoint(couleurDepart, pointDepart);
							Mine arrivee = this.metier.getMineParMineraiPoint(couleurArrivee, pointArrivee);

							if (depart != null && arrivee != null) {
								this.metier.ajouterRoute(depart, arrivee, troncons);
							}
						}
					}
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
