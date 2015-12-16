package org.barrak.springintegration.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Emilien Guenichon <emilien.guenichon@post.lu>
 */
public class SRIGenericRequest implements Serializable {

    private String id;
    private Map<String, Object> attributes;

    public SRIGenericRequest(String id) {
        this(id, new HashMap<String, Object>());
    }

    public SRIGenericRequest(String id, Map<String, Object> attributes) {
        this.id = id;
        this.attributes = attributes;
    }

    public String getId() {
        return id;
    }

    public Object addAttribute(String key, String value) {
        return attributes.put(key, value);
    }

    public Object getAttribute(String key) {
        return attributes.get(key);
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

}
