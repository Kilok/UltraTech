package common.cout970.UltraTech.machines.renders;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.machines.models.ModelSolarPanel;


public class RenderSolarPanel extends TileEntitySpecialRenderer{

	private ModelSolarPanel model;

	public RenderSolarPanel() {
		this.model = new ModelSolarPanel();
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y,
			double z, float scale) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		bindTexture(new ResourceLocation("ultratech:textures/misc/solarpanel.png"));
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		
		this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}
}
