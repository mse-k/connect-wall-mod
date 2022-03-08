package connwall.content;

import java.lang.reflect.*;
import mindustry.ctype.ContentList;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Wall;
import connwall.content.*;
import mindustry.content.*;
import mindustry.ctype.*;
import mindustry.*;
import arc.struct.*;
import arc.util.*;

import static mindustry.type.ItemStack.*;

public class ExBlocks implements ContentList{

    /*protected void replaceBlock(Block oldBlock, Block newBlock) {
        var blockMap = Reflect.<ObjectMap<String, MappableContent>[]>get(Vars.content, "contentNameMap")[ContentType.block.ordinal()];
        blockMap.set(oldBlock.name, newBlock); // Replace old block in the name map
        blockMap.remove(newBlock.name); // Remove the old name of the new block in the name map
        //newBlock.name = oldBlock.name; // Change the name
        oldBlock = newBlock; // Replace the old block with the new one
    }*/
    
    /*public static Block
    
    //test walls (grey)
    //testWall, testWallPlast, testWallTit, testWallThor,

    //fake walls for testing
    fCopperWall, fTitaniumWall, fPlastaniumWall, fPhaseWall, fThoriumWall, fSurgeWall;*/

    public void load(){
        /*//testWall = new ConnWall("shaped-wall"){{
        //    requirements(Category.defense, with(Items.lead, 6));
        //    health = 420;
        //}};
        //testWallPlast = new ConnWall("shaped-wall-plast"){{
        //    requirements(Category.defense, with(Items.metaglass, 6));
        //    health = 420;
        //    insulated = true;
        //    absorbLasers = true;
        //}};
        //testWallTit = new ConnWall("shaped-wall-titanium"){{
        //    requirements(Category.defense, with(Items.silicon, 6));
        //    health = 420;
        //}};
        //testWallThor = new ConnWall("shaped-wall-thor"){{
        //    requirements(Category.defense, with(Items.coal, 6));
        //    health = 420;
        //}};
        fCopperWall = new ConnWall("copper-wall"){{
            requirements(Category.defense, with(Items.copper, 6));
            health = 80 * 4;
        }};
        fTitaniumWall = new ConnWall("titanium-wall"){{
            requirements(Category.defense, with(Items.titanium, 6));
            health = 110 * 4;
        }};
        fPlastaniumWall = new ConnWall("plastanium-wall"){{
            requirements(Category.defense, with(Items.plastanium, 5, Items.metaglass, 2));
            health = 125 * 4;
            insulated = true;
            absorbLasers = true;
            schematicPriority = 10;
        }};
        fPhaseWall = new ConnWall("phase-wall"){{
            requirements(Category.defense, with(Items.phaseFabric, 6));
            health = 150 * 4;
            chanceDeflect = 10f;
            flashHit = true;
        }};
        fThoriumWall = new ConnWall("thorium-wall"){{
            requirements(Category.defense, with(Items.thorium, 6));
            health = 200 * 4;
        }};
        fSurgeWall = new ConnWall("surge-wall"){{
            requirements(Category.defense, with(Items.surgeAlloy, 6));
            health = 230 * 4;
            lightningChance = 0.05f;
        }};
        replaceBlock(Blocks.copperWall, fCopperWall);
        replaceBlock(Blocks.titaniumWall, fTitaniumWall);
        replaceBlock(Blocks.plastaniumWall, fPlastaniumWall);
        replaceBlock(Blocks.thoriumWall, fThoriumWall);
        replaceBlock(Blocks.phaseWall, fPhaseWall);
        replaceBlock(Blocks.surgeWall, fSurgeWall);*/
        var blockMap = Reflect.<ObjectMap<String, MappableContent>[]>get(Vars.content, "contentNameMap")[ContentType.block.ordinal()];
        Vars.content.blocks().<Wall>each(b -> b instanceof Wall && !(b instanceof ConnWall) && b.size == 1 && b != Blocks.scrapWall, b -> {
            blockMap.remove(b.name); // Remove old one
            b = new ConnWall(b.name);
        });
    }
}
