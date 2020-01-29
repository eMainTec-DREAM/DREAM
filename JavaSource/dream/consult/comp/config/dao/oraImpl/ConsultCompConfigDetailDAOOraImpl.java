package dream.consult.comp.config.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.consult.comp.config.dao.ConsultCompConfigDetailDAO;
import dream.consult.comp.config.dto.ConsultCompConfigCommonDTO;
import dream.consult.comp.config.dto.ConsultCompConfigDetailDTO;

/**
 * 시스템환경변수 - 상세 dao
 * 
 * @author syyang
 * @version $Id: ConsultCompConfigDetailDAO.java,v 1.0 20155/12/02 08:25:47 syyang Exp $
 * @since 1.0
 * @spring.bean id="consultCompConfigDetailDAOTarget"
 * @spring.txbn id="consultCompConfigDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultCompConfigDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements ConsultCompConfigDetailDAO
{
    /**
     * detail find
     * @author syyang
     * @version $Id: ConsultCompConfigDetailDAO.java,v 1.0 20155/12/02 08:25:47 syyang Exp $
     * @since   1.0
     * 
     * @param configName
     * @return
     */
    public ConsultCompConfigDetailDTO findDetail(ConsultCompConfigCommonDTO consultCompConfigCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT																	");
        query.append("        x.comp_no 								AS compNo				");
        query.append("       ,(select aa.description from tacomp aa where x.comp_no = aa.comp_no) AS compDesc	");
        query.append("       ,x.compconfig_id							AS compconfigId			");
        query.append("       ,x.name									AS compconfigName		");
        query.append("       ,x.value$									AS compconfigValue		");
        query.append("       ,x.description								AS compconfigDesc		");
        query.append("       ,x.is_system								AS isSystem				");
        query.append("FROM   TACOMPCONFIG x														");
        query.append("WHERE  x.compconfig_id = '"+consultCompConfigCommonDTO.getCompconfigId()+"'	");
        query.append("  AND  x.comp_no 	= '"+consultCompConfigCommonDTO.getCompNo()+"'			");
    
        ConsultCompConfigDetailDTO consultCompConfigDetailDTO = 
        		(ConsultCompConfigDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new ConsultCompConfigDetailDTO()));
        
        return consultCompConfigDetailDTO;
    }
    /**
     * detail update
     * @author syyang
     * @version $Id: ConsultCompConfigDetailDAO.java,v 1.0 20155/12/02 08:25:47 syyang Exp $
     * @since   1.0
     * 
     * @param consultCompConfigDetailDTO
     * @return
     */
    public int updateDetail(ConsultCompConfigDetailDTO consultCompConfigDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	

    	query.append("UPDATE TACOMPCONFIG SET		");
    	query.append("	name				= ?,	");
    	query.append("	description			= ?,	");
    	query.append("	is_system			= ?,	");
    	query.append("	value$				= ?		");
    	query.append("WHERE compconfig_id	= ?		");
        query.append("  AND comp_no 		= ?		");
    	
    	Object[] objects = new Object[] {
    			consultCompConfigDetailDTO.getCompconfigName(),
    			consultCompConfigDetailDTO.getCompconfigDesc(),
    			consultCompConfigDetailDTO.getIsSystem(),
    			consultCompConfigDetailDTO.getCompconfigValue(),
    			consultCompConfigDetailDTO.getCompconfigId(),
    			consultCompConfigDetailDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    /**
     * detail insert
     * @author syyang
     * @version $Id: ConsultCompConfigDetailDAO.java,v 1.0 20155/12/02 08:25:47 syyang Exp $
     * @since   1.0
     * 
     * @param consultCompConfigDetailDTO
     * @return
     */
    public int insertDetail(ConsultCompConfigDetailDTO consultCompConfigDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TACOMPCONFIG					");
    	query.append("	(comp_no,		name,					");
    	query.append("	 value$,		description,			");
    	query.append("	is_system,		compconfig_id)	VALUES	");
    	query.append("	(?,				?,						");
    	query.append("	 ?,				?,						");
    	query.append("	 ?,				?)						");
    	
    	Object[] objects = new Object[] {
    			consultCompConfigDetailDTO.getCompNo(),
    			consultCompConfigDetailDTO.getCompconfigName(),
    			consultCompConfigDetailDTO.getCompconfigValue(),
    			consultCompConfigDetailDTO.getCompconfigDesc(),
    			consultCompConfigDetailDTO.getIsSystem(),
    			consultCompConfigDetailDTO.getCompconfigId()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
}