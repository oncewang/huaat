package net.huaat.system.service.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.huaat.common.util.pagination.Pagination;
import net.huaat.common.util.query.QueryParam;
import net.huaat.system.dao.QualityDao;
import net.huaat.system.service.sys.QualityService;
@Service("qualityService")
public class QualityServiceImpl implements QualityService {
	@Resource
	QualityDao  qualityDao;
	
	@Override
	public List<Object[]> getAllProgress() {
		// TODO Auto-generated method stub
		return qualityDao.getAllProgress();
	}

	@Override
	public List<Object[]> getIncomeList(Pagination pagination,String columns,
			List<QueryParam> querList, String string) {
		// TODO Auto-generated method stub
		return qualityDao.getIncomeList(pagination,columns,querList,string);
	}

	@Override
	public List<Object[]> getListObject(List<QueryParam> querList, String table) {
		// TODO Auto-generated method stub
		return qualityDao.getListObject(querList,table);
	}

}
