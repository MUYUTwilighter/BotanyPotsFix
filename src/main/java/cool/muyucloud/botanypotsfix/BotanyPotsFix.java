package cool.muyucloud.botanypotsfix;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BotanyPotsFix implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger();
    public static Long LAST_RELOAD = 0L;

    @Override
    public void onInitialize() {
        LOGGER.info("Ready to optimize Botany Pots!");
        LOGGER.info("This mod is designed to fix issue #421 of Botany Pots, made by MUYU_Twilighter");
        LOGGER.info("The fix might be a part of Botany Pots in the future. At that time, this mod is no longer needed.");
        ServerLifecycleEvents.SERVER_STARTING.register((server) -> markReload());
        ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((server, context, packs) -> markReload());
    }

    public static void markReload() {
        LAST_RELOAD = System.currentTimeMillis();
    }
}
