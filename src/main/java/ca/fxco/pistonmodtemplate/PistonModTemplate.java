package ca.fxco.pistonmodtemplate;

import ca.fxco.pistonmodtemplate.base.*;
import ca.fxco.pistonlib.api.PistonLibInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PistonModTemplate implements ModInitializer, PistonLibInitializer {

    public static final String MOD_ID = "pistonmodtemplate";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {}

    @Override
    public void initialize() {}

    @Override
    public void registerPistonFamilies() {
        ModPistonFamilies.bootstrap();
    }

    @Override
    public void registerStickyGroups() {
        ModStickyGroups.bootstrap();
    }

    @Override
    public void bootstrap() {
        ModBlocks.bootstrap();
        ModBlockEntities.bootstrap();
        ModItems.bootstrap();
    }

    public static ResourceLocation id(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }
}
