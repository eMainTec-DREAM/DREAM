package common.finder.valid.service.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.bean.User;
import common.finder.valid.dao.ValidationDAO;
import common.finder.valid.service.ValidationService;

/**
 * Validation ServiceImpl
 * @author  javaworker
 * @version $Id: ValidationServiceImpl.java,v 1.11 2014/08/05 01:14:19 pochul2423 Exp $
 * @since   1.0
 *
 * @spring.bean id="validationServiceTarget"
 * @spring.txbn id="validationService"
 * @spring.property name="validationDAO" ref="validationDAO"
 */
public class ValidationServiceImpl implements ValidationService
{
    /** Validation DAO */
    private ValidationDAO validationDAO = null;

    public ValidationDAO getValidationDAO()
    {
        return validationDAO;
    }

    public void setValidationDAO(ValidationDAO validationDAO)
    {
        this.validationDAO = validationDAO;
    }

    public Map findUsrDirDescCode(String desc, String dirType, String codeKind,String compNo)
    {
        List list = validationDAO.findUsrDirDescCode(desc, dirType,compNo);
        Map rtnMap = new HashMap();
        rtnMap.put("status", "desc");
        
        if(list.size() == 0)
        { 
            list = validationDAO.findUsrDirCode(desc, dirType,compNo);
            rtnMap.put("status", "code");
        }
        rtnMap.put("list", list);
        return rtnMap;
    }
    
    public Map findSysDirDescCode(String desc, String dirType, String codeKind, User user)
    {
        List list = validationDAO.findSysDirDescCode(desc, dirType, user);
        Map rtnMap = new HashMap();
        rtnMap.put("status", "desc");
        
        if(list.size() == 0){
            list = validationDAO.findSysDirCode(desc, dirType, user);
            rtnMap.put("status", "code");
        }
        rtnMap.put("list", list);
        return rtnMap;
    }
    
    public Map findSysDirCodeDesc(String code, String dirType, String codeKind, User user)
    {
        List list = validationDAO.findSysDirCodeDesc(code, dirType,user);
        
        Map rtnMap = new HashMap();
        rtnMap.put("status", "desc");
        rtnMap.put("list", list);
        return rtnMap;
    }

    public String findUsrDirCodeDesc(String code, String dirType, String codeKind, String compNo)
    {
        String value = validationDAO.findUsrDirCodeDesc(code, dirType, compNo);
        return value;
    }
    
    public Map findSysDirIdDesc(String id, User user)
    {
       List list =  validationDAO.findSysDirIdDesc(id, user);
       
       Map rtnMap = new HashMap();
       rtnMap.put("status", "desc");
       rtnMap.put("list", list);
       
       return rtnMap;
    }

    public String findUsrDirIdDesc(String id, String compNo)
    {
        String value = validationDAO.findUsrDirIdDesc(id, compNo);
        return value;
    }
    public String findNextVal(String sequenceName)
    {
        return validationDAO.findNextVal(sequenceName);
    }
    
    public String findNextNoVal(String tableName, String columnName,String compNo)
    {
        return validationDAO.findNextNoVal(tableName, columnName, compNo);
    }
    
    public Map findTableDescCode(String desc, String descType, String expCode, String compNo, String lang, String expCode2,String expCode3)
    {
        List values = null;
        Map rtnMap = new HashMap();
        rtnMap.put("status", "desc");
        
        if ("TAMENU".equals(descType))
        {
            values = validationDAO.findMenuDesc(desc,lang);

            rtnMap.put("list", values);
                
        }
        else if ("TAPAGE".equals(descType))
        {
            values = validationDAO.findPageDesc(desc);
        }
        else if ("TABUTTON".equals(descType))
        {
            values = validationDAO.findButtonDesc(desc);
        }
        else if ("TACDUSRD".equals(descType))
        {
            values = validationDAO.findCdUsrdDesc(expCode, desc);
            
            if(values.size() == 0)
            {
                values = validationDAO.findCdUsrdCode(expCode, desc);
                rtnMap.put("status", "code");
            }
        }
        else if ("TADEPT".equals(descType))
        {
            values = validationDAO.findDeptDesc(desc,expCode);
            
            if(values.size() == 0)
            {
                values = validationDAO.findDeptCode(desc,expCode);
                rtnMap.put("status", "code");
            }
        }
        else if ("TAEMPNO".equals(descType))
        {
            values = validationDAO.findEmpNo(desc,compNo);
        }
        else if ("TAUSER".equals(descType))
        {
            values = validationDAO.findUserName(desc,compNo);
        }
        else if ("TAEMPNAME".equals(descType))
        {
            values = validationDAO.findEmpName(desc,compNo);
            
            if(values.size() == 0)
            {
                values = validationDAO.findEmpId(desc,compNo);
                rtnMap.put("status", "code");
            }
        }
        else if ("TAUSRGRP".equals(descType))
        {
            values = validationDAO.findUsrGrpDesc(desc);
        }
        else if ("TAEQLOC".equals(descType))
        {
            values = validationDAO.findEqLocDesc(desc,compNo,expCode);
        }
        else if ("TAEQLOCFULL".equals(descType))
        {
            values = validationDAO.findEqLocFullDesc(desc,compNo,expCode);
        }
        else if ("TAEQCTG".equals(descType))
        {
            values = validationDAO.findEqCtgDesc(desc,compNo);
        }
        else if ("TAEQCTGFULL".equals(descType))
        {
            values = validationDAO.findEqCtgFullDesc(desc,compNo,expCode);
        }
        else if ("TAEQCTGASMB".equals(descType))
        {
            values = validationDAO.findEqCtgAsmbDesc(desc,expCode,compNo);
        }
        else if ("TAEQASMB".equals(descType))
        {
            values = validationDAO.findEqAsmbDesc(desc,expCode,compNo,expCode2);
        }
        else if ("TAFAILURE".equals(descType))
        {
            values = validationDAO.findFailureDesc(desc,expCode,compNo,lang);
        }
        else if ("TAPARTS".equals(descType))
        {
            values = validationDAO.findPartsDesc(desc,expCode,compNo,lang);
        }
        else if ("TATPARTS".equals(descType))
        {
            values = validationDAO.findTpartsDesc(desc,expCode,compNo);
        }
        else if ("TAREQPARTS".equals(descType))
        {
            values = validationDAO.findReqPartsDesc(desc,expCode,compNo);
        }
        else if ("YN".equals(descType))
        {
            values = validationDAO.findYn(desc);
        }
        else if ("LEVEL".equals(descType))
        {
            values = validationDAO.findLvl(desc);
        }
        else if ("TAEQASSET".equals(descType))
        {
            values = validationDAO.findEqAssetDesc(desc,expCode,compNo);
        }
        else if ("TAPOPLINE".equals(descType))
        {
            values = validationDAO.findLineDesc(desc,expCode,compNo);
        }
        else if ("TAEQAPP".equals(descType))
        {
            values = validationDAO.findEqAppDesc(desc,expCode,compNo);
        }
        else if ("TAEQUIPMENT".equals(descType))
        {
            values = validationDAO.findEqDesc(desc,expCode,compNo,lang);
            
            if(values.size() == 0)
            {
                values = validationDAO.findEqCode(desc,expCode,compNo, lang);
                rtnMap.put("status", "code");
            }
        }
        else if ("TAWAREHOUSE".equals(descType))
        {
            values = validationDAO.findWcodeDesc(desc,expCode,compNo);
        }
        else if ("TATWAREHOUSE".equals(descType))
        {
            values = validationDAO.findTWcodeDesc(desc,expCode,compNo);
        }
        else if ("TAVENDOR".equals(descType))
        {
            values = validationDAO.findVendorDesc(desc,expCode,compNo);
        }
        else if ("TALANG".equals(descType))
        {
            values = validationDAO.findLangDesc(desc,expCode,compNo,lang);
        }
        else if ("TACTCTR".equals(descType))
        {
            values = validationDAO.findCtCtrDesc(desc,compNo);
        }        
        else if ("TAWKCTR".equals(descType))
        {
        	values = validationDAO.findWkCtrDesc(desc,compNo);
        }        
        else if ("TAPTAPP".equals(descType))
        {
            values = validationDAO.findPtAppDesc(desc,expCode,compNo);
        }       
        else if ("MESLINE".equals(descType))
        {
            values = validationDAO.findMesLineDesc(desc,expCode,compNo);
        }       
        else if ("TAWORKORDER".equals(descType))
        {
            values = validationDAO.findWoNo(desc,expCode,compNo,expCode2,expCode3,lang);
        }
        else if ("TAPLANT".equals(descType))
        {
            values = validationDAO.findPlantDesc(desc,compNo);
        }
        
        rtnMap.put("list", values);
        
        return rtnMap;
    }

    public List findFileAttach(String code, String expCode, User user)
    {
        List resultList = null;
        List returnList = null;
        
        if("IMAGE".equals(expCode))
        {
            returnList = validationDAO.findImageAttach(code, expCode, user);
        }
        else if("DOCUMENT".equals(expCode))
        {
           resultList =  validationDAO.findFileAttach(code, expCode, user);
           String userSecGrade = user.getSecurGrade();

           returnList = new ArrayList();
           
           for(Object resultMap : resultList)
           {
               Map result = (Map)resultMap;
               
               String securGrade = String.valueOf(result.get("SECURGRADE"));
               
               
               //문서 보안이 설정 안되어 있다면 그냥 통과!
               //if(securGrade == "null") continue;
               if(securGrade == "null"||securGrade==null||securGrade.equals(""))
               {
                   securGrade = "3";
               }
               
               //유저 권한이 설정되어 있지 않다면 일반문서 권한 부여
               if(userSecGrade == "null"||userSecGrade==null||userSecGrade.equals(""))
               {
                   userSecGrade = "3";
               }
               
               //유저권한도 설정되고 파일 권한도 설정도어 있다!
//               else
//               {
                   int userGrade = Integer.parseInt(userSecGrade);
                   int docGrade = Integer.parseInt(securGrade);
                   
                   //유저 권한이 낮으면 빼라!
                   if(userGrade > docGrade)
                   {
                       result.put("DOCDATA_ID", "");
                   }
                   
//               }
               
               returnList.add(result);
           }
        }
            
        return returnList;
    }
}

