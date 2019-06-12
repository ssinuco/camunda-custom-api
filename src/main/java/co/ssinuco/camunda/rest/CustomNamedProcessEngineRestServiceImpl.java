package co.ssinuco.camunda.rest;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import org.camunda.bpm.engine.rest.DeploymentRestService;
import org.camunda.bpm.engine.rest.impl.NamedProcessEngineRestServiceImpl;

@Path(CustomNamedProcessEngineRestServiceImpl.PATH)
public class CustomNamedProcessEngineRestServiceImpl extends NamedProcessEngineRestServiceImpl {

  @Override
  @Path("/{name}" + DeploymentRestService.PATH)
  public DeploymentRestService getDeploymentRestService(@PathParam("name") String engineName) {
    String rootResourcePath = getRelativeEngineUri(engineName).toASCIIString();
    CustomDeploymentRestServiceImpl subResource = new CustomDeploymentRestServiceImpl(engineName, getObjectMapper());
    subResource.setRelativeRootResourceUri(rootResourcePath);
    return subResource;
  }  
}
