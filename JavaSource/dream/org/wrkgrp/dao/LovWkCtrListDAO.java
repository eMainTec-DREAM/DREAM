package dream.org.wrkgrp.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.org.wrkgrp.dto.LovWkCtrListDTO;

/**
 * 작업그룹 팝업
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 */
public interface LovWkCtrListDAO
{
    /**
     * 작업그룹 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovWkCtrListDTO
     * @return
     */
    public List findWkCtrList(LovWkCtrListDTO lovWkCtrListDTO, User loginUser);
    /**
     * 작업그룹 검색 AC LOV
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
     * 작업그룹 검색 COOUNT
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