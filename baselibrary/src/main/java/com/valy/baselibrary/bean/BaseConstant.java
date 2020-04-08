package com.valy.baselibrary.bean;

/**
 * @Auther: valy
 * @datetime: 2020-02-10
 * @desc:
 */
public class BaseConstant {


    public static String LOGINAWAY = "";
    public static String USERNAME = "";
    public static String USERPASSWORD = "";
    public static String DEVICEID = "";
    public static String AREACODE = "";
    public static String CLIENT = "android";
    public static String CURRENTLANGUAGE = "zh_cn";
    public static Boolean isAutomaticLogIn = false;
    //商家端
    public static final String USERTYPE = "1";

    public class RESULT_CODE {
        //选择照片
        public static final int REQUEST_CODE_CHOOSE_IMAGE_CLIP = 28705;
        public static final int REQUEST_CODE_CHOOSE_IMAGE_CLIP_TWO = 387815;
        public static final int REQUEST_CODE_CHOOSE_IMAGE = 22148;
        public static final int REQUEST_CODE_CHOOSE_IMAGE_TWO = 13248;
        public static final int REQUEST_CODE_CHOOSE_IMAGE_THREE = 11818;
        public static final int REQUEST_CODE_CHOOSE_IMAGE_FOUR = 22548;
        //拍照
        public static final int REQUEST_CAMERA = 18510;
        //其他
        public static final int REQUEST_ETRAS = 11070;


    }

    public class YOUMENG {
        public static final String KEY = "5e4ba1e40feb474e621fb426";
    }

    public class WECAHT {
        public static final String APP_ID = "wxb4e3c4df7d8d18ea";
        public static final String APP_SECRET = "1948374c7fed868f06f0018e1ab1238d";
    }

    public class PERMISSION {
        public static final int OVERLAY_PERMISSION_REQ_CODE = 2825;

    }
}
