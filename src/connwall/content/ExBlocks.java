package connwall.content;

import mindustry.ctype.ContentList;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Wall;
import connwall.content.*;

import static mindustry.type.ItemStack.*;

public class ExBlocks implements ContentList{

    public static Block
    testWall;

    public void load(){
        testWall = new ConnWall("test-wall"){{
            requirements(Category.defense, with(Items.lead, 6));
            health = 420;
        }};
    }
}
