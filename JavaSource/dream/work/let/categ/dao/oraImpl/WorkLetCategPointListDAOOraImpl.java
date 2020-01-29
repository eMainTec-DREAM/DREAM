package dream.work.let.categ.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.let.categ.dao.WorkLetCategPointListDAO;
import dream.work.let.categ.dto.WorkLetCategCommonDTO;
import dream.work.let.categ.dto.WorkLetCategPointListDTO;

/**
 * 안전작업유형 - 점검항목 List Page - List DAO implements
 * @author euna0207
 * @version $Id: WorkLetCategPointListDAOOraImpl.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workLetCategPointListDAOTarget"
 * @spring.txbn id="workLetCategPointListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkLetCategPointListDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkLetCategPointListDAO
{
	public List findList(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategPointListDTO workLetCategPointListDTO, User user) throws Exception
    { 
        QueryBuffer query = new QueryBuffer();
       
        query.append("SELECT																															");
        query.append(" ''                      																				 		 AS isDelCheck		");
        query.append(", ''                    	 																					 AS seqNo			");
        query.append(", comp_no                    	 																	 			 AS compNo			");
        query.append(", woletctgpoint_id                    	 																	 AS woLetCtgPointId	");
        query.append(", step_num         																							 AS stepNum			");
        query.append(", check_position 																								 AS checkPosition	");
        query.append(", check_point 																							     AS checkPoint		");
        query.append(", check_method 																								 AS checkMethod		");
        query.append(", fit_basis     																							     AS fitBasis		");
        query.append(", SFACODE_TO_DESC(a.check_type, 'CHECK_TYPE', 'SYS', '', 'ko') 												 AS checkType		");
        query.append(", check_min                                                                          							 AS checkMin		");
        query.append(", check_basis_val                                                                  							 AS checkBasisVal	");
        query.append(", check_max                                                                         							 AS checkMax		");
        query.append(", uom                                                                                  						 AS uom				");
        query.append(", SFACODE_TO_DESC(a.is_use, 'IS_USE', 'SYS', '', 'ko')           												 AS isUse			");
        query.append(", (SELECT description FROM TAWOLETCTG WHERE comp_no = a.comp_no AND woletctg_id = a.woletctg_id)   			 AS woLetCtgId		");
        query.append("FROM TAWOLETCTGPOINT a																											");
        query.append("WHERE  1=1																														");
    	query.append(this.getWhere(workLetCategCommonDTO, workLetCategPointListDTO, user));
        query.getOrderByQuery("a.woletctgpoint_id", workLetCategPointListDTO.getOrderBy(), workLetCategPointListDTO.getDirection());
        
        
        return getJdbcTemplate().queryForList(query.toString(workLetCategPointListDTO.getIsLoadMaxCount(), workLetCategPointListDTO.getFirstRow()));
        } 

	//최상위 list와 탭의 list는 조건문이 조금 다르므로 유의해서 확인
	//왜? 최상위 list의 key값도 조건문에 추가시켜줘야하므로! DTO 파라미터가 두개
	private String getWhere(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategPointListDTO workLetCategPointListDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getStringEqualQuery("a.comp_no", user.getCompNo());
        //** 한줄 추가된것 최상위 list의 key값 
        query.getAndNumKeyQuery("a.woletctg_id", workLetCategCommonDTO.getWoLetCtgId());
        if(!"".equals(workLetCategPointListDTO.getWoLetCtgPointId())){
        	query.getAndQuery("a.woletctgpoint_id", workLetCategPointListDTO.getWoLetCtgPointId());
        	return query.toString();
        }

    	return query.toString();
    }

    public int deleteList(String id, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TAWOLETCTGPOINT			");
        query.append("WHERE  comp_no 			= ?			");
        query.append("  AND  woletctgpoint_id  	= ?			");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public String findTotalCount(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategPointListDTO workLetCategPointListDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                    ");
        query.append("      COUNT(1)                            ");
        query.append("FROM  TAWOLETCTGPOINT a					");
    	query.append("WHERE 1=1									");
    	query.append(this.getWhere(workLetCategCommonDTO, workLetCategPointListDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}