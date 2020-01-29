package dream.tool.iss.rent.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.tool.iss.rent.dao.MaPttIssDetailDAO;
import dream.tool.iss.rent.dto.MaPttIssDetailDTO;

/**
 * 구매입고 - 상세 dao
 * 
 * @author ssong
 * @version $Id: $
 * @since 1.0
 * @spring.bean id="maPttIssDetailDAOTarget"
 * @spring.txbn id="maPttIssDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPttIssDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPttIssDetailDAO
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
    public MaPttIssDetailDTO findDetail(User user, String ptIssListId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String compNo = user.getCompNo();
        
        query.append("SELECT ");
        query.append("    x.ptisslist_id 											ptIssListId,	");
        query.append("    x.ptiss_status 											ptIssStatus,	");
        query.append("    dbo.SFACODE_TO_DESC(x.ptiss_status, 'PTISS_STATUS', 'SYS', x.comp_no,'"+user.getLangId()+"') ptissStatusDesc,");
        query.append("    x.issue_date 												issueDate,		");
        query.append("    x.wcode_id 												wcodeId,		");
        query.append("   (SELECT wname FROM TAWAREHOUSE                      						");
        query.append("    WHERE  comp_no  = x.comp_no                        						");
        query.append("      AND  wcode_id = x.wcode_id)        						wname,			");
        query.append("    x.part_id 												partId,			");
        query.append("    y.part_no 												partNo,			");
        query.append("    x.issue_dept 												issueDept,		");
        query.append("    dbo.SFAIDTODESC(x.issue_dept, '', 'DEPT', x.comp_no)		issueDeptDesc,  ");
        query.append("    CONCAT(y.description,', ',y.pt_size)						partDesc,		");
        query.append("    x.issue_qty 												issueQty,		");
        query.append("    x.rec_by 													recBy,			");
        query.append("    dbo.SFAIDTODESC(x.rec_by, '', 'EMP', x.comp_no)			recByName,		");
        query.append("    x.rec_dept 												recDept,		");
        query.append("    dbo.SFAIDTODESC(x.rec_dept, '', 'DEPT', x.comp_no)  		recDeptDesc,	");
        query.append("    x.remark 													remark    		");
        query.append("FROM TAPTISSLIST x LEFT JOIN TAPARTS y										");
        query.append("   ON x.comp_no = y.comp_no                            						");
        query.append("  AND x.part_id = y.part_id          											");
        query.append("  WHERE 1=1																	");
        query.append("  AND  x.comp_no      = '"+compNo+"'	                    					");
        query.append("  AND  x.ptisslist_id = '"+ptIssListId+"'                 					");
    
        MaPttIssDetailDTO maPttIssDetailDTO = 
        		(MaPttIssDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaPttIssDetailDTO()));
        
        return maPttIssDetailDTO;
    }
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttIssDetailDTO
     * @return
     */
    public int insertDetail(MaPttIssDetailDTO maPttIssDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAPTISSLIST (                               	");
    	query.append("  comp_no,    ptisslist_id,  wcode_id,   part_grade,		");
    	query.append("  issue_date, part_id,       issue_qty,  ptiss_status,    ");
    	query.append("  issue_dept, issue_by,      rec_by,      rec_dept,		");
    	query.append("  remark													");
    	query.append(")	VALUES (                                                ");
    	query.append("  ?,         ?,             ?,            ?,              ");
    	query.append("  ?,         ?,             ?,            ?,              ");
    	query.append("  ?,         ?,             ?,            ?,              ");
    	query.append("  ?        			                					");
    	query.append(")                                                         ");
    	
    	Object[] objects = new Object[] {
    			maPttIssDetailDTO.getCompNo(),
    			maPttIssDetailDTO.getPtIssListId(),
    			maPttIssDetailDTO.getWcodeId(),
    			"B",
    			maPttIssDetailDTO.getIssueDate(),
    			maPttIssDetailDTO.getPartId(),
    			maPttIssDetailDTO.getIssueQty(),
    			maPttIssDetailDTO.getPtissStatus(),
    			maPttIssDetailDTO.getIssueDept(),
    			maPttIssDetailDTO.getEnterBy(),
    			maPttIssDetailDTO.getRecBy(),
    			maPttIssDetailDTO.getRecDept(),
    			maPttIssDetailDTO.getRemark()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail stock
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttIssDetailDTO
     * @return
     */
    public int updateDetail(MaPttIssDetailDTO maPttIssDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TAPTISSLIST SET		      ");
    	query.append("	     wcode_id           = ?,      ");
    	query.append("	     issue_date   		= ?,      ");
    	query.append("	     part_id   			= ?,      ");
    	query.append("	     issue_qty   		= ?,      ");
    	query.append("	     issue_dept   		= ?,      ");
    	query.append("	     rec_by   			= ?,      ");
    	query.append("	     rec_dept   		= ?,      ");	
    	query.append("	     ptiss_status  		= ?,      ");	
        query.append("       remark             = ?       ");
    	query.append("WHERE  comp_no            = ?	      ");
    	query.append("  AND  ptisslist_id       = ?       ");
    	
    	Object[] objects = new Object[] {
    			maPttIssDetailDTO.getWcodeId(),
    			maPttIssDetailDTO.getIssueDate(),
    			maPttIssDetailDTO.getPartId(),
    			maPttIssDetailDTO.getIssueQty(),
    			maPttIssDetailDTO.getIssueDept(),
    			maPttIssDetailDTO.getRecBy(),
    			maPttIssDetailDTO.getRecDept(),    	
    			maPttIssDetailDTO.getPtissStatus(),
                maPttIssDetailDTO.getRemark(),
                maPttIssDetailDTO.getCompNo(),
                maPttIssDetailDTO.getPtIssListId()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    

       
    /**
     * valid check
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPttIssDetailDTO
     * @return
     */
    public String validPrRecListNo(MaPttIssDetailDTO maPttIssDetailDTO)
    {
        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT COUNT(*)                                           ");
        query.append("FROM   TAPTPRRECLIST x                                    ");
        query.append("WHERE  comp_no      = '"+maPttIssDetailDTO.getCompNo()+"'  ");
       // query.append("  AND  prreclist_no = '"+maPttIssDetailDTO.getPrRecListNo()+"'  ");
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    
    /**
     * 상태 조회
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPttIssDetailDTO
     * @return
     */
    public String findPtIssListStatus(String compNo, String ptIssListId)
    {
        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT ptiss_status                                   ");
        query.append("FROM   TAPTISSLIST x                                    ");
        query.append("WHERE  comp_no      = '"+compNo+"'                        ");
        query.append("  AND  ptisslist_id = '"+ptIssListId+"'                   ");
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
      
    /**
     * 입고 History insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttIssDetailDTO
     * @return
     */
    public int insertRecHistory(String ptRecHistId, MaPttIssDetailDTO maPttIssDetailDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("INSERT INTO TAPTRECHIST                                   ");
        query.append("SELECT x.comp_no,     '"+ptRecHistId+"',                  ");
        //query.append("       '"+maPttIssDetailDTO.getPtRecMode()+"' ptrecMode,   ");
        query.append("       'PRREC'                               ptrecType,   ");
        query.append("       prreclist_id,  dept_id,    x.wcode_id,    vendor_id,");
        query.append("       rec_date,      x.part_id,  '' partGrade,  rec_qty,  ");
        query.append("       unit_price,    tot_price                           ");
        query.append("FROM   TAPTPRRECLIST x                                    ");
        query.append("WHERE  x.comp_no      = '"+maPttIssDetailDTO.getCompNo()+"'      ");
        //query.append("  AND  x.prreclist_id = '"+maPttIssDetailDTO.getPrRecListId()+"' ");
        
        return this.getJdbcTemplate().update(query.toString());
    }

    
    public int updatePtIssList(MaPttIssDetailDTO maPttIssDetailDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("UPDATE TAPTISSLIST SET                        ");
        query.append("  ptiss_status              = ?               ");
        query.append("WHERE ptisslist_id          = ?               ");

        
        Object[] objects = new Object[] {
                maPttIssDetailDTO.getPtissStatus(),
                maPttIssDetailDTO.getPtIssListId()
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
        
    }
    
    public int insertPtIssHist(MaPttIssDetailDTO maPttIssDetailDTO, String ptisshistId, String ptissMode)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;
        
        query.append("INSERT INTO  TAPTISSHIST                                          ");
        query.append("(comp_no,             ptisshist_id,       ptiss_mode,             ");
        query.append(" ptiss_type,          isslist_id,         dept_id,                ");
        query.append(" wcode_id,            iss_date,           part_id,                ");
        query.append(" iss_qty,             part_grade) ");
        query.append("SELECT x.comp_no                                                  ");
        query.append("      ,?                                                          ");
        query.append("      ,?                                                          ");
        query.append("      ,'COST'                                                     ");
        query.append("      ,x.ptisslist_id                                   			");
        query.append("      ,x.issue_dept                     							");
        query.append("      ,ISNULL(x.wcode_id,0)                                          ");
        query.append("      ,x.issue_date                              					");
        query.append("      ,x.part_id                                                  ");
        query.append("      ,x.issue_qty                                          		");
        query.append("      ,part_grade                                                 ");
        query.append("FROM TAPTISSLIST x                                                ");
        query.append("WHERE comp_no         = ?                                         ");
        query.append("  AND ptisslist_id    = ?                                       	");
        Object[] objects = new Object[] {
                ptisshistId,
                ptissMode,
                maPttIssDetailDTO.getCompNo(),
                maPttIssDetailDTO.getPtIssListId()
              
        };
        
        rtnValue = getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
    
    public void execSP_PT_OUTSTOCK(MaPttIssDetailDTO maPttIssDetailDTO, String ptisshistId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        query.append("EXEC dbo.SP_PT_OUTSTOCK                                        ");
        query.append("                  '"+maPttIssDetailDTO.getCompNo()+"'          ");
        query.append("                 ,"+ptisshistId+"                              ");
        query.append("                 ;                                             ");
    
        this.getJdbcTemplate().execute(query.toString());
    }
    
    public int insertPtRentStock(MaPttIssDetailDTO maPttIssDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("DECLARE @t1 TABLE( 																	");
    	query.append("	compNo 		NVARCHAR(1000), 														");
    	query.append("	wcodeId 	NVARCHAR(1000), 														");
    	query.append("	rentQty 	NVARCHAR(1000), 														");
    	query.append("	partId 		NVARCHAR(1000), 														");
    	query.append("	partGrade 	NVARCHAR(1000), 														");
    	query.append("	recDept 	NVARCHAR(1000), 														");
    	query.append("	recBy 		NVARCHAR(1000) 															");
    	query.append(") 																					");
    	query.append(" 																						");
    	query.append("INSERT INTO @t1  																		");
    	query.append("SELECT  																				");
    	query.append("      ?, 																				");
    	query.append("      (SELECT c.wcode_id FROM TAWAREHOUSE c WHERE c.wcode_id = ? AND c.comp_no = ?),	");
    	query.append("      ?, 																				");
    	query.append("      (SELECT c.part_id FROM TAPARTS c WHERE c.part_no = ? AND c.comp_no = ?), 		");
    	query.append("      ?, 																				");
    	query.append("      ?, 																				");
    	query.append("      ? 																				");
    	query.append(" 																						");
    	query.append("IF EXISTS( 																			");
    	query.append("SELECT 1 																				");
    	query.append("  FROM TAPTRENT_STOCK a JOIN @t1 b 													");
    	query.append("    ON a.comp_no = b.compNo          													");
    	query.append("   AND a.part_id = b.partId          													");
    	query.append("   AND a.part_grade = b.partGrade    													");
    	query.append("   AND a.rec_dept = b.recDept         												");
    	query.append("   AND a.rec_by = b.recBy             												");
    	query.append("   AND a.wcode_id = b.wcodeId  														");
    	query.append(") 																					");
    	query.append("    BEGIN 																			");
    	query.append("        UPDATE TAPTRENT_STOCK SET                        								");
    	query.append("              rent_qty = a.rent_qty+b.rentQty 										");
    	query.append("          FROM TAPTRENT_STOCK a JOIN @t1 b 											");
    	query.append("            ON a.comp_no = b.compNo          											");
    	query.append("           AND a.part_id = b.partId          											");
    	query.append("           AND a.part_grade = b.partGrade    											");
    	query.append("           AND a.rec_dept = b.recDept         										");
    	query.append("           AND a.rec_by = b.recBy             										");
    	query.append("           AND a.wcode_id = b.wcodeId  												");
    	query.append("    END 																				");
    	query.append("ELSE 																					");
    	query.append("    BEGIN 																			");
    	query.append("        INSERT INTO TAPTRENT_STOCK (comp_no,          wcode_id,        part_id,    	");
    	query.append("                part_grade,       rec_dept,        rec_by,     						");
    	query.append("                rent_qty,         ptrentlist_id                     					");
    	query.append("                )                                                         			");
    	query.append("        SELECT b.compNo,           b.wcodeId,         b.partId,     					");
    	query.append("               b.partGrade,        b.recDept,         b.recBy,      					");
    	query.append("               b.rentQty,          NEXT VALUE FOR SQAPTRENTLIST_ID         			");
    	query.append("          FROM @t1 b 																	");
    	query.append("    END 																				");
    	

//        query.append("MERGE INTO TAPTRENT_STOCK a             ");
//        query.append("USING(    SELECT                   ");                                                                 
//        query.append("                  ? compNo,        ");                                                                            
//        query.append("                  (SELECT c.wcode_id FROM TAWAREHOUSE c WHERE c.wcode_id = ? AND c.comp_no = ? ) wcodeId,  ");        
//        query.append("                  ? rentQty,       "); 
//        query.append("                  (SELECT c.part_id FROM TAPARTS c WHERE c.part_no = ? AND c.comp_no = ? ) partId,        ");
//        query.append("                  ? partGrade,     "); 
//        query.append("                  ? recDept,       ");  
//        query.append("                  ? recBy          ");  
//        query.append("      ) b             ");
//        query.append("ON(   a.comp_no = b.compNo         ");
//        query.append("  AND a.part_id = b.partId         ");
//        query.append("  AND a.part_grade = b.partGrade   ");
//        query.append("  AND a.rec_dept = b.recDept   	 ");
//        query.append("  AND a.rec_by = b.recBy   	     ");
//        query.append("  AND a.wcode_id = b.wcodeId    )  ");
//        query.append("WHEN MATCHED THEN                  ");
//        query.append("  UPDATE SET                       ");  
//        query.append("              a.rent_qty = a.rent_qty+b.rentQty");
//        query.append("WHEN NOT MATCHED THEN                                         ");
//        query.append("  INSERT (a.comp_no,          a.wcode_id,        a.part_id,   ");
//        query.append("          a.part_grade,       a.rec_dept,        a.rec_by,    ");
//        query.append("          a.rent_qty,     	a.ptrentlist_id				    ");
//        query.append("         )                                                    ");
//        query.append("  VALUES (b.compNo,           b.wcodeId,         b.partId,    ");
//        query.append("          b.partGrade,        b.recDept,         b.recBy,     ");
//        query.append("          b.rentQty,  		NEXT VALUE FOR SQAPTRENTLIST_ID        ");
//        query.append("         )                                                    ");
        
        Object[] objects = new Object[] {
                
        		maPttIssDetailDTO.getCompNo(),
        		maPttIssDetailDTO.getWcodeId(),
        		maPttIssDetailDTO.getCompNo(),
        		maPttIssDetailDTO.getIssueQty(),
        		maPttIssDetailDTO.getPartNo(),
        		maPttIssDetailDTO.getCompNo(),
        		"B",
        		maPttIssDetailDTO.getRecDept(),
        		maPttIssDetailDTO.getRecBy()
                
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    public int updatePtRentStock(MaPttIssDetailDTO maPttIssDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAPTRENT_STOCK");
    	query.append("   SET rent_qty = rent_qty - ?");
    	query.append("WHERE comp_no	= ?");
    	query.append("  AND wcode_id= ?");
    	query.append("  AND part_id = ?");
    	query.append("  AND rec_dept= ?");
    	query.append("  AND rec_by=   ?");
        
        Object[] objects = new Object[] {                
        		maPttIssDetailDTO.getIssueQty(),
        		maPttIssDetailDTO.getCompNo(),
        		maPttIssDetailDTO.getWcodeId(),
        		maPttIssDetailDTO.getPartId(),
        		maPttIssDetailDTO.getRecDept(),
        		maPttIssDetailDTO.getRecBy()                
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }    
}