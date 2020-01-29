package dream.mgr.cal.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.mgr.cal.dao.MgrCalCompWkrcalDetailDAO;
import dream.mgr.cal.dto.MgrCalCompWkrcalCommonDTO;
import dream.mgr.cal.dto.MgrCalCompWkrcalDetailDTO;

/**
 * 근무일달력설정 - 상세 dao
 *
 * @author euna0207
 * @version $Id: MgrCalCompWkrcalDetailDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
 * @since 1.0
 * @spring.bean id="mgrCalCompWkrcalDetailDAOTarget"
 * @spring.txbn id="mgrCalCompWkrcalDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrCalCompWkrcalDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MgrCalCompWkrcalDetailDAO
{
    /**
     * detail find
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalDetailDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
     * @since   1.0
     *
     * @param buttonId
     * @return
     */
    public MgrCalCompWkrcalDetailDTO findDetail(MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT								        		");
        query.append("       x.comp_no						compNo,     	");
        query.append("       (SELECT description 	        				");
        query.append("       	FROM TACOMP 	        					");
        query.append("         WHERE comp_no = x.comp_no) 	compDesc,		");
        query.append("       x.plant						plantNo,		");
        query.append("       (SELECT description 	        				");
        query.append("       	FROM TAPLANT	        					");
        query.append("         WHERE plant = x.plant  						");
        query.append("       	 AND comp_no = x.comp_no) 	plantDesc,		");
        query.append("       x.wrkcallist_no				wrkcalListNo,	");
        query.append("       x.wrkcallist_id				wrkcalListId,	");
        query.append("       x.description					wrkcalListDesc,	");
        query.append("       x.is_use						isUse,			");
        query.append("       x.remark						remark			");
        query.append("FROM   TAWRKCALLIST x						    		");
        query.append("WHERE  x.wrkcallist_id = '"+mgrCalCompWkrcalCommonDTO.getWrkcalListId()+"'	");
        query.append("       and   x.comp_No = '"+user.getCompNo()+"'	");

        MgrCalCompWkrcalDetailDTO mgrCalCompWkrcalDetailDTO =
        		(MgrCalCompWkrcalDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MgrCalCompWkrcalDetailDTO()));

        return mgrCalCompWkrcalDetailDTO;
    }
    /**
     * detail insert
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalDetailDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrCalCompWkrcalDetailDTO
     * @return
     */
    public int insertDetail(MgrCalCompWkrcalDetailDTO mgrCalCompWkrcalDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAWRKCALLIST						");
    	query.append("	(comp_no,		WRKCALLIST_ID,	plant,      ");
    	query.append("	 WRKCALLIST_NO,	description, 	IS_USE,     ");
    	query.append("	 REMARK      								");
    	query.append("	)	VALUES									");
    	query.append("	(?,				?,				?,			");
    	query.append("	 ?,				?,				?,			");
    	query.append("	 ?											");
    	query.append("	)											");

    	Object[] objects = new Object[] {
    			user.getCompNo(),
    			mgrCalCompWkrcalDetailDTO.getWrkcalListId(),
    			mgrCalCompWkrcalDetailDTO.getPlantNo(),
    			mgrCalCompWkrcalDetailDTO.getWrkcalListNo(),
    			mgrCalCompWkrcalDetailDTO.getWrkcalListDesc(),
    			mgrCalCompWkrcalDetailDTO.getIsUse(),
    			mgrCalCompWkrcalDetailDTO.getRemark()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    /**
     * detail update
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalDetailDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrCalCompWkrcalDetailDTO
     * @return
     */
    public int updateDetail(MgrCalCompWkrcalDetailDTO mgrCalCompWkrcalDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAWRKCALLIST SET	        ");
    	query.append("	 WRKCALLIST_NO	        = ? 	");
    	query.append("	,description	        = ? 	");
    	query.append("	,IS_USE			        = ? 	");
    	query.append("	,PLANT			        = ? 	");
    	query.append("	,REMARK			        = ? 	");
    	query.append("WHERE comp_no 	        = ?		");
    	query.append("  AND WRKCALLIST_ID 		= ?		");

    	Object[] objects = new Object[] {
    			mgrCalCompWkrcalDetailDTO.getWrkcalListNo()
    			,mgrCalCompWkrcalDetailDTO.getWrkcalListDesc()
    			,mgrCalCompWkrcalDetailDTO.getIsUse()
    			,mgrCalCompWkrcalDetailDTO.getPlantNo()
    			,mgrCalCompWkrcalDetailDTO.getRemark()
    			,user.getCompNo()
    			,mgrCalCompWkrcalDetailDTO.getWrkcalListId()
    	};

    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
	@Override
	public void SP_SETDEFAULT_WRKCAL_BYONE(MgrCalCompWkrcalDetailDTO mgrCalCompWkrcalDetailDTO, User user) throws Exception {

    	QueryBuffer query = new QueryBuffer();
    	
        query.append("begin                                                     																	");
        query.append("SP_SETDEFAULT_WRKCAL_BYONE('"+user.getCompNo()+"','"+user.getUserNo()+"', '"+mgrCalCompWkrcalDetailDTO.getWrkcalListId()+"');  ");
        query.append("end;                                                      																	");
        
        this.getJdbcTemplate().execute(query.toString());
        
	}
}