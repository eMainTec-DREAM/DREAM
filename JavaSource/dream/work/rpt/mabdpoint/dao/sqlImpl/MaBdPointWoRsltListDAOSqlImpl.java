package dream.work.rpt.mabdpoint.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.rpt.mabdpoint.dao.MaBdPointWoRsltListDAO;
import dream.work.rpt.mabdpoint.dto.MaBdPointCommonDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointWoRsltListDTO;

/**
 * 이상점검조치 - 작업결과 목록 dao
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maBdPointWoRsltListDAOTarget"
 * @spring.txbn id="maBdPointWoRsltListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaBdPointWoRsltListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaBdPointWoRsltListDAO
{
    /**
     * grid find
     * @author  syyang
     * @version $Id:$
     * @since   1.0
     * 
     * @param maBdPointCommonDTO
     * @param maBdPointWoRsltListDTO
     * @param user
     * @return List
     */
    public List findList(MaBdPointCommonDTO maBdPointCommonDTO,MaBdPointWoRsltListDTO maBdPointWoRsltListDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT														");
        query.append("    ''									AS seqNo			");
        query.append("  , ''									AS isDelCheck		");
        query.append("  , y.wkor_id 							AS wkorId			");
        query.append("  , y.wo_no 								AS woNo				");
        query.append("  , y.description 						AS woDesc			");
        query.append("  , y.wkor_date 							AS woDate			");
        query.append("  , (SELECT b.description         							");
        query.append("     FROM TAWOEQUIP a, TAEQUIPMENT b  						");
        query.append("     WHERE a.comp_no = b.comp_no 							  	");
        query.append("      AND a.equip_id = b.equip_id     						");
        query.append("       AND a.wkor_id = y.wkor_id       						");
        query.append("        AND a.comp_no = y.comp_no) 		AS equipDesc		");
        query.append("  , (SELECT a.description FROM TADEPT a WHERE a.comp_no = y.comp_no AND a.dept_id = y.dept_id)	AS deptDesc		");
        query.append("  , y.wo_type     						AS woType			");
        query.append("  , dbo.SFACODETODESC(y.wo_type, 'WO_TYPE', 'SYS', x.comp_no) 			AS woTypeDesc	");
        query.append("  , y.pm_type     						AS pmType			");
        query.append("  , dbo.SFACODETODESC(y.pm_type, y.wo_type+'_TYPE', 'SYS', x.comp_no)		AS pmTypeDesc	");
        query.append("  , dbo.SFACODETODESC(y.wo_status, 'WO_STATUS', 'SYS', x.comp_no)			AS woStatus		");
        query.append("  , y.perform 							AS remark			");
        query.append("  , (SELECT a.param1 FROM TACDSYSD a WHERE a.list_Type= y.wo_type+'_TYPE' AND a.cdsysd_no=y.pm_type)	AS param	");
        query.append("  ,x.wongpointres_id						AS wongpointresid	");
        query.append("FROM TAWONGPOINTRES x INNER JOIN TAWORKORDER y				");
        query.append("ON x.comp_no = y.comp_no										");
        query.append("AND x.wkor_id = y.wkor_id										");
        query.append("WHERE 1=1														");
        query.append(this.getWhere(maBdPointCommonDTO,maBdPointWoRsltListDTO,user));
        query.getOrderByQuery("x.wongpointres_id", "x.wongpointres_id", maBdPointWoRsltListDTO.getOrderBy(), maBdPointWoRsltListDTO.getDirection());
            
        return getJdbcTemplate().queryForList(query.toString(maBdPointWoRsltListDTO.getIsLoadMaxCount(), maBdPointWoRsltListDTO.getFirstRow()));

    }
    

    public int deleteList(String id, String compNo)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        int rtnValue  = 0;

        query.append("DELETE FROM TAWONGPOINTRES            ");
        query.append("WHERE wongpointres_id = "+id+"       	");
        query.append("  AND comp_no  = '"+compNo+"'        ");
        
        rtnValue = this.getJdbcTemplate().update(query.toString());
        
        return rtnValue;

    }
    private String getWhere(MaBdPointCommonDTO maBdPointCommonDTO,MaBdPointWoRsltListDTO maBdPointWoRsltListDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	//회사
    	query.getStringEqualQuery("x.comp_no", user.getCompNo());
    	//작업요청 처리방법
    	query.getAndQuery("x.ngpointres_method", "WORSLT");
    	//점검이상 값 id
        query.getAndQuery("x.wongpoint_id", maBdPointCommonDTO.getWoNgPointId());
        //점검이상 값 id
        query.getAndQuery("x.wongpointres_id", maBdPointWoRsltListDTO.getWoNgPointResId());
        // 작업상태
        query.getSysCdQuery("y.wo_status", maBdPointWoRsltListDTO.getWoStatusId(), "", "WO_STATUS", user.getCompNo(),user.getLangId());

        return query.toString();
    }


	@Override
	public String findTotalCount(MaBdPointCommonDTO maBdPointCommonDTO,MaBdPointWoRsltListDTO maBdPointWoRsltListDTO, User user) throws Exception
	{
		QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT											");
        query.append("       COUNT(1)     								");
        query.append("FROM TAWONGPOINTRES x INNER JOIN TAWORKORDER y	");
        query.append("ON x.comp_no = y.comp_no							");
        query.append("AND x.wkor_id = y.wkor_id							");
        query.append("WHERE 1=1											");
        query.append(this.getWhere(maBdPointCommonDTO,maBdPointWoRsltListDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
	}
	
	
	@Override
	public String validWkorId(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoRsltListDTO maBdPointWoRsltListDTO, User user) throws Exception
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT count(*) 			");
		query.append("FROM TAWONGPOINTRES		");
		query.append("WHERE 1=1					");
		query.getAndQuery("comp_no", user.getCompNo());
		query.getAndQuery("wongpointres_id", maBdPointWoRsltListDTO.getWoNgPointResId());
		query.getAndQuery("wongpoint_id", maBdPointCommonDTO.getWoNgPointId());
		query.getAndQuery("wkor_id", maBdPointWoRsltListDTO.getWkorId());
		
		return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
	}
	
	
	@Override
	public int updateWoRsltPmPointRepStatus(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoRsltListDTO maBdPointWoRsltListDTO, User user) throws Exception
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		int rtnValue  = 0;

        query.append("UPDATE TAWONGPOINT SET 											");
    	query.append(" 	pm_point_rep_status =											");
    	query.append("			(	CASE WHEN											");
    	query.append("            			(SELECT count(*) 							");
    	query.append("             			 FROM TAWORKORDER  								");
    	query.append("             			 WHERE 1=1  								");
    	query.append("             			 AND comp_no = ?							");
    	query.append("             			 AND wo_status != ?   						");
    	query.append("             			 AND wkor_id IN (SELECT wkor_id  			");
    	query.append("             			 				 FROM TAWONGPOINTRES   		");
    	query.append("             			 				 WHERE  1=1   				");
    	query.append("             			 				 AND comp_no = ?	    	");
    	query.append("             			 				 AND wongpoint_id = ?      	");
    	query.append("             			 				 AND ngpointres_method = ?	");
    	query.append("             			 				 AND wkor_id IS NOT NULL	");
    	query.append("             			 				)                    		");
    	query.append("         				)=0											");
    	query.append("					 THEN 'GD' ELSE pm_point_rep_status 			");
    	query.append("				END	)												");
    	query.append("WHERE 1=1															");
    	query.append("AND comp_no = ?													");
		query.append("AND wongpoint_id = ?  											");									
         
    	
	    Object[] objects = new Object[] {
	    		user.getCompNo()
	    		, "C"
	    		, user.getCompNo()
	    		, maBdPointCommonDTO.getWoNgPointId()
	    		, "WORSLT"
	    		
	    		, user.getCompNo()
	    		, maBdPointCommonDTO.getWoNgPointId()
	    };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
	}


	@Override
	public int insertWoNgPointRes(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoRsltListDTO maBdPointWoRsltListDTO, User user) throws Exception
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		int rtnValue  = 0;
		
		query.append("INSERT INTO TAWONGPOINTRES (  	");
        query.append("    comp_no               		");
        query.append("  , wongpointres_id          		");
        query.append("  , wongpoint_id           		");
        query.append("  , ngpointres_method       		");
        query.append("  , wkor_id		           		");
        query.append("  , res_date           			");
        query.append("  , res_time           			");
        query.append("  , dept_id	        	   		");
        query.append("  , emp_id    		       		");
        query.append(")VALUES(      	            	");
        query.append("    ?             	        	");
        query.append("  , ?                 	    	");
        query.append("  , ?                     		");
        query.append("  , ?                     		");
        query.append("  , ?	     	                	");
        query.append("  , CONVERT(varchar, GETDATE(),112)						");
        query.append("  , REPLACE(CONVERT(VARCHAR(12), GETDATE(),108),':','')	");
        query.append("  , ?	     	                	");
        query.append("  , ?	     	                	");
        query.append(" )    	                    	");
  
        Object[] objects = new Object[] {
                user.getCompNo()
              , maBdPointWoRsltListDTO.getWoNgPointResId()
              , maBdPointCommonDTO.getWoNgPointId()
              , "WORSLT"
              , maBdPointWoRsltListDTO.getWkorId()
              , user.getDeptId()
              , user.getEmpId()
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
	}
    
}