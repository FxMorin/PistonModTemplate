package ca.fxco.pistonmodtemplate;

import ca.fxco.pistonlib.api.PistonLibInitializer;
import ca.fxco.pistonlib.api.config.ConfigFieldEntrypoint;
import ca.fxco.pistonmodtemplate.base.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/** THIS CLASS IS ONLY USED DURING THE SETUP! */
public class PistonModTemplatePistonLibConfig implements ModInitializer, PistonLibInitializer, ConfigFieldEntrypoint {

    public static final String MOD_ID = "pistonmodtemplate";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {}

    @Override
    public void initialize() {}

    @Override
    public Map<String, List<Field>> getConfigFields() {
        return Map.of("pistonlib", List.of(PistonModTemplateConfig.class.getFields()));
    }

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
