package dream.doc.notice.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.doc.notice.dao.DocNoticeDeptDetailDAO;
import dream.doc.notice.dto.DocNoticeCommonDTO;
import dream.doc.notice.dto.DocNoticeDeptDetailDTO;
import dream.doc.notice.dto.DocNoticeDeptListDTO;

/**
 * 평가점수 - Detail DAO implements
 * @author youngjoo38
 * @version $Id: DocNoticeDeptDetailDAOSqlImpl.java,v 1.0 2017/08/28 12:37:40 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="docNoticeDeptDetailDAOTarget"
 * @spring.txbn id="docNoticeDeptDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class DocNoticeDeptDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements DocNoticeDeptDetailDAO
{
	
    public DocNoticeDeptDetailDTO findDetail(DocNoticeCommonDTO docNoticeCommonDTO, DocNoticeDeptListDTO docNoticeDeptListDTO, User user) 
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("SELECT                                ");
        query.append("    x.notidept_id 	AS notiDeptId	");
        query.append("  , x.notice_id 		AS noticeId		");
        query.append("  , x.dept_id 		AS deptId		");
        query.append("  , (SELECT a.dept_no FROM TADEPT a WHERE a.comp_no = x.comp_no AND a.dept_id = x.dept_id) AS dept		");
        query.append("  , (SELECT a.description FROM TADEPT a WHERE a.comp_no = x.comp_no AND a.dept_id = x.dept_id) AS deptDesc		");
        query.append("  , x.remark 			AS remark		");
        query.append("FROM TANOTIDEPT x						");
        query.append("WHERE  1=1                            ");
        query.append("AND  x.comp_no         = ?            ");
        query.append("AND  x.notice_id       = ?            ");
        query.append("AND  x.notidept_id     = ?            ");
        
        Object[] objects = new Object[] {
                  user.getCompNo()
                , docNoticeCommonDTO.getNoticeId()
                , docNoticeDeptListDTO.getNotiDeptId()
        };

    
        DocNoticeDeptDetailDTO docNoticeDeptDetailDTO = 
        		(DocNoticeDeptDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new DocNoticeDeptDetailDTO()));
        
        return docNoticeDeptDetailDTO;
        
    }

    public int insertDetail(DocNoticeCommonDTO docNoticeCommonDTO,DocNoticeDeptDetailDTO docNoticeDeptDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;

    	query.append("INSERT INTO TANOTIDEPT (  ");
    	query.append("  comp_no                 ");
    	query.append(", notidept_id				");
    	query.append(", notice_id               ");
    	query.append(", dept_id                 ");
    	query.append(", remark                  ");
    	query.append(") VALUES (                ");
    	query.append("   ?                      ");
    	query.append(" , ?                      ");
    	query.append(" , ?                      ");
    	query.append(" , ?                      ");
    	query.append(" , ?                      ");
    	query.append(")                         ");

    	Object[] objects = new Object[] {
    			 user.getCompNo()
    		   , docNoticeDeptDetailDTO.getNotiDeptId()
    		   , docNoticeCommonDTO.getNoticeId()
    		   , docNoticeDeptDetailDTO.getDeptId()
    		   , docNoticeDeptDetailDTO.getRemark()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    public int updateDetail(DocNoticeCommonDTO docNoticeCommonDTO,DocNoticeDeptDetailDTO docNoticeDeptDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TANOTIDEPT SET					");
    	query.append("	 dept_id				= ?		    ");
    	query.append("	,remark					= ?		    ");
    	query.append("WHERE  comp_no			= ?		    ");
    	query.append("  AND  notice_id			= ?		    ");
    	query.append("  AND  notidept_id      	= ?		    ");
    	
    	Object[] objects = new Object[] {
    			  docNoticeDeptDetailDTO.getDeptId()
    			, docNoticeDeptDetailDTO.getRemark()
    			, user.getCompNo()
    			, docNoticeCommonDTO.getNoticeId()
    			, docNoticeDeptDetailDTO.getNotiDeptId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }

	@Override
	public int insertNotiUsrDetail(DocNoticeCommonDTO docNoticeCommonDTO, DocNoticeDeptDetailDTO docNoticeDeptDetailDTO,
			User user) throws Exception {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TANOTIUSR 						    ");
    	query.append(" ( comp_no	, notiusr_id	, notice_id		    ");
    	query.append(" , plant		, dept_id		, emp_id		    ");
    	query.append(" , read_yn	, read_date		, read_time		    ");
    	query.append(" , required_yn	)							    ");
    	query.append("SELECT 										    ");
    	query.append("   a.comp_no	, NEXT VALUE FOR SQANOTIUSR_ID		, ?		");
    	query.append(" , a.plant	, a.dept_id		, a.emp_id		    ");
    	query.append(" , ?			, ''			, ''			    ");
    	query.append(" , ?											    ");
    	query.append("FROM TAEMP a									    ");
    	query.append("WHERE 1=1										    ");
    	query.append("AND a.comp_no = ?								    ");
    	query.append("AND a.emp_id NOT IN (SELECT b.emp_id FROM TANOTIUSR b WHERE b.comp_no = a.comp_no AND b.notice_id = ? )		");
    	query.append("AND a.dept_id 								    ");
    	query.append("    IN (SELECT b.dept_id							");
    	query.append("        FROM dbo.SFADEPT_CALL(?,?,'') b ) 		");

    	Object[] objects = new Object[] {
    			  docNoticeCommonDTO.getNoticeId()
    			, "N"
    			, "N"
    			, user.getCompNo()
    			, docNoticeCommonDTO.getNoticeId()
    			, user.getCompNo()
    			, docNoticeDeptDetailDTO.getDeptId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
	}

	@Override
	public int insertNotiUsrAgain(DocNoticeCommonDTO docNoticeCommonDTO, DocNoticeDeptDetailDTO docNoticeDeptDetailDTO,
			User user) throws Exception {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TANOTIUSR 						    ");
    	query.append(" ( comp_no	, notiusr_id	, notice_id		    ");
    	query.append(" , plant		, dept_id		, emp_id		    ");
    	query.append(" , read_yn	, read_date		, read_time		    ");
    	query.append(" , required_yn	)							    ");
    	query.append("SELECT 										    ");
    	query.append("   a.comp_no	, NEXT VALUE FOR SQANOTIUSR_ID		, ?		");
    	query.append(" , a.plant	, a.dept_id		, a.emp_id		    ");
    	query.append(" , ?			, ''			, ''			    ");
    	query.append(" , ?											    ");
    	query.append("FROM TAEMP a									    ");
    	query.append("WHERE 1=1										    ");
    	query.append("AND a.comp_no = ?								    ");
    	query.append("AND a.emp_id NOT IN (SELECT b.emp_id FROM TANOTIUSR b WHERE b.comp_no = a.comp_no AND b.notice_id = ? )		");
    	query.append("AND a.dept_id 								    ");
    	query.append("    IN (SELECT b.dept_id							");
    	query.append("        FROM dbo.SFADEPT_CALL(?,?,'') b ) 		");
    	
    	
    	Object[] objects = new Object[] {
    			docNoticeCommonDTO.getNoticeId()
    			, "N"
    			, "Y"
    			, user.getCompNo()
    			, docNoticeCommonDTO.getNoticeId()
    			, user.getCompNo()
    			, docNoticeDeptDetailDTO.getDeptId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
	}
	
	@Override
	public String checkDetail(DocNoticeDeptDetailDTO docNoticeDeptDetailDTO, User user) throws Exception {
    	QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("SELECT 					");
		query.append("    CASE WHEN COUNT(1)>=1 THEN 1 ELSE 0 END AS isExist	");
		query.append("FROM TANOTIDEPT x 		");
		query.append("WHERE 1=1					");
		query.append("AND x.comp_no 	= ?		");
		query.append("AND x.notidept_id = ?		");
		query.append("AND x.notice_id 	= ?		");
		query.append("AND x.dept_id 	= ?		");

		
        Object[] objects = new Object[] {
        		user.getCompNo()
              ,	docNoticeDeptDetailDTO.getNotiDeptId()
              , docNoticeDeptDetailDTO.getNoticeId()
              , docNoticeDeptDetailDTO.getDeptId()
        };
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), objects));
        
	}
}