package co.ssinuco.camunda.rest;

import java.util.List;
import java.util.Set;

import javax.ws.rs.core.UriInfo;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.application.ProcessApplicationReference;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.application.ProcessApplicationManager;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.rest.dto.repository.DeploymentWithDefinitionsDto;
import org.camunda.bpm.engine.rest.impl.DeploymentRestServiceImpl;
import org.camunda.bpm.engine.rest.mapper.MultipartFormData;
import org.camunda.bpm.application.ProcessApplicationDeploymentInfo;
import org.camunda.bpm.application.ProcessApplicationInfo;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomDeploymentRestServiceImpl extends DeploymentRestServiceImpl {

	public CustomDeploymentRestServiceImpl(String engineName, ObjectMapper objectMapper) {
		super(engineName, objectMapper);
	}
		
	public DeploymentWithDefinitionsDto createDeployment(UriInfo uriInfo, MultipartFormData payload) {
		DeploymentWithDefinitionsDto deploymentDto = (DeploymentWithDefinitionsDto) super.createDeployment(uriInfo, payload);
		
		 ProcessEngine defaultProcessEngine = BpmPlatform.getDefaultProcessEngine();		 
		 ProcessEngineConfigurationImpl defaultProcessEngineConfiguration = (ProcessEngineConfigurationImpl)defaultProcessEngine.getProcessEngineConfiguration();
		 ProcessApplicationManager defaultProcessApplicationManager = defaultProcessEngineConfiguration.getProcessApplicationManager();
		 
		 ProcessApplicationReference processApplication = null;
		 Set<String> processAppNames = BpmPlatform.getProcessApplicationService().getProcessApplicationNames();
		 
		 for (String appName : processAppNames){
			 if(appName.equals("customApplication")) {
				 ProcessApplicationInfo prossApp = BpmPlatform.getProcessApplicationService().getProcessApplicationInfo(appName);
				 List<ProcessApplicationDeploymentInfo> deplymentInfo = prossApp.getDeploymentInfo();
				 for (ProcessApplicationDeploymentInfo processApplicationDeploymentInfo :deplymentInfo){
					 processApplication = defaultProcessApplicationManager.getProcessApplicationForDeployment(processApplicationDeploymentInfo.getDeploymentId());
				 }
				 break;
			 }
		 }
		 if(processApplication != null) {
			 defaultProcessEngine.getManagementService().registerProcessApplication(deploymentDto.getId(), processApplication); 
		 }
		return deploymentDto;		
	}
}
