package dream.rcm.pmtask.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.rcm.pmtask.dao.RcmPmtaskCndtListDAO;
import dream.rcm.pmtask.dto.RcmPmtaskCndtListDTO;
import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;

/**
 * ¸ñ·Ï dao
 * @author  kim21017
 * @version $Id: RcmPmtaskCndtListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="rcmPmtaskCndtListDAOTarget"
 * @spring.txbn id="rcmPmtaskCndtListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmPmtaskCndtListDAOOraImpl extends BaseJdbcDaoSupportOra implements RcmPmtaskCndtListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: RcmPmtaskCndtListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskCommonDTO
     * @param rcmPmtaskCndtListDTO
     * @return List
     */
    public List findList(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, RcmPmtaskCndtListDTO rcmPmtaskCndtListDTO)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = rcmPmtaskCommonDTO.getCompNo();
        
        query.append("SELECT											");
        query.append("       '' seqNo									");
        query.append("       ,'' isDelCheck								");
        query.append("       ,taskcndt									");
        query.append("       ,taskefinfo								");
        query.append("       ,remark									");
        query.append("       ,comp_no compNo							");
        query.append("       ,rcmpmtaskcndt_id rcmpmtaskcndtId			");
        query.append("       ,rcmpmtask_id rcmpmtaskId					");
        query.append("       ,rcmlist_id rcmlistId						");
        query.append("FROM TARCMPMTASKCNDT								");
        query.append("WHERE  comp_no = '"+compNo+"'						");
        query.append(this.getWhere(rcmPmtaskCommonDTO,rcmPmtaskCndtListDTO));
        query.getOrderByQuery("rcmpmtaskcndt_id","rcmpmtaskcndt_id", rcmPmtaskCommonDTO.getOrderBy(), rcmPmtaskCommonDTO.getDirection());
    	
        return getJdbcTemplate().queryForList(query.toString(rcmPmtaskCommonDTO.getIsLoadMaxCount(), rcmPmtaskCommonDTO.getFirstRow()));

    }
    
    private String getWhere(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, RcmPmtaskCndtListDTO rcmPmtaskCndtListDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	if (!"".equals(rcmPmtaskCndtListDTO.getRcmpmtaskcndtId()))
        {
            query.getAndQuery("rcmpmtaskcndt_id", rcmPmtaskCndtListDTO.getRcmpmtaskcndtId());
            return query.toString();
        }
    	query.getAndQuery("rcmpmtask_id", rcmPmtaskCommonDTO.getRcmpmtaskId());
    	
    	return query.toString();
    }


	public int deleteList(String key) 
	{
		QueryBuffer query = new QueryBuffer();
    	
    	query.append("DELETE FROM TARCMPMTASKCNDT			");
    	query.append("WHERE rcmpmtaskcndt_id 	= '"+key+"' ");
    	
    	return this.getJdbcTemplate().update(query.toString());
	}

	@Override
	public String findTotalCount(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, RcmPmtaskCndtListDTO rcmPmtaskCndtListDTO,
			User user) {
		QueryBuffer query = new QueryBuffer();
        String compNo = rcmPmtaskCommonDTO.getCompNo();
        
        query.append("SELECT											");
        query.append("  COUNT(1)										");
        query.append("FROM TARCMPMTASKCNDT								");
        query.append("WHERE  comp_no = '"+compNo+"'						");
        query.append(this.getWhere(rcmPmtaskCommonDTO,rcmPmtaskCndtListDTO));
    	
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}

}