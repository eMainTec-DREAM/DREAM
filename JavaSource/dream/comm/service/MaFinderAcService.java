package dream.comm.service;

import java.util.Map;

import common.bean.User;


/**
 * AutoComplete Service
 * @author  javaworker
 * @version $Id: ValidationService.java,v 1.1 2013/08/30 09:10:52 javaworker Exp $
 * @since   1.0
 *
 */
public interface MaFinderAcService
{
    /**
     * Auto Complete Common
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     * 
     * @param keySearchCol
     * @param keySearchVal
     * @param columnMap
     * @param tableName
     * @param ConditionParam
     * @param labelMap
     * @param lang
     * @param limited
     * @param user 
     * @return
     */
    Map findAutoCompleteDesc(String keySearchCol, String keySearchVal, Map<String, String> columnMap, String tableName, Map<String,String> ConditionParam, Map<String, String> labelMap, String lang, boolean limited, User user);

    /**
     * LOV For Auto Complete Common
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     * 
     * @param keyCode
     * @param code
     * @param codeType
     * @param conditionMap
     * @return
     */
    int findValCnt(String keyCode, String code, String codeType,Map<String, String> conditionMap);

    /**
     * Auto Complete Custom
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     * 
     * @param sValue
     * @param acType
     * @param codeTypeMap
     * @param user
     * @return
     */
    Map findAcCustom(String sValue, String acType, Map<String, String> codeTypeMap, User user);
}
