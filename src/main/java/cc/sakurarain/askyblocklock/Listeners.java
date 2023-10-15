package cc.sakurarain.askyblocklock;

import com.wasteofplastic.askyblock.ASkyBlockAPI;
import com.wasteofplastic.askyblock.Island;

import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class Listeners implements Listener {
    private ASkyBlockAPI api = ASkyBlockAPI.getInstance();

    public Listeners() {
    }

    @EventHandler
    public void onPlayerPortalEvent(PlayerPortalEvent e) {
        Player player = e.getPlayer();
        if (!this.isCanVisit(player)) {
            e.setCancelled(true);
        }

    }

    @EventHandler
    public void onPlayerTeleportEvent(PlayerTeleportEvent e) {
        Player player = e.getPlayer();
        Location location = e.getTo();
        if (!this.isCanVisit(player, location)) {
            e.setCancelled(true);
        }

    }

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (!this.isCanVisit(player)) {
            e.setCancelled(true);
        }

    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (!this.isCanDo(player)) {
            e.setCancelled(true);
        }

    }

    @EventHandler
    public void onPlayerDropItemEvent(PlayerDropItemEvent e) {
        Player player = e.getPlayer();
        if (!this.isCanDo(player)) {
            e.setCancelled(true);
        }

    }

    @EventHandler
    public void onPlayerPickupItemEvent(PlayerPickupItemEvent e) {
        Player player = e.getPlayer();
        if (!this.isCanDo(player)) {
            e.setCancelled(true);
        }

    }

    public boolean isCanDo(Player player, Location location) {
        String worldName = location.getWorld().getName().toLowerCase();
        if (player.isOp()) {
            return true;
        } else if (!worldName.equals(this.api.getIslandWorld().getName().toLowerCase())) {
            return true;
        } else {
            Island island = this.api.getIslandAt(location);
            if (island != null) {
                List<UUID> list = island.getMembers();
                list.add(island.getOwner());
                if (list.contains(player.getUniqueId())) {
                    return true;
                }
            }

            return false;
        }
    }

    public boolean isCanDo(Player player) {
        return this.isCanDo(player, player.getLocation());
    }

    public boolean isCanVisit(Player player, Location location) {
        String worldName = location.getWorld().getName().toLowerCase();
        if (player.isOp()) {
            return true;
        } else if (!worldName.equals(this.api.getIslandWorld().getName().toLowerCase())) {
            return true;
        } else {
            Island island = this.api.getIslandAt(location);
            if (island != null) {
                if (!island.isLocked()) {
                    return true;
                }

                List<UUID> list = island.getMembers();
                list.add(island.getOwner());
                if (list.contains(player.getUniqueId())) {
                    return true;
                }
            }

            return true;
        }
    }

    public boolean isCanVisit(Player player) {
        return this.isCanVisit(player, player.getLocation());
    }
}
