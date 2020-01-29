package dream.org.dept.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.org.dept.dao.MaDeptDetailDAO;
import dream.org.dept.dto.MaDeptDetailDTO;

/**
 * 보전부서 - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 * @spring.bean id="maDeptDetailDAOTarget"
 * @spring.txbn id="maDeptDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaDeptDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaDeptDetailDAO
{
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param deptNo
     * @return
     */
    public MaDeptDetailDTO findDetail(User user, String deptId)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String compNo = user.getCompNo();
        
        query.append("SELECT								                    ");
        query.append("       x.comp_no                              compNo,     ");
        query.append("       x.dept_id                              deptId,     ");
        query.append("       x.dept_no                              deptNo,     ");
        query.append("       x.description	                        description,");
        query.append("       CASE WHEN x.p_dept_id='0' THEN '' ELSE x.p_dept_id END pdeptId, ");
        query.append("       dbo.SFAIDTODESC(x.p_dept_id, '', 'DEPT', x.comp_no) pdeptDesc,  ");
        query.append("       x.dept_categ                           deptCateg,  ");
        query.append("       dbo.SFACODE_TO_DESC(x.dept_categ, 'DEPT_CATEG', 'SYS', x.comp_no,'"+user.getLangId()+"') deptCategDesc,");
        query.append("       x.ord_no                               ordNo,      ");
        query.append("       x.is_use			                    isUse,      ");
        query.append("       x.wcode_id 					wcodeId,		                ");
        query.append("       (SELECT wname									                ");
        query.append("          FROM TAWAREHOUSE							                ");
        query.append("         WHERE comp_no = x.comp_no					                ");
        query.append("           AND wcode_id = x.wcode_id) wcodeDesc,		                ");
        query.append("       x.twcode_id 					twcodeId,		                ");
        query.append("       (SELECT wname									                ");
        query.append("          FROM TAWAREHOUSE							                ");
        query.append("         WHERE comp_no = x.comp_no					                ");
        query.append("           AND wcode_id = x.twcode_id) twcodeDesc,		            ");
        query.append("       dbo.SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', x.comp_no,'"+user.getLangId()+"') isUseDesc, ");
        query.append("		 x.plant          								plantId,		");
        query.append("		 dbo.SFAPLANTTODESC(x.comp_no, x.plant) plantDesc,               ");
        query.append("       x.is_maint                               isMaint,      ");
        query.append("       dbo.SFACODE_TO_DESC(x.is_maint, 'IS_USE', 'SYS', x.comp_no,'"+user.getLangId()+"') isMaintDesc, ");
        query.append("       x.is_monitoring                               isMonitoring,      ");
        query.append("       dbo.SFACODE_TO_DESC(x.is_monitoring, 'IS_USE', 'SYS', x.comp_no,'"+user.getLangId()+"') isMonitoringDesc ");
        query.append("	   , from_wcode_id 					fromWcodeId						");
        query.append("     ,(SELECT a.wname FROM TAWAREHOUSE a WHERE a.comp_no = x.comp_no AND a.wcode_id = x.from_wcode_id) fromWcodeDesc	");
        query.append("     , to_wcode_id 					toWcodeId						");
        query.append("     ,(SELECT a.wname FROM TAWAREHOUSE a WHERE a.comp_no = x.comp_no AND a.wcode_id = x.to_wcode_id) toWcodeDesc	");
        query.append("     ,(SELECT count(*)                                            	");
        query.append("       FROM TAEQUIPMENT a                                        		");
        query.append("       WHERE a.comp_no = x.comp_no                               		");
        query.append("        AND a.eq_status in ('R','S')                          		");  // RUN[가동중], STANDBY[예비설비]
        query.append("        AND a.is_deleted = 'N'                                		");
        query.append("        AND a.is_last_version = 'Y'                           		");
        query.append("        AND a.dept_id IN (SELECT aa.dept_id FROM dbo.SFADEPT_CALL(x.comp_no, x.dept_id, '') aa )	");
        query.append("      ) 												AS eqCnt      	");
        query.append("FROM   TADEPT x						                    ");
        query.append("WHERE  x.comp_no = '"+compNo+"'	");
        query.append("  AND  x.dept_id = '"+deptId+"'	");
    
        MaDeptDetailDTO maDeptDetailDTO = 
        		(MaDeptDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaDeptDetailDTO()));

        
        return maDeptDetailDTO;
    }
    
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maDeptDetailDTO
     * @return
     */
    public int insertDetail(MaDeptDetailDTO maDeptDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;

    	query.append("INSERT INTO TADEPT(                                       	");
    	query.append("   comp_no,    dept_id,      dept_no, description,			");
    	query.append("	 dept_categ, p_dept_id,    is_use,  ord_no,wcode_id,plant, 	");
    	query.append("  twcode_id,  is_maint,     is_monitoring                 	");
    	query.append(" ,from_wcode_id	,to_wcode_id								");
    	query.append(")VALUES(							                        ");
    	query.append("	 ?,          ?,	           ?,       ?,                  ");
    	query.append("	 ?,          ISNULL(?, '0'),  ?,       ?,		?, ?,   ");
    	query.append("  ?,          ?,            ?                            	");
    	query.append(" ,?		   ,?											");
    	query.append(")													        ");
    	
    	Object[] objects = new Object[] {
    			maDeptDetailDTO.getCompNo(),
    			maDeptDetailDTO.getDeptId(),
    			maDeptDetailDTO.getDeptNo(),
    			maDeptDetailDTO.getDescription(),
    			maDeptDetailDTO.getDeptCateg(),
    			maDeptDetailDTO.getPdeptId(),
    			maDeptDetailDTO.getIsUse(),
    			maDeptDetailDTO.getOrdNo(),
    			maDeptDetailDTO.getWcodeId(),
    			maDeptDetailDTO.getPlantId(),
    			maDeptDetailDTO.getTwcodeId(),
    			maDeptDetailDTO.getIsMaint(),
    			maDeptDetailDTO.getIsMonitoring()
    		   ,maDeptDetailDTO.getFromWcodeId()
    		   ,maDeptDetailDTO.getToWcodeId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	query = new QuerySqlBuffer();
    	query.append("UPDATE TADEPT SET IS_LOWEST_LVL = 'N'					");
    	query.append("	FROM TADEPT a                    					");
    	query.append(" WHERE 1=1                                            ");
    	query.append("   AND a.comp_no = '"+maDeptDetailDTO.getCompNo()+"'	");
    	query.append("   AND a.dept_id IN (SELECT dept_id                   ");
    	query.append("                       FROM dbo.SFADEPT_CALL('"+maDeptDetailDTO.getCompNo()+"','"+maDeptDetailDTO.getDeptId()+"','')	");
    	query.append("                    )                                 ");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	
    	//만약에 해다 값이 없다면
        query = new QuerySqlBuffer();
        
        query.append("SELECT dept_id                                            ");
        query.append("  FROM dbo.SFADEPT_ALL('"+maDeptDetailDTO.getCompNo()+"','"+maDeptDetailDTO.getDeptId()+"')	");
        
        if (!QuerySqlBuffer.haveData(getJdbcTemplate().queryForList(query.toString()))) {
        	
        	query = new QuerySqlBuffer();
        	query.append("UPDATE TADEPT SET IS_LOWEST_LVL = 'Y'             	");
        	query.append(" WHERE 1=1                                            ");
        	query.append("   AND comp_no = '"+maDeptDetailDTO.getCompNo()+"' 	");
        	query.append("   AND dept_id = "+maDeptDetailDTO.getDeptId()+"		");
        	rtnValue = this.getJdbcTemplate().update(query.toString());
        	
        }
    	
    	
    	return rtnValue;
    }
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maDeptDetailDTO
     * @return
     */
    public int updateDetail(MaDeptDetailDTO maDeptDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TADEPT SET	                    ");
    	query.append("	     description    = ?,	        ");
    	query.append("	     dept_categ     = ?,	        ");
    	query.append("	     p_dept_id      = ISNULL(?, 0),	");
    	query.append("	     is_use	        = ?,	        ");
    	query.append("	     wcode_id       = ?,	        ");
    	query.append("	     twcode_id      = ?,	        ");
    	query.append("	     plant          = ?,	        ");
    	query.append("	     ord_no         = ?,		    ");
    	query.append("      is_maint        = ?,            ");
    	query.append("      is_monitoring   = ?             ");
    	query.append("     ,from_wcode_id   = ?				");
    	query.append("     ,to_wcode_id     = ?				");
    	query.append("WHERE  comp_no        = ?	            ");
    	query.append("  AND  dept_id        = ?	            ");
    	
    	Object[] objects = new Object[] {
    			maDeptDetailDTO.getDescription(),
    			maDeptDetailDTO.getDeptCateg(),
    			maDeptDetailDTO.getPdeptId(),
    			maDeptDetailDTO.getIsUse(),
    			maDeptDetailDTO.getWcodeId(),
    			maDeptDetailDTO.getTwcodeId(),
    			maDeptDetailDTO.getPlantId(),
    			maDeptDetailDTO.getOrdNo(),
    			maDeptDetailDTO.getIsMaint(),
    			maDeptDetailDTO.getIsMonitoring(),
    			maDeptDetailDTO.getFromWcodeId(),
    			maDeptDetailDTO.getToWcodeId(),
    			maDeptDetailDTO.getCompNo(),
    			maDeptDetailDTO.getDeptId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	query = new QuerySqlBuffer();
    	query.append("UPDATE TADEPT SET IS_LOWEST_LVL = 'N'					");
    	query.append("	FROM TADEPT a                    					");
    	query.append(" WHERE 1=1                                            ");
    	query.append("   AND a.comp_no = '"+maDeptDetailDTO.getCompNo()+"'	");
    	query.append("   AND a.dept_id IN (SELECT dept_id                   ");
    	query.append("                       FROM dbo.SFADEPT_CALL('"+maDeptDetailDTO.getCompNo()+"','"+maDeptDetailDTO.getDeptId()+"','')	");
    	query.append("                    )                                 ");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	
    	
    	//만약에 해다 값이 없다면
        query = new QuerySqlBuffer();
        
        query.append("SELECT dept_id                                            ");
        query.append("  FROM dbo.SFADEPT_ALL('"+maDeptDetailDTO.getCompNo()+"','"+maDeptDetailDTO.getDeptId()+"')	");
        
        if (!QuerySqlBuffer.haveData(getJdbcTemplate().queryForList(query.toString()))) {
        	
        	query = new QuerySqlBuffer();
        	query.append("UPDATE TADEPT SET IS_LOWEST_LVL = 'Y'             	");
        	query.append(" WHERE 1=1                                            ");
        	query.append("   AND comp_no = '"+maDeptDetailDTO.getCompNo()+"' 	");
        	query.append("   AND dept_id = "+maDeptDetailDTO.getDeptId()+"		");
        	rtnValue = this.getJdbcTemplate().update(query.toString());
        	
        }
        
    	
    	return rtnValue;
    }
    
    /**
     * valid deptNo
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maDeptDetailDTO
     * @return
     */
    public String validDeptNo(MaDeptDetailDTO maDeptDetailDTO)
    {
        
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT COUNT(*)                        ");
        query.append("FROM   TADEPT x                        ");
        query.append("WHERE  x.comp_no = '" + maDeptDetailDTO.getCompNo() + "'");
        query.append("  AND  x.dept_no = '" + maDeptDetailDTO.getDeptNo() + "'");
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    
    }
}