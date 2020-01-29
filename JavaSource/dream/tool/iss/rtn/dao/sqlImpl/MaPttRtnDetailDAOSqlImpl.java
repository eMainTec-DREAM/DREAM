package dream.tool.iss.rtn.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.tool.iss.rtn.dao.MaPttRtnDetailDAO;
import dream.tool.iss.rtn.dto.MaPttRtnDetailDTO;

/**
 * 공기구반납 - 상세 dao
 * 
 * @author ssong
 * @version $Id: $
 * @since 1.0
 * @spring.bean id="maPttRtnDetailDAOTarget"
 * @spring.txbn id="maPttRtnDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPttRtnDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPttRtnDetailDAO
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
    public MaPttRtnDetailDTO findDetail(User user, String ptRtnListId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String compNo = user.getCompNo();
        
        query.append("SELECT 																	");
        query.append("    x.ptRtnlist_id										ptRtnListId,	");
        query.append("    x.ptrtn_status										ptRtnStatus,	");
        query.append("    dbo.SFACODE_TO_DESC(x.ptrtn_status, 'PTRTN_STATUS', 'SYS', x.comp_no,'"+user.getLangId()+"') ptRtnStatusDesc,");
        query.append("    x.rtn_date											rtnDate,		");
        query.append("    x.wcode_id											wcodeId,		");
        query.append("   (SELECT wname FROM TAWAREHOUSE                      					");
        query.append("    WHERE  comp_no  = x.comp_no                        					");
        query.append("      AND  wcode_id = x.wcode_id)							wname,			");
        query.append("    x.part_id												partId,			");
        query.append("    y.part_no												partNo,			");
        query.append("    x.exe_dept											rtnDept,		");
        query.append("    dbo.SFAIDTODESC(x.exe_dept, '', 'DEPT', x.comp_no)	rtnDeptDesc,    ");
        query.append("    CONCAT(y.description,', ',y.pt_size)					partDesc,		");
        query.append("    x.rtn_qty												rtnQty,			");
        query.append("    x.rec_by												recBy,			");
        query.append("    dbo.SFAIDTODESC(x.rec_by, '', 'EMP', x.comp_no)		recByName,		");
        query.append("    x.rec_dept											recDept,		");
        query.append("    dbo.SFAIDTODESC(x.rec_dept, '', 'DEPT', x.comp_no)	recDeptDesc,	");
        query.append("    x.remark												remark    		");
        query.append("FROM TAPTRTNLIST x LEFT OUTER JOIN TAPARTS y								");
        query.append("	 ON x.comp_no = y.comp_no                            					");
        query.append("  AND x.part_id = y.part_id         										");
        query.append("WHERE 1=1						                            				");
        query.append("  AND  x.comp_no      = '"+compNo+"'	                    				");
        query.append("  AND  x.ptRtnlist_id = '"+ptRtnListId+"'                 				");
    
        MaPttRtnDetailDTO maPttRtnDetailDTO = 
        		(MaPttRtnDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaPttRtnDetailDTO()));
        
        return maPttRtnDetailDTO;
    }
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRtnDetailDTO
     * @return
     */
    public int insertDetail(MaPttRtnDetailDTO maPttRtnDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAPTRTNLIST (                               	");
    	query.append("  comp_no,    ptRtnlist_id,  wcode_id,   part_grade,		");
    	query.append("  rtn_date, part_id,       rtn_qty,  ptrtn_status,    ");
    	query.append("  exe_dept, exe_by,      rec_by,      rec_dept,		");
    	query.append("  remark													");
    	query.append(")	VALUES (                                                ");
    	query.append("  ?,         ?,             ?,            ?,              ");
    	query.append("  ?,         ?,             ?,            ?,              ");
    	query.append("  ?,         ?,             ?,            ?,              ");
    	query.append("  ?        			                					");
    	query.append(")                                                         ");
    	
    	Object[] objects = new Object[] {
    			maPttRtnDetailDTO.getCompNo(),
    			maPttRtnDetailDTO.getPtRtnListId(),
    			maPttRtnDetailDTO.getWcodeId(),
    			"B",
    			maPttRtnDetailDTO.getRtnDate(),
    			maPttRtnDetailDTO.getPartId(),
    			maPttRtnDetailDTO.getRtnQty(),
    			maPttRtnDetailDTO.getPtRtnStatus(),
    			maPttRtnDetailDTO.getRtnDept(),
    			maPttRtnDetailDTO.getEnterBy(),
    			maPttRtnDetailDTO.getRecBy(),
    			maPttRtnDetailDTO.getRecDept(),
    			maPttRtnDetailDTO.getRemark()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail stock
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRtnDetailDTO
     * @return
     */
    public int updateDetail(MaPttRtnDetailDTO maPttRtnDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TAPTRTNLIST SET		      ");
    	query.append("	     wcode_id           = ?,      ");
    	query.append("	     rtn_date   		= ?,      ");
    	query.append("	     part_id   			= ?,      ");
    	query.append("	     rtn_qty   		    = ?,      ");
    	query.append("	     exe_dept   		= ?,      ");
    	query.append("	     rec_by   			= ?,      ");
    	query.append("	     rec_dept   		= ?,      ");	
    	query.append("	     ptrtn_status  		= ?,      ");	
        query.append("       remark             = ?       ");
    	query.append("WHERE  comp_no            = ?	      ");
    	query.append("  AND  ptRtnlist_id       = ?       ");
    	
    	Object[] objects = new Object[] {
    			maPttRtnDetailDTO.getWcodeId(),
    			maPttRtnDetailDTO.getRtnDate(),
    			maPttRtnDetailDTO.getPartId(),
    			maPttRtnDetailDTO.getRtnQty(),
    			maPttRtnDetailDTO.getRtnDept(),
    			maPttRtnDetailDTO.getRecBy(),
    			maPttRtnDetailDTO.getRecDept(),    	
    			maPttRtnDetailDTO.getPtRtnStatus(),
                maPttRtnDetailDTO.getRemark(),
                maPttRtnDetailDTO.getCompNo(),
                maPttRtnDetailDTO.getPtRtnListId()
    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    

       
    /**
     * valid check
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPttRtnDetailDTO
     * @return
     */
    public String validPrRecListNo(MaPttRtnDetailDTO maPttRtnDetailDTO)
    {
        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT COUNT(*)                                           ");
        query.append("FROM   TAPTPRRECLIST x                                    ");
        query.append("WHERE  comp_no      = '"+maPttRtnDetailDTO.getCompNo()+"'  ");
       // query.append("  AND  prreclist_no = '"+maPttRtnDetailDTO.getPrRecListNo()+"'  ");
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    
    /**
     * 상태 조회
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPttRtnDetailDTO
     * @return
     */
    public String findPtRtnListStatus(String compNo, String ptRtnListId)
    {
        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT ptrtn_status                                   ");
        query.append("FROM   TAPTRTNLIST x                                    ");
        query.append("WHERE  comp_no      = '"+compNo+"'                        ");
        query.append("  AND  ptRtnlist_id = '"+ptRtnListId+"'                   ");
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
      
    /**
     * 반납 History insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRtnDetailDTO
     * @return
     */
    public int insertRecHistory(String ptRecHistId, MaPttRtnDetailDTO maPttRtnDetailDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("INSERT INTO TAPTRECHIST                                   ");
        query.append("SELECT x.comp_no,     '"+ptRecHistId+"',                  ");
        //query.append("       '"+maPttRtnDetailDTO.getPtRecMode()+"' ptrecMode,   ");
        query.append("       'PRREC'                               ptrecType,   ");
        query.append("       prreclist_id,  dept_id,    x.wcode_id,    vendor_id,");
        query.append("       rec_date,      x.part_id,  '' partGrade,  rec_qty,  ");
        query.append("       unit_price,    tot_price                           ");
        query.append("FROM   TAPTPRRECLIST x                                    ");
        query.append("WHERE  x.comp_no      = '"+maPttRtnDetailDTO.getCompNo()+"'      ");
        //query.append("  AND  x.prreclist_id = '"+maPttRtnDetailDTO.getPrRecListId()+"' ");
        
        return this.getJdbcTemplate().update(query.toString());
    }

    
    public int updatePtRtnList(MaPttRtnDetailDTO maPttRtnDetailDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("UPDATE TAPTRTNLIST SET                        ");
        query.append("  ptrtn_status              = ?               ");
        query.append("WHERE ptRtnlist_id          = ?               ");

        
        Object[] objects = new Object[] {
                maPttRtnDetailDTO.getPtRtnStatus(),
                maPttRtnDetailDTO.getPtRtnListId()
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
        
    }
    
    public int insertPtIssHist(MaPttRtnDetailDTO maPttRtnDetailDTO, String ptisshistId, String ptissMode)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;
        
        query.append("INSERT INTO  TAPTRTNHIST                                          ");
        query.append("(comp_no,             ptisshist_id,       ptiss_mode,             ");
        query.append(" ptiss_type,          isslist_id,         dept_id,                ");
        query.append(" wcode_id,            iss_date,           part_id,                ");
        query.append(" iss_qty,             part_grade) ");
        query.append("SELECT x.comp_no                                                  ");
        query.append("      ,?                                                          ");
        query.append("      ,?                                                          ");
        query.append("      ,'COST'                                                     ");
        query.append("      ,x.ptRtnlist_id                                   			");
        query.append("      ,x.exe_dept                     							");
        query.append("      ,ISNULL(x.wcode_id,0)                                          ");
        query.append("      ,x.rtn_date                              					");
        query.append("      ,x.part_id                                                  ");
        query.append("      ,x.rtn_qty                                          		");
        query.append("      ,part_grade                                                 ");
        query.append("FROM TAPTRTNLIST x                                                ");
        query.append("WHERE comp_no         = ?                                         ");
        query.append("  AND ptRtnlist_id    = ?                                       	");
        Object[] objects = new Object[] {
                ptisshistId,
                ptissMode,
                maPttRtnDetailDTO.getCompNo(),
                maPttRtnDetailDTO.getPtRtnListId()
              
        };
        
        rtnValue = getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
    
    public void updatePtStock(MaPttRtnDetailDTO maPttRtnDetailDTO)
    {
    	System.out.println("업데이트 PTSTOCK");
    	System.out.println(maPttRtnDetailDTO.toString());
        QuerySqlBuffer query = new QuerySqlBuffer();
        query.append("UPDATE TAPTSTOCK		");
        query.append("SET    stock_qty= stock_qty+ ?");
        query.append("WHERE  comp_no  = ?  	");
        query.append("  AND  wcode_id = ?	");
        query.append("  AND  part_id  = ?	");

        Object[] objects = new Object[] {
        		maPttRtnDetailDTO.getRtnQty(),
        		maPttRtnDetailDTO.getCompNo(),
        		maPttRtnDetailDTO.getWcodeId(),
        		maPttRtnDetailDTO.getPartId()
              
        };
      
        getJdbcTemplate().update(query.toString(), objects);
    }
    
    public void cancelPtStock(MaPttRtnDetailDTO maPttRtnDetailDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        query.append("UPDATE TAPTSTOCK		");
        query.append("SET    stock_qty= stock_qty- ?");
        query.append("WHERE  COMP_no  = ?  	");
        query.append("  AND  wcode_id = ?	");
        query.append("  AND  part_id  = ?	");

        Object[] objects = new Object[] {
        		maPttRtnDetailDTO.getRtnQty(),
        		maPttRtnDetailDTO.getCompNo(),
        		maPttRtnDetailDTO.getWcodeId(),
        		maPttRtnDetailDTO.getPartId()
              
        };
      
        getJdbcTemplate().update(query.toString(), objects);
    }
    public int insertPtRentStock(MaPttRtnDetailDTO maPttRtnDetailDTO)
    {
    	System.out.println("insert PTRENTSTOCK");
    	System.out.println(maPttRtnDetailDTO.toString());
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("MERGE INTO TAPTRENT_STOCK a        ");
        query.append("USING(    SELECT                   ");                                                                 
        query.append("                  ? compNo,        ");                                                                            
        query.append("                  (SELECT c.wcode_id FROM TAWAREHOUSE c WHERE c.wcode_id = ? AND c.comp_no = ? ) wcodeId,  ");        
        query.append("                  ? rentQty,       "); 
        query.append("                  (SELECT c.part_id FROM TAPARTS c WHERE c.part_no = ? AND c.comp_no = ? ) partId,        ");
        query.append("                  ? partGrade,     "); 
        query.append("                  ? recDept,       ");  
        query.append("                  ? recBy          ");  
        query.append("      ) b             			 ");
        query.append("ON(   a.comp_no = b.compNo         ");
        query.append("  AND a.part_id = b.partId         ");
        query.append("  AND a.part_grade = b.partGrade   ");
        query.append("  AND a.rec_dept = b.recDept   	 ");
        query.append("  AND a.rec_by = b.recBy   	     ");
        query.append("  AND a.wcode_id = b.wcodeId    )  ");
        query.append("WHEN MATCHED THEN                  ");
        query.append("  UPDATE SET                       ");  
        query.append("              a.rent_qty = a.rent_qty-b.rentQty				");
        query.append("WHEN NOT MATCHED THEN                                         ");
        query.append("  INSERT (comp_no,          wcode_id,        part_id,   		");
        query.append("          part_grade,       rec_dept,        rec_by,    		");
        query.append("          rent_qty     							        	");
        query.append("         )                                                    ");
        query.append("  VALUES (b.compNo,           b.wcodeId,         b.partId,    ");
        query.append("          b.partGrade,        b.recDept,         b.recBy,     ");
        query.append("          -CAST(b.rentQty AS numeric)  						");
        query.append("         );                                                   ");
        
        Object[] objects = new Object[] {                
        		maPttRtnDetailDTO.getCompNo(),
        		maPttRtnDetailDTO.getWcodeId(),
        		maPttRtnDetailDTO.getCompNo(),
        		maPttRtnDetailDTO.getRtnQty(),
        		maPttRtnDetailDTO.getPartNo(),
        		maPttRtnDetailDTO.getCompNo(),
        		"B",
        		maPttRtnDetailDTO.getRecDept(),
        		maPttRtnDetailDTO.getRecBy()             
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    } 
    
    public int cancelPtRentStock(MaPttRtnDetailDTO maPttRtnDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAPTRENT_STOCK");
    	query.append("   SET rent_qty = rent_qty + ?");
    	query.append("WHERE comp_no	= ?");
    	query.append("  AND wcode_id= ?");
    	query.append("  AND part_id = ?");
    	query.append("  AND rec_dept= ?");
    	query.append("  AND rec_by=   ?");
        
        Object[] objects = new Object[] {                
        		maPttRtnDetailDTO.getRtnQty(),
        		maPttRtnDetailDTO.getCompNo(),
        		maPttRtnDetailDTO.getWcodeId(),
        		maPttRtnDetailDTO.getPartId(),
        		maPttRtnDetailDTO.getRecDept(),
        		maPttRtnDetailDTO.getRecBy()                
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    } 
}