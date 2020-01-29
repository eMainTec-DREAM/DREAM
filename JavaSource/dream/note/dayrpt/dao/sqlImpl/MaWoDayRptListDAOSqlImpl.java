package dream.note.dayrpt.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.note.dayrpt.dao.MaWoDayRptListDAO;
import dream.note.dayrpt.dto.MaWoDayRptCommonDTO;

/**
 * 업무일지 - 목록 dao
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maWoDayRptListDAOTarget"
 * @spring.txbn id="maWoDayRptListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoDayRptListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaWoDayRptListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoDayRptCommonDTO
     * @return List
     */
    public List findList(MaWoDayRptCommonDTO maWoDayRptCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
            query.append("SELECT                                                   ");
            query.append("        ''                        seqNo,                 ");
            query.append("        ''                        isDelCheck,            ");
            query.getDate("x.wrk_date", "workDate,");
            query.append("        x.wrkdayrpt_id wrkDayRptId,                      ");
            query.append("        (SELECT a.emp_name                               ");
            query.append("         FROM TAEMP a                                    ");
            query.append("         WHERE a.comp_no = x.comp_no                     ");
            query.append("           AND a.emp_id = x.emp_id)             empDesc, ");
            query.append("        x.title title,                                   ");
            query.append("        x.wrk_time workTime,                             ");
            query.append("        x.dept_id deptId,                                ");
            query.append("        (SELECT a.description                            ");
            query.append("         FROM TADEPT a                                   ");
            query.append("         WHERE a.comp_no = x.comp_no                     ");
            query.append("           AND a.dept_id = x.dept_id)           deptDesc,");
            query.append("        x.contents                              contents ");
            query.append("FROM TAWRKDAYRPT x                                       ");
            query.append("WHERE 1 = 1                                              ");
            query.append(this.getWhere(maWoDayRptCommonDTO, user));
            query.getOrderByQuery("x.wrkdayrpt_id", "x.dept_id, x.emp_id, x.wrk_date desc, x.wrk_time   asc", maWoDayRptCommonDTO.getOrderBy(), maWoDayRptCommonDTO.getDirection());
//            query.append("ORDER BY x.dept_id, x.emp_id, x.wrk_date desc, x.wrk_time	asc");
        
        return getJdbcTemplate().queryForList(query.toString(maWoDayRptCommonDTO.getIsLoadMaxCount(), maWoDayRptCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoDayRptCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaWoDayRptCommonDTO maWoDayRptCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.getAndQuery("x.comp_no", user.getCompNo());
        if (!"".equals(maWoDayRptCommonDTO.getWrkDayRptId()))
        {
            query.getAndQuery("x.wrkdayrpt_id", maWoDayRptCommonDTO.getWrkDayRptId());
            return query.toString();
        }
        
//        if(!"".equals(maWoDayRptCommonDTO.getFilterEmpId())||!"".equals(maWoDayRptCommonDTO.getFilterEmpDesc())){
//        	query.append(" AND x.emp_id in (select aa.emp_id from taemp aa where 1=1 ");
//        	query.getAndQuery("aa.comp_no", user.getCompNo());
//        	query.getAndQuery("aa.emp_no", maWoDayRptCommonDTO.getFilterEmpId());
//        	query.append("                  )     ");
//        	
//        }
        
        query.getAndDateQuery("x.wrk_date", maWoDayRptCommonDTO.getFilterStartDate(), maWoDayRptCommonDTO.getFilterEndDate());
        query.getCodeLikeQuery("x.dept_id", "(SELECT description FROM TADEPT WHERE comp_no = x.comp_no AND dept_id = x.dept_id)", maWoDayRptCommonDTO.getFilterDeptId(), maWoDayRptCommonDTO.getFilterDeptDesc());
        query.getCodeLikeQuery("x.emp_id", "(SELECT emp_name FROM TAEMP WHERE comp_no = x.comp_no AND emp_id = x.emp_id)", maWoDayRptCommonDTO.getFilterEmpId(), maWoDayRptCommonDTO.getFilterEmpDesc());
        
        
        
        return query.toString();
    }

    public int deleteList(String id, String compNo)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;
        
        query.append("DELETE FROM TAWRKDAYRPT                ");
        query.append("WHERE wrkdayrpt_id     = "+id+"        ");
        query.append("  AND comp_no          = '"+compNo+"'  ");
        
        rtnValue = this.getJdbcTemplate().update(query.toString());
        
        return rtnValue;

    }
    
    @Override
    public String findTotalCount(MaWoDayRptCommonDTO maWoDayRptCommonDTO,
            User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                   ");
        query.append("      count(1)                                           ");
        query.append("FROM TAWRKDAYRPT x                                       ");
        query.append("WHERE 1 = 1                                              ");
        query.append(this.getWhere(maWoDayRptCommonDTO, user));
//        query.getOrderByQuery("x.wrkdayrpt_id", "x.dept_id, x.emp_id, x.wrk_date desc, x.wrk_time   asc", maWoDayRptCommonDTO.getOrderBy(), maWoDayRptCommonDTO.getDirection());
//        query.append("ORDER BY x.dept_id, x.emp_id, x.wrk_date desc, x.wrk_time   asc");
    
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
    
}