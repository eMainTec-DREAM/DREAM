package intf.dream.android.offline.mainitcode.service;

import java.util.List;
import java.util.Map;

/**
 *  service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface AnIfInitcodeListService
{     
    public List findLangList(Map map);
    public List findSyscodeList(Map map);
    public List findUsrcodeList(Map map);
    public List findFailureList(Map map);
    public List findUserList(Map map);
    public List findEmpList(Map map);
    public List findDeptList(Map map);
    public List findWkctrList(Map map);
    public List findPlantList(Map map);
    public List findEquipmentList(Map map, String eqCtgType, String eqLocId, String deptId, String usageDeptId);
    public List findEqtoolList(Map map, String eqCtgType, String eqLocId, String deptId, String usageDeptId);
    public List findEqspecList(Map map, String eqCtgType, String eqLocId, String deptId, String usageDeptId);
    public List findEqpartList(Map map, String eqCtgType, String eqLocId, String deptId, String usageDeptId);
    public List findEqasmbList(Map map, String eqCtgType, String eqLocId, String deptId, String usageDeptId);
    public List findPmequipList(Map map);
    public List findEqlocList(Map map);
    public List findEqctgList(Map map);
    public List findWarehouseList(Map map);
    public List findMenuList(Map map);
    public List findProductList(Map map);
    public List findUsrGrpMenuList(Map map);
    public List findConfigList(Map map);
    public List findStockList(Map map, String wcodeId);
    public List findPartsList(Map map, String wcodeId);
    public List findDownUserCheck(Map map);

    public String findUserEqCtgType(Map map);
    public String findUserEqLoc(Map map);
    public String findUserDept(Map map);
    public String findIsMaintDept(Map map);
    public String findUserUsageDept(Map map);
    public String findUserWcode(Map map);
    
    public List findPageList(Map map);
    public List findPgBtnList(Map map);
    public List findPgFieldList(Map map);
    public List findPgGridList(Map map);
    public List findPgGridColList(Map map);
    public List findPgPageList(Map map);
    public List findPgLinkedFuncList(Map map);
    public List findUgPgAuList(Map map);
    public List findUgPgBtnAuList(Map map);
    public List findUgPgPgAuList(Map map);
}
