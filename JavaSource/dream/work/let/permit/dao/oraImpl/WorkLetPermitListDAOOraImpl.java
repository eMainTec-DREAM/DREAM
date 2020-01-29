package dream.work.let.permit.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.let.dto.WorkLetCommonDTO;
import dream.work.let.permit.dao.WorkLetPermitListDAO;
import dream.work.let.permit.dto.WorkLetPermitListDTO;

/**
 * 안전작업 - 안전작업허가서 목록 dao
 * @author  syyang
 * @version $Id$
 * @since   1.0
 * @spring.bean id="workLetPermitListDAOTarget"
 * @spring.txbn id="workLetPermitListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkLetPermitListDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkLetPermitListDAO
{
    /**
     * grid find
     * @author  syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param workLetCommonDTO
     * @param workLetPermitListDTO
     * @param loginUser
     * @return List
     */
    public List findWoLetPermitList(WorkLetCommonDTO workLetCommonDTO, WorkLetPermitListDTO workLetPermitListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        String lang = loginUser.getLangId();
        
        query.append("SELECT													");
        query.append("		'' 								AS isDelCheck		");
        query.append("		,'' 							AS seqNo			");
        query.append("		,x.woletlist_no 				AS woLetListNo		");
        query.append("		,													");
        query.getDate("x.wolet_date", "woLetDate");
        query.append("		,x.woletctg_type 				AS woLetCtgType		");
        query.append("		,SFACODE_TO_DESC(x.woletctg_type,'WOLETCTG_TYPE','SYS','','"+lang+"')		AS woLetCtgTypeDesc		");
        query.append("		,x.woletlist_status 			AS woLetListStatus	");
        query.append("		,SFACODE_TO_DESC(x.woletlist_status,'WOLETLIST_STATUS','SYS','','"+lang+"')	AS woLetListStatusDesc	");
        query.append("		,x.place		 				AS place			");
        query.append("		,(to_char(to_date(x.start_date,'YYYYMMDD'),'YYYY-MM-DD')		");
        query.append("        ||' ~ '|| to_char(to_date(x.end_date,'YYYYMMDD'),'YYYY-MM-DD')	");
        query.append("       )    							AS woDate   		");
        query.append("		,x.remark 						AS remark			");
        query.append("		,x.woletlist_id 				AS woLetListId		");
        query.append("		,x.wolet_id 					AS woLetId			");
        query.append("FROM TAWOLETLIST x 										");
        query.append("WHERE  1 = 1												");
        query.append(this.getWhere(workLetCommonDTO, workLetPermitListDTO,loginUser));
        query.getOrderByQuery("x.woletlist_no", workLetPermitListDTO.getOrderBy(), workLetPermitListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workLetPermitListDTO.getIsLoadMaxCount(), workLetPermitListDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteWoLetList(String id, String compNo)
    {
    	QueryBuffer query = new QueryBuffer();

    	String woLetListId = id;
    	
    	query.append("DELETE FROM TAWOLETLIST						");
    	query.append("WHERE  comp_no 		= '"+compNo+"'			");
    	query.append(" AND  woletlist_id	= '"+woLetListId+"'		");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(WorkLetCommonDTO workLetCommonDTO, WorkLetPermitListDTO workLetPermitListDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	// 회사
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	// 안전작업허가서 ID
    	query.getAndQuery("x.wolet_id", workLetCommonDTO.getWoLetId());
    	// 안전작업허가서 유형 ID
    	if (!"".equals(workLetPermitListDTO.getWoLetListId()))
        {
            query.getAndQuery("x.woletlist_id", workLetPermitListDTO.getWoLetListId());
            return query.toString();
        }
    	
    	return query.toString();
    }

	@Override
	public String findTotalCount(WorkLetCommonDTO workLetCommonDTO, WorkLetPermitListDTO workLetPermitListDTO, User loginUser) throws Exception
	{
		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT						");
        query.append("       COUNT(1)               ");
        query.append("FROM TAWOLETLIST x 			");
        query.append("WHERE  1 = 1					");
        query.append(this.getWhere(workLetCommonDTO, workLetPermitListDTO,loginUser));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);

	}
}