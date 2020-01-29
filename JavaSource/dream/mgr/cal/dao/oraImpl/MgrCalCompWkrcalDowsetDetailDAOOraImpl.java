package dream.mgr.cal.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.mgr.cal.dao.MgrCalCompWkrcalDowsetDetailDAO;
import dream.mgr.cal.dto.MgrCalCompWkrcalCommonDTO;
import dream.mgr.cal.dto.MgrCalCompWkrcalDowsetDetailDTO;

/**
 * 휴무요일설정 - 상세 dao
 *
 * @author euna0207
 * @version $Id: MgrCalCompWkrcalDowsetDetailDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
 * @since 1.0
 * @spring.bean id="mgrCalCompWkrcalDowsetDetailDAOTarget"
 * @spring.txbn id="mgrCalCompWkrcalDowsetDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrCalCompWkrcalDowsetDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MgrCalCompWkrcalDowsetDetailDAO
{
    /**
     * detail find
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalDowsetDetailDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
     * @since   1.0
     *
     * @param buttonId
     * @return
     */
    public MgrCalCompWkrcalDowsetDetailDTO findDetail(String wrkcalDowsetId, String lang)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT								        					");
        query.append("       x.dow							dow,    					");
        query.append("		 SFACODE_TO_DESC(x.dow,'DOW','SYS','','"+lang+"') dowDesc,	");
        query.append("       x.is_off						isOff,						");
        query.append("       x.wrkcaldowset_id				wrkcalDowsetId				");
        query.append("FROM   TAWRKCALDOWSET x											");
        query.append("WHERE  x.wrkcaldowset_id = '"+wrkcalDowsetId+"'					");

        MgrCalCompWkrcalDowsetDetailDTO mgrCalCompWkrcalDowsetDetailDTO =
        		(MgrCalCompWkrcalDowsetDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MgrCalCompWkrcalDowsetDetailDTO()));

        return mgrCalCompWkrcalDowsetDetailDTO;
    }
    /**
     * detail insert
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalDowsetDetailDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrCalCompWkrcalDowsetDetailDTO
     * @return
     */
    public int insertDetail(MgrCalCompWkrcalDowsetDetailDTO mgrCalCompWkrcalDowsetDetailDTO, MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAWRKCALDOWSET						");
    	query.append("	(comp_no,	wrkcallist_id,	wrkcaldowset_id,	");
    	query.append("	 dow,		is_off     							");
    	query.append("	)	VALUES										");
    	query.append("	(?,				?,				?,				");
    	query.append("	 ?,				?								");
    	query.append("	)												");

    	Object[] objects = new Object[] {
    			user.getCompNo(),
    			mgrCalCompWkrcalCommonDTO.getWrkcalListId(),
    			mgrCalCompWkrcalDowsetDetailDTO.getWrkcalDowsetId(),
    			mgrCalCompWkrcalDowsetDetailDTO.getDow(),
    			mgrCalCompWkrcalDowsetDetailDTO.getIsOff()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    /**
     * detail update
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalDowsetDetailDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrCalCompWkrcalDowsetDetailDTO
     * @return
     */
    public int updateDetail(MgrCalCompWkrcalDowsetDetailDTO mgrCalCompWkrcalDowsetDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAWRKCALDOWSET SET		");
    	query.append("		dow				= ?, 	");
    	query.append("		is_off			= ? 	");
    	query.append("WHERE wrkcaldowset_id	= ?		");

    	Object[] objects = new Object[] {
    			mgrCalCompWkrcalDowsetDetailDTO.getDow(),
    			mgrCalCompWkrcalDowsetDetailDTO.getIsOff(),
    			mgrCalCompWkrcalDowsetDetailDTO.getWrkcalDowsetId()
    	};

    	return getJdbcTemplate().update(query.toString(), objects);
    }
}