package intf.dream.bee.cal.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import dream.work.list.dao.WoPlanDetailDAO;
import dream.work.list.dto.WoPlanDetailDTO;
import intf.dream.bee.cal.dao.BeeCalListDAO;
import intf.dream.bee.cal.dto.BeeCalCommonDTO;
import intf.dream.bee.cal.service.BeeCalListService;
import intf.dream.bee.pmwork.dao.BeePmworkListDAO;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beeCalListServiceTarget"
 * @spring.txbn id="beeCalListService"
 * @spring.property name="beeCalListDAO" ref="beeCalListDAO"
 * @spring.property name="woPlanDetailDAO" ref="woPlanDetailDAO"
 * @spring.property name="beePmworkListDAO" ref="beePmworkListDAO"
 */
public class BeeCalListServiceImpl implements BeeCalListService
{
    private BeeCalListDAO beeCalListDAO = null;
    private BeePmworkListDAO beePmworkListDAO = null;
    private WoPlanDetailDAO woPlanDetailDAO = null;
    

	public WoPlanDetailDAO getWoPlanDetailDAO() {
		return woPlanDetailDAO;
	}
	public void setWoPlanDetailDAO(WoPlanDetailDAO woPlanDetailDAO) {
		this.woPlanDetailDAO = woPlanDetailDAO;
	}
	public BeePmworkListDAO getBeePmworkListDAO() {
		return beePmworkListDAO;
	}
	public void setBeePmworkListDAO(BeePmworkListDAO beePmworkListDAO) {
		this.beePmworkListDAO = beePmworkListDAO;
	}
	public BeeCalListDAO getBeeCalListDAO() {
		return beeCalListDAO;
	}
	public void setBeeCalListDAO(BeeCalListDAO beeCalListDAO) {
		this.beeCalListDAO = beeCalListDAO;
	}
	
	public List findCalList(BeeCalCommonDTO beeCalCommonDTO, Map map) throws Exception
	{      
		return beeCalListDAO.findCalList(beeCalCommonDTO,map);
	}
	
	public List findCalCount(BeeCalCommonDTO beeCalCommonDTO, Map map) throws Exception
	{      
		return beeCalListDAO.findCalCount(beeCalCommonDTO,map);
	}
	
	public int deleteCal(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeCalListDAO.deleteWoCalib(map);
			beeCalListDAO.deleteCalEtcValueList(map);
			beeCalListDAO.deleteCalGnlValueList(map);
			beeCalListDAO.deleteCalSclValueList(map);
			beeCalListDAO.deleteCalStdEqList(map);
			beePmworkListDAO.deleteWoCraft(map);
			beePmworkListDAO.deleteWoequip(map);
			beeCalListDAO.deleteCal(map);
		}
		return resultQty;
	}
	public int insertCal(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeCalListDAO.insertCal(map);
			beePmworkListDAO.insertWoequip(map);
			beeCalListDAO.insertWoCalib(map);
			
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
			beeCalListDAO.updateCal(map);
			beePmworkListDAO.updateWoequip(map);
			beeCalListDAO.updateWoCalib(map);
		}
		return resultQty;
	}
	

	public List findCalEtcValueList(Map map) throws Exception
	{      
		return beeCalListDAO.findCalEtcValueList(map);
	}
	public int deleteCalEtcValueList(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeCalListDAO.deleteCalEtcValueList(map);
		}
		return resultQty;
	}
	public int insertCalEtcValueList(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeCalListDAO.insertCalEtcValueList(map);
		}
		return resultQty;
	}
	public int updateCalEtcValueList(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeCalListDAO.updateCalEtcValueList(map);
		}
		return resultQty;
	}
	
	public List findCalStdEqList(Map map) throws Exception
	{      
		return beeCalListDAO.findCalStdEqList(map);
	}
	public int deleteCalStdEqList(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeCalListDAO.deleteCalStdEqList(map);
		}
		return resultQty;
	}
	public int insertCalStdEqList(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeCalListDAO.insertCalStdEqList(map);
			//차기교정일 업데이트 
			beeCalListDAO.updateCalStdEqNextDateList(map);
		}
		return resultQty;
	}
	public int updateCalStdEqList(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeCalListDAO.updateCalStdEqList(map);
			//차기교정일 업데이트 
			beeCalListDAO.updateCalStdEqNextDateList(map);
		}
		return resultQty;
	}

	public List findCalGnlValueList(Map map) throws Exception
	{      
		return beeCalListDAO.findCalGnlValueList(map);
	}
	public int deleteCalGnlValueList(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeCalListDAO.deleteCalGnlValueList(map);
		}
		return resultQty;
	}
	public int insertCalGnlValueList(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeCalListDAO.insertCalGnlValueList(map);
		}
		return resultQty;
	}
	public int updateCalGnlValueList(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeCalListDAO.updateCalGnlValueList(map);
		}
		return resultQty;
	}
	
	public List findCalSclValueList(Map map) throws Exception
	{      
		return beeCalListDAO.findCalSclValueList(map);
	}
	public int deleteCalSclValueList(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeCalListDAO.deleteCalSclValueList(map);
		}
		return resultQty;
	}
	public int insertCalSclValueList(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeCalListDAO.insertCalSclValueList(map);
		}
		return resultQty;
	}
	public int updateCalSclValueList(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeCalListDAO.updateCalSclValueList(map);
		}
		return resultQty;
	}
}

