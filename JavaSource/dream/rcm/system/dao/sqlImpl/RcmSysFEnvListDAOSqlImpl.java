package dream.rcm.system.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.rcm.system.dao.RcmSysFEnvListDAO;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.dto.RcmSysFEnvListDTO;

/**
 * 운전환경 목록 dao
 * @author  kim21017
 * @version $Id: RcmSysFEnvListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="rcmSysFEnvListDAOTarget"
 * @spring.txbn id="rcmSysFEnvListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmSysFEnvListDAOSqlImpl extends BaseJdbcDaoSupportSql implements RcmSysFEnvListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: RcmSysFEnvListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysCommonDTO
     * @param rcmSysFEnvListDTO
     * @return List
     */
    public List findList(RcmSysCommonDTO rcmSysCommonDTO, RcmSysFEnvListDTO rcmSysFEnvListDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                ");
        query.append("       ''             seqNo,          ");
        query.append("       ''             isDelCheck,     ");
        query.append("      dbo.SFACODE_TO_DESC(x.rcmfenv_type,'RCMFENV_TYPE','SYS','','"+user.getLangId()+"')  rcmFEnvType,");
        query.append("       x.description  description,    ");
        query.append("       x.remark       remark,         ");
        query.append("       x.rcmfenv_id   rcmFEnvId       ");
        query.append("FROM TARCMFENV x                      ");
        query.append("WHERE 1 = 1                           ");
        query.append("AND x.comp_no = '"+user.getCompNo()+"'");
        query.append(this.getWhere(rcmSysCommonDTO,rcmSysFEnvListDTO));
        query.append("ORDER BY x.rcmfenv_id                 ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: RcmSysFEnvListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @param deleteRowsExt 
     * @return
     */
    public int deleteList(String id)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("DELETE FROM TARCMFENV						");
    	query.append("WHERE rcmfenv_id 	= '"+id+"'				");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    /**
     * input
     * @author  kim21017
     * @version $Id: RcmSysFEnvListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysCommonDTO
     * @param rcmSysFEnvListDTO
     * @return List
     */
    public int inputList(RcmSysCommonDTO rcmSysCommonDTO, RcmSysFEnvListDTO rcmSysFEnvListDTO, String multiKey)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
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
    			rcmSysCommonDTO.getCompNo()
    			,rcmSysCommonDTO.getRcmListId()
    			,rcmSysFEnvListDTO.getRcmFuncId()
    			,rcmSysFEnvListDTO.getRcmFEnvId()
    			,multiKey
    			,""
    			,""
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
    }
    
    private String getWhere(RcmSysCommonDTO rcmSysCommonDTO, RcmSysFEnvListDTO rcmSysFEnvListDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.getAndQuery("x.rcmfunc_id", rcmSysFEnvListDTO.getRcmFuncId());
    	if (!"".equals(rcmSysFEnvListDTO.getRcmFEnvId()))
        {
            query.getAndQuery("x.rcmfenv_id", rcmSysFEnvListDTO.getRcmFEnvId());
            return query.toString();
        }
    	
    	return query.toString();
    }
}