package cc.sakurarain.askyblocklock;

import org.bukkit.plugin.java.JavaPlugin;

public final class ASkyBlockLock extends JavaPlugin {

    @Override
    public void onEnable() {
        if (this.getServer().getPluginManager().getPlugin("ASkyBlock") != null && this.getServer().getPluginManager().getPlugin("ASkyBlock").isEnabled()) {
            this.getServer().getPluginManager().registerEvents(new Listeners(), this);
            this.getLogger().info("空岛全局锁已开启");
        } else {
            this.getLogger().warning("检测到ASkyblock未启用,插件已关闭");
            this.onDisable();
        }
    }

    @Override
    public void onDisable() {
        this.getLogger().info("空岛全局锁已关闭");
    }
}
