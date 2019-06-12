package co.ssinuco.camunda;

import java.util.List;

import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.DeploymentQuery;
import org.camunda.bpm.engine.rest.dto.repository.DeploymentQueryDto;
import org.camunda.bpm.engine.spring.application.SpringProcessApplication;

@ProcessApplication("customApplication")
public class CustomApplication extends SpringProcessApplication {
	
	@PostDeploy
	public void registerAllProcessDefinitions(ProcessEngine defaultProcessEngine) {
		 DeploymentQueryDto queryDto = new DeploymentQueryDto();
		 DeploymentQuery query = queryDto.toQuery(defaultProcessEngine);
		 List<Deployment> matchingDeployments = query.list();
		 for (Deployment deployment : matchingDeployments) {
			 defaultProcessEngine.getManagementService().registerProcessApplication(deployment.getId(), this.getReference());
		 }		 
	}
}
