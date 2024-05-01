package bytemusketeers.heslingtonhustle.entity;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Entity extends Sprite {
    public float worldX, worldY;
    public float speed; // walking speed per frame
    public Animation<TextureRegion> currentAnimation;
    public float stateTime; // Tracks animation time
    private TextureRegion tr;

    public TextureRegion getTr() {
        return tr;
    }

    public void setTr(TextureRegion tr) {
        this.tr = tr;
    }


}
