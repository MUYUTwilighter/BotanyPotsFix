package cool.muyucloud.botanypotsfix.mixin;

import net.darkhax.bookshelf.api.block.entity.WorldlyInventoryBlockEntity;
import net.darkhax.botanypots.block.BlockEntityBotanyPot;
import net.darkhax.botanypots.block.inv.BotanyPotContainer;
import net.darkhax.botanypots.data.recipes.crop.Crop;
import net.darkhax.botanypots.data.recipes.soil.Soil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Objects;

@Mixin(BlockEntityBotanyPot.class)
public abstract class BlockEntityBotanyPotMixin extends WorldlyInventoryBlockEntity<BotanyPotContainer> {
    @Shadow
    @Nullable
    public abstract Crop getCrop();

    @Shadow
    @Nullable
    public abstract Soil getSoil();

    public BlockEntityBotanyPotMixin(BlockEntityType type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Redirect(method = "load", at = @At(value = "INVOKE", target = "Lnet/darkhax/bookshelf/api/block/entity/WorldlyInventoryBlockEntity;load(Lnet/minecraft/nbt/CompoundTag;)V"))
    public void redirectLoad(WorldlyInventoryBlockEntity instance, CompoundTag compoundTag) {
        if (this.getCrop() == null || this.getSoil() == null) {
            super.load(compoundTag);
            return;
        }
        if (this.getLevel() != null && !this.getLevel().isClientSide) {
            super.load(compoundTag);
        }
    }
}
