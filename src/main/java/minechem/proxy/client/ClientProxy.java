package minechem.proxy.client;

import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import minechem.Compendium;
import minechem.apparatus.tier1.centrifuge.CentrifugeTileEntity;
import minechem.apparatus.tier1.centrifuge.CentrifugeTileEntityRenderer;
import minechem.apparatus.tier1.electricCrucible.ElectricCrucibleTileEntity;
import minechem.apparatus.tier1.electricCrucible.ElectricCrucibleTileEntityRenderer;
import minechem.apparatus.tier1.electrolysis.ElectrolysisTileEntity;
import minechem.apparatus.tier1.electrolysis.ElectrolysisTileEntityRenderer;
import minechem.apparatus.tier1.opticalMicroscope.OpticalMicroscopeTileEntity;
import minechem.apparatus.tier1.opticalMicroscope.OpticalMicroscopeTileEntityRenderer;
import minechem.blocks.LightRenderer;
import minechem.handler.ResourceReloadListener;
import minechem.helper.LogHelper;
import minechem.item.chemical.ChemicalItemRenderer;
import minechem.proxy.CommonProxy;
import minechem.registry.BlockRegistry;
import minechem.registry.ItemRegistry;
import net.afterlifelochie.fontbox.font.FontException;
import net.afterlifelochie.fontbox.font.GLFont;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import org.apache.logging.log4j.Level;

public class ClientProxy extends CommonProxy
{
    @Override
    public World getClientWorld()
    {
        return FMLClientHandler.instance().getClient().world;
    }

    @Override
    public void registerRenderers()
    {
//        TODO: rendering
//        RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
//        ISBRH_ID = RenderingRegistry.getNextAvailableRenderId();
//
//        OpticalMicroscopeTileEntityRenderer opticalMicroscopeRenderer = new OpticalMicroscopeTileEntityRenderer();
//        ClientRegistry.bindTileEntitySpecialRenderer(OpticalMicroscopeTileEntity.class, opticalMicroscopeRenderer);
//        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockRegistry.opticalMicroscope),
//            new BasicItemRenderer(opticalMicroscopeRenderer, new OpticalMicroscopeTileEntity()));
//
//        ElectrolysisTileEntityRenderer electrolysisRenderer = new ElectrolysisTileEntityRenderer();
//        ClientRegistry.bindTileEntitySpecialRenderer(ElectrolysisTileEntity.class, electrolysisRenderer);
//        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockRegistry.electrolysisBlock),
//            new BasicItemRenderer(electrolysisRenderer, new ElectrolysisTileEntity()));
//
//        ElectricCrucibleTileEntityRenderer electricCrucibleRenderer = new ElectricCrucibleTileEntityRenderer();
//        ClientRegistry.bindTileEntitySpecialRenderer(ElectricCrucibleTileEntity.class, electricCrucibleRenderer);
//        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockRegistry.electricCrucibleBlock),
//            new BasicItemRenderer(electricCrucibleRenderer, new ElectricCrucibleTileEntity()));
//
//        CentrifugeTileEntityRenderer centrifugeRenderer = new CentrifugeTileEntityRenderer();
//        ClientRegistry.bindTileEntitySpecialRenderer(CentrifugeTileEntity.class, centrifugeRenderer);
//        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockRegistry.centrifugeBlock),
//            new BasicItemRenderer(centrifugeRenderer, new CentrifugeTileEntity()));
//
//        RenderingRegistry.registerBlockHandler(BlockRegistry.blockLight.getRenderType(), new LightRenderer());
//        MinecraftForgeClient.registerItemRenderer(ItemRegistry.chemicalItem, new ChemicalItemRenderer());
    }

    @Override
    public void registerResourcesListener()
    {
        LogHelper.debug("Registering Resource Reload Listener...");
        ((IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager())
            .registerReloadListener(new ResourceReloadListener());
    }

    /**
     * Get the current lang code
     *
     * @return eg. 'en_US'
     */
    public String getCurrentLanguage()
    {
        return Minecraft.getMinecraft().getLanguageManager().getCurrentLanguage().getLanguageCode();
    }

    @Override
    public World getWorld(MessageContext context)
    {
        return Minecraft.getMinecraft().world;
    }

    @Override
    public EntityPlayer getPlayer(MessageContext context)
    {
        return Minecraft.getMinecraft().player;
    }

    @Override
    public void registerFonts()
    {
        try
        {
            GLFont.fromTTF(Compendium.Fontbox.tracer(), 22.0f, new ResourceLocation(Compendium.Naming.id, "fonts/daniel.ttf"));
            GLFont.fromTTF(Compendium.Fontbox.tracer(), 22.0f, new ResourceLocation(Compendium.Naming.id, "fonts/notethis.ttf"));
            GLFont.fromTTF(Compendium.Fontbox.tracer(), 22.0f, new ResourceLocation(Compendium.Naming.id, "fonts/ampersand.ttf"));
        } catch (FontException font)
        {
            LogHelper.exception(font, Level.ERROR);
        }
    }

}
