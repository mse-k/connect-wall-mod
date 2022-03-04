package connwall.content;

import mindustry.ctype.ContentList;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Wall;
import connwall.content.*;
import mindustry.content.*;

import static mindustry.type.ItemStack.*;

public class ExBlocks implements ContentList{

    public static Block
    testWall, testWallPlast, testWallTit, testWallThor, fCopperWall, fTitaniumWall, fPlastaniumWall, fPhaseWall;

    public void load(){
        testWall = new ConnWall("shaped-wall"){{
            requirements(Category.defense, with(Items.lead, 6));
            health = 420;
        }};
        testWallPlast = new ConnWall("shaped-wall-plast"){{
            requirements(Category.defense, with(Items.metaglass, 6));
            health = 420;
            insulated = true;
            absorbLasers = true;
        }};
        testWallTit = new ConnWall("shaped-wall-titanium"){{
            requirements(Category.defense, with(Items.silicon, 6));
            health = 420;
        }};
        testWallThor = new ConnWall("shaped-wall-thor"){{
            requirements(Category.defense, with(Items.coal, 6));
            health = 420;
        }};
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
    }
}
