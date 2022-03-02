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
    testWall, fCopperWall, fTitaniumWall;

    public void load(){
        testWall = new ConnWall("shaped-wall"){{
            requirements(Category.defense, with(Items.lead, 6));
            health = 420;
        }};
        fCopperWall = new ConnWall("copper-wall"){{
            requirements(Category.defense, with(Items.copper, 6));
            health = 80*4;
        }};
        fTitaniumWall = new ConnWall("titanium-wall"){{
            requirements(Category.defense, with(Items.titanium, 6));
            health = 110*4;
        }};
    }
}
