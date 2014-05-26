package common.cout970.UltraTech.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.Tier2.FurnaceEntity;
import common.cout970.UltraTech.lib.UT_Utils;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class UTfurnaceGui extends GuiContainer{

	private FurnaceEntity entity;
	
	public UTfurnaceGui(Container par1Container,InventoryPlayer ip ,FurnaceEntity entity) {
		super(par1Container);
		this.entity = entity;
		}


	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/utfurnace.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

		//progres bar
		int i1 = (int) entity.progres*24/1000;
		this.drawTexturedModalRect(xStart + 85, yStart + 31, 176, 14, i1 + 1, 16);

		//energy bar  
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/energy.png"));
		int p = (int) (entity.getPower().getCharge()*50/entity.getPower().maxCharge());
		this.drawTexturedModalRect(xStart+14, yStart+15+(50-p), 0, 0, 25, p);
		
		this.drawCenteredString(fontRendererObj, "Speed upgrades: "+entity.speedUpgrades+"/5", xStart+95, yStart+70, UT_Utils.RGBtoInt(255, 255, 255));
	}

	@Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {

		String s = this.entity.getInventoryName();
        this.fontRendererObj.drawString(s, this.xSize - 130, 6, 4210752);
        
        //text
        int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		
        if(UT_Utils.isIn(x, y, xStart+14, yStart+15, 25, 50)){
        	List<String> energy = new ArrayList<String>();
        	energy.add("Energy: "+((int)entity.getPower().getCharge())+"FT");
        	this.drawHoveringText(energy, x-xStart, y-yStart, fontRendererObj);
        	RenderHelper.enableGUIStandardItemLighting();
        }
	}
}
