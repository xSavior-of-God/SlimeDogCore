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
    private static final String HANGAR_URL_BASE = "https://hangar.papermc.io/api/v1/projects/{author}/{slug}/latestrelease";
    private final SlimeDogPlugin plugin;
    private final String url;
    private final BiConsumer<VersionResponse, String> versionResponse;
    private final String currentVersion;

    public UpdateChecker(SlimeDogPlugin plugin, BiConsumer<VersionResponse, String> consumer, String url) {
        this.plugin = plugin;
        this.currentVersion = plugin.getPluginInformation().getPluginVersion();
        this.url = url;
        this.versionResponse = consumer;
    }

    private void checkNow() {
        try {
            HttpURLConnection httpURLConnection = (HttpsURLConnection) new URL(url).openConnection();
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
    }

    public void check() {
        check(false);
    }

    public void check(boolean inSync) {
        if (inSync) {
            checkNow();
        } else {
            plugin.getScheduler().runTaskAsync(() -> {
                checkNow();
            });
        }
    }

    public static final UpdateChecker forSpigot(SlimeDogPlugin plugin, BiConsumer<VersionResponse, String> consumer,
            int spigotId) {
        String url = SPIGOT_URL_BASE + spigotId;
        return new UpdateChecker(plugin, consumer, url);
    }

    public static final UpdateChecker forHangar(SlimeDogPlugin plugin, BiConsumer<VersionResponse, String> consumer,
            String author, String slug) {
        String url = HANGAR_URL_BASE.replace("{author}", author).replace("{slug}", slug);
        return new UpdateChecker(plugin, consumer, url);
    }

    public static enum VersionResponse {
        LATEST, FOUND_NEW, UNAVAILABLE
    }

}
