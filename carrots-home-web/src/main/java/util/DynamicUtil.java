package util;

import java.util.HashMap;
import java.util.Map;

import com.qding.common.util.DataUtils;

public class DynamicUtil {

	public static Map<String, Object> getProfessionList(Long companyId,
														String companyName, String name, String province, String city,
														String county, String industry, String[] category,
														String[] subCategory, String experience, String education,
														String compensation, Long updateAt, Long recommend, Long startAt,
														Long endAt) {

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
			params.put("company_id & in ","(" + industry + ")");
		}

		if (DataUtils.isNotNullOrEmpty(category)) {
			StringBuffer sbuf = new StringBuffer();
			for (String str : category) {
				sbuf.append(str);
				sbuf.append(",");
			}
			if (sbuf.length() > 0) {
				sbuf.deleteCharAt(sbuf.length() - 1);
				params.put("category & in ", "(" + sbuf + ")");
			}
		}

		if (DataUtils.isNotNullOrEmpty(subCategory)) {
			if (null != subCategory) {
				StringBuffer sbuf = new StringBuffer();
				for (String str : subCategory) {
					sbuf.append(str);
					sbuf.append(",");
				}
				if (sbuf.length() > 0) {
					sbuf.deleteCharAt(sbuf.length() - 1);
					params.put("sub_category & in ", "(" + sbuf + ")");
				}
			}
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

		// 前台只返回上架职位
		params.put("status", 1);

		System.out.println("===============================2");

		if (recommend == null || recommend == 0L) {
			// recommend为0，按时间逆序排列
			params.put("@order", "  update_at desc ");
		} else if (recommend == 1L) {
			// recommend为1，按recommend和时间逆序排列
			// System.out.println("=============================recommend====" +
			// recommend);
			params.put("@order", "  recommend desc, update_at desc ");
		}
		System.out.println("===============================3");

		params.put("@query", " id");

		params.put("@table", " profession ");

		return params;
	}

	public static Map<String, Object> getCompanyList(String name,
			String product, String province, String city, String county,
			String industry, String financing, Long freezed, Long approved,
			Long returnPage, Long updateTimeType) {

		Map<String, Object> params = new HashMap<String, Object>();

		if (DataUtils.isNotNullOrEmpty(name)) {
			params.put("name & like", "'%" + name + "%'");
		}

		if (DataUtils.isNotNullOrEmpty(product)) {
			params.put("product & like", "'%" + product + "%'");
		}

		if (DataUtils.isNotNullOrEmpty(province)) {
			params.put("province & in ", "(" + province + ")");
		}
		if (DataUtils.isNotNullOrEmpty(city)) {
			params.put("city & in ", "(" + city + ")");
		}
		if (DataUtils.isNotNullOrEmpty(county)) {
			params.put("county & in", "(" + county + ")");
		}
		if (DataUtils.isNotNullOrEmpty(industry)) {
			params.put("id & in ",
					"(select company_id from company_industry where industry in ("
							+ industry + "))");
		}
		if (DataUtils.isNotNullOrEmpty(financing)) {
			params.put("financing & in", "(" + financing + ")");
		}
		if (DataUtils.isNotNullOrEmpty(freezed)) {
			params.put("freezed ", freezed);
		}
		if (DataUtils.isNotNullOrEmpty(approved)) {
			params.put("approved ", approved);
		}
		// 搜索公司页和公司列表页的返回为公司列表（认证在前，普通在后；再按更新时间逆序）
		if (returnPage == null && approved == null) {
			params.put("@order", " approved desc, update_at desc ");
		}
		// 找精英的成功案例（认证在前，普通在后；再按职位更新时间排序）
		if (returnPage != null && returnPage == 0) {
			params.put("@order", " approved desc, profession_update_at desc ");
		}
		// 找职位的推荐职位部分
		// 排序时间类型updateTimeType
		// 0-公司更新时间
		// 1-职位更新时间
		if (returnPage != null && returnPage == 1) {
			if (updateTimeType == 0) {
				params.put("@order", " update_at desc ");
			}
			if (updateTimeType == 1) {
				params.put("@order", " profession_update_at desc ");
			}
		}

		params.put("@query", " id");

		params.put("@table", " company ");

		return params;
	}

	public static Map<String, Object> getArticleList(String title,
			String author, Long startAt, Long endAt, Long type, Long status,
			Long industry) {
		Map<String, Object> params = new HashMap<String, Object>();

		if (DataUtils.isNotNullOrEmpty(title)) {
			params.put("title & like ", "'%" + title + "%'");
		}
		if (DataUtils.isNotNullOrEmpty(author)) {
			params.put("author & like ", "'%" + author + "%'");
		}
		if (DataUtils.isNotNullOrEmpty(startAt)) {
			params.put("update_at & >=", startAt);
		}
		if (DataUtils.isNotNullOrEmpty(endAt)) {
			params.put("update_at & <= ", endAt);
		}
		if (DataUtils.isNotNullOrEmpty(type)) {
			params.put("type ", type);
		}
		if (DataUtils.isNotNullOrEmpty(status)) {
			params.put("status ", status);
		}

		params.put("@order", "  orderBy asc ");

		params.put("@query", " id");

		params.put("@table", " article ");

		return params;
	}

	public static Map<String, Object> getIndustryList(String companyIds) {
		Map<String, Object> params = new HashMap<String, Object>();

		if (DataUtils.isNotNullOrEmpty(companyIds)) {
			params.put("company_id & in ", "(" + companyIds + ")");
		}

//		params.put("@order", "  update_at desc ");

		params.put("@query", " id");

		params.put("@table", " company_industry ");

		return params;
	}

	public static Map<String, Object> getProfessionTags(String professionIds) {
		Map<String, Object> params = new HashMap<String, Object>();

		if (DataUtils.isNotNullOrEmpty(professionIds)) {
			params.put("profession_id & in ", "(" + professionIds + ")");
		}

//		params.put("@order", "  update_at desc ");

		params.put("@query", " id");

		params.put("@table", " profession_tags ");

		return params;
	}
}
