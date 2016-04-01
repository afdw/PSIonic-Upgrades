package wiresegal.psionup.common.items.component.botania

import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import vazkii.botania.api.mana.ManaItemHandler
//import vazkii.botania.api.mana.ManaItemHandler
import vazkii.psi.api.cad.EnumCADComponent
import vazkii.psi.api.cad.EnumCADStat
import vazkii.psi.api.cad.ICADAssembly
import vazkii.psi.api.spell.Spell
import vazkii.psi.api.spell.SpellContext
import vazkii.psi.common.item.base.IExtraVariantHolder
import wiresegal.psionup.api.enabling.IManaTrick
import wiresegal.psionup.api.enabling.ITrickEnablerComponent
import wiresegal.psionup.client.core.ModelHandler
import wiresegal.psionup.common.items.base.ItemComponent
import wiresegal.psionup.common.lib.LibNames

/**
 * @author WireSegal
 * Created at 7:05 PM on 3/31/16.
 */
class ItemBlasterAssembly(name: String) : ItemComponent(name, name), ICADAssembly, IExtraVariantHolder, ITrickEnablerComponent {

    override fun enablePiece(player: EntityPlayer, component: ItemStack, cad: ItemStack, context: SpellContext, spell: Spell, x: Int, y: Int): Boolean {
        if (spell.grid.gridData[x][y] is IManaTrick) {
            val drain = (spell.grid.gridData[x][y] as IManaTrick).manaDrain(context, spell, x, y)
            return ManaItemHandler.requestManaExact(cad, context.caster, drain, true)
        }
        return false
    }

    override fun registerStats() {
        this.addStat(EnumCADStat.EFFICIENCY, 0, 80)
        this.addStat(EnumCADStat.POTENCY, 0, 250)
    }

    override fun getComponentType(p0: ItemStack) = EnumCADComponent.ASSEMBLY

    override fun getCADModel(stack: ItemStack, cad: ItemStack): ModelResourceLocation? {
        return ModelHandler.resourceLocations[LibNames.Items.LIVINGWOOD_CAD_MODEL]
    }

    override fun getExtraVariants() = arrayOf(LibNames.Items.LIVINGWOOD_CAD_MODEL)

}