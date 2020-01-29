package dream.asset.rpt.monthnew.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.asset.rpt.monthnew.dao.AssetRptMonthNewListDAO;
import dream.asset.rpt.monthnew.dto.AssetRptMonthNewListDTO;

/**
 * 신규설비등록현황 DAO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="assetRptMonthNewListDAOTarget"
 * @spring.txbn id="assetRptMonthNewListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetRptMonthNewListDAOOraImpl extends BaseJdbcDaoSupportOra implements AssetRptMonthNewListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param assetRptMonthNewListDTO
     * @return List
     */
    public List findConnList(AssetRptMonthNewListDTO assetRptMonthNewListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        int month = Integer.parseInt(assetRptMonthNewListDTO.getMonths());
        
        query.append("SELECT                                                ");
        query.append("    ''        				AS    seqno        		");
        query.append("    ,x.dept_id                AS    deptId        	");
        query.append("    ,x.description            AS    deptDesc    		");
        query.append("	  ,x.p_dept_id        		AS 	  pdeptId			");
        query.append("    ,x.dept_id                AS 	  ID       			");
        query.append("   , LEVEL          			AS	  LVL				");
        query.append("   , MIN(LEVEL) OVER() 		AS	  MINLVL			");
        
        for(int a=0; a<=month; a++) {
        	
        	String fromYear = assetRptMonthNewListDTO.getFilterStartDate().replaceAll("-", "").substring(0, 4); // 년
        	String fromMonth = assetRptMonthNewListDTO.getFilterStartDate().replaceAll("-", "").substring(4);
        	String fromDate = fromYear+fromMonth;
        	
            query.append("    ,( SELECT                                         ");
            query.append("           COUNT(1)                                   ");
            query.append("         FROM TAEQUIPMENT a                           ");
            query.append("         WHERE 1=1                                    ");
            query.append("  AND  a.comp_no = x.comp_no							");
            query.getAndQuery("a.is_last_version", "Y");
            query.getAndQuery("a.is_deleted", "N");
            query.append("  AND a.cre_date BETWEEN '"+fromDate+"01000000' AND '"+fromDate+"31235959' ");
            query.append("AND a.dept_id IN ( SELECT dept_id                     ");
            query.append("                     FROM TADEPT                      ");
            query.append("                     WHERE comp_no = x.comp_no        ");
            query.getAndQuery("is_use", "Y");
            query.getAndQuery("is_monitoring", "Y");
            //공장코드
            query.getCodeLikeQuery("plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+loginUser.getCompNo()+"' AND a.plant = plant )", 
            		assetRptMonthNewListDTO.getFilterPlantId(), assetRptMonthNewListDTO.getFilterPlantDesc());
            query.append("                     START WITH dept_id = x.dept_id   ");
            query.append("                     CONNECT BY PRIOR dept_id = p_dept_id    ");
            query.append("                  )                                   ");
            query.append("     )      newEquipCnt"+fromDate+"    				");
            
            if(Integer.parseInt(fromMonth)+1==13)
            {
            	fromYear = Integer.toString(Integer.parseInt(fromYear)+1); // 1년 증가
        		fromMonth = "01"; // 1월로 월 초기화
            }
        	else
        	{
        		fromMonth = Integer.parseInt(fromMonth)+1<10?"0"+Integer.toString(Integer.parseInt(fromMonth)+1):Integer.toString(Integer.parseInt(fromMonth)+1);
        	}
	            assetRptMonthNewListDTO.setFilterStartDate(fromYear+fromMonth);
	            
            }
        query.append("FROM TADEPT x                                         ");
        query.append("WHERE 1=1                                             ");
        query.append(this.getWhere(assetRptMonthNewListDTO, loginUser));
        query.append("START WITH p_dept_id = 0								");
        query.append("CONNECT BY PRIOR x.dept_id = x.p_dept_id      		");

        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param assetRptMonthNewListDTO
     * @return
     * @throws Exception
     */
    private String getWhere(AssetRptMonthNewListDTO assetRptMonthNewListDTO, User loginUser)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getAndQuery("x.is_use", "Y");
        query.getAndQuery("x.is_monitoring", "Y");

        //부서
        query.getDeptLevelQuery("x.dept_id", assetRptMonthNewListDTO.getFilterDeptId(), assetRptMonthNewListDTO.getFilterDeptDesc(), loginUser.getCompNo());

        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+loginUser.getCompNo()+"' AND a.plant = x.plant )", 
        		assetRptMonthNewListDTO.getFilterPlantId(), assetRptMonthNewListDTO.getFilterPlantDesc());
        
        return query.toString();
    }        

}