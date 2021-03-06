package dream.asset.rpt.maintcost.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.asset.rpt.maintcost.dao.AssetRptMaintCostListDAO;
import dream.asset.rpt.maintcost.dto.AssetRptMaintCostCommonDTO;

/**
 * 수선유지비 집행현황 목록  - List DAO implements
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="assetRptMaintCostListDAOTarget"
 * @spring.txbn id="assetRptMaintCostListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetRptMaintCostListDAOOraImpl  extends BaseJdbcDaoSupportOra implements AssetRptMaintCostListDAO
{

    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptMaintCostCommonDTO
     * @return List
     */
    public List findList(AssetRptMaintCostCommonDTO assetRptMaintCostCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("select 																											");
        query.append("    '' 																						as seqNo			");
        query.append("    ,a.plant    																				as plantId			");
        query.append("    ,(select description from TAPLANT where comp_no = a.comp_no and plant = a.plant)          as plantDesc		");
        query.append("        ,(SELECT c.description FROM TADEPT c WHERE c.comp_no = a.comp_no AND c.dept_id = b.dept_id)	 DEPTDESC 		");
        query.append("    ,SUBSTR(a.tmonth,0,4)||'-'||SUBSTR(a.tmonth,5,2) 																				as month			");
        query.append("    ,NVL(b.tot_cnt,0) 																		as EXEMAINTAMT		");
        query.append("    ,NVL(b.mit_cnt,0) 																		as PLANMAINTAMT		");
        query.append("    ,ROUND(NVL(b.mit_cnt,0) / case when b.tot_cnt is null then 1 else b.tot_cnt end *100,2) 	as PLANVSRSLTAMT	");
        query.append("from  (select y.plant, x.tmonth, y.comp_no																		");
        query.append("       from TAMONTH x inner join TAPLANT y on 1=1																	");
        query.append("       where 1=1																									");
        query.append(this.getWhere(assetRptMaintCostCommonDTO, user, "y"));
        query.append("          ) a left outer join 								");
        query.append("      (select 												");
        query.append("              a.plant											");
        query.append("              ,a.res_date 						as tmonth	");
        query.append("              ,COUNT(*) 							as tot_cnt	");
        query.append("              ,SUM(mit_cnt) 						as mit_cnt	");
        query.append("              ,a.dept_id										");
        query.append("       from (													");
        query.append("            select 											");
        query.append("                  b.plant										");
        query.append("                  ,SUBSTR(a.res_date,1,6) 		as res_date	");
        query.append("                  ,case when a.woreq_gen_type ='EM'  then 1 else 0 end mit_cnt								");
        query.append("					, b.dept_id 					as dept_id	");
        query.append("            from TAWOREQRES a inner join TAWORKORDER b on a.comp_no = b.comp_no and a.wkor_id = b.wkor_id		");
        query.append("            where 1=1											");
        query.append(this.getWhere(assetRptMaintCostCommonDTO, user, "b"));
        query.append("            ) a												");
        query.append("        where 1=1												");
        query.append("        group by a.plant, a.res_date							");
        query.append("		, a.dept_id												");
        query.append("       ) b on a.plant = b.plant and a.tmonth = b.tmonth 		");
        query.append("where 1=1														");
//        query.append("order by MONTH,b.plant										");
        
        query.getOrderByQuery("MONTH, b.plant", assetRptMaintCostCommonDTO.getOrderBy(), assetRptMaintCostCommonDTO.getDirection());

        
        return getJdbcTemplate().queryForList(query.toString(assetRptMaintCostCommonDTO.getIsLoadMaxCount(), assetRptMaintCostCommonDTO.getFirstRow()));
    }

    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     *   
     * @param assetRptMaintCostCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(AssetRptMaintCostCommonDTO assetRptMaintCostCommonDTO, User user, String alias) throws Exception
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery(alias+".comp_no", user.getCompNo());
        
        // 공장
        query.getCodeLikeQuery(alias + ".plant", "(SELECT xx.description FROM  TAPLANT xx WHERE xx.comp_no = '"+user.getCompNo()+"' AND " +alias +".plant = xx.plant )", 
        		assetRptMaintCostCommonDTO.getFilterPlantId(), assetRptMaintCostCommonDTO.getFilterPlantDesc());
        // 월
        String fromMonth = assetRptMaintCostCommonDTO.getFilterStartDate();
        String toMonth   = assetRptMaintCostCommonDTO.getFilterEndDate();
        
        if (fromMonth != null && !"".equals(fromMonth) && toMonth != null && !"".equals(toMonth))
        {
        	if ("b".equals(alias)) {
        		query.getAndDateQuery("a.res_date", fromMonth +"01", DateUtil.plusLastDayOfMonth(toMonth));
			} else if ("y".equals(alias)){
				query.getAndDateQuery("x.tmonth", fromMonth, toMonth);
			}
        }
        
        if ("b".equals(alias)) {
			query.getAndQuery("a.woreqres_method", "WO");
			query.getAndQuery("b.is_deleted", "N");
			//부서
			query.getDeptLevelQuery("b.dept_id", assetRptMaintCostCommonDTO.getFilterDeptId(), assetRptMaintCostCommonDTO.getFilterDeptDesc(), user.getCompNo());
		
		}
        return query.toString();
    }

    public String findTotalCount(
            AssetRptMaintCostCommonDTO assetRptMaintCostCommonDTO, User user) throws Exception {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                            	");
        query.append("    		count(1)									");
        query.append("from  (select y.plant, x.tmonth, y.comp_no																	");
        query.append("       from TAMONTH x inner join TAPLANT y on 1=1																");
        query.append("       where 1=1																								");
        query.append(this.getWhere(assetRptMaintCostCommonDTO, user, "y"));
        query.append("          ) a left outer join 								");
        query.append("      (select 												");
        query.append("              a.plant											");
        query.append("              ,a.res_date 						as tmonth	");
        query.append("              ,COUNT(*) 							as tot_cnt	");
        query.append("              ,SUM(mit_cnt) 						as mit_cnt	");
        query.append("              ,a.dept_id										");
        query.append("       from (													");
        query.append("            select 											");
        query.append("                  b.plant										");
        query.append("                  ,SUBSTR(a.res_date,1,6) 		as res_date	");
        query.append("                  ,case when a.woreq_gen_type ='EM'  then 1 else 0 end mit_cnt								");
        query.append("					, b.dept_id 					as dept_id	");
        query.append("            from TAWOREQRES a inner join TAWORKORDER b on a.comp_no = b.comp_no and a.wkor_id = b.wkor_id		");
        query.append("            where 1=1											");
        query.append(this.getWhere(assetRptMaintCostCommonDTO, user, "b"));
        query.append("            ) a												");
        query.append("        where 1=1												");
        query.append("        group by a.plant, a.res_date							");
        query.append("		, a.dept_id												");
        query.append("       ) b on a.plant = b.plant and a.tmonth = b.tmonth 		");
        query.append("where 1=1														");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}
