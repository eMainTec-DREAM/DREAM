package dream.part.list.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.part.list.dao.PartListSafQtyDetailDAO;
import dream.part.list.dto.PartListSafQtyDetailDTO;
import dream.part.list.dto.PartListSafQtyListDTO;
/**
 * 부품창고 보관위치 - Detail DAO implements
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="partListSafQtyDetailDAOTarget"
 * @spring.txbn id="partListSafQtyDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PartListSafQtyDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements PartListSafQtyDetailDAO
{
	
    public PartListSafQtyDetailDTO findPtWhEmpDetail(PartListSafQtyListDTO partListSafQtyListDTO, User user) 
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	 query.append("SELECT                                                                        ");
         query.append("       ''                                                    AS seqNo         ");
         query.append("       ,''                                                   AS ISDELCHECK    ");
         query.append("       ,(SELECT a.wname        												 ");
         query.append("       FROM tawarehouse a        											 ");
         query.append("       WHERE x.comp_no=A.COMP_NO AND X.WCODE_ID=A.WCODE_ID)  AS wname     	 ");
         query.append("       ,X.SAFTY_QTY                                          AS minSaftyQty   ");
         query.append("       ,X.MAX_SAFTY_QTY                                      AS maxSaftyQty   ");
         query.append("       ,x.WCODE_ID                                           AS wCodeId		 ");
         query.append("       ,x.PART_ID                                            AS partId		 ");
         query.append("FROM taptsaftystock x                                                         ");
         query.append("WHERE 1=1        															 ");
         query.append("AND x.comp_no=?                      										 ");
         query.append(" AND x.PART_ID = ?															 ");


         Object[] objects = new Object[] {
                 user.getCompNo()
                 ,partListSafQtyListDTO.getPartId()
     	};
     
        PartListSafQtyDetailDTO partListSafQtyDetailDTO = 
        		(PartListSafQtyDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new PartListSafQtyDetailDTO()));
        
        return partListSafQtyDetailDTO;
        
    }
    

    public int insertPtWhEmpDetail(PartListSafQtyDetailDTO partListSafQtyDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;

        query.append("MERGE INTO TAPTSAFTYSTOCK a                                           ");
    	query.append("USING(    SELECT     ?                             comp_no            ");
    	query.append("                    ,?                             safty_qty          ");
    	query.append("                    ,?                             max_safty_qty		");
    	query.append("                    ,?                             part_id        	");
    	query.append("                    ,?                             wcode_id           ");
    	query.append("            ) b  			                                          	");
    	query.append("ON(    a.comp_no = b.comp_no                                        	");
    	query.append("    AND a.part_id = b.part_id											");
    	query.append("    AND a.wcode_id = b.wcode_id     )                                 ");
    	
    	query.append("WHEN MATCHED THEN                                                    	");
    	query.append("    UPDATE SET   a.safty_qty = b.safty_qty                            ");
    	query.append("                ,a.max_safty_qty = b.max_safty_qty                    ");
    	
    	query.append("WHEN NOT MATCHED THEN                                                 ");
    	query.append("    INSERT (comp_no		,safty_qty		,max_safty_qty        		");
    	query.append("            ,part_id		,wcode_id         						)   ");
    	query.append("    VALUES (b.comp_no     ,b.safty_qty	,b.max_safty_qty        	");
    	query.append("            ,b.part_id    ,b.wcode_id           					);  ");

    	
    	Object[] objects = new Object[] {
    			 user.getCompNo()
    			 ,partListSafQtyDetailDTO.getMinSaftyQty()
    			 ,partListSafQtyDetailDTO.getMaxSaftyQty()
    			 ,partListSafQtyDetailDTO.getPartId()
    			 ,partListSafQtyDetailDTO.getWcodeId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
        return rtnValue;
    }
    
    
    
    public int updatePtWhEmpDetail(PartListSafQtyDetailDTO partListSafQtyDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        int rtnValue  = 0;

        query.append("UPDATE taptsaftystock SET                		");
    	query.append("    SAFTY_QTY             = ?   				");
    	query.append("    ,MAX_SAFTY_QTY        = ?   				");
    	query.append("	  ,WCODE_ID         	= ?         		");
    	query.append("    ,PART_ID              = ?   				");
    	query.append("WHERE comp_no 			= ?					");
        query.append(" AND PART_ID	 			= ?					");

    	Object[] objects = new Object[] {
    			partListSafQtyDetailDTO.getMinSaftyQty()
    			,partListSafQtyDetailDTO.getMaxSaftyQty()
    			,partListSafQtyDetailDTO.getWcodeId()
    			,partListSafQtyDetailDTO.getPartId()
    			,user.getCompNo()
    			,partListSafQtyDetailDTO.getPartId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
        return rtnValue;
    }


	@Override
	public String validEmpNo(PartListSafQtyListDTO partListSafQtyListDTO, PartListSafQtyDetailDTO partListSafQtyDetailDTO, User user)
	{
		QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT COUNT(*)                 		");
        query.append("FROM   taptsaftystock              		");
        query.append("WHERE 1=1								");
        query.append(" AND comp_no	 		= ?				");
        query.append(" AND WCODE_ID 		= ?				");

        
    	Object[] objects = new Object[] {
    			user.getCompNo()
    			,partListSafQtyDetailDTO.getWcodeId()
    	};
    	   
    	return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), objects));
	}
}