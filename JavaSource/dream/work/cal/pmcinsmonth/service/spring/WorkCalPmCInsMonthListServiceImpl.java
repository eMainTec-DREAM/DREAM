package dream.work.cal.pmcinsmonth.service.spring;

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
import dream.work.cal.pmcinsmonth.dao.WorkCalPmCInsMonthListDAO;
import dream.work.cal.pmcinsmonth.dto.WorkCalPmCInsMonthCommonDTO;
import dream.work.cal.pmcinsmonth.service.WorkCalPmCInsMonthListService;

/**
 * 월간예방일정 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: WorkCalPmCInsMonthListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 *
 * @spring.bean id="workCalPmCInsMonthListServiceTarget"
 * @spring.txbn id="workCalPmCInsMonthListService"
 * @spring.property name="workCalPmCInsMonthListDAO" ref="workCalPmCInsMonthListDAO"
 */
public class WorkCalPmCInsMonthListServiceImpl implements WorkCalPmCInsMonthListService
{
    private WorkCalPmCInsMonthListDAO workCalPmCInsMonthListDAO = null;


	public WorkCalPmCInsMonthListDAO getWorkCalPmCInsMonthListDAO() {
		return workCalPmCInsMonthListDAO;
	}
	public void setWorkCalPmCInsMonthListDAO(
			WorkCalPmCInsMonthListDAO workCalPmCInsMonthListDAO) {
		this.workCalPmCInsMonthListDAO = workCalPmCInsMonthListDAO;
	}
	

	public List findSchedList(WorkCalPmCInsMonthCommonDTO workCalPmCInsMonthCommonDTO, User user)
    {
        return workCalPmCInsMonthListDAO.findSchedList(workCalPmCInsMonthCommonDTO,user);
    }
	public String findSchedule(WorkCalPmCInsMonthCommonDTO workCalPmCInsMonthCommonDTO, User user) throws Exception
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
        String yyyymm = CommonUtil.getRowDateToNum(workCalPmCInsMonthCommonDTO.getYyyymmdd());
        if(yyyymm.length()<6) return "";
        int lastDay = Integer.parseInt(DateUtil.getLastDayOfMonth(yyyymm.substring(0, 4), yyyymm.substring(4, 6)));
        for(int d=1;d<=lastDay;d++){
            String yyyyMMdd = yyyymm.substring(0, 6) + (d<10?"0"+d:""+d);
            dateMap.put(yyyyMMdd, new HashMap<String,Integer>(statusMap));
            dateMap.get(yyyyMMdd).put("WEEK", DateUtil.getWeekOfMonth(yyyyMMdd));
            dateMap.get(yyyyMMdd).put("DOW", DateUtil.getDayOfWeek(yyyyMMdd));
        }
        List<Map> list = this.findSchedList(workCalPmCInsMonthCommonDTO, user);
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

	public int deleteSched(String[] deleteRows, User user) throws Exception{
        int result = 0;
        String pSchedStatus = "";

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
            		result = result + workCalPmCInsMonthListDAO.deleteSched(id, user);
            		
            		pSchedStatus =  workCalPmCInsMonthListDAO.checkSched(id, user);
                	
                	if("S".equals(pSchedStatus)){
                		result = result + workCalPmCInsMonthListDAO.updateDeleteTagSched(id, user);
                	}else if("P".equals(pSchedStatus)){
                		result = result + workCalPmCInsMonthListDAO.deleteSched(id, user);
                	}
                	
            }
        return result;
    }

	public ResponseDTO monthlyScheduled(WorkCalPmCInsMonthCommonDTO workCalPmCInsMonthCommonDTO,User user) throws Exception
	{
	    workCalPmCInsMonthCommonDTO.setYyyymmdd(workCalPmCInsMonthCommonDTO.getYyyymmdd().substring(0, 6));
	    workCalPmCInsMonthCommonDTO.setSchedType("P");
		String[] nextSchedList = StringUtil.toSingleArray(this.findSchedList(workCalPmCInsMonthCommonDTO, user), "PMINSDSCHEDID");
		return this.dailyScheduled(nextSchedList, workCalPmCInsMonthCommonDTO, user);
    }

	public ResponseDTO dailyScheduled(String[] fixRows, WorkCalPmCInsMonthCommonDTO workCalPmCInsMonthCommonDTO,User user) throws Exception
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
        workCalPmCInsMonthCommonDTO.setPmInsDSchedId(StringUtils.join(fixRows, "+"));
        List<Map> list = this.findSchedList(workCalPmCInsMonthCommonDTO, user);
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
            workCalPmCInsMonthListDAO.SP_PM_MAKE_TO_PMI(id, user);
        }
        list = this.findSchedList(workCalPmCInsMonthCommonDTO, user);
        builder.status(HttpServletResponse.SC_OK)
        .message(MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG044"))
        .data(list);
        
        return builder.build();
    }


    public void updateSchedule(List gridList, User user)
    {
        for(Object obj : gridList)
        {
        	String pSchedStatus = "";
        	Map rowMap = (Map)obj;
            String scheDate     = ((String)rowMap.get("SCHEDDATE"));
            String pmInsSchedId    = (String)rowMap.get("PMINSDSCHEDID");

            if(scheDate == "") continue;
            
            if(scheDate.indexOf("-") >=0)scheDate = CommonUtil.convertDate(scheDate);

            pSchedStatus =  workCalPmCInsMonthListDAO.checkSched(pmInsSchedId, user);
        	if("S".equals(pSchedStatus)){
        		workCalPmCInsMonthListDAO.updateScheduleDate(pmInsSchedId, scheDate, user );
        		workCalPmCInsMonthListDAO.updateResultSchedDetail(pmInsSchedId, scheDate, user );
        	}else if("P".equals(pSchedStatus)){	
        		workCalPmCInsMonthListDAO.updateScheduleDate(pmInsSchedId, scheDate, user );
        	}
        	
        }

    }
    
    @Override
    public String findTotalCount(WorkCalPmCInsMonthCommonDTO workCalPmCInsMonthCommonDTO, User user)
    {
        return workCalPmCInsMonthListDAO.findTotalCount(workCalPmCInsMonthCommonDTO, user);
    }

}

