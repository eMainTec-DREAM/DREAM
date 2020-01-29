package dream.rcm.system.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.rcm.system.dao.RcmSysFEnvDetailDAO;
import dream.rcm.system.dto.RcmSysFEnvDetailDTO;
import dream.rcm.system.dto.RcmSysFEnvListDTO;
import dream.rcm.system.dto.RcmSysCommonDTO;

/**
 * 운전환경 상세 dao
 * @author  kim21017
 * @version $Id: RcmSysFEnvDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="rcmSysFEnvDetailDAOTarget"
 * @spring.txbn id="rcmSysFEnvDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmSysFEnvDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements RcmSysFEnvDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmSysFEnvDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysFEnvListDTO
     * @param rcmSysCommonDTO
     * @return
     */
    public RcmSysFEnvDetailDTO findDetail(RcmSysFEnvListDTO rcmSysFEnvListDTO, RcmSysCommonDTO rcmSysCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT								");
        query.append("    	x.rcmfenv_type 	rcmFEnvType,	");
        query.append("		SFACODE_TO_DESC(x.rcmfenv_type,'RCMFENV_TYPE','SYS','','"+user.getLangId()+"')	rcmFEnvTypeDesc,");
        query.append("    	x.description 	description,	");
        query.append("    	x.remark 		remark,			");
        query.append("    	x.rcmfunc_id 	rcmFuncId,		");
        query.append("    	x.rcmfenv_id 	rcmFEnvId		");
        query.append("FROM TARCMFENV x						");
        query.append("WHERE 1=1								");
        query.append("AND x.comp_no = '"+user.getCompNo()+"'	");
        query.getAndQuery("x.rcmfenv_id",rcmSysFEnvListDTO.getRcmFEnvId());
    
        RcmSysFEnvDetailDTO rcmSysFEnvDetailDTO1 = 
        		(RcmSysFEnvDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new RcmSysFEnvDetailDTO()));
        
        return rcmSysFEnvDetailDTO1;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmSysFEnvDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysFEnvDetailDTO
     * @param rcmSysCommonDTO
     * @return
     */
    public int updateDetail(RcmSysFEnvDetailDTO rcmSysFEnvDetailDTO, RcmSysCommonDTO rcmSysCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TARCMFENV SET				");
    	query.append("	rcmfenv_type			= ?	,	");
    	query.append("	description				= ?	,	");
    	query.append("	remark				    = ?		");
    	query.append("WHERE rcmfenv_id 		    = ?		");
    	query.append("  AND comp_no				= ? 	");
    	
    	Object[] objects = new Object[] {
    			rcmSysFEnvDetailDTO.getRcmFEnvType(),
    			rcmSysFEnvDetailDTO.getDescription(),
    			rcmSysFEnvDetailDTO.getRemark(),
    			rcmSysFEnvDetailDTO.getRcmFEnvId(),
    			rcmSysCommonDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmSysFEnvDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysFEnvDetailDTO
     * @param rcmSysCommonDTO
     * @return
     */
    public int insertDetail(RcmSysFEnvDetailDTO rcmSysFEnvDetailDTO, RcmSysCommonDTO rcmSysCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TARCMFENV (								");
    	query.append("	comp_no,		rcmlist_id,		    rcmfunc_id,		");
    	query.append("	rcmfenv_id ,	rcmfenv_type,		description,	");
    	query.append("	remark		    									");
    	query.append("	)	VALUES				(							");
    	query.append("	?,				?,					?,				");
    	query.append("	?, 				?,					?,				");
    	query.append("	?													");
    	query.append("	)													");
    	
    	Object[] objects = new Object[] {
    			rcmSysCommonDTO.getCompNo(),
    			rcmSysCommonDTO.getRcmListId(),
    			rcmSysFEnvDetailDTO.getRcmFuncId(),
    			rcmSysFEnvDetailDTO.getRcmFEnvId(),
    			rcmSysFEnvDetailDTO.getRcmFEnvType(),
    			rcmSysFEnvDetailDTO.getDescription(),
    			rcmSysFEnvDetailDTO.getRemark()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
}