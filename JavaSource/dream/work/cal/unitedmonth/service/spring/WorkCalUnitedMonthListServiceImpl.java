package dream.work.cal.unitedmonth.service.spring;

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
import dream.work.cal.pmyear.dao.MaWoYearSchedDetailDAO;
import dream.work.cal.unitedmonth.dao.WorkCalUnitedMonthListDAO;
import dream.work.cal.unitedmonth.dto.WorkCalUnitedMonthCommonDTO;
import dream.work.cal.unitedmonth.service.WorkCalUnitedMonthListService;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 월간작업일정 - 목록 serviceimpl
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="workCalUnitedMonthListServiceTarget"
 * @spring.txbn id="workCalUnitedMonthListService"
 * @spring.property name="workCalUnitedMonthListDAO" ref="workCalUnitedMonthListDAO"
 * @spring.property name="maWoYearSchedDetailDAO" ref="maWoYearSchedDetailDAO"
 */
public class WorkCalUnitedMonthListServiceImpl implements WorkCalUnitedMonthListService
{
    private WorkCalUnitedMonthListDAO workCalUnitedMonthListDAO = null;

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

    public WorkCalUnitedMonthListDAO getWorkCalUnitedMonthListDAO() {
		return workCalUnitedMonthListDAO;
	}

	public void setWorkCalUnitedMonthListDAO(WorkCalUnitedMonthListDAO workCalUnitedMonthListDAO) {
		this.workCalUnitedMonthListDAO = workCalUnitedMonthListDAO;
	}

	public List findWorkSchedList(WorkCalUnitedMonthCommonDTO workCalUnitedMonthCommonDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User loginUser)
    {      
		return workCalUnitedMonthListDAO.findWorkSchedList(workCalUnitedMonthCommonDTO,maWoResultMstrCommonDTO, loginUser);
    }
	
	public List findPmiSchedList(WorkCalUnitedMonthCommonDTO workCalUnitedMonthCommonDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User loginUser)
	{      
		return workCalUnitedMonthListDAO.findPmiSchedList(workCalUnitedMonthCommonDTO,maWoResultMstrCommonDTO,loginUser);
	}
	public String findSchedule(WorkCalUnitedMonthCommonDTO workCalUnitedMonthCommonDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user) throws Exception
	{
	    Map<String,Map<String,Object>> dateMap = new HashMap<String,Map<String,Object>>();
	    Map<String,Map<String,Integer>> cntMap = new HashMap<String,Map<String,Integer>>();
	    
        Map<String,Integer> woStatusMap = new HashMap<String,Integer>();
        MgrCdSysCodeListService mgrCdSysCodeListService = (MgrCdSysCodeListService) CommonUtil.getBean("mgrCdSysCodeListService", user);
        MgrCdSysCodeListDTO mgrCdSysCodeListDTO = new MgrCdSysCodeListDTO();
        mgrCdSysCodeListDTO.setListType("WO_STATUS");
        String[] woStatusArray = StringUtil.toSingleArray(mgrCdSysCodeListService.findCodeList(new MgrCdSysCommonDTO(), mgrCdSysCodeListDTO), "CODE");
        for(String status:woStatusArray){
            woStatusMap.put(status, 0);
        }
        woStatusMap.put("TOTAL", 0);
        
        Map<String,Integer> pmiStatusMap = new HashMap<String,Integer>();
        mgrCdSysCodeListDTO.setListType("PMSCHED_STATUS");
        String[] pmiStatusArray = StringUtil.toSingleArray(mgrCdSysCodeListService.findCodeList(new MgrCdSysCommonDTO(), mgrCdSysCodeListDTO), "CODE");
        for(String status:pmiStatusArray){
            pmiStatusMap.put(status, 0);
        }
        pmiStatusMap.put("TOTAL", 0);
        
        String yyyymm = CommonUtil.getRowDateToNum(workCalUnitedMonthCommonDTO.getYyyymmdd());
        if(yyyymm.length()<6) return "";
        int lastDay = Integer.parseInt(DateUtil.getLastDayOfMonth(yyyymm.substring(0, 4), yyyymm.substring(4, 6)));
        for(int d=1;d<=lastDay;d++){
            String yyyyMMdd = yyyymm.substring(0, 6) + (d<10?"0"+d:""+d);
            cntMap.put("WO", new HashMap<String, Integer>(woStatusMap));
            cntMap.put("PMI", new HashMap<String, Integer>(pmiStatusMap));
            dateMap.put(yyyyMMdd, new HashMap<String,Object>(cntMap));
            dateMap.get(yyyyMMdd).put("WEEK", DateUtil.getWeekOfMonth(yyyyMMdd));
            dateMap.get(yyyyMMdd).put("DOW", DateUtil.getDayOfWeek(yyyyMMdd));
        }
        
        List<Map> woList = this.findWorkSchedList(workCalUnitedMonthCommonDTO, maWoResultMstrCommonDTO, user);
        for(Map map:woList){
            if(StringUtil.valueOf(map.get("WKORDATE")).length()==8){
                String day = StringUtil.valueOf(map.get("WKORDATE"));
                
                woStatusMap = (Map<String, Integer>) dateMap.get(day).get("WO");
                String status = StringUtil.valueOf(map.get("WOSTATUS"));
                if(woStatusMap.containsKey(status)){
                    woStatusMap.put(status, woStatusMap.get(status)+1);
                }
                woStatusMap.put("TOTAL", woStatusMap.get("TOTAL")+1);
            }
        }
        List<Map> pmiList = this.findPmiSchedList(workCalUnitedMonthCommonDTO, maWoResultMstrCommonDTO, user);
        for(Map map:pmiList){
            if(StringUtil.valueOf(map.get("WKORDATE")).length()==8){
                String day = StringUtil.valueOf(map.get("WKORDATE"));
                
                pmiStatusMap = (Map<String, Integer>) dateMap.get(day).get("PMI");
                String status = StringUtil.valueOf(map.get("PMSCHEDSTATUS"));
                if(pmiStatusMap.containsKey(status)){
                    pmiStatusMap.put(status, pmiStatusMap.get(status)+1);
                }
                pmiStatusMap.put("TOTAL", pmiStatusMap.get("TOTAL")+1);
            }
        }
        return new Gson().toJson(dateMap);
	}
	
	@Override
	public String findTotalCount(WorkCalUnitedMonthCommonDTO workCalUnitedMonthCommonDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user) throws Exception {
		return workCalUnitedMonthListDAO.findTotalCount(workCalUnitedMonthCommonDTO, maWoResultMstrCommonDTO,user);
	}
	@Override
	public String findPmiTotalCount(WorkCalUnitedMonthCommonDTO workCalUnitedMonthCommonDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user) throws Exception {
		return workCalUnitedMonthListDAO.findPmiTotalCount(workCalUnitedMonthCommonDTO, maWoResultMstrCommonDTO,user);
	}
}

