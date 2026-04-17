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
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicPaintingWideBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicShelfBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicSofaBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicTableBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicWardrobeBottomBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicWardrobeTopBlock;
import xyz.apex.minecraft.fantasyfurniture.registry.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    private static final String NORDIC = "fantasyfurniture_nordic";

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
