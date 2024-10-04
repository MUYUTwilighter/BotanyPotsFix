package cool.muyucloud.botanypotsfix.mixin;

import net.darkhax.bookshelf.api.block.entity.WorldlyInventoryBlockEntity;
import net.darkhax.botanypots.block.BlockEntityBotanyPot;
import net.darkhax.botanypots.block.inv.BotanyPotContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BlockEntityBotanyPot.class)
public abstract class BlockEntityBotanyPotMixin extends WorldlyInventoryBlockEntity<BotanyPotContainer> {
    public BlockEntityBotanyPotMixin(BlockEntityType type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    /** Only load super if crops and soils are any different,
     * indicating a change should be done on renderer.<br/>
     * Client just need the crop and soil stacks to render BE,
     * see {@link net.darkhax.botanypots.block.BotanyPotRenderer}.<br/>
     * The product stacks are only needed on Pot UI,
     * but data are synced in {@link BlockEntityBotanyPot#createMenu(int, Inventory)} invoked on server side
     * */
    @Redirect(method = "load", at = @At(value = "INVOKE", target = "Lnet/darkhax/bookshelf/api/block/entity/WorldlyInventoryBlockEntity;load(Lnet/minecraft/nbt/CompoundTag;)V"))
    public void redirectLoad(WorldlyInventoryBlockEntity<BotanyPotContainer> instance, CompoundTag compoundTag) {
        // Does not contain inventory tag, return in case of NPE
        if (!compoundTag.contains("Inventory")) {
            return;
        }
        // only load if crops and soils are any different
        ItemStack clientSoilStack = this.getInventory().getSoilStack();
        ItemStack clientCropStack = this.getInventory().getCropStack();
        ListTag items = compoundTag.getCompound("Inventory").getList("Items", 10);
        ItemStack serverSoilStack = ItemStack.of(items.getCompound(0));
        ItemStack serverCropStack = ItemStack.of(items.getCompound(1));
        if (!ItemStack.isSameItemSameTags(clientCropStack, serverCropStack) ||
            !ItemStack.isSameItemSameTags(clientSoilStack, serverSoilStack)) {
            super.load(compoundTag);
        }
    }
}
