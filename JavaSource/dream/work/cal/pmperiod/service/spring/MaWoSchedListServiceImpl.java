package dream.work.cal.pmperiod.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.fins.gt.util.StringUtils;

import common.bean.User;
import common.util.CommonUtil;
import common.util.StringUtil;
import dream.work.cal.pmperiod.dao.MaWoSchedListDAO;
import dream.work.cal.pmperiod.dto.MaWoSchedCommonDTO;
import dream.work.cal.pmperiod.dto.MaWoSchedDetailDTO;
import dream.work.cal.pmperiod.service.MaWoSchedListService;
import dream.work.cal.pmyear.dao.MaWoYearSchedListDAO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.service.MaWoResultMstrListService;
import dream.work.list.service.WoPlanListService;

/**
 * 예방작업일정(기간) - 목록 serviceimpl
 * @author kim21017
 * @version $Id: MaWoSchedListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maWoSchedListServiceTarget"
 * @spring.txbn id="maWoSchedListService"
 * @spring.property name="maWoSchedListDAO" ref="maWoSchedListDAO"
 * @spring.property name="maWoYearSchedListDAO" ref="maWoYearSchedListDAO"
 */
public class MaWoSchedListServiceImpl implements MaWoSchedListService
{
    private MaWoSchedListDAO maWoSchedListDAO = null;
    
    private MaWoYearSchedListDAO maWoYearSchedListDAO = null;
    

    public MaWoYearSchedListDAO getMaWoYearSchedListDAO() {
		return maWoYearSchedListDAO;
	}

	public void setMaWoYearSchedListDAO(MaWoYearSchedListDAO maWoYearSchedListDAO) {
		this.maWoYearSchedListDAO = maWoYearSchedListDAO;
	}


    public MaWoSchedListDAO getMaWoSchedListDAO() {
		return maWoSchedListDAO;
	}

	public void setMaWoSchedListDAO(MaWoSchedListDAO maWoSchedListDAO) {
		this.maWoSchedListDAO = maWoSchedListDAO;
	}

	public List findSchedList(MaWoSchedCommonDTO maWoSchedCommonDTO, User user)
    {      
        return maWoSchedListDAO.findSchedList(maWoSchedCommonDTO, user);
    }
	
	public int deleteSched(String[] deleteRows, User user) throws Exception{
	    int result = 0;
        if(!deleteRows.equals(null) && deleteRows.length>0){
            String pmSchedId = StringUtils.join(deleteRows, "+");
            
            //DELETE WORKORDER
            MaWoResultMstrListService maWoResultMstrListService = (MaWoResultMstrListService) CommonUtil.getBean("maWoResultMstrListService", user);
            MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
            maWoResultMstrCommonDTO.setPmSchedId(pmSchedId);
            maWoResultMstrCommonDTO.setFilterWoStatus("-C");
            String[] wkorIds = StringUtil.toSingleArray(maWoResultMstrListService.findWoResultMstrList(maWoResultMstrCommonDTO, user), "WKORID");
            maWoResultMstrListService.deleteWoResultMstr(wkorIds, user);
            
            //DELETE WOPLAN
            WoPlanListService woPlanListService = (WoPlanListService) CommonUtil.getBean("woPlanListService", user);
            WoPlanCommonDTO woPlanCommonDTO = new WoPlanCommonDTO();
            woPlanCommonDTO.setPmSchedId(pmSchedId);
            woPlanCommonDTO.setFilterWoPlanStatus("-PRC");
            String[] woPlanIds = StringUtil.toSingleArray(woPlanListService.findWoResultMstrList(woPlanCommonDTO, user), "WKORID");
            woPlanListService.deleteWoResultMstr(woPlanIds, user);
            
            //DELETE PMSCHED
            List list = new ArrayList();
            MaWoSchedDetailDTO maWoSchedDetailDTO = new MaWoSchedDetailDTO();
            for(String id : deleteRows)
            {
                maWoSchedDetailDTO.setPmSchedId(id);
                list.add(BeanUtils.cloneBean(maWoSchedDetailDTO));
            }
            result = maWoSchedListDAO.updateDeleteTag(list, user).length;
        }
        return result;
    }
	
    public void updateSchedule(List gridList, User user)
    {
    	String pSchedStatus = "";
        
        for(Object obj : gridList)
        {
            Map rowMap = (Map)obj;
            String scheDate     = ((String)rowMap.get("SCHEDDATE"));
            String pmSchedId    = (String)rowMap.get("PMSCHEDID");
            String remark       = (String)rowMap.get("REMARK");
            String compNo       = user.getCompNo();

            if(scheDate == "") continue;
            if(scheDate.indexOf("-") == -1)scheDate = CommonUtil.convertDate(scheDate);
            scheDate = scheDate.replaceAll("-", "");
            
            pSchedStatus =  maWoYearSchedListDAO.checkSched(pmSchedId, user);
            
        	if("S".equals(pSchedStatus)){
        		maWoYearSchedListDAO.updateScheduleDate(pmSchedId, scheDate, remark, user);
        		maWoYearSchedListDAO.updateResultSchedDetail(pmSchedId, scheDate, user);
        	}else if("P".equals(pSchedStatus)){
        		maWoYearSchedListDAO.updateScheduleDate(pmSchedId, scheDate, remark, user);
        	}
            
        }
        
    }

	@Override
	public String findTotalCount(MaWoSchedCommonDTO maWoSchedCommonDTO, User user)
	{
	    return maWoSchedListDAO.findTotalCount(maWoSchedCommonDTO, user);
	}
}

