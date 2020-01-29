package dream.note.dayrpt.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.note.dayrpt.dao.MaWoDayRptDetailDAO;
import dream.note.dayrpt.dto.MaWoDayRptCommonDTO;
import dream.note.dayrpt.dto.MaWoDayRptDetailDTO;

/**
 *  - »ó¼¼ dao
 * 
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * @spring.bean id="maWoDayRptDetailDAOTarget"
 * @spring.txbn id="maWoDayRptDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoDayRptDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaWoDayRptDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param IfFailureCommonDTO
     * @param loginUser
     * @return
     */
    public MaWoDayRptDetailDTO findDetail(MaWoDayRptCommonDTO maWoDayRptCommonDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT                                                   ");
        query.append("        x.wrk_date workDate                              ");
        query.append("        ,x.wrkdayrpt_id wrkDayRptId                      ");
        query.append("        ,x.emp_id	empId								   ");
        query.append("        ,(SELECT a.emp_name                              ");
        query.append("         FROM TAEMP a                                    ");
        query.append("         WHERE a.comp_no = x.comp_no                     ");
        query.append("           AND a.emp_id = x.emp_id)             empDesc  ");
        query.append("        ,x.title title                                   ");
        query.append("        ,x.contents contents                             ");
        query.append("        ,x.wrk_time workTime                             ");
        query.append("        ,x.dept_id deptId                                ");
        query.append("        ,(SELECT a.description                           ");
        query.append("         FROM TADEPT a                                   ");
        query.append("         WHERE a.comp_no = x.comp_no                     ");
        query.append("           AND a.dept_id = x.dept_id)           deptDesc ");
        query.append("        ,x.plan_date planDate                            ");
        query.append("        ,x.plan_time planTime                            ");
        query.append("        ,x.plan_contents planContents                    ");
        query.append("		  ,x.remark 							  remark   ");
        query.append("FROM TAWRKDAYRPT x                                       ");
        query.append("WHERE 1=1                                                ");
        query.append("    and x.comp_no = ?                                    ");
        query.append("    and x.wrkdayrpt_id = ?                               ");
        
        Object[] objects = new Object[] {
        		loginUser.getCompNo()
        		,maWoDayRptCommonDTO.getWrkDayRptId()
    	};
        MaWoDayRptDetailDTO resultDTO = 
        		(MaWoDayRptDetailDTO)getJdbcTemplate().query(query.toString(),objects, new MwareExtractor(new MaWoDayRptDetailDTO()));
        
        return resultDTO;
    }
    
    /**
     * detail update
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param IfFailureDetailDTO
     * @return
     */
    public int updateDetail(MaWoDayRptDetailDTO maWoDayRptDetailDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TAWRKDAYRPT SET		           ");
    	query.append("	     wrk_date          = ?             ");
    	query.append("	     ,title            = ?             ");
    	query.append("	     ,wrk_time         = ?             ");
    	query.append("	     ,contents         = ?             ");
    	query.append("	     ,plan_date        = ?             ");
    	query.append("	     ,plan_time        = ?             ");
    	query.append("	     ,plan_contents    = ?             ");
    	query.append("	     ,remark           = ?             ");
    	query.append("WHERE  wrkdayrpt_id      = ?	           ");
    	query.append("  AND comp_no            = ?             ");
    	
    	Object[] objects = new Object[] {
    			maWoDayRptDetailDTO.getWorkDate()
    			,maWoDayRptDetailDTO.getTitle()
    			,maWoDayRptDetailDTO.getWorkTime()
    			,maWoDayRptDetailDTO.getContents()
    			,maWoDayRptDetailDTO.getPlanDate()
    			,maWoDayRptDetailDTO.getPlanTime()
    			,maWoDayRptDetailDTO.getPlanContents()
    			,maWoDayRptDetailDTO.getRemark()
    			,maWoDayRptDetailDTO.getWrkDayRptId()
    			,loginUser.getCompNo()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }

    public int insertDetail(MaWoDayRptDetailDTO maWoDayRptDetailDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;
        
        query.append("INSERT INTO TAWRKDAYRPT                            ");
        query.append("  (comp_no        ,wrkdayrpt_id     ,wrk_date      ");
        query.append("   ,emp_id        ,dept_id          ,title         ");
        query.append("   ,contents      ,wrk_time                        ");
        query.append("   ,plan_date     ,plan_time        ,plan_contents ");
        query.append("   ,remark										 ");
        query.append("  )   VALUES                                       ");
        query.append("  ( ?             ,?              ,?               ");
        query.append("   ,?             ,?              ,?               ");
        query.append("   ,?             ,?                               ");
        query.append("   ,?             ,?              ,?               ");
        query.append("   ,?												 ");
        query.append("  )                                                ");
        
        Object[] objects = new Object[] {
                loginUser.getCompNo(),
                maWoDayRptDetailDTO.getWrkDayRptId()
                ,maWoDayRptDetailDTO.getWorkDate()
                ,maWoDayRptDetailDTO.getEmpId()
                ,maWoDayRptDetailDTO.getDeptId()
                ,maWoDayRptDetailDTO.getTitle()
                ,maWoDayRptDetailDTO.getContents()
                ,maWoDayRptDetailDTO.getWorkTime()
                ,maWoDayRptDetailDTO.getPlanDate()
    			,maWoDayRptDetailDTO.getPlanTime()
    			,maWoDayRptDetailDTO.getPlanContents()
    			,maWoDayRptDetailDTO.getRemark()
        };
        
        rtnValue = getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
}