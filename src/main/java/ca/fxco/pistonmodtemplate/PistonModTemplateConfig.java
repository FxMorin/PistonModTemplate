package ca.fxco.pistonmodtemplate;

import ca.fxco.pistonlib.api.config.Category;
import ca.fxco.pistonlib.api.config.ConfigValue;

public class PistonModTemplateConfig {

    // TODO-TEMPLATE
    @ConfigValue(
            desc = "This is an example config option",
            keyword = {"template", "example"},
            category = {Category.WIP, Category.EXPERIMENTAL}
    )
    public static boolean templateConfigOption = true;
}
