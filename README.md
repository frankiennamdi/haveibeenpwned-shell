# Description

This project is a client to the popular https://haveibeenpwned.com/API/v2 site and supports
some of the services. It supports interactive (REPL) and non-interactive (SCRIPT) mode.
Interactive mode is faster as the application only loads once.

# Technologies
1. Gradle 4.6 (included)
2. Java 8.x - chosen for some of its appealing functionality like streams, functions and method references.
3. Developed with IDEA Intellij CE 2018.2- chosen as my IDE of choice.
4. Spring Boot/Spring Shell

# Tests

Most the tests are integration (ITest) tests as they access the **haveibeenpwned** site. So
it is possible to reach the rate limit if run repeatedly and frequently. This gives us
over 92% Class / 84% Method coverage. In the future some unit tests can be added if needed.

# Build

```
./gradlew clean build"
```

# Interactive Execution (REPL Mode)
Below are some examples of how to execute the application.

### Printing the services supported

/pwned is a wrapper script around the `java -jar haveibeenpwned-shell-[VERSION].jar`. Which
create a shell prompt.


```
./pwned
```

**output:**

```
pawned:>
````


You can type the following command in the prompt. `pawed:>` is not part of the command

### Printing Help

In the shell you can type help. The help provides documentation of what the commands do.

```
pawned:>help
AVAILABLE COMMANDS

Breach Command
        breach: Get a single breached site

Breached Account Command
        breached-account: Return a list of all breaches a particular account has been involved in

Breaches Command
        breaches: Find all breaches

Paste Account Command
        paste-account: Searches breaches related the given email account
pawned:>

```

```
pawned:>help breach


NAME
	breach - Get a single breached site

SYNOPSYS
	breach [-name] string

OPTIONS
	-name  string
		name of the site
		[Mandatory]



pawned:>
```



### breachedaccount

In the prompt you can type **breached-account**

```
pawned:>breached-account -account test@example.com -domain adobe.com -truncate-response -include-unverified
```


### breaches

```
breaches -domain adobe.com
```


### pasteaccount

```
pawned:>paste-account -account test@example.com
```


### breach

```
pawned:>breach -name adobe
```

# Non-Interactive Execution (Script Mode)

You can run all the commands listed above but with the -c options

```
./pwned -c "breach -name adobe"
```