package common.mafinder.mamstr.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.mafinder.mamstr.dao.LovWoPartsListDAO;
import common.mafinder.mamstr.dto.LovWoPartsListDTO;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;

/**
 * 작업부품 팝업
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovWoPartsListDAOTarget"
 * @spring.txbn id="lovWoPartsListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovWoPartsListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovWoPartsListDAO
{
	@Override
	public List findWoPartsAcList(LovWoPartsListDTO lovWoPartsListDTO, User user, Map<String, String> conditionMap) {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
        
		query.append("SELECT                                                                                                      		");
		query.append("       ''                                                                               AS seqNo,           		");
		query.append("       x.wkor_id                                                                        AS wkorId,          		");
		query.append("       x.wo_no                                                                          AS woNo,            		");
		query.append("       x.description                                                                    AS woDesc,          		");
		query.append("       x.wkor_date                                                                      AS woDate,          		");
		query.append("       b.item_no                                             							  AS equipNo,         		");
		query.append("       b.description                                             				  AS equipDesc,       		");
		query.append("       (SELECT full_desc                                                                                    		");
		query.append("        FROM TAEQASMB                                                                                       		");
		query.append("        WHERE comp_no = x.comp_no                                                                           		");
		query.append("         AND eqasmb_id = x.eqasmb_id)                                                    AS eqAsmbDesc,      		");
		query.append("       (SELECT c.full_desc                                                                      			");
		query.append("        FROM TAEQLOC c                                                    									");
		query.append("        WHERE b.comp_no = c.comp_no                                                                     	");
		query.append("         AND b.eqloc_id = c.eqloc_id)                                              AS eqLocDesc,       		");
		query.append("       dbo.SFACODE_TO_DESC(x.wo_status,'WO_STATUS','SYS','','"+user.getLangId()+"')     AS woStatusDesc,    		");
		query.append("       (SELECT description                                                                                  		");
		query.append("        FROM TADEPT                                                                                         		");
		query.append("        WHERE comp_no = x.comp_no                                                                           		");
		query.append("         AND dept_id = x.dept_id)                                                        AS deptDesc,        		");
		query.append("       (SELECT description                                                                                  		");
		query.append("        FROM TAWKCTR                                                                                        		");
		query.append("        WHERE comp_no = x.comp_no                                                                           		");
		query.append("         AND wkctr_id = x.wkctr_id)                                                      AS wkCtrDesc,       		");
		query.append("       (SELECT emp_name                                                                                     		");
		query.append("        FROM TAEMP                                                                                          		");
		query.append("        WHERE comp_no = x.comp_no                                                                           		");
		query.append("         AND emp_id = x.emp_id)                                                          AS empDesc,         		");
		query.append("       y.wopart_id                                                                      AS woPartId,        		");
		query.append("       (SELECT wname                                                                                        		");
		query.append("        FROM TAWAREHOUSE                                                                                    		");
		query.append("        WHERE comp_no = y.comp_no                                                                           		");
		query.append("         AND wcode_id = y.wcode_id)                                                      AS issWname,        		");
		query.append("       (SELECT part_no                                                                                      		");
		query.append("        FROM TAPARTS                                                                                        		");
		query.append("        WHERE comp_no = y.comp_no                                                                           		");
		query.append("         AND part_id = y.part_id)                                                        AS partNo,          		");
		query.append("       (SELECT full_desc                                                                                    		");
		query.append("        FROM TAPARTS                                                                                        		");
		query.append("        WHERE comp_no = y.comp_no                                                                           		");
		query.append("         AND part_id = y.part_id)                                                        AS partPtSize,      		");
		query.append("       dbo.SFACODE_TO_DESC(y.part_grade,'PART_GRADE','SYS','','"+user.getLangId()+"')   AS partGrade,       		");
		query.append("       y.use_qty                                                                        AS useQty,          		");
		query.append("       y.remark                                                                         AS remark           		");
		query.append("FROM TAWORKORDER x INNER JOIN TAWOPARTS y                                                                   		");
		query.append("ON x.comp_no = y.comp_no                                                                                    		");
		query.append(" AND x.wkor_id = y.wkor_id                                                                                   		");
		query.append("		INNER JOIN TAWOEQUIP a                                        	                                       		");
		query.append("		ON x.comp_no = a.comp_no                                                                          			");
		query.append("		 AND x.wkor_id = a.wkor_id 																					");
		query.append("      		INNER JOIN TAEQUIPMENT b                              		                                       	");
		query.append("              ON a.comp_no = b.comp_no                                	                                       	");
		query.append("               AND a.equip_id = b.equip_id									                                    ");
		query.append("WHERE 1=1                                                                                                   		");
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getAndQuery("x.wo_status", conditionMap);
        query.getAndQuery("y.wcode_id", conditionMap);
        query.getAndQuery("y.part_id", conditionMap);
        query.getAndQuery("y.part_grade", conditionMap);
        query.getAndQuery("y.ptisslist_id", conditionMap);
        query.append(this.getWhere(lovWoPartsListDTO,user));
        query.getOrderByQuery("x.wkor_id", "x.wo_no", lovWoPartsListDTO.getOrderBy(), lovWoPartsListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(lovWoPartsListDTO.getIsLoadMaxCount(), lovWoPartsListDTO.getFirstRow()));
	}
	
	@Override
	public String findWoPartsListTotalCount(LovWoPartsListDTO lovWoPartsListDTO, User user, Map<String, String> conditionMap)
	{
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                    	");
        query.append("       COUNT(1)                           	");
        query.append("FROM TAWORKORDER x INNER JOIN TAWOPARTS y		");
		query.append("ON x.comp_no = y.comp_no                  	");
		query.append(" AND x.wkor_id = y.wkor_id                	");
		query.append("		INNER JOIN TAWOEQUIP a                  ");
		query.append("		ON x.comp_no = a.comp_no                ");
		query.append("		 AND x.wkor_id = a.wkor_id 				");
		query.append("      		INNER JOIN TAEQUIPMENT b        ");
		query.append("              ON a.comp_no = b.comp_no        ");
		query.append("               AND a.equip_id = b.equip_id	");
		query.append("WHERE 1=1                                     ");
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getAndQuery("x.wo_status", conditionMap);
        query.getAndQuery("y.wcode_id", conditionMap);
        query.getAndQuery("y.part_id", conditionMap);
        query.getAndQuery("y.part_grade", conditionMap);
        query.getAndQuery("y.ptisslist_id", conditionMap);
        query.append(this.getWhere(lovWoPartsListDTO,user));
        List resultList = getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
	}
	
	private String getWhere(LovWoPartsListDTO lovWoPartsListDTO, User loginUser)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        String startDate = lovWoPartsListDTO.getFilterStartDate();
        String endDate = lovWoPartsListDTO.getFilterEndDate();
        String compNo  =loginUser.getCompNo();
      //작업번호
        query.getAndQuery("x.wo_no", lovWoPartsListDTO.getFilterWoNo());
        //작업명
        query.getLikeQuery("x.description", lovWoPartsListDTO.getFilterWkOrDesc());
        //법정설비
        if(!"".equals(lovWoPartsListDTO.getFilterIsLawEq())){
            query.append("AND x.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP           ");
            query.append("              WHERE equip_id IN (SELECT equip_id      ");
            query.append("                                  FROM TAEQUIPMENT    ");
            query.append("                                  WHERE 1=1           ");
            query.getLikeQuery("is_law_eq", lovWoPartsListDTO.getFilterIsLawEq());
            query.append("                                  ))                  ");
        }
        //작업일자
        query.getAndDateQuery("x.wkor_date", startDate, endDate);
      //내/외자
        if(!"".equals(lovWoPartsListDTO.getFilterPlfTypeId())||!"".equals(lovWoPartsListDTO.getFilterPlfTypeDesc())){
            query.append("AND x.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP           ");
            query.append("              WHERE equip_id IN (SELECT equip_id      ");
            query.append("                                  FROM TAEQUIPMENT    ");
            query.append("                                  WHERE 1=1           ");
            query.getSysCdQuery("plf_type", lovWoPartsListDTO.getFilterPlfTypeId(), lovWoPartsListDTO.getFilterPlfTypeDesc(), "PLF_TYPE", compNo,loginUser.getLangId());
            query.append("                                  ))                  ");
        }
        //작업종류
        query.getSysCdQuery("x.wo_type", lovWoPartsListDTO.getFilterWoTypeId(), lovWoPartsListDTO.getFilterWoTypeDesc(), "WO_TYPE", compNo,loginUser.getLangId());
      //작업형태
        query.getSysCdQuery("x.pm_type", lovWoPartsListDTO.getFilterPmTypeId(), lovWoPartsListDTO.getFilterPmTypeDesc(), "x.wo_type+'_TYPE'", compNo,loginUser.getLangId());
        //부서
        query.getDeptLevelQuery("x.dept_id", lovWoPartsListDTO.getFilterDeptId(), lovWoPartsListDTO.getFilterDeptDesc(), compNo);
        //설비
        if(!"".equals(lovWoPartsListDTO.getFilterEquipId())||!"".equals(lovWoPartsListDTO.getFilterEquipDesc())){
            query.append("AND x.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP           ");
            query.append("              WHERE equip_id IN (SELECT equip_id      ");
            query.append("                                  FROM TAEQUIPMENT    ");
            query.append("                                  WHERE 1=1           ");
            query.getCodeLikeQuery("equip_id", "description+item_no", 
                    lovWoPartsListDTO.getFilterEquipId(), lovWoPartsListDTO.getFilterEquipDesc());
            query.append("                                  ))                  ");
        }
        
        //담당자
        query.getCodeLikeQuery("x.emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.emp_id AND a.comp_no='"+compNo+"')", 
                lovWoPartsListDTO.getFilterEmpId(), lovWoPartsListDTO.getFilterEmpDesc());
        //관리자(정)
        if(!"".equals(lovWoPartsListDTO.getFilterMainMngId())||!"".equals(lovWoPartsListDTO.getFilterMainMngName())){
            query.append("AND x.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP           ");
            query.append("              WHERE equip_id IN (SELECT equip_id      ");
            query.append("                                  FROM TAEQUIPMENT    ");
            query.append("                                  WHERE 1=1           ");
            query.getCodeLikeQuery("main_mng_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = main_mng_id AND a.comp_no='"+compNo+"')", 
                    lovWoPartsListDTO.getFilterMainMngId(), lovWoPartsListDTO.getFilterMainMngName());
            query.append("                                  ))                  ");
        }
        //관리자(부)
        if(!"".equals(lovWoPartsListDTO.getFilterSubMngId())||!"".equals(lovWoPartsListDTO.getFilterSubMngName())){
            query.append("AND x.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP           ");
            query.append("              WHERE equip_id IN (SELECT equip_id      ");
            query.append("                                  FROM TAEQUIPMENT    ");
            query.append("                                  WHERE 1=1           ");
            query.getCodeLikeQuery("sub_mng_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = sub_mng_id AND a.comp_no='"+compNo+"')", 
                    lovWoPartsListDTO.getFilterSubMngId(), lovWoPartsListDTO.getFilterSubMngName());
            query.append("                                  ))                  ");
        }
        //위치
        if(!"".equals(lovWoPartsListDTO.getFilterEqLocId())||!"".equals(lovWoPartsListDTO.getFilterEqLocDesc())){
            query.append("AND x.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP           ");
            query.append("              WHERE equip_id IN (SELECT equip_id      ");
            query.append("                                  FROM TAEQUIPMENT    ");
            query.append("                                  WHERE 1=1           ");
            query.getEqLocLevelQuery("eqloc_id", lovWoPartsListDTO.getFilterEqLocId(), lovWoPartsListDTO.getFilterEqLocDesc(), compNo);
            query.append("                                  ))                  ");
        }
        //종류
        if(!"".equals(lovWoPartsListDTO.getFilterEqCtgId())||!"".equals(lovWoPartsListDTO.getFilterEqCtgDesc())){
            query.append("AND x.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP           ");
            query.append("              WHERE equip_id IN (SELECT equip_id      ");
            query.append("                                  FROM TAEQUIPMENT    ");
            query.append("                                  WHERE 1=1           ");
            query.getEqCtgLevelQuery("eqctg_id", lovWoPartsListDTO.getFilterEqCtgId(), lovWoPartsListDTO.getFilterEqCtgDesc(), compNo);
            query.append("                                  ))                  ");
        }
        //상태
        query.getSysCdQuery("x.wo_status", lovWoPartsListDTO.getFilterWoStatus(), lovWoPartsListDTO.getFilterWoStatusDesc(), "WO_STATUS", compNo,loginUser.getLangId());
        
        query.getCodeLikeQuery("x.self_vendor_type", "dbo.SFACODE_TO_DESC(x.self_vendor_type,'SELF_VENDOR_TYPE','SYS','','"+loginUser.getLangId()+"')", lovWoPartsListDTO.getSelfVendorType(), lovWoPartsListDTO.getSelfVendorTypeDesc());
        query.getCodeLikeQuery("x.vendor_id", "dbo.SFAIDTODESC(x.vendor_id, '', 'VENDOR', x.comp_no)", lovWoPartsListDTO.getVendorId(), lovWoPartsListDTO.getVendorDesc());
        
        if(!"".equals(lovWoPartsListDTO.getPmType())){
            if(lovWoPartsListDTO.getPmType().indexOf("|")>-1){
                String[] arr = lovWoPartsListDTO.getPmType().split("\\|");
                String str = "";
                for (int i = 0; i < arr.length; i++) {
                    str += "'"+arr[i]+"',";
                }
                str = str.substring(0,str.length()-1);
                query.append("AND x.pm_type IN ("+str+")    ");
            }else{
                query.getAndQuery("x.pm_type", lovWoPartsListDTO.getPmType());
            }
        }
        
        query.getAndQuery("x.wo_status", lovWoPartsListDTO.getWoStatus());
        query.getAndQuery("x.wo_type", lovWoPartsListDTO.getWoType());
        
        return query.toString();
    }
}