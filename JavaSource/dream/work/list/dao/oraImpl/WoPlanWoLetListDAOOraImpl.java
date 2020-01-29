package dream.work.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.list.dao.WoPlanWoLetListDAO;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanWoLetListDTO;

/**
 * �۾���ȹ��� �����۾� ��� dao
 * @author  syyang
 * @version $Id$
 * @since   1.0
 * @spring.bean id="woPlanWoLetListDAOTarget"
 * @spring.txbn id="woPlanWoLetListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WoPlanWoLetListDAOOraImpl extends BaseJdbcDaoSupportOra implements WoPlanWoLetListDAO
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
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT													");
        query.append("		'' 								AS isDelCheck		");
        query.append("		,'' 							AS seqNo			");
        query.append("		,x.woletctg_type 				AS woLetCtgType		");
        query.append("		,SFACODE_TO_DESC(x.woletctg_type,'WOLETCTG_TYPE','SYS','','ko')		AS woLetCtgTypeDesc	");
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
        query.append("FROM TAWOWOLETLIST x 										");
        query.append("WHERE  1 = 1												");
        query.append(this.getWhere(woPlanCommonDTO,woPlanWoLetListDTO,loginUser));
        query.getOrderByQuery("x.wowoletlist_id", woPlanWoLetListDTO.getOrderBy(), woPlanWoLetListDTO.getDirection());
        
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
    	QueryBuffer query = new QueryBuffer();

    	String woWoLetListId = id;
    	
    	query.append("DELETE FROM TAWOWOLETLIST						");
    	query.append("WHERE  comp_no 		= '"+compNo+"'			");
    	query.append(" AND  wowoletlist_id	= '"+woWoLetListId+"'	");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(WoPlanCommonDTO woPlanCommonDTO, WoPlanWoLetListDTO woPlanWoLetListDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	// ȸ��
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	// �۾� ID
    	query.getAndQuery("x.wkor_id", woPlanCommonDTO.getWkOrId());
    	// wo�����۾����� ID
    	query.getAndQuery("x.wowoletlist_id", woPlanWoLetListDTO.getWoWoLetListId());
    	
    	return query.toString();
    }

	@Override
	public String findTotalCount(WoPlanCommonDTO woPlanCommonDTO, WoPlanWoLetListDTO woPlanWoLetListDTO, User loginUser) throws Exception
	{
		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT						");
        query.append("       COUNT(1)               ");
        query.append("FROM TAWOWOLETLIST x 			");
        query.append("WHERE  1 = 1					");
        query.append(this.getWhere(woPlanCommonDTO,woPlanWoLetListDTO,loginUser));
	
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);

	}
}