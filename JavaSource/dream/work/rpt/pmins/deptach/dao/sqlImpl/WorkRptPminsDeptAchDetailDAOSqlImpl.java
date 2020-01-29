package dream.work.rpt.pmins.deptach.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.QuerySqlBuffer;
import dream.work.rpt.pmins.deptach.dao.WorkRptPminsDeptAchDetailDAO;
import dream.work.rpt.pmins.deptach.dto.WorkRptPminsDeptAchDetailDTO;

/**
 * 예방점검 이행율(부서) 상세 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="workRptPminsDeptAchDetailDAOTarget"
 * @spring.txbn id="workRptPminsDeptAchDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptPminsDeptAchDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkRptPminsDeptAchDetailDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPminsDeptAchDetailDTO
     * @param loginUser
     * @return List
     */
    public List findDetail(WorkRptPminsDeptAchDetailDTO workRptPminsDeptAchDetailDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT        ");
        query.append("    ''                                                                  seqNo          ");
        query.append("    , x.month                   										month          ");
        query.append("    ,COUNT(x.pminslistId)                                                                                                                   planed         ");
        query.append("    ,COUNT(CASE WHEN x.pmschedStatus = 'C' THEN x.pminslistId END)                                                     completed     ");
        query.append("    ,ROUND(COUNT(CASE WHEN x.pmschedStatus = 'C' THEN x.pminslistId END)/CONVERT(FLOAT,COUNT(x.pminslistId))*100,2)    achievement ");
        query.append("FROM (        ");
        query.append("    SELECT        ");
        query.append("        SUBSTRING(a.wkor_date,1,4)+'-'+SUBSTRING(a.wkor_date,5,2)        month             ");
        query.append("        ,a.pminslist_id                                                               pminslistId       ");
        query.append("        ,a.pmsched_status                                                            	pmschedStatus ");
        query.append("        ,a.comp_no                                                            		comp_no ");
        query.append("    FROM TAPMINSLIST a        ");
        query.append("    WHERE 1=1     ");
        query.append(this.getWhere(workRptPminsDeptAchDetailDTO,loginUser));
        query.append(") x       ");
        query.append("GROUP BY x.month, x.comp_no      ");
        query.getOrderByQuery("x.comp_no", "x.month", workRptPminsDeptAchDetailDTO.getOrderBy(), workRptPminsDeptAchDetailDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public String getWhere(WorkRptPminsDeptAchDetailDTO workRptPminsDeptAchDetailDTO,User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("a.comp_no", loginUser.getCompNo());
        query.getAndQuery("a.dept_id", workRptPminsDeptAchDetailDTO.getDeptId());
        query.getAndQuery("a.is_deleted", "N");

        query.append("AND a.plant IS NOT NULL      ");
        query.append("AND a.wkor_date IS NOT NULL      ");

        String fromDate = CommonUtil.dateToData(workRptPminsDeptAchDetailDTO.getStartDate()+"-01");
        String toDate   = CommonUtil.dateToData(workRptPminsDeptAchDetailDTO.getEndDate()+"-01");
        
        if (fromDate != null && !"".equals(fromDate) && !"null".equals(fromDate))
        {
            query.append("AND a.wkor_date >=  CONVERT(VARCHAR,'"+fromDate+"')     ");
        }
        
        if (toDate != null && !"".equals(toDate) && !"null".equals(toDate))
        {
            query.append("AND a.wkor_date <=  CONVERT(VARCHAR, DATEADD(DAY, -1, DATEADD(MONTH, 1, '"+toDate+"')), 112)        ");
        }        
        
        return query.toString();
    }
    
    @Override
    public String findTotalCount(WorkRptPminsDeptAchDetailDTO workRptPminsDeptAchDetailDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT COUNT(1)                                            								");
        query.append("	FROM (                                            										");
        query.append("		SELECT                                            									");
        query.append("       	x.comp_no                                   									");
        query.append("		  FROM (        																	");
        query.append("    		SELECT        																	");
        query.append("        		SUBSTRING(a.wkor_date,1,4)+'-'+SUBSTRING(a.wkor_date,5,2)	month           ");
        query.append("        	  , a.pminslist_id                                              pminslistId     ");
        query.append("        	  , a.pmsched_status                                            pmschedStatus	");
        query.append("        	  , a.comp_no                                                   comp_no 		");
        query.append("    		  FROM TAPMINSLIST a        													");
        query.append("    		 WHERE 1=1     																	");
        query.append(this.getWhere(workRptPminsDeptAchDetailDTO,loginUser));
        query.append("		) x       																			");
        query.append("GROUP BY x.month, x.comp_no      															");
        query.append("	) a      																				");

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}