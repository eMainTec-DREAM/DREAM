package dream.rcm.system.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.rcm.system.dao.RcmSysFDefListDAO;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.dto.RcmSysFDefListDTO;

/**
 * 기능정의  dao
 * @author  kim21017
 * @version $Id: RcmSysFDefListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="rcmSysFDefListDAOTarget"
 * @spring.txbn id="rcmSysFDefListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmSysFDefListDAOOraImpl extends BaseJdbcDaoSupportOra implements RcmSysFDefListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: RcmSysFDefListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(RcmSysCommonDTO rcmSysCommonDTO, RcmSysFDefListDTO rcmSysFDefListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT												");
        query.append("       '' 							seqNo,			");
        query.append("       '' 							isDelCheck,		");
        query.append("       x.rcmfunc_no 					rcmFuncNo,		");
        query.append("       x.description 					description,	");
        query.append("       x.remark 						remark,			");
        query.append("       x.rcmfunc_id 					rcmFuncId		");
        query.append("FROM   TARCMFUNC x	 								");
        query.append("WHERE 1=1												");
        query.append(this.getWhere(rcmSysCommonDTO,rcmSysFDefListDTO,loginUser));
        query.getOrderByQuery("x.rcmfunc_id","x.rcmfunc_id", rcmSysCommonDTO.getOrderBy(), rcmSysCommonDTO.getDirection());
        return getJdbcTemplate().queryForList(query.toString(rcmSysCommonDTO.getIsLoadMaxCount(), rcmSysCommonDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: RcmSysFDefListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteList(String id, String compNo)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("DELETE FROM TARCMFUNC							");
    	query.append("WHERE  rcmfunc_id 	= '"+id+"'				");
    	query.append("  AND  comp_no		= '"+compNo+"'			");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(RcmSysCommonDTO rcmSysCommonDTO, RcmSysFDefListDTO rcmSysFDefListDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.getAndQuery("x.rcmlist_id", rcmSysCommonDTO.getRcmListId());
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	
    	if (!"".equals(rcmSysFDefListDTO.getRcmFuncId()))
        {
            query.getAndQuery("x.rcmfunc_id", rcmSysFDefListDTO.getRcmFuncId());
            return query.toString();
        }
    	
    	return query.toString();
    }

	@Override
	public String findTotalCount(RcmSysCommonDTO rcmSysCommonDTO, RcmSysFDefListDTO rcmSysFDefListDTO, User loginUser) {
		
		QueryBuffer query = new QueryBuffer();
    	
    	query.append("SELECT					");
    	query.append("		count(1)			");
    	query.append("FROM   TARCMFUNC x		");
    	query.append("WHERE  1 = 1              ");
    	query.append(this.getWhere(rcmSysCommonDTO,rcmSysFDefListDTO,loginUser));

    	List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}
}