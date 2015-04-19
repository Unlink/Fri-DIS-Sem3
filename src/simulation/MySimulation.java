package simulation;

import OSPABA.*;
import agents.*;
import entity.Linka;
import entity.TypVozidlo;
import entity.Vozidlo;
import entity.Zastavka;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import tools.ImportTools;

public class MySimulation extends Simulation
{
	
	private ImportTools aIt;
	private List<List<Vozidlo>> aVozidla;
	
	public MySimulation()
	{
		init();
		aIt = ImportTools.importData();
		aVozidla = new ArrayList<>(3);
		aVozidla.add(new LinkedList<>());
		aVozidla.add(new LinkedList<>());
		aVozidla.add(new LinkedList<>());
		aVozidla.get(0).add(new Vozidlo(aIt.getVozidla().get(0)));
		aVozidla.get(0).add(new Vozidlo(aIt.getVozidla().get(0)));
		aVozidla.get(0).add(new Vozidlo(aIt.getVozidla().get(2)));
		aVozidla.get(1).add(new Vozidlo(aIt.getVozidla().get(1)));
		aVozidla.get(1).add(new Vozidlo(aIt.getVozidla().get(1)));
		aVozidla.get(1).add(new Vozidlo(aIt.getVozidla().get(2)));
		aVozidla.get(2).add(new Vozidlo(aIt.getVozidla().get(0)));
		aVozidla.get(2).add(new Vozidlo(aIt.getVozidla().get(1)));
		aVozidla.get(2).add(new Vozidlo(aIt.getVozidla().get(2)));
		aVozidla.get(2).add(new Vozidlo(aIt.getVozidla().get(2)));
	}
	
	public List<Zastavka> getZastavky() {
		return aIt.getZastavky();
	}
	
	public List<Linka> getLinky() {
		return aIt.getLinky();
	}
	
	public List<List<Vozidlo>> getVozidla() {
		return aVozidla;
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		setAgentModelu(new AgentModelu(Id.agentModelu, this, null));
		setAgentOkolia(new AgentOkolia(Id.agentOkolia, this, agentModelu()));
		setAgentPrepravy(new AgentPrepravy(Id.agentPrepravy, this, agentModelu()));
		setAgentPresunov(new AgentPresunov(Id.agentPresunov, this, agentPrepravy()));
		setAgentNastupov(new AgentNastupov(Id.agentNastupov, this, agentPrepravy()));
		setAgentVystupov(new AgentVystupov(Id.agentVystupov, this, agentPrepravy()));
		setAgentDepa(new AgentDepa(Id.agentDepa, this, agentPrepravy()));
	}

	private AgentModelu _agentModelu;

	public AgentModelu agentModelu()
	{ return _agentModelu; }

	public void setAgentModelu(AgentModelu agentModelu)
	{ _agentModelu = agentModelu; }

	private AgentOkolia _agentOkolia;

	public AgentOkolia agentOkolia()
	{ return _agentOkolia; }

	public void setAgentOkolia(AgentOkolia agentOkolia)
	{ _agentOkolia = agentOkolia; }

	private AgentPrepravy _agentPrepravy;

	public AgentPrepravy agentPrepravy()
	{ return _agentPrepravy; }

	public void setAgentPrepravy(AgentPrepravy agentPrepravy)
	{ _agentPrepravy = agentPrepravy; }

	private AgentPresunov _agentPresunov;

	public AgentPresunov agentPresunov()
	{ return _agentPresunov; }

	public void setAgentPresunov(AgentPresunov agentPresunov)
	{ _agentPresunov = agentPresunov; }

	private AgentNastupov _agentNastupov;

	public AgentNastupov agentNastupov()
	{ return _agentNastupov; }

	public void setAgentNastupov(AgentNastupov agentNastupov)
	{ _agentNastupov = agentNastupov; }

	private AgentVystupov _agentVystupov;

	public AgentVystupov agentVystupov()
	{ return _agentVystupov; }

	public void setAgentVystupov(AgentVystupov agentVystupov)
	{ _agentVystupov = agentVystupov; }

	private AgentDepa _agentDepa;

	public AgentDepa agentDepa()
	{ return _agentDepa; }

	public void setAgentDepa(AgentDepa agentDepa)
	{ _agentDepa = agentDepa; }
	//meta! tag="end"
}