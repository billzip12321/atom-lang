/**
 * obullxl@gmail.com
 */
package com.github.obullxl.lang.das;

/**
 * A fields const object database.
 */
public interface DAS {

	//
	public static interface CATG {
		//
		public static final String CODE = "code";
		//
		public static final int CODE_MAX = 64;
		//
		public static final String CATG = "catg";
		//
		public static final int CATG_MAX = 64;
		//
		public static final String SORT = "sort";
		//
		public static final int SORT_MAX = 10;
		//
		public static final String TITLE = "title";
		//
		public static final int TITLE_MAX = 256;
		//
		public static final String EXT_MAP = "ext_map";
		//
		public static final int EXT_MAP_MAX = 1024;
		//
		public static final String SUMMARY = "summary";
		//
		public static final int SUMMARY_MAX = 1024;
		//
		public static final String GMT_CREATE = "gmt_create";
		//
		public static final String GMT_MODIFY = "gmt_modify";
	}
	
	//
	public static interface CFG {
		//
		public static final String CATG = "catg";
		//
		public static final int CATG_MAX = 64;
		//
		public static final String NAME = "name";
		//
		public static final int NAME_MAX = 64;
		//
		public static final String TITLE = "title";
		//
		public static final int TITLE_MAX = 128;
		//
		public static final String VALUE = "value";
		//
		public static final int VALUE_MAX = 512;
		//
		public static final String VALUE_EXT = "value_ext";
		//
		public static final int VALUE_EXT_MAX = 1024;
		//
		public static final String GMT_CREATE = "gmt_create";
		//
		public static final String GMT_MODIFY = "gmt_modify";
	}
	
	//
	public static interface RELATE {
		//
		public static final String CATG = "catg";
		//
		public static final int CATG_MAX = 64;
		//
		public static final String SRC_NO = "src_no";
		//
		public static final int SRC_NO_MAX = 64;
		//
		public static final String SRC_NAME = "src_name";
		//
		public static final int SRC_NAME_MAX = 128;
		//
		public static final String DST_NO = "dst_no";
		//
		public static final int DST_NO_MAX = 64;
		//
		public static final String DST_NAME = "dst_name";
		//
		public static final int DST_NAME_MAX = 128;
		//
		public static final String EXT_MAP = "ext_map";
		//
		public static final int EXT_MAP_MAX = 1024;
		//
		public static final String GMT_CREATE = "gmt_create";
		//
		public static final String GMT_MODIFY = "gmt_modify";
	}
	
	//
	public static interface USER {
		//
		public static final String NO = "no";
		//
		public static final int NO_MAX = 64;
		//
		public static final String FLAG = "flag";
		//
		public static final int FLAG_MAX = 64;
		//
		public static final String NICK_NAME = "nick_name";
		//
		public static final int NICK_NAME_MAX = 32;
		//
		public static final String PASSWD = "passwd";
		//
		public static final int PASSWD_MAX = 64;
		//
		public static final String PASSWD_ERR_COUNT = "passwd_err_count";
		//
		public static final String REGIST_DATE = "regist_date";
		//
		public static final int REGIST_DATE_MAX = 10;
		//
		public static final String ACTIVE_DATE = "active_date";
		//
		public static final int ACTIVE_DATE_MAX = 10;
		//
		public static final String AUTH_DATE = "auth_date";
		//
		public static final int AUTH_DATE_MAX = 10;
		//
		public static final String MOBILE = "mobile";
		//
		public static final int MOBILE_MAX = 16;
		//
		public static final String EMAIL = "email";
		//
		public static final int EMAIL_MAX = 64;
		//
		public static final String REAL_NAME = "real_name";
		//
		public static final int REAL_NAME_MAX = 64;
		//
		public static final String BIRTH_DATE = "birth_date";
		//
		public static final int BIRTH_DATE_MAX = 10;
		//
		public static final String AVATAR_PATH = "avatar_path";
		//
		public static final int AVATAR_PATH_MAX = 128;
		//
		public static final String POST_CODE = "post_code";
		//
		public static final int POST_CODE_MAX = 10;
		//
		public static final String PROVINCE_CODE = "province_code";
		//
		public static final int PROVINCE_CODE_MAX = 10;
		//
		public static final String CITY_CODE = "city_code";
		//
		public static final int CITY_CODE_MAX = 10;
		//
		public static final String COUNTY_CODE = "county_code";
		//
		public static final int COUNTY_CODE_MAX = 10;
		//
		public static final String STREET_INFO = "street_info";
		//
		public static final int STREET_INFO_MAX = 128;
		//
		public static final String GMT_CREATE = "gmt_create";
		//
		public static final String GMT_MODIFY = "gmt_modify";
	}
	
}
