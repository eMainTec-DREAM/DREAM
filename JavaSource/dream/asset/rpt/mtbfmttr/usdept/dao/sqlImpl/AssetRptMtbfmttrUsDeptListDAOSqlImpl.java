package dream.asset.rpt.mtbfmttr.usdept.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.asset.rpt.mtbfmttr.usdept.dao.AssetRptMtbfmttrUsDeptListDAO;
import dream.asset.rpt.mtbfmttr.usdept.dto.AssetRptMtbfmttrUsDeptCommonDTO;

/**
 * MTBF,MTTR(사용부서) 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="assetRptMtbfmttrUsDeptListDAOTarget"
 * @spring.txbn id="assetRptMtbfmttrUsDeptListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetRptMtbfmttrUsDeptListDAOSqlImpl extends BaseJdbcDaoSupportSql implements AssetRptMtbfmttrUsDeptListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptMtbfmttrUsDeptCommonDTO
     * @param loginUser
     * @return List
     * @throws Exception 
     */
    public List findList(AssetRptMtbfmttrUsDeptCommonDTO assetRptMtbfmttrUsDeptCommonDTO, User loginUser) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String startDate = CommonUtil.getRowDateToNum(assetRptMtbfmttrUsDeptCommonDTO.getFilterStartDate());
        String endDate = CommonUtil.getRowDateToNum(assetRptMtbfmttrUsDeptCommonDTO.getFilterEndDate());
        if(startDate.length()>=6) startDate = startDate + "01";
        if(endDate.length()>=6) endDate = endDate + DateUtil.getLastDayOfMonth(endDate.substring(0, 4), endDate.substring(4, 6));
        
        query.append("WITH bm_info AS(                                                                                                  ");
        query.append("SELECT                                                                                                            ");
        query.append("    a.comp_no                                                                                                     ");
        query.append("    ,a.usage_dept                                                                                                 ");
        query.append("    ,SUM(CASE WHEN b.eqhistory_id IS NULL THEN 0 ELSE 1 END)  bm_cnt                                              ");
        query.append("    ,SUM(b.work_time)                                         work_time                                           ");
        query.append("    ,SUM(b.eqdn_work_time)/60                                 eqdn_work_time                                      ");
        query.append("FROM TAEQUIPMENT a LEFT OUTER JOIN TAEQHISTORY b                                                                  ");
        query.append("ON a.comp_no = b.comp_no AND a.item_no = b.item_no AND a.is_last_version = 'Y' AND a.is_deleted = 'N' AND b.wo_type = 'BM'  ");
        query.getAndDateQuery("b.wkor_date", startDate, endDate);
        query.append("WHERE 1=1                                                                                                         ");
        query.append("AND a.is_deleted = 'N'                                                                                            ");
        query.append("AND a.is_last_version = 'Y'                                                                                       ");
        query.append("AND a.usage_dept IS NOT NULL                                                                                      ");
        query.append("GROUP BY                                                                                                          ");
        query.append("    a.comp_no                                                                                                     ");
        query.append("    ,a.usage_dept                                                                                                 ");
        query.append(")                                                                                                                 ");
        query.append("SELECT                                                                                                            ");
        query.append("    ''                                                                                    SEQNO                   ");
        query.append("    ,''                                                                                   ISDELCHECK              ");
        query.append("    ,a.usage_dept                                                                         usageDeptId             ");
        query.append("    ,(SELECT description FROM TADEPT                                                                              ");
        query.append("      WHERE comp_no = a.comp_no AND dept_id = a.usage_dept)                               usageDeptDesc           ");
        query.append("    ,ROUND(a.run_time/CASE b.bm_cnt WHEN 0 THEN 1 ELSE b.bm_cnt END, 2)                   mtbf                    ");
        query.append("    ,ROUND(ISNULL(b.work_time,0)/CASE b.bm_cnt WHEN 0 THEN 1 ELSE b.bm_cnt END, 2)        mttr                    ");
        query.append("    ,CASE a.run_time WHEN 0 THEN 0 ELSE ROUND(ISNULL(b.eqdn_work_time,0)/a.run_time, 2) END   failureRate         ");
        query.append("FROM (                                                                                                            ");
        query.append("    SELECT                                                                                                        ");
        query.append("        a.comp_no                                                                                                 ");
        query.append("        ,a.usage_dept                                                                                             ");
        query.append("        ,SUM(c.dtime+c.ntime+c.etime)/60  run_time                                                                ");
        query.append("    FROM TAEQUIPMENT a INNER JOIN TALNWRKLIST b                                                                   ");
        query.append("    ON a.comp_no = b.comp_no AND a.lnwrklist_id = b.lnwrklist_id                                                  ");
        query.append("    INNER JOIN TALNWRKTIME c                                                                                      ");
        query.append("    ON b.comp_no = c.comp_no AND b.lnwrklist_id = c.lnwrklist_id                                                  ");
        query.getAndDateQuery("c.wrk_date", startDate, endDate);
        query.append("    WHERE 1=1                                                                                                     ");
        query.append(this.getWhere(assetRptMtbfmttrUsDeptCommonDTO,loginUser));
        query.append("    GROUP BY                                                                                                      ");
        query.append("        a.comp_no                                                                                                 ");
        query.append("        ,a.usage_dept                                                                                             ");
        query.append(") a LEFT OUTER JOIN bm_info b                                                                                     ");
        query.append("ON a.comp_no=b.comp_no AND a.usage_dept = b.usage_dept                                                            ");
        query.append("WHERE 1=1                                                                                                         ");
        query.getOrderByQuery("a.comp_no", "a.usage_dept", assetRptMtbfmttrUsDeptCommonDTO.getOrderBy(), assetRptMtbfmttrUsDeptCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(assetRptMtbfmttrUsDeptCommonDTO.getIsLoadMaxCount(), assetRptMtbfmttrUsDeptCommonDTO.getFirstRow()));
    }
    
    private String getWhere(AssetRptMtbfmttrUsDeptCommonDTO assetRptMtbfmttrUsDeptCommonDTO,User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("a.comp_no", loginUser.getCompNo());
        query.append("AND a.is_deleted = 'N'                                         ");
        query.append("AND a.is_last_version = 'Y'                                    ");
        query.append("AND a.usage_dept IS NOT NULL                                   ");
        
        query.getDeptLevelQuery("a.usage_dept", assetRptMtbfmttrUsDeptCommonDTO.getFilterUsageDeptId(), assetRptMtbfmttrUsDeptCommonDTO.getFilterUsageDeptDesc(), loginUser.getCompNo());
        
        query.getCodeLikeQuery("a.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = a.comp_no AND plant = a.plant )", 
                assetRptMtbfmttrUsDeptCommonDTO.getFilterPlantId(), assetRptMtbfmttrUsDeptCommonDTO.getFilterPlantDesc());
        
        return query.toString();
    }
    
    @Override
    public String findTotalCount(AssetRptMtbfmttrUsDeptCommonDTO assetRptMtbfmttrUsDeptCommonDTO, User loginUser) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String startDate = CommonUtil.getRowDateToNum(assetRptMtbfmttrUsDeptCommonDTO.getFilterStartDate());
        String endDate = CommonUtil.getRowDateToNum(assetRptMtbfmttrUsDeptCommonDTO.getFilterEndDate());
        if(startDate.length()>=6) startDate = startDate + "01";
        if(endDate.length()>=6) endDate = endDate + DateUtil.getLastDayOfMonth(endDate.substring(0, 4), endDate.substring(4, 6));
        
        query.append("SELECT COUNT(1)                                                    ");
        query.append("FROM (                                                             ");
        query.append("    SELECT                                                         ");
        query.append("        a.comp_no                                                  ");
        query.append("        ,a.usage_dept                                              ");
        query.append("    FROM TAEQUIPMENT a INNER JOIN TALNWRKLIST b                    ");
        query.append("    ON a.comp_no = b.comp_no AND a.lnwrklist_id = b.lnwrklist_id   ");
        query.append("    INNER JOIN TALNWRKTIME c                                       ");
        query.append("    ON b.comp_no = c.comp_no AND b.lnwrklist_id = c.lnwrklist_id   ");
        query.getAndDateQuery("c.wrk_date", startDate, endDate);
        query.append("    WHERE 1=1                                                      ");
        query.append(this.getWhere(assetRptMtbfmttrUsDeptCommonDTO,loginUser));
        query.append("    GROUP BY                                                       ");
        query.append("        a.comp_no                                                  ");
        query.append("        ,a.usage_dept                                              ");
        query.append(") a                                                                ");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
    
}