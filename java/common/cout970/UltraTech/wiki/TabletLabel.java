package common.cout970.UltraTech.wiki;

import common.cout970.UltraTech.lib.UT_Utils;

import net.minecraft.client.renderer.texture.TextureManager;

public class TabletLabel {

	public String info = "";
	public int color = UT_Utils.RGBtoInt(0, 0, 0);
	public int posX,posY;

	public TabletLabel(int x,int y){
		posX = x;
		posY = y;
	}
	public void drawLabel(TextureManager t){

	}
}
