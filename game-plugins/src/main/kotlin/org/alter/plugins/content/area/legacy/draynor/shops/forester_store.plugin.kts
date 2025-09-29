package org.alter.plugins.content.area.legacy.draynor.shops

import org.alter.game.model.shop.PurchasePolicy
import org.alter.plugins.content.mechanics.shops.AnimainfusedbarkCurrency
import org.alter.plugins.content.mechanics.shops.CoinCurrency
import org.alter.plugins.content.mechanics.shops.UnidentifiedMineralsCurrency

create_shop("Forester Store", AnimainfusedbarkCurrency(), purchasePolicy = PurchasePolicy.BUY_NONE) {
    // Wooden Shield
    items[0] = ShopItem(Items.LUMBERJACK_BOOTS, 1, 600, 300, 1, 500)
    items[1] = ShopItem(Items.LUMBERJACK_HAT, 1, 600, 300, 1, 500)
    items[2] = ShopItem(Items.LUMBERJACK_LEGS, 1, 750, 400, 1, 500)
    items[3] = ShopItem(Items.LUMBERJACK_TOP, 1, 750, 400, 1, 500)
    items[4] = ShopItem(Items.DRAGON_AXE, 1, 2000, 1000, 1, 800)
}
