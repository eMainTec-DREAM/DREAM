package dream.org.wrkgrp.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.org.wrkgrp.dto.LovWkCtrListDTO;

/**
 * �۾��׷� �˾�
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 */
public interface LovWkCtrListDAO
{
    /**
     * �۾��׷� �˻�
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovWkCtrListDTO
     * @return
     */
    public List findWkCtrList(LovWkCtrListDTO lovWkCtrListDTO, User loginUser);
    /**
     * �۾��׷� �˻� AC LOV
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovWkCtrListDTO
     * @param loginUser
     * @param columnMap
     * @param conditionMap
     * @return
     */
    public List findWkCtrAcList(LovWkCtrListDTO lovWkCtrListDTO, User loginUser, Map<String, String> columnMap, Map<String, String> conditionMap);
    /**
     * �۾��׷� �˻� COOUNT
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovWkCtrListDTO
     * @param loginUser
     * @param columnMap
     * @param conditionMap
     * @return
     */
    public String findTotalCount(LovWkCtrListDTO lovWkCtrListDTO, User loginUser, Map<String, String> columnMap, Map<String, String> conditionMap);
}