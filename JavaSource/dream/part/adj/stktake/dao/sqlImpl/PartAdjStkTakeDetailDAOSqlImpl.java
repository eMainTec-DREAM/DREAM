package dream.part.adj.stktake.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.part.adj.stktake.dao.PartAdjStkTakeDetailDAO;
import dream.part.adj.stktake.dto.PartAdjStkTakeCommonDTO;
import dream.part.adj.stktake.dto.PartAdjStkTakeDetailDTO;
import dream.pers.appreq.dto.AppReqDetailDTO;

/**
 * 부품실사 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: PartAdjStkTakeDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="partAdjStkTakeDetailDAOTarget"
 * @spring.txbn id="partAdjStkTakeDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PartAdjStkTakeDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements PartAdjStkTakeDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param partAdjStkTakeCommonDTO
     * @return
     */
    public PartAdjStkTakeDetailDTO findDetail(PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO,User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                                        ");
        query.append("      x.ptstktakelist_id ptStkTakeListId,                                     ");
        query.append("      x.ptstktakelist_no ptStkTakeListNo,                                     ");
        query.append("      x.description description,                                              ");
        query.append("      x.wcode_id wcodeId,                                                     ");
        query.append("       (SELECT wname FROM TAWAREHOUSE                                         ");
        query.append("        WHERE  comp_no  = x.comp_no                                           ");
        query.append("          AND  wcode_id = x.wcode_id)        wname,                           ");
        query.append("       (SELECT wh_type FROM TAWAREHOUSE                                       ");
        query.append("        WHERE  comp_no  = x.comp_no                                           ");
        query.append("          AND  wcode_id = x.wcode_id)        whType,                          ");
        query.append("      x.plan_date pmDate,                                                     ");
        query.append("      x.REMARK REMARK,                                                        ");
        query.append("      act_date actDate,                                                       ");
        query.append("      x.dept_id deptId,                                                       ");
        query.append("      x.ptstktake_status ptStkTakeStatus,                                     ");
        query.append("        dbo.SFACODE_TO_DESC(x.ptstktake_status,'PTSTKTAKE_STATUS','SYS','','"+user.getLangId()+"')             ptStkTakeStatusDesc,         ");
        query.append("      (SELECT description                                                     ");
        query.append("       FROM   TADEPT a                                                        ");
        query.append("       WHERE  a.comp_no = x.comp_no                                           ");
        query.append("         AND  a.dept_id = x.dept_id) deptDesc,                                ");
        query.append("      x.reg_id userId,                                                        ");
        query.append("         (SELECT a.emp_name                                                   ");
        query.append("            FROM TAEMP a                                                      ");
        query.append("           WHERE a.comp_no = x.comp_no                                        ");
        query.append("             AND a.emp_id = x.reg_id) userDesc,                               ");
//        query.append("      (SELECT a.user_name                                                     ");
//        query.append("       FROM   TAUSER a INNER JOIN TAEMP b ON a.emp_id = b.emp_id              ");
//        query.append("       WHERE  a.comp_no = x.comp_no                                           ");
//        query.append("         AND  a.user_id = x.reg_id) userDesc,                                 ");
        query.append("       x.reg_date regDate                                                     ");
        query.append("       ,x.plant                        	AS plantId							");
        query.append("       ,(SELECT description                            						");
        query.append("          FROM TAPLANT                                 						");
        query.append("          WHERE comp_no = x.comp_no                    						");
        query.append("            AND plant = x.plant)       	AS plantDesc  						");
        query.append("       , lapp_no       					AS lappNo 	 						");
        query.append("FROM   TAPTSTKTAKELIST x                                                      ");
        query.append("WHERE  x.comp_no = '"+user.getCompNo()+"'                  					");
        query.append("  AND  x.ptstktakelist_id = '"+partAdjStkTakeCommonDTO.getPtStkTakeListId()+"'      ");
    
        PartAdjStkTakeDetailDTO partAdjStkTakeDetailDTO = 
        		(PartAdjStkTakeDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new PartAdjStkTakeDetailDTO()));
        
        return partAdjStkTakeDetailDTO;
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param partAdjStkTakeDetailDTO
     * @return
     */
    public int insertDetail(PartAdjStkTakeDetailDTO partAdjStkTakeDetailDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAPTSTKTAKELIST								");
    	query.append("	(comp_no		,ptstktakelist_id	,ptstktakelist_no	");
    	query.append("	 ,description	,ptstktake_status   ,wcode_id			");
    	query.append("	 ,dept_id		,reg_date			,reg_id				");
    	query.append("	 ,plan_date     ,act_date       	,remark				");
    	query.append("	 ,plant 		,lapp_no								");
    	query.append("	)	VALUES												");
    	query.append("	(?				,?					,?					");
    	query.append("	 ,?				,?					,?					");
    	query.append("	 ,?				,?					,?					");
    	query.append("	 ,?				,?					,? 					");
    	query.append("	 ,?				,?					 					");
    	query.append("	)														");
    	
    	Object[] objects = new Object[] {
    			partAdjStkTakeDetailDTO.getCompNo()
    			, partAdjStkTakeDetailDTO.getPtStkTakeListId()
    			, partAdjStkTakeDetailDTO.getPtStkTakeListId()
    			, partAdjStkTakeDetailDTO.getDescription()
    			, partAdjStkTakeDetailDTO.getPtStkTakeStatus()
    			, partAdjStkTakeDetailDTO.getWcodeId()
    			, partAdjStkTakeDetailDTO.getDeptId()
    			, partAdjStkTakeDetailDTO.getRegDate()
    			, partAdjStkTakeDetailDTO.getUserId()
    			, partAdjStkTakeDetailDTO.getPmDate()
    			, partAdjStkTakeDetailDTO.getActDate()
    			, partAdjStkTakeDetailDTO.getRemark()
    			, partAdjStkTakeDetailDTO.getPlantId()
    			, partAdjStkTakeDetailDTO.getLappNo()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailDAO.java,v 1.0 2015/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param partAdjStkTakeDetailDTO
     * @return
     */
    public int updateDetail(PartAdjStkTakeDetailDTO partAdjStkTakeDetailDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAPTSTKTAKELIST SET		");	
    	query.append("		description			= ?		");
    	query.append("		,wcode_id			= ?		");
    	query.append("		,plan_date			= ?		");
    	query.append("		,reg_id				= ?		");
    	query.append("		,dept_id			= ?		");
    	query.append("		,plant			   	= ? 	");
    	query.append("		,remark			    = ? 	");
    	query.append("		,lapp_no		    = ? 	");
    	query.append("WHERE ptstktakelist_id    = ?		");
    	query.append("  AND comp_no 		    = ?		");
    	
    	Object[] objects = new Object[] {
    	        partAdjStkTakeDetailDTO.getDescription()
    	        , partAdjStkTakeDetailDTO.getWcodeId()
                , partAdjStkTakeDetailDTO.getPmDate()
                , partAdjStkTakeDetailDTO.getUserId()
                , partAdjStkTakeDetailDTO.getDeptId()
                , partAdjStkTakeDetailDTO.getPlantId()
                , partAdjStkTakeDetailDTO.getRemark()
                , partAdjStkTakeDetailDTO.getLappNo()
                , partAdjStkTakeDetailDTO.getPtStkTakeListId()
                , partAdjStkTakeDetailDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    

    


	@Override
	public String validPtSaftyStck(PartAdjStkTakeDetailDTO partAdjStkTakeDetailDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertPtSaftyStock(PartAdjStkTakeDetailDTO partAdjStkTakeDetailDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updatePtSaftyStock(PartAdjStkTakeDetailDTO partAdjStkTakeDetailDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List findItemList(PartAdjStkTakeDetailDTO partAdjStkTakeDetailDTO, User user) {
	    QuerySqlBuffer query = new QuerySqlBuffer();
        query.append("SELECT												");
        query.append("       '' seqNo,										");
        query.append("       '' isDelCheck,									");
        query.append("       y.part_no partNo, 								");
        query.append("       y.description partDesc, 								");
        query.append("       y.pt_size partSize, 								");
        query.append("       x.part_grade ptGrade, 								");
        query.append("       x.stock_qty stockQty, 								");
        query.append("       x.real_qty realQty, 								");
        query.append("       x.gap_qty gapQty, 								");
        query.append("       x.remark remark, 								");
        query.append("       x.part_id partId, 								");
        query.append("       x.ptstktakeitem_id ptStkTakeItemId 							");
        query.append("FROM TAPTSTKTAKEITEM x, TAPARTS y										");
        query.append("WHERE x.part_id=y.part_id									");
        query.append("  AND x.comp_no =y.comp_no								");
        query.append("  AND x.comp_no = '"+user.getCompNo()+"'						");
        query.append("  AND  x.ptstktakelist_id = '"+partAdjStkTakeDetailDTO.getPtStkTakeListId()+"'");
        query.append("ORDER BY x.ptstktakelist_id								");
        
        return getJdbcTemplate().queryForList(query.toString());
	}

	@Override
	public int insertPtRecHistory(String ptRecHistId, String recQty, String partGrade,
			PartAdjStkTakeDetailDTO partAdjStkTakeDetailDTO, User loginUser, String ptStkTakeItemId) {
	    QuerySqlBuffer query = new QuerySqlBuffer();

		 query.append("INSERT INTO TAPTRECHIST (                                 		");
		 query.append("       comp_no,        ptrechist_id,                      		");
		 query.append("       ptrec_mode,     ptrec_type,            reclist_id, 		");
		 query.append("       dept_id,        wcode_id,              rec_date,   		");
		 query.append("       part_id,        part_grade,            rec_qty,    		");
		 query.append("       unit_price,     tot_price                          		");
		 query.append(")                                                         		");
		 query.append("SELECT y.comp_no,        ? ptrechistId,                     		");
		 query.append("       'C' ptrecMode,  'STOCK' ptrecType,     NULL reclistId,                 		");
		 query.append("       y.dept_Id,       y.wcode_Id,             convert(varchar, getdate(), 112),   		");
		 query.append("       x.part_id,      x.part_grade,           ? recQty,         		");
		 query.append("       z.last_price,   ISNULL(?, 0)*ISNULL(z.last_price, 0) totPrice  		");
		 query.append("FROM   TAPTSTKTAKEITEM x, TAPTSTKTAKELIST y, TAPARTS z		");
		 query.append("WHERE X.ptstktakelist_id = y.ptstktakelist_id           		");
		 query.append("AND x.part_id=z.part_id                               		");
		 query.append("AND  x.comp_no = ?                                      		");
		 query.append("  AND  x.ptstktakeitem_id = ?    		");



	        Object[] objects = new Object[] {
	                ptRecHistId,
	                recQty,
	                recQty,
	                loginUser.getCompNo(),
	                ptStkTakeItemId
	        };
	        
	        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
	}

	@Override
	public int insertPtIssHistory(String ptIssHistId, String issQty, String partGrade,
			PartAdjStkTakeDetailDTO partAdjStkTakeDetailDTO, User loginUser, String ptStkTakeItemId) {
	    QuerySqlBuffer query = new QuerySqlBuffer();
        

        query.append("INSERT INTO TAPTISSHIST (                                 		");
		 query.append("       comp_no,        ptisshist_id,                      		");
		 query.append("       ptiss_mode,     ptiss_type,            isslist_id, 		");
		 query.append("       dept_id,        wcode_id,              iss_date,   		");
		 query.append("       part_id,        part_grade,            iss_qty,    		");
		 query.append("       unit_price,     tot_price                          		");
		 query.append(")                                                         		");
		 query.append("SELECT y.comp_no,        ? ptisshistId,                     		");
		 query.append("       'C' ptissMode,  'STOCK' ptissType,     NULL isslistId,                 		");
		 query.append("       y.dept_Id,       y.wcode_Id,             convert(varchar, getdate(), 112),   		");
		 query.append("       x.part_id,      x.part_grade,           ? issQty,         		");
		 query.append("       z.last_price,   ISNULL(?, 0)*ISNULL(z.last_price, 0) totPrice  		");
		 query.append("FROM   TAPTSTKTAKEITEM x, TAPTSTKTAKELIST y, TAPARTS z		");
		 query.append("WHERE X.ptstktakelist_id = y.ptstktakelist_id           		");
		 query.append("AND x.part_id=z.part_id                               		");
		 query.append("AND  x.comp_no = ?                                      		");
		 query.append("  AND  x.ptstktakeitem_id = ?    		");



	        Object[] objects = new Object[] {
	        		ptIssHistId,
	                issQty,
	                issQty,
	                loginUser.getCompNo(),
	                ptStkTakeItemId
	        };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
	}

	@Override
	public int executeSpPtInStock(String compNo, String ptRecHistId) throws Exception {
	    QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("EXEC                                                  ");
        query.append("SP_PT_INSTOCK '"+compNo+"', '"+ptRecHistId+"' ;       ");
      
        this.getJdbcTemplate().execute(query.toString());
        
        return 0;
	}

	@Override
	public int executeSpPtOutStock(String compNo, String ptIssHistId) throws Exception {
	    QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("EXEC                                                  ");
        query.append("SP_PT_OUTSTOCK '"+compNo+"', '"+ptIssHistId+"' ;      ");
      
        this.getJdbcTemplate().execute(query.toString());
        
        return 0;
	}
	
	/**
     * detail update
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailDAO.java,v 1.0 2015/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param partAdjStkTakeDetailDTO
     * @return
     */
    public int confirmDetail(PartAdjStkTakeDetailDTO partAdjStkTakeDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAPTSTKTAKELIST SET			");	
    	query.append("	ptstktake_status	    = 'C',	");
    	query.append("	act_date				= convert(varchar, getdate(), 112)	");
    	query.append("WHERE ptstktakelist_id    = ?		");
    	query.append("  AND comp_no 		    = ?		");
    	
    	Object[] objects = new Object[] {
    			partAdjStkTakeDetailDTO.getPtStkTakeListId(),
    			user.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }

	@Override
	public int updateItemRecHistory(String ptRecHistId, String recQty, String partGrade, PartAdjStkTakeDetailDTO partAdjStkTakeDetailDTO, User loginUser, String ptStkTakeItemId) 
	{
	    QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("UPDATE TAPTSTKTAKEITEM SET            "); 
        query.append("  pt_rec_iss_type             = 'REC',    ");
        query.append("  ptrechist_id                = ? ");
        query.append("WHERE ptstktakeitem_id    = ?     ");
        query.append("  AND comp_no             = ?     ");
        
        Object[] objects = new Object[] {
                ptRecHistId,
                ptStkTakeItemId,
                loginUser.getCompNo()
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
	}

	@Override
	public int updateItemIssHistory(String ptIssHistId, String issQty, String partGrade, PartAdjStkTakeDetailDTO partAdjStkTakeDetailDTO, User loginUser, String ptStkTakeItemId) 
	{
	    QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("UPDATE TAPTSTKTAKEITEM SET            "); 
        query.append("  pt_rec_iss_type             = 'ISS',    ");
        query.append("  ptisshist_id                = ? ");
        query.append("WHERE ptstktakeitem_id    = ?     ");
        query.append("  AND comp_no             = ?     ");
        
        Object[] objects = new Object[] {
                ptIssHistId,
                ptStkTakeItemId,
                partAdjStkTakeDetailDTO.getCompNo()
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
	}
	
	public int setStatus(AppReqDetailDTO appReqDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TAPTSTKTAKELIST SET                    ");
        query.append("       ptstktake_status   = ?             ");
        query.append("WHERE  ptstktakelist_id       = ?             ");
        query.append("  AND comp_no             = ?     ");
        
        Object[] objects = new Object[] {
                appReqDetailDTO.getParentStatus(),
                appReqDetailDTO.getObjectId(),
                user.getCompNo()
        };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}