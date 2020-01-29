package dream.tool.iss.rent.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
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
public class MaPttIssDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPttIssDetailDAO
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
        QueryBuffer query = new QueryBuffer();
        
        String compNo = user.getCompNo();
        
        query.append("SELECT");
        query.append("    x.ptisslist_id ptIssListId,");
        query.append("    x.ptiss_status ptIssStatus,");
        query.append("    SFACODE_TO_DESC(x.ptiss_status, 'PTISS_STATUS', 'SYS', x.comp_no,'"+user.getLangId()+"') ptissStatusDesc,");
        query.append("    x.issue_date issueDate,");
        query.append("    x.wcode_id wcodeId,");
        query.append("  (SELECT wname FROM TAWAREHOUSE                      ");
        query.append("       WHERE  comp_no  = x.comp_no                        ");
        query.append("         AND  wcode_id = x.wcode_id)        wname,");
        query.append("    x.part_id partId,");
        query.append("     y.part_no partNo,");
        query.append("     x.issue_dept issueDept,");
        query.append("     SFAIDTODESC(x.issue_dept, '', 'DEPT', x.comp_no)  issueDeptDesc,     ");
        query.append("     y.description||', '||y.pt_size                 partDesc,");
        query.append("     x.issue_qty issueQty,");
        query.append("        x.rec_by recBy,");
        query.append("        SFAIDTODESC(x.rec_by, '', 'EMP', x.comp_no) recByName ,");
        query.append("        x.rec_dept recDept,");
        query.append("         SFAIDTODESC(x.rec_dept, '', 'DEPT', x.comp_no)  recDeptDesc,");
        query.append("         x.remark remark    ");
        query.append("FROM TAPTISSLIST x, TAPARTS y");
        query.append("WHERE x.comp_no = y.comp_no(+)                            ");
        query.append("  AND x.part_id = y.part_id(+)         ");
        query.append("  AND  x.comp_no      = '"+compNo+"'	                    ");
        query.append("  AND  x.ptisslist_id = '"+ptIssListId+"'                 ");
    
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
    	QueryBuffer query = new QueryBuffer();

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
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
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
    	QueryBuffer query = new QueryBuffer();
    	
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
    	
    	return this.getJdbcTemplate().update(query.toString(), objects);
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
        
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT COUNT(*)                                           ");
        query.append("FROM   TAPTPRRECLIST x                                    ");
        query.append("WHERE  comp_no      = '"+maPttIssDetailDTO.getCompNo()+"'  ");
       // query.append("  AND  prreclist_no = '"+maPttIssDetailDTO.getPrRecListNo()+"'  ");
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
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
        
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT ptiss_status                                   ");
        query.append("FROM   TAPTISSLIST x                                    ");
        query.append("WHERE  comp_no      = '"+compNo+"'                        ");
        query.append("  AND  ptisslist_id = '"+ptIssListId+"'                   ");
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
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
        QueryBuffer query = new QueryBuffer();

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
        QueryBuffer query = new QueryBuffer();

        query.append("UPDATE TAPTISSLIST SET                        ");
        query.append("  ptiss_status              = ?               ");
        query.append("WHERE ptisslist_id          = ?               ");

        
        Object[] objects = new Object[] {
                maPttIssDetailDTO.getPtissStatus(),
                maPttIssDetailDTO.getPtIssListId()
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
        
    }
    
    public int insertPtIssHist(MaPttIssDetailDTO maPttIssDetailDTO, String ptisshistId, String ptissMode)
    {
        QueryBuffer query = new QueryBuffer();
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
        query.append("      ,NVL(x.wcode_id,0)                                          ");
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
        QueryBuffer query = new QueryBuffer();
        query.append("BEGIN                                                          ");
        query.append("SP_PT_OUTSTOCK(                                                ");
        query.append("                  '"+maPttIssDetailDTO.getCompNo()+"'          ");
        query.append("                 ,"+ptisshistId+"                              ");
        query.append("                 );                                            ");
        query.append("END;                                                           ");
    
        this.getJdbcTemplate().execute(query.toString());
    }
    
    public int insertPtRentStock(MaPttIssDetailDTO maPttIssDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();

        query.append("MERGE INTO TAPTRENT_STOCK a             ");
        query.append("USING(    SELECT                   ");                                                                 
        query.append("                  ? compNo,        ");                                                                            
        query.append("                  (SELECT c.wcode_id FROM TAWAREHOUSE c WHERE c.wcode_id = ? AND c.comp_no = ? ) wcodeId,  ");        
        query.append("                  ? rentQty,       "); 
        query.append("                  (SELECT c.part_id FROM TAPARTS c WHERE c.part_no = ? AND c.comp_no = ? ) partId,        ");
        query.append("                  ? partGrade,     "); 
        query.append("                  ? recDept,       ");  
        query.append("                  ? recBy          ");  
        query.append("          FROM DUAL) b             ");
        query.append("ON(   a.comp_no = b.compNo         ");
        query.append("  AND a.part_id = b.partId         ");
        query.append("  AND a.part_grade = b.partGrade   ");
        query.append("  AND a.rec_dept = b.recDept   	 ");
        query.append("  AND a.rec_by = b.recBy   	     ");
        query.append("  AND a.wcode_id = b.wcodeId    )  ");
        query.append("WHEN MATCHED THEN                  ");
        query.append("  UPDATE SET                       ");  
        query.append("              a.rent_qty = a.rent_qty+b.rentQty");
        query.append("WHEN NOT MATCHED THEN                                         ");
        query.append("  INSERT (a.comp_no,          a.wcode_id,        a.part_id,   ");
        query.append("          a.part_grade,       a.rec_dept,        a.rec_by,    ");
        query.append("          a.rent_qty,     	a.ptrentlist_id				    ");
        query.append("         )                                                    ");
        query.append("  VALUES (b.compNo,           b.wcodeId,         b.partId,    ");
        query.append("          b.partGrade,        b.recDept,         b.recBy,     ");
        query.append("          b.rentQty,  		SQAPTRENTLIST_ID.NEXTVAL        ");
        query.append("         )                                                    ");
        
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
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
    
    public int updatePtRentStock(MaPttIssDetailDTO maPttIssDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();

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
        
        return getJdbcTemplate().update(query.toString(), objects);
    }    
}