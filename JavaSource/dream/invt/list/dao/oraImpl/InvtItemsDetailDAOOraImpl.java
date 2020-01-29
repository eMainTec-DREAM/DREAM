package dream.invt.list.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
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
public class InvtItemsDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements InvtItemsDetailDAO
{
    /**
     * detail find
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param invtItemsListDTO
     * @param invtCommonDTO
     * @return
     */
    public InvtItemsDetailDTO findDetail(InvtCommonDTO invtCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT 							    ");
        query.append("    x.invtitems_id	INVTITEMSID	    ");
        query.append("  , x.invtlist_id     INVTLISTID	    ");
        query.append("  , x.description     ITEMDESC	    ");
        query.append("  , x.price           AMT			    ");
        query.append("  , x.ord_no          ORDNO		    ");
        query.append("  , x.remark          REMARK		    ");
        query.append("  , x.equip_id equipId                ");
        query.append("  , (SELECT y.description             ");
        query.append("     FROM   TAEQUIPMENT y             ");
        query.append("     WHERE  x.equip_id = y.equip_id   ");
        query.append("       AND  x.comp_no = y.comp_no     ");
        query.append("     ) equipDesc                      ");
        query.append("FROM TAINVTITEMS x				    ");
        query.append("WHERE  1 = 1 						    ");
        query.append("  AND  invtitems_id 	= ?			    ");
        query.append("  AND  comp_no 		= ?			    ");
        
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
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAINVTITEMS SET            ");
    	query.append("        description   = ?        	");
    	query.append("      , price         = ?        	");
    	query.append("      , ord_no        = ?        	");
    	query.append("      , remark        = ?        	");
    	query.append("      , equip_id      = ?            ");
    	query.append("WHERE invtitems_id    = ?     	");
    	query.append("  AND comp_no         = ?     	");
    	
    	Object[] objects = new Object[] {
    			  invtItemsDetailDTO.getItemDesc()
    			, invtItemsDetailDTO.getAmt()
    			, invtItemsDetailDTO.getOrdNo()
    			, invtItemsDetailDTO.getRemark()
    			, invtItemsDetailDTO.getEquipId()
    			, invtItemsDetailDTO.getInvtItemsId()
    			, user.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
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
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TAINVTITEMS (                                	");
    	query.append("    comp_no       , invtitems_id      , invtlist_id		");
    	query.append("  , description   , price        		, ord_no            ");
    	query.append("  , remark		, equip_id					    		");
    	query.append("    )    VALUES     (                          			");
    	query.append("    ?             , ?                 , ?                	");
    	query.append("  , ?             , ?                 , ?     			");
    	query.append("  , ?             , ?										");
    	query.append("    )                                          			");

    	Object[] objects = new Object[] {
    			  user.getCompNo()
    			, invtItemsDetailDTO.getInvtItemsId()
    			, invtCommonDTO.getInvtlistId()
    			, invtItemsDetailDTO.getItemDesc()
    			, invtItemsDetailDTO.getAmt()
    			, invtItemsDetailDTO.getOrdNo()
    			, invtItemsDetailDTO.getRemark()
    			, invtItemsDetailDTO.getEquipId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }

    public String copyDetail(String oldInvtId, String newInvtId, String oldKeyId, String newKeyId, User user) throws Exception {
    	
    	QueryBuffer query = new QueryBuffer();
    	
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
    		query.append("   , sqainvtitems_id.NEXTVAL   	");
    	}
    	query.append("  , ?														");
    	query.append("  , description||'-Copied'   , price  , ord_no            ");
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