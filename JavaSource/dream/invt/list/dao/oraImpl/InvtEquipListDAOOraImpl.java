package dream.invt.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.invt.list.dao.InvtEquipListDAO;
import dream.invt.list.dto.InvtCommonDTO;


/**
 * ¸ñ·Ï dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="invtEquipListDAOTarget"
 * @spring.txbn id="invtEquipListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class InvtEquipListDAOOraImpl extends BaseJdbcDaoSupportOra implements InvtEquipListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param invtCommonDTO
     * @param invtEquipListDTO
     * @return List
     */
    public List findList(InvtCommonDTO invtCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT 										");
        query.append("    '' 							seqNo		");
        query.append("  , '' 							isDelCheck	");
        query.append("  , x.invtlist_id					INVTLISTID	");
        query.append("  , x.invtequip_id				INVTEQUIPID	");
        query.append("  , x.equip_id 					EQUIPID		");
        query.append("	, (SELECT param1 FROM TACDSYSD WHERE list_type='EQCTG_TYPE' AND cdsysd_no = y.eqctg_type) 	  PARAM			");
        query.append("  , y.eqctg_type					EQCTGTYPE	");
        query.append("  , y.item_no       				ITEMNO		");
        query.append("  , y.description     			EQUIPDESC	");
        query.append("  , (SELECT a.full_desc FROM TAEQLOC a WHERE a.comp_no = y.comp_no AND a.eqloc_id = y.eqloc_id) EQLOCDESC		");
        query.append("  , (SELECT a.full_desc FROM TAEQCTG a WHERE a.comp_no = y.comp_no AND a.eqctg_id = y.eqctg_id) EQCTGDESC		");
        query.append("  , y.model_no 					MODEL		");
        query.append("  , y.maker 						MAKER		");
        query.append("  , y.setup_date 					SETUPDATE	");
        query.append("FROM TAINVTEQUIP x INNER JOIN TAEQUIPMENT y	");
        query.append("ON x.comp_no = y.comp_no 						");
        query.append("AND x.equip_id = y.equip_id					");
        query.append("WHERE  1 = 1 									");
        query.append(this.getWhere(invtCommonDTO, user));
        query.getOrderByQuery("y.item_no", invtCommonDTO.getOrderBy(), invtCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(invtCommonDTO.getIsLoadMaxCount(), invtCommonDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param deleteRow
     * @param deleteRowsExt 
     * @return
     */
    public int deleteList(String deleteRow, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	String id = deleteRow;
    	
    	query.append("DELETE FROM TAINVTEQUIP				");
    	query.append("WHERE invtequip_id 	= '" + id + "'	");
    	query.getAndQuery("comp_no", user.getCompNo());
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(InvtCommonDTO invtCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.getAndQuery("x.comp_no", user.getCompNo());
    	query.getAndNumKeyQuery("x.invtlist_id", invtCommonDTO.getInvtlistId());
    	
    	if (!"".equals(invtCommonDTO.getEquipId()))
        {
            query.getAndQuery("x.equip_id", invtCommonDTO.getEquipId());
            return query.toString();
        }
    	
    	return query.toString();
    }

	@Override
	public String findTotalCount(InvtCommonDTO invtCommonDTO, User user) throws Exception {

		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT                    					");
        query.append("       COUNT(1)           					");
        query.append("FROM TAINVTEQUIP x INNER JOIN TAEQUIPMENT y	");
        query.append("ON x.comp_no = y.comp_no 						");
        query.append("AND x.equip_id = y.equip_id					");
        query.append("WHERE  1 = 1 									");
        query.append(this.getWhere(invtCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
	}

	public String validEquip(InvtCommonDTO invtCommonDTO, User user) throws Exception
	{
    	QueryBuffer query = new QueryBuffer();

    	query.append("SELECT count(*) 			");
    	query.append("FROM TAINVTEQUIP			");
    	query.append("WHERE 1=1					");
    	query.getAndQuery("comp_no", user.getCompNo());
    	query.getAndQuery("invtlist_id", invtCommonDTO.getInvtlistId());
    	query.getAndQuery("equip_id", invtCommonDTO.getEquipId());
    	
    	return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));

	}
	
	public int insertNewEqList(InvtCommonDTO invtCommonDTO, User user) throws Exception 
	{
		QueryBuffer query = new QueryBuffer();
		
		int rtnValue  = 0;
		
		query.append("INSERT INTO TAINVTEQUIP(  ");
        query.append("    comp_no               ");
        query.append("  , invtequip_id          ");
        query.append("  , invtlist_id           ");
        query.append("  , equip_id              ");
        query.append(")VALUES(                  ");
        query.append("    ?                     ");
        query.append("  , ?                     ");
        query.append("  , ?                     ");
        query.append("  , ?                     ");
        query.append(" )                        ");
  
        Object[] objects = new Object[] {
                user.getCompNo()
              , invtCommonDTO.getInvtEquipId()
              , invtCommonDTO.getInvtlistId()
              , invtCommonDTO.getEquipId()
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
	}
}