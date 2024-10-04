package cool.muyucloud.botanypotsfix;

import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod("botanypotsfix")
public class BotanyPotsFix {
    private static final Logger LOGGER = LogUtils.getLogger();

    public BotanyPotsFix() {
        LOGGER.info("Ready to optimize Botany Pots!");
        LOGGER.info("This mod is designed to fix issue #421 of Botany Pots, made by MUYU_Twilighter");
        LOGGER.info("The fix might be a part of Botany Pots in the future. At that time, this mod is no longer needed.");
    }
}
