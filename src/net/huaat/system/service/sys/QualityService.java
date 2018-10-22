package net.huaat.system.service.sys;

import java.util.List;

import net.huaat.common.util.pagination.Pagination;
import net.huaat.common.util.query.QueryParam;

public interface QualityService {
	public List<Object[]>  getAllProgress();

	public List<Object[]> getIncomeList(Pagination pagination,String columns,
			List<QueryParam> querList, String string);

	public List<Object[]> getListObject(List<QueryParam> querList, String table);

}
