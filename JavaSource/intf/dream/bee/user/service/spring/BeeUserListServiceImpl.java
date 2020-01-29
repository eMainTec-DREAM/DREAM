package intf.dream.bee.user.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.MwareConfig;
import common.util.CommonUtil;
import common.util.DateUtil;
import intf.dream.bee.user.dao.BeeUserListDAO;
import intf.dream.bee.user.service.BeeUserListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beeUserListServiceTarget"
 * @spring.txbn id="beeUserListService"
 * @spring.property name="beeUserListDAO" ref="beeUserListDAO"
 */
public class BeeUserListServiceImpl implements BeeUserListService
{
    private BeeUserListDAO beeUserListDAO = null;

	public BeeUserListDAO getBeeUserListDAO() {
		return beeUserListDAO;
	}
	public void setBeeUserListDAO(BeeUserListDAO beeUserListDAO) {
		this.beeUserListDAO = beeUserListDAO;
	}
	
	public List findList(Map map) throws Exception
	{      
		return beeUserListDAO.findList(map);
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
			
			beeUserListDAO.updateFilter(map,interval+"");
		}
		return resultQty;
	}
}

