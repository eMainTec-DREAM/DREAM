package dream.work.let.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.let.dao.WorkLetListDAO;
import dream.work.let.dto.WorkLetCommonDTO;

/**
 * �����۾� - ��� dao
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
     * Filter ����
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
        
        // CommonDTO�� woLetId�� �ִ°��� �󼼿��� ���� �Ǿ List�� �� Row���� ����ȸ
        if (!"".equals(workLetCommonDTO.getWoLetId()))
        {
            query.getAndQuery("x.wolet_id", workLetCommonDTO.getWoLetId());
            return query.toString();
        }
        
        //�����۾���
        query.getLikeQuery("x.description", workLetCommonDTO.getFilterWoLetDesc());
        //�����۾���ȣ
        query.getAndQuery("x.wolet_no", workLetCommonDTO.getFilterWoLetNo());
     
        //��û�μ�
        query.getDeptLevelQuery("x.req_dept", workLetCommonDTO.getFilterReqDeptId(), workLetCommonDTO.getFilterReqDeptDesc(), compNo);
        //��û��
        query.getCodeLikeQuery("x.req_by", "(SELECT a.emp_name FROM TAEMP a WHERE a.emp_id = x.req_id AND a.comp_no=x.comp_no)", 
        		workLetCommonDTO.getFilterReqById(), workLetCommonDTO.getFilterReqByDesc());
        //�㰡�μ�
        query.getDeptLevelQuery("x.rec_dept", workLetCommonDTO.getFilterRecDeptId(), workLetCommonDTO.getFilterRecDeptDesc(), compNo);
        //�㰡��
        query.getCodeLikeQuery("x.rec_by", "(SELECT a.emp_name FROM TAEMP a WHERE a.emp_id = x.rec_id AND a.comp_no=x.comp_no)", 
        		workLetCommonDTO.getFilterRecById(), workLetCommonDTO.getFilterRecByDesc());
     
        //����/���
        query.getLikeQuery("x.item_desc", workLetCommonDTO.getFilterItemDesc());
        //�۾����
        query.getLikeQuery("x.place", workLetCommonDTO.getFilterPlace());
        
        //�����ڵ�
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM TAPLANT a WHERE a.comp_no = '"+compNo+"' AND a.plant = x.plant )", 
                workLetCommonDTO.getFilterPlant(), workLetCommonDTO.getFilterPlantDesc());
        //�۾�#
        query.getCodeLikeQuery("x.wo_no", "(SELECT a.wo_no FROM TAWORKORDER a WHERE a.comp_no = '"+compNo+"' AND a.wkor_id = x.wkor_id)", 
        		workLetCommonDTO.getFilterWkorId(), workLetCommonDTO.getFilterWoNo());
        
        //�۾��Ⱓ From
        query.getAndDateQuery("x.start_date", fromStartDate, fromEndDate);
        //�۾��Ⱓ To
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