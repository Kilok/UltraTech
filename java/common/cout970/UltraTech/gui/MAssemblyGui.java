package common.cout970.UltraTech.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.electric.MolecularAssemblyEntity;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.lib.UT_Utils;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class MAssemblyGui extends GuiContainer{

	public MolecularAssemblyEntity tile;
	
	public MAssemblyGui(Container par1Container,InventoryPlayer ip ,MolecularAssemblyEntity entity) {
		super(par1Container);
		tile = entity;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/ma.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
		
		//progres bar
		int i1 = (int) this.tile.progres*24/1000;
		this.drawTexturedModalRect(xStart + 118, yStart + 33, 176, 14, i1, 16);

		
		//energy
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/energy.png"));
		int p = (int) (tile.getEnergy()*50/tile.maxEnergy());
		this.drawTexturedModalRect(xStart+14, yStart+15+(50-p), 0, 0, 25, p);
		
		this.drawCenteredString(fontRendererObj, "Precision crafter", xStart+65, yStart+4, UT_Utils.RGBtoInt(255, 255, 255));
	}
	
	@Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {

        //text
        int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		
        if(UT_Utils.isIn(x, y, xStart+14, yStart+15, 25, 50)){
        	List<String> energy = new ArrayList<String>();
        	energy.add("Energy: "+((int)tile.getEnergy())+EnergyCosts.E);
        	this.drawHoveringText(energy, x-xStart, y-yStart, fontRendererObj);
        	RenderHelper.enableGUIStandardItemLighting();
        }
	}

}
