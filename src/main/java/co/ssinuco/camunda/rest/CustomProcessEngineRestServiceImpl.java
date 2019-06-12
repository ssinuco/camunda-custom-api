package co.ssinuco.camunda.rest;

import java.net.URI;

import javax.ws.rs.Path;

import org.camunda.bpm.engine.rest.ProcessDefinitionRestService;
import org.camunda.bpm.engine.rest.impl.AbstractProcessEngineRestServiceImpl;

public class CustomProcessEngineRestServiceImpl extends AbstractProcessEngineRestServiceImpl {

	  public static final String PATH = "";
	  
	  @Path(ProcessDefinitionRestService.PATH)
	  public ProcessDefinitionRestService getProcessDefinitionService() {
	    return super.getProcessDefinitionService(null);
	  }

	@Override
	protected URI getRelativeEngineUri(String engineName) {
		// TODO Auto-generated method stub
		return URI.create("/");
	}
}
