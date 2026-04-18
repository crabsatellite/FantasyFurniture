package xyz.apex.minecraft.fantasyfurniture.datagen;

import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import xyz.apex.minecraft.fantasyfurniture.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicBedDoubleBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicBenchBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicBoiledCremeTreatsBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicBookshelfBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicChairBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicChestBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicCounterBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicDeskLeftBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicDeskRightBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicDoorDoubleBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicDoorSingleBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicDresserBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicFloorLightBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicMeadBottlesBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicPaintingWideBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicShelfBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicSofaBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicSweetRollsBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicTableBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicTableLargeBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicTableWideBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicWardrobeBottomBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicWardrobeTopBlock;
import xyz.apex.minecraft.fantasyfurniture.block.royal.RoyalChalicesBlock;
import xyz.apex.minecraft.fantasyfurniture.block.royal.RoyalPlatterBlock;
import xyz.apex.minecraft.fantasyfurniture.block.royal.RoyalWallMirrorTallBlock;
import xyz.apex.minecraft.fantasyfurniture.block.bone.BoneChalicesBlock;
import xyz.apex.minecraft.fantasyfurniture.block.venthyr.VenthyrBannerBlock;
import xyz.apex.minecraft.fantasyfurniture.block.venthyr.VenthyrChalicesBlock;
import xyz.apex.minecraft.fantasyfurniture.block.venthyr.VenthyrTeaCupsBlock;
import xyz.apex.minecraft.fantasyfurniture.block.venthyr.VenthyrTeaSetBlock;
import xyz.apex.minecraft.fantasyfurniture.block.venthyr.VenthyrTomesBlock;
import xyz.apex.minecraft.fantasyfurniture.registry.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    private static final String NORDIC = "fantasyfurniture_nordic";
    private static final String ROYAL = "fantasyfurniture_royal";
    private static final String DUNMER = "fantasyfurniture_dunmer";
    private static final String VENTHYR = "fantasyfurniture_venthyr";
    private static final String NECROLORD = "fantasyfurniture_necrolord";
    private static final String BONE_SKELETON = "fantasyfurniture_bone_skeleton";
    private static final String BONE_WITHER = "fantasyfurniture_bone_wither";

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, FantasyFurniture.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // --- Furniture Station ---
        facingBlock(ModBlocks.FURNITURE_STATION.get(), existingModel("fantasyfurniture", "block/furniture_station"));

        // --- Nordic Wool / Carpet (custom textures from fantasyfurniture_nordic) ---
        // Model names are namespaced so output goes under assets/fantasyfurniture_nordic/models/block/*.
        ResourceLocation woolTexture = ResourceLocation.fromNamespaceAndPath(NORDIC, "block/wool");
        ModelFile nordicWoolModel = models().cubeAll(NORDIC + ":wool", woolTexture);
        ModelFile nordicCarpetModel = models().carpet(NORDIC + ":carpet", woolTexture);
        simpleBlock(ModBlocks.NORDIC_WOOL.get(), nordicWoolModel);
        simpleBlock(ModBlocks.NORDIC_CARPET.get(), nordicCarpetModel);

        // --- Simple facing blocks (model from fantasyfurniture_nordic) ---
        nordicHorizontalBlock(ModBlocks.NORDIC_WALL_LIGHT.get(), "wall_light");
        nordicMultiBlockHorizontal(ModBlocks.NORDIC_FLOOR_LIGHT.get(), NordicFloorLightBlock.PART, "floor_light");
        simpleBlock(ModBlocks.NORDIC_CHANDELIER_LIGHT.get(), nordicModel("chandelier"));
        nordicMultiBlockHorizontal(ModBlocks.NORDIC_CHAIR.get(), NordicChairBlock.PART, "chair");
        nordicMultiBlockHorizontal(ModBlocks.NORDIC_BENCH.get(), NordicBenchBlock.PART, "bench");
        nordicHorizontalBlock(ModBlocks.NORDIC_STOOL.get(), "stool");
        nordicHorizontalBlock(ModBlocks.NORDIC_CUSHION.get(), "cushion");
        registerShelf();
        nordicHorizontalBlock(ModBlocks.NORDIC_DRAWER.get(), "drawer");
        nordicHorizontalBlock(ModBlocks.NORDIC_LOCKBOX.get(), "lockbox");
        nordicMultiBlockHorizontal(ModBlocks.NORDIC_DESK_LEFT.get(), NordicDeskLeftBlock.PART, "desk_left");
        nordicMultiBlockHorizontal(ModBlocks.NORDIC_DESK_RIGHT.get(), NordicDeskRightBlock.PART, "desk_right");
        nordicMultiBlockHorizontal(ModBlocks.NORDIC_DRESSER.get(), NordicDresserBlock.PART, "dresser");
        registerCounter();
        nordicMultiBlockHorizontal(ModBlocks.NORDIC_CHEST.get(), NordicChestBlock.PART, "chest");
        nordicMultiBlockHorizontal(ModBlocks.NORDIC_WARDROBE_BOTTOM.get(), NordicWardrobeBottomBlock.PART, "wardrobe_bottom");
        nordicMultiBlockHorizontal(ModBlocks.NORDIC_WARDROBE_TOP.get(), NordicWardrobeTopBlock.PART, "wardrobe_top");
        nordicMultiBlockHorizontal(ModBlocks.NORDIC_BOOKSHELF.get(), NordicBookshelfBlock.PART, "bookshelf");
        nordicHorizontalBlock(ModBlocks.NORDIC_PAINTING_SMALL.get(), "painting_small");
        nordicMultiBlockHorizontal(ModBlocks.NORDIC_PAINTING_WIDE.get(), NordicPaintingWideBlock.PART, "painting_wide");

        // --- Table (no facing, uses connection booleans selecting 16 directional models) ---
        registerTable();

        // --- Multi-block tables: visible model on PART=0, empty elsewhere.
        // 1.19.4 models use extended-range elements (-16..16) so the origin's model
        // covers the whole footprint; other parts are hollow placeholders.
        nordicMultiBlockHorizontal(ModBlocks.NORDIC_TABLE_WIDE.get(), NordicTableWideBlock.PART, "table_wide");
        nordicMultiBlockHorizontal(ModBlocks.NORDIC_TABLE_LARGE.get(), NordicTableLargeBlock.PART, "table_large");

        // --- Sofa (facing + connection type) ---
        registerSofa();

        // --- Bed Single (extends BedBlock: FACING + BedPart) ---
        registerBedSingle();

        // --- Bed Double (FACING + IntegerProperty PART 0-3) ---
        registerBedDouble();

        // --- Doors (FACING + OPEN + IntegerProperty PART 0-1) ---
        registerDoorSingle();
        registerDoorDouble();

        // --- Oven (FACING + LIT) ---
        registerOven();

        // --- Nordic Decorations ---
        registerNordicDecorations();

        // ====================================================================
        // Royal set (fantasyfurniture_royal:*)
        // Uses 1.19.4-derived models copied into our Royal namespace. Model
        // naming matches Nordic for shared types (chair, stool, etc.); tables
        // collapse 1.19.4's small/wide/large triad into a single model
        // (table_small) because our NordicTableBlock mechanics assume one model.
        // ====================================================================
        registerRoyal();

        // ====================================================================
        // Dunmer set (fantasyfurniture_dunmer:*)
        // 1.19.4 upstream forward-port. Uses 1.19.4-derived models copied into
        // our Dunmer namespace. Like Royal, tables collapse to table_small
        // rendering for the single "table_small" block; table_wide/table_large
        // use multiblock part-based rendering with existing 1.19.4 models.
        // ====================================================================
        registerDunmer();

        // ====================================================================
        // Venthyr set (fantasyfurniture_venthyr:*)
        // 1.19.4 upstream forward-port. Unique to Venthyr: three "fancy" table
        // variants (table_small_fancy, table_wide_fancy, table_large_fancy)
        // that reuse NordicTable/Wide/Large block classes with their own models.
        // ====================================================================
        registerVenthyr();

        // ====================================================================
        // Necrolord set (fantasyfurniture_necrolord:*)
        // 1.19.4 upstream forward-port. Reuses Nordic block classes; only the
        // namespace, model JSONs, and textures differ. Like Dunmer, tables are
        // single, wide, and large (no fancy variants).
        // ====================================================================
        registerNecrolord();
        registerBoneSkeleton();
        registerBoneWither();

        // === Item Models ===
        registerItemModels();
    }

    private void registerShelf() {
        ModelFile shelfSingle = nordicModel("shelf_single");
        ModelFile shelfCenter = nordicModel("shelf_center");
        ModelFile shelfLeft = nordicModel("shelf_left");
        ModelFile shelfRight = nordicModel("shelf_right");

        getVariantBuilder(ModBlocks.NORDIC_SHELF.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            NordicShelfBlock.ConnectionType connection = state.getValue(NordicShelfBlock.CONNECTION);
            int yRot = getYRotation(facing);

            ModelFile model = switch (connection) {
                case CENTER -> shelfCenter;
                case LEFT -> shelfLeft;
                case RIGHT -> shelfRight;
                default -> shelfSingle;
            };

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerTable() {
        ModelFile table = nordicModel("table");
        ModelFile tableN = nordicModel("table_n");
        ModelFile tableE = nordicModel("table_e");
        ModelFile tableS = nordicModel("table_s");
        ModelFile tableW = nordicModel("table_w");
        ModelFile tableNE = nordicModel("table_ne");
        ModelFile tableNS = nordicModel("table_ns");
        ModelFile tableNW = nordicModel("table_nw");
        ModelFile tableES = nordicModel("table_es");
        ModelFile tableEW = nordicModel("table_ew");
        ModelFile tableSW = nordicModel("table_sw");
        ModelFile tableNES = nordicModel("table_nes");
        ModelFile tableNEW = nordicModel("table_new");
        ModelFile tableNSW = nordicModel("table_nsw");
        ModelFile tableESW = nordicModel("table_esw");
        ModelFile tableNESW = nordicModel("table_nesw");

        getVariantBuilder(ModBlocks.NORDIC_TABLE.get()).forAllStates(state -> {
            boolean n = state.getValue(NordicTableBlock.NORTH);
            boolean e = state.getValue(NordicTableBlock.EAST);
            boolean s = state.getValue(NordicTableBlock.SOUTH);
            boolean w = state.getValue(NordicTableBlock.WEST);

            int mask = (n ? 8 : 0) | (e ? 4 : 0) | (s ? 2 : 0) | (w ? 1 : 0);

            ModelFile model = switch (mask) {
                case 0b0000 -> table;
                case 0b1000 -> tableN;
                case 0b0100 -> tableE;
                case 0b0010 -> tableS;
                case 0b0001 -> tableW;
                case 0b1100 -> tableNE;
                case 0b1010 -> tableNS;
                case 0b1001 -> tableNW;
                case 0b0110 -> tableES;
                case 0b0101 -> tableEW;
                case 0b0011 -> tableSW;
                case 0b1110 -> tableNES;
                case 0b1101 -> tableNEW;
                case 0b1011 -> tableNSW;
                case 0b0111 -> tableESW;
                case 0b1111 -> tableNESW;
                default -> table;
            };

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .build();
        });
    }

    private void registerSofa() {
        ModelFile sofaSingle = nordicModel("sofa_single");
        ModelFile sofaLeft = nordicModel("sofa_left");
        ModelFile sofaRight = nordicModel("sofa_right");
        ModelFile sofaCenter = nordicModel("sofa_center");
        ModelFile sofaCorner = nordicModel("sofa_corner");

        getVariantBuilder(ModBlocks.NORDIC_SOFA.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            NordicSofaBlock.ConnectionType connection = state.getValue(NordicSofaBlock.CONNECTION);
            int yRot = getYRotation(facing);

            ModelFile model = switch (connection) {
                case LEFT -> sofaLeft;
                case RIGHT -> sofaRight;
                case CENTER -> sofaCenter;
                case CORNER -> sofaCorner;
                default -> sofaSingle;
            };

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerBedSingle() {
        ModelFile bedSingle = nordicModel("bed_single");
        ModelFile empty = emptyModel();

        getVariantBuilder(ModBlocks.NORDIC_BED_SINGLE.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            BedPart part = state.getValue(BlockStateProperties.BED_PART);
            int yRot = getYRotation(facing);

            ModelFile model = part == BedPart.FOOT ? bedSingle : empty;
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerBedDouble() {
        ModelFile bedDouble = nordicModel("bed_double");
        ModelFile empty = emptyModel();

        getVariantBuilder(ModBlocks.NORDIC_BED_DOUBLE.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int part = state.getValue(NordicBedDoubleBlock.PART);
            int yRot = getYRotation(facing);

            ModelFile model = part == 0 ? bedDouble : empty;
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerCounter() {
        ModelFile counterSingle = nordicModel("counter_single");
        ModelFile counterCorner = nordicModel("counter_corner");

        getVariantBuilder(ModBlocks.NORDIC_COUNTER.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            NordicCounterBlock.ConnectionType connection = state.getValue(NordicCounterBlock.CONNECTION);
            int yRot = getYRotation(facing);

            ModelFile model = connection == NordicCounterBlock.ConnectionType.CORNER ? counterCorner : counterSingle;

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerDoorSingle() {
        ModelFile doorRight = nordicModel("door_single");
        ModelFile doorLeft = nordicModel("door_single_flipped");
        ModelFile empty = emptyModel();

        getVariantBuilder(ModBlocks.NORDIC_DOOR_SINGLE.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            DoorHingeSide hinge = state.getValue(BlockStateProperties.DOOR_HINGE);
            boolean open = state.getValue(BlockStateProperties.OPEN);
            int part = state.getValue(NordicDoorSingleBlock.PART);

            ModelFile model;
            int yRot;
            if (part != 0) {
                model = empty;
                yRot = getYRotation(facing);
            } else {
                model = selectDoorModel(hinge, open, doorLeft, doorRight);
                yRot = doorYRotation(facing, hinge, open);
            }

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerDoorDouble() {
        ModelFile doorRight = nordicModel("door_double");
        ModelFile doorLeft = nordicModel("door_double_flipped");
        ModelFile empty = emptyModel();

        getVariantBuilder(ModBlocks.NORDIC_DOOR_DOUBLE.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            DoorHingeSide hinge = state.getValue(BlockStateProperties.DOOR_HINGE);
            boolean open = state.getValue(BlockStateProperties.OPEN);
            int part = state.getValue(NordicDoorDoubleBlock.PART);

            ModelFile model;
            int yRot;
            if (part != 0) {
                model = empty;
                yRot = getYRotation(facing);
            } else {
                model = selectDoorModel(hinge, open, doorLeft, doorRight);
                yRot = doorYRotation(facing, hinge, open);
            }

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private static ModelFile selectDoorModel(DoorHingeSide hinge, boolean open, ModelFile leftHingeModel, ModelFile rightHingeModel) {
        return hinge == DoorHingeSide.RIGHT ? rightHingeModel : leftHingeModel;
    }

    private static int doorYRotation(Direction facing, DoorHingeSide hinge, boolean open) {
        int yRot = getYRotation(facing);
        if (open) yRot += 90;
        if (hinge == DoorHingeSide.LEFT && open) yRot += 180;
        return ((yRot % 360) + 360) % 360;
    }

    private void registerOven() {
        ModelFile oven = nordicModel("oven");

        getVariantBuilder(ModBlocks.NORDIC_OVEN.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int yRot = getYRotation(facing);

            return ConfiguredModel.builder()
                    .modelFile(oven)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerNordicDecorations() {
        // Soul gems: simple facing blocks
        facingBlock(ModBlocks.NORDIC_SOUL_GEMS_LIGHT.get(), nordicDecorationModel("soul_gems_light"));
        facingBlock(ModBlocks.NORDIC_SOUL_GEMS_DARK.get(), nordicDecorationModel("soul_gems_dark"));

        // Stacked decorations: FACING + IntegerProperty (0..2) → 3 models
        ModelFile[] boiledModels = new ModelFile[]{
                nordicDecorationModel("boiled_creme_treats_0"),
                nordicDecorationModel("boiled_creme_treats_1"),
                nordicDecorationModel("boiled_creme_treats_2"),
        };
        getVariantBuilder(ModBlocks.NORDIC_BOILED_CREME_TREATS.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int count = state.getValue(NordicBoiledCremeTreatsBlock.TREATS);
            return ConfiguredModel.builder()
                    .modelFile(boiledModels[count])
                    .rotationY(getYRotation(facing))
                    .build();
        });

        ModelFile[] sweetrollsModels = new ModelFile[]{
                nordicDecorationModel("sweetrolls_0"),
                nordicDecorationModel("sweetrolls_1"),
                nordicDecorationModel("sweetrolls_2"),
        };
        getVariantBuilder(ModBlocks.NORDIC_SWEETROLLS.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int count = state.getValue(NordicSweetRollsBlock.ROLLS);
            return ConfiguredModel.builder()
                    .modelFile(sweetrollsModels[count])
                    .rotationY(getYRotation(facing))
                    .build();
        });

        ModelFile[] meadModels = new ModelFile[]{
                nordicDecorationModel("mead_bottles_0"),
                nordicDecorationModel("mead_bottles_1"),
                nordicDecorationModel("mead_bottles_2"),
        };
        getVariantBuilder(ModBlocks.NORDIC_MEAD_BOTTLES.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int count = state.getValue(NordicMeadBottlesBlock.BOTTLES);
            return ConfiguredModel.builder()
                    .modelFile(meadModels[count])
                    .rotationY(getYRotation(facing))
                    .build();
        });
    }

    private ModelFile nordicDecorationModel(String name) {
        return existingModel(NORDIC, "block/decorations/" + name);
    }

    private void registerRoyal() {
        // Wool + carpet (generated like Nordic)
        ResourceLocation royalWoolTex = ResourceLocation.fromNamespaceAndPath(ROYAL, "block/wool");
        ModelFile royalWoolModel = models().cubeAll(ROYAL + ":wool", royalWoolTex);
        ModelFile royalCarpetModel = models().carpet(ROYAL + ":carpet", royalWoolTex);
        simpleBlock(ModBlocks.ROYAL_WOOL.get(), royalWoolModel);
        simpleBlock(ModBlocks.ROYAL_CARPET.get(), royalCarpetModel);

        // Simple facing blocks (existing Royal models)
        facingBlock(ModBlocks.ROYAL_WALL_LIGHT.get(), royalModel("wall_light"));
        royalMultiBlockHorizontal(ModBlocks.ROYAL_FLOOR_LIGHT.get(), NordicFloorLightBlock.PART, "floor_light");
        simpleBlock(ModBlocks.ROYAL_CHANDELIER_LIGHT.get(), royalModel("chandelier"));
        royalMultiBlockHorizontal(ModBlocks.ROYAL_CHAIR.get(), NordicChairBlock.PART, "chair");
        royalMultiBlockHorizontal(ModBlocks.ROYAL_BENCH.get(), NordicBenchBlock.PART, "bench");
        facingBlock(ModBlocks.ROYAL_STOOL.get(), royalModel("stool"));
        facingBlock(ModBlocks.ROYAL_CUSHION.get(), royalModel("cushion"));
        registerRoyalShelf();
        facingBlock(ModBlocks.ROYAL_DRAWER.get(), royalModel("drawer"));
        facingBlock(ModBlocks.ROYAL_LOCKBOX.get(), royalModel("lockbox"));
        royalMultiBlockHorizontal(ModBlocks.ROYAL_DESK_LEFT.get(), NordicDeskLeftBlock.PART, "desk_left");
        royalMultiBlockHorizontal(ModBlocks.ROYAL_DESK_RIGHT.get(), NordicDeskRightBlock.PART, "desk_right");
        royalMultiBlockHorizontal(ModBlocks.ROYAL_DRESSER.get(), NordicDresserBlock.PART, "dresser");
        registerRoyalCounter();
        royalMultiBlockHorizontal(ModBlocks.ROYAL_CHEST.get(), NordicChestBlock.PART, "chest");
        royalMultiBlockHorizontal(ModBlocks.ROYAL_WARDROBE_BOTTOM.get(), NordicWardrobeBottomBlock.PART, "wardrobe_bottom");
        royalMultiBlockHorizontal(ModBlocks.ROYAL_WARDROBE_TOP.get(), NordicWardrobeTopBlock.PART, "wardrobe_top");
        royalMultiBlockHorizontal(ModBlocks.ROYAL_BOOKSHELF.get(), NordicBookshelfBlock.PART, "bookshelf");
        facingBlock(ModBlocks.ROYAL_PAINTING_SMALL.get(), royalModel("painting_small"));
        royalMultiBlockHorizontal(ModBlocks.ROYAL_PAINTING_WIDE.get(), NordicPaintingWideBlock.PART, "painting_wide");

        // Royal tables: 1.19.4 ships table_small/wide/large as distinct single-block
        // models; our NordicTableBlock uses 16 connection variants. Until per-set
        // table mechanics split out, Royal table always renders as table_small.
        registerRoyalTable();

        registerRoyalSofa();
        registerRoyalBedSingle();
        registerRoyalBedDouble();
        registerRoyalDoorSingle();
        registerRoyalDoorDouble();
        registerRoyalOven();

        registerRoyalDecorations();
    }

    private void registerRoyalDecorations() {
        facingBlock(ModBlocks.ROYAL_CROWN.get(), royalDecorationModel("crown"));
        facingBlock(ModBlocks.ROYAL_CUSHIONED_CROWN.get(), royalDecorationModel("cushioned_crown"));
        facingBlock(ModBlocks.ROYAL_CANDELABRA.get(), royalDecorationModel("candelabra"));
        facingBlock(ModBlocks.ROYAL_FOOD.get(), royalDecorationModel("food_0"));
        facingBlock(ModBlocks.ROYAL_FLOOR_CUSHION.get(), royalDecorationModel("floor_cushion"));
        facingBlock(ModBlocks.ROYAL_WALL_MIRROR.get(), royalDecorationModel("wall_mirror"));

        // Chalices: 3 stack variants
        ModelFile chalices0 = royalDecorationModel("chalices_0");
        ModelFile chalices1 = royalDecorationModel("chalices_1");
        ModelFile chalices2 = royalDecorationModel("chalices_2");
        getVariantBuilder(ModBlocks.ROYAL_CHALICES.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int count = state.getValue(RoyalChalicesBlock.CHALICES);
            ModelFile model = switch (count) {
                case 2 -> chalices2;
                case 1 -> chalices1;
                default -> chalices0;
            };
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(getYRotation(facing))
                    .build();
        });

        // Platter: 16 stack variants (platter_0..platter_15)
        ModelFile[] platterModels = new ModelFile[16];
        for (int i = 0; i < 16; i++) {
            platterModels[i] = royalDecorationModel("platter_" + i);
        }
        getVariantBuilder(ModBlocks.ROYAL_PLATTER.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int count = state.getValue(RoyalPlatterBlock.PLATTER);
            return ConfiguredModel.builder()
                    .modelFile(platterModels[count % 16])
                    .rotationY(getYRotation(facing))
                    .build();
        });

        // Tall wall mirror: single-block visual model anchored at PART=0 (lower);
        // PART=1 (upper) renders the empty placeholder since the tall model extends upward.
        ModelFile mirrorTall = royalDecorationModel("wall_mirror_tall");
        ModelFile empty = royalEmptyModel();
        getVariantBuilder(ModBlocks.ROYAL_WALL_MIRROR_TALL.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int part = state.getValue(RoyalWallMirrorTallBlock.PART);
            return ConfiguredModel.builder()
                    .modelFile(part == 0 ? mirrorTall : empty)
                    .rotationY(getYRotation(facing))
                    .build();
        });
    }

    private ModelFile royalDecorationModel(String name) {
        return existingModel(ROYAL, "block/decorations/" + name);
    }

    private void registerRoyalShelf() {
        ModelFile single = royalModel("shelf_single");
        ModelFile center = royalModel("shelf_center");
        ModelFile left = royalModel("shelf_left");
        ModelFile right = royalModel("shelf_right");

        getVariantBuilder(ModBlocks.ROYAL_SHELF.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            NordicShelfBlock.ConnectionType connection = state.getValue(NordicShelfBlock.CONNECTION);
            int yRot = getYRotation(facing);

            ModelFile model = switch (connection) {
                case CENTER -> center;
                case LEFT -> left;
                case RIGHT -> right;
                default -> single;
            };

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerRoyalCounter() {
        ModelFile single = royalModel("counter_single");
        ModelFile corner = royalModel("counter_corner");

        getVariantBuilder(ModBlocks.ROYAL_COUNTER.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            NordicCounterBlock.ConnectionType connection = state.getValue(NordicCounterBlock.CONNECTION);
            int yRot = getYRotation(facing);

            ModelFile model = connection == NordicCounterBlock.ConnectionType.CORNER ? corner : single;
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerRoyalTable() {
        ModelFile table = royalModel("table_small");

        getVariantBuilder(ModBlocks.ROYAL_TABLE.get()).forAllStates(state -> ConfiguredModel.builder()
                .modelFile(table)
                .build());
    }

    private void registerRoyalSofa() {
        ModelFile single = royalModel("sofa_single");
        ModelFile left = royalModel("sofa_left");
        ModelFile right = royalModel("sofa_right");
        ModelFile center = royalModel("sofa_center");
        ModelFile corner = royalModel("sofa_corner");

        getVariantBuilder(ModBlocks.ROYAL_SOFA.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            NordicSofaBlock.ConnectionType connection = state.getValue(NordicSofaBlock.CONNECTION);
            int yRot = getYRotation(facing);

            ModelFile model = switch (connection) {
                case LEFT -> left;
                case RIGHT -> right;
                case CENTER -> center;
                case CORNER -> corner;
                default -> single;
            };

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerRoyalBedSingle() {
        ModelFile bed = royalModel("bed_single");
        ModelFile empty = royalEmptyModel();

        getVariantBuilder(ModBlocks.ROYAL_BED_SINGLE.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            BedPart part = state.getValue(BlockStateProperties.BED_PART);
            int yRot = getYRotation(facing);

            ModelFile model = part == BedPart.FOOT ? bed : empty;
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerRoyalBedDouble() {
        ModelFile bed = royalModel("bed_double");
        ModelFile empty = royalEmptyModel();

        getVariantBuilder(ModBlocks.ROYAL_BED_DOUBLE.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int part = state.getValue(NordicBedDoubleBlock.PART);
            int yRot = getYRotation(facing);

            ModelFile model = part == 0 ? bed : empty;
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerRoyalDoorSingle() {
        // Royal 1.19.4 naming: door_single_right is the right-hinge model,
        // door_single_left is the left-hinge (mirrored) variant.
        ModelFile right = royalModel("door_single_right");
        ModelFile left = royalModel("door_single_left");
        ModelFile empty = royalEmptyModel();

        getVariantBuilder(ModBlocks.ROYAL_DOOR_SINGLE.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            DoorHingeSide hinge = state.getValue(BlockStateProperties.DOOR_HINGE);
            boolean open = state.getValue(BlockStateProperties.OPEN);
            int part = state.getValue(NordicDoorSingleBlock.PART);

            ModelFile model;
            int yRot;
            if (part != 0) {
                model = empty;
                yRot = getYRotation(facing);
            } else {
                model = selectDoorModel(hinge, open, left, right);
                yRot = doorYRotation(facing, hinge, open);
            }

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerRoyalDoorDouble() {
        ModelFile right = royalModel("door_double_right");
        ModelFile left = royalModel("door_double_left");
        ModelFile empty = royalEmptyModel();

        getVariantBuilder(ModBlocks.ROYAL_DOOR_DOUBLE.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            DoorHingeSide hinge = state.getValue(BlockStateProperties.DOOR_HINGE);
            boolean open = state.getValue(BlockStateProperties.OPEN);
            int part = state.getValue(NordicDoorDoubleBlock.PART);

            ModelFile model;
            int yRot;
            if (part != 0) {
                model = empty;
                yRot = getYRotation(facing);
            } else {
                model = selectDoorModel(hinge, open, left, right);
                yRot = doorYRotation(facing, hinge, open);
            }

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerRoyalOven() {
        ModelFile oven = royalModel("oven");

        getVariantBuilder(ModBlocks.ROYAL_OVEN.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int yRot = getYRotation(facing);

            return ConfiguredModel.builder()
                    .modelFile(oven)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void royalMultiBlockHorizontal(Block block, IntegerProperty partProp, String modelName) {
        ModelFile model = royalModel(modelName);
        ModelFile empty = royalEmptyModel();

        getVariantBuilder(block).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int part = state.getValue(partProp);
            int yRot = getYRotation(facing);

            return ConfiguredModel.builder()
                    .modelFile(part == 0 ? model : empty)
                    .rotationY(yRot)
                    .build();
        });
    }

    private ModelFile royalModel(String name) {
        return existingModel(ROYAL, "block/" + name);
    }

    private ModelFile royalEmptyModel() {
        return models().getBuilder(ROYAL + ":empty_multiblock_part")
                .texture("particle", ROYAL + ":block/particle");
    }

    private void royalBlockItem(Block block, String modelName) {
        blockItem(block, ROYAL, "block/" + modelName);
    }

    // ------------------------------------------------------------------------
    // Dunmer set helpers and registrations
    // ------------------------------------------------------------------------

    private void registerDunmer() {
        ResourceLocation dunmerWoolTex = ResourceLocation.fromNamespaceAndPath(DUNMER, "block/wool");
        ModelFile dunmerWoolModel = models().cubeAll(DUNMER + ":wool", dunmerWoolTex);
        ModelFile dunmerCarpetModel = models().carpet(DUNMER + ":carpet", dunmerWoolTex);
        simpleBlock(ModBlocks.DUNMER_WOOL.get(), dunmerWoolModel);
        simpleBlock(ModBlocks.DUNMER_CARPET.get(), dunmerCarpetModel);

        facingBlock(ModBlocks.DUNMER_WALL_LIGHT.get(), dunmerModel("wall_light"));
        dunmerMultiBlockHorizontal(ModBlocks.DUNMER_FLOOR_LIGHT.get(), NordicFloorLightBlock.PART, "floor_light");
        simpleBlock(ModBlocks.DUNMER_CHANDELIER_LIGHT.get(), dunmerModel("chandelier"));
        dunmerMultiBlockHorizontal(ModBlocks.DUNMER_CHAIR.get(), NordicChairBlock.PART, "chair");
        dunmerMultiBlockHorizontal(ModBlocks.DUNMER_BENCH.get(), NordicBenchBlock.PART, "bench");
        facingBlock(ModBlocks.DUNMER_STOOL.get(), dunmerModel("stool"));
        facingBlock(ModBlocks.DUNMER_CUSHION.get(), dunmerModel("cushion"));
        registerDunmerShelf();
        facingBlock(ModBlocks.DUNMER_DRAWER.get(), dunmerModel("drawer"));
        facingBlock(ModBlocks.DUNMER_LOCKBOX.get(), dunmerModel("lockbox"));
        dunmerMultiBlockHorizontal(ModBlocks.DUNMER_DESK_LEFT.get(), NordicDeskLeftBlock.PART, "desk_left");
        dunmerMultiBlockHorizontal(ModBlocks.DUNMER_DESK_RIGHT.get(), NordicDeskRightBlock.PART, "desk_right");
        dunmerMultiBlockHorizontal(ModBlocks.DUNMER_DRESSER.get(), NordicDresserBlock.PART, "dresser");
        registerDunmerCounter();
        dunmerMultiBlockHorizontal(ModBlocks.DUNMER_CHEST.get(), NordicChestBlock.PART, "chest");
        dunmerMultiBlockHorizontal(ModBlocks.DUNMER_WARDROBE_BOTTOM.get(), NordicWardrobeBottomBlock.PART, "wardrobe_bottom");
        dunmerMultiBlockHorizontal(ModBlocks.DUNMER_WARDROBE_TOP.get(), NordicWardrobeTopBlock.PART, "wardrobe_top");
        dunmerMultiBlockHorizontal(ModBlocks.DUNMER_BOOKSHELF.get(), NordicBookshelfBlock.PART, "bookshelf");
        facingBlock(ModBlocks.DUNMER_PAINTING_SMALL.get(), dunmerModel("painting_small"));
        dunmerMultiBlockHorizontal(ModBlocks.DUNMER_PAINTING_WIDE.get(), NordicPaintingWideBlock.PART, "painting_wide");

        registerDunmerTableSmall();
        dunmerMultiBlockHorizontal(ModBlocks.DUNMER_TABLE_WIDE.get(), NordicTableWideBlock.PART, "table_wide");
        dunmerMultiBlockHorizontal(ModBlocks.DUNMER_TABLE_LARGE.get(), NordicTableLargeBlock.PART, "table_large");

        registerDunmerSofa();
        registerDunmerBedSingle();
        registerDunmerBedDouble();
        registerDunmerDoorSingle();
        registerDunmerDoorDouble();
        registerDunmerOven();

        registerDunmerDecorations();
    }

    private void registerDunmerDecorations() {
        facingBlock(ModBlocks.DUNMER_POTTERY_0.get(), dunmerDecorationModel("pottery_0"));
        facingBlock(ModBlocks.DUNMER_POTTERY_1.get(), dunmerDecorationModel("pottery_1"));
    }

    private ModelFile dunmerDecorationModel(String name) {
        return existingModel(DUNMER, "block/decorations/" + name);
    }

    private void registerDunmerShelf() {
        ModelFile single = dunmerModel("shelf_single");
        ModelFile center = dunmerModel("shelf_center");
        ModelFile left = dunmerModel("shelf_left");
        ModelFile right = dunmerModel("shelf_right");

        getVariantBuilder(ModBlocks.DUNMER_SHELF.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            NordicShelfBlock.ConnectionType connection = state.getValue(NordicShelfBlock.CONNECTION);
            int yRot = getYRotation(facing);

            ModelFile model = switch (connection) {
                case CENTER -> center;
                case LEFT -> left;
                case RIGHT -> right;
                default -> single;
            };

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerDunmerCounter() {
        ModelFile single = dunmerModel("counter_single");
        ModelFile corner = dunmerModel("counter_corner");

        getVariantBuilder(ModBlocks.DUNMER_COUNTER.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            NordicCounterBlock.ConnectionType connection = state.getValue(NordicCounterBlock.CONNECTION);
            int yRot = getYRotation(facing);

            ModelFile model = connection == NordicCounterBlock.ConnectionType.CORNER ? corner : single;
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerDunmerTableSmall() {
        ModelFile table = dunmerModel("table_small");
        getVariantBuilder(ModBlocks.DUNMER_TABLE_SMALL.get()).forAllStates(state -> ConfiguredModel.builder()
                .modelFile(table)
                .build());
    }

    private void registerDunmerSofa() {
        ModelFile single = dunmerModel("sofa_single");
        ModelFile left = dunmerModel("sofa_left");
        ModelFile right = dunmerModel("sofa_right");
        ModelFile center = dunmerModel("sofa_center");
        ModelFile corner = dunmerModel("sofa_corner");

        getVariantBuilder(ModBlocks.DUNMER_SOFA.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            NordicSofaBlock.ConnectionType connection = state.getValue(NordicSofaBlock.CONNECTION);
            int yRot = getYRotation(facing);

            ModelFile model = switch (connection) {
                case LEFT -> left;
                case RIGHT -> right;
                case CENTER -> center;
                case CORNER -> corner;
                default -> single;
            };

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerDunmerBedSingle() {
        ModelFile bed = dunmerModel("bed_single");
        ModelFile empty = dunmerEmptyModel();

        getVariantBuilder(ModBlocks.DUNMER_BED_SINGLE.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            BedPart part = state.getValue(BlockStateProperties.BED_PART);
            int yRot = getYRotation(facing);

            ModelFile model = part == BedPart.FOOT ? bed : empty;
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerDunmerBedDouble() {
        ModelFile bed = dunmerModel("bed_double");
        ModelFile empty = dunmerEmptyModel();

        getVariantBuilder(ModBlocks.DUNMER_BED_DOUBLE.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int part = state.getValue(NordicBedDoubleBlock.PART);
            int yRot = getYRotation(facing);

            ModelFile model = part == 0 ? bed : empty;
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerDunmerDoorSingle() {
        ModelFile right = dunmerModel("door_single_right");
        ModelFile left = dunmerModel("door_single_left");
        ModelFile empty = dunmerEmptyModel();

        getVariantBuilder(ModBlocks.DUNMER_DOOR_SINGLE.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            DoorHingeSide hinge = state.getValue(BlockStateProperties.DOOR_HINGE);
            boolean open = state.getValue(BlockStateProperties.OPEN);
            int part = state.getValue(NordicDoorSingleBlock.PART);

            ModelFile model;
            int yRot;
            if (part != 0) {
                model = empty;
                yRot = getYRotation(facing);
            } else {
                model = selectDoorModel(hinge, open, left, right);
                yRot = doorYRotation(facing, hinge, open);
            }

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerDunmerDoorDouble() {
        ModelFile right = dunmerModel("door_double_right");
        ModelFile left = dunmerModel("door_double_left");
        ModelFile empty = dunmerEmptyModel();

        getVariantBuilder(ModBlocks.DUNMER_DOOR_DOUBLE.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            DoorHingeSide hinge = state.getValue(BlockStateProperties.DOOR_HINGE);
            boolean open = state.getValue(BlockStateProperties.OPEN);
            int part = state.getValue(NordicDoorDoubleBlock.PART);

            ModelFile model;
            int yRot;
            if (part != 0) {
                model = empty;
                yRot = getYRotation(facing);
            } else {
                model = selectDoorModel(hinge, open, left, right);
                yRot = doorYRotation(facing, hinge, open);
            }

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerDunmerOven() {
        ModelFile oven = dunmerModel("oven");

        getVariantBuilder(ModBlocks.DUNMER_OVEN.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int yRot = getYRotation(facing);

            return ConfiguredModel.builder()
                    .modelFile(oven)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void dunmerMultiBlockHorizontal(Block block, IntegerProperty partProp, String modelName) {
        ModelFile model = dunmerModel(modelName);
        ModelFile empty = dunmerEmptyModel();

        getVariantBuilder(block).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int part = state.getValue(partProp);
            int yRot = getYRotation(facing);

            return ConfiguredModel.builder()
                    .modelFile(part == 0 ? model : empty)
                    .rotationY(yRot)
                    .build();
        });
    }

    private ModelFile dunmerModel(String name) {
        return existingModel(DUNMER, "block/" + name);
    }

    private ModelFile dunmerEmptyModel() {
        return models().getBuilder(DUNMER + ":empty_multiblock_part")
                .texture("particle", DUNMER + ":block/particle");
    }

    private void dunmerBlockItem(Block block, String modelName) {
        blockItem(block, DUNMER, "block/" + modelName);
    }

    // ------------------------------------------------------------------------
    // Venthyr set helpers and registrations
    // ------------------------------------------------------------------------

    private void registerVenthyr() {
        ResourceLocation venthyrWoolTex = ResourceLocation.fromNamespaceAndPath(VENTHYR, "block/wool");
        ModelFile venthyrWoolModel = models().cubeAll(VENTHYR + ":wool", venthyrWoolTex);
        ModelFile venthyrCarpetModel = models().carpet(VENTHYR + ":carpet", venthyrWoolTex);
        simpleBlock(ModBlocks.VENTHYR_WOOL.get(), venthyrWoolModel);
        simpleBlock(ModBlocks.VENTHYR_CARPET.get(), venthyrCarpetModel);

        facingBlock(ModBlocks.VENTHYR_WALL_LIGHT.get(), venthyrModel("wall_light"));
        venthyrMultiBlockHorizontal(ModBlocks.VENTHYR_FLOOR_LIGHT.get(), NordicFloorLightBlock.PART, "floor_light");
        simpleBlock(ModBlocks.VENTHYR_CHANDELIER_LIGHT.get(), venthyrModel("chandelier"));
        venthyrMultiBlockHorizontal(ModBlocks.VENTHYR_CHAIR.get(), NordicChairBlock.PART, "chair");
        venthyrMultiBlockHorizontal(ModBlocks.VENTHYR_BENCH.get(), NordicBenchBlock.PART, "bench");
        facingBlock(ModBlocks.VENTHYR_STOOL.get(), venthyrModel("stool"));
        facingBlock(ModBlocks.VENTHYR_CUSHION.get(), venthyrModel("cushion"));
        registerVenthyrShelf();
        facingBlock(ModBlocks.VENTHYR_DRAWER.get(), venthyrModel("drawer"));
        facingBlock(ModBlocks.VENTHYR_LOCKBOX.get(), venthyrModel("lockbox"));
        venthyrMultiBlockHorizontal(ModBlocks.VENTHYR_DESK_LEFT.get(), NordicDeskLeftBlock.PART, "desk_left");
        venthyrMultiBlockHorizontal(ModBlocks.VENTHYR_DESK_RIGHT.get(), NordicDeskRightBlock.PART, "desk_right");
        venthyrMultiBlockHorizontal(ModBlocks.VENTHYR_DRESSER.get(), NordicDresserBlock.PART, "dresser");
        registerVenthyrCounter();
        venthyrMultiBlockHorizontal(ModBlocks.VENTHYR_CHEST.get(), NordicChestBlock.PART, "chest");
        venthyrMultiBlockHorizontal(ModBlocks.VENTHYR_WARDROBE_BOTTOM.get(), NordicWardrobeBottomBlock.PART, "wardrobe_bottom");
        venthyrMultiBlockHorizontal(ModBlocks.VENTHYR_WARDROBE_TOP.get(), NordicWardrobeTopBlock.PART, "wardrobe_top");
        venthyrMultiBlockHorizontal(ModBlocks.VENTHYR_BOOKSHELF.get(), NordicBookshelfBlock.PART, "bookshelf");
        facingBlock(ModBlocks.VENTHYR_PAINTING_SMALL.get(), venthyrModel("painting_small"));
        venthyrMultiBlockHorizontal(ModBlocks.VENTHYR_PAINTING_WIDE.get(), NordicPaintingWideBlock.PART, "painting_wide");

        // Tables: regular + fancy variants
        registerVenthyrTableSmall();
        registerVenthyrTableSmallFancy();
        venthyrMultiBlockHorizontal(ModBlocks.VENTHYR_TABLE_WIDE.get(), NordicTableWideBlock.PART, "table_wide");
        venthyrMultiBlockHorizontal(ModBlocks.VENTHYR_TABLE_WIDE_FANCY.get(), NordicTableWideBlock.PART, "table_wide_fancy");
        venthyrMultiBlockHorizontal(ModBlocks.VENTHYR_TABLE_LARGE.get(), NordicTableLargeBlock.PART, "table_large");
        venthyrMultiBlockHorizontal(ModBlocks.VENTHYR_TABLE_LARGE_FANCY.get(), NordicTableLargeBlock.PART, "table_large_fancy");

        registerVenthyrSofa();
        registerVenthyrBedSingle();
        registerVenthyrBedDouble();
        registerVenthyrDoorSingle();
        registerVenthyrDoorDouble();
        registerVenthyrOven();

        registerVenthyrDecorations();
    }

    private void registerVenthyrDecorations() {
        facingBlock(ModBlocks.VENTHYR_FOOD_0.get(), venthyrDecorationModel("food_0"));
        facingBlock(ModBlocks.VENTHYR_FOOD_1.get(), venthyrDecorationModel("food_1"));

        // Tomes: 3 stack variants
        ModelFile tomes0 = venthyrDecorationModel("tomes_0");
        ModelFile tomes1 = venthyrDecorationModel("tomes_1");
        ModelFile tomes2 = venthyrDecorationModel("tomes_2");
        getVariantBuilder(ModBlocks.VENTHYR_TOMES.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int count = state.getValue(VenthyrTomesBlock.TOMES);
            ModelFile model = switch (count) {
                case 2 -> tomes2;
                case 1 -> tomes1;
                default -> tomes0;
            };
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(getYRotation(facing))
                    .build();
        });

        // Tea cups: 3 stack variants
        ModelFile teaCups0 = venthyrDecorationModel("tea_cups_0");
        ModelFile teaCups1 = venthyrDecorationModel("tea_cups_1");
        ModelFile teaCups2 = venthyrDecorationModel("tea_cups_2");
        getVariantBuilder(ModBlocks.VENTHYR_TEA_CUPS.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int count = state.getValue(VenthyrTeaCupsBlock.TEA_CUPS);
            ModelFile model = switch (count) {
                case 2 -> teaCups2;
                case 1 -> teaCups1;
                default -> teaCups0;
            };
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(getYRotation(facing))
                    .build();
        });

        // Platter: 16 stack variants (platter_0..platter_15)
        ModelFile[] platterModels = new ModelFile[16];
        for (int i = 0; i < 16; i++) {
            platterModels[i] = venthyrDecorationModel("platter_" + i);
        }
        getVariantBuilder(ModBlocks.VENTHYR_PLATTER.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int count = state.getValue(RoyalPlatterBlock.PLATTER);
            return ConfiguredModel.builder()
                    .modelFile(platterModels[count % 16])
                    .rotationY(getYRotation(facing))
                    .build();
        });

        // Tea set: multi-block 2x1x1; origin (PART=0) renders full model, PART=1 renders empty.
        ModelFile teaSet = venthyrDecorationModel("tea_set");
        ModelFile teaSetEmpty = venthyrEmptyModel();
        getVariantBuilder(ModBlocks.VENTHYR_TEA_SET.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int part = state.getValue(VenthyrTeaSetBlock.PART);
            return ConfiguredModel.builder()
                    .modelFile(part == 0 ? teaSet : teaSetEmpty)
                    .rotationY(getYRotation(facing))
                    .build();
        });

        // Banner: multi-block 1x2x1 vertical; PART=0 (bottom) renders full banner model (extends to Y=32), PART=1 empty.
        ModelFile banner = venthyrDecorationModel("banner");
        ModelFile bannerEmpty = venthyrEmptyModel();
        getVariantBuilder(ModBlocks.VENTHYR_BANNER.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int part = state.getValue(VenthyrBannerBlock.PART);
            return ConfiguredModel.builder()
                    .modelFile(part == 0 ? banner : bannerEmpty)
                    .rotationY(getYRotation(facing))
                    .build();
        });

        // Candles: single model, LIT state only affects light level + particles (no model swap).
        ModelFile candles = venthyrDecorationModel("candles");
        getVariantBuilder(ModBlocks.VENTHYR_CANDLES.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            return ConfiguredModel.builder()
                    .modelFile(candles)
                    .rotationY(getYRotation(facing))
                    .build();
        });

        // Chalices: 3 stack variants (chalices_0..chalices_2)
        ModelFile chalices0 = venthyrDecorationModel("chalices_0");
        ModelFile chalices1 = venthyrDecorationModel("chalices_1");
        ModelFile chalices2 = venthyrDecorationModel("chalices_2");
        getVariantBuilder(ModBlocks.VENTHYR_CHALICES.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int count = state.getValue(VenthyrChalicesBlock.CHALICES);
            ModelFile model = switch (count) {
                case 2 -> chalices2;
                case 1 -> chalices1;
                default -> chalices0;
            };
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(getYRotation(facing))
                    .build();
        });

        // Widow Bloom: BE-rendered (BlockEntityRenderer draws the Java model).
        // Blockstate points at a particle-only model so break particles use the decoration texture.
        ModelFile widowBloomModel = models().getBuilder(VENTHYR + ":block/decorations/widow_bloom")
                .texture("particle", VENTHYR + ":block/decorations/widow_bloom");
        getVariantBuilder(ModBlocks.VENTHYR_WIDOW_BLOOM.get())
                .forAllStates(state -> ConfiguredModel.builder().modelFile(widowBloomModel).build());
    }

    private ModelFile venthyrDecorationModel(String name) {
        return existingModel(VENTHYR, "block/decorations/" + name);
    }

    private void registerVenthyrShelf() {
        ModelFile single = venthyrModel("shelf_single");
        ModelFile center = venthyrModel("shelf_center");
        ModelFile left = venthyrModel("shelf_left");
        ModelFile right = venthyrModel("shelf_right");

        getVariantBuilder(ModBlocks.VENTHYR_SHELF.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            NordicShelfBlock.ConnectionType connection = state.getValue(NordicShelfBlock.CONNECTION);
            int yRot = getYRotation(facing);

            ModelFile model = switch (connection) {
                case CENTER -> center;
                case LEFT -> left;
                case RIGHT -> right;
                default -> single;
            };

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerVenthyrCounter() {
        ModelFile single = venthyrModel("counter_single");
        ModelFile corner = venthyrModel("counter_corner");

        getVariantBuilder(ModBlocks.VENTHYR_COUNTER.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            NordicCounterBlock.ConnectionType connection = state.getValue(NordicCounterBlock.CONNECTION);
            int yRot = getYRotation(facing);

            ModelFile model = connection == NordicCounterBlock.ConnectionType.CORNER ? corner : single;
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerVenthyrTableSmall() {
        ModelFile table = venthyrModel("table_small");
        getVariantBuilder(ModBlocks.VENTHYR_TABLE_SMALL.get()).forAllStates(state -> ConfiguredModel.builder()
                .modelFile(table)
                .build());
    }

    private void registerVenthyrTableSmallFancy() {
        ModelFile table = venthyrModel("table_small_fancy");
        getVariantBuilder(ModBlocks.VENTHYR_TABLE_SMALL_FANCY.get()).forAllStates(state -> ConfiguredModel.builder()
                .modelFile(table)
                .build());
    }

    private void registerVenthyrSofa() {
        ModelFile single = venthyrModel("sofa_single");
        ModelFile left = venthyrModel("sofa_left");
        ModelFile right = venthyrModel("sofa_right");
        ModelFile center = venthyrModel("sofa_center");
        ModelFile corner = venthyrModel("sofa_corner");

        getVariantBuilder(ModBlocks.VENTHYR_SOFA.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            NordicSofaBlock.ConnectionType connection = state.getValue(NordicSofaBlock.CONNECTION);
            int yRot = getYRotation(facing);

            ModelFile model = switch (connection) {
                case LEFT -> left;
                case RIGHT -> right;
                case CENTER -> center;
                case CORNER -> corner;
                default -> single;
            };

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerVenthyrBedSingle() {
        ModelFile bed = venthyrModel("bed_single");
        ModelFile empty = venthyrEmptyModel();

        getVariantBuilder(ModBlocks.VENTHYR_BED_SINGLE.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            BedPart part = state.getValue(BlockStateProperties.BED_PART);
            int yRot = getYRotation(facing);

            ModelFile model = part == BedPart.FOOT ? bed : empty;
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerVenthyrBedDouble() {
        ModelFile bed = venthyrModel("bed_double");
        ModelFile empty = venthyrEmptyModel();

        getVariantBuilder(ModBlocks.VENTHYR_BED_DOUBLE.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int part = state.getValue(NordicBedDoubleBlock.PART);
            int yRot = getYRotation(facing);

            ModelFile model = part == 0 ? bed : empty;
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerVenthyrDoorSingle() {
        ModelFile right = venthyrModel("door_single_right");
        ModelFile left = venthyrModel("door_single_left");
        ModelFile empty = venthyrEmptyModel();

        getVariantBuilder(ModBlocks.VENTHYR_DOOR_SINGLE.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            DoorHingeSide hinge = state.getValue(BlockStateProperties.DOOR_HINGE);
            boolean open = state.getValue(BlockStateProperties.OPEN);
            int part = state.getValue(NordicDoorSingleBlock.PART);

            ModelFile model;
            int yRot;
            if (part != 0) {
                model = empty;
                yRot = getYRotation(facing);
            } else {
                model = selectDoorModel(hinge, open, left, right);
                yRot = doorYRotation(facing, hinge, open);
            }

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerVenthyrDoorDouble() {
        ModelFile right = venthyrModel("door_double_right");
        ModelFile left = venthyrModel("door_double_left");
        ModelFile empty = venthyrEmptyModel();

        getVariantBuilder(ModBlocks.VENTHYR_DOOR_DOUBLE.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            DoorHingeSide hinge = state.getValue(BlockStateProperties.DOOR_HINGE);
            boolean open = state.getValue(BlockStateProperties.OPEN);
            int part = state.getValue(NordicDoorDoubleBlock.PART);

            ModelFile model;
            int yRot;
            if (part != 0) {
                model = empty;
                yRot = getYRotation(facing);
            } else {
                model = selectDoorModel(hinge, open, left, right);
                yRot = doorYRotation(facing, hinge, open);
            }

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerVenthyrOven() {
        ModelFile oven = venthyrModel("oven");

        getVariantBuilder(ModBlocks.VENTHYR_OVEN.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int yRot = getYRotation(facing);

            return ConfiguredModel.builder()
                    .modelFile(oven)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void venthyrMultiBlockHorizontal(Block block, IntegerProperty partProp, String modelName) {
        ModelFile model = venthyrModel(modelName);
        ModelFile empty = venthyrEmptyModel();

        getVariantBuilder(block).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int part = state.getValue(partProp);
            int yRot = getYRotation(facing);

            return ConfiguredModel.builder()
                    .modelFile(part == 0 ? model : empty)
                    .rotationY(yRot)
                    .build();
        });
    }

    private ModelFile venthyrModel(String name) {
        return existingModel(VENTHYR, "block/" + name);
    }

    private ModelFile venthyrEmptyModel() {
        return models().getBuilder(VENTHYR + ":empty_multiblock_part")
                .texture("particle", VENTHYR + ":block/particle");
    }

    private void venthyrBlockItem(Block block, String modelName) {
        blockItem(block, VENTHYR, "block/" + modelName);
    }

    // ------------------------------------------------------------------------
    // Necrolord set helpers and registrations
    // ------------------------------------------------------------------------

    private void registerNecrolord() {
        ResourceLocation necrolordWoolTex = ResourceLocation.fromNamespaceAndPath(NECROLORD, "block/wool");
        ModelFile necrolordWoolModel = models().cubeAll(NECROLORD + ":wool", necrolordWoolTex);
        ModelFile necrolordCarpetModel = models().carpet(NECROLORD + ":carpet", necrolordWoolTex);
        simpleBlock(ModBlocks.NECROLORD_WOOL.get(), necrolordWoolModel);
        simpleBlock(ModBlocks.NECROLORD_CARPET.get(), necrolordCarpetModel);

        facingBlock(ModBlocks.NECROLORD_WALL_LIGHT.get(), necrolordModel("wall_light"));
        necrolordMultiBlockHorizontal(ModBlocks.NECROLORD_FLOOR_LIGHT.get(), NordicFloorLightBlock.PART, "floor_light");
        simpleBlock(ModBlocks.NECROLORD_CHANDELIER_LIGHT.get(), necrolordModel("chandelier"));
        necrolordMultiBlockHorizontal(ModBlocks.NECROLORD_CHAIR.get(), NordicChairBlock.PART, "chair");
        necrolordMultiBlockHorizontal(ModBlocks.NECROLORD_BENCH.get(), NordicBenchBlock.PART, "bench");
        facingBlock(ModBlocks.NECROLORD_STOOL.get(), necrolordModel("stool"));
        facingBlock(ModBlocks.NECROLORD_CUSHION.get(), necrolordModel("cushion"));
        registerNecrolordShelf();
        facingBlock(ModBlocks.NECROLORD_DRAWER.get(), necrolordModel("drawer"));
        facingBlock(ModBlocks.NECROLORD_LOCKBOX.get(), necrolordModel("lockbox"));
        necrolordMultiBlockHorizontal(ModBlocks.NECROLORD_DESK_LEFT.get(), NordicDeskLeftBlock.PART, "desk_left");
        necrolordMultiBlockHorizontal(ModBlocks.NECROLORD_DESK_RIGHT.get(), NordicDeskRightBlock.PART, "desk_right");
        necrolordMultiBlockHorizontal(ModBlocks.NECROLORD_DRESSER.get(), NordicDresserBlock.PART, "dresser");
        registerNecrolordCounter();
        necrolordMultiBlockHorizontal(ModBlocks.NECROLORD_CHEST.get(), NordicChestBlock.PART, "chest");
        necrolordMultiBlockHorizontal(ModBlocks.NECROLORD_WARDROBE_BOTTOM.get(), NordicWardrobeBottomBlock.PART, "wardrobe_bottom");
        necrolordMultiBlockHorizontal(ModBlocks.NECROLORD_WARDROBE_TOP.get(), NordicWardrobeTopBlock.PART, "wardrobe_top");
        necrolordMultiBlockHorizontal(ModBlocks.NECROLORD_BOOKSHELF.get(), NordicBookshelfBlock.PART, "bookshelf");
        facingBlock(ModBlocks.NECROLORD_PAINTING_SMALL.get(), necrolordModel("painting_small"));
        necrolordMultiBlockHorizontal(ModBlocks.NECROLORD_PAINTING_WIDE.get(), NordicPaintingWideBlock.PART, "painting_wide");

        registerNecrolordTableSmall();
        necrolordMultiBlockHorizontal(ModBlocks.NECROLORD_TABLE_WIDE.get(), NordicTableWideBlock.PART, "table_wide");
        necrolordMultiBlockHorizontal(ModBlocks.NECROLORD_TABLE_LARGE.get(), NordicTableLargeBlock.PART, "table_large");

        registerNecrolordSofa();
        registerNecrolordBedSingle();
        registerNecrolordBedDouble();
        registerNecrolordDoorSingle();
        registerNecrolordDoorDouble();
        registerNecrolordOven();

        registerNecrolordDecorations();
    }

    private void registerNecrolordDecorations() {
        facingBlock(ModBlocks.NECROLORD_CANDELABRA.get(), necrolordDecorationModel("candelabra"));
    }

    private ModelFile necrolordDecorationModel(String name) {
        return existingModel(NECROLORD, "block/decorations/" + name);
    }

    private void registerNecrolordShelf() {
        ModelFile single = necrolordModel("shelf_single");
        ModelFile center = necrolordModel("shelf_center");
        ModelFile left = necrolordModel("shelf_left");
        ModelFile right = necrolordModel("shelf_right");

        getVariantBuilder(ModBlocks.NECROLORD_SHELF.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            NordicShelfBlock.ConnectionType connection = state.getValue(NordicShelfBlock.CONNECTION);
            int yRot = getYRotation(facing);

            ModelFile model = switch (connection) {
                case CENTER -> center;
                case LEFT -> left;
                case RIGHT -> right;
                default -> single;
            };

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerNecrolordCounter() {
        ModelFile single = necrolordModel("counter_single");
        ModelFile corner = necrolordModel("counter_corner");

        getVariantBuilder(ModBlocks.NECROLORD_COUNTER.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            NordicCounterBlock.ConnectionType connection = state.getValue(NordicCounterBlock.CONNECTION);
            int yRot = getYRotation(facing);

            ModelFile model = connection == NordicCounterBlock.ConnectionType.CORNER ? corner : single;
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerNecrolordTableSmall() {
        ModelFile table = necrolordModel("table_small");
        getVariantBuilder(ModBlocks.NECROLORD_TABLE_SMALL.get()).forAllStates(state -> ConfiguredModel.builder()
                .modelFile(table)
                .build());
    }

    private void registerNecrolordSofa() {
        ModelFile single = necrolordModel("sofa_single");
        ModelFile left = necrolordModel("sofa_left");
        ModelFile right = necrolordModel("sofa_right");
        ModelFile center = necrolordModel("sofa_center");
        ModelFile corner = necrolordModel("sofa_corner");

        getVariantBuilder(ModBlocks.NECROLORD_SOFA.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            NordicSofaBlock.ConnectionType connection = state.getValue(NordicSofaBlock.CONNECTION);
            int yRot = getYRotation(facing);

            ModelFile model = switch (connection) {
                case LEFT -> left;
                case RIGHT -> right;
                case CENTER -> center;
                case CORNER -> corner;
                default -> single;
            };

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerNecrolordBedSingle() {
        ModelFile bed = necrolordModel("bed_single");
        ModelFile empty = necrolordEmptyModel();

        getVariantBuilder(ModBlocks.NECROLORD_BED_SINGLE.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            BedPart part = state.getValue(BlockStateProperties.BED_PART);
            int yRot = getYRotation(facing);

            ModelFile model = part == BedPart.FOOT ? bed : empty;
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerNecrolordBedDouble() {
        ModelFile bed = necrolordModel("bed_double");
        ModelFile empty = necrolordEmptyModel();

        getVariantBuilder(ModBlocks.NECROLORD_BED_DOUBLE.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int part = state.getValue(NordicBedDoubleBlock.PART);
            int yRot = getYRotation(facing);

            ModelFile model = part == 0 ? bed : empty;
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerNecrolordDoorSingle() {
        ModelFile right = necrolordModel("door_single_right");
        ModelFile left = necrolordModel("door_single_left");
        ModelFile empty = necrolordEmptyModel();

        getVariantBuilder(ModBlocks.NECROLORD_DOOR_SINGLE.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            DoorHingeSide hinge = state.getValue(BlockStateProperties.DOOR_HINGE);
            boolean open = state.getValue(BlockStateProperties.OPEN);
            int part = state.getValue(NordicDoorSingleBlock.PART);

            ModelFile model;
            int yRot;
            if (part != 0) {
                model = empty;
                yRot = getYRotation(facing);
            } else {
                model = selectDoorModel(hinge, open, left, right);
                yRot = doorYRotation(facing, hinge, open);
            }

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerNecrolordDoorDouble() {
        ModelFile right = necrolordModel("door_double_right");
        ModelFile left = necrolordModel("door_double_left");
        ModelFile empty = necrolordEmptyModel();

        getVariantBuilder(ModBlocks.NECROLORD_DOOR_DOUBLE.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            DoorHingeSide hinge = state.getValue(BlockStateProperties.DOOR_HINGE);
            boolean open = state.getValue(BlockStateProperties.OPEN);
            int part = state.getValue(NordicDoorDoubleBlock.PART);

            ModelFile model;
            int yRot;
            if (part != 0) {
                model = empty;
                yRot = getYRotation(facing);
            } else {
                model = selectDoorModel(hinge, open, left, right);
                yRot = doorYRotation(facing, hinge, open);
            }

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerNecrolordOven() {
        ModelFile oven = necrolordModel("oven");

        getVariantBuilder(ModBlocks.NECROLORD_OVEN.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int yRot = getYRotation(facing);

            return ConfiguredModel.builder()
                    .modelFile(oven)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void necrolordMultiBlockHorizontal(Block block, IntegerProperty partProp, String modelName) {
        ModelFile model = necrolordModel(modelName);
        ModelFile empty = necrolordEmptyModel();

        getVariantBuilder(block).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int part = state.getValue(partProp);
            int yRot = getYRotation(facing);

            return ConfiguredModel.builder()
                    .modelFile(part == 0 ? model : empty)
                    .rotationY(yRot)
                    .build();
        });
    }

    private ModelFile necrolordModel(String name) {
        return existingModel(NECROLORD, "block/" + name);
    }

    private ModelFile necrolordEmptyModel() {
        return models().getBuilder(NECROLORD + ":empty_multiblock_part")
                .texture("particle", NECROLORD + ":block/particle");
    }

    private void necrolordBlockItem(Block block, String modelName) {
        blockItem(block, NECROLORD, "block/" + modelName);
    }

    // ------------------------------------------------------------------------
    // Bone-Skeleton set — 1.19.4 parity. Reuses Nordic block classes.
    // The cushion-class block is registered as "skull" for this set (legacy
    // `fantasyfurniture:bone/skeleton/skull`).
    // ------------------------------------------------------------------------

    private void registerBoneSkeleton() {
        ResourceLocation boneSkeletonWoolTex = ResourceLocation.fromNamespaceAndPath(BONE_SKELETON, "block/wool");
        ModelFile boneSkeletonWoolModel = models().cubeAll(BONE_SKELETON + ":wool", boneSkeletonWoolTex);
        ModelFile boneSkeletonCarpetModel = models().carpet(BONE_SKELETON + ":carpet", boneSkeletonWoolTex);
        simpleBlock(ModBlocks.BONE_SKELETON_WOOL.get(), boneSkeletonWoolModel);
        simpleBlock(ModBlocks.BONE_SKELETON_CARPET.get(), boneSkeletonCarpetModel);

        facingBlock(ModBlocks.BONE_SKELETON_WALL_LIGHT.get(), boneSkeletonModel("wall_light"));
        boneSkeletonMultiBlockHorizontal(ModBlocks.BONE_SKELETON_FLOOR_LIGHT.get(), NordicFloorLightBlock.PART, "floor_light");
        simpleBlock(ModBlocks.BONE_SKELETON_CHANDELIER_LIGHT.get(), boneSkeletonModel("chandelier"));
        boneSkeletonMultiBlockHorizontal(ModBlocks.BONE_SKELETON_CHAIR.get(), NordicChairBlock.PART, "chair");
        boneSkeletonMultiBlockHorizontal(ModBlocks.BONE_SKELETON_BENCH.get(), NordicBenchBlock.PART, "bench");
        facingBlock(ModBlocks.BONE_SKELETON_STOOL.get(), boneSkeletonModel("stool"));
        facingBlock(ModBlocks.BONE_SKELETON_SKULL.get(), boneSkeletonModel("skull"));
        registerBoneSkeletonShelf();
        facingBlock(ModBlocks.BONE_SKELETON_DRAWER.get(), boneSkeletonModel("drawer"));
        facingBlock(ModBlocks.BONE_SKELETON_LOCKBOX.get(), boneSkeletonModel("lockbox"));
        boneSkeletonMultiBlockHorizontal(ModBlocks.BONE_SKELETON_DESK_LEFT.get(), NordicDeskLeftBlock.PART, "desk_left");
        boneSkeletonMultiBlockHorizontal(ModBlocks.BONE_SKELETON_DESK_RIGHT.get(), NordicDeskRightBlock.PART, "desk_right");
        boneSkeletonMultiBlockHorizontal(ModBlocks.BONE_SKELETON_DRESSER.get(), NordicDresserBlock.PART, "dresser");
        registerBoneSkeletonCounter();
        boneSkeletonMultiBlockHorizontal(ModBlocks.BONE_SKELETON_CHEST.get(), NordicChestBlock.PART, "chest");
        boneSkeletonMultiBlockHorizontal(ModBlocks.BONE_SKELETON_WARDROBE_BOTTOM.get(), NordicWardrobeBottomBlock.PART, "wardrobe_bottom");
        boneSkeletonMultiBlockHorizontal(ModBlocks.BONE_SKELETON_WARDROBE_TOP.get(), NordicWardrobeTopBlock.PART, "wardrobe_top");
        boneSkeletonMultiBlockHorizontal(ModBlocks.BONE_SKELETON_BOOKSHELF.get(), NordicBookshelfBlock.PART, "bookshelf");
        facingBlock(ModBlocks.BONE_SKELETON_PAINTING_SMALL.get(), boneSkeletonModel("painting_small"));
        boneSkeletonMultiBlockHorizontal(ModBlocks.BONE_SKELETON_PAINTING_WIDE.get(), NordicPaintingWideBlock.PART, "painting_wide");

        registerBoneSkeletonTableSmall();
        boneSkeletonMultiBlockHorizontal(ModBlocks.BONE_SKELETON_TABLE_WIDE.get(), NordicTableWideBlock.PART, "table_wide");
        boneSkeletonMultiBlockHorizontal(ModBlocks.BONE_SKELETON_TABLE_LARGE.get(), NordicTableLargeBlock.PART, "table_large");

        registerBoneSkeletonSofa();
        registerBoneSkeletonBedSingle();
        registerBoneSkeletonBedDouble();
        registerBoneSkeletonDoorSingle();
        registerBoneSkeletonDoorDouble();
        registerBoneSkeletonOven();
        registerBoneSkeletonDecorations();
    }

    private void registerBoneSkeletonDecorations() {
        ModelFile pile = boneSkeletonDecorationModel("pile");
        getVariantBuilder(ModBlocks.BONE_SKELETON_PILE.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            return ConfiguredModel.builder()
                    .modelFile(pile)
                    .rotationY(getYRotation(facing))
                    .build();
        });

        ModelFile chalices0 = boneSkeletonDecorationModel("chalices_0");
        ModelFile chalices1 = boneSkeletonDecorationModel("chalices_1");
        ModelFile chalices2 = boneSkeletonDecorationModel("chalices_2");
        getVariantBuilder(ModBlocks.BONE_SKELETON_CHALICES.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int count = state.getValue(BoneChalicesBlock.CHALICES);
            ModelFile model = switch (count) {
                case 2 -> chalices2;
                case 1 -> chalices1;
                default -> chalices0;
            };
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(getYRotation(facing))
                    .build();
        });

        // Skull Blossoms: BE-rendered (BlockEntityRenderer draws the Java model).
        // Particle-only blockstate model so break particles use the decoration texture.
        ModelFile skullBlossomsSkeletonModel = models().getBuilder(BONE_SKELETON + ":block/decorations/skull_blossoms")
                .texture("particle", BONE_SKELETON + ":block/decorations/skull_blossoms");
        getVariantBuilder(ModBlocks.BONE_SKELETON_SKULL_BLOSSOMS.get())
                .forAllStates(state -> ConfiguredModel.builder().modelFile(skullBlossomsSkeletonModel).build());
    }

    private ModelFile boneSkeletonDecorationModel(String name) {
        return existingModel(BONE_SKELETON, "block/decorations/" + name);
    }

    private void registerBoneSkeletonShelf() {
        ModelFile single = boneSkeletonModel("shelf_single");
        ModelFile center = boneSkeletonModel("shelf_center");
        ModelFile left = boneSkeletonModel("shelf_left");
        ModelFile right = boneSkeletonModel("shelf_right");

        getVariantBuilder(ModBlocks.BONE_SKELETON_SHELF.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            NordicShelfBlock.ConnectionType connection = state.getValue(NordicShelfBlock.CONNECTION);
            int yRot = getYRotation(facing);

            ModelFile model = switch (connection) {
                case CENTER -> center;
                case LEFT -> left;
                case RIGHT -> right;
                default -> single;
            };

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerBoneSkeletonCounter() {
        ModelFile single = boneSkeletonModel("counter_single");
        ModelFile corner = boneSkeletonModel("counter_corner");

        getVariantBuilder(ModBlocks.BONE_SKELETON_COUNTER.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            NordicCounterBlock.ConnectionType connection = state.getValue(NordicCounterBlock.CONNECTION);
            int yRot = getYRotation(facing);

            ModelFile model = connection == NordicCounterBlock.ConnectionType.CORNER ? corner : single;
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerBoneSkeletonTableSmall() {
        ModelFile table = boneSkeletonModel("table_small");
        getVariantBuilder(ModBlocks.BONE_SKELETON_TABLE_SMALL.get()).forAllStates(state -> ConfiguredModel.builder()
                .modelFile(table)
                .build());
    }

    private void registerBoneSkeletonSofa() {
        ModelFile single = boneSkeletonModel("sofa_single");
        ModelFile left = boneSkeletonModel("sofa_left");
        ModelFile right = boneSkeletonModel("sofa_right");
        ModelFile center = boneSkeletonModel("sofa_center");
        ModelFile corner = boneSkeletonModel("sofa_corner");

        getVariantBuilder(ModBlocks.BONE_SKELETON_SOFA.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            NordicSofaBlock.ConnectionType connection = state.getValue(NordicSofaBlock.CONNECTION);
            int yRot = getYRotation(facing);

            ModelFile model = switch (connection) {
                case LEFT -> left;
                case RIGHT -> right;
                case CENTER -> center;
                case CORNER -> corner;
                default -> single;
            };

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerBoneSkeletonBedSingle() {
        ModelFile bed = boneSkeletonModel("bed_single");
        ModelFile empty = boneSkeletonEmptyModel();

        getVariantBuilder(ModBlocks.BONE_SKELETON_BED_SINGLE.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            BedPart part = state.getValue(BlockStateProperties.BED_PART);
            int yRot = getYRotation(facing);

            ModelFile model = part == BedPart.FOOT ? bed : empty;
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerBoneSkeletonBedDouble() {
        ModelFile bed = boneSkeletonModel("bed_double");
        ModelFile empty = boneSkeletonEmptyModel();

        getVariantBuilder(ModBlocks.BONE_SKELETON_BED_DOUBLE.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int part = state.getValue(NordicBedDoubleBlock.PART);
            int yRot = getYRotation(facing);

            ModelFile model = part == 0 ? bed : empty;
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerBoneSkeletonDoorSingle() {
        ModelFile right = boneSkeletonModel("door_single_right");
        ModelFile left = boneSkeletonModel("door_single_left");
        ModelFile empty = boneSkeletonEmptyModel();

        getVariantBuilder(ModBlocks.BONE_SKELETON_DOOR_SINGLE.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            DoorHingeSide hinge = state.getValue(BlockStateProperties.DOOR_HINGE);
            boolean open = state.getValue(BlockStateProperties.OPEN);
            int part = state.getValue(NordicDoorSingleBlock.PART);

            ModelFile model;
            int yRot;
            if (part != 0) {
                model = empty;
                yRot = getYRotation(facing);
            } else {
                model = selectDoorModel(hinge, open, left, right);
                yRot = doorYRotation(facing, hinge, open);
            }

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerBoneSkeletonDoorDouble() {
        ModelFile right = boneSkeletonModel("door_double_right");
        ModelFile left = boneSkeletonModel("door_double_left");
        ModelFile empty = boneSkeletonEmptyModel();

        getVariantBuilder(ModBlocks.BONE_SKELETON_DOOR_DOUBLE.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            DoorHingeSide hinge = state.getValue(BlockStateProperties.DOOR_HINGE);
            boolean open = state.getValue(BlockStateProperties.OPEN);
            int part = state.getValue(NordicDoorDoubleBlock.PART);

            ModelFile model;
            int yRot;
            if (part != 0) {
                model = empty;
                yRot = getYRotation(facing);
            } else {
                model = selectDoorModel(hinge, open, left, right);
                yRot = doorYRotation(facing, hinge, open);
            }

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerBoneSkeletonOven() {
        ModelFile oven = boneSkeletonModel("oven");

        getVariantBuilder(ModBlocks.BONE_SKELETON_OVEN.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int yRot = getYRotation(facing);

            return ConfiguredModel.builder()
                    .modelFile(oven)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void boneSkeletonMultiBlockHorizontal(Block block, IntegerProperty partProp, String modelName) {
        ModelFile model = boneSkeletonModel(modelName);
        ModelFile empty = boneSkeletonEmptyModel();

        getVariantBuilder(block).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int part = state.getValue(partProp);
            int yRot = getYRotation(facing);

            return ConfiguredModel.builder()
                    .modelFile(part == 0 ? model : empty)
                    .rotationY(yRot)
                    .build();
        });
    }

    private ModelFile boneSkeletonModel(String name) {
        return existingModel(BONE_SKELETON, "block/" + name);
    }

    private ModelFile boneSkeletonEmptyModel() {
        return models().getBuilder(BONE_SKELETON + ":empty_multiblock_part")
                .texture("particle", BONE_SKELETON + ":block/particle");
    }

    private void boneSkeletonBlockItem(Block block, String modelName) {
        blockItem(block, BONE_SKELETON, "block/" + modelName);
    }

    private void registerBoneWither() {
        ResourceLocation boneWitherWoolTex = ResourceLocation.fromNamespaceAndPath(BONE_WITHER, "block/wool");
        ModelFile boneWitherWoolModel = models().cubeAll(BONE_WITHER + ":wool", boneWitherWoolTex);
        ModelFile boneWitherCarpetModel = models().carpet(BONE_WITHER + ":carpet", boneWitherWoolTex);
        simpleBlock(ModBlocks.BONE_WITHER_WOOL.get(), boneWitherWoolModel);
        simpleBlock(ModBlocks.BONE_WITHER_CARPET.get(), boneWitherCarpetModel);

        facingBlock(ModBlocks.BONE_WITHER_WALL_LIGHT.get(), boneWitherModel("wall_light"));
        boneWitherMultiBlockHorizontal(ModBlocks.BONE_WITHER_FLOOR_LIGHT.get(), NordicFloorLightBlock.PART, "floor_light");
        simpleBlock(ModBlocks.BONE_WITHER_CHANDELIER_LIGHT.get(), boneWitherModel("chandelier"));
        boneWitherMultiBlockHorizontal(ModBlocks.BONE_WITHER_CHAIR.get(), NordicChairBlock.PART, "chair");
        boneWitherMultiBlockHorizontal(ModBlocks.BONE_WITHER_BENCH.get(), NordicBenchBlock.PART, "bench");
        facingBlock(ModBlocks.BONE_WITHER_STOOL.get(), boneWitherModel("stool"));
        facingBlock(ModBlocks.BONE_WITHER_SKULL.get(), boneWitherModel("skull"));
        registerBoneWitherShelf();
        facingBlock(ModBlocks.BONE_WITHER_DRAWER.get(), boneWitherModel("drawer"));
        facingBlock(ModBlocks.BONE_WITHER_LOCKBOX.get(), boneWitherModel("lockbox"));
        boneWitherMultiBlockHorizontal(ModBlocks.BONE_WITHER_DESK_LEFT.get(), NordicDeskLeftBlock.PART, "desk_left");
        boneWitherMultiBlockHorizontal(ModBlocks.BONE_WITHER_DESK_RIGHT.get(), NordicDeskRightBlock.PART, "desk_right");
        boneWitherMultiBlockHorizontal(ModBlocks.BONE_WITHER_DRESSER.get(), NordicDresserBlock.PART, "dresser");
        registerBoneWitherCounter();
        boneWitherMultiBlockHorizontal(ModBlocks.BONE_WITHER_CHEST.get(), NordicChestBlock.PART, "chest");
        boneWitherMultiBlockHorizontal(ModBlocks.BONE_WITHER_WARDROBE_BOTTOM.get(), NordicWardrobeBottomBlock.PART, "wardrobe_bottom");
        boneWitherMultiBlockHorizontal(ModBlocks.BONE_WITHER_WARDROBE_TOP.get(), NordicWardrobeTopBlock.PART, "wardrobe_top");
        boneWitherMultiBlockHorizontal(ModBlocks.BONE_WITHER_BOOKSHELF.get(), NordicBookshelfBlock.PART, "bookshelf");
        facingBlock(ModBlocks.BONE_WITHER_PAINTING_SMALL.get(), boneWitherModel("painting_small"));
        boneWitherMultiBlockHorizontal(ModBlocks.BONE_WITHER_PAINTING_WIDE.get(), NordicPaintingWideBlock.PART, "painting_wide");

        registerBoneWitherTableSmall();
        boneWitherMultiBlockHorizontal(ModBlocks.BONE_WITHER_TABLE_WIDE.get(), NordicTableWideBlock.PART, "table_wide");
        boneWitherMultiBlockHorizontal(ModBlocks.BONE_WITHER_TABLE_LARGE.get(), NordicTableLargeBlock.PART, "table_large");

        registerBoneWitherSofa();
        registerBoneWitherBedSingle();
        registerBoneWitherBedDouble();
        registerBoneWitherDoorSingle();
        registerBoneWitherDoorDouble();
        registerBoneWitherOven();
        registerBoneWitherDecorations();
    }

    private void registerBoneWitherDecorations() {
        ModelFile pile = boneWitherDecorationModel("pile");
        getVariantBuilder(ModBlocks.BONE_WITHER_PILE.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            return ConfiguredModel.builder()
                    .modelFile(pile)
                    .rotationY(getYRotation(facing))
                    .build();
        });

        ModelFile chalices0 = boneWitherDecorationModel("chalices_0");
        ModelFile chalices1 = boneWitherDecorationModel("chalices_1");
        ModelFile chalices2 = boneWitherDecorationModel("chalices_2");
        getVariantBuilder(ModBlocks.BONE_WITHER_CHALICES.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int count = state.getValue(BoneChalicesBlock.CHALICES);
            ModelFile model = switch (count) {
                case 2 -> chalices2;
                case 1 -> chalices1;
                default -> chalices0;
            };
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(getYRotation(facing))
                    .build();
        });

        // Skull Blossoms: BE-rendered (BlockEntityRenderer draws the Java model).
        // Particle-only blockstate model so break particles use the decoration texture.
        ModelFile skullBlossomsWitherModel = models().getBuilder(BONE_WITHER + ":block/decorations/skull_blossoms")
                .texture("particle", BONE_WITHER + ":block/decorations/skull_blossoms");
        getVariantBuilder(ModBlocks.BONE_WITHER_SKULL_BLOSSOMS.get())
                .forAllStates(state -> ConfiguredModel.builder().modelFile(skullBlossomsWitherModel).build());
    }

    private ModelFile boneWitherDecorationModel(String name) {
        return existingModel(BONE_WITHER, "block/decorations/" + name);
    }

    private void registerBoneWitherShelf() {
        ModelFile single = boneWitherModel("shelf_single");
        ModelFile center = boneWitherModel("shelf_center");
        ModelFile left = boneWitherModel("shelf_left");
        ModelFile right = boneWitherModel("shelf_right");

        getVariantBuilder(ModBlocks.BONE_WITHER_SHELF.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            NordicShelfBlock.ConnectionType connection = state.getValue(NordicShelfBlock.CONNECTION);
            int yRot = getYRotation(facing);

            ModelFile model = switch (connection) {
                case CENTER -> center;
                case LEFT -> left;
                case RIGHT -> right;
                default -> single;
            };

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerBoneWitherCounter() {
        ModelFile single = boneWitherModel("counter_single");
        ModelFile corner = boneWitherModel("counter_corner");

        getVariantBuilder(ModBlocks.BONE_WITHER_COUNTER.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            NordicCounterBlock.ConnectionType connection = state.getValue(NordicCounterBlock.CONNECTION);
            int yRot = getYRotation(facing);

            ModelFile model = connection == NordicCounterBlock.ConnectionType.CORNER ? corner : single;
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerBoneWitherTableSmall() {
        ModelFile table = boneWitherModel("table_small");
        getVariantBuilder(ModBlocks.BONE_WITHER_TABLE_SMALL.get()).forAllStates(state -> ConfiguredModel.builder()
                .modelFile(table)
                .build());
    }

    private void registerBoneWitherSofa() {
        ModelFile single = boneWitherModel("sofa_single");
        ModelFile left = boneWitherModel("sofa_left");
        ModelFile right = boneWitherModel("sofa_right");
        ModelFile center = boneWitherModel("sofa_center");
        ModelFile corner = boneWitherModel("sofa_corner");

        getVariantBuilder(ModBlocks.BONE_WITHER_SOFA.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            NordicSofaBlock.ConnectionType connection = state.getValue(NordicSofaBlock.CONNECTION);
            int yRot = getYRotation(facing);

            ModelFile model = switch (connection) {
                case LEFT -> left;
                case RIGHT -> right;
                case CENTER -> center;
                case CORNER -> corner;
                default -> single;
            };

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerBoneWitherBedSingle() {
        ModelFile bed = boneWitherModel("bed_single");
        ModelFile empty = boneWitherEmptyModel();

        getVariantBuilder(ModBlocks.BONE_WITHER_BED_SINGLE.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            BedPart part = state.getValue(BlockStateProperties.BED_PART);
            int yRot = getYRotation(facing);

            ModelFile model = part == BedPart.FOOT ? bed : empty;
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerBoneWitherBedDouble() {
        ModelFile bed = boneWitherModel("bed_double");
        ModelFile empty = boneWitherEmptyModel();

        getVariantBuilder(ModBlocks.BONE_WITHER_BED_DOUBLE.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int part = state.getValue(NordicBedDoubleBlock.PART);
            int yRot = getYRotation(facing);

            ModelFile model = part == 0 ? bed : empty;
            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerBoneWitherDoorSingle() {
        ModelFile right = boneWitherModel("door_single_right");
        ModelFile left = boneWitherModel("door_single_left");
        ModelFile empty = boneWitherEmptyModel();

        getVariantBuilder(ModBlocks.BONE_WITHER_DOOR_SINGLE.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            DoorHingeSide hinge = state.getValue(BlockStateProperties.DOOR_HINGE);
            boolean open = state.getValue(BlockStateProperties.OPEN);
            int part = state.getValue(NordicDoorSingleBlock.PART);

            ModelFile model;
            int yRot;
            if (part != 0) {
                model = empty;
                yRot = getYRotation(facing);
            } else {
                model = selectDoorModel(hinge, open, left, right);
                yRot = doorYRotation(facing, hinge, open);
            }

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerBoneWitherDoorDouble() {
        ModelFile right = boneWitherModel("door_double_right");
        ModelFile left = boneWitherModel("door_double_left");
        ModelFile empty = boneWitherEmptyModel();

        getVariantBuilder(ModBlocks.BONE_WITHER_DOOR_DOUBLE.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            DoorHingeSide hinge = state.getValue(BlockStateProperties.DOOR_HINGE);
            boolean open = state.getValue(BlockStateProperties.OPEN);
            int part = state.getValue(NordicDoorDoubleBlock.PART);

            ModelFile model;
            int yRot;
            if (part != 0) {
                model = empty;
                yRot = getYRotation(facing);
            } else {
                model = selectDoorModel(hinge, open, left, right);
                yRot = doorYRotation(facing, hinge, open);
            }

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void registerBoneWitherOven() {
        ModelFile oven = boneWitherModel("oven");

        getVariantBuilder(ModBlocks.BONE_WITHER_OVEN.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int yRot = getYRotation(facing);

            return ConfiguredModel.builder()
                    .modelFile(oven)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void boneWitherMultiBlockHorizontal(Block block, IntegerProperty partProp, String modelName) {
        ModelFile model = boneWitherModel(modelName);
        ModelFile empty = boneWitherEmptyModel();

        getVariantBuilder(block).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int part = state.getValue(partProp);
            int yRot = getYRotation(facing);

            return ConfiguredModel.builder()
                    .modelFile(part == 0 ? model : empty)
                    .rotationY(yRot)
                    .build();
        });
    }

    private ModelFile boneWitherModel(String name) {
        return existingModel(BONE_WITHER, "block/" + name);
    }

    private ModelFile boneWitherEmptyModel() {
        return models().getBuilder(BONE_WITHER + ":empty_multiblock_part")
                .texture("particle", BONE_WITHER + ":block/particle");
    }

    private void boneWitherBlockItem(Block block, String modelName) {
        blockItem(block, BONE_WITHER, "block/" + modelName);
    }

    private void registerItemModels() {
        // Furniture station uses fantasyfurniture namespace
        blockItem(ModBlocks.FURNITURE_STATION.get(), FantasyFurniture.MOD_ID, "block/furniture_station");

        // Wool and carpet item models parent the generated block models under fantasyfurniture_nordic.
        blockItem(ModBlocks.NORDIC_WOOL.get(), NORDIC, "block/wool");
        blockItem(ModBlocks.NORDIC_CARPET.get(), NORDIC, "block/carpet");

        // Nordic blocks reference fantasyfurniture_nordic models
        nordicBlockItem(ModBlocks.NORDIC_WALL_LIGHT.get(), "wall_light");
        nordicBlockItem(ModBlocks.NORDIC_FLOOR_LIGHT.get(), "floor_light");
        nordicBlockItem(ModBlocks.NORDIC_CHANDELIER_LIGHT.get(), "chandelier");
        nordicBlockItem(ModBlocks.NORDIC_TABLE.get(), "table");
        nordicBlockItem(ModBlocks.NORDIC_TABLE_WIDE.get(), "table_wide");
        nordicBlockItem(ModBlocks.NORDIC_TABLE_LARGE.get(), "table_large");
        nordicBlockItem(ModBlocks.NORDIC_CHAIR.get(), "chair");
        nordicBlockItem(ModBlocks.NORDIC_BENCH.get(), "bench");
        nordicBlockItem(ModBlocks.NORDIC_STOOL.get(), "stool");
        nordicBlockItem(ModBlocks.NORDIC_CUSHION.get(), "cushion");
        nordicBlockItem(ModBlocks.NORDIC_SHELF.get(), "shelf_single");
        nordicBlockItem(ModBlocks.NORDIC_SOFA.get(), "sofa_single");
        nordicBlockItem(ModBlocks.NORDIC_DRAWER.get(), "drawer");
        nordicBlockItem(ModBlocks.NORDIC_LOCKBOX.get(), "lockbox");
        nordicBlockItem(ModBlocks.NORDIC_DESK_LEFT.get(), "desk_left");
        nordicBlockItem(ModBlocks.NORDIC_DESK_RIGHT.get(), "desk_right");
        nordicBlockItem(ModBlocks.NORDIC_DRESSER.get(), "dresser");
        nordicBlockItem(ModBlocks.NORDIC_COUNTER.get(), "counter_single");
        nordicBlockItem(ModBlocks.NORDIC_CHEST.get(), "chest");
        nordicBlockItem(ModBlocks.NORDIC_WARDROBE_BOTTOM.get(), "wardrobe_bottom");
        nordicBlockItem(ModBlocks.NORDIC_WARDROBE_TOP.get(), "wardrobe_top");
        nordicBlockItem(ModBlocks.NORDIC_BOOKSHELF.get(), "bookshelf");
        nordicBlockItem(ModBlocks.NORDIC_BED_SINGLE.get(), "bed_single");
        nordicBlockItem(ModBlocks.NORDIC_BED_DOUBLE.get(), "bed_double");
        nordicBlockItem(ModBlocks.NORDIC_DOOR_SINGLE.get(), "door_single");
        nordicBlockItem(ModBlocks.NORDIC_DOOR_DOUBLE.get(), "door_double");
        nordicBlockItem(ModBlocks.NORDIC_PAINTING_SMALL.get(), "painting_small");
        nordicBlockItem(ModBlocks.NORDIC_PAINTING_WIDE.get(), "painting_wide");
        nordicBlockItem(ModBlocks.NORDIC_OVEN.get(), "oven");

        // Nordic decorations item models
        nordicBlockItem(ModBlocks.NORDIC_BOILED_CREME_TREATS.get(), "decorations/boiled_creme_treats_2");
        nordicBlockItem(ModBlocks.NORDIC_SWEETROLLS.get(), "decorations/sweetrolls_2");
        nordicBlockItem(ModBlocks.NORDIC_MEAD_BOTTLES.get(), "decorations/mead_bottles_2");
        nordicBlockItem(ModBlocks.NORDIC_SOUL_GEMS_LIGHT.get(), "decorations/soul_gems_light");
        nordicBlockItem(ModBlocks.NORDIC_SOUL_GEMS_DARK.get(), "decorations/soul_gems_dark");

        // Royal item models
        blockItem(ModBlocks.ROYAL_WOOL.get(), ROYAL, "block/wool");
        blockItem(ModBlocks.ROYAL_CARPET.get(), ROYAL, "block/carpet");
        royalBlockItem(ModBlocks.ROYAL_WALL_LIGHT.get(), "wall_light");
        royalBlockItem(ModBlocks.ROYAL_FLOOR_LIGHT.get(), "floor_light");
        royalBlockItem(ModBlocks.ROYAL_CHANDELIER_LIGHT.get(), "chandelier");
        royalBlockItem(ModBlocks.ROYAL_TABLE.get(), "table_small");
        royalBlockItem(ModBlocks.ROYAL_CHAIR.get(), "chair");
        royalBlockItem(ModBlocks.ROYAL_BENCH.get(), "bench");
        royalBlockItem(ModBlocks.ROYAL_STOOL.get(), "stool");
        royalBlockItem(ModBlocks.ROYAL_CUSHION.get(), "cushion");
        royalBlockItem(ModBlocks.ROYAL_SHELF.get(), "shelf_single");
        royalBlockItem(ModBlocks.ROYAL_SOFA.get(), "sofa_single");
        royalBlockItem(ModBlocks.ROYAL_DRAWER.get(), "drawer");
        royalBlockItem(ModBlocks.ROYAL_LOCKBOX.get(), "lockbox");
        royalBlockItem(ModBlocks.ROYAL_DESK_LEFT.get(), "desk_left");
        royalBlockItem(ModBlocks.ROYAL_DESK_RIGHT.get(), "desk_right");
        royalBlockItem(ModBlocks.ROYAL_DRESSER.get(), "dresser");
        royalBlockItem(ModBlocks.ROYAL_COUNTER.get(), "counter_single");
        royalBlockItem(ModBlocks.ROYAL_CHEST.get(), "chest");
        royalBlockItem(ModBlocks.ROYAL_WARDROBE_BOTTOM.get(), "wardrobe_bottom");
        royalBlockItem(ModBlocks.ROYAL_WARDROBE_TOP.get(), "wardrobe_top");
        royalBlockItem(ModBlocks.ROYAL_BOOKSHELF.get(), "bookshelf");
        royalBlockItem(ModBlocks.ROYAL_BED_SINGLE.get(), "bed_single");
        royalBlockItem(ModBlocks.ROYAL_BED_DOUBLE.get(), "bed_double");
        royalBlockItem(ModBlocks.ROYAL_DOOR_SINGLE.get(), "door_single_right");
        royalBlockItem(ModBlocks.ROYAL_DOOR_DOUBLE.get(), "door_double_right");
        royalBlockItem(ModBlocks.ROYAL_PAINTING_SMALL.get(), "painting_small");
        royalBlockItem(ModBlocks.ROYAL_PAINTING_WIDE.get(), "painting_wide");
        royalBlockItem(ModBlocks.ROYAL_OVEN.get(), "oven");

        // Royal decorations item models (under block/decorations/)
        royalBlockItem(ModBlocks.ROYAL_CROWN.get(), "decorations/crown");
        royalBlockItem(ModBlocks.ROYAL_CUSHIONED_CROWN.get(), "decorations/cushioned_crown");
        royalBlockItem(ModBlocks.ROYAL_CANDELABRA.get(), "decorations/candelabra");
        royalBlockItem(ModBlocks.ROYAL_CHALICES.get(), "decorations/chalices_2");
        royalBlockItem(ModBlocks.ROYAL_PLATTER.get(), "decorations/platter_0");
        royalBlockItem(ModBlocks.ROYAL_FOOD.get(), "decorations/food_0");
        royalBlockItem(ModBlocks.ROYAL_FLOOR_CUSHION.get(), "decorations/floor_cushion");
        royalBlockItem(ModBlocks.ROYAL_WALL_MIRROR.get(), "decorations/wall_mirror");
        royalBlockItem(ModBlocks.ROYAL_WALL_MIRROR_TALL.get(), "decorations/wall_mirror_tall");

        // Dunmer item models
        blockItem(ModBlocks.DUNMER_WOOL.get(), DUNMER, "block/wool");
        blockItem(ModBlocks.DUNMER_CARPET.get(), DUNMER, "block/carpet");
        dunmerBlockItem(ModBlocks.DUNMER_WALL_LIGHT.get(), "wall_light");
        dunmerBlockItem(ModBlocks.DUNMER_FLOOR_LIGHT.get(), "floor_light");
        dunmerBlockItem(ModBlocks.DUNMER_CHANDELIER_LIGHT.get(), "chandelier");
        dunmerBlockItem(ModBlocks.DUNMER_TABLE_SMALL.get(), "table_small");
        dunmerBlockItem(ModBlocks.DUNMER_TABLE_WIDE.get(), "table_wide");
        dunmerBlockItem(ModBlocks.DUNMER_TABLE_LARGE.get(), "table_large");
        dunmerBlockItem(ModBlocks.DUNMER_CHAIR.get(), "chair");
        dunmerBlockItem(ModBlocks.DUNMER_BENCH.get(), "bench");
        dunmerBlockItem(ModBlocks.DUNMER_STOOL.get(), "stool");
        dunmerBlockItem(ModBlocks.DUNMER_CUSHION.get(), "cushion");
        dunmerBlockItem(ModBlocks.DUNMER_SHELF.get(), "shelf_single");
        dunmerBlockItem(ModBlocks.DUNMER_SOFA.get(), "sofa_single");
        dunmerBlockItem(ModBlocks.DUNMER_DRAWER.get(), "drawer");
        dunmerBlockItem(ModBlocks.DUNMER_LOCKBOX.get(), "lockbox");
        dunmerBlockItem(ModBlocks.DUNMER_DESK_LEFT.get(), "desk_left");
        dunmerBlockItem(ModBlocks.DUNMER_DESK_RIGHT.get(), "desk_right");
        dunmerBlockItem(ModBlocks.DUNMER_DRESSER.get(), "dresser");
        dunmerBlockItem(ModBlocks.DUNMER_COUNTER.get(), "counter_single");
        dunmerBlockItem(ModBlocks.DUNMER_CHEST.get(), "chest");
        dunmerBlockItem(ModBlocks.DUNMER_WARDROBE_BOTTOM.get(), "wardrobe_bottom");
        dunmerBlockItem(ModBlocks.DUNMER_WARDROBE_TOP.get(), "wardrobe_top");
        dunmerBlockItem(ModBlocks.DUNMER_BOOKSHELF.get(), "bookshelf");
        dunmerBlockItem(ModBlocks.DUNMER_BED_SINGLE.get(), "bed_single");
        dunmerBlockItem(ModBlocks.DUNMER_BED_DOUBLE.get(), "bed_double");
        dunmerBlockItem(ModBlocks.DUNMER_DOOR_SINGLE.get(), "door_single_right");
        dunmerBlockItem(ModBlocks.DUNMER_DOOR_DOUBLE.get(), "door_double_right");
        dunmerBlockItem(ModBlocks.DUNMER_PAINTING_SMALL.get(), "painting_small");
        dunmerBlockItem(ModBlocks.DUNMER_PAINTING_WIDE.get(), "painting_wide");
        dunmerBlockItem(ModBlocks.DUNMER_OVEN.get(), "oven");
        dunmerBlockItem(ModBlocks.DUNMER_POTTERY_0.get(), "decorations/pottery_0");
        dunmerBlockItem(ModBlocks.DUNMER_POTTERY_1.get(), "decorations/pottery_1");

        // Venthyr item models
        blockItem(ModBlocks.VENTHYR_WOOL.get(), VENTHYR, "block/wool");
        blockItem(ModBlocks.VENTHYR_CARPET.get(), VENTHYR, "block/carpet");
        venthyrBlockItem(ModBlocks.VENTHYR_WALL_LIGHT.get(), "wall_light");
        venthyrBlockItem(ModBlocks.VENTHYR_FLOOR_LIGHT.get(), "floor_light");
        venthyrBlockItem(ModBlocks.VENTHYR_CHANDELIER_LIGHT.get(), "chandelier");
        venthyrBlockItem(ModBlocks.VENTHYR_TABLE_SMALL.get(), "table_small");
        venthyrBlockItem(ModBlocks.VENTHYR_TABLE_SMALL_FANCY.get(), "table_small_fancy");
        venthyrBlockItem(ModBlocks.VENTHYR_TABLE_WIDE.get(), "table_wide");
        venthyrBlockItem(ModBlocks.VENTHYR_TABLE_WIDE_FANCY.get(), "table_wide_fancy");
        venthyrBlockItem(ModBlocks.VENTHYR_TABLE_LARGE.get(), "table_large");
        venthyrBlockItem(ModBlocks.VENTHYR_TABLE_LARGE_FANCY.get(), "table_large_fancy");
        venthyrBlockItem(ModBlocks.VENTHYR_CHAIR.get(), "chair");
        venthyrBlockItem(ModBlocks.VENTHYR_BENCH.get(), "bench");
        venthyrBlockItem(ModBlocks.VENTHYR_STOOL.get(), "stool");
        venthyrBlockItem(ModBlocks.VENTHYR_CUSHION.get(), "cushion");
        venthyrBlockItem(ModBlocks.VENTHYR_SHELF.get(), "shelf_single");
        venthyrBlockItem(ModBlocks.VENTHYR_SOFA.get(), "sofa_single");
        venthyrBlockItem(ModBlocks.VENTHYR_DRAWER.get(), "drawer");
        venthyrBlockItem(ModBlocks.VENTHYR_LOCKBOX.get(), "lockbox");
        venthyrBlockItem(ModBlocks.VENTHYR_DESK_LEFT.get(), "desk_left");
        venthyrBlockItem(ModBlocks.VENTHYR_DESK_RIGHT.get(), "desk_right");
        venthyrBlockItem(ModBlocks.VENTHYR_DRESSER.get(), "dresser");
        venthyrBlockItem(ModBlocks.VENTHYR_COUNTER.get(), "counter_single");
        venthyrBlockItem(ModBlocks.VENTHYR_CHEST.get(), "chest");
        venthyrBlockItem(ModBlocks.VENTHYR_WARDROBE_BOTTOM.get(), "wardrobe_bottom");
        venthyrBlockItem(ModBlocks.VENTHYR_WARDROBE_TOP.get(), "wardrobe_top");
        venthyrBlockItem(ModBlocks.VENTHYR_BOOKSHELF.get(), "bookshelf");
        venthyrBlockItem(ModBlocks.VENTHYR_BED_SINGLE.get(), "bed_single");
        venthyrBlockItem(ModBlocks.VENTHYR_BED_DOUBLE.get(), "bed_double");
        venthyrBlockItem(ModBlocks.VENTHYR_DOOR_SINGLE.get(), "door_single_right");
        venthyrBlockItem(ModBlocks.VENTHYR_DOOR_DOUBLE.get(), "door_double_right");
        venthyrBlockItem(ModBlocks.VENTHYR_PAINTING_SMALL.get(), "painting_small");
        venthyrBlockItem(ModBlocks.VENTHYR_PAINTING_WIDE.get(), "painting_wide");
        venthyrBlockItem(ModBlocks.VENTHYR_OVEN.get(), "oven");
        venthyrBlockItem(ModBlocks.VENTHYR_FOOD_0.get(), "decorations/food_0");
        venthyrBlockItem(ModBlocks.VENTHYR_FOOD_1.get(), "decorations/food_1");
        venthyrBlockItem(ModBlocks.VENTHYR_TOMES.get(), "decorations/tomes_2");
        venthyrBlockItem(ModBlocks.VENTHYR_TEA_CUPS.get(), "decorations/tea_cups_2");
        venthyrBlockItem(ModBlocks.VENTHYR_PLATTER.get(), "decorations/platter_0");
        venthyrBlockItem(ModBlocks.VENTHYR_TEA_SET.get(), "decorations/tea_set");
        venthyrBlockItem(ModBlocks.VENTHYR_BANNER.get(), "decorations/banner");
        venthyrBlockItem(ModBlocks.VENTHYR_CANDLES.get(), "decorations/candles");
        venthyrBlockItem(ModBlocks.VENTHYR_CHALICES.get(), "decorations/chalices_2");
        // BE-rendered decoration: flat inventory icon
        flatBlockItem(ModBlocks.VENTHYR_WIDOW_BLOOM.get(), VENTHYR, "block/decorations/widow_bloom");

        // Necrolord item models
        blockItem(ModBlocks.NECROLORD_WOOL.get(), NECROLORD, "block/wool");
        blockItem(ModBlocks.NECROLORD_CARPET.get(), NECROLORD, "block/carpet");
        necrolordBlockItem(ModBlocks.NECROLORD_WALL_LIGHT.get(), "wall_light");
        necrolordBlockItem(ModBlocks.NECROLORD_FLOOR_LIGHT.get(), "floor_light");
        necrolordBlockItem(ModBlocks.NECROLORD_CHANDELIER_LIGHT.get(), "chandelier");
        necrolordBlockItem(ModBlocks.NECROLORD_TABLE_SMALL.get(), "table_small");
        necrolordBlockItem(ModBlocks.NECROLORD_TABLE_WIDE.get(), "table_wide");
        necrolordBlockItem(ModBlocks.NECROLORD_TABLE_LARGE.get(), "table_large");
        necrolordBlockItem(ModBlocks.NECROLORD_CHAIR.get(), "chair");
        necrolordBlockItem(ModBlocks.NECROLORD_BENCH.get(), "bench");
        necrolordBlockItem(ModBlocks.NECROLORD_STOOL.get(), "stool");
        necrolordBlockItem(ModBlocks.NECROLORD_CUSHION.get(), "cushion");
        necrolordBlockItem(ModBlocks.NECROLORD_SHELF.get(), "shelf_single");
        necrolordBlockItem(ModBlocks.NECROLORD_SOFA.get(), "sofa_single");
        necrolordBlockItem(ModBlocks.NECROLORD_DRAWER.get(), "drawer");
        necrolordBlockItem(ModBlocks.NECROLORD_LOCKBOX.get(), "lockbox");
        necrolordBlockItem(ModBlocks.NECROLORD_DESK_LEFT.get(), "desk_left");
        necrolordBlockItem(ModBlocks.NECROLORD_DESK_RIGHT.get(), "desk_right");
        necrolordBlockItem(ModBlocks.NECROLORD_DRESSER.get(), "dresser");
        necrolordBlockItem(ModBlocks.NECROLORD_COUNTER.get(), "counter_single");
        necrolordBlockItem(ModBlocks.NECROLORD_CHEST.get(), "chest");
        necrolordBlockItem(ModBlocks.NECROLORD_WARDROBE_BOTTOM.get(), "wardrobe_bottom");
        necrolordBlockItem(ModBlocks.NECROLORD_WARDROBE_TOP.get(), "wardrobe_top");
        necrolordBlockItem(ModBlocks.NECROLORD_BOOKSHELF.get(), "bookshelf");
        necrolordBlockItem(ModBlocks.NECROLORD_BED_SINGLE.get(), "bed_single");
        necrolordBlockItem(ModBlocks.NECROLORD_BED_DOUBLE.get(), "bed_double");
        necrolordBlockItem(ModBlocks.NECROLORD_DOOR_SINGLE.get(), "door_single_right");
        necrolordBlockItem(ModBlocks.NECROLORD_DOOR_DOUBLE.get(), "door_double_right");
        necrolordBlockItem(ModBlocks.NECROLORD_PAINTING_SMALL.get(), "painting_small");
        necrolordBlockItem(ModBlocks.NECROLORD_PAINTING_WIDE.get(), "painting_wide");
        necrolordBlockItem(ModBlocks.NECROLORD_OVEN.get(), "oven");
        necrolordBlockItem(ModBlocks.NECROLORD_CANDELABRA.get(), "decorations/candelabra");

        // Bone-Skeleton item models
        blockItem(ModBlocks.BONE_SKELETON_WOOL.get(), BONE_SKELETON, "block/wool");
        blockItem(ModBlocks.BONE_SKELETON_CARPET.get(), BONE_SKELETON, "block/carpet");
        boneSkeletonBlockItem(ModBlocks.BONE_SKELETON_WALL_LIGHT.get(), "wall_light");
        boneSkeletonBlockItem(ModBlocks.BONE_SKELETON_FLOOR_LIGHT.get(), "floor_light");
        boneSkeletonBlockItem(ModBlocks.BONE_SKELETON_CHANDELIER_LIGHT.get(), "chandelier");
        boneSkeletonBlockItem(ModBlocks.BONE_SKELETON_TABLE_SMALL.get(), "table_small");
        boneSkeletonBlockItem(ModBlocks.BONE_SKELETON_TABLE_WIDE.get(), "table_wide");
        boneSkeletonBlockItem(ModBlocks.BONE_SKELETON_TABLE_LARGE.get(), "table_large");
        boneSkeletonBlockItem(ModBlocks.BONE_SKELETON_CHAIR.get(), "chair");
        boneSkeletonBlockItem(ModBlocks.BONE_SKELETON_BENCH.get(), "bench");
        boneSkeletonBlockItem(ModBlocks.BONE_SKELETON_STOOL.get(), "stool");
        boneSkeletonBlockItem(ModBlocks.BONE_SKELETON_SKULL.get(), "skull");
        boneSkeletonBlockItem(ModBlocks.BONE_SKELETON_SHELF.get(), "shelf_single");
        boneSkeletonBlockItem(ModBlocks.BONE_SKELETON_SOFA.get(), "sofa_single");
        boneSkeletonBlockItem(ModBlocks.BONE_SKELETON_DRAWER.get(), "drawer");
        boneSkeletonBlockItem(ModBlocks.BONE_SKELETON_LOCKBOX.get(), "lockbox");
        boneSkeletonBlockItem(ModBlocks.BONE_SKELETON_DESK_LEFT.get(), "desk_left");
        boneSkeletonBlockItem(ModBlocks.BONE_SKELETON_DESK_RIGHT.get(), "desk_right");
        boneSkeletonBlockItem(ModBlocks.BONE_SKELETON_DRESSER.get(), "dresser");
        boneSkeletonBlockItem(ModBlocks.BONE_SKELETON_COUNTER.get(), "counter_single");
        boneSkeletonBlockItem(ModBlocks.BONE_SKELETON_CHEST.get(), "chest");
        boneSkeletonBlockItem(ModBlocks.BONE_SKELETON_WARDROBE_BOTTOM.get(), "wardrobe_bottom");
        boneSkeletonBlockItem(ModBlocks.BONE_SKELETON_WARDROBE_TOP.get(), "wardrobe_top");
        boneSkeletonBlockItem(ModBlocks.BONE_SKELETON_BOOKSHELF.get(), "bookshelf");
        boneSkeletonBlockItem(ModBlocks.BONE_SKELETON_BED_SINGLE.get(), "bed_single");
        boneSkeletonBlockItem(ModBlocks.BONE_SKELETON_BED_DOUBLE.get(), "bed_double");
        boneSkeletonBlockItem(ModBlocks.BONE_SKELETON_DOOR_SINGLE.get(), "door_single_right");
        boneSkeletonBlockItem(ModBlocks.BONE_SKELETON_DOOR_DOUBLE.get(), "door_double_right");
        boneSkeletonBlockItem(ModBlocks.BONE_SKELETON_PAINTING_SMALL.get(), "painting_small");
        boneSkeletonBlockItem(ModBlocks.BONE_SKELETON_PAINTING_WIDE.get(), "painting_wide");
        boneSkeletonBlockItem(ModBlocks.BONE_SKELETON_OVEN.get(), "oven");
        boneSkeletonBlockItem(ModBlocks.BONE_SKELETON_CHALICES.get(), "decorations/chalices_2");
        boneSkeletonBlockItem(ModBlocks.BONE_SKELETON_PILE.get(), "decorations/pile");
        // BE-rendered decoration: flat inventory icon
        flatBlockItem(ModBlocks.BONE_SKELETON_SKULL_BLOSSOMS.get(), BONE_SKELETON, "block/decorations/skull_blossoms");

        // Bone-Wither item models
        blockItem(ModBlocks.BONE_WITHER_WOOL.get(), BONE_WITHER, "block/wool");
        blockItem(ModBlocks.BONE_WITHER_CARPET.get(), BONE_WITHER, "block/carpet");
        boneWitherBlockItem(ModBlocks.BONE_WITHER_WALL_LIGHT.get(), "wall_light");
        boneWitherBlockItem(ModBlocks.BONE_WITHER_FLOOR_LIGHT.get(), "floor_light");
        boneWitherBlockItem(ModBlocks.BONE_WITHER_CHANDELIER_LIGHT.get(), "chandelier");
        boneWitherBlockItem(ModBlocks.BONE_WITHER_TABLE_SMALL.get(), "table_small");
        boneWitherBlockItem(ModBlocks.BONE_WITHER_TABLE_WIDE.get(), "table_wide");
        boneWitherBlockItem(ModBlocks.BONE_WITHER_TABLE_LARGE.get(), "table_large");
        boneWitherBlockItem(ModBlocks.BONE_WITHER_CHAIR.get(), "chair");
        boneWitherBlockItem(ModBlocks.BONE_WITHER_BENCH.get(), "bench");
        boneWitherBlockItem(ModBlocks.BONE_WITHER_STOOL.get(), "stool");
        boneWitherBlockItem(ModBlocks.BONE_WITHER_SKULL.get(), "skull");
        boneWitherBlockItem(ModBlocks.BONE_WITHER_SHELF.get(), "shelf_single");
        boneWitherBlockItem(ModBlocks.BONE_WITHER_SOFA.get(), "sofa_single");
        boneWitherBlockItem(ModBlocks.BONE_WITHER_DRAWER.get(), "drawer");
        boneWitherBlockItem(ModBlocks.BONE_WITHER_LOCKBOX.get(), "lockbox");
        boneWitherBlockItem(ModBlocks.BONE_WITHER_DESK_LEFT.get(), "desk_left");
        boneWitherBlockItem(ModBlocks.BONE_WITHER_DESK_RIGHT.get(), "desk_right");
        boneWitherBlockItem(ModBlocks.BONE_WITHER_DRESSER.get(), "dresser");
        boneWitherBlockItem(ModBlocks.BONE_WITHER_COUNTER.get(), "counter_single");
        boneWitherBlockItem(ModBlocks.BONE_WITHER_CHEST.get(), "chest");
        boneWitherBlockItem(ModBlocks.BONE_WITHER_WARDROBE_BOTTOM.get(), "wardrobe_bottom");
        boneWitherBlockItem(ModBlocks.BONE_WITHER_WARDROBE_TOP.get(), "wardrobe_top");
        boneWitherBlockItem(ModBlocks.BONE_WITHER_BOOKSHELF.get(), "bookshelf");
        boneWitherBlockItem(ModBlocks.BONE_WITHER_BED_SINGLE.get(), "bed_single");
        boneWitherBlockItem(ModBlocks.BONE_WITHER_BED_DOUBLE.get(), "bed_double");
        boneWitherBlockItem(ModBlocks.BONE_WITHER_DOOR_SINGLE.get(), "door_single_right");
        boneWitherBlockItem(ModBlocks.BONE_WITHER_DOOR_DOUBLE.get(), "door_double_right");
        boneWitherBlockItem(ModBlocks.BONE_WITHER_PAINTING_SMALL.get(), "painting_small");
        boneWitherBlockItem(ModBlocks.BONE_WITHER_PAINTING_WIDE.get(), "painting_wide");
        boneWitherBlockItem(ModBlocks.BONE_WITHER_OVEN.get(), "oven");
        boneWitherBlockItem(ModBlocks.BONE_WITHER_CHALICES.get(), "decorations/chalices_2");
        boneWitherBlockItem(ModBlocks.BONE_WITHER_PILE.get(), "decorations/pile");
        // BE-rendered decoration: flat inventory icon
        flatBlockItem(ModBlocks.BONE_WITHER_SKULL_BLOSSOMS.get(), BONE_WITHER, "block/decorations/skull_blossoms");
    }

    // --- Helpers ---

    private void nordicHorizontalBlock(Block block, String modelName) {
        facingBlock(block, nordicModel(modelName));
    }

    private void nordicMultiBlockHorizontal(Block block, IntegerProperty partProp, String modelName) {
        ModelFile model = nordicModel(modelName);
        ModelFile empty = emptyModel();

        getVariantBuilder(block).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int part = state.getValue(partProp);
            int yRot = getYRotation(facing);

            return ConfiguredModel.builder()
                    .modelFile(part == 0 ? model : empty)
                    .rotationY(yRot)
                    .build();
        });
    }

    private ModelFile emptyModel() {
        return models().getBuilder(NORDIC + ":empty_multiblock_part")
                .texture("particle", "fantasyfurniture_nordic:block/particle");
    }

    private ModelFile nordicModel(String name) {
        return existingModel(NORDIC, "block/" + name);
    }

    private ModelFile existingModel(String namespace, String path) {
        return models().getExistingFile(ResourceLocation.fromNamespaceAndPath(namespace, path));
    }

    private void nordicBlockItem(Block block, String modelName) {
        blockItem(block, NORDIC, "block/" + modelName);
    }

    private void blockItem(Block block, String namespace, String modelPath) {
        // Use the block's full namespaced id so item models for Nordic blocks are written
        // under assets/fantasyfurniture_nordic/models/item/, not the provider's default namespace.
        ResourceLocation blockId = block.builtInRegistryHolder().key().location();
        itemModels().getBuilder(blockId.toString())
                .parent(new ModelFile.UncheckedModelFile(ResourceLocation.fromNamespaceAndPath(namespace, modelPath)));
    }

    // Flat item model (minecraft:item/generated) for BE-rendered blocks that have no JSON block model.
    private void flatBlockItem(Block block, String namespace, String texturePath) {
        ResourceLocation blockId = block.builtInRegistryHolder().key().location();
        itemModels().getBuilder(blockId.toString())
                .parent(new ModelFile.UncheckedModelFile(ResourceLocation.parse("minecraft:item/generated")))
                .texture("layer0", namespace + ":" + texturePath);
    }

    private static int getYRotation(Direction facing) {
        return switch (facing) {
            case NORTH -> 0;
            case EAST -> 90;
            case SOUTH -> 180;
            case WEST -> 270;
            default -> 0;
        };
    }

    private void facingBlock(Block block, ModelFile model) {
        getVariantBuilder(block).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            int yRot = getYRotation(facing);

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }
}
