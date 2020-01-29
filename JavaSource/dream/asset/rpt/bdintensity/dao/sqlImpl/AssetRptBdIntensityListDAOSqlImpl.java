package dream.asset.rpt.bdintensity.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.asset.rpt.bdintensity.dao.AssetRptBdIntensityListDAO;
import dream.asset.rpt.bdintensity.dto.AssetRptBdIntensityCommonDTO;

/**
 * 설비별 고장강도율 - List DAO implements
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="assetRptBdIntensityListDAOTarget"
 * @spring.txbn id="assetRptBdIntensityListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetRptBdIntensityListDAOSqlImpl  extends BaseJdbcDaoSupportSql implements AssetRptBdIntensityListDAO
{

    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptBdIntensityCommonDTO
     * @return List
     */
    public List findPlantList(AssetRptBdIntensityCommonDTO assetRptBdIntensityCommonDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                                                                 ");
        query.append("SELECT                                                                                                            ");
        query.append("      ''                                                                                              seqNo       ");
        query.append("    , a.equip_id                                                                                      EQUIPID     ");
        query.append("    , a.item_no                                                                                       ITEMNO      ");
        query.append("    , a.DESCRIPTION                                                                                   EQUIPDESC   ");
       // query.append("    , CONVERT(numeric(18,2),a.loadTime)                                                               TIMELOAD    ");
       // query.append("    , CONVERT(numeric(18,2),a.eqdn_time)                                                              bmTime      ");
        query.append("    , CONVERT(numeric(18),a.loadTime)                                                                 TIMELOAD    ");
        query.append("    , CONVERT(numeric(18,2),a.eqdn_time)                                                              bmTime      ");
        query.append("    , CONVERT(numeric(18),a.eqdn_cnt)                                                                 bdTimeFreq  ");
        query.append("    , CONVERT(numeric(5,2),isnull(a.eqdn_time / CASE WHEN a.loadTime = 0 THEN NULL ELSE a.loadTime END,0) * 100 )    BDDURARATE      ");
        query.append("    , CONVERT(numeric(5,2),isnull(a.eqdn_cnt / CASE WHEN a.loadTime = 0 THEN NULL ELSE a.loadTime END,0) * 100 )     BDFREQRATE      ");
        query.append("FROM (                                                                                                            ");
        query.append("    SELECT x.equip_id, x.item_no, x.description, x.loadTime, y.eqdn_time, y.eqdn_cnt FROM (                       ");
        query.append("        SELECT                                                                                                    ");
        query.append("              a.comp_no                                                                                           ");
        query.append("            , a.equip_id                                                                                          ");
        query.append("            , a.item_no                                                                                           ");
        query.append("            , a.description                                                                                       ");
        query.append("            ,(ISNULL(SUM(aa.dtime),0) + ISNULL(SUM(aa.ntime),0) + ISNULL(SUM(aa.etime),0))/60 loadTime            ");
        query.append("        FROM TAEQUIPMENT a LEFT OUTER JOIN TALNWRKTIME aa                                                         ");
        query.append("        ON a.comp_no = aa.comp_no AND a.lnwrklist_id = aa.lnwrklist_id                                            ");
        query.append(this.getLnWrkWhere(assetRptBdIntensityCommonDTO, user));
        query.append("        WHERE 1=1                                                                                                 ");
        query.append(this.getWhere(assetRptBdIntensityCommonDTO, user));
        query.append("        GROUP BY a.comp_no, a.equip_id, a.item_no, a.description                                                  ");
        query.append("    ) x INNER JOIN (                                                                                              ");
        query.append("        SELECT                                                                                                    ");
        query.append("              a.comp_no                                                                                           ");
        query.append("            , a.equip_id                                                                                          ");
        query.append("            , a.item_no                                                                                           ");
        query.append("            , a.description                                                                                       ");
        query.append("            ,ISNULL(SUM(d.eqdn_work_time),0)/60    eqdn_time                                                      ");
        query.append("            ,count(d.wofail_id)                    eqdn_cnt                                                       ");
        query.append("        FROM TAEQUIPMENT a LEFT OUTER JOIN TAWOEQUIP b                                                            ");
        query.append("        ON a.comp_no = b.comp_no AND a.equip_id = b.equip_id                                                      ");
        query.append("        LEFT OUTER JOIN TAWORKORDER aa                                                                            ");
        query.append("        ON b.comp_no = aa.comp_no AND b.wkor_id = aa.wkor_id                                                      ");
        query.append(this.getWkorWhere(assetRptBdIntensityCommonDTO, user));
        query.append("        LEFT OUTER JOIN TAWOFAIL d                                                                                ");
        query.append("        ON aa.comp_no = d.comp_no AND aa.wkor_id = d.wkor_id                                                      ");
        query.append("        WHERE 1=1                                                                                                 ");
        query.append(this.getWhere(assetRptBdIntensityCommonDTO, user));
        query.append("        GROUP BY a.comp_no, a.equip_id, a.item_no, a.description                                                  ");
        query.append("    ) y                                                                                                           ");
        query.append("    ON x.comp_no = y.comp_no AND x.equip_id = y.equip_id                                                          ");
        query.append(") a                                                                                                               ");
        query.getOrderByQuery("a.equip_id", "a.eqdn_time DESC", assetRptBdIntensityCommonDTO.getOrderBy(), assetRptBdIntensityCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(assetRptBdIntensityCommonDTO.getIsLoadMaxCount(), assetRptBdIntensityCommonDTO.getFirstRow()));
    }
    
    public List findEqLocList(AssetRptBdIntensityCommonDTO assetRptBdIntensityCommonDTO, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;     																");
    	query.append("SELECT   									");
        query.append("	  '' 					AS seqNo		");
        query.append("	, c.plant   			AS plantId		");
        query.append("  , (SELECT a.description FROM TAPLANT a WHERE a.comp_no = c.comp_no AND a.plant = c.plant) 		AS plantDesc		");
        query.append("  , c.eqloc_id 			AS eqLocId		");
        query.append("  , (SELECT a.full_desc FROM TAEQLOC a WHERE a.comp_no = c.comp_no AND a.eqloc_id = c.eqloc_id )  AS eqLocDesc		");
        query.append("  , '' 					AS usageDeptId	");
        query.append("  , ''		  			AS usageDeptDesc");
        query.append("  , ''		 			AS eqCtgId		");
        query.append("  , ''					AS eqCtgDesc	");
        query.append("  , ''		     		AS equipId		");
        query.append("  , '' 					AS itemNo		");
        query.append("  , '' 					AS equipDesc	");
        query.append("  , ISNULL(ROUND( (COUNT(1)/MAX(z.loadtm))*100,2), ROUND((COUNT(1) / (o.avgTime) ) * 100 ,2))   			AS bdFreqRate		");
        query.append("  , ISNULL(ROUND((ISNULL(SUM(y.work_time),0)/MAX(z.loadtm))*100,2),  ROUND((ISNULL(SUM(y.work_time),0) / (o.avgTime)) * 100 ,2)) 	AS bdDuraRate	");
        query.append("FROM TAEQUIPMENT c LEFT OUTER JOIN 		");
        query.append("	TAWOEQUIP x  ON c.equip_id = x.equip_id	");
        query.append("  LEFT OUTER JOIN TAWORKORDER y ON x.wkor_id = y.wkor_id AND wkor_date > dateadd(month, -12, convert(varchar, getdate(), 112))		");
        query.append("  AND wkor_date <= convert(varchar, getdate(), 112)  AND y.wo_status = 'C' AND y.wo_type ='BM'		");
        query.append(this.getWkorWhere(assetRptBdIntensityCommonDTO, user));
        query.append("LEFT OUTER JOIN (SELECT 																	");
        query.append("						SUM(CASE WHEN A.workse LIKE '%R' THEN A.sumworktime END)  loadtm	");
        query.append("                    , c.equip_id															");
        query.append("                    , c.comp_no															");
        query.append("                 FROM TXDWHP_POPEQACT A INNER JOIN TXDWHP_POPEQ b              			");
        query.append("                   ON A.eqpid = b.eqpid              										");
        query.append("                 INNER JOIN TAEQUIPMENT c ON c.equip_id =  b.equip_id       				");
        query.append("                 WHERE A.workentm > dateadd(month, -12, convert(varchar, getdate(), 112))	");
        query.append("                   AND A.workentm <= convert(varchar, getdate(), 112)						");
        query.append("                 GROUP BY c.comp_no, c.equip_id											");
        query.append("                 )  z ON c.equip_id = z.equip_id	AND c.comp_no = z.comp_no				");
        query.append("		CROSS JOIN ( SELECT 																");
        query.append("						ROUND(SUM(p.ruTime)/COUNT(p.equip_id),0) avgTime					");
        query.append("                   FROM ( SELECT															");
        query.append("                          	SUM(CASE WHEN A.workse LIKE '%R' THEN A.sumworktime END)  ruTime ");
        query.append("                            , b.equip_id 													");
        query.append("                          FROM TXDWHP_POPEQACT A INNER JOIN TXDWHP_POPEQ b              	");
        query.append("                            ON A.eqpid = b.eqpid        									");
        query.append("                         WHERE A.workentm IS NOT NULL      								");
        query.append("                           AND b.equip_id IS NOT NULL										");
        query.append("                           AND A.workentm > dateadd(month, -12, convert(varchar, getdate(), 112))			");
        query.append("                           AND A.workentm <= convert(varchar, getdate(), 112)      		");
        query.append("                         GROUP BY b.equip_id												");
        query.append("                         ) p																");
        query.append("                 ) o																		");
        query.append("WHERE 1 = 1																				");
        query.append(this.getWhere(assetRptBdIntensityCommonDTO, user));
        query.append("GROUP BY  c.comp_no, o.avgTime, c.plant, c.eqloc_id                     		");
        query.append("ORDER BY  c.comp_no, o.avgTime, c.plant, c.eqloc_id                     		");
    	
    	return getJdbcTemplate().queryForList(query.toString(assetRptBdIntensityCommonDTO.getIsLoadMaxCount(), assetRptBdIntensityCommonDTO.getFirstRow()));
    }
    
    public List findUsageDeptList(AssetRptBdIntensityCommonDTO assetRptBdIntensityCommonDTO, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;     																");
    	query.append("SELECT   									");
        query.append("	  '' 					AS seqNo		");
        query.append("	, c.plant   			AS plantId		");
        query.append("  , (SELECT a.description FROM TAPLANT a WHERE a.comp_no = c.comp_no AND a.plant = c.plant) 		AS plantDesc		");
        query.append("  , '' 					AS eqLocId		");
        query.append("  , ''  					AS eqLocDesc	");
        query.append("  , c.usage_dept 			AS usageDeptId	");
        query.append("  , (SELECT a.description FROM TADEPT a WHERE a.comp_no = c.comp_no AND a.dept_id = c.usage_dept) AS usageDeptDesc	");
        query.append("  , ''		 			AS eqCtgId		");
        query.append("  , ''					AS eqCtgDesc	");
        query.append("  , ''		     		AS equipId		");
        query.append("  , '' 					AS itemNo		");
        query.append("  , '' 					AS equipDesc	");
        query.append("  , ISNULL(ROUND( (COUNT(1)/MAX(z.loadtm))*100,2), ROUND((COUNT(1) / (o.avgTime) ) * 100 ,2))   			AS bdFreqRate		");
        query.append("  , ISNULL(ROUND((ISNULL(SUM(y.work_time),0)/MAX(z.loadtm))*100,2),  ROUND((ISNULL(SUM(y.work_time),0) / (o.avgTime)) * 100 ,2)) 	AS bdDuraRate	");
        query.append("FROM TAEQUIPMENT c LEFT OUTER JOIN 		");
        query.append("	TAWOEQUIP x  ON c.equip_id = x.equip_id	");
        query.append("  LEFT OUTER JOIN TAWORKORDER y ON x.wkor_id = y.wkor_id AND wkor_date > dateadd(month, -12, convert(varchar, getdate(), 112))		");
        query.append("  AND wkor_date <= convert(varchar, getdate(), 112)  AND y.wo_status = 'C' AND y.wo_type ='BM'		");
        query.append(this.getWkorWhere(assetRptBdIntensityCommonDTO, user));
        query.append("LEFT OUTER JOIN (SELECT 																	");
        query.append("						SUM(CASE WHEN A.workse LIKE '%R' THEN A.sumworktime END)  loadtm	");
        query.append("                    , c.equip_id															");
        query.append("                    , c.comp_no															");
        query.append("                 FROM TXDWHP_POPEQACT A INNER JOIN TXDWHP_POPEQ b              			");
        query.append("                   ON A.eqpid = b.eqpid              										");
        query.append("                 INNER JOIN TAEQUIPMENT c ON c.equip_id =  b.equip_id       				");
        query.append("                 WHERE A.workentm > dateadd(month, -12, convert(varchar, getdate(), 112))	");
        query.append("                   AND A.workentm <= convert(varchar, getdate(), 112)						");
        query.append("                 GROUP BY c.comp_no, c.equip_id											");
        query.append("                 )  z ON c.equip_id = z.equip_id	AND c.comp_no = z.comp_no				");
        query.append("		CROSS JOIN ( SELECT 																");
        query.append("						ROUND(SUM(p.ruTime)/COUNT(p.equip_id),0) avgTime					");
        query.append("                   FROM ( SELECT															");
        query.append("                          	SUM(CASE WHEN A.workse LIKE '%R' THEN A.sumworktime END)  ruTime ");
        query.append("                            , b.equip_id 													");
        query.append("                          FROM TXDWHP_POPEQACT A INNER JOIN TXDWHP_POPEQ b              	");
        query.append("                            ON A.eqpid = b.eqpid        									");
        query.append("                         WHERE A.workentm IS NOT NULL      								");
        query.append("                           AND b.equip_id IS NOT NULL										");
        query.append("                           AND A.workentm > dateadd(month, -12, convert(varchar, getdate(), 112))			");
        query.append("                           AND A.workentm <= convert(varchar, getdate(), 112)      		");
        query.append("                         GROUP BY b.equip_id												");
        query.append("                         ) p																");
        query.append("                 ) o																		");
        query.append("WHERE 1 = 1																				");
        query.append(this.getWhere(assetRptBdIntensityCommonDTO, user));
        query.append("GROUP BY  c.comp_no, o.avgTime, c.plant, c.usage_dept       		");
        query.append("ORDER BY  c.comp_no, o.avgTime, c.plant, c.usage_dept       		");      	
    	
    	return getJdbcTemplate().queryForList(query.toString(assetRptBdIntensityCommonDTO.getIsLoadMaxCount(), assetRptBdIntensityCommonDTO.getFirstRow()));
    }
    
    public List findEqCtgList(AssetRptBdIntensityCommonDTO assetRptBdIntensityCommonDTO, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;     																");
    	query.append("SELECT   									");
        query.append("	  '' 					AS seqNo		");
        query.append("	, c.plant   			AS plantId		");
        query.append("  , (SELECT a.description FROM TAPLANT a WHERE a.comp_no = c.comp_no AND a.plant = c.plant) 		AS plantDesc		");
        query.append("  , '' 					AS eqLocId		");
        query.append("  , ''  					AS eqLocDesc	");
        query.append("  , '' 					AS usageDeptId	");
        query.append("  , '' 					AS usageDeptDesc");
        query.append("  , c.eqctg_id 			AS eqCtgId		");
        query.append("  , (SELECT a.description FROM TAEQCTG a WHERE a.comp_no = c.comp_no AND a.eqctg_id = c.eqctg_id) AS eqCtgDesc		");
        query.append("  , ''		     		AS equipId		");
        query.append("  , '' 					AS itemNo		");
        query.append("  , '' 					AS equipDesc	");
        query.append("  , ISNULL(ROUND( (COUNT(1)/MAX(z.loadtm))*100,2), ROUND((COUNT(1) / (o.avgTime) ) * 100 ,2))   			AS bdFreqRate		");
        query.append("  , ISNULL(ROUND((ISNULL(SUM(y.work_time),0)/MAX(z.loadtm))*100,2),  ROUND((ISNULL(SUM(y.work_time),0) / (o.avgTime)) * 100 ,2)) 	AS bdDuraRate	");
        query.append("FROM TAEQUIPMENT c LEFT OUTER JOIN 		");
        query.append("	TAWOEQUIP x  ON c.equip_id = x.equip_id	");
        query.append("  LEFT OUTER JOIN TAWORKORDER y ON x.wkor_id = y.wkor_id AND wkor_date > dateadd(month, -12, convert(varchar, getdate(), 112))		");
        query.append("  AND wkor_date <= convert(varchar, getdate(), 112)  AND y.wo_status = 'C' AND y.wo_type ='BM'		");
        query.append(this.getWkorWhere(assetRptBdIntensityCommonDTO, user));
        query.append("LEFT OUTER JOIN (SELECT 																	");
        query.append("						SUM(CASE WHEN A.workse LIKE '%R' THEN A.sumworktime END)  loadtm	");
        query.append("                    , c.equip_id															");
        query.append("                    , c.comp_no															");
        query.append("                 FROM TXDWHP_POPEQACT A INNER JOIN TXDWHP_POPEQ b              			");
        query.append("                   ON A.eqpid = b.eqpid              										");
        query.append("                 INNER JOIN TAEQUIPMENT c ON c.equip_id =  b.equip_id       				");
        query.append("                 WHERE A.workentm > dateadd(month, -12, convert(varchar, getdate(), 112))	");
        query.append("                   AND A.workentm <= convert(varchar, getdate(), 112)						");
        query.append("                 GROUP BY c.comp_no, c.equip_id											");
        query.append("                 )  z ON c.equip_id = z.equip_id	AND c.comp_no = z.comp_no				");
        query.append("		CROSS JOIN ( SELECT 																");
        query.append("						ROUND(SUM(p.ruTime)/COUNT(p.equip_id),0) avgTime					");
        query.append("                   FROM ( SELECT															");
        query.append("                          	SUM(CASE WHEN A.workse LIKE '%R' THEN A.sumworktime END)  ruTime ");
        query.append("                            , b.equip_id 													");
        query.append("                          FROM TXDWHP_POPEQACT A INNER JOIN TXDWHP_POPEQ b              	");
        query.append("                            ON A.eqpid = b.eqpid        									");
        query.append("                         WHERE A.workentm IS NOT NULL      								");
        query.append("                           AND b.equip_id IS NOT NULL										");
        query.append("                           AND A.workentm > dateadd(month, -12, convert(varchar, getdate(), 112))			");
        query.append("                           AND A.workentm <= convert(varchar, getdate(), 112)      		");
        query.append("                         GROUP BY b.equip_id												");
        query.append("                         ) p																");
        query.append("                 ) o																		");
        query.append("WHERE 1 = 1																				");
        query.append(this.getWhere(assetRptBdIntensityCommonDTO, user));
		query.append("GROUP BY  c.comp_no, o.avgTime, c.plant, c.eqctg_id	");
		query.append("ORDER BY  c.comp_no, o.avgTime, c.plant, c.eqctg_id	");
        
    	return getJdbcTemplate().queryForList(query.toString(assetRptBdIntensityCommonDTO.getIsLoadMaxCount(), assetRptBdIntensityCommonDTO.getFirstRow()));
    }
    
    public List findEqpList(AssetRptBdIntensityCommonDTO assetRptBdIntensityCommonDTO, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;     																");
    	query.append("SELECT   									");
        query.append("	  '' 					AS seqNo		");
        query.append("	, c.plant   			AS plantId		");
        query.append("  , (SELECT a.description FROM TAPLANT a WHERE a.comp_no = c.comp_no AND a.plant = c.plant) 		AS plantDesc		");
        query.append("  , c.eqloc_id 			AS eqLocId		");
        query.append("  , (SELECT a.full_desc FROM TAEQLOC a WHERE a.comp_no = c.comp_no AND a.eqloc_id = c.eqloc_id )  AS eqLocDesc		");
        query.append("  , c.usage_dept 			AS usageDeptId	");
        query.append("  , (SELECT a.description FROM TADEPT a WHERE a.comp_no = c.comp_no AND a.dept_id = c.usage_dept) AS usageDeptDesc	");
        query.append("  , c.eqctg_id 			AS eqCtgId		");
        query.append("  , (SELECT a.description FROM TAEQCTG a WHERE a.comp_no = c.comp_no AND a.eqctg_id = c.eqctg_id) AS eqCtgDesc		");
        query.append("  , c.equip_id     		AS equipId		");
        query.append("  , MAX(c.item_no) 		AS itemNo		");
        query.append("  , MAX(c.description) 	AS equipDesc	");
        query.append("  , ISNULL(ROUND( (COUNT(1)/MAX(z.loadtm))*100,2), ROUND((COUNT(1) / (o.avgTime) ) * 100 ,2))   			AS bdFreqRate		");
        query.append("  , ISNULL(ROUND((ISNULL(SUM(y.work_time),0)/MAX(z.loadtm))*100,2),  ROUND((ISNULL(SUM(y.work_time),0) / (o.avgTime)) * 100 ,2)) 	AS bdDuraRate	");
        query.append("FROM TAEQUIPMENT c LEFT OUTER JOIN 		");
        query.append("	TAWOEQUIP x  ON c.equip_id = x.equip_id	");
        query.append("  LEFT OUTER JOIN TAWORKORDER y ON x.wkor_id = y.wkor_id AND wkor_date > dateadd(month, -12, convert(varchar, getdate(), 112))		");
        query.append("  AND wkor_date <= convert(varchar, getdate(), 112)  AND y.wo_status = 'C' AND y.wo_type ='BM'		");
        query.append(this.getWkorWhere(assetRptBdIntensityCommonDTO, user));
        query.append("LEFT OUTER JOIN (SELECT 																	");
        query.append("						SUM(CASE WHEN A.workse LIKE '%R' THEN A.sumworktime END)  loadtm	");
        query.append("                    , c.equip_id															");
        query.append("                    , c.comp_no															");
        query.append("                 FROM TXDWHP_POPEQACT A INNER JOIN TXDWHP_POPEQ b              			");
        query.append("                   ON A.eqpid = b.eqpid              										");
        query.append("                 INNER JOIN TAEQUIPMENT c ON c.equip_id =  b.equip_id       				");
        query.append("                 WHERE A.workentm > dateadd(month, -12, convert(varchar, getdate(), 112))	");
        query.append("                   AND A.workentm <= convert(varchar, getdate(), 112)						");
        query.append("                 GROUP BY c.comp_no, c.equip_id											");
        query.append("                 )  z ON c.equip_id = z.equip_id	AND c.comp_no = z.comp_no				");
        query.append("		CROSS JOIN ( SELECT 																");
        query.append("						ROUND(SUM(p.ruTime)/COUNT(p.equip_id),0) avgTime					");
        query.append("                   FROM ( SELECT															");
        query.append("                          	SUM(CASE WHEN A.workse LIKE '%R' THEN A.sumworktime END)  ruTime ");
        query.append("                            , b.equip_id 													");
        query.append("                          FROM TXDWHP_POPEQACT A INNER JOIN TXDWHP_POPEQ b              	");
        query.append("                            ON A.eqpid = b.eqpid        									");
        query.append("                         WHERE A.workentm IS NOT NULL      								");
        query.append("                           AND b.equip_id IS NOT NULL										");
        query.append("                           AND A.workentm > dateadd(month, -12, convert(varchar, getdate(), 112))			");
        query.append("                           AND A.workentm <= convert(varchar, getdate(), 112)      		");
        query.append("                         GROUP BY b.equip_id												");
        query.append("                         ) p																");
        query.append("                 ) o																		");
        query.append("WHERE 1 = 1																				");
        query.append(this.getWhere(assetRptBdIntensityCommonDTO, user));
		query.append("GROUP BY  c.comp_no, o.avgTime, c.plant, c.eqloc_id, c.usage_dept, c.eqctg_id, c.equip_id		");
		query.append("ORDER BY  c.comp_no, o.avgTime, c.plant, c.eqloc_id, c.usage_dept, c.eqctg_id, c.equip_id		");
        
    	return getJdbcTemplate().queryForList(query.toString(assetRptBdIntensityCommonDTO.getIsLoadMaxCount(), assetRptBdIntensityCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param assetRptBdIntensityCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(AssetRptBdIntensityCommonDTO assetRptBdIntensityCommonDTO, User user) throws Exception
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.getAndQuery("a.comp_no", user.getCompNo());
        query.getAndQuery("a.is_last_version", "Y");
        query.getAndQuery("a.is_deleted", "N");
        query.getAndQuery("a.eq_status", "R");
    	
    	// 부서
		query.getDeptLevelQuery("a.dept_id", assetRptBdIntensityCommonDTO.getFilterDeptId(), assetRptBdIntensityCommonDTO.getFilterDeptDesc(), user.getCompNo());

		// 공장
    	query.getCodeLikeQuery("a.plant", "(SELECT dd.description FROM  TAPLANT dd WHERE dd.comp_no = aa.comp_no AND dd.plant = a.plant )",
                assetRptBdIntensityCommonDTO.getFilterPlantId(), assetRptBdIntensityCommonDTO.getFilterPlantDesc());
    	//설비번호
        query.getLikeQuery("a.item_no", assetRptBdIntensityCommonDTO.getFilterEquipNo());
        
        return query.toString();
    }
    
    private String getWkorWhere(AssetRptBdIntensityCommonDTO assetRptBdIntensityCommonDTO, User user) throws Exception
    {        
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append(" AND aa.wo_status IN('PRC', 'C')	");
    	query.append(" AND aa.wo_type = 'BM'			");

    	// 월
    	String fromMonth = assetRptBdIntensityCommonDTO.getFilterStartDate();
    	String toMonth   = assetRptBdIntensityCommonDTO.getFilterEndDate();
    	
    	if (fromMonth != null && !"".equals(fromMonth) && toMonth != null && !"".equals(toMonth))
    	{
    		query.getAndDateQuery("SUBSTRING(aa.wkor_date,1,6)", fromMonth, toMonth);
    	}

    	return query.toString();
    }
    
    private String getLnWrkWhere(AssetRptBdIntensityCommonDTO assetRptBdIntensityCommonDTO, User user) throws Exception
    {        
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	// 월
    	String fromMonth = assetRptBdIntensityCommonDTO.getFilterStartDate();
    	String toMonth   = assetRptBdIntensityCommonDTO.getFilterEndDate();
    	
    	if (fromMonth != null && !"".equals(fromMonth) && toMonth != null && !"".equals(toMonth))
    	{
    		query.getAndDateQuery("SUBSTRING(aa.wrk_date,1,6)", fromMonth, toMonth);
    	}
    	
    	return query.toString();
    }
    
    public String findTotalCount(
            AssetRptBdIntensityCommonDTO assetRptBdIntensityCommonDTO, User user) throws Exception {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT                ");
        query.append("    COUNT(1)          ");
        query.append("FROM TAEQUIPMENT a    ");
        query.append("WHERE 1=1             ");
        query.append(this.getWhere(assetRptBdIntensityCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }    
}
