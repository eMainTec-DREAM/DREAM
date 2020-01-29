package dream.consult.program.config.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.consult.program.config.dao.MaConfigDetailDAO;
import dream.consult.program.config.dto.MaConfigCommonDTO;
import dream.consult.program.config.dto.MaConfigDetailDTO;

/**
 * 시스템환경변수 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaConfigDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="maConfigDetailDAOTarget"
 * @spring.txbn id="maConfigDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaConfigDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaConfigDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaConfigDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param configName
     * @return
     */
    public MaConfigDetailDTO findDetail(MaConfigCommonDTO maConfigCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT															");
        query.append("       x.config_id								configId		");
        query.append("       ,x.name										configName		");
        query.append("       ,x.value$									configValue	");
        query.append("       ,x.description								configDesc		");
        query.append("       ,x.is_system								isSystem		");
        query.append("FROM   TACONFIG x													");
        query.append("WHERE  x.config_id 	= '"+maConfigCommonDTO.getConfigId()+"'		");
    
        MaConfigDetailDTO maConfigDetailDTO = 
        		(MaConfigDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaConfigDetailDTO()));
        
        return maConfigDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaConfigDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maConfigDetailDTO
     * @return
     */
    public int updateDetail(MaConfigDetailDTO maConfigDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	

    	query.append("UPDATE TACONFIG SET		");
    	query.append("	name			= ?,	");
    	query.append("	description		= ?,	");
    	query.append("	is_system		= ?,	");
    	query.append("	value$			= ?		");
    	query.append("WHERE config_id 	= ?		");
    	
    	Object[] objects = new Object[] {
    			maConfigDetailDTO.getConfigName(),
    			maConfigDetailDTO.getConfigDesc(),
    			maConfigDetailDTO.getIsSystem(),
    			maConfigDetailDTO.getConfigValue(),
    			maConfigDetailDTO.getConfigId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaConfigDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maConfigDetailDTO
     * @return
     */
    public int insertDetail(MaConfigDetailDTO maConfigDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TACONFIG									");
    	query.append("		 (name,			value$,		description,		");
    	query.append("	 	  is_system,	config_id				)		");
    	query.append("VALUES (?,			?,			?,					");
    	query.append("	 	  ?,			?						)		");
    	
    	Object[] objects = new Object[] {
    			maConfigDetailDTO.getConfigName(),
    			maConfigDetailDTO.getConfigValue(),
    			maConfigDetailDTO.getConfigDesc(),
    			maConfigDetailDTO.getIsSystem(),
    			maConfigDetailDTO.getConfigId()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}