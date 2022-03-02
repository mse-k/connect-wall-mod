package connwall.content;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.math.geom.Point2;
import arc.struct.BoolSeq;
import arc.struct.IntMap;
import arc.struct.OrderedSet;
import arc.struct.Seq;
import arc.util.Tmp;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.Vars;
import mindustry.gen.Building;
import mindustry.gen.Bullet;
import mindustry.world.blocks.defense.Wall;

import static mindustry.Vars.tilesize;

public class ConnWall extends Wall{
	protected static final int defaultKey = "00000000".hashCode();
	
	protected static final int[] needCheckPoint = {4, 5, 6, 7};
	
	protected static final int[][] tileKey = {
		{5, 1, 4},
		{2,    0},
		{6, 3, 7}
	};
	
	//DO NOT USE Geometry.d8. This array is designed to make the load and match method more brief.
	protected static final Point2[] traverseKey = {
		new Point2(1, 0),
		new Point2(0, 1),
		new Point2(-1, 0),
		new Point2(0, -1),
		
		//Edge Points Needed To Be Checked. Index from 4 to 7.
		
		new Point2(1, 1),
		new Point2(-1, 1),
		new Point2(-1, -1),
		new Point2(1, -1)
	};
	
	public final IntMap<TextureRegion> sprites = new IntMap<>();
	
	public ConnWall(String name){
		super(name);
		size = 1;
	}
	
	@Override
	public void load(){
		super.load();
		sprites.put(defaultKey, Core.atlas.find(name));
		loop: for(int i = 0; i < 256; i ++){
			String key = Integer.toBinaryString(i);
			StringBuilder builder = new StringBuilder();
			for(int j = 0; j < 8 - key.length(); j++)builder.append(0);
			builder.append(key);
			for(int j : needCheckPoint){
				if(builder.charAt(j) == '0')continue;
				if(builder.charAt((j - 3) % 4) != '1' || builder.charAt(j - 4) != '1')continue loop;
			}
			key = builder.toString();
			if(key.startsWith("0000"))continue;
			sprites.put(key.hashCode(), Core.atlas.find(name + "-" + key));
		}
	}
	
	public class ConnWallBuild extends Building{
		public BoolSeq proximityWalls = new BoolSeq(8);
		protected int drawKey = defaultKey;
		
		public void updateKey(){
			StringBuilder builder = new StringBuilder();
			
			for(int i = 0; i < 8; i++){
				builder.append(Mathf.num(proximityWalls.get(i)));
			}
			
			for(int i : needCheckPoint){
				if(builder.charAt((i - 3) % 4) != '1' || builder.charAt((i - 4) % 4) != '1')builder.setCharAt(i, '0');
			}
			
			String key = builder.toString();
			drawKey = key.hashCode();
			
			if(key.startsWith("0000"))drawKey = defaultKey;
		}
		
		public void computePoint(Point2 point){
			/*
				5 1 4
				2 x 0
				6 3 7
			 */
			int x = point.x, y = point.y;
			
			for(int i = 0; i < traverseKey.length; i++){
				if(point.equals(traverseKey[i])){
					proximityWalls.set(i, !proximityWalls.get(i));
					break;
				}
			}
			
			updateKey();
		}
	
		public void updateIndexKey(){
			for(Point2 index : traverseKey){
				Building build = Vars.world.build(tileX() + index.x, tileY() + index.y);
				if(build instanceof ConnWallBuild){
					computePoint(Tmp.p1.set(build.tileX() - tileX(), build.tileY() - tileY()));
					((ConnWallBuild)build).computePoint(Tmp.p1.set(tileX() - build.tileX(), tileY() - build.tileY()));
				}
			}
		}
		
		@Override
		public void draw(){
			TextureRegion t = sprites.get(drawKey);
			if(t != null)Draw.rect(t, x, y);
		}
		
		@Override
		public void created(){
			super.created();
			if(Vars.net.active() && !Vars.headless){
				initSeq();
				updateIndexKey();
			}
		}
		
		@Override
		public void placed(){
			super.placed();
			
			initSeq();
			
			updateIndexKey();
		}
		
		public void initSeq(){
			if(proximityWalls.size < 8){
				proximityWalls.clear();
				for(int i = 0; i < 8; i++){
					proximityWalls.add(false);
				}
			}
		}
		
		@Override
		public void onRemoved(){
			super.onRemoved();
			updateIndexKey();
		}
		
		/*@Override
		public void write(Writes write){
			super.write(write);
			write.i(drawKey);
			if(proximityWalls.size == 8)for(int i = 0; i < 8; i++){
				write.bool(proximityWalls.get(i));
			}else for(int i = 0; i < 8; i++){
				write.bool(false);
			}
		}
		
		@Override
		public void read(Reads read, byte revision){
			super.read(read, revision);
			drawKey = read.i();
			for(int i = 0; i < 8; i++){
				proximityWalls.add(read.bool());
			}
		}*/ //removal needed for compatibility, probably bad tho
		
		@Override
		public void read(Reads read, byte revision){
			super.read(read, revision);
			initSeq();
			updateIndexKey();
		}
	}
}
