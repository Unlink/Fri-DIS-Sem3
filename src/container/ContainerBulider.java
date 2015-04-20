/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package container;

import OSPRNG.ExponentialRNG;
import OSPRNG.RNG;
import entity.Linka;
import entity.TypVozidlo;
import entity.Vozidlo;
import entity.Zastavka;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;
import tools.ImportTools;

/**
 *
 * @author Unlink
 */
public class ContainerBulider {
	
	private ImportTools aIt;
	
	private int[][] aPoctyVozidiel;
	
	private SimVariants aVarinata;

	public ContainerBulider(ImportTools paIt) {
		this.aIt = paIt;
		aPoctyVozidiel = new int[aIt.getLinky().size()][];
		for (int i = 0; i < aIt.getLinky().size(); i++) {
			aPoctyVozidiel[i] = new int[aIt.getVozidla().size()];
		}
		aVarinata = SimVariants.A;
	}
	
	public void setCount(int paLinka, int paTypVozidla, int paCount) {
		aPoctyVozidiel[paLinka][paTypVozidla] = paCount;
	}
	
	public int getCountAt(int paLinka, int paTypVozidla) {
		return aPoctyVozidiel[paLinka][paTypVozidla];
	}
	
	public String[] getZoznamLiniek() {
		return (String[]) aIt.getLinky().stream().map(Linka::getId).toArray();
	}
	
	public String[] getZoznamVozidiel() {
		return (String[]) aIt.getVozidla().stream().map(TypVozidlo::getMeno).toArray();
	}
	
	public SimContainer build() {
		List<List<Vozidlo>> vozidla = new ArrayList<>();
		int counter = 0;
		for (int i = 0; i < aPoctyVozidiel.length; i++) {
			ArrayList<Vozidlo> v = new ArrayList<>();
			for (int j = 0; j < aPoctyVozidiel[i].length; j++) {
				for (int k = 0; k < aPoctyVozidiel[i][j]; k++) {
					v.add(new Vozidlo(aIt.getVozidla().get(j), counter++));
				}
			}
			vozidla.add(v);
		}
		
		SimContainer simContainer = new SimContainer(aIt.getZastavky(), aIt.getLinky(), vozidla, aVarinata);
		simContainer.injectGeneratoryPrichodov(createGeneratoryPrichodov());
		simContainer.injectGeneratoryNastupov(createGeneratoryNastupov(vozidla));
		simContainer.injectGeneratoryVystupov(createGeneratoryVystupov(vozidla));
		
		return simContainer;
	}

	public void setVarinata(SimVariants paVarinata) {
		this.aVarinata = paVarinata;
	}

	private List<RNG<Double>> createGeneratoryPrichodov() {
		List<RNG<Double>> g = new ArrayList<>(aIt.getZastavky().size());
		for (Zastavka z : aIt.getZastavky()) {
			g.add(z.getId(), new ExponentialRNG(65/(double)z.getPocLudi()));
		}
		return g;
	}

	private List<RNG<Double>> createGeneratoryNastupov(List<List<Vozidlo>> paVozidla) {
		List<RNG<Double>> g = new ArrayList<>();
		paVozidla.stream().forEach((l) -> l.stream().forEach((v) -> {
			g.add(v.getId(), v.getTypVozidlo().createGeneratorNastupu());
		}));
		return g;
	}

	private List<RNG<Double>> createGeneratoryVystupov(List<List<Vozidlo>> paVozidla) {
		List<RNG<Double>> g = new ArrayList<>();
		paVozidla.stream().forEach((l) -> l.stream().forEach((v) -> {
			g.add(v.getId(), v.getTypVozidlo().createGeneratorVystupu());
		}));
		return g;
	}
	
}
