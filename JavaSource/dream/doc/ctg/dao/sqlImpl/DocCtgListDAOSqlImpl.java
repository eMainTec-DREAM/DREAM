package dream.doc.ctg.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.doc.ctg.dao.DocCtgListDAO;
import dream.doc.ctg.dto.DocCtgCommonDTO;


/**
 * 문서분류체계 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="docCtgListDAOTarget"
 * @spring.txbn id="docCtgListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class DocCtgListDAOSqlImpl extends BaseJdbcDaoSupportSql implements DocCtgListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param docCtgCommonDTO
     * @return List
     */
    public List findList(DocCtgCommonDTO docCtgCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String pDocctgId = !"".equals(docCtgCommonDTO.getpDocctgId())?docCtgCommonDTO.getpDocctgId():"0";
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  				");
        query.append("SELECT                                                               				");
        query.append("      DISTINCT(x.docctg_id) 			AS ID,                                      ");
        query.append("        ''                            SEQNO,                          			");
        query.append("        '' 							ISDELCHECK,									");
        query.append("        x.DESCRIPTION,                                                            ");
        query.append("        x.docctg_no 					DOCCTGNO,                                   ");
        query.append("        x.is_use 						ISUSE,                                      ");
        query.append("        x.ord_no 						ORDNO,                                      ");
        query.append("        x.docctg_id 					DOCCTGID,                                   ");
        query.append("        x.p_docctg_id 				PDOCCTGID                                   ");
        query.append("FROM    TADOCCTG x                                                     			");
        query.append("WHERE 1=1                                                         				");
        query.append(this.getWhere(docCtgCommonDTO, user));
        query.getOrderByQuery("x.docctg_id", "x.ord_no", docCtgCommonDTO.getOrderBy(), docCtgCommonDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(docCtgCommonDTO.getIsLoadMaxCount(), docCtgCommonDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteList(String id, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query = new QuerySqlBuffer();
    	
    	query.append("DELETE FROM TADOCCTG                   	");
        query.append("WHERE  docctg_id     = '"+id+"' 	    	");
        query.append(" AND   comp_no       = '"+user.getCompNo()+"'       ");
        
        rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	return rtnValue;
    }
    
    /**
     * Filter 조건
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param docCtgCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(DocCtgCommonDTO docCtgCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
      
        query.getAndQuery("x.comp_no", user.getCompNo());
        if(!"".equals(docCtgCommonDTO.getDocctgId()))
        {
            query.getAndQuery("x.docctg_Id", docCtgCommonDTO.getDocctgId());
            return query.toString();
        }
        
        query.getLikeQuery("x.description", docCtgCommonDTO.getDescription());
        
        //상위분류명
        query.getCodeLikeQuery("x.p_docctg_id", "(select a.description from TADOCCTG a where a.docctg_id = x.p_docctg_id)", docCtgCommonDTO.getpDocctgId(), docCtgCommonDTO.getpDocctgDesc());

        return query.toString();
    }

	public String findTotalCount(DocCtgCommonDTO docCtgCommonDTO, User user) 
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT                    ");
        query.append("       COUNT(1)           ");
        query.append("FROM   TADOCCTG x      	");
        query.append("WHERE  1=1                ");
        query.append(this.getWhere(docCtgCommonDTO,user));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
	}
}