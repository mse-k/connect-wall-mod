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
    testWall, testWallPlast, fCopperWall, fTitaniumWall, fPlastaniumWall;

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
        fCopperWall = new ConnWall("copper-wall"){{
            requirements(Category.defense, with(Items.copper, 6));
            health = 80 * 4;
        }};
        fTitaniumWall = new ConnWall("titanium-wall"){{
            requirements(Category.defense, with(Items.titanium, 6));
            health = 110 * 4;
        }};
        fPlastaniumWall = new Wall("plastanium-wall"){{
            requirements(Category.defense, with(Items.plastanium, 5, Items.metaglass, 2));
            health = 125 * 4;
            insulated = true;
            absorbLasers = true;
            schematicPriority = 10;
        }};
    }
}
