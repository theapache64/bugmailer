package com.theah64.bugmailer.core;

import com.theah64.bugmailer.models.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by theapache64 on 9/9/17.
 */

public class NodeBuilder {

    private final List<Node> nodes = new ArrayList<>();

    public NodeBuilder add(Node node) {
        nodes.add(node);
        return this;
    }

    public NodeBuilder add(final String key, final String value) {
        return add(new Node(key, value));
    }

    public List<Node> build() {
        return nodes;
    }
}
