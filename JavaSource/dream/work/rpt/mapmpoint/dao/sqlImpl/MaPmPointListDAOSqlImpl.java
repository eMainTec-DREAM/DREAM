package dream.work.rpt.mapmpoint.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.rpt.mapmpoint.dao.MaPmPointListDAO;
import dream.work.rpt.mapmpoint.dto.MaPmPointListDTO;

/**
 * 예방점검내역DAO
 * @author  kim21017
 * @version $Id: MaPmPointListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maPmPointListDAOTarget"
 * @spring.txbn id="maPmPointListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPmPointListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPmPointListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaPmPointListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPmPointListDTO
     * @return List
     */
    public List findPmPointList(MaPmPointListDTO maPmPointListDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("SELECT 																											");
		query.append("    ''                                   		AS seqNo                                                    		");
		query.append("       ,x.wo_no                            	AS woNo                                                        		");
		query.append("       ,x.description                         AS woDesc     		  		  		  		  		  		  		");
        query.append("       ,																											");
		query.getDate("x.start_date", "startDate");
		query.append("        ,b.description                        AS eqDesc                                                    		");
		query.append("       ,(SELECT description FROM TADEPT WHERE comp_no = x.comp_no AND dept_id = x.dept_id)    AS deptDesc    		");
		query.append("       ,(SELECT emp_name FROM TAEMP WHERE comp_no = x.comp_no AND emp_id = x.emp_id)     		AS empDesc    		");
		query.append("       ,(SELECT description FROM TAEQASMB WHERE comp_no = z.comp_no AND eqasmb_id = (SELECT eqasmb_id FROM TAPMPOINT WHERE pm_point_id = z.pm_point_id))    AS eqAsmbDesc    		");
		query.append("       ,(SELECT check_point FROM TAPMPOINT WHERE pm_point_id = z.pm_point_id)        AS 'checkPoint'        		");
		query.append("       ,dbo.SFACODE_TO_DESC(z.pm_point_rslt_status,'PM_POINT_RSLT_STATUS','SYS',x.comp_no,'ko')    AS pmPointRsltStatus    		");
		query.append("       ,z.result_value                 		AS resultValue                                                		");
		query.append("       ,x.pm_type                           	AS pmType      														");
		query.append("       ,																											");
		query.getDate("z.repair_date", "repairDate");
		query.append("       ,(SELECT emp_name FROM TAEMP WHERE comp_no = x.comp_no AND emp_id = z.repair_by)    AS repairBy    		");
		query.append("       ,z.repair_desc                         AS repairDesc                                                		");
		query.append("       ,x.wkor_id                         	AS wkorId                                                    		");
		query.append("       ,CONVERT(NVARCHAR, b.equip_id)         AS eqId                                                        		");
		query.append("FROM TAWORKORDER x INNER JOIN TAWOPOINT z                                                                         ");
		query.append("ON x.wkor_id = z.wkor_id                                                                                        	");
		query.append(" AND x.comp_no = z.comp_no                                                                                      	");
		query.append("		INNER JOIN TAWOEQUIP a				                            											");
		query.append("		ON x.comp_no = a.comp_no                                                                					");
		query.append("		 AND x.wkor_id = a.wkor_id                                                              					");
		query.append("       		INNER JOIN TAEQUIPMENT b                            												");
		query.append("              ON a.comp_no = b.comp_no                                            								");
		query.append("               AND a.equip_id = b.equip_id                                        								");
		query.append("WHERE 1=1                                                                                                    		");
		query.append(this.getWhere(maPmPointListDTO, user));
		query.getOrderByQuery("x.wkor_id", "x.start_date", maPmPointListDTO.getOrderBy(), maPmPointListDTO.getDirection());

		return getJdbcTemplate().queryForList(query.toString(maPmPointListDTO.getIsLoadMaxCount(), maPmPointListDTO.getFirstRow()));

    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaPmPointListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPmPointListDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaPmPointListDTO maPmPointListDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	String compNo = maPmPointListDTO.getCompNo();
    	//회사코드
    	query.getAndQuery("x.comp_no", compNo);
    	//고장작업
    	query.getAndQuery("SUBSTRING(x.wo_type,1,2)", "PM");
    	//점검
    	query.getAndQuery("x.pm_type", "INS");
    	//상태- 완료
    	query.getAndQuery("x.wo_status", "C");
    	//작업일자
		query.getAndDateQuery("x.start_date", maPmPointListDTO.getFilterStartDate(), maPmPointListDTO.getFilterEndDate());
		//부서
        query.getDeptLevelQuery("x.dept_id", maPmPointListDTO.getFilterDeptId(), maPmPointListDTO.getFilterDeptDesc(), compNo);
        //설비
        if(!"".equals(maPmPointListDTO.getFilterEquipId())||!"".equals(maPmPointListDTO.getFilterEquipDesc())){
        	query.append("AND x.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 		");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getCodeLikeQuery("equip_id", "description+item_no", maPmPointListDTO.getFilterEquipId(), maPmPointListDTO.getFilterEquipDesc());
            query.append("									))					");
        }
        //위치
        if(!"".equals(maPmPointListDTO.getFilterEqLocId())||!"".equals(maPmPointListDTO.getFilterEqLocDesc())){
        	query.append("AND x.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 		");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getEqLocLevelQuery("eqloc_id", maPmPointListDTO.getFilterEqLocId(), maPmPointListDTO.getFilterEqLocDesc(), compNo);
            query.append("									))					");
        }
        //종류
        if(!"".equals(maPmPointListDTO.getFilterEqCtgId())||!"".equals(maPmPointListDTO.getFilterEqCtgDesc())){
        	query.append("AND x.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 		");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getEqCtgLevelQuery("eqctg_id", maPmPointListDTO.getFilterEqCtgId(), maPmPointListDTO.getFilterEqCtgDesc(), compNo);
            query.append("									))					");
        }
        //내/외자
        if(!"".equals(maPmPointListDTO.getFilterPlfTypeId())||!"".equals(maPmPointListDTO.getFilterPlfTypeDesc())){
        	query.append("AND x.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 		");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getSysCdQuery("plf_type", maPmPointListDTO.getFilterPlfTypeId(), maPmPointListDTO.getFilterPlfTypeDesc(), "PLF_TYPE", compNo, user.getLangId());
        	query.append("									))					");
        }
    	//법정설비
        if(!"".equals(maPmPointListDTO.getFilterIsLawEq())){
        	query.append("AND x.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 		");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getLikeQuery("is_law_eq", maPmPointListDTO.getFilterIsLawEq());
            query.append("									))					");
        }
        //관리자(정)
        if(!"".equals(maPmPointListDTO.getFilterMainMngId())||!"".equals(maPmPointListDTO.getFilterMainMngName())){
        	query.append("AND x.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 		");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getCodeLikeQuery("main_mng_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = main_mng_id AND a.comp_no='"+compNo+"')", 
            		maPmPointListDTO.getFilterMainMngId(), maPmPointListDTO.getFilterMainMngName());
            query.append("									))					");
        }
        //관리자(부)
        if(!"".equals(maPmPointListDTO.getFilterSubMngId())||!"".equals(maPmPointListDTO.getFilterSubMngName())){
        	query.append("AND x.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 		");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getCodeLikeQuery("sub_mng_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = sub_mng_id AND a.comp_no='"+compNo+"')", 
            		maPmPointListDTO.getFilterSubMngId(), maPmPointListDTO.getFilterSubMngName());
        	query.append("									))					");
        }
        
        //담당자
        query.getCodeLikeQuery("x.emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.emp_id AND a.comp_no='"+compNo+"')", 
                maPmPointListDTO.getFilterEmpId(), maPmPointListDTO.getFilterEmpDesc());

        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = x.comp_no AND plant = x.plant )", 
        		maPmPointListDTO.getFilterPlantId(), maPmPointListDTO.getFilterPlantDesc());
        
    	return query.toString();
    }

	@Override
	public String findTotalCount(MaPmPointListDTO maPmPointListDTO, User user)
	{
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT										");
        query.append("       COUNT(1)      							");
        query.append("FROM TAWORKORDER x INNER JOIN TAWOPOINT z		");
		query.append("ON x.wkor_id = z.wkor_id                  	");
		query.append(" AND x.comp_no = z.comp_no                	");
		query.append("		INNER JOIN TAWOEQUIP a					");
		query.append("		ON x.comp_no = a.comp_no            	");
		query.append("		 AND x.wkor_id = a.wkor_id          	");
		query.append("       		INNER JOIN TAEQUIPMENT b    	");
		query.append("              ON a.comp_no = b.comp_no    	");
		query.append("               AND a.equip_id = b.equip_id	");
		query.append("WHERE 1=1										");
		query.append(this.getWhere(maPmPointListDTO, user));
		
		List resultList = getJdbcTemplate().queryForList(query.toString());
		
		return QuerySqlBuffer.listToString(resultList);
	}
}