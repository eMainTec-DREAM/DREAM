package dream.tool.rec.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.tool.rec.dao.MaPttRecListDAO;
import dream.tool.rec.dto.MaPttRecCommonDTO;

/**
 * 구매입고 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPttRecListDAOTarget"
 * @spring.txbn id="maPttRecListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPttRecListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPttRecListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRecCommonDTO
     * @return List
     */
    public List findList(MaPttRecCommonDTO maPttRecCommonDTO,User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = maPttRecCommonDTO.getCompNo();
        
        query.append("SELECT ''                                             		seqNo,          ");
        query.append("       ''                                             		isDelCheck,     ");
        query.append("       x.comp_no                                      		compNo,         ");
        query.append("       x.prreclist_id                                 		prRecListId,    ");
        query.append("       x.prreclist_no                                 		prRecListNo,    ");
        query.append("       dbo.SFAIDTODESC(x.dept_id, '', 'DEPT', x.comp_no)  	deptDesc,       "); 
        query.getDate("x.rec_date", "recDate,");
        query.append("       dbo.SFAIDTODESC(x.vendor_id, '', 'VENDOR', x.comp_no) 	vendorDesc,  	"); 
        query.append("       x.prreclist_status                             		prRecListStatus,");
        query.append("       dbo.SFACODE_TO_DESC(x.prreclist_status, 'PRRECLIST_STATUS', 'SYS', x.comp_no,'"+user.getLangId()+"') recStatusDesc, ");
        query.append("       y.part_no                                      		partNo,         ");
        query.append("       CONCAT(y.description,', ',y.pt_size)           		partNameSize,   ");
        query.append("       ISNULL(x.rec_qty, 0)                              		recQty,         "); 
        query.append("       ISNULL(x.unit_price, 0)                           		unitPrice,      "); 
        query.append("       ISNULL(x.tot_price, 0)                            		totPrice,       ");
        query.append("       dbo.SFAIDTODESC(x.inspector, '', 'EMP', x.comp_no) 	inspectorName   ");  
        query.append("FROM   TAPTPRRECLIST x LEFT OUTER JOIN TAPARTS y                           	");
        query.append("   ON x.comp_no = y.comp_no                            						");
        query.append("  AND x.part_id = y.part_id                            						");
        query.append("  WHERE 1=1                          											");
        query.append("  AND x.comp_no = '"+compNo+"'                            					");
        query.append(this.getWhere(maPttRecCommonDTO, user));
        query.getOrderByQuery("x.prreclist_id","x.prreclist_id DESC", maPttRecCommonDTO.getOrderBy(), maPttRecCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maPttRecCommonDTO.getIsLoadMaxCount(), maPttRecCommonDTO.getFirstRow()));
   }
    
    /**
     * delete
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteList(String compNo, String prRecListId)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query = new QuerySqlBuffer();
    	
    	query.append("DELETE FROM TAPTPRRECLIST		              ");
    	query.append("WHERE  comp_no       = '"+compNo+"'		  ");
    	query.append("  AND  prreclist_id  = '"+prRecListId+"'    ");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	return rtnValue;
    }
    
    /**
     * Filter 조건
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRecCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaPttRecCommonDTO maPttRecCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
      
        if (!"".equals(maPttRecCommonDTO.getPrRecListId()))
        {
            query.getAndQuery("x.prreclist_id", maPttRecCommonDTO.getPrRecListId());
            return query.toString();
        }
        query.getAndQuery("y.part_categ", "TOOL");
        //상태
        query.getSysCdQuery("x.prreclist_status", maPttRecCommonDTO.getPrRecStatus(), maPttRecCommonDTO.getPrRecStatusDesc(), "PRRECLIST_STATUS", maPttRecCommonDTO.getCompNo(), user.getLangId());
          
        // 부서
          query.getDeptLevelQuery("x.dept_id", maPttRecCommonDTO.getFilterDeptId(), maPttRecCommonDTO.getFilterDeptDesc(), maPttRecCommonDTO.getCompNo());
        
        //입고일자
        String startDate = maPttRecCommonDTO.getFilterRecStartDate();
        String endDate = maPttRecCommonDTO.getFilterRecEndDate();
        query.getAndDateQuery("x.rec_date", startDate, endDate);

        
        // 검수자
        if(!"".equals(maPttRecCommonDTO.getFilterInspector()))
        {
            query.getAndQuery("x.inspector", maPttRecCommonDTO.getFilterInspector());
        }
        else if(!"".equals(maPttRecCommonDTO.getFilterInspectorName()))
        {
            query.append(" AND  x.inspector IN (SELECT emp_id FROM TAEMP        ");
            query.append("                      WHERE  comp_no = x.comp_no      ");
            query.getLikeQuery("emp_name", maPttRecCommonDTO.getFilterInspectorName());
            query.append("					   )						        ");
        }
        
        // 품명/규격
        if(!"".equals(maPttRecCommonDTO.getFilterPartNameSize()))
        {
            query.append(" AND  x.part_id IN (SELECT part_id FROM TAPARTS       ");
            query.append("                    WHERE  comp_no = x.comp_no        ");
            query.getLikeQuery("description", maPttRecCommonDTO.getFilterPartNameSize());
            query.append("                    UNION ALL");      
            query.append("                    SELECT part_id FROM TAPARTS       ");
            query.append("                    WHERE  comp_no = x.comp_no        ");
            query.getLikeQuery("pt_size", maPttRecCommonDTO.getFilterPartNameSize());
            query.append("                    )                                 ");      
        }
        
        // 거래처
        if(!"".equals(maPttRecCommonDTO.getFilterDeptId()))
        {
            query.getAndQuery("x.vendor_id", maPttRecCommonDTO.getFilterVendorId());
        }
        else if(!"".equals(maPttRecCommonDTO.getFilterVendorDesc()))
        {
            query.append(" AND  x.vendor_id IN (SELECT vendor_id FROM TAVENDOR  ");
            query.append("                    WHERE  comp_no = x.comp_no        ");
            query.getLikeQuery("description", maPttRecCommonDTO.getFilterVendorDesc());
            query.append("                     )                                ");
        }
        //상태
        query.getAndQuery("x.prreclist_status", maPttRecCommonDTO.getPrRecStatus());
        
        return query.toString();
    }

	@Override
	public String findTotalCount(MaPttRecCommonDTO maPttRecCommonDTO, User user) throws Exception {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
        query.append("SELECT          		            				     ");
        query.append("		  COUNT(*)      			                	 ");
        query.append("FROM   TAPTPRRECLIST x LEFT OUTER JOIN TAPARTS y                           	");
        query.append("   ON x.comp_no = y.comp_no                            						");
        query.append("  AND x.part_id = y.part_id                            						");
        query.append("  WHERE 1=1                          											");
        query.append("  AND x.comp_no = '"+user.getCompNo()+"'                     					");
        query.append(this.getWhere(maPttRecCommonDTO, user));
        List resultList =  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
	}
}