package com.leo.lCore;

import com.leo.lCore.Commands.AdminCommands.*;
import com.leo.lCore.Commands.UserCommands.InfoCommands;
import com.leo.lCore.Commands.UserCommands.MenuCommand;
import com.leo.lCore.Commands.UserCommands.MessageCommands;
import com.leo.lCore.Commands.UserCommands.ReplayCommand;
import com.leo.lCore.Listeners.*;
import com.leo.lCore.Manager.NametagManager;
import com.leo.lCore.Manager.RankManager;
import com.leo.lCore.Manager.RewardManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

public final class Main extends JavaPlugin implements Listener {

    private HashMap<UUID, UUID> recentMessages;
    private RankManager ranksManager;
    private NametagManager nametagManager;
    private FileConfiguration licenseConfig;

    @Override
    public void onEnable() {
        // 1. Mostrar el ASCII art de inicio
        String asciiArt = " .__  _________                       \n" +
                "|  | \\_   ___ \\  ___________   ____  \n" +
                "|  | /    \\  \\/ /  _ \\_  __ \\_/ __ \\ \n" +
                "|  |_\\     \\___(  <_> )  | \\/\\  ___/ \n" +
                "|____/\\______  /\\____/|__|    \\___  >\n" +
                "             \\/                   \\/ ";
        getLogger().info(asciiArt);


        // 5. Inicializar managers
        ranksManager = new RankManager(this);
        nametagManager = new NametagManager(this);

        // 6. Registrar comandos
        registerCommands();

        // 7. Registrar listeners
        registerListeners();

        // 8. Inicializar archivos
        try {
            initiateFile("database.yml");
            initiateFile("config.yml");
            initiateFile("ranks.yml");
            initiateFile("tags.yml");
            initiateFile("language.yml");
        } catch (Exception e) {
            getLogger().severe("§cError inicializando archivos: " + e.getMessage());
        }

        // 9. Inicializar el mapa de mensajes recientes
        recentMessages = new HashMap<>();
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    /**
     * Registra los comandos del plugin.
     */
    private void registerCommands() {
        // COMANDOS DE ADMINISTRADOR
        getCommand("ban").setExecutor(new BanCommand());
        getCommand("broadcast").setExecutor(new BroadcastCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("alert").setExecutor(new AlertCommand());
        getCommand("unban").setExecutor(new UnBanCommand());
        getCommand("banwave").setExecutor(new BanWaveCommand());
        getCommand("maintenance").setExecutor(new MaintenanceCommand(this));
        getCommand("automessage").setExecutor(new AutoMessagesCommand(this));
        getCommand("rank").setExecutor(new RankCommand(this));

        // COMANDOS DE PRUEBA
        getCommand("test").setExecutor(new TestCommand(this));
        getCommand("menu").setExecutor(new MenuCommand());

        // COMANDOS DE USUARIO
        getCommand("msg").setExecutor(new MessageCommands(this));
        getCommand("r").setExecutor(new ReplayCommand(this));
        getCommand("info").setExecutor(new InfoCommands());
    }

    /**
     * Registra los listeners del plugin.
     */
    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new JoinListener(this), this);
        getServer().getPluginManager().registerEvents(new QuitListener(this), this);
        getServer().getPluginManager().registerEvents(new MenuListener(), this);
        getServer().getPluginManager().registerEvents(new RankListener(this), this);
        getServer().getPluginManager().registerEvents(new RewardManager(this), this);
    }

    /**
     * Inicializa un archivo de configuración si no existe.
     *
     * @param name El nombre del archivo.
     * @throws Exception Si ocurre un error al crear el archivo.
     */
    private void initiateFile(String name) throws Exception {
        File file = new File(getDataFolder(), name);
        if (!file.exists()) {
            file.createNewFile();
        }
        YamlConfiguration modifyFile = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Obtiene el mapa de mensajes recientes.
     *
     * @return El mapa de mensajes recientes.
     */
    public HashMap<UUID, UUID> getRecentMessages() {
        return recentMessages;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        recentMessages.remove(e.getPlayer().getUniqueId());
    }

    /**
     * Obtiene el RankManager.
     *
     * @return El RankManager.
     */
    public RankManager getRanksManager() {
        return ranksManager;
    }

    /**
     * Obtiene el NametagManager.
     *
     * @return El NametagManager.
     */
    public NametagManager getNametagManager() {
        return nametagManager;
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin desactivado");
    }
}