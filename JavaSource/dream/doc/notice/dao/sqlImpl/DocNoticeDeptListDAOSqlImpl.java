package dream.doc.notice.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.doc.notice.dao.DocNoticeDeptListDAO;
import dream.doc.notice.dto.DocNoticeCommonDTO;
import dream.doc.notice.dto.DocNoticeDeptListDTO;

/**
 * DocNoticeDept Page - List DAO implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="docNoticeDeptListDAOTarget"
 * @spring.txbn id="docNoticeDeptListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class DocNoticeDeptListDAOSqlImpl  extends BaseJdbcDaoSupportSql implements DocNoticeDeptListDAO
{

    /**
     * grid find
     * @author  youngjoo38
     * @version $Id: docNoticeDeptListDAO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param docNoticeDeptListDTO
     * @return List
     */
    public List findList(DocNoticeCommonDTO docNoticeCommonDTO, DocNoticeDeptListDTO docNoticeDeptListDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                ");
        query.append("    ''                AS seqNo        ");
        query.append("  , ''                AS isDelCheck   ");
        query.append("  , x.notidept_id 	AS notiDeptId	");
        query.append("  , x.notice_id 		AS noticeId		");
        query.append("  , x.dept_id 		AS deptId		");
        query.append("  , (SELECT a.dept_no FROM TADEPT a WHERE a.comp_no = x.comp_no AND a.dept_id = x.dept_id) AS dept		");
        query.append("  , (SELECT a.description FROM TADEPT a WHERE a.comp_no = x.comp_no AND a.dept_id = x.dept_id) AS deptDesc		");
        query.append("  , x.remark 			AS remark		");
        query.append("FROM TANOTIDEPT x						");
        query.append("WHERE  1=1                            ");
        query.append(this.getWhere(docNoticeCommonDTO, docNoticeDeptListDTO, user));

        query.getOrderByQuery("x.notidept_id","x.notice_id", docNoticeCommonDTO.getOrderBy(), docNoticeCommonDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(docNoticeCommonDTO.getIsLoadMaxCount(), docNoticeCommonDTO.getFirstRow()));

    }

    private String getWhere(DocNoticeCommonDTO docNoticeCommonDTO, DocNoticeDeptListDTO docNoticeDeptListDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getAndQuery("x.notice_id", docNoticeCommonDTO.getNoticeId());
        
        if (!"".equals(docNoticeDeptListDTO.getNotiDeptId()))
        {
            query.getAndQuery("x.notidept_id", docNoticeDeptListDTO.getNotiDeptId());
        }

        return query.toString();
    }
    
    /**
     * Filter Á¶°Ç
     * @author  youngjoo38
     * @version $Id: docNoticeDeptListDAO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param docNoticeDeptListDTO
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("DELETE FROM TANOTIDEPT   ");
        query.append("WHERE notidept_id = ?    ");
        query.append("  AND comp_no = ?        ");
        
        Object[] objects = new Object[] {   
                 id
                ,user.getCompNo()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public String findTotalCount(DocNoticeCommonDTO docNoticeCommonDTO, 
            DocNoticeDeptListDTO docNoticeDeptListDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                   ");
        query.append("       COUNT(1)          ");
        query.append("FROM   TANOTIDEPT x	   ");
        query.append("WHERE  1=1               ");
        query.append(this.getWhere(docNoticeCommonDTO,docNoticeDeptListDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }    
}
