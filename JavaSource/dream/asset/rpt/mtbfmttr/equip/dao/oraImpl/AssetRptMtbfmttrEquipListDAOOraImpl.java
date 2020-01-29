package dream.asset.rpt.mtbfmttr.equip.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.asset.rpt.mtbfmttr.equip.dao.AssetRptMtbfmttrEquipListDAO;
import dream.asset.rpt.mtbfmttr.equip.dto.AssetRptMtbfmttrEquipCommonDTO;

/**
 * MTBF,MTTR(설비) 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="assetRptMtbfmttrEquipListDAOTarget"
 * @spring.txbn id="assetRptMtbfmttrEquipListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetRptMtbfmttrEquipListDAOOraImpl extends BaseJdbcDaoSupportOra implements AssetRptMtbfmttrEquipListDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptMtbfmttrEquipCommonDTO
     * @param loginUser
     * @return List
     * @throws Exception 
     */
    public List findList(AssetRptMtbfmttrEquipCommonDTO assetRptMtbfmttrEquipCommonDTO, User loginUser) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        String startDate = CommonUtil.getRowDateToNum(assetRptMtbfmttrEquipCommonDTO.getFilterStartDate());
        String endDate = CommonUtil.getRowDateToNum(assetRptMtbfmttrEquipCommonDTO.getFilterEndDate());
        if(startDate.length()>=6) startDate = startDate + "01";
        if(endDate.length()>=6) endDate = endDate + DateUtil.getLastDayOfMonth(endDate.substring(0, 4), endDate.substring(4, 6));
        
        query.append("WITH bm_info AS(                                                                                                  ");
        query.append("SELECT                                                                                                            ");
        query.append("    a.comp_no                                                                                                     ");
        query.append("    ,a.equip_id                                                                                                   ");
        query.append("    ,SUM(CASE WHEN b.eqhistory_id IS NULL THEN 0 ELSE 1 END)  bm_cnt                                              ");
        query.append("    ,SUM(b.work_time)                                         work_time                                           ");
        query.append("    ,SUM(b.eqdn_work_time)/60                                 eqdn_work_time                                      ");
        query.append("FROM TAEQUIPMENT a LEFT OUTER JOIN TAEQHISTORY b                                                                  ");
        query.append("ON a.comp_no = b.comp_no AND a.item_no = b.item_no AND a.is_last_version = 'Y' AND a.is_deleted = 'N' AND b.wo_type = 'BM'  ");
        query.getAndDateQuery("b.wkor_date", startDate, endDate);
        query.append("WHERE 1=1                                                                                                         ");
        query.append("AND a.is_deleted = 'N'                                                                                            ");
        query.append("AND a.is_last_version = 'Y'                                                                                       ");
        query.append("GROUP BY                                                                                                          ");
        query.append("    a.comp_no                                                                                                     ");
        query.append("    ,a.equip_id                                                                                                   ");
        query.append(")                                                                                                                 ");
        query.append("SELECT                                                                                                            ");
        query.append("    ''                                                                                    SEQNO                   ");
        query.append("    ,''                                                                                   ISDELCHECK              ");
        query.append("    ,a.equip_id                                                                           equipId                 ");
        query.append("    ,a.eqloc_id                                                                           eqlocId                 ");
        query.append("    ,(SELECT full_desc FROM TAEQLOC                                                                               ");
        query.append("      WHERE comp_no = a.comp_no AND eqloc_id = a.eqloc_id)                                eqlocDesc               ");
        query.append("    ,a.item_no                                                                            itemNo                  ");
        query.append("    ,a.description                                                                        itemDesc                ");
        query.append("    ,a.eqctg_id                                                                           eqctgId                 ");
        query.append("    ,(SELECT description FROM TAEQCTG                                                                             ");
        query.append("      WHERE comp_no = a.comp_no AND eqctg_id = a.eqctg_id)                                eqctgDesc               ");
        query.append("    ,a.dept_id                                                                            deptId                  ");
        query.append("    ,(SELECT description FROM TADEPT                                                                              ");
        query.append("      WHERE comp_no = a.comp_no AND dept_id = a.dept_id)                                  deptDesc                ");
        query.append("    ,a.usage_dept                                                                         usageDept               ");
        query.append("    ,(SELECT description FROM TADEPT                                                                              ");
        query.append("      WHERE comp_no = a.comp_no AND dept_id = a.usage_dept)                               usageDeptDesc           ");
        query.append("    ,ROUND(a.run_time/CASE b.bm_cnt WHEN 0 THEN 1 ELSE b.bm_cnt END, 2)                   mtbf                    ");
        query.append("    ,ROUND(NVL(b.work_time,0)/CASE b.bm_cnt WHEN 0 THEN 1 ELSE b.bm_cnt END, 2)           mttr                    ");
        query.append("    ,CASE a.run_time WHEN 0 THEN 0 ELSE ROUND(NVL(b.eqdn_work_time,0)/a.run_time, 2) END  failureRate             ");
        query.append("    ,(SELECT emp_name FROM TAEMP                                                                                  ");
        query.append("      WHERE comp_no = a.comp_no AND emp_id = a.main_mng_id)                               mainMngName             ");
        query.append("    ,(SELECT emp_name FROM TAEMP                                                                                  ");
        query.append("      WHERE comp_no = a.comp_no AND emp_id = a.sub_mng_id)                                subMngName              ");
        query.append("FROM (                                                                                                            ");
        query.append("    SELECT                                                                                                        ");
        query.append("        a.comp_no                                                                                                 ");
        query.append("        ,a.item_no                                                                                                ");
        query.append("        ,a.equip_id                                                                                               ");
        query.append("        ,a.description                                                                                            ");
        query.append("        ,a.plant                                                                                                  ");
        query.append("        ,a.eqctg_id                                                                                               ");
        query.append("        ,a.eqloc_id                                                                                               ");
        query.append("        ,a.dept_id                                                                                                ");
        query.append("        ,a.usage_dept                                                                                             ");
        query.append("        ,a.main_mng_id                                                                                            ");
        query.append("        ,a.sub_mng_id                                                                                             ");
        query.append("        ,a.lnwrklist_id                                                                                           ");
        query.append("        ,a.is_deleted                                                                                             ");
        query.append("        ,a.is_last_version                                                                                        ");
        query.append("        ,SUM(c.dtime+c.ntime+c.etime)/60  run_time                                                                ");
        query.append("    FROM TAEQUIPMENT a INNER JOIN TALNWRKLIST b                                                                   ");
        query.append("    ON a.comp_no = b.comp_no AND a.lnwrklist_id = b.lnwrklist_id                                                  ");
        query.append("    INNER JOIN TALNWRKTIME c                                                                                      ");
        query.append("    ON b.comp_no = c.comp_no AND b.lnwrklist_id = c.lnwrklist_id                                                  ");
        query.getAndDateQuery("c.wrk_date", startDate, endDate);
        query.append("    WHERE 1=1                                                                                                     ");
        query.append(this.getWhere(assetRptMtbfmttrEquipCommonDTO,loginUser));
        query.append("    GROUP BY                                                                                                      ");
        query.append("        a.comp_no                                                                                                 ");
        query.append("        ,a.item_no                                                                                                ");
        query.append("        ,a.equip_id                                                                                               ");
        query.append("        ,a.description                                                                                            ");
        query.append("        ,a.plant                                                                                                  ");
        query.append("        ,a.eqctg_id                                                                                               ");
        query.append("        ,a.eqloc_id                                                                                               ");
        query.append("        ,a.dept_id                                                                                                ");
        query.append("        ,a.usage_dept                                                                                             ");
        query.append("        ,a.main_mng_id                                                                                            ");
        query.append("        ,a.sub_mng_id                                                                                             ");
        query.append("        ,a.lnwrklist_id                                                                                           ");
        query.append("        ,a.is_deleted                                                                                             ");
        query.append("        ,a.is_last_version                                                                                        ");
        query.append(") a LEFT OUTER JOIN bm_info b                                                                                     ");
        query.append("ON a.comp_no=b.comp_no AND a.equip_id = b.equip_id                                                                ");
        query.append("WHERE 1=1                                                                                                         ");
        if("MTBF".equals(assetRptMtbfmttrEquipCommonDTO.getSelectType())){
            query.append("AND b.bm_cnt IS NOT NULL");
            query.append("AND a.run_time IS NOT NULL");
        }
        if("MTTR".equals(assetRptMtbfmttrEquipCommonDTO.getSelectType())){
            query.append("AND b.bm_cnt IS NOT NULL");
            query.append("AND b.work_time IS NOT NULL");
        }
        query.getOrderByQuery("(SELECT full_desc FROM TAEQLOC WHERE comp_no = a.comp_no AND eqloc_id = a.eqloc_id), a.description", assetRptMtbfmttrEquipCommonDTO.getOrderBy(), assetRptMtbfmttrEquipCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(assetRptMtbfmttrEquipCommonDTO.getIsLoadMaxCount(), assetRptMtbfmttrEquipCommonDTO.getFirstRow()));
    }
    
    private String getWhere(AssetRptMtbfmttrEquipCommonDTO assetRptMtbfmttrEquipCommonDTO,User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("a.comp_no", loginUser.getCompNo());
        query.append("AND a.is_deleted = 'N'                                         ");
        query.append("AND a.is_last_version = 'Y'                                    ");
        
        query.getEqLocLevelQuery("a.eqloc_id", assetRptMtbfmttrEquipCommonDTO.getFilterEqLocId(), assetRptMtbfmttrEquipCommonDTO.getFilterEqLocDesc(), loginUser.getCompNo());
        query.getEqCtgLevelQuery("a.eqctg_id", assetRptMtbfmttrEquipCommonDTO.getFilterEqCtgId(), assetRptMtbfmttrEquipCommonDTO.getFilterEqCtgDesc(), loginUser.getCompNo());
        query.getDeptLevelQuery("a.dept_id", assetRptMtbfmttrEquipCommonDTO.getFilterDeptId(), assetRptMtbfmttrEquipCommonDTO.getFilterDeptDesc(), loginUser.getCompNo());
        query.getDeptLevelQuery("a.usage_dept", assetRptMtbfmttrEquipCommonDTO.getFilterUsageDept(), assetRptMtbfmttrEquipCommonDTO.getFilterUsageDeptDesc(), loginUser.getCompNo());
        
        //공장코드
        query.getCodeLikeQuery("a.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = a.comp_no AND plant = b.plant )", 
                assetRptMtbfmttrEquipCommonDTO.getFilterPlantId(), assetRptMtbfmttrEquipCommonDTO.getFilterPlantDesc());
        
        //설비
        query.getCodeLikeQuery("a.equip_id", "a.description", 
                assetRptMtbfmttrEquipCommonDTO.getFilterEquipId(), assetRptMtbfmttrEquipCommonDTO.getFilterEquipDesc());
        
        //관리자(정)
        query.getCodeLikeQuery("a.main_mng_id", "(SELECT c.emp_name FROM  TAEMP c WHERE c.comp_no = a.comp_no AND c.emp_id = b.main_mng_id )", 
        		assetRptMtbfmttrEquipCommonDTO.getFilterMainMngId(), assetRptMtbfmttrEquipCommonDTO.getFilterMainMngName());
        //관리자(부)
        query.getCodeLikeQuery("a.sub_mng_id", "(SELECT c.emp_name FROM  TAEMP c WHERE c.comp_no = a.comp_no AND c.emp_id = b.sub_mng_id )", 
        		assetRptMtbfmttrEquipCommonDTO.getFilterSubMngId(), assetRptMtbfmttrEquipCommonDTO.getFilterSubMngName());
        
        return query.toString();
    }
    
    @Override
    public String findTotalCount(AssetRptMtbfmttrEquipCommonDTO assetRptMtbfmttrEquipCommonDTO, User loginUser) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        String startDate = CommonUtil.getRowDateToNum(assetRptMtbfmttrEquipCommonDTO.getFilterStartDate());
        String endDate = CommonUtil.getRowDateToNum(assetRptMtbfmttrEquipCommonDTO.getFilterEndDate());
        if(startDate.length()>=6) startDate = startDate + "01";
        if(endDate.length()>=6) endDate = endDate + DateUtil.getLastDayOfMonth(endDate.substring(0, 4), endDate.substring(4, 6));
        
        query.append("SELECT COUNT(1)                                                                                               ");
        query.append("FROM (                                                                                                        ");
        query.append("    SELECT                                                                                                    ");
        query.append("        a.comp_no                                                                                             ");
        query.append("        ,a.equip_id                                                                                           ");
        query.append("    FROM TAEQUIPMENT a INNER JOIN TALNWRKLIST b                                                               ");
        query.append("    ON a.comp_no = b.comp_no AND a.lnwrklist_id = b.lnwrklist_id                                              ");
        query.append("    INNER JOIN TALNWRKTIME c                                                                                  ");
        query.append("    ON b.comp_no = c.comp_no AND b.lnwrklist_id = c.lnwrklist_id                                              ");
        query.getAndDateQuery("c.wrk_date", startDate, endDate);
        query.append("    WHERE 1=1                                                                                                 ");
        query.append(this.getWhere(assetRptMtbfmttrEquipCommonDTO,loginUser));
        query.append("    GROUP BY                                                                                                  ");
        query.append("        a.comp_no                                                                                             ");
        query.append("        ,a.equip_id                                                                                           ");
        query.append(") a                                                                                                           ");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
    
}