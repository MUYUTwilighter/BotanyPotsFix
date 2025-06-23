package cool.muyucloud.botanypotsfix.fabric;

import net.fabricmc.api.ModInitializer;

import cool.muyucloud.botanypotsfix.BotanyPotsFix;

public final class BotanyPotsFixFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        BotanyPotsFix.init();
    }
}
