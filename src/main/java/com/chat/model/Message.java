package com.chat.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonView;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
    @JsonView
    public String user;
    @JsonView
    public String text;
    @JsonView
    public String ts;
    @JsonView
    public String thread_name;
}