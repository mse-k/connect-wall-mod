package example.content;

import mindustry.ctype.ContentList;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Wall;

import static mindustry.type.ItemStack.*;

public class ExBlocks implements ContentList{

    public static Block
    testWall

    @Override
    public void load(){
        testWall = new ConnWall("test-wall"){{
            requirements(Category.defense, with(Items.lead, 6));
            health = 420;
        }};
    }
}
