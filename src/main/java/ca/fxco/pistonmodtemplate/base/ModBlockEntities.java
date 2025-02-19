package ca.fxco.pistonmodtemplate.base;

import ca.fxco.pistonlib.blocks.pistons.basePiston.BasicMovingBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.Util;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import static ca.fxco.pistonmodtemplate.PistonModTemplate.LOGGER;
import static ca.fxco.pistonmodtemplate.PistonModTemplate.id;

/**
 * Holds instances of all block entities registered by this mod.</br>
 * Minecraft does the same thing in {@link BlockEntityType}
 */
public class ModBlockEntities {

    // TODO-TEMPLATE
    public static final BlockEntityType<BasicMovingBlockEntity> TEMPLATE_MOVING_BLOCK_ENTITY = registerMovingBlock(
            id("template_moving_block"),
            BasicMovingBlockEntity::new
    );

    /**
     * Builds a block entity type from the given factories and
     * registers it to the given namespaced id.
     * <br>
     * No blocks need to be passed here since they are added
     * to the block entity type after the corresponding piston
     * families are registered.
     *
     * @param <T>      the type of moving block entity
     * @param id       a namespaced id to uniquely identify the block
     *                 entity type
     * @param factory  the block entity factory for the block
     *                 entity registry
     * @return the block entity type that was registered
     */
    private static <T extends BasicMovingBlockEntity> BlockEntityType<T> registerMovingBlock(
            ResourceLocation id,
            FabricBlockEntityTypeBuilder.Factory<T> factory
    ) {
        return Registry.register(
                BuiltInRegistries.BLOCK_ENTITY_TYPE,
                id,
                FabricBlockEntityTypeBuilder.create(factory).build()
        );
    }

    // For registering block entities that aren't moving block's
    private static <T extends BlockEntity> BlockEntityType<T> register(
            String string,
            FabricBlockEntityTypeBuilder.Factory<T> factory,
            Block... blocks
    ) {
        if (blocks.length == 0) {
            LOGGER.warn("Block entity type {} requires at least one valid block to be defined!", string);
        }

        Util.fetchChoiceType(References.BLOCK_ENTITY, string);
        return Registry.register(
                BuiltInRegistries.BLOCK_ENTITY_TYPE,
                string,
                FabricBlockEntityTypeBuilder.create(factory, blocks).build()
        );
    }

    public static void bootstrap() {}
}
