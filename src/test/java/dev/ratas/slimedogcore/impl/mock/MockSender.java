package dev.ratas.slimedogcore.impl.mock;

import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;

public class MockSender implements CommandSender {
    private final Logger logger;

    public MockSender(Logger logger) {
        this.logger = logger;
    }

    @Override
    public boolean isPermissionSet(String name) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isPermissionSet(Permission perm) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasPermission(String name) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasPermission(Permission perm) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int ticks) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, int ticks) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void removeAttachment(PermissionAttachment attachment) {
        // TODO Auto-generated method stub

    }

    @Override
    public void recalculatePermissions() {
        // TODO Auto-generated method stub

    }

    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isOp() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setOp(boolean value) {
        // TODO Auto-generated method stub

    }

    @Override
    public void sendMessage(String message) {
        logger.info(message);
    }

    @Override
    public void sendMessage(String... messages) {
        for (String msg : messages) {
            sendMessage(msg);
        }
    }

    @Override
    public void sendMessage(UUID sender, String message) {
        sendMessage(message);
    }

    @Override
    public void sendMessage(UUID sender, String... messages) {
        sendMessage(messages);
    }

    @Override
    public Server getServer() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getName() {
        return "MockSender";
    }

    @Override
    public Spigot spigot() {
        // TODO Auto-generated method stub
        return null;
    }

}
