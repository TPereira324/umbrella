package pt.iade.ei.bestumbrella1

import android.app.Application
import com.paypal.checkout.createorder.CreateOrder
import com.paypal.checkout.PayPalCheckout
import com.paypal.checkout.config.CheckoutConfig
import com.paypal.checkout.config.Environment
import com.paypal.checkout.config.SettingsConfig
import com.paypal.checkout.config.UIConfig
import com.paypal.checkout.order.Amount
import com.paypal.checkout.order.AppContext
import com.paypal.checkout.order.Order
import com.paypal.checkout.order.OrderIntent
import com.paypal.checkout.order.PurchaseUnit
import com.paypal.checkout.order.UserAction
import pt.iade.ei.bestumbrella1.data.ApiConfig

class BestUmbrellaApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Configure PayPal Checkout SDK
        val returnUrl = "pt.iade.ei.bestumbrella1://paypalpay"

        PayPalCheckout.setConfig(
            CheckoutConfig(
                application = this,
                clientId = ApiConfig.API_KEY,
                environment = Environment.SANDBOX,
                // Ajuste a moeda conforme necessário
                currencyCode = com.paypal.checkout.order.CurrencyCode.EUR,
                userAction = UserAction.PAY_NOW,
                returnUrl = returnUrl,
                settingsConfig = SettingsConfig(
                    loggingEnabled = true
                ),
                uiConfig = UIConfig(
                    // Configurações de UI opcionais
                )
            )
        )
    }
}