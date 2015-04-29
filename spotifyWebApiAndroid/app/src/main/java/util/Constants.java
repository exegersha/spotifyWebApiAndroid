//Copyright 2012 BSkyB Ltd. All rights reserved

package util;

public class Constants {

    public static final long BRANDING_WAIT_TIME = 2000L;

    public static final int DEFAULT_EXPIRY_TIME_HOURS = 24;
    public static final int SEARCH_EXPIRY_TIME_MINUTES = 1;
    public static final int MOVIE_EXPIRY_TIME_HOURS = 24;
    public static final int MY_TV_EXPIRY_TIME_MINUTES = 0;
    // TODO: config isn't cached, therefore should expire immediately?
    public static final int CONFIG_EXPIRY_TIME_HOURS = 24;
    public static final int SPORTS_PASS_EXPIRY_TIME = 0;
    public static final int TV_SHOW_EXPIRY_TIME_HOURS = 24;
    public static final int TVSHOWS_LIST_EXPIRY_TIME_HOURS = 24;
    public static final int PRODUCTS_LIST_EXPIRY_TIME_HOURS = 0;
    public static final int TVSHOW_DETAILS_EXPIRTY_TIME_MINUTES = 60;
    public static final int IMAGE_SIZES_EXPIRY_TIME_HOURS = 7 * 24;

    public static final float IMAGE_16X9_RATIO_INVERSE = 0.5625f;
    public static final float IMAGE_16X9_RATIO = 1.778f;
    public static final float IMAGE_4X3_RATIO = 1.333f;

    public static final String PREFS_FILE_NAME = "popcorn_prefs";

    public static final boolean HEARTBEAT_ENABLED = true;
    /**
     * Does two things; if true we we'll track using the test account and we'll enable
     * debug in the Omniture library.
     */
    public static final boolean OMNITURE_TEST_MODE = false;
    /** Default value, can be overridden in code */
    public static final boolean OMNITURE_ENABLED = false;
    public static final boolean VERSION_IN_MENU_ENABLED = true;
    public static final int MAXIMUM_NUMBER_OF_SEARCH_ITEMS_TO_LOAD = 300;

    /** Disk cache size in bytes;used by SkyAndroidCoreLibrary. Default set by the library is 5MB */
    public static final long DISK_CACHE_SIZE_IN_BYTES = 50 * 1024 * 1024;

    public static enum EnvUrls {

        URLS_ANDROID("http://client.android.nowtv.bskyb.com", "http://services.android.nowtv.bskyb.com", "http://mobile.static.nowtv.com/android/integration")

        ,
        URLS_IOS("http://client.ios.nowtv.bskyb.com", "http://services.ios.nowtv.bskyb.com", "http://mobile.static.nowtv.com/android/integration")

        ,
        URLS_SKY_GO_TT("http://client.skygott.nowtv.bskyb.com", "http://services.skygott.nowtv.bskyb.com", "http://mobile.static.nowtv.com/android/integration")

        ,
        URLS_QUALITY("http://client.quality.nowtv.bskyb.com", "http://services.quality.nowtv.bskyb.com", "http://mobile.static.nowtv.com/android/integration")

        // , URLS_SHOWCASE("http://client.showcase.popcorn.bskyb.com"
        // , "http://services.showcase.popcorn.bskyb.com"
        // , "http://mobile.static.nowtv.com/android/stage")

        ,
        URLS_STAGE("http://client.stage.nowtv.com", "https://services.stage.nowtv.com", "http://mobile.static.nowtv.com/android/stage")

        ,
        URLS_INTEGRATION(
                "http://client.integration.nowtv.bskyb.com",
                "http://services.integration.nowtv.bskyb.com",
                "http://mobile.static.nowtv.com/android/integration")

        ,
        URLS_TEST_FLIGHT("http://client.nowtv.com", "https://services.nowtv.com", "http://mobile.static.nowtv.com/android/testflight")

        ,
        URLS_LIVE("http://client.nowtv.com", "https://services.nowtv.com", "http://mobile.static.nowtv.com/android/prod");

        private final String mClient;
        private final String mSession;
        private final String mStorage;

        EnvUrls(String client, String session, String storage) {
            mClient = client;
            mSession = session;
            mStorage = storage;
        }

        public String getClient() {
            return mClient;
        }

        public String getSession() {
            return mSession;
        }

        public String getStorage() {
            return mStorage;
        }

    }

}
