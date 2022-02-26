package dev.ratas.slimedogcore.impl.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.function.BiConsumer;

import javax.net.ssl.HttpsURLConnection;

import com.google.common.io.Resources;
import com.google.common.net.HttpHeaders;

import dev.ratas.slimedogcore.api.SlimeDogPlugin;

public class UpdateChecker {
    private static final String SPIGOT_URL_BASE = "https://api.spigotmc.org/legacy/update.php?resource=";
    private final SlimeDogPlugin plugin;
    private final String spigotUrl;
    private final BiConsumer<VersionResponse, String> versionResponse;
    private final String currentVersion;

    public UpdateChecker(SlimeDogPlugin plugin, BiConsumer<VersionResponse, String> consumer, int spigotId) {
        this.plugin = plugin;
        this.currentVersion = plugin.getPluginInformation().getPluginVersion();
        this.spigotUrl = SPIGOT_URL_BASE + spigotId;
        this.versionResponse = consumer;
    }

    public void check() {
        plugin.getScheduler().runTaskAsync(() -> {
            try {
                HttpURLConnection httpURLConnection = (HttpsURLConnection) new URL(spigotUrl).openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setRequestProperty(HttpHeaders.USER_AGENT, "Mozilla/5.0");

                String fetchedVersion = Resources.toString(httpURLConnection.getURL(), Charset.defaultCharset());

                boolean latestVersion = fetchedVersion.equalsIgnoreCase(this.currentVersion);

                plugin.getScheduler().runTask(() -> this.versionResponse.accept(
                        latestVersion ? VersionResponse.LATEST : VersionResponse.FOUND_NEW,
                        latestVersion ? this.currentVersion : fetchedVersion));
            } catch (IOException exception) {
                exception.printStackTrace();
                plugin.getScheduler().runTask(() -> this.versionResponse.accept(VersionResponse.UNAVAILABLE, null));
            }
        });
    }

    public static enum VersionResponse {
        LATEST, FOUND_NEW, UNAVAILABLE
    }

}
