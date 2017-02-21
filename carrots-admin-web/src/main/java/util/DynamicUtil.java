package util;

import java.util.HashMap;
import java.util.Map;

import com.qding.common.util.DataUtils;

public class DynamicUtil {
	
	public static Map<String, Object> getProfessionTagsList(Long companyId,
			String tag) {
		
		Map<String, Object> params = new HashMap<String, Object>();

		if (DataUtils.isNotNullOrEmpty(companyId)) {
			params.put("company_id ", companyId);
		}
		if (DataUtils.isNotNullOrEmpty(tag)) {
			params.put("tag", "'" + tag + "'");
		}
		
		params.put("@order", " update_at desc ");
		
		params.put("@query", " id");

		params.put("@table", " profession_tags ");

		return params;
	}
	

	public static Map<String, Object> getProfessionList(Long companyId,
			String companyName, String name, String province, String city,
			String county, String industry, String category, String subCategory, String experience,
			String education, String compensation, Long updateAt,
			Long recommend, Long startAt, Long endAt, Long status) {
		
		Map<String, Object> params = new HashMap<String, Object>();

		if (DataUtils.isNotNullOrEmpty(companyId)) {
			params.put("company_id ", companyId);
		}
		if (DataUtils.isNotNullOrEmpty(companyName)) {
			params.put("company_name & like ", "'%" + companyName + "%'");
		}
		if (DataUtils.isNotNullOrEmpty(name)) {
			params.put("name & like", "'%" + name + "%'");
		}
		if (DataUtils.isNotNullOrEmpty(province)) {

			params.put("province & in", "(" + province + ")");
		}
		
		if (DataUtils.isNotNullOrEmpty(city)) {
			
			params.put("city & in", "(" + city + ")");
		}
		if (DataUtils.isNotNullOrEmpty(county)) {
			params.put("county & in", "(" + county + ")");
		}
		if (DataUtils.isNotNullOrEmpty(industry)) {
			params.put("company_id & in ",
					"(select company_id from company_industry where industry in ("
							+ industry + "))");
		}
		
		if (DataUtils.isNotNullOrEmpty(category)) {

			params.put("category & in ", "(" + category + ")");
		}
		
		if (DataUtils.isNotNullOrEmpty(subCategory)) {

			params.put("sub_category & in ", "(" + subCategory + ")");
		}
		
		if (DataUtils.isNotNullOrEmpty(experience)) {

			params.put("experience & in ", "(" + experience + ")");
		}
		
		if (DataUtils.isNotNullOrEmpty(education)) {

			params.put("education & in ", "(" + education + ")");
		}

		if (DataUtils.isNotNullOrEmpty(compensation)) {
			params.put("compensation & in", "(" + compensation + ")");
		}
		System.out.println("===============================1");
		// 计算时间戳
		if (DataUtils.isNotNullOrEmpty(updateAt)) {
			Long time = System.currentTimeMillis();
			switch (updateAt.intValue()) {
			case 0:
				// 1天内
				params.put("update_at & >", time - 86400000);
				break;

			case 1:
				// 3天内
				params.put("update_at & >", time - 86400000 * 3);
				break;

			case 2:
				// 7天内
				params.put("update_at & >", time - 86400000 * 7);
				break;
			}
		}

		// 需测试
		if (DataUtils.isNotNullOrEmpty(startAt)) {
			params.put("update_at & >=", startAt);
		}
		if (DataUtils.isNotNullOrEmpty(endAt)) {
			params.put("update_at & <=", endAt);
		}
		System.out.println("===============================2");
		
		if (DataUtils.isNotNullOrEmpty(status)) {
			params.put("status", status);
		}
		if (recommend == null||recommend == 0L ) {
		 // recommend为0，按时间逆序排列
		 params.put("@order", "  update_at desc ");
		 } else if (recommend == 1L) {
		 // recommend为1，按recommend和时间逆序排列
//			 System.out.println("=============================recommend====" + recommend);
		 params.put("@order", "  recommend desc, update_at desc ");
		 }
		System.out.println("===============================3");
		
		params.put("@query", " id");

		params.put("@table", " profession ");

		return params;
	}

	public static Map<String, Object> getCompanyMangerList(String name,
			String product, String province, String city, String county,
			Long industry, Long financing, Long freezed, Long approved) {

		Map<String, Object> params = new HashMap<String, Object>();

		if (DataUtils.isNotNullOrEmpty(name)) {
			params.put("name & like", "'%" + name + "%'");
		}

		if (DataUtils.isNotNullOrEmpty(product)) {
			params.put("product & like", "'%" + product + "%'");
		}

		if (DataUtils.isNotNullOrEmpty(province)) {
			params.put("province ", "'" + province + "'");
		}
		if (DataUtils.isNotNullOrEmpty(city)) {
			params.put("city ", "'" + city + "'");
		}
		if (DataUtils.isNotNullOrEmpty(county)) {
			params.put("county ", "'" + county + "'");
		}
		if (DataUtils.isNotNullOrEmpty(industry)) {
			params.put("id & in ", "(select company_id from company_industry where industry in ("
					+ industry + "))");
		}
		if (DataUtils.isNotNullOrEmpty(financing)) {
			params.put("financing ", financing);
		}
		if (DataUtils.isNotNullOrEmpty(freezed)) {
			params.put("freezed ", freezed);
		}
		if (DataUtils.isNotNullOrEmpty(approved)) {
			params.put("approved ", approved);
		}

		params.put("@order", " update_at desc ");

		params.put("@query", " id");

		params.put("@table", " company ");

		return params;
	}

	public static Map<String, Object> getArticleList(String title,
			String author, Long startAt, Long endAt, Long type, Long status,
			Long industry) {
		Map<String, Object> params = new HashMap<String, Object>();

		if (DataUtils.isNotNullOrEmpty(title)) {
			params.put("title & like ", "'%" +title+ "%'");
		}
		if (DataUtils.isNotNullOrEmpty(author)) {
			params.put("author & like ", "'%" +author + "%'");
		}
		if (DataUtils.isNotNullOrEmpty(startAt)) {
			params.put("update_at & >=",  startAt );
		}
		if (DataUtils.isNotNullOrEmpty(endAt)) {
			params.put("update_at & <= ", endAt );
		}
		if (DataUtils.isNotNullOrEmpty(type)) {
			params.put("type ", type);
		}
		if (DataUtils.isNotNullOrEmpty(status)) {
			params.put("status ", status);
		}

		
		params.put("@order", "  publish_at desc ");

		params.put("@query", " id");

		params.put("@table", " article ");

		return params;
	}
	
	public static Map<String, Object> getProfessionNum(Long companyId) {
		Map<String, Object> params = new HashMap<String, Object>();

		if (DataUtils.isNotNullOrEmpty(companyId)) {
			params.put("company_id", companyId);
		}

		params.put("@order", " update_at desc ");

		params.put("@query", " count(id)");

		params.put("@table", " profession ");

		return params;
	}
}
