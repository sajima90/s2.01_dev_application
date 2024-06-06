package dessin.metier;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Groupe 4 SAE2.01 : Guelle Clément , Cauvin Pierre , Montagne Aubin , Delpech Nicolas
 * @version 1.0
 */


public class Pioche
{
	private List<Jeton> lstJetons;


	public Pioche()
	{
		this.lstJetons = new ArrayList<Jeton>();
		this.initPioche();
	}

	public Jeton tirerJeton()
	{
		if(!this.lstJetons.isEmpty())
		{
			return lstJetons.remove(lstJetons.size() - 1);
		}
		return null;
	}

	private void initPioche()
	{
		this.lstJetons.add(new Jeton(Epice.CANNELLE ));
		this.lstJetons.add(new Jeton(Epice.SAFRAN   ));
		this.lstJetons.add(new Jeton(Epice.SAFRAN   ));
		this.lstJetons.add(new Jeton(Epice.SAFRAN   ));
		this.lstJetons.add(new Jeton(Epice.SESAME   ));
		this.lstJetons.add(new Jeton(Epice.PAPRIKA  ));
		this.lstJetons.add(new Jeton(Piece.ARGENT   ));
		this.lstJetons.add(new Jeton(Piece.ARGENT   ));
		this.lstJetons.add(new Jeton(Epice.POIVRE   ));
		this.lstJetons.add(new Jeton(Epice.SESAME   ));
		this.lstJetons.add(new Jeton(Epice.SAFRAN   ));
		this.lstJetons.add(new Jeton(Piece.OR       ));
		this.lstJetons.add(new Jeton(Epice.CARDAMONE));
		this.lstJetons.add(new Jeton(Epice.CURCUMA  ));
		this.lstJetons.add(new Jeton(Epice.SUMAC    ));
		this.lstJetons.add(new Jeton(Epice.POIVRE  ));

	}
}
