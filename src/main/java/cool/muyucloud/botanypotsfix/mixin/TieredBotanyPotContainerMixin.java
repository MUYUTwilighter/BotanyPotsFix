package cool.muyucloud.botanypotsfix.mixin;

import com.ultramega.botanypotstiers.block.inv.TieredBotanyPotContainer;
import cool.muyucloud.botanypotsfix.BotanyPotsFix;
import cool.muyucloud.botanypotsfix.access.BotanyPotContainerAccess;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TieredBotanyPotContainer.class)
public abstract class TieredBotanyPotContainerMixin implements BotanyPotContainerAccess {
    @Shadow(remap = false)
    public abstract ItemStack getCropStack();

    @Shadow(remap = false)
    public abstract ItemStack getSoilStack();

    private Long lastUpdate = 0L;
    private ItemStack oldCrop = ItemStack.EMPTY;
    private ItemStack oldSoil = ItemStack.EMPTY;

    @Override
    public Long getLastUpdate() {
        return this.lastUpdate;
    }

    @Override
    public void markUpdate() {
        this.lastUpdate = System.currentTimeMillis();
    }

    @Override
    public boolean hasGrowthChanged() {
        ItemStack newCrop = this.getCropStack();
        ItemStack newSoil = this.getSoilStack();
        if (ItemStack.isSameItemSameTags(oldCrop, newCrop) && ItemStack.isSameItemSameTags(oldSoil, newSoil)) {
            return false;
        } else {
            oldCrop = newCrop;
            oldSoil = newSoil;
            return true;
        }
    }

    @Inject(method = "update", at = @At("HEAD"), remap = false, cancellable = true)
    private void onUpdate(CallbackInfo ci) {
        if (this.hasGrowthChanged() || this.getLastUpdate() < BotanyPotsFix.LAST_RELOAD) {
            this.markUpdate();
        } else {
            ci.cancel();
        }
    }
}
