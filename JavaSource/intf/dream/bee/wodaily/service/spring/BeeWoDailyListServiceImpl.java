package intf.dream.bee.wodaily.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import dream.note.daily.dao.MaWoDailyDetailDAO;
import dream.note.daily.dto.MaWoDailyDetailDTO;
import intf.dream.bee.wodaily.dao.BeeWoDailyListDAO;
import intf.dream.bee.wodaily.dto.BeeWoDailyCommonDTO;
import intf.dream.bee.wodaily.service.BeeWoDailyListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beeWoDailyListServiceTarget"
 * @spring.txbn id="beeWoDailyListService"
 * @spring.property name="beeWoDailyListDAO" ref="beeWoDailyListDAO"
 * @spring.property name="maWoDailyDetailDAO" ref="maWoDailyDetailDAO"
 */
public class BeeWoDailyListServiceImpl implements BeeWoDailyListService
{
    private BeeWoDailyListDAO beeWoDailyListDAO = null;
    private MaWoDailyDetailDAO maWoDailyDetailDAO = null;
    
	public BeeWoDailyListDAO getBeeWoDailyListDAO() {
		return beeWoDailyListDAO;
	}
	public void setBeeWoDailyListDAO(BeeWoDailyListDAO beeWoDailyListDAO) {
		this.beeWoDailyListDAO = beeWoDailyListDAO;
	}
    public MaWoDailyDetailDAO getMaWoDailyDetailDAO() 
    {
		return maWoDailyDetailDAO;
	}

	public void setMaWoDailyDetailDAO(MaWoDailyDetailDAO maWoDailyDetailDAO) 
	{
		this.maWoDailyDetailDAO = maWoDailyDetailDAO;
	}

	public List findWoDailyList(BeeWoDailyCommonDTO beeWoDailyCommonDTO,Map map) throws Exception
	{      
		return beeWoDailyListDAO.findWoDailyList(beeWoDailyCommonDTO,map);
	}
	
	public List findWoDailyCount(BeeWoDailyCommonDTO beeWoDailyCommonDTO,Map map) throws Exception
	{      
		return beeWoDailyListDAO.findWoDailyCount(beeWoDailyCommonDTO,map);
	}
	
	public int deleteWoDaily(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeWoDailyListDAO.deleteWoDaily(map);
		}
		return resultQty;
	}
	public int insertWoDaily(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeWoDailyListDAO.insertWoDaily(map);
			
			String compNo = CommonUtil.convertString(map.get("compNo"));
			String wodaylistId = CommonUtil.convertString(map.get("wodaylistId"));
			//String wodayactId = CommonUtil.convertString(map.get("wodayactId"));
			String woDesc =  CommonUtil.convertString(map.get("woDesc"));
			User loginUser = new User();
			loginUser.setCompNo(compNo);
			loginUser.setUserId(CommonUtil.convertString(map.get("empId")));
			
			MaWoDailyDetailDTO maWoDailyDetailDTO = new MaWoDailyDetailDTO();
			maWoDailyDetailDTO.setCompNo(compNo);
			maWoDailyDetailDTO.setWoDayListId(wodaylistId);
			//maWoDailyDetailDTO.setEnterBy(CommonUtil.convertString(map.get("empId")));
			maWoDailyDetailDAO.insertBmActivities(maWoDailyDetailDTO, loginUser);
			
		}
		return resultQty;
	}
	public int updateWoDaily(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeWoDailyListDAO.updateWoDaily(map);
			completedUnplanWorkOrder(map);
		}
		return resultQty;
	}

	public List findDailyUnPlanList(Map map) throws Exception
	{      
		return beeWoDailyListDAO.findDailyUnPlanList(map);
	}
	public List findDailyRoutineList(Map map) throws Exception
	{      
		return beeWoDailyListDAO.findDailyRoutineList(map);
	}
	
	private void completedUnplanWorkOrder(Map map) throws Exception{
		
		beeWoDailyListDAO.updateStatus(map);
		
	}

}

