package dev.imshadow.API;

import dev.imshadow.RyzenStaff;
import dev.imshadow.Commands.Chat.AdminChat;
import dev.imshadow.Commands.Chat.ChatControlCommands;
import dev.imshadow.StaffSystem.ReportSystem;
import org.bukkit.entity.Player;

import java.util.UUID;

public class RyzenStaffApi {

    private static RyzenStaff plugin;

    public RyzenStaffApi(RyzenStaff plugin) {
        RyzenStaffApi.plugin = plugin;
    }

    // --- ChatControl ---
    public boolean isChatEnabled() {
        ChatControlCommands chatControl = plugin.getChatControlCommands();
        return chatControl != null && chatControl.isChatEnabled();
    }

    // --- AdminChat ---
    public boolean isAdminChatMode(Player player) {
        AdminChat adminChat = plugin.getAdminChat();
        return adminChat != null && adminChat.hasAdminChatMode(player.getUniqueId());
    }

    public void toggleAdminChatMode(Player player) {
        AdminChat adminChat = plugin.getAdminChat();
        if (adminChat != null) {
            adminChat.toggleAdminChatMode(player);
        }
    }

    public void sendAdminChatMessage(Player sender, String message) {
        AdminChat adminChat = plugin.getAdminChat();
        if (adminChat != null) {
            adminChat.broadcastAdminMessage(sender, message);
        }
    }

    // --- Reports ---
    public boolean createReport(Player reporter, String targetName, String reason) {
        return ReportSystem.createReport(reporter, targetName, reason);
    }

    // --- Vanish ---
    public boolean isVanished(Player player) {
        if (player.hasMetadata("vanished") && player.getMetadata("vanished").get(0).asBoolean()) {
            return true;
        }
        return dev.imshadow.StaffSystem.VanishSystem.isVanished(player);
    }

}
