package com.theah64.bugmailerexample


import com.theah64.bugmailer.core.BugMailerNode
import com.theah64.bugmailer.core.NodeBuilder
import com.theah64.bugmailer.models.Node

/**
 * Created by theapache64 on 9/9/17.
 */

class Person internal constructor(val name: String, val age: String) : BugMailerNode {
    override val nodes: List<Node>
        get() = NodeBuilder()
                .add("Name", name)
                .add("Age", age)
                .build()
}