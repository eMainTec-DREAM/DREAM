package dream.work.let.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.let.dao.WorkLetListDAO;
import dream.work.let.dto.WorkLetCommonDTO;

/**
 * 안전작업 - 목록 dao
 * @author  syyang
 * @version $Id: WorkLetListDAO.java,v 1.0 2015/12/02 09:14:12 syyang Exp $
 * @since   1.0
 * @spring.bean id="workLetListDAOTarget"
 * @spring.txbn id="workLetListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkLetListDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkLetListDAO
{
    /**
     * grid find
     * @author  syyang
     * @version $Id: WorkLetListDAO.java,v 1.0 2015/12/02 09:14:12 syyang Exp $
     * @since   1.0
     * 
     * @param workLetCommonDTO
     * @return List
     */
    public List findWoLetList(WorkLetCommonDTO workLetCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT																		");
        query.append("		''															seqNo		");
        query.append("		,''															isDelCheck	");
        query.append("		,x.wolet_id													woLetId		");
        query.append("		,x.wolet_no 												woletNo		");
        query.append("		,x.description 												description	");
        query.append("		,x.item_desc 												itemDesc	");
        query.append("		,x.wolet_status												woLetStatus	");
        query.append("		,SFACODE_TO_DESC(x.wolet_status,'WOLET_STATUS','SYS','','"+user.getLangId()+"')	woLetStatusDesc	");
        query.append("		,x.place 													place		");
        query.append("		,(    																	");
        query.append("      	to_char(to_date(x.start_date,'YYYYMMDD'),'YYYY-MM-DD') ||' '|| to_char(to_date(x.start_time,'hh24mi'),'hh24:mi')	");
        query.append("          ||' ~ '||															");
        query.append("          to_char(to_date(x.end_date,'YYYYMMDD'),'YYYY-MM-DD') ||' '|| to_char(to_date(x.end_time,'hh24mi'),'hh24:mi')		");
        query.append("       )    														woLetDate   ");
        query.append("		,x.req_by 													reqBy		");
        query.append("		,(SELECT aa.emp_name													");
        query.append("		  FROM TAEMP aa															");
        query.append("		  WHERE aa.comp_no = x.comp_no											");
        query.append("		   AND aa.emp_id = x.req_by ) 								reqByDesc	");
        query.append("		,x.rec_by 													recBy		");
        query.append("		,(SELECT aa.emp_name													");
        query.append("		  FROM TAEMP aa															");
        query.append("		  WHERE aa.comp_no = x.comp_no											");
        query.append("		   AND aa.emp_id = x.rec_by ) 								recByDesc	");
        query.append("		,x.plant 													plant		");
        query.append("		,(SELECT aa.description													");
        query.append("		  FROM TAPLANT aa														");
        query.append("		  WHERE aa.comp_no = x.comp_no											");
        query.append("		   AND aa.plant = x.plant ) 								plantDesc	");
        query.append("		,x.wkor_id													wkOrId		");
        query.append("		,(SELECT aa.wo_no														");
        query.append("		  FROM TAWORKORDER aa													");
        query.append("		  WHERE aa.comp_no = x.comp_no											");
        query.append("		   AND aa.wkor_id = x.wkor_id ) 							woNo		");
        query.append("      ,x.remark                                               	remark 		");
        query.append("FROM TAWOLET x																");
        query.append("WHERE 1=1																		");
        query.append(this.getWhere(workLetCommonDTO,user));
        query.getOrderByQuery("x.wolet_no", workLetCommonDTO.getOrderBy(), workLetCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workLetCommonDTO.getIsLoadMaxCount(), workLetCommonDTO.getFirstRow()));
    }

    /**
     * delete
     * @author syyang
     * @version $Id: WorkLetListDAO.java,v 1.0 20155/12/02 08:25:47 syyang Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteWoLet(String id, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;
    	
    	query.append("DELETE FROM TAWOLET		");
        query.append("WHERE COMP_NO  = ?        ");
    	query.append("  AND wolet_id =  ?       ");
    	
    	Object[] objects = new Object[]{
    			user.getCompNo()
        		,id
        };
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString(),objects);
    	
    	return rtnValue;
    }
    
    /**
     * Filter 조건
     * @author  syyang
     * @version $Id: WorkLetListDAO.java,v 1.0 2015/12/02 09:14:12 syyang Exp $
     * @since   1.0
     * 
     * @param workLetCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(WorkLetCommonDTO workLetCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();

        String compNo  = user.getCompNo();
        String fromStartDate = workLetCommonDTO.getFilterFromStartDate();
        String fromEndDate = workLetCommonDTO.getFilterFromEndDate();
        String toStartDate = workLetCommonDTO.getFilterToStartDate();
        String toEndDate = workLetCommonDTO.getFilterToEndDate();
        
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        
        // CommonDTO의 woLetId가 있는경우는 상세에서 수정 되어서 List의 한 Row만을 재조회
        if (!"".equals(workLetCommonDTO.getWoLetId()))
        {
            query.getAndQuery("x.wolet_id", workLetCommonDTO.getWoLetId());
            return query.toString();
        }
        
        //안전작업명
        query.getLikeQuery("x.description", workLetCommonDTO.getFilterWoLetDesc());
        //안전작업번호
        query.getAndQuery("x.wolet_no", workLetCommonDTO.getFilterWoLetNo());
     
        //신청부서
        query.getDeptLevelQuery("x.req_dept", workLetCommonDTO.getFilterReqDeptId(), workLetCommonDTO.getFilterReqDeptDesc(), compNo);
        //신청자
        query.getCodeLikeQuery("x.req_by", "(SELECT a.emp_name FROM TAEMP a WHERE a.emp_id = x.req_id AND a.comp_no=x.comp_no)", 
        		workLetCommonDTO.getFilterReqById(), workLetCommonDTO.getFilterReqByDesc());
        //허가부서
        query.getDeptLevelQuery("x.rec_dept", workLetCommonDTO.getFilterRecDeptId(), workLetCommonDTO.getFilterRecDeptDesc(), compNo);
        //허가자
        query.getCodeLikeQuery("x.rec_by", "(SELECT a.emp_name FROM TAEMP a WHERE a.emp_id = x.rec_id AND a.comp_no=x.comp_no)", 
        		workLetCommonDTO.getFilterRecById(), workLetCommonDTO.getFilterRecByDesc());
     
        //설비/기기
        query.getLikeQuery("x.item_desc", workLetCommonDTO.getFilterItemDesc());
        //작업장소
        query.getLikeQuery("x.place", workLetCommonDTO.getFilterPlace());
        
        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM TAPLANT a WHERE a.comp_no = '"+compNo+"' AND a.plant = x.plant )", 
                workLetCommonDTO.getFilterPlant(), workLetCommonDTO.getFilterPlantDesc());
        //작업#
        query.getCodeLikeQuery("x.wo_no", "(SELECT a.wo_no FROM TAWORKORDER a WHERE a.comp_no = '"+compNo+"' AND a.wkor_id = x.wkor_id)", 
        		workLetCommonDTO.getFilterWkorId(), workLetCommonDTO.getFilterWoNo());
        
        //작업기간 From
        query.getAndDateQuery("x.start_date", fromStartDate, fromEndDate);
        //작업기간 To
        query.getAndDateQuery("x.end_date", toStartDate, toEndDate);

        
        return query.toString();
    }

    public String findTotalCount(WorkLetCommonDTO workLetCommonDTO,User user, String woType)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT							");
        query.append("      COUNT(1)                	");
        query.append("FROM TAWOLET x					");
        query.append("WHERE 1=1							");
        query.append(this.getWhere(workLetCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
    }

}