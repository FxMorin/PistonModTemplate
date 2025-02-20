# Template for PistonLib mods

This is a template PistonLib Mod. It allows you to quickly setup your own piston mod using PistonLib.

## Setup

Setting up the template is pretty straightforward. Everything's been automated!

1. Open the `setup.config` file and change the following values:
   - `mod_id` - Change `pistonmodtemplate` to your new mod id
   - `mod_name` - Change `PistonModTemplate` to your mod name/archive base name *(without spaces)*
   - `maven_group` - Change `ca.fxco` to your package
   - `access_widener` - Set to `true` if you want to use access wideners
   - `mixin` - Set to `true` if you want to use mixin
   - `use_pistonlib_config` - Set to `true` if you want to use pistonlib's config system
   - Make sure there's a new line at the end of the config
2. Now run the setup file
   - On Windows, run `setup.bat`
   - On Mac/Linux, run `setup.sh`
3. You're done.
   - The setup file changes the structure of the template mod, to use your values!
   - Now you can start working on your piston mod

## Piston Mod

### Code Structure
```
📦 ca.fxco.pistonmodtemplate
 ┣ 📂 base
 ┃ ┣ ModBlockEntities
 ┃ ┣ ModBlocks
 ┃ ┣ ModItems
 ┃ ┣ ModPistonFamilies
 ┃ ┗ ModStickyGroups
 ┣ 📂 client
 ┃ ┗ PistonModTemplateClient
 ┗ PistonModTemplate
```

This template mod provides a couple base template examples for different types of implementations. 
You can find all of them by searching for `TODO-TEMPLATE`.  
These templates should be replaced with your own implementations!
