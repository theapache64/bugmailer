package com.theah64.bugmailerexample;

import android.support.annotation.Nullable;

import com.theah64.bugmailer.core.BugMailerNode;
import com.theah64.bugmailer.core.NodeBuilder;
import com.theah64.bugmailer.models.Node;

import java.util.List;


/**
 * Created by shifar on 29/7/16.
 * Utility class to create API request object.
 */
public class APIRequestBuilder implements BugMailerNode {


    //Primary constants
    //private static final String REAL_BASE_URL = "http://cybazeserver/gudbuy/api/vone";
    //private static final String REAL_BASE_URL = "http://cybazedemo.co.in/gudbuy/api/vone";
    private static final String REAL_BASE_URL = "https://goodbuyonlinestore.com/api/vone";
    private static final String MOCK_BASE_URL = "http://theapache64.xyz:8080/mock_api/get_json/goodbuy";
    private static final String KEY_AUTHORIZATION = "Authorization";

    //Routes
    public static final String ROUTE_ADD_FEEDBACK = "/add_feedback";
    public static final String ROUTE_CHANGE_EMAIL = "/change_email";
    public static final String ROUTE_CHANGE_MOBILE = "/change_mobile";
    public static final String ROUTE_CHANGE_NAME = "/change_name";
    public static final String ROUTE_CLEAR_CART = "/clear_cart";
    public static final String ROUTE_CLEAR_FAVORITES = "/clear_favorites";
    public static final String ROUTE_DELETE_ADDRESS = "/delete_address";
    public static final String ROUTE_GET_ACCOUNT_INFO = "/get_account_info";
    public static final String ROUTE_GET_ADDRESSES = "/get_addresses";
    public static final String ROUTE_GET_BRANDS = "/get_brands";
    public static final String ROUTE_GET_CART = "/get_cart";
    public static final String ROUTE_GET_CHECKOUT = "/get_checkout";
    public static final String ROUTE_GET_DELIVERY_LOCATIONS = "/get_delivery_locations";
    public static final String ROUTE_GET_FAVORITES = "/get_favorites";
    public static final String ROUTE_GET_HOME_PAGE_DATA = "/get_home_page_data";
    public static final String ROUTE_GET_ORDER = "/get_order";
    public static final String ROUTE_GET_PRODUCT = "/get_product";
    public static final String ROUTE_GET_PRODUCTS = "/get_products";
    public static final String ROUTE_GET_PRODUCTS_BY_BRAND = "/get_products_by_brand";
    public static final String ROUTE_GET_PRODUCTS_SEARCH = "/get_products_search";
    public static final String ROUTE_GET_RECENT_ORDERS = "/get_recent_orders";
    public static final String ROUTE_GET_REORDER = "/get_reorder";
    public static final String ROUTE_GET_REWARD_HISTORY = "/get_reward_history";
    public static final String ROUTE_GUEST_SIGN_UP = "/guest_sign_up";
    public static final String ROUTE_IS_USER_EXIST = "/is_user_exist";
    public static final String ROUTE_LOGIN = "/login";
    public static final String ROUTE_PLACE_ORDER = "/place_order";
    public static final String ROUTE_REQUEST_OTP = "/request_otp";
    public static final String ROUTE_RESET_PASSWORD = "/reset_password";
    public static final String ROUTE_SIGN_UP = "/sign_up";
    public static final String ROUTE_UPDATE_CART = "/update_cart";
    public static final String ROUTE_UPDATE_FAVORITES = "/update_favorites";
    public static final String ROUTE_VERIFY_OTP = "/verify_otp";
    public static final String ROUTE_ADD_ADDRESS = "/add_address";
    public static final String ROUTE_EDIT_ADDRESS = "/edit_address";
    public static final String ROUTE_SEARCH_SUGGESTION = "/get_search_suggestions";


    private static final String X = APIRequestBuilder.class.getSimpleName();


    private final StringBuilder logBuilder = new StringBuilder();

    private final String url;


    public APIRequestBuilder(String route, @Nullable final String apiKey) {

        this.url = isReady(route) ? REAL_BASE_URL + route : MOCK_BASE_URL + route;
        appendLog("URL", url);

        if (apiKey != null) {
            appendLog(KEY_AUTHORIZATION, apiKey);
        }

        logBuilder.append("--------------------------------\n");
    }

    private static boolean isReady(String route) {

        switch (route) {

            //Ready routes
            case ROUTE_ADD_ADDRESS: //OK
            case ROUTE_ADD_FEEDBACK: //OK
            case ROUTE_CHANGE_EMAIL: //OK
            case ROUTE_CHANGE_MOBILE: //OK
            case ROUTE_CHANGE_NAME: //OK
            case ROUTE_CLEAR_CART: //OK
            case ROUTE_CLEAR_FAVORITES: //OK
            case ROUTE_DELETE_ADDRESS://OK
            case ROUTE_EDIT_ADDRESS: //OK
            case ROUTE_GET_ACCOUNT_INFO: //OK
            case ROUTE_GET_ADDRESSES: //OK
            case ROUTE_GET_BRANDS: //OK
            case ROUTE_GET_CART: //OK
            case ROUTE_GET_CHECKOUT: //OK
            case ROUTE_GET_DELIVERY_LOCATIONS: //OK
            case ROUTE_GET_FAVORITES: //OK
            case ROUTE_GET_HOME_PAGE_DATA: //OK
            case ROUTE_GET_PRODUCT: //OK
            case ROUTE_GET_PRODUCTS: //OK
            case ROUTE_GET_PRODUCTS_BY_BRAND: //OK
            case ROUTE_GET_PRODUCTS_SEARCH: //OK
            case ROUTE_GUEST_SIGN_UP: //OK
            case ROUTE_IS_USER_EXIST: //OK
            case ROUTE_LOGIN: //OK
            case ROUTE_REQUEST_OTP: //OK
            case ROUTE_RESET_PASSWORD: //OK
            case ROUTE_SEARCH_SUGGESTION: //OK
            case ROUTE_SIGN_UP://OK
            case ROUTE_UPDATE_CART://OK
            case ROUTE_UPDATE_FAVORITES: //OK
            case ROUTE_VERIFY_OTP: //OK
            case ROUTE_PLACE_ORDER: //OK
            case ROUTE_GET_RECENT_ORDERS: //OK
            case ROUTE_GET_ORDER: //OK
            case ROUTE_GET_REWARD_HISTORY: //OK
            case ROUTE_GET_REORDER: //OK
                return true;

            //Routes that are not ready
            /*case ROUTE_GET_REORDER:
                return false;*/

            default:
                return false;
        }

    }

    private void appendLog(String key, String value) {
        logBuilder.append(String.format("%s:%s\n", key, value));
    }

    private APIRequestBuilder addParam(final boolean isAllowNull, final String key, String value) {

        if (isAllowNull) {
            appendLog(key, value);
        } else {

            //value must not be null.
            if (value != null) {
                appendLog(key, value);
            }
        }

        return this;
    }

    public APIRequestBuilder addParam(final String key, final String value) {
        return addParam(true, key, value);
    }

    public APIRequestBuilder addParam(final String key, final int value) {
        return addParam(true, key, String.valueOf(value));
    }

    public APIRequestBuilder addParam(final String key, final boolean value) {
        return addParam(true, key, value ? "1" : "0");
    }

    public APIRequestBuilder addOptionalParam(String key, String value) {
        return addParam(false, key, value);
    }

    @Override
    public List<Node> getNodes() {
        return new NodeBuilder()
                .add("APIRequest", logBuilder.toString())
                .build();
    }
}
