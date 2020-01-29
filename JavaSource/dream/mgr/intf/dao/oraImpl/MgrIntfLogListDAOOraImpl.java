package dream.mgr.intf.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.mgr.intf.dao.MgrIntfLogListDAO;
import dream.mgr.intf.dto.MgrIntfCommonDTO;
import dream.mgr.intf.dto.MgrIntfLogListDTO;

/**
 * Interface Log Page - List DAO implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="mgrIntfLogListDAOTarget"
 * @spring.txbn id="mgrIntfLogListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrIntfLogListDAOOraImpl extends BaseJdbcDaoSupportOra implements MgrIntfLogListDAO
{
	public List findList(MgrIntfCommonDTO mgrIntfCommonDTO, MgrIntfLogListDTO mgrIntfLogListDTO, User user) throws Exception
    { 
        QueryBuffer query = new QueryBuffer();
       
        query.append("SELECT																	      		        ");
        query.append("		''														                  AS seqNo		");
        query.append("		,''														                  AS isDelCheck	");
        query.append("		,x.intflog_id												              AS intfLogId	");
        query.append("      ,SUBSTR(x.exe_time,1,4)||'-'||SUBSTR(x.exe_time,5,2)||'-'||SUBSTR(x.exe_time,7,2)||' '||");
        query.append("       SUBSTR(x.exe_time,9,2)||':'||SUBSTR(x.exe_time,11,2)                     AS exeTime    ");
        query.append("		,x.rtnyn											                      AS rtnYn		");
        query.append("FROM TAINTFLOG x															      		        ");
    	query.append("WHERE  1=1																      		        ");
    	query.append(this.getWhere(mgrIntfCommonDTO, mgrIntfLogListDTO, user));
        query.getOrderByQuery("x.exe_time desc", mgrIntfLogListDTO.getOrderBy(), mgrIntfLogListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(mgrIntfLogListDTO.getIsLoadMaxCount(), mgrIntfLogListDTO.getFirstRow()));
        } 

	private String getWhere(MgrIntfCommonDTO mgrIntfCommonDTO, MgrIntfLogListDTO mgrIntfLogListDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        if(!"".equals(mgrIntfLogListDTO.getIntfId())){
        	query.getAndQuery("x.intflog_id", mgrIntfLogListDTO.getIntfLogId());
        	return query.toString();
        }
        query.getLikeQuery("x.intf_id", mgrIntfCommonDTO.getIntfId());

    	return query.toString();
    }

    public int deleteList(String id, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TAINTFLOG			");
        query.append("WHERE  comp_no 		= ?		");
        query.append("  AND  intflog_id  	= ?		");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public String findTotalCount(MgrIntfCommonDTO mgrIntfCommonDTO, MgrIntfLogListDTO mgrIntfLogListDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM TAINTFLOG x										    ");
    	query.append("WHERE  1=1												");
    	query.append(this.getWhere(mgrIntfCommonDTO, mgrIntfLogListDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}