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

    private void returnLatest() {
        this.versionResponse.accept(VersionResponse.LATEST, this.currentVersion);
    }

    private void atttemptReturnLatest(boolean now) {
        if (now) {
            returnLatest();
        } else {
            plugin.getScheduler().runTask(this::returnLatest);
        }
    }

    private void returnUpdate(String version) {
        this.versionResponse.accept(VersionResponse.FOUND_NEW, version);
    }

    private void atttemptReturnUpdate(String version, boolean inSync) {
        if (inSync) {
            returnUpdate(version);
        } else {
            plugin.getScheduler().runTask(() -> returnUpdate(version));
        }
    }

    private void returnUnavailable() {
        this.versionResponse.accept(VersionResponse.UNAVAILABLE, null);
    }

    private void atttemptReturnUnavailable(boolean inSync) {
        if (inSync) {
            returnUnavailable();
        } else {
            plugin.getScheduler().runTask(this::returnUnavailable);
        }
    }

    private void checkNow(boolean inSync) {
        try {
            HttpURLConnection httpURLConnection = (HttpsURLConnection) new URL(url).openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty(HttpHeaders.USER_AGENT, "Mozilla/5.0");

            String fetchedVersion = Resources.toString(httpURLConnection.getURL(), Charset.defaultCharset());

            boolean latestVersion = fetchedVersion.equalsIgnoreCase(this.currentVersion);
            if (latestVersion) {
                atttemptReturnLatest(inSync);
            } else {
                atttemptReturnUpdate(fetchedVersion, inSync);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
            atttemptReturnUnavailable(inSync);
        }
    }

    public void check() {
        check(false);
    }

    public void check(boolean inSync) {
        if (inSync) {
            checkNow(inSync);
        } else {
            plugin.getScheduler().runTaskAsync(() -> {
                checkNow(inSync);
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

    public static final UpdateChecker forHangarOrSpigot(SlimeDogPlugin plugin,
            BiConsumer<VersionResponse, String> consumer, int spigotId, String hangarAuthor, String hangarSlug,
            boolean hangar) {
        if (hangar) {
            return forHangar(plugin, consumer, hangarAuthor, hangarSlug);
        }
        return forSpigot(plugin, consumer, spigotId);
    }

    public static enum VersionResponse {
        LATEST, FOUND_NEW, UNAVAILABLE
    }

}
