package dream.work.rpt.mabdpoint.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.rpt.mabdpoint.dao.MaBdPointWoPlanListDAO;
import dream.work.rpt.mabdpoint.dto.MaBdPointCommonDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointWoPlanListDTO;

/**
 * 이상점검조치 - 작업계획 목록 dao
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maBdPointWoPlanListDAOTarget"
 * @spring.txbn id="maBdPointWoPlanListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaBdPointWoPlanListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaBdPointWoPlanListDAO
{
    /**
     * grid find
     * @author  syyang
     * @version $Id:$
     * @since   1.0
     * 
     * @param maBdPointCommonDTO
     * @param maBdPointWoPlanListDto
     * @param user
     * @return List
     */
    public List findList(MaBdPointCommonDTO maBdPointCommonDTO,MaBdPointWoPlanListDTO maBdPointWoPlanListDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
            query.append("SELECT														");
            query.append("    ''									AS seqNo			");
            query.append("  , ''									AS isDelCheck		");
            query.append("  , y.wkor_id 							AS wkorId			");
            query.append("  , y.wo_no 								AS woNo				");
            query.append("  , y.description 						AS woDesc			");
            query.append("  , y.wkor_date 							AS woDate			");
            query.append("  , (SELECT b.description             	    		   		");
            query.append("     FROM  TAWOEQUIP a, TAEQUIPMENT b  		   		  		");
            query.append("     WHERE a.comp_no = b.comp_no      		        		");
            query.append("      AND a.equip_id = b.equip_id 	 	  	    	    	");
            query.append("       AND  a.wkor_id = y.wkor_id		    	       	    	");
            query.append("        AND a.comp_no = y.comp_no )		AS equipDesc		");
            query.append("  , (SELECT a.description FROM TADEPT a WHERE a.comp_no = y.comp_no AND a.dept_id = y.dept_id)	AS deptDesc	");
            query.append("  , y.wo_type     						AS woType			");
            query.append("  , SFACODETODESC(y.wo_type, 'WO_TYPE', 'SYS', x.comp_no) 		AS woTypeDesc	");
            query.append("  , y.pm_type     						AS pmType			");
            query.append("  , SFACODETODESC(y.pm_type,y.wo_type||'_TYPE', 'SYS', x.comp_no)	AS pmTypeDesc	");
            query.append("  , SFACODETODESC(y.woplan_status, 'WOPLAN_STATUS', 'SYS', x.comp_no) 	AS woStatus		");
            query.append("  , y.perform 							AS remark			");
            query.append("  ,x.wongpointres_id						AS wongpointresid	");
            query.append("FROM TAWONGPOINTRES x INNER JOIN TAWOPLAN y					");
            query.append("ON x.comp_no = y.comp_no										");
            query.append("  AND x.wkor_id = y.wkor_id									");
            query.append("WHERE 1=1														");
            query.append(this.getWhere(maBdPointCommonDTO,maBdPointWoPlanListDTO,user));
            query.getOrderByQuery("x.wongpointres_id", maBdPointWoPlanListDTO.getOrderBy(), maBdPointWoPlanListDTO.getDirection());
            
            return getJdbcTemplate().queryForList(query.toString(maBdPointWoPlanListDTO.getIsLoadMaxCount(), maBdPointWoPlanListDTO.getFirstRow()));
    }
    

    public int deleteList(String id, String compNo)
    {
        QueryBuffer query = new QueryBuffer();
        
        int rtnValue  = 0;
        
        query.append("DELETE FROM TAWONGPOINTRES               	");
        query.append("WHERE wongpointres_id     = "+id+"       	");
        query.append("  AND comp_no  			= '"+compNo+"'	");
        
        rtnValue = this.getJdbcTemplate().update(query.toString());
        
        return rtnValue;

    }
    private String getWhere(MaBdPointCommonDTO maBdPointCommonDTO,MaBdPointWoPlanListDTO maBdPointWoPlanListDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	//회사
    	query.getStringEqualQuery("x.comp_no", user.getCompNo());
    	//작업요청 처리방법
    	query.getAndQuery("x.ngpointres_method", "WOPLAN");
    	//점검이상값 id
        query.getAndQuery("x.wongpoint_id", maBdPointCommonDTO.getWoNgPointId());
        // 작업상태
        query.getSysCdQuery("y.woplan_status", maBdPointWoPlanListDTO.getWoStatusId(), "", "WOPLAN_STATUS", user.getCompNo(),user.getLangId());
        
        return query.toString();
    }


	@Override
	public String findTotalCount(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoPlanListDTO maBdPointWoPlanListDTO, User user) throws Exception
	{
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT										");
        query.append("       COUNT(1)     							");
        query.append("FROM TAWONGPOINTRES x INNER JOIN TAWOPLAN y	");
        query.append("ON x.comp_no = y.comp_no						");
        query.append("  AND x.wkor_id = y.wkor_id					");
        query.append("WHERE 1=1										");
        query.append(this.getWhere(maBdPointCommonDTO,maBdPointWoPlanListDTO,user));
	
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);

	}

	public String validWkorId(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoPlanListDTO maBdPointWoPlanListDTO, User user) throws Exception
	{
    	QueryBuffer query = new QueryBuffer();

    	query.append("SELECT count(*) 			");
    	query.append("FROM TAWONGPOINTRES		");
    	query.append("WHERE 1=1					");
    	query.getAndQuery("comp_no", user.getCompNo());
    	query.getAndQuery("wongpointres_id", maBdPointWoPlanListDTO.getWoNgPointResId());
    	query.getAndQuery("wongpoint_id", maBdPointCommonDTO.getWoNgPointId());
    	query.getAndQuery("wkor_id", maBdPointWoPlanListDTO.getWkorId());
    	
    	return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
	}
	
	public int updateWoPlanPmPointRepStatus(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoPlanListDTO maBdPointWoPlanListDTO, User user) throws Exception 
	{
		QueryBuffer query = new QueryBuffer();
		
		int rtnValue  = 0;

        query.append("UPDATE TAWONGPOINT SET 											");
    	query.append(" 	pm_point_rep_status =											");
    	query.append("			(	CASE WHEN											");
    	query.append("            			(SELECT count(*) 							");
    	query.append("             			 FROM TAWOPLAN  								");
    	query.append("             			 WHERE 1=1  								");
    	query.append("             			 AND comp_no = ?							");
    	query.append("             			 AND woplan_status != ?   						");
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
	    		, "PPC"
	    		, user.getCompNo()
	    		, maBdPointCommonDTO.getWoNgPointId()
	    		, "WOPLAN"
	    		
	    		, user.getCompNo()
	    		, maBdPointCommonDTO.getWoNgPointId()
	    };
        
        rtnValue = getJdbcTemplate().update(query.toString(), objects);
        
		return rtnValue;
	}
	
	public int insertWoNgPointRes(MaBdPointCommonDTO maBdPointCommonDTO, MaBdPointWoPlanListDTO maBdPointWoPlanListDTO, User user) throws Exception 
	{
		QueryBuffer query = new QueryBuffer();
		
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
        query.append("  , TO_CHAR(SYSDATE,'YYYYMMDD')	");
        query.append("  , TO_CHAR(SYSDATE,'HH24MI')		");
        query.append("  , ?	     	                	");
        query.append("  , ?	     	                	");
        query.append(" )    	                    	");
  
        Object[] objects = new Object[] {
                user.getCompNo()
              , maBdPointWoPlanListDTO.getWoNgPointResId()
              , maBdPointCommonDTO.getWoNgPointId()
              , "WOPLAN"
              , maBdPointWoPlanListDTO.getWkorId()
              , user.getDeptId()
              , user.getEmpId()
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
	}
	
}