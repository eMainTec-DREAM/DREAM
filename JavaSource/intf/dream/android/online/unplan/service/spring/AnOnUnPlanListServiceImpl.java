package intf.dream.android.online.unplan.service.spring;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import dream.work.list.dao.WoPlanCraftDetailDAO;
import dream.work.list.dao.WoPlanDetailDAO;
import dream.work.list.dao.WoPlanPartDetailDAO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultMstrDetailDTO;
import dream.work.list.dto.MaWoResultPmCalDTO;
import dream.work.list.dto.WoPlanDetailDTO;
import dream.work.list.service.MaWoResultMstrDetailService;
import intf.dream.android.online.pmwork.dao.AnOnPmworkListDAO;
import intf.dream.android.online.unplan.dao.AnOnUnPlanListDAO;
import intf.dream.android.online.unplan.service.AnOnUnPlanListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anOnUnPlanListServiceTarget"
 * @spring.txbn id="anOnUnPlanListService"
 * @spring.property name="anOnUnPlanListDAO" ref="anOnUnPlanListDAO"
 * @spring.property name="anOnPmworkListDAO" ref="anOnPmworkListDAO"
 * @spring.property name="maWoResultMstrDetailService" ref="maWoResultMstrDetailService"
 * @spring.property name="woPlanDetailDAO" ref="woPlanDetailDAO"
 * @spring.property name="woPlanCraftDetailDAO" ref="woPlanCraftDetailDAO"
 * @spring.property name="woPlanPartDetailDAO" ref="woPlanPartDetailDAO"
 */
public class AnOnUnPlanListServiceImpl implements AnOnUnPlanListService
{
    private AnOnUnPlanListDAO anOnUnPlanListDAO = null;
    private AnOnPmworkListDAO anOnPmworkListDAO = null;
    private MaWoResultMstrDetailService maWoResultMstrDetailService = null;
    private WoPlanDetailDAO woPlanDetailDAO = null;
    private WoPlanCraftDetailDAO woPlanCraftDetailDAO = null;
    private WoPlanPartDetailDAO woPlanPartDetailDAO = null;
    
    public WoPlanDetailDAO getWoPlanDetailDAO() {
		return woPlanDetailDAO;
	}

	public void setWoPlanDetailDAO(WoPlanDetailDAO woPlanDetailDAO) {
		this.woPlanDetailDAO = woPlanDetailDAO;
	}

	public WoPlanCraftDetailDAO getWoPlanCraftDetailDAO() {
		return woPlanCraftDetailDAO;
	}

	public void setWoPlanCraftDetailDAO(WoPlanCraftDetailDAO woPlanCraftDetailDAO) {
		this.woPlanCraftDetailDAO = woPlanCraftDetailDAO;
	}

	public WoPlanPartDetailDAO getWoPlanPartDetailDAO() {
		return woPlanPartDetailDAO;
	}

	public void setWoPlanPartDetailDAO(WoPlanPartDetailDAO woPlanPartDetailDAO) {
		this.woPlanPartDetailDAO = woPlanPartDetailDAO;
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
	public AnOnPmworkListDAO getAnOnPmworkListDAO() {
		return anOnPmworkListDAO;
	}
	public void setAnOnPmworkListDAO(AnOnPmworkListDAO anOnPmworkListDAO) {
		this.anOnPmworkListDAO = anOnPmworkListDAO;
	}
	public AnOnUnPlanListDAO getAnOnUnPlanListDAO() {
		return anOnUnPlanListDAO;
	}
	public void setAnOnUnPlanListDAO(AnOnUnPlanListDAO anOnUnPlanListDAO) {
		this.anOnUnPlanListDAO = anOnUnPlanListDAO;
	}
	
	public List findUnPlanList(Map map) throws Exception
	{      
		return anOnUnPlanListDAO.findUnPlanList(map);
	}
	public int deleteUnPlan(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnUnPlanListDAO.deleteWoFail(map);
			anOnPmworkListDAO.deleteWoCraft(map);
			anOnPmworkListDAO.deleteWoParts(map);
			anOnPmworkListDAO.deleteWoequip(map);
			anOnUnPlanListDAO.deleteUnPlan(map);
		}
		return resultQty;
	}
	public int insertUnPlan(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnUnPlanListDAO.insertUnPlan(map);
			anOnPmworkListDAO.insertWoequip(map);
			
			//TAWOPLAN INSERT 
        	User loginUser = new User();
        	loginUser.setPlant(CommonUtil.convertString(map.get("plant")));
        	WoPlanDetailDTO woPlanDetailDTO = new WoPlanDetailDTO();
        	woPlanDetailDTO.setCompNo(CommonUtil.convertString(map.get("compNo")));
        	woPlanDetailDTO.setWkOrId(CommonUtil.convertString(map.get("wkorId")));
        	woPlanDetailDTO.setWoPlanStatusId("PPC");
        	woPlanDetailDTO.setWkOrDesc(CommonUtil.convertString(map.get("woDesc")));
        	woPlanDetailDTO.setWoTypeId(CommonUtil.convertString(map.get("woType")));
        	woPlanDetailDTO.setDeptId(CommonUtil.convertString(map.get("deptId")));
        	woPlanDetailDTO.setPmTypeId(CommonUtil.convertString(map.get("pmType")));
        	woPlanDetailDTO.setEmpId(CommonUtil.convertString(map.get("empId")));
        	woPlanDetailDTO.setPerform(CommonUtil.convertString(map.get("perform")));
        	woPlanDetailDTO.setWoNo(CommonUtil.convertString(map.get("wkorId")));
        	woPlanDetailDTO.setShiftTypeId(CommonUtil.convertString(map.get("shiftType")));
        	woPlanDetailDTO.setWkorDate(CommonUtil.convertDate(CommonUtil.convertString(map.get("wkorDate"))));
        	woPlanDetailDTO.setWkCtrId(CommonUtil.convertString(map.get("wkCtrId")));
        	woPlanDetailDTO.setSelfVendorType(CommonUtil.convertString(map.get("vendorTypeId")));
			
        	woPlanDetailDAO.insertDetail(woPlanDetailDTO, loginUser);
        	
			completedUnplanWorkOrder(map);
			
		}
		return resultQty;
	}
	public int updateUnPlan(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnUnPlanListDAO.updateUnPlan(map);
			anOnPmworkListDAO.updateWoequip(map);
			
			completedUnplanWorkOrder(map);
		}
		return resultQty;
	}
	
	public List findWoFailList(Map map) throws Exception
	{      
		return anOnUnPlanListDAO.findWoFailList(map);
	}
	public int deleteWoFail(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnUnPlanListDAO.deleteWoFail(map);
		}
		return resultQty;
	}
	public int insertWoFailList(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			if("0".equals(anOnUnPlanListDAO.findWoFailCount(map))){
				anOnUnPlanListDAO.insertWoFailList(map);
			}else{
				anOnUnPlanListDAO.updateWoFailList(map);
			}
			
			anOnUnPlanListDAO.updateWorkOrderEqAsmbId(map);
		}
		return resultQty;
	}
	public int updateWoFailList(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnUnPlanListDAO.updateWoFailList(map);
			anOnUnPlanListDAO.updateWorkOrderEqAsmbId(map);
		}
		return resultQty;
	}
	
	private void completedUnplanWorkOrder(Map map) throws Exception{
		String wkorId = CommonUtil.convertString(map.get("wkorId"));
		String woStatus = CommonUtil.convertString(map.get("woStatus"));
        String compNo = CommonUtil.convertString(map.get("compNo"));
        
        User user = new User();
        user.setCompNo(compNo);
        user.setUserId(CommonUtil.convertString(map.get("userId")));
        String lang = "".equals(CommonUtil.convertString(map.get("lang")))?"ko":CommonUtil.convertString(map.get("lang"));
        user.setLangId(lang);
        Locale locale = new Locale(lang);
        user.setLocale(locale);
        
        maWoResultMstrDetailService = (MaWoResultMstrDetailService) CommonUtil.getBean("maWoResultMstrDetailService", user);
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
        if(!"".equals(wkorId) && "C".equals(woStatus))
        {
            maWoResultMstrCommonDTO.setWkOrId(wkorId);
            maWoResultMstrCommonDTO.setCompNo(compNo);
            MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = maWoResultMstrDetailService.findDetail(maWoResultMstrCommonDTO);
            MaWoResultPmCalDTO maWoResultPmCalDTO = new MaWoResultPmCalDTO();
            maWoResultMstrDetailDTO.setLoginUser(user);
            maWoResultMstrDetailDTO.setCompNo(compNo);
            maWoResultMstrDetailDTO.setEnterBy(CommonUtil.convertString(map.get("userId")));
            maWoResultMstrDetailDTO.setUserLang(CommonUtil.convertString(map.get("lang")));

            maWoResultMstrDetailDTO.setEndDate(maWoResultMstrDetailDTO.getEndDate());
            maWoResultMstrDetailDTO.setEndTime(maWoResultMstrDetailDTO.getEndTime());
            maWoResultMstrDetailDTO.setUpdDate(maWoResultMstrDetailDTO.getUpdDate());
            maWoResultMstrDetailDTO.setStartDate(maWoResultMstrDetailDTO.getStartDate());
            maWoResultMstrDetailDTO.setStartTime(maWoResultMstrDetailDTO.getStartTime());
            maWoResultMstrDetailDTO.setWkorDate(maWoResultMstrDetailDTO.getWkorDate());
            
            //완료가 아니면 완료 시킴
            if(!"C".equals(maWoResultMstrDetailDTO.getWoStatusId())&&maWoResultMstrDetailDTO.getWkOrId()!=null&&!"".equals(maWoResultMstrDetailDTO.getWkOrId())){
                maWoResultMstrDetailService.completeDetail(maWoResultMstrDetailDTO,maWoResultPmCalDTO);
            }
        }
	}
}
