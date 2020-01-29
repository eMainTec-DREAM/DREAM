package dream.work.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.list.dao.WoPlanWoLetListDAO;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanWoLetListDTO;

/**
 * 작업계획목록 안전작업 목록 dao
 * @author  syyang
 * @version $Id$
 * @since   1.0
 * @spring.bean id="woPlanWoLetListDAOTarget"
 * @spring.txbn id="woPlanWoLetListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WoPlanWoLetListDAOSqlImpl extends BaseJdbcDaoSupportSql implements WoPlanWoLetListDAO
{
    /**
     * grid find
     * @author  syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanCommonDTO
     * @param woPlanWoLetListDTO
     * @param loginUser
     * @return List
     */
    public List findWoLetList(WoPlanCommonDTO woPlanCommonDTO, WoPlanWoLetListDTO woPlanWoLetListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT													");
        query.append("		'' 								AS isDelCheck		");
        query.append("		,'' 							AS seqNo			");
        query.append("		,x.woletctg_type 				AS woLetCtgType		");
        query.append("		,dbo.SFACODE_TO_DESC(x.woletctg_type,'WOLETCTG_TYPE','SYS','','ko')		AS woLetCtgTypeDesc	");
        query.append("		,(SELECT y.woletctg_no 								");
        query.append("		  FROM TAWOLETCTG y	 								");
        query.append("		  WHERE	y.comp_no = x.comp_no						");
        query.append(" 		   AND y.woletctg_id = x.woletctg_id				");
        query.append("		 )								AS woletctgNo		");
        query.append("		,(SELECT y.description								");
        query.append("		  FROM TAWOLETCTG y	 								");
        query.append("		  WHERE	y.comp_no = x.comp_no						");
        query.append(" 		   AND y.woletctg_id = x.woletctg_id				");
        query.append("		 )								AS description		");
        query.append("		,x.remark						AS remark			");
        query.append("		,x.wowoletlist_id 				AS woWoLetListId	");
        query.append("		,x.woletctg_id					AS woletctgid		");
        query.append("FROM   TAWOWOLETLIST x 									");
        query.append("WHERE  1 = 1												");
        query.append(this.getWhere(woPlanCommonDTO,woPlanWoLetListDTO,loginUser));
        query.getOrderByQuery("x.wowoletlist_id", "x.wowoletlist_id", woPlanWoLetListDTO.getOrderBy(), woPlanWoLetListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(woPlanWoLetListDTO.getIsLoadMaxCount(), woPlanWoLetListDTO.getFirstRow()));

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
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	String woWoLetListId = id;

    	query.append("DELETE FROM TAWOWOLETLIST						");
    	query.append("WHERE  comp_no 		= '"+compNo+"'			");
    	query.append(" AND  wowoletlist_id	= '"+woWoLetListId+"'	");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(WoPlanCommonDTO woPlanCommonDTO, WoPlanWoLetListDTO woPlanWoLetListDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	// 회사
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	// 작업 ID
    	query.getAndQuery("x.wkor_id", woPlanCommonDTO.getWkOrId());
    	// wo안전작업유형 ID
    	query.getAndQuery("x.wowoletlist_id", woPlanWoLetListDTO.getWoWoLetListId());
    	
    	return query.toString();
    }

	@Override
	public String findTotalCount(WoPlanCommonDTO woPlanCommonDTO, WoPlanWoLetListDTO woPlanWoLetListDTO, User loginUser)
			throws Exception {
		QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT						");
        query.append("       COUNT(1)               ");
        query.append("FROM TAWOWOLETLIST x 			");
        query.append("WHERE 1 = 1					");
        query.append(this.getWhere(woPlanCommonDTO,woPlanWoLetListDTO,loginUser));
	
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);

	}
}