# SlimeDogCore
Core functionality for SlimeDog resources

You are welcome to use it under the terms of the GPL3 license.

Please give credit where due.

[Javadocs](https://blackdog.straight8.com/minecraft/SlimeDogCore/javadocs/)

## Resources known to incorporate SlimeDogCore
[AggressiveAnimals](https://github.com/SlimeDog/AggressiveAnimals/)

## How to incorporate SlimeDogCore in your plugin
Add to `pom.xml` sections
```
    <repositories>
        <repository>
            <id>jitpack.io</id> <!-- For automated building of SlimeDogCore-->
            <url>https://jitpack.io</url>
        </repository>
    </repositories>
    
    <dependencies>
        <dependency>
            <groupId>com.github.SlimeDog</groupId>
            <artifactId>SlimeDogCore</artifactId>
            <version>0.1.1-SNAPSHOT</version>
            <scope>compile</scope>
        </dependency>
    </dependencies>
```
