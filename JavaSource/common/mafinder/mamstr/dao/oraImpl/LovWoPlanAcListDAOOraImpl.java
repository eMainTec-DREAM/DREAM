package common.mafinder.mamstr.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.mafinder.mamstr.dao.LovWoPlanAcListDAO;
import common.mafinder.mamstr.dto.LovWoPlanAcListDTO;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;

/**
 * 작업 팝업
 * @author  kim21017
 * @version $Id: LovWoPlanAcListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
 * @since   1.0
 *
 * @spring.bean id="lovWoPlanAcListDAOTarget"
 * @spring.txbn id="lovWoPlanAcListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovWoPlanAcListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovWoPlanAcListDAO
{
    /**
     * 작업 검색
     * @author  kim21017
     * @version $Id: LovWoPlanAcListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovWoPlanAcListDTO
     * @param loginUser
     * @return
     */
    public List findWoPlanAcList(LovWoPlanAcListDTO lovWoPlanAcListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                	");
        query.append("       x.wkor_id 								AS wkorId                   ");
        query.append("       ,x.wo_no 								AS woNo                   	");
        query.append("       ,x.description 						AS woDesc                   ");
        query.append("       ,SFACODE_TO_DESC(wo_status,'WO_STATUS','SYS','','"+loginUser.getLangId()+"')	AS woStatusDesc     ");
        query.append("       ,z.description							AS equipDesc          		");
        query.append("       ,(SELECT c.full_desc                           					");
        query.append("         FROM TAEQLOC c                    								");
        query.append("         WHERE c.comp_no = x.comp_no                                 		");
        query.append("         	AND c.eqloc_id = z.eqloc_id	)   	AS eqLocDesc           		");
        query.append("		 ,(SELECT description												");
        query.append("		   FROM TADEPT														");
        query.append("		   WHERE comp_no = x.comp_no										");
        query.append("		 	AND dept_id = x.dept_id) 			AS deptDesc					");
        query.append("		 ,SFACODE_TO_DESC(x.wo_type,'WO_TYPE','SYS','','"+loginUser.getLangId()+"') 			AS woTypeDesc	");
        query.append("		 ,SFACODE_TO_DESC(x.pm_type,x.wo_type||'_TYPE','SYS','','"+loginUser.getLangId()+"')	AS pmTypeDesc	");
        query.append("		 ,(SELECT emp_name													");
        query.append("		   FROM TAEMP														");
        query.append("		   WHERE comp_no = x.comp_no										");
        query.append("		 	AND emp_id = x.emp_id) 				AS empDesc					");
        query.append("		 ,(SELECT c.emp_name												");
        query.append("		   FROM TAEMP c														");
        query.append("		   WHERE c.comp_no = x.comp_no										");
        query.append("		 	 AND c.emp_id = z.sub_mng_id ) 		AS subMng					");
        query.append("FROM TAWOPLAN x INNER JOIN TAWOEQUIP y									");
        query.append("ON x.comp_no = y.comp_no													");
        query.append(" AND x.wkor_id = y.wkor_id												");
        query.append("		INNER JOIN TAEQUIPMENT z											");
        query.append("      ON y.comp_no = z.comp_no											");
        query.append("       AND y.equip_id = z.equip_id										");
        query.append("WHERE 1=1                                                                 ");
        query.append(this.getWhere(lovWoPlanAcListDTO,loginUser));
        if(!"".equals(lovWoPlanAcListDTO.getPmType())){
        	if(lovWoPlanAcListDTO.getPmType().indexOf("|")>-1){
        		String[] arr = lovWoPlanAcListDTO.getPmType().split("\\|");
        		String str = "";
        		for (int i = 0; i < arr.length; i++) {
					str += "'"+arr[i]+"',";
				}
        		str = str.substring(0,str.length()-1);
        		query.append("AND x.pm_type IN ("+str+")	");
        	}else{
        		query.getAndQuery("x.pm_type", lovWoPlanAcListDTO.getPmType());
        	}
        }
        
        query.getAndQuery("x.wo_status", lovWoPlanAcListDTO.getWoStatus());
        query.getAndQuery("x.wo_type", lovWoPlanAcListDTO.getWoType());
        query.getOrderByQuery("x.wo_no DESC", lovWoPlanAcListDTO.getOrderBy(), lovWoPlanAcListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(lovWoPlanAcListDTO.getIsLoadMaxCount(), lovWoPlanAcListDTO.getFirstRow()));

    }
    private String getWhere(LovWoPlanAcListDTO lovWoPlanAcListDTO, User loginUser)
    {        
        QueryBuffer query = new QueryBuffer();
        String startDate = lovWoPlanAcListDTO.getFilterStartDate();
        String endDate = lovWoPlanAcListDTO.getFilterEndDate();
//        String compNo  =lovWoPlanAcListDTO.getCompNo();
        String compNo  =loginUser.getCompNo();
      //작업번호
        query.getAndQuery("x.wo_no", lovWoPlanAcListDTO.getFilterWoNo());
        //작업명
        query.getLikeQuery("x.description", lovWoPlanAcListDTO.getFilterWkOrDesc());
        //법정설비
        if(!"".equals(lovWoPlanAcListDTO.getFilterIsLawEq())){
        	query.append("AND x.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 			");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getLikeQuery("is_law_eq", lovWoPlanAcListDTO.getFilterIsLawEq());
            query.append("									))					");
        }
        //작업일자
        query.getAndDateQuery("x.wkor_date", startDate, endDate);
      //내/외자
        if(!"".equals(lovWoPlanAcListDTO.getFilterPlfTypeId())||!"".equals(lovWoPlanAcListDTO.getFilterPlfTypeDesc())){
        	query.append("AND x.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 			");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getSysCdQuery("plf_type", lovWoPlanAcListDTO.getFilterPlfTypeId(), lovWoPlanAcListDTO.getFilterPlfTypeDesc(), "PLF_TYPE", compNo,loginUser.getLangId());
            query.append("									))					");
        }
    	//작업종류
    	query.getSysCdQuery("x.wo_type", lovWoPlanAcListDTO.getFilterWoTypeId(), lovWoPlanAcListDTO.getFilterWoTypeDesc(), "WO_TYPE", compNo,loginUser.getLangId());
      //작업형태
    	query.getSysCdQuery("x.pm_type", lovWoPlanAcListDTO.getFilterPmTypeId(), lovWoPlanAcListDTO.getFilterPmTypeDesc(), "x.wo_type||'_TYPE'", compNo,loginUser.getLangId());
        //부서
        query.getDeptLevelQuery("x.dept_id", lovWoPlanAcListDTO.getFilterDeptId(), lovWoPlanAcListDTO.getFilterDeptDesc(), compNo);
        //설비
        if(!"".equals(lovWoPlanAcListDTO.getFilterEquipId())||!"".equals(lovWoPlanAcListDTO.getFilterEquipDesc())){
        	query.append("AND x.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 			");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getCodeLikeQuery("equip_id", "description||item_no", 
            		lovWoPlanAcListDTO.getFilterEquipId(), lovWoPlanAcListDTO.getFilterEquipDesc());
            query.append("									))					");
        }
        
        //담당자
        query.getCodeLikeQuery("x.emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.emp_id AND a.comp_no='"+compNo+"')", 
        		lovWoPlanAcListDTO.getFilterEmpId(), lovWoPlanAcListDTO.getFilterEmpDesc());
        //관리자(정)
        if(!"".equals(lovWoPlanAcListDTO.getFilterMainMngId())||!"".equals(lovWoPlanAcListDTO.getFilterMainMngName())){
        	query.append("AND x.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 			");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getCodeLikeQuery("main_mng_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = main_mng_id AND a.comp_no='"+compNo+"')", 
            		lovWoPlanAcListDTO.getFilterMainMngId(), lovWoPlanAcListDTO.getFilterMainMngName());
            query.append("									))					");
        }
        //관리자(부)
        if(!"".equals(lovWoPlanAcListDTO.getFilterSubMngId())||!"".equals(lovWoPlanAcListDTO.getFilterSubMngName())){
        	query.append("AND x.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 			");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getCodeLikeQuery("sub_mng_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = sub_mng_id AND a.comp_no='"+compNo+"')", 
            		lovWoPlanAcListDTO.getFilterSubMngId(), lovWoPlanAcListDTO.getFilterSubMngName());
            query.append("									))					");
        }
        //위치
        if(!"".equals(lovWoPlanAcListDTO.getFilterEqLocId())||!"".equals(lovWoPlanAcListDTO.getFilterEqLocDesc())){
        	query.append("AND x.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 			");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getEqLocLevelQuery("eqloc_id", lovWoPlanAcListDTO.getFilterEqLocId(), lovWoPlanAcListDTO.getFilterEqLocDesc(), compNo);
            query.append("									))					");
        }
        //종류
        if(!"".equals(lovWoPlanAcListDTO.getFilterEqCtgId())||!"".equals(lovWoPlanAcListDTO.getFilterEqCtgDesc())){
        	query.append("AND x.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 			");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getEqCtgLevelQuery("eqctg_id", lovWoPlanAcListDTO.getFilterEqCtgId(), lovWoPlanAcListDTO.getFilterEqCtgDesc(), compNo);
            query.append("									))					");
        }
        //상태
        query.getSysCdQuery("x.wo_status", lovWoPlanAcListDTO.getFilterWoStatus(), lovWoPlanAcListDTO.getFilterWoStatusDesc(), "WO_STATUS", compNo,loginUser.getLangId());
        
        query.getCodeLikeQuery("x.self_vendor_type", "SFACODE_TO_DESC(x.self_vendor_type,'SELF_VENDOR_TYPE','SYS','','"+loginUser.getLangId()+"')", lovWoPlanAcListDTO.getSelfVendorType(), lovWoPlanAcListDTO.getSelfVendorTypeDesc());
        query.getCodeLikeQuery("x.vendor_id", "SFAIDTODESC(x.vendor_id, '', 'VENDOR', x.comp_no)", lovWoPlanAcListDTO.getVendorId(), lovWoPlanAcListDTO.getVendorDesc());
        
        return query.toString();
    }
	@Override
	public List findWoAcList(LovWoPlanAcListDTO lovWoPlanAcListDTO, User loginUser, Map<String, String> conditionMap) {
		
		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                    	");
        query.append("       x.wkor_id 									AS wkorId                   ");
        query.append("       ,x.wo_no 									AS woNo                   	");
        query.append("       ,x.description 							AS woDesc                   ");
        query.append("       ,SFACODE_TO_DESC(wo_status,'WO_STATUS','SYS','','"+loginUser.getLangId()+"')	AS woStatusDesc     ");
        query.append("       ,z.description  							AS equipDesc             	");
        query.append("       ,(SELECT c.full_desc                           						");
        query.append("         FROM TAEQLOC c                    									");
        query.append("         WHERE c.comp_no = x.comp_no                                 			");
        query.append("            AND c.eqloc_id = z.eqloc_id  )   		AS eqLocDesc             	");
        query.append("		 ,(SELECT description													");
        query.append("		   FROM TADEPT															");
        query.append("		   WHERE comp_no = x.comp_no											");
        query.append("			 AND dept_id = x.dept_id) 				AS deptDesc					");
        query.append("		 ,SFACODE_TO_DESC(x.wo_type,'WO_TYPE','SYS','','"+loginUser.getLangId()+"') 			AS woTypeDesc	");
        query.append("		 ,SFACODE_TO_DESC(x.pm_type,x.wo_type||'_TYPE','SYS','','"+loginUser.getLangId()+"') 	AS pmTypeDesc	");
        query.append("		 ,(SELECT emp_name														");
        query.append("		   FROM TAEMP															");
        query.append("		   WHERE comp_no = x.comp_no											");
        query.append("			 AND emp_id = x.emp_id) 				AS empDesc					");
        query.append("		 ,(SELECT c.emp_name													");
        query.append("			FROM TAEMP c														");
        query.append("			WHERE c.comp_no = x.comp_no											");
        query.append("			   AND c.emp_id	= z.sub_mng_id ) 		AS subMng					");
        query.append("FROM TAWOPLAN x INNER JOIN TAWOEQUIP y							");
        query.append("ON x.comp_no = y.comp_no											");
        query.append(" AND x.wkor_id = y.wkor_id										");
        query.append("		INNER JOIN TAEQUIPMENT z									");
        query.append("      ON y.comp_no = z.comp_no									");
        query.append("       AND y.equip_id = z.equip_id								");
        query.append("WHERE 1=1                                                                 	");
        
        query.getAndQuery("wo_status", conditionMap);
        
        query.append(this.getWhere(lovWoPlanAcListDTO,loginUser));
        if(!"".equals(lovWoPlanAcListDTO.getPmType())){
        	if(lovWoPlanAcListDTO.getPmType().indexOf("|")>-1){
        		String[] arr = lovWoPlanAcListDTO.getPmType().split("\\|");
        		String str = "";
        		for (int i = 0; i < arr.length; i++) {
					str += "'"+arr[i]+"',";
				}
        		str = str.substring(0,str.length()-1);
        		query.append("AND x.pm_type IN ("+str+")	");
        	}else{
        		query.getAndQuery("x.pm_type", lovWoPlanAcListDTO.getPmType());
        	}
        }
        
        query.getAndQuery("x.wo_status", lovWoPlanAcListDTO.getWoStatus());
        query.getAndQuery("x.wo_type", lovWoPlanAcListDTO.getWoType());
        query.getOrderByQuery("x.wo_no DESC", lovWoPlanAcListDTO.getOrderBy(), lovWoPlanAcListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(lovWoPlanAcListDTO.getIsLoadMaxCount(), lovWoPlanAcListDTO.getFirstRow()));
	}

	public String findWoListTotalCount(LovWoPlanAcListDTO lovWoPlanAcListDTO, User user,
			Map<String, String> conditionMap) {
		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT															");
        query.append("       COUNT(1)       											");
        query.append("FROM TAWOPLAN x INNER JOIN TAWOEQUIP y							");
        query.append("ON x.comp_no = y.comp_no											");
        query.append(" AND x.wkor_id = y.wkor_id										");
        query.append("		INNER JOIN TAEQUIPMENT z									");
        query.append("      ON y.comp_no = z.comp_no									");
        query.append("       AND y.equip_id = z.equip_id								");
        query.append("WHERE 1=1             											");
        query.getAndQuery("x.comp_no", conditionMap);
        query.getAndQuery("x.wo_status", conditionMap);
        query.append(this.getWhere(lovWoPlanAcListDTO,user));

        List resultList = getJdbcTemplate().queryForList(query.toString());
        
		return QueryBuffer.listToString(resultList);
	}
}