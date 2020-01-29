package dream.part.adj.stktake.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.part.adj.stktake.dao.PartAdjStkTakeItemListDAO;
import dream.part.adj.stktake.dto.PartAdjStkTakeCommonDTO;
import dream.part.adj.stktake.dto.PartAdjStkTakeListDTO;

/**
 * 부품실사 item 목록 dao
 * @author  kim21017
 * @version $Id: PartAdjStkTakeListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="partAdjStkTakeItemListDAOTarget"
 * @spring.txbn id="partAdjStkTakeItemListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PartAdjStkTakeItemListDAOSqlImpl extends BaseJdbcDaoSupportSql implements PartAdjStkTakeItemListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: PartAdjStkTakeListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param partAdjStkTakeCommonDTO
     * @param partAdjStkTakeListDTO
     * @return List
     */
    public List findItemList(PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, PartAdjStkTakeListDTO partAdjStkTakeListDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                                                                    ");
        query.append("        ''                                                                             AS seqNo           ");
        query.append("      , ''                                                                             AS isDelCheck      ");
        query.append("      , y.part_no                                                                      AS partNo          ");
        query.append("      , y.description                                                                  AS partDesc        ");
        query.append("      , y.pt_size                                                                      AS ptSize          ");
        query.append("      , y.model                                                                        AS model           ");
        query.append("      , dbo.SFACODE_TO_DESC(x.part_grade,'PART_GRADE','SYS','','"+user.getLangId()+"') AS ptGrade         ");
        query.append("      , CONVERT( INT, x.stock_qty )                                                    AS stockQty        ");
        query.append("      , CONVERT( INT, x.real_qty )                                                     AS realQty         ");
        query.append("      , CONVERT( INT, x.gap_qty )                                                      AS gapQty          ");
        query.append("      , x.remark                                                                       AS remark          ");
        query.append("      , y.maker                                                                        AS MAKER       	");
        query.append("      , x.part_id                                                                      AS partId          ");
        query.append("      , x.ptstktakeitem_id                                                             AS ptStkTakeItemId ");
        query.append("      , (SELECT ptstktakelist_no													 						");
        query.append("         FROM TAPTSTKTAKELIST 													 						");
        query.append("         WHERE comp_no=x.comp_no													 						");
        query.append("          AND ptstktakelist_id=x.ptstktakelist_id									 						");
        query.append("        )                                                          				 	 AS ptStkTakeListNo ");
        query.append("  FROM TAPTSTKTAKEITEM x                                                                                  ");
        query.append(" INNER JOIN TAPARTS y                                                                                     ");
        query.append("         ON x.part_id = y.part_id                                                                         ");
        query.append("        AND x.comp_no = y.comp_no                                                                         ");
        query.append(" WHERE 1=1                                                                                                ");
        query.append(this.getWhere(partAdjStkTakeCommonDTO, partAdjStkTakeListDTO, user));
        query.getOrderByQuery("x.ptstktakelist_id","x.ptstktakeitem_id DESC", partAdjStkTakeListDTO.getOrderBy(), partAdjStkTakeListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(partAdjStkTakeListDTO.getIsLoadMaxCount(), partAdjStkTakeListDTO.getFirstRow()));

    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: PartAdjStkTakeListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @param deleteRowsExt 
     * @return
     */
    public int deleteItemList(String id, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("DELETE FROM TAPTSTKTAKEITEM      ");
    	query.append("      WHERE comp_no 	       = ? ");
    	query.append("        AND ptstktakeitem_id = ? ");
    	
    	 Object[] objects = new Object[] {   
                 user.getCompNo()
                 ,id
         };
    	 
    	return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    private String getWhere(PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, PartAdjStkTakeListDTO partAdjStkTakeListDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	query.getAndQuery("x.comp_no", user.getCompNo());
    	query.getAndQuery("x.ptstktakelist_id", partAdjStkTakeCommonDTO.getPtStkTakeListId());
    	if (!"".equals(partAdjStkTakeListDTO.getPtStkTakeItemId()))
        {
            query.getAndQuery("x.ptstktakeitem_id", partAdjStkTakeListDTO.getPtStkTakeItemId());
            return query.toString();
        }
    	
    	return query.toString();
    }

	@Override
	public String findTotalCount(PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO,
			PartAdjStkTakeListDTO partAdjStkTakeListDTO, User user) throws Exception {

        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = partAdjStkTakeCommonDTO.getCompNo();
        query.append("SELECT                                ");
        query.append("       COUNT(1)                       ");
        query.append("FROM TAPTSTKTAKEITEM x, TAPARTS y		");
        query.append("WHERE x.part_id=y.part_id             ");
        query.append("  AND x.comp_no =y.comp_no            ");
        query.append("  AND x.comp_no = '"+compNo+"'        ");
        query.append(this.getWhere(partAdjStkTakeCommonDTO,partAdjStkTakeListDTO, user));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);

	}

	@Override
	public String getData(User user)
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
        query.append("SELECT											");
		query.append("    CASE WHEN (SELECT COUNT(x.exceltab_id) 		");
		query.append("               FROM TAEXCELTAB x					");
		query.append("               WHERE exceltab_no = ?) = 0			");
		query.append("         THEN '0' 								");
		query.append("         ELSE (SELECT 							");
		query.append("                CONVERT(VARCHAR,x.exceltab_id) + ',' + x.description + ',' + x.table_name	");
		query.append("            	 FROM TAEXCELTAB x 					");
		query.append("            	 WHERE x.exceltab_no = ? )			");
		query.append("    END											");
		
        Object[] objects = new Object[] {
        		"PTSTKTAKE"
        		,"PTSTKTAKE"
        };

		return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
	}
}
