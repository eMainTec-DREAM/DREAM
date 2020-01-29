package dream.work.rpt.mapmrep.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.rpt.mapmrep.dao.MaPmRepListDAO;
import dream.work.rpt.mapmrep.dto.MaPmRepListDTO;

/**
 * 예방수리내역DAO
 * @author  kim21017
 * @version $Id: MaPmRepListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maPmRepListDAOTarget"
 * @spring.txbn id="maPmRepListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPmRepListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPmRepListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaPmRepListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPmRepListDTO
     * @return List
     */
    public List findPmRepList(MaPmRepListDTO maPmRepListDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT '' 								AS seqNo													");
		query.append("       ,x.wo_no 							AS woNo														");
		query.append("       ,x.description 					AS woDesc													");
        query.append("       ,																								");
		query.getDate("x.start_date", "startDate");
        query.append("		,z.item_no							AS itemNo													");
        query.append("		,z.description						AS eqDesc													");
		query.append("       ,(SELECT description FROM TADEPT WHERE comp_no = x.comp_no AND dept_id = x.dept_id) deptDesc	");
		query.append("       ,SFACODE_TO_DESC(x.pm_type,x.wo_type||'_TYPE','SYS',x.comp_no,'"+user.getLangId()+"') pmTypeDesc									");
		query.append("       ,x.pm_type	 						AS pmType													");
		query.append("       ,x.perform 						AS perform													");
		query.append("       ,x.wkor_id 						AS wkorId													");
        query.append("		 ,TO_CHAR(z.equip_id)	 			AS eqId														");
        query.append("       ,(SELECT a.emp_name                                                            				");
        query.append("         FROM TAEMP a                                                               					");
        query.append("         WHERE a.comp_no = x.comp_no                                                    				");
        query.append("          AND a.emp_id = x.emp_id)    	AS empDesc                         							");
        query.append("FROM TAWORKORDER x INNER JOIN TAWOEQUIP y                                     						");
		query.append("ON x.comp_no = y.comp_no																				");
		query.append(" AND x.wkor_id = y.wkor_id  																			");
		query.append("		INNER JOIN TAEQUIPMENT z                                    							        ");
		query.append("      ON y.comp_no = z.comp_no                                                      					");
		query.append("       AND y.equip_id = z.equip_id										            				");
		query.append("WHERE 1=1																								");
		query.append(this.getWhere(maPmRepListDTO,user));
		query.getOrderByQuery("x.start_date", maPmRepListDTO.getOrderBy(), maPmRepListDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(maPmRepListDTO.getIsLoadMaxCount(), maPmRepListDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaPmRepListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPmRepListDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaPmRepListDTO maPmRepListDTO,User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	String compNo = maPmRepListDTO.getCompNo();
    	//회사코드
    	query.getAndQuery("x.comp_no", compNo);
    	//삭제여부
    	query.getAndQuery("x.is_deleted", "N");
    	query.getAndQuery("z.is_deleted", "N");
    	//고장작업
    	query.getAndQuery("substr(x.wo_type,0,3)", "PMW");
    	//상태- 완료
    	query.getAndQuery("x.wo_status", "C");
    	//작업일자
		query.getAndDateQuery("x.start_date", maPmRepListDTO.getFilterStartDate(), maPmRepListDTO.getFilterEndDate());
		//부서
        query.getDeptLevelQuery("x.dept_id", maPmRepListDTO.getFilterDeptId(), maPmRepListDTO.getFilterDeptDesc(), compNo);
        //설비
        if(!"".equals(maPmRepListDTO.getFilterEquipId())||!"".equals(maPmRepListDTO.getFilterEquipDesc())){
        	query.append("AND x.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 		");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getCodeLikeQuery("equip_id", "description||item_no", maPmRepListDTO.getFilterEquipId(), maPmRepListDTO.getFilterEquipDesc());
            query.append("									))					");
        }
        //위치
        if(!"".equals(maPmRepListDTO.getFilterEqLocId())||!"".equals(maPmRepListDTO.getFilterEqLocDesc())){
        	query.append("AND x.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 		");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getEqLocLevelQuery("eqloc_id", maPmRepListDTO.getFilterEqLocId(), maPmRepListDTO.getFilterEqLocDesc(), compNo);
            query.append("									))					");
        }
        //종류
        if(!"".equals(maPmRepListDTO.getFilterEqCtgId())||!"".equals(maPmRepListDTO.getFilterEqCtgDesc())){
        	query.append("AND x.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 		");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getEqCtgLevelQuery("eqctg_id", maPmRepListDTO.getFilterEqCtgId(), maPmRepListDTO.getFilterEqCtgDesc(), compNo);
            query.append("									))					");
        }
        //내/외자
        if(!"".equals(maPmRepListDTO.getFilterPlfTypeId())||!"".equals(maPmRepListDTO.getFilterPlfTypeDesc())){
        	query.append("AND x.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 		");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getSysCdQuery("plf_type", maPmRepListDTO.getFilterPlfTypeId(), maPmRepListDTO.getFilterPlfTypeDesc(), "PLF_TYPE", compNo, user.getLangId());
        	query.append("									))					");
        }
    	//법정설비
        if(!"".equals(maPmRepListDTO.getFilterIsLawEq())){
        	query.append("AND x.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 		");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getLikeQuery("is_law_eq", maPmRepListDTO.getFilterIsLawEq());
            query.append("									))					");
        }
        //관리자(정)
        if(!"".equals(maPmRepListDTO.getFilterMainMngId())||!"".equals(maPmRepListDTO.getFilterMainMngName())){
        	query.append("AND x.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 		");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getCodeLikeQuery("main_mng_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = main_mng_id AND a.comp_no='"+compNo+"')", 
        			maPmRepListDTO.getFilterMainMngId(), maPmRepListDTO.getFilterMainMngName());
            query.append("									))					");
        }
        //관리자(부)
        if(!"".equals(maPmRepListDTO.getFilterSubMngId())||!"".equals(maPmRepListDTO.getFilterSubMngName())){
        	query.append("AND x.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 		");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getCodeLikeQuery("sub_mng_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = sub_mng_id AND a.comp_no='"+compNo+"')", 
        			maPmRepListDTO.getFilterSubMngId(), maPmRepListDTO.getFilterSubMngName());
        	query.append("									))					");
        }
        //작업형태
    	query.getSysCdQuery("x.pm_type", maPmRepListDTO.getFilterPmTypeId(), maPmRepListDTO.getFilterPmTypeDesc(), "x.wo_type||'_TYPE'", compNo, user.getLangId());
    	
    	//공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = x.comp_no AND plant = x.plant )", 
        		maPmRepListDTO.getFilterPlantId(), maPmRepListDTO.getFilterPlantDesc());
        
    	return query.toString();
    }

	@Override
	public String findTotalCount(MaPmRepListDTO maPmRepListDTO, User user)
	{
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT								                                        	");
        query.append("       COUNT(1)      					                                        	");
        query.append("FROM TAWORKORDER x INNER JOIN TAWOEQUIP y                                     	");
		query.append("ON x.comp_no = y.comp_no															");
		query.append(" AND x.wkor_id = y.wkor_id  														");
		query.append("		INNER JOIN TAEQUIPMENT z                                    				");
		query.append("      ON y.comp_no = z.comp_no                                                    ");
		query.append("       AND y.equip_id = z.equip_id										        ");
		query.append("WHERE 1=1								                                        	");
		query.append(this.getWhere(maPmRepListDTO,user));
		
		List resultList = getJdbcTemplate().queryForList(query.toString());
		
		return QueryBuffer.listToString(resultList);
	}
}