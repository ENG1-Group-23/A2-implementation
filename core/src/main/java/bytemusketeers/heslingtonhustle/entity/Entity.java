package bytemusketeers.heslingtonhustle.entity;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

/**
 * An {@link Entity} represents an animatable {@link Sprite} with a {@link bytemusketeers.heslingtonhustle.map.GameMap}-
 * respecting position and speed.
 *
 * @author ENG1 Team 25
 * @author ENG1 Team 23
 */
public class Entity extends Sprite implements Disposable {
    /**
     * The X position, relative to the world
     */
    public float worldX;

    /**
     * The Y position, relative to the world.
     */
    public float worldY;

    /**
     * The walking/animation speed per frame
     */
    public float speed;

    /**
     * The relevant LibGDX {@link Animation}
     */
    public Animation<TextureRegion> currentAnimation;
    /**
     * The {@link #currentAnimation} state time
     */
    public float stateTime;
    private TextureRegion tr;

    public TextureRegion getTr() {
        return tr;
    }

    public void setTr(TextureRegion tr) {
        this.tr = tr;
    }

    @Override
    public void dispose() {
    }
}
