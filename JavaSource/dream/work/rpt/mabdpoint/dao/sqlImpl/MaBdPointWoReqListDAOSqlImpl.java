package dream.work.rpt.mabdpoint.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.work.rpt.mabdpoint.dao.MaBdPointWoReqListDAO;
import dream.work.rpt.mabdpoint.dto.MaBdPointCommonDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointWoReqListDTO;

/**
 * 이상점검조치 - 작업요청 목록 dao
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maBdPointWoReqListDAOTarget"
 * @spring.txbn id="maBdPointWoReqListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaBdPointWoReqListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaBdPointWoReqListDAO
{
    /**
     * grid find
     * @author  syyang
     * @version $Id:$
     * @since   1.0
     *
     * @param maBdPointWoReqListDTO
     * @return List
     */
	public List findList(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoReqListDTO maBdPointWoReqListDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

            query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;				");
            query.append("SELECT														");
	        query.append("		''									AS seqNo			");
	        query.append("		,''									AS isDelCheck		");
	        query.append("		,y.woreq_id							AS woReqId			");
	        query.append("		,y.woreq_no							AS woReqNo			");
	        query.append("		,														");
	        query.getDate("y.req_date", "reqDate");
	        query.append("      ,                                   					");
            query.getDate("y.req_com_date", "reqComDate");	
	        query.append("		,y.description						AS reqDesc			");
	        query.append("		,(SELECT a.description									");
	        query.append("			FROM TAEQUIPMENT a									");
	        query.append("			WHERE a.comp_no = y.comp_no							");
	        query.append("			AND	a.equip_id = y.equip_id)	AS equipDesc		");
	        query.append("		,(SELECT a.full_desc									");
	        query.append("			FROM TAEQLOC a										");
	        query.append("			WHERE a.comp_no = y.comp_no							");
	        query.append("			AND	a.eqloc_id = y.eqloc_id)	AS eqLocDesc		");
	        query.append("        ,y.woreq_status       			AS woReqStatus 		");
	        query.append("		,dbo.SFACODE_TO_DESC(y.woreq_status,'WOREQ_STATUS','SYS','','"+user.getLangId()+"')		AS woReqStatusDesc	");
	        query.append("		,(SELECT a.emp_name										");
	        query.append("			FROM TAEMP a										");
	        query.append("			WHERE a.comp_no = y.comp_no							");
	        query.append("			AND a.emp_id = y.req_emp_id)	AS reqBy		    ");
	        query.append("		,(SELECT a.description									");
	        query.append("		   FROM TADEPT a										");
	        query.append("		   WHERE a.comp_no = y.comp_no							");
	        query.append("		   AND a.dept_id = y.req_dept_id)	AS reqDept			");	
	        query.append("      ,(SELECT a.description	          						");
            query.append("         FROM TAPLANT a              				     		");
            query.append("         WHERE a.comp_no = y.comp_no  		    			");
            query.append("         AND a.plant = y.plant)			AS plantDesc  	 	");
            query.append("		,(select param1 from tacdsysd where list_type='WOREQ_TYPE' AND cdsysd_no = y.woreq_type)		AS param			");
            query.append("      ,y.woreq_type      					AS woReqType		");
            query.append("      ,(SELECT description FROM tacdsysd WHERE list_type='WOREQ_TYPE' AND cdsysd_no = y.woreq_type)	AS woReqTypeDesc	");
            query.append("      ,x.wongpointres_id					AS wongpointresid	");
            query.append("FROM TAWONGPOINTRES x INNER JOIN TAWOREQ y					");
            query.append("ON x.comp_no = y.comp_no										");
            query.append("  AND x.woreq_id = y.woreq_id									");
            query.append("WHERE 1 = 1													");
            query.append(this.getWhere(maBdPointCommonDTO, maBdPointWoReqListDTO, user));
            query.getOrderByQuery("x.wongpointres_id", "x.wongpointres_id", maBdPointWoReqListDTO.getOrderBy(), maBdPointWoReqListDTO.getDirection());
            
            return getJdbcTemplate().queryForList(query.toString(maBdPointWoReqListDTO.getIsLoadMaxCount(), maBdPointWoReqListDTO.getFirstRow()));

    }

    public int deleteList(String id, String compNo)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;

        query.append("DELETE FROM TAWONGPOINTRES				");
        query.append("WHERE wongpointres_id		= "+id+"       	");
        query.append("  AND comp_no  			= '"+compNo+"' 	");

        rtnValue = this.getJdbcTemplate().update(query.toString());

        return rtnValue;
    }

    private String getWhere(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoReqListDTO maBdPointWoReqListDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
        
    	// 회사
        query.getAndQuery("x.comp_no", user.getCompNo());
        // 작업요청처리 방법
        query.getAndQuery("x.ngpointres_method", "WOREQ");
        //점검이상값 id
        query.getAndQuery("x.wongpoint_id", maBdPointCommonDTO.getWoNgPointId());
        // 작업요청 상태
        query.getSysCdQuery("y.woreq_status", maBdPointWoReqListDTO.getWoReqStatusId(), "", "WOREQ_STATUS", user.getCompNo(),user.getLangId());
        
        return query.toString();
    }

	@Override
	public String findTotalCount(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoReqListDTO maBdPointWoReqListDTO, User user) throws Exception {
		QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;		");
        query.append("SELECT										");
        query.append("       COUNT(1)                           	");
        query.append("FROM TAWONGPOINTRES x INNER JOIN TAWOREQ y	");
        query.append("ON x.comp_no = y.comp_no						");
        query.append("  AND x.woreq_id = y.woreq_id					");
        query.append("WHERE 1 = 1									");
        query.append(this.getWhere(maBdPointCommonDTO, maBdPointWoReqListDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
	}

	@Override
	public int insertWoNgPointRes(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoReqListDTO maBdPointWoReqListDTO, User user) throws Exception
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		int rtnValue  = 0;
		
		query.append("INSERT INTO TAWONGPOINTRES (  	");
        query.append("    comp_no               		");
        query.append("  , wongpointres_id          		");
        query.append("  , wongpoint_id           		");
        query.append("  , ngpointres_method       		");
        query.append("  , woreq_id		           		");
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
        query.append("  , CONVERT(varchar, GETDATE(),112)	");
        query.append("  , REPLACE(CONVERT(VARCHAR(12), GETDATE(),108),':','')	");
        query.append("  , ?	     	                	");
        query.append("  , ?	     	                	");
        query.append(" )    	                    	");
  
        Object[] objects = new Object[] {
                user.getCompNo()
              , maBdPointWoReqListDTO.getWoNgPointResId()
              , maBdPointCommonDTO.getWoNgPointId()
              , "WOREQ"
              , maBdPointWoReqListDTO.getWoReqId()
              , user.getDeptId()
              , user.getEmpId()
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
	}

	@Override
	public String validWoReqId(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoReqListDTO maBdPointWoReqListDTO, User user) throws Exception
	{
		QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("SELECT count(*) 			");
    	query.append("FROM TAWONGPOINTRES		");
    	query.append("WHERE 1=1					");
    	query.getAndQuery("comp_no", user.getCompNo());
    	query.getAndQuery("wongpoint_id", maBdPointCommonDTO.getWoNgPointId());
    	query.getAndQuery("woreq_id", maBdPointWoReqListDTO.getWoReqId());
    	
    	return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
	}

	@Override
	public int updateWoReqPmPointRepStatus(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoReqListDTO maBdPointWoReqListDTO, User user) throws Exception
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;

        query.append("UPDATE TAWONGPOINT SET    									                    ");
    	query.append(" 	pm_point_rep_status =    									                    ");
    	query.append("			(	CASE WHEN															");
    	query.append("            			(SELECT count(*) 											");
    	query.append("             			 FROM TAWOREQ  												");
    	query.append("             			 WHERE 1=1  												");
    	query.append("             			 AND comp_no = ?								    		");
    	query.append("             			 AND woreq_status != ?   									");
    	query.append("             			 AND woreq_id IN (SELECT woreq_id  							");
    	query.append("             			 				  FROM TAWONGPOINTRES   					");
    	query.append("             			 				  WHERE  1=1   								");
    	query.append("             			 				  AND comp_no = ?	    					");
    	query.append("             			 				  AND wongpoint_id = ?      				");
    	query.append("             			 				  AND ngpointres_method = ?     			");
    	query.append("             			 				  AND woreq_id IS NOT NULL     				");
    	query.append("             			 				 )                    						");
    	query.append("         				)=0															");
    	query.append("					 THEN 'GD' ELSE pm_point_rep_status 							");
    	query.append("				END	)																");
    	query.append("WHERE 1=1																			");
    	query.append("AND comp_no = ?																	");
		query.append("AND wongpoint_id = ?  															");									
         
    	
	    Object[] objects = new Object[] {
	    		user.getCompNo()
	    		, "COM"
	    		, user.getCompNo()
	    		, maBdPointCommonDTO.getWoNgPointId()
	    		, "WOREQ"
	    		
	    		, user.getCompNo()
	    		, maBdPointCommonDTO.getWoNgPointId()
	    };
        
        rtnValue = getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
	}


}