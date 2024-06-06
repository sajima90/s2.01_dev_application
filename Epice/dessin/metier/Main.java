package dessin.metier;

import dessin.metier.Pioche;
import dessin.metier.Plateau;

public class Main
{
	public static void main(String[] args)
	{
		Pioche pioche   = new Pioche();
		Plateau plateau = new Plateau();
		System.out.println("Etat initial du Plateau \n" + plateau);

		System.out.println("Ajout des ressources à partir\n" + "des jetons de la pioche\n");
		Jeton jeton;
		while ((jeton = pioche.tirerJeton()) != null)
		{
			boolean effectue = plateau.ajouterRessource(jeton);
			String type = jeton.getType() instanceof Epice ? "Epice" : "Piece";
			System.out.println(String.format("%-1s %-15s : %s", type, jeton.getType(), effectue));
		}

		// String.format("%3d", blabla);

		plateau.score();
		System.out.println();
		System.out.println("État final du plateau :\n" + plateau);
		System.out.println("Score : " + plateau.getScore() + " points");
		System.out.println(plateau.getDetailScore());

	}
}