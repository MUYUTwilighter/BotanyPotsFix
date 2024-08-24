package cool.muyucloud.botanypotsfix;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import java.util.logging.Logger;

public class BotanyPotsFix implements ModInitializer {
    public static final Logger LOGGER = Logger.getLogger("BotanyPotsFix");

    @Override
    public void onInitialize() {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER) {
            LOGGER.warning("This is a client mod, running on server is ineffective.");
        }
    }
}
