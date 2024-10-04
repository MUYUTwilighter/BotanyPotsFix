package cool.muyucloud.botanypotsfix;

import net.fabricmc.api.ModInitializer;

import java.util.logging.Logger;

public class BotanyPotsFix implements ModInitializer {
    public static final Logger LOGGER = Logger.getLogger("BotanyPotsFix");

    @Override
    public void onInitialize() {
        LOGGER.info("Ready to optimize Botany Pots!");
        LOGGER.info("This mod is designed to fix issue #421 of Botany Pots, made by MUYU_Twilighter");
        LOGGER.info("The fix might be a part of Botany Pots in the future. At that time, this mod is no longer needed.");
    }
}
