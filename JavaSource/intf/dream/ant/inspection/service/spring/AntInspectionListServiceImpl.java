package intf.dream.ant.inspection.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.reflect.TypeToken;

import common.bean.MwareConfig;
import common.bean.User;
import common.util.CommonUtil;
import common.util.DateUtil;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultMstrDetailDTO;
import dream.work.list.dto.MaWoResultPmCalDTO;
import dream.work.list.service.MaWoResultMstrDetailService;
import intf.dream.ant.common.AntCommonValues;
import intf.dream.ant.inspection.dao.AntInspectionListDAO;
import intf.dream.ant.inspection.service.AntInspectionListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="antInspectionListServiceTarget"
 * @spring.txbn id="antInspectionListService"
 * @spring.property name="antInspectionListDAO" ref="antInspectionListDAO"
 * @spring.property name="maWoResultMstrDetailService" ref="maWoResultMstrDetailService"
 */
public class AntInspectionListServiceImpl implements AntInspectionListService
{
    private AntInspectionListDAO antInspectionListDAO = null;
    private MaWoResultMstrDetailService maWoResultMstrDetailService = null;
    
    public MaWoResultMstrDetailService getMaWoResultMstrDetailService()
    {
        return maWoResultMstrDetailService;
    }

    public void setMaWoResultMstrDetailService(
            MaWoResultMstrDetailService maWoResultMstrDetailService)
    {
        this.maWoResultMstrDetailService = maWoResultMstrDetailService;
    }
	public AntInspectionListDAO getAntInspectionListDAO() {
		return antInspectionListDAO;
	}
	public void setAntInspectionListDAO(AntInspectionListDAO antInspectionListDAO) {
		this.antInspectionListDAO = antInspectionListDAO;
	}
	
	public List findSchedList(Map map) throws Exception
	{      
		return antInspectionListDAO.findSchedList(map);
	}
	public List findPmlstList(Map map) throws Exception
	{      
		return antInspectionListDAO.findPmlstList(map);
	}
	public List findPmequipList(Map map) throws Exception
	{      
		return antInspectionListDAO.findPmequipList(map);
	}
	public List findPmpointList(Map map) throws Exception
	{      
		return antInspectionListDAO.findPmpointList(map);
	}
	
	public int saveWorkorder(List list)  throws Exception
	{
        Type type = new TypeToken<List<Map<String, String>>>(){}.getType();
		MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();

		int resultQty = 0;
		
		for(Object obj : list){
			Map map = (Map)obj;
			
        	//W/O 상태체크 후 상태가 업로드 대상이 아니면 Skip
        	if(!CommonUtil.isUseStringInArray(AntCommonValues.USED_WO_STATES, antInspectionListDAO.getWoStatusOfWorkOrder(map))){
        		continue;
        	}
			
			//시간계산
            String workTime = "";
            String startDate = CommonUtil.convertString(map.get("startDate"));
            String startTime = CommonUtil.convertString(map.get("startTime")); //5분전 없을경우
            String endDate = CommonUtil.convertString(map.get("endDate"));
            String endTime = CommonUtil.convertString(map.get("endTime"));
            
            workTime = DateUtil.getTimeInterval(startDate+startTime, endDate+endTime);
            map.put("workTime", workTime);
            
            String userId = CommonUtil.convertString(map.get("userId"));
            String wkOrId = CommonUtil.convertString(map.get("wkorId"));
            String compNo = CommonUtil.convertString(map.get("compNo"));
            
            String wopointJson = CommonUtil.convertString(map.get("POINT"));
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setLongSerializationPolicy( LongSerializationPolicy.STRING );
            Gson gson = gsonBuilder.create();
            
            List wopointList = gson.fromJson(wopointJson, type);
            
            if(wopointList.size() != 0)
            {
            	for(Object pointObj : wopointList)
                {
            		Map pointMap = (Map)pointObj;   
            		String pmPointRsltStatus = CommonUtil.convertString(pointMap.get("PM_POINT_RSLT_STATUS"));
            		String wopointId = "";
            		wopointId = antInspectionListDAO.getWopoint(pointMap);
            		if("".equals(wopointId)){
            			wopointId = antInspectionListDAO.getNextSequence("SQAWOPOINT_ID");
            		}
            		
            		antInspectionListDAO.saveWopoint(pointMap,wopointId); //점검 업로드
                    
                    if(!"GD".equals(pmPointRsltStatus) && "Y".equals(MwareConfig.getIsCompInswrkMoload())) antInspectionListDAO.mergeAbnormalRslt(pointMap,wopointId);
                }
            }
            
//            antInspectionListDAO.updatePmSched(map); // 스캐줄 완료 
            antInspectionListDAO.updateWorkOrder(map);
            antInspectionListDAO.updateWoCraft(map);
            
            if(!"".equals(wkOrId))
            {
                maWoResultMstrCommonDTO.setWkOrId(wkOrId);
                maWoResultMstrCommonDTO.setCompNo(compNo);
                MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = maWoResultMstrDetailService.findDetail(maWoResultMstrCommonDTO);
                MaWoResultPmCalDTO maWoResultPmCalDTO = new MaWoResultPmCalDTO();
                
                User user = new User();
                user.setCompNo(compNo);
                user.setUserId(userId);
                String lang = "".equals(CommonUtil.convertString(map.get("lang")))?"ko":CommonUtil.convertString(map.get("lang"));
                user.setLangId(lang);
                Locale locale = new Locale(lang);
                user.setLocale(locale);
                maWoResultMstrDetailDTO.setLoginUser(user);
                
                maWoResultMstrDetailDTO.setCompNo(compNo);
                maWoResultMstrDetailDTO.setEnterBy(userId);
                
                //완료가 아니면 완료 시킴
                if("Y".equals(MwareConfig.getIsCompInswrkMoload())&&!"C".equals(maWoResultMstrDetailDTO.getWoStatusId())&&maWoResultMstrDetailDTO.getWkOrId()!=null&&!"".equals(maWoResultMstrDetailDTO.getWkOrId())){
                	maWoResultMstrDetailService.completeDetail(maWoResultMstrDetailDTO,maWoResultPmCalDTO);
                }
            }
        }
        return resultQty;
	}
}

