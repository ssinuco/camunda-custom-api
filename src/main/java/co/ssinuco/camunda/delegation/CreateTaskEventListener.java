package co.ssinuco.camunda.delegation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

public class CreateTaskEventListener implements TaskListener {

	private static final Logger logger = LoggerFactory.getLogger(CreateTaskEventListener.class);
	
	public void notify(DelegateTask execution) {
		this.logger.info("CreateTaskEventListener notified");
	}
}