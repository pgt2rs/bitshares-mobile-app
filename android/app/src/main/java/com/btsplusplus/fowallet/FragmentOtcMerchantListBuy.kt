package com.btsplusplus.fowallet


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import bitshares.dp
import bitshares.forEach
import org.json.JSONArray
import org.json.JSONObject


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentOtcMerchantListBuy : BtsppFragment() {

    private var _ctx: Context? = null
    private var _view: View? = null
    private lateinit var _data: JSONArray
    private var _asset_name: String = ""

    override fun onInitParams(args: Any?) {
        _asset_name = args as String
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _ctx = inflater.context
        _view = inflater.inflate(R.layout.fragment_otc_merchant_list_buy, container, false)
        getDataAndRefreshUI()
        return _view
    }

    private fun getDataAndRefreshUI() {
        _data = JSONArray().apply {
            for (i in 0 until 10){
                put(JSONObject().apply {
                    put("mmerchant_name","吉祥承兑")
                    put("total",3332)
                    put("rate","94%")
                    put("trade_count",1500)
                    put("legal_asset_symbol","¥")
                    put("limit_min","30")
                    put("limit_max","1250")
                    put("price","7.21")
                    put("payment_methods", JSONArray().apply {
                        put("alipay")
                        put("wechat")
                    })
                })
            }
        }
        refreshUI()
    }

    private fun refreshUI() {
        if (_view == null) {
            return
        }
        val layout: LinearLayout = _view!!.findViewById(R.id.layout_buy_from_fragment_merchant_list)
        if (_data.length() == 0){
            layout.addView(ViewUtils.createEmptyCenterLabel(_ctx!!, "没有任何商家在线"))
        } else {
            _data.forEach<JSONObject> {
                val view = ViewOtcMerchantCell(_ctx!!,_asset_name,1,it!!)
                layout.addView(view)
            }
        }
    }
}
