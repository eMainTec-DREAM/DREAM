package dream.work.rpt.pmwcmptrate.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.work.rpt.pmwcmptrate.dao.WorkRptPmwCmptDetailDAO;
import dream.work.rpt.pmwcmptrate.dto.WorkRptPmwCmptDetailDTO;

/**
 * 주기정비 계획대비 실행 비율 상세 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="workRptPmwCmptDetailDAOTarget"
 * @spring.txbn id="workRptPmwCmptDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptPmwCmptDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkRptPmwCmptDetailDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPmwCmptDetailDTO
     * @param loginUser
     * @return List
     */
    public List findDetail(WorkRptPmwCmptDetailDTO workRptPmwCmptDetailDTO, User loginUser) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT        ");
        query.append("    '' SEQNO      ");
        query.append("    ,a.wkor_id    ");
        query.append("    ,a.wo_no      ");
        query.append("    ,a.description        ");
        query.append("    ,a.start_date     ");
        query.append("    ,a.end_date       ");
        query.append("    ,b.equip_id       ");
        query.append("    ,(SELECT aa.eqctg_type FROM taequipment aa WHERE b.comp_no = aa.comp_no AND b.equip_id = aa.equip_id) AS eqctg_type     ");
        query.append("    ,(SELECT aa.item_no FROM taequipment aa WHERE b.comp_no = aa.comp_no AND b.equip_id = aa.equip_id) AS item_no     ");
        query.append("    ,(SELECT aa.description FROM taequipment aa WHERE b.comp_no = aa.comp_no AND b.equip_id = aa.equip_id) AS item_desc       ");
        query.append("    ,(SELECT aa.description FROM tadept aa WHERE a.comp_no = aa.comp_no AND a.dept_id = aa.dept_id) AS dept_desc      ");
        query.append("    ,(SELECT aa.emp_name FROM taemp aa WHERE a.comp_no = aa.comp_no AND a.emp_id = aa.emp_id) AS emp_name         ");
        query.append("    ,b.sched_date     ");
        query.append("    ,(select param1 FROM tacdsysd where list_Type= a.wo_type+'_TYPE' AND cdsysd_no=a.pm_type)  param ");
        query.append("FROM taworkorder a INNER JOIN TAPMSCHED b ON a.comp_no = b.comp_no AND a.pmsched_id = b.pmsched_id        ");
        query.append("                   INNER JOIN tawoequip c ON a.comp_no = c.comp_no AND a.wkor_id = c.wkor_id      ");
        query.append("                   INNER JOIN TAPMLST d ON b.comp_no = d.comp_no AND b.pm_id = d.pm_id      ");
        query.append("                   INNER JOIN TAPMEQUIP e ON d.comp_no = e.comp_no AND d.pm_id = e.pm_id AND b.pmequip_id = e.pmequip_id     ");
        query.append("WHERE 1=1     ");
        query.append(this.getWhere(workRptPmwCmptDetailDTO,loginUser));
        query.getOrderByQuery("a.wkor_id", "a.wkor_date ASC", workRptPmwCmptDetailDTO.getOrderBy(), workRptPmwCmptDetailDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workRptPmwCmptDetailDTO.getIsLoadMaxCount(), workRptPmwCmptDetailDTO.getFirstRow()));
    }
    
    public String getWhere(WorkRptPmwCmptDetailDTO workRptPmwCmptDetailDTO,User loginUser) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append(" AND b.is_deleted = 'N'       ");
        query.append(" AND d.is_deleted = 'N'       ");
        query.append(" AND e.is_deleted = 'N'       ");
        query.append(" AND b.pmsched_status = 'C'       ");
        query.append(" AND a.wo_status IN('PRC', 'C')       ");
        query.append(" AND a.wo_type = 'PMW'        ");
        query.append("  AND d.plant IS NOT NULL     ");
        
        query.getStringEqualQuery("a.comp_no", loginUser.getCompNo());
        
        // 월
        String yyyymm = workRptPmwCmptDetailDTO.getYyyymm();
        
        if (yyyymm != null && !"".equals(yyyymm))
        {
            // toMonth가 오늘이후면 오늘까지만 조회
            if(DateUtil.compareDate(DateUtil.getDate(), DateUtil.plusLastDayOfMonth(yyyymm)))
            {
                query.getAndDateQuery("b.sched_date", yyyymm +"01", DateUtil.getDate());
            }
            else{
                query.getAndDateQuery("b.sched_date", yyyymm +"01", DateUtil.plusLastDayOfMonth(yyyymm));
            }
        }
        
        query.getAndQuery("d.plant", workRptPmwCmptDetailDTO.getPlantId());
        
        return query.toString();
    }
    
    public String findTotalCount(WorkRptPmwCmptDetailDTO workRptPmwCmptDetailDTO, User loginUser) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                          ");
        query.append("    COUNT(1)                                    ");
        query.append("FROM taworkorder a INNER JOIN TAPMSCHED b ON a.comp_no = b.comp_no AND a.pmsched_id = b.pmsched_id        ");
        query.append("                   INNER JOIN tawoequip c ON a.comp_no = c.comp_no AND a.wkor_id = c.wkor_id      ");
        query.append("                   INNER JOIN TAPMLST d ON b.comp_no = d.comp_no AND b.pm_id = d.pm_id      ");
        query.append("                   INNER JOIN TAPMEQUIP e ON d.comp_no = e.comp_no AND d.pm_id = e.pm_id AND b.pmequip_id = e.pmequip_id     ");
        query.append("WHERE 1=1     ");
        query.append(this.getWhere(workRptPmwCmptDetailDTO,loginUser));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
    
}