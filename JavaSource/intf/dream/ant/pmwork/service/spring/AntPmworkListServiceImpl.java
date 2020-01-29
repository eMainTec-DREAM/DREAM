package intf.dream.ant.pmwork.service.spring;

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
import intf.dream.ant.common.AntCommonValues;
import intf.dream.ant.pmwork.dao.AntPmworkListDAO;
import intf.dream.ant.pmwork.service.AntPmworkListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="antPmworkListServiceTarget"
 * @spring.txbn id="antPmworkListService"
 * @spring.property name="antPmworkListDAO" ref="antPmworkListDAO"
 * @spring.property name="woPlanDetailDAO" ref="woPlanDetailDAO"
 * @spring.property name="woPlanCraftDetailDAO" ref="woPlanCraftDetailDAO"
 * @spring.property name="woPlanPartDetailDAO" ref="woPlanPartDetailDAO"
 */
public class AntPmworkListServiceImpl implements AntPmworkListService
{
    private AntPmworkListDAO antPmworkListDAO = null;
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
	public AntPmworkListDAO getAntPmworkListDAO() {
		return antPmworkListDAO;
	}
	public void setAntPmworkListDAO(AntPmworkListDAO antPmworkListDAO) {
		this.antPmworkListDAO = antPmworkListDAO;
	}
	
	public List findHdrList(Map map) throws Exception
	{      
		return antPmworkListDAO.findHdrList(map);
	}
	public List findWocraftList(Map map) throws Exception
	{      
		return antPmworkListDAO.findWocraftList(map);
	}
	public List findWopartsList(Map map) throws Exception
	{      
		return antPmworkListDAO.findWopartsList(map);
	}
	public List findWoequipList(Map map) throws Exception
	{      
		return antPmworkListDAO.findWoequipList(map);
	}

	public int saveWorkorder(List list)  throws Exception
	{
		Type type = new TypeToken<List<Map<String, String>>>(){}.getType();
		
		int resultQty = 0;
        
		for(Object obj : list){
			Map map = (Map)obj;
			//시간계산
            String workTime = "";
            String startDate = CommonUtil.convertString(map.get("startDate"));
            String startTime = CommonUtil.convertString(map.get("startTime")); //5분전 없을경우
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
            	wkorId = antPmworkListDAO.getNextSequence("SQAWKOR_ID");
            	//insert
            	antPmworkListDAO.insertWorkOrder(map, wkorId);
            	antPmworkListDAO.insertWorkOrderLog(map, wkorId);
            	
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
            	
            	antPmworkListDAO.changeFileSeq(map, wkorId);
            }else{
            	wkorId = CommonUtil.convertString(map.get("wkorId"));
            	//W/O 상태체크 후 상태가 업로드 대상이 아니면 Skip
            	if(!CommonUtil.isUseStringInArray(AntCommonValues.USED_WO_STATES, antPmworkListDAO.getWoStatusOfWorkOrder(map))){
            		continue;
            	}
            	
            	//update
            	antPmworkListDAO.updateWorkOrder(map);
            }
            
            //하위 insert
            if(equipList.size() != 0)
            {
            	for(Object equipObj : equipList)
                {
            		Map equipMap = (Map)equipObj;
            		antPmworkListDAO.saveWoequip(equipMap,wkorId);
                }
            }
            
            if(craftList.size() != 0)
            {
            	for(Object craftObj : craftList)
                {
            		String woCraftId = antPmworkListDAO.getNextSequence("SQAWOCRAFT_ID");
            		
            		Map craftMap = (Map)craftObj;
            		antPmworkListDAO.saveWocraft(craftMap,wkorId,woCraftId);

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
            		String woPartId = antPmworkListDAO.getNextSequence("SQAWOPART_ID");
            		
            		Map partsMap = (Map)partsObj;
            		antPmworkListDAO.saveWoparts(partsMap,wkorId,woPartId);
            		
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

