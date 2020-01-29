package dream.work.cal.woweek.service.spring;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.work.cal.pmyear.dao.MaWoYearSchedDetailDAO;
import dream.work.cal.woweek.dao.MaWoWeekWoListDAO;
import dream.work.cal.woweek.dto.MaWoWeekWoCommonDTO;
import dream.work.cal.woweek.service.MaWoWeekWoListService;

/**
 * 주간작업일정 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: MaWoWeekWoListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maWoWeekWoListServiceTarget"
 * @spring.txbn id="maWoWeekWoListService"
 * @spring.property name="maWoWeekWoListDAO" ref="maWoWeekWoListDAO"
 * @spring.property name="maWoYearSchedDetailDAO" ref="maWoYearSchedDetailDAO"
 */
public class MaWoWeekWoListServiceImpl implements MaWoWeekWoListService
{
    private MaWoWeekWoListDAO maWoWeekWoListDAO = null;

    private MaWoYearSchedDetailDAO maWoYearSchedDetailDAO = null;

    
    public MaWoYearSchedDetailDAO getMaWoYearSchedDetailDAO()
    {
        return maWoYearSchedDetailDAO;
    }

    public void setMaWoYearSchedDetailDAO(
            MaWoYearSchedDetailDAO maWoYearSchedDetailDAO)
    {
        this.maWoYearSchedDetailDAO = maWoYearSchedDetailDAO;
    }

    public MaWoWeekWoListDAO getMaWoWeekWoListDAO() {
		return maWoWeekWoListDAO;
	}

	public void setMaWoWeekWoListDAO(MaWoWeekWoListDAO maWoWeekWoListDAO) {
		this.maWoWeekWoListDAO = maWoWeekWoListDAO;
	}

	public List findSchedList(MaWoWeekWoCommonDTO maWoWeekWoCommonDTO, User user)
    {      
        return maWoWeekWoListDAO.findSchedList(maWoWeekWoCommonDTO,user);
    }
	
	public String findTotalCount(MaWoWeekWoCommonDTO maWoWeekWoCommonDTO, User user)
    {      
        return maWoWeekWoListDAO.findTotalCount(maWoWeekWoCommonDTO,user);
    }
	
	public String[][] findSchedule(MaWoWeekWoCommonDTO maWoWeekWoCommonDTO,User user) throws IOException{
		
		return maWoWeekWoListDAO.findSchedule(maWoWeekWoCommonDTO,user);
	}
	public String[][] findWoType(MaWoWeekWoCommonDTO maWoWeekWoCommonDTO,User user) throws IOException{
		
		return maWoWeekWoListDAO.findWoType(maWoWeekWoCommonDTO,user);
	}
	
	public int updateDeleteTagSched(String[] deleteRows, User user) throws Exception{
        int result = 0;
        
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maWoWeekWoListDAO.updateDeleteTagSched(id, user);
                result = result + maWoWeekWoListDAO.create4wp(id, user);
            }
        return result;
    }
	public List getReportView(MaWoWeekWoCommonDTO maWoWeekWoCommonDTO) {
		Map<String, Object> reportMap = null;
        List<Map<String, Object>> reportList = new ArrayList<Map<String, Object>>();
        
        String[] wkorId = maWoWeekWoCommonDTO.getSelectedWkorId().split(",");
        //맨앞은 빈칸이라 1부터
        for (int i = 1; i < wkorId.length; i++) {
        	List detailList = maWoWeekWoListDAO.findReportWoList(wkorId[i], maWoWeekWoCommonDTO);
        	reportMap = (Map)detailList.get(0);
        	
        	reportMap.put("CRAFT_LIST", maWoWeekWoListDAO.findReportWoCraftList(wkorId[i], maWoWeekWoCommonDTO));
         	reportMap.put("PART_LIST", maWoWeekWoListDAO.findReportWoPartList(wkorId[i], maWoWeekWoCommonDTO));
         	reportMap.put("POINT_LIST", maWoWeekWoListDAO.findReportWoPointList(wkorId[i], maWoWeekWoCommonDTO));
         	reportMap.put("EQ_LIST", maWoWeekWoListDAO.findReportWoEqList(wkorId[i], maWoWeekWoCommonDTO));
        	reportList.add((Map)reportMap);
		}
        
		return reportList;
	}
	
	
	
}

