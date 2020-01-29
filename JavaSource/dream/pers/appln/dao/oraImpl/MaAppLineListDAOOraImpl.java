package dream.pers.appln.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.pers.appln.dao.MaAppLineListDAO;
import dream.pers.appln.dto.MaAppLineCommonDTO;

/**
 * dao
 * @author  kim21017
 * @version $Id: MaAppLineListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maAppLineListDAOTarget"
 * @spring.txbn id="maAppLineListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaAppLineListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaAppLineListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaAppLineListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineCommonDTO
     * @return List
     */
    public List findQnaList(MaAppLineCommonDTO maAppLineCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                        ");
        query.append("       '' AS seqNo,                                           ");
        query.append("       '' AS isDelCheck,                                      ");
        query.append("       title,                                                 ");
        query.append("       remark,                                                ");
        query.append("       apprline_id apprlineId                                 ");
        query.append("FROM  TAAPPRLINE                                              ");
        query.append("WHERE 1 = 1                                                   ");
        query.append(this.getWhere(maAppLineCommonDTO, user));
        //query.append("ORDER by apprline_id desc       						        ");
        query.getOrderByQuery("apprline_id desc", maAppLineCommonDTO.getOrderBy(), maAppLineCommonDTO.getDirection());


        return getJdbcTemplate().queryForList(query.toString(maAppLineCommonDTO.getIsLoadMaxCount(), maAppLineCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter Á¶°Ç
     * @author  kim21017
     * @version $Id: MaAppLineListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaAppLineCommonDTO maAppLineCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        query.getAndQuery("comp_no", user.getCompNo());
        if (!"".equals(maAppLineCommonDTO.getApprlineId()))
        {
            query.getAndQuery("apprline_id", maAppLineCommonDTO.getApprlineId());
            return query.toString();
        }
        
        query.getLikeQuery("title", maAppLineCommonDTO.getTitle());
        query.getAndQuery("emp_id", maAppLineCommonDTO.getEnterBy());

        return query.toString();
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaAppLineListDAO.java,v 1.0 2015   5/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteQna(String id, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	String qnaId = id;
    	
    	query.append("DELETE FROM TAAPPRLINE			");
    	query.append("WHERE apprline_id = '"+qnaId+"'	");
    	query.append("AND comp_no = '"+user.getCompNo()+"'  ");

    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAAPPRLINEUSR				");
    	query.append("WHERE apprline_id = '"+qnaId+"'	");
    	query.append("AND comp_no = '"+user.getCompNo()+"'  ");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	return rtnValue;
    }

    public int insertLine(MaAppLineCommonDTO maAppLineCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        query.append("INSERT INTO TAAPPRUSR                                     ");
        query.append("  (comp_no,       apprusr_id,    apprlist_id,             ");
        query.append("   appr_seq,      appr_by,    apprusr_action,             ");
        query.append("   apprusr_status, apprusr_type                           ");
        query.append("  )  SELECT                                               ");
        query.append("   comp_no,      SQAAPPRUSR_ID.NEXTVAL, ?,                ");
        query.append("   appr_seq,appr_by,    ?,              ?                 ");
        query.append("   ,apprusr_type                                          ");
        query.append("   FROM   TAAPPRLINEUSR                                   ");
        query.append("   WHERE apprline_id =  ?                                 ");
        query.append("     AND comp_no    =  ?                                 	");
        
        Object[] objects = new Object[] {
        		maAppLineCommonDTO.getApprlistId()
                ,"Z"
                ,"Z"
                ,maAppLineCommonDTO.getApprlineId()
                ,user.getCompNo()
        };
        return getJdbcTemplate().update(query.toString(), objects);
    }

    @Override
    public int deleteLine(MaAppLineCommonDTO maAppLineCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TAAPPRUSR      ");
        query.append("WHERE apprlist_id = ?      ");
        query.append("AND comp_no = ?       	 ");

        Object[] objects = new Object[] {
                maAppLineCommonDTO.getApprlistId(),
                user.getCompNo()
        };
        return getJdbcTemplate().update(query.toString(), objects);
    }
    public int mergeAppList(MaAppLineCommonDTO maAppLineCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("MERGE INTO TAAPPRLIST a																			");
    	query.append("USING(	SELECT 	? compNo,																		");
    	query.append("					? apprListId,																	");
    	query.append("					? apprType,																		");
    	query.append("					? objectId																		");
    	query.append("			FROM DUAL) b																			");
    	query.append("ON(	a.comp_no = b.compNo																		");
    	query.append("	AND a.apprlist_id = b.apprListId	)															");
    	query.append("WHEN MATCHED THEN																					");
    	query.append("	UPDATE SET 	a.appr_type = b.apprType,															");
    	query.append("				a.object_id = b.objectId															");
    	query.append("WHEN NOT MATCHED THEN																				");
    	query.append("	INSERT (a.comp_no,	a.apprlist_id,		a.appr_type,	a.object_id					)			");
    	query.append("	VALUES (b.compNo,	b.apprListId,		b.apprType,		b.objectId					)			");
    	
    	
    	Object[] objects = new Object[] {
    			user.getCompNo()
    			,maAppLineCommonDTO.getApprlistId()
    			,maAppLineCommonDTO.getApprType()
    			,maAppLineCommonDTO.getObjectId()
    	};
        return getJdbcTemplate().update(query.toString(), objects);
    }
    
    @Override
    public String findTotalCount(MaAppLineCommonDTO maAppLineCommonDTO, User user) 
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT               	 	");
        query.append("       COUNT(1)       	");
        query.append("FROM  TAAPPRLINE        	");
        query.append("WHERE 1 = 1              	");
        query.append(this.getWhere(maAppLineCommonDTO, user));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
    }
}