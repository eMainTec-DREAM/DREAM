package dream.org.wrkgrp.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.org.wrkgrp.dao.LovWkCtrListDAO;
import dream.org.wrkgrp.dto.LovWkCtrListDTO;

/**
 * 작업그룹 팝업
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovWkCtrListDAOTarget"
 * @spring.txbn id="lovWkCtrListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovWkCtrListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovWkCtrListDAO
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
    public List findWkCtrList(LovWkCtrListDTO lovWkCtrListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT												");
        query.append("		wkctr_id		AS id,							");
        query.append("		wkctr_id		AS wkctrId,						");
        query.append("		p_wkctr_id		AS paWkCtrId,					");
        query.append("		wkctr_no		AS wkctrNo,						");
        query.append("      MIN(LEVEL) OVER() AS minLvl,	");
        query.append("      LEVEL,											");
        query.append("		description		AS wkctrDesc					");
        query.append("FROM TAWKCTR											");
        query.append("WHERE 1=1												");
        query.getAndQuery("is_use", "Y");
        query.getAndQuery("comp_no", loginUser.getCompNo());
        query.getLikeQuery("wkctr_no", lovWkCtrListDTO.getWkCtrNo());
        query.getLikeQuery("full_desc", lovWkCtrListDTO.getWkCtrDesc());
        query.append(" START WITH p_wkctr_id = 0							");
        query.append(" CONNECT BY PRIOR wkctr_id = p_wkctr_id				");
        query.append("ORDER BY ord_no");
//      
        return getJdbcTemplate().queryForList(query.toString());
    }
    
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
    public List findWkCtrAcList(LovWkCtrListDTO lovWkCtrListDTO, User loginUser, Map<String, String> columnMap, Map<String, String> conditionMap)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT b.*,  LEVEL LVL, MIN(LEVEL) OVER() MINLVL FROM (               ");
        query.append("SELECT												");
        query.append("		''				AS seqNo						");
        query.append("		,comp_no		AS comp_no						");
        query.append("		,wkctr_id		AS id							");
        query.append("		,wkctr_id		AS wkctr_id						");
        query.append("		,p_wkctr_id		AS p_wkctr_id					");
        query.append("		,wkctr_no		AS wkctr_no						");
        query.append("      ,LEVEL											");
        query.append("		,description	AS description				    ");
        query.append("		,full_desc		AS full_desc					");
        query.append("		,is_use		    AS is_use					    ");
        query.append("FROM TAWKCTR											");
        query.append("WHERE 1=1												");
        query.append(" START WITH p_wkctr_id = 0							");
        query.append(" CONNECT BY PRIOR wkctr_id = p_wkctr_id				");
        query.append("ORDER SIBLINGS BY ord_no");
        query.append(" ) b                                                  ");
        query.append("WHERE 1 = 1                                           ");
        query.getAndQuery("is_use", conditionMap);
        query.getAndQuery("comp_no", conditionMap);
        query.getLikeQuery("wkctr_no", lovWkCtrListDTO.getWkCtrNo());
        query.getLikeQuery("full_desc", lovWkCtrListDTO.getWkCtrDesc());
        query.append("START WITH p_wkctr_id = 0                             ");
        query.append("CONNECT BY PRIOR wkctr_id = p_wkctr_id                ");


        return getJdbcTemplate().queryForList(query.toString());
    }
    
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
     * @return String
     */
    public String findTotalCount(LovWkCtrListDTO lovWkCtrListDTO, User loginUser, Map<String, String> columnMap, Map<String, String> conditionMap)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT												");
        query.append("		count(*)										");
        query.append("FROM TAWKCTR											");
        query.append("WHERE 1=1												");
        query.getAndQuery("is_use", conditionMap);
        query.getAndQuery("comp_no", conditionMap);
        query.getAndQuery("wkctr_no", lovWkCtrListDTO.getWkCtrNo());
        query.getLikeQuery("full_desc", lovWkCtrListDTO.getWkCtrDesc());
        query.append(" START WITH p_wkctr_id = 0							");
        query.append(" CONNECT BY PRIOR wkctr_id = p_wkctr_id				");

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}