package co.ssinuco.camunda.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.camunda.bpm.engine.rest.impl.CamundaRestResources;

public class RestProcessEngineDeployment extends Application {

  @Override
  public Set<Class<?>> getClasses() {
	  
    Set<Class<?>> classes = new HashSet<Class<?>>();

    Set<Class<?>> RESOURCE_CLASSES = new HashSet<Class<?>>();
    RESOURCE_CLASSES.add(CustomNamedProcessEngineRestServiceImpl.class);
    
    classes.addAll(RESOURCE_CLASSES);
    classes.addAll(CamundaRestResources.getConfigurationClasses());

    return classes;
  }

}