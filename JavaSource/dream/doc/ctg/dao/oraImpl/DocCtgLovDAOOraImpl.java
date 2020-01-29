package dream.doc.ctg.dao.oraImpl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.doc.ctg.dao.DocCtgLovDAO;
import dream.doc.ctg.dto.DocCtgLovDTO;

/**
 * 문서분류체계 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="docCtgLovDAOTarget"
 * @spring.txbn id="docCtgLovDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class DocCtgLovDAOOraImpl extends BaseJdbcDaoSupportOra implements DocCtgLovDAO
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
    public List findList(DocCtgLovDTO docCtgLovDTO,Map<String,String> columnMap, Map<String,String> conditionMap , User user)
    {
        QueryBuffer query = new QueryBuffer();
        Map cols = new LinkedHashMap<String,String>();
        
        query.append("SELECT a.*, LEVEL LVL, MIN(LEVEL) OVER() MINLVL FROM (                		");
        query.append("SELECT                                                                		");
        query.append("       docctg_id,                                                      		");
        query.append("       p_docctg_id,                                                      		");
        query.append("       description,                                                      		");
        query.append("       docctg_no,                                                      		");
        query.append("       docctg_id AS ID                                                        ");
        query.append("        ,ROWNUM  seqNo                          								");
        query.append("        ,LEVEL                                                        		");
        query.append("        ,ord_no ordNo            												");
        query.append("FROM  TADOCCTG x																");
        query.append("WHERE 1 = 1																	");
        query.getAndQuery("comp_no", conditionMap);
        query.getAndQuery("is_use", conditionMap);
        query.getAndQuery("p_docctg_id", conditionMap);
        if(!"".equals(docCtgLovDTO.getDocctgId()))
        	query.getLikeQuery("x.docctg_id","-"+docCtgLovDTO.getDocctgId());
        query.append(this.getWhere(docCtgLovDTO, user));
        if(!"".equals(docCtgLovDTO.getPdocctgId()))
        {
        	query.append(" START WITH p_docctg_id = '"+docCtgLovDTO.getPdocctgId()+"'");
        }
//        else if(!"".equals(docCtgLovDTO.getPdocctgDesc()))
//        {
//        	query.append(" START WITH p_docctg_id IN (SELECT a.docctg_id FROM  TADOCCTG a WHERE a.docctg_id = x.p_docctg_id AND a.description like '%"+docCtgCommonDTO.getpDocctgDesc()+"%' )  ");
//        }
        else query.append(" START WITH p_docctg_id = '0'                                     		");
        query.append(" CONNECT BY PRIOR docctg_id = p_docctg_id                           			");
        query.append(" ORDER SIBLINGS BY ord_no) a        											");
        
        query.append("START WITH p_docctg_id = '0'      											");
        query.append(" CONNECT BY PRIOR docctg_id = p_docctg_id             						");
        query.append(" ORDER BY seqNo       														");

        
        return getJdbcTemplate().queryForList(query.toString());
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
    private String getWhere(DocCtgLovDTO docCtgLovDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();

        query.getAndQuery("x.comp_no", user.getCompNo());

        query.getLikeQuery("description", docCtgLovDTO.getDescription());

        return query.toString();
    }
}