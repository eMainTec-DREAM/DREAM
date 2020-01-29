package dream.part.adj.stkmove.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.part.adj.stkmove.dao.PartAdjStkMoveDetailDAO;
import dream.part.adj.stkmove.dto.PartAdjStkMoveCommonDTO;
import dream.part.adj.stkmove.dto.PartAdjStkMoveDetailDTO;

/**
 * 재고이동 - 상세 dao
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="partAdjStkMoveDetailDAOTarget"
 * @spring.txbn id="partAdjStkMoveDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PartAdjStkMoveDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements PartAdjStkMoveDetailDAO
{
    /**
     * detail find
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param partAdjStkMoveCommonDTO
     * @return
     */
    public PartAdjStkMoveDetailDTO findDetail(PartAdjStkMoveCommonDTO partAdjStkMoveCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                                                ");
        query.append("    a.ptstkmove_id                                                                            AS ptStkMoveId          ");
        query.append("    ,a.ptstkmove_no                                                                           AS ptStkMoveNo          ");
        query.append("    ,a.ptstkmove_status                                                                       AS ptStkMoveStatus      ");
        query.append("    ,SFACODE_TO_DESC(a.ptstkmove_status,'PTSTKMOVE_STATUS','SYS','','ko')                     AS ptStkMoveStatusDesc  ");
        query.append("    ,a.plant                                                                                  AS plant                ");
        query.append("    ,(SELECT description FROM TAPLANT WHERE comp_no = a.comp_no AND plant = a.plant)          AS plantDesc            ");
        query.append("    ,a.from_wcode_id                                                                          AS fromWcodeId          ");
        query.append("    ,(SELECT wname FROM TAWAREHOUSE WHERE comp_no = a.comp_no AND wcode_id = a.from_wcode_id) AS fromWname            ");
        query.append("    ,a.to_wcode_id                                                                            AS toWcodeId            ");
        query.append("    ,(SELECT wname FROM TAWAREHOUSE WHERE comp_no = a.comp_no AND wcode_id = a.to_wcode_id)   AS toWname              ");
        query.append("    ,a.move_date                                                                              AS moveDate             ");
        query.append("    ,a.part_id                                                                                AS partId               ");
        query.append("    ,b.part_no                                                                                AS partNo               ");
        query.append("    ,b.description||', '||b.pt_size                                                           AS ptNameSize           ");
        query.append("    ,b.description                                                                            AS ptDesc               ");
        query.append("    ,b.pt_size                                                                                AS ptSize               ");
        query.append("    ,b.model                                                                                  AS model                ");
        query.append("    ,a.part_grade                                                                             AS partGrade            ");
        query.append("    ,SFACODE_TO_DESC(a.part_grade,'PART_GRADE','SYS','','ko')                                 AS partGradeDesc        ");
        query.append("    ,a.move_qty                                                                               AS moveQty              ");
        query.append("    ,a.reg_dept                                                                               AS regDept              ");
        query.append("    ,a.reg_date                                                                               AS regDate              ");
        query.append("    ,a.reg_id                                                                                 AS regId                ");
        query.append("    ,(SELECT emp_name FROM TAEMP WHERE comp_no = a.comp_no AND emp_id = a.reg_id)||'/'||                              ");
        query.append("     (SELECT description FROM TADEPT WHERE comp_no = a.comp_no AND dept_id = a.reg_dept)      AS regEmpDept           ");
        query.append("    ,a.remark                                                                                 AS remark               ");
        query.append("FROM TAPTSTKMOVE a LEFT OUTER JOIN TAPARTS b                                                                          ");
        query.append("ON a.comp_no = b.comp_no                                                                                              ");
        query.append("AND a.part_id = b.part_id                                                                                             ");
        query.append("  WHERE  a.comp_no = '"+user.getCompNo()+"'");
        query.append("  AND  a.ptstkmove_id = '"+partAdjStkMoveCommonDTO.getPtStkMoveId()+"'");
    
        PartAdjStkMoveDetailDTO partAdjStkMoveDetailDTO = 
        		(PartAdjStkMoveDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new PartAdjStkMoveDetailDTO()));
        
        return partAdjStkMoveDetailDTO;
    }
    
    /**
     * detail insert
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param partAdjStkMoveDetailDTO
     * @return
     */
    public int insertDetail(PartAdjStkMoveDetailDTO partAdjStkMoveDetailDTO, User user) throws Exception
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAPTSTKMOVE								    ");
    	query.append("	 (comp_no           ,ptstkmove_id       ,ptstkmove_no   ");
    	query.append("	 ,ptstkmove_status  ,plant              ,from_wcode_id  ");
    	query.append("	 ,to_wcode_id       ,part_id            ,part_grade     ");
    	query.append("	 ,move_qty          ,reg_dept           ,reg_date       ");
    	query.append("	 ,reg_id            ,remark                             ");
    	query.append("	)	VALUES												");
    	query.append("   (?                 ,?                  ,?              ");
        query.append("   ,?                 ,?                  ,?              ");
        query.append("   ,?                 ,?                  ,?              ");
        query.append("   ,?                 ,?                  ,?              ");
        query.append("   ,?                 ,?                                  ");
    	query.append("	)														");
    	
    	Object[] objects = new Object[] {
    	        user.getCompNo()
    			,partAdjStkMoveDetailDTO.getPtStkMoveId()
    			,partAdjStkMoveDetailDTO.getPtStkMoveNo()
    			,partAdjStkMoveDetailDTO.getPtStkMoveStatus()
    			,partAdjStkMoveDetailDTO.getPlant()
    			,partAdjStkMoveDetailDTO.getFromWcodeId()
    			,partAdjStkMoveDetailDTO.getToWcodeId()
    			,partAdjStkMoveDetailDTO.getPartId()
    			,partAdjStkMoveDetailDTO.getPartGrade()
    			,partAdjStkMoveDetailDTO.getMoveQty()
    			,partAdjStkMoveDetailDTO.getRegDept()
    			,partAdjStkMoveDetailDTO.getRegDate()
    			,partAdjStkMoveDetailDTO.getRegId()
    			,partAdjStkMoveDetailDTO.getRemark()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    /**
     * detail update
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param partAdjStkMoveDetailDTO
     * @return
     */
    public int updateDetail(PartAdjStkMoveDetailDTO partAdjStkMoveDetailDTO, User user) throws Exception
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TAPTSTKMOVE SET		    ");	
    	query.append("		from_wcode_id		= ?		");
    	query.append("		,to_wcode_id		= ?		");
    	query.append("		,part_id			= ?		");
    	query.append("		,part_grade			= ?		");
    	query.append("		,move_qty			= ?		");
    	query.append("		,remark			    = ? 	");
    	query.append("WHERE ptstkmove_id        = ?		");
    	query.append("  AND comp_no 		    = ?		");
    	
    	Object[] objects = new Object[] {
    			partAdjStkMoveDetailDTO.getFromWcodeId()
    			,partAdjStkMoveDetailDTO.getToWcodeId()
    			,partAdjStkMoveDetailDTO.getPartId()
    			,partAdjStkMoveDetailDTO.getPartGrade()
    			,partAdjStkMoveDetailDTO.getMoveQty()
    			,partAdjStkMoveDetailDTO.getRemark()
    			,partAdjStkMoveDetailDTO.getPtStkMoveId()
    			,user.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    @Override
    public int insertPtIssHistory(PartAdjStkMoveDetailDTO partAdjStkMoveDetailDTO, String ptIssHistId, String issMode, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("INSERT INTO TAPTISSHIST (                                         ");
        query.append("       comp_no,        ptisshist_id,                              ");
        query.append("       ptiss_mode,     ptiss_type,            isslist_id,         ");
        query.append("       dept_id,        wcode_id,              iss_date,           ");
        query.append("       part_id,        part_grade,            iss_qty,            ");
        query.append("       unit_price,     tot_price                                  ");
        query.append(")                                                                 ");
        query.append("SELECT x.comp_no,      ?,                                         ");
        query.append("       ?,              ?,                     x.ptstkmove_id,     ");
        query.append("       ?,              x.from_wcode_id,       ?,                  ");
        query.append("       x.part_id,      x.part_grade,          x.move_qty,         ");
        query.append("       y.last_price,   NVL(x.move_qty, 0)*NVL(y.last_price, 0)    ");
        query.append("FROM   TAPTSTKMOVE x INNER JOIN TAPARTS y                         ");
        query.append("  ON   x.comp_no = y.comp_no                                      ");
        query.append(" AND   x.part_id = y.part_id                                      ");
        query.append("WHERE  x.comp_no          = ?                                     ");
        query.append("  AND  x.ptstkmove_id     = ?                                     ");
        
        Object[] objects = new Object[] {
                ptIssHistId,
                issMode,
                "MOVE",
                user.getDeptId(),
                partAdjStkMoveDetailDTO.getMoveDate(),
                user.getCompNo(),
                partAdjStkMoveDetailDTO.getPtStkMoveId()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    @Override
    public int executeSpPtOutStock(String ptIssHistId, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("begin                                                     ");
        query.append("SP_PT_OUTSTOCK('"+user.getCompNo()+"', '"+ptIssHistId+"' );");
        query.append("end;                                                      ");
        
        this.getJdbcTemplate().execute(query.toString());
        
        return 0;
    }

    @Override
    public int insertPtRecHistory(PartAdjStkMoveDetailDTO partAdjStkMoveDetailDTO, String ptRecHistId, String recMode, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("INSERT INTO TAPTRECHIST (                                         ");
        query.append("       comp_no,        ptrechist_id,                              ");
        query.append("       ptrec_mode,     ptrec_type,            reclist_id,         ");
        query.append("       dept_id,        wcode_id,              rec_date,           ");
        query.append("       part_id,        part_grade,            rec_qty,            ");
        query.append("       unit_price,     tot_price                                  ");
        query.append(")                                                                 ");
        query.append("SELECT x.comp_no,      ?,                                         ");
        query.append("       ?,              ?,                     x.ptstkmove_id,     ");
        query.append("       ?,              x.to_wcode_id,         ?,                  ");
        query.append("       x.part_id,      x.part_grade,          x.move_qty,         ");
        query.append("       y.last_price,   NVL(x.move_qty, 0)*NVL(y.last_price, 0)    ");
        query.append("FROM   TAPTSTKMOVE x INNER JOIN TAPARTS y                         ");
        query.append("  ON   x.comp_no = y.comp_no                                      ");
        query.append(" AND   x.part_id = y.part_id                                      ");
        query.append("WHERE  x.comp_no          = ?                                     ");
        query.append("  AND  x.ptstkmove_id     = ?                                     ");
        
        Object[] objects = new Object[] {
                ptRecHistId,
                recMode,
                "MOVE",
                user.getDeptId(),
                partAdjStkMoveDetailDTO.getMoveDate(),
                user.getCompNo(),
                partAdjStkMoveDetailDTO.getPtStkMoveId()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    @Override
    public int executeSpPtInStock(String ptRecHistId, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("begin                                                     ");
        query.append("SP_PT_INSTOCK('"+user.getCompNo()+"', '"+ptRecHistId+"' );");
        query.append("end;                                                      ");
        
        this.getJdbcTemplate().execute(query.toString());
        
        return 0;
    }
	
	/**
     * detail update
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param partAdjStkMoveDetailDTO
     * @return
     */
    public int confirmDetail(PartAdjStkMoveDetailDTO partAdjStkMoveDetailDTO, User user) throws Exception
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TAPTSTKMOVE SET			");
    	query.append("	ptstkmove_status	    = ?,	");
    	query.append("	move_date				= ?	    ");
    	query.append("WHERE ptstkmove_id        = ?		");
    	query.append("  AND comp_no 		    = ?		");
    	
    	Object[] objects = new Object[] {
    	        "C"
    	        ,partAdjStkMoveDetailDTO.getMoveDate()
    			,partAdjStkMoveDetailDTO.getPtStkMoveId()
    			,user.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    public int cancelDetail(PartAdjStkMoveDetailDTO partAdjStkMoveDetailDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TAPTSTKMOVE SET			");
        query.append("	ptstkmove_status	    = ?,	");
        query.append("	move_date				= ?	    ");
        query.append("WHERE ptstkmove_id        = ?		");
        query.append("  AND comp_no 		    = ?		");
        
        Object[] objects = new Object[] {
                "W"
                ,partAdjStkMoveDetailDTO.getMoveDate()
                ,partAdjStkMoveDetailDTO.getPtStkMoveId()
                ,user.getCompNo()
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
}