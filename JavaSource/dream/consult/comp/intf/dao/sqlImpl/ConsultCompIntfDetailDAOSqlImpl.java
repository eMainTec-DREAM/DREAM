package dream.consult.comp.intf.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.consult.comp.intf.dao.ConsultCompIntfDetailDAO;
import dream.consult.comp.intf.dto.ConsultCompIntfCommonDTO;
import dream.consult.comp.intf.dto.ConsultCompIntfDetailDTO;

/**
 * Interface Page - Detail DAO implements
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="consultCompIntfDetailDAOTarget"
 * @spring.txbn id="consultCompIntfDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultCompIntfDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements ConsultCompIntfDetailDAO
{
	
    public ConsultCompIntfDetailDTO findDetail(ConsultCompIntfCommonDTO consultCompIntfCommonDTO, User user) 
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                                                        ");
        query.append("      x.intf_id                                                               AS intfId       ");
        query.append("      ,x.intf_no                                                              AS intfNo       ");
        query.append("		,x.comp_no																AS compNo		");
        query.append("		,(SELECT a.description FROM TACOMP a WHERE a.comp_no = x.comp_no) 		AS compDesc		");
        query.append("      ,x.description                                                          AS intfDesc     ");
        query.append("      ,x.intf_type                                                            AS intfType     ");
        query.append("      ,x.last_exe_date                                                        AS lastExeDate  ");
        query.append("      ,x.remark                                                               AS remark       ");
        query.append("      ,x.is_use                                                               AS isUse        ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.is_use,'IS_USE','SYS','','"+user.getLangId()+"') AS isUseDesc    ");
        query.append("      ,x.success_date                                                         AS successDate  ");
        query.append("      ,x.acc_url                                                              AS accUrl       ");
        query.append("      ,x.acc_name                                                             AS accName      ");
        query.append("      ,x.acc_port                                                             AS accPort      ");
        query.append("      ,x.acc_user                                                             AS accUser      ");
        query.append("      ,x.acc_password                                                         AS accPassword  ");
        query.append("      ,x.acc_site                                                             AS accSite      ");
        query.append("FROM TAINTF x                                                                                 ");
        query.append("WHERE  1=1                                                                                    ");
        query.append("AND    x.intf_id    = ?                                                                       ");
        query.append("AND    x.comp_no    = ?                                                                       ");
        
        Object[] objects = new Object[] {
                consultCompIntfCommonDTO.getIntfId()
                ,consultCompIntfCommonDTO.getCompNo()
        };
    
        ConsultCompIntfDetailDTO consultCompIntfDetailDTO = 
        		(ConsultCompIntfDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new ConsultCompIntfDetailDTO()));
        
        return consultCompIntfDetailDTO;
        
    }
    

    public int insertDetail(ConsultCompIntfDetailDTO consultCompIntfDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;

    	query.append("INSERT INTO TAINTF(  ");
        query.append("  comp_no             ");
        query.append("  ,intf_id            ");
        query.append("  ,intf_no            ");
        query.append("  ,description        ");
        query.append("  ,intf_type          ");
        query.append("  ,remark             ");
        query.append("  ,is_use             ");
        query.append("  ,acc_url            ");
        query.append("  ,acc_name           ");
        query.append("  ,acc_port           ");
        query.append("  ,acc_user           ");
        query.append("  ,acc_password       ");
        query.append("  ,acc_site           ");
        query.append(")VALUES(              ");
        query.append("  ?                   ");
        query.append("  ,?                  ");
        query.append("  ,?                  ");
        query.append("  ,?                  ");
        query.append("  ,?                  ");
        query.append("  ,?                  ");
        query.append("  ,?                  ");
        query.append("  ,?                  ");
        query.append("  ,?                  ");
        query.append("  ,?                  ");
        query.append("  ,?                  ");
        query.append("  ,?                  ");
        query.append("  ,?                  ");
        query.append(")                     ");
        
        Object[] objects = new Object[] {
        		consultCompIntfDetailDTO.getCompNo()
                ,consultCompIntfDetailDTO.getIntfId()
                ,consultCompIntfDetailDTO.getIntfNo()
                ,consultCompIntfDetailDTO.getIntfDesc()
                ,consultCompIntfDetailDTO.getIntfType()
                ,consultCompIntfDetailDTO.getRemark()
                ,consultCompIntfDetailDTO.getIsUse()
                ,consultCompIntfDetailDTO.getAccUrl()
                ,consultCompIntfDetailDTO.getAccName()
                ,consultCompIntfDetailDTO.getAccPort()
                ,consultCompIntfDetailDTO.getAccUser()
                ,consultCompIntfDetailDTO.getAccPassword()
                ,consultCompIntfDetailDTO.getAccSite()
        };
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
    }
    
    
    
    public int updateDetail(ConsultCompIntfDetailDTO consultCompIntfDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TAINTF SET                     ");
        query.append("  intf_no             = ?             ");
        query.append("  ,description        = ?             ");
        query.append("  ,intf_type          = ?             ");
        query.append("  ,remark             = ?             ");
        query.append("  ,is_use             = ?             ");
        query.append("  ,acc_url            = ?             ");
        query.append("  ,acc_name           = ?             ");
        query.append("  ,acc_port           = ?             ");
        query.append("  ,acc_user           = ?             ");
        query.append("  ,acc_password       = ?             ");
        query.append("  ,acc_site           = ?             ");
        query.append("WHERE  comp_no        = ?             ");
        query.append("  AND  intf_id        = ?             ");
        
        Object[] objects = new Object[] {
                consultCompIntfDetailDTO.getIntfNo()
                ,consultCompIntfDetailDTO.getIntfDesc()
                ,consultCompIntfDetailDTO.getIntfType()
                ,consultCompIntfDetailDTO.getRemark()
                ,consultCompIntfDetailDTO.getIsUse()
                ,consultCompIntfDetailDTO.getAccUrl()
                ,consultCompIntfDetailDTO.getAccName()
                ,consultCompIntfDetailDTO.getAccPort()
                ,consultCompIntfDetailDTO.getAccUser()
                ,consultCompIntfDetailDTO.getAccPassword()
                ,consultCompIntfDetailDTO.getAccSite()
                ,consultCompIntfDetailDTO.getCompNo()
                ,consultCompIntfDetailDTO.getIntfId()
        };
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
    }
}