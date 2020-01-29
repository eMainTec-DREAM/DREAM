package dream.comm.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;

/**
 * Validation DAOIml
 * @author  javaworker
 * @version $Id: ValidationDAO.java,v 1.20 2014/09/03 04:19:26 pochul2423 Exp $
 * @since   1.0
 */
public interface MaFinderAcDAO
{
    public List findAutoCompleteCommon(String keySearchCol, String keySearchVal,
            Map<String, String> columnMap, String tableName,
            Map<String, String> conditionParam, Map<String, String> labelMap, String acLength, String lang);

    /**
     * 중복 채크
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     * 
     * @param keySearchCol
     * @param keySearchVal
     * @param tableName
     * @param conditionParam
     * @return
     */
    public int findValCnt(String keySearchCol, String keySearchVal, String tableName,
            Map<String, String> conditionParam);

    public List findSysDirDescCode(String sValue, String codeType, User user);

    public List findCdsysd(String keySearchVal, Map<String, String> colMap, Map<String, String> conditionParam, String lang, String length, User user);
    public List findCdsysm(String keySearchVal, Map<String, String> colMap, Map<String, String> conditionParam, String lang, String length, User user);
    public List findCdusrm(String keySearchVal, Map<String, String> colMap, Map<String, String> conditionParam, String lang, String length, User user);

    public List findUser(String keySearchVal, Map<String, String> colMap, Map<String, String> conditionParam, String lang, String length, User user);

    public List findEquipment(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);

    public List findEquipLoc(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);

    public List findEquipCtg(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);

    public List findDept(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);

    public List findEmp(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);

    public List findUserRptTable(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);

    public List findUserRpttabCol(String keySearchVal, Map<String, String> columnMap,
            Map<String, String> conditionParam, String lang, String acLength, User user);
    
    public List findParts(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);
    
    public List findPartsDesc(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);
    public List findPartsModel(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);
    public List findPartsPtSize(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);
    
    public List findEhUser(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);
    
    public List findLang(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);
    
    public List findPage(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);
    
    public List findPMenu(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);
    
    public List findWarehouse(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);
    
    public List findCertList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);
    public List findFailuare(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);
    public List findCourseList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);
    public List findDocctgList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);

    public List findPlantList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);

    public List findUsrdList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);
    
    public List findRcmList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);
    
    public List findEqToolList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);
    
    public List findWrkcalList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);
    
    public List findCompList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);
    
    public List findEqAsmbList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);
    
    public List findTaskNoList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);
    
    public List findCrityList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);
    
    public List findTaskMapList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);

    public List findRcmCrityValList(String keySearchVal, Map<String, String> columnMap,
            Map<String, String> conditionParam, String lang, String acLength, User user);

    public List findCdsysdCode(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);

    public List findInvtprcTpList(String keySearchVal, Map<String, String> columnMap,
            Map<String, String> conditionParam, String lang, String acLength, User user);
    public List findAssBaseList(String keySearchVal, Map<String, String> columnMap,
            Map<String, String> conditionParam, String lang, String acLength, User user);
    public List findAssBasePointValList(String keySearchVal, Map<String, String> columnMap,
            Map<String, String> conditionParam, String lang, String acLength, User user);

    public List findInvtList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);

    public List findInvtPrcphList(String keySearchVal, Map<String, String> columnMap,
            Map<String, String> conditionParam, String lang, String acLength, User user);

    public List findWoList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);
    public List findWkCtrList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);
    public List findPmList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);
    public List findAssetList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);

    public List findEqCtgAsmbList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);

    public List findVendorList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);

    public List findCtCtrList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);

    public List findMesLineList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);
    
    public List findUsrRptTabList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);
    
    public List findUsrGrpList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);

    public List findPartEquipList(String keySearchVal, Map<String, String> columnMap,
            Map<String, String> conditionParam, String lang, String acLength, User user);
    public List findCalibStdList(String keySearchVal, Map<String, String> columnMap,
            Map<String, String> conditionParam, String lang, String acLength, User user);
       
    public List findStdCheckPointList(String keySearchVal, Map<String, String> columnMap,
            Map<String, String> conditionParam, String lang, String acLength, User user);
    
    public List findStWrkWorkList(String keySearchVal, Map<String, String> columnMap,
            Map<String, String> conditionParam, String lang, String acLength, User user);
    
    public List findProductList(String keySearchVal, Map<String, String> columnMap,
            Map<String, String> conditionParam, String lang, String acLength, User user);

    public List findEqAsmbByPmList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);
    
    public List findLnWrkList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);
    
    public List findCMenu(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);
    
    public List findPmEquipList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);
    
    public List findExcelTabList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);

    public List findEmgPartList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);
    
    public List findEqCtgPartList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);
    
    public List findEqCtgSpecList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);
    
    public List findLinkedFuncList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);
    
    public List findMeasureTime(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);
    
    public List findEqAssList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);
    
    public List findWoPlanList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);    
    
    public List findDbConList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);

    public List findPtStckList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);

    public List findPtprList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam, String lang, String string,
            User user);
    
    public List findEqCtgPartAcAcList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);
    
    public List findWoReqList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam, 
            String lang, String acLength, User user);

    public List findPartListBinList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam, 
            String lang, String acLength, User user);
    
    public List findPtpnList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam, 
            String lang, String acLength, User user);

    public List findAccountList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);

    public List findWoPartsList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);

    public List findPtIssList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam,
            String lang, String acLength, User user);    
    
    public List findPoItemList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam, 
            String lang, String acLength, User user);
    
    public List findToolAcList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam, 
            String lang, String acLength, User user);
   
    public List findWoLetCtgAcList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam, 
            String lang, String acLength, User user);
    
    public List findMsgCategAcList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam, 
    		String lang, String acLength, User user);
    
    public List findMsgCompAcList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam, 
    		String lang, String acLength, User user);
     
    public List findUsrPlantAuthAcList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam, 
    		String lang, String acLength, User user);
   
    public List findPmUInsAcList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam, 
    		String lang, String acLength, User user);
    
    public List findUsageWrkAcList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam, 
    		String lang, String acLength, User user);
    
    public List findMgrContractList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam, 
    		String lang, String acLength, User user);
    
    public List findWorkServiceList(String keySearchVal, Map<String, String> columnMap, Map<String, String> conditionParam, 
    		String lang, String acLength, User user);
    
}