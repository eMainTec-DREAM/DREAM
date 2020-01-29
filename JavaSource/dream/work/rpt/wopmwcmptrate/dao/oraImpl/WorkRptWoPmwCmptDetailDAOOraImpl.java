package dream.work.rpt.wopmwcmptrate.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.work.rpt.wopmwcmptrate.dao.WorkRptWoPmwCmptDetailDAO;
import dream.work.rpt.wopmwcmptrate.dto.WorkRptWoPmwCmptDetailDTO;

/**
 * 예방작업 계획대비 실행 비율 상세 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="workRptWoPmwCmptDetailDAOTarget"
 * @spring.txbn id="workRptWoPmwCmptDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptWoPmwCmptDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkRptWoPmwCmptDetailDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptWoPmwCmptDetailDTO
     * @param loginUser
     * @return List
     */
    public List findDetail(WorkRptWoPmwCmptDetailDTO workRptWoPmwCmptDetailDTO, User loginUser) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT        ");
        query.append("     '' SEQNO    ");
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
        query.append("    ,NVL(NVL(b.sched_date,c.start_date),a.start_date) AS sched_date       ");
        query.append("    ,(select param1 FROM tacdsysd where list_Type= a.wo_type||'_TYPE' AND cdsysd_no=a.pm_type)  param ");
        query.append("FROM taworkorder a LEFT OUTER JOIN tapmsched b ON a.comp_no = b.comp_no AND a.pmsched_id = b.pmsched_id       ");
        query.append("                   LEFT OUTER JOIN tawoplan c ON a.comp_no = c.comp_no AND a.wkor_id = c.wkor_id      ");
        query.append("WHERE 1=1     ");
        query.append(this.getWhere(workRptWoPmwCmptDetailDTO,loginUser));
        query.getOrderByQuery("a.wkor_date ASC", workRptWoPmwCmptDetailDTO.getOrderBy(), workRptWoPmwCmptDetailDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workRptWoPmwCmptDetailDTO.getIsLoadMaxCount(), workRptWoPmwCmptDetailDTO.getFirstRow()));
    }
    
    public String getWhere(WorkRptWoPmwCmptDetailDTO workRptWoPmwCmptDetailDTO,User loginUser) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append(" AND a.is_deleted = 'N'       ");
        query.append(" AND a.wo_status IN('PRC', 'C')       ");
        query.append(" AND a.wo_type = 'PMW'        ");
        
        query.getStringEqualQuery("a.comp_no", loginUser.getCompNo());
        
        // 월
        String yyyymm = workRptWoPmwCmptDetailDTO.getYyyymm();
        
        if (yyyymm != null && !"".equals(yyyymm))
        {
            // toMonth가 오늘이후면 오늘까지만 조회
            if(DateUtil.compareDate(DateUtil.getDate(), DateUtil.plusLastDayOfMonth(yyyymm)))
            {
                query.getAndDateQuery("a.wkor_date", yyyymm +"01", DateUtil.getDate());
            }
            else{
                query.getAndDateQuery("a.wkor_date", yyyymm +"01", DateUtil.plusLastDayOfMonth(yyyymm));
            }
        }
        
        query.getAndQuery("a.plant", workRptWoPmwCmptDetailDTO.getPlantId());
        
        return query.toString();
    }
    
    public String findTotalCount(WorkRptWoPmwCmptDetailDTO workRptWoPmwCmptDetailDTO, User loginUser) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                          ");
        query.append("    COUNT(1)                                    ");
        query.append("FROM taworkorder a LEFT OUTER JOIN tapmsched b ON a.comp_no = b.comp_no AND a.pmsched_id = b.pmsched_id       ");
        query.append("                   LEFT OUTER JOIN tawoplan c ON a.comp_no = c.comp_no AND a.wkor_id = c.wkor_id      ");
        query.append("WHERE 1=1     ");
        query.append(this.getWhere(workRptWoPmwCmptDetailDTO,loginUser));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
    
}