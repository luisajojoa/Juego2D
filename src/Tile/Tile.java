package Tile;

import gfx.Screen;
import level.Level;

public abstract class Tile {

    public static Tile[] tiles = new Tile[256]; //16*16
    public static Tile VOID = new SolidTile(10, 0, 0, 0x15C0 , 0x3301 , 0x57CA , 0b011001100000001);
    public static Tile STONE = new SolidTile(4, 4, 0, 0x6E67, 0x5CE3, 0x776C, 0x5E64);
    public static Tile GRASS = new BaseTile(5, 2, 0, 0x6E67, 0x5E64, 0x776C, 0x5E64);
    
    //6E67 5CE3 776C
    //
    protected byte id;
    protected boolean solid;
    protected boolean emitter;

    public Tile(int id, boolean isSolid, boolean isEmitter) {
        this.id = (byte)id;
        if (tiles[id] != null) throw new RuntimeException("Duplicate tile id on" + id);
        tiles[id] = this;
            
        this.solid = isSolid;
        this.emitter = isEmitter;
    }

    public abstract void render(Screen screen, Level level, int x, int y);

    public boolean isSolid() {
        return solid;
    }

    public boolean isEmitter() {
        return emitter;
    }
    
    public byte getID(){
        return id;
    }
}
