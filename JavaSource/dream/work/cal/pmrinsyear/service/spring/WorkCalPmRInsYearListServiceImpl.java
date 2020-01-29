package dream.work.cal.pmrinsyear.service.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.fins.gt.util.StringUtils;
import com.google.gson.Gson;

import common.bean.ResponseDTO;
import common.bean.User;
import common.util.CommonUtil;
import common.util.MessageUtil;
import common.util.StringUtil;
import dream.mgr.cdsys.dto.MgrCdSysCodeListDTO;
import dream.mgr.cdsys.dto.MgrCdSysCommonDTO;
import dream.mgr.cdsys.service.MgrCdSysCodeListService;
import dream.work.cal.pmrinsyear.dao.WorkCalPmRInsYearListDAO;
import dream.work.cal.pmrinsyear.dto.WorkCalPmRInsYearCommonDTO;
import dream.work.cal.pmrinsyear.service.WorkCalPmRInsYearListService;

/**
 * 연간작업일정 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: WorkCalPmRInsListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 *
 * @spring.bean id="workCalPmRInsYearListServiceTarget"
 * @spring.txbn id="workCalPmRInsYearListService"
 * @spring.property name="workCalPmRInsYearListDAO" ref="workCalPmRInsYearListDAO"
 */
public class WorkCalPmRInsYearListServiceImpl implements WorkCalPmRInsYearListService
{
    private WorkCalPmRInsYearListDAO workCalPmRInsYearListDAO = null;


	public WorkCalPmRInsYearListDAO getWorkCalPmRInsYearListDAO() {
		return workCalPmRInsYearListDAO;
	}
	public void setWorkCalPmRInsYearListDAO(
			WorkCalPmRInsYearListDAO workCalPmRInsYearListDAO) {
		this.workCalPmRInsYearListDAO = workCalPmRInsYearListDAO;
	}
	
	public List findSchedMonthlyList(WorkCalPmRInsYearCommonDTO workCalPmRInsYearCommonDTO,User user)
    {
        return workCalPmRInsYearListDAO.findSchedMonthlyList(workCalPmRInsYearCommonDTO,user);
    }
	
	public List findYearReport(WorkCalPmRInsYearCommonDTO workCalPmRInsYearCommonDTO,User user) {
        Map<String, Object> reportMap = null;
        List<Map<String, Object>> reportList = new ArrayList<Map<String, Object>>();
        
        List detailList = workCalPmRInsYearListDAO.findYearReport(workCalPmRInsYearCommonDTO,user);
        reportMap = (Map)detailList.get(0);
        reportMap.put("INS_LIST", workCalPmRInsYearListDAO.findYearInsReport(workCalPmRInsYearCommonDTO,user));
        
        reportList.add((Map)reportMap);

         return reportList;
    }
	public int deleteSched(String[] deleteRows,User user) throws Exception{
        int result = 0;
        String pSchedStatus = "";

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
            	pSchedStatus =  workCalPmRInsYearListDAO.checkSched(id, user);
            	if("S".equals(pSchedStatus)){
            		result = result + workCalPmRInsYearListDAO.updateDeleteTagSched(id, user);
            	}else if("P".equals(pSchedStatus)){
            		result = result + workCalPmRInsYearListDAO.deleteSched(id, user);
            	}
            }
        return result;
    }
	
	

    public void updateSchedule(List gridList, User user)
    {
    	String pSchedStatus = "";
    	
    	for(Object obj : gridList)
        {
            Map rowMap = (Map)obj;
            String scheDate     = (String)rowMap.get("SCHEDDATE");
            String pmSchedId    = (String)rowMap.get("PMINSSCHEDID");
            String remark       = (String)rowMap.get("REMARK");
            String compNo       = user.getCompNo();


            if(scheDate == "") continue;

            if(scheDate.indexOf("-") > -1)scheDate = CommonUtil.convertDate(scheDate);
            
            pSchedStatus =  workCalPmRInsYearListDAO.checkSched(pmSchedId, user);
        	if("S".equals(pSchedStatus)){
        		workCalPmRInsYearListDAO.updateScheduleDate(pmSchedId, scheDate, remark, user);
        		workCalPmRInsYearListDAO.updateResultSchedDetail(pmSchedId, scheDate, user);
        	}else if("P".equals(pSchedStatus)){
        		workCalPmRInsYearListDAO.updateScheduleDate(pmSchedId, scheDate, remark, user);
        	}

        }

    }

    public String findTotalCount(WorkCalPmRInsYearCommonDTO workCalPmRInsYearCommonDTO,User user)
    {
        return workCalPmRInsYearListDAO.findTotalCount(workCalPmRInsYearCommonDTO,user);
    }

	public String findTotalCountByMonthly(WorkCalPmRInsYearCommonDTO workCalPmRInsYearCommonDTO,User user) throws Exception 
	{
	    Map<String,Map<String,Integer>> dateMap = new HashMap<String,Map<String,Integer>>();
        Map<String,Integer> statusMap = new HashMap<String,Integer>();
        MgrCdSysCodeListService mgrCdSysCodeListService = (MgrCdSysCodeListService) CommonUtil.getBean("mgrCdSysCodeListService", user);
        MgrCdSysCodeListDTO mgrCdSysCodeListDTO = new MgrCdSysCodeListDTO();
        mgrCdSysCodeListDTO.setListType("PMSCHED_STATUS");
        String[] statusArray = StringUtil.toSingleArray(mgrCdSysCodeListService.findCodeList(new MgrCdSysCommonDTO(), mgrCdSysCodeListDTO), "CODE");
        for(String status:statusArray){
            statusMap.put(status, 0);
        }
        statusMap.put("TOTAL", 0);
        for(int m=1;m<=12;m++){
            dateMap.put(m<10?"0"+m:""+m, new HashMap<String,Integer>(statusMap));
        }
        dateMap.put("TOTAL", new HashMap<String,Integer>(statusMap));
        List<Map> list = this.findSchedMonthlyList(workCalPmRInsYearCommonDTO, user);
        for(Map map:list){
            if(StringUtil.valueOf(map.get("SCHEDDATE")).length()>=6){
                String month = StringUtil.valueOf(map.get("SCHEDDATE")).substring(4, 6);
                
                statusMap = dateMap.get(month);
                String status = StringUtil.valueOf(map.get("PMSTATUSCODE"));
                if(statusMap.containsKey(status)){
                    statusMap.put(status, statusMap.get(status)+1);
                    dateMap.get("TOTAL").put(status, dateMap.get("TOTAL").get(status)+1);
                }
                statusMap.put("TOTAL", statusMap.get("TOTAL")+1);
                dateMap.get("TOTAL").put("TOTAL", dateMap.get("TOTAL").get("TOTAL")+1);
            }
        }
        return new Gson().toJson(dateMap);
	}
	@Override
	public ResponseDTO dailyScheduled(String[] fixRows, WorkCalPmRInsYearCommonDTO workCalPmRInsYearCommonDTO, User user) throws Exception
	{
	    ResponseDTO.Builder builder = new ResponseDTO.Builder(MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG044")).status(HttpServletResponse.SC_OK);
	    
	    //no selected rows
	    if(fixRows.equals(null) || fixRows.length==0) {
	        builder
	        .status(HttpServletResponse.SC_BAD_REQUEST)
            .message(MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG0074"));
	        return builder.build();
	    }
	    
	    //validation
        boolean isValid = true;
        workCalPmRInsYearCommonDTO.setPmInsSchedId(StringUtils.join(fixRows, "+"));
        List<Map> list = this.findSchedMonthlyList(workCalPmRInsYearCommonDTO, user);
        for(Map map:list) {
            if(!"P".equals(StringUtil.valueOf(map.get("PMSTATUSCODE")))) {
                isValid = false;
                builder.addData(map);
            }
        }
        if(!isValid) {
            builder
            .status(HttpServletResponse.SC_BAD_REQUEST)
            .message(MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG1013"));
            return builder.build();
        }
        
        //success
        for(String id : fixRows)
        {
            workCalPmRInsYearListDAO.SP_PM_MAKE_TO_PMI(id, user);
        }
        list = this.findSchedMonthlyList(workCalPmRInsYearCommonDTO, user);
        builder
        .status(HttpServletResponse.SC_OK)
        .message(MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG044"))
        .data(list);
        
        return builder.build();
	}
}

