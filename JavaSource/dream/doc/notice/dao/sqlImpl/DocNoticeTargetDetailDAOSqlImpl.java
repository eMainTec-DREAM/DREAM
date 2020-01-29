package dream.doc.notice.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.doc.notice.dao.DocNoticeTargetDetailDAO;
import dream.doc.notice.dto.DocNoticeCommonDTO;
import dream.doc.notice.dto.DocNoticeTargetDetailDTO;
import dream.doc.notice.dto.DocNoticeTargetListDTO;

/**
 * 평가점수 - Detail DAO implements
 * @author youngjoo38
 * @version $Id: DocNoticeTargetDetailDAOSqlImpl.java,v 1.0 2017/08/28 12:37:40 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="docNoticeTargetDetailDAOTarget"
 * @spring.txbn id="docNoticeTargetDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class DocNoticeTargetDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements DocNoticeTargetDetailDAO
{
	
    public DocNoticeTargetDetailDTO findDetail(DocNoticeCommonDTO docNoticeCommonDTO, DocNoticeTargetListDTO docNoticeTargetListDTO, User user) 
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("SELECT                                ");
        query.append("    x.notiusr_id 		AS NOTIUSRID	");
        query.append("  , x.notice_id 		AS NOTICEID		");
        query.append("  , (SELECT b.description FROM TADEPT b WHERE b.comp_no = x.comp_no AND b.dept_id = x.dept_id) AS targetDeptDesc	");
        query.append("  , (SELECT b.emp_name FROM TAEMP b WHERE b.comp_no = x.comp_no AND b.emp_id = x.emp_id) 		 AS targetDesc		");
        query.append("  , x.read_yn         AS readYnId																					");
        query.append("  , dbo.SFACODE_TO_DESC(x.read_yn, 'IS_USE', 'SYS', x.comp_no, '"+user.getLangId()+"')  			 AS readYnDesc		");
        query.append("  , x.read_date + ' ' + x.read_time	AS noticeReadDate 	");
        query.append("FROM TANOTIUSR x						");
        query.append("WHERE  1=1                            ");
        query.append("AND  x.comp_no         = ?            ");
        query.append("AND  x.notice_id       = ?            ");
        query.append("AND  x.notiusr_id      = ?            ");
        
        Object[] objects = new Object[] {
                  user.getCompNo()
                , docNoticeTargetListDTO.getNoticeId()
                , docNoticeTargetListDTO.getNotiUsrId()
        };
    
        DocNoticeTargetDetailDTO docNoticeTargetDetailDTO = 
        		(DocNoticeTargetDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new DocNoticeTargetDetailDTO()));
        
        return docNoticeTargetDetailDTO;
        
    }

    public int insertDetail(DocNoticeCommonDTO docNoticeCommonDTO,DocNoticeTargetDetailDTO docNoticeTargetDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;

    	query.append("INSERT INTO TANOTIUSR (  	");
    	query.append("  comp_no                 ");
    	query.append(", notiusr_id				");
    	query.append(", notice_id               ");
    	query.append(", plant                 	");
    	query.append(", dept_id                 ");
    	query.append(", emp_id                  ");
    	query.append(", read_yn                 ");
    	query.append(", required_yn             ");
    	query.append(") VALUES (                ");
    	query.append("   ?                      ");
    	query.append(" , ?                      ");
    	query.append(" , ?                      ");
    	query.append(" , ?                      ");
    	query.append(" , ?                      ");
    	query.append(" , ?                      ");
    	query.append(" , ?                      ");
    	query.append(" , ?                      ");
    	query.append(")                         ");

    	Object[] objects = new Object[] {
    			 user.getCompNo()
    			,docNoticeTargetDetailDTO.getNotiUsrId()
    			,docNoticeCommonDTO.getNoticeId()
    			,docNoticeTargetDetailDTO.getTargetPlantId()
    			,docNoticeTargetDetailDTO.getTargetDeptId()
    			,docNoticeTargetDetailDTO.getTargetId()
    			,"N"
    			,"N"
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    public int updateDetail(DocNoticeCommonDTO docNoticeCommonDTO,DocNoticeTargetDetailDTO docNoticeTargetDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TANOTIUSR SET			");
    	query.append("      plant               = ? ");
    	query.append("    , dept_id             = ? ");
    	query.append("    , emp_id              = ?	");
    	query.append("WHERE  comp_no			= ?	");
    	query.append("  AND  notice_id			= ?	");
    	query.append("  AND  notiusr_id      	= ?	");
    	
    	Object[] objects = new Object[] {
    			  docNoticeTargetDetailDTO.getTargetPlantId()
    			, docNoticeTargetDetailDTO.getTargetDeptId()
    			, docNoticeTargetDetailDTO.getTargetId()
    			, user.getCompNo()
    			, docNoticeTargetDetailDTO.getNoticeId()
    			, docNoticeTargetDetailDTO.getNotiUsrId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
}