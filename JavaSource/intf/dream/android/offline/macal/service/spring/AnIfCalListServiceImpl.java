package intf.dream.android.offline.macal.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import common.util.CommonUtil;
import dream.work.list.dao.WoPlanCraftDetailDAO;
import dream.work.list.dao.WoPlanDetailDAO;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanCraftDetailDTO;
import dream.work.list.dto.WoPlanDetailDTO;
import intf.dream.android.common.CommonValues;
import intf.dream.android.offline.macal.dao.AnIfCalListDAO;
import intf.dream.android.offline.macal.service.AnIfCalListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anIfCalListServiceTarget"
 * @spring.txbn id="anIfCalListService"
 * @spring.property name="anIfCalListDAO" ref="anIfCalListDAO"
 * @spring.property name="woPlanDetailDAO" ref="woPlanDetailDAO"
 * @spring.property name="woPlanCraftDetailDAO" ref="woPlanCraftDetailDAO"
 */
public class AnIfCalListServiceImpl implements AnIfCalListService
{
    private AnIfCalListDAO anIfCalListDAO = null;
    private WoPlanDetailDAO woPlanDetailDAO = null;
    private WoPlanCraftDetailDAO woPlanCraftDetailDAO = null;
    
    
	public WoPlanCraftDetailDAO getWoPlanCraftDetailDAO() {
		return woPlanCraftDetailDAO;
	}
	public void setWoPlanCraftDetailDAO(WoPlanCraftDetailDAO woPlanCraftDetailDAO) {
		this.woPlanCraftDetailDAO = woPlanCraftDetailDAO;
	}
	public WoPlanDetailDAO getWoPlanDetailDAO() {
		return woPlanDetailDAO;
	}
	public void setWoPlanDetailDAO(WoPlanDetailDAO woPlanDetailDAO) {
		this.woPlanDetailDAO = woPlanDetailDAO;
	}
	public AnIfCalListDAO getAnIfCalListDAO() {
		return anIfCalListDAO;
	}
	public void setAnIfCalListDAO(AnIfCalListDAO anIfCalListDAO) {
		this.anIfCalListDAO = anIfCalListDAO;
	}
	
	public List findHdrList(Map map) throws Exception
	{      
		return anIfCalListDAO.findHdrList(map);
	}
	public List findWocraftList(Map map) throws Exception
	{      
		return anIfCalListDAO.findWocraftList(map);
	}
	public List findWocalibList(Map map) throws Exception
	{      
		return anIfCalListDAO.findWocalibList(map);
	}
	public List findWocalibValueList(Map map) throws Exception
	{      
		return anIfCalListDAO.findWocalibValueList(map);
	}
	public List findWoequipList(Map map) throws Exception
	{      
		return anIfCalListDAO.findWoequipList(map);
	}
	public List findWocalibStdEq(Map map) throws Exception
	{      
		return anIfCalListDAO.findWocalibStdEq(map);
	}
	public List findWocalibGnlValue(Map map) throws Exception
	{      
		return anIfCalListDAO.findWocalibGnlValue(map);
	}
	public List findWocalibSclValue(Map map) throws Exception
	{      
		return anIfCalListDAO.findWocalibSclValue(map);
	}

	public int saveWorkorder(List list)  throws Exception
	{
		Type type = new TypeToken<List<Map<String, String>>>(){}.getType();
		
		int resultQty = 0;
        
		for(Object obj : list){
			Map map = (Map)obj;
            map.put("workTime", "");
            String equipJson = CommonUtil.convertString(map.get("EQUIP"));
            String craftJson = CommonUtil.convertString(map.get("CRAFT"));
            String calibJson = CommonUtil.convertString(map.get("CALIB"));
            String calibValueJson = CommonUtil.convertString(map.get("CALIBVALUE"));
            String calibStdEqJson = CommonUtil.convertString(map.get("CALIBSTDEQ"));
            String calibGnlValueJson = CommonUtil.convertString(map.get("CALIBGNLVALUE"));
            String calibSclValueJson = CommonUtil.convertString(map.get("CALIBSCLVALUE"));
            
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setLongSerializationPolicy( LongSerializationPolicy.STRING );
            Gson gson = gsonBuilder.create();

            List equipList = gson.fromJson(equipJson, type);
            List craftList = gson.fromJson(craftJson, type);
            List calibList = gson.fromJson(calibJson, type);
            List calibValueList = gson.fromJson(calibValueJson, type);
            List calibStdEqList = gson.fromJson(calibStdEqJson, type);
            List calibGnlValueList = gson.fromJson(calibGnlValueJson, type);
            List calibSclValueList = gson.fromJson(calibSclValueJson, type);
            
            String wkorId = "";
            if("Y".equals(CommonUtil.convertString(map.get("isNew")))){
            	wkorId = anIfCalListDAO.getNextSequence("SQAWKOR_ID");
            	//insert
            	anIfCalListDAO.insertWorkOrder(map, wkorId);
            	anIfCalListDAO.insertWorkOrderLog(map, wkorId);
            	
            	//TAWOPLAN INSERT 
            	User loginUser = new User();
            	loginUser.setPlant(CommonUtil.convertString(map.get("plant")));
            	WoPlanDetailDTO woPlanDetailDTO = new WoPlanDetailDTO();
            	woPlanDetailDTO.setCompNo(CommonUtil.convertString(map.get("compNo")));
            	woPlanDetailDTO.setWkOrId(wkorId);
            	woPlanDetailDTO.setWoPlanStatusId("PPC");
            	woPlanDetailDTO.setWkOrDesc(CommonUtil.convertString(map.get("woDesc")));
            	woPlanDetailDTO.setWoTypeId(CommonUtil.convertString(map.get("woType")));
            	woPlanDetailDTO.setDeptId(CommonUtil.convertString(map.get("deptId")));
            	woPlanDetailDTO.setPmTypeId(CommonUtil.convertString(map.get("pmType")));
            	woPlanDetailDTO.setEmpId(CommonUtil.convertString(map.get("empId")));
            	woPlanDetailDTO.setStartDate(CommonUtil.convertDate(CommonUtil.convertString(map.get("startDate"))));
            	woPlanDetailDTO.setStartTime(CommonUtil.convertTime(CommonUtil.convertString(map.get("startTime"))));
            	woPlanDetailDTO.setEndDate(CommonUtil.convertDate(CommonUtil.convertString(map.get("endDate"))));
            	woPlanDetailDTO.setEndTime(CommonUtil.convertTime(CommonUtil.convertString(map.get("endTime"))));
            	woPlanDetailDTO.setWorkTime(CommonUtil.convertString(map.get("workTime")));
            	woPlanDetailDTO.setPerform(CommonUtil.convertString(map.get("perform")));
            	woPlanDetailDTO.setWoNo(wkorId);
            	woPlanDetailDTO.setShiftTypeId(CommonUtil.convertString(map.get("shiftType")));
            	woPlanDetailDTO.setWkorDate(CommonUtil.convertDate(CommonUtil.convertString(map.get("wkorDate"))));
            	woPlanDetailDTO.setWkCtrId(CommonUtil.convertString(map.get("wkctrId")));
            	
            	woPlanDetailDAO.insertDetail(woPlanDetailDTO, loginUser);
            	
            	//change Sequence
            	anIfCalListDAO.changeFileSeq(map, wkorId);
            	
            }else{
            	wkorId = CommonUtil.convertString(map.get("wkorId"));
            	
            	//W/O 상태체크 후 상태가 업로드 대상이 아니면 Skip
            	if(!CommonUtil.isUseStringInArray(CommonValues.USED_WO_STATES, anIfCalListDAO.getWoStatusOfWorkOrder(map))){
            		continue;
            	}
            	
            	//update
            	anIfCalListDAO.updateWorkOrder(map);
            }
          //하위 insert
            if(equipList.size() != 0)
            {
            	for(Object equipObj : equipList)
                {
            		Map equipMap = (Map)equipObj;
            		anIfCalListDAO.saveWoequip(equipMap,wkorId);
                }
            }
            if(craftList.size() != 0)
            {
            	for(Object craftObj : craftList)
                {
            		String woCraftId = anIfCalListDAO.getNextSequence("SQAWOCRAFT_ID");
            		
            		Map craftMap = (Map)craftObj;
            		anIfCalListDAO.saveWocraft(craftMap,wkorId,woCraftId);
            		
            		WoPlanCommonDTO woPlanCommonDTO = new WoPlanCommonDTO();
            		woPlanCommonDTO.setCompNo(CommonUtil.convertString(craftMap.get("COMPNO")));
            		woPlanCommonDTO.setWkOrId(wkorId);

            		WoPlanCraftDetailDTO woPlanCraftDetailDTO = new WoPlanCraftDetailDTO();
            		woPlanCraftDetailDTO.setWoCraftId(woCraftId);
            		woPlanCraftDetailDTO.setEmpId(CommonUtil.convertString(craftMap.get("EMPID")));
            		woPlanCraftDetailDTO.setStartDate(CommonUtil.convertDate(CommonUtil.convertString(craftMap.get("STARTDATE"))));
            		woPlanCraftDetailDTO.setStartTime(CommonUtil.convertTime(CommonUtil.convertString(craftMap.get("STARTTIME"))));
            		woPlanCraftDetailDTO.setEndDate(CommonUtil.convertDate(CommonUtil.convertString(craftMap.get("ENDDATE"))));
            		woPlanCraftDetailDTO.setEndTime(CommonUtil.convertTime(CommonUtil.convertString(craftMap.get("ENDTIME"))));
            		woPlanCraftDetailDTO.setWorkTime(CommonUtil.convertString(craftMap.get("WORKTIME")));
            		woPlanCraftDetailDTO.setRemark(CommonUtil.convertString(craftMap.get("REMARK")));
            		
            		woPlanCraftDetailDAO.insertDetail(woPlanCraftDetailDTO, woPlanCommonDTO);
                }
            }

            if(calibList.size() != 0)
            {
            	for(Object calibObj : calibList)
                {
            		Map calibMap = (Map)calibObj;
            		anIfCalListDAO.saveWocalib(calibMap,wkorId);
                }
            }

            if(calibValueList.size() != 0)
            {
            	for(Object calibValueObj : calibValueList)
                {
            		Map calibValueMap = (Map)calibValueObj;
            		anIfCalListDAO.saveWocalibValue(calibValueMap,wkorId);
                }
            }
            if(calibStdEqList.size() != 0)
            {
            	for(Object calibStdEqObj : calibStdEqList)
                {
            		String sqawocalibstdeq_id = anIfCalListDAO.getNextSequence("SQAWOCALIBSTDEQ_ID");
            		Map calibStdEqMap = (Map)calibStdEqObj;
            		anIfCalListDAO.saveWocalibStdEq(calibStdEqMap,wkorId,sqawocalibstdeq_id);
                }
            }
            if(calibGnlValueList.size() != 0)
            {
            	for(Object calibGnlValueObj : calibGnlValueList)
                {
            		Map calibGnlValueMap = (Map)calibGnlValueObj;
            		anIfCalListDAO.saveWocalibGnlValue(calibGnlValueMap,wkorId);
                }
            }
            if(calibSclValueList.size() != 0)
            {
            	for(Object calibSclValueObj : calibSclValueList)
                {
            		Map calibSclValueMap = (Map)calibSclValueObj;
            		anIfCalListDAO.saveWocalibSclValue(calibSclValueMap,wkorId);
                }
            }
        }
        return resultQty;
	}
	
}

