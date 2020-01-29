package dream.work.cal.pmmonth.service.spring;

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
import dream.work.cal.pmmonth.dao.MaWoMonthSchedListDAO;
import dream.work.cal.pmmonth.dto.MaWoMonthSchedCommonDTO;
import dream.work.cal.pmmonth.service.MaWoMonthSchedListService;
import dream.work.cal.pmperiod.service.MaWoSchedListService;
import dream.work.cal.pmyear.dao.MaWoYearSchedListDAO;

/**
 * 월간예방일정 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: MaWoMonthSchedListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maWoMonthSchedListServiceTarget"
 * @spring.txbn id="maWoMonthSchedListService"
 * @spring.property name="maWoMonthSchedListDAO" ref="maWoMonthSchedListDAO"
 * @spring.property name="maWoYearSchedListDAO" ref="maWoYearSchedListDAO"
 */
public class MaWoMonthSchedListServiceImpl implements MaWoMonthSchedListService
{
    private MaWoMonthSchedListDAO maWoMonthSchedListDAO = null;
    
    private MaWoYearSchedListDAO maWoYearSchedListDAO = null;
    

    public MaWoYearSchedListDAO getMaWoYearSchedListDAO() {
		return maWoYearSchedListDAO;
	}

	public void setMaWoYearSchedListDAO(MaWoYearSchedListDAO maWoYearSchedListDAO) {
		this.maWoYearSchedListDAO = maWoYearSchedListDAO;
	}


    public MaWoMonthSchedListDAO getMaWoMonthSchedListDAO() {
		return maWoMonthSchedListDAO;
	}

	public void setMaWoMonthSchedListDAO(MaWoMonthSchedListDAO maWoMonthSchedListDAO) {
		this.maWoMonthSchedListDAO = maWoMonthSchedListDAO;
	}

	public List findSchedList(MaWoMonthSchedCommonDTO maWoMonthSchedCommonDTO, User user)
    {      
        return maWoMonthSchedListDAO.findSchedList(maWoMonthSchedCommonDTO,user);
    }
	public String findSchedule(MaWoMonthSchedCommonDTO maWoMonthSchedCommonDTO, User user) throws Exception{
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
        String yyyymm = CommonUtil.getRowDateToNum(maWoMonthSchedCommonDTO.getYyyymmdd());
        if(yyyymm.length()<6) return "";
        int lastDay = Integer.parseInt(DateUtil.getLastDayOfMonth(yyyymm.substring(0, 4), yyyymm.substring(4, 6)));
        for(int d=1;d<=lastDay;d++){
            String yyyyMMdd = yyyymm.substring(0, 6) + (d<10?"0"+d:""+d);
            dateMap.put(yyyyMMdd, new HashMap<String,Integer>(statusMap));
            dateMap.get(yyyyMMdd).put("WEEK", DateUtil.getWeekOfMonth(yyyyMMdd));
            dateMap.get(yyyyMMdd).put("DOW", DateUtil.getDayOfWeek(yyyyMMdd));
        }
        List<Map> list = this.findSchedList(maWoMonthSchedCommonDTO, user);
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
	
	public ResponseDTO dailyScheduled(String[] fixRows, MaWoMonthSchedCommonDTO maWoMonthSchedCommonDTO,User user) throws Exception
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
        maWoMonthSchedCommonDTO.setPmSchedId(StringUtils.join(fixRows, "+"));
        List<Map> list = this.findSchedList(maWoMonthSchedCommonDTO, user);
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
            maWoMonthSchedListDAO.SP_PM_MAKE_WO_BYONE(user.getCompNo(), user.getUserNo(),id);
        }
        list = this.findSchedList(maWoMonthSchedCommonDTO, user);
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
    
    public int deleteSched(String[] deleteRows, User user) throws Exception{
        MaWoSchedListService maWoSchedListService = (MaWoSchedListService) CommonUtil.getBean("maWoSchedListService", user);
        return maWoSchedListService.deleteSched(deleteRows, user);
    }

	@Override
	public String findTotalCount(MaWoMonthSchedCommonDTO maWoMonthSchedCommonDTO, User user)
	{
	    return maWoMonthSchedListDAO.findTotalCount(maWoMonthSchedCommonDTO, user);

	}
    
    
}

