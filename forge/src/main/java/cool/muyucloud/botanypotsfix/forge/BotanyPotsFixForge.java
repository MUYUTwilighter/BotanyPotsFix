package cool.muyucloud.botanypotsfix.forge;

import cool.muyucloud.botanypotsfix.BotanyPotsFix;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BotanyPotsFix.MOD_ID)
public final class BotanyPotsFixForge {
    @SuppressWarnings("removal")
    public BotanyPotsFixForge() {
        EventBuses.registerModEventBus(BotanyPotsFix.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        BotanyPotsFix.init();
    }
}
