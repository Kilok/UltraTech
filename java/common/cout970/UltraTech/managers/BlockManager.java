package common.cout970.UltraTech.managers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import api.cout970.UltraTech.Wpower.Machine;
import api.cout970.UltraTech.microparts.CableEntity;
import common.cout970.UltraTech.TileEntities.electric.BoilerEntity;
import common.cout970.UltraTech.TileEntities.electric.CVD_Entity;
import common.cout970.UltraTech.TileEntities.electric.ChargeStationEntity;
import common.cout970.UltraTech.TileEntities.electric.ClimateEntity;
import common.cout970.UltraTech.TileEntities.electric.CutterEntity;
import common.cout970.UltraTech.TileEntities.electric.FermenterEntity;
import common.cout970.UltraTech.TileEntities.electric.FluidGenerator;
import common.cout970.UltraTech.TileEntities.electric.FurnaceEntity;
import common.cout970.UltraTech.TileEntities.electric.GeneratorEntity;
import common.cout970.UltraTech.TileEntities.electric.LavaGeneratorEntity;
import common.cout970.UltraTech.TileEntities.electric.MinerEntity;
import common.cout970.UltraTech.TileEntities.electric.MolecularAssemblyEntity;
import common.cout970.UltraTech.TileEntities.electric.PresuricerEntity;
import common.cout970.UltraTech.TileEntities.electric.PumpEntity;
import common.cout970.UltraTech.TileEntities.electric.PurifierEntity;
import common.cout970.UltraTech.TileEntities.electric.SolarPanelEntity;
import common.cout970.UltraTech.TileEntities.electric.SteamTurbineEntity;
import common.cout970.UltraTech.TileEntities.electric.StorageTier1;
import common.cout970.UltraTech.TileEntities.electric.StorageTier2;
import common.cout970.UltraTech.TileEntities.electric.StorageTier3;
import common.cout970.UltraTech.TileEntities.electric.TesseractEntity;
import common.cout970.UltraTech.TileEntities.electric.WindMillEntity;
import common.cout970.UltraTech.TileEntities.fluid.AluminumPipeEntity;
import common.cout970.UltraTech.TileEntities.fluid.CopperPipeEntity;
import common.cout970.UltraTech.TileEntities.fluid.DestileryEntity;
import common.cout970.UltraTech.TileEntities.fluid.DestileryInEntity;
import common.cout970.UltraTech.TileEntities.fluid.DestileryOutEntity;
import common.cout970.UltraTech.TileEntities.fluid.LeadPipeEntity;
import common.cout970.UltraTech.TileEntities.fluid.TankEntity;
import common.cout970.UltraTech.TileEntities.intermod.DynamoEntity;
import common.cout970.UltraTech.TileEntities.intermod.EnergyTransformer;
import common.cout970.UltraTech.TileEntities.intermod.EngineEntity;
import common.cout970.UltraTech.TileEntities.utility.CrafterEntity;
import common.cout970.UltraTech.TileEntities.utility.HologramEmiterEntity;
import common.cout970.UltraTech.TileEntities.utility.Printer3DEntity;
import common.cout970.UltraTech.TileEntities.utility.ReactorControllerEntity;
import common.cout970.UltraTech.TileEntities.utility.ReactorEntity;
import common.cout970.UltraTech.TileEntities.utility.ReactorTankEntity;
import common.cout970.UltraTech.TileEntities.utility.ReactorWallEntity;
import common.cout970.UltraTech.TileEntities.utility.SteamExtractorEntity;
import common.cout970.UltraTech.TileEntities.utility.WaterBlockEntity;
import common.cout970.UltraTech.TileEntities.utility.hitBoxEntity;
import common.cout970.UltraTech.blocks.ChasisBlock;
import common.cout970.UltraTech.blocks.CovedGlass;
import common.cout970.UltraTech.blocks.DestileryBlock;
import common.cout970.UltraTech.blocks.DiamondGlass;
import common.cout970.UltraTech.blocks.MiscBlock;
import common.cout970.UltraTech.blocks.MultiTank;
import common.cout970.UltraTech.blocks.OreBlock;
import common.cout970.UltraTech.blocks.ReactorMultiblock;
import common.cout970.UltraTech.blocks.StoneBlock;
import common.cout970.UltraTech.blocks.StorageBlock;
import common.cout970.UltraTech.blocks.Tier1Block;
import common.cout970.UltraTech.blocks.Tier2Block;
import common.cout970.UltraTech.blocks.Tier3Block;
import common.cout970.UltraTech.blocks.UT_Block;
import common.cout970.UltraTech.blocks.models.FluidTank;
import common.cout970.UltraTech.blocks.models.LavaGenerator;
import common.cout970.UltraTech.blocks.models.PumpBlock;
import common.cout970.UltraTech.blocks.models.RefineryBlock;
import common.cout970.UltraTech.blocks.models.SteamTurbine;
import common.cout970.UltraTech.blocks.models.WindMill;
import common.cout970.UltraTech.itemBlock.UT_ItemBlock;
import common.cout970.UltraTech.itemBlock.UT_ItemBlockDeco;
import common.cout970.UltraTech.itemBlock.UT_ItemBlockDestilery;
import common.cout970.UltraTech.itemBlock.UT_ItemBlockMisc;
import common.cout970.UltraTech.itemBlock.UT_ItemBlockOre;
import common.cout970.UltraTech.itemBlock.UT_ItemBlockReactor;
import common.cout970.UltraTech.itemBlock.UT_ItemBlockRefinery;
import common.cout970.UltraTech.itemBlock.UT_ItemBlockStone;
import common.cout970.UltraTech.itemBlock.UT_ItemBlockStorage;
import common.cout970.UltraTech.itemBlock.UT_ItemBlockTier1;
import common.cout970.UltraTech.itemBlock.UT_ItemBlockTier2;
import common.cout970.UltraTech.itemBlock.UT_ItemBlockTier3;
import common.cout970.UltraTech.lib.Control;
import common.cout970.UltraTech.multiblocks.refinery.BaseRef;
import common.cout970.UltraTech.multiblocks.refinery.CoreRefinery;
import common.cout970.UltraTech.multiblocks.refinery.OutRef;
import common.cout970.UltraTech.multiblocks.refinery.TileGag;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockManager {

	//blocks	
	public static List<UT_Block> deco = new ArrayList<UT_Block>();
	
	public static Block stoneblock;
	public static  Block Chasis;
	public static  Block Ores;
	public static  Block Reactor;
	public static  Block SolarPanel;
	public static  Block WindMill;
	public static  Block Engine;
	public static  Block Tier1;
	public static  Block Tier2;
	public static  Block Tier3;
	public static  Block Misc;
	public static  Block Storage;
	public static  Block DiamondGlass;
	public static  Block CovedGlass;
	public static  Block AluminumPipe;
	public static  Block CopperPipe;
	public static  Block LeadPipe;
	public static  Block Boiler;
	public static  Block Tank;
	public static  Block Destilery;
	public static  Block Turbine;
	public static  Block Refinery;
	public static  Block Dynamo;
	public static  Block MultiTank;
	public static  Block Pump;
	public static  Block AlienBlock;
	public static  Block LavaGenerator;
	
	public static  Block CableBlock;
	//fluid
	public static Fluid Steam;
	public static Fluid Juice;
	public static Fluid Gas_etanol;
	public static Fluid Etanol;
	public static Fluid Gas_Oil;
	public static Fluid Gasoline;


	public static void InitBlocks(){
		
		Chasis = new ChasisBlock(Material.iron);
		Reactor = new ReactorMultiblock(Material.iron);
		Ores = new OreBlock(Material.rock);
		SolarPanel = new common.cout970.UltraTech.blocks.models.SolarPanel(Material.piston);
		WindMill = new WindMill(Material.piston);
		Engine = new common.cout970.UltraTech.blocks.models.Engine(Material.piston);
//		Transformer = new common.cout970.UltraTech.blocks.models.Transformer(Material.piston);
		Tier1 = new Tier1Block(Material.iron);
		Tier2 = new Tier2Block(Material.iron);
		Tier3 = new Tier3Block(Material.iron);
		Misc = new MiscBlock(Material.rock);
		Storage = new StorageBlock(Material.iron);
		DiamondGlass = new DiamondGlass(Material.glass,false);	
		CovedGlass = new CovedGlass(Material.glass,false);
		AluminumPipe = new common.cout970.UltraTech.blocks.models.AluminumPipe(Material.iron);
		CopperPipe = new common.cout970.UltraTech.blocks.models.CopperPipe(Material.iron);
		LeadPipe = new common.cout970.UltraTech.blocks.models.LeadPipe(Material.iron);
		Boiler = new common.cout970.UltraTech.blocks.models.Boiler(Material.iron);
		Tank = new FluidTank(Material.iron);
		Destilery = new DestileryBlock(Material.iron);
		Turbine = new SteamTurbine(Material.iron);
		Refinery = new RefineryBlock(Material.iron);
		Dynamo = new common.cout970.UltraTech.blocks.models.Dynamo(Material.iron);
		MultiTank = new MultiTank(Material.iron);
		Pump = new PumpBlock(Material.iron);
		AlienBlock = new common.cout970.UltraTech.blocks.AlienBlock(Material.iron);
		LavaGenerator = new LavaGenerator(Material.iron);
		
		if(!Control.isMicroPartActived)CableBlock = new api.cout970.UltraTech.microparts.CableBlock(Material.iron);
		//deco
		
		deco.add(new UT_Block("deco",true));
		deco.add(new UT_Block("deco2",true));
		deco.add(new UT_Block("deco3",true));
		deco.add(new UT_Block("deco4",true));
		deco.add(new UT_Block("deco5",true));
		deco.add(new UT_Block("deco6",true));
		deco.add(new UT_Block("deco7",true));
		deco.add(new UT_Block("deco8",true));
		//white
		deco.add(new UT_Block("deco",false));
		deco.add(new UT_Block("deco2",false));
		deco.add(new UT_Block("deco3",false));
		deco.add(new UT_Block("deco4",false));
		deco.add(new UT_Block("deco5",false));
		deco.add(new UT_Block("deco6",false));
		deco.add(new UT_Block("deco7",false));
		deco.add(new UT_Block("deco8",false));
		
		stoneblock = new StoneBlock(Material.rock);
		//fluid
		Steam = new Fluid("steam");
		Juice = new Fluid("juice");
		Gas_etanol = new Fluid("gas_ethanol");
		Etanol = new Fluid("bioEthanol");
		Gas_Oil = new Fluid("gas_oil");
		Gasoline = new Fluid("gasoline");
		if(!FluidRegistry.isFluidRegistered("steam")){
			FluidRegistry.registerFluid(Steam);
			Steam.setDensity(-5000);
			Steam.setViscosity(1000);
			Steam.setGaseous(true);
		}
		
		if(!FluidRegistry.isFluidRegistered("juice"))FluidRegistry.registerFluid(Juice);
		if(!FluidRegistry.isFluidRegistered("gas_ethanol"))FluidRegistry.registerFluid(Gas_etanol);
		if(!FluidRegistry.isFluidRegistered("bioethanol"))FluidRegistry.registerFluid(Etanol);
		if(!FluidRegistry.isFluidRegistered("gas_oil"))FluidRegistry.registerFluid(Gas_Oil);
		if(!FluidRegistry.isFluidRegistered("gasoline"))FluidRegistry.registerFluid(Gasoline);
	}


	public static void RegisterBlocks(){

		for(UT_Block b : deco)
			GameRegistry.registerBlock(b, UT_ItemBlockDeco.class, b.texture+"_"+(b.black ? "w" : "b"));

		//Blocks & Itemblocks
		GameRegistry.registerBlock(Chasis, UT_ItemBlock.class ,"Chasis_UT");
		GameRegistry.registerBlock(Ores, UT_ItemBlockOre.class ,"Ores_UT");
		GameRegistry.registerBlock(Tier1, UT_ItemBlockTier1.class ,"Tier1_UT");
		GameRegistry.registerBlock(Tier2, UT_ItemBlockTier2.class ,"Tier2_UT");
		GameRegistry.registerBlock(Tier3, UT_ItemBlockTier3.class ,"Tier3_UT");
		GameRegistry.registerBlock(Storage, UT_ItemBlockStorage.class ,"Storage_UT");
//		GameRegistry.registerBlock(Cable, UT_ItemBlockCable.class ,"Cable_UT");
		GameRegistry.registerBlock(Reactor, UT_ItemBlockReactor.class, "ReactorMultiblock");
		GameRegistry.registerBlock(Misc, UT_ItemBlockMisc.class, "Misc_UT");
		GameRegistry.registerBlock(Destilery, UT_ItemBlockDestilery.class, "Destilery_UT");
		GameRegistry.registerBlock(Refinery, UT_ItemBlockRefinery.class, "Refinery_UT");
		GameRegistry.registerBlock(stoneblock, UT_ItemBlockStone.class, "StoneBlock");
		
		GameRegistry.registerBlock(SolarPanel, "SolarPanel");
		GameRegistry.registerBlock(WindMill, "WindMill");
		GameRegistry.registerBlock(Engine, "Engine");
//		GameRegistry.registerBlock(Transformer, "Transformer");
		GameRegistry.registerBlock(DiamondGlass, "Diamond_Glass");
		GameRegistry.registerBlock(CovedGlass, "Coved_Glass");
		GameRegistry.registerBlock(AluminumPipe, "AluminumPipe");
		GameRegistry.registerBlock(CopperPipe, "CopperPipe");
		GameRegistry.registerBlock(LeadPipe, "LeadPipe");
		GameRegistry.registerBlock(Boiler, "Boiler");
		GameRegistry.registerBlock(Tank, "FluidTank");
		GameRegistry.registerBlock(Turbine, "SteamTurbine");
		GameRegistry.registerBlock(Dynamo, "Dynamo");
		GameRegistry.registerBlock(MultiTank, "MultiTank");
		GameRegistry.registerBlock(Pump, "Pump");
		GameRegistry.registerBlock(AlienBlock, "AlienBlock");
		GameRegistry.registerBlock(LavaGenerator, "Lava Generator");
		
		if(!Control.isMicroPartActived)GameRegistry.registerBlock(CableBlock, "UT_CableBlock");
		//TileEntities
		
		GameRegistry.registerTileEntity(Machine.class, "Energy_UT");
		GameRegistry.registerTileEntity(CVD_Entity.class, "cvd_UT");
		GameRegistry.registerTileEntity(FurnaceEntity.class, "furnace_UT");
		GameRegistry.registerTileEntity(StorageTier1.class, "Storage1_UT");
		GameRegistry.registerTileEntity(StorageTier2.class, "Storage2_UT");
		GameRegistry.registerTileEntity(StorageTier3.class, "Storage3_UT");
		GameRegistry.registerTileEntity(hitBoxEntity.class, "hitBox_UT");
		GameRegistry.registerTileEntity(CutterEntity.class, "cutter_UT");
		GameRegistry.registerTileEntity(PurifierEntity.class, "purifier_UT");
		GameRegistry.registerTileEntity(PresuricerEntity.class, "presuricer_UT");
		GameRegistry.registerTileEntity(GeneratorEntity.class, "generator_UT");
		GameRegistry.registerTileEntity(MinerEntity.class, "miner_UT");
		GameRegistry.registerTileEntity(ReactorWallEntity.class, "reactorwall_UT");
		GameRegistry.registerTileEntity(ReactorTankEntity.class, "ReactorTank_UT");
		GameRegistry.registerTileEntity(SteamExtractorEntity.class, "SteamTurbine_UT");
		GameRegistry.registerTileEntity(WaterBlockEntity.class, "WaterBlock_UT");
		GameRegistry.registerTileEntity(MolecularAssemblyEntity.class, "MolecularAssembly_UT");
		GameRegistry.registerTileEntity(ChargeStationEntity.class, "ChargeStation_UT");
		GameRegistry.registerTileEntity(SolarPanelEntity.class, "SolarPanel_UT");
		GameRegistry.registerTileEntity(WindMillEntity.class, "WindMill_UT");
		GameRegistry.registerTileEntity(Printer3DEntity.class, "Printer3D_UT");
		GameRegistry.registerTileEntity(ReactorControllerEntity.class, "ReactorController_UT");
		GameRegistry.registerTileEntity(HologramEmiterEntity.class, "HologramEmiter_UT");
		GameRegistry.registerTileEntity(CrafterEntity.class, "Crafter_UT");
		GameRegistry.registerTileEntity(EngineEntity.class, "Engine_UT");
		GameRegistry.registerTileEntity(ReactorEntity.class, "Reactor_UT");
		GameRegistry.registerTileEntity(EnergyTransformer.class, "TransFormer_UT");
		GameRegistry.registerTileEntity(TesseractEntity.class, "Tesseract_UT");
		GameRegistry.registerTileEntity(FermenterEntity.class, "Fermenter_UT");
		GameRegistry.registerTileEntity(ClimateEntity.class, "Climate_UT");
		GameRegistry.registerTileEntity(AluminumPipeEntity.class, "AluminumPipeEntity_UT");
		GameRegistry.registerTileEntity(CopperPipeEntity.class, "CopperPipeEntity_UT");
		GameRegistry.registerTileEntity(LeadPipeEntity.class, "LeadPipeEntity_UT");
		GameRegistry.registerTileEntity(BoilerEntity.class, "Boiler_UT");
		GameRegistry.registerTileEntity(DestileryEntity.class, "Destilery_UT");
		GameRegistry.registerTileEntity(DestileryInEntity.class, "RefineryIn_UT");
		GameRegistry.registerTileEntity(DestileryOutEntity.class, "RefineryOut_UT");
		GameRegistry.registerTileEntity(TankEntity.class, "Tank_UT");
		GameRegistry.registerTileEntity(FluidGenerator.class, "FluidGen_UT");
		GameRegistry.registerTileEntity(SteamTurbineEntity.class, "Turbine_UT");
		GameRegistry.registerTileEntity(CoreRefinery.class, "RefineryCore_UT");
		GameRegistry.registerTileEntity(BaseRef.class, "RefBase_UT");
		GameRegistry.registerTileEntity(TileGag.class, "RefGag_UT");
		GameRegistry.registerTileEntity(OutRef.class, "RefOut_UT");
		GameRegistry.registerTileEntity(DynamoEntity.class, "Dynamo_UT");
		GameRegistry.registerTileEntity(PumpEntity.class, "Pump_UT");
		GameRegistry.registerTileEntity(LavaGeneratorEntity.class, "LavaGenerator_UT");
		if(!Control.isMicroPartActived)GameRegistry.registerTileEntity(CableEntity.class, "CableEntity_UT");
		
		Language.AddBlockNames();
	}

}
