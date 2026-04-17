package xyz.apex.minecraft.fantasyfurniture.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import xyz.apex.minecraft.fantasyfurniture.FantasyFurniture;

import java.util.function.Supplier;

public final class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FantasyFurniture.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> FANTASYFURNITURE =
            CREATIVE_TABS.register("fantasyfurniture",
                    () -> CreativeModeTab.builder()
                            .title(Component.translatable("itemGroup.fantasyfurniture"))
                            .icon(() -> ModItems.FURNITURE_STATION.get().getDefaultInstance())
                            .displayItems((parameters, output) -> {
                                for (Supplier<? extends Item> itemSupplier : ModItems.CREATIVE_TAB_ITEMS) {
                                    output.accept(itemSupplier.get());
                                }
                            })
                            .build());

    private ModCreativeTabs() {}
}
