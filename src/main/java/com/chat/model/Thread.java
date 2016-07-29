package com.chat.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonView;

/**
 * Created by sharjjeel on 7/11/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Thread {
    @JsonView
    public String thread_name;

    @JsonView
    public String ts;
}
