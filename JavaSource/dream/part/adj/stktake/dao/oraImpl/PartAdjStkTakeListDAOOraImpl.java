package dream.part.adj.stktake.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.part.adj.stktake.dao.PartAdjStkTakeListDAO;
import dream.part.adj.stktake.dto.PartAdjStkTakeCommonDTO;

/**
 * 부품실사 dao
 * @author  kim21017
 * @version $Id: PartAdjStkTakeListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="partAdjStkTakeListDAOTarget"
 * @spring.txbn id="partAdjStkTakeListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PartAdjStkTakeListDAOOraImpl extends BaseJdbcDaoSupportOra implements PartAdjStkTakeListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: PartAdjStkTakeListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param partAdjStkTakeCommonDTO
     * @return List
     */
    public List findItemList(PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT												");
        query.append("       '' seqNo,										");
        query.append("       '' isDelCheck,									");
        query.append("       x.ptstktakelist_no cntr,						");
        query.append("       x.ptstktake_status ptStkTakeStatus,			");
        query.append("       x.plan_date planDate,							");
        query.append("       x.description title,							");
        query.append("       (SELECT y.wname FROM TAWAREHOUSE y where x.comp_no = y.comp_no AND x.wcode_id=y.wcode_id) wname,  	");
        query.append("		SFACODE_TO_DESC(x.ptstktake_status,'PTSTKTAKE_STATUS','SYS','','"+user.getLangId()+"') 			status,	");
        query.append("       x.act_date actDate,                                    ");
        query.append("       x.reg_date regDate,                                    ");
        query.append("      (SELECT a.emp_name                              ");
        query.append("       FROM   TAEMP a                                 ");
        query.append("       WHERE  a.comp_no = x.comp_no                   ");
        query.append("         AND  a.emp_id = x.reg_id) creBy              ");
        query.append("      , x.ptstktakelist_id ptStkTakeListId			");
        query.append("      ,x.plant			 		plant				");
        query.append("      ,(SELECT a.description          				");
        query.append("        FROM TAPLANT a                   				");
        query.append("        WHERE a.comp_no = x.comp_no      				");
        query.append("          AND a.plant = x.plant            			");
        query.append("       )                    		plantDesc   		");
        query.append("       ,lapp_no              		lappNo   			");
        query.append("  FROM TAPTSTKTAKELIST x								");
        query.append("WHERE  1=1               								");
        query.append(this.getWhere(partAdjStkTakeCommonDTO, user));
        query.getOrderByQuery("x.ptstktakelist_no", partAdjStkTakeCommonDTO.getOrderBy(), partAdjStkTakeCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(partAdjStkTakeCommonDTO.getIsLoadMaxCount(), partAdjStkTakeCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: PartAdjStkTakeListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param partAdjStkTakeCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        String compNo = partAdjStkTakeCommonDTO.getCompNo();
        
        query.getAndQuery("x.comp_no", compNo);
        if (!"".equals(partAdjStkTakeCommonDTO.getPtStkTakeListId()))
        {
            query.getAndQuery("x.ptstktakelist_id", partAdjStkTakeCommonDTO.getPtStkTakeListId());
            return query.toString();
        }
        query.getLikeQuery("x.description", partAdjStkTakeCommonDTO.getFilterPtPrListDesc());


      //담당자
        query.getCodeLikeQuery("x.reg_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.reg_id AND a.comp_no='"+compNo+"')", 
        		partAdjStkTakeCommonDTO.getFilterUserId(), partAdjStkTakeCommonDTO.getFilterUserDesc());

     // 창고명
        if(!"".equals(partAdjStkTakeCommonDTO.getFilterWcodeId()))
        {
            query.getLikeQuery("x.wcode_id", partAdjStkTakeCommonDTO.getFilterWcodeId());
        }
        else if(!"".equals(partAdjStkTakeCommonDTO.getFilterWname()))
        {
            query.append(" AND  x.wcode_id IN (SELECT a.wcode_id FROM TAWAREHOUSE a ");
            query.append("                     WHERE  a.comp_no = x.comp_no       ");
            query.getLikeQuery("a.wname", partAdjStkTakeCommonDTO.getFilterWname());
            query.append("					   )						        ");
        }
        
        // 계획일자
        query.getAndDateQuery("x.plan_date", partAdjStkTakeCommonDTO.getFilterStartPlanDate(), partAdjStkTakeCommonDTO.getFilterEndPlanDate());
      
        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no=x.comp_no AND a.plant=x.plant )", 
        		partAdjStkTakeCommonDTO.getFilterPlantId(), partAdjStkTakeCommonDTO.getFilterPlantDesc());
        
        return query.toString();
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: PartAdjStkTakeListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteItem(String id, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("DELETE FROM TAPTSTKTAKEITEM					");
    	query.append("WHERE 1=1									");
    	query.getAndQuery("comp_no", user.getCompNo());
    	query.getAndQuery("ptstktakelist_id", id);
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAPTSTKTAKELIST			");
    	query.append("WHERE ptstktakelist_id = '"+id+"'		");
    	query.getAndQuery("comp_no", user.getCompNo());
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	return rtnValue;
    }

	@Override
	public String findTotalCount(PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO, User user) throws Exception {

		QueryBuffer query = new QueryBuffer();

        query.append("SELECT						");
        query.append("       COUNT(1)               ");
        query.append("  FROM TAPTSTKTAKELIST x		");
        query.append("WHERE  1=1               		");
        query.append(this.getWhere(partAdjStkTakeCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);

	}

}