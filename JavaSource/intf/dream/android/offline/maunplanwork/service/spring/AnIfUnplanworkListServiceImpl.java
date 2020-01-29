package intf.dream.android.offline.maunplanwork.service.spring;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Locale;
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
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultMstrDetailDTO;
import dream.work.list.dto.MaWoResultPmCalDTO;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanCraftDetailDTO;
import dream.work.list.dto.WoPlanDetailDTO;
import dream.work.list.dto.WoPlanPartDetailDTO;
import dream.work.list.service.MaWoResultMstrDetailService;
import intf.dream.android.common.CommonValues;
import intf.dream.android.offline.maunplanwork.dao.AnIfUnplanworkListDAO;
import intf.dream.android.offline.maunplanwork.service.AnIfUnplanworkListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anIfUnplanworkListServiceTarget"
 * @spring.txbn id="anIfUnplanworkListService"
 * @spring.property name="anIfUnplanworkListDAO" ref="anIfUnplanworkListDAO"
 * @spring.property name="maWoResultMstrDetailService" ref="maWoResultMstrDetailService"
 * @spring.property name="woPlanDetailDAO" ref="woPlanDetailDAO"
 * @spring.property name="woPlanCraftDetailDAO" ref="woPlanCraftDetailDAO"
 * @spring.property name="woPlanPartDetailDAO" ref="woPlanPartDetailDAO"
 */
public class AnIfUnplanworkListServiceImpl implements AnIfUnplanworkListService
{
    private AnIfUnplanworkListDAO anIfUnplanworkListDAO = null;
    private MaWoResultMstrDetailService maWoResultMstrDetailService = null;
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
    public MaWoResultMstrDetailService getMaWoResultMstrDetailService()
    {
        return maWoResultMstrDetailService;
    }

    public void setMaWoResultMstrDetailService(
            MaWoResultMstrDetailService maWoResultMstrDetailService)
    {
        this.maWoResultMstrDetailService = maWoResultMstrDetailService;
    }
	public AnIfUnplanworkListDAO getAnIfUnplanworkListDAO() {
		return anIfUnplanworkListDAO;
	}
	public void setAnIfUnplanworkListDAO(AnIfUnplanworkListDAO anIfUnplanworkListDAO) {
		this.anIfUnplanworkListDAO = anIfUnplanworkListDAO;
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
            String failJson  = CommonUtil.convertString(map.get("FAIL"));
            
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setLongSerializationPolicy( LongSerializationPolicy.STRING );
            Gson gson = gsonBuilder.create();

            List equipList = gson.fromJson(equipJson, type);
            List craftList = gson.fromJson(craftJson, type);
            List partsList = gson.fromJson(partsJson, type);
            List failList  = gson.fromJson(failJson, type);
            String wkorId = "";
            if("Y".equals(CommonUtil.convertString(map.get("isNew")))){
            	wkorId = anIfUnplanworkListDAO.getNextSequence("SQAWKOR_ID");
            	//insert
            	anIfUnplanworkListDAO.insertWorkOrder(map, wkorId);
            	anIfUnplanworkListDAO.insertWorkOrderLog(map, wkorId);
            	
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
            	anIfUnplanworkListDAO.changeFileSeq(map, wkorId);
            	anIfUnplanworkListDAO.changeWoReqResWkorId(map, wkorId);
            	anIfUnplanworkListDAO.changeWoReqStatus(map, wkorId);
            }else{
            	wkorId = CommonUtil.convertString(map.get("wkorId"));
            	
            	//W/O 상태체크 후 상태가 업로드 대상이 아니면 Skip
            	if(!CommonUtil.isUseStringInArray(CommonValues.USED_WO_STATES, anIfUnplanworkListDAO.getWoStatusOfWorkOrder(map))){
            		continue;
            	}
            	
            	//update
            	anIfUnplanworkListDAO.updateWorkOrder(map);
            }

          //하위 insert
            if(equipList.size() != 0)
            {
            	for(Object equipObj : equipList)
                {
            		Map equipMap = (Map)equipObj;
            		anIfUnplanworkListDAO.saveWoequip(equipMap,wkorId);
                }
            }
            
            if(craftList.size() != 0)
            {
            	for(Object craftObj : craftList)
                {
            		String woCraftId = anIfUnplanworkListDAO.getNextSequence("SQAWOCRAFT_ID");
            		
            		Map craftMap = (Map)craftObj;
            		anIfUnplanworkListDAO.saveWocraft(craftMap,wkorId,woCraftId);
            		
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
            		String woPartId = anIfUnplanworkListDAO.getNextSequence("SQAWOPART_ID");
            		
            		Map partsMap = (Map)partsObj;
            		anIfUnplanworkListDAO.saveWoparts(partsMap,wkorId,woPartId);
            		
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
            if(failList.size() != 0)
            {
            	for(Object failObj : failList)
                {
            		Map failMap = (Map)failObj;
            		
            		String wt = "";
                    String sd = CommonUtil.convertString(failMap.get("EQDNSTARTDATE"));
                    String st = CommonUtil.convertString(failMap.get("EQDNSTARTTIME")); //5분전 없을경우
                    String ed = CommonUtil.convertString(failMap.get("EQDNENDDATE"));
                    String et = CommonUtil.convertString(failMap.get("EQDNENDTIME"));
                    
                    wt = DateUtil.getTimeInterval(sd+st, ed+et);
                    failMap.put("WORKTIME", wt);
            		
            		anIfUnplanworkListDAO.saveWofail(failMap,wkorId);
            		anIfUnplanworkListDAO.updateWorkOrderEqAsmgId(failMap,wkorId);
                }
            }
            
            String woStatus = CommonUtil.convertString(map.get("woStatus"));
            String compNo = CommonUtil.convertString(map.get("compNo"));
            MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
            if(!"".equals(wkorId) && "C".equals(woStatus))
            {
                maWoResultMstrCommonDTO.setWkOrId(wkorId);
                maWoResultMstrCommonDTO.setCompNo(compNo);
                MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = maWoResultMstrDetailService.findDetail(maWoResultMstrCommonDTO);
                MaWoResultPmCalDTO maWoResultPmCalDTO = new MaWoResultPmCalDTO();

                User user = new User();
                user.setCompNo(compNo);
                user.setUserId(CommonUtil.convertString(map.get("updBy")));
                String lang = "".equals(CommonUtil.convertString(map.get("lang")))?"ko":CommonUtil.convertString(map.get("lang"));
                user.setLangId(lang);
                Locale locale = new Locale(lang);
                user.setLocale(locale);
                maWoResultMstrDetailDTO.setLoginUser(user);
                
                maWoResultMstrDetailDTO.setLoginUser(user);
                maWoResultMstrDetailDTO.setCompNo(compNo);
                maWoResultMstrDetailDTO.setEnterBy(CommonUtil.convertString(map.get("updBy")));
                
                //완료가 아니면 완료 시킴
                if(!"C".equals(maWoResultMstrDetailDTO.getWoStatusId())&&maWoResultMstrDetailDTO.getWkOrId()!=null&&!"".equals(maWoResultMstrDetailDTO.getWkOrId())){
                	maWoResultMstrDetailService.completeDetail(maWoResultMstrDetailDTO,maWoResultPmCalDTO);
                }
            }
            
        }
        return resultQty;
	}
}

