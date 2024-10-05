package cool.muyucloud.botanypotsfix;

import com.mojang.logging.LogUtils;
import net.darkhax.bookshelf.impl.util.ForgeEventHelper;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.EventBus;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.IEventListener;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.slf4j.Logger;

@Mod("botanypotsfix")
public class BotanyPotsFix {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static Long LAST_RELOAD = 0L;

    public BotanyPotsFix() {
        LOGGER.info("Ready to optimize Botany Pots!");
        LOGGER.info("This mod is designed to fix issue #421 of Botany Pots, made by MUYU_Twilighter");
        LOGGER.info("The fix might be a part of Botany Pots in the future. At that time, this mod is no longer needed.");
        IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addListener(BotanyPotsFix::onDatapackReload);
        bus.addListener(BotanyPotsFix::onServerStarting);
    }

    public static void onDatapackReload(AddReloadListenerEvent event) {
        LAST_RELOAD = System.currentTimeMillis();
    }

    public static void onServerStarting(ServerStartingEvent event) {
        LAST_RELOAD = System.currentTimeMillis();
    }
}
