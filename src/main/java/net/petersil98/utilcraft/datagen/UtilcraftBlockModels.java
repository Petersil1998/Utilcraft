package net.petersil98.utilcraft.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.util.Direction;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.blocks.sideslabs.SideSlabBlock;

public class UtilcraftBlockModels extends BlockModelProvider {

    public UtilcraftBlockModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Utilcraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        registerSideSlab();
    }

    private void registerSideSlab() {
        withExistingParent(SideSlabBlock.NAME,BLOCK_FOLDER+"/block")
                .texture("particle", "#side")
                .element().from(0, 0, 0).to( 16, 16, 8)
                    .face(Direction.DOWN).texture("#bottom").end()
                    .face(Direction.UP).texture("#top").end()
                    .face(Direction.NORTH).texture("#side").cullface(Direction.NORTH).end()
                    .face(Direction.SOUTH).texture("#side").end()
                    .face(Direction.WEST).texture("#side").end()
                    .face(Direction.EAST).texture("#side").end()
                .end();
    }
}
