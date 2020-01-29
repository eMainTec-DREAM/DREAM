package dream.rcm.crity.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.rcm.crity.dao.RcmCrityColListDAO;
import dream.rcm.crity.dto.RcmCrityColListDTO;
import dream.rcm.crity.dto.RcmCrityCommonDTO;

/**
 * Criticality Matrix Col Page - List DAO implements
 * @author kim21017
 * @version $Id: RcmCrityColListDAOOraImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmCrityColListDAOTarget"
 * @spring.txbn id="rcmCrityColListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmCrityColListDAOOraImpl extends BaseJdbcDaoSupportOra implements RcmCrityColListDAO
{
	public List findList(RcmCrityCommonDTO rcmCrityCommonDTO, RcmCrityColListDTO rcmCrityColListDTO, User user) throws Exception
    { 
        QueryBuffer query = new QueryBuffer();
       
        query.append("SELECT													");
        query.append("		''								AS seqNo			");
        query.append("		,''								AS isDelCheck		");
        query.append("		,x.critylist_id					AS crityListId		");
        query.append("		,x.critycol_id					AS crityColId		");
        query.append("		,x.col_name						AS crityColDesc		");
        query.append("		,x.ord_no						AS ordNo			");
        query.append("		,x.remark						AS remark			");
        query.append("FROM TACRITYCOL x											");
    	query.append("WHERE  1=1												");
    	query.getStringEqualQuery("x.comp_no", user.getCompNo());
    	query.getAndNumKeyQuery("x.critylist_id", rcmCrityCommonDTO.getCrityListId());
        
    	if (!"".equals(rcmCrityColListDTO.getCrityColId()))
        {
            query.getAndQuery("x.critycol_id", rcmCrityColListDTO.getCrityColId());
        }
    	
        query.getOrderByQuery("x.critylist_id","x.ord_no", rcmCrityCommonDTO.getOrderBy(), rcmCrityCommonDTO.getDirection());
    	
        return getJdbcTemplate().queryForList(query.toString(rcmCrityCommonDTO.getIsLoadMaxCount(), rcmCrityCommonDTO.getFirstRow()));
 } 
    public int deleteList(String id, User user) throws Exception
    {
    	QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TACRITYCOL		");
        query.append("WHERE  comp_no 		= ?		");
        query.append("  AND  critycol_id  	= ?		");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public int deleteValList(String id, User user) throws Exception
    {
    	QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TACRITYVALUE		");
        query.append("WHERE  comp_no 		= ?		");
        query.append("  AND  critycol_id  	= ?		");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
	@Override
	public String findTotalCount(RcmCrityCommonDTO rcmCrityCommonDTO, RcmCrityColListDTO rcmCrityColListDTO,
			User user) {
        
		QueryBuffer query = new QueryBuffer();
        String compNo = rcmCrityCommonDTO.getCompNo();
        
        query.append("SELECT											");
        query.append("  COUNT(1)										");
        query.append("FROM TACRITYCOL x											");
    	query.append("WHERE  1=1												");
    	query.getStringEqualQuery("x.comp_no", user.getCompNo());
    	query.getAndNumKeyQuery("x.critylist_id", rcmCrityCommonDTO.getCrityListId());
    	
    	if (!"".equals(rcmCrityColListDTO.getCrityColId()))
        {
            query.getAndQuery("x.critycol_id", rcmCrityColListDTO.getCrityColId());
        }
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
   
	}
}