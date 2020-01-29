package dream.work.cal.pmyear.service.spring;

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
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.work.cal.pmperiod.service.MaWoSchedListService;
import dream.work.cal.pmyear.dao.MaWoYearSchedListDAO;
import dream.work.cal.pmyear.dto.MaWoYearSchedCommonDTO;
import dream.work.cal.pmyear.service.MaWoYearSchedListService;

/**
 * 연간작업일정 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: MaWoYearSchedListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maWoYearSchedListServiceTarget"
 * @spring.txbn id="maWoYearSchedListService"
 * @spring.property name="maWoYearSchedListDAO" ref="maWoYearSchedListDAO"
 */
public class MaWoYearSchedListServiceImpl implements MaWoYearSchedListService
{
    private MaWoYearSchedListDAO maWoYearSchedListDAO = null;
    

    public MaWoYearSchedListDAO getMaWoYearSchedListDAO() {
		return maWoYearSchedListDAO;
	}

	public void setMaWoYearSchedListDAO(MaWoYearSchedListDAO maWoYearSchedListDAO) {
		this.maWoYearSchedListDAO = maWoYearSchedListDAO;
	}

	public List findSchedList(MaWoYearSchedCommonDTO maWoYearSchedCommonDTO,User user)
    {      
        return maWoYearSchedListDAO.findSchedList(maWoYearSchedCommonDTO,user);
    }
	
	public int deleteSched(String[] deleteRows,User user) throws Exception{
	    MaWoSchedListService maWoSchedListService = (MaWoSchedListService) CommonUtil.getBean("maWoSchedListService", user);
        return maWoSchedListService.deleteSched(deleteRows, user);
    }

    public void updateSchedule(List gridList, User user)
    {
    	String pSchedStatus = "";
    	
        for(Object obj : gridList)
        {
            Map rowMap = (Map)obj;
            String pmSchedId    = (String)rowMap.get("PMSCHEDID");
            String scheDate     = (String)rowMap.get("SCHEDDATE");
            String remark       = (String)rowMap.get("REMARK");
            
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
    public String findTotalCount(MaWoYearSchedCommonDTO maWoYearSchedCommonDTO,User user)
    {
        return maWoYearSchedListDAO.findTotalCount(maWoYearSchedCommonDTO,user);
    }

	@Override
	public String findSchedule(MaWoYearSchedCommonDTO maWoYearSchedCommonDTO,User user) throws Exception
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
        List<Map> list = this.findSchedList(maWoYearSchedCommonDTO, user);
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
	public ResponseDTO dailyScheduled(String[] fixRows, MaWoYearSchedCommonDTO maWoYearSchedCommonDTO, User user) throws Exception
	{
		ResponseDTO.Builder builder = new ResponseDTO.Builder(MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG044")).status(HttpServletResponse.SC_OK);
		
		//no selected rows
		if(fixRows.equals(null) || fixRows.length==0) {
		    builder
		    .status(HttpServletResponse.SC_BAD_REQUEST)
            .message(MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG0074"));
		    return builder.build();
		}
		
		//data validation
		boolean isValid = true;
		maWoYearSchedCommonDTO.setPmSchedId(StringUtils.join(fixRows, "+"));
		List<Map> list = this.findSchedList(maWoYearSchedCommonDTO, user);
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
		    maWoYearSchedListDAO.SP_PM_MAKE_WO_BYONE(user.getCompNo(), user.getUserNo(),id);
		}
		list = this.findSchedList(maWoYearSchedCommonDTO, user);
		builder.status(HttpServletResponse.SC_OK)
		.message(MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG044"))
		.data(list);
		
        return builder.build();
	}
	
	@Override
    public void apprProcess(AppReqDetailDTO appReqDetailDTO, User user) throws Exception
    {
        String pmSchedStatus = "";
        switch (appReqDetailDTO.getParentStatus())
        {
            case "PRWRA":
                pmSchedStatus = "IRWRA";    // 결재 요청
                break;
            case "PRWOA":
                pmSchedStatus = "IRWOA";    // 결재 중
                break;
            case "PRWDA":
                pmSchedStatus = "IRWDA";    // 결재 반려
                break;
            case "C":
                pmSchedStatus = "C";        // 완료  
                break;
            case "":
                pmSchedStatus = "";         // 작성중
                break;
            default:
                break;
        }
        maWoYearSchedListDAO.updatePmSchedStatus(appReqDetailDTO, user, pmSchedStatus);
    }
}

