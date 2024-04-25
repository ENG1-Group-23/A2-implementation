package bytemusketeers.heslingtonhustle;

import com.badlogic.gdx.Gdx;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class AssetTests {
  @Test
  public void testAssetExists() {
    Assert.assertTrue("The asset for ships exists",
        Gdx.files.internal("map/MainMap.tmx").exists());
  }
}
