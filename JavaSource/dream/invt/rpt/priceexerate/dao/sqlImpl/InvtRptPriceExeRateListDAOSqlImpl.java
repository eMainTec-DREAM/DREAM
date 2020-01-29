package dream.invt.rpt.priceexerate.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.invt.rpt.priceexerate.dao.InvtRptPriceExeRateListDAO;
import dream.invt.rpt.priceexerate.dto.InvtRptPriceExeRateCommonDTO;

/**
 * 투자비 집행현황 목록 - List DAO implements
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="invtRptPriceExeRateListDAOTarget"
 * @spring.txbn id="invtRptPriceExeRateListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class InvtRptPriceExeRateListDAOSqlImpl  extends BaseJdbcDaoSupportSql implements InvtRptPriceExeRateListDAO
{

    /**
     * grid find
     * @author  cjscjs9
     * @version $Id:$
     * @since   1.0
     * 
     * @param invtRptPriceExeRateCommonDTO
     * @return List
     */
    public List findList(InvtRptPriceExeRateCommonDTO invtRptPriceExeRateCommonDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;     															");
        query.append("select 																											");
        query.append("    '' 																							as seqNo		");
        query.append("    ,a.plant    																					as plantId		");
        query.append("    ,(select description from TAPLANT where comp_no = a.comp_no and plant = a.plant)          	as plantDesc	");
        query.append("    ,a.tmonth 																					as month		");
        query.append("    ,isnull(b.tot_cnt,0) 																			as totCnt		");
        query.append("    ,isnull(b.mit_cnt,0) 																			as mitCnt		");
        query.append("    ,ROUND(isnull(b.mit_cnt,0) / case when b.tot_cnt is null then 1 else b.tot_cnt end *100,2) 	as mitRate		");
        query.append("from  (select y.plant, x.tmonth, y.comp_no																		");
        query.append("       from TAMONTH x inner join TAPLANT y on 1=1																	");
        query.append("       where 1=1																									");
        query.append(this.getWhere(invtRptPriceExeRateCommonDTO, user, "y"));
        query.append("          ) a left outer join 								");
        query.append("      (select 												");
        query.append("              a.plant											");
        query.append("              ,a.res_date 						as tmonth	");
        query.append("              ,COUNT(*) 							as tot_cnt	");
        query.append("              ,SUM(mit_cnt) 						as mit_cnt	");
        query.append("       from (													");
        query.append("            select 											");
        query.append("                  b.plant										");
        query.append("                  ,SUBSTRING(a.res_date,1,6) 		as res_date	");
        query.append("                  ,case when a.woreq_gen_type ='EM'  then 1 else 0 end mit_cnt								");
        query.append("            from TAWOREQRES a inner join TAWORKORDER b on a.comp_no = b.comp_no and a.wkor_id = b.wkor_id		");
        query.append("            where 1=1											");
        query.append(this.getWhere(invtRptPriceExeRateCommonDTO, user, "b"));
        query.append("            ) a												");
        query.append("        where 1=1												");
        query.append("        group by a.plant, a.res_date							");
        query.append("       ) b on a.plant = b.plant and a.tmonth = b.tmonth 		");
        query.append("where 1=1														");
        query.getOrderByQuery("a.plant", "a.plant, a.tmonth", invtRptPriceExeRateCommonDTO.getOrderBy(), invtRptPriceExeRateCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(invtRptPriceExeRateCommonDTO.getIsLoadMaxCount(), invtRptPriceExeRateCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  cjscjs9
     * @version $Id: $
     * @since   1.0
     * 
     * @param invtRptPriceExeRateCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(InvtRptPriceExeRateCommonDTO invtRptPriceExeRateCommonDTO, User user, String alias) throws Exception
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery(alias+".comp_no", user.getCompNo());
        
        // 공장
        query.getCodeLikeQuery(alias + ".plant", "(SELECT xx.description FROM  TAPLANT xx WHERE xx.comp_no = '"+user.getCompNo()+"' AND " +alias +".plant = xx.plant )", 
        		invtRptPriceExeRateCommonDTO.getFilterPlantId(), invtRptPriceExeRateCommonDTO.getFilterPlantDesc());
        // 월
        String fromMonth = invtRptPriceExeRateCommonDTO.getFilterStartDate();
        String toMonth   = invtRptPriceExeRateCommonDTO.getFilterEndDate();
        
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
		}
        
        return query.toString();
    }
    
    public String findTotalCount(
            InvtRptPriceExeRateCommonDTO invtRptPriceExeRateCommonDTO, User user) throws Exception {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;     		");
        query.append("SELECT                                            			");
        query.append("    		count(1)											");
        query.append("from  (select y.plant, x.tmonth, y.comp_no					");
        query.append("       from TAMONTH x inner join TAPLANT y on 1=1				");
        query.append("       where 1=1												");
        query.append(this.getWhere(invtRptPriceExeRateCommonDTO, user, "y"));
        query.append("          ) a left outer join 								");
        query.append("      (select 												");
        query.append("              a.plant											");
        query.append("              ,a.res_date 						as tmonth	");
        query.append("              ,COUNT(*) 							as tot_cnt	");
        query.append("              ,SUM(mit_cnt) 						as mit_cnt	");
        query.append("       from (													");
        query.append("            select 											");
        query.append("                  b.plant										");
        query.append("                  ,SUBSTRING(a.res_date,1,6) 		as res_date	");
        query.append("                  ,case when a.woreq_gen_type ='EM'  then 1 else 0 end mit_cnt								");
        query.append("            from TAWOREQRES a inner join TAWORKORDER b on a.comp_no = b.comp_no and a.wkor_id = b.wkor_id		");
        query.append("            where 1=1											");
        query.append(this.getWhere(invtRptPriceExeRateCommonDTO, user, "b"));
        query.append("            ) a												");
        query.append("        where 1=1												");
        query.append("        group by a.plant, a.res_date							");
        query.append("       ) b on a.plant = b.plant and a.tmonth = b.tmonth 		");
        query.append("where 1=1														");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }    
}
