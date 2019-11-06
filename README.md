# BugMailer
A simple android library to get bug reports instantly via email

### How to integrate this library ?

**Step one - Add to build.gradle**

```groovy
compile 'com.theah64.bugmailer:bugmailer:3.0.1'
```

**Step two - Initialize BugMailer in application instance**
```java
public class App extends Application {
    
    
    @Override
    public void onCreate() {
        super.onCreate();
        
        BugMailer.init(this, new BugMailerConfig("youremail@ddress.com"));
    }
}
```

That's it!


### Customization

**Report manually**

You can also report exceptions manually

```java
        try {
            ...
        } catch (JSONException e) {
            e.printStackTrace();
            
            //Manually reporting
            BugMailer.report(e);
        }
```

**Include your own model object**

You can also include object properties within the error report using `BugMailerNode` interface.

***Example***

```java
public class Person implements BugMailerNode {

    private final String name;
    private final String age;

    Person(String name, String age) {
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
```

and pass the object to the `BugMailer.report()` method

```java
        final Person person = new Person("theapache64", "20");

        try {
            ...
        } catch (JSONException e) {
            e.printStackTrace();

            //Manually reporting with custom object
            BugMailer.report(e, person);
        }
```

and the error report will look like this

![](https://i.stack.imgur.com/Utmwz.png)

**Color customization**
 
 You can also change the theme color of the report using the `BugMailerConfig` class on `init`
 
 ```java
 BugMailer.init(this, new BugMailerConfig("theapache64@gmail.com")
                    .setThemeColor(Colors.MATERIAL_DEEP_BLUE_500)
            );
```
The  `Colors` class comes with all material `500` colors.
You can also use custom colors with `#RGB`, `#RRGGBB` and `#AARRGGBB` formats.

**Multiple mails**

You can set CC to report mails using `BugMailerConfig.addRepientEmail()` method

```java
        try {
            BugMailer.init(this, new BugMailerConfig("theapache64@gmail.com")
                    .setThemeColor(Colors.MATERIAL_DEEP_BLUE_500)
                    .addRepientEmail("co-developer@email.com")
            );
        } catch (BugMailerException e) {
            e.printStackTrace();
        }
```

**EMail with specific exception**

You can set custom exception type to emails, so that they'll get bug report only when that specific exception occurred.

```java
	try {
            BugMailer.init(this, new BugMailerConfig("theapache64@gmail.com")
                    .addRecipientEmail("api_developer@example.com", JSONException.class)
                    .addRecipientEmail("web_developer_1@example.com", JSONException.class)
            );
        } catch (BugMailerException e) {
            e.printStackTrace();
        }

```

**GitHub issue tracker integration**

To integrate your github repo's issue tracker with BugMailer, use `BugMailerConfig#enableGitHubIssueTracker`.

```java
    bugmailerConfig.enableGitHubIssueTracker("author-name", "your-repo-name")
```


**Issue or Improvements**

Shoot me a mail to theapache64@gmail.com :) 
