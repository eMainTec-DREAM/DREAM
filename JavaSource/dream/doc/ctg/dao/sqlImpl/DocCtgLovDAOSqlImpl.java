package dream.doc.ctg.dao.sqlImpl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
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
public class DocCtgLovDAOSqlImpl extends BaseJdbcDaoSupportSql implements DocCtgLovDAO
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
        QuerySqlBuffer query = new QuerySqlBuffer();
        Map cols = new LinkedHashMap<String,String>();
        
        query.append("SELECT                                                                               ");
        query.append("        x.docctg_id              AS DOCCTG_ID                                        ");
        query.append("       ,x.p_docctg_id            AS P_DOCCTG_ID                                      ");
        query.append("       ,x.description            AS DESCRIPTION                                      ");
        query.append("       ,x.docctg_no              AS DOCCTG_NO                                        ");
        query.append("       ,x.docctg_id              AS ID                                               ");
        query.append("       ,MIN(y.LVL) OVER( ORDER BY ISNULL(x.ord_no, '99999999')) AS MINLVL            ");
        query.append("       ,y.LVL                    AS LVL                                              ");
        query.append("       ,x.ord_no                 AS ORDNO                                            ");
        query.append("FROM   TADOCCTG    x                                                ");
        query.append("         ,(SELECT * FROM dbo.SFADOCCTG_ALL('"+user.getCompNo()+"','0')) y     ");
        query.append("WHERE 1=1                                                           ");
        query.append("AND     x.docctg_id = y.docctg_id                                   ");
        query.append("AND     x.comp_no = y.comp_No                                       ");
        query.getAndQuery("x.comp_no", conditionMap);
        query.getAndQuery("x.is_use", conditionMap);
        query.getAndQuery("x.p_docctg_id", conditionMap);
        if(!"".equals(docCtgLovDTO.getDocctgId()))
        	query.getLikeQuery("x.docctg_id","-"+docCtgLovDTO.getDocctgId());
        query.append(this.getWhere(docCtgLovDTO, user));
        query.append("ORDER BY y.LVL, ISNULL(x.ord_no, '99999999')                        ");
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
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.getAndQuery("x.comp_no", user.getCompNo());
        
        query.getLikeQuery("description", docCtgLovDTO.getDescription());

        return query.toString();
    }
}