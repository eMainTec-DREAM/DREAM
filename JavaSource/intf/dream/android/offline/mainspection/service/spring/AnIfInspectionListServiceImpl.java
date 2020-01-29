package intf.dream.android.offline.mainspection.service.spring;

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
import intf.dream.android.common.CommonValues;
import intf.dream.android.offline.mainspection.dao.AnIfInspectionListDAO;
import intf.dream.android.offline.mainspection.service.AnIfInspectionListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anIfInspectionListServiceTarget"
 * @spring.txbn id="anIfInspectionListService"
 * @spring.property name="anIfInspectionListDAO" ref="anIfInspectionListDAO"
 * @spring.property name="maWoResultMstrDetailService" ref="maWoResultMstrDetailService"
 */
public class AnIfInspectionListServiceImpl implements AnIfInspectionListService
{
    private AnIfInspectionListDAO anIfInspectionListDAO = null;
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
	public AnIfInspectionListDAO getAnIfInspectionListDAO() {
		return anIfInspectionListDAO;
	}
	public void setAnIfInspectionListDAO(AnIfInspectionListDAO anIfInspectionListDAO) {
		this.anIfInspectionListDAO = anIfInspectionListDAO;
	}
	
	public List findSchedList(Map map) throws Exception
	{      
		return anIfInspectionListDAO.findSchedList(map);
	}
	public List findPmlstList(Map map) throws Exception
	{      
		return anIfInspectionListDAO.findPmlstList(map);
	}
	public List findPmequipList(Map map) throws Exception
	{      
		return anIfInspectionListDAO.findPmequipList(map);
	}
	public List findPmpointList(Map map) throws Exception
	{      
		return anIfInspectionListDAO.findPmpointList(map);
	}
	
	public int saveWorkorder(List list)  throws Exception
	{
        Type type = new TypeToken<List<Map<String, String>>>(){}.getType();
		MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();

		int resultQty = 0;
		
		for(Object obj : list){
			Map map = (Map)obj;
			
        	//W/O ����üũ �� ���°� ���ε� ����� �ƴϸ� Skip
        	if(!CommonUtil.isUseStringInArray(CommonValues.USED_WO_STATES, anIfInspectionListDAO.getWoStatusOfWorkOrder(map))){
        		continue;
        	}
			
			//�ð����
            String workTime = "";
            String startDate = CommonUtil.convertString(map.get("startDate"));
            String startTime = CommonUtil.convertString(map.get("startTime")); //5���� �������
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
            		wopointId = anIfInspectionListDAO.getWopoint(pointMap);
            		if("".equals(wopointId)){
            			wopointId = anIfInspectionListDAO.getNextSequence("SQAWOPOINT_ID");
            		}
            		
            		anIfInspectionListDAO.saveWopoint(pointMap,wopointId); //���� ���ε�
                    
                    if(!"GD".equals(pmPointRsltStatus) && "Y".equals(MwareConfig.getIsCompInswrkMoload())) anIfInspectionListDAO.mergeAbnormalRslt(pointMap,wopointId);
                }
            }
            
//            anIfInspectionListDAO.updatePmSched(map); // ��ĳ�� �Ϸ� 
            anIfInspectionListDAO.updateWorkOrder(map);
            anIfInspectionListDAO.updateWoCraft(map);
            
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
                
                //�Ϸᰡ �ƴϸ� �Ϸ� ��Ŵ
                if("Y".equals(MwareConfig.getIsCompInswrkMoload())&&!"C".equals(maWoResultMstrDetailDTO.getWoStatusId())&&maWoResultMstrDetailDTO.getWkOrId()!=null&&!"".equals(maWoResultMstrDetailDTO.getWkOrId())){
                	maWoResultMstrDetailService.completeDetail(maWoResultMstrDetailDTO,maWoResultPmCalDTO);
                }
            }
        }
        return resultQty;
	}
}

