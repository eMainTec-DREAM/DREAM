package common.mafinder.mamstr.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.mafinder.mamstr.dao.LovWoListDAO;
import common.mafinder.mamstr.dto.LovWoListDTO;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;

/**
 * 작업 팝업
 * @author  kim21017
 * @version $Id: LovWoListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
 * @since   1.0
 *
 * @spring.bean id="lovWoListDAOTarget"
 * @spring.txbn id="lovWoListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovWoListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovWoListDAO
{
    /**
     * 작업 검색
     * @author  kim21017
     * @version $Id: LovWoListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovWoListDTO
     * @param loginUser
     * @return
     */
    public List findWoList(LovWoListDTO lovWoListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                    	");
        query.append("       x.wkor_id 										AS wkorId               ");
        query.append("       ,x.wo_no                            			AS woNo                 ");
        query.append("       ,x.description 								AS woDesc				");
        query.append("       ,SFACODE_TO_DESC(x.wo_status,'WO_STATUS','SYS','','"+loginUser.getLangId()+"')	AS woStatusDesc	");
        query.append("       ,z.description	  								AS equipDesc           	");
        query.append("       ,(SELECT c.full_desc					    	                   		");
        query.append("         FROM TAEQLOC c                    									");
        query.append("         WHERE x.comp_no = c.comp_no              	                   		");
        query.append("          AND z.eqloc_id = c.eqloc_id)   				AS eqLocDesc        	");
        query.append("		 ,(SELECT description													");
        query.append("		   FROM TADEPT															");
        query.append("		   WHERE comp_no = x.comp_no											");
        query.append("		    AND dept_id = x.dept_id) 					AS deptDesc				");
        query.append("		 ,SFACODE_TO_DESC(x.wo_type,'WO_TYPE','SYS','','"+loginUser.getLangId()+"') 			AS woTypeDesc	");
        query.append("		 ,SFACODE_TO_DESC(x.pm_type,x.wo_type||'_TYPE','SYS','','"+loginUser.getLangId()+"')	AS pmTypeDesc	");
        query.append("		 ,(SELECT emp_name														");
        query.append("		   FROM TAEMP															");
        query.append("		   WHERE comp_no = x.comp_no											");
        query.append("			AND emp_id = x.emp_id) 						AS empDesc				");
        query.append("		 ,(SELECT c.emp_name													");
        query.append("		   FROM TAEMP c															");
        query.append("		   WHERE x.comp_no = c.comp_no											");
        query.append("			AND z.sub_mng_id = c.emp_id) 				AS subMng				");
        query.append("FROM TAWORKORDER x INNER JOIN TAWOEQUIP y										");
        query.append("ON x.wkor_id = y.wkor_id														");
        query.append(" AND x.comp_no = y.comp_no													");
        query.append("		INNER JOIN TAEQUIPMENT z    											");
        query.append("		ON y.comp_no = z.comp_no      											");
        query.append("		 AND y.equip_id = z.equip_id   											");
        query.append("WHERE 1=1                                                             	    ");
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.append(this.getWhere(lovWoListDTO,loginUser));
        query.getOrderByQuery("x.wo_no", lovWoListDTO.getOrderBy(), lovWoListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(lovWoListDTO.getIsLoadMaxCount(), lovWoListDTO.getFirstRow()));
    }
    private String getWhere(LovWoListDTO lovWoListDTO, User loginUser)
    {        
        QueryBuffer query = new QueryBuffer();
        String startDate = lovWoListDTO.getFilterStartDate();
        String endDate = lovWoListDTO.getFilterEndDate();
        String compNo  =loginUser.getCompNo();

        // 삭제여부
        query.getStringEqualQuery("x.IS_DELETED", "N");
        
        //작업번호
        query.getAndQuery("x.wo_no", lovWoListDTO.getFilterWoNo());
        //작업명
        query.getLikeQuery("x.description", lovWoListDTO.getFilterWkOrDesc());
        //법정설비
        if(!"".equals(lovWoListDTO.getFilterIsLawEq())){
        	query.append("AND x.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 			");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getLikeQuery("is_law_eq", lovWoListDTO.getFilterIsLawEq());
            query.append("									))					");
        }
        //작업일자
        query.getAndDateQuery("x.wkor_date", startDate, endDate);
        //내/외자
        if(!"".equals(lovWoListDTO.getFilterPlfTypeId())||!"".equals(lovWoListDTO.getFilterPlfTypeDesc())){
        	query.append("AND x.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 			");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getSysCdQuery("plf_type", lovWoListDTO.getFilterPlfTypeId(), lovWoListDTO.getFilterPlfTypeDesc(), "PLF_TYPE", compNo,loginUser.getLangId());
            query.append("									))					");
        }
    	//작업종류
    	query.getSysCdQuery("x.wo_type", lovWoListDTO.getFilterWoTypeId(), lovWoListDTO.getFilterWoTypeDesc(), "WO_TYPE", compNo,loginUser.getLangId());
    	//작업형태
    	query.getSysCdQuery("x.pm_type", lovWoListDTO.getFilterPmTypeId(), lovWoListDTO.getFilterPmTypeDesc(), "x.wo_type||'_TYPE'", compNo,loginUser.getLangId());
        //부서
        query.getDeptLevelQuery("x.dept_id", lovWoListDTO.getFilterDeptId(), lovWoListDTO.getFilterDeptDesc(), compNo);
        //설비
        if(!"".equals(lovWoListDTO.getFilterEquipId())||!"".equals(lovWoListDTO.getFilterEquipDesc())){
        	query.append("AND x.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 			");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getCodeLikeQuery("equip_id", "description||item_no", 
            		lovWoListDTO.getFilterEquipId(), lovWoListDTO.getFilterEquipDesc());
            query.append("									))					");
        }
        
        //담당자
        query.getCodeLikeQuery("x.emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.emp_id AND a.comp_no='"+compNo+"')", 
        		lovWoListDTO.getFilterEmpId(), lovWoListDTO.getFilterEmpDesc());
        //관리자(정)
        if(!"".equals(lovWoListDTO.getFilterMainMngId())||!"".equals(lovWoListDTO.getFilterMainMngName())){
        	query.append("AND x.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 			");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getCodeLikeQuery("main_mng_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = main_mng_id AND a.comp_no='"+compNo+"')", 
            		lovWoListDTO.getFilterMainMngId(), lovWoListDTO.getFilterMainMngName());
            query.append("									))					");
        }
        //관리자(부)
        if(!"".equals(lovWoListDTO.getFilterSubMngId())||!"".equals(lovWoListDTO.getFilterSubMngName())){
        	query.append("AND x.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 			");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getCodeLikeQuery("sub_mng_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = sub_mng_id AND a.comp_no='"+compNo+"')", 
            		lovWoListDTO.getFilterSubMngId(), lovWoListDTO.getFilterSubMngName());
            query.append("									))					");
        }
        //위치
        if(!"".equals(lovWoListDTO.getFilterEqLocId())||!"".equals(lovWoListDTO.getFilterEqLocDesc())){
        	query.append("AND x.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 			");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getEqLocLevelQuery("eqloc_id", lovWoListDTO.getFilterEqLocId(), lovWoListDTO.getFilterEqLocDesc(), compNo);
            query.append("									))					");
        }
        //종류
        if(!"".equals(lovWoListDTO.getFilterEqCtgId())||!"".equals(lovWoListDTO.getFilterEqCtgDesc())){
        	query.append("AND x.wkor_id IN (SELECT wkor_id FROM TAWOEQUIP 			");
        	query.append("				WHERE equip_id IN (SELECT equip_id		");
        	query.append("									FROM TAEQUIPMENT	");
        	query.append("									WHERE 1=1			");
        	query.getEqCtgLevelQuery("eqctg_id", lovWoListDTO.getFilterEqCtgId(), lovWoListDTO.getFilterEqCtgDesc(), compNo);
            query.append("									))					");
        }
        //상태
        query.getSysCdQuery("x.wo_status", lovWoListDTO.getFilterWoStatus(), lovWoListDTO.getFilterWoStatusDesc(), "WO_STATUS", compNo,loginUser.getLangId());
        
        query.getCodeLikeQuery("x.self_vendor_type", "SFACODE_TO_DESC(x.self_vendor_type,'SELF_VENDOR_TYPE','SYS','','"+loginUser.getLangId()+"')", lovWoListDTO.getSelfVendorType(), lovWoListDTO.getSelfVendorTypeDesc());
        query.getCodeLikeQuery("x.vendor_id", "SFAIDTODESC(x.vendor_id, '', 'VENDOR', x.comp_no)", lovWoListDTO.getVendorId(), lovWoListDTO.getVendorDesc());
        
//        query.append(this.getWhere(lovWoListDTO,loginUser));
        if(!"".equals(lovWoListDTO.getPmType())){
            if(lovWoListDTO.getPmType().indexOf("|")>-1){
                String[] arr = lovWoListDTO.getPmType().split("\\|");
                String str = "";
                for (int i = 0; i < arr.length; i++) {
                    str += "'"+arr[i]+"',";
                }
                str = str.substring(0,str.length()-1);
                query.append("AND x.pm_type IN ("+str+")    ");
            }else{
                query.getAndQuery("x.pm_type", lovWoListDTO.getPmType());
            }
        }
        
        query.getAndQuery("x.wo_status", lovWoListDTO.getWoStatus());
        query.getAndQuery("x.wo_type", lovWoListDTO.getWoType());
        
        
        return query.toString();
    }
	@Override
	public List findWoAcList(LovWoListDTO lovWoListDTO, User loginUser, Map<String, String> conditionMap) {
		
		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                    	");
        query.append("       x.wkor_id 												AS wkorId       ");
        query.append("       ,x.wo_no 												AS woNo         ");
        query.append("       ,x.description 										AS woDesc		");
        query.append("       ,SFACODE_TO_DESC(wo_status,'WO_STATUS','SYS','','"+loginUser.getLangId()+"')	AS woStatusDesc	");
        query.append("       ,y.item_no  											AS equipNo		");
        query.append("       ,y.description											AS equipDesc	");
        query.append("       ,(SELECT full_desc                                 	                ");
        query.append("         FROM TAEQASMB                                    	              	");
        query.append("         WHERE comp_no = x.comp_no                        	               	");
        query.append("          AND eqasmb_id = x.eqasmb_id)          				AS eqAsmbDesc	");
        query.append("       ,(SELECT c.full_desc					            	               	");
        query.append("         FROM TAEQLOC c                    									");
        query.append("         WHERE x.comp_no = c.comp_no                      	          		");
        query.append("          AND y.eqloc_id = c.eqloc_id )  	 					AS eqLocDesc	");
        query.append("		,(SELECT description													");
        query.append("		  FROM TADEPT															");
        query.append("		  WHERE comp_no = x.comp_no												");
        query.append("			AND dept_id = x.dept_id) 							AS deptDesc		");
        query.append("		,SFACODE_TO_DESC(x.wo_type,'WO_TYPE','SYS','','"+loginUser.getLangId()+"')			AS woTypeDesc	");
        query.append("		,SFACODE_TO_DESC(x.pm_type,x.wo_type||'_TYPE','SYS','','"+loginUser.getLangId()+"')	AS pmTypeDesc	");
        query.append("		,(SELECT emp_name														");
        query.append("		  FROM TAEMP															");
        query.append("		  WHERE comp_no = x.comp_no												");
        query.append("			AND emp_id = x.emp_id) 								AS empDesc		");
        query.append("		,(SELECT c.emp_name														");
        query.append("		  FROM TAEMP c															");
        query.append("		  WHERE x.comp_no = c.comp_no											");
        query.append("			AND y.sub_mng_id = c.emp_id) 						AS subMng		");
        query.append("FROM TAWORKORDER x LEFT OUTER JOIN (SELECT 									");
		query.append("                                    	a.comp_no								");
		query.append("                                      ,a.wkor_id								");
		query.append("                                      ,b.item_no								");
		query.append("                                      ,b.description  						");
		query.append("                                      ,b.eqloc_id								");
		query.append("                                      ,b.sub_mng_id							");
		query.append("                                   FROM TAWOEQUIP a INNER JOIN TAEQUIPMENT b	");
		query.append("                                   ON a.comp_no = b.comp_no      				");
		query.append("                                    AND a.equip_id = b.equip_id				");
		query.append("                                  ) y											");
		query.append("ON x.comp_no = y.comp_no     													");
		query.append("AND x.wkor_id = y.wkor_id														");
        query.append("WHERE 1=1                                                                 	");
        query.getAndQuery("x.comp_no", conditionMap);
        query.getAndQuery("x.wo_status", conditionMap);
        query.append(this.getWhere(lovWoListDTO,loginUser));
        query.getOrderByQuery("x.wo_no", lovWoListDTO.getOrderBy(), lovWoListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(lovWoListDTO.getIsLoadMaxCount(), lovWoListDTO.getFirstRow()));
	}
	
	@Override
	public String findWoListTotalCount(LovWoListDTO lovWoListDTO, User user, Map<String, String> conditionMap)
	{
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT				    										");
        query.append("       COUNT(1)           										");
        query.append("FROM TAWORKORDER x INNER JOIN TAWOEQUIP y							");
        query.append("ON x.wkor_id = y.wkor_id											");
        query.append(" AND x.comp_no = y.comp_no										");
        query.append("		INNER JOIN TAEQUIPMENT z    								");
        query.append("		ON y.comp_no = z.comp_no      								");
        query.append("		 AND y.equip_id = z.equip_id   								");
        query.append("WHERE 1=1                                                         ");
        query.getAndQuery("x.comp_no", conditionMap);
        query.getAndQuery("x.wo_status", conditionMap);
        query.append(this.getWhere(lovWoListDTO,user));

        List resultList = getJdbcTemplate().queryForList(query.toString());
        
		return QueryBuffer.listToString(resultList);
	}
}