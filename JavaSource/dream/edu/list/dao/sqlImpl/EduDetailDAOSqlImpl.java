package dream.edu.list.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.edu.list.dao.EduDetailDAO;
import dream.edu.list.dto.EduDetailDTO;

/**
 * 자격증분류 - 상세 dao
 * 
 * @author ssong
 * @version $Id: $
 * @since 1.0
 * @spring.bean id="eduDetailDAOTarget"
 * @spring.txbn id="eduDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class EduDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements EduDetailDAO
{
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param prRecListId
     * @return
     */
    public EduDetailDTO findDetail(User user, String courseListId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String compNo = user.getCompNo();
        
        query.append("SELECT x.comp_no                             compNo,      ");
        query.append("       x.courselist_id                        courseListId, ");
        query.append("       x.courselist_no                        courseListNo, ");
        query.append("       x.course_type                        courseType, ");
        query.append("       dbo.SFACODE_TO_DESC(x.course_type, 'COURSE_TYPE', 'USR', x.comp_no,'"+user.getLangId()+"') courseTypeDesc, ");        
        query.append("       x.description                        description, ");
        query.append("       x.cre_date                        creDate, ");
        query.append("       x.is_use                        isUse, ");
        query.append("       x.cre_by                        empId, ");
        query.append("       x.cre_by                        empId, ");
        query.append("		(SELECT emp_name															");
        query.append("		   FROM TAEMP																");
        query.append("		  WHERE comp_no = x.comp_no													");
        query.append("			AND emp_id = x.cre_by)									empDesc,		");
        query.append("       x.cre_dept                        deptId, ");
        query.append("		(SELECT description															");
        query.append("		   FROM TADEPT																");
        query.append("		  WHERE comp_no = x.comp_no													");
        query.append("			AND dept_id = x.cre_dept)								deptDesc,		");
        query.append("       x.contents                        contents ");
        query.append("FROM   TACOURSELIST x                         ");
        query.append("WHERE  1=1                    ");
        query.append("  AND  x.comp_no      = '"+compNo+"'	                    ");
        query.append("  AND  x.courselist_id = '"+courseListId+"'                 ");
    
        EduDetailDTO eduDetailDTO = 
        		(EduDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new EduDetailDTO()));
        
        return eduDetailDTO;
    }
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param eduDetailDTO
     * @return
     */
    public int insertDetail(EduDetailDTO eduDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TACOURSELIST (                                  ");
    	query.append("  comp_no,   courselist_id, courselist_no, description,");
    	query.append("  course_type,   cre_date,     cre_by,    cre_dept,        ");
    	query.append("  contents,   is_use 	    								");
    	query.append(")	VALUES (                                                ");
    	query.append("  ?,         ?,             ?,            ?,              ");
    	query.append("  ?,         ?,             ?,            ?,              ");
    	query.append("  ?,         ?            ");
    	query.append(")                                                         ");
    	
    	Object[] objects = new Object[] {
    			eduDetailDTO.getCompNo(),
    			eduDetailDTO.getCourseListId(),
    			eduDetailDTO.getCourseListNo(),
    			eduDetailDTO.getDescription(),
    			eduDetailDTO.getCourseType(),
    			eduDetailDTO.getCreDate(),
    			eduDetailDTO.getEmpId(),
    			eduDetailDTO.getDeptId(),
    			eduDetailDTO.getContents(),
    			eduDetailDTO.getIsUse()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail stock
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param eduDetailDTO
     * @return
     */
    public int updateDetail(EduDetailDTO eduDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TACOURSELIST SET		      ");
    	query.append("	     description         = ?,      ");
        query.append("       course_type         = ?,      ");
        query.append("       cre_date            = ?,      ");
        query.append("       cre_by              = ?,      ");    
        query.append("       cre_dept            = ?,      ");    
        query.append("       contents            = ?,      ");
        query.append("       is_use              = ?       ");
    	query.append("WHERE  comp_no             = ?	   ");
    	query.append("  AND  courselist_id       = ?       ");
    	
    	Object[] objects = new Object[] {
    			eduDetailDTO.getDescription(),
    			eduDetailDTO.getCourseType(),
    			eduDetailDTO.getCreDate(),
    			eduDetailDTO.getEmpId(),
    			eduDetailDTO.getDeptId(),
    			eduDetailDTO.getContents(),
    			eduDetailDTO.getIsUse(),
    			eduDetailDTO.getCompNo(),
    			eduDetailDTO.getCourseListId()    			
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    

    
}