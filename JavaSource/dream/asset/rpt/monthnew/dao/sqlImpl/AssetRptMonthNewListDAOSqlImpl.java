package dream.asset.rpt.monthnew.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
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
public class AssetRptMonthNewListDAOSqlImpl extends BaseJdbcDaoSupportSql implements AssetRptMonthNewListDAO
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
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        int month = Integer.parseInt(assetRptMonthNewListDTO.getMonths());
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;     ");
        query.append("SELECT                                                ");
        query.append("    ''        				AS    SEQNO        		");
        query.append("    ,x.dept_id                AS    DEPTID        	");
        query.append("    ,x.description            AS    DEPTDESC    		");
        query.append("	  ,x.p_dept_id        		AS 	  PDEPTID			");
        query.append("    ,x.dept_id                AS 	  ID       			");
        query.append("    ,MIN(y.lvl) OVER()    	AS 	  MINLVL    		");
        query.append("    ,y.lvl 					AS	  LVL             	");
        
        for(int a=0; a<=month; a++) {
        	
        	String fromYear = assetRptMonthNewListDTO.getFilterStartDate().replaceAll("-", "").substring(0, 4); // 년
        	String fromMonth = assetRptMonthNewListDTO.getFilterStartDate().replaceAll("-", "").substring(4);   // 월
        	String fromDate = fromYear+fromMonth;
        	
            query.append("    ,( SELECT                                         ");
            query.append("           COUNT(1)                                   ");
            query.append("         FROM TAEQUIPMENT a                           ");
            query.append("         WHERE 1=1                                    ");
            query.append("  AND  a.comp_no = x.comp_no							");
            query.getAndQuery("a.is_last_version", "Y");
            query.getAndQuery("a.is_deleted", "N");
            query.append("  AND a.cre_date BETWEEN '"+fromDate+"01000000' AND '"+fromDate+"31235959' ");
            query.append("  AND a.dept_id IN ( SELECT b.dept_id 				");
            query.append("                     FROM dbo.SFADEPT_ALL('"+loginUser.getCompNo()+"',y.dept_id ) b INNER JOIN TADEPT c		");
            query.append("						ON b.comp_no = c.comp_no		");
            query.append("						AND b.dept_id = c.dept_id		");
            query.getAndQuery("c.is_use", "Y");
            query.getAndQuery("c.is_monitoring", "Y");
            //공장코드
            query.getCodeLikeQuery("c.plant", "(SELECT d.description FROM  TAPLANT d WHERE d.comp_no = '"+loginUser.getCompNo()+"' AND d.plant = c.plant )", 
            		assetRptMonthNewListDTO.getFilterPlantId(), assetRptMonthNewListDTO.getFilterPlantDesc());
            
            query.append("			)											");
            query.append("     )      NEWEQUIPCNT"+fromDate+"    				");
            
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
        query.append("FROM   TADEPT x ,(SELECT * FROM dbo.SFADEPT_ALL('"+loginUser.getCompNo()+"','0')) y                                           ");
    	query.append("WHERE x.dept_id = y.dept_id                                                ");
        query.append(this.getWhere(assetRptMonthNewListDTO, loginUser));

        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public String getWhere(AssetRptMonthNewListDTO assetRptMonthNewListDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
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