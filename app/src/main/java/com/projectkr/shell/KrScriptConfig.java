package com.projectkr.shell;

import android.content.Context;

import com.omarea.krscript.executor.ScriptEnvironmen;
import com.omarea.krscript.model.PageNode;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;

public class KrScriptConfig {
    private static final String ASSETS_FILE = "file:///android_asset/";

    private final static String TOOLKIT_DIR = "toolkit_dir";
    private final static String TOOLKIT_DIR_DEFAULT = "file:///android_asset/kr-script/toolkit";

    private final static String EXECUTOR_CORE = "executor_core";
    private final static String PAGE_LIST_CONFIG = "page_list_config";
    private final static String PAGE_LIST_CONFIG_SH = "page_list_config_sh";

    private final static String PAGE_LIST_CONFIG1 = "page_list_config1";
    private final static String PAGE_LIST_CONFIG_SH1 = "page_list_config_sh1";

    private final static String PAGE_LIST_CONFIG2 = "page_list_config2";
    private final static String PAGE_LIST_CONFIG_SH2 = "page_list_config_sh2";

    private final static String PAGE_LIST_CONFIG3 = "page_list_config3";
    private final static String PAGE_LIST_CONFIG_SH3 = "page_list_config_sh3";

    private final static String FAVORITE_CONFIG = "favorite_config";
    private final static String FAVORITE_CONFIG_SH = "favorite_config_sh";
    private final static String ALLOW_HOME_PAGE = "allow_home_page";
    private final static String BEFORE_START_SH = "before_start_sh";
    private static HashMap<String, String> configInfo;
    private final String EXECUTOR_CORE_DEFAULT = "file:///android_asset/kr-script/executor.sh";
    private final String PAGE_LIST_CONFIG_DEFAULT = "file:///android_asset/kr-script/pages/more.xml";
    private final String PAGE_LIST_CONFIG_DEFAULT1 = "file:///android_asset/kr-script/page1.xml";
    private final String PAGE_LIST_CONFIG_DEFAULT2 = "file:///android_asset/kr-script/page2.xml";
    private final String PAGE_LIST_CONFIG_DEFAULT3 = "file:///android_asset/kr-script/page3.xml";
    private final String FAVORITE_CONFIG_DEFAULT = "file:///android_asset/kr-script/pages/favorites.xml";
    private final String ALLOW_HOME_PAGE_DEFAULT = "1";
    private final String BEFORE_START_SH_DEFAULT = ""; //"file:///android_asset/kr-script/before_start.sh";

    public KrScriptConfig init(Context context) {
        if (configInfo == null) {
            configInfo = new HashMap<>();
            configInfo.put(EXECUTOR_CORE, EXECUTOR_CORE_DEFAULT);
            configInfo.put(PAGE_LIST_CONFIG, PAGE_LIST_CONFIG_DEFAULT);
            configInfo.put(PAGE_LIST_CONFIG1, PAGE_LIST_CONFIG_DEFAULT1);
            configInfo.put(PAGE_LIST_CONFIG2, PAGE_LIST_CONFIG_DEFAULT2);
            configInfo.put(PAGE_LIST_CONFIG3, PAGE_LIST_CONFIG_DEFAULT3);
            configInfo.put(FAVORITE_CONFIG, FAVORITE_CONFIG_DEFAULT);
            configInfo.put(ALLOW_HOME_PAGE, ALLOW_HOME_PAGE_DEFAULT);
            configInfo.put(TOOLKIT_DIR, TOOLKIT_DIR_DEFAULT);
            configInfo.put(BEFORE_START_SH, BEFORE_START_SH_DEFAULT);

            try {
                String fileName = context.getString(R.string.kr_script_config);
                if (fileName.startsWith(ASSETS_FILE)) {
                    fileName = fileName.substring(ASSETS_FILE.length());
                }
                InputStream inputStream = context.getAssets().open(fileName);
                byte[] bytes = new byte[inputStream.available()];
                inputStream.read(bytes);
                String[] rows = new String(bytes, Charset.defaultCharset()).split("\n");
                for (String row : rows) {
                    String rowText = row.trim();
                    if (!rowText.startsWith("#") && rowText.contains("=")) {
                        int separator = rowText.indexOf("=");
                        String key = rowText.substring(0, separator).trim();
                        String value = rowText.substring(separator + 2, rowText.length() - 1).trim();
                        configInfo.remove(key);
                        configInfo.put(key, value);
                    }
                }
            } catch (Exception ex) {
            }
            ScriptEnvironmen.init(context, getExecutorCore(), getToolkitDir());
        }

        return this;
    }

    public HashMap<String, String> getVariables() {
        return configInfo;
    }

    private String getExecutorCore() {
        if (configInfo != null && configInfo.containsKey(EXECUTOR_CORE)) {
            return configInfo.get(EXECUTOR_CORE);
        }
        return EXECUTOR_CORE_DEFAULT;
    }

    private String getToolkitDir() {
        if (configInfo != null && configInfo.containsKey(TOOLKIT_DIR)) {
            return configInfo.get(TOOLKIT_DIR);
        }
        return TOOLKIT_DIR_DEFAULT;
    }

    public PageNode getPageListConfig() {
        if (configInfo != null) {
            PageNode pageInfo = new PageNode("");
            if (configInfo.containsKey(PAGE_LIST_CONFIG_SH)) {
                pageInfo.setPageConfigSh(configInfo.get(PAGE_LIST_CONFIG_SH));
            }
            if (configInfo.containsKey(PAGE_LIST_CONFIG)) {
                pageInfo.setPageConfigPath(configInfo.get(PAGE_LIST_CONFIG));
            }
            return pageInfo;
        }
        return null;
    }


    public PageNode getPageListConfig1() {
        if (configInfo != null) {
            PageNode pageInfo = new PageNode("");
            if (configInfo.containsKey(PAGE_LIST_CONFIG_SH1)) {
                pageInfo.setPageConfigSh(configInfo.get(PAGE_LIST_CONFIG_SH1));
            }
            if (configInfo.containsKey(PAGE_LIST_CONFIG1)) {
                pageInfo.setPageConfigPath(configInfo.get(PAGE_LIST_CONFIG1));
            }
            return pageInfo;
        }
        return null;
    }


    public PageNode getPageListConfig2() {
        if (configInfo != null) {
            PageNode pageInfo = new PageNode("");
            if (configInfo.containsKey(PAGE_LIST_CONFIG_SH2)) {
                pageInfo.setPageConfigSh(configInfo.get(PAGE_LIST_CONFIG_SH2));
            }
            if (configInfo.containsKey(PAGE_LIST_CONFIG2)) {
                pageInfo.setPageConfigPath(configInfo.get(PAGE_LIST_CONFIG2));
            }
            return pageInfo;
        }
        return null;
    }


    public PageNode getPageListConfig3() {
        if (configInfo != null) {
            PageNode pageInfo = new PageNode("");
            if (configInfo.containsKey(PAGE_LIST_CONFIG_SH3)) {
                pageInfo.setPageConfigSh(configInfo.get(PAGE_LIST_CONFIG_SH3));
            }
            if (configInfo.containsKey(PAGE_LIST_CONFIG3)) {
                pageInfo.setPageConfigPath(configInfo.get(PAGE_LIST_CONFIG3));
            }
            return pageInfo;
        }
        return null;
    }

    public PageNode getFavoriteConfig() {
        if (configInfo != null) {
            PageNode pageInfo = new PageNode("");
            if (configInfo.containsKey(FAVORITE_CONFIG_SH)) {
                pageInfo.setPageConfigSh(configInfo.get(FAVORITE_CONFIG_SH));
            }
            if (configInfo.containsKey(FAVORITE_CONFIG)) {
                pageInfo.setPageConfigPath(configInfo.get(FAVORITE_CONFIG));
            }
            return pageInfo;
        }
        return null;
    }

    public boolean getAllowHomePage() {
        if (configInfo != null && configInfo.containsKey(ALLOW_HOME_PAGE)) {
            String value = configInfo.get(ALLOW_HOME_PAGE);
            return value != null && value.equals("1");
        }
        return ALLOW_HOME_PAGE_DEFAULT.equals("1");
    }

    public String getBeforeStartSh() {
        if (configInfo != null && configInfo.containsKey(BEFORE_START_SH)) {
            return configInfo.get(BEFORE_START_SH);
        }
        return BEFORE_START_SH_DEFAULT;
    }
}
