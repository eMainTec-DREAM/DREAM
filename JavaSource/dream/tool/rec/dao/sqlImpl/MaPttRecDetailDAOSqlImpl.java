package dream.tool.rec.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.tool.rec.dao.MaPttRecDetailDAO;
import dream.tool.rec.dto.MaPttRecDetailDTO;

/**
 * 구매입고 - 상세 dao
 * 
 * @author ssong
 * @version $Id: $
 * @since 1.0
 * @spring.bean id="maPttRecDetailDAOTarget"
 * @spring.txbn id="maPttRecDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPttRecDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPttRecDetailDAO
{
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param prRecListId
     * @return
     */
    public MaPttRecDetailDTO findDetail(User user, String prRecListId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String compNo = user.getCompNo();
        
        query.append("SELECT x.comp_no                             					compNo,      		");
        query.append("       x.prreclist_id                        					prRecListId, 		");
        query.append("       x.prreclist_no                        					prRecListNo,		");
        query.append("       x.prreclist_status                    					prRecListStatus,	");
        query.append("       dbo.SFACODE_TO_DESC(x.prreclist_status, 'PRRECLIST_STATUS', 'SYS', x.comp_no,'"+user.getLangId()+"') prRecListStatusDesc, "); 
        query.append("       x.rec_date                            					recDate,     		"); 
        query.append("       x.vendor_id                           					vendorId,    		"); 
        query.append("       dbo.SFAIDTODESC(x.vendor_id, '', 'VENDOR', x.comp_no)	vendorDesc,  		"); 
        query.append("       x.part_id                             					partId,      		"); 
        query.append("       y.part_no                             					partNo,      		");
        query.append("       x.dept_id                             					deptId,      		"); 
        query.append("       dbo.SFAIDTODESC(x.dept_id, '', 'DEPT', x.comp_no) 		deptDesc,	 		"); 
        query.append("       CONCAT(y.description,', ',y.pt_size)        			partNameSize,		");
        query.append("       x.wcode_id                            					wcodeId,     		");
        query.append("      (SELECT wname FROM TAWAREHOUSE                      						");
        query.append("       WHERE  comp_no  = x.comp_no                        						");
        query.append("         AND  wcode_id = x.wcode_id)         					wname,       		"); //창고명         
        query.append("       ISNULL(x.rec_qty, 0)                     				recQty,      		"); 
        query.append("       ISNULL(x.unit_price, 0)                  				unitPrice,   		"); 
        query.append("       x.inspector                           					inspector,   		");  
        query.append("       dbo.SFAIDTODESC(x.inspector, '', 'EMP', x.comp_no) 	inspectorName, 		");  
        query.append("       ISNULL(x.tot_price, 0)                   				totPrice,    		"); 
        query.append("       x.remark                              					remark       		"); 
        query.append("FROM   TAPTPRRECLIST x LEFT JOIN TAPARTS y                         				");
        query.append("  ON   x.comp_no      = y.comp_no	                    							");
        query.append("  AND  x.part_id      = y.part_id                      							");
        query.append("  WHERE 1=1                  														");
        query.append("  AND  x.comp_no      = '"+compNo+"'	                    						");
        query.append("  AND  x.prreclist_id = '"+prRecListId+"'                 						");
    
        MaPttRecDetailDTO maPttRecDetailDTO = 
        		(MaPttRecDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaPttRecDetailDTO()));
        
        return maPttRecDetailDTO;
    }
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRecDetailDTO
     * @return
     */
    public int insertDetail(MaPttRecDetailDTO maPttRecDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAPTPRRECLIST (                               ");
    	query.append("  comp_no,   prreclist_id, prreclist_no, prreclist_status,");
    	query.append("  dept_id,   wcode_id,     vendor_id,    rec_date,        ");
    	query.append("  part_id,   rec_qty,      unit_price,   tot_price, 	    ");
    	query.append("	inspector, remark                                       ");
    	query.append(")	VALUES (                                                ");
    	query.append("  ?,         ?,             ?,            ?,              ");
    	query.append("  ?,         ?,             ?,            ?,              ");
    	query.append("  ?,         ?,             ?,            ?,              ");
    	query.append("  ?,         ?							                ");
    	query.append(")                                                         ");
    	
    	Object[] objects = new Object[] {
    			maPttRecDetailDTO.getCompNo(),
    			maPttRecDetailDTO.getPrRecListId(),
    			maPttRecDetailDTO.getPrRecListNo(),
    			maPttRecDetailDTO.getPrRecListStatus(),
    			maPttRecDetailDTO.getDeptId(),
    			maPttRecDetailDTO.getWcodeId(),
    			maPttRecDetailDTO.getVendorId(),
    			maPttRecDetailDTO.getRecDate(),
    			maPttRecDetailDTO.getPartId(),
    			maPttRecDetailDTO.getRecQty(),
    			maPttRecDetailDTO.getUnitPrice(),
    			maPttRecDetailDTO.getTotPrice(),
    			maPttRecDetailDTO.getInspector(),
    			maPttRecDetailDTO.getRemark()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail stock
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRecDetailDTO
     * @return
     */
    public int updateDetail(MaPttRecDetailDTO maPttRecDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TAPTPRRECLIST SET		      ");
    	query.append("	     prreclist_status   = ?,      ");
        query.append("       dept_id            = ?,      ");
        query.append("       wcode_id           = ?,      ");
        query.append("       vendor_id          = ?,      ");    
        query.append("       rec_date           = ?,      ");    
        query.append("       part_id            = ?,      ");
        query.append("       rec_qty            = ?,      ");     
        query.append("       unit_price         = ?,      ");  
        query.append("       tot_price          = ?,      ");
        query.append("       inspector          = ?,      ");
        query.append("       prreclist_no       = ?,      ");
        query.append("       remark             = ?       ");
    	query.append("WHERE  comp_no            = ?	      ");
    	query.append("  AND  prreclist_id       = ?       ");
    	
    	Object[] objects = new Object[] {
                maPttRecDetailDTO.getPrRecListStatus(),
                maPttRecDetailDTO.getDeptId(),
                maPttRecDetailDTO.getWcodeId(),
                maPttRecDetailDTO.getVendorId(),
                maPttRecDetailDTO.getRecDate(),
                maPttRecDetailDTO.getPartId(),
                maPttRecDetailDTO.getRecQty(),
                maPttRecDetailDTO.getUnitPrice(),
                maPttRecDetailDTO.getTotPrice(),
                maPttRecDetailDTO.getInspector(),
                maPttRecDetailDTO.getPrRecListNo(),
                maPttRecDetailDTO.getRemark(),
                maPttRecDetailDTO.getCompNo(),
                maPttRecDetailDTO.getPrRecListId()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail 상태 Update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRecDetailDTO
     * @return
     */
    public int updatePtRecListStatus(MaPttRecDetailDTO maPttRecDetailDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TAPTPRRECLIST SET		      ");
        query.append("	     prreclist_status   = ?       ");
        query.append("WHERE  comp_no            = ?	      ");
        query.append("  AND  prreclist_id       = ?       ");
        
        Object[] objects = new Object[] {
                maPttRecDetailDTO.getPrRecListStatus(),
                maPttRecDetailDTO.getCompNo(),
                maPttRecDetailDTO.getPrRecListId()
        };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
       
    /**
     * valid check
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPttRecDetailDTO
     * @return
     */
    public String validPrRecListNo(MaPttRecDetailDTO maPttRecDetailDTO)
    {
        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT COUNT(*)                                           ");
        query.append("FROM   TAPTPRRECLIST x                                    ");
        query.append("WHERE  comp_no      = '"+maPttRecDetailDTO.getCompNo()+"'  ");
        query.append("  AND  prreclist_no = '"+maPttRecDetailDTO.getPrRecListNo()+"'  ");
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    
    /**
     * 상태 조회
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPttRecDetailDTO
     * @return
     */
    public String findPrRecListStatus(String compNo, String prRecListId)
    {
        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT prreclist_status                                   ");
        query.append("FROM   TAPTPRRECLIST x                                    ");
        query.append("WHERE  comp_no      = '"+compNo+"'                        ");
        query.append("  AND  prreclist_id = '"+prRecListId+"'                   ");
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
      
    /**
     * 입고 History insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRecDetailDTO
     * @return
     */
    public int insertRecHistory(String ptRecHistId, MaPttRecDetailDTO maPttRecDetailDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("INSERT INTO TAPTRECHIST                                   ");
        query.append("SELECT x.comp_no,     '"+ptRecHistId+"',                  ");
        query.append("       '"+maPttRecDetailDTO.getPtRecMode()+"' ptrecMode,   ");
        query.append("       'PRREC'                               ptrecType,   ");
        query.append("       prreclist_id,  dept_id,    x.wcode_id,    vendor_id,");
        query.append("       rec_date,      x.part_id,  '' partGrade,  rec_qty,  ");
        query.append("       unit_price,    tot_price                           ");
        query.append("FROM   TAPTPRRECLIST x                                    ");
        query.append("WHERE  x.comp_no      = '"+maPttRecDetailDTO.getCompNo()+"'      ");
        query.append("  AND  x.prreclist_id = '"+maPttRecDetailDTO.getPrRecListId()+"' ");
        
        return this.getJdbcTemplate().update(query.toString());
    }
    
    /**
     * 입고완료 전에 SP_PT_INSTOCK 프로시져 호출 
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param ptRecHistId
     * @return
     * @throws Exception
     */
    public int executeSpPtInstock(String compNo, String ptRecHistId) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("EXEC dbo.SP_PT_INSTOCK '"+compNo+"', '"+ptRecHistId+"';          ");
      
        this.getJdbcTemplate().execute(query.toString());
        
        return 0;
    }
    
}