package Tile;

public class SolidTile extends BaseTile{

    public SolidTile(int id, int x, int y, int color1, int color2, int color3, int color4) {
        super(id, x, y, color1, color2, color3, color4);
        this.solid = true;
    }

}
