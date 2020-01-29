package intf.dream.android.online.user.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.MwareConfig;
import common.util.CommonUtil;
import common.util.DateUtil;
import intf.dream.android.online.user.dao.AnOnUserListDAO;
import intf.dream.android.online.user.service.AnOnUserListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anOnUserListServiceTarget"
 * @spring.txbn id="anOnUserListService"
 * @spring.property name="anOnUserListDAO" ref="anOnUserListDAO"
 */
public class AnOnUserListServiceImpl implements AnOnUserListService
{
    private AnOnUserListDAO anOnUserListDAO = null;

	public AnOnUserListDAO getAnOnUserListDAO() {
		return anOnUserListDAO;
	}
	public void setAnOnUserListDAO(AnOnUserListDAO anOnUserListDAO) {
		this.anOnUserListDAO = anOnUserListDAO;
	}
	
	public List findList(Map map) throws Exception
	{      
		return anOnUserListDAO.findList(map);
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
			
			anOnUserListDAO.updateFilter(map,interval+"");
		}
		return resultQty;
	}
}

