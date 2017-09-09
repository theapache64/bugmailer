package com.theah64.bugmailerexample;


import com.theah64.bugmailer.core.BugMailerNode;
import com.theah64.bugmailer.core.NodeBuilder;
import com.theah64.bugmailer.models.Node;

import java.util.List;

/**
 * Created by theapache64 on 9/9/17.
 */

public class Person implements BugMailerNode {

    private final String name;
    private final String age;

    public Person(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }


    @Override
    public List<Node> getNodes() {
        return new NodeBuilder()
                .add("Name", name)
                .add("Age", age)
                .build();
    }
}
