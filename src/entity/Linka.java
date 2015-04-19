/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package entity;

/**
 *
 * @author Unlink
 */
public class Linka {

	private final String aId;
	private final int[] aZastavky;
	private final double[] aPresuny;

	public Linka(String paId, int[] paZastavky, double[] paPresuny) {
		this.aId = paId;
		this.aZastavky = paZastavky;
		this.aPresuny = paPresuny;
	}
	
	public int dajDalsiuZastavku(int paAkt) {
		return (paAkt+1) % aZastavky.length;
	}
	
	public int getZastavkaId(int paAkt) {
		return aZastavky[paAkt];
	}
	
	public double dajCasKDalsej(int paAkt) {
		return aPresuny[paAkt];
	}

}
