package dream.part.rpt.orgptusehist.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.part.rpt.orgptusehist.dao.PartRptOrgPtUseHistDetailDAO;
import dream.part.rpt.orgptusehist.dto.PartRptOrgPtUseHistDetailDTO;

/**
 * PartRptOrgPtUseHist Page - Detail DAO implements
 * @author youngjoo38
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="partRptOrgPtUseHistDetailDAOTarget"
 * @spring.txbn id="partRptOrgPtUseHistDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PartRptOrgPtUseHistDetailDAOOraImpl  extends BaseJdbcDaoSupportOra implements PartRptOrgPtUseHistDetailDAO
{

    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param partRptOrgPtUseHistDetailDTO
     * @return List
     */
    public List findDetail(PartRptOrgPtUseHistDetailDTO partRptOrgPtUseHistDetailDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("    ''                              AS SEQNO              ");
        query.append("  , b.tmonth                        AS MONTH              ");
        query.append("  , NVL(SUM(a.use_qty),0)           AS USECNT             ");
        query.append("  , NVL(SUM(a.tot_price),0)         AS USEAMT             ");
        query.append("FROM (                                                    ");
        query.append("    SELECT                                                ");
        query.append("        y.wkor_date                                       ");
        query.append("        ,x.use_qty                                        ");
        query.append("        ,x.tot_price                                      ");
        query.append("    FROM TAWOPARTS x INNER JOIN TAWORKORDER y             ");
        query.append("    ON x.comp_no = y.comp_no                              ");
        query.append("    AND x.wkor_id = y.wkor_id                             ");
        query.append("    WHERE 1=1                                             ");
        query.append(this.getWhere(partRptOrgPtUseHistDetailDTO, user));
        query.append(") a RIGHT OUTER JOIN TADAY b                              ");
        query.append("ON a.wkor_date = b.tday                                   ");
        query.append("WHERE 1=1                                                 ");
        query.append(this.getDayWhere(partRptOrgPtUseHistDetailDTO, user));
        query.append("GROUP BY b.tmonth                                         ");
        query.append("ORDER BY b.tmonth                                         ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    /**
     * find chart
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param partRptOrgPtUseHistDetailDTO
     * @return List
     */
    public List findChart(PartRptOrgPtUseHistDetailDTO partRptOrgPtUseHistDetailDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("    ''                              AS SEQNO              ");
        query.append("  , b.tmonth                        AS MONTH              ");
        query.append("  , NVL(SUM(a.use_qty),0)           AS USECNT             ");
        query.append("  , NVL(SUM(a.tot_price),0)         AS USEAMT             ");
        query.append("FROM (                                                    ");
        query.append("    SELECT                                                ");
        query.append("        y.wkor_date                                       ");
        query.append("        ,x.use_qty                                        ");
        query.append("        ,x.tot_price                                      ");
        query.append("    FROM TAWOPARTS x INNER JOIN TAWORKORDER y             ");
        query.append("    ON x.comp_no = y.comp_no                              ");
        query.append("    AND x.wkor_id = y.wkor_id                             ");
        query.append("    WHERE 1=1                                             ");
        query.append(this.getWhere(partRptOrgPtUseHistDetailDTO, user));
        query.append(") a RIGHT OUTER JOIN TADAY b                              ");
        query.append("ON a.wkor_date = b.tday                                   ");
        query.append("WHERE 1=1                                                 ");
        query.append(this.getDayWhere(partRptOrgPtUseHistDetailDTO, user));
        query.append("GROUP BY b.tmonth                                         ");
        query.append("ORDER BY b.tmonth                                         ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }

    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     *   
     * @param partRptOrgPtUseHistDetailDTO
     * @return
     * @throws Exception
     */
    private String getWhere(PartRptOrgPtUseHistDetailDTO partRptOrgPtUseHistDetailDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.append("  AND y.wo_status = 'C'         ");
        query.append("  AND y.dept_id IS NOT NULL		");
        query.append("  AND y.wkor_date IS NOT NULL		");
        
        // 부서 
        query.append("  AND y.dept_id IN (SELECT a.dept_id 		");
        query.append("                    FROM TADEPT a  		");
        query.append("                    WHERE 1=1 			");
        query.getAndQuery("a.comp_no", user.getCompNo());
        query.append("                    START WITH a.dept_id = "
        	+	partRptOrgPtUseHistDetailDTO.getDeptId() + "	");
        query.append("                    CONNECT BY PRIOR a.dept_id = a.p_dept_id)		");
            
        // 공장
        query.getCodeLikeQuery("y.plant"
                , "(SELECT z.description FROM TAPLANT z WHERE z.comp_no = x.comp_no AND z.plant = y.plant) "
                , partRptOrgPtUseHistDetailDTO.getPlantId(), partRptOrgPtUseHistDetailDTO.getPlantDesc());

        return query.toString();
    }
    
    private String getDayWhere(PartRptOrgPtUseHistDetailDTO partRptOrgPtUseHistDetailDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        // 월
        String fromYyyymm = partRptOrgPtUseHistDetailDTO.getStartYyyymm();
        String toYyyymm   = partRptOrgPtUseHistDetailDTO.getEndYyyymm();
        
        if (fromYyyymm != null && !"".equals(fromYyyymm) && !"null".equals(fromYyyymm))
        {
            query.append("AND b.tday >= TO_CHAR(TO_DATE('"+fromYyyymm+"','YYYY-MM'),'YYYYMMDD')     ");
        }
        
        if (toYyyymm != null && !"".equals(toYyyymm) && !"null".equals(toYyyymm))
        {
            query.append("AND b.tday < TO_CHAR(ADD_MONTHS(TO_DATE('"+toYyyymm+"','YYYY-MM'),1),'YYYYMMDD')        ");
        }        
        
        return query.toString();
    }
}
