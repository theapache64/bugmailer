package com.theah64.bugmailer.core

import com.theah64.bugmailer.models.Node

import java.util.ArrayList

/**
 * Created by theapache64 on 9/9/17.
 */

class NodeBuilder {

    private val nodes = ArrayList<Node>()

    fun add(node: Node): NodeBuilder {
        nodes.add(node)
        return this
    }

    fun add(key: String, value: String): NodeBuilder {
        return add(Node(key, value))
    }

    fun build(): List<Node> {
        return nodes
    }
}
