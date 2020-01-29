package dream.part.rpt.orgptusehist.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.part.rpt.orgptusehist.dao.PartRptOrgPtUseHistListDAO;
import dream.part.rpt.orgptusehist.dto.PartRptOrgPtUseHistCommonDTO;

/**
 * PartRptOrgPtUseHist Page - List DAO implements
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="partRptOrgPtUseHistListDAOTarget"
 * @spring.txbn id="partRptOrgPtUseHistListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PartRptOrgPtUseHistListDAOOraImpl  extends BaseJdbcDaoSupportOra implements PartRptOrgPtUseHistListDAO
{

    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param partRptOrgPtUseHistCommonDTO
     * @return List
     */
    public List findList(PartRptOrgPtUseHistCommonDTO partRptOrgPtUseHistCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        
        query.append("SELECT 														");
        query.append("  ''                        			AS SEQNO				");
        query.append("  , a.dept_id          				AS DEPTID				");
        query.append("  , a.description     				AS ORGNAME				");
        query.append("  , ( SELECT                                        			");
        query.append("      	NVL(SUM(x.use_qty),0)     							");
        query.append("      FROM TAWOPARTS x INNER JOIN TAWORKORDER y        		");
        query.append("      ON x.comp_no = y.comp_no                        		");
        query.append("      AND x.wkor_id = y.wkor_id                        		");
        query.append("      WHERE 1=1                                        		");
        query.append(this.getDeptWhere(partRptOrgPtUseHistCommonDTO, user));
        query.append("        				  ) 			AS USECNT				");
        query.append("   , ( SELECT                                        			");
        query.append("       	NVL(SUM(x.tot_price),0) 							");
        query.append("       FROM TAWOPARTS x INNER JOIN TAWORKORDER y        		");
        query.append("       ON x.comp_no = y.comp_no                        		");
        query.append("       AND x.wkor_id = y.wkor_id                        		");
        query.append("       WHERE 1=1                                        		");
        query.append(this.getDeptWhere(partRptOrgPtUseHistCommonDTO, user));
        query.append("        				  ) 			AS USEAMT				");
        query.append("FROM TADEPT a													");
        query.append("WHERE 1=1 													");
        query.append(this.getWhere(partRptOrgPtUseHistCommonDTO, user));

        query.getOrderByQuery("a.dept_id","a.description", partRptOrgPtUseHistCommonDTO.getOrderBy(), partRptOrgPtUseHistCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(partRptOrgPtUseHistCommonDTO.getIsLoadMaxCount(), partRptOrgPtUseHistCommonDTO.getFirstRow()));
    }

    private String getDeptWhere(PartRptOrgPtUseHistCommonDTO partRptOrgPtUseHistCommonDTO, User user)
    {        
    	QueryBuffer query = new QueryBuffer();

    	query.getAndQuery("x.comp_no", user.getCompNo());
    	query.append(" AND y.wo_status = 'C'            ");
        query.append(" AND y.dept_id IS NOT NULL								");
	    query.append(" AND y.dept_id IN (SELECT dept_id 						");
	    query.append("                   FROM TADEPT  							");
	    query.append("                   WHERE comp_no = a.comp_no 				");
	    query.append("                   START WITH dept_id = a.dept_id			");
	    query.append("                   CONNECT BY PRIOR dept_id = p_dept_id)	");

        // 월
        String fromYyyymm = partRptOrgPtUseHistCommonDTO.getFilterStartYyyymm();
        String toYyyymm   = partRptOrgPtUseHistCommonDTO.getFilterEndYyyymm();
        
        if (fromYyyymm != null && !"".equals(fromYyyymm) && !"null".equals(fromYyyymm))
        {
            query.append("AND y.wkor_date >= TO_CHAR(TO_DATE('"+fromYyyymm+"','YYYY-MM'),'YYYYMMDD')     ");
        }
        
        if (toYyyymm != null && !"".equals(toYyyymm) && !"null".equals(toYyyymm))
        {
            query.append("AND y.wkor_date < TO_CHAR(ADD_MONTHS(TO_DATE('"+toYyyymm+"','YYYY-MM'),1),'YYYYMMDD')        ");
        }        

        // 공장
        query.getCodeLikeQuery("y.plant"
                , "(SELECT z.description FROM TAPLANT z WHERE z.comp_no = x.comp_no AND z.plant = y.plant) "
                , partRptOrgPtUseHistCommonDTO.getFilterPlantId(), partRptOrgPtUseHistCommonDTO.getFilterPlantDesc());

    	return query.toString();
    }
    
    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     *   
     * @param partRptOrgPtUseHistCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(PartRptOrgPtUseHistCommonDTO partRptOrgPtUseHistCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("a.comp_no", user.getCompNo());
        query.getAndQuery("a.is_use", "Y");

        // 부서
        query.getDeptLevelQuery("a.dept_id", partRptOrgPtUseHistCommonDTO.getFilterDeptId(), partRptOrgPtUseHistCommonDTO.getFilterDeptDesc(), user.getCompNo());
        
    	// 조직구분
        query.getCodeLikeQuery("a.dept_categ","SFACODE_TO_DESC(a.dept_categ,'DEPT_CATEG','SYS','','"+user.getLangId()+"')", partRptOrgPtUseHistCommonDTO.getFilterDeptCategId(), partRptOrgPtUseHistCommonDTO.getFilterDeptCategDesc());

        //공장
        query.getCodeLikeQuery("a.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = '"+user.getCompNo()+"' AND plant = a.plant )", 
                partRptOrgPtUseHistCommonDTO.getFilterPlantId(), partRptOrgPtUseHistCommonDTO.getFilterPlantDesc());
        
        return query.toString();
    }

    public String findTotalCount(
            PartRptOrgPtUseHistCommonDTO partRptOrgPtUseHistCommonDTO, User user) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT            ");
        query.append("	COUNT(1)        ");
        query.append("FROM TADEPT a		");
        query.append("WHERE 1=1 		");
        query.append(this.getWhere(partRptOrgPtUseHistCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}
