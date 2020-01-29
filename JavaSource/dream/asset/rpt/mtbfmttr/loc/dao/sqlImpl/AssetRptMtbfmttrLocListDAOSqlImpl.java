package dream.asset.rpt.mtbfmttr.loc.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.asset.rpt.mtbfmttr.loc.dao.AssetRptMtbfmttrLocListDAO;
import dream.asset.rpt.mtbfmttr.loc.dto.AssetRptMtbfmttrLocCommonDTO;

/**
 * MTBF,MTTR(위치) 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="assetRptMtbfmttrLocListDAOTarget"
 * @spring.txbn id="assetRptMtbfmttrLocListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetRptMtbfmttrLocListDAOSqlImpl extends BaseJdbcDaoSupportSql implements AssetRptMtbfmttrLocListDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptMtbfmttrLocCommonDTO
     * @param loginUser
     * @return List
     * @throws Exception 
     */
    public List findList(AssetRptMtbfmttrLocCommonDTO assetRptMtbfmttrLocCommonDTO, User loginUser) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String startDate = CommonUtil.getRowDateToNum(assetRptMtbfmttrLocCommonDTO.getFilterStartDate());
        String endDate = CommonUtil.getRowDateToNum(assetRptMtbfmttrLocCommonDTO.getFilterEndDate());
        if(startDate.length()>=6) startDate = startDate + "01";
        if(endDate.length()>=6) endDate = endDate + DateUtil.getLastDayOfMonth(endDate.substring(0, 4), endDate.substring(4, 6));
        
        query.append("WITH bm_info AS(                                                                                                          ");
        query.append("SELECT                                                                                                                    ");
        query.append("    x.comp_no                                                                                                             ");
        query.append("    ,x.eqloc_id                                                                                                           ");
        query.append("    ,SUM(CASE WHEN b.eqhistory_id IS NULL THEN 0 ELSE 1 END)  bm_cnt                                                      ");
        query.append("    ,SUM(b.work_time)                                         work_time                                                   ");
        query.append("    ,SUM(b.eqdn_work_time)/60                                 eqdn_work_time                                              ");
        query.append("FROM TAEQLOC x LEFT OUTER JOIN TAEQUIPMENT a                                                                              ");
        query.append("ON x.comp_no = a.comp_no AND x.eqloc_id = a.eqloc_id                                                                      ");
        query.append("AND a.is_deleted = 'N'                                                                                                    ");
        query.append("AND a.is_last_version = 'Y'                                                                                               ");
        query.append("LEFT OUTER JOIN TAEQHISTORY b                                                                                             ");
        query.append("ON a.comp_no = b.comp_no AND a.item_no = b.item_no AND a.is_last_version = 'Y' AND a.is_deleted = 'N' AND b.wo_type = 'BM'");
        query.getAndDateQuery("b.wkor_date", startDate, endDate);
        query.append("WHERE 1=1                                                                                                                 ");
        query.append("GROUP BY                                                                                                                  ");
        query.append("    x.comp_no                                                                                                             ");
        query.append("    ,x.eqloc_id                                                                                                           ");
        query.append(")                                                                                                                         ");
        query.append("SELECT                                                                                                                    ");
        query.append("    ''                                                                                            SEQNO                   ");
        query.append("    ,''                                                                                           ISDELCHECK              ");
        query.append("    ,a.eqloc_id                                                                                   eqLocId                 ");
        query.append("    ,(SELECT description FROM TAEQLOC                                                                                     ");
        query.append("      WHERE comp_no = a.comp_no AND eqloc_id = a.eqloc_id)                                        eqlocLeafDesc           ");
        query.append("    ,(SELECT full_desc FROM TAEQLOC                                                                                       ");
        query.append("      WHERE comp_no = a.comp_no AND eqloc_id = a.eqloc_id)                                        eqLocDesc               ");
        query.append("    ,ROUND(a.run_time/CASE SUM(b.bm_cnt) WHEN 0 THEN 1 ELSE SUM(b.bm_cnt) END, 2)                 mtbf                    ");
        query.append("    ,ROUND(ISNULL(SUM(b.work_time),0)/CASE SUM(b.bm_cnt) WHEN 0 THEN 1 ELSE SUM(b.bm_cnt) END, 2) mttr                    ");
        query.append("    ,CASE a.run_time WHEN 0 THEN 0 ELSE ROUND(ISNULL(SUM(b.eqdn_work_time),0)/a.run_time, 2) END  failureRate             ");
        query.append("FROM (                                                                                                                    ");
        query.append("    SELECT                                                                                                                ");
        query.append("        a.comp_no                                                                                                         ");
        query.append("        ,a.eqloc_id                                                                                                       ");
        query.append("        ,SUM(c.dtime+c.ntime+c.etime)/60  run_time                                                                        ");
        query.append("    FROM TAEQLOC a INNER JOIN TALNWRKLIST b                                                                               ");
        query.append("    ON a.comp_no = b.comp_no AND a.lnwrklist_id = b.lnwrklist_id                                                          ");
        query.append("    INNER JOIN TALNWRKTIME c                                                                                              ");
        query.append("    ON b.comp_no = c.comp_no AND b.lnwrklist_id = c.lnwrklist_id                                                          ");
        query.getAndDateQuery("c.wrk_date", startDate, endDate);
        query.append("    WHERE 1=1                                                                                                             ");
        query.append(this.getWhere(assetRptMtbfmttrLocCommonDTO,loginUser));
        query.append("    GROUP BY                                                                                                              ");
        query.append("        a.comp_no                                                                                                         ");
        query.append("        ,a.eqloc_id                                                                                                       ");
        query.append(") a LEFT OUTER JOIN bm_info b                                                                                             ");
        query.append("ON a.comp_no=b.comp_no                                                                                                    ");
        query.append("WHERE 1=1                                                                                                                 ");
        query.append("AND b.eqloc_id IN(SELECT eqloc_id FROM dbo.SFAEQLOC_CALL(a.comp_no, a.eqloc_id, ''))                                      ");
        query.append("GROUP BY a.comp_no, a.eqloc_id, a.run_time                                                                                ");
        query.getOrderByQuery("a.eqloc_id", "(SELECT full_desc FROM TAEQLOC WHERE comp_no=a.comp_no and eqloc_id=a.eqloc_id)", assetRptMtbfmttrLocCommonDTO.getOrderBy(), assetRptMtbfmttrLocCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(assetRptMtbfmttrLocCommonDTO.getIsLoadMaxCount(), assetRptMtbfmttrLocCommonDTO.getFirstRow()));
    }
    
    public String getWhere(AssetRptMtbfmttrLocCommonDTO assetRptMtbfmttrLocCommonDTO,User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("a.comp_no", loginUser.getCompNo());
        
        query.getEqLocLevelQuery("a.eqloc_id", assetRptMtbfmttrLocCommonDTO.getFilterEqLocId(), assetRptMtbfmttrLocCommonDTO.getFilterEqLocDesc(), loginUser.getCompNo());
        
        if(!"".equals(assetRptMtbfmttrLocCommonDTO.getFilterEqLocLevel()) || !"".equals(assetRptMtbfmttrLocCommonDTO.getFilterEqLocLevelDesc()))
        {
            query.append("AND EXISTS (SELECT bb.eqloc_id            ");
            query.append("            FROM TAEQLOC bb               ");
            query.append("            WHERE bb.eqloc_id=a.eqloc_id  ");
            query.append("            AND bb.comp_no=a.comp_no      ");
            query.getSysCdQuery("bb.eqloc_lvl_type", assetRptMtbfmttrLocCommonDTO.getFilterEqLocLevel(), assetRptMtbfmttrLocCommonDTO.getFilterEqLocLevelDesc(), "EQLOC_LVL_TYPE", loginUser.getCompNo(),loginUser.getLangId());
            query.append("            )                             ");
        }
        
        //공장코드
        query.getCodeLikeQuery("a.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = a.comp_no AND plant = a.plant )", 
                assetRptMtbfmttrLocCommonDTO.getFilterPlantId(), assetRptMtbfmttrLocCommonDTO.getFilterPlantDesc());
        
        return query.toString();
    }

    @Override
    public String findTotalCount(AssetRptMtbfmttrLocCommonDTO assetRptMtbfmttrLocCommonDTO, User loginUser) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String startDate = CommonUtil.getRowDateToNum(assetRptMtbfmttrLocCommonDTO.getFilterStartDate());
        String endDate = CommonUtil.getRowDateToNum(assetRptMtbfmttrLocCommonDTO.getFilterEndDate());
        if(startDate.length()>=6) startDate = startDate + "01";
        if(endDate.length()>=6) endDate = endDate + DateUtil.getLastDayOfMonth(endDate.substring(0, 4), endDate.substring(4, 6));
        
        query.append("SELECT COUNT(1)                                                                                               ");
        query.append("FROM (                                                                                                        ");
        query.append("    SELECT                                                                                                    ");
        query.append("        a.comp_no                                                                                             ");
        query.append("        ,a.eqloc_id                                                                                           ");
        query.append("    FROM TAEQLOC a INNER JOIN TALNWRKLIST b                                                                   ");
        query.append("    ON a.comp_no = b.comp_no AND a.lnwrklist_id = b.lnwrklist_id                                              ");
        query.append("    INNER JOIN TALNWRKTIME c                                                                                  ");
        query.append("    ON b.comp_no = c.comp_no AND b.lnwrklist_id = c.lnwrklist_id                                              ");
        query.getAndDateQuery("c.wrk_date", startDate, endDate);
        query.append("    WHERE 1=1                                                                                                 ");
        query.append(this.getWhere(assetRptMtbfmttrLocCommonDTO,loginUser));
        query.append("    GROUP BY                                                                                                  ");
        query.append("        a.comp_no                                                                                             ");
        query.append("        ,a.eqloc_id                                                                                           ");
        query.append(") a                                                                                                           ");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
    
}