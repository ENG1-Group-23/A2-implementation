package bytemusketeers.heslingtonhustle.entity;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * TODO
 *
 * @author ENG1 Team 25
 * @author ENG1 Team 23
 */
public class Entity extends Sprite {
    public float worldX;
    public float worldY;
    public float speed; // walking speed per frame
    public Animation<TextureRegion> currentAnimation;
    public float stateTime; // Tracks animation time

}
