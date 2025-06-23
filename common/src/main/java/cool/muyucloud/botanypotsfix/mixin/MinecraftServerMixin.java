package cool.muyucloud.botanypotsfix.mixin;

import cool.muyucloud.botanypotsfix.BotanyPotsFix;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
    @Inject(method = "reloadResources", at = @At("HEAD"))
    public void onReload(Collection<String> collection, CallbackInfoReturnable<CompletableFuture<Void>> cir) {
        BotanyPotsFix.markReload();
    }
}
