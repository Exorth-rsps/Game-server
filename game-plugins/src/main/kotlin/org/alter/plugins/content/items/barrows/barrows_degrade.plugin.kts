package org.alter.plugins.content.items.barrows

import org.alter.api.EquipmentType

on_equip_to_slot(EquipmentType.HEAD.id) { BarrowsDegrade.onEquip(player, EquipmentType.HEAD.id) }
on_equip_to_slot(EquipmentType.WEAPON.id) { BarrowsDegrade.onEquip(player, EquipmentType.WEAPON.id) }
on_equip_to_slot(EquipmentType.CHEST.id) { BarrowsDegrade.onEquip(player, EquipmentType.CHEST.id) }
on_equip_to_slot(EquipmentType.LEGS.id) { BarrowsDegrade.onEquip(player, EquipmentType.LEGS.id) }

