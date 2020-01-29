package common.finder.valid.service;

import java.util.List;
import java.util.Map;

import common.bean.User;

/**
 * Validation Service
 * @author  javaworker
 * @version $Id: ValidationService.java,v 1.1 2013/08/30 09:10:52 javaworker Exp $
 * @since   1.0
 *
 */
public interface ValidationService
{
    
    /**
     * 사용자코드
     * find code of Description
     * @author  javaworker
     * @version $Id: ValidationService.java,v 1.1 2013/08/30 09:10:52 javaworker Exp $
     * @since   1.0
     * 
     * @param code
     * @param codeType
     * @param codeKind
     * @param compNo
     * @return
     */
    Map findUsrDirDescCode(String code, String codeType, String codeKind, String compNo);
   
    /**
     * 시스템코드
     * find code of Description
     * @author  javaworker
     * @version $Id: ValidationService.java,v 1.1 2013/08/30 09:10:52 javaworker Exp $
     * @since   1.0
     * 
     * @param code
     * @param codeType
     * @param codeKind
     * @return
     */
    Map findSysDirDescCode(String code, String codeType, String codeKind, User user);
    
    /**
     * 시스템코드
     * find code of Description
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param code
     * @param codeType
     * @param codeKind
     * @return
     */
    Map findSysDirCodeDesc(String code, String codeType, String codeKind,User user);
    
    /**
     * 사용자코드
     * find code of Description
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param code
     * @param codeType
     * @param codeKind
     * @param compNo
     * @return
     */
    String findUsrDirCodeDesc(String code, String codeType, String codeKind, String compNo);
    /**
     * 시스템코드
     * find code of Description
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    Map findSysDirIdDesc(String id, User user);
    
    /**
     * 사용자코드
     * find code of Description
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    String findUsrDirIdDesc(String id, String compNo);
    /**
     * 테이블
     * find code of Description
     * @author  javaworker
     * @version $Id: ValidationService.java,v 1.1 2013/08/30 09:10:52 javaworker Exp $
     * @since   1.0
     * 
     * @param desc
     * @param descType
     * @param expCode2 
     * @param expCode3 
     * @param compNo
     * @param codeKind
     * @param lang
     * @return
     */
    Map findTableDescCode(String desc, String descType, String expCode, String compNo, String lang, String expCode2, String expCode3);
    
    
    /**
     * sequence nextval
     * @author  wondo
     * @version $Id: ValidationService.java,v 1.1 2013/08/30 09:10:52 javaworker Exp $
     * @since   1.0
     * 
     * @param sequenceName
     * @return
     */
    String findNextVal(String sequenceName);
    /**
     * no nextval(next no)
     * @author  kim21017
     * @version $Id: ValidationService.java,v 1.1 2013/08/30 09:10:52 javaworker Exp $
     * @since   1.0
     * 
     * @param tableName
     * @param columnName
     * @param compNo
     * @return
     */
    String findNextNoVal(String tableName, String columnName, String compNo);

    List findFileAttach(String code, String expCode, User user);
    
}
