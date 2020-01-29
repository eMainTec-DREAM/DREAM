package dream.work.let.categ.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.work.let.categ.dao.WorkLetCategEtcListDAO;
import dream.work.let.categ.dto.WorkLetCategCommonDTO;
import dream.work.let.categ.dto.WorkLetCategEtcListDTO;

/**
 * 안전작업유형 보완사항 list page - List DAO implements
 * @author euna0207
 * @version $Id: WorkLetCategEtcListDAOSqlImpl.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workLetCategEtcListDAOTarget"
 * @spring.txbn id="workLetCategEtcListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkLetCategEtcListDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkLetCategEtcListDAO
{
	public List findList(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategEtcListDTO workLetCategEtcListDTO, User user) throws Exception
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SELECT 																													");
        query.append(" ''                      																				 AS isDelCheck		");
        query.append("  ,''                     																			 AS seqNo			");
        query.append("  , comp_no 																							 AS compNo			");
        query.append("  , woletctgetc_id 																					 AS woLetCtgEtcId	");
        query.append("  , description     																					 AS description		");
        query.append("  , ord_no           																					 AS ordNo			");
        query.append("  , dbo.SFACODE_TO_DESC(a.is_use, 'IS_USE', 'SYS', '', 'ko')        									 AS isUse			");
        query.append("  , remark        																					 AS remark			");
        query.append("  , (SELECT description FROM TAWOLETCTG WHERE comp_no = a.comp_no AND woletctg_id = a.woletctg_id)     AS woLetCtgId      ");
        query.append("FROM TAWOLETCTGETC a																										");
        query.append("WHERE  1=1																												");
    	query.append(this.getWhere(workLetCategCommonDTO, workLetCategEtcListDTO, user));
        query.getOrderByQuery("a.woletctgetc_id", "a.woletctgetc_id", workLetCategEtcListDTO.getOrderBy(), workLetCategEtcListDTO.getDirection());
        
    	return getJdbcTemplate().queryForList(query.toString(workLetCategEtcListDTO.getIsLoadMaxCount(), workLetCategEtcListDTO.getFirstRow()));
    } 

	private String getWhere(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategEtcListDTO workLetCategEtcListDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getStringEqualQuery("a.comp_no", user.getCompNo());
        query.getAndNumKeyQuery("a.woletctg_id", workLetCategCommonDTO.getWoLetCtgId());
        if(!"".equals(workLetCategEtcListDTO.getWoLetCtgEtcId())){
        	query.getAndQuery("a.woletctgetc_id", workLetCategEtcListDTO.getWoLetCtgEtcId());
        	return query.toString();
        }
    	return query.toString();
    }

    public int deleteList(String id, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
    	query.append("DELETE FROM TAWOLETCTGETC			");
        query.append("WHERE  comp_no 			= ?		");
        query.append("  AND  woletctgetc_id  	= ?		");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
                };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    public String findTotalCount(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategEtcListDTO workLetCategEtcListDTO, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
    	query.append("SELECT                               ");
        query.append("       COUNT(1)                      ");
        query.append("FROM TAWOLETCTGETC a				   ");
    	query.append("WHERE  1=1						   ");
    	query.append(this.getWhere(workLetCategCommonDTO, workLetCategEtcListDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}