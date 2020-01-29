package intf.dream.android.offline.mainitcode.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.android.offline.mainitcode.dao.AnIfInitcodeListDAO;
import intf.dream.android.offline.mainitcode.service.AnIfInitcodeListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anIfInitcodeListServiceTarget"
 * @spring.txbn id="anIfInitcodeListService"
 * @spring.property name="anIfInitcodeListDAO" ref="anIfInitcodeListDAO"
 */
public class AnIfInitcodeListServiceImpl implements AnIfInitcodeListService
{
    private AnIfInitcodeListDAO anIfInitcodeListDAO = null;

	public AnIfInitcodeListDAO getAnIfInitcodeListDAO() {
		return anIfInitcodeListDAO;
	}
	public void setAnIfInitcodeListDAO(AnIfInitcodeListDAO anIfInitcodeListDAO) {
		this.anIfInitcodeListDAO = anIfInitcodeListDAO;
	}
	
	public List findLangList(Map map)
	{      
		return anIfInitcodeListDAO.findLangList(map);
	}
	public List findSyscodeList(Map map)
	{      
		return anIfInitcodeListDAO.findSyscodeList(map);
	}
	public List findUsrcodeList(Map map)
	{      
		return anIfInitcodeListDAO.findUsrcodeList(map);
	}
	public List findFailureList(Map map)
	{      
		return anIfInitcodeListDAO.findFailureList(map);
	}
	public List findUserList(Map map)
	{      
		return anIfInitcodeListDAO.findUserList(map);
	}
	public List findEmpList(Map map)
	{      
		return anIfInitcodeListDAO.findEmpList(map);
	}
	public List findDeptList(Map map)
	{      
		return anIfInitcodeListDAO.findDeptList(map);
	}
	public List findWkctrList(Map map)
	{      
		return anIfInitcodeListDAO.findWkctrList(map);
	}
	public List findPlantList(Map map)
	{      
		return anIfInitcodeListDAO.findPlantList(map);
	}
	public List findEquipmentList(Map map, String eqCtgType, String eqLocId, String deptId, String usageDeptId)
	{      
		return anIfInitcodeListDAO.findEquipmentList(map,eqCtgType,eqLocId,deptId,usageDeptId);
	}
	public List findEqtoolList(Map map, String eqCtgType, String eqLocId, String deptId, String usageDeptId)
	{      
		return anIfInitcodeListDAO.findEqtoolList(map,eqCtgType,eqLocId,deptId,usageDeptId);
	}
	public List findEqspecList(Map map, String eqCtgType, String eqLocId, String deptId, String usageDeptId)
	{      
		return anIfInitcodeListDAO.findEqspecList(map,eqCtgType,eqLocId,deptId,usageDeptId);
	}
	public List findEqpartList(Map map, String eqCtgType, String eqLocId, String deptId, String usageDeptId)
	{      
		return anIfInitcodeListDAO.findEqpartList(map,eqCtgType,eqLocId,deptId,usageDeptId);
	}
	public List findEqasmbList(Map map, String eqCtgType, String eqLocId, String deptId, String usageDeptId)
	{      
		return anIfInitcodeListDAO.findEqasmbList(map,eqCtgType,eqLocId,deptId,usageDeptId);
	}
	public List findPmequipList(Map map)
	{      
		return anIfInitcodeListDAO.findPmequipList(map);
	}
	public List findEqlocList(Map map)
	{      
		return anIfInitcodeListDAO.findEqlocList(map);
	}
	public List findEqctgList(Map map)
	{      
		return anIfInitcodeListDAO.findEqctgList(map);
	}
	public List findWarehouseList(Map map)
	{      
		return anIfInitcodeListDAO.findWarehouseList(map);
	}
	public List findMenuList(Map map)
	{      
		return anIfInitcodeListDAO.findMenuList(map);
	}
	public List findProductList(Map map)
	{      
		return anIfInitcodeListDAO.findProductList(map);
	}
	public List findUsrGrpMenuList(Map map)
	{      
		return anIfInitcodeListDAO.findUsrGrpMenuList(map);
	}
	public List findConfigList(Map map)
	{      
		return anIfInitcodeListDAO.findConfigList(map);
	}
	public List findStockList(Map map, String wcodeId)
	{      
		return anIfInitcodeListDAO.findStockList(map,wcodeId);
	}
	public List findPartsList(Map map, String wcodeId)
	{      
		return anIfInitcodeListDAO.findPartsList(map,wcodeId);
	}
	public List findDownUserCheck(Map map)
	{      
		return anIfInitcodeListDAO.findDownUserCheck(map);
	}

	public String findUserEqCtgType(Map map)
	{      
		return anIfInitcodeListDAO.findUserEqCtgType(map);
	}
	public String findUserEqLoc(Map map)
	{      
		return anIfInitcodeListDAO.findUserEqLoc(map);
	}
	public String findUserDept(Map map)
	{      
		return anIfInitcodeListDAO.findUserDept(map);
	}
	public String findUserUsageDept(Map map)
	{      
		return anIfInitcodeListDAO.findUserUsageDept(map);
	}
	public String findUserWcode(Map map)
	{      
		return anIfInitcodeListDAO.findUserWcode(map);
	}
	@Override
	public List findPageList(Map map) {
		return anIfInitcodeListDAO.findPageList(map);
	}
	@Override
	public List findPgBtnList(Map map) {
		return anIfInitcodeListDAO.findPgBtnList(map);
	}
	@Override
	public List findPgFieldList(Map map) {
		return anIfInitcodeListDAO.findPgFieldList(map);
	}
	@Override
	public List findPgGridList(Map map) {
		return anIfInitcodeListDAO.findPgGridList(map);
	}
	@Override
	public List findPgGridColList(Map map) {
		return anIfInitcodeListDAO.findPgGridColList(map);
	}
	@Override
	public List findPgPageList(Map map) {
		return anIfInitcodeListDAO.findPgPageList(map);
	}
	@Override
	public List findPgLinkedFuncList(Map map) {
		return anIfInitcodeListDAO.findPgLinkedFuncList(map);
	}
	@Override
	public List findUgPgAuList(Map map) {
		return anIfInitcodeListDAO.findUgPgAuList(map);
	}
	@Override
	public List findUgPgBtnAuList(Map map) {
		return anIfInitcodeListDAO.findUgPgBtnAuList(map);
	}
	@Override
	public List findUgPgPgAuList(Map map) {
		return anIfInitcodeListDAO.findUgPgPgAuList(map);
	}
	@Override
	public String findIsMaintDept(Map map) {
		return anIfInitcodeListDAO.findIsMaintDept(map);
	}
}

