package dream.work.cal.pmptrlmonth.service.spring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import common.bean.User;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.StringUtil;
import dream.mgr.cdsys.dto.MgrCdSysCodeListDTO;
import dream.mgr.cdsys.dto.MgrCdSysCommonDTO;
import dream.mgr.cdsys.service.MgrCdSysCodeListService;
import dream.work.cal.pmptrlmonth.dao.WorkCalPmPtrlMonthListDAO;
import dream.work.cal.pmptrlmonth.dto.WorkCalPmPtrlMonthCommonDTO;
import dream.work.cal.pmptrlmonth.service.WorkCalPmPtrlMonthListService;

/**
 * 월간예방일정 - 목록 serviceimpl
 * @author youngjoo38
 * @version $Id: WorkCalPmPtrlMonthListServiceImpl.java,v 1.0 2017/09/24 09:12:51 youngjoo38 Exp $
 * @since 1.0
 *
 * @spring.bean id="workCalPmPtrlMonthListServiceTarget"
 * @spring.txbn id="workCalPmPtrlMonthListService"
 * @spring.property name="workCalPmPtrlMonthListDAO" ref="workCalPmPtrlMonthListDAO"
 */
public class WorkCalPmPtrlMonthListServiceImpl implements WorkCalPmPtrlMonthListService
{
    private WorkCalPmPtrlMonthListDAO workCalPmPtrlMonthListDAO = null;


	public WorkCalPmPtrlMonthListDAO getWorkCalPmPtrlMonthListDAO() {
		return workCalPmPtrlMonthListDAO;
	}
	public void setWorkCalPmPtrlMonthListDAO(
			WorkCalPmPtrlMonthListDAO workCalPmPtrlMonthListDAO) {
		this.workCalPmPtrlMonthListDAO = workCalPmPtrlMonthListDAO;
	}
	
	public List findSchedList(WorkCalPmPtrlMonthCommonDTO workCalPmPtrlMonthCommonDTO, User user)
    {
        return workCalPmPtrlMonthListDAO.findSchedList(workCalPmPtrlMonthCommonDTO,user);
    }
	public String findSchedule(WorkCalPmPtrlMonthCommonDTO workCalPmPtrlMonthCommonDTO, User user) throws Exception
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
        String yyyymm = CommonUtil.getRowDateToNum(workCalPmPtrlMonthCommonDTO.getYyyymmdd());
        if(yyyymm.length()<6) return "";
        int lastDay = Integer.parseInt(DateUtil.getLastDayOfMonth(yyyymm.substring(0, 4), yyyymm.substring(4, 6)));
        for(int d=1;d<=lastDay;d++){
            String yyyyMMdd = yyyymm.substring(0, 6) + (d<10?"0"+d:""+d);
            dateMap.put(yyyyMMdd, new HashMap<String,Integer>(statusMap));
            dateMap.get(yyyyMMdd).put("WEEK", DateUtil.getWeekOfMonth(yyyyMMdd));
            dateMap.get(yyyyMMdd).put("DOW", DateUtil.getDayOfWeek(yyyyMMdd));
        }
        List<Map> list = this.findSchedList(workCalPmPtrlMonthCommonDTO, user);
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

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + workCalPmPtrlMonthListDAO.deleteSched(id, user);
            }
        return result;
    }
	
	@Override
    public String findTotalCount(WorkCalPmPtrlMonthCommonDTO workCalPmPtrlMonthCommonDTO, User user)
    {
        return workCalPmPtrlMonthListDAO.findTotalCount(workCalPmPtrlMonthCommonDTO, user);
    }
}

