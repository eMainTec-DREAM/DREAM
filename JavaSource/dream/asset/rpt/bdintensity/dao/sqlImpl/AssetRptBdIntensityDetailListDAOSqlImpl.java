package dream.asset.rpt.bdintensity.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.QuerySqlBuffer;
import dream.asset.rpt.bdintensity.dao.AssetRptBdIntensityDetailListDAO;
import dream.asset.rpt.bdintensity.dto.AssetRptBdIntensityCommonDTO;
import dream.asset.rpt.bdintensity.dto.AssetRptBdIntensityDetailListDTO;
import dream.asset.rpt.bdintensity.form.AssetRptBdIntensityDetailListForm;

/**
 * 에너지사용량(일별) 상세 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="assetRptBdIntensityDetailListDAOTarget"
 * @spring.txbn id="assetRptBdIntensityDetailListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetRptBdIntensityDetailListDAOSqlImpl extends BaseJdbcDaoSupportSql implements AssetRptBdIntensityDetailListDAO
{
    @Override
    public List findFreqChart(AssetRptBdIntensityDetailListForm assetRptBdIntensityDetailListForm, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("SELECT                                                                                                                            ");
        query.append("      SUBSTRING(a.tmonth,1,4)+'-'+SUBSTRING(a.tmonth,5,2)                                                            WKORDATE     ");
        query.append("    , ROUND(ISNULL(SUM(b.eqdn_time) / CASE WHEN SUM(b.loadTime) = 0 THEN NULL ELSE SUM(b.loadTime) END,0) * 100,2)   BDDURARATE   ");
        query.append("    , ROUND(ISNULL(SUM(b.eqdn_cnt) / CASE WHEN SUM(b.loadTime) = 0 THEN NULL ELSE SUM(b.loadTime) END,0) * 100,2)    BDFREQRATE   ");
        query.append("FROM TADAY a LEFT OUTER JOIN (                                                                                                    ");
        query.append("    SELECT x.equip_id, ISNULL(x.tday,y.tday) tday, x.loadTime, y.eqdn_time, y.eqdn_cnt FROM (                                     ");
        query.append("        SELECT                                                                                                                    ");
        query.append("              a.comp_no                                                                                                           ");
        query.append("            , a.equip_id                                                                                                          ");
        query.append("            , aa.wrk_date                                                            tday                                         ");
        query.append("            ,(ISNULL(SUM(aa.dtime),0) + ISNULL(SUM(aa.ntime),0) + ISNULL(SUM(aa.etime),0))/60 loadTime                            ");
        query.append("        FROM TAEQUIPMENT a LEFT OUTER JOIN TALNWRKTIME aa                                                                         ");
        query.append("        ON a.comp_no = aa.comp_no AND a.lnwrklist_id = aa.lnwrklist_id                                                            ");
        query.append(this.getLnWrkWhere(assetRptBdIntensityDetailListForm, loginUser));
        query.append("        WHERE 1=1                                                                                                                 ");
        query.append(this.getWhere(assetRptBdIntensityDetailListForm,loginUser));
        query.append("        GROUP BY a.comp_no, a.equip_id, aa.wrk_date                                                                               ");
        query.append("    ) x FULL OUTER JOIN (                                                                                                         ");
        query.append("        SELECT                                                                                                                    ");
        query.append("              a.comp_no                                                                                                           ");
        query.append("            , a.equip_id                                                                                                          ");
        query.append("            , aa.wkor_date                         tday                                                                           ");
        query.append("            ,ISNULL(SUM(d.eqdn_work_time),0)/60    eqdn_time                                                                      ");
        query.append("            ,count(d.wofail_id)                    eqdn_cnt                                                                       ");
        query.append("        FROM TAEQUIPMENT a LEFT OUTER JOIN TAWOEQUIP b                                                                            ");
        query.append("        ON a.comp_no = b.comp_no AND a.equip_id = b.equip_id                                                                      ");
        query.append("        LEFT OUTER JOIN TAWORKORDER aa                                                                                            ");
        query.append("        ON b.comp_no = aa.comp_no AND b.wkor_id = aa.wkor_id                                                                      ");
        query.append(this.getWkorWhere(assetRptBdIntensityDetailListForm,loginUser));
        query.append("        LEFT OUTER JOIN TAWOFAIL d                                                                                                ");
        query.append("        ON aa.comp_no = d.comp_no AND aa.wkor_id = d.wkor_id                                                                      ");
        query.append("        WHERE 1=1                                                                                                                 ");
        query.append(this.getWhere(assetRptBdIntensityDetailListForm,loginUser));
        query.append("        GROUP BY a.comp_no, a.equip_id, aa.wkor_date                                                                              ");
        query.append("    ) y                                                                                                                           ");
        query.append("    ON x.comp_no = y.comp_no AND x.equip_id = y.equip_id AND x.tday = y.tday                                                      ");
        query.append(") b                                                                                                                               ");
        query.append("ON a.tday = b.tday                                                                                                                ");
        query.append("WHERE 1 = 1                                                                                                                       ");
        query.append(this.getOuterWhere(assetRptBdIntensityDetailListForm,loginUser));
        query.append("GROUP BY a.tmonth                                                                                                                 ");
        query.append("ORDER BY a.tmonth                                                                                                                 ");

        return getJdbcTemplate().queryForList(query.toString());
        
    }
    
    public List findDuraChart(AssetRptBdIntensityDetailListForm assetRptBdIntensityDetailListForm, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("SELECT                                                                                                                            ");
        query.append("      SUBSTRING(a.tmonth,1,4)+'-'+SUBSTRING(a.tmonth,5,2)                                                            WKORDATE     ");
        query.append("    , ROUND(ISNULL(SUM(b.eqdn_time) / CASE WHEN SUM(b.loadTime) = 0 THEN NULL ELSE SUM(b.loadTime) END,0) * 100,2)   BDDURARATE   ");
        query.append("    , ROUND(ISNULL(SUM(b.eqdn_cnt) / CASE WHEN SUM(b.loadTime) = 0 THEN NULL ELSE SUM(b.loadTime) END,0) * 100,2)    BDFREQRATE   ");
        query.append("FROM TADAY a LEFT OUTER JOIN (                                                                                                    ");
        query.append("    SELECT x.equip_id, ISNULL(x.tday,y.tday) tday, x.loadTime, y.eqdn_time, y.eqdn_cnt FROM (                                     ");
        query.append("        SELECT                                                                                                                    ");
        query.append("              a.comp_no                                                                                                           ");
        query.append("            , a.equip_id                                                                                                          ");
        query.append("            , aa.wrk_date                                                            tday                                         ");
        query.append("            ,(ISNULL(SUM(aa.dtime),0) + ISNULL(SUM(aa.ntime),0) + ISNULL(SUM(aa.etime),0))/60 loadTime                            ");
        query.append("        FROM TAEQUIPMENT a LEFT OUTER JOIN TALNWRKTIME aa                                                                         ");
        query.append("        ON a.comp_no = aa.comp_no AND a.lnwrklist_id = aa.lnwrklist_id                                                            ");
        query.append(this.getLnWrkWhere(assetRptBdIntensityDetailListForm, loginUser));
        query.append("        WHERE 1=1                                                                                                                 ");
        query.append(this.getWhere(assetRptBdIntensityDetailListForm,loginUser));
        query.append("        GROUP BY a.comp_no, a.equip_id, aa.wrk_date                                                                               ");
        query.append("    ) x FULL OUTER JOIN (                                                                                                         ");
        query.append("        SELECT                                                                                                                    ");
        query.append("              a.comp_no                                                                                                           ");
        query.append("            , a.equip_id                                                                                                          ");
        query.append("            , aa.wkor_date                         tday                                                                           ");
        query.append("            ,ISNULL(SUM(d.eqdn_work_time),0)/60    eqdn_time                                                                      ");
        query.append("            ,count(d.wofail_id)                    eqdn_cnt                                                                       ");
        query.append("        FROM TAEQUIPMENT a LEFT OUTER JOIN TAWOEQUIP b                                                                            ");
        query.append("        ON a.comp_no = b.comp_no AND a.equip_id = b.equip_id                                                                      ");
        query.append("        LEFT OUTER JOIN TAWORKORDER aa                                                                                            ");
        query.append("        ON b.comp_no = aa.comp_no AND b.wkor_id = aa.wkor_id                                                                      ");
        query.append(this.getWkorWhere(assetRptBdIntensityDetailListForm,loginUser));
        query.append("        LEFT OUTER JOIN TAWOFAIL d                                                                                                ");
        query.append("        ON aa.comp_no = d.comp_no AND aa.wkor_id = d.wkor_id                                                                      ");
        query.append("        WHERE 1=1                                                                                                                 ");
        query.append(this.getWhere(assetRptBdIntensityDetailListForm,loginUser));
        query.append("        GROUP BY a.comp_no, a.equip_id, aa.wkor_date                                                                              ");
        query.append("    ) y                                                                                                                           ");
        query.append("    ON x.comp_no = y.comp_no AND x.equip_id = y.equip_id AND x.tday = y.tday                                                      ");
        query.append(") b                                                                                                                               ");
        query.append("ON a.tday = b.tday                                                                                                                ");
        query.append("WHERE 1 = 1                                                                                                                       ");
        query.append(this.getOuterWhere(assetRptBdIntensityDetailListForm,loginUser));
        query.append("GROUP BY a.tmonth                                                                                                                 ");
        query.append("ORDER BY a.tmonth                                                                                                                 ");
        
    	return getJdbcTemplate().queryForList(query.toString());
    	
    }

    public String getWhere(AssetRptBdIntensityDetailListForm assetRptBdIntensityDetailListForm,User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        AssetRptBdIntensityDetailListDTO assetRptBdIntensityDetailListDTO = assetRptBdIntensityDetailListForm.getAssetRptBdIntensityDetailListDTO();

        query.getAndQuery("a.comp_no", loginUser.getCompNo());
        query.getAndQuery("a.plant", assetRptBdIntensityDetailListDTO.getPlantId());
        query.getAndQuery("a.eqloc_id", assetRptBdIntensityDetailListDTO.getEqLocId());
        query.getAndQuery("a.usage_dept", assetRptBdIntensityDetailListDTO.getUsageDeptId());
        query.getAndQuery("a.eqctg_id", assetRptBdIntensityDetailListDTO.getEqCtgId());
        query.getAndQuery("a.equip_id", assetRptBdIntensityDetailListDTO.getEquipId());
    	
        return query.toString();
    }
    
    public String getLnWrkWhere(AssetRptBdIntensityDetailListForm assetRptBdIntensityDetailListForm,User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        AssetRptBdIntensityCommonDTO assetRptBdIntensityCommonDTO = assetRptBdIntensityDetailListForm.getAssetRptBdIntensityCommonDTO();
        
        String fromMonth = CommonUtil.convertDate(assetRptBdIntensityCommonDTO.getFilterStartDate());
        String toMonth   = CommonUtil.convertDate(assetRptBdIntensityCommonDTO.getFilterEndDate());
        
        if (fromMonth != null && !"".equals(fromMonth) && toMonth != null && !"".equals(toMonth))
        {
            query.getAndDateQuery("SUBSTRING(aa.wrk_date,1,6)", fromMonth, toMonth);
        }        
        
        return query.toString();
    }
    
    public String getWkorWhere(AssetRptBdIntensityDetailListForm assetRptBdIntensityDetailListForm,User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	AssetRptBdIntensityCommonDTO assetRptBdIntensityCommonDTO = assetRptBdIntensityDetailListForm.getAssetRptBdIntensityCommonDTO();
    	
    	query.append(" AND aa.wo_status IN('PRC', 'C')  ");
        query.append(" AND aa.wo_type = 'BM'            ");
    	
    	String fromMonth = CommonUtil.convertDate(assetRptBdIntensityCommonDTO.getFilterStartDate());
    	String toMonth   = CommonUtil.convertDate(assetRptBdIntensityCommonDTO.getFilterEndDate());
    	
    	if (fromMonth != null && !"".equals(fromMonth) && toMonth != null && !"".equals(toMonth))
    	{
    		query.getAndDateQuery("SUBSTRING(aa.wkor_date,1,6)", fromMonth, toMonth);
    	}        
    	
    	return query.toString();
    }
    
    public String getOuterWhere(AssetRptBdIntensityDetailListForm assetRptBdIntensityDetailListForm,User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	AssetRptBdIntensityCommonDTO assetRptBdIntensityCommonDTO = assetRptBdIntensityDetailListForm.getAssetRptBdIntensityCommonDTO();
    	
    	String fromMonth = CommonUtil.convertDate(assetRptBdIntensityCommonDTO.getFilterStartDate());
    	String toMonth   = CommonUtil.convertDate(assetRptBdIntensityCommonDTO.getFilterEndDate());
    	
    	if (fromMonth != null && !"".equals(fromMonth) && toMonth != null && !"".equals(toMonth))
    	{
    		query.getAndDateQuery("a.tmonth", fromMonth, toMonth);
    	}        
    	
    	return query.toString();
    }
    
}