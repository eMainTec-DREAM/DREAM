package dream.mgr.ptwh.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.mgr.ptwh.dao.MgrPtWhDetailDAO;
import dream.mgr.ptwh.dto.MgrPtWhCommonDTO;
import dream.mgr.ptwh.dto.MgrPtWhDetailDTO;

/**
 * 부품창고 - Detail DAO implements
 * @author sy.yang
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="mgrPtWhDetailDAOTarget"
 * @spring.txbn id="mgrPtWhDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrPtWhDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MgrPtWhDetailDAO
{
	
    public MgrPtWhDetailDTO findDetail(MgrPtWhCommonDTO mgrPtWhCommonDTO, User user) 
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT																									");
        query.append("		x.wcode 																			AS wcode		");
        query.append("		,x.plant 																			AS plant		");        
        query.append("		,(SELECT description FROM TAPLANT WHERE comp_no = x.comp_no AND plant = x.plant) 	As plantDesc	");
        query.append("		,x.wname 																			AS wname		");
        query.append("		,x.wh_type                                                                    		AS whType		");
        query.append("		,dbo.SFACODE_TO_DESC(x.wh_type,'WH_TYPE','SYS','','"+user.getLangId()+"') 			AS whTypeDesc 	");
        query.append("		,x.is_use                                                                        	AS isUse 		");
        query.append("		,x.remark                                                                  			AS remark 		");
        query.append("		,x.wcode_id 																		AS wcodeId		");
        query.append("FROM   TAWAREHOUSE x                                                                        				");
        query.append("WHERE  1 = 1                                                                              				");
        query.append(" AND    x.comp_no    = ?                           														");
        query.append("  AND    x.wcode_id    = ?                																");
        
        Object[] objects = new Object[] {
        		user.getCompNo()
               , mgrPtWhCommonDTO.getWcodeId()
        };
    
        MgrPtWhDetailDTO mgrPtWhDetailDTO =  
        		(MgrPtWhDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new MgrPtWhDetailDTO()));
        
        return mgrPtWhDetailDTO;
        
    }
    
    
    public int updatePtWhDetail(MgrPtWhDetailDTO mgrPtWhDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TAWAREHOUSE SET                ");
    	query.append("    is_use				= ?         ");
    	query.append("    ,remark               = ?       	");
    	query.append("WHERE  comp_no        	= ?        	");
    	query.append("  AND  wcode_id        	= ?        	");

    	
    	Object[] objects = new Object[] {
    			mgrPtWhDetailDTO.getIsUse()
    			,mgrPtWhDetailDTO.getRemark()
    			,user.getCompNo()
    			,mgrPtWhDetailDTO.getWcodeId()
    	};
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
}