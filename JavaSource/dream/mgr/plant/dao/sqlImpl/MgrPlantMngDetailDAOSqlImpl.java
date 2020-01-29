package dream.mgr.plant.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.mgr.plant.dao.MgrPlantMngDetailDAO;
import dream.mgr.plant.dto.MgrPlantMngCommonDTO;
import dream.mgr.plant.dto.MgrPlantMngDetailDTO;

/**
 * 공장설정 - 상세 dao
 *
 * @author euna0207
 * @version $Id: MgrPlantMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
 * @since 1.0
 * @spring.bean id="mgrPlantMngDetailDAOTarget"
 * @spring.txbn id="mgrPlantMngDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrPlantMngDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MgrPlantMngDetailDAO
{
    /**
     * detail find
     * @author euna0207
     * @version $Id: MgrPlantMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
     * @since   1.0
     *
     * @param buttonId
     * @return
     */
    public MgrPlantMngDetailDTO findDetail(MgrPlantMngCommonDTO mgrPlantMngCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT								        ");
        query.append("       x.comp_no			    compNo		                                                            ");
        query.append("       ,x.plant			    plantNo		                                                            ");
        query.append("       ,x.plant_id		    plantId		                                                            ");
        query.append("       ,x.description		    plantDesc	                                                            ");
        query.append("       ,x.is_use  		    isUse    	                                                            ");
        query.append("FROM   TAPLANT x						        ");
        query.append("WHERE  x.comp_no = '"+user.getCompNo()+"'	 ");
        query.append("  AND  x.plant_id = '"+mgrPlantMngCommonDTO.getPlantId()+"' ");

        MgrPlantMngDetailDTO mgrPlantMngDetailDTO =
        		(MgrPlantMngDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MgrPlantMngDetailDTO()));

        return mgrPlantMngDetailDTO;
    }
    /**
     * detail insert
     * @author euna0207
     * @version $Id: MgrPlantMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrPlantMngDetailDTO
     * @return
     */
    public int insertDetail(MgrPlantMngDetailDTO mgrPlantMngDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAPLANT                       ");
    	query.append("    (comp_no,        		plant,			");
    	query.append("     description,       	is_use,			");
    	query.append("     plant_id		");
    	query.append("    )    VALUES                           ");
    	query.append("    (?,                	?,              ");
    	query.append("     ?, 					?,				");
    	query.append("     ?									");
    	query.append("    )                						");

    	Object[] objects = new Object[] {
    			user.getCompNo()
    			,mgrPlantMngDetailDTO.getPlantNo()
    			,mgrPlantMngDetailDTO.getPlantDesc()
    			,mgrPlantMngDetailDTO.getIsUse()
    			,mgrPlantMngDetailDTO.getPlantId()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    /**
     * detail update
     * @author euna0207
     * @version $Id: MgrPlantMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrPlantMngDetailDTO
     * @return
     */
    public int updateDetail(MgrPlantMngDetailDTO mgrPlantMngDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAPLANT SET	");
    	query.append("	description	= ? 	");
    	query.append("	,is_use	= ?     	");
    	query.append("WHERE comp_no = ?		");
    	query.append("  and plant_id = ?	");

    	Object[] objects = new Object[] {
    			mgrPlantMngDetailDTO.getPlantDesc()
    			,mgrPlantMngDetailDTO.getIsUse()
    			,user.getCompNo()
    			,mgrPlantMngDetailDTO.getPlantId()
    	};

    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
	
}