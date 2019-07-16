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
        fragment(HOME, R.layout.fragment_home)
                .setValue(item(R.id.lang_txt, TS.LOCALE))
                .navigator(show(R.id.sel_lang, R.id.lang, true))
                .component(TC.RECYCLER,
                        model(API.CATEGORIES, 1).sort("order"),
                        view(R.id.recycler, R.layout.item_home),
                        navigator(start(CATEGORY, PS.RECORD)));

        fragment(REPAIRS_MAIN, R.layout.fragment_repairs_main)
                .setValue(item(R.id.lang_txt, TS.LOCALE))
                .navigator(show(R.id.sel_lang, R.id.lang, true),
                        start(R.id.apply, SERVICE, true));

        fragment(ABOUT, R.layout.fragment_about)
                .setValue(item(R.id.lang_txt, TS.LOCALE))
                .navigator(show(R.id.sel_lang, R.id.lang, true))
                .component(TC.PANEL,
                        model(API.ABOUT),
                        view(R.id.panel),
                        navigator(start(R.id.apply, WRITE_US),
                                start(R.id.video, YOUTUBE),
                                handler(R.id.phone, VH.DIAL_UP),
                                showHide(R.id.full_desc, R.id.text2, R.string.hide, R.string.full_desc)));
        fragment(CATEGORY, R.layout.fragment_category).animate(AS.RL)
                .navigator(back(R.id.back))
                .component(TC.PANEL,
                        model(ARGUMENTS),
                        view(R.id.panel))
                .component(TC.RECYCLER,
                        model(API.PRODUCTS, "categoryId").errorShowView(R.id.error_view),
                        view(R.id.recycler, R.layout.item_category).noDataView(R.id.no_product),
                        navigator(start(0, PRODUCT)));

        fragment(PRODUCT, R.layout.fragment_product).animate(AS.RL)
                .navigator(back(R.id.back),
                        handler(R.id.apply, ITEM_FORM, PS.RECORD_COMPONENT, R.id.panel))
                .component(TC.PANEL,
                        model(API.DETAIL, "productId"),
                        view(R.id.panel).visibilityManager(visibility(R.id.video, "videoLink"),
                                visibility(R.id.full_desc, "text2"),
                                visibility(R.id.charact, "description.characteristics")),
                        navigator(handler(R.id.video, VH.SET_PARAM), start(R.id.video, YOUTUBE),
                                showHide(R.id.full_desc, R.id.text2, R.string.hide, R.string.full_desc)));

        activity(YOUTUBE, YouTubeActivity.class).animate(AS.RL)
                .navigator(back(R.id.cancel))
                .componentYoutube(R.id.player)
                .setValue(item(R.id.player, TS.PARAM, "videoLink"));
    }
}
