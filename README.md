# SlimeDogCore
Core functionality for high-quality Minecraft resources produced by [SlimeDog](https://github.com/SlimeDog) and engineered by (mostly) [mart-r](https://github.com/mart-r)

⚠️ SlimeDogCore is a work-in-progress.
You are welcome to use it under the terms of the GPL3 license,
with the understanding that changes may be made as it is incorporated into other SlimeDog projects.

Please give credit where due.

📜 [Javadocs](https://blackdog.straight8.com/minecraft/SlimeDogCore/javadocs/)

## Resources known to incorporate SlimeDogCore
[SlimeDog/AggressiveAnimals](https://github.com/SlimeDog/AggressiveAnimals/) <br>
[SlimeDog/BiomeRemap](https://github.com/SlimeDog/BiomeRemap/) <br>
[SlimeDog/MobColors](https://github.com/SlimeDog/MobColors/) <br>
[SlimeDog/NetworkInterceptor](https://github.com/SlimeDog/NetworkInterceptor/) <br>
[SlimeDog/pHD](https://github.com/SlimeDog/pHD/) <br>
[SlimeDog/PluginVersions](https://github.com/SlimeDog/PluginVersions/) <br>

Let us know if you would like your project included in this list.

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
            <version>0.1.9-SNAPSHOT</version>
            <scope>compile</scope>
        </dependency>
    </dependencies>
```
The dependency needs to be shaded into the plugin (included in its jar).
So the following is also needed under build -> plugins
```
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-shade-plugin</artifactId>
      <version>3.3.1</version>
      <configuration>
        <relocations>
            <relocation>
                <pattern>dev.ratas.slimedogcore</pattern>
                <shadedPattern>${project.groupId}.${project.artifactId}.core</shadedPattern>
            </relocation>
        </relocations>
      </configuration>
      <executions>
        <execution>
          <phase>package</phase>
          <goals>
            <goal>shade</goal>
          </goals>
        </execution>
      </executions>
    </plugin>
```
PS: It is strongly advised that the dependency be relocated so as to avoid conflicts with other plugins which may have a different version of SlimeDogCore included. One can notice that this is also done in the above example.
