package dream.rcm.crity.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.rcm.crity.dao.RcmCrityValLovDAO;
import dream.rcm.crity.dto.RcmCrityCommonDTO;

/**
 * Criticality Matrix Page - List DAO implements
 * @author kim21017
 * @version $Id: RcmCrityValLovDAOOraImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmCrityValLovDAOTarget"
 * @spring.txbn id="rcmCrityValLovDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmCrityValLovDAOOraImpl extends BaseJdbcDaoSupportOra implements RcmCrityValLovDAO
{
	public List findList(RcmCrityCommonDTO rcmCrityCommonDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) throws Exception 
    { 
        QueryBuffer query = new QueryBuffer();
       
        query.append("SELECT						");
        query.append("		 ''	 AS seqNo			");
        query.append("		 ,'' AS isDelCheck		");
        query.append("       ,comp_no				");
        query.append("       ,crityvalue_id			");
        query.append("       ,critylist_id			");
        query.append("       ,col_name				");
        query.append("       ,row_name				");
        query.append("       ,crityvalue			");
        query.append("       ,critycolor			");
        query.append("       ,is_critical			");
        query.append("       ,remark				");
        query.append("       ,critycol_id			");
        query.append("       ,crityrow_id			");
        query.append("       ,crity_lvl				");
        query.append("       ,row_ord_no			");
        query.append("       ,col_ord_no			");
        query.append("FROM   TACRITYVALUE			");
    	query.append("WHERE  1=1					");
    	query.getAndQuery("comp_no", conditionMap);
        query.getAndQuery("critylist_id", conditionMap);
        if(!"".equals(conditionMap.get("row_name")))
        {
        	query.getAndQuery("row_name", conditionMap);
        }
        
        if(!"".equals(conditionMap.get("col_name")))
        {
        	query.getAndQuery("col_name", conditionMap);
        }
    	query.append(this.getWhere(rcmCrityCommonDTO, user));
        query.append("ORDER BY col_ord_no, row_ord_no");
        
        
        return getJdbcTemplate().queryForList(query.toString());
    } 

	private String getWhere(RcmCrityCommonDTO rcmCrityCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();

        if(!"".equals(rcmCrityCommonDTO.getCrityListId())){
        	query.getAndQuery("critylist_id", rcmCrityCommonDTO.getCrityListId());
        	return query.toString();
        }
        //Criticality 리스트 명
       // query.getLikeQuery("x.description", rcmCrityCommonDTO.getFilterCrityListDesc());

        query.getLikeQuery("col_name", rcmCrityCommonDTO.getFilterColName());
        query.getLikeQuery("row_name", rcmCrityCommonDTO.getFilterRowName());
        
    	return query.toString();
    }

	
}