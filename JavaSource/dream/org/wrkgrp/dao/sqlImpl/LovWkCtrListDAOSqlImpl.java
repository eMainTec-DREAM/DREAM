package dream.org.wrkgrp.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
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
public class LovWkCtrListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovWkCtrListDAO
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
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT												");
        query.append("		x.wkctr_id		AS ID,							");
        query.append("		x.wkctr_id		AS WKCTRID,						");
        query.append("		x.p_wkctr_id		AS PAWKCTRID,				");
        query.append("		x.wkctr_no		AS WKCTRNO,						");
        query.append("      MIN(y.lvl) OVER() AS MINLVL,	");
        query.append("      y.lvl 			AS LEVEL,						");
        query.append("		x.description		AS wkctrDesc				");
        query.append("FROM TAWKCTR x										");
        query.append("	  ,(SELECT * FROM dbo.SFAWKCTR_ALL('"+loginUser.getCompNo()+"','0')) y	");
        query.append("WHERE 1=1												");
        query.append("AND 	x.WKCTR_ID = y.WKCTR_ID							");
        query.getAndQuery("x.is_use", "Y");
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getLikeQuery("x.wkctr_no", lovWkCtrListDTO.getWkCtrNo());
        query.getLikeQuery("x.full_desc", lovWkCtrListDTO.getWkCtrDesc());
        query.append("ORDER BY ISNULL(x.ord_no, '99999999')");
        
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
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	String compNo = conditionMap.containsKey("comp_no")?conditionMap.get("comp_no"):loginUser.getCompNo();

        query.append("SELECT												");
        query.append("		''				AS seqNo						");
        query.append("		,x.wkctr_id		AS ID							");
        query.append("		,x.wkctr_id		AS WKCTR_ID						");
        query.append("		,x.p_wkctr_id	AS P_WKCTR_ID					");
        query.append("		,x.wkctr_no		AS WKCTR_NO						");
        query.append("      ,MIN(y.lvl) OVER() AS MINLVL	");
        query.append("      ,y.lvl 			AS LEVEL						");
        query.append("		,x.description	AS description					");
        query.append("FROM TAWKCTR x										");
        query.append("	  ,(SELECT * FROM dbo.SFAWKCTR_ALL('"+compNo+"','0')) y	");
        query.append("WHERE 1=1												");
        query.append("AND 	x.WKCTR_ID = y.WKCTR_ID							");
        query.getAndQuery("x.comp_no", compNo);
        query.getAndQuery("x.is_use", conditionMap);
        query.getLikeQuery("x.wkctr_no", lovWkCtrListDTO.getWkCtrNo());
        query.getLikeQuery("x.full_desc", lovWkCtrListDTO.getWkCtrDesc());
        query.append("ORDER BY ISNULL(x.ord_no, '99999999')");

        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * 작업그룹 검색 COUNT
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
    public String findTotalCount(LovWkCtrListDTO lovWkCtrListDTO, User loginUser, Map<String, String> columnMap, Map<String, String> conditionMap)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	String compNo = conditionMap.containsKey("comp_no")?conditionMap.get("comp_no"):loginUser.getCompNo();

        query.append("SELECT												");
        query.append("		count(*)										");
        query.append("FROM TAWKCTR x										");
        query.append("	  ,(SELECT * FROM dbo.SFAWKCTR_ALL('"+compNo+"','0')) y	");
        query.append("WHERE 1=1												");
        query.append("AND 	x.WKCTR_ID = y.WKCTR_ID							");
        query.getAndQuery("x.comp_no", compNo);
        query.getAndQuery("x.is_use", conditionMap);
        query.getAndQuery("x.wkctr_no", lovWkCtrListDTO.getWkCtrNo());
        query.getLikeQuery("x.full_desc", lovWkCtrListDTO.getWkCtrDesc());

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}