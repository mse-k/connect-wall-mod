package connwall;

import arc.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import connwall.content.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

public class ConnWallMod extends Mod{

    public ConnWallMod(){
        //Log.info("Loaded ExampleJavaMod constructor.");

        //listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
            //show dialog upon startup
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("frog");
                dialog.cont.add("behold... Amogus!").row();
                //mod sprites are prefixed with the mod name (this mod is called 'example-java-mod' in its config)
                dialog.cont.button("Quite sussy.", dialog::hide).size(100f, 50f);
                dialog.show();
            });
        });
    }
    
    public void init(){//loadContent(){
        new ExBlocks().load();
    }

}
