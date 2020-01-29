package dream.work.cal.pmrinsmonth.service.spring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.fins.gt.util.StringUtils;
import com.google.gson.Gson;

import common.bean.ResponseDTO;
import common.bean.User;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.MessageUtil;
import common.util.StringUtil;
import dream.mgr.cdsys.dto.MgrCdSysCodeListDTO;
import dream.mgr.cdsys.dto.MgrCdSysCommonDTO;
import dream.mgr.cdsys.service.MgrCdSysCodeListService;
import dream.work.cal.pmrinsmonth.dao.WorkCalPmRInsMonthListDAO;
import dream.work.cal.pmrinsmonth.dto.WorkCalPmRInsMonthCommonDTO;
import dream.work.cal.pmrinsmonth.service.WorkCalPmRInsMonthListService;

/**
 * 월간예방일정 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: WorkCalPmRInsMonthListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 *
 * @spring.bean id="workCalPmRInsMonthListServiceTarget"
 * @spring.txbn id="workCalPmRInsMonthListService"
 * @spring.property name="workCalPmRInsMonthListDAO" ref="workCalPmRInsMonthListDAO"
 */
public class WorkCalPmRInsMonthListServiceImpl implements WorkCalPmRInsMonthListService
{
    private WorkCalPmRInsMonthListDAO workCalPmRInsMonthListDAO = null;


	public WorkCalPmRInsMonthListDAO getWorkCalPmRInsMonthListDAO() {
		return workCalPmRInsMonthListDAO;
	}
	public void setWorkCalPmRInsMonthListDAO(
			WorkCalPmRInsMonthListDAO workCalPmRInsMonthListDAO) {
		this.workCalPmRInsMonthListDAO = workCalPmRInsMonthListDAO;
	}
	

	public List findSchedList(WorkCalPmRInsMonthCommonDTO workCalPmRInsMonthCommonDTO, User user)
    {
        return workCalPmRInsMonthListDAO.findSchedList(workCalPmRInsMonthCommonDTO,user);
    }
	public String findSchedule(WorkCalPmRInsMonthCommonDTO workCalPmRInsMonthCommonDTO, User user) throws Exception
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
        String yyyymm = CommonUtil.getRowDateToNum(workCalPmRInsMonthCommonDTO.getYyyymmdd());
        if(yyyymm.length()<6) return "";
        int lastDay = Integer.parseInt(DateUtil.getLastDayOfMonth(yyyymm.substring(0, 4), yyyymm.substring(4, 6)));
        for(int d=1;d<=lastDay;d++){
            String yyyyMMdd = yyyymm.substring(0, 6) + (d<10?"0"+d:""+d);
            dateMap.put(yyyyMMdd, new HashMap<String,Integer>(statusMap));
            dateMap.get(yyyyMMdd).put("WEEK", DateUtil.getWeekOfMonth(yyyyMMdd));
            dateMap.get(yyyyMMdd).put("DOW", DateUtil.getDayOfWeek(yyyyMMdd));
        }
        List<Map> list = this.findSchedList(workCalPmRInsMonthCommonDTO, user);
        for(Map map:list){
            if(StringUtil.valueOf(map.get("SCHEDDATE")).length()==8){
                String day = StringUtil.valueOf(map.get("SCHEDDATE"));
                
                statusMap = dateMap.get(day);
                String status = StringUtil.valueOf(map.get("PMSTATUSCODE"));
                if(statusMap.containsKey(status)){
                    statusMap.put(status, statusMap.get(status)+1);
                }
                statusMap.put("TOTAL", statusMap.get("TOTAL")+1);
            }
        }
        return new Gson().toJson(dateMap);
	}

	public ResponseDTO dailyScheduled(String[] fixRows, WorkCalPmRInsMonthCommonDTO workCalPmRInsMonthCommonDTO,User user) throws Exception
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
        workCalPmRInsMonthCommonDTO.setPmInsSchedId(StringUtils.join(fixRows, "+"));
        List<Map> list = this.findSchedList(workCalPmRInsMonthCommonDTO, user);
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
            workCalPmRInsMonthListDAO.SP_PM_MAKE_TO_PMI(id, user);
        }
        list = this.findSchedList(workCalPmRInsMonthCommonDTO, user);
        builder
        .status(HttpServletResponse.SC_OK)
        .message(MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG044"))
        .data(list);
        
        return builder.build();
    }


    public void updateSchedule(List gridList, User user)
    {
    	String pSchedStatus = "";
    	
    	for(Object obj : gridList)
        {
            Map rowMap = (Map)obj;
            String scheDate     = ((String)rowMap.get("SCHEDDATE"));
            String pmInsSchedId    = (String)rowMap.get("PMINSSCHEDID");
            String remark       = (String)rowMap.get("REMARK");

            if(scheDate == "") continue;
            
            if(scheDate.indexOf("-") >=0)scheDate = CommonUtil.convertDate(scheDate);
            
            pSchedStatus =  workCalPmRInsMonthListDAO.checkSched(pmInsSchedId, user);
        	if("S".equals(pSchedStatus)){
        		workCalPmRInsMonthListDAO.updateScheduleDate(pmInsSchedId, scheDate, remark, user);
        		workCalPmRInsMonthListDAO.updateResultSchedDetail(pmInsSchedId, scheDate, user);
        	}else if("P".equals(pSchedStatus)){
        		workCalPmRInsMonthListDAO.updateScheduleDate(pmInsSchedId, scheDate, remark, user);
        	}
        }
    }
    
    
    public int deleteSched(String[] deleteRows, User user) throws Exception{
        int result = 0;
        String pSchedStatus = "";

        if(!deleteRows.equals(null))
            for(String pmInsSchedId : deleteRows)
            {
            	//TAPMINSSCHED 상태값이 P이면 삭제하고 S이면 삭제태그, C이면 삭제하지 않음.
            	pSchedStatus =  workCalPmRInsMonthListDAO.checkSched(pmInsSchedId, user);
            	if("S".equals(pSchedStatus)){
            		result = result + workCalPmRInsMonthListDAO.updateDeleteTagSched(pmInsSchedId, user);
            	}else if("P".equals(pSchedStatus)){
            		result = result + workCalPmRInsMonthListDAO.deleteSched(pmInsSchedId, user);
            	}
            	
            }
        return result;
    }
	@Override
	public String findTotalCount(WorkCalPmRInsMonthCommonDTO workCalPmRInsMonthCommonDTO, User user) throws Exception {
		return workCalPmRInsMonthListDAO.findTotalCount(workCalPmRInsMonthCommonDTO, user);
	}

}

