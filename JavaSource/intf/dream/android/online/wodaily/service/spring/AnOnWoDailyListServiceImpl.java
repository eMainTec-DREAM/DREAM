package intf.dream.android.online.wodaily.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import dream.note.daily.dao.MaWoDailyDetailDAO;
import dream.note.daily.dto.MaWoDailyDetailDTO;
import intf.dream.android.online.wodaily.dao.AnOnWoDailyListDAO;
import intf.dream.android.online.wodaily.service.AnOnWoDailyListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anOnWoDailyListServiceTarget"
 * @spring.txbn id="anOnWoDailyListService"
 * @spring.property name="anOnWoDailyListDAO" ref="anOnWoDailyListDAO"
 * @spring.property name="maWoDailyDetailDAO" ref="maWoDailyDetailDAO"
 */
public class AnOnWoDailyListServiceImpl implements AnOnWoDailyListService
{
    private AnOnWoDailyListDAO anOnWoDailyListDAO = null;
    private MaWoDailyDetailDAO maWoDailyDetailDAO = null;
    
	public AnOnWoDailyListDAO getAnOnWoDailyListDAO() {
		return anOnWoDailyListDAO;
	}
	public void setAnOnWoDailyListDAO(AnOnWoDailyListDAO anOnWoDailyListDAO) {
		this.anOnWoDailyListDAO = anOnWoDailyListDAO;
	}
    public MaWoDailyDetailDAO getMaWoDailyDetailDAO() 
    {
		return maWoDailyDetailDAO;
	}

	public void setMaWoDailyDetailDAO(MaWoDailyDetailDAO maWoDailyDetailDAO) 
	{
		this.maWoDailyDetailDAO = maWoDailyDetailDAO;
	}

	public List findWoDailyList(Map map) throws Exception
	{      
		return anOnWoDailyListDAO.findWoDailyList(map);
	}
	public int deleteWoDaily(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnWoDailyListDAO.deleteWoDaily(map);
		}
		return resultQty;
	}
	public int insertWoDaily(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnWoDailyListDAO.insertWoDaily(map);
			
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
			anOnWoDailyListDAO.updateWoDaily(map);
			completedUnplanWorkOrder(map);
		}
		return resultQty;
	}

	public List findDailyUnPlanList(Map map) throws Exception
	{      
		return anOnWoDailyListDAO.findDailyUnPlanList(map);
	}
	public List findDailyRoutineList(Map map) throws Exception
	{      
		return anOnWoDailyListDAO.findDailyRoutineList(map);
	}
	
	private void completedUnplanWorkOrder(Map map) throws Exception{
		
		anOnWoDailyListDAO.updateStatus(map);
		
	}

}

