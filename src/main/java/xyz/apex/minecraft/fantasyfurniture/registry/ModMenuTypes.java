package xyz.apex.minecraft.fantasyfurniture.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import xyz.apex.minecraft.fantasyfurniture.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.menu.FurnaceMenu;
import xyz.apex.minecraft.fantasyfurniture.menu.FurnitureStationMenu;
import xyz.apex.minecraft.fantasyfurniture.menu.LargeContainerMenu;
import xyz.apex.minecraft.fantasyfurniture.menu.MediumContainerMenu;
import xyz.apex.minecraft.fantasyfurniture.menu.SmallContainerMenu;

public final class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES =
            DeferredRegister.create(Registries.MENU, FantasyFurniture.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<SmallContainerMenu>> SMALL_CONTAINER =
            MENU_TYPES.register("small_container",
                    () -> IMenuTypeExtension.create(SmallContainerMenu::new));

    public static final DeferredHolder<MenuType<?>, MenuType<MediumContainerMenu>> MEDIUM_CONTAINER =
            MENU_TYPES.register("medium_container",
                    () -> IMenuTypeExtension.create(MediumContainerMenu::new));

    public static final DeferredHolder<MenuType<?>, MenuType<LargeContainerMenu>> LARGE_CONTAINER =
            MENU_TYPES.register("large_container",
                    () -> IMenuTypeExtension.create(LargeContainerMenu::new));

    public static final DeferredHolder<MenuType<?>, MenuType<FurnaceMenu>> FURNACE =
            MENU_TYPES.register("furnace",
                    () -> IMenuTypeExtension.create(FurnaceMenu::new));

    public static final DeferredHolder<MenuType<?>, MenuType<FurnitureStationMenu>> FURNITURE_STATION =
            MENU_TYPES.register("furniture_station",
                    () -> IMenuTypeExtension.create(FurnitureStationMenu::new));

    private ModMenuTypes() {}
}
