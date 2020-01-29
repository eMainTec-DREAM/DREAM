package dream.part.adj.stktake.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.CommonUtil;
import common.util.QuerySqlBuffer;
import dream.part.adj.stktake.dao.PartAdjStkTakeItemDetailDAO;
import dream.part.adj.stktake.dto.PartAdjStkTakeCommonDTO;
import dream.part.adj.stktake.dto.PartAdjStkTakeItemDetailDTO;
import dream.part.adj.stktake.dto.PartAdjStkTakeListDTO;

/**
 * 부품실사 item 상세 dao
 * @author  kim21017
 * @version $Id: PartAdjStkTakeDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="partAdjStkTakeItemDetailDAOTarget"
 * @spring.txbn id="partAdjStkTakeItemDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PartAdjStkTakeItemDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements PartAdjStkTakeItemDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param partAdjStkTakeListDTO
     * @param partAdjStkTakeCommonDTO
     * @return
     */
    public PartAdjStkTakeItemDetailDTO findDetail(PartAdjStkTakeListDTO partAdjStkTakeListDTO, PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                                                                                     ");
        query.append("        x.ptstktakeitem_id                                                                              AS ptStkTakeItemId ");
        query.append("      , x.ptstktakelist_id                                                                              AS ptStkTakeListId ");
        query.append("      , (SELECT z.ptstktake_status FROM TAPTSTKTAKELIST z WHERE x.ptstktakelist_id= z.ptstktakelist_id) AS ptStkTakeStatus ");
        query.append("      , x.part_id                                                                                       AS partId          ");
        query.append("      , y.part_no                                                                                       AS partNo          ");
        query.append("      , dbo.SFACODE_TO_DESC(x.part_grade,'PART_GRADE','SYS','','"+user.getLangId()+"')                  AS partGradeDesc   ");
        query.append("      , y.description                                                                                   AS partDesc        ");
        query.append("      , x.part_grade                                                                                    AS partGrade       ");
        query.append("      , y.pt_size                                                                                       AS ptSize          ");
        query.append("      , y.model                                                                                         AS model           ");
        query.append("      , CONVERT( INT, x.stock_qty )                                                                     AS sstockQty       ");
        query.append("      , CONVERT( INT, x.real_qty )                                                                      AS realQty         ");
        query.append("      , CONVERT( INT, x.gap_qty )                                                                       AS gapQty          ");
        query.append("      , x.remark                                                                                        AS remark          ");
        query.append("      , y.maker                                                                                         AS MAKER       	 ");
        query.append("  FROM TAPTSTKTAKEITEM x                                                                                                   ");
        query.append(" INNER JOIN TAPARTS y                                                                                                      ");
        query.append("         ON x.part_id = y.part_id                                                                                          ");
        query.append("        AND x.comp_no = y.comp_no                                                                                          ");
        query.append(" WHERE x.comp_no = '"+user.getCompNo()+"'                                                                                  ");
        query.getAndQuery("x.ptstktakelist_id", partAdjStkTakeCommonDTO.getPtStkTakeListId());
        query.getAndQuery("x.ptstktakeitem_id", partAdjStkTakeListDTO.getPtStkTakeItemId());
        
        PartAdjStkTakeItemDetailDTO partAdjStkTakeItemDetailDTO1 = 
        		(PartAdjStkTakeItemDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new PartAdjStkTakeItemDetailDTO()));
        
        return partAdjStkTakeItemDetailDTO1;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param partAdjStkTakeItemDetailDTO
     * @param partAdjStkTakeCommonDTO
     * @return
     */
    public int updateDetail(PartAdjStkTakeItemDetailDTO partAdjStkTakeItemDetailDTO, PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAPTSTKTAKEITEM SET	    	");
    	query.append("	  part_id				= ?			");
    	query.append("	, part_grade			= ?			");
    	query.append("	, real_qty				= ?			");
    	query.append("	, remark				= ?			");
    	query.append("WHERE ptstktakelist_id 		= ?		");
    	query.append("  AND ptstktakeitem_id		= ? 	");
    	query.append("  AND comp_no					= ? 	");
    	
    	Object[] objects = new Object[] {
    			partAdjStkTakeItemDetailDTO.getPartId(),
    			partAdjStkTakeItemDetailDTO.getPartGrade(),
    			CommonUtil.getRowMoneyToNum(partAdjStkTakeItemDetailDTO.getRealQty()),
    			partAdjStkTakeItemDetailDTO.getRemark(),
    			partAdjStkTakeCommonDTO.getPtStkTakeListId(),
    			partAdjStkTakeItemDetailDTO.getPtStkTakeItemId(),
    			user.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: PartAdjStkTakeDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param partAdjStkTakeItemDetailDTO
     * @param partAdjStkTakeCommonDTO
     * @return
     */
    public int insertDetail(PartAdjStkTakeItemDetailDTO partAdjStkTakeItemDetailDTO, PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TAPTSTKTAKEITEM (								");
    	query.append("	comp_no,		ptstktakeitem_id,	ptstktakelist_id,		");
    	query.append("	part_id,	    part_grade,		    real_qty,		");
    	query.append("	remark   		");
    	query.append("	)	VALUES				(							");
    	query.append("	?,				?,					?,				");
    	query.append("	?,				?,					?,				");
    	query.append("	?													");
    	query.append("	)													");
    	
    	Object[] objects = new Object[] {
    			user.getCompNo(),
    			partAdjStkTakeItemDetailDTO.getPtStkTakeItemId(),
    			partAdjStkTakeCommonDTO.getPtStkTakeListId(),
    			partAdjStkTakeItemDetailDTO.getPartId(),
    			partAdjStkTakeItemDetailDTO.getPartGrade(),
    			CommonUtil.getRowMoneyToNum(partAdjStkTakeItemDetailDTO.getRealQty()),
    			partAdjStkTakeItemDetailDTO.getRemark()
    			
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
    }

	public int insertGap(PartAdjStkTakeItemDetailDTO partAdjStkTakeItemDetailDTO,PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, User user)
	{
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAPTSTKTAKEITEM SET                																	");
    	query.append("    stock_qty		=        			 ISNULL(( SELECT a.stock_qty                                     		");
    	query.append("                                                FROM TAPTSTOCK a                                           	");
    	query.append("                                                WHERE a.part_id = ?                                			");
    	query.append("                                                 AND a.part_grade = ?                                 		");
    	query.append("                                                  AND a.comp_no = ?                                			");
    	query.append("                                                   AND a.wcode_id = ( SELECT z.wcode_id                       ");
    	query.append("                                                   				    FROM TAPTSTKTAKELIST z                  ");
    	query.append("                                                   				    WHERE z.comp_no = ?                     ");
    	query.append("                                                   				     AND z.ptstktakelist_id = ? ) ), 0)		");
    	query.append("    , gap_qty		=		( real_qty - ISNULL(( SELECT a.stock_qty                                     		");
    	query.append("                                                FROM TAPTSTOCK a                                      		");
    	query.append("                                                WHERE a.part_id = ?                                			");
    	query.append("                                                 AND a.part_grade = ?                                			");
    	query.append("                                                  AND a.comp_no = ?                                			");
    	query.append("                                                   AND a.wcode_id = ( SELECT z.wcode_id                       ");
    	query.append("                                                      			    FROM TAPTSTKTAKELIST z                  ");
    	query.append("                                                      			    WHERE z.comp_no = ?                     ");
    	query.append("                                                      			     AND z.ptstktakelist_id = ? ) ),0) )    ");
    	query.append("WHERE ptstktakeitem_id         = ?     																		");
    	
    	Object[] objects = new Object[] {
    	        partAdjStkTakeItemDetailDTO.getPartId()
    	      , partAdjStkTakeItemDetailDTO.getPartGrade()
    	      , user.getCompNo()
    	      , user.getCompNo()
    	      , partAdjStkTakeCommonDTO.getPtStkTakeListId()    	      
    	      , partAdjStkTakeItemDetailDTO.getPartId()
    	      , partAdjStkTakeItemDetailDTO.getPartGrade()
    	      , user.getCompNo()
    	      , user.getCompNo()
    	      , partAdjStkTakeCommonDTO.getPtStkTakeListId()
    	      , partAdjStkTakeItemDetailDTO.getPtStkTakeItemId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
	}
	

	@Override
	public int insertDefaultItem(PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, User user)
	{
    	QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("INSERT INTO TAPTSTKTAKEITEM	(               	");
		query.append("		 comp_no			,ptstktakeitem_id		");
		query.append("		,ptstktakelist_id	,part_id				");
		query.append("		,part_grade			,stock_qty 				");
		query.append("		,real_qty			,gap_qty    	    	");
		query.append(" )                                	            ");
		query.append("SELECT                      						");
		query.append("      x.comp_no			,NEXT VALUE FOR sqaptstktakeitem_id	");
		query.append("		,?					,x.part_id      	    ");
		query.append("		,x.part_grade		,x.STOCK_QTY			");
		query.append("		,x.STOCK_QTY		,0						");
		query.append("FROM taptstock x INNER JOIN TAPARTS y      		");
		query.append("ON   x.comp_no = y.comp_no                        ");
		query.append(" AND x.part_id = y.part_id						");
		query.append("WHERE 1=1            								");
		query.append(" AND x.comp_no = ?								");
		query.append(" AND x.wcode_id = ( SELECT aa.wcode_id 			");
		query.append(" 				   	  FROM TAPTSTKTAKELIST aa 		");
		query.append(" 				   	  WHERE comp_no = ? 			");
		query.append(" 				   	   AND ptstktakelist_id = ?		");
		query.append("					 )								");
		query.getAndQuery("y.is_stock_control", "Y");
		query.getAndQuery("y.part_categ", "SPPT");

		Object[] objects = new Object[] {
			 partAdjStkTakeCommonDTO.getPtStkTakeListId()
			, user.getCompNo() 
			, user.getCompNo() 
			, partAdjStkTakeCommonDTO.getPtStkTakeListId()
		};
		
		return this.getJdbcTemplate().update(query.toString(), objects);
	}
	@Override
	public String validItem(PartAdjStkTakeItemDetailDTO partAdjStkTakeItemDetailDTO, PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, User user)
	{
    	QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("SELECT                    ");
		query.append("       COUNT(1)           ");
		query.append("FROM TAPTSTKTAKEITEM x			");
		query.append("WHERE 1=1					");
		query.getAndQuery("x.comp_no", user.getCompNo());
		query.getInequalityQuery("x.ptstktakeitem_id", "!=", partAdjStkTakeItemDetailDTO.getPtStkTakeItemId() );
		query.getAndQuery("x.part_id", partAdjStkTakeItemDetailDTO.getPartId());
		query.getAndQuery("x.part_grade", partAdjStkTakeItemDetailDTO.getPartGrade());
		query.getAndQuery("x.ptstktakelist_id", partAdjStkTakeCommonDTO.getPtStkTakeListId());

		return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
	}
    
}