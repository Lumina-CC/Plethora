package io.sc3.plethora.gameplay.overlay

import io.sc3.plethora.gameplay.registry.Registration
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.Camera
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.item.ItemStack
import net.minecraft.util.Hand
import java.lang.Math.PI

object OverlayRenderer {
  private var ticks = 0f

  @JvmStatic
  @Environment(EnvType.CLIENT)
  fun renderOverlay(
    client: MinecraftClient,
    matrices: MatrixStack,
    tickDelta: Float,
    camera: Camera
  ) {
    ticks += tickDelta
    if (ticks > PI * 2 * 1000) ticks = 0f

    val player = client.player ?: return

    // Prevent rendering an overlay twice if it is in both hands
    var renderScanner: ItemStack? = null
    var renderSensor: ItemStack? = null

    for (hand in Hand.entries) {
      val stack = player.getStackInHand(hand)
      if (stack.isEmpty) continue

      val item = stack.item
      if (renderScanner == null && item === Registration.ModItems.SCANNER_MODULE) {
        renderScanner = stack
      } else if (renderSensor == null && item === Registration.ModItems.SENSOR_MODULE) {
        renderSensor = stack
      }
      // TODO: Chat recorder?
    }

    if (renderScanner != null) {
      ScannerOverlayRenderer.render(player, renderScanner, matrices, ticks, tickDelta, camera)
    }
    if (renderSensor != null) {
      SensorOverlayRenderer.render(player, renderSensor, matrices, ticks, camera)
    }
  }
}
