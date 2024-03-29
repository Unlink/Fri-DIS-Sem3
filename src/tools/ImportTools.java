/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package tools;

import OSPRNG.DeterministicRNG;
import OSPRNG.ExponentialRNG;
import OSPRNG.NormalGNG;
import OSPRNG.TriangularRNG;
import OSPRNG.UniformContinuousRNG;
import entity.IGeneratorFactory;
import entity.Linka;
import entity.TypVozidlo;
import entity.Zastavka;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Unlink
 */
public class ImportTools {

	private ArrayList<TypVozidlo> aVozidla;
	private ArrayList<Zastavka> aZastavky;
	private HashMap<String, Zastavka> aMappingZastavok;
	private ArrayList<Linka> aLinky;
	private ArrayList<IGeneratorFactory> aGeneratory;
	private HashMap<String, List[]> aPom;

	public static ImportTools importData() {
		return new ImportTools();
	}

	private ImportTools() {
		aVozidla = new ArrayList<>();
		aZastavky = new ArrayList<>();
		aMappingZastavok = new HashMap<>();
		aLinky = new ArrayList<>();
		aGeneratory = new ArrayList<>();
		aPom = new HashMap<>();
		String[] subory = new String[]{"zastavky", "linky", "generatory", "dopravneProstriedky"};
		for (String subor : subory) {
			String f = "/data/" + subor + ".csv";
			try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(f)))) {
				String line;
				while ((line = br.readLine()) != null) {
					String split[] = line.split(",");
					for (int i = 0; i < split.length; i++) {
						split[i] = split[i].trim();
					}
					//System.out.println(String.join(",", split));
					switch (subor) {
						case "zastavky":
							insertZastavka(split);
							break;
						case "linky":
							insertLinka(split);
							break;
						case "generatory":
							insertGenerator(split);
							break;
						case "dopravneProstriedky":
							insertVozidlo(split);
							break;
					}
				}
			}
			catch (IOException ex) {
				throw new RuntimeException("Nepodarilo sa načítať konfiguráciu", ex);
			}
		}
		spocitajLinky();
	}

	public ArrayList<TypVozidlo> getVozidla() {
		return aVozidla;
	}

	public ArrayList<Zastavka> getZastavky() {
		return aZastavky;
	}

	public ArrayList<Linka> getLinky() {
		return aLinky;
	}

	private void spocitajLinky() {
		for (Map.Entry<String, List[]> entrySet : aPom.entrySet()) {
			double[] d = new double[entrySet.getValue()[1].size()];
			int[] z = new int[d.length];
			double summator = 0;
			for (int i = d.length - 1; i >= 0; i--) {
				d[i] = (double) entrySet.getValue()[1].get(i);
				Zastavka x = ((Zastavka) entrySet.getValue()[0].get(i));
				z[i] = (x == null) ? -1 : x.getId();
				if (i < d.length - 1) {
					summator += d[i];
					aZastavky.get(z[i]).setVzdialenost(summator);
				}
			}
			aLinky.add(new Linka(entrySet.getKey(), z, d));
		}
	}

	private void insertZastavka(String[] paSplit) {
		Zastavka z = new Zastavka(aZastavky.size(), paSplit[0], parseInt(paSplit[1]));
		aZastavky.add(z);
		aMappingZastavok.put(z.getMeno(), z);
	}

	private void insertLinka(String[] paSplit) {
		Zastavka z = aMappingZastavok.get(paSplit[1]);
		String linkaId = paSplit[0].intern();
		if (!aPom.containsKey(linkaId)) {
			aPom.put(linkaId, new List[]{new LinkedList<>(), new LinkedList<>()});
		}
		List[] l = aPom.get(linkaId);
		l[0].add(z);
		l[1].add(parseDouble(paSplit[2]) * 60);
	}

	private void insertGenerator(String[] paSplit) {
		int id = parseInt(paSplit[0]);
		switch (paSplit[1]) {
			case "tria":
				aGeneratory.add(id, (IGeneratorFactory) (paSeedGenerator) -> new TriangularRNG(parseDouble(paSplit[2]), parseDouble(paSplit[4]), parseDouble(paSplit[3]), paSeedGenerator));
				break;
			case "unif":
				aGeneratory.add(id, (IGeneratorFactory) (paSeedGenerator) -> new UniformContinuousRNG(parseDouble(paSplit[2]), parseDouble(paSplit[3]), paSeedGenerator));
				break;
			case "exp":
				aGeneratory.add(id, (IGeneratorFactory) (paSeedGenerator) -> new ExponentialRNG(parseDouble(paSplit[2]), paSeedGenerator));
				break;
			case "norm":
				aGeneratory.add(id, (IGeneratorFactory) (paSeedGenerator) -> new NormalGNG(parseDouble(paSplit[2]), parseDouble(paSplit[3]), paSeedGenerator));
				break;
			case "det":
				aGeneratory.add(id, (IGeneratorFactory) (paSeedGenerator) -> new DeterministicRNG(parseDouble(paSplit[2])));
				break;
		}
	}

	private void insertVozidlo(String[] paSplit) {
		String name = paSplit[0].intern();
		aVozidla.add(new TypVozidlo(aVozidla.size(), name, parseInt(paSplit[1]), parseInt(paSplit[2]), parseDouble(paSplit[3]), parseDouble(paSplit[4]), aGeneratory.get(parseInt(paSplit[5])), aGeneratory.get(parseInt(paSplit[6]))));
	}

}
