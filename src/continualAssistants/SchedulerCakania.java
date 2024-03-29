package continualAssistants;

import OSPABA.*;
import simulation.*;
import agents.*;

//meta! id="58"
public class SchedulerCakania extends Scheduler {
	public SchedulerCakania(int id, Simulation mySim, CommonAgent myAgent) {
		super(id, mySim, myAgent);
	}

	//meta! sender="AgentNastupov", id="59"
	public void processStart(MessageForm message) {
		MyMessage mm = (MyMessage) message;
		mm.setCode(Mc.finish);
		hold(mm.getVozidlo().getTypVozidlo().getCaka(), mm);
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processOther(MessageForm message) {
		switch (message.code()) {
			case Mc.finish:
				processFinished(message);
				break;
		}
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	@Override
	public void processMessage(MessageForm message) {
		switch (message.code()) {
			case Mc.start:
				processStart(message);
				break;

			default:
				processOther(message);
				break;
		}
	}
	//meta! tag="end"

	private void processFinished(MessageForm message) {
		assistantFinished(message);
	}
}
