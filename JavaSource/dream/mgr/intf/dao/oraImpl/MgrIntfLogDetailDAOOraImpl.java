package dream.mgr.intf.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.mgr.intf.dao.MgrIntfLogDetailDAO;
import dream.mgr.intf.dto.MgrIntfLogDetailDTO;
import dream.mgr.intf.dto.MgrIntfLogListDTO;

/**
 * Interface Log Page - Detail DAO implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="mgrIntfLogDetailDAOTarget"
 * @spring.txbn id="mgrIntfLogDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrIntfLogDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MgrIntfLogDetailDAO
{
	
    public MgrIntfLogDetailDTO findDetail(MgrIntfLogListDTO mgrIntfLogListDTO, User user) 
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                        ");
        query.append("      x.intflog_id                                                              AS intfLogId  ");
        query.append("      ,x.intf_id                                                                AS intfId     ");
        query.append("      ,SUBSTR(x.exe_time,1,4)||'-'||SUBSTR(x.exe_time,5,2)||'-'||SUBSTR(x.exe_time,7,2)||' '||");
        query.append("       SUBSTR(x.exe_time,9,2)||':'||SUBSTR(x.exe_time,11,2)                     AS exeTime    ");
        query.append("      ,(SELECT user_name FROM TAUSER                                                          ");
        query.append("        WHERE comp_no=x.comp_no AND user_id=x.exe_by)                           AS exeBy      ");
        query.append("      ,x.rtnyn                                                                  AS rtnYn      ");
        query.append("      ,x.rtnmsg                                                                 AS rtnMsg     ");
        query.append("      ,x.exelog                                                                 AS exeLog     ");
        query.append("FROM TAINTFLOG x                                                                              ");
    	query.append("WHERE  1=1												                      			    ");
    	query.append("AND    x.intflog_id = ?									                      			    ");
    	query.append("AND    x.comp_no    = ?									                      			    ");
        
        Object[] objects = new Object[] {
        		mgrIntfLogListDTO.getIntfLogId()
    			,user.getCompNo()
    	};
    
        MgrIntfLogDetailDTO mgrIntfLogDetailDTO = 
        		(MgrIntfLogDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new MgrIntfLogDetailDTO()));
        
        return mgrIntfLogDetailDTO;
        
    }
}