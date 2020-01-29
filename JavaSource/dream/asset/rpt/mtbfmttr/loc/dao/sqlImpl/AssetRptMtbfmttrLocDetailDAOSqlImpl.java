package dream.asset.rpt.mtbfmttr.loc.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.QuerySqlBuffer;
import dream.asset.rpt.mtbfmttr.loc.dao.AssetRptMtbfmttrLocDetailDAO;
import dream.asset.rpt.mtbfmttr.loc.dto.AssetRptMtbfmttrLocDetailDTO;

/**
 * MTBF,MTTR(위치) 상세 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="assetRptMtbfmttrLocDetailDAOTarget"
 * @spring.txbn id="assetRptMtbfmttrLocDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetRptMtbfmttrLocDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements AssetRptMtbfmttrLocDetailDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptMtbfmttrLocDetailDTO
     * @param loginUser
     * @return List
     */
    public List findDetail(AssetRptMtbfmttrLocDetailDTO assetRptMtbfmttrLocDetailDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("WITH bm_info AS(                                                                                                  ");
        query.append("SELECT                                                                                                            ");
        query.append("    x.comp_no                                                                                                     ");
        query.append("    ,mon.tmonth                                                                                                   ");
        query.append("    ,x.eqloc_id                                                                                                   ");
        query.append("    ,SUM(CASE WHEN b.eqhistory_id IS NULL THEN 0 ELSE 1 END)  bm_cnt                                              ");
        query.append("    ,SUM(b.work_time)                                         work_time                                           ");
        query.append("    ,SUM(b.eqdn_work_time)/60                                 eqdn_work_time                                      ");
        query.append("FROM TAEQLOC x INNER JOIN TAMONTH mon                                                                             ");
        query.append("ON 1=1                                                                                                            ");
        query.getAndDateQuery("mon.tmonth", CommonUtil.getRowDateToNum(assetRptMtbfmttrLocDetailDTO.getStartDate()), CommonUtil.getRowDateToNum(assetRptMtbfmttrLocDetailDTO.getEndDate()));
        query.append("LEFT OUTER JOIN TAEQUIPMENT a                                                                                     ");
        query.append("ON x.comp_no = a.comp_no AND x.eqloc_id = a.eqloc_id AND a.is_last_version = 'Y' AND a.is_deleted = 'N'           ");
        query.append("LEFT OUTER JOIN TAEQHISTORY b                                                                                     ");
        query.append("ON a.comp_no = b.comp_no AND a.item_no = b.item_no AND a.is_last_version = 'Y' AND a.is_deleted = 'N' AND b.wo_type = 'BM' AND b.wkor_date LIKE mon.tmonth+'%' ");
        query.append("WHERE 1=1                                                                                                         ");
        query.append("AND x.eqloc_id IN(SELECT eqloc_id FROM dbo.SFAEQLOC_CALL('"+loginUser.getCompNo()+"', '"+assetRptMtbfmttrLocDetailDTO.getEqLocId()+"', ''))   ");
        query.append("GROUP BY                                                                                                          ");
        query.append("    x.comp_no                                                                                                     ");
        query.append("    ,mon.tmonth                                                                                                   ");
        query.append("    ,x.eqloc_id                                                                                                   ");
        query.append(")                                                                                                                 ");
        query.append("SELECT                                                                                                            ");
        query.append("    SUBSTRING(a.tmonth,1,4)+'-'+SUBSTRING(a.tmonth,5,2)                                           month           ");
        query.append("    ,a.eqloc_id                                                                                   eqlocId         ");
        query.append("    ,(SELECT full_desc FROM TAEQLOC                                                                               ");
        query.append("      WHERE comp_no = a.comp_no AND eqloc_id = a.eqloc_id)                                        eqlocDesc       ");
        query.append("    ,ROUND(a.run_time/CASE SUM(b.bm_cnt) WHEN 0 THEN 1 ELSE SUM(b.bm_cnt) END, 2)                 mtbf            ");
        query.append("    ,ROUND(ISNULL(SUM(b.work_time),0)/CASE SUM(b.bm_cnt) WHEN 0 THEN 1 ELSE SUM(b.bm_cnt) END, 2) mttr            ");
        query.append("    ,CASE a.run_time WHEN 0 THEN 0 ELSE ROUND(ISNULL(SUM(b.eqdn_work_time),0)/a.run_time, 2) END  failureRate     ");
        query.append("FROM (                                                                                                            ");
        query.append("    SELECT                                                                                                        ");
        query.append("        a.comp_no                                                                                                 ");
        query.append("        ,mon.tmonth                                                                                               ");
        query.append("        ,a.eqloc_id                                                                                               ");
        query.append("        ,SUM(c.dtime+c.ntime+c.etime)/60  run_time                                                                ");
        query.append("    FROM TAEQLOC a INNER JOIN TAMONTH mon                                                                         ");
        query.append("    ON 1=1                                                                                                        ");
        query.getAndDateQuery("mon.tmonth", CommonUtil.getRowDateToNum(assetRptMtbfmttrLocDetailDTO.getStartDate()), CommonUtil.getRowDateToNum(assetRptMtbfmttrLocDetailDTO.getEndDate()));
        query.append("    AND a.comp_no = '"+loginUser.getCompNo()+"'                                                                   ");
        query.append("    AND a.eqloc_id = '"+assetRptMtbfmttrLocDetailDTO.getEqLocId()+"'                                              ");
        query.append("    INNER JOIN TALNWRKLIST b                                                                                      ");
        query.append("    ON a.comp_no = b.comp_no AND a.lnwrklist_id = b.lnwrklist_id                                                  ");
        query.append("    INNER JOIN TALNWRKTIME c                                                                                      ");
        query.append("    ON b.comp_no = c.comp_no AND b.lnwrklist_id = c.lnwrklist_id AND c.wrk_date LIKE mon.tmonth+'%'               ");
        query.append("    GROUP BY                                                                                                      ");
        query.append("        a.comp_no                                                                                                 ");
        query.append("        ,mon.tmonth                                                                                               ");
        query.append("        ,a.eqloc_id                                                                                               ");
        query.append(") a LEFT OUTER JOIN bm_info b                                                                                     ");
        query.append("ON a.comp_no=b.comp_no AND a.tmonth = b.tmonth                                                                    ");
        query.append("WHERE 1=1                                                                                                         ");
        query.append("AND b.eqloc_id IN(SELECT eqloc_id FROM dbo.SFAEQLOC_CALL(a.comp_no, a.eqloc_id, ''))                              ");
        query.append("GROUP BY a.tmonth, a.comp_no, a.eqloc_id, a.run_time                                                              ");
        query.append("ORDER BY a.tmonth                                                                                                 ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
}