package intf.dream.android.offline.mapmwork.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.reflect.TypeToken;

import common.bean.User;
import common.util.CommonUtil;
import common.util.DateUtil;
import dream.work.list.dao.WoPlanCraftDetailDAO;
import dream.work.list.dao.WoPlanDetailDAO;
import dream.work.list.dao.WoPlanPartDetailDAO;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanCraftDetailDTO;
import dream.work.list.dto.WoPlanDetailDTO;
import dream.work.list.dto.WoPlanPartDetailDTO;
import intf.dream.android.common.CommonValues;
import intf.dream.android.offline.mapmwork.dao.AnIfPmworkListDAO;
import intf.dream.android.offline.mapmwork.service.AnIfPmworkListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anIfPmworkListServiceTarget"
 * @spring.txbn id="anIfPmworkListService"
 * @spring.property name="anIfPmworkListDAO" ref="anIfPmworkListDAO"
 * @spring.property name="woPlanDetailDAO" ref="woPlanDetailDAO"
 * @spring.property name="woPlanCraftDetailDAO" ref="woPlanCraftDetailDAO"
 * @spring.property name="woPlanPartDetailDAO" ref="woPlanPartDetailDAO"
 */
public class AnIfPmworkListServiceImpl implements AnIfPmworkListService
{
    private AnIfPmworkListDAO anIfPmworkListDAO = null;
    private WoPlanDetailDAO woPlanDetailDAO = null;
    private WoPlanCraftDetailDAO woPlanCraftDetailDAO = null;
    private WoPlanPartDetailDAO woPlanPartDetailDAO = null;

    
    public WoPlanPartDetailDAO getWoPlanPartDetailDAO() {
		return woPlanPartDetailDAO;
	}
	public void setWoPlanPartDetailDAO(WoPlanPartDetailDAO woPlanPartDetailDAO) {
		this.woPlanPartDetailDAO = woPlanPartDetailDAO;
	}
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
	public AnIfPmworkListDAO getAnIfPmworkListDAO() {
		return anIfPmworkListDAO;
	}
	public void setAnIfPmworkListDAO(AnIfPmworkListDAO anIfPmworkListDAO) {
		this.anIfPmworkListDAO = anIfPmworkListDAO;
	}
	
	public List findHdrList(Map map) throws Exception
	{      
		return anIfPmworkListDAO.findHdrList(map);
	}
	public List findWocraftList(Map map) throws Exception
	{      
		return anIfPmworkListDAO.findWocraftList(map);
	}
	public List findWopartsList(Map map) throws Exception
	{      
		return anIfPmworkListDAO.findWopartsList(map);
	}
	public List findWoequipList(Map map) throws Exception
	{      
		return anIfPmworkListDAO.findWoequipList(map);
	}

	public int saveWorkorder(List list)  throws Exception
	{
		Type type = new TypeToken<List<Map<String, String>>>(){}.getType();
		
		int resultQty = 0;
        
		for(Object obj : list){
			Map map = (Map)obj;
			//�ð����
            String workTime = "";
            String startDate = CommonUtil.convertString(map.get("startDate"));
            String startTime = CommonUtil.convertString(map.get("startTime")); //5���� �������
            String endDate = CommonUtil.convertString(map.get("endDate"));
            String endTime = CommonUtil.convertString(map.get("endTime"));

            workTime = DateUtil.getTimeInterval(startDate+startTime, endDate+endTime);
            map.put("workTime", workTime);

            String equipJson = CommonUtil.convertString(map.get("EQUIP"));
            String craftJson = CommonUtil.convertString(map.get("CRAFT"));
            String partsJson = CommonUtil.convertString(map.get("PARTS"));
            
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setLongSerializationPolicy( LongSerializationPolicy.STRING );
            Gson gson = gsonBuilder.create();

            List equipList = gson.fromJson(equipJson, type);
            List craftList = gson.fromJson(craftJson, type);
            List partsList = gson.fromJson(partsJson, type);
            String wkorId = "";
            if("Y".equals(CommonUtil.convertString(map.get("isNew")))){
            	wkorId = anIfPmworkListDAO.getNextSequence("SQAWKOR_ID");
            	//insert
            	anIfPmworkListDAO.insertWorkOrder(map, wkorId);
            	anIfPmworkListDAO.insertWorkOrderLog(map, wkorId);
            	
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
            	
            	anIfPmworkListDAO.changeFileSeq(map, wkorId);
            }else{
            	wkorId = CommonUtil.convertString(map.get("wkorId"));
            	//W/O ����üũ �� ���°� ���ε� ����� �ƴϸ� Skip
            	if(!CommonUtil.isUseStringInArray(CommonValues.USED_WO_STATES, anIfPmworkListDAO.getWoStatusOfWorkOrder(map))){
            		continue;
            	}
            	
            	//update
            	anIfPmworkListDAO.updateWorkOrder(map);
            }
            
            //���� insert
            if(equipList.size() != 0)
            {
            	for(Object equipObj : equipList)
                {
            		Map equipMap = (Map)equipObj;
            		anIfPmworkListDAO.saveWoequip(equipMap,wkorId);
                }
            }
            
            if(craftList.size() != 0)
            {
            	for(Object craftObj : craftList)
                {
            		String woCraftId = anIfPmworkListDAO.getNextSequence("SQAWOCRAFT_ID");
            		
            		Map craftMap = (Map)craftObj;
            		anIfPmworkListDAO.saveWocraft(craftMap,wkorId,woCraftId);

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
            
            if(partsList.size() != 0)
            {
            	for(Object partsObj : partsList)
                {
            		String woPartId = anIfPmworkListDAO.getNextSequence("SQAWOPART_ID");
            		
            		Map partsMap = (Map)partsObj;
            		anIfPmworkListDAO.saveWoparts(partsMap,wkorId,woPartId);
            		
            		WoPlanCommonDTO woPlanCommonDTO = new WoPlanCommonDTO();
            		woPlanCommonDTO.setCompNo(CommonUtil.convertString(partsMap.get("COMPNO")));
            		woPlanCommonDTO.setWkOrId(wkorId);

            		WoPlanPartDetailDTO woPlanPartDetailDTO = new WoPlanPartDetailDTO();
            		woPlanPartDetailDTO.setWoPartId(woPartId);
            		woPlanPartDetailDTO.setPartId(CommonUtil.convertString(partsMap.get("PARTID")));
            		woPlanPartDetailDTO.setWcodeId(CommonUtil.convertString(partsMap.get("WCODEID")));
            		woPlanPartDetailDTO.setUseQty(CommonUtil.convertString(partsMap.get("USEQTY")));
            		woPlanPartDetailDTO.setPartGrade(CommonUtil.convertString(partsMap.get("PARTGRADE")));
            		woPlanPartDetailDTO.setRemark(CommonUtil.convertString(partsMap.get("REMARK")));
            		
            		woPlanPartDetailDAO.insertDetail(woPlanPartDetailDTO, woPlanCommonDTO);
                }
            }
        }
        return resultQty;
	}
}

