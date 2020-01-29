package dream.tool.adj.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.tool.adj.dao.MaPttDisDetailDAO;
import dream.tool.adj.dto.MaPttDisDetailDTO;

/**
 * 공기구반납 - 상세 dao
 * 
 * @author ssong
 * @version $Id: $
 * @since 1.0
 * @spring.bean id="maPttDisDetailDAOTarget"
 * @spring.txbn id="maPttDisDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPttDisDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPttDisDetailDAO
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
    public MaPttDisDetailDTO findDetail(User user, String ptdisuseListId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String compNo = user.getCompNo();
        
        query.append("SELECT");
        query.append("       x.ptdisuselist_id ptdisuselistId,");
        query.append("       x.ptdisuse_status ptDisStatus,");
        query.append("       dbo.SFACODE_TO_DESC(x.ptdisuse_status, 'PTDISUSE_STATUS', 'SYS', x.comp_no,'"+user.getLangId()+"') ptDisStatusDesc,");
        query.append("       x.description description,");
        query.append("       x.disuse_date disDate,");
        query.append("       x.wcode_id wcodeId,");
        query.append("       (SELECT wname FROM TAWAREHOUSE                      ");
        query.append("            WHERE  comp_no  = x.comp_no                        ");
        query.append("              AND  wcode_id = x.wcode_id)        wname,");
        query.append("          x.exe_dept exeDept,");
        query.append("          dbo.SFAIDTODESC(x.exe_dept, '', 'DEPT', x.comp_no)  exeDeptDesc,     ");
        query.append("             x.exe_by exeBy,");
        query.append("             dbo.SFAIDTODESC(x.exe_by, '', 'EMP', x.comp_no) exeByName ,");
        query.append("        x.remark remark ");
        query.append("FROM TAPTDISUSELIST x");
        query.append("WHERE 1=1                         ");
        query.append("  AND  x.comp_no      = '"+compNo+"'	                    ");
        query.append("  AND  x.ptdisuselist_id = '"+ptdisuseListId+"'                 ");
    
        MaPttDisDetailDTO maPttDisDetailDTO = 
        		(MaPttDisDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaPttDisDetailDTO()));
        
        return maPttDisDetailDTO;
    }
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttDisDetailDTO
     * @return
     */
    public int insertDetail(MaPttDisDetailDTO maPttDisDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAPTDISUSELIST (                               	");
    	query.append("  comp_no,    ptdisuselist_id,  ptdisuse_status,   wcode_id,		");
    	query.append("  description,disuse_date,      exe_dept,          exe_by,		");
    	query.append("  remark		");
    	query.append(")	VALUES (                                                ");
    	query.append("  ?,         ?,             ?,            ?,              ");
    	query.append("  ?,         ?,             ?,            ?,              ");
    	query.append("  ?        			                					");
    	query.append(")                                                         ");
    	
    	Object[] objects = new Object[] {
    			maPttDisDetailDTO.getCompNo(),
    			maPttDisDetailDTO.getPtdisuselistId(),
    			maPttDisDetailDTO.getPtDisStatus(),
    			maPttDisDetailDTO.getWcodeId(),
    			maPttDisDetailDTO.getDescription(),
    			maPttDisDetailDTO.getDisDate(),
    			maPttDisDetailDTO.getExeDept(),
    			maPttDisDetailDTO.getExeBy(),
    			maPttDisDetailDTO.getRemark()

    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail stock
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttDisDetailDTO
     * @return
     */
    public int updateDetail(MaPttDisDetailDTO maPttDisDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAPTDISUSELIST SET		     ");    	       
    	query.append("	     ptdisuse_status   = ?,      ");
    	query.append("	     wcode_id   	   = ?,      ");
    	query.append("	     description   	   = ?,      ");
    	query.append("	     disuse_date   	   = ?,      ");
    	query.append("	     exe_dept   	   = ?,      ");	
    	query.append("	     exe_by  		   = ?,      ");	
        query.append("       remark            = ?       ");
    	query.append("WHERE  comp_no           = ?	     ");
    	query.append("  AND  ptdisuselist_id   = ?       ");
    	
    	Object[] objects = new Object[] {
    			maPttDisDetailDTO.getPtDisStatus(),
    			maPttDisDetailDTO.getWcodeId(),
    			maPttDisDetailDTO.getDescription(),
    			maPttDisDetailDTO.getDisDate(),
    			maPttDisDetailDTO.getExeDept(),
    			maPttDisDetailDTO.getExeBy(),
    			maPttDisDetailDTO.getRemark(),
    			maPttDisDetailDTO.getCompNo(),
    			maPttDisDetailDTO.getPtdisuselistId()

    	};
    	
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    

       
    /**
     * valid check
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPttDisDetailDTO
     * @return
     */
    public String validPrRecListNo(MaPttDisDetailDTO maPttDisDetailDTO)
    {
        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT COUNT(*)                                           ");
        query.append("FROM   TAPTPRRECLIST x                                    ");
        query.append("WHERE  comp_no      = '"+maPttDisDetailDTO.getCompNo()+"'  ");
       // query.append("  AND  prreclist_no = '"+maPttDisDetailDTO.getPrRecListNo()+"'  ");
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    
    /**
     * 상태 조회
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPttDisDetailDTO
     * @return
     */
    public String findPtDisListStatus(String compNo, String ptdisuseListId)
    {
        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT ptdisuse_status                                    ");
        query.append("FROM   TAPTDISUSELIST x                                   ");
        query.append("WHERE  comp_no      = '"+compNo+"'                        ");
        query.append("  AND  ptdisuselist_id = '"+ptdisuseListId+"'             ");
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
      


    
    public int updatePtDisList(MaPttDisDetailDTO maPttDisDetailDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("UPDATE TAPTDISUSELIST SET                        ");
        query.append("  ptdisuse_status           = ?               ");
        query.append("WHERE ptdisuselist_id          = ?               ");

        
        Object[] objects = new Object[] {
                maPttDisDetailDTO.getPtDisStatus(),
                maPttDisDetailDTO.getPtdisuselistId()
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
        
    }
    
    public int insertPtIssHist(String ptIssHistId,MaPttDisDetailDTO maPttDisDetailDTO,  String ptissMode)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;
        
        query.append("INSERT INTO  TAPTISSHIST                                          ");
        query.append("(comp_no,             ptisshist_id,       ptiss_mode,             ");
        query.append(" ptiss_type,          isslist_id,         dept_id,                ");
        query.append(" wcode_id,            iss_date,           part_id,                ");
        query.append(" iss_qty,             part_grade) ");
        query.append("SELECT x.comp_no                                                  ");
        query.append("      ,?                                   ");
        query.append("      ,?                                                          ");
        query.append("      ,'DISUSE'                                                   ");
        query.append("      ,x.ptdisuseitem_id                                			");
        query.append("      ,y.exe_dept                     							");
        query.append("      ,ISNULL(y.wcode_id,0)                                          ");
        query.append("      ,y.disuse_date                             					");
        query.append("      ,x.part_id                                                  ");
        query.append("      ,x.disuse_qty                                          		");
        query.append("      ,part_grade                                                 ");
        query.append("FROM TAPTDISUSEITEM x, TAPTDISUSELIST y                           ");
        query.append("WHERE x.comp_no       = ?                                         ");
        query.append("AND x.ptdisuselist_id=y.ptdisuselist_id                           ");
        query.append("AND x.ptdisuselist_id = ?                                       	");
        Object[] objects = new Object[] {
        		ptIssHistId,
                ptissMode,
                maPttDisDetailDTO.getCompNo(),
                maPttDisDetailDTO.getPtdisuselistId()
              
        };
        
        rtnValue = getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
    
    public void updatePtStock(String partId,String disQty,String wcodeId,String compNo)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        query.append("UPDATE TAPTSTOCK		");
        query.append("SET    stock_qty= stock_qty- ?");
        query.append("WHERE  COMP_no  = ?  	");
        query.append("  AND  wcode_id = ?	");
        query.append("  AND  part_id  = ?	");

        Object[] objects = new Object[] {
        		disQty,
        		compNo,
        		wcodeId,
        		partId
              
        };
      
        getJdbcTemplate().update(query.toString(), objects);
    }
    
    public void cancelPtStock(MaPttDisDetailDTO maPttDisDetailDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        query.append("UPDATE TAPTSTOCK		");
        query.append("SET    stock_qty= stock_qty- ?");
        query.append("WHERE  COMP_no  = ?  	");
        query.append("  AND  wcode_id = ?	");
        query.append("  AND  part_id  = ?	");

        Object[] objects = new Object[] {
/*        		maPttDisDetailDTO.getRtnQty(),
        		maPttDisDetailDTO.getCompNo(),
        		maPttDisDetailDTO.getWcodeId(),
        		maPttDisDetailDTO.getPartId()*/
              
        };
      
        getJdbcTemplate().update(query.toString(), objects);
    }


    
    public List findItemList(MaPttDisDetailDTO maPttDisDetailDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append(" SELECT");
        query.append("        x.comp_no compNo,");
        query.append("        x.ptdisuseitem_id ptdisuseitemId,");
        query.append("        y.part_id partId,");
        query.append("        x.disuse_qty disQty,");
        query.append("        z.wcode_id wcodeId");
        query.append(" FROM TAPTDISUSEITEM x, TAPARTS y, TAPTDISUSELIST z");
        query.append(" WHERE x.part_id  = y.part_id");
        query.append("  AND  x.ptdisuselist_id=z.ptdisuselist_id");
        query.append("  AND  x.comp_no      = '"+maPttDisDetailDTO.getCompNo()+"'	                    ");
        query.append("  AND  x.ptdisuselist_id      = '"+maPttDisDetailDTO.getPtdisuselistId()+"'          ");
        query.append("ORDER BY x.ptdisuseitem_id									");
        
        return getJdbcTemplate().queryForList(query.toString());
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
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("EXEC dbo.SP_PT_OUTSTOCK '"+compNo+"', '"+ptIssHistId+"';         ");
      
        this.getJdbcTemplate().execute(query.toString());
        
        return 0;
    }
}