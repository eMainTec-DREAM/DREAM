package dream.doc.ctg.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
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
public class DocCtgListDAOOraImpl extends BaseJdbcDaoSupportOra implements DocCtgListDAO
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
        QueryBuffer query = new QueryBuffer();
        String compNo = user.getCompNo();
        
        query.append("SELECT                                                        	        ");
        query.append("      DISTINCT(docctg_id) AS ID,                                          ");
        query.append("        ''                            seqNo,                         	 	");
        query.append("        '' isDelCheck,                                                    ");
        query.append("        description,                                                      ");
        query.append("        docctg_no docctgNo,                                               ");
        query.append("        is_use isUse,                                                     ");
        query.append("        ord_no ordNo,                                                     ");
        query.append("        docctg_id docctgId,                                               ");
        query.append("        p_docctg_id pDocctgId                                             ");
        query.append("		, full_desc    					fullDesc							");
        query.append("FROM    TADOCCTG x                                                     	");
        query.append("WHERE 1=1                                                         		");
        query.append(this.getWhere(docCtgCommonDTO, user));
        query.getOrderByQuery("x.ord_no", docCtgCommonDTO.getOrderBy(), docCtgCommonDTO.getDirection());

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
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query = new QueryBuffer();
    	
    	query.append("DELETE FROM TADOCCTG			        ");
    	query.append("WHERE  docctg_id       = '"+id+"'		");
    	query.append("  AND  comp_no       = '"+user.getCompNo()+"'	");
    	
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
        QueryBuffer query = new QueryBuffer();
      
        query.getAndQuery("comp_no", user.getCompNo());
        
        if (!"".equals(docCtgCommonDTO.getDocctgId()))
        {
            query.getAndQuery("docctg_id", docCtgCommonDTO.getDocctgId());
            return query.toString();
        }
        query.getLikeQuery("description", docCtgCommonDTO.getDescription());

        //상위분류명
        query.getCodeLikeQuery("x.p_docctg_id", "(select a.description from TADOCCTG a where a.docctg_id = x.p_docctg_id)", docCtgCommonDTO.getpDocctgId(), docCtgCommonDTO.getpDocctgDesc());
        
        return query.toString();
    }

	public String findTotalCount(DocCtgCommonDTO docCtgCommonDTO, User user) {
		
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT                    ");
        query.append("       COUNT(1)           ");
        query.append("FROM   TADOCCTG x      	");
        query.append("WHERE  1=1                ");
        query.append(this.getWhere(docCtgCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}
}