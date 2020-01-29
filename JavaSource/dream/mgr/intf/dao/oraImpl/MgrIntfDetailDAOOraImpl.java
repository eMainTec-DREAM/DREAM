package dream.mgr.intf.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.mgr.intf.dao.MgrIntfDetailDAO;
import dream.mgr.intf.dto.MgrIntfCommonDTO;
import dream.mgr.intf.dto.MgrIntfDetailDTO;

/**
 * Interface Page - Detail DAO implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="mgrIntfDetailDAOTarget"
 * @spring.txbn id="mgrIntfDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrIntfDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MgrIntfDetailDAO
{
	
    public MgrIntfDetailDTO findDetail(MgrIntfCommonDTO mgrIntfCommonDTO, User user) 
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                    ");
        query.append("      x.intf_id                                                           AS intfId       ");
        query.append("      ,x.intf_no                                                          AS intfNo       ");
        query.append("      ,x.description                                                      AS intfDesc     ");
        query.append("      ,x.intf_type                                                        AS intfType     ");
        query.append("      ,x.last_exe_date                                                    AS lastExeDate  ");
        query.append("      ,x.remark                                                           AS remark       ");
        query.append("      ,x.is_use                                                           AS isUse        ");
        query.append("      ,SFACODE_TO_DESC(x.is_use,'IS_USE','SYS','','"+user.getLangId()+"') AS isUseDesc    ");
        query.append("      ,x.success_date                                                     AS successDate  ");
        query.append("      ,x.acc_url                                                          AS accUrl       ");
        query.append("      ,x.acc_name                                                         AS accName      ");
        query.append("      ,x.acc_port                                                         AS accPort      ");
        query.append("      ,x.acc_user                                                         AS accUser      ");
        query.append("      ,x.acc_password                                                     AS accPassword  ");
        query.append("      ,x.acc_site                                                         AS accSite      ");
        query.append("FROM TAINTF x                                                                             ");
    	query.append("WHERE  1=1																		        ");
    	query.append("AND    x.intf_id    = ?																    ");
    	query.append("AND    x.comp_no    = ?																    ");
        
        Object[] objects = new Object[] {
        		mgrIntfCommonDTO.getIntfId()
    			,user.getCompNo()
    	};
    
        MgrIntfDetailDTO mgrIntfDetailDTO = 
        		(MgrIntfDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new MgrIntfDetailDTO()));
        
        return mgrIntfDetailDTO;
        
    }
    

    public int insertDetail(MgrIntfDetailDTO mgrIntfDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;

    	query.append("INSERT INTO TAINTF(	");
    	query.append("	comp_no				");
    	query.append("	,intf_id			");
    	query.append("	,intf_no			");
    	query.append("	,description		");
    	query.append("	,intf_type			");
    	query.append("	,remark			    ");
    	query.append("	,is_use			    ");
    	query.append("	,acc_url			");
    	query.append("	,acc_name			");
    	query.append("	,acc_port			");
    	query.append("	,acc_user			");
    	query.append("	,acc_password		");
    	query.append("	,acc_site			");
    	query.append(")VALUES(				");
    	query.append("	?				    ");
    	query.append("	,?				    ");
    	query.append("	,?				    ");
    	query.append("	,?				    ");
    	query.append("	,?				    ");
    	query.append("	,?				    ");
    	query.append("	,?				    ");
    	query.append("	,?				    ");
    	query.append("	,?				    ");
    	query.append("	,?				    ");
    	query.append("	,?				    ");
    	query.append("	,?				    ");
    	query.append("	,?				    ");
    	query.append(")					    ");
    	
    	Object[] objects = new Object[] {
    			 user.getCompNo()
    			,mgrIntfDetailDTO.getIntfId()
    			,mgrIntfDetailDTO.getIntfNo()
    			,mgrIntfDetailDTO.getIntfDesc()
    			,mgrIntfDetailDTO.getIntfType()
    			,mgrIntfDetailDTO.getRemark()
    			,mgrIntfDetailDTO.getIsUse()
    			,mgrIntfDetailDTO.getAccUrl()
    			,mgrIntfDetailDTO.getAccName()
    			,mgrIntfDetailDTO.getAccPort()
    			,mgrIntfDetailDTO.getAccUser()
    			,mgrIntfDetailDTO.getAccPassword()
    			,mgrIntfDetailDTO.getAccSite()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    
    
    public int updateDetail(MgrIntfDetailDTO mgrIntfDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TAINTF SET				    	");
    	query.append("	intf_no				= ?				");
    	query.append("	,description		= ?				");
    	query.append("	,intf_type			= ?				");
    	query.append("	,remark				= ?				");
    	query.append("	,is_use				= ?				");
    	query.append("	,acc_url			= ?				");
    	query.append("	,acc_name			= ?				");
    	query.append("	,acc_port			= ?				");
    	query.append("	,acc_user			= ?				");
    	query.append("	,acc_password		= ?				");
    	query.append("	,acc_site			= ?				");
    	query.append("WHERE  comp_no		= ?				");
    	query.append("  AND  intf_id		= ?				");
    	
    	Object[] objects = new Object[] {
    			mgrIntfDetailDTO.getIntfNo()
    			,mgrIntfDetailDTO.getIntfDesc()
    			,mgrIntfDetailDTO.getIntfType()
    			,mgrIntfDetailDTO.getRemark()
    			,mgrIntfDetailDTO.getIsUse()
    			,mgrIntfDetailDTO.getAccUrl()
    			,mgrIntfDetailDTO.getAccName()
    			,mgrIntfDetailDTO.getAccPort()
    			,mgrIntfDetailDTO.getAccUser()
    			,mgrIntfDetailDTO.getAccPassword()
    			,mgrIntfDetailDTO.getAccSite()
    			,user.getCompNo()
    			,mgrIntfDetailDTO.getIntfId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
}