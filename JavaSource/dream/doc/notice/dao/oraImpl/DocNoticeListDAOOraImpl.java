package dream.doc.notice.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.doc.notice.dao.DocNoticeListDAO;
import dream.doc.notice.dto.DocNoticeCommonDTO;

/**
 * DocNotice Page - List DAO implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="docNoticeListDAOTarget"
 * @spring.txbn id="docNoticeListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class DocNoticeListDAOOraImpl  extends BaseJdbcDaoSupportOra implements DocNoticeListDAO
{

    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param docNoticeCommonDTO
     * @return List
     */
    public List findList(DocNoticeCommonDTO docNoticeCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                ");
        query.append("    ''                AS SEQNO        ");
        query.append("  , ''                AS ISDELCHECK   ");
        query.append("  , x.notice_id    	AS NOTICEID		");
        query.append("  , x.notice_no   	AS NOTICENO		");
        query.append("  , x.description 	AS TITLE		");
        query.append("  , x.notice_status   AS NOTICESTATUS         ");
        query.append("  , SFACODE_TO_DESC(x.notice_status, 'NOTICE_STATUS', 'SYS', x.comp_no, '"+user.getLangId()+"') AS NOTICESTATUSDESC");
        query.append("  , x.limit_date    	AS NOTICEPERIOD	");
        query.append("  , x.write_date   	AS NOTICEDATE	");
        query.append("  , (SELECT a.description FROM TADEPT a WHERE a.comp_no = x.comp_no AND a.dept_id = x.write_dept) AS regDept		");
        query.append("  , (SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = x.comp_no AND a.emp_id = x.write_by) AS writeBy		");
        query.append("FROM TANOTICE x						");
        query.append("WHERE 1=1 							");
        query.append(this.getWhere(docNoticeCommonDTO, user));
        query.getOrderByQuery("x.notice_no desc", docNoticeCommonDTO.getOrderBy(), docNoticeCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(docNoticeCommonDTO.getIsLoadMaxCount(), docNoticeCommonDTO.getFirstRow()));
    }
    
    public List findCheckList(DocNoticeCommonDTO docNoticeCommonDTO, User user) throws Exception
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("SELECT                                        ");
    	query.append("    ''                AS SEQNO                ");
    	query.append("  , ''                AS ISDELCHECK           ");
    	query.append("  , x.notice_id    	AS NOTICEID		        ");
    	query.append("  , x.notice_no   	AS NOTICENO		        ");
    	query.append("  , x.description 	AS TITLE		        ");
    	query.append("  , x.notice_status   AS NOTICESTATUS         ");
        query.append("  , SFACODE_TO_DESC(x.notice_status, 'NOTICE_STATUS', 'SYS', x.comp_no, '"+user.getLangId()+"') AS NOTICESTATUSDESC");
    	query.append("  , x.limit_date    	AS NOTICEPERIOD	        ");
    	query.append("  , x.write_date   	AS NOTICEDATE	        ");
    	query.append("  , (SELECT a.description FROM TADEPT a WHERE a.comp_no = x.comp_no AND a.dept_id = x.write_dept) AS regDept		");
    	query.append("  , (SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = x.comp_no AND a.emp_id = x.write_by) AS writeBy		");
    	query.append("FROM TANOTICE X                               ");
    	query.append("WHERE 1=1 							        ");
    	query.append(this.getCheckWhere(docNoticeCommonDTO, user));
    	query.getOrderByQuery("x.notice_no desc", docNoticeCommonDTO.getOrderBy(), docNoticeCommonDTO.getDirection());
    	
    	return getJdbcTemplate().queryForList(query.toString(docNoticeCommonDTO.getIsLoadMaxCount(), docNoticeCommonDTO.getFirstRow()));
    }

    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param docNoticeCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(DocNoticeCommonDTO docNoticeCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("x.comp_no", user.getCompNo());
        
        if(!"".equals(docNoticeCommonDTO.getNoticeId())){
            query.getAndQuery("x.notice_id", docNoticeCommonDTO.getNoticeId());
            return query.toString();
        }
        
        // 공지일자
        query.getAndDateQuery("x.write_date", docNoticeCommonDTO.getFilterNoticeFromDate(), docNoticeCommonDTO.getFilterNoticeToDate());

        // 작성자 
        query.getCodeLikeQuery("x.write_by", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.write_by AND a.comp_no=x.comp_no)", 
        		docNoticeCommonDTO.getFilterWriteById(), docNoticeCommonDTO.getFilterWriteByDesc());

        // 제목 
        query.getLikeQuery("x.description", docNoticeCommonDTO.getFilterTitle());
        
        return query.toString();
    }
    
    private String getCheckWhere(DocNoticeCommonDTO docNoticeCommonDTO, User user)
    {        
    	QueryBuffer query = new QueryBuffer();
    	
    	query.getAndQuery("x.comp_no", user.getCompNo());
    	
    	if(!"".equals(docNoticeCommonDTO.getNoticeId())){
    		query.getAndQuery("x.notice_id", docNoticeCommonDTO.getNoticeId());
    		return query.toString();
    	}
    	
    	query.getAndQuery("x.notice_status", "C");
        
    	if(!"".equals(docNoticeCommonDTO.getFilterReadYn()) && !"".equals(docNoticeCommonDTO.getFilterEmpId()))
    	{
    	    query.append("AND EXISTS(SELECT notiusr_id                                        ");
            query.append("           FROM TANOTIUSR                                           ");
            query.append("           WHERE comp_no = x.comp_no                                ");
            query.append("           AND notice_id = x.notice_id                              ");
            query.append("           AND read_yn = '"+docNoticeCommonDTO.getFilterReadYn()+"' ");
            query.append("           AND emp_id = '"+docNoticeCommonDTO.getFilterEmpId()+"'   ");
            query.append("          )                                                         ");
    	}
    	
    	// 공지일자
    	query.getAndDateQuery("x.write_date", docNoticeCommonDTO.getFilterNoticeFromDate(), docNoticeCommonDTO.getFilterNoticeToDate());
    	
    	// 공지기한
    	query.getAndDateQuery("x.limit_date", docNoticeCommonDTO.getFilterNoticePeriodFromDate(), docNoticeCommonDTO.getFilterNoticePeriodToDate());
    	
    	// 작성자 
    	query.getCodeLikeQuery("x.write_by", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.write_by AND a.comp_no=x.comp_no)", 
    			docNoticeCommonDTO.getFilterWriteById(), docNoticeCommonDTO.getFilterWriteByDesc());
    	
    	// 제목 
    	query.getLikeQuery("x.description", docNoticeCommonDTO.getFilterTitle());
    	
    	return query.toString();
    }

    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id: docNoticeListDAO.java,v 1.0 2017/08/22 15:20:12 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param docNoticeCommonDTO
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TANOTICE   ");
        query.append("WHERE notice_id = ?    ");
        query.append("  AND comp_no = ?      ");
        query.append("  AND notice_status != 'C'	");
        
        Object[] objects = new Object[] {   
                id
                ,user.getCompNo()
        };
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    public String findTotalCount(
            DocNoticeCommonDTO docNoticeCommonDTO, User user) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                ");
        query.append("       COUNT(1)                       ");
        query.append("FROM TANOTICE x						");
        query.append("WHERE 1=1 							");
        query.append(this.getWhere(docNoticeCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
    
    public String findCheckTotalCount(
    		DocNoticeCommonDTO docNoticeCommonDTO, User user) {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("SELECT                                      ");
    	query.append("       COUNT(1)                             ");
    	query.append("FROM TANOTICE X                             ");
    	query.append("WHERE 1=1 							      ");
    	query.append(this.getCheckWhere(docNoticeCommonDTO,user));
    	
    	List resultList=  getJdbcTemplate().queryForList(query.toString());
    	return QueryBuffer.listToString(resultList);
    }    
}
