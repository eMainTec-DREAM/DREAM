package intf.dream.android.online.cal.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import dream.work.list.dao.WoPlanDetailDAO;
import dream.work.list.dto.WoPlanDetailDTO;
import intf.dream.android.online.cal.dao.AnOnCalListDAO;
import intf.dream.android.online.cal.service.AnOnCalListService;
import intf.dream.android.online.pmwork.dao.AnOnPmworkListDAO;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anOnCalListServiceTarget"
 * @spring.txbn id="anOnCalListService"
 * @spring.property name="anOnCalListDAO" ref="anOnCalListDAO"
 * @spring.property name="woPlanDetailDAO" ref="woPlanDetailDAO"
 * @spring.property name="anOnPmworkListDAO" ref="anOnPmworkListDAO"
 */
public class AnOnCalListServiceImpl implements AnOnCalListService
{
    private AnOnCalListDAO anOnCalListDAO = null;
    private AnOnPmworkListDAO anOnPmworkListDAO = null;
    private WoPlanDetailDAO woPlanDetailDAO = null;
    

	public WoPlanDetailDAO getWoPlanDetailDAO() {
		return woPlanDetailDAO;
	}
	public void setWoPlanDetailDAO(WoPlanDetailDAO woPlanDetailDAO) {
		this.woPlanDetailDAO = woPlanDetailDAO;
	}
	public AnOnPmworkListDAO getAnOnPmworkListDAO() {
		return anOnPmworkListDAO;
	}
	public void setAnOnPmworkListDAO(AnOnPmworkListDAO anOnPmworkListDAO) {
		this.anOnPmworkListDAO = anOnPmworkListDAO;
	}
	public AnOnCalListDAO getAnOnCalListDAO() {
		return anOnCalListDAO;
	}
	public void setAnOnCalListDAO(AnOnCalListDAO anOnCalListDAO) {
		this.anOnCalListDAO = anOnCalListDAO;
	}
	
	public List findCalList(Map map) throws Exception
	{      
		return anOnCalListDAO.findCalList(map);
	}
	public int deleteCal(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnCalListDAO.deleteWoCalib(map);
			anOnCalListDAO.deleteCalEtcValueList(map);
			anOnCalListDAO.deleteCalGnlValueList(map);
			anOnCalListDAO.deleteCalSclValueList(map);
			anOnCalListDAO.deleteCalStdEqList(map);
			anOnPmworkListDAO.deleteWoCraft(map);
			anOnPmworkListDAO.deleteWoequip(map);
			anOnCalListDAO.deleteCal(map);
		}
		return resultQty;
	}
	public int insertCal(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnCalListDAO.insertCal(map);
			anOnPmworkListDAO.insertWoequip(map);
			anOnCalListDAO.insertWoCalib(map);
			
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
			
        	woPlanDetailDAO.insertDetail(woPlanDetailDTO, loginUser);
		}
		return resultQty;
	}
	public int updateCal(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnCalListDAO.updateCal(map);
			anOnPmworkListDAO.updateWoequip(map);
			anOnCalListDAO.updateWoCalib(map);
		}
		return resultQty;
	}
	

	public List findCalEtcValueList(Map map) throws Exception
	{      
		return anOnCalListDAO.findCalEtcValueList(map);
	}
	public int deleteCalEtcValueList(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnCalListDAO.deleteCalEtcValueList(map);
		}
		return resultQty;
	}
	public int insertCalEtcValueList(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnCalListDAO.insertCalEtcValueList(map);
		}
		return resultQty;
	}
	public int updateCalEtcValueList(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnCalListDAO.updateCalEtcValueList(map);
		}
		return resultQty;
	}
	
	public List findCalStdEqList(Map map) throws Exception
	{      
		return anOnCalListDAO.findCalStdEqList(map);
	}
	public int deleteCalStdEqList(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnCalListDAO.deleteCalStdEqList(map);
		}
		return resultQty;
	}
	public int insertCalStdEqList(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnCalListDAO.insertCalStdEqList(map);
			//차기교정일 업데이트 
			anOnCalListDAO.updateCalStdEqNextDateList(map);
		}
		return resultQty;
	}
	public int updateCalStdEqList(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnCalListDAO.updateCalStdEqList(map);
			//차기교정일 업데이트 
			anOnCalListDAO.updateCalStdEqNextDateList(map);
		}
		return resultQty;
	}

	public List findCalGnlValueList(Map map) throws Exception
	{      
		return anOnCalListDAO.findCalGnlValueList(map);
	}
	public int deleteCalGnlValueList(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnCalListDAO.deleteCalGnlValueList(map);
		}
		return resultQty;
	}
	public int insertCalGnlValueList(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnCalListDAO.insertCalGnlValueList(map);
		}
		return resultQty;
	}
	public int updateCalGnlValueList(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnCalListDAO.updateCalGnlValueList(map);
		}
		return resultQty;
	}
	
	public List findCalSclValueList(Map map) throws Exception
	{      
		return anOnCalListDAO.findCalSclValueList(map);
	}
	public int deleteCalSclValueList(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnCalListDAO.deleteCalSclValueList(map);
		}
		return resultQty;
	}
	public int insertCalSclValueList(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnCalListDAO.insertCalSclValueList(map);
		}
		return resultQty;
	}
	public int updateCalSclValueList(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnCalListDAO.updateCalSclValueList(map);
		}
		return resultQty;
	}
}

