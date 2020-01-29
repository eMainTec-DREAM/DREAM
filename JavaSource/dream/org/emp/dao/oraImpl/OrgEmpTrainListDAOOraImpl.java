package dream.org.emp.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.org.emp.dao.OrgEmpTrainListDAO;
import dream.org.emp.dto.MaEmpCommonDTO;
import dream.org.emp.dto.OrgEmpTrainListDTO;

/**
 * 구매신청 item 목록 dao
 * @author  kim21017
 * @version $Id: PartAdjStkTakeListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="orgEmpTrainListDAOTarget"
 * @spring.txbn id="orgEmpTrainListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class OrgEmpTrainListDAOOraImpl extends BaseJdbcDaoSupportOra implements OrgEmpTrainListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: PartAdjStkTakeListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEmpCommonDTO
     * @param orgEmpTrainListDTO
     * @return List
     */
    public List findItemList(MaEmpCommonDTO maEmpCommonDTO, OrgEmpTrainListDTO orgEmpTrainListDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
         
        query.append("SELECT 																																		");
        query.append("        '' seqNo                         																										");
        query.append("      , '' isDelCheck            																												");
        query.append("      , x.comp_no compNo																														");                
        query.append("      , x.emptrainlist_id empTrainListId																										");
        query.append("      , x.emp_id empId																														");        
        query.append("      , x.courselist_id certListId																										 	");
        query.append("      , DECODE(x.train_fdate,'','',SUBSTR(x.train_fdate, 0, 4)||'-'||SUBSTR(x.train_fdate, 5, 2)||'-'||SUBSTR(x.train_fdate, 7, 2))||' ~ '|| 	");
        query.append("        DECODE(x.train_tdate,'','',SUBSTR(x.train_tdate, 0, 4)||'-'||SUBSTR(x.train_tdate, 5, 2)||'-'||SUBSTR(x.train_tdate, 7, 2)) trainDate	");
        query.append("      , y.description description																												");
        query.append("      , x.train_agency trainAgency																											");
        query.append("      , x.teacher teacher																														");
        query.append("      , x.place place																															");
        query.append("      , x.remark remark																														");
        query.append("  FROM  TAEMPTRAINLIST x                                                                                                                      ");
        query.append(" INNER JOIN TACOURSELIST y                                                                                                                    ");
        query.append("         ON x.courselist_id = y.courselist_id                                                                                                 ");
        query.append(this.getWhere(maEmpCommonDTO,orgEmpTrainListDTO,user));
        
        query.getOrderByQuery("x.train_fdate", maEmpCommonDTO.getOrderBy(), maEmpCommonDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(maEmpCommonDTO.getIsLoadMaxCount(), maEmpCommonDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: PartAdjStkTakeListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @param deleteRowsExt 
     * @return
     */
    public int deleteItemList(String id, User user) throws Exception
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("DELETE 	                 ");
    	query.append("  FROM TAEMPTRAINLIST      ");
    	query.append(" WHERE comp_no         = ? ");
    	query.append("   AND emptrainlist_id = ? ");
    	    	
    	Object[] objects = new Object[] {   
    	         user.getCompNo()
    	       , id
        };
    	return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    private String getWhere(MaEmpCommonDTO maEmpCommonDTO, OrgEmpTrainListDTO orgEmpTrainListDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.getAndQuery("x.comp_no", user.getCompNo());
    	query.getAndQuery("x.emp_id", maEmpCommonDTO.getEmpId());
    	
    	if (!"".equals(orgEmpTrainListDTO.getEmpTrainListId()))
        {
            query.getAndQuery("x.emptrainlist_id", orgEmpTrainListDTO.getEmpTrainListId());
            return query.toString();
        }
    	return query.toString();
    }

	@Override
	public String findTotalCount(MaEmpCommonDTO maEmpCommonDTO, OrgEmpTrainListDTO orgEmpTrainListDTO, User user)
	{
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                       ");
        query.append("       count(1)                              ");
        query.append("  FROM TAEMPTRAINLIST x                      ");
        query.append(" INNER JOIN TACOURSELIST y                   ");
        query.append("         ON x.courselist_id = y.courselist_id");
        query.append(this.getWhere(maEmpCommonDTO, orgEmpTrainListDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
	}
}
