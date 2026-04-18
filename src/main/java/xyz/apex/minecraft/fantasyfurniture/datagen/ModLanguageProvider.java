package xyz.apex.minecraft.fantasyfurniture.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import xyz.apex.minecraft.fantasyfurniture.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.registry.ModBlocks;
import xyz.apex.minecraft.fantasyfurniture.registry.ModEntities;

public final class ModLanguageProvider {
    private ModLanguageProvider() {}

    public static LanguageProvider main(PackOutput output) {
        return new LanguageProvider(output, FantasyFurniture.MOD_ID, "en_us") {
            @Override
            protected void addTranslations() {
                add("itemGroup.fantasyfurniture", "Fantasy's Furniture");
                addBlock(ModBlocks.FURNITURE_STATION, "Furniture Station");
                addEntityType(ModEntities.SEAT, "Seat");
                add("container.fantasyfurniture.small_container", "Small Container");
                add("container.fantasyfurniture.medium_container", "Medium Container");
                add("container.fantasyfurniture.large_container", "Large Container");
                add("container.fantasyfurniture.bookshelf", "Bookshelf");
                add("container.fantasyfurniture.furnace", "Oven");
                add("container.fantasyfurniture.furniture_station", "Furniture Station");
            }
        };
    }

    public static LanguageProvider nordic(PackOutput output) {
        return new LanguageProvider(output, FantasyFurniture.NORDIC_ID, "en_us") {
            @Override
            protected void addTranslations() {
                addBlock(ModBlocks.NORDIC_WOOL, "Nordic Wool");
                addBlock(ModBlocks.NORDIC_CARPET, "Nordic Carpet");
                addBlock(ModBlocks.NORDIC_WALL_LIGHT, "Nordic Wall Light");
                addBlock(ModBlocks.NORDIC_FLOOR_LIGHT, "Nordic Floor Light");
                addBlock(ModBlocks.NORDIC_CHANDELIER_LIGHT, "Nordic Chandelier");
                addBlock(ModBlocks.NORDIC_TABLE, "Nordic Table");
                addBlock(ModBlocks.NORDIC_TABLE_WIDE, "Nordic Table (Wide)");
                addBlock(ModBlocks.NORDIC_TABLE_LARGE, "Nordic Table (Large)");
                addBlock(ModBlocks.NORDIC_CHAIR, "Nordic Chair");
                addBlock(ModBlocks.NORDIC_BENCH, "Nordic Bench");
                addBlock(ModBlocks.NORDIC_STOOL, "Nordic Stool");
                addBlock(ModBlocks.NORDIC_CUSHION, "Nordic Cushion");
                addBlock(ModBlocks.NORDIC_SHELF, "Nordic Shelf");
                addBlock(ModBlocks.NORDIC_SOFA, "Nordic Sofa");
                addBlock(ModBlocks.NORDIC_DRAWER, "Nordic Drawer");
                addBlock(ModBlocks.NORDIC_LOCKBOX, "Nordic Lockbox");
                addBlock(ModBlocks.NORDIC_DESK_LEFT, "Nordic Desk (Left)");
                addBlock(ModBlocks.NORDIC_DESK_RIGHT, "Nordic Desk (Right)");
                addBlock(ModBlocks.NORDIC_DRESSER, "Nordic Dresser");
                addBlock(ModBlocks.NORDIC_COUNTER, "Nordic Counter");
                addBlock(ModBlocks.NORDIC_CHEST, "Nordic Chest");
                addBlock(ModBlocks.NORDIC_WARDROBE_BOTTOM, "Nordic Wardrobe");
                addBlock(ModBlocks.NORDIC_WARDROBE_TOP, "Nordic Wardrobe Top");
                addBlock(ModBlocks.NORDIC_BOOKSHELF, "Nordic Bookshelf");
                addBlock(ModBlocks.NORDIC_BED_SINGLE, "Nordic Bed (Single)");
                addBlock(ModBlocks.NORDIC_BED_DOUBLE, "Nordic Bed (Double)");
                addBlock(ModBlocks.NORDIC_DOOR_SINGLE, "Nordic Door (Single)");
                addBlock(ModBlocks.NORDIC_DOOR_DOUBLE, "Nordic Door (Double)");
                addBlock(ModBlocks.NORDIC_PAINTING_SMALL, "Nordic Painting (Small)");
                addBlock(ModBlocks.NORDIC_PAINTING_WIDE, "Nordic Painting (Wide)");
                addBlock(ModBlocks.NORDIC_OVEN, "Nordic Oven");
                addBlock(ModBlocks.NORDIC_BOILED_CREME_TREATS, "Nordic Boiled Creme Treats");
                addBlock(ModBlocks.NORDIC_SWEETROLLS, "Nordic Sweet Rolls");
                addBlock(ModBlocks.NORDIC_MEAD_BOTTLES, "Nordic Mead Bottles");
                addBlock(ModBlocks.NORDIC_SOUL_GEMS_LIGHT, "Nordic Soul Gems (Light)");
                addBlock(ModBlocks.NORDIC_SOUL_GEMS_DARK, "Nordic Soul Gems (Dark)");
            }
        };
    }

    public static LanguageProvider royal(PackOutput output) {
        return new LanguageProvider(output, FantasyFurniture.ROYAL_ID, "en_us") {
            @Override
            protected void addTranslations() {
                addBlock(ModBlocks.ROYAL_WOOL, "Royal Wool");
                addBlock(ModBlocks.ROYAL_CARPET, "Royal Carpet");
                addBlock(ModBlocks.ROYAL_WALL_LIGHT, "Royal Wall Light");
                addBlock(ModBlocks.ROYAL_FLOOR_LIGHT, "Royal Floor Light");
                addBlock(ModBlocks.ROYAL_CHANDELIER_LIGHT, "Royal Chandelier");
                addBlock(ModBlocks.ROYAL_TABLE, "Royal Table");
                addBlock(ModBlocks.ROYAL_CHAIR, "Royal Chair");
                addBlock(ModBlocks.ROYAL_BENCH, "Royal Bench");
                addBlock(ModBlocks.ROYAL_STOOL, "Royal Stool");
                addBlock(ModBlocks.ROYAL_CUSHION, "Royal Cushion");
                addBlock(ModBlocks.ROYAL_SHELF, "Royal Shelf");
                addBlock(ModBlocks.ROYAL_SOFA, "Royal Sofa");
                addBlock(ModBlocks.ROYAL_DRAWER, "Royal Drawer");
                addBlock(ModBlocks.ROYAL_LOCKBOX, "Royal Lockbox");
                addBlock(ModBlocks.ROYAL_DESK_LEFT, "Royal Desk (Left)");
                addBlock(ModBlocks.ROYAL_DESK_RIGHT, "Royal Desk (Right)");
                addBlock(ModBlocks.ROYAL_DRESSER, "Royal Dresser");
                addBlock(ModBlocks.ROYAL_COUNTER, "Royal Counter");
                addBlock(ModBlocks.ROYAL_CHEST, "Royal Chest");
                addBlock(ModBlocks.ROYAL_WARDROBE_BOTTOM, "Royal Wardrobe");
                addBlock(ModBlocks.ROYAL_WARDROBE_TOP, "Royal Wardrobe Top");
                addBlock(ModBlocks.ROYAL_BOOKSHELF, "Royal Bookshelf");
                addBlock(ModBlocks.ROYAL_BED_SINGLE, "Royal Bed (Single)");
                addBlock(ModBlocks.ROYAL_BED_DOUBLE, "Royal Bed (Double)");
                addBlock(ModBlocks.ROYAL_DOOR_SINGLE, "Royal Door (Single)");
                addBlock(ModBlocks.ROYAL_DOOR_DOUBLE, "Royal Door (Double)");
                addBlock(ModBlocks.ROYAL_PAINTING_SMALL, "Royal Painting (Small)");
                addBlock(ModBlocks.ROYAL_PAINTING_WIDE, "Royal Painting (Wide)");
                addBlock(ModBlocks.ROYAL_OVEN, "Royal Oven");
                addBlock(ModBlocks.ROYAL_CROWN, "Royal Crown");
                addBlock(ModBlocks.ROYAL_CUSHIONED_CROWN, "Royal Cushioned Crown");
                addBlock(ModBlocks.ROYAL_CANDELABRA, "Royal Candelabra");
                addBlock(ModBlocks.ROYAL_CHALICES, "Royal Chalices");
                addBlock(ModBlocks.ROYAL_PLATTER, "Royal Platter");
                addBlock(ModBlocks.ROYAL_FOOD, "Royal Food");
                addBlock(ModBlocks.ROYAL_FLOOR_CUSHION, "Royal Floor Cushion");
                addBlock(ModBlocks.ROYAL_WALL_MIRROR, "Royal Wall Mirror");
                addBlock(ModBlocks.ROYAL_WALL_MIRROR_TALL, "Royal Wall Mirror (Tall)");
            }
        };
    }

    public static LanguageProvider venthyr(PackOutput output) {
        return new LanguageProvider(output, FantasyFurniture.VENTHYR_ID, "en_us") {
            @Override
            protected void addTranslations() {
                addBlock(ModBlocks.VENTHYR_WOOL, "Venthyr Wool");
                addBlock(ModBlocks.VENTHYR_CARPET, "Venthyr Carpet");
                addBlock(ModBlocks.VENTHYR_WALL_LIGHT, "Venthyr Wall Light");
                addBlock(ModBlocks.VENTHYR_FLOOR_LIGHT, "Venthyr Floor Light");
                addBlock(ModBlocks.VENTHYR_CHANDELIER_LIGHT, "Venthyr Chandelier");
                addBlock(ModBlocks.VENTHYR_TABLE_SMALL, "Venthyr Table (Small)");
                addBlock(ModBlocks.VENTHYR_TABLE_SMALL_FANCY, "Venthyr Table (Small, Fancy)");
                addBlock(ModBlocks.VENTHYR_TABLE_WIDE, "Venthyr Table (Wide)");
                addBlock(ModBlocks.VENTHYR_TABLE_WIDE_FANCY, "Venthyr Table (Wide, Fancy)");
                addBlock(ModBlocks.VENTHYR_TABLE_LARGE, "Venthyr Table (Large)");
                addBlock(ModBlocks.VENTHYR_TABLE_LARGE_FANCY, "Venthyr Table (Large, Fancy)");
                addBlock(ModBlocks.VENTHYR_CHAIR, "Venthyr Chair");
                addBlock(ModBlocks.VENTHYR_BENCH, "Venthyr Bench");
                addBlock(ModBlocks.VENTHYR_STOOL, "Venthyr Stool");
                addBlock(ModBlocks.VENTHYR_CUSHION, "Venthyr Cushion");
                addBlock(ModBlocks.VENTHYR_SHELF, "Venthyr Shelf");
                addBlock(ModBlocks.VENTHYR_SOFA, "Venthyr Sofa");
                addBlock(ModBlocks.VENTHYR_DRAWER, "Venthyr Drawer");
                addBlock(ModBlocks.VENTHYR_LOCKBOX, "Venthyr Lockbox");
                addBlock(ModBlocks.VENTHYR_DESK_LEFT, "Venthyr Desk (Left)");
                addBlock(ModBlocks.VENTHYR_DESK_RIGHT, "Venthyr Desk (Right)");
                addBlock(ModBlocks.VENTHYR_DRESSER, "Venthyr Dresser");
                addBlock(ModBlocks.VENTHYR_COUNTER, "Venthyr Counter");
                addBlock(ModBlocks.VENTHYR_CHEST, "Venthyr Chest");
                addBlock(ModBlocks.VENTHYR_WARDROBE_BOTTOM, "Venthyr Wardrobe");
                addBlock(ModBlocks.VENTHYR_WARDROBE_TOP, "Venthyr Wardrobe Top");
                addBlock(ModBlocks.VENTHYR_BOOKSHELF, "Venthyr Bookshelf");
                addBlock(ModBlocks.VENTHYR_BED_SINGLE, "Venthyr Bed (Single)");
                addBlock(ModBlocks.VENTHYR_BED_DOUBLE, "Venthyr Bed (Double)");
                addBlock(ModBlocks.VENTHYR_DOOR_SINGLE, "Venthyr Door (Single)");
                addBlock(ModBlocks.VENTHYR_DOOR_DOUBLE, "Venthyr Door (Double)");
                addBlock(ModBlocks.VENTHYR_PAINTING_SMALL, "Venthyr Painting (Small)");
                addBlock(ModBlocks.VENTHYR_PAINTING_WIDE, "Venthyr Painting (Wide)");
                addBlock(ModBlocks.VENTHYR_OVEN, "Venthyr Oven");
                addBlock(ModBlocks.VENTHYR_FOOD_0, "Venthyr Food");
                addBlock(ModBlocks.VENTHYR_FOOD_1, "Venthyr Food (Platter)");
                addBlock(ModBlocks.VENTHYR_TOMES, "Venthyr Tomes");
                addBlock(ModBlocks.VENTHYR_TEA_CUPS, "Venthyr Tea Cups");
                addBlock(ModBlocks.VENTHYR_PLATTER, "Venthyr Platter");
                addBlock(ModBlocks.VENTHYR_TEA_SET, "Venthyr Tea Set");
                addBlock(ModBlocks.VENTHYR_BANNER, "Venthyr Banner");
                addBlock(ModBlocks.VENTHYR_CANDLES, "Venthyr Candles");
                addBlock(ModBlocks.VENTHYR_CHALICES, "Venthyr Chalices");
                addBlock(ModBlocks.VENTHYR_WIDOW_BLOOM, "Venthyr Widow Bloom");
            }
        };
    }

    public static LanguageProvider dunmer(PackOutput output) {
        return new LanguageProvider(output, FantasyFurniture.DUNMER_ID, "en_us") {
            @Override
            protected void addTranslations() {
                addBlock(ModBlocks.DUNMER_WOOL, "Dunmer Wool");
                addBlock(ModBlocks.DUNMER_CARPET, "Dunmer Carpet");
                addBlock(ModBlocks.DUNMER_WALL_LIGHT, "Dunmer Wall Light");
                addBlock(ModBlocks.DUNMER_FLOOR_LIGHT, "Dunmer Floor Light");
                addBlock(ModBlocks.DUNMER_CHANDELIER_LIGHT, "Dunmer Chandelier");
                addBlock(ModBlocks.DUNMER_TABLE_SMALL, "Dunmer Table (Small)");
                addBlock(ModBlocks.DUNMER_TABLE_WIDE, "Dunmer Table (Wide)");
                addBlock(ModBlocks.DUNMER_TABLE_LARGE, "Dunmer Table (Large)");
                addBlock(ModBlocks.DUNMER_CHAIR, "Dunmer Chair");
                addBlock(ModBlocks.DUNMER_BENCH, "Dunmer Bench");
                addBlock(ModBlocks.DUNMER_STOOL, "Dunmer Stool");
                addBlock(ModBlocks.DUNMER_CUSHION, "Dunmer Cushion");
                addBlock(ModBlocks.DUNMER_SHELF, "Dunmer Shelf");
                addBlock(ModBlocks.DUNMER_SOFA, "Dunmer Sofa");
                addBlock(ModBlocks.DUNMER_DRAWER, "Dunmer Drawer");
                addBlock(ModBlocks.DUNMER_LOCKBOX, "Dunmer Lockbox");
                addBlock(ModBlocks.DUNMER_DESK_LEFT, "Dunmer Desk (Left)");
                addBlock(ModBlocks.DUNMER_DESK_RIGHT, "Dunmer Desk (Right)");
                addBlock(ModBlocks.DUNMER_DRESSER, "Dunmer Dresser");
                addBlock(ModBlocks.DUNMER_COUNTER, "Dunmer Counter");
                addBlock(ModBlocks.DUNMER_CHEST, "Dunmer Chest");
                addBlock(ModBlocks.DUNMER_WARDROBE_BOTTOM, "Dunmer Wardrobe");
                addBlock(ModBlocks.DUNMER_WARDROBE_TOP, "Dunmer Wardrobe Top");
                addBlock(ModBlocks.DUNMER_BOOKSHELF, "Dunmer Bookshelf");
                addBlock(ModBlocks.DUNMER_BED_SINGLE, "Dunmer Bed (Single)");
                addBlock(ModBlocks.DUNMER_BED_DOUBLE, "Dunmer Bed (Double)");
                addBlock(ModBlocks.DUNMER_DOOR_SINGLE, "Dunmer Door (Single)");
                addBlock(ModBlocks.DUNMER_DOOR_DOUBLE, "Dunmer Door (Double)");
                addBlock(ModBlocks.DUNMER_PAINTING_SMALL, "Dunmer Painting (Small)");
                addBlock(ModBlocks.DUNMER_PAINTING_WIDE, "Dunmer Painting (Wide)");
                addBlock(ModBlocks.DUNMER_OVEN, "Dunmer Cooking Spit");
                addBlock(ModBlocks.DUNMER_POTTERY_0, "Dunmer Pottery");
                addBlock(ModBlocks.DUNMER_POTTERY_1, "Dunmer Pottery (Wide)");
            }
        };
    }

    public static LanguageProvider necrolord(PackOutput output) {
        return new LanguageProvider(output, FantasyFurniture.NECROLORD_ID, "en_us") {
            @Override
            protected void addTranslations() {
                addBlock(ModBlocks.NECROLORD_WOOL, "Necrolord Wool");
                addBlock(ModBlocks.NECROLORD_CARPET, "Necrolord Carpet");
                addBlock(ModBlocks.NECROLORD_WALL_LIGHT, "Necrolord Wall Light");
                addBlock(ModBlocks.NECROLORD_FLOOR_LIGHT, "Necrolord Floor Light");
                addBlock(ModBlocks.NECROLORD_CHANDELIER_LIGHT, "Necrolord Chandelier");
                addBlock(ModBlocks.NECROLORD_TABLE_SMALL, "Necrolord Small Table");
                addBlock(ModBlocks.NECROLORD_TABLE_WIDE, "Necrolord Wide Table");
                addBlock(ModBlocks.NECROLORD_TABLE_LARGE, "Necrolord Large Table");
                addBlock(ModBlocks.NECROLORD_CHAIR, "Necrolord Chair");
                addBlock(ModBlocks.NECROLORD_BENCH, "Necrolord Bench");
                addBlock(ModBlocks.NECROLORD_STOOL, "Necrolord Stool");
                addBlock(ModBlocks.NECROLORD_CUSHION, "Necrolord Cushion");
                addBlock(ModBlocks.NECROLORD_SHELF, "Necrolord Shelf");
                addBlock(ModBlocks.NECROLORD_SOFA, "Necrolord Sofa");
                addBlock(ModBlocks.NECROLORD_DRAWER, "Necrolord Drawer");
                addBlock(ModBlocks.NECROLORD_LOCKBOX, "Necrolord Lockbox");
                addBlock(ModBlocks.NECROLORD_DESK_LEFT, "Necrolord Desk (Left)");
                addBlock(ModBlocks.NECROLORD_DESK_RIGHT, "Necrolord Desk (Right)");
                addBlock(ModBlocks.NECROLORD_DRESSER, "Necrolord Dresser");
                addBlock(ModBlocks.NECROLORD_COUNTER, "Necrolord Counter");
                addBlock(ModBlocks.NECROLORD_CHEST, "Necrolord Chest");
                addBlock(ModBlocks.NECROLORD_WARDROBE_BOTTOM, "Necrolord Wardrobe (Bottom)");
                addBlock(ModBlocks.NECROLORD_WARDROBE_TOP, "Necrolord Wardrobe (Top)");
                addBlock(ModBlocks.NECROLORD_BOOKSHELF, "Necrolord Bookshelf");
                addBlock(ModBlocks.NECROLORD_BED_SINGLE, "Necrolord Single Bed");
                addBlock(ModBlocks.NECROLORD_BED_DOUBLE, "Necrolord Double Bed");
                addBlock(ModBlocks.NECROLORD_DOOR_SINGLE, "Necrolord Single Door");
                addBlock(ModBlocks.NECROLORD_DOOR_DOUBLE, "Necrolord Double Door");
                addBlock(ModBlocks.NECROLORD_PAINTING_SMALL, "Necrolord Small Painting");
                addBlock(ModBlocks.NECROLORD_PAINTING_WIDE, "Necrolord Wide Painting");
                addBlock(ModBlocks.NECROLORD_OVEN, "Necrolord Oven");
                addBlock(ModBlocks.NECROLORD_CANDELABRA, "Necrolord Candelabra");
            }
        };
    }

    public static LanguageProvider boneSkeleton(PackOutput output) {
        return new LanguageProvider(output, FantasyFurniture.BONE_SKELETON_ID, "en_us") {
            @Override
            protected void addTranslations() {
                addBlock(ModBlocks.BONE_SKELETON_WOOL, "Bone Wool");
                addBlock(ModBlocks.BONE_SKELETON_CARPET, "Bone Carpet");
                addBlock(ModBlocks.BONE_SKELETON_WALL_LIGHT, "Bone Wall Light");
                addBlock(ModBlocks.BONE_SKELETON_FLOOR_LIGHT, "Bone Floor Light");
                addBlock(ModBlocks.BONE_SKELETON_CHANDELIER_LIGHT, "Bone Chandelier");
                addBlock(ModBlocks.BONE_SKELETON_TABLE_SMALL, "Bone Table Small");
                addBlock(ModBlocks.BONE_SKELETON_TABLE_WIDE, "Bone Table Wide");
                addBlock(ModBlocks.BONE_SKELETON_TABLE_LARGE, "Bone Table Large");
                addBlock(ModBlocks.BONE_SKELETON_CHAIR, "Bone Chair");
                addBlock(ModBlocks.BONE_SKELETON_BENCH, "Bone Bench");
                addBlock(ModBlocks.BONE_SKELETON_STOOL, "Bone Stool");
                addBlock(ModBlocks.BONE_SKELETON_SKULL, "Bone Skull");
                addBlock(ModBlocks.BONE_SKELETON_SHELF, "Bone Shelf");
                addBlock(ModBlocks.BONE_SKELETON_SOFA, "Bone Sofa");
                addBlock(ModBlocks.BONE_SKELETON_DRAWER, "Bone Drawer");
                addBlock(ModBlocks.BONE_SKELETON_LOCKBOX, "Bone Lockbox");
                addBlock(ModBlocks.BONE_SKELETON_DESK_LEFT, "Bone Desk Left");
                addBlock(ModBlocks.BONE_SKELETON_DESK_RIGHT, "Bone Desk Right");
                addBlock(ModBlocks.BONE_SKELETON_DRESSER, "Bone Dresser");
                addBlock(ModBlocks.BONE_SKELETON_COUNTER, "Bone Counter");
                addBlock(ModBlocks.BONE_SKELETON_CHEST, "Bone Chest");
                addBlock(ModBlocks.BONE_SKELETON_WARDROBE_BOTTOM, "Bone Wardrobe Bottom");
                addBlock(ModBlocks.BONE_SKELETON_WARDROBE_TOP, "Bone Wardrobe Top");
                addBlock(ModBlocks.BONE_SKELETON_BOOKSHELF, "Bone Bookshelf");
                addBlock(ModBlocks.BONE_SKELETON_BED_SINGLE, "Bone Bed Single");
                addBlock(ModBlocks.BONE_SKELETON_BED_DOUBLE, "Bone Bed Double");
                addBlock(ModBlocks.BONE_SKELETON_DOOR_SINGLE, "Bone Door Single");
                addBlock(ModBlocks.BONE_SKELETON_DOOR_DOUBLE, "Bone Door Double");
                addBlock(ModBlocks.BONE_SKELETON_PAINTING_SMALL, "Bone Painting Small");
                addBlock(ModBlocks.BONE_SKELETON_PAINTING_WIDE, "Bone Painting Wide");
                addBlock(ModBlocks.BONE_SKELETON_OVEN, "Bone Oven");
                addBlock(ModBlocks.BONE_SKELETON_CHALICES, "Bone Chalices");
                addBlock(ModBlocks.BONE_SKELETON_PILE, "Bone Pile");
                addBlock(ModBlocks.BONE_SKELETON_SKULL_BLOSSOMS, "Bone Skull Blossoms");
            }
        };
    }

    public static LanguageProvider boneWither(PackOutput output) {
        return new LanguageProvider(output, FantasyFurniture.BONE_WITHER_ID, "en_us") {
            @Override
            protected void addTranslations() {
                addBlock(ModBlocks.BONE_WITHER_WOOL, "Wither Bone Wool");
                addBlock(ModBlocks.BONE_WITHER_CARPET, "Wither Bone Carpet");
                addBlock(ModBlocks.BONE_WITHER_WALL_LIGHT, "Wither Bone Wall Light");
                addBlock(ModBlocks.BONE_WITHER_FLOOR_LIGHT, "Wither Bone Floor Light");
                addBlock(ModBlocks.BONE_WITHER_CHANDELIER_LIGHT, "Wither Bone Chandelier");
                addBlock(ModBlocks.BONE_WITHER_TABLE_SMALL, "Wither Bone Table Small");
                addBlock(ModBlocks.BONE_WITHER_TABLE_WIDE, "Wither Bone Table Wide");
                addBlock(ModBlocks.BONE_WITHER_TABLE_LARGE, "Wither Bone Table Large");
                addBlock(ModBlocks.BONE_WITHER_CHAIR, "Wither Bone Chair");
                addBlock(ModBlocks.BONE_WITHER_BENCH, "Wither Bone Bench");
                addBlock(ModBlocks.BONE_WITHER_STOOL, "Wither Bone Stool");
                addBlock(ModBlocks.BONE_WITHER_SKULL, "Wither Bone Skull");
                addBlock(ModBlocks.BONE_WITHER_SHELF, "Wither Bone Shelf");
                addBlock(ModBlocks.BONE_WITHER_SOFA, "Wither Bone Sofa");
                addBlock(ModBlocks.BONE_WITHER_DRAWER, "Wither Bone Drawer");
                addBlock(ModBlocks.BONE_WITHER_LOCKBOX, "Wither Bone Lockbox");
                addBlock(ModBlocks.BONE_WITHER_DESK_LEFT, "Wither Bone Desk Left");
                addBlock(ModBlocks.BONE_WITHER_DESK_RIGHT, "Wither Bone Desk Right");
                addBlock(ModBlocks.BONE_WITHER_DRESSER, "Wither Bone Dresser");
                addBlock(ModBlocks.BONE_WITHER_COUNTER, "Wither Bone Counter");
                addBlock(ModBlocks.BONE_WITHER_CHEST, "Wither Bone Chest");
                addBlock(ModBlocks.BONE_WITHER_WARDROBE_BOTTOM, "Wither Bone Wardrobe Bottom");
                addBlock(ModBlocks.BONE_WITHER_WARDROBE_TOP, "Wither Bone Wardrobe Top");
                addBlock(ModBlocks.BONE_WITHER_BOOKSHELF, "Wither Bone Bookshelf");
                addBlock(ModBlocks.BONE_WITHER_BED_SINGLE, "Wither Bone Bed Single");
                addBlock(ModBlocks.BONE_WITHER_BED_DOUBLE, "Wither Bone Bed Double");
                addBlock(ModBlocks.BONE_WITHER_DOOR_SINGLE, "Wither Bone Door Single");
                addBlock(ModBlocks.BONE_WITHER_DOOR_DOUBLE, "Wither Bone Door Double");
                addBlock(ModBlocks.BONE_WITHER_PAINTING_SMALL, "Wither Bone Painting Small");
                addBlock(ModBlocks.BONE_WITHER_PAINTING_WIDE, "Wither Bone Painting Wide");
                addBlock(ModBlocks.BONE_WITHER_OVEN, "Wither Bone Oven");
                addBlock(ModBlocks.BONE_WITHER_CHALICES, "Wither Bone Chalices");
                addBlock(ModBlocks.BONE_WITHER_PILE, "Wither Bone Pile");
                addBlock(ModBlocks.BONE_WITHER_SKULL_BLOSSOMS, "Wither Bone Skull Blossoms");
            }
        };
    }
}
