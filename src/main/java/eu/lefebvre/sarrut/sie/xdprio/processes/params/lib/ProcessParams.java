/*
 * Copyright Editions Lefebvre-Sarrut 2019
 */
package eu.lefebvre.sarrut.sie.xdprio.processes.params.lib;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Process parameters.
 * Process parameters are send to process from XDPrio container.
 * Process main class is started with only one argument : URL of parameters file.
 * Process main class <strong>must</strong> use {@link ProcessParamsUtils} to get
 * a {@code ProcessParameters} instance.
 * It is strongly recommanded to not try to parse manually the parameters file.
 * @author cmarchand
 */
public class ProcessParams {
    /**
     * Parameter name for working directory
     */
    public static final String WORKING_DIR_PROPERTY = "working.directory";
    
    private static final Logger LOGGER = LogManager.getLogger(ProcessParams.class);
    private File workingDir;
    private final Map<String,String> parameters;
    
    ProcessParams() {
        super();
        parameters = new HashMap<>();
    }
    
    /**
     * Returns a parameter value, or {@code null} if this parameter is not defined.
     * @param paramName The parameter name
     * @return Parameter value, or {@code null} if missing.
     */
    public String getParameterValue(String paramName) {
        return parameters.get(paramName);
    }
    
    /**
     * Returns working directory URI.
     * @return Working directory
     */
    public URI getWorkingDir() {
        return workingDir.toURI();
    }
    
    void setParameter(String key, String value) {
        parameters.put(key, value);
        if(WORKING_DIR_PROPERTY.equals(key)) {
            if(value.contains(":")) {
                try {
                    workingDir = new File(new URI(value));
                } catch(URISyntaxException ex) {
                    LOGGER.error(value+"::", ex);
                }
            } else {
                workingDir = new File(value);
            }
        }
    }
}
