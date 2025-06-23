package cool.muyucloud.botanypotsfix;

import dev.architectury.event.events.common.LifecycleEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class BotanyPotsFix {
    public static final String MOD_ID = "botanypotsfix";
    public static final Logger LOGGER = LogManager.getLogger();
    protected static Long LAST_RELOAD = 0L;

    public static void init() {
        LOGGER.info("Ready to optimize Botany Pots!");
        LOGGER.info("This mod is designed to fix issue #421 of Botany Pots, made by MUYU_Twilighter");
        LOGGER.info("The fix might be a part of Botany Pots in the future. At that time, this mod is no longer needed.");
        LifecycleEvent.SERVER_STARTING.register((server) -> markReload());
    }

    public static void markReload() {
        LAST_RELOAD = System.currentTimeMillis();
    }

    public static long lastReload() {
        return LAST_RELOAD;
    }
}
