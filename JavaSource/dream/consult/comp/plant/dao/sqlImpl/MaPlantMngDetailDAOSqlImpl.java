package dream.consult.comp.plant.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;

import dream.consult.comp.plant.dao.MaPlantMngDetailDAO;
import dream.consult.comp.plant.dto.MaPlantMngCommonDTO;
import dream.consult.comp.plant.dto.MaPlantMngDetailDTO;

/**
 * 회사설정 - 상세 dao
 *
 * @author kim21017
 * @version $Id: MaPlantMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="maPlantMngDetailDAOTarget"
 * @spring.txbn id="maPlantMngDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPlantMngDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPlantMngDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaPlantMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param buttonId
     * @return
     */
    public MaPlantMngDetailDTO findDetail(MaPlantMngCommonDTO maPlantMngCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT								        ");
        query.append("       x.comp_no			    compNo		                                                            ");
        query.append("       ,(select aa.description from tacomp aa where aa.comp_no = x.comp_no) as compDesc		        ");
        query.append("       ,x.plant			    plantNo		                                                            ");
        query.append("       ,x.plant_id		    plantId		                                                            ");
        query.append("       ,x.description		    plantDesc	                                                            ");
        query.append("       ,x.is_use  		    isUse    	                                                            ");
        query.append("       ,x.wrkcallist_id 								wrkCalListId									");
        query.append("       ,(SELECT aa.description 																		");
        query.append("       	FROM TAWRKCALLIST aa																		");
        query.append("        	WHERE aa.comp_no = x.comp_no 																");
        query.append("            AND aa.wrkcallist_id = x.wrkcallist_id)	wrkCalListDesc      							");
        query.append("FROM   TAPLANT x						        ");
        query.append("WHERE  x.comp_no = '"+maPlantMngCommonDTO.getCompNo()+"'	 ");
        query.append("  AND  x.plant_id = '"+maPlantMngCommonDTO.getPlantId()+"' ");

        MaPlantMngDetailDTO maPlantMngDetailDTO =
        		(MaPlantMngDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaPlantMngDetailDTO()));

        return maPlantMngDetailDTO;
    }
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaPlantMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param maPlantMngDetailDTO
     * @return
     */
    public int insertDetail(MaPlantMngDetailDTO maPlantMngDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAPLANT                       ");
    	query.append("    (comp_no,        		plant,			");
    	query.append("     description,       	is_use,			");
    	query.append("     wrkcallist_id,       plant_id		");
    	query.append("    )    VALUES                           ");
    	query.append("    (?,                	?,              ");
    	query.append("     ?, 					?,				");
    	query.append("     ?,            		?				");
    	query.append("    )                						");

    	Object[] objects = new Object[] {
    			maPlantMngDetailDTO.getCompNo()
    			,maPlantMngDetailDTO.getPlantNo()
    			,maPlantMngDetailDTO.getPlantDesc()
    			,maPlantMngDetailDTO.getIsUse()
    			,maPlantMngDetailDTO.getWrkCalListId()
    			,maPlantMngDetailDTO.getPlantId()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaPlantMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param maPlantMngDetailDTO
     * @return
     */
    public int updateDetail(MaPlantMngDetailDTO maPlantMngDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAPLANT SET	");
    	query.append("	description	= ? 	");
    	query.append("	,is_use	= ?     	");
    	query.append("	,wrkcallist_id	= ? ");
    	query.append("WHERE comp_no = ?		");
    	query.append("  and plant_id = ?	");

    	Object[] objects = new Object[] {
    			maPlantMngDetailDTO.getPlantDesc()
    			,maPlantMngDetailDTO.getIsUse()
    			,maPlantMngDetailDTO.getWrkCalListId()
    			,maPlantMngDetailDTO.getCompNo()
    			,maPlantMngDetailDTO.getPlantId()
    	};

    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
	
}