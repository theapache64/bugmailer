package com.theah64.bugmailer.models;

/**
 * Created by theapache64 on 9/9/17.
 */

public class BoldNode extends Node {

    public BoldNode(String key, String value) {
        super(key, String.format("<b>%s</b>", value));
    }
}
