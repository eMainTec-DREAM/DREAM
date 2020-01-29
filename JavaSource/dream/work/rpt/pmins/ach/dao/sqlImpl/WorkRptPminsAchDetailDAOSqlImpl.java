package dream.work.rpt.pmins.ach.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.QuerySqlBuffer;
import dream.work.rpt.org.bd.dto.WorkRptOrgBdCommonDTO;
import dream.work.rpt.pmins.ach.dao.WorkRptPminsAchDetailDAO;
import dream.work.rpt.pmins.ach.dto.WorkRptPminsAchDetailDTO;

/**
 * 예방점검 이행율(담당자) 상세 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="workRptPminsAchDetailDAOTarget"
 * @spring.txbn id="workRptPminsAchDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptPminsAchDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkRptPminsAchDetailDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPminsAchDetailDTO
     * @param loginUser
     * @return List
     */
    public List findDetail(WorkRptPminsAchDetailDTO workRptPminsAchDetailDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT        ");
        query.append("    x.month                                                                                                                                     month          ");
        query.append("    ,COUNT(x.pminslistId)                                                                                                                   planed         ");
        query.append("    ,COUNT(CASE WHEN x.pmschedStatus = 'C' THEN x.pminslistId END)                                                     completed     ");
        query.append("    ,ROUND(COUNT(CASE WHEN x.pmschedStatus = 'C' THEN x.pminslistId END)/COUNT(x.pminslistId)*100,2)    achievement ");
        query.append("FROM (        ");
        query.append("    SELECT        ");
        query.append("        SUBSTRING(a.wkor_date,1,4)+'-'+SUBSTRING(a.wkor_date,5,2)        month             ");
        query.append("        ,a.pminslist_id                                                                   pminslistId       ");
        query.append("        ,a.pmsched_status                                                            pmschedStatus ");
        query.append("        ,a.comp_no                                                            comp_no ");
        query.append("    FROM TAPMINSLIST a        ");
        query.append("    WHERE 1=1     ");
        query.append(this.getWhere(workRptPminsAchDetailDTO,loginUser));
        query.append(") x       ");
        query.append("GROUP BY x.month, x.comp_no      ");
        query.getOrderByQuery("x.comp_no", "x.month", workRptPminsAchDetailDTO.getOrderBy(), workRptPminsAchDetailDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public String getWhere(WorkRptPminsAchDetailDTO workRptPminsAchDetailDTO,User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("a.comp_no", loginUser.getCompNo());
        query.getAndQuery("a.dept_id", workRptPminsAchDetailDTO.getDeptId());
        query.getAndQuery("a.emp_id", workRptPminsAchDetailDTO.getEmpId());
        query.getAndQuery("a.is_deleted", "N");

        query.append("AND a.plant IS NOT NULL      ");
        query.append("AND a.wkor_date IS NOT NULL      ");
        query.append("AND a.emp_id IS NOT NULL      ");

        String fromDate = CommonUtil.dateToData(workRptPminsAchDetailDTO.getStartDate()+"-01");
        String toDate   = CommonUtil.dateToData(workRptPminsAchDetailDTO.getEndDate()+"-01");
        
        if (fromDate != null && !"".equals(fromDate) && !"null".equals(fromDate))
        {
//            query.append("AND a.wkor_date >=  TO_CHAR(TO_DATE('"+fromDate+"','YYYY-MM'),'YYYYMMDD')     ");
            query.append("AND a.wkor_date >=  CONVERT(VARCHAR, DATEADD(MONTH, 1, '"+fromDate+"'), 112)     ");
        }
        
        if (toDate != null && !"".equals(toDate) && !"null".equals(toDate))
        {
//            query.append("AND a.wkor_date <  TO_CHAR(ADD_MONTHS(TO_DATE('"+toDate+"','YYYY-MM'),1),'YYYYMMDD')        ");
            query.append("AND a.wkor_date <=  CONVERT(VARCHAR, DATEADD(DAY, -1, DATEADD(MONTH, 1, '"+toDate+"')), 112)        ");
        }        
        
        return query.toString();
    }
    
    @Override
    public String findTotalCount(WorkRptPminsAchDetailDTO workRptPminsAchDetailDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                            										");
        query.append("       COUNT(1)                                   										");
        query.append("	FROM (                        															");
        query.append("		SELECT                                            									");
        query.append("      	x.comp_no                                   									");
        query.append("		  FROM (                        													");
        query.append("    		SELECT        																	");
        query.append("      		SUBSTRING(a.wkor_date,1,4)+'-'+SUBSTRING(a.wkor_date,5,2)	month           ");
        query.append("      	  , a.pminslist_id                                           	pminslistId     ");
        query.append("      	  , a.pmsched_status                                         	pmschedStatus	");
        query.append("      	  , a.comp_no                                                	comp_no 		");
        query.append("    		  FROM TAPMINSLIST a        													");
        query.append("     		 WHERE 1=1     																	");
        query.append(this.getWhere(workRptPminsAchDetailDTO,loginUser));
        query.append("		) x      	 																		");
        query.append("GROUP BY x.month, x.comp_no      															");
        query.append("	) a 	     	 																		");

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
    }
}