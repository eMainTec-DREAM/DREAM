package intf.dream.cricket.user.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.MwareConfig;
import common.util.CommonUtil;
import common.util.DateUtil;
import intf.dream.cricket.user.dao.CricketUserListDAO;
import intf.dream.cricket.user.service.CricketUserListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="cricketUserListServiceTarget"
 * @spring.txbn id="cricketUserListService"
 * @spring.property name="cricketUserListDAO" ref="cricketUserListDAO"
 */
public class CricketUserListServiceImpl implements CricketUserListService
{
    private CricketUserListDAO cricketUserListDAO = null;

	public CricketUserListDAO getCricketUserListDAO() {
		return cricketUserListDAO;
	}
	public void setCricketUserListDAO(CricketUserListDAO cricketUserListDAO) {
		this.cricketUserListDAO = cricketUserListDAO;
	}
	
	public List findList(Map map) throws Exception
	{      
		return cricketUserListDAO.findList(map);
	}
	public int updateFilter(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			
			String filterDate = CommonUtil.convertString(map.get("filterStartDate"));
			
			String workStartTime = "".equals(MwareConfig.getWorkStartBaseTime())?"0000":MwareConfig.getWorkStartBaseTime();
			String nowTime = DateUtil.getDateTime("HHmm");
			int interval = 0;
			if(Integer.parseInt(nowTime) >= Integer.parseInt(workStartTime)){
				interval = DateUtil.getDayInterval(DateUtil.getDate(), filterDate);
			}else{
				interval = DateUtil.getDayInterval(DateUtil.getDate(-1),  filterDate);
			}
			if(interval > 0) interval = 0;
			
			cricketUserListDAO.updateFilter(map,interval+"");
		}
		return resultQty;
	}
}

