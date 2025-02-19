package ca.fxco.pistonmodtemplate.base;

import ca.fxco.pistonlib.api.pistonLogic.sticky.StickRules;
import ca.fxco.pistonlib.api.pistonLogic.sticky.StickyGroup;
import ca.fxco.pistonlib.api.pistonLogic.sticky.StickyGroups;

import static ca.fxco.pistonmodtemplate.PistonModTemplate.id;

/**
 * Holds instances of all sticky groups registered by this mod.</br>
 * PistonLib does the same thing in {@link ca.fxco.pistonlib.base.ModStickyGroups}
 */
public class ModStickyGroups {

    // TODO-TEMPLATE
    public static final StickyGroup TEMPLATE_STICKY_GROUP = StickyGroups.register(
            id("template_sticky_group"),
            new StickyGroup(StickRules.STRICT_SAME)
    );

    public static void bootstrap() {}
}
