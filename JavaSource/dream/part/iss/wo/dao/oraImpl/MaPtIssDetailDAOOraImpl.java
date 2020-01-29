package dream.part.iss.wo.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.part.iss.wo.dao.MaPtIssDetailDAO;
import dream.part.iss.wo.dto.MaPtIssCommonDTO;
import dream.part.iss.wo.dto.MaPtIssDetailDTO;

/**
 * 자재출고확정 - 상세 dao
 * 
 * @author ssong
 * @version $Id: MaPtIssDetailDAO.java,v 1.0 2015/12/02 08:25:47 ssong Exp $
 * @since 1.0
 * @spring.bean id="maPtIssDetailDAOTarget"
 * @spring.txbn id="maPtIssDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtIssDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPtIssDetailDAO
{

	public MaPtIssDetailDTO findDetail(MaPtIssCommonDTO maPtIssCommonDTO,User user)
    {
        QueryBuffer query = new QueryBuffer(); 
        
        query.append("SELECT                                                                                                        	");
        query.append("		 x.ptisslist_id                                               							AS PTISSLISTID      ");
        query.append("     , x.ptisslist_id                                               							AS PTISSLISTNO      ");
        query.append("     , x.ptiss_status                                               							AS PTISSSTATUS      ");
        query.append("     , SFACODE_TO_DESC(x.ptiss_status, 'PTISS_STATUS', 'SYS', '', '"+user.getLangId()+"')		AS PTISSSTATUSDESC	");
        query.append("     , x.issue_date                                                 							AS ISSUEDATE        ");
        query.append("     , x.issue_dept                                                 							AS ISSUEDEPTID      ");
        query.append("     , (SELECT a.description                                                                                  	");
        query.append("          FROM TADEPT a                                                                                       	");
        query.append("         WHERE a.comp_no = x.comp_no                                                                          	");
        query.append("           AND a.dept_id = x.issue_dept)                            							AS ISSUEDEPTDESC    ");
        query.append("     , x.issue_by                                                   							AS ISSUEBYID        ");
        query.append("     , (SELECT a.emp_name                                                                                     	");
        query.append("       	FROM TAEMP a                                                                                        	");
        query.append("         WHERE a.comp_no = x.comp_no                                                                         	 	");
        query.append("           AND a.emp_id  = x.issue_by)                               							AS ISSUEBYDESC      ");
        query.append("     , x.ptiss_type                                                                      		AS PTISSTYPEID      ");
        query.append("     , SFACODE_TO_DESC(x.ptiss_type, 'PTISS_TYPE', 'SYS', '', '"+user.getLangId()+"')     	AS PTISSTYPEDESC    ");
        query.append("     , x.plant                                                      							AS PLANTID          ");
        query.append("     , (SELECT a.description                                                                                      ");
        query.append("       	FROM TAPLANT a                                                                                          ");
        query.append("         WHERE a.comp_no = x.comp_no                                                                              ");
        query.append("           AND a.plant   = x.plant)                                   						AS PLANTDESC        ");
        query.append("     , x.wcode_id                                                   							AS WCODEID          ");
        query.append("	   , (SELECT a.wname 																							");
        query.append("			FROM TAWAREHOUSE a																						");
        query.append("		   WHERE a.comp_no  = x.comp_no																				");
        query.append("			 AND a.wcode_id = x.wcode_id)														AS WNAME			");
        query.append("     , y.part_id                                                   							AS PARTID           ");
        query.append("     , y.part_no                                                   							AS PARTNO           ");
        query.append("     , y.description                                               							AS PARTDESC         ");
        query.append("     , y.pt_size                                                   							AS PTSIZE           ");
        query.append("     , y.MODEL                                                     							AS MODEL            ");
        query.append("     , x.part_grade                                                 							AS PARTGRADE        ");
        query.append("     , SFACODE_TO_DESC(x.part_grade, 'PART_GRADE', 'SYS', '', '"+user.getLangId()+"')			AS PARTGRADEDESC    ");
        query.append("     , x.issue_qty                                                 							AS ISSUEQTY         ");
        query.append("     , x.rec_by                                                     							AS RECBYID          ");
        query.append("     , (SELECT a.emp_name                                                                                         ");
        query.append("       	FROM TAEMP a                                                                                            ");
        query.append("         WHERE a.comp_no = x.comp_no                                                                              ");
        query.append("         	 AND a.emp_id  = x.rec_by)                                 							AS RECBYDESC        ");
        query.append("     , NVL((SELECT a.stock_qty                                                                                 ");
        query.append("                 FROM TAPTSTOCK a                                                                                 ");
        query.append("                WHERE a.comp_no = x.comp_no                                                                       ");
        query.append("                  AND a.part_id = x.part_id                                                                       ");
        query.append("                  AND a.part_grade = x.part_grade                                                                 ");
        query.append("                  AND a.wcode_id = x.wcode_id), 0)                							AS STOCKQTY         ");
        query.append("     , x.REMARK                                                     							AS REMARK           ");
        query.append("     , y.maker                                                     							AS MAKER           ");
        query.append(" FROM  TAPTISSLIST x INNER JOIN TAPARTS y																			");
        query.append("  					   ON y.comp_no = x.comp_no  																");
        query.append("  					  AND y.part_id = x.part_id																	");
        query.append("WHERE 1 = 1                                                                            							");
        query.append("  AND x.comp_no 		= ?                                                                            				");
        query.append("  AND x.ptisslist_id  = ?                                                                       					");
        
        Object[] objects = new Object[]{
    			user.getCompNo()
    			,maPtIssCommonDTO.getPtisslistId()
    	};

        MaPtIssDetailDTO maPtIssDetailDTO = 
        		(MaPtIssDetailDTO)getJdbcTemplate().query(query.toString(),objects, new MwareExtractor(new MaPtIssDetailDTO()));
        
        return maPtIssDetailDTO;
    }
	
	
	public boolean checkWorkOrderStatus(MaPtIssDetailDTO maPtIssDetailDTO, User user)
    {
    	
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append(" SELECT a.wkor_id     ");
    	query.append("   FROM TAWORKORDER a ");
    	query.append("  WHERE 1 = 1         ");
    	query.append("    AND a.comp_no = ?	");
    	query.append("    AND a.wkor_id = ?	");
    	Object[] objects = new Object[]{
    			user.getCompNo()
    			,maPtIssDetailDTO.getWkorId()
    	};
    	
    	return QueryBuffer.haveData(getJdbcTemplate().queryForList(query.toString(),objects) );
    }
	
	public int insertWoPart(MaPtIssDetailDTO maPtIssDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("INSERT INTO TAWOPARTS (                            						");
        query.append("		comp_no			, wopart_id			, wkor_id		, part_id       ");
        query.append("    , wcode_id  		, part_grade 		, use_qty 		, unit_price	");
        query.append("    , tot_price 		, ptisslist_id                       				");
        query.append(") SELECT                                            						");
        query.append("     		?                                      							");
        query.append("     	  , ?                                             					");
        query.append("        , ?                                             					");
        query.append("     	  , a.part_id                                     					");
        query.append("     	  , ?                                             					");
        query.append("     	  , ?                                             					");
        query.append("     	  , ?                                             					");
        query.append("     	  , a.last_price                                  					");
        query.append("     	  , a.last_price * ?                              					");
        query.append("     	  , ?                                             					");
        query.append("   FROM TAPARTS a                                     					");
        query.append("  WHERE 1 = 1                                         					");
        query.append("    AND a.comp_no = ?                              						");
        query.append("    AND a.part_id = ?                              						");
        
        Object[] objects = new Object[] {
        		 user.getCompNo()
         	   , maPtIssDetailDTO.getWopartId()
         	   , maPtIssDetailDTO.getWkorId()
         	   , maPtIssDetailDTO.getWcodeId()
         	   , maPtIssDetailDTO.getPartGrade()
         	   , maPtIssDetailDTO.getIssueQty()
         	   , maPtIssDetailDTO.getIssueQty()
         	   , maPtIssDetailDTO.getPtisslistId()
         	   , user.getCompNo()
         	   , maPtIssDetailDTO.getPartId()
        };
        return getJdbcTemplate().update(query.toString(), objects);
    }
	
	
	public int insertPtIssList(MaPtIssDetailDTO maPtIssDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append(" INSERT INTO TAPTISSLIST(                                    		");     
    	query.append("     comp_no 		,ptisslist_id 	,wopart_id 		,wkor_id        ");
    	query.append("    ,wcode_id 	,part_grade 	,issue_date 	,part_id        ");
    	query.append("    ,issue_qty 	,unit_price 	,tot_price 		,ptiss_status	");
    	query.append("    ,issue_dept 	,issue_by 		,rec_by 		,rec_dept       ");
    	query.append("    ,ptiss_type 	,plant          ,remark                        	");
    	query.append("   )                                                         		");
    	query.append(" SELECT                                                      		");
    	query.append("     a.comp_no    ,?              ,?              ,?             	");
    	query.append("     ,?           ,?              ,?              ,a.part_id      ");
    	query.append("     ,?          	,a.last_price   ,(a.last_price * ?)	,'W'        ");
    	query.append("     ,?           ,?              ,?              ,(SELECT dept_id FROM TAEMP WHERE comp_no = ? AND emp_id = ?)	");
    	query.append("     ,?           ,?              ,?                             	");
    	query.append(" FROM TAPARTS a                                              		");
    	query.append(" WHERE  1=1                                                  		");
    	query.append("     AND a.comp_no = ?                                       		");
    	query.append("     AND a.part_id = ?                                       		");

    	Object[] objects = new Object[] {
    			maPtIssDetailDTO.getPtisslistId()
        		,maPtIssDetailDTO.getWopartId()
        		,maPtIssDetailDTO.getWkorId()
        		,maPtIssDetailDTO.getWcodeId()
        		,maPtIssDetailDTO.getPartGrade()
        		,maPtIssDetailDTO.getIssueDate()
        		,maPtIssDetailDTO.getIssueQty()
        		,maPtIssDetailDTO.getIssueQty()
        		,maPtIssDetailDTO.getIssueDeptId()
        		,maPtIssDetailDTO.getIssueById()
        		,maPtIssDetailDTO.getRecById()
        		,user.getCompNo()
        		,maPtIssDetailDTO.getRecById()
        		,maPtIssDetailDTO.getPtissTypeId()
        		,maPtIssDetailDTO.getPlantId()
        		,maPtIssDetailDTO.getRemark()
        		,user.getCompNo()
        		,maPtIssDetailDTO.getPartId()
        };
		
            
        return getJdbcTemplate().update(query.toString(), objects);
    }
	
	
	public int updateWoPart(MaPtIssDetailDTO maPtIssDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("UPDATE TAWOPARTS  SET                                             ");
        query.append("   part_id                 = ?                                     ");
        query.append("  ,wkor_id                 = ?                                     ");
        query.append("  ,use_qty                 = ?                                     ");
        query.append("  ,wcode_id                = ?                                     ");
        query.append("  ,part_grade              = ?                                     ");
        query.append("  ,unit_price = (SELECT NVL(last_price,0)                          ");
        query.append("                  FROM TAPARTS                                     ");
        query.append("                  WHERE comp_no = ?                                ");
        query.append("                    AND part_id = ?)                               ");
        query.append("  ,tot_price = ((SELECT NVL(last_price,0)                          ");
        query.append("                  FROM TAPARTS                                     ");
        query.append("                  WHERE comp_no = ?                                ");
        query.append("                    AND part_id = ?)*NVL(use_qty,0))               ");
        query.append("WHERE wopart_id       = ?                                          ");
        query.append("  AND comp_no         = ?                                          ");
        
        Object[] objects = new Object[] {
                maPtIssDetailDTO.getPartId()
                ,maPtIssDetailDTO.getWkorId()
                ,maPtIssDetailDTO.getIssueQty()
                ,maPtIssDetailDTO.getWcodeId()
                ,maPtIssDetailDTO.getPartGrade()
                ,user.getCompNo()
                ,maPtIssDetailDTO.getPartId()
                ,user.getCompNo()
                ,maPtIssDetailDTO.getPartId()
                ,maPtIssDetailDTO.getWopartId()
                ,user.getCompNo()
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
    }
	
	public int updatePtIssList(MaPtIssDetailDTO maPtIssDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("UPDATE TAPTISSLIST SET                                             ");
        query.append("   wopart_id           = ?                                         ");
        query.append("  ,wkor_id             = ?                                         ");
        query.append("  ,part_id             = ?                                         ");
        query.append("  ,wcode_id            = ?                                         ");
        query.append("  ,part_grade          = ?                                         ");
        query.append("  ,issue_qty           = ?                                         ");
        query.append("  ,unit_price = (SELECT NVL(last_price,0)                          ");
        query.append("                  FROM TAPARTS aa                                  ");
        query.append("                  WHERE comp_no = ?                                ");
        query.append("                    AND part_id = ?)                               ");
        query.append("  ,tot_price = ((SELECT NVL(last_price,0)                          ");
        query.append("                  FROM TAPARTS                                     ");
        query.append("                  WHERE comp_no = ?                                ");
        query.append("                    AND part_id = ?)*NVL(issue_qty,0))             ");
        query.append("  ,issue_dept          = ?                                         ");
        query.append("  ,issue_by            = ?                                         ");
        query.append("  ,rec_dept            = (SELECT dept_id FROM TAEMP WHERE comp_no = ? AND emp_id = ?)  ");
        query.append("  ,rec_by              = ?                                         ");
        query.append("  ,ptiss_type          = ?                                         ");
        query.append("  ,plant	          	 = ?                                         ");
        query.append("  ,remark	          	 = ?                                         ");
        query.append("WHERE ptisslist_id     = ?                                         ");
        query.append("    and comp_no        = ?                                         ");

        
        Object[] objects = new Object[] {
                maPtIssDetailDTO.getWopartId()
                ,maPtIssDetailDTO.getWkorId()
                ,maPtIssDetailDTO.getPartId()
                ,maPtIssDetailDTO.getWcodeId()
                ,maPtIssDetailDTO.getPartGrade()
                ,maPtIssDetailDTO.getIssueQty()
                ,user.getCompNo()
                ,maPtIssDetailDTO.getPartId()
                ,user.getCompNo()
                ,maPtIssDetailDTO.getPartId()
                ,maPtIssDetailDTO.getIssueDeptId()
        		,maPtIssDetailDTO.getIssueById()
        		,user.getCompNo()
        		,maPtIssDetailDTO.getRecById()
        		,maPtIssDetailDTO.getRecById()
        		,maPtIssDetailDTO.getPtissTypeId()
        		,maPtIssDetailDTO.getPlantId()
        		,maPtIssDetailDTO.getRemark()
        		,maPtIssDetailDTO.getPtisslistId()
                ,user.getCompNo()
        };
        
        return getJdbcTemplate().update(query.toString(), objects);
        
    }
	
	
	public int confirmIssuePart(MaPtIssDetailDTO maPtIssDetailDTO, User user)
	{
		QueryBuffer query = new QueryBuffer();
		
		query.append("UPDATE TAPTISSLIST SET                                             ");
		query.append("   issue_date          = ?                                         ");
		query.append("  ,ptiss_status        = ?                                         ");
		query.append("  ,unit_price = (SELECT NVL(last_price,0)                          ");
		query.append("                  FROM TAPARTS aa                                  ");
		query.append("                  WHERE comp_no = ?                                ");
		query.append("                    AND part_id = ?)                               ");
		query.append("  ,tot_price = ((SELECT NVL(last_price,0)                          ");
		query.append("                  FROM TAPARTS                                     ");
		query.append("                  WHERE comp_no = ?                                ");
		query.append("                    AND part_id = ?)*NVL(issue_qty,0))               ");
		query.append("  ,issue_dept          = ?                                         ");
		query.append("  ,issue_by            = ?                                         ");
		query.append("  ,rec_dept            = ?                                         ");
		query.append("  ,rec_by              = ?                                         ");
		query.append("WHERE ptisslist_id     = ?                                         ");
		query.append("    and comp_no        = ?                                         ");
		
		
		Object[] objects = new Object[] {
				 DateUtil.getDate()
				,maPtIssDetailDTO.getPtissStatus()
				,user.getCompNo()
				,maPtIssDetailDTO.getPartId()
				,user.getCompNo()
				,maPtIssDetailDTO.getPartId()
				,user.getDeptId()
				,user.getEmpId()
				,user.getDeptId()
				,maPtIssDetailDTO.getRecById()
				,maPtIssDetailDTO.getPtisslistId()
				,user.getCompNo()
		};
		
		return getJdbcTemplate().update(query.toString(), objects);
		
	}
	
	
	public int insertPtIssHist(MaPtIssDetailDTO maPtIssDetailDTO, String ptisshistId, String ptissMode, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append(" insert into TAPTISSHIST(                                 ");
        query.append("     comp_no ,ptisshist_id ,ptiss_mode ,ptiss_type        ");
        query.append("     ,isslist_id ,dept_id ,wcode_id ,iss_date             ");
        query.append("     ,part_id ,part_grade ,iss_qty ,unit_price            ");
        query.append("     ,tot_price                                           ");
        query.append(" )                                                        ");
        query.append(" select                                                   ");
        query.append("     a.comp_no                                            ");
        query.append("     ,?                                                   ");
        query.append("     ,?                                                   ");
        query.append("     ,a.ptiss_type                                        ");
        query.append("     ,a.ptisslist_id                                      ");
        query.append("     ,a.issue_dept                                        ");
        query.append("     ,a.wcode_id                                          ");
        query.append("     ,a.issue_date                                        ");
        query.append("     ,a.part_id                                           ");
        query.append("     ,a.part_grade                                        ");
        query.append("     ,a.issue_qty                                         ");
        query.append("     ,a.unit_price                                        ");
        query.append("     ,a.tot_price                                         ");
        query.append(" from TAPTISSLIST a                                       ");
        query.append(" where 1=1                                                ");
        query.append("     and a.comp_no = ?                                    ");
        query.append("     and a.ptisslist_id = ?                               ");
        
        Object[] objects = new Object[] {
        		ptisshistId
        		,ptissMode
        		,user.getCompNo()
        		,maPtIssDetailDTO.getPtisslistId()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
	
	
	public void execSP_PT_OUTSTOCK(String ptisshistId, User user)
    {
        QueryBuffer query = new QueryBuffer();
        query.append("BEGIN                                                          ");
        query.append("SP_PT_OUTSTOCK(                                                ");
        query.append("                  '"+user.getCompNo()+"'                       ");
        query.append("                 ,"+ptisshistId+"                              ");
        query.append("                 );                                            ");
        query.append("END;                                                           ");
    
        this.getJdbcTemplate().execute(query.toString());
    }


    @Override
    public String getUseQty(MaPtIssDetailDTO maPtIssDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT NVL(SUM(use_qty),0)");
        query.append("FROM TAWOPARTS            ");
        query.append("WHERE comp_no    = ?      ");
        query.append("AND ptisslist_id = ?      ");
        
        Object[] objects = new Object[] {
                user.getCompNo()
                ,maPtIssDetailDTO.getPtisslistId()
        };
        
        return QueryBuffer.listToString(this.getJdbcTemplate().queryForList(query.toString(), objects));
    }


    @Override
    public int updateEqPart(MaPtIssDetailDTO maPtIssDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;
        String today = DateUtil.getTimeStamp(user.getOffset());
        
        query.append("MERGE INTO TAEQPART a                                                 ");
        query.append("USING ( SELECT                                                        ");
        query.append("            x.comp_no compNo                                          ");
        query.append("            ,y.equip_id equipId                                       ");
        query.append("            ,NVL(x.eqasmb_id, z.eqasmb_id) eqAsmbId                   ");
        query.append("            ,x.part_id partId                                         ");
        query.append("        FROM TAWOPARTS x INNER JOIN TAWOEQUIP y                       ");
        query.append("        ON x.comp_no = y.comp_no                                      ");
        query.append("        AND x.wkor_id = y.wkor_id                                     ");
        query.append("        INNER JOIN TAWORKORDER z                                      ");
        query.append("        ON x.comp_no = z.comp_no                                      ");
        query.append("        AND x.wkor_id = z.wkor_id                                     ");
        query.append("        WHERE x.comp_no    = ?                                        ");
        query.append("        AND x.ptisslist_id = ?) b                                     ");
        query.append("ON (  a.comp_no = b.compNo                                            ");
        query.append("  AND a.equip_id = b.equipId                                          ");
        query.append("  AND NVL(a.eqasmb_id,0) = NVL(b.eqAsmbId,0)                          ");
        query.append("  AND a.part_id = b.partId)                                           ");
        query.append("WHEN MATCHED THEN                                                     ");
        query.append("  UPDATE SET a.issue_time = NVL(a.issue_time,0) + 1                   ");
        query.append("             ,a.issue_last_date = ?                                   ");
        query.append("             ,a.use_qty = NVL(a.use_qty,0) + ?                        ");
        query.append("WHEN NOT MATCHED THEN                                                 ");
        query.append("INSERT (a.comp_no, a.eqpart_id, a.equip_id, a.eqasmb_id, a.part_id, a.consist_qty, a.use_qty, a.issue_time, a.issue_first_date, a.issue_last_date, a.is_eqtype_part, a.is_use)           ");
        query.append("VALUES (b.compNo, SQAEQPART_ID.NEXTVAL, b.equipId,b.eqAsmbId, b.partId, 1, ?, 1, ?, ?, 'N', 'Y' )           ");
        
        Object[] objects = new Object[] {
                user.getCompNo()
                ,maPtIssDetailDTO.getPtisslistId()
                ,today
                ,maPtIssDetailDTO.getIssueQty()
                ,maPtIssDetailDTO.getIssueQty()
                ,today
                ,today
        };
        
        rtnValue = getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }


    @Override
    public int updateCancelEqPart(MaPtIssDetailDTO maPtIssDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;
        
        query.append("MERGE INTO TAEQPART a                                                 ");
        query.append("USING ( SELECT                                                        ");
        query.append("            x.comp_no compNo                                          ");
        query.append("            ,y.equip_id equipId                                       ");
        query.append("            ,x.eqasmb_id eqAsmbId                                     ");
        query.append("            ,x.part_id partId                                         ");
        query.append("        FROM TAWOPARTS x INNER JOIN TAWOEQUIP y                       ");
        query.append("        ON x.comp_no = y.comp_no                                      ");
        query.append("        AND x.wkor_id = y.wkor_id                                     ");
        query.append("        WHERE x.comp_no    = ?                                        ");
        query.append("        AND x.ptisslist_id = ?) b                                     ");
        query.append("ON (  a.comp_no = b.compNo                                            ");
        query.append("  AND a.equip_id = b.equipId                                          ");
        query.append("  AND NVL(a.eqasmb_id,0) = NVL(b.eqAsmbId,0)                          ");
        query.append("  AND a.part_id = b.partId)                                           ");
        query.append("WHEN MATCHED THEN                                                     ");
        query.append("  UPDATE SET a.issue_time = NVL(a.issue_time,0) - 1                   ");
        query.append("             ,a.issue_last_date = ?                                   ");
        query.append("             ,a.use_qty = NVL(a.use_qty,0) - ?                        ");
        
        Object[] objects = new Object[] {
                user.getCompNo()
                ,maPtIssDetailDTO.getPtisslistId()
                ,DateUtil.getTimeStamp(user.getOffset())
                ,maPtIssDetailDTO.getIssueQty()
        };
        
        rtnValue = getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }


    @Override
    public String findStockQty(MaPtIssDetailDTO maPtIssDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT NVL(stock_qty,0)   ");
        query.append("FROM TAPTSTOCK            ");
        query.append("WHERE comp_no  = ?        ");
        query.append("AND part_id    = ?        ");
        query.append("AND part_grade = ?        ");
        query.append("AND wcode_id   = ?        ");
        
        Object[] objects = new Object[] {
                user.getCompNo()
                ,maPtIssDetailDTO.getPartId()
                ,maPtIssDetailDTO.getPartGrade()
                ,maPtIssDetailDTO.getWcodeId()
        };
        
        return QueryBuffer.listToString(this.getJdbcTemplate().queryForList(query.toString(), objects));
    }
}