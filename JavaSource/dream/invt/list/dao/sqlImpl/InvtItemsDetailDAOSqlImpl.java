package dream.invt.list.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.invt.list.dao.InvtItemsDetailDAO;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.dto.InvtItemsDetailDTO;


/**
 * 상세 dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="invtItemsDetailDAOTarget"
 * @spring.txbn id="invtItemsDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class InvtItemsDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements InvtItemsDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: InvtItemsDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtItemsListDTO
     * @param invtCommonDTO
     * @return
     */
    public InvtItemsDetailDTO findDetail(InvtCommonDTO invtCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT 							");
        query.append("    x.invtitems_id	INVTITEMSID	");
        query.append("  , x.invtlist_id     INVTLISTID	");
        query.append("  , x.description     ITEMDESC	");
        query.append("  , x.price           AMT			");
        query.append("  , x.ord_no          ORDNO		");
        query.append("  , x.remark          REMARK		");
        query.append("FROM TAINVTITEMS x				");
        query.append("WHERE  1 = 1 						");
        query.append("  AND  invtitems_id 	= ?			");
        query.append("  AND  comp_no 		= ?			");
        
        Object[] objects = new Object[] {
        		  invtCommonDTO.getInvtItemsId()
        		, user.getCompNo()
        };
    
        InvtItemsDetailDTO invtItemsDetailDTO1 = 
        		(InvtItemsDetailDTO)getJdbcTemplate().query(query.toString(),objects, new MwareExtractor(new InvtItemsDetailDTO()));
        
        return invtItemsDetailDTO1;
    }
    /**
     * detail update
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param invtItemsDetailDTO
     * @param invtCommonDTO
     * @return
     */
    public int updateDetail(InvtItemsDetailDTO invtItemsDetailDTO, InvtCommonDTO invtCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("UPDATE TAINVTITEMS SET            ");
    	query.append("        description   = ?        	");
    	query.append("      , price         = ?        	");
    	query.append("      , ord_no        = ?        	");
    	query.append("      , remark        = ?        	");
    	query.append("WHERE invtitems_id    = ?     	");
    	query.append("  AND comp_no         = ?     	");
    	
    	Object[] objects = new Object[] {
    			  invtItemsDetailDTO.getItemDesc()
    			, invtItemsDetailDTO.getAmt()
    			, invtItemsDetailDTO.getOrdNo()
    			, invtItemsDetailDTO.getRemark()
    			, invtItemsDetailDTO.getInvtItemsId()
    			, user.getCompNo()
    	};
    	
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param invtItemsDetailDTO
     * @param invtCommonDTO
     * @return
     */
    public int insertDetail(InvtItemsDetailDTO invtItemsDetailDTO, InvtCommonDTO invtCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        int rtnValue  = 0;
    	
    	query.append("INSERT INTO TAINVTITEMS (                                	");
    	query.append("    comp_no       , invtitems_id      , invtlist_id		");
    	query.append("  , description   , price        		, ord_no            ");
    	query.append("  , remark												");
    	query.append("    )    VALUES     (                          			");
    	query.append("    ?             , ?                 , ?                	");
    	query.append("  , ?             , ?                 , ?     			");
    	query.append("  , ?              										");
    	query.append("    )                                          			");

    	Object[] objects = new Object[] {
    			  user.getCompNo()
    			, invtItemsDetailDTO.getInvtItemsId()
    			, invtCommonDTO.getInvtlistId()
    			, invtItemsDetailDTO.getItemDesc()
    			, invtItemsDetailDTO.getAmt()
    			, invtItemsDetailDTO.getOrdNo()
    			, invtItemsDetailDTO.getRemark()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	        
        return rtnValue;
    }


    public String copyDetail(String oldInvtId, String newInvtId, String oldKeyId, String newKeyId, User user) throws Exception {
    	
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("INSERT INTO TAINVTITEMS (                                	");
    	query.append("    comp_no       , invtitems_id      , invtlist_id		");
    	query.append("  , description   , price        		, ord_no            ");
    	query.append("  , remark										)       ");
    	query.append("SELECT													");
      	query.append("    comp_no             					");

    	if(!"".equals(newKeyId))
    	{	// Unit 복사인 경우
    		query.append("   , '"+newKeyId+"'   ");
    	}
    	else
    	{	// 전체 복사인 경우
    		query.append("   , NEXT VALUE FOR sqainvtitems_id   	");
    	}
    	query.append("  , ?														");
    	query.append("  , description   , price        		, ord_no            ");
    	query.append("  , remark										        ");
    	query.append("FROM TAINVTITEMS 											");
    	query.append("WHERE comp_no 	= ?										");
    	query.append("  AND invtlist_id = ?										");
    	
    	// Unit 복사인 경우 Key 값을 넣어 하나만 복사한다.
    	query.getAndQuery("invtitems_id", oldKeyId);
    	
    	Object[] objects = new Object[] {
    			  newInvtId
    			, user.getCompNo()
    			, oldInvtId
    	};
    	
    	getJdbcTemplate().update(query.toString(), objects);
    	
		return "0";
	}

}