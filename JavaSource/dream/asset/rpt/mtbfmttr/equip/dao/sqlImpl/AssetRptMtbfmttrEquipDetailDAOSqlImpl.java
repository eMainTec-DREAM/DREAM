package dream.asset.rpt.mtbfmttr.equip.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.QuerySqlBuffer;
import dream.asset.rpt.mtbfmttr.equip.dao.AssetRptMtbfmttrEquipDetailDAO;
import dream.asset.rpt.mtbfmttr.equip.dto.AssetRptMtbfmttrEquipCommonDTO;
import dream.asset.rpt.mtbfmttr.equip.dto.AssetRptMtbfmttrEquipDetailDTO;

/**
 * MTBF,MTTR(설비) 상세 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="assetRptMtbfmttrEquipDetailDAOTarget"
 * @spring.txbn id="assetRptMtbfmttrEquipDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetRptMtbfmttrEquipDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements AssetRptMtbfmttrEquipDetailDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptMtbfmttrEquipDetailDTO
     * @param loginUser
     * @return List
     */
    public List findDetail(AssetRptMtbfmttrEquipCommonDTO assetRptMtbfmttrEquipCommonDTO,AssetRptMtbfmttrEquipDetailDTO assetRptMtbfmttrEquipDetailDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("WITH bm_info AS(                                                                                                  ");
        query.append("SELECT                                                                                                            ");
        query.append("    a.comp_no                                                                                                     ");
        query.append("    ,mon.tmonth                                                                                                   ");
        query.append("    ,a.equip_id                                                                                                   ");
        query.append("    ,SUM(CASE WHEN b.eqhistory_id IS NULL THEN 0 ELSE 1 END)  bm_cnt                                              ");
        query.append("    ,SUM(b.work_time)                                         work_time                                           ");
        query.append("    ,SUM(b.eqdn_work_time)/60                                 eqdn_work_time                                      ");
        query.append("FROM TAEQUIPMENT a INNER JOIN TAMONTH mon                                                                         ");
        query.append("ON 1=1                                                                                                            ");
        query.getAndDateQuery("mon.tmonth", CommonUtil.getRowDateToNum(assetRptMtbfmttrEquipDetailDTO.getStartDate()), CommonUtil.getRowDateToNum(assetRptMtbfmttrEquipDetailDTO.getEndDate()));
        query.append("LEFT OUTER JOIN TAEQHISTORY b                                                                                     ");
        query.append("ON a.comp_no = b.comp_no AND a.item_no = b.item_no AND a.is_last_version = 'Y' AND a.is_deleted = 'N' AND b.wo_type = 'BM' AND b.wkor_date LIKE mon.tmonth+'%' ");
        query.append("WHERE a.comp_no = '"+loginUser.getCompNo()+"'                                                                     ");
        query.append("AND a.equip_id = '"+assetRptMtbfmttrEquipDetailDTO.getEquipId()+"'                                                ");
        query.append("AND a.is_deleted = 'N'                                                                                            ");
        query.append("AND a.is_last_version = 'Y'                                                                                       ");
        query.append("GROUP BY                                                                                                          ");
        query.append("    a.comp_no                                                                                                     ");
        query.append("    ,mon.tmonth                                                                                                   ");
        query.append("    ,a.equip_id                                                                                                   ");
        query.append(")                                                                                                                 ");
        query.append("SELECT                                                                                                            ");
        query.append("    SUBSTRING(a.tmonth,1,4)+'-'+SUBSTRING(a.tmonth,5,2)                           month                           ");
        query.append("    ,a.equip_id                                                                   equipId                         ");
        query.append("    ,a.eqloc_id                                                                   eqlocId                         ");
        query.append("    ,(SELECT full_desc FROM TAEQLOC                                                                               ");
        query.append("      WHERE comp_no = a.comp_no AND eqloc_id = a.eqloc_id)                        eqlocDesc                       ");
        query.append("    ,ROUND(a.run_time/CASE b.bm_cnt WHEN 0 THEN 1 ELSE b.bm_cnt END, 2)           mtbf                            ");
        query.append("    ,ROUND(ISNULL(b.work_time,0)/CASE b.bm_cnt WHEN 0 THEN 1 ELSE b.bm_cnt END, 2)    mttr                        ");
        query.append("    ,CASE a.run_time WHEN 0 THEN 0 ELSE ROUND(ISNULL(b.eqdn_work_time,0)/a.run_time, 2) END failureRate           ");
        query.append("FROM (                                                                                                            ");
        query.append("    SELECT                                                                                                        ");
        query.append("        a.comp_no                                                                                                 ");
        query.append("        ,mon.tmonth                                                                                               ");
        query.append("        ,a.equip_id                                                                                               ");
        query.append("        ,a.eqloc_id                                                                                               ");
        query.append("        ,a.lnwrklist_id                                                                                           ");
        query.append("        ,a.is_deleted                                                                                             ");
        query.append("        ,a.is_last_version                                                                                        ");
        query.append("        ,SUM(c.dtime+c.ntime+c.etime)/60  run_time                                                                ");
        query.append("    FROM TAEQUIPMENT a INNER JOIN TAMONTH mon                                                                     ");
        query.append("    ON 1=1                                                                                                        ");
        query.getAndDateQuery("mon.tmonth", CommonUtil.getRowDateToNum(assetRptMtbfmttrEquipDetailDTO.getStartDate()), CommonUtil.getRowDateToNum(assetRptMtbfmttrEquipDetailDTO.getEndDate()));
        query.append("    INNER JOIN TALNWRKLIST b                                                                                      ");
        query.append("    ON a.comp_no = b.comp_no AND a.lnwrklist_id = b.lnwrklist_id                                                  ");
        query.append("    INNER JOIN TALNWRKTIME c                                                                                      ");
        query.append("    ON b.comp_no = c.comp_no AND b.lnwrklist_id = c.lnwrklist_id AND c.wrk_date LIKE mon.tmonth+'%'               ");
        query.append("    WHERE a.comp_no = '"+loginUser.getCompNo()+"'                                                                 ");
        query.append("    AND a.equip_id = '"+assetRptMtbfmttrEquipDetailDTO.getEquipId()+"'                                            ");
        query.append("    AND a.is_deleted = 'N'                                                                                        ");
        query.append("    AND a.is_last_version = 'Y'                                                                                   ");
        query.append("    GROUP BY                                                                                                      ");
        query.append("        a.comp_no                                                                                                 ");
        query.append("        ,mon.tmonth                                                                                               ");
        query.append("        ,a.equip_id                                                                                               ");
        query.append("        ,a.eqloc_id                                                                                               ");
        query.append("        ,a.lnwrklist_id                                                                                           ");
        query.append("        ,a.is_deleted                                                                                             ");
        query.append("        ,a.is_last_version                                                                                        ");
        query.append(") a LEFT OUTER JOIN bm_info b                                                                                     ");
        query.append("ON a.comp_no=b.comp_no AND a.equip_id = b.equip_id AND a.tmonth = b.tmonth                                        ");
        query.getOrderByQuery("a.equip_id", "a.tmonth", assetRptMtbfmttrEquipDetailDTO.getOrderBy(), assetRptMtbfmttrEquipDetailDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(assetRptMtbfmttrEquipDetailDTO.getIsLoadMaxCount(), assetRptMtbfmttrEquipDetailDTO.getFirstRow()));
    }
    
    @Override
    public String findTotalCount(AssetRptMtbfmttrEquipCommonDTO assetRptMtbfmttrEquipCommonDTO, AssetRptMtbfmttrEquipDetailDTO assetRptMtbfmttrEquipDetailDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("WITH bm_info AS(                                                                                                  ");
        query.append("SELECT                                                                                                            ");
        query.append("    a.comp_no                                                                                                     ");
        query.append("    ,mon.tmonth                                                                                                   ");
        query.append("    ,a.equip_id                                                                                                   ");
        query.append("    ,SUM(CASE WHEN b.eqhistory_id IS NULL THEN 0 ELSE 1 END)  bm_cnt                                              ");
        query.append("    ,SUM(b.work_time)                                         work_time                                           ");
        query.append("    ,SUM(b.eqdn_work_time)/60                                 eqdn_work_time                                      ");
        query.append("FROM TAEQUIPMENT a INNER JOIN TAMONTH mon                                                                         ");
        query.append("ON 1=1                                                                                                            ");
        query.getAndDateQuery("mon.tmonth", CommonUtil.getRowDateToNum(assetRptMtbfmttrEquipDetailDTO.getStartDate()), CommonUtil.getRowDateToNum(assetRptMtbfmttrEquipDetailDTO.getEndDate()));
        query.append("LEFT OUTER JOIN TAEQHISTORY b                                                                                     ");
        query.append("ON a.comp_no = b.comp_no AND a.item_no = b.item_no AND a.is_last_version = 'Y' AND a.is_deleted = 'N' AND b.wo_type = 'BM' AND b.wkor_date LIKE mon.tmonth+'%' ");
        query.append("WHERE a.comp_no = '"+user.getCompNo()+"'                                                                     ");
        query.append("AND a.equip_id = '"+assetRptMtbfmttrEquipDetailDTO.getEquipId()+"'                                                ");
        query.append("AND a.is_deleted = 'N'                                                                                            ");
        query.append("AND a.is_last_version = 'Y'                                                                                       ");
        query.append("GROUP BY                                                                                                          ");
        query.append("    a.comp_no                                                                                                     ");
        query.append("    ,mon.tmonth                                                                                                   ");
        query.append("    ,a.equip_id                                                                                                   ");
        query.append(")                                                                                                                 ");
        query.append("SELECT                                                                                                            ");
        query.append("    COUNT(1)                                                                                                      ");
        query.append("FROM (                                                                                                            ");
        query.append("    SELECT                                                                                                        ");
        query.append("        a.comp_no                                                                                                 ");
        query.append("        ,mon.tmonth                                                                                               ");
        query.append("        ,a.equip_id                                                                                               ");
        query.append("        ,a.eqloc_id                                                                                               ");
        query.append("        ,a.lnwrklist_id                                                                                           ");
        query.append("        ,a.is_deleted                                                                                             ");
        query.append("        ,a.is_last_version                                                                                        ");
        query.append("        ,SUM(c.dtime+c.ntime+c.etime)/60  run_time                                                                ");
        query.append("    FROM TAEQUIPMENT a INNER JOIN TAMONTH mon                                                                     ");
        query.append("    ON 1=1                                                                                                        ");
        query.getAndDateQuery("mon.tmonth", CommonUtil.getRowDateToNum(assetRptMtbfmttrEquipDetailDTO.getStartDate()), CommonUtil.getRowDateToNum(assetRptMtbfmttrEquipDetailDTO.getEndDate()));
        query.append("    INNER JOIN TALNWRKLIST b                                                                                      ");
        query.append("    ON a.comp_no = b.comp_no AND a.lnwrklist_id = b.lnwrklist_id                                                  ");
        query.append("    INNER JOIN TALNWRKTIME c                                                                                      ");
        query.append("    ON b.comp_no = c.comp_no AND b.lnwrklist_id = c.lnwrklist_id AND c.wrk_date LIKE mon.tmonth+'%'               ");
        query.append("    WHERE a.comp_no = '"+user.getCompNo()+"'                                                                 ");
        query.append("    AND a.equip_id = '"+assetRptMtbfmttrEquipDetailDTO.getEquipId()+"'                                            ");
        query.append("    AND a.is_deleted = 'N'                                                                                        ");
        query.append("    AND a.is_last_version = 'Y'                                                                                   ");
        query.append("    GROUP BY                                                                                                      ");
        query.append("        a.comp_no                                                                                                 ");
        query.append("        ,mon.tmonth                                                                                               ");
        query.append("        ,a.equip_id                                                                                               ");
        query.append("        ,a.eqloc_id                                                                                               ");
        query.append("        ,a.lnwrklist_id                                                                                           ");
        query.append("        ,a.is_deleted                                                                                             ");
        query.append("        ,a.is_last_version                                                                                        ");
        query.append(") a LEFT OUTER JOIN bm_info b                                                                                     ");
        query.append("ON a.comp_no=b.comp_no AND a.equip_id = b.equip_id AND a.tmonth = b.tmonth                                        ");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}