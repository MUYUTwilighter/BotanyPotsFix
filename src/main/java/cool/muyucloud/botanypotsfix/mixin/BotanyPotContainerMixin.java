package cool.muyucloud.botanypotsfix.mixin;

import cool.muyucloud.botanypotsfix.BotanyPotsFix;
import cool.muyucloud.botanypotsfix.access.BotanyPotContainerAccess;
import net.darkhax.botanypots.block.inv.BotanyPotContainer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BotanyPotContainer.class)
public abstract class BotanyPotContainerMixin implements BotanyPotContainerAccess {
    @Shadow(remap = false) public abstract ItemStack getCropStack();
    @Shadow(remap = false) public abstract ItemStack getSoilStack();

    @Shadow public abstract void setChanged();

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

    @Inject(method = "update", at= @At("HEAD"), remap = false, cancellable = true)
    private void onUpdate(CallbackInfo ci) {
        if (this.hasGrowthChanged() || this.getLastUpdate() < BotanyPotsFix.LAST_RELOAD) {
            this.markUpdate();
        } else {
            ci.cancel();
        }
    }
}
