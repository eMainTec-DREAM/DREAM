package dream.work.cal.pmdinsmonth.service.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

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
import dream.work.cal.pmdinsmonth.dao.WorkCalPmDInsMonthListDAO;
import dream.work.cal.pmdinsmonth.dto.WorkCalPmDInsMonthCommonDTO;
import dream.work.cal.pmdinsmonth.dto.WorkCalPmDInsMonthDetailDTO;
import dream.work.cal.pmdinsmonth.service.WorkCalPmDInsMonthDetailService;
import dream.work.cal.pmdinsmonth.service.WorkCalPmDInsMonthListService;
import dream.work.pm.list.dto.WorkPmiDInsCommonDTO;
import dream.work.pm.list.service.WorkPmiDInsListService;

/**
 * 월간예방일정 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: WorkCalPmDInsMonthListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 *
 * @spring.bean id="workCalPmDInsMonthListServiceTarget"
 * @spring.txbn id="workCalPmDInsMonthListService"
 * @spring.property name="workCalPmDInsMonthListDAO" ref="workCalPmDInsMonthListDAO"
 */
public class WorkCalPmDInsMonthListServiceImpl implements WorkCalPmDInsMonthListService
{
    private WorkCalPmDInsMonthListDAO workCalPmDInsMonthListDAO = null;

	public WorkCalPmDInsMonthListDAO getWorkCalPmDInsMonthListDAO() {
		return workCalPmDInsMonthListDAO;
	}
	public void setWorkCalPmDInsMonthListDAO(
			WorkCalPmDInsMonthListDAO workCalPmDInsMonthListDAO) {
		this.workCalPmDInsMonthListDAO = workCalPmDInsMonthListDAO;
	}
	
	public List findSchedList(WorkCalPmDInsMonthCommonDTO workCalPmDInsMonthCommonDTO, User user)
    {
        return workCalPmDInsMonthListDAO.findSchedList(workCalPmDInsMonthCommonDTO,user);
    }
	
	public String findSchedule(WorkCalPmDInsMonthCommonDTO workCalPmDInsMonthCommonDTO, User user) throws Exception
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
        String yyyymm = CommonUtil.getRowDateToNum(workCalPmDInsMonthCommonDTO.getYyyymmdd());
        if(yyyymm.length()<6) return "";
        int lastDay = Integer.parseInt(DateUtil.getLastDayOfMonth(yyyymm.substring(0, 4), yyyymm.substring(4, 6)));
        for(int d=1;d<=lastDay;d++){
            String yyyyMMdd = yyyymm.substring(0, 6) + (d<10?"0"+d:""+d);
            dateMap.put(yyyyMMdd, new HashMap<String,Integer>(statusMap));
            dateMap.get(yyyyMMdd).put("WEEK", DateUtil.getWeekOfMonth(yyyyMMdd));
            dateMap.get(yyyyMMdd).put("DOW", DateUtil.getDayOfWeek(yyyyMMdd));
        }
        List<Map> list = this.findSchedList(workCalPmDInsMonthCommonDTO, user);
        for(Map map:list){
            if(StringUtil.valueOf(map.get("SCHEDDATE")).length()==8){
                String day = StringUtil.valueOf(map.get("SCHEDDATE"));
                
                statusMap = dateMap.get(day);
                String status = StringUtil.valueOf(map.get("PMSCHEDSTATUS"));
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
        
        if(!deleteRows.equals(null) && deleteRows.length>0){
            String pmInsDSchedId = StringUtils.join(deleteRows, "+");
            
            //DELETE TAPMINSDLIST
            WorkPmiDInsListService workPmiDInsListService = (WorkPmiDInsListService) CommonUtil.getBean("workPmiDInsListService", user);
            WorkPmiDInsCommonDTO workPmiDInsCommonDTO = new WorkPmiDInsCommonDTO();
            workPmiDInsCommonDTO.setPmInsDSchedId(pmInsDSchedId);
            String[] pmInsDListIds = StringUtil.toSingleArray(workPmiDInsListService.findList(workPmiDInsCommonDTO, user), "PMINSDLISTID");
            workPmiDInsListService.deleteList(pmInsDListIds, user);
            
            //DELETE TAPMINSDSCHED
            List list = new ArrayList();
            WorkCalPmDInsMonthDetailDTO workCalPmDInsMonthDetailDTO = new WorkCalPmDInsMonthDetailDTO();
            for(String id:deleteRows){
                workCalPmDInsMonthDetailDTO.setPmInsDSchedId(id);
                list.add(BeanUtils.cloneBean(workCalPmDInsMonthDetailDTO));
            }
            result = workCalPmDInsMonthListDAO.updateDeleteTag(list, user).length;
        }
        
        return result;
    }
	
	public void updateSchedule(List<Map> gridList, User user) throws Exception
    {
	    WorkCalPmDInsMonthDetailService workCalPmDInsMonthDetailService = (WorkCalPmDInsMonthDetailService) CommonUtil.getBean("workCalPmDInsMonthDetailService", user);
	    WorkCalPmDInsMonthDetailDTO workCalPmDInsMonthDetailDTO;
	    List listDto = new ArrayList();
		for(Map mapDto : gridList)
        {
		    workCalPmDInsMonthDetailDTO = (WorkCalPmDInsMonthDetailDTO) CommonUtil.makeDTO(mapDto, WorkCalPmDInsMonthDetailDTO.class);
		    if("".equals(workCalPmDInsMonthDetailDTO.getSchedDate())) continue;
            listDto.add(BeanUtils.cloneBean(workCalPmDInsMonthDetailDTO));
        }
		workCalPmDInsMonthDetailService.updateList(listDto, user);
    }
	
	@Override
	public ResponseDTO dailyScheduled(String[] fixRows, WorkCalPmDInsMonthCommonDTO workCalPmDInsMonthCommonDTO, User user) throws Exception
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
        workCalPmDInsMonthCommonDTO.setPmInsDSchedId(StringUtils.join(fixRows, "+"));
        List<Map> list = this.findSchedList(workCalPmDInsMonthCommonDTO, user);
        for(Map map:list) {
            if(!"P".equals(StringUtil.valueOf(map.get("PMSCHEDSTATUS")))) {
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
        
        for(String id : fixRows)
        {
            workCalPmDInsMonthListDAO.SP_PM_MAKE_TO_PMI(id, user);
        }
        list = this.findSchedList(workCalPmDInsMonthCommonDTO, user);
        builder
        .status(HttpServletResponse.SC_OK)
        .message(MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG044"))
        .data(list);
        
        return builder.build();
	}
	
	@Override
    public String findTotalCount(WorkCalPmDInsMonthCommonDTO workCalPmDInsMonthCommonDTO, User user)
    {
        return workCalPmDInsMonthListDAO.findTotalCount(workCalPmDInsMonthCommonDTO, user);
    }
}

