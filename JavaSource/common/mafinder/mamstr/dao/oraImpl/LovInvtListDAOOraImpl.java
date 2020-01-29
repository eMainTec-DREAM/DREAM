package common.mafinder.mamstr.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.mafinder.mamstr.dao.LovInvtListDAO;
import common.mafinder.mamstr.dto.LovInvtListDTO;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;

/**
 * 투자 목록 팝업
 * @author  js.lee
 * @version $Id: $
 * @since   1.0
 *
 * @spring.bean id="lovInvtListDAOTarget"
 * @spring.txbn id="lovInvtListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovInvtListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovInvtListDAO
{
    /**
     * 투자 목록 검색
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     * 
     * @param lovInvtListDTO
     * @param loginUser
     * @return
     */
    public List findInvtList(LovInvtListDTO lovInvtListDTO, User user, Map<String, String> conditionMap)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT													");
        query.append("       '' seqNo											");
        query.append("       ,'' isDelCheck										");
        query.append("       ,invtlist_no invtlistNo							");
        query.append("       ,x.description										");
        query.append("       ,invt_categ invtCateg								");
        query.append("       ,SFACODE_TO_DESC(invt_categ,'INVT_CATEG','SYS','','"+user.getLangId()+"') invtCategDesc	");
        query.append("       ,invt_Type invtTypeId								");
        query.append("       ,SFACODE_TO_DESC(invt_Type,'INVT_TYPE','SYS','','"+user.getLangId()+"') invtTypeDesc	");
        query.append("       ,(SELECT full_desc                                 ");
        query.append("         FROM   TAEQCTG                                   ");
        query.append("         WHERE  comp_no = x.comp_no                       ");
        query.append("           AND  eqctg_id = x.eqctg_id) eqCtgDesc			");
        query.append("       ,(SELECT full_desc                                 ");
        query.append("          FROM  TAEQLOC                                   ");
        query.append("          WHERE comp_no = x.comp_no                       ");
        query.append("            AND eqloc_id = x.eqloc_id) eqLocDesc			");
        query.append("       ,x.eqloc_id eqlocId								");
        query.append("       ,x.eqctg_id eqctgId								");
        query.append("       ,x.equip_id equipId								");
        query.append("       ,y.description equipDesc							");
        query.append("       ,x.plant plant								     	");
        query.append("       ,(SELECT a.description								");
        query.append("         FROM   TAPLANT a									");
        query.append("         WHERE  a.comp_no = x.comp_no         		    ");
        query.append("           AND  a.plant = x.plant) plantDesc		    	");
        query.append("       ,x.dept_id deptId									");
        query.append("       ,(SELECT a.description								");
        query.append("         FROM   TADEPT a									");
        query.append("         WHERE  a.dept_id = x.dept_id) deptDesc			");
        query.append("       ,emp_id empId										");
        query.append("       ,(SELECT a.emp_name								");
        query.append("         FROM   TAEMP a									");
        query.append("         WHERE  a.emp_id = x.emp_id) empDesc				");
        query.append("       ,plan_amt planAmt									");
        query.append("       ,plan_sdate planSdate								");
        query.append("       ,plan_edate planEdate								");
        query.append("       ,SFACODE_TO_DESC(invtlist_status,'INVTLIST_STATUS','SYS','','"+user.getLangId()+"') invtStatusDesc	");
        query.append("       ,invtlist_id invtlistId							");
        query.append("       ,end_date endDate									");
        query.append("       ,x.remark            								");
        query.append("       ,(SELECT sum(actual_amt)											");
        query.append("         FROM tainvtphase a												");
        query.append("         WHERE  1=1														");
        query.append("         AND a.invtlist_id = x.invtlist_id 								");
        query.append("         AND a.comp_no = x.comp_no										");
        query.getStringEqualQuery("a.invtphase_status", "C");
        query.append("         GROUP BY a.comp_no , a.invtlist_id) actualAmt				");
        query.append("       ,(SELECT round(sum((CASE WHEN a.invtphase_status='C' THEN 1 ELSE 0 END))/sum(1)*100,2)||'%'		");
        query.append("         FROM tainvtphase a												");
        query.append("         WHERE  1=1														");
        query.append("         AND a.invtlist_id = x.invtlist_id 								");
        query.append("         AND a.comp_no = x.comp_no										");
        query.append("         GROUP BY a.comp_no , a.invtlist_id) actualRate					");
        query.append("FROM   TAINVTLIST x LEFT OUTER JOIN TAEQUIPMENT y 						");
        query.append("                  ON x.equip_id = y.equip_id				");
        query.append("WHERE  1=1               									");
        query.append(" and x.comp_no = '"+user.getCompNo()+"'	");
        query.append(this.getWhere(lovInvtListDTO, user, conditionMap));
        query.getOrderByQuery("x.invtlist_id", lovInvtListDTO.getOrderBy(), lovInvtListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(lovInvtListDTO.getIsLoadMaxCount(), lovInvtListDTO.getFirstRow()));

    }
    private String getWhere(LovInvtListDTO lovInvtListDTO, User user, Map<String, String> conditionParam)
    {        
    	  QueryBuffer query = new QueryBuffer();
          
//    	  String notIn = conditionParam.get("NOT_IN_STATUS");
    	  
          if (!"".equals(lovInvtListDTO.getInvtlistId()))
          {
          	query.getAndNumKeyQuery("x.invtlist_id", lovInvtListDTO.getInvtlistId());
              return query.toString();
          }

//          query.getAndQuery("x.comp_no", conditionParam);
//          query.getAndQuery("x.invtprctp_id", conditionParam);
//          query.getAndQuery("x.invtlist_status", conditionParam);
//          if(!"".equals(notIn) && notIn!=null) {
//              query.append("AND invtlist_status NOT IN("+notIn+")    ");
//          }
          
          //분류
      	  query.getSysCdQuery("x.invt_type", lovInvtListDTO.getFilterInvtType(), lovInvtListDTO.getFilterInvtTypeDesc(), "INVT_TYPE", user.getCompNo(),user.getLangId());
      	  query.getLikeQuery("x.description", lovInvtListDTO.getFilterInvtDesc());
          query.getCodeLikeQuery("x.invtlist_status", "SFACODE_TO_DESC(invtlist_status,'INVTLIST_STATUS','SYS','','"+user.getLangId()+"')", lovInvtListDTO.getFilterInvtStatus(), lovInvtListDTO.getFilterInvtStatusDesc());
          query.getDeptLevelQuery("x.dept_id", lovInvtListDTO.getFilterDeptId(), lovInvtListDTO.getFilterDeptDesc(), user.getCompNo());
          
          query.getCodeLikeQuery("x.emp_id", "(SELECT a.emp_name FROM TAEMP a WHERE a.emp_id = x.emp_id)",lovInvtListDTO.getFilterEmpId(), lovInvtListDTO.getFilterEmpDesc());
          query.getCodeLikeQuery("x.invt_categ", "SFACODE_TO_DESC(invt_categ,'INVT_CATEG','SYS','','"+user.getLangId()+"')",lovInvtListDTO.getFilterInvtCateg(), lovInvtListDTO.getFilterInvtCategDesc());
          query.getEqLocLevelQuery("x.eqloc_id", lovInvtListDTO.getFilterEqlocId(), lovInvtListDTO.getFilterEqlocDesc(), user.getCompNo());
          query.getEqCtgLevelQuery("x.eqctg_id", lovInvtListDTO.getFilterEqctgId(), lovInvtListDTO.getFilterEqctgDesc(), user.getCompNo());
          query.getCodeLikeQuery("x.equip_id", "y.description", lovInvtListDTO.getFilterEquipId(), lovInvtListDTO.getFilterEquipDesc());
          query.getAndDateQuery("x.plan_sdate", lovInvtListDTO.getFilterStartDate(), lovInvtListDTO.getFilterEndDate());
          
          //공장코드
          query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+user.getCompNo()+"' AND a.plant = x.plant )", 
          		lovInvtListDTO.getFilterPlantId(), lovInvtListDTO.getFilterPlantDesc());

          return query.toString();
    }
	
}