package dream.asset.rpt.mtbfmttr.usdept.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import dream.asset.rpt.mtbfmttr.usdept.dao.AssetRptMtbfmttrUsDeptDetailDAO;
import dream.asset.rpt.mtbfmttr.usdept.dto.AssetRptMtbfmttrUsDeptDetailDTO;

/**
 * MTBF,MTTR(사용부서) 상세 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="assetRptMtbfmttrUsDeptDetailDAOTarget"
 * @spring.txbn id="assetRptMtbfmttrUsDeptDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetRptMtbfmttrUsDeptDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements AssetRptMtbfmttrUsDeptDetailDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptMtbfmttrUsDeptDetailDTO
     * @param loginUser
     * @return List
     */
    public List findDetail(AssetRptMtbfmttrUsDeptDetailDTO assetRptMtbfmttrUsDeptDetailDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("WITH bm_info AS(                                                                                                  ");
        query.append("SELECT                                                                                                            ");
        query.append("    a.comp_no                                                                                                     ");
        query.append("    ,mon.tmonth                                                                                                   ");
        query.append("    ,a.usage_dept                                                                                                 ");
        query.append("    ,SUM(CASE WHEN b.eqhistory_id IS NULL THEN 0 ELSE 1 END)  bm_cnt                                              ");
        query.append("    ,SUM(b.work_time)                                         work_time                                           ");
        query.append("    ,SUM(b.eqdn_work_time)/60                                 eqdn_work_time                                      ");
        query.append("FROM TAEQUIPMENT a INNER JOIN TAMONTH mon                                                                         ");
        query.append("ON 1=1                                                                                                            ");
        query.getAndDateQuery("mon.tmonth", CommonUtil.getRowDateToNum(assetRptMtbfmttrUsDeptDetailDTO.getStartDate()), CommonUtil.getRowDateToNum(assetRptMtbfmttrUsDeptDetailDTO.getEndDate()));
        query.append("LEFT OUTER JOIN TAEQHISTORY b                                                                                     ");
        query.append("ON a.comp_no = b.comp_no AND a.item_no = b.item_no AND a.is_last_version = 'Y' AND a.is_deleted = 'N' AND b.wo_type = 'BM' AND b.wkor_date LIKE mon.tmonth||'%' ");
        query.append("WHERE a.comp_no = '"+loginUser.getCompNo()+"'                                                                     ");
        query.append("AND a.usage_dept = '"+assetRptMtbfmttrUsDeptDetailDTO.getUsageDeptId()+"'                                         ");
        query.append("AND a.is_deleted = 'N'                                                                                            ");
        query.append("AND a.is_last_version = 'Y'                                                                                       ");
        query.append("GROUP BY                                                                                                          ");
        query.append("    a.comp_no                                                                                                     ");
        query.append("    ,mon.tmonth                                                                                                   ");
        query.append("    ,a.usage_dept                                                                                                 ");
        query.append(")                                                                                                                 ");
        query.append("SELECT                                                                                                            ");
        query.append("    SUBSTR(a.tmonth,1,4)||'-'||SUBSTR(a.tmonth,5,2)                                      month                    ");
        query.append("    ,a.usage_dept                                                                        usageDeptId              ");
        query.append("    ,(SELECT description FROM TADEPT                                                                              ");
        query.append("      WHERE comp_no = a.comp_no AND dept_id = a.usage_dept)                              usageDeptDesc            ");
        query.append("    ,ROUND(a.run_time/CASE b.bm_cnt WHEN 0 THEN 1 ELSE b.bm_cnt END, 2)                  mtbf                     ");
        query.append("    ,ROUND(NVL(b.work_time,0)/CASE b.bm_cnt WHEN 0 THEN 1 ELSE b.bm_cnt END, 2)          mttr                     ");
        query.append("    ,CASE a.run_time WHEN 0 THEN 0 ELSE ROUND(NVL(b.eqdn_work_time,0)/a.run_time, 2) END failureRate              ");
        query.append("FROM (                                                                                                            ");
        query.append("    SELECT                                                                                                        ");
        query.append("        a.comp_no                                                                                                 ");
        query.append("        ,mon.tmonth                                                                                               ");
        query.append("        ,a.usage_dept                                                                                             ");
        query.append("        ,SUM(c.dtime+c.ntime+c.etime)/60  run_time                                                                ");
        query.append("    FROM TAEQUIPMENT a INNER JOIN TAMONTH mon                                                                     ");
        query.append("    ON 1=1                                                                                                        ");
        query.getAndDateQuery("mon.tmonth", CommonUtil.getRowDateToNum(assetRptMtbfmttrUsDeptDetailDTO.getStartDate()), CommonUtil.getRowDateToNum(assetRptMtbfmttrUsDeptDetailDTO.getEndDate()));
        query.append("    INNER JOIN TALNWRKLIST b                                                                                      ");
        query.append("    ON a.comp_no = b.comp_no AND a.lnwrklist_id = b.lnwrklist_id                                                  ");
        query.append("    INNER JOIN TALNWRKTIME c                                                                                      ");
        query.append("    ON b.comp_no = c.comp_no AND b.lnwrklist_id = c.lnwrklist_id AND c.wrk_date LIKE mon.tmonth||'%'              ");
        query.append("    WHERE a.comp_no = '"+loginUser.getCompNo()+"'                                                                 ");
        query.append("    AND a.usage_dept = '"+assetRptMtbfmttrUsDeptDetailDTO.getUsageDeptId()+"'                                     ");
        query.append("    AND a.is_deleted = 'N'                                                                                        ");
        query.append("    AND a.is_last_version = 'Y'                                                                                   ");
        query.append("    GROUP BY                                                                                                      ");
        query.append("        a.comp_no                                                                                                 ");
        query.append("        ,mon.tmonth                                                                                               ");
        query.append("        ,a.usage_dept                                                                                             ");
        query.append(") a LEFT OUTER JOIN bm_info b                                                                                     ");
        query.append("ON a.comp_no=b.comp_no AND a.usage_dept = b.usage_dept AND a.tmonth = b.tmonth                                    ");
        query.append("ORDER BY a.tmonth                                                                                                 ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
}