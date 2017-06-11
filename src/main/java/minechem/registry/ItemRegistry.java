package minechem.registry;

import net.minecraftforge.fml.common.registry.GameRegistry;
import minechem.item.augment.AugmentedItem;
import minechem.item.chemical.ChemicalItem;
import minechem.item.journal.JournalItem;
import minechem.recipes.AugmentRecipe;
import minechem.recipes.WrapperRecipe;

public class ItemRegistry
{
    public static JournalItem journal;
    public static AugmentedItem augmentedItem;
    public static ChemicalItem chemicalItem;

    public static void init()
    {
        journal = new JournalItem();
        GameRegistry.register(journal);

        augmentedItem = new AugmentedItem();
        GameRegistry.register(augmentedItem);
        GameRegistry.addRecipe(new AugmentRecipe());
        GameRegistry.addRecipe(new WrapperRecipe());

        chemicalItem = new ChemicalItem();
        GameRegistry.register(chemicalItem);
    }

}
