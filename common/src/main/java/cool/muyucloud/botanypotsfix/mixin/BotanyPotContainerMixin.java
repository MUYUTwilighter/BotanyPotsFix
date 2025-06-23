package cool.muyucloud.botanypotsfix.mixin;

import cool.muyucloud.botanypotsfix.BotanyPotsFix;
import net.darkhax.botanypots.block.inv.BotanyPotContainer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BotanyPotContainer.class)
public abstract class BotanyPotContainerMixin {
    @Shadow(remap = false) public abstract ItemStack getCropStack();
    @Shadow(remap = false) public abstract ItemStack getSoilStack();

    @Unique
    private Long botanypotsfix$lastUpdate = 0L;
    @Unique
    private ItemStack botanypotsfix$oldCrop = ItemStack.EMPTY;
    @Unique
    private ItemStack botanypotsfix$oldSoil = ItemStack.EMPTY;

    @Unique
    public Long botanypotsfix$getLastUpdate() {
        return this.botanypotsfix$lastUpdate;
    }

    @Unique
    public void botanypotsfix$markUpdate() {
        this.botanypotsfix$lastUpdate = System.currentTimeMillis();
    }

    @Unique
    public boolean botanypotsfix$hasGrowthChanged() {
        ItemStack newCrop = this.getCropStack();
        ItemStack newSoil = this.getSoilStack();
        if (ItemStack.isSameItemSameTags(botanypotsfix$oldCrop, newCrop) && ItemStack.isSameItemSameTags(botanypotsfix$oldSoil, newSoil)) {
            return false;
        } else {
            botanypotsfix$oldCrop = newCrop;
            botanypotsfix$oldSoil = newSoil;
            return true;
        }
    }

    @Inject(method = "update", at= @At("HEAD"), remap = false, cancellable = true)
    private void onUpdate(CallbackInfo ci) {
        if (this.botanypotsfix$hasGrowthChanged() || this.botanypotsfix$getLastUpdate() < BotanyPotsFix.lastReload()) {
            this.botanypotsfix$markUpdate();
            // Go on updating recipe...
        } else {
            ci.cancel();
        }
    }
}