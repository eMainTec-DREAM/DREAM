package dream.work.cal.pmptrlmonth.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.QuerySqlBuffer;
import dream.work.cal.pmptrlmonth.dao.WorkCalPmPtrlMonthListDAO;
import dream.work.cal.pmptrlmonth.dto.WorkCalPmPtrlMonthCommonDTO;

/**
 * 월간예방일정 - 목록 dao
 * @author  youngjoo38
 * @version $Id: WorkCalPmPtrlMonthListDAO.java,v 1.0 2015/12/02 09:14:12 youngjoo38 Exp $
 * @since   1.0
 * @spring.bean id="workCalPmPtrlMonthListDAOTarget"
 * @spring.txbn id="workCalPmPtrlMonthListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkCalPmPtrlMonthListDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkCalPmPtrlMonthListDAO
{
	/**
     * grid find
     * @author  youngjoo38
     * @version $Id: WorkCalPmPtrlMonthListDAO.java,v 1.0 2015/12/02 09:14:12 youngjoo38 Exp $
     * @since   1.0
     *
     * @param workCalPmPtrlMonthCommonDTO
     * @return List
     */
    public List findSchedList(WorkCalPmPtrlMonthCommonDTO workCalPmPtrlMonthCommonDTO, User user)
    {

        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                                        ");
        query.append("      ''                              AS seqNo                                ");
        query.append("      ,''                             AS isDelCheck                           ");
        query.append("      , x.sched_date                    AS schedDate                          ");
        query.append("      , y.pmptrlrsltlist_id             AS PMPTRLRSLTLISTID                   ");
        query.append("      , x.pmptrlsched_id                AS PMPTRLSCHEDID                      ");
        query.append("      , x.pm_id                         AS ptrlWorkNo                         ");
        query.append("      , y.description                   AS ptrlWorkTitle                      ");
        query.append("      , y.dept_id                       AS deptId                             ");
        query.append("      , (SELECT a.description FROM TADEPT a WHERE y.dept_id = a.dept_id)                AS dept                   ");
        query.append("      , y.shift_type                  AS shiftType                            ");
        query.append("      , y.pmsched_status                AS proStatusId                        ");
        query.append("      , dbo.SFACODE_TO_DESC(y.pmsched_status, 'PMSCHED_STATUS', 'SYS', y.comp_no, '"+user.getLangId()+"')   AS proStatus                ");
        query.append("      , y.end_date + ' ' + y.end_time   AS ptrlCompleteDate                 ");
        query.append("      , y.emp_id                        AS managerId                          ");
        query.append("      , (SELECT emp_name                                                      ");
        query.append("         FROM TAEMP a                                                         ");
        query.append("         WHERE y.emp_id = a.emp_id)   AS managerDesc                          ");
        query.append("      , y.close_id                      AS ptrlInspectorId                    ");
        query.append("      , (SELECT emp_name                                                      ");
        query.append("         FROM TAEMP a                                                         ");
        query.append("         WHERE y.close_id = a.emp_id) AS ptrlInspector                        ");
        query.append("      , y.REMARK                        AS REMARK                             ");
        query.append("      , y.pm_type                       AS pmType                             ");
        query.append("      ,(SELECT param2 FROM TACDSYSD WHERE cdsysd_no=y.pm_type AND list_type= y.wo_type+'_TYPE') param  ");
        query.append("      , (SELECT emp_id FROM TAPMPTRLRSLTLIST WHERE pm_id = x.pm_id AND pmptrlrsltlist_id = x.pmptrlrsltlist_id) empId            ");
        query.append("      , (SELECT emp_name FROM TAEMP WHERE emp_id = (SELECT emp_id FROM TAPMPTRLRSLTLIST WHERE pm_id = x.pm_id AND pmptrlrsltlist_id = x.pmptrlrsltlist_id)) empDesc      ");

        query.append("FROM TAPMPTRLSCHED x JOIN TAPMPTRLRSLTLIST y                                  ");
        query.append("ON x.pmptrlrsltlist_id = y.pmptrlrsltlist_id                                  ");
        query.append("WHERE  1=1                                                                    ");

        query.append(this.getWhere(workCalPmPtrlMonthCommonDTO,user));
        query.getOrderByQuery("x.pmptrlsched_id", "x.pm_id", workCalPmPtrlMonthCommonDTO.getOrderBy(), workCalPmPtrlMonthCommonDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(workCalPmPtrlMonthCommonDTO.getIsLoadMaxCount(), workCalPmPtrlMonthCommonDTO.getFirstRow()));
    }

    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id: WorkCalPmPtrlMonthListDAO.java,v 1.0 2015/12/02 09:14:12 youngjoo38 Exp $
     * @since   1.0
     *
     * @param day
     * @param status
     * @param compNo
     * @param deptId
     * @param deptDesc
     * @return
     * @throws Exception
     */
    private String getWhere(WorkCalPmPtrlMonthCommonDTO workCalPmPtrlMonthCommonDTO,User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String yyyymmdd    = CommonUtil.getRowDateToNum(workCalPmPtrlMonthCommonDTO.getYyyymmdd());
        
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        
        if(!"".equals(workCalPmPtrlMonthCommonDTO.getPmPtrlSchedId()) || !"".equals(workCalPmPtrlMonthCommonDTO.getPmPtrlRsltListId())){
            query.getAndQuery("x.pmptrlsched_id", workCalPmPtrlMonthCommonDTO.getPmPtrlSchedId());
            query.getAndQuery("x.pmptrlrsltlist_id", workCalPmPtrlMonthCommonDTO.getPmPtrlRsltListId());
            return query.toString();
        }
        
        query.append("  AND x.sched_date like        '"+yyyymmdd+"%'             ");
        
        // 순회업무명
        query.getLikeQuery("y.description", workCalPmPtrlMonthCommonDTO.getPtrlWorkTitle());

        // 부서
        query.getDeptLevelQuery("y.dept_id", workCalPmPtrlMonthCommonDTO.getDeptId(), workCalPmPtrlMonthCommonDTO.getDeptDesc(), user.getCompNo());
        
        query.getSysCdQuery("x.pmsched_status", workCalPmPtrlMonthCommonDTO.getSchedType(), "", "PMSCHED_STATUS", user.getCompNo(), user.getLangId());
        
        // 작업그룹
        query.getAndQuery("y.wkctr_id", workCalPmPtrlMonthCommonDTO.getPtrlWorkGrpId());
        
        // 담당자
        query.getCodeLikeQuery("(SELECT emp_id FROM TAPMPTRLRSLTLIST "
                + "WHERE pm_id = x.pm_id AND pmptrlrsltlist_id = x.pmptrlrsltlist_id)"
                , "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = (SELECT emp_id FROM TAPMPTRLRSLTLIST WHERE pm_id = x.pm_id AND pmptrlrsltlist_id = x.pmptrlrsltlist_id)"
                        + "  AND a.comp_no='"+user.getCompNo()+"')", workCalPmPtrlMonthCommonDTO.getManagerId(), workCalPmPtrlMonthCommonDTO.getManagerDesc());
        
        // 순회업무#
        query.getLikeQuery("x.pm_id", workCalPmPtrlMonthCommonDTO.getPtrlWorkNo());
        
        return query.toString();
    }

    /**
     * delete
     * @author youngjoo38
     * @version $Id: WorkCalPmPtrlMonthListDAO.java,v 1.0 2017/09/24 08:25:47 youngjoo38 Exp $
     * @since   1.0
     *
     * @param id
     * @param compNo
     * @return
     */
    public int deleteSched(String id, User user)
    {
    	
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	String pmPtrlSchedId = id;
        
        int result = 0;
        query.append("DELETE FROM TAPMPTRLSCHED     ");
        query.append("WHERE comp_no = ?             ");
        query.append("  AND pmptrlsched_id =  ?     ");
        Object[] objects = new Object[]{
                user.getCompNo()
                ,pmPtrlSchedId
        };
        result = this.getJdbcTemplate().update(query.toString(),objects);
        
        query.setClear();
        query.append("DELETE FROM TAPMPTRLRSLTLIST   ");
        query.append("WHERE comp_no = ?              ");
        query.append("  AND pmptrlsched_id =  ?      ");
        objects = new Object[]{
                user.getCompNo()
                ,pmPtrlSchedId
        };
        result = this.getJdbcTemplate().update(query.toString(),objects);
        
        return  this.getJdbcTemplate().update(query.toString(),objects);
    }

    @Override
    public String findTotalCount(WorkCalPmPtrlMonthCommonDTO workCalPmPtrlMonthCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                            					");
        query.append("       COUNT(1)                                   					");
        query.append("	FROM TAPMPTRLSCHED x JOIN TAPMPTRLRSLTLIST y                        ");
        query.append("						   ON x.pmptrlrsltlist_id = y.pmptrlrsltlist_id	");
        query.append(" WHERE 1 = 1                                                          ");
        query.append(this.getWhere(workCalPmPtrlMonthCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}