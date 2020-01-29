package dream.pers.appln.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
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
public class MaAppLineListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaAppLineListDAO
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
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;             ");
        query.append("SELECT                                                        ");
        query.append("       '' AS seqNo,                                           ");
        query.append("       '' AS isDelCheck,                                      ");
        query.append("       title,                                                 ");
        query.append("       remark,                                                ");
        query.append("       apprline_id apprlineId                                 ");
        query.append("FROM  TAAPPRLINE                                              ");
        query.append("WHERE 1 = 1                                                   ");
        query.append(this.getWhere(maAppLineCommonDTO, user));
        query.getOrderByQuery("apprline_id","apprline_id desc", maAppLineCommonDTO.getOrderBy(), maAppLineCommonDTO.getDirection());
        
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
        QuerySqlBuffer query = new QuerySqlBuffer();
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
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	String qnaId = id;
    	
    	query.append("DELETE FROM TAAPPRLINE			");
    	query.append("WHERE apprline_id = '"+qnaId+"'	");
    	query.append("AND comp_no = '"+user.getCompNo()+"'  ");

    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	query = new QuerySqlBuffer();
    	
    	query.append("DELETE FROM TAAPPRLINEUSR				");
    	query.append("WHERE apprline_id = '"+qnaId+"'	");
    	query.append("AND comp_no = '"+user.getCompNo()+"'  ");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	return rtnValue;
    }

    public int insertLine(MaAppLineCommonDTO maAppLineCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();


        query.append("INSERT INTO TAAPPRUSR                                     ");
        query.append("  (comp_no,       apprusr_id,    apprlist_id,             ");
        query.append("   appr_seq,      appr_by,    apprusr_action,             ");
        query.append("   apprusr_status, apprusr_type                           ");
        query.append("  )  SELECT                                               ");
        query.append("   comp_no,      NEXT VALUE FOR SQAAPPRUSR_ID, ?,         ");
        query.append("   appr_seq,appr_by,?,?                                   ");
        query.append("   ,apprusr_type                                          ");
        query.append("   FROM TAAPPRLINEUSR                                     ");
        query.append("   WHERE apprline_id =  ?                                 ");
        query.append("     AND comp_no    =  ?  								");

        Object[] objects = new Object[] {
        		maAppLineCommonDTO.getApprlistId()
                ,"Z"
                ,"Z"
                ,maAppLineCommonDTO.getApprlineId()
                ,user.getCompNo()
        };
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    @Override
    public int deleteLine(MaAppLineCommonDTO maAppLineCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("DELETE FROM TAAPPRUSR     ");
        query.append("WHERE apprlist_id = ?      ");
        query.append("AND comp_no = ?        ");

        Object[] objects = new Object[] {
                maAppLineCommonDTO.getApprlistId(),
                user.getCompNo()
        };
        return getJdbcTemplate().update(query.toString(), objects);
    }
    public int mergeAppList(MaAppLineCommonDTO maAppLineCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("DECLARE @t1 TABLE( 																		");
    	query.append("	comp_no NVARCHAR(1000), 																");
    	query.append("	apprlist_id NVARCHAR(1000), 															");
    	query.append("	appr_type NVARCHAR(1000), 																");
    	query.append("	object_id NVARCHAR(1000) 																");
    	query.append(") 																						");
    	query.append("INSERT INTO @t1 VALUES (?,?,?,?	)																");
    	query.append("IF EXISTS( 																				");
    	query.append("	SELECT 1 																				");
    	query.append("	FROM TAAPPRLIST A, @t1 b 																");
    	query.append("	WHERE A.comp_no = b.comp_no 															");
    	query.append("	AND A.apprlist_id = b.apprlist_id 														");
    	query.append(") 																						");
    	query.append("BEGIN  																					");
    	query.append("	UPDATE TAAPPRLIST SET																	");
    	query.append("		comp_no = b.comp_no																	");
    	query.append("	FROM TAAPPRLIST A JOIN @t1 b															");
    	query.append("	ON A.comp_no = b.comp_no 																");
    	query.append("	AND A.apprlist_id = b.apprlist_id 														");
    	query.append(" END 																						");
    	query.append("ELSE 																						");
    	query.append("BEGIN 																					");
    	query.append("  INSERT INTO TAAPPRLIST 																	");
    	query.append("  (comp_no,	apprlist_id,	appr_type,	object_id	)									");
    	query.append("SELECT 																					");
    	query.append("	b.comp_no,b.apprlist_id,b.appr_type,b.object_id											");
    	query.append("FROM 	@t1 b 																				");
    	query.append("	END 																					");
    	
    	
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
    	QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;    	");
    	query.append("SELECT               	 								");
        query.append("       COUNT(1)       								");
        query.append("FROM  TAAPPRLINE        								");
        query.append("WHERE 1 = 1              								");
        query.append(this.getWhere(maAppLineCommonDTO, user));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
    	return QuerySqlBuffer.listToString(resultList);
    }
}