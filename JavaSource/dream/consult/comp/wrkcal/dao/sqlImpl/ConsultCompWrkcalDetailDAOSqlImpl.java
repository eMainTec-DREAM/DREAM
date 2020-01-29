package dream.consult.comp.wrkcal.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.consult.comp.wrkcal.dao.ConsultCompWrkcalDetailDAO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalCommonDTO;
import dream.consult.comp.wrkcal.dto.ConsultCompWrkcalDetailDTO;

/**
 * 회사설정 - 상세 dao
 *
 * @author kim21017
 * @version $Id: ConsultCompWrkcalDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="consultCompWrkcalDetailDAOTarget"
 * @spring.txbn id="consultCompWrkcalDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultCompWrkcalDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements ConsultCompWrkcalDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: ConsultCompWrkcalDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param buttonId
     * @return
     */
    public ConsultCompWrkcalDetailDTO findDetail(ConsultCompWrkcalCommonDTO consultCompWrkcalCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

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
        query.append("       x.is_use						isUse,		");
        query.append("       x.remark						remark			");
        query.append("FROM   TAWRKCALLIST x						    		");
        query.append("WHERE  x.wrkcallist_id = '"+consultCompWrkcalCommonDTO.getWrkcalListId()+"'	");
        query.append("       and   x.comp_No = '"+consultCompWrkcalCommonDTO.getCompNo()+"'	");

        ConsultCompWrkcalDetailDTO consultCompWrkcalDetailDTO =
        		(ConsultCompWrkcalDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new ConsultCompWrkcalDetailDTO()));

        return consultCompWrkcalDetailDTO;
    }
    /**
     * detail insert
     * @author kim21017
     * @version $Id: ConsultCompWrkcalDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompWrkcalDetailDTO
     * @return
     */
    public int insertDetail(ConsultCompWrkcalDetailDTO consultCompWrkcalDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

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
    			consultCompWrkcalDetailDTO.getCompNo(),
    			consultCompWrkcalDetailDTO.getWrkcalListId(),
    			consultCompWrkcalDetailDTO.getPlantNo(),
    			consultCompWrkcalDetailDTO.getWrkcalListNo(),
    			consultCompWrkcalDetailDTO.getWrkcalListDesc(),
    			consultCompWrkcalDetailDTO.getIsUse(),
    			consultCompWrkcalDetailDTO.getRemark()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: ConsultCompWrkcalDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param consultCompWrkcalDetailDTO
     * @return
     */
    public int updateDetail(ConsultCompWrkcalDetailDTO consultCompWrkcalDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAWRKCALLIST SET	        ");
    	query.append("	 WRKCALLIST_NO	        = ? 	");
    	query.append("	,description	        = ? 	");
    	query.append("	,IS_USE			        = ? 	");
    	query.append("	,PLANT			        = ? 	");
    	query.append("	,REMARK			        = ? 	");
    	query.append("WHERE comp_no 	        = ?		");
    	query.append("  AND WRKCALLIST_ID 		= ?		");

    	Object[] objects = new Object[] {
    			consultCompWrkcalDetailDTO.getWrkcalListNo()
    			,consultCompWrkcalDetailDTO.getWrkcalListDesc()
    			,consultCompWrkcalDetailDTO.getIsUse()
    			,consultCompWrkcalDetailDTO.getPlantNo()
    			,consultCompWrkcalDetailDTO.getRemark()
    			,consultCompWrkcalDetailDTO.getCompNo()
    			,consultCompWrkcalDetailDTO.getWrkcalListId()
    	};

    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}