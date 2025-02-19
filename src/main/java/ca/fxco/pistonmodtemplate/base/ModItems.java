package ca.fxco.pistonmodtemplate.base;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

import java.util.function.Function;

import static ca.fxco.pistonmodtemplate.PistonModTemplate.id;

/**
 * Holds instances of all items registered by this mod.</br>
 * Minecraft does the same thing in {@link Items}
 */
public class ModItems {

    // TODO-TEMPLATE
    public static final Item TEMPLATE_BLOCK = registerBlock(ModBlocks.TEMPLATE_BLOCK);

    private static BlockItem registerBlock(Block block) {
        return registerBlock(block, new Item.Properties());
    }

    private static BlockItem registerBlock(Block block, Item.Properties itemProperties) {
        ResourceKey<Item> resourceKey = ResourceKey.create(Registries.ITEM, BuiltInRegistries.BLOCK.getKey(block));
        return register(resourceKey, new BlockItem(block, itemProperties.setId(resourceKey)));
    }

    private static <T extends Item> T register(String name, Function<Item.Properties, T> item,
                                               Item.Properties properties) {
        return register(id(name), item, properties);
    }

    private static <T extends Item> T register(ResourceLocation id, Function<Item.Properties, T> item,
                                               Item.Properties properties) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, id);
        return Registry.register(BuiltInRegistries.ITEM, key, item.apply((properties.setId(key))));
    }

    private static <T extends Item> T register(ResourceKey<Item> id, T item) {
        return Registry.register(BuiltInRegistries.ITEM, id, item);
    }

    public static void bootstrap() {}
}
