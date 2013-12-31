package common.cout970.UltraTech.proxy;

import common.cout970.UltraTech.machines.models.ReactorTankRender;
import common.cout970.UltraTech.machines.models.SateliteRender;
import common.cout970.UltraTech.machines.tileEntities.ReactorTankEntity;
import common.cout970.UltraTech.machines.tileEntities.SateliteEntity;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy{
	
	public static int BlockRenderTipe;
	public static int renderPass;

	@Override
	public void registerRenders() {
		ClientRegistry.bindTileEntitySpecialRenderer(SateliteEntity.class, new SateliteRender());
		ClientRegistry.bindTileEntitySpecialRenderer(ReactorTankEntity.class, new ReactorTankRender());
		setCustomRenderers();
	}
	
	  public static void setCustomRenderers()
      {
              BlockRenderTipe = RenderingRegistry.getNextAvailableRenderId();
              RenderingRegistry.registerBlockHandler(new Block_UT_Render());
      }
}