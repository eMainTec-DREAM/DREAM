package dream.consult.program.guide.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.consult.program.guide.dao.ConsultPgmGuideListDAO;
import dream.consult.program.guide.dto.ConsultPgmGuideCommonDTO;

/**
 * Guide Page - List DAO implements
 * @author kim21017
 * @version $Id: ConsultPgmGuideListDAOSqlImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="consultPgmGuideListDAOTarget"
 * @spring.txbn id="consultPgmGuideListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultPgmGuideListDAOSqlImpl extends BaseJdbcDaoSupportSql implements ConsultPgmGuideListDAO
{
	public List findPgmGuideList(ConsultPgmGuideCommonDTO consultPgmGuideCommonDTO, User user) throws Exception
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SELECT																			");
        query.append("		''														AS seqNo			");
        query.append("		,''														AS isDelCheck		");
        query.append("		,x.pgguide_id											AS pgGuideId		");
        query.append("		,x.pgguide_no											AS pgGuideNo		");
        query.append("		,x.description											AS pgGuideDesc		");
        query.append("		,x.eqloc_id												AS eqLocId			");
        query.append("		,(SELECT a.full_desc														");
        query.append("			FROM TAEQLOC a															");
        query.append("			WHERE a.comp_no 	= x.comp_no											");
        query.append("			AND a.eqloc_id 		= x.eqloc_id										");
        query.append("		) 														AS eqLocDesc		");
        query.append("		,x.equip_id												AS equipId			");
        query.append("		,(SELECT a.description														");
        query.append("			FROM TAEQUIPMENT a														");
        query.append("			WHERE a.comp_no 	= x.comp_no											");
        query.append("			AND a.equip_id 		= x.equip_id										");
        query.append("		) 														AS equipDesc		");
        query.append("		,x.eqctg_id												AS eqCtgId			");
        query.append("		,(SELECT a.full_desc														");
        query.append("			FROM TAEQCTG a															");
        query.append("			WHERE a.comp_no 	= x.comp_no											");
        query.append("			AND a.eqctg_id 		= x.eqctg_id										");
        query.append("		) 														AS eqCtgDesc		");
        query.append("		,x.dept_id												AS deptId			");
        query.append("		,(SELECT a.description														");
        query.append("			FROM TADEPT a															");
        query.append("			WHERE a.comp_no 	= x.comp_no											");
        query.append("			AND a.dept_id 		= x.dept_id											");
        query.append("		) 														AS deptDesc			");
        query.append("		,x.emp_id												AS empId			");
        query.append("		,(SELECT a.emp_name															");
        query.append("			FROM TAEMP a															");
        query.append("			WHERE a.comp_no 	= x.comp_no											");
        query.append("			AND a.emp_id 		= x.emp_id											");
        query.append("		) 														AS empDesc			");
        query.append("		,x.eq_status											AS eqStatusId		");
        query.append("		,dbo.SFACODE_TO_DESC(x.eq_status,'EQ_STATUS','SYS',''						");
        query.append("							,'"+user.getLangId()+"') 			AS eqStatusDesc		");
        query.append("		,x.plant												AS plantId			");
        query.append("		,(SELECT a.description														");
        query.append("			FROM TAPLANT a															");
        query.append("			WHERE a.comp_no 	= x.comp_no											");
        query.append("			AND a.plant 		= x.plant											");
        query.append("		) 														AS plantDesc		");
        query.append("		,x.part_id												AS partId			");
        query.append("		,(SELECT a.full_desc														");
        query.append("			FROM TAPARTS a															");
        query.append("			WHERE a.comp_no 	= x.comp_no											");
        query.append("			AND a.part_id 		= x.part_id											");
        query.append("		) 														AS partDesc			");
        query.append("		,x.remark												AS remark			");
        query.append("		,x.is_use												AS isUseId			");
        query.append("		,dbo.SFACODE_TO_DESC(x.is_use,'IS_USE','SYS',''								");
        query.append("							,'"+user.getLangId()+"') 			AS isUseDesc		");
        query.append("FROM TXPGGUIDE x																	");
    	query.append("WHERE  1=1																		");
    	query.append(this.getWhere(consultPgmGuideCommonDTO, user));
    	query.getOrderByQuery("x.pgguide_id","x.pgguide_no", consultPgmGuideCommonDTO.getOrderBy(), consultPgmGuideCommonDTO.getDirection());
        
        
    	return getJdbcTemplate().queryForList(query.toString(consultPgmGuideCommonDTO.getIsLoadMaxCount(), consultPgmGuideCommonDTO.getFirstRow()));
    } 

	private String getWhere(ConsultPgmGuideCommonDTO consultPgmGuideCommonDTO, User user)
    {        
		QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        if(!"".equals(consultPgmGuideCommonDTO.getPgGuideId())){
        	query.getAndQuery("x.pgguide_id", consultPgmGuideCommonDTO.getPgGuideId());
        	return query.toString();
        }
        //프로그램 가이드 명
        query.getLikeQuery("x.description", consultPgmGuideCommonDTO.getFilterPgGuideDesc());
        //설비위치
        query.getEqLocLevelQuery("x.eqloc_id", consultPgmGuideCommonDTO.getFilterEqLocId(), consultPgmGuideCommonDTO.getFilterEqLocDesc(), user.getCompNo());
        //설비종류
        query.getEqCtgLevelQuery("x.eqctg_id", consultPgmGuideCommonDTO.getFilterEqCtgId(), consultPgmGuideCommonDTO.getFilterEqCtgDesc(), user.getCompNo());
        //설비
        query.getCodeLikeQuery("x.equip_id", "(SELECT a.description FROM TAEQUIPMENT a WHERE a.equip_id = x.equip_id AND a.comp_no='"+user.getCompNo()+"')", 
        		consultPgmGuideCommonDTO.getFilterEquipId(), consultPgmGuideCommonDTO.getFilterEquipDesc());
        //부서
        query.getDeptLevelQuery("x.dept_id", consultPgmGuideCommonDTO.getFilterDeptId(), consultPgmGuideCommonDTO.getFilterDeptDesc(), user.getCompNo());
        //사원
        query.getCodeLikeQuery("x.emp_id", "(SELECT a.emp_name FROM TAEMP a WHERE a.emp_id = x.emp_id AND a.comp_no='"+user.getCompNo()+"')", 
        		consultPgmGuideCommonDTO.getFilterEmpId(), consultPgmGuideCommonDTO.getFilterEmpDesc());
        //설비상태
    	query.getSysCdQuery("x.eq_status", consultPgmGuideCommonDTO.getFilterEqStatusId(), consultPgmGuideCommonDTO.getFilterEqStatusDesc(), "EQ_STATUS'", user.getCompNo(),user.getLangId());
        //공장
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM TAPLANT a WHERE a.plant = x.plant AND a.comp_no='"+user.getCompNo()+"')", 
        		consultPgmGuideCommonDTO.getFilterPlantId(), consultPgmGuideCommonDTO.getFilterPlantDesc());
        //자재
        query.getCodeLikeQuery("x.part_id", "(SELECT a.full_desc FROM TAPARTS a WHERE a.part_id = x.part_id AND a.comp_no='"+user.getCompNo()+"')", 
        		consultPgmGuideCommonDTO.getFilterPartId(), consultPgmGuideCommonDTO.getFilterPartDesc());
        //비고
        query.getLikeQuery("x.remark", consultPgmGuideCommonDTO.getFilterRemark());
        //사용여부
    	query.getSysCdQuery("x.is_use", consultPgmGuideCommonDTO.getFilterIsUseId(), consultPgmGuideCommonDTO.getFilterIsUseDesc(), "IS_USE", user.getCompNo(),user.getLangId());

    	return query.toString();
    }

    public int deletePgmGuideList(String id, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE FROM TXPGGUIDE			");
        query.append("WHERE  comp_no 		= ?		");
        query.append("  AND  pgguide_id  	= ?		");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public String findTotalCount(ConsultPgmGuideCommonDTO consultPgmGuideCommonDTO, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM TXPGGUIDE x																	");
    	query.append("WHERE  1=1																		");
    	query.append(this.getWhere(consultPgmGuideCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}