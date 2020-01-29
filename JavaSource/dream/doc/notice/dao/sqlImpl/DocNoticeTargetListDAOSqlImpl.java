package dream.doc.notice.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.doc.notice.dao.DocNoticeTargetListDAO;
import dream.doc.notice.dto.DocNoticeCommonDTO;
import dream.doc.notice.dto.DocNoticeTargetListDTO;

/**
 * DocNoticeTarget Page - List DAO implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="docNoticeTargetListDAOTarget"
 * @spring.txbn id="docNoticeTargetListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class DocNoticeTargetListDAOSqlImpl  extends BaseJdbcDaoSupportSql implements DocNoticeTargetListDAO
{

    /**
     * grid find
     * @author  youngjoo38
     * @version $Id: docNoticeTargetListDAO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param docNoticeTargetListDTO
     * @return List
     */
    public List findList(DocNoticeCommonDTO docNoticeCommonDTO, DocNoticeTargetListDTO docNoticeTargetListDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                ");
        query.append("    ''                AS SEQNO        ");
        query.append("  , ''                AS ISDELCHECK   ");
        query.append("  , x.notiusr_id 		AS NOTIUSRID	");
        query.append("  , x.notice_id 		AS NOTICEID		");
        query.append("  , (SELECT b.description FROM TADEPT b WHERE b.comp_no = x.comp_no AND b.dept_id = x.dept_id) AS DEPT		");
        query.append("  , (SELECT b.emp_name FROM TAEMP b WHERE b.comp_no = x.comp_no AND b.emp_id = x.emp_id) 		 AS EMP			");
        query.append("  , dbo.SFACODE_TO_DESC(x.read_yn, 'IS_USE', 'SYS', x.comp_no, '"+user.getLangId()+"')  			 AS READYNDESC	");
        query.append("  , x.read_date + ' ' + x.read_time	AS APPRDATE 		");
        query.append("FROM TANOTIUSR x						");
        query.append("WHERE  1=1                            ");
        query.append(this.getWhere(docNoticeCommonDTO,docNoticeTargetListDTO, user));

        query.getOrderByQuery("x.notiusr_id","x.notice_id", docNoticeCommonDTO.getOrderBy(), docNoticeCommonDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(docNoticeCommonDTO.getIsLoadMaxCount(), docNoticeCommonDTO.getFirstRow()));

    }

    private String getWhere(DocNoticeCommonDTO docNoticeCommonDTO, DocNoticeTargetListDTO docNoticeTargetListDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getAndQuery("x.notice_id", docNoticeCommonDTO.getNoticeId());
        
        if (!"".equals(docNoticeTargetListDTO.getNotiUsrId()))
        {
            query.getAndQuery("x.notiusr_id", docNoticeTargetListDTO.getNotiUsrId());
        }
        
        return query.toString();
    }
    
    /**
     * Filter Á¶°Ç
     * @author  youngjoo38
     * @version $Id: docNoticeTargetListDAO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param docNoticeTargetListDTO
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("DELETE FROM TANOTIUSR   ");
        query.append("WHERE notiusr_id = ?    ");
        query.append("  AND comp_no = ?        ");
        
        Object[] objects = new Object[] {   
                 id
                ,user.getCompNo()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public String findTotalCount(DocNoticeCommonDTO docNoticeCommonDTO, 
            DocNoticeTargetListDTO docNoticeTargetListDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                   ");
        query.append("       COUNT(1)          ");
        query.append("FROM   TANOTIUSR x	   ");
        query.append("WHERE  1=1               ");
        query.append(this.getWhere(docNoticeCommonDTO,docNoticeTargetListDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }    
}
