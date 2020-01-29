package dream.work.cal.womonth.service.spring;

import java.util.ArrayList;
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
import dream.work.cal.womonth.dao.MaWoMonthWoListDAO;
import dream.work.cal.womonth.dto.MaWoMonthWoCommonDTO;
import dream.work.cal.womonth.service.MaWoMonthWoListService;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 월간작업일정 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: MaWoMonthWoListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maWoMonthWoListServiceTarget"
 * @spring.txbn id="maWoMonthWoListService"
 * @spring.property name="maWoMonthWoListDAO" ref="maWoMonthWoListDAO"
 * @spring.property name="maWoYearSchedDetailDAO" ref="maWoYearSchedDetailDAO"
 */
public class MaWoMonthWoListServiceImpl implements MaWoMonthWoListService
{
    private MaWoMonthWoListDAO maWoMonthWoListDAO = null;

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

    public MaWoMonthWoListDAO getMaWoMonthWoListDAO() {
		return maWoMonthWoListDAO;
	}

	public void setMaWoMonthWoListDAO(MaWoMonthWoListDAO maWoMonthWoListDAO) {
		this.maWoMonthWoListDAO = maWoMonthWoListDAO;
	}

	public List findSchedList(MaWoMonthWoCommonDTO maWoMonthWoCommonDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User loginUser)
    {      
        return maWoMonthWoListDAO.findSchedList(maWoMonthWoCommonDTO,maWoResultMstrCommonDTO,loginUser);
    }
	public String findSchedule(MaWoMonthWoCommonDTO maWoMonthWoCommonDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user) throws Exception
	{
	    Map<String,Map<String,Integer>> dateMap = new HashMap<String,Map<String,Integer>>();
        Map<String,Integer> statusMap = new HashMap<String,Integer>();
        MgrCdSysCodeListService mgrCdSysCodeListService = (MgrCdSysCodeListService) CommonUtil.getBean("mgrCdSysCodeListService", user);
        MgrCdSysCodeListDTO mgrCdSysCodeListDTO = new MgrCdSysCodeListDTO();
        mgrCdSysCodeListDTO.setListType("WO_STATUS");
        String[] statusArray = StringUtil.toSingleArray(mgrCdSysCodeListService.findCodeList(new MgrCdSysCommonDTO(), mgrCdSysCodeListDTO), "CODE");
        for(String status:statusArray){
            statusMap.put(status, 0);
        }
        statusMap.put("TOTAL", 0);
        String yyyymm = CommonUtil.getRowDateToNum(maWoMonthWoCommonDTO.getYyyymmdd());
        if(yyyymm.length()<6) return "";
        int lastDay = Integer.parseInt(DateUtil.getLastDayOfMonth(yyyymm.substring(0, 4), yyyymm.substring(4, 6)));
        for(int d=1;d<=lastDay;d++){
            String yyyyMMdd = yyyymm.substring(0, 6) + (d<10?"0"+d:""+d);
            dateMap.put(yyyyMMdd, new HashMap<String,Integer>(statusMap));
            dateMap.get(yyyyMMdd).put("WEEK", DateUtil.getWeekOfMonth(yyyyMMdd));
            dateMap.get(yyyyMMdd).put("DOW", DateUtil.getDayOfWeek(yyyyMMdd));
        }
        List<Map> list = this.findSchedList(maWoMonthWoCommonDTO, maWoResultMstrCommonDTO, user);
        for(Map map:list){
            if(StringUtil.valueOf(map.get("WKORDATE")).length()==8){
                String day = StringUtil.valueOf(map.get("WKORDATE"));
                
                statusMap = dateMap.get(day);
                String status = StringUtil.valueOf(map.get("WOSTATUS"));
                if(statusMap.containsKey(status)){
                    statusMap.put(status, statusMap.get(status)+1);
                }
                statusMap.put("TOTAL", statusMap.get("TOTAL")+1);
            }
        }
        return new Gson().toJson(dateMap);
	}
	
	public int updateDeleteTagSched(String[] deleteRows, User user) throws Exception{
        int result = 0;
        
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maWoMonthWoListDAO.updateDeleteTagSched(id, user);
                result = result + maWoMonthWoListDAO.create4wp(id, user);
            }
        return result;
    }
	
	
	public List getReportView(MaWoMonthWoCommonDTO maWoMonthWoCommonDTO) {
		Map<String, Object> reportMap = null;
        List<Map<String, Object>> reportList = new ArrayList<Map<String, Object>>();
        
        String[] wkorId = maWoMonthWoCommonDTO.getSelectedWkorId().split(",");
        //맨앞은 빈칸이라 1부터
        for (int i = 1; i < wkorId.length; i++) {
        	List detailList = maWoMonthWoListDAO.findReportWoList(wkorId[i], maWoMonthWoCommonDTO);
        	reportMap = (Map)detailList.get(0);
        	
        	reportMap.put("CRAFT_LIST", maWoMonthWoListDAO.findReportWoCraftList(wkorId[i], maWoMonthWoCommonDTO));
         	reportMap.put("PART_LIST", maWoMonthWoListDAO.findReportWoPartList(wkorId[i], maWoMonthWoCommonDTO));
         	reportMap.put("POINT_LIST", maWoMonthWoListDAO.findReportWoPointList(wkorId[i], maWoMonthWoCommonDTO));
         	reportMap.put("EQ_LIST", maWoMonthWoListDAO.findReportWoEqList(wkorId[i], maWoMonthWoCommonDTO));
        	reportList.add((Map)reportMap);
		}
        
		return reportList;
	}

	@Override
	public String findTotalCount(MaWoMonthWoCommonDTO maWoMonthWoCommonDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user) throws Exception {
		return maWoMonthWoListDAO.findTotalCount(maWoMonthWoCommonDTO, maWoResultMstrCommonDTO, user);
	}
}

