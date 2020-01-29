package dream.tool.stk.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.tool.stk.dao.MaPttStckDetailDAO;
import dream.tool.stk.dto.MaPttStckCommonDTO;
import dream.tool.stk.dto.MaPttStckDetailDTO;

/**
 * 자재재고 - 상세 dao
 * 
 * @author ssong
 * @version $Id: MaPttStckDetailDAO.java,v 1.0 2015/12/02 08:25:47 ssong Exp $
 * @since 1.0
 * @spring.bean id="maPttStckDetailDAOTarget"
 * @spring.txbn id="maPttStckDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPttStckDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPttStckDetailDAO
{
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttStckCommonDTO
     * @return
     */
    public MaPttStckDetailDTO findDetail(MaPttStckCommonDTO maPttStckCommonDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       x.comp_no                            compNo,       ");
        query.append("       x.wcode_id                           wcodeId,      ");
        query.append("       x.part_id                            partId,       ");
        query.append("      (SELECT wname FROM TAWAREHOUSE                      ");
        query.append("       WHERE  comp_no  = x.comp_no                        ");
        query.append("         AND  wcode_id = x.wcode_id)        wname,        "); //창고명 
        query.append("      (SELECT part_no FROM TAPARTS                        ");                                  
        query.append("       WHERE  comp_no  = x.comp_no                        ");
        query.append("         AND  part_id  = x.part_id)         partNo,       ");
        query.append("      (SELECT description||', '|| pt_size FROM TAPARTS    ");                                  
        query.append("       WHERE  comp_no  = x.comp_no                        ");
        query.append("         AND  part_id  = x.part_id)         partNameSize, "); //부품명/규격
        query.append("       SUM(DECODE(x.part_grade,'B',x.stock_qty,0)) bStockQty,  "); //b현재고
        query.append("       MIN(y.safty_qty)                  minSaftyQty,     "); //안전재고 min
        query.append("       MIN(y.max_safty_qty)              maxSaftyQty,      "); //안전재고 max
        query.append("       x.bin_no                             binNo         "); //보관위치 
        query.append("FROM   TAPTSTOCK x,                                       ");
        query.append("       TAPTSAFTYSTOCK y                                   ");
        query.append("WHERE  x.comp_no    = y.comp_no(+)                        ");
        query.append("  AND  x.wcode_id   = y.wcode_id(+)                       ");
        query.append("  AND  x.part_id    = y.part_id(+)                        ");
        query.append("  AND  x.comp_no    = '"+maPttStckCommonDTO.getCompNo()+"'	");
        query.append("  AND  x.wcode_id   = '"+maPttStckCommonDTO.getWcodeId()+"'");
        query.append("  AND  x.part_id    = '"+maPttStckCommonDTO.getPartId()+"'	");
	    query.append("group by x.comp_no,x.wcode_id,x.part_id,x.bin_no			");
        MaPttStckDetailDTO maPttStckDetailDTO = 
        		(MaPttStckDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaPttStckDetailDTO()));
        
        return maPttStckDetailDTO;
    }
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttStckDetailDTO
     * @return
     */
    public int insertPtStock(MaPttStckDetailDTO maPttStckDetailDTO,String stockQty,String partGrade)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAPTSTOCK (                                   ");
    	query.append("  comp_no,   wcode_id,   part_id,    part_grade,	        ");
    	query.append("	stock_qty, bin_no                                       ");
    	query.append(")	VALUES (                                                ");
    	query.append("  ?,         ?,          ?,          ?,                   ");
    	query.append("  ?,         ?							                ");
    	query.append(")                                                         ");
    	
    	Object[] objects = new Object[] {
    			maPttStckDetailDTO.getCompNo(),
    			maPttStckDetailDTO.getWcodeId(),
    			maPttStckDetailDTO.getPartId(),
    			partGrade,
    			stockQty,
    			maPttStckDetailDTO.getBinNo()
    	};
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttStckDetailDTO
     * @return
     */
    public int insertPtSaftyStock(MaPttStckDetailDTO maPttStckDetailDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("INSERT INTO TAPTSAFTYSTOCK (                                             ");
        query.append("  comp_no,   wcode_id,   part_id,    safty_qty, max_safty_qty            ");
        query.append(")	VALUES (                                                               ");
        query.append("  ?,         ?,          ?,          ?,         ?                        ");
        query.append(")                                                                        ");
        
        Object[] objects = new Object[] {
                maPttStckDetailDTO.getCompNo(),
                maPttStckDetailDTO.getWcodeId(),
                maPttStckDetailDTO.getPartId(),
                maPttStckDetailDTO.getMinSaftyQty(),
                maPttStckDetailDTO.getMaxSaftyQty()
        };
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail stock
     * stock_qty는 업데이트 하지 않는다. 입고확정,취소시 프로시져 통해 변경됨. 
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttStckDetailDTO
     * @return
     */
    public int updatePtStock(MaPttStckDetailDTO maPttStckDetailDTO, String partGrade)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TAPTSTOCK SET		       ");
    	query.append("	     bin_no        = ?         ");
    	query.append("WHERE  comp_no       = ?	       ");
    	query.append("  AND  wcode_id      = ?         ");
    	query.append("  AND  part_id       = ?         ");
    	query.append("  AND  part_grade    = ?         ");
    	
    	Object[] objects = new Object[] {
                maPttStckDetailDTO.getBinNo(),
                maPttStckDetailDTO.getCompNo(),
                maPttStckDetailDTO.getWcodeId(),
                maPttStckDetailDTO.getPartId(),
                partGrade
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail stock
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttStckDetailDTO
     * @return
     */
    public int updatePtSaftyStock(MaPttStckDetailDTO maPttStckDetailDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TAPTSAFTYSTOCK SET        ");
        query.append("	     safty_qty     = ?,        ");
        query.append("	     max_safty_qty = ?         ");
        query.append("WHERE  comp_no       = ?	       ");
        query.append("  AND  wcode_id      = ?         ");
        query.append("  AND  part_id       = ?         ");
        
        Object[] objects = new Object[] {
                maPttStckDetailDTO.getMinSaftyQty(),
                maPttStckDetailDTO.getMaxSaftyQty(),
                maPttStckDetailDTO.getCompNo(),
                maPttStckDetailDTO.getWcodeId(),
                maPttStckDetailDTO.getPartId()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * valid check
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPttStckDetailDTO
     * @return
     */
    public String validPtStck(MaPttStckDetailDTO maPttStckDetailDTO)
    {
        
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT COUNT(*)                                           ");
        query.append("FROM   TAPTSTOCK x                                        ");
        query.append("WHERE  comp_no    = '"+maPttStckDetailDTO.getCompNo()+"'   ");
        query.append("  AND  wcode_id   = '"+maPttStckDetailDTO.getWcodeId()+"'  ");
        query.append("  AND  part_id    = '"+maPttStckDetailDTO.getPartId()+"'   ");
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    
    /**
     * find stockQty
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPttStckDetailDTO
     * @return
     */
    public String getStockQty(MaPttStckDetailDTO maPttStckDetailDTO, String partGrade)
    {
        
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT stock_qty                                          ");
        query.append("FROM   TAPTSTOCK x                                        ");
        query.append("WHERE  comp_no    = '"+maPttStckDetailDTO.getCompNo()+"'   ");
        query.append("  AND  wcode_id   = '"+maPttStckDetailDTO.getWcodeId()+"'  ");
        query.append("  AND  part_id    = '"+maPttStckDetailDTO.getPartId()+"'   ");
        query.append("  AND  part_grade    = '"+partGrade+"'                    ");
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    /**
     * find all stockQty
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPttStckDetailDTO
     * @return
     */
    public String getAllStockQty(MaPttStckDetailDTO maPttStckDetailDTO)
    {
        
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT sum(stock_qty)                                     ");
        query.append("FROM   TAPTSTOCK x                                        ");
        query.append("WHERE  comp_no    = '"+maPttStckDetailDTO.getCompNo()+"'   ");
        query.append("  AND  wcode_id   = '"+maPttStckDetailDTO.getWcodeId()+"'  ");
        query.append("  AND  part_id    = '"+maPttStckDetailDTO.getPartId()+"'   ");
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    
    /**
     * find stockGrade
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPttStckDetailDTO
     * @return
     */
    public String[] getStockGrade(MaPttStckDetailDTO maPttStckDetailDTO)
    {
        
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT part_grade                                         ");
        query.append("FROM   TAPTSTOCK x                                        ");
        query.append("WHERE  comp_no    = '"+maPttStckDetailDTO.getCompNo()+"'   ");
        query.append("  AND  wcode_id   = '"+maPttStckDetailDTO.getWcodeId()+"'  ");
        query.append("  AND  part_id    = '"+maPttStckDetailDTO.getPartId()+"'   ");
        query.append("GROUP BY part_grade										");
        
        return QueryBuffer.toStringSingleArray(getJdbcTemplate().queryForList(query.toString()));
    }
    
    /**
     * valid check
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPttStckDetailDTO
     * @return
     */
    public String validPtSaftyStck(MaPttStckDetailDTO maPttStckDetailDTO)
    {
        
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT COUNT(*)                  ");
        query.append("FROM   TAPTSAFTYSTOCK x          ");
        query.append("WHERE  comp_no    = '"+maPttStckDetailDTO.getCompNo()+"'   ");
        query.append("  AND  wcode_id   = '"+maPttStckDetailDTO.getWcodeId()+"'  ");
        query.append("  AND  part_id    = '"+maPttStckDetailDTO.getPartId()+"'   ");
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()) );
    }
    
    /**
     * 입고 History insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param recQty
     * @param maPtRecDetailDTO
     * @return
     */
    public int insertPtRecHistory(String ptRecHistId, String recQty, String partGrade, 
            MaPttStckDetailDTO maPttStckDetailDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("INSERT INTO TAPTRECHIST (                                 ");
        query.append("       comp_no,        ptrechist_id,                      ");
        query.append("       ptrec_mode,     ptrec_type,            reclist_id, ");
        query.append("       dept_id,        wcode_id,              rec_date,   ");
        query.append("       part_id,        part_grade,            rec_qty,    ");
        query.append("       unit_price,     tot_price                          ");
        query.append(")                                                         ");
        query.append("SELECT comp_no,        ? ptrechistId,                     ");
        query.append("       'C' ptrecMode,  'STOCK' ptrecType,     null reclistId,                 ");
        query.append("       ? deptId,       ? wcodeId,             TO_CHAR(SYSDATE, 'YYYYMMDD'),   ");
        query.append("       x.part_id,      ? partGrade,           ? recQty,         ");
        query.append("       x.last_price,   NVL(?, 0)*NVL(x.last_price, 0) totPrice  ");
        query.append("FROM   TAPARTS x                                          ");
        query.append("WHERE  x.comp_no = ?                                      ");
        query.append("  AND  x.part_id = ?                                      ");


        Object[] objects = new Object[] {
                ptRecHistId,
                loginUser.getDeptId(),
                maPttStckDetailDTO.getWcodeId(),
                partGrade,
                recQty,
                recQty,
                maPttStckDetailDTO.getCompNo(),
                maPttStckDetailDTO.getPartId()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * 출고  History insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param ptIssHistId
     * @param maPtRecDetailDTO
     * @return
     */
    public int insertPtIssHistory(String ptIssHistId, String issQty, String partGrade,
            MaPttStckDetailDTO maPttStckDetailDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("INSERT INTO TAPTISSHIST (                                 ");
        query.append("       comp_no,        ptisshist_id,                      ");
        query.append("       ptiss_mode,     ptiss_type,            isslist_id, ");
        query.append("       dept_id,        wcode_id,              iss_date,   ");
        query.append("       part_id,        part_grade,            iss_qty,    ");
        query.append("       unit_price,     tot_price                          ");
        query.append(")                                                         ");
        query.append("SELECT comp_no,        ? ptisshistId,                     ");
        query.append("       'C' ptissMode,  'STOCK' ptissType,     null isslistId,                 ");
        query.append("       ? deptId,       ? wcodeId,             TO_CHAR(SYSDATE, 'YYYYMMDD'),   ");
        query.append("       x.part_id,      ? partGrade,           ? issQty,         ");
        query.append("       x.last_price,   NVL(?, 0)*NVL(x.last_price, 0) totPrice  ");
        query.append("FROM   TAPARTS x                                          ");
        query.append("WHERE  x.comp_no = ?                                      ");
        query.append("  AND  x.part_id = ?                                      ");


        Object[] objects = new Object[] {
                ptIssHistId,
                loginUser.getDeptId(),
                maPttStckDetailDTO.getWcodeId(),
                partGrade,
                issQty,
                issQty,
                maPttStckDetailDTO.getCompNo(),
                maPttStckDetailDTO.getPartId()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     *  SP_PT_INSTOCK 프로시져 호출 
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param ptRecHistId
     * @return
     * @throws Exception
     */
    public int executeSpPtInStock(String compNo, String ptRecHistId) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("begin                                                     ");
        query.append("SP_PT_INSTOCK('"+compNo+"', '"+ptRecHistId+"' );          ");
        query.append("end;                                                      ");
      
        this.getJdbcTemplate().execute(query.toString());
        
        return 0;
    }
    
    /**
     *  SP_PT_INSTOCK 프로시져 호출 
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param ptRecHistId
     * @return
     * @throws Exception
     */
    public int executeSpPtOutStock(String compNo, String ptIssHistId) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("begin                                                     ");
        query.append("SP_PT_OUTSTOCK('"+compNo+"', '"+ptIssHistId+"' );         ");
        query.append("end;                                                      ");
      
        this.getJdbcTemplate().execute(query.toString());
        
        return 0;
    }



}