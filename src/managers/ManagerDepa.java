package managers;

import OSPABA.*;
import simulation.*;
import agents.*;
import continualAssistants.*;
import entity.Vozidlo;
import instantAssistants.*;
import java.util.List;

//meta! id="20"
public class ManagerDepa extends Manager {

	public ManagerDepa(int id, Simulation mySim, Agent myAgent) {
		super(id, mySim, myAgent);
	}

	//meta! sender="SchedulerVozidiel", id="55"
	public void processFinish(MessageForm message) {
		message.setAddressee(myAgent().parent());
		message.setCode(Mc.noveVozidlo);
		notice(message);
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processOther(MessageForm message) {
		switch (message.code()) {
		}
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	@Override
	public void processMessage(MessageForm message) {
		switch (message.code()) {
			case Mc.finish:
				processFinish(message);
				break;

			case Mc.init:
				processInit(message);
				break;

			default:
				processOther(message);
				break;
		}
	}

	//meta! tag="end"
	//meta! sender="AgentPrepravy", id="73", type="notice"

	public void processInit(MessageForm message) {
		message.setAddressee(myAgent().findAssistant(Id.schedulerVozidiel));
		startContinualAssistant(message);
	}

}
