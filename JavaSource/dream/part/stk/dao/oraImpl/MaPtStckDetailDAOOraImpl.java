package dream.part.stk.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.part.stk.dao.MaPtStckDetailDAO;
import dream.part.stk.dto.MaPtStckCommonDTO;
import dream.part.stk.dto.MaPtStckDetailDTO;

/**
 * 자재재고 - 상세 dao
 * 
 * @author ssong
 * @version $Id: MaPtStckDetailDAO.java,v 1.0 2015/12/02 08:25:47 ssong Exp $
 * @since 1.0
 * @spring.bean id="maPtStckDetailDAOTarget"
 * @spring.txbn id="maPtStckDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtStckDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPtStckDetailDAO
{
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtStckCommonDTO
     * @return
     */
    public MaPtStckDetailDTO findDetail(MaPtStckCommonDTO maPtStckCommonDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       x.comp_no                            compNo,       ");
        query.append("       x.wcode_id                           wcodeId,      ");
        query.append("       x.part_id                            partId,       ");
        query.append("      (SELECT wname FROM TAWAREHOUSE                      ");
        query.append("       WHERE  comp_no  = x.comp_no                        ");
        query.append("         AND  wcode_id = x.wcode_id)        wname,        "); //창고명 
        query.append("      (SELECT wh_type FROM TAWAREHOUSE                      ");
        query.append("       WHERE  comp_no  = x.comp_no                        ");
        query.append("         AND  wcode_id = x.wcode_id)        whType,       "); //창고분류
        query.append("      (SELECT part_no FROM TAPARTS                        ");                                  
        query.append("       WHERE  comp_no  = x.comp_no                        ");
        query.append("         AND  part_id  = x.part_id)         partNo,       ");
        query.append("      (SELECT description FROM TAPARTS                    ");                                  
        query.append("       WHERE  comp_no  = x.comp_no                        ");
        query.append("         AND  part_id  = x.part_id)         partDesc,     ");
        query.append("      (SELECT pt_size FROM TAPARTS                        ");                                  
        query.append("       WHERE  comp_no  = x.comp_no                        ");
        query.append("         AND  part_id  = x.part_id)         ptSize,       ");
        query.append("      (SELECT model FROM TAPARTS                          ");                                  
        query.append("       WHERE  comp_no  = x.comp_no                        ");
        query.append("         AND  part_id  = x.part_id)         model,        ");
        query.append("      (SELECT description||', '|| pt_size FROM TAPARTS    ");                                  
        query.append("       WHERE  comp_no  = x.comp_no                        ");
        query.append("         AND  part_id  = x.part_id)         partNameSize, "); //부품명/규격
        query.append("       SUM(DECODE(x.part_grade,'A',x.stock_qty,0)) aStockQty,  "); //a현재고
        query.append("       SUM(DECODE(x.part_grade,'B',x.stock_qty,0)) bStockQty,  "); //b현재고
        query.append("       MIN(y.safty_qty)                  minSaftyQty,     "); //안전재고 min
        query.append("       MIN(y.max_safty_qty)              maxSaftyQty,     "); //안전재고 max
        query.append("       MAX(DECODE(x.part_grade,'A',x.bin_no,'')) abinNo,  "); //a현재고
        query.append("       MAX(DECODE(x.part_grade,'B',x.bin_no,'')) bbinNo,   "); //b현재고
        query.append("       (SELECT z.is_serial_part FROM taparts z WHERE x.comp_no=z.comp_no and x.part_id=z.part_id) isSerial 		");
        query.append("FROM   TAPTSTOCK x,                                       ");
        query.append("       TAPTSAFTYSTOCK y                                   ");
        query.append("WHERE  x.comp_no    = y.comp_no(+)                        ");
        query.append("  AND  x.wcode_id   = y.wcode_id(+)                       ");
        query.append("  AND  x.part_id    = y.part_id(+)                        ");
        query.append("  AND  x.comp_no    = '"+maPtStckCommonDTO.getCompNo()+"'	");
        query.append("  AND  x.wcode_id   = '"+maPtStckCommonDTO.getWcodeId()+"'");
        query.append("  AND  x.part_id    = '"+maPtStckCommonDTO.getPartId()+"'	");
	    query.append("group by x.comp_no,x.wcode_id,x.part_id					");
        MaPtStckDetailDTO maPtStckDetailDTO = 
        		(MaPtStckDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaPtStckDetailDTO()));
        
        return maPtStckDetailDTO;
    }
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtStckDetailDTO
     * @return
     */
    public int insertPtStock(MaPtStckDetailDTO maPtStckDetailDTO,String stockQty,String partGrade)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAPTSTOCK (                                   ");
    	query.append("  comp_no,   wcode_id,   part_id,    part_grade,	        ");
    	query.append("	stock_qty, bin_no                                       ");
    	query.append(")	VALUES (                                                ");
    	query.append("  ?,         ?,          ?,          ?,                   ");
    	query.append("  ?,         DECODE(?,'A',?,'B',?,'')		                ");
    	query.append(")                                                         ");
    	
    	Object[] objects = new Object[] {
    			maPtStckDetailDTO.getCompNo(),
    			maPtStckDetailDTO.getWcodeId(),
    			maPtStckDetailDTO.getPartId(),
    			partGrade,
    			stockQty,
    			partGrade,
    			maPtStckDetailDTO.getAbinNo(),
    			maPtStckDetailDTO.getBbinNo()
    	};
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtStckDetailDTO
     * @return
     */
    public int insertPtSaftyStock(MaPtStckDetailDTO maPtStckDetailDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("INSERT INTO TAPTSAFTYSTOCK (                                             ");
        query.append("  comp_no,   wcode_id,   part_id,    safty_qty, max_safty_qty            ");
        query.append(")	VALUES (                                                               ");
        query.append("  ?,         ?,          ?,          ?,         ?                        ");
        query.append(")                                                                        ");
        
        Object[] objects = new Object[] {
                maPtStckDetailDTO.getCompNo(),
                maPtStckDetailDTO.getWcodeId(),
                maPtStckDetailDTO.getPartId(),
                maPtStckDetailDTO.getMinSaftyQty(),
                maPtStckDetailDTO.getMaxSaftyQty()
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
     * @param maPtStckDetailDTO
     * @return
     */
    public int updatePtStock(MaPtStckDetailDTO maPtStckDetailDTO, String partGrade, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("MERGE INTO TAPTSTOCK a          		");
    	query.append("USING ( SELECT    					");
    	query.append("              ?    compNo        		");
    	query.append("            , ?    wcode_id        	");
    	query.append("            , ?    part_id			");
    	query.append("            , ?    part_grade     	");
    	query.append("            , CASE ? WHEN 'A' THEN ? WHEN 'B' THEN ? ELSE '' END    bin_no		");
    	query.append("            , CASE ? WHEN 'A' THEN ? WHEN 'B' THEN ? ELSE '' END    stock_qty		");
    	query.append("        FROM DUAL     ) b   			");
    	query.append("ON (a.comp_no = b.compNo          	");
    	query.append("    AND a.wcode_id = b.wcode_id		");
    	query.append("    AND a.part_id = b.part_id			");
    	query.append("    AND a.part_grade = b.part_grade ) ");
    	query.append("WHEN MATCHED THEN                 	");
    	query.append("  UPDATE SET                      	");
    	query.append("      a.bin_no = b.bin_no      		");	//stock_qty는 업데이트 하지 않는다. 입고확정,취소시 프로시져 통해 변경됨. 
    	query.append("WHEN NOT MATCHED THEN             	");
    	query.append("  INSERT (  comp_no	,   wcode_id	,   part_id	  ,   part_grade  ,   stock_qty	 ,   bin_no	)    ");
    	query.append("  VALUES (b.compNo    , b.wcode_id	, b.part_id	  , b.part_grade  , b.stock_qty  , b.bin_no )    ");

    	Object[] objects = new Object[] {
    			loginUser.getCompNo()
    			, maPtStckDetailDTO.getWcodeId()
    			, maPtStckDetailDTO.getPartId()
    			, partGrade
    			, partGrade
                , maPtStckDetailDTO.getAbinNo()
                , maPtStckDetailDTO.getBbinNo()
                , partGrade
                , maPtStckDetailDTO.getAstockQty()
                , maPtStckDetailDTO.getBstockQty()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail stock
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtStckDetailDTO
     * @return
     */
    public int updatePtSaftyStock(MaPtStckDetailDTO maPtStckDetailDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TAPTSAFTYSTOCK SET        ");
        query.append("	     safty_qty     = ?,        ");
        query.append("	     max_safty_qty = ?         ");
        query.append("WHERE  comp_no       = ?	       ");
        query.append("  AND  wcode_id      = ?         ");
        query.append("  AND  part_id       = ?         ");
        
        Object[] objects = new Object[] {
                maPtStckDetailDTO.getMinSaftyQty(),
                maPtStckDetailDTO.getMaxSaftyQty(),
                maPtStckDetailDTO.getCompNo(),
                maPtStckDetailDTO.getWcodeId(),
                maPtStckDetailDTO.getPartId()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * valid check
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPtStckDetailDTO
     * @return
     */
    public String validPtStck(MaPtStckDetailDTO maPtStckDetailDTO)
    {
        
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT COUNT(*)                                           ");
        query.append("FROM   TAPTSTOCK x                                        ");
        query.append("WHERE  comp_no    = '"+maPtStckDetailDTO.getCompNo()+"'   ");
        query.append("  AND  wcode_id   = '"+maPtStckDetailDTO.getWcodeId()+"'  ");
        query.append("  AND  part_id    = '"+maPtStckDetailDTO.getPartId()+"'   ");
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    
    /**
     * find stockQty
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPtStckDetailDTO
     * @return
     */
    public String getStockQty(MaPtStckDetailDTO maPtStckDetailDTO, String partGrade)
    {
        
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT stock_qty                                          ");
        query.append("FROM   TAPTSTOCK x                                        ");
        query.append("WHERE  comp_no    = '"+maPtStckDetailDTO.getCompNo()+"'   ");
        query.append("  AND  wcode_id   = '"+maPtStckDetailDTO.getWcodeId()+"'  ");
        query.append("  AND  part_id    = '"+maPtStckDetailDTO.getPartId()+"'   ");
        query.append("  AND  part_grade    = '"+partGrade+"'                    ");
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    /**
     * find all stockQty
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPtStckDetailDTO
     * @return
     */
    public String getAllStockQty(MaPtStckDetailDTO maPtStckDetailDTO)
    {
        
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT sum(stock_qty)                                     ");
        query.append("FROM   TAPTSTOCK x                                        ");
        query.append("WHERE  comp_no    = '"+maPtStckDetailDTO.getCompNo()+"'   ");
        query.append("  AND  wcode_id   = '"+maPtStckDetailDTO.getWcodeId()+"'  ");
        query.append("  AND  part_id    = '"+maPtStckDetailDTO.getPartId()+"'   ");
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    
    /**
     * find stockGrade
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPtStckDetailDTO
     * @return
     */
    public String[] getStockGrade(MaPtStckDetailDTO maPtStckDetailDTO)
    {
        
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT part_grade                                         ");
        query.append("FROM   TAPTSTOCK x                                        ");
        query.append("WHERE  comp_no    = '"+maPtStckDetailDTO.getCompNo()+"'   ");
        query.append("  AND  wcode_id   = '"+maPtStckDetailDTO.getWcodeId()+"'  ");
        query.append("  AND  part_id    = '"+maPtStckDetailDTO.getPartId()+"'   ");
        query.append("GROUP BY part_grade										");
        
        return QueryBuffer.toStringSingleArray(getJdbcTemplate().queryForList(query.toString()));
    }
    
    /**
     * valid check
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPtStckDetailDTO
     * @return
     */
    public String validPtSaftyStck(MaPtStckDetailDTO maPtStckDetailDTO)
    {
        
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT COUNT(*)                  ");
        query.append("FROM   TAPTSAFTYSTOCK x          ");
        query.append("WHERE  comp_no    = '"+maPtStckDetailDTO.getCompNo()+"'   ");
        query.append("  AND  wcode_id   = '"+maPtStckDetailDTO.getWcodeId()+"'  ");
        query.append("  AND  part_id    = '"+maPtStckDetailDTO.getPartId()+"'   ");
        
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
            MaPtStckDetailDTO maPtStckDetailDTO, User loginUser)
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
                maPtStckDetailDTO.getWcodeId(),
                partGrade,
                recQty,
                recQty,
                maPtStckDetailDTO.getCompNo(),
                maPtStckDetailDTO.getPartId()
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
            MaPtStckDetailDTO maPtStckDetailDTO, User loginUser)
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
                maPtStckDetailDTO.getWcodeId(),
                partGrade,
                issQty,
                issQty,
                maPtStckDetailDTO.getCompNo(),
                maPtStckDetailDTO.getPartId()
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

    public List findReportPtDetail(MaPtStckDetailDTO maPtStckDetailDTO,User user) {

		QueryBuffer query = new QueryBuffer();
		String lang = user.getLangId();
		
        query.append("SELECT 																													");
        query.append("		(SELECT user_name																									");
        query.append("			FROM TAUSER																										");
        query.append("			WHERE comp_no = '"+user.getCompNo()+"'																			");
        query.append("			AND user_id = '"+user.getUserId()+"') empName,																	");
        query.append("		'"+user.getDeptDesc()+"' deptDesc,																					");
        query.append("		TO_CHAR(sysdate,'yyyy/mm') yyyymm,																					");
        query.append("		TO_CHAR(sysdate,'yyyy-mm-dd') yyyymmdd,																				");
        query.append("		TO_CHAR(sysdate,'/mm/yyyy') mmyyyy,																					");
        query.append("		TO_CHAR(sysdate,'yyyy-mm-dd HH24:mi') TODAY,																		");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='ptReqBuyList' AND key_type='LABEL') ptReqBuyList,	");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='number' AND key_type='LABEL') no,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='title' AND key_type='LABEL') title,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='manager' AND key_type='LABEL') manager,				");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='ptBuyReqTitle' AND key_type='LABEL') ptBuyReqTitle1,	");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='requestDate' AND key_type='LABEL') requestDate,		");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='partNo' AND key_type='LABEL') partNo,				");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='partNameSize' AND key_type='LABEL') partNameSize,	");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='model' AND key_type='LABEL') model,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='uom' AND key_type='LABEL') uom,						");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='reqQty' AND key_type='LABEL') reqQty,				");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='stockQty' AND key_type='LABEL') STOCKQTY,			");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='maxSaftyQty' AND key_type='LABEL') maxSaftyQty,		");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='minSaftyQty' AND key_type='LABEL') minSaftyQty,		");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='price' AND key_type='LABEL') price,					");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='remark' AND key_type='LABEL') remark,				");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='seller' AND key_type='LABEL') seller,				");
        query.append("		(SELECT key_name FROM TALANG WHERE lang='"+lang+"' AND key_no='seqNo' AND key_type='LABEL') seqNo					");
        query.append("FROM DUAL x																											");

		return getJdbcTemplate().queryForList(query.toString());
	}
    

    public List findReportPartList(MaPtStckDetailDTO maPtStckDetailDTO,User user) {

		QueryBuffer query = new QueryBuffer();

		query.append("SELECT											");
		query.append("		ROWNUM seqNum,								");
		query.append("		(SELECT a.part_no 							");
		query.append("		   FROM TAPARTS	a							");
		query.append("		WHERE a.comp_no = x.comp_no					");
		query.append("		AND a.part_id = x.part_id) partNum,			");
		
		query.append("		(SELECT a.description			 			");
		query.append("		   FROM TAPARTS	a							");
		query.append("		WHERE a.comp_no = x.comp_no					");
		query.append("		AND a.part_id = x.part_id) ptNameSize,		");
		
		query.append("		(SELECT a.model 							");
		query.append("		   FROM TAPARTS a							");
		query.append("		WHERE a.comp_no = x.comp_no					");
		query.append("		AND a.part_id = x.part_id) ptmodel,			");
		
		query.append("		x.stock_Qty currQty,						");
		query.append("		(SELECT safty_qty							");
		query.append("			FROM taptsaftystock a					");
		query.append("			WHERE a.comp_no = x.comp_no				");
		query.append("			AND a.part_id = x.part_id ) stockMinQty,");
		query.append("		(SELECT seller 								");
		query.append("		   FROM TAPARTS								");
		query.append("		WHERE comp_no = x.comp_no					");
		query.append("		AND part_id = x.part_id) stockSeller		");
        query.append("FROM TAPTSTOCK x									");
        query.append("WHERE 1=1											");
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getAndQuery("x.part_id", maPtStckDetailDTO.getPartId());
        query.append("AND x.part_grade = (SELECT value$ FROM TACONFIG WHERE 1=1	");
        query.getAndQuery("comp_no", user.getCompNo());
        query.getStringEqualQuery("name", "PART_GRADE");
        query.append(")");
		return getJdbcTemplate().queryForList(query.toString());
	}


}