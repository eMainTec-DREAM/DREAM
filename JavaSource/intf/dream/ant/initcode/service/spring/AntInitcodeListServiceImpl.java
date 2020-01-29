package intf.dream.ant.initcode.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.ant.initcode.dao.AntInitcodeListDAO;
import intf.dream.ant.initcode.service.AntInitcodeListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="antInitcodeListServiceTarget"
 * @spring.txbn id="antInitcodeListService"
 * @spring.property name="antInitcodeListDAO" ref="antInitcodeListDAO"
 */
public class AntInitcodeListServiceImpl implements AntInitcodeListService
{
    private AntInitcodeListDAO antInitcodeListDAO = null;

	public AntInitcodeListDAO getAntInitcodeListDAO() {
		return antInitcodeListDAO;
	}
	public void setAntInitcodeListDAO(AntInitcodeListDAO antInitcodeListDAO) {
		this.antInitcodeListDAO = antInitcodeListDAO;
	}
	
	public List findLangList(Map map)
	{      
		return antInitcodeListDAO.findLangList(map);
	}
	public List findSyscodeList(Map map)
	{      
		return antInitcodeListDAO.findSyscodeList(map);
	}
	public List findUsrcodeList(Map map)
	{      
		return antInitcodeListDAO.findUsrcodeList(map);
	}
	public List findFailureList(Map map)
	{      
		return antInitcodeListDAO.findFailureList(map);
	}
	public List findUserList(Map map)
	{      
		return antInitcodeListDAO.findUserList(map);
	}
	public List findEmpList(Map map)
	{      
		return antInitcodeListDAO.findEmpList(map);
	}
	public List findDeptList(Map map)
	{      
		return antInitcodeListDAO.findDeptList(map);
	}
	public List findWkctrList(Map map)
	{      
		return antInitcodeListDAO.findWkctrList(map);
	}
	public List findPlantList(Map map)
	{      
		return antInitcodeListDAO.findPlantList(map);
	}
	public List findEquipmentList(Map map, String eqCtgType, String eqLocId, String deptId, String usageDeptId)
	{      
		return antInitcodeListDAO.findEquipmentList(map,eqCtgType,eqLocId,deptId,usageDeptId);
	}
	public List findEqtoolList(Map map, String eqCtgType, String eqLocId, String deptId, String usageDeptId)
	{      
		return antInitcodeListDAO.findEqtoolList(map,eqCtgType,eqLocId,deptId,usageDeptId);
	}
	public List findEqspecList(Map map, String eqCtgType, String eqLocId, String deptId, String usageDeptId)
	{      
		return antInitcodeListDAO.findEqspecList(map,eqCtgType,eqLocId,deptId,usageDeptId);
	}
	public List findEqpartList(Map map, String eqCtgType, String eqLocId, String deptId, String usageDeptId)
	{      
		return antInitcodeListDAO.findEqpartList(map,eqCtgType,eqLocId,deptId,usageDeptId);
	}
	public List findEqasmbList(Map map, String eqCtgType, String eqLocId, String deptId, String usageDeptId)
	{      
		return antInitcodeListDAO.findEqasmbList(map,eqCtgType,eqLocId,deptId,usageDeptId);
	}
	public List findPmequipList(Map map)
	{      
		return antInitcodeListDAO.findPmequipList(map);
	}
	public List findEqlocList(Map map)
	{      
		return antInitcodeListDAO.findEqlocList(map);
	}
	public List findEqctgList(Map map)
	{      
		return antInitcodeListDAO.findEqctgList(map);
	}
	public List findWarehouseList(Map map)
	{      
		return antInitcodeListDAO.findWarehouseList(map);
	}
	public List findMenuList(Map map)
	{      
		return antInitcodeListDAO.findMenuList(map);
	}
	public List findProductList(Map map)
	{      
		return antInitcodeListDAO.findProductList(map);
	}
	public List findUsrGrpMenuList(Map map)
	{      
		return antInitcodeListDAO.findUsrGrpMenuList(map);
	}
	public List findConfigList(Map map)
	{      
		return antInitcodeListDAO.findConfigList(map);
	}
	public List findStockList(Map map, String wcodeId)
	{      
		return antInitcodeListDAO.findStockList(map,wcodeId);
	}
	public List findPartsList(Map map, String wcodeId)
	{      
		return antInitcodeListDAO.findPartsList(map,wcodeId);
	}
	public List findDownUserCheck(Map map)
	{      
		return antInitcodeListDAO.findDownUserCheck(map);
	}

	public String findUserEqCtgType(Map map)
	{      
		return antInitcodeListDAO.findUserEqCtgType(map);
	}
	public String findUserEqLoc(Map map)
	{      
		return antInitcodeListDAO.findUserEqLoc(map);
	}
	public String findUserDept(Map map)
	{      
		return antInitcodeListDAO.findUserDept(map);
	}
	public String findUserUsageDept(Map map)
	{      
		return antInitcodeListDAO.findUserUsageDept(map);
	}
	public String findUserWcode(Map map)
	{      
		return antInitcodeListDAO.findUserWcode(map);
	}
	@Override
	public List findPageList(Map map) {
		return antInitcodeListDAO.findPageList(map);
	}
	@Override
	public List findPgBtnList(Map map) {
		return antInitcodeListDAO.findPgBtnList(map);
	}
	@Override
	public List findPgFieldList(Map map) {
		return antInitcodeListDAO.findPgFieldList(map);
	}
	@Override
	public List findPgGridList(Map map) {
		return antInitcodeListDAO.findPgGridList(map);
	}
	@Override
	public List findPgGridColList(Map map) {
		return antInitcodeListDAO.findPgGridColList(map);
	}
	@Override
	public List findPgPageList(Map map) {
		return antInitcodeListDAO.findPgPageList(map);
	}
	@Override
	public List findPgLinkedFuncList(Map map) {
		return antInitcodeListDAO.findPgLinkedFuncList(map);
	}
	@Override
	public List findUgPgAuList(Map map) {
		return antInitcodeListDAO.findUgPgAuList(map);
	}
	@Override
	public List findUgPgBtnAuList(Map map) {
		return antInitcodeListDAO.findUgPgBtnAuList(map);
	}
	@Override
	public List findUgPgPgAuList(Map map) {
		return antInitcodeListDAO.findUgPgPgAuList(map);
	}
	@Override
	public String findIsMaintDept(Map map) {
		return antInitcodeListDAO.findIsMaintDept(map);
	}
}

