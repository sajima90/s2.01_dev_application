package sae.metier;
import javax.swing.*;
import java.io.*;
public class GestionFichier
{
	private String fichierCharger = "";

	private final Metier metier;

	public GestionFichier(Metier metier)
	{
		this.metier = metier;
	}

	public void enregistrer() {
		this.sauvegarderVillesRoutes(this.getFichierCharger());
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
		this.chargerVillesRoutes(path);
	}

	private void setNouveauFichier(String absolutePath) {
		this.fichierCharger = absolutePath;
		this.sauvegarderVillesRoutes(absolutePath);
	}

	private void sauvegarderVillesRoutes(String path) {
		if (path == null || path.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Veuillez enregistrer dans votre fichier", "Erreur", JOptionPane.ERROR_MESSAGE);
			return;
		}

		try (PrintWriter writer = new PrintWriter(new FileOutputStream(path, false))) {
			StringBuilder sb = new StringBuilder();

			sb.append("[Villes] :\n");
			for (Ville ville : this.metier.getVilles()) {
				String villeLine = String.format("%-20s, %-5d, %-5d\n", ville.getNom(), ville.getX(), ville.getY());
				sb.append(villeLine);
			}

			sb.append("---------------------------------\n");
			sb.append("[Routes] :\n");

			for (Route route : this.metier.getRoutes()) {
				String routeLine = String.format("%-20s, %-20s, %-5d\n", route.getDepart().getNom(), route.getArrivee().getNom(), route.getTroncons());
				sb.append(routeLine);
			}

			writer.println(sb);
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	private void chargerVillesRoutes(String path) {
		if (path == null || path.isEmpty()) {
			return;
		}

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
			if (!this.metier.getRoutes().isEmpty() && !this.metier.getVilles().isEmpty()) {
				this.metier.getVilles().clear();
				this.metier.getRoutes().clear();
				this.metier.resetId();
			}

			String ligne;
			boolean lireVilles = false;
			boolean lireRoutes = false;

			while ((ligne = reader.readLine()) != null) {
				if (ligne.equals("[Villes] :")) {
					lireVilles = true;
					lireRoutes = false;
					continue;
				} else if (ligne.equals("[Routes] :")) {
					lireRoutes = true;
					lireVilles = false;
					continue;
				}

				if (lireVilles) {
					if (ligne.equals("---------------------------------")) continue;
					String[] ville = ligne.split(",");
					if (ville.length == 3) {
						String nom = ville[0].trim();
						try {
							int x = Integer.parseInt(ville[1].trim());
							int y = Integer.parseInt(ville[2].trim());
							this.metier.ajouterVille(nom, x, y);
						} catch (NumberFormatException e) {
							System.err.println("Erreur de format pour les coordonnées : " + ligne);
						}
					}
				} else if (lireRoutes) {
					if (ligne.equals("---------------------------------")) continue;
					String[] route = ligne.split(",");
					if (route.length == 3) {
						String villeDepart = route[0].trim();
						String villeArrivee = route[1].trim();
						try {
							int nombreTroncons = Integer.parseInt(route[2].trim());
							Ville depart = this.metier.getVilleParNom(villeDepart);
							Ville arrivee = this.metier.getVilleParNom(villeArrivee);
							this.metier.ajouterRoute(depart, arrivee, nombreTroncons);
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
