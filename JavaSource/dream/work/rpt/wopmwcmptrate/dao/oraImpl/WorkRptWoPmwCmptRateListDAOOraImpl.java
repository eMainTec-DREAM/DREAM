package dream.work.rpt.wopmwcmptrate.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.work.rpt.wopmwcmptrate.dao.WorkRptWoPmwCmptRateListDAO;
import dream.work.rpt.wopmwcmptrate.dto.WorkRptWoPmwCmptRateCommonDTO;

/**
 * �����۾� ��ȹ��� ���� ���� ���  - List DAO implements
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptWoPmwCmptRateListDAOTarget"
 * @spring.txbn id="workRptWoPmwCmptRateListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptWoPmwCmptRateListDAOOraImpl  extends BaseJdbcDaoSupportOra implements WorkRptWoPmwCmptRateListDAO
{

    /**
     * grid find
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptWoPmwCmptRateCommonDTO
     * @return List
     */
    public List findList(WorkRptWoPmwCmptRateCommonDTO workRptWoPmwCmptRateCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT        ");
        query.append("  '' SEQNO    ");
        query.append(" ,a.plant     ");
        query.append(" ,(SELECT description FROM TAPLANT WHERE comp_no = a.comp_no AND plant = a.plant) PLANTDESC     ");
        query.append(" ,SUBSTR(a.wkor_date,1,6) AS yyyymm       ");
        query.append(" ,sum(CASE WHEN (NVL(CASE WHEN b.sched_date IS NOT NULL THEN b.sched_date ELSE c.start_date END,a.start_date)) >= a.start_date  THEN 1 ELSE 0 END) AS c_cnt       ");
        query.append(" ,count(*) AS t_cnt       ");
        query.append(" ,round(      ");
        query.append("      (  sum(CASE WHEN (NVL(CASE WHEN b.sched_date IS NOT NULL THEN b.sched_date ELSE c.start_date END,a.start_date)) >= a.start_date  THEN 1.0 ELSE 0 END)       ");
        query.append("       / count(*)     ");
        query.append("     ) *100,2) AS c_rate      ");
        query.append("FROM taworkorder a LEFT OUTER JOIN tapmsched b ON a.comp_no = b.comp_no AND a.pmsched_id = b.pmsched_id       ");
        query.append("                   LEFT OUTER JOIN tawoplan c ON a.comp_no = c.comp_no AND a.wkor_id = c.wkor_id      ");
        query.append("WHERE 1=1     ");
        query.append(this.getWhere(workRptWoPmwCmptRateCommonDTO, user));
        query.append("GROUP BY a.comp_no,a.plant,SUBSTR(a.wkor_date,1,6)      ");
        query.getOrderByQuery("a.comp_no,a.plant,SUBSTR(a.wkor_date,1,6)", workRptWoPmwCmptRateCommonDTO.getOrderBy(), workRptWoPmwCmptRateCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workRptWoPmwCmptRateCommonDTO.getIsLoadMaxCount(), workRptWoPmwCmptRateCommonDTO.getFirstRow()));
    }

    /**
     * Filter ����
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     *   
     * @param workRptWoPmwCmptRateCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(WorkRptWoPmwCmptRateCommonDTO workRptWoPmwCmptRateCommonDTO, User user) throws Exception
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.append(" AND a.is_deleted = 'N'       ");
        query.append(" AND a.wo_status IN('PRC', 'C')       ");
        query.append(" AND a.wo_type = 'PMW'        ");
        
        query.getStringEqualQuery("a.comp_no", user.getCompNo());
        
        // ��
        String fromMonth = workRptWoPmwCmptRateCommonDTO.getFilterStartDate();
        String toMonth   = workRptWoPmwCmptRateCommonDTO.getFilterEndDate();
        
        if (fromMonth != null && !"".equals(fromMonth) && toMonth != null && !"".equals(toMonth))
        {
            // toMonth�� �������ĸ� ���ñ����� ��ȸ
            if(DateUtil.compareDate(DateUtil.getDate(), DateUtil.plusLastDayOfMonth(toMonth)))
            {
                query.getAndDateQuery("a.wkor_date", fromMonth +"01", DateUtil.getDate());
            }
            else{
                query.getAndDateQuery("a.wkor_date", fromMonth +"01", DateUtil.plusLastDayOfMonth(toMonth));
            }
        }
        
        query.getPlantCdQuery("a.plant", workRptWoPmwCmptRateCommonDTO.getFilterPlantId(), workRptWoPmwCmptRateCommonDTO.getFilterPlantDesc(), user.getCompNo());
        
        return query.toString();
    }

    public String findTotalCount(
            WorkRptWoPmwCmptRateCommonDTO workRptWoPmwCmptRateCommonDTO, User user) throws Exception {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                            	");
        query.append("    		count(1)									");
        query.append("FROM (									            ");
        query.append("SELECT a.comp_no,a.plant,SUBSTR(a.wkor_date,1,6) yyyymm ");
        query.append("FROM taworkorder a LEFT OUTER JOIN tapmsched b ON a.comp_no = b.comp_no AND a.pmsched_id = b.pmsched_id       ");
        query.append("                   LEFT OUTER JOIN tawoplan c ON a.comp_no = c.comp_no AND a.wkor_id = c.wkor_id      ");
        query.append("WHERE 1=1     ");
        query.append(this.getWhere(workRptWoPmwCmptRateCommonDTO, user));
        query.append("GROUP BY a.comp_no,a.plant,SUBSTR(a.wkor_date,1,6)    ");
        query.append(")                                                     ");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}
