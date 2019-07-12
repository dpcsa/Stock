package com.dpcsa.stock;

import com.dpcsa.compon.base.DeclareScreens;

public class StockDeclareScreens extends DeclareScreens {
    public final static String MAIN = "main", HOME = "home", SERVICE = "service", NEWS = "news",
            CATEGORY = "category", PRODUCT = "product", ITEM_FORM_REPAIR = "item_form_repair",
            ITEM_FORM = "item_form", REPAIRS_MAIN = "repairs_main", REPAIRS_CALC = "repairs_calc",
            ITEM_FORM_SERVICE = "ITEM_FORM_SERVICE", COUNTRY_CODE_PH = "COUNTRY_CODE_PH", COMENT = "COMENT",
            THANKS = "THANKS", NEWS_DETAIL = "NEWS_DETAIL", ABOUT = "ABOUT", WRITE_US = "WRITE_US",
            BACK_THANKS = "BACK_THANKS", YOUTUBE = "YOUTUBE";

    @Override
    public void declare() {
        activity(MAIN, R.layout.activity_main)
                .fragmentsContainer(R.id.content_frame)
                .navigator(handler(R.id.apply, VH.SET_LOCALE))
                .menuBottom(R.id.nav, HOME, REPAIRS_MAIN, ABOUT, NEWS)
                .component(TC.RECYCLER,             // меню вибору мови
                        model(JSON, getString(R.string.jsonListLang)),
                        view(R.id.recycler, new int[] {R.layout.item_lang, R.layout.item_lang_sel}).selected("id_language"));
    }
}
