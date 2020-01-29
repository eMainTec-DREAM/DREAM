package dream.work.rpt.pm.monthnew.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.rpt.pm.monthnew.dao.WorkRptPmMonthNewListDAO;
import dream.work.rpt.pm.monthnew.dto.WorkRptPmMonthNewListDTO;

/**
 * 신규점검등록현황 DAO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="workRptPmMonthNewListDAOTarget"
 * @spring.txbn id="workRptPmMonthNewListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptPmMonthNewListDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkRptPmMonthNewListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param workRptPmMonthNewListDTO
     * @return List
     */
    public List findConnList(WorkRptPmMonthNewListDTO workRptPmMonthNewListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        int month = Integer.parseInt(workRptPmMonthNewListDTO.getMonths());
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;     ");
        query.append("SELECT                                                ");
        query.append("    ''        				AS    SEQNO        		");
        query.append("    ,x.dept_id                AS    DEPTID        	");
        query.append("    ,x.description            AS    DEPTDESC    		");
        query.append("	  ,x.p_dept_id        		AS 	  PDEPTID			");
        query.append("    ,x.dept_id                AS 	  ID       			");
        query.append("    ,MIN(y.lvl) OVER()    	AS 	  MINLVL    		");
        query.append("    ,y.lvl 					AS	  LVL             	");
        
        for(int a=0; a<=month; a++) {
        	
        	String fromYear = workRptPmMonthNewListDTO.getFilterStartDate().replaceAll("-", "").substring(0, 4); // 년
        	String fromMonth = workRptPmMonthNewListDTO.getFilterStartDate().replaceAll("-", "").substring(4);   // 월
        	String fromDate = fromYear+fromMonth;
        	
            query.append("    ,( SELECT                                         ");
            query.append("           COUNT(1)                                   ");
            query.append("         FROM TAPMLST a                           	");
            query.append("         WHERE 1=1                                    ");
            query.append("  AND  a.comp_no = x.comp_no							");
            query.append("  AND  a.is_deleted = 'N'								");
            query.append("  AND a.wo_type = 'PMI'		");
            query.getAndQuery("a.is_last_version", "Y");
            query.append("  AND a.cre_date BETWEEN '"+fromDate+"01000000' AND '"+fromDate+"31235959' ");
            query.append("  AND a.dept_id IN ( SELECT b.dept_id 				");
            query.append("                     FROM dbo.SFADEPT_ALL('"+loginUser.getCompNo()+"',y.dept_id ) b INNER JOIN TADEPT c ");
            query.append("						ON b.comp_no = c.comp_no		");
            query.append("						AND b.dept_id = c.dept_id		");
            query.getAndQuery("c.is_use", "Y");
            query.getAndQuery("c.is_monitoring", "Y");
            query.append("			)											");
            query.append("     )      NEWPMICNT"+fromDate+"    					");
            
            if(Integer.parseInt(fromMonth)+1==13)
            {
            	fromYear = Integer.toString(Integer.parseInt(fromYear)+1); // 1년 증가
        		fromMonth = "01"; // 1월로 월 초기화
            }
        	else
        	{
        		fromMonth = Integer.parseInt(fromMonth)+1<10?"0"+Integer.toString(Integer.parseInt(fromMonth)+1):Integer.toString(Integer.parseInt(fromMonth)+1);
        	}
	            workRptPmMonthNewListDTO.setFilterStartDate(fromYear+fromMonth);
	            
            }
        query.append("FROM   TADEPT x ,(SELECT * FROM dbo.SFADEPT_ALL('"+loginUser.getCompNo()+"','0')) y                                           ");
    	query.append("WHERE x.dept_id = y.dept_id                                                ");
        query.append(this.getWhere(workRptPmMonthNewListDTO, loginUser));

        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public String getWhere(WorkRptPmMonthNewListDTO workRptPmMonthNewListDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getAndQuery("x.is_use", "Y");
        query.getAndQuery("x.is_monitoring", "Y");

        //부서
        query.getDeptLevelQuery("x.dept_id", workRptPmMonthNewListDTO.getFilterDeptId(), workRptPmMonthNewListDTO.getFilterDeptDesc(), loginUser.getCompNo());

    	return query.toString();
    }
    
}