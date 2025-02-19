package ca.fxco.pistonmodtemplate.base;

import ca.fxco.pistonlib.api.pistonLogic.families.PistonFamilies;
import ca.fxco.pistonlib.api.pistonLogic.families.PistonFamily;
import ca.fxco.pistonlib.pistonLogic.families.PistonFamilyImpl;

import static ca.fxco.pistonmodtemplate.PistonModTemplate.id;

/**
 * Holds instances of all piston families registered by this mod.</br>
 * PistonLib does the same thing in {@link ca.fxco.pistonlib.base.ModPistonFamilies}
 */
public class ModPistonFamilies {

    // TODO-TEMPLATE - This will crash, either remove it or implement it correctly!
    public static final PistonFamily TEMPLATE = register("template", PistonFamilyImpl.builder());

    private static PistonFamily register(String name, PistonFamily.Builder familyBuilder) {
        return register(name, familyBuilder.build());
    }

    private static PistonFamily register(String name, PistonFamily family) {
        return PistonFamilies.register(id(name), family);
    }

    public static void bootstrap() {}
}
