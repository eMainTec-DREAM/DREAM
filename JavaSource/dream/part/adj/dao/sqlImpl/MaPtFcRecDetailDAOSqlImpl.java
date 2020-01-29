package dream.part.adj.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.part.adj.dao.MaPtFcRecDetailDAO;
import dream.part.adj.dto.MaPtFcRecDetailDTO;

/**
 * 무상입고 - 상세 dao
 * 
 * @author ssong
 * @version $Id: $
 * @since 1.0
 * @spring.bean id="maPtFcRecDetailDAOTarget"
 * @spring.txbn id="maPtFcRecDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtFcRecDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPtFcRecDetailDAO
{
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param fcRecListId
     * @return
     */
    public MaPtFcRecDetailDTO findDetail(User user, String fcRecListId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String compNo = user.getCompNo();
        query.append("SELECT x.comp_no                             compNo,      ");
        query.append("       x.fcreclist_id                        fcRecListId, ");
        query.append("       x.fcreclist_no                        fcRecListNo, ");
        query.append("       x.fcreclist_status                    fcRecListStatus,");
        query.append("       dbo.SFACODE_TO_DESC(x.fcreclist_status, 'FCRECLIST_STATUS', 'SYS', x.comp_no,'"+user.getLangId()+"') fcRecListStatusDesc, "); 
        query.append("       x.rec_date                            recDate,     "); 
        query.append("       x.part_id                             partId,      "); 
        query.append("       y.part_no                             partNo,      ");
        query.append("       x.dept_id                             deptId,      "); 
        query.append("       dbo.SFAIDTODESC(x.dept_id, '', 'DEPT', x.comp_no) deptDesc, "); 
        query.append("       y.description+', '+ISNULL(y.pt_size,'')           partNameSize,");
        query.append("       x.wcode_id                            wcodeId,     ");
        query.append("      (SELECT wname FROM TAWAREHOUSE                      ");
        query.append("       WHERE  comp_no  = x.comp_no                        ");
        query.append("         AND  wcode_id = x.wcode_id)         wname,       "); //창고명         
        query.append("       CONVERT( INT, ISNULL(x.rec_qty, 0) )  recQty,      "); 
        query.append("       ISNULL(x.unit_price, 0)                  unitPrice,   "); 
        query.append("       ISNULL(x.tot_price, 0)                   totPrice,    "); 
        query.append("       x.remark                              remark,      "); 
        query.append("       x.vendor_id                           vendorId,	");
        query.append("       (SELECT description 							    ");
        query.append("          FROM TAVENDOR a								    ");
        query.append("         WHERE a.comp_no = x.comp_no					    ");
        query.append("           AND a.vendor_id = x.vendor_id) vendorDesc	    ");
        query.append("      , x.currency                           														AS currencyId	");
        query.append("      , dbo.SFACODE_TO_DESC(x.currency, 'CURRENCY', 'SYS', x.comp_no,'"+ user.getLangId() +"') 	AS currencyDesc	");
        query.append("     , x.part_grade 						   partGrade	");
        query.append("     , (SELECT dbo.SFACODE_TO_DESC(x.part_grade, 'PART_GRADE', 'SYS', x.comp_no, '"+user.getLangId()+"') ) partGradeDesc		");
        query.append("		, y.description 						partDesc	");
        query.append("		, y.model 								partModel	");
        query.append("		, y.pt_size 							partSize	");
        query.append("FROM   TAPTFCRECLIST x LEFT OUTER JOIN TAPARTS y          ");
        query.append("		ON  	x.comp_no      = y.comp_no	            	");
        query.append("  	AND  	x.part_id      = y.part_id              	");
        query.append("WHERE  1=1	                    						");
        query.append("  AND  x.comp_no      = '"+compNo+"'	                    ");
        query.append("  AND  x.fcreclist_id = '"+fcRecListId+"'                 ");
    
        MaPtFcRecDetailDTO maPtFcRecDetailDTO = 
        		(MaPtFcRecDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaPtFcRecDetailDTO()));
        
        return maPtFcRecDetailDTO;
    }
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtFcRecDetailDTO
     * @return
     */
    public int insertDetail(MaPtFcRecDetailDTO maPtFcRecDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAPTFCRECLIST (                               ");
    	query.append("  comp_no,   fcreclist_id, fcreclist_no, fcreclist_status,");
    	query.append("  dept_id,   wcode_id,     part_grade,   rec_date,        ");
    	query.append("  part_id,   rec_qty,      unit_price,   tot_price, 	    ");
    	query.append("	remark,    vendor_id,    currency                       ");
    	query.append(")	VALUES (                                                ");
    	query.append("  ?,         ?,             ?,            ?,              ");
    	query.append("  ?,         ?,             ?,            ?,              ");
    	query.append("  ?,         ?,             ?,            ?,              ");
    	query.append("  ?,         ?,   		  ?				         	    ");
    	query.append(")                                                         ");
    	
    	Object[] objects = new Object[] {
    			maPtFcRecDetailDTO.getCompNo(),
    			maPtFcRecDetailDTO.getFcRecListId(),
    			maPtFcRecDetailDTO.getFcRecListNo(),
    			maPtFcRecDetailDTO.getFcRecListStatus(),
    			maPtFcRecDetailDTO.getDeptId(),
    			maPtFcRecDetailDTO.getWcodeId(),
    			maPtFcRecDetailDTO.getPartGrade(),
    			maPtFcRecDetailDTO.getRecDate(),
    			maPtFcRecDetailDTO.getPartId(),
    			maPtFcRecDetailDTO.getRecQty(),
    			maPtFcRecDetailDTO.getUnitPrice(),
    			maPtFcRecDetailDTO.getTotPrice(),
    			maPtFcRecDetailDTO.getRemark(),
    			maPtFcRecDetailDTO.getVendorId(),
    			maPtFcRecDetailDTO.getCurrencyId()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail stock
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     */
    public int updateDetail(MaPtFcRecDetailDTO maPtFcRecDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TAPTFCRECLIST SET		      ");
    	query.append("	     fcreclist_status   = ?,      ");
        query.append("       dept_id            = ?,      ");
        query.append("       wcode_id           = ?,      ");
        query.append("       rec_date           = ?,      ");
        query.append("       part_id            = ?,      ");
        query.append("       rec_qty            = ?,      ");
        query.append("       unit_price         = ?,      ");
        query.append("       tot_price          = ?,      ");
        query.append("       fcreclist_no       = ?,      ");
        query.append("       vendor_id          = ?,      ");
        query.append("       currency          	= ?,      ");
        query.append("       remark             = ?       ");
        query.append("     , part_grade         = ?       ");
    	query.append("WHERE  comp_no            = ?	      ");
    	query.append("  AND  fcreclist_id       = ?       ");
    	
    	Object[] objects = new Object[] {
    			maPtFcRecDetailDTO.getFcRecListStatus(),
    			maPtFcRecDetailDTO.getDeptId(),
    			maPtFcRecDetailDTO.getWcodeId(),
                maPtFcRecDetailDTO.getRecDate(),
                maPtFcRecDetailDTO.getPartId(),
                maPtFcRecDetailDTO.getRecQty(),
                maPtFcRecDetailDTO.getUnitPrice(),
                maPtFcRecDetailDTO.getTotPrice(),
                maPtFcRecDetailDTO.getFcRecListNo(),
                maPtFcRecDetailDTO.getVendorId(),
                maPtFcRecDetailDTO.getCurrencyId(),
                maPtFcRecDetailDTO.getRemark(),
                maPtFcRecDetailDTO.getPartGrade(),
                maPtFcRecDetailDTO.getCompNo(),
                maPtFcRecDetailDTO.getFcRecListId()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail 상태 Update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRecDetailDTO
     * @return
     */
    public int updatePtFcRecListStatus(MaPtFcRecDetailDTO maPtRecDetailDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TAPTFCRECLIST SET		      ");
        query.append("	     fcreclist_status   = ?       ");
        query.append("WHERE  comp_no            = ?	      ");
        query.append("  AND  fcreclist_id       = ?       ");
        
        Object[] objects = new Object[] {
                maPtRecDetailDTO.getFcRecListStatus(),
                maPtRecDetailDTO.getCompNo(),
                maPtRecDetailDTO.getFcRecListId()
        };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
       
    /**
     * valid check
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPtFcRecDetailDTO
     * @return
     */
    public String validFcRecListNo(MaPtFcRecDetailDTO maPtFcRecDetailDTO)
    {
        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT COUNT(*)                                           ");
        query.append("FROM   TAPTFCRECLIST x                                    ");
        query.append("WHERE  comp_no      = '"+maPtFcRecDetailDTO.getCompNo()+"'  ");
        query.append("  AND  fcreclist_no = '"+maPtFcRecDetailDTO.getFcRecListNo()+"'  ");
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    
    /**
     * 상태 조회
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPtFcRecDetailDTO
     * @return
     */
    public String findFcRecListStatus(String compNo, String fcRecListId)
    {
    	
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        //입고상태 조회
        query.append("SELECT fcreclist_status                                   ");
        query.append("FROM   TAPTFCRECLIST x                                    ");
        query.append("WHERE  comp_no      = '"+compNo+"'                        ");
        query.append("  AND  fcreclist_id = '"+fcRecListId+"'                   ");
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
      
    /**
     * 입고 History insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtFcRecDetailDTO
     * @return
     */
    public int insertRecHistory(String ptRecHistId, MaPtFcRecDetailDTO maPtFcRecDetailDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("INSERT INTO TAPTRECHIST (                                          ");
        query.append("       comp_no, ptrechist_id                                       ");
        query.append("       ,ptrec_mode                                                 ");
        query.append("       ,ptrec_type                                                 ");
        query.append("       ,reclist_id, dept_id, wcode_id, vendor_id                   ");
        query.append("       , rec_date, part_id, part_grade,rec_qty                     ");
        query.append("       , unit_price, tot_price, currency )                        ");
        query.append("SELECT x.comp_no,     '"+ptRecHistId+"',                          ");
        query.append("       '"+maPtFcRecDetailDTO.getFcRecMode()+"' ptrecMode,         ");
        query.append("       'FCREC'                               ptrecType,           ");
        query.append("       fcreclist_id,  dept_id,    x.wcode_id,    x.vendor_id,     ");
        query.append("       rec_date,      x.part_id,  x.part_grade,  rec_qty,         ");
        query.append("       unit_price,    tot_price,   x.currency                     ");
        query.append("FROM   TAPTFCRECLIST x                                            ");
        query.append("WHERE  x.comp_no      = '"+maPtFcRecDetailDTO.getCompNo()+"'      ");
        query.append("  AND  x.fcreclist_id = '"+maPtFcRecDetailDTO.getFcRecListId()+"' ");
        
        return this.getJdbcTemplate().update(query.toString());
    }
    public int updateGrDate(MaPtFcRecDetailDTO maPtFcRecDetailDTO, String compNo)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("UPDATE TAPARTS SET last_gr_date = CONVERT(VARCHAR, GETDATE(), 112),  ");
        query.append("       seller = ?	                        ");
        query.append("WHERE comp_no = ?                         ");
        query.append("AND   part_id = ?                         ");
        
        Object[] objects = new Object[] {
        		maPtFcRecDetailDTO.getVendorDesc(),
        		compNo,
        		maPtFcRecDetailDTO.getPartId()
    	};
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
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