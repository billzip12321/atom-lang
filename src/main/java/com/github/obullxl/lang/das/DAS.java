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
		public static final String CATG = "catg";
		//
		public static final String SORT = "sort";
		//
		public static final String TITLE = "title";
		//
		public static final String EXT_MAP = "ext_map";
		//
		public static final String SUMMARY = "summary";
	}
	
	//
	public static interface CFG {
		//
		public static final String CATG = "catg";
		//
		public static final String NAME = "name";
		//
		public static final String TITLE = "title";
		//
		public static final String VALUE = "value";
		//
		public static final String VALUE_EXT = "value_ext";
	}
	
	//
	public static interface USER {
		//
		public static final String NO = "no";
		//
		public static final String FLAG = "flag";
		//
		public static final String NICK_NAME = "nick_name";
		//
		public static final String PASSWD = "passwd";
		//
		public static final String REGIST_DATE = "regist_date";
		//
		public static final String ACTIVE_DATE = "active_date";
		//
		public static final String AUTH_DATE = "auth_date";
		//
		public static final String MOBILE = "mobile";
		//
		public static final String EMAIL = "email";
		//
		public static final String REAL_NAME = "real_name";
		//
		public static final String BIRTH_DATE = "birth_date";
		//
		public static final String AVATAR_PATH = "avatar_path";
		//
		public static final String POST_CODE = "post_code";
		//
		public static final String PROVINCE_CODE = "province_code";
		//
		public static final String CITY_CODE = "city_code";
		//
		public static final String COUNTY_CODE = "county_code";
		//
		public static final String STREET_INFO = "street_info";
	}
	
	//
	public static interface USER_RIGHT {
		//
		public static final String USER_NO = "user_no";
		//
		public static final String NICK_NAME = "nick_name";
		//
		public static final String RGT_CODE = "rgt_code";
		//
		public static final String RGT_NAME = "rgt_name";
		//
		public static final String EXT_MAP = "ext_map";
	}
	
}
