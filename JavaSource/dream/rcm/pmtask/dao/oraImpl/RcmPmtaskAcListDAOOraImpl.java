package dream.rcm.pmtask.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.rcm.pmtask.dao.RcmPmtaskAcListDAO;
import dream.rcm.pmtask.dto.RcmPmtaskAcListDTO;

/**
 * LOV - List DAO implements
 * @author kim21017
 * @version $Id: RcmPmtaskAcListDAOOraImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmPmtaskAcListDAOTarget"
 * @spring.txbn id="rcmPmtaskAcListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmPmtaskAcListDAOOraImpl extends BaseJdbcDaoSupportOra implements RcmPmtaskAcListDAO
{
	public List findList(RcmPmtaskAcListDTO rcmPmtaskAcListDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) throws Exception
    { 
        QueryBuffer query = new QueryBuffer();
       
        query.append("SELECT																			");
        query.append("    x.description                         ");
        query.append("  , x.rcmlist_id                          ");
        query.append("  , x.rcmlist_no                          ");
        query.append("FROM TARCMLIST x							");
    	query.append("WHERE  1=1								");
    	query.append(this.getWhere(rcmPmtaskAcListDTO, user, columnMap, conditionMap));
        query.getOrderByQuery("x.description", rcmPmtaskAcListDTO.getOrderBy(), rcmPmtaskAcListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(rcmPmtaskAcListDTO.getIsLoadMaxCount(), rcmPmtaskAcListDTO.getFirstRow()));
    } 

	private String getWhere(RcmPmtaskAcListDTO rcmPmtaskAcListDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap)
    {        
        QueryBuffer query = new QueryBuffer();
        
        // system ºÐ¼®¸í
        query.getLikeQuery("x.description", rcmPmtaskAcListDTO.getRcmlistDesc());

    	return query.toString();
    }

    public String findTotalCount(RcmPmtaskAcListDTO rcmPmtaskAcListDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT						");
        query.append("		COUNT(1)				");
        query.append("FROM TARCMLIST x			    ");
    	query.append("WHERE  1=1					");
    	query.append(this.getWhere(rcmPmtaskAcListDTO, user, columnMap, conditionMap));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}