package dream.comm.service.spring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.bean.MwareConfig;
import common.bean.User;

import dream.comm.dao.MaFinderAcDAO;
import dream.comm.service.MaFinderAcService;

/**
 * Validation ServiceImpl
 * @author  javaworker
 * @version $Id: ValidationServiceImpl.java,v 1.11 2014/08/05 01:14:19 pochul2423 Exp $
 * @since   1.0
 *
 * @spring.bean id="maFinderAcServiceTarget"
 * @spring.txbn id="maFinderAcService"
 * @spring.property name="maFinderAcDAO" ref="maFinderAcDAO"
 */
public class MaFinderAcServiceImpl implements MaFinderAcService
{
    /** Validation DAO */
    private MaFinderAcDAO maFinderAcDAO = null;
    
    public MaFinderAcDAO getMaFinderAcDAO()
    {
        return maFinderAcDAO;
    }

    public void setMaFinderAcDAO(MaFinderAcDAO maFinderAcDAO)
    {
        this.maFinderAcDAO = maFinderAcDAO;
    }

    @Override
    public Map findAutoCompleteDesc(String keySearchCol, String keySearchVal,
            Map<String, String> columnMap, String tableName,
            Map<String, String> conditionParam, Map<String, String> labelMap, String lang, boolean limited, User user)
    {

        List resultList = null;
        
        switch (tableName.toUpperCase())
        {
            case "TACDSYSD":
                resultList = maFinderAcDAO.findCdsysd(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TACDSYSD_CODE":
                resultList = maFinderAcDAO.findCdsysdCode(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAUSER":
                resultList = maFinderAcDAO.findUser(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAEQUIPMENT":
                resultList = maFinderAcDAO.findEquipment(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAEQLOC":
                resultList = maFinderAcDAO.findEquipLoc(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAEQCTG":
                resultList = maFinderAcDAO.findEquipCtg(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TADEPT":
                resultList = maFinderAcDAO.findDept(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAEMP":
                resultList = maFinderAcDAO.findEmp(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TATABLE":
                resultList = maFinderAcDAO.findUserRptTable(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TATABCOL":
                resultList = maFinderAcDAO.findUserRpttabCol(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAPARTS":
                resultList = maFinderAcDAO.findParts(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAPARTSDESC":
                resultList = maFinderAcDAO.findPartsDesc(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAPARTSMODEL":
                resultList = maFinderAcDAO.findPartsModel(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAPARTSPTSIZE":
                resultList = maFinderAcDAO.findPartsPtSize(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAEHMENU":
                resultList = maFinderAcDAO.findEhUser(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TALANG":
                resultList = maFinderAcDAO.findLang(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAPAGE":
                resultList = maFinderAcDAO.findPage(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAPMENU":
                resultList = maFinderAcDAO.findPMenu(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAFAILURE":
                resultList = maFinderAcDAO.findFailuare(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAWAREHOUSE":
                resultList = maFinderAcDAO.findWarehouse(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TACERTLIST":
                resultList = maFinderAcDAO.findCertList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TACOURSELIST":
                resultList = maFinderAcDAO.findCourseList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TADOCCTG":
                resultList = maFinderAcDAO.findDocctgList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAPLANT":
                resultList = maFinderAcDAO.findPlantList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TACDUSRD":
                resultList = maFinderAcDAO.findUsrdList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TARCMLIST":
                resultList = maFinderAcDAO.findRcmList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAEQTOOL":
                resultList = maFinderAcDAO.findEqToolList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAWRKCALLIST":
                resultList = maFinderAcDAO.findWrkcalList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TACOMP":
                resultList = maFinderAcDAO.findCompList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAEQASMB":
                resultList = maFinderAcDAO.findEqAsmbList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAPMTASKMAP":
                resultList = maFinderAcDAO.findTaskNoList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TACRITYLIST":
                resultList = maFinderAcDAO.findCrityList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAPMTASKMAPLIST":
                resultList = maFinderAcDAO.findTaskMapList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TACRITYVALUE":
                resultList = maFinderAcDAO.findRcmCrityValList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAINVTPRCTP":
                resultList = maFinderAcDAO.findInvtprcTpList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAASSBASE":
                resultList = maFinderAcDAO.findAssBaseList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAASSBASEPVAL":
                resultList = maFinderAcDAO.findAssBasePointValList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAINVTLIST":
                resultList = maFinderAcDAO.findInvtList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAINVTPRCPH":
                resultList = maFinderAcDAO.findInvtPrcphList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAWORKORDER":
                resultList = maFinderAcDAO.findWoList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAWKCTR":
                resultList = maFinderAcDAO.findWkCtrList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAASSET":
                resultList = maFinderAcDAO.findAssetList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAPMLST":
                resultList = maFinderAcDAO.findPmList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAEQCTGASMB":
                resultList = maFinderAcDAO.findEqCtgAsmbList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAVENDOR":
                resultList = maFinderAcDAO.findVendorList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TACTCTR":
                resultList = maFinderAcDAO.findCtCtrList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAMESLINE":
                resultList = maFinderAcDAO.findMesLineList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAUSRRPTTAB":
                resultList = maFinderAcDAO.findUsrRptTabList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAPARTEQUIP": //순환자재
                resultList = maFinderAcDAO.findPartEquipList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAPMCALIBSTDTP": //교정표준값
                resultList = maFinderAcDAO.findCalibStdList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TASTWRKWORK": //교정표준값
                resultList = maFinderAcDAO.findStWrkWorkList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAEQASMBBYPM": //예방작업 설정 - 점검항목 용
                resultList = maFinderAcDAO.findEqAsmbByPmList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TALNWRKLIST": 
                resultList = maFinderAcDAO.findLnWrkList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TACMENU":
                resultList = maFinderAcDAO.findCMenu(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAPRODUCT":
                resultList = maFinderAcDAO.findProductList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAPMEQUIP":
                resultList = maFinderAcDAO.findPmEquipList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAEXCELTAB":
                resultList = maFinderAcDAO.findExcelTabList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAUSRGRP":
                resultList = maFinderAcDAO.findUsrGrpList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAPTEMGISSLIST":
                resultList = maFinderAcDAO.findEmgPartList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAEQCTGPART":
                resultList = maFinderAcDAO.findEqCtgPartList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAEQCTGSPEC":
                resultList = maFinderAcDAO.findEqCtgSpecList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TALINKEDFUNC":
                resultList = maFinderAcDAO.findLinkedFuncList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TACDSYSM":
                resultList = maFinderAcDAO.findCdsysm(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TACDUSRM":
                resultList = maFinderAcDAO.findCdusrm(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAMEASURETIME":
                resultList = maFinderAcDAO.findMeasureTime(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAEQASSLIST":
                resultList = maFinderAcDAO.findEqAssList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAWOPLAN":
                resultList = maFinderAcDAO.findWoPlanList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TADBCONTENTS":
                resultList = maFinderAcDAO.findDbConList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAPTSTOCK":
                resultList = maFinderAcDAO.findPtStckList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAPTPRLIST":
                resultList = maFinderAcDAO.findPtprList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAEQCTGPMPOINT":
                resultList = maFinderAcDAO.findEqCtgPartAcAcList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;  
            case "TAWOREQ":
                resultList = maFinderAcDAO.findWoReqList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAPTBINLIST":
                resultList = maFinderAcDAO.findPartListBinList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;    
            case "TAACCOUNT":
                resultList = maFinderAcDAO.findAccountList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;  
            case "TAWOPARTS":
                resultList = maFinderAcDAO.findWoPartsList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAPTISSLIST":
                resultList = maFinderAcDAO.findPtIssList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAPOITEMLIST":
                resultList = maFinderAcDAO.findPoItemList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TAPARTSTOOLS":
            	resultList = maFinderAcDAO.findToolAcList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
            	break;
            case "TAWOLETCTG":
            	resultList = maFinderAcDAO.findWoLetCtgAcList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
            	break;
            case "TAMESSAGECATEG":
            	resultList = maFinderAcDAO.findMsgCategAcList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
            	break;
            case "TAMSGCOMPSET":
            	resultList = maFinderAcDAO.findMsgCompAcList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
            	break;
            case "TAUSRPLANTAUTH":
            	resultList = maFinderAcDAO.findUsrPlantAuthAcList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
            	break;
            case "TASTDCHECKPOINT":
            	resultList = maFinderAcDAO.findStdCheckPointList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
            	break;
            case "TAPMUPMLST":
            	resultList = maFinderAcDAO.findPmUInsAcList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
            	break;
            case "TAUSAGEWRKLIST": 
                resultList = maFinderAcDAO.findUsageWrkAcList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
                break;
            case "TACONTRACT": 
            	resultList = maFinderAcDAO.findMgrContractList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
            	break;
            case "TASERVICE": 
            	resultList = maFinderAcDAO.findWorkServiceList(keySearchVal, columnMap, conditionParam, lang, limited?MwareConfig.getAcLength():"", user);
            	break;
            default:
                resultList = maFinderAcDAO.findAutoCompleteCommon(keySearchCol, keySearchVal, columnMap, tableName, conditionParam,labelMap, limited?MwareConfig.getAcLength():"", lang);
                break;
        }
        
        
//        resultList = maFinderAcDAO.findAutoCompleteCommon(keySearchCol, keySearchVal, columnMap, tableName, conditionParam,labelMap, limited?MwareConfig.getAcLength():"", lang);
        Map rtnMap = new HashMap();

        rtnMap.put("list", resultList);
        
        return rtnMap;
    }

    @Override
    public int findValCnt(String keyCode, String code, String codeType,
            Map<String, String> conditionMap)
    {
        int cnt = maFinderAcDAO.findValCnt(keyCode, code, codeType, conditionMap);
        
        return cnt;
    }

    @Override
    public Map findAcCustom(String sValue, String acType, Map<String, String> codeTypeMap, User user)
    {
        int typeCnt = 0;
        String codeType = "";
        for(String key : codeTypeMap.keySet())
        {
            if(typeCnt ==0) codeType = codeTypeMap.get(key);
            typeCnt ++;
        }
        List resultList = null;
        switch (acType)
        {
            case "TACDSYSD":
                resultList = maFinderAcDAO.findSysDirDescCode(sValue, codeType, user);
                break;
            default :

                break;
        }
        
        Map rtnMap = new HashMap();

        rtnMap.put("list", resultList);
        
        return rtnMap;
    }

}

