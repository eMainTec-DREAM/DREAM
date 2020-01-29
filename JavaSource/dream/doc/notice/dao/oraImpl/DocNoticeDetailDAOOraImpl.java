package dream.doc.notice.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.doc.notice.dao.DocNoticeDetailDAO;
import dream.doc.notice.dto.DocNoticeCommonDTO;
import dream.doc.notice.dto.DocNoticeDetailDTO;

/**
 * DocNotice Page - Detail DAO implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="docNoticeDetailDAOTarget"
 * @spring.txbn id="docNoticeDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class DocNoticeDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements DocNoticeDetailDAO
{

    public DocNoticeDetailDTO findDetail(DocNoticeCommonDTO docNoticeCommonDTO, User user) 
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                        ");
        query.append("    x.notice_id    		AS noticeId			");
        query.append("  , x.notice_no   		AS noticeNo			");
        query.append("  , x.description 		AS title			");
        query.append("  , x.notice_status 		AS noticeStatusId	");
        query.append("  , SFACODE_TO_DESC(x.notice_status, 'NOTICE_STATUS', 'SYS', x.comp_no, ? ) AS noticeStatus         		");
        query.append("  , x.limit_date    		AS noticePeriod		");
        query.append("  , x.write_date   		AS noticeDate		");
        query.append("  , x.write_dept    		AS regDeptId		");
        query.append("  , (SELECT a.description FROM TADEPT a WHERE a.comp_no = x.comp_no AND a.dept_id = x.write_dept) AS regDept		");
        query.append("  , x.write_by    		AS writeById		");
        query.append("  , (SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = x.comp_no AND a.emp_id = x.write_by) AS writeBy		");
        query.append("  , x.subject 			AS remark			");
        query.append("FROM TANOTICE x								");
        query.append("WHERE 1=1 									");
        query.append("AND    x.comp_no    = ?                       ");
        query.append("AND    x.notice_id  = ?                       ");
        
        Object[] objects = new Object[] {
                user.getLangId()
                ,user.getCompNo()
                , docNoticeCommonDTO.getNoticeId()
        };
    
        DocNoticeDetailDTO docNoticeDetailDTO = 
                (DocNoticeDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new DocNoticeDetailDTO()));
        
        return docNoticeDetailDTO;
        
    }
    

    public int insertDetail(DocNoticeDetailDTO docNoticeDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;

        query.append("INSERT INTO TANOTICE(  ");
        query.append("  comp_no              ");
        query.append(", notice_id            ");
        query.append(", notice_no            ");
        query.append(", description          ");
        query.append(", notice_status        ");
        query.append(", limit_date           ");
        query.append(", write_date           ");
        query.append(", write_dept           ");
        query.append(", write_by             ");
        query.append(", subject              ");
        query.append(")VALUES(               ");
        query.append("   ?                   ");
        query.append(" , ?                   ");
        query.append(" , ?                   ");
        query.append(" , ?                   ");
        query.append(" , ?                   ");
        query.append(" , ?                   ");
        query.append(" , ?                   ");
        query.append(" , ?                   ");
        query.append(" , ?                   ");
        query.append(" , ?                   ");
        query.append(" )                     ");
        
        Object[] objects = new Object[] {
                user.getCompNo()
              , docNoticeDetailDTO.getNoticeId()
              , docNoticeDetailDTO.getNoticeNo()
              , docNoticeDetailDTO.getTitle()
              , docNoticeDetailDTO.getNoticeStatusId()
              , docNoticeDetailDTO.getNoticePeriod()
              , docNoticeDetailDTO.getNoticeDate()
              , docNoticeDetailDTO.getRegDeptId()
              , docNoticeDetailDTO.getWriteById()
              , docNoticeDetailDTO.getRemark()
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
    
    public int updateDetail(DocNoticeDetailDTO docNoticeDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        int rtnValue  = 0;

        query.append("UPDATE TANOTICE SET    	");
        query.append("  notice_no       = ?     ");
        query.append(", description     = ?     ");
        query.append(", limit_date      = ?     ");
        query.append(", subject         = ?     ");
        query.append("WHERE  comp_no    = ?     ");
        query.append("  AND  notice_id	= ?	    ");
        
        Object[] objects = new Object[] {
        		docNoticeDetailDTO.getNoticeNo()
        	  , docNoticeDetailDTO.getTitle()
        	  , docNoticeDetailDTO.getNoticePeriod()
        	  , docNoticeDetailDTO.getRemark()
        	  , user.getCompNo()
        	  , docNoticeDetailDTO.getNoticeId()
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
    
    public int goNotice(DocNoticeDetailDTO docNoticeDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        int rtnValue  = 0;
        
        query.append("UPDATE TANOTICE SET 		");
        query.append("    notice_status = ?		");
        query.append("WHERE  comp_no    = ?     ");
        query.append("  AND  notice_id	= ?	    ");
                
        Object[] objects = new Object[] {
        		"C"
              , user.getCompNo()
              , docNoticeDetailDTO.getNoticeId()
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(),objects);
        
        return rtnValue;
    }
    
    public int updateUserDetail(DocNoticeCommonDTO docNoticeCommonDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
        
        int rtnValue  = 0;
        
        query.append("UPDATE TANOTIUSR SET	");
        query.append("    read_date = ?		");
        query.append("  , read_time = ?		");
        query.append("  , read_yn 	= ?		");
        query.append("WHERE comp_no = ?		");
        query.append("AND notice_id = ?		");
        query.append("AND emp_id 	= ?		");
        query.append("AND read_yn 	= ?		");

        Object[] objects = new Object[] {
        		DateUtil.getDate()
        	  , DateUtil.getDateTime().substring(8)
        	  , "Y"
        	  , loginUser.getCompNo()
        	  , docNoticeCommonDTO.getNoticeId()
        	  , loginUser.getEmpId()
        	  , "N"
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
}
