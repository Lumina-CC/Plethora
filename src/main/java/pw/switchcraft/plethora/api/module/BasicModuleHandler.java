package pw.switchcraft.plethora.api.module;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import javax.annotation.Nonnull;

/**
 * A basic module handler.
 */
public class BasicModuleHandler extends AbstractModuleHandler {
	private final Identifier id;
	private final Item item;

	public BasicModuleHandler(Identifier id, Item item) {
		this.id = id;
		this.item = item;
	}

	@Nonnull
	@Override
	public Identifier getModule() {
		return id;
	}

//	@Nonnull
//	@Override
//	@SideOnly(Side.CLIENT)
//	public Pair<IBakedModel, Matrix4f> getModel(float delta) {
//		Matrix4f matrix = new Matrix4f();
//		matrix.setIdentity();
//		matrix.setRotation(new AxisAngle4f(0f, 1f, 0f, delta));
//
//		IBakedModel model = this.model;
//		if (model == null) {
//			model = this.model = RenderHelpers.getMesher().getItemModel(new ItemStack(item));
//		}
//
//		return Pair.of(model, matrix);
//	}
}
